package com.xavier.webservices.upandrunning.ch04.team;

import com.xavier.webservices.upandrunning.ch01.team.Player;
import com.xavier.webservices.upandrunning.ch01.team.Team;
import org.w3c.dom.NodeList;
import javax.annotation.Resource;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;
import javax.xml.ws.http.HTTPException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by Xavier on 2017/12/20.
 */
@WebServiceProvider
@ServiceMode(value = Service.Mode.MESSAGE)
//Indicate that the service deals with raw XML over HTTP instead of SOAP over HTTP
@BindingType(value = HTTPBinding.HTTP_BINDING)
public class RestfulTeams implements Provider<Source> {

    @Resource
    protected WebServiceContext ws_ctx;

    private Map<String, Team> team_map;
    private List<Team> teams;
    private byte[] team_bytes;
    private static final String file_name="teams.ser";
    private static final String put_post_key = "Cargo";

    public RestfulTeams() {
        read_teams_from_file();
        deserialize();
    }

    private void deserialize() {
        XMLDecoder dec = new XMLDecoder(new ByteArrayInputStream(team_bytes));
        teams = (List<Team>) dec.readObject();

        team_map = Collections.synchronizedMap(new HashMap<String, Team>());
        for(Team team: teams) team_map.put(team.getName(), team);
    }

    private void read_teams_from_file() {
        try {
            String cwd = System.getProperty("user.dir");
            String sep = System.getProperty("file.separator");
            String path = get_file_path();
            int len = (int) new File(path).length();
            team_bytes = new byte[len];
            new FileInputStream(path).read(team_bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String get_file_path() {
        String cwd = System.getProperty("user.dir");
        String sep = System.getProperty("file.separator");
        return cwd+sep+"rc"+sep+"ch04" + sep + "team" + sep + file_name;
    }

    public Source invoke(Source request) {
        if(ws_ctx == null) throw new RuntimeException("DI failed on ws_ctx");;

        MessageContext msg_ctx = ws_ctx.getMessageContext();
        String http_verb = (String) msg_ctx.get(MessageContext.HTTP_REQUEST_METHOD);
        http_verb = http_verb.trim().toUpperCase();
        if(http_verb.equals("GET")) return doGet(msg_ctx);
        else if (http_verb.equals("POST")) return doPost(msg_ctx);
        else if (http_verb.equals("DELETE")) return doDelete(msg_ctx);
        else if (http_verb.equals("PUT")) return doPut(msg_ctx);
        else throw new HTTPException(405); //Method not allowed
    }

    private Source doPut(MessageContext msg_ctx) {
        String query_string = (String) msg_ctx.get(MessageContext.QUERY_STRING);
        String name = null;
        String new_name = null;

        if (query_string == null) throw new HTTPException(403);
        else {
            String[] parts = query_string.split("&");
            if (parts[0] == null || parts[1] == null) throw new HTTPException(403);
            name = get_value_from_qs("name", parts[0]);
            new_name = get_value_from_qs("new_name", parts[1]);
            if(name == null || new_name == null) throw new HTTPException(403);

            Team team = team_map.get("name");
            if(team == null) throw new HTTPException(404);
            team.setName(new_name);
            team_map.put(new_name, team);
            serialize();
        }
        return response_to_client("Team " + name + " changed to " + new_name);
    }

    //http://localhost:8888/teams?name=SmothersBrothers2
    private Source doDelete(MessageContext msg_ctx) {
        String query_string =(String) msg_ctx.get(MessageContext.QUERY_STRING);
        if(query_string == null) throw new HTTPException(403);  //Forbidden - Request refused
        else {
            String name = get_value_from_qs("name", query_string);
            if (!team_map.containsKey(name)) throw new HTTPException(404);  //Resource not found
            Team team = team_map.get(name);
            teams.remove(team);
            team_map.remove(name);
            serialize();
            return response_to_client(name + " deleted.");
        }
    }

    //Headers - Cargo - <create_team><name>SmothersBrothers2</name><players><player><name>Thomas</name><nickname>Tom</nickname></player><player><name>Richard</name><nickname>Dickie</nickname></player></players></create_team>
    private Source doPost(MessageContext msg_ctx) {
        Map<String, List> request = (Map<String, List>) msg_ctx.get(MessageContext
        .HTTP_REQUEST_HEADERS);
        List<String> cargo = request.get(put_post_key);
        if(cargo == null) throw new HTTPException(400); //Bad request - Requested malformed.
        String xml = "";
        for(String next: cargo) xml += next.trim();
        ByteArrayInputStream xml_stream = new ByteArrayInputStream(xml.getBytes());
        String team_name = null;
        try{
            DOMResult dom = new DOMResult();
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            trans.transform(new StreamSource(xml_stream), dom);
            URI ns_URI = new URI("create_team");

            XPathFactory xpf = XPathFactory.newInstance();
            XPath xp = xpf.newXPath();
            xp.setNamespaceContext(new NSResolver("", ns_URI.toString()));
            team_name = xp.evaluate("/create_team/name", dom.getNode());

            if(team_map.containsKey(team_name))
                throw new HTTPException(400);   //Bad request - Request malformed

            List<Player> team_players = new ArrayList<Player>();
            NodeList players = (NodeList) xp.evaluate("player", dom.getNode(), XPathConstants.NODESET);
            
            for(int i = 1; i <= players.getLength(); i++) {
                String name = xp.evaluate("name", dom.getNode());
                String nickname = xp.evaluate("nickname", dom.getNode());
                Player player = new Player(name, nickname);
                team_players.add(player);
            }
            
            Team t = new Team(team_name, team_players);
            team_map.put(team_name, t) ;
            teams.add(t);
            serialize();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return response_to_client("Team " + team_name + " created.");
    }

    private Source response_to_client(String msg) {
        HttpResponse response = new HttpResponse();
        response.setResponse(msg);
        ByteArrayInputStream stream = encode_to_stream(response);
        return new StreamSource(stream);
    }

    private void serialize() {
        try {
            String path = get_file_path();
            BufferedOutputStream out = new BufferedOutputStream((new FileOutputStream(path)));
            XMLEncoder enc = new XMLEncoder(out);
            enc.writeObject(teams);
            enc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Source doGet(MessageContext msg_ctx) {
        String query_string = (String) msg_ctx.get(MessageContext.QUERY_STRING);
        if(query_string == null) {
            return new StreamSource(new ByteArrayInputStream((team_bytes)));
        } else {
            String name = get_value_from_qs("name", query_string);
            Team team = team_map.get(name);
            if(team == null) throw new HTTPException(404);
            ByteArrayInputStream stream = encode_to_stream(team);
            return new StreamSource(stream);
        }
    }

    private ByteArrayInputStream encode_to_stream(Object obj) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        XMLEncoder enc = new XMLEncoder(stream);
        enc.writeObject(obj);
        enc.close();
        return new ByteArrayInputStream(stream.toByteArray());
    }

    private String get_value_from_qs(String key, String qs) {
        String[] parts = qs.split("=");
        if(!parts[0].equalsIgnoreCase(key))
            throw new HTTPException(400); //Bad Request
        return parts[1].trim();
    }
}
