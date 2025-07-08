package ProgrammingParthashala.webcrawler.exceps;

import org.springframework.stereotype.Component;

import java.util.HashMap;
@Component
public class CrawlDB {
    static HashMap<Integer,CrawlResult> results = new HashMap<>();

    public static void pushResults(CrawlResult crawlResult){
        results.put(crawlResult.getCrawlProcessId(),crawlResult);
    }

    public static CrawlResult getResults(int id){
        return results.get(id);
    }

    public static boolean  hasId(int id){
        return results.containsKey(id);
    }
}
