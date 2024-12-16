package br.csi.ecommerce_livros_feliperighi.model.Livro;

import br.csi.ecommerce_livros_feliperighi.model.Cliente.Cliente;
import br.csi.ecommerce_livros_feliperighi.model.CarrinhoCompra.CarrinhoCompra;
import br.csi.ecommerce_livros_feliperighi.model.ItensCompra.ItensCompra;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa um livro no sistema")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do livro", example = "1")
    private Long idlivro;

    @UuidGenerator
    @Schema(description = "UUID do livro", example = "e7eaa4dc-d5b4-4b8d-bf04-08f8e1731d4b")
    private UUID uuid;

    @NonNull
    @NotBlank
    @Size(max = 255, message = "Título deve ter no máximo 255 caracteres")
    @Schema(description = "Título do livro", example = "O Senhor dos Anéis")
    private String titulo;

    @NonNull
    @NotBlank
    @Size(max = 255, message = "Autor deve ter no máximo 255 caracteres")
    @Schema(description = "Autor do livro", example = "J.R.R. Tolkien")
    private String autor;

    @NonNull
    @NotBlank
    @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
    @Schema(description = "Descrição do livro", example = "Uma obra de fantasia épica.")
    private String descricao;

    @NonNull
    @NotNull
    @Schema(description = "Preço do livro", example = "39.90")
    private BigDecimal preco;

    @NonNull
    @NotNull
    @Schema(description = "Indica se o livro está à venda", example = "true")
    private Boolean esta_a_venda;


    @Size(max = 255, message = "URL deve ter no máximo 255 caracteres")
    @Schema(description = "URL da imagem do livro", example = "http://example.com/livro.jpg")
    private String url;

    @NonNull
    @NotNull
    @Schema(description = "Quantidade de livros disponíveis", example = "10")
    private Integer qntd_livros;

    // Mapeamento Many-to-One com Cliente
    @Schema(description = "ID do cliente proprietário do livro", example = "1")
    @ManyToOne
    @JoinColumn(name = "id_cliente_dono")
    private Cliente clienteDono;

    // Mapeamento One-to-Many com CarrinhoCompra
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Lista de itens de carrinho que contêm este livro")
    private List<CarrinhoCompra> carrinhoCompras;

    // Mapeamento One-to-Many com ItensCompra
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Lista de itens de compra que contêm este livro")
    private List<ItensCompra> itensCompra;
}

