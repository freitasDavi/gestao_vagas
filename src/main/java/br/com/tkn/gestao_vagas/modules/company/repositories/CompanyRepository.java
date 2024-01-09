package br.com.tkn.gestao_vagas.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tkn.gestao_vagas.modules.company.entities.CompanyEntitiy;

public interface CompanyRepository extends JpaRepository<CompanyEntitiy, UUID> {
	Optional<CompanyEntitiy> findByUsernameOrEmail(String name, String email);
}
