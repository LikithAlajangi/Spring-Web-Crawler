package ProgrammingParthashala.webcrawler.exceps;



import java.util.ArrayList;
import java.util.List;

public class CrawlResult {
    private final String seedURL;
   private final int crawlProcessId;//token to get results
   private CrawlProcessStatus crawlProcessStatus;

    public List<String> getUrlsFound() {
        return urlsFound;
    }

    private final List<String> urlsFound;

    public CrawlResult(String seedURL, int crawlProcessId) {
        this.seedURL = seedURL;
        this.crawlProcessId = crawlProcessId;
        crawlProcessStatus = CrawlProcessStatus.IN_PROGRESS;
        urlsFound=new ArrayList<>();
    }

    public String getSeedURL() {
        return seedURL;
    }
    public void addURL(String url) {
        urlsFound.add(url);
    }

    public Integer getCrawlProcessId() {
        return crawlProcessId;
    }

    public void setCrawlProcessStatus(CrawlProcessStatus crawlProcessStatus) {
        this.crawlProcessStatus = crawlProcessStatus;
    }

    public CrawlProcessStatus getCrawlProcessStatus() {
        return this.crawlProcessStatus;
    }
}
