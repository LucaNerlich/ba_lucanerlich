package com.t8y.lucanerlich.tests.volkswagencarnet.com;

import com.jayway.restassured.path.json.JsonPath;
import org.junit.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by lucan on 23.05.2016.
 */
public class TestKundenPortalTracking {

    File harJson = new File("./har-files/visitKundenportal(com.t8y.lucanerlich.tests.volkswagencarnet.com.TestKundenportal)160523-141956.json");

    @Test
    public void TestKPTracking() {
        List<String> urls = JsonPath.from(harJson).get("log.entries.request.url");
        List<ArrayList<String>> queryStrings = JsonPath.from(harJson).get("log.entries.request.queryString");
        List<ArrayList<HashMap>> metricParameter = new ArrayList();
        List<String> metrics = new ArrayList<String>();
        String headers[];

        //"store.book.findAll { book -> book.price >= 5 && book.price <= 15 }")
        for (ArrayList queryString : queryStrings) {
            if (queryString.size() > 0) {
                metricParameter.add(queryString);
            }
        }

        //pattern to get url id and value ([^=]+)=([^&#]+)
        for (String url : urls) {
            if (url.contains("metric")) {
                metrics.add(url);
                String[] parts = url.split("\\?")[1].split("&");
                try {
                    URL url1 = new URL(url);
                    Map xy = splitQuery(url1);
                    System.out.println();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        //regex to get parameter from url
        // [(\?|\&)]([^=]+)\=([^&#]+)

        //See eg. v1 = MS-Car-Net-de-de
        for (ArrayList<HashMap> list : metricParameter) {
            for (HashMap map : list) {
                System.out.println("Map: " + list.iterator());
                System.out.println("    " + map.get("name"));
                System.out.println("    " + map.get("value"));
                System.out.println();

                //assertEquals(map.get("name"), "v1");
                //assertEquals(map.get("value"), "MS-Car-Net-de-de");
            }
        }
    }

    // http://stackoverflow.com/questions/13592236/parse-a-uri-string-into-name-value-collection
    public Map<String, List<String>> splitQuery(URL url) throws UnsupportedEncodingException {
        final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
        final String[] pairs = url.getQuery().split("&");
        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
            if (!query_pairs.containsKey(key)) {
                query_pairs.put(key, new LinkedList<String>());
            }
            final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
            query_pairs.get(key).add(value);
        }
        return query_pairs;
    }
}
