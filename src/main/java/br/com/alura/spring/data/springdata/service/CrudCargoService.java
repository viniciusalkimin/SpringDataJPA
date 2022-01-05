
package br.com.alura.spring.data.springdata.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.springdata.orm.Cargo;
import br.com.alura.spring.data.springdata.repository.CargoRepository;

@Service
public class CrudCargoService {

	private final CargoRepository cargoRepository;
	private Boolean system = true;

	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}

	public void inicial(Scanner sc) {

		while (system) {
			System.out.println("Escolha a acao de cargo a ser executada");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualiazar");
			System.out.println("4 - Deletar");

			int action = sc.nextInt();
			switch (action) {
			case 1:
				salvar(sc);
				break;
			case 2:
				atualizar(sc);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(sc);
				break;
			default:
				system = false;
				break;
			}
		}
	}

	private void salvar(Scanner sc) {
		System.out.println("Descricao do cargo;");
		String descricao = sc.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Dado salvo");
	}

	private void atualizar(Scanner sc) {
		System.out.println("ID do cargo a ser atualizado");
		Integer id = sc.nextInt();
		System.out.println("Nova descricao: ");
		String novaDescricao = sc.next();

		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(novaDescricao);
		cargoRepository.save(cargo);
		System.out.println("Cargo atualizado");
	}

	private void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}

	private void deletar(Scanner sc) {
		System.out.println("Digite o ID do cargo a ser deletado:");
		int id = sc.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("Cargo deletado!");
	}

}
