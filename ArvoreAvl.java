package arvore;

public class ArvoreAvl {
	
	/*
	 * Criado por: Jose Caio
	 * 
	 * Criado em 24/03/2020
	 * 
	 * Parte dos codigo foram reutilizados de arvores binarias de busca 
	 * que eu tinha feito nas aulas de ED1, principalmente as impressoes
	 * 
	 * Omitivi a acentucao ate nos comentarios para evitar problemas
	 */

  private No raiz;

	public void inserir(int numero) { //Nesse metodo e passado somente um numero
		No no = new No(numero);		  //esse numero sera direcionado a uma insercao AVL
		balancearAVL(this.raiz, no);
	}

	public void balancearAVL(No comparacao, No insercao) {

		if (comparacao == null) { //Se não houver no a comparar então insere normalemnte
			this.raiz = insercao;
			System.out.println("Esse valor foi inserido na raiz");

		} else {

			if (insercao.getChave() < comparacao.getChave()) {

				if (comparacao.getEsquerda() == null) {
					comparacao.setEsquerda(insercao);
					insercao.setPai(comparacao);
					verificarBalanceamento(comparacao);
					System.out.println(comparacao.getChave() + " inserido a esquerda do no pai");

				} else {
					balancearAVL(comparacao.getEsquerda(), insercao);
				}

			} else if (insercao.getChave() > comparacao.getChave()) {

				if (comparacao.getDireita() == null) {
					comparacao.setDireita(insercao);
					insercao.setPai(comparacao);
					System.out.println(comparacao.getChave() + " inserido a direita do no pai");
					verificarBalanceamento(comparacao);

				} else {
					balancearAVL(comparacao.getDireita(), insercao);
				}

			} else {
				System.out.println("O no ja existe !");
			}
		}
	}

	public void verificarBalanceamento(No no) {
		setBalanceamento(no);
		int balanceamento = no.getBalanceamento();

		if (balanceamento == -2) {

			if (altura(no.getEsquerda().getEsquerda()) >= altura(no.getEsquerda().getDireita())) {
				no = rotacaoDireita(no);
				
				System.out.println("Balanceando com rotacao a direita");

			} else {
				no = duplaRotacaoEsquerdaDireita(no);
				System.out.println("Balanceando com dupla rotacao a esquerda e a direita");
			}

		} else if (balanceamento == 2) {

			if (altura(no.getDireita().getDireita()) >= altura(no.getDireita().getEsquerda())) {
				no = rotacaoEsquerda(no);
				
				System.out.println("Balanceando com rotacao a esquerda");

			} else {
				no = duplaRotacaoDireitaEsquerda(no);
				System.out.println("Balanceando com dupla rotacao a direita e a esquerda");
			}
		}

		if (no.getPai() != null) {
			verificarBalanceamento(no.getPai());
			//Enquanto o No nao for nulo a funcao nao para 
		} else {
			this.raiz = no;
		}
	}

	public void removerArvore() {
		this.raiz = null;
		System.out.println("Toda a sua arvore foi removida");
	}


	public No rotacaoEsquerda(No inicial) {

		No direita = inicial.getDireita();
		direita.setPai(inicial.getPai());

		inicial.setDireita(direita.getEsquerda());

		if (inicial.getDireita() != null) {
			inicial.getDireita().setPai(inicial);
		}

		direita.setEsquerda(inicial);
		inicial.setPai(direita);

		if (direita.getPai() != null) {

			if (direita.getPai().getDireita() == inicial) {
				direita.getPai().setDireita(direita);
			
			} else if (direita.getPai().getEsquerda() == inicial) {
				direita.getPai().setEsquerda(direita);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(direita);

		return direita;
	}

	public No rotacaoDireita(No inicial) {

		No esquerda = inicial.getEsquerda();
		esquerda.setPai(inicial.getPai());

		inicial.setEsquerda(esquerda.getDireita());

		if (inicial.getEsquerda() != null) {
			inicial.getEsquerda().setPai(inicial);
		}

		esquerda.setDireita(inicial);
		inicial.setPai(esquerda);

		if (esquerda.getPai() != null) {

			if (esquerda.getPai().getDireita() == inicial) {
				esquerda.getPai().setDireita(esquerda);
			
			} else if (esquerda.getPai().getEsquerda() == inicial) {
				esquerda.getPai().setEsquerda(esquerda);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(esquerda);

		return esquerda;
	}

	public No duplaRotacaoEsquerdaDireita(No inicial) {
		inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}

	public No duplaRotacaoDireitaEsquerda(No inicial) {
		inicial.setDireita(rotacaoDireita(inicial.getDireita()));
		return rotacaoEsquerda(inicial);
	}

	public No sucessor(No no) {
		if (no.getDireita() != null) {
			No auxiliar = no.getDireita();
			while (auxiliar.getEsquerda() != null) {
				auxiliar = auxiliar.getEsquerda();
			}
			return auxiliar;
		} else {
			No pai = no.getPai();
			while (pai != null && no == pai.getDireita()) {
				no = pai;
				pai = no.getPai();
			}
			return pai;
		}
	}

	private int altura(No atual) {
		if (atual == null) {
			return -1;
		}

		if (atual.getEsquerda() == null && atual.getDireita() == null) {
			return 0;
		
		} else if (atual.getEsquerda() == null) {
			return 1 + altura(atual.getDireita());
		
		} else if (atual.getDireita() == null) {
			return 1 + altura(atual.getEsquerda());
		
		} else {
			return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
		}
	}

	private void setBalanceamento(No no) {
		no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
	}
	
	public void ordem () {
		System.out.println("Imprimindo os valores da arvore em ordem");
		ordem(this.raiz);
	}
	
	public void ordem (No no) {
		if (no != null) {
			ordem(no.getEsquerda());
			System.out.print(no.getChave() + " , ");
			ordem(no.getDireita());
		}
	}

}