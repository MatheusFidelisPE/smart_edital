package ufrpe.apsoo.com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pesquisa")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pesquisa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String description;
    private String area;
    private String maturidade;
    private String impacto;
    private String conexoesODS;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pesquisador_id")
    private PesquisadorEmpreendedor pesquisadorEmpreendedor;
}
