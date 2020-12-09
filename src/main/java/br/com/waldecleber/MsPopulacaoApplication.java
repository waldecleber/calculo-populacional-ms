package br.com.waldecleber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsPopulacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPopulacaoApplication.class, args);
	}

}
