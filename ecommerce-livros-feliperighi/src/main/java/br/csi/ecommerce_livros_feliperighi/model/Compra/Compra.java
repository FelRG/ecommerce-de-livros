package br.csi.ecommerce_livros_feliperighi.model.Compra;

import br.csi.ecommerce_livros_feliperighi.model.Cliente.Cliente;
import br.csi.ecommerce_livros_feliperighi.model.ItensCompra.ItensCompra;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa uma compra no sistema")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único da compra", example = "1")
    private Long idcompra;

    @UuidGenerator
    @Schema(description = "UUID da compra, gerado automaticamente", example = "d4f5c6a8-3b9e-4dca-bb9e-4f8e123abcde")
    private UUID uuid;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @Schema(description = "Data em que a compra foi realizada", example = "2024-11-06")
    private Date dataCompra;

    @Column(nullable = false, precision = 10, scale = 2)
    @Schema(description = "Valor total da compra", example = "99.99")
    private BigDecimal valorTotal;

    // Mapeamento Many-to-One com Cliente
    @ManyToOne
    @JoinColumn(name = "id_cliente_comp", nullable = false)
    @Schema(description = "Cliente que realizou a compra", example = "Cliente com ID 1")
    private Cliente cliente;

    // Mapeamento One-to-Many com ItensCompra
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Lista de itens incluídos na compra")
    private List<ItensCompra> itensCompra;
}

