package br.com.vendas.repository;

import br.com.vendas.domain.entity.VendaItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<VendaItemEntity, Long> {
}
