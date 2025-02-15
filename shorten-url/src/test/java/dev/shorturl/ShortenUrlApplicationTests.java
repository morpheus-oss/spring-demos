package dev.shorturl;


import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class ShortenUrlApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
    }

	@Test
	void testMain() {
		// Mocking SpringApplication to avoid starting the actual app
		try (ConfigurableApplicationContext context = mock(ConfigurableApplicationContext.class)) {
			SpringApplication mockSpringApplication = mock(SpringApplication.class);
			when(mockSpringApplication.run()).thenReturn(context);

			ShortenUrlApplication.main(new String[] {});
			verify(mockSpringApplication, times(1)).run();
		}
	}

}
