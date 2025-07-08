package ProgrammingParthashala.webcrawler.exceps;

import ProgrammingParthashala.webcrawler.data.CrawlStarterResponse;
import ProgrammingParthashala.webcrawler.data.CrawlStaterRequest;
import ProgrammingParthashala.webcrawler.data.ResultsFetcherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/crawl")
public class CrawlController {
@Autowired
private CrawlManager crawlManager;

@RequestMapping(method = RequestMethod.POST,path = "/start")
public CrawlStarterResponse startCrawling(@RequestBody CrawlStaterRequest crawlStaterRequest){
if(crawlStaterRequest.getSeedURL()==null){
    throw new NullPointerException("Seed URL is null");
}

CrawlResult crawlResult = CrawlManager.start(crawlStaterRequest.getSeedURL());//got an crawlResult with token

    CrawlStarterResponse crawlStarterResponse = new CrawlStarterResponse(crawlResult.getCrawlProcessStatus(),crawlResult.getCrawlProcessId());
return crawlStarterResponse;
}

@RequestMapping(method = RequestMethod.GET,path = "/results")
    public ResultsFetcherResponse getResults(@RequestParam int id){
    if(id<=0){
        throw new IllegalArgumentException("crawlProcessId: "+id + " is negative");
    }

    CrawlResult crawlResult = crawlManager.getResult(id);
  return new ResultsFetcherResponse(crawlResult);
}

}
