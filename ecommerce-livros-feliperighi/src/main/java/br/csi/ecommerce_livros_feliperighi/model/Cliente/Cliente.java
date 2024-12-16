package br.csi.ecommerce_livros_feliperighi.model.Cliente;

import br.csi.ecommerce_livros_feliperighi.model.CarrinhoCompra.CarrinhoCompra;
import br.csi.ecommerce_livros_feliperighi.model.Compra.Compra;
import br.csi.ecommerce_livros_feliperighi.model.Livro.Livro;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "Cliente")
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa um cliente no sistema")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do cliente", example = "1")
    private Long idcliente;

    @UuidGenerator
    @Schema(description = "UUID do cliente", example = "a9e5f1e4-5b6f-4e0c-9b2f-08f8e1731d4b")
    private UUID uuid;

    @NonNull
    @NotBlank
    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
    @Schema(description = "Nome do cliente", example = "Felipe")
    private String nome;

    @NonNull
    @NotBlank
    @Size(max = 255, message = "Email deve ter no máximo 255 caracteres")
    @Email(message = "Email inválido")
    @Column(unique = true)
    @Schema(description = "Email do cliente", example = "felipe@example.com")
    private String email;

    @NonNull
    @NotBlank
    @Size(max = 255, message = "Senha deve ter no máximo 255 caracteres")
    @Schema(description = "Senha do cliente", example = "senha123")
    private String senha;

    @NonNull
    @NotBlank
    @Size(max = 20, message = "permissao deve ter no máximo 20 caracteres")
    @Schema(description = "Nome da permissao", example = "ROLE_ADMIN")
    private String permissao;

    @NonNull
    @NotNull
    @Temporal(TemporalType.DATE)
    @Schema(description = "Data de cadastro do cliente", example = "2024-11-06")
    private Date datacadastro;

    @Schema(description = "Status de atividade do cliente", example = "true")
    private Boolean ativo;

    // Relacionamento com Livro (OneToMany)
    @OneToMany(mappedBy = "clienteDono", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Lista de livros pertencentes ao cliente")
    private List<Livro> livros;

    // Relacionamento com CarrinhoCompra (OneToMany)
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Lista de itens no carrinho de compras do cliente")
    private List<CarrinhoCompra> carrinhoCompras;

    // Relacionamento com Compra (OneToMany)
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Lista de compras realizadas pelo cliente")
    private List<Compra> compras;
}

