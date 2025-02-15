package dev.shorturl.config;

import dev.shorturl.config.RedisConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RedisConfigTest {

    @InjectMocks
    private RedisConfig redisConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRedisTemplateBean() {
        RedisConnectionFactory connectionFactory = mock(RedisConnectionFactory.class);

        RedisTemplate<String, String> redisTemplate = redisConfig.stringRedisTemplate(connectionFactory);

        assertNotNull(redisTemplate);
        assertEquals(String.class, redisTemplate.getKeySerializer().getClass());
        assertEquals(String.class, redisTemplate.getValueSerializer().getClass());
    }
}

