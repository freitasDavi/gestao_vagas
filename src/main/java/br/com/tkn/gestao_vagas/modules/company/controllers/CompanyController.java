package br.com.tkn.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tkn.gestao_vagas.modules.company.entities.CompanyEntitiy;
import br.com.tkn.gestao_vagas.modules.company.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CreateCompanyUseCase createCompanyUseCase;

	@PostMapping("/")
	public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntitiy companyEntitiy) {
		try {
			var company = this.createCompanyUseCase.execute(companyEntitiy);

			return ResponseEntity.ok().body(company);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
