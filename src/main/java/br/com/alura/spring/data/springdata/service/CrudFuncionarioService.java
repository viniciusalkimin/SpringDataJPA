package br.com.alura.spring.data.springdata.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.springdata.orm.Cargo;
import br.com.alura.spring.data.springdata.orm.Funcionario;
import br.com.alura.spring.data.springdata.orm.UnidadeTrabalho;
import br.com.alura.spring.data.springdata.repository.CargoRepository;
import br.com.alura.spring.data.springdata.repository.FuncionarioRepository;
import br.com.alura.spring.data.springdata.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private final FuncionarioRepository funcionarioRepository;
	private final CargoRepository cargoRepository;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository,
			UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}

	public void inicial(Scanner sc) {

		System.out.println("Escolha a opção desejada:");
		System.out.println("0 - Sair");
		System.out.println("1 - Salvar");
		System.out.println("2 - Atualizar");
		System.out.println("3 - Visualiazar");
		System.out.println("4 - Deletar");
		int option = sc.nextInt();

//		while (system) {
			switch (option) {
			case 0:
				system = false;
				break;
			case 1:
				salvar(sc);
				break;
			case 2:
				atualizar(sc);
				break;
			case 3:
				visualizar(sc);
				break;
			case 4:
				deletar(sc);
				break;
			default:
				system = false;
				break;
			}
	}
//	}

	private void salvar(Scanner sc) {

		System.out.println("Digite o nome do funcionario");
		String nome = sc.next();
		System.out.println("Digite o CPF do funcionario");
		String cpf = sc.next();
		System.out.println("Digite o salario do funcionario");
		Double salario = sc.nextDouble();
		System.out.println("Digite a data de contratacao do funcionario(dd/MM/aaaa");
		String dataContratacao = sc.next();
		System.out.println("Digite o cargo Id");
		int cargoId = sc.nextInt();

		List<UnidadeTrabalho> unidades = unidade(sc);

		Funcionario func = new Funcionario();
		func.setNome(nome);
		func.setCpf(cpf);
		func.setDataContratacao(LocalDate.parse(dataContratacao, formatter));

		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		func.setCargo(cargo.get());
		func.setUnidadeTrabalhos(unidades);
		func.setSalario(salario);
		
		funcionarioRepository.save(func);
		System.out.println("Salvo");
	}

	private List<UnidadeTrabalho> unidade(Scanner sc) {
		Boolean isTrue = true;
		List<UnidadeTrabalho> unidades = new ArrayList<>();

		while (isTrue) {
			System.out.println("Digite o unidadeId (Para sair digite 0)");
			Integer unidadeId = sc.nextInt();

			if (unidadeId != 0) {
				Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
				unidades.add(unidade.get());
			} else {
				isTrue = false;
			}
		}

		return unidades;
	}

	public void atualizar(Scanner sc) {
		System.out.println("Digite o ID do funcionário que deseja atualizar:");
		int id = sc.nextInt();
		System.out.println("Digite o nome do usuario a ser alterado:");
		String nome = sc.next();
		System.out.println("Digite o CPF do usuario a ser alterado:");
		String cpf = sc.next();
		System.out.println("Digite o salário do usuario a ser alterado:");
		Double salario = sc.nextDouble();
		System.out.println("Digite a data de contratação");
		String dataContratacao = sc.next();
		System.out.println("Digite o cargo Id");
		int cargoId = sc.nextInt();

		Funcionario func = new Funcionario();
		func.setId(id);
		func.setNome(nome);
		func.setCpf(cpf);
		func.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		func.setCargo(cargo.get());
		func.setSalario(salario);

		funcionarioRepository.save(func);
		System.out.println("Alterado");
	}

	private void visualizar(Scanner sc) {
		System.out.println("Digite a pagina que deseja abrir");
		Integer page = sc.nextInt();
		
		Pageable pageable = PageRequest.of(page -1 , 5 , Sort.by(Sort.Direction.ASC,"nome"));			
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		
		System.out.println(funcionarios);
		System.out.println("Pagina atual: " + funcionarios.getNumber()+1);
		System.out.println("Total elementos: " + funcionarios.getTotalElements());
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}

	private void deletar(Scanner sc) {
		System.out.println("Id");
		int id = sc.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("Deletado");
	}

}
