package br.com.vendas.repository;

import br.com.vendas.domain.entity.VendaEntity;
import br.com.vendas.domain.request.VendaRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendaRepo extends JpaRepository<VendaEntity, Long > {

    Optional<VendaEntity> findById(VendaRequest vendaId);
}
