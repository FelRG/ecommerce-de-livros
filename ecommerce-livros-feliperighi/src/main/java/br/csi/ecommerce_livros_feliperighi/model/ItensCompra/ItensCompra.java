package br.csi.ecommerce_livros_feliperighi.model.ItensCompra;

import br.csi.ecommerce_livros_feliperighi.model.Compra.Compra;
import br.csi.ecommerce_livros_feliperighi.model.Livro.Livro;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "itens_compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa um item de compra no sistema")
public class ItensCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do item de compra", example = "1")
    private Long iditenscompra;

    @UuidGenerator
    @Schema(description = "UUID do item de compra, gerado automaticamente", example = "c3b4e5f8-4a9e-47db-9f1e-5c123abc7890")
    private UUID uuid;

    @Column(nullable = false)
    @Schema(description = "Quantidade do livro comprado", example = "3")
    private int quantidade;

    @Column(nullable = false, precision = 10, scale = 2)
    @Schema(description = "Preço unitário do livro no momento da compra", example = "29.90")
    private BigDecimal preco;

    // Mapeamento Many-to-One com Livro
    @ManyToOne
    @JoinColumn(name = "id_livro_it", nullable = false)
    @Schema(description = "Livro associado ao item de compra", example = "Livro com ID 1")
    private Livro livro;

    // Mapeamento Many-to-One com Compra
    @ManyToOne
    @JoinColumn(name = "id_compra_it", nullable = false)
    @Schema(description = "Compra à qual o item pertence", example = "Compra com ID 1")
    private Compra compra;
}

