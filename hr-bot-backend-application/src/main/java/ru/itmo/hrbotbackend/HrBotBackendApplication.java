package ru.itmo.hrbotbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Точка входа в приложение.
 */
@SpringBootApplication(scanBasePackages = {
		"ru.itmo.hrbotbackend"
})
public class HrBotBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(HrBotBackendApplication.class, args);
	}
}
