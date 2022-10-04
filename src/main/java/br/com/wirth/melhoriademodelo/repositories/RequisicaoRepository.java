package br.com.wirth.melhoriademodelo.repositories;

import br.com.wirth.melhoriademodelo.dtos.RequisicaoDTO;
import br.com.wirth.melhoriademodelo.entities.Requisicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RequisicaoRepository extends JpaRepository<Requisicao,Long> {

    Page<Requisicao> findRequisicaoByProcessadoIsFalse(Pageable pageable);
    Page<Requisicao>findRequisicaoByProcessadoIsTrue(Pageable pageable);
}
