package br.uefs.model;

import java.util.Stack;

public class Analiser {
	
	private Stack<Integer> pilha;
	
	public void analisadorPreditivo(){
		// para fazer
	}
	
	public void producoes(int naoterminal){
		switch(naoterminal){
			case Library.nt_programa:
				pilha.pop();
				pilha.push(Library.nt_inicio_const_var_func);
				break;
		
		}
	}
	

}
