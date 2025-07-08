package ProgrammingParthashala.webcrawler.data;

import ProgrammingParthashala.webcrawler.exceps.CrawlProcessStatus;
import ProgrammingParthashala.webcrawler.exceps.CrawlResult;

import java.util.List;
import java.util.Set;

public class ResultsFetcherResponse {
    public CrawlResult getCrawlResult() {
        return crawlResult;
    }

    private final CrawlResult crawlResult;
//    private final List<String> resultURLs;
//    private final CrawlProcessStatus resultStatus;
//    private final int resId;
//    private final String sourceURL;
    public ResultsFetcherResponse(CrawlResult res) {
        this.crawlResult = res;
//        resultURLs = res.getUrlsFound();
//        resultStatus = res.getCrawlProcessStatus();
//        resId = res.getCrawlProcessId();
//        sourceURL = res.getSeedURL();
    }
}
