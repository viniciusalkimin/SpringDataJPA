package br.com.alura.spring.data.springdata.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.springdata.orm.Funcionario;
import br.com.alura.spring.data.springdata.repository.FuncionarioRepository;
import br.com.alura.spring.data.springdata.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {
	
	private final FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner sc) {
	System.out.println("Digite o nome");
	String nome = sc.next();
	
	if(nome.equalsIgnoreCase("null")) {
		nome = null;
	}
	
	System.out.println("Digite o cpf");
	String cpf = sc.next();
	
	if(cpf.equalsIgnoreCase("null")) {
		cpf = null;
	}
	
	System.out.println("Digite o Salario");
	Double salario = sc.nextDouble();
	
	if(salario == 0) {
		salario = null;
	}
	
	System.out.println("Digite o data de contratacao");
	String data = sc.next();
	
	LocalDate dataContratacao;
	if(data.equalsIgnoreCase("null")) {
		dataContratacao = null;
	} else {
		dataContratacao = LocalDate.parse(data, formatter);
	}
	
	List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification
			.where(
					SpecificationFuncionario.nome(nome))
					.or(SpecificationFuncionario.cpf(cpf))
					.or(SpecificationFuncionario.salario(salario))
					.or(SpecificationFuncionario.dataContratacao(dataContratacao))
			);
	funcionarios.forEach(System.out::println);
	}

}
