package arvore;

public class Principal {
	
	/*
	 * Esse codigo ja vem com um pequeno teste, porem fique a vontate 
	 * para utilizar todas as funcoes dessa arvore
	 */

	public static void main(String[] args) {
		
		ArvoreAvl avl = new ArvoreAvl();
		
		avl.inserir(7);
		avl.inserir(8);
		avl.inserir(9);
		avl.inserir(10);
		avl.inserir(11);
		avl.inserir(12);
		avl.inserir(13);
		
		/*
		 * Nesse parte do codigo nao e possivel ver a arvore, mais o seu balanceamento
		 * e feito durante a insersao, dessa forma e possivel manter a arvore AVL sempre
		 * que algo novo for inserido
		 */
		
		avl.ordem();

	}

}
