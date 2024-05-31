package dev.shorturl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestShortenUrlApplication {

/*
	@Bean
	@ServiceConnection(name = "redis")
	GenericContainer<?> redisContainer() {
		return new GenericContainer<>(DockerImageName.parse("redis:latest")).withExposedPorts(6379);
	}
*/

	public static void main(String[] args) {
		SpringApplication.from(ShortenUrlApplication::main).with(TestShortenUrlApplication.class).run(args);
	}

}
