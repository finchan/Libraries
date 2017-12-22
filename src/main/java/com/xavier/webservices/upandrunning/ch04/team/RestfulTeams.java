package com.xavier.webservices.upandrunning.ch04.team;

import com.xavier.webservices.upandrunning.ch01.team.Team;

import javax.annotation.Resource;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;
import javax.xml.ws.http.HTTPException;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final String file_name="team_ser";

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
//        else if (http_verb.equals("POST")) return doPost(msg_ctx);
//        else if (http_verb.equals("PUT")) return doPut(msg_ctx);
//        else if (http_verb.equals("DELETE")) return doDelete(msg_ctx);
        else throw new HTTPException(405); //Method not allowed
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
