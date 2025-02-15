package dev.shorturl.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import dev.shorturl.service.UrlShortenerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class URLControllerTest {

    @Mock
    private UrlShortenerService urlShortenerService;

    @InjectMocks
    private URLController urlController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(urlController).build();
    }

    @Test
    void testShortenUrl() throws Exception {
        String originalUrl = "https://www.example.com";
        String shortCode = "abc123";

        when(urlShortenerService.getShortCode(originalUrl)).thenReturn(shortCode);

        mockMvc.perform(post("/shorturl/url")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\": \"" + originalUrl + "\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(shortCode));
    }

    @Test
    void testGetOriginalUrl() throws Exception {
        String shortCode = "abc123";
        String originalUrl = "https://www.example.com";

        when(urlShortenerService.getActualURL(shortCode)).thenReturn(originalUrl);

        mockMvc.perform(get("/shorturl/url/" + shortCode))
                .andExpect(status().isOk())
                .andExpect(content().string(originalUrl));
    }
}

