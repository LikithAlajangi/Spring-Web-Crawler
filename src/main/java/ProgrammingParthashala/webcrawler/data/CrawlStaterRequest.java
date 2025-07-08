package ProgrammingParthashala.webcrawler.data;

import org.springframework.stereotype.Component;


public class CrawlStaterRequest {

    private String seedURL;

    public String getSeedURL(){return seedURL;}
    public void setSeedURL(String url){
        this.seedURL = url;
    }
}
