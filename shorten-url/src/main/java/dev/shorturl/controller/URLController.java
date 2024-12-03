package dev.shorturl.controller;

import dev.shorturl.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class URLController {

    @Autowired
    private UrlShortenerService shortener;

    @PostMapping("/generate")
    public ResponseEntity<Object> generateURL(@RequestParam("url") String actualURL)   {
        if (!isValidURL(actualURL))   {
            return new ResponseEntity<>("Invalid URL: " + actualURL, HttpStatus.BAD_REQUEST);
        }
        String shortURL = shortener.shortenURL(actualURL);
        return new ResponseEntity<>(shortURL, HttpStatus.OK);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Object> redirect(@PathVariable("shortUrl") String shortURL)     {
        Optional<String> actualURL = Optional.ofNullable(shortener.getActualURL(shortURL));
        ResponseEntity<Object> response = null;

        response = actualURL.<ResponseEntity<Object>>map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(
                "Provided short URL (" + shortURL + ") not found!", HttpStatus.NOT_FOUND));

        return response;
    }

    private boolean isValidURL(String url)  {
        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
        return true;
    }

}
