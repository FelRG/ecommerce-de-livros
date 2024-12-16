package br.csi.ecommerce_livros_feliperighi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "API Ecommerce de Livros",
				version = "1.0",
				description = "Documentação da API Ecommerce de Livros",
				contact = @Contact(name = "Suporte", email = "suporte@exemplo.com")
		)
)

@SpringBootApplication
public class EcommerceLivrosFelipeRighiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceLivrosFelipeRighiApplication.class, args);}

	/*@Bean
	public CommandLineRunner demo(ClienteRepository repository){
		return (args) ->{
			repository.save(new Cliente("Felipe", "felipe@gmail.com", "1234", new Date(), true));
		};
	}*/

}
