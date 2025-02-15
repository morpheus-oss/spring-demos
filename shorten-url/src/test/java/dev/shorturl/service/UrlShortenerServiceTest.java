package dev.shorturl.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UrlShortenerServiceTest {

    @Mock
    private RedisTemplate<String, String> redisTemplate;

    @InjectMocks
    private UrlShortenerService urlShortenerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShortenUrl() {
        String originalUrl = "https://www.example.com";
        String shortCode = "abc123";

        // Simulate storing in Redis
        doNothing().when(redisTemplate).opsForValue().set(shortCode, originalUrl);

        String generatedCode = urlShortenerService.getShortCode(originalUrl);

        assertNotNull(generatedCode);
        verify(redisTemplate, times(1)).opsForValue().set(generatedCode, originalUrl);
    }

    @Test
    void testGetOriginalUrl() {
        String shortCode = "abc123";
        String originalUrl = "https://www.example.com";

        when(redisTemplate.opsForValue().get(shortCode)).thenReturn(originalUrl);

        String result = urlShortenerService.getActualURL(shortCode);

        assertEquals(originalUrl, result);
        verify(redisTemplate, times(1)).opsForValue().get(shortCode);
    }
}

