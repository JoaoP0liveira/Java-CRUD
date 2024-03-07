// set set path=%path%;C:\Users\DevLibbs\Downloads\jdk-11.0.1\bin
// cd Download
// java -jar lombok.jar
package br.com.pagamento.sistema.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Entidade inicando que é Objeto
@Data // Gera automaticamente o getts and setters
@NoArgsConstructor // Nao recebe argumentos
public class Pagamento {
	@Id
	@GeneratedValue
	private Long id;

	private String nome;
	private String localizacao;
	private double valor;
}
