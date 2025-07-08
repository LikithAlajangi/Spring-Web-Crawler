package ProgrammingParthashala.webcrawler.exceps;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

public class CrawlWorker implements Callable<CrawlResult> {
    private final CrawlResult crawlResult;
    private final int maxDepth;
    public CrawlWorker(CrawlResult crawlResult) {
        this.crawlResult = crawlResult;
        this.maxDepth = 1;
    }


    @Override
    public CrawlResult call() throws Exception {
        Set<String> visitedURLs = new HashSet<>();
        System.out.println("Starting Crawling!!");
        int depth =0;
        crawl(crawlResult, crawlResult.getSeedURL(),visitedURLs,depth);//get all links using Jsoup
        crawlResult.setCrawlProcessStatus(CrawlProcessStatus.FINISHED);
        System.out.println("crawl Finished "+ crawlResult.getCrawlProcessId()+ " "+ crawlResult.getCrawlProcessStatus());
        return crawlResult;
    }


    public void crawl(CrawlResult crawlResult, String url, Set<String> visitedURLs, int currDepth){
        //System.out.println("currDepth: "+currDepth + " max Depth: "+maxDepth);
        if(currDepth>maxDepth || visitedURLs.contains(url)){
          return;
        }
        System.out.println("new URL: "+url);
        visitedURLs.add(url);
        crawlResult.addURL(url);
        try {
          Document document = Jsoup.connect(url).get();
          Elements links = document.select("a[href]");
          for(Element link : links){
              String absoluteUrl = link.absUrl("href");
              if(!visitedURLs.contains(absoluteUrl) && absoluteUrl.startsWith("https://")){
                  //System.out.println(absoluteUrl);
                  crawl(crawlResult,absoluteUrl,visitedURLs,currDepth+1);//Recursive call
              }
          }
        } catch (IOException e) {
            System.out.println("failed to crawl: "+ url);
        }
    }
}
