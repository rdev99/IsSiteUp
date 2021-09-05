package com.rdev.demo.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class isSiteUp {

    private final String SITE_UP = "Site is up and running !";
    private final String SITE_DOWN = "OOPS ! Site is down !";
    private final String INCORRECT_URL = "OOPS ! Incorrect URL !";
    
    @GetMapping("/check")
    public String isUrlUp(@RequestParam String url) {
        String returnmessage = "";

        try {
            URL urlobj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlobj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int res = conn.getResponseCode();
            res/=100;
            if(res>=4)
            {
                returnmessage=SITE_DOWN;
            }
            else
            {
                returnmessage=SITE_UP;
            }
        } catch (MalformedURLException e) {
            returnmessage = INCORRECT_URL;
        } catch (IOException e) {
            returnmessage=SITE_DOWN;
        }

        return returnmessage;
    }
}
