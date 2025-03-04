package ufrpe.apsoo.com.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pesquisador_empreendedor")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PesquisadorEmpreendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;
    private String detalhesGraduacao;
    private String detalhesPosGraduacao;
    private String expProfissional;
    private String curLates;
    private boolean expEmpreen;
    private boolean interesseEmpresa;
    private boolean interesseTransferenciaTecnologia;
    private boolean disponibilidadeCapacitacao;

    @JsonManagedReference
    @OneToMany(mappedBy = "pesquisadorEmpreendedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pesquisa> pesquisas;
}
