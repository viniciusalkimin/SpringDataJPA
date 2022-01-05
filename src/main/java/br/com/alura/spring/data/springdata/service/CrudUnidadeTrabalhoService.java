package br.com.alura.spring.data.springdata.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.springdata.orm.UnidadeTrabalho;
import br.com.alura.spring.data.springdata.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
	
	private Boolean system = true;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	
	
	public CrudUnidadeTrabalhoService (UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}

	
	public void inicial(Scanner sc) {
	while(system) {
		System.out.println("Escolha a acao de unidade a ser executada");
		System.out.println("0 - Sair");
		System.out.println("1 - Salvar");
		System.out.println("2 - Atualizar");
		System.out.println("3 - Visualiazar");
		System.out.println("4 - Deletar");	
			int action = sc.nextInt();
			switch (action) {
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
		System.out.println("Digite o nome da unidade");
        String nome = sc.next();

        System.out.println("Digite o endereco");
        String endereco = sc.next();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setDescricao(nome);
        unidadeTrabalho.setEndereco(endereco);

        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Salvo");
	}
	
	private void atualizar(Scanner sc) {
		System.out.println("Digite o id");
        Integer id = sc.nextInt();

        System.out.println("Digite o nome da unidade");
        String nome = sc.next();

        System.out.println("Digite o endereco");
        String endereco = sc.next();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setDescricao(nome);
        unidadeTrabalho.setEndereco(endereco);

        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Alterado");
	}
	
	private void visualizar() {
		Iterable<UnidadeTrabalho> unidades = unidadeTrabalhoRepository.findAll();
		unidades.forEach(unidade -> System.out.println(unidade));
	}
	
	private void deletar(Scanner sc){
		System.out.println("Id");
		int id = sc.nextInt();
		unidadeTrabalhoRepository.deleteById(id);
		System.out.println("Deletado");
	}
	
		
	
}
