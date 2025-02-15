package dev.shorturl.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class UrlShortenerService {

    Logger logger = LoggerFactory.getLogger(UrlShortenerService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;
    private final AtomicLong counter = new AtomicLong();

    private final String PREFIX = "shorturl:";
    private final String PREFIX_REV = "shorturl-rev:";

    public String getShortCode(String actualURL)  {
        long id = counter.getAndIncrement();
        String shortCode = encode(id);
        logger.info("Short code {0} generated for URL {1}.", shortCode, actualURL);

        redisTemplate.opsForValue().set(PREFIX + shortCode, actualURL);
        redisTemplate.opsForValue().set(PREFIX_REV + actualURL, shortCode);
        return shortCode;
    }

    public String getActualURL(String shortURL) {
        return redisTemplate.opsForValue().get(PREFIX + shortURL);
    }

    public String lookupShortCode(String actualURL) {
        return redisTemplate.opsForValue().get(PREFIX_REV + actualURL);
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
