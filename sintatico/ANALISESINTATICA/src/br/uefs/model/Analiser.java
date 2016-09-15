package br.uefs.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
public class Analiser {
	
	private static HashMap<String, Integer> prd_id = new HashMap<String,Integer>();
	private Stack<Integer> pilha;
	// nt x t
	private static int [][]tb_predicao = new int[63][43];
	
	
	public static void main(String[] args) {
		try {
			enderacar_nt();
			criar_tb_predicao();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < 63;  i++)
		{
			for(int j = 0; j<43; j++)
				System.err.print(tb_predicao[i][j]+"\t");
			System.err.println("");
		}
	}
	
	static void criar_tb_predicao() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("tb.in"));
		String linha;
		int i = 0;
		linha = br.readLine();
		while(linha !=null)
		{
			String []tk_tb =  linha.split(";");
			Arrays.fill(tb_predicao[i], -1); // 
			for(int x = 0; x < tk_tb.length; x++)
				tb_predicao[i][x] = get_nt_id(tk_tb[x]);
			linha = br.readLine();
			i++;
		}
		br.close();
	}
	
	private static int get_nt_id(String nt_prod) {
		if(prd_id.containsKey(nt_prod))
			return prd_id.get(nt_prod);
		else
			return -1;
	}
	
	private static void enderacar_nt() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("producoes.in"));
		String prod = br.readLine();
		int i = 44; 
		while(prod!=null)
		{
			prod = prod.trim();
			prd_id.put(prod,i);
			//System.err.println(prod);
			prod = br.readLine();
			i++;
		}
	}
	


	public void analisadorPreditivo(){
		// para fazer
	}
	
	// fazer os milhoes de cases e empilhar ...
	public void producoes(int nt){
		pilha.pop();
		switch(nt){
			case Library.nt_array:
				 pilha.push(Library.tk_maior);
				 pilha.push(Library.tk_maior);
				 pilha.push(Library.nt_array_i);
				 pilha.push(Library.nt_array_indexes);
				 pilha.push(Library.tk_menor);
	             pilha.push(Library.tk_menor);
				break;
			case Library.nt_array_c2: //TODO
				
				break;
			case Library.nt_array_i:
				pilha.push(Library.nt_array_i);
				pilha.push(Library.nt_array_indexes);
				pilha.push(Library.tk_virgula);
				break;
			case Library.nt_array_i_c2: //TODO
				break;
			case Library.nt_array_indexes:
				pilha.push(Library.nt_exp_simples);
				break;
			case Library.nt_array_indexes_opt:
				pilha.push(Library.nt_array_indexes);
				break;
			case Library.nt_array_indexes_opt_c2://TODO
				break;
			case Library.nt_array_param:
				 pilha.push(Library.tk_maior);
				 pilha.push(Library.tk_maior);
				 pilha.push(Library.nt_array_i);
				 pilha.push(Library.nt_array_indexes_opt);
				 pilha.push(Library.tk_menor);
	             pilha.push(Library.tk_menor);
				break;
			case Library.nt_array_param_c2://TODO
				break;
			case Library.nt_array_param_i:
				 pilha.push(Library.nt_array_i);
				 pilha.push(Library.nt_array_indexes_opt);
				 pilha.push(Library.tk_virgula);
				break;
			case Library.nt_array_param_i_c2://TODO
				break;
			case Library.nt_chama_ou_atribui:
				pilha.push(Library.nt_whos_next);
				pilha.push(Library.tk_identificador);
				break;
			case Library.nt_chama_ou_atribui_c2:  //TODO
				break;
			case Library.nt_comandos://TODO
				break;
			case Library.nt_comandos_c2://TODO
				break;
			case Library.nt_comandos_c3://TODO
				break;
			case Library.nt_comandos_c4://TODO
				break;
			case Library.nt_comandos_c5://TODO
				break;
			case Library.nt_comandos_c6://TODO
				break;
			case Library.nt_comandos_c7://TODO
				break;
			case Library.nt_corpo: // TODO 
				pilha.push(Library.nt_corpo);
				pilha.push(Library.nt_comandos);
				break;
			case Library.nt_corpo_c2: // TODO 
				break;
			case Library.nt_dec_const:// TODO 
				break;
			case Library.nt_dec_const_continuo:
				break;
			case Library.nt_dec_const_i:
				break;
			case Library.nt_dec_const_i_c2:
				break;
			case Library.nt_dec_const_ii:
				break;
			case Library.nt_dec_const_ii_c2:
				break;
			case Library.nt_dec_const_var_derivada:
				break;
			case Library.nt_dec_const_var_derivada_c2:
				break;
			case Library.nt_dec_enquanto:
				break;
			case Library.nt_dec_escrita:
				break;
			case Library.nt_dec_func:
				break;
			case Library.nt_dec_func_c2:
				break;
			case Library.nt_dec_func_i:
				break;
			case Library.nt_dec_func_i_c2:
				break;
			case Library.nt_dec_leitura:
				break;
			case Library.nt_dec_main:
				break;
			case Library.nt_dec_se:
				break;
			case Library.nt_dec_var:
				break;
			case Library.nt_dec_var_continuo:
				break;
			case Library.nt_dec_var_i:
				break;
			
			case Library.nt_dec_var_i_c2:
				break;
			case Library.nt_dec_var_ii:
				break;
			case Library.nt_dec_var_ii_c2:
				break;
			case Library.nt_else_opc:
				break;
			case Library.nt_else_opc_c2:
				break;
			case Library.nt_escrevivel:
				break;
			case Library.nt_escrevivel_c2:
				break;
			case Library.nt_escrevivel_i:
				break;
			case Library.nt_escrevivel_i_c2:
				break;
			case Library.nt_exp_conjunta:
				break;
			case Library.nt_exp_conjunta_i:
				break;
			case Library.nt_exp_conjunta_i_c2:
				break;
			case Library.nt_exp_relacional:
				break;
			case Library.nt_exp_relacional_bool:
				break;
			case Library.nt_exp_relacional_i:
				break;
			case Library.nt_exp_relacional_i_c2:
				break;
			case Library.nt_exp_simples:
				break;
			case Library.nt_exp_simples_c2:
				break;
			case Library.nt_fator:
				break;
			case Library.nt_fator_c2:
				break;
			case Library.nt_fator_c3:
				break;
			case Library.nt_fator_c4:
				break;
			case Library.nt_fator_c5:
				break;
			case Library.nt_fator_c6:
				break;
			case Library.nt_fator_c7:
				break;
			case Library.nt_fator_e:
				break;
			case Library.nt_fator_e_c2:
				break;
			case Library.nt_fator_e_c3:
				break;
			case Library.nt_fator_e_c4:
				break;
			case Library.nt_fator_e_c5:
				
				break;
			case Library.nt_fator_i:
				break;
			case Library.nt_fator_i_c2:
				break;
			case Library.nt_fator_i_e:
				break;
			case Library.nt_fator_i_e_c2:
				break;
			case Library.nt_id_funcao_e_outros:
				break;
			case Library.nt_id_funcao_e_outros_c2:
				break;
			case Library.nt_id_funcao_e_outros_derivado:
				break;
			case Library.nt_inicio_const_var_func:
				break;
			case Library.nt_inicio_func:
				pilha.push(Library.nt_dec_main);
				pilha.push(Library.nt_dec_func);
				break;
			case Library.nt_inicio_var_func:
				break;
			case Library.nt_leitura_i:
				break;
			case Library.nt_leitura_i_c2:
				break;
			case Library.nt_not_opc:
				break;
			case Library.nt_not_opc_c2:
				break;
			case Library.nt_novo_escopo:
				break;
			case Library.nt_op_mais_menos:
				break;
			case Library.nt_op_mais_menos_c2:
				break;	
			case Library.nt_op_multi_div:
				break;
			case Library.nt_op_multi_div_c2:
				break;
			case Library.nt_op_relacional:
				break;
			case Library.nt_op_relacional_c2:
				break;	
			case Library.nt_op_relacional_c3:
				break;
			case Library.nt_op_relacional_c4:
				break;
			case Library.nt_op_relacional_c5:
				break;
			case Library.nt_operar_relacionalmente:
				break;
			case Library.nt_operar_relacionalmente_c2:
				break;	
			case Library.nt_parametros:
				break;
			case Library.nt_parametros_c2://TODO
				break;
			case Library.nt_parametros_i:
				break;
			case Library.nt_passa_param_i_c2:
				break;
			case Library.nt_possible_func:
				break;
			case Library.nt_possible_func_c2:
				break;
			case Library.nt_programa:
				pilha.push(Library.nt_inicio_const_var_func);
				break;
			case Library.nt_programa_c2:
				pilha.push(Library.nt_inicio_var_func);
				break;
			case Library.nt_programa_c3:
				pilha.push(Library.nt_inicio_func);
				break;
			case Library.nt_retorno_func:
				break;
			case Library.nt_termo: // TODO
				break;
			case Library.nt_termo_e:
				pilha.push(Library.nt_fator_i_e);
				pilha.push(Library.nt_fator_e);
				break;
			case Library.nt_termo_i: // TODO
				break;
			case Library.nt_termo_i_c2:
				break;
			case Library.nt_termo_i_e:
				pilha.push(Library.nt_fator_i_e);
				pilha.push(Library.nt_fator_e);
				pilha.push(Library.nt_op_mais_menos);
				break;
			case Library.nt_termo_i_e_c2:
				break;
			case Library.nt_tipo:
				pilha.push(Library.tk_inteiro);
				break;
			case Library.nt_tipo_c2:
				pilha.push(Library.tk_real);
				break;
			case Library.nt_tipo_c3:
				pilha.push(Library.tk_booleano);
				break;
			case Library.nt_tipo_c4:
				pilha.push(Library.tk_cadeia);
				break;
			case Library.nt_tipo_c5:
				pilha.push(Library.tk_caractere);
				break;
			case Library.nt_whos_next:
				pilha.push(Library.tk_pontoevirgula);
				pilha.push(Library.tk_fechar_parentese);
				pilha.push(Library.nt_passa_param);
				pilha.push(Library.tk_abrir_parentese);
				break;
			case Library.nt_whos_next_c2:
				break;
			case Library.nt_epsilon:
				break;
		}
	}
	

}
