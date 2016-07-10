package com.goda5.zooplar.web;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Controller
public class HomeController {
    @Value("${app.api.key}")
    private String key;
    @Value("${app.api.url}")
    private String url;
    @Value("${app.api.datatypes.areaprice}")
    private String areaPrice;

    private LoadingCache<String, String> areaPriceData = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.HOURS)
            .build(
                    new CacheLoader<String, String>() {
                        public String load(String key) throws IOException {
                            return queryAreaPrice(key);
                        }
                    });

    @RequestMapping(value = "/areaPrice", produces = "application/xml")
    @ResponseBody
    private String areaPrice(@RequestParam String postcode) throws IOException, ExecutionException {
        return areaPriceData.get(postcode);
    }

    private String queryAreaPrice(String postcode) throws IOException {
        return Request.Get(url + areaPrice + "?api_key=" + key + "&area=" + postcode + "&output_type=outcode")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnContent().asString();
    }

    @RequestMapping("/")
    public String home() {
        return "home.html";
    }
}
