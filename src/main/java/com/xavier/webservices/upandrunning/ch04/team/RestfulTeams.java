package com.xavier.webservices.upandrunning.ch04.team;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPException;
import java.io.ByteArrayInputStream;

/**
 * Created by Xavier on 2017/12/20.
 */
@WebServiceProvider
@ServiceMode(value = Service.Mode.MESSAGE)
public class RestfulTeams implements Provider<Source> {

    @Resource
    protected WebServiceContext ws_ctx;

    private Map<String, Team> team_map;
    private List<Team> teams;
    private byte[] team_bytes;
    private static final String final_name="team_ser";

    public RestfulTeams() {
        read_teams_from_file();
        deserialize();
    }

    public Source invoke(Source request) {
        if(ws_ctx == null) throw new RuntimeException("DI failed on ws_ctx");;

        MessageContext msg_ctx = ws_ctx.getMessageContext();
        String http_verb = (String) msg_ctx.get(MessageContext.HTTP_REQUEST_METHOD);
        http_verb = http_verb.trim().toUpperCase();
        if(http_verb.equals("GET")) return doGet(msg_ctx);
        else throw new HTTPException(405); //Method not allowed
    }

    private Source doGet(MessageContext msg_ctx) {
        String query_string = (String) msg_ctx.get(MessageContext.QUERY_STRING);
        if(query_string == null) {
            return new StreamSource(new ByteArrayInputStream((team_bytes)));
        } else {
            String name = get_value_from_qs("name", query_string);
        }
        Team team = team_map.get("name");
        if(team == null) throw new HTTPException(404);
        ByteArrayInputStream stream = encode_to_stream(team);
        return new StreamSource(stream);
    }
}
