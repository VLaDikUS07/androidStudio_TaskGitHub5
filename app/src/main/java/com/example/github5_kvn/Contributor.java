package com.example.github5_kvn;

public class Contributor {
    private String login;
    private int contributions;
    private int id;

    // И другие поля
    //String html_url;

    @Override
    public String toString() {
        return "\r\n ID " + id + ": " + login + " (" + contributions + ")";
    }

}
