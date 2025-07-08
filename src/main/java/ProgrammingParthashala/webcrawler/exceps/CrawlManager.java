package ProgrammingParthashala.webcrawler.exceps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
@Component
public class CrawlManager {//gives an random token value between (1,100000)

   private static final ExecutorService threadPool = Executors.newFixedThreadPool(5);

    public static CrawlResult start(String seedURL){
        int crawlProcessId=0;
        Random random = new Random();
        while(true){
            crawlProcessId = random.nextInt(1,100000);
            if(!CrawlDB.hasId(crawlProcessId)) {
                break;
            }
        }
         CrawlResult crawlResult = new CrawlResult(seedURL,crawlProcessId);
        threadPool.submit(new FutureTask<CrawlResult>(new CrawlWorker(crawlResult)));
        CrawlDB.pushResults(crawlResult);
        return crawlResult;
    }

    public CrawlResult getResult(int crawlProcessId){
        return CrawlDB.getResults(crawlProcessId);
    }

}
