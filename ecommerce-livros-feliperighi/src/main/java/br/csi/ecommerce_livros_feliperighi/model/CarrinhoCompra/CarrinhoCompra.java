package br.csi.ecommerce_livros_feliperighi.model.CarrinhoCompra;

import br.csi.ecommerce_livros_feliperighi.model.Cliente.Cliente;
import br.csi.ecommerce_livros_feliperighi.model.Livro.Livro;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "carrinho_compra", uniqueConstraints = @UniqueConstraint(columnNames = {"id_cliente_car", "id_livro_car"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa um item de carrinho de compras no sistema")
public class CarrinhoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do item de carrinho de compras", example = "1")
    private Long idcarrinhocompra;

    @UuidGenerator
    @Schema(description = "UUID do item de carrinho de compras, gerado automaticamente", example = "b1a5b4c4-3c5f-4d9a-8d3e-1f84e3a00f6c")
    private UUID uuid;

    @Column(nullable = false)
    @Schema(description = "Quantidade do livro no carrinho de compras", example = "2")
    private int quantidade;

    // Mapeamento Many-to-One com Cliente
    @ManyToOne
    @JoinColumn(name = "id_cliente_car", nullable = false)
    @Schema(description = "Cliente associado ao item de carrinho", example = "Cliente com ID 1")
    private Cliente cliente;

    // Mapeamento Many-to-One com Livro
    @ManyToOne
    @JoinColumn(name = "id_livro_car", nullable = false)
    @Schema(description = "Livro associado ao item de carrinho", example = "Livro com ID 1")
    private Livro livro;
}

