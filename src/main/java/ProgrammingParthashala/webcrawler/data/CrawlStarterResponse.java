package ProgrammingParthashala.webcrawler.data;

import ProgrammingParthashala.webcrawler.exceps.CrawlProcessStatus;


public class CrawlStarterResponse {
    public int getCrawlProcessId() {
        return crawlProcessId;
    }

    public String getMessage() {
        return message;
    }
    private final int crawlProcessId;
    private final String message;
    private CrawlProcessStatus crawlProcessStatus;
    public CrawlProcessStatus getCrawlProcessStatus() {
        return crawlProcessStatus;
    }

    public void setCrawlProcessStatus(CrawlProcessStatus crawlProcessStatus) {
        this.crawlProcessStatus = crawlProcessStatus;
    }


    public CrawlStarterResponse(CrawlProcessStatus crawlProcessStatus, Integer processId) {
this.crawlProcessStatus = crawlProcessStatus;
        crawlProcessId = processId;
message = "Started Crawling..call /results endpoint to know your results once ready :)";
    }
}
