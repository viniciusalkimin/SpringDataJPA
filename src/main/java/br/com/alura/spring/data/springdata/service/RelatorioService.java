package br.com.alura.spring.data.springdata.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.springdata.orm.Funcionario;
import br.com.alura.spring.data.springdata.orm.FuncionarioProjecao;
import br.com.alura.spring.data.springdata.repository.FuncionarioRepository;

@Service
public class RelatorioService {
	private Boolean system = true;
	private FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatorioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner sc) {
		while (system) {
			System.out.println("Escolha a acao de cargo a ser executada");
			System.out.println("0 - Sair");
			System.out.println("1 - Procurar Funcionario por nome");
			System.out.println("2 - Procurar Funcionario por nome, data contratacao e salario maior");
			System.out.println("3 - Procurar Funcionario por data contratacao");
			System.out.println("4 - Pesquisa Funcionario com projeção Id, Nome e Salario");

			int action = sc.nextInt();
			switch (action) {
			case 1:
				findByNome(sc);
				break;
			case 2:
				buscarFuncionarioNomeSalarioMaiorData(sc);
				break;
			case 3:
				buscaFuncionarioDataContratacao(sc);
				break;
			case 4:
				pesquisaFuncionarioSalario();
				break;
			default:
				system = false;
				break;
		}
	}}
	
	private void findByNome(Scanner sc) {
		System.out.println("Digite o nome a ser procurado");
		String nome = sc.next();
		List<Funcionario> funcionario = funcionarioRepository.findByNome(nome);
		funcionario.forEach(func -> System.out.println(func));		
	}
	
	private void buscarFuncionarioNomeSalarioMaiorData(Scanner sc) {
		System.out.println("Digite o nome");
		String nome = sc.next();
		
		System.out.println("Digite o salario");
		Double salario = sc.nextDouble();
		
		System.out.println("Digite a data");
		String data = sc.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		list.forEach(f -> System.out.println(f));
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Qual data contratacao deseja pesquisar");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);
	}
	
	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario ID: " + f.getId() + " , Nome: " + f.getNome() + ", Salario: " + f.getSalario()));
	}

}