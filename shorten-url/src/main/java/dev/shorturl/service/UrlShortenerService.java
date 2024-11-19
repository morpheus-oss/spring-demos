package dev.shorturl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UrlShortenerService {

    @Autowired
    private StringRedisTemplate redisTemplate;
//    private final ConcurrentHashMap<String, String> urlMap = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public String shortenURL(String actualURL)  {
        long id = counter.getAndIncrement();
        String shortURL = encode(id);
        redisTemplate.opsForValue().set(shortURL, actualURL);
//        urlMap.put(shortURL, actualURL);
        return shortURL;
    }

    public String getActualURL(String shortURL) {
        return redisTemplate.opsForValue().get(shortURL);
//        return urlMap.get(shortURL);
    }

    private static final String BASE62_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private String encode(long num) {
        StringBuilder encodedStr = new StringBuilder();
        if (num == 0)   {
            encodedStr.insert(0, BASE62_CHARACTERS.charAt(0));
        }
        while (num > 0) {
            int remainder = (int) (num % 62);
            encodedStr.insert(0, BASE62_CHARACTERS.charAt(remainder));
            num /= 62;
        }
        return encodedStr.toString();
    }

    public static long decode(String base62Str) {
        long num = 0;

        for (int i = 0; i < base62Str.length(); i++) {
            num = num * 62 + BASE62_CHARACTERS.indexOf(base62Str.charAt(i));
        }

        return num;
    }
}
