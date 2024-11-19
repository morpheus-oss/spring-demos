package dev.shorturl.controller;

import dev.shorturl.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class URLController {

    @Autowired
    private UrlShortenerService shortener;

    @GetMapping("/generate")
    public String generateURL(@RequestParam("url") String actualURL)   {
        return shortener.shortenURL(actualURL);
    }

    @GetMapping("/{shortUrl}")
    public String redirect(@PathVariable("shortUrl") String shortURL)     {
        return shortener.getActualURL(shortURL);
    }

}
