package dev.shorturl.controller;

import dev.shorturl.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class URLController {

    @Autowired
    private UrlShortenerService shortener;

    @PostMapping("/generateURL")
    public ResponseEntity<String> generateURL(@RequestBody String actualURL)   {
        String shortURL = shortener.shortenURL(actualURL);
        return new ResponseEntity<>(shortURL, HttpStatus.CREATED);
    }

    @GetMapping("/redirect")
    public ResponseEntity<Void> redirect(@PathVariable String shortURL)     {
        String actualURL = shortener.getActualURL(shortURL);
        if (actualURL == null)  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", actualURL).build();
    }

}
