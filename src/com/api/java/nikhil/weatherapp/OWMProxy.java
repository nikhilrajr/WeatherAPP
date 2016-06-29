package com.api.java.nikhil.weatherapp;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;

public class OWMProxy {

	private String ip;
    private int port;
    private String user;
    private String pass;

    public OWMProxy(String ip, int port, String user, String pass) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pass = pass;
    }

    public Proxy getProxy() {
        Proxy proxy = null;

        if (ip != null && (! "".equals(ip)) && port != Integer.MIN_VALUE) {
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
        }

        if (user != null && (! "".equals(user)) && pass != null && (! "".equals(pass))) {
            Authenticator.setDefault(getAuthenticatorInstance(user, pass));
        }

        return proxy;
    }

    private Authenticator getAuthenticatorInstance(final String user, final String pass) {
        Authenticator authenticator = new Authenticator() {

            public PasswordAuthentication getPasswordAuthentication() {
                return (new PasswordAuthentication(user, pass.toCharArray()));
            }
        };

        return authenticator;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
