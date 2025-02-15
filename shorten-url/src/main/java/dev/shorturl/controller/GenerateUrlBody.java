package dev.shorturl.controller;

public class GenerateUrlBody {

    private String url;

    public GenerateUrlBody()    {}

    public GenerateUrlBody(String url)  {
        super();
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
