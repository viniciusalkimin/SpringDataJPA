package br.com.alura.spring.data.springdata;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.alura.spring.data.springdata.service.CrudCargoService;
import br.com.alura.spring.data.springdata.service.CrudFuncionarioService;
import br.com.alura.spring.data.springdata.service.CrudUnidadeTrabalhoService;
import br.com.alura.spring.data.springdata.service.RelatorioFuncionarioDinamico;
import br.com.alura.spring.data.springdata.service.RelatorioService;

@EnableJpaRepositories
@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeTrabalhoService unidadeService;
	private final RelatorioService relatorioService;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;
	private Boolean system = true;

	public SpringDataApplication(CrudCargoService cargoService, CrudFuncionarioService funcionarioService, CrudUnidadeTrabalhoService unidadeService, RelatorioService relatorioService,RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeService = unidadeService;
		this.relatorioService = relatorioService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		Scanner sc = new Scanner(System.in);

		while (system) {

			System.out.println("Escolha as acoes abaixo:");
			System.out.println("0 - Sair");
			System.out.println("1 - Gerenciar Cargos");
			System.out.println("2 - Gerenciar Funcionarios");
			System.out.println("3 - Gerenciar Unidades");
			System.out.println("4 - Relatorios");
			System.out.println("5 - Relatorio Dinamico");

			Integer function = sc.nextInt();

			switch (function) {
				case 1:
					cargoService.inicial(sc);
					break;
				case 2:
					funcionarioService.inicial(sc);
					break;
				case 3:
					unidadeService.inicial(sc);
					break;
				case 4:
					relatorioService.inicial(sc);
					break;
				case 5:
					relatorioFuncionarioDinamico.inicial(sc);
					break;
				default:
					System.out.println("Finalizando");
					system = false;
					break;
			}
		}
		sc.close();
	}

}
