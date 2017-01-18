package nl.chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class OnlineChessApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineChessApplication.class, args);
	}
}
