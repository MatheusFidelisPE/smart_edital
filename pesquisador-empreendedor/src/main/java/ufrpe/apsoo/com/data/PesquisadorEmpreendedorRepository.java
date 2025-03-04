package ufrpe.apsoo.com.data;

import ufrpe.apsoo.com.model.PesquisadorEmpreendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PesquisadorEmpreendedorRepository extends JpaRepository<PesquisadorEmpreendedor, Long> {
}
