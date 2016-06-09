package com.ircclient;

import java.io.Serializable;

/**
 * Created by pfff on 15.05.2016.
 */
public class Server implements Serializable {
    private String serverName;
    private String serverHostname;
    private String nick;
    private String userName;
    private String realName;
    private int port;

    public Server() {
        this.serverName = "";
        this.port = 0;
        this.serverHostname = "";
    }

    public Server(String serverName, String serverHostname, int port, String nick, String userName, String realName) {
        this.serverName = serverName;
        this.port = port;
        this.serverHostname = serverHostname;
        this.nick = nick;
        this.userName = userName;
        this.realName = realName;
    }

    public String getServerName() {
        return serverName;
    }

    public String getServerHostname() {
        return serverHostname;
    }

    public int getPort() {
        return port;
    }

    public String getNick() {
        return nick;
    }

    public String getUserName() {
        return userName;
    }

    public String getRealName() {
        return realName;
    }
}
