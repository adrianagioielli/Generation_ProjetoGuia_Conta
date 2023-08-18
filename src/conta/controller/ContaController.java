package conta.controller;

import conta.model.Conta;
import conta.repository.ContaRepository;
import java.util.ArrayList;


public class ContaController implements ContaRepository{
	
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	int numero = 0;
	

	//procura conta pelo número
	@Override
	public void procurarPorNumero(int numero) {
		var conta = buscarNaCollection(numero);
		if(conta != null) {
			conta.visualizar();
		}else {
			System.out.println("\nA conta número: " + numero + "não foi encontrada!");
		}
	}

	@Override
	//lista contas -> opção 2 do menu
	public void listarTodas() {
		for(var conta : listaContas) {
			conta.visualizar();
		}
	}

	@Override
	//cadastra uma conta -> opcao 3 do menu
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("\nA conta número: " + conta.getNumero() + 
				" foi criada com sucesso!");
	}

	@Override
	//atualiza conta -> opcao 4 do menu
	public void atualizar(Conta conta) {
		var buscaConta = buscarNaCollection(conta.getNumero());
		if(buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("\nA conta número: " + conta.getNumero() + "foi atualizada com sucesso!");
		}else {
			System.out.println("\nA conta número: " + conta.getNumero() + "não foi encontrada!");
		}
	}

	@Override
	//exclui uma conta -> opcao 5 do menu
	public void deletar(int numero) {
		var conta = buscarNaCollection(numero);
		if(conta != null) {
			if(listaContas.remove(conta) == true) {
				System.out.println("\nA conta número: " + numero + "foi deletada com sucesso!");
			}else {
				System.out.println("\nA conta número: " + numero + "não foi encontrada");
			}
		}
	}

	@Override
	//retira quantia em valor da conta -> opcao 6 do menu
	public void sacar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		if(conta != null) {
			if(conta.sacar(valor) == true) {
				System.out.println("\nO saque na Conta número: " + numero + "foi efetuado com sucesso!");
			}
		}else {
			System.out.println("\nA conta número: " + numero + "não foi encontrada");
		}
	}

	@Override
	//inclui quantia em valor da conta -> opcao 7 do menu
	public void depositar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		if(conta != null) {
			conta.depositar(valor);
			System.out.println("\nO depósito na conta número: " + numero + "foi efetuado com sucesso");
		}else {
			System.out.println("\nA conta número: " + numero + "não foi encontrada ou a conta destino não é a conta corrente!");
		}
	}

	@Override
	//retira quantia de valor em uma conta e adiciona quantia em valor de outra conta
	// -> opcao 8 do menu
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		var contaOrigem = buscarNaCollection(numeroOrigem);
		var contaDestino = buscarNaCollection(numeroDestino);
		if(contaOrigem != null && contaDestino != null) {
			if(contaOrigem.sacar(valor) == true) {
				contaDestino.depositar(valor);
				System.out.println("\nA transferência foi efetuada com sucesso!");
			}
		}else {
			System.out.println("\nA conta de origem e/oiu destino não foram encontradas!");
		}
	}
	
	
	//metodos auxiliares
	//método para gerar numero da conta
	public int gerarNumero() {
		return ++numero;
	}
	//faz busca no objeto conta
	public Conta buscarNaCollection(int numero) {
		for(var conta : listaContas) {
			if(conta.getNumero() == numero) {
				return conta;
			}
		}
		return null;
	}
	//retorna apenas o tipo de conta
	public int retornaTipo(int numero) {
		for(var conta :  listaContas) {
			if(conta.getNumero() == numero) {
				return conta.getTipo();
			}
		}
		return 0;
	}

}
