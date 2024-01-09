package br.com.tkn.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tkn.gestao_vagas.exceptions.UserFoundException;
import br.com.tkn.gestao_vagas.modules.company.entities.CompanyEntitiy;
import br.com.tkn.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

	@Autowired
	private CompanyRepository companyRepository;

	public CompanyEntitiy execute(CompanyEntitiy companyEntitiy) {
		this.companyRepository.findByUsernameOrEmail(companyEntitiy.getUsername(), companyEntitiy.getEmail())
				.ifPresent((user) -> {
					throw new UserFoundException();
				});

		return this.companyRepository.save(companyEntitiy);
	}
}
