package com.goda5.zooplar.web;

import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class HomeController {
    @Value("${app.api.key}")
    private String key;
    @Value("${app.api.url}")
    private String url;
    @Value("${app.api.datatypes.areaprice}")
    private String areaPrice;

    @RequestMapping(value = "/areaPrice", produces = "application/xml")
    @ResponseBody
    private String areaPrice(@RequestParam String postcode) throws IOException {
        return Request.Get(url + areaPrice + "?api_key=" + key + "&area=" + postcode + "&output_type=outcode")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnContent().asString();
    }
}
