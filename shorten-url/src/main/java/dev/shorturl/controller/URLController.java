package dev.shorturl.controller;

import dev.shorturl.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/shorturl")
public class URLController {

    Logger logger = LoggerFactory.getLogger(URLController.class);

    @Autowired
    private UrlShortenerService shortener;

    @RequestMapping(
        path = "/url",
        method = RequestMethod.POST
//        consumes = MediaType.APPLICATION_JSON_VALUE,
//        produces = {
//                MediaType.APPLICATION_XML_VALUE,
//                MediaType.APPLICATION_JSON_VALUE,
//                MediaType.APPLICATION_
//        }
    )
    public ResponseEntity<Object> generateURL(@RequestHeader HttpHeaders headers,
                                              @RequestBody GenerateUrlBody reqBody) {
        if (!isValidURL(reqBody.getUrl())) {
            return new ResponseEntity<>("Invalid URL: " + reqBody.getUrl(), HttpStatus.BAD_REQUEST);
        }
        logger.info("Generate Short URL for URL: {0}.", reqBody.getUrl());

        String shortCode = shortener.lookupShortCode(reqBody.getUrl());
        if (shortCode == null || shortCode.isEmpty())   {
            shortCode = shortener.getShortCode(reqBody.getUrl());
        }

        String shorturl = constructShortUrl(Objects.requireNonNull(headers.getHost()), shortCode);

        return new ResponseEntity<>(shorturl, HttpStatus.OK);
    }

    @GetMapping("/url/{shortUrl}")
    public ResponseEntity<Object> getURL(@PathVariable("shortUrl") String shortURL) {
        logger.info("Get actual URL for the short URL: {0}", shortURL);
        Optional<String> actualURL = Optional.ofNullable(shortener.getActualURL(shortURL));
        logger.info("Actual URL for the short URL {0} is {1}.", shortURL, actualURL.orElse(""));

        ResponseEntity<Object> response = null;
        response = actualURL.<ResponseEntity<Object>>map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(
                        "Provided short URL (" + shortURL + ") not found!", HttpStatus.NOT_FOUND));

        return response;
    }

    @PutMapping("/url/{shortUrl}")
    public void redirect(@PathVariable("shortUrl") String shortURL,
                         @RequestParam("redirect") String redirect,
                         HttpServletResponse response) throws IOException {
        logger.info("Redirect for the short URL: {0}", shortURL);
        if (!Objects.equals("true", redirect))   {
            getURL(shortURL);
        }

        String actualUrl = shortener.getActualURL(shortURL);
        response.sendRedirect(actualUrl);
    }

    @GetMapping("/health")
    public String health() {
        return "Request hit /health";
    }

    private String constructShortUrl(InetSocketAddress host, String shortCode)  {
        return "http://" + host.getHostName() + ":" +
                host.getPort() + "/shorturl/url" + shortCode;
    }

    private boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
        return true;
    }

}
