package ufrpe.apsoo.com.data;

import ufrpe.apsoo.com.model.Pesquisa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PesquisaRepository extends JpaRepository<Pesquisa, Long> {
}
