package br.com.pagamento.sistema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// anottation => indica que é um projeto SpringBoot
@SpringBootApplication
public class SistemaApplication {

	public static void main(String[] args) {
		// ele crua um contexto que é uma aplicação Spring~
		// varredura, das classes antes de iniciar 
		// inicia a Aplicação
		SpringApplication.run(SistemaApplication.class, args);
	}

}
