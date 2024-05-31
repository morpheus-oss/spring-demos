package dev.shorturl.service;

import dev.shorturl.Base62Encoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UrlShortenerService {
    private ConcurrentHashMap<Long, String> urlMap = new ConcurrentHashMap<>();
    private AtomicLong counter = new AtomicLong(1);

    public String shortenURL(String actualURL)  {
        long id = counter.getAndIncrement();
        String shortURL = Base62Encoder.encode(id);
        urlMap.put(id, actualURL);
        return shortURL;
    }

    public String getActualURL(String shortURL) {
        long id = Base62Encoder.decode(shortURL);
        return urlMap.get(id);
    }
}
