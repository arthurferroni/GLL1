package br.uefs.control;
import br.uefs.lib.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Sintatico {
	
	static Stack<Integer> pilha = new Stack<Integer>();
	static LinkedList<Token> tokens_int = new LinkedList<Token>();
	static LinkedList<Integer> tokens = new LinkedList<Integer>();
	static LinkedList<String> linha_tokens = new LinkedList<String>();
	static int size = 500;
	static int[][]_matriz = new int[size][size];
	static String[] tokens_id = new String[45];
	int i = 0 ;
	
	
	public static void main(String args[])
	{
		new Sintatico().analises();
		
	}
	

	public void initTokensId(){
		tokens_id[Library.tk_programa] 				= "tk_programa";
		tokens_id[Library.tk_const]					= "tk_const";
	    tokens_id[Library.tk_var]				 	= "tk_var";
	    tokens_id[Library.tk_funcao]			 	= "tk_funcao";
	    tokens_id[Library.tk_inicio]				= "tk_inicio";
	    tokens_id[Library.tk_fim]					= "tk_fim";
	    tokens_id[Library.tk_se] 					= "tk_se";
	    tokens_id[Library.tk_entao] 				= "tk_entao";
	    tokens_id[Library.tk_senao] 				= "tk_senao";
	    tokens_id[Library.tk_enquanto] 				= "tk_enquanto";
	    tokens_id[Library.tk_faca] 					= "tk_faca";
	    tokens_id[Library.tk_leia] 					= "tk_leia";
	    tokens_id[Library.tk_escreva] 				= "tk_escreva";
	    tokens_id[Library.tk_inteiro] 				= "tk_inteiro";
	    tokens_id[Library.tk_real] 					= "tk_real";
	    tokens_id[Library.tk_booleano] 				= "tk_booleano";
	    tokens_id[Library.tk_verdadeiro] 			= "tk_verdadeiro";
	    tokens_id[Library.tk_falso] 				= "tk_falso";
	    tokens_id[Library.tk_cadeia] 				= "tk_cadeia";
	    tokens_id[Library.tk_caractere] 			= "tk_caractere";
	    tokens_id[Library.tk_nao] 					= "tk_nao";
	    tokens_id[Library.tk_e] 					= "tk_e";
	    tokens_id[Library.tk_ou] 					= "tk_ou";
	    tokens_id[Library.tk_soma] 					= "tk_soma";
	    tokens_id[Library.tk_subtracao] 			= "tk_subtracao";
	    tokens_id[Library.tk_multiplicacao] 		= "tk_multiplicacao";
	    tokens_id[Library.tk_divisao] 				= "tk_divisao";
	    tokens_id[Library.tk_diferente] 			= "tk_diferente";
	    tokens_id[Library.tk_igual] 				= "tk_igual";
	    tokens_id[Library.tk_menor] 				= "tk_menor";
	    tokens_id[Library.tk_menorigual] 			= "tk_menorigual";
	    tokens_id[Library.tk_maior] 				= "tk_maior";
	    tokens_id[Library.tk_maiorigual] 			= "tk_maiorigual";
	    tokens_id[Library.tk_identificador] 		= "tk_identificador";
	    tokens_id[Library.tk_numero] 				= "tk_numero";
	    tokens_id[Library.tk_pontoevirgula] 		= "tk_pontoevirgula";
	    tokens_id[Library.tk_virgula] 				= "tk_virgula";
	    tokens_id[Library.tk_abrir_parentese] 		= "tk_abrir_parentese";
	    tokens_id[Library.tk_fechar_parentese] 		= "tk_fechar_parentese";
	    tokens_id[Library.tk_cadeia_de_caracteres] 	= "tk_cadeia_de_caracteres";
	    tokens_id[Library.tk_caractere_l] 			= "tk_caractere_l";
	    tokens_id[Library.tk_dolar] 				= "tk_dolar";
		tokens_id[Library.tk_fimarq] 				= "tk_fimarq";
		tokens_id[Library.tk_epsilon] 				= "tk_epsilon";
	}
	public void initializeMatriz(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				_matriz[i][j] = -1;
			}
		}
	}
	public int returnTokenId(String tk){
		for (int i = 1; i < tokens.lastIndexOf(tokens_id) + 1; i++){
			if(tokens.get(i).equals(tk)){
				return i;
			}
		}
		return 0;
	}
	
	public void startMatriz()
	{
		initializeMatriz();
		// PROGRAMA
		_matriz[Library.nt_programa][Library.tk_programa] = Library.nt_inicio_func; 
		_matriz[Library.nt_programa][Library.tk_var] = Library.nt_inicio_var_func; 
		_matriz[Library.nt_programa][Library.tk_const] = Library.nt_inicio_const_var_func;
		_matriz[Library.nt_programa][Library.tk_funcao] = Library.nt_inicio_func; 
		//DEC_CONST
		_matriz[Library.nt_dec_const][Library.tk_const] = Library.nt_dec_const; 
		_matriz[Library.nt_dec_const_continuo][Library.tk_inteiro] = Library.nt_dec_const_continuo; 
		_matriz[Library.nt_dec_const_continuo][Library.tk_booleano] = Library.nt_dec_const_continuo; 
		_matriz[Library.nt_dec_const_continuo][Library.tk_cadeia] = Library.nt_dec_const_continuo;
		_matriz[Library.nt_dec_const_continuo][Library.tk_real] = Library.nt_dec_const_continuo; 
		_matriz[Library.nt_dec_const_continuo][Library.tk_caractere] = Library.nt_dec_const_continuo; 
		// TIPO N
		_matriz[Library.nt_tipo_n][Library.tk_inteiro] = Library.nt_tipo_c1; 
		_matriz[Library.nt_tipo_n][Library.tk_booleano] = Library.nt_tipo_c3; 
		_matriz[Library.nt_tipo_n][Library.tk_cadeia] = Library.nt_tipo_c4;
		_matriz[Library.nt_tipo_n][Library.tk_real] = Library.nt_tipo_c2; 
		_matriz[Library.nt_tipo_n][Library.tk_caractere] = Library.nt_tipo_c5; 
		//DEC_CONST_1
		Arrays.fill(_matriz[Library.nt_dec_const_i], Library.nt_epsilon);
		_matriz[Library.nt_dec_const_i][Library.tk_virgula] = Library.nt_dec_const_i; 
		//DEC_CONST_2
		Arrays.fill(_matriz[Library.nt_dec_const_ii], Library.nt_epsilon);
		_matriz[Library.nt_dec_const_ii][Library.tk_inteiro] = Library.nt_dec_const_ii; 
		_matriz[Library.nt_dec_const_ii][Library.tk_booleano] = Library.nt_dec_const_ii; 
		_matriz[Library.nt_dec_const_ii][Library.tk_cadeia] = Library.nt_dec_const_ii;
		_matriz[Library.nt_dec_const_ii][Library.tk_real] = Library.nt_dec_const_ii; 
		_matriz[Library.nt_dec_const_ii][Library.tk_caractere] = Library.nt_dec_const_ii; 
		// DEC_CONST_VAR_DERIVADA
		_matriz[Library.nt_dec_const_var_derivada][Library.tk_var] = Library.nt_dec_const_var_derivada_c1; 
		_matriz[Library.nt_dec_const_var_derivada][Library.tk_funcao] = Library.nt_dec_const_var_derivada_c2; 
		_matriz[Library.nt_dec_const_var_derivada][Library.tk_programa] = Library.nt_dec_const_var_derivada_c2; 
		//DEC_VAR
		_matriz[Library.nt_dec_var][Library.tk_var] = Library.nt_dec_var; 
		_matriz[Library.nt_dec_var_continuo][Library.tk_inteiro] = Library.nt_dec_var_continuo; 
		_matriz[Library.nt_dec_var_continuo][Library.tk_booleano] = Library.nt_dec_var_continuo; 
		_matriz[Library.nt_dec_var_continuo][Library.tk_cadeia] = Library.nt_dec_var_continuo;
		_matriz[Library.nt_dec_var_continuo][Library.tk_real] = Library.nt_dec_var_continuo; 
		_matriz[Library.nt_dec_var_continuo][Library.tk_caractere] = Library.nt_dec_var_continuo; 
		// DEC_VAR_I
		Arrays.fill(_matriz[Library.nt_dec_var_i], Library.nt_epsilon);
		_matriz[Library.nt_dec_var_i][Library.tk_virgula] = Library.nt_dec_var_i; 
		// DEC_VAR_II
		Arrays.fill(_matriz[Library.nt_dec_var_ii], Library.nt_epsilon);
		_matriz[Library.nt_dec_var_ii][Library.tk_inteiro] = Library.nt_dec_var_ii; 
		_matriz[Library.nt_dec_var_ii][Library.tk_booleano] = Library.nt_dec_var_ii; 
		_matriz[Library.nt_dec_var_ii][Library.tk_cadeia] = Library.nt_dec_var_ii;
		_matriz[Library.nt_dec_var_ii][Library.tk_real] = Library.nt_dec_var_ii; 
		_matriz[Library.nt_dec_var_ii][Library.tk_caractere] = Library.nt_dec_var_ii; 
		// ARRAY
		Arrays.fill(_matriz[Library.nt_array], Library.nt_epsilon);
		_matriz[Library.nt_array][Library.tk_menor] = Library.nt_array; 
		// ARRAY INDEX
		Arrays.fill(_matriz[Library.nt_array_indexes], Library.nt_exp_simples);
		// ARRAY_I
		Arrays.fill(_matriz[Library.nt_array_i], Library.nt_epsilon);
		_matriz[Library.nt_array_i][Library.tk_virgula] = Library.nt_array_i; 
		// EXP_RELACIONAL_BOOL
		_matriz[Library.nt_exp_relacional_bool][Library.tk_numero] = Library.nt_exp_relacional_bool; 
		_matriz[Library.nt_exp_relacional_bool][Library.tk_verdadeiro] = Library.nt_exp_relacional_bool; // TODO VERDADE - FALSO
		_matriz[Library.nt_exp_relacional_bool][Library.tk_falso] = Library.nt_exp_relacional_bool; // TODO VERDADE - FALSO
		_matriz[Library.nt_exp_relacional_bool][Library.tk_subtracao] = Library.nt_exp_relacional_bool;
		_matriz[Library.nt_exp_relacional_bool][Library.tk_menor] = Library.nt_exp_relacional_bool;
		_matriz[Library.nt_exp_relacional_bool][Library.tk_cadeia_de_caracteres] = Library.nt_exp_relacional_bool;
		_matriz[Library.nt_exp_relacional_bool][Library.tk_nao] = Library.nt_exp_relacional_bool;
		_matriz[Library.nt_exp_relacional_bool][Library.tk_soma] = Library.nt_exp_relacional_bool;
		_matriz[Library.nt_exp_relacional_bool][Library.tk_caractere_l] = Library.nt_exp_relacional_bool; 
		_matriz[Library.nt_exp_relacional_bool][Library.tk_abrir_parentese] = Library.nt_exp_relacional_bool;
		_matriz[Library.nt_exp_relacional_bool][Library.tk_identificador] = Library.nt_exp_relacional_bool;
		// EXP_CONJU
		_matriz[Library.nt_exp_conjunta][Library.tk_numero] = Library.nt_exp_conjunta; 
		_matriz[Library.nt_exp_conjunta][Library.tk_verdadeiro] = Library.nt_exp_conjunta; // TODO VERDADE - FALSO
		_matriz[Library.nt_exp_conjunta][Library.tk_falso] = Library.nt_exp_conjunta;
		_matriz[Library.nt_exp_conjunta][Library.tk_subtracao] = Library.nt_exp_conjunta;
		_matriz[Library.nt_exp_conjunta][Library.tk_menor] = Library.nt_exp_conjunta;
		_matriz[Library.nt_exp_conjunta][Library.tk_cadeia_de_caracteres] = Library.nt_exp_conjunta;
		_matriz[Library.nt_exp_conjunta][Library.tk_nao] = Library.nt_exp_conjunta;
		_matriz[Library.nt_exp_conjunta][Library.tk_soma] = Library.nt_exp_conjunta;
		_matriz[Library.nt_exp_conjunta][Library.tk_caractere_l] = Library.nt_exp_conjunta;  
		_matriz[Library.nt_exp_conjunta][Library.tk_abrir_parentese] = Library.nt_exp_conjunta;
		_matriz[Library.nt_exp_conjunta][Library.tk_identificador] = Library.nt_exp_conjunta;
		// EXP_CONJ_I
		Arrays.fill(_matriz[Library.nt_exp_conjunta_i], Library.nt_epsilon);
		_matriz[Library.nt_exp_conjunta_i][Library.tk_ou] = Library.nt_exp_conjunta_i;
		//EXP_RELACIONAL
		_matriz[Library.nt_exp_relacional][Library.tk_numero] = Library.nt_exp_relacional; 
		_matriz[Library.nt_exp_relacional][Library.tk_verdadeiro] = Library.nt_exp_relacional; // TODO VERDADE - FALSO
		_matriz[Library.nt_exp_relacional][Library.tk_falso] = Library.nt_exp_relacional;
		_matriz[Library.nt_exp_relacional][Library.tk_subtracao] = Library.nt_exp_relacional;
		_matriz[Library.nt_exp_relacional][Library.tk_menor] = Library.nt_exp_relacional;
		_matriz[Library.nt_exp_relacional][Library.tk_cadeia_de_caracteres] = Library.nt_exp_relacional;
		_matriz[Library.nt_exp_relacional][Library.tk_nao] = Library.nt_exp_relacional;
		_matriz[Library.nt_exp_relacional][Library.tk_soma] = Library.nt_exp_relacional;
		_matriz[Library.nt_exp_relacional][Library.tk_caractere_l] = Library.nt_exp_relacional;  
		_matriz[Library.nt_exp_relacional][Library.tk_abrir_parentese] = Library.nt_exp_relacional;
		_matriz[Library.nt_exp_relacional][Library.tk_identificador] = Library.nt_exp_relacional;
		//EXP_RELACIONAL_I
		Arrays.fill(_matriz[Library.nt_exp_relacional_i], Library.nt_epsilon);
		_matriz[Library.nt_exp_relacional_i][Library.tk_e] = Library.nt_exp_relacional_i;
		// NOT_OPC
		Arrays.fill(_matriz[Library.nt_not_opc], Library.nt_epsilon);
		_matriz[Library.nt_not_opc][Library.tk_nao] = Library.nt_not_opc;
		// EXPR_SIMPLES
		_matriz[Library.nt_exp_simples][Library.tk_numero] = Library.nt_exp_simples; 
		_matriz[Library.nt_exp_simples][Library.tk_verdadeiro] = Library.nt_exp_simples; // TODO VERDADE - FALSO
		_matriz[Library.nt_exp_simples][Library.tk_falso] = Library.nt_exp_simples; 
		_matriz[Library.nt_exp_simples][Library.tk_subtracao] = Library.nt_exp_simples_c2;
		_matriz[Library.nt_exp_simples][Library.tk_menor] = Library.nt_exp_simples;
		_matriz[Library.nt_exp_simples][Library.tk_cadeia_de_caracteres] = Library.nt_exp_simples;
		_matriz[Library.nt_exp_simples][Library.tk_soma] = Library.nt_exp_simples_c2;
		_matriz[Library.nt_exp_simples][Library.tk_caractere_l] = Library.nt_exp_simples;  
		_matriz[Library.nt_exp_simples][Library.tk_abrir_parentese] = Library.nt_exp_simples;
		_matriz[Library.nt_exp_simples][Library.tk_identificador] = Library.nt_exp_simples;
		// OP+|- | * | /
		_matriz[Library.nt_op_mais_menos][Library.tk_soma] = Library.nt_op_mais_menos_c1;
		_matriz[Library.nt_op_mais_menos][Library.tk_subtracao] = Library.nt_op_mais_menos_c2;
		_matriz[Library.nt_op_multi_div][Library.tk_multiplicacao] = Library.nt_op_multi_div_c1;
		_matriz[Library.nt_op_multi_div][Library.tk_divisao] = Library.nt_op_multi_div_c2;
		// TERMO
		_matriz[Library.nt_termo][Library.tk_numero] = Library.nt_termo; 
		_matriz[Library.nt_termo][Library.tk_verdadeiro] = Library.nt_termo;
		_matriz[Library.nt_termo][Library.tk_falso] = Library.nt_termo;  // TODO VERDADE - FALSO
		_matriz[Library.nt_termo][Library.tk_menor] = Library.nt_termo;
		_matriz[Library.nt_termo][Library.tk_cadeia_de_caracteres] = Library.nt_termo;
		_matriz[Library.nt_termo][Library.tk_caractere_l] = Library.nt_termo;  
		_matriz[Library.nt_termo][Library.tk_abrir_parentese] = Library.nt_termo;
		_matriz[Library.nt_termo][Library.tk_identificador] = Library.nt_termo;
		// TERMO_I
		Arrays.fill(_matriz[Library.nt_termo_i], Library.nt_epsilon);
		_matriz[Library.nt_termo_i][Library.tk_soma] = Library.nt_termo_i;
		_matriz[Library.nt_termo_i][Library.tk_subtracao] = Library.nt_termo_i;
		// FATOR
		_matriz[Library.nt_fator][Library.tk_numero] = Library.nt_fator_c1; 
		_matriz[Library.nt_fator][Library.tk_verdadeiro] = Library.nt_fator_c2;  // TODO VERDADE - FALSO
		_matriz[Library.nt_fator][Library.tk_falso] = Library.nt_fator_c6;  // TODO VERDADE - FALSO
		_matriz[Library.nt_fator][Library.tk_menor] = Library.nt_id_funcao_e_outros; 
		_matriz[Library.nt_fator][Library.tk_cadeia_de_caracteres] = Library.nt_fator_c3;
		_matriz[Library.nt_fator][Library.tk_caractere_l] = Library.nt_fator_c4;  
		_matriz[Library.nt_fator][Library.tk_abrir_parentese] = Library.nt_fator_c5;
		_matriz[Library.nt_fator][Library.tk_identificador] = Library.nt_id_funcao_e_outros_derivado; 
		//FATOR_I
		Arrays.fill(_matriz[Library.nt_fator_i], Library.nt_epsilon);
		_matriz[Library.nt_fator_i][Library.tk_multiplicacao] = Library.nt_fator_i;
		_matriz[Library.nt_fator_i][Library.tk_divisao] = Library.nt_fator_i;
		// POSSI_F
		Arrays.fill(_matriz[Library.nt_possible_func], Library.nt_epsilon);
		_matriz[Library.nt_possible_func][Library.tk_abrir_parentese] = Library.nt_possible_func;
		// PASSA PARAM
		Arrays.fill(_matriz[Library.nt_passa_param], Library.nt_epsilon);
		_matriz[Library.nt_passa_param][Library.tk_numero] = Library.nt_passa_param; 
		_matriz[Library.nt_passa_param][Library.tk_verdadeiro] = Library.nt_passa_param;  // TODO VERDADE - FALSO
		_matriz[Library.nt_passa_param][Library.tk_falso] = Library.nt_passa_param; 
		_matriz[Library.nt_passa_param][Library.tk_subtracao] = Library.nt_passa_param;
		_matriz[Library.nt_passa_param][Library.tk_menor] = Library.nt_passa_param;
		_matriz[Library.nt_passa_param][Library.tk_cadeia_de_caracteres] = Library.nt_passa_param;
		_matriz[Library.nt_passa_param][Library.tk_nao] = Library.nt_passa_param;
		_matriz[Library.nt_passa_param][Library.tk_soma] = Library.nt_passa_param;
		_matriz[Library.nt_passa_param][Library.tk_caractere_l] = Library.nt_passa_param; 
		_matriz[Library.nt_passa_param][Library.tk_abrir_parentese] = Library.nt_passa_param;
		_matriz[Library.nt_passa_param][Library.tk_identificador] = Library.nt_passa_param;
		// PASSA PARAM I
		Arrays.fill(_matriz[Library.nt_passa_param_i], Library.nt_epsilon);
		_matriz[Library.nt_passa_param_i][Library.tk_virgula] = Library.nt_passa_param;
		// OPERAR_RELACIONALMENTE  '>' | '>=' | '<' | '<=' | '<>'
		Arrays.fill(_matriz[Library.nt_operar_relacionalmente], Library.nt_epsilon);
		_matriz[Library.nt_operar_relacionalmente][Library.tk_menor] = Library.nt_operar_relacionalmente_c1;
		_matriz[Library.nt_operar_relacionalmente][Library.tk_maior] = Library.nt_operar_relacionalmente_c2;
		_matriz[Library.nt_operar_relacionalmente][Library.tk_menorigual] = Library.nt_operar_relacionalmente_c3;
		_matriz[Library.nt_operar_relacionalmente][Library.tk_maiorigual] = Library.nt_operar_relacionalmente_c4;
		_matriz[Library.nt_operar_relacionalmente][Library.tk_diferente] = Library.nt_operar_relacionalmente_c5;
		// DEC_FUNC
		Arrays.fill(_matriz[Library.nt_dec_func], Library.nt_epsilon);
		_matriz[Library.nt_dec_func][Library.tk_funcao] = Library.nt_dec_func;
		
		//DEC_FUNC_i
		_matriz[Library.nt_dec_func_i][Library.tk_identificador] = Library.nt_dec_func_i;
		_matriz[Library.nt_dec_func_i][Library.tk_inteiro] = Library.nt_dec_func_i_c2;
		_matriz[Library.nt_dec_func_i][Library.tk_booleano] = Library.nt_dec_func_i_c2; 
		_matriz[Library.nt_dec_func_i][Library.tk_cadeia] = Library.nt_dec_func_i_c2;
		_matriz[Library.nt_dec_func_i][Library.tk_real] = Library.nt_dec_func_i_c2; 
		_matriz[Library.nt_dec_func_i][Library.tk_caractere] = Library.nt_dec_func_i_c2; 
		//  PARAMENTROS
		Arrays.fill(_matriz[Library.nt_parametros], Library.nt_epsilon);
		_matriz[Library.nt_parametros][Library.tk_inteiro] = Library.nt_parametros;
		_matriz[Library.nt_parametros][Library.tk_booleano] = Library.nt_parametros; 
		_matriz[Library.nt_parametros][Library.tk_cadeia] = Library.nt_parametros;
		_matriz[Library.nt_parametros][Library.tk_real] = Library.nt_parametros; 
		_matriz[Library.nt_parametros][Library.tk_caractere] = Library.nt_parametros;
		// ARRAY  PARRAM
		Arrays.fill(_matriz[Library.nt_array_param], Library.nt_epsilon);
		_matriz[Library.nt_array_param][Library.tk_menor] = Library.nt_array_param;
		// ARRAY INDEX OPT
		Arrays.fill(_matriz[Library.nt_array_indexes_opt], Library.nt_epsilon);
		_matriz[Library.nt_array_indexes_opt][Library.tk_numero] = Library.nt_array_indexes; 
		_matriz[Library.nt_array_indexes_opt][Library.tk_verdadeiro] = Library.nt_array_indexes; // TODO VERDADE - FALSO
		_matriz[Library.nt_array_indexes_opt][Library.tk_falso] = Library.nt_array_indexes; // TODO VERDADE - FALSO
		_matriz[Library.nt_array_indexes_opt][Library.tk_subtracao] = Library.nt_array_indexes;
		_matriz[Library.nt_array_indexes_opt][Library.tk_menor] = Library.nt_array_indexes;
		_matriz[Library.nt_array_indexes_opt][Library.tk_cadeia_de_caracteres] = Library.nt_array_indexes;
		_matriz[Library.nt_array_indexes_opt][Library.tk_soma] = Library.nt_array_indexes;
		_matriz[Library.nt_array_indexes_opt][Library.tk_caractere_l] = Library.nt_array_indexes;  
		_matriz[Library.nt_array_indexes_opt][Library.tk_abrir_parentese] = Library.nt_array_indexes;
		_matriz[Library.nt_array_indexes_opt][Library.tk_identificador] = Library.nt_array_indexes;
		// ARRAY PARAM I		
		Arrays.fill(_matriz[Library.nt_array_param_i], Library.nt_epsilon);
		_matriz[Library.nt_array_param_i][Library.tk_virgula] = Library.nt_array_param_i;
		//
		Arrays.fill(_matriz[Library.nt_parametros_i], Library.nt_epsilon);
		_matriz[Library.nt_parametros_i][Library.tk_virgula] = Library.nt_parametros_i;
		// RETORNO DE FUN
		_matriz[Library.nt_retorno_func][Library.tk_igual] = Library.nt_retorno_func;
		// CORPO
		Arrays.fill(_matriz[Library.nt_corpo], Library.nt_epsilon);
		_matriz[Library.nt_corpo][Library.tk_leia] = Library.nt_corpo;
		_matriz[Library.nt_corpo][Library.tk_inicio] = Library.nt_corpo;
		_matriz[Library.nt_corpo][Library.tk_var] = Library.nt_corpo;
		_matriz[Library.nt_corpo][Library.tk_menor] = Library.nt_corpo;
		_matriz[Library.nt_corpo][Library.tk_escreva] = Library.nt_corpo;
		_matriz[Library.nt_corpo][Library.tk_se] = Library.nt_corpo;
		_matriz[Library.nt_corpo][Library.tk_enquanto] = Library.nt_corpo;
		_matriz[Library.nt_corpo][Library.tk_identificador] = Library.nt_corpo;
		//COMANDOS
		_matriz[Library.nt_comandos][Library.tk_leia] = Library.nt_comandos_c1;
		_matriz[Library.nt_comandos][Library.tk_inicio] = Library.nt_comandos_c2;
		_matriz[Library.nt_comandos][Library.tk_var] = Library.nt_dec_var;
		_matriz[Library.nt_comandos][Library.tk_menor] = Library.nt_comandos_c3;
		_matriz[Library.nt_comandos][Library.tk_escreva] = Library.nt_comandos_c4;
		_matriz[Library.nt_comandos][Library.tk_se] = Library.nt_comandos_c5;
		_matriz[Library.nt_comandos][Library.tk_enquanto] = Library.nt_comandos_c6;
		_matriz[Library.nt_comandos][Library.tk_identificador] = Library.nt_comandos_c7;
		//LEIA_i
		Arrays.fill(_matriz[Library.nt_leitura_i], Library.nt_epsilon);
		_matriz[Library.nt_leitura_i][Library.tk_virgula] = Library.nt_leitura_i;
		// ESCREVIVEL_I
		Arrays.fill(_matriz[Library.nt_escrevivel_i], Library.nt_epsilon);
		_matriz[Library.nt_escrevivel_i][Library.tk_virgula] = Library.nt_escrevivel_i;
		//SE
		Arrays.fill(_matriz[Library.nt_else_opc], Library.nt_epsilon);
		_matriz[Library.nt_else_opc][Library.tk_senao] = Library.nt_else_opc;
		// WHO NEXT
		_matriz[Library.nt_whos_next][Library.tk_igual] = Library.nt_whos_next;
		_matriz[Library.nt_whos_next][Library.tk_abrir_parentese] = Library.nt_whos_next_c2;
		
		// ESCRiVEL
		_matriz[Library.nt_escrevivel][Library.tk_soma] = Library.nt_escrevivel;
		_matriz[Library.nt_escrevivel][Library.tk_subtracao] = Library.nt_escrevivel;
		_matriz[Library.nt_escrevivel][Library.tk_menor] = Library.nt_escrevivel_c2;
		_matriz[Library.nt_escrevivel][Library.tk_identificador] = Library.nt_escrevivel_c2;
		_matriz[Library.nt_escrevivel][Library.tk_numero] = Library.nt_escrevivel_c2;
		_matriz[Library.nt_escrevivel][Library.tk_cadeia] = Library.nt_escrevivel_c2;
		_matriz[Library.nt_escrevivel][Library.tk_caractere] = Library.nt_escrevivel_c2;
		_matriz[Library.nt_escrevivel][Library.tk_abrir_parentese] = Library.nt_escrevivel_c2;
		
		// TERMO E
	
		_matriz[Library.nt_termo_e][Library.tk_menor] = Library.nt_termo_e;
		_matriz[Library.nt_termo_e][Library.tk_identificador] = Library.nt_termo_e;
		_matriz[Library.nt_termo_e][Library.tk_numero] = Library.nt_termo_e;
		_matriz[Library.nt_termo_e][Library.tk_cadeia] = Library.nt_termo_e;
		_matriz[Library.nt_termo_e][Library.tk_caractere] = Library.nt_termo_e;
		_matriz[Library.nt_termo_e][Library.tk_abrir_parentese] = Library.nt_termo_e;
		
		//  TERMO I E
		Arrays.fill(_matriz[Library.nt_termo_i_e], Library.nt_epsilon);
		_matriz[Library.nt_termo_i_e][Library.tk_soma] = Library.nt_termo_i_e;
		_matriz[Library.nt_termo_i_e][Library.tk_subtracao] = Library.nt_termo_i_e;
		
		// FATOR E
		_matriz[Library.nt_fator_e][Library.tk_numero] = Library.nt_fator_e_c1; 
		_matriz[Library.nt_fator_e][Library.tk_menor] = Library.nt_fator_e_c4; 
		_matriz[Library.nt_fator_e][Library.tk_cadeia_de_caracteres] = Library.nt_fator_e_c5;
		_matriz[Library.nt_fator_e][Library.tk_caractere_l] = Library.nt_fator_e_c6;  
		_matriz[Library.nt_fator_e][Library.tk_abrir_parentese] = Library.nt_fator_e_c7;
		_matriz[Library.nt_fator_e][Library.tk_identificador] = Library.nt_fator_e_c4; 
		
		//  FATOR I E 
		Arrays.fill(_matriz[Library.nt_fator_i_e], Library.nt_epsilon);
		_matriz[Library.nt_fator_i_e][Library.tk_multiplicacao] = Library.nt_fator_i_e;
		_matriz[Library.nt_fator_i_e][Library.tk_divisao] = Library.nt_fator_i_e;
		
		// TODO AQUI
	}
	
	public void openFile() throws IOException{
		try {
			File dir = new File("./programas");
			if (!dir.exists()) {
				System.out.println("NAO EXISTE");
				System.exit(-1);
			}
			File listaDeArquivos[] = dir.listFiles();
			
			//Percorre os arquivos na pasta
			for (int i = 0; i < listaDeArquivos.length; i++) {
				//Se for diretorio ou for arquivo de saida , passa pro proximo
				if (listaDeArquivos[i].isDirectory()
                   || listaDeArquivos[i].getName().startsWith("t_")
                   || listaDeArquivos[i].getName().endsWith(".jar"))
					continue;

				//verificando se o arquivo existe para comecar a analisar
				if (listaDeArquivos[i].exists()) {
					System.out.println("Lendo "+listaDeArquivos[i].getName());
					readLines(listaDeArquivos[i]);
				}
			}
		}
		catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		System.out.println("Leitura finalizada. Resultados em ./saidas/t_*");
					
	}
	
	public void readLines(File FILE_NAME) throws IOException{
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(FILE_NAME);
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(reader);
	
		File file = new File("./saidas/p_"+FILE_NAME.getName());
		//FileWriter writer = new FileWriter(file);
		//PrintWriter gravarArq = new PrintWriter(writer);
	
		String linha = null;
		//System.out.println();
		try {
			linha = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String elementos[];
		while (linha != null) {
			if(!linha.equals("")){
				elementos = linha.trim().split(" ");
				//System.out.println("Lendo "+elementos[1]);
				
				tokens.add(returnTokenId(elementos[1]));
				linha_tokens.add(elementos[0]);
			}
			try {
				linha = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		stream.close();
		//writer.close();
		br.close();
	}
	
	public void analises()
	{
		pilha.push(Library.tk_dolar);
		pilha.push(Library.nt_programa);
		
		tokens.add(Library.tk_const);
		tokens.add(Library.tk_inicio);
		tokens.add(Library.tk_inteiro);
		tokens.add(Library.tk_identificador);
		tokens.add(Library.tk_igual);
		tokens.add(Library.tk_menor);
		tokens.add(Library.tk_menor);
		tokens.add(Library.tk_identificador);
		tokens.add(Library.tk_maior);
		tokens.add(Library.tk_maior);
		tokens.add(Library.tk_identificador);
		tokens.add(Library.tk_soma);
		tokens.add(Library.tk_numero);
		tokens.add(Library.tk_soma);
		tokens.add(Library.tk_numero);
		tokens.add(Library.tk_multiplicacao);
		tokens.add(Library.tk_identificador);
		tokens.add(Library.tk_abrir_parentese);
		tokens.add(Library.tk_numero);
		tokens.add(Library.tk_fechar_parentese);
		tokens.add(Library.tk_maior);
		tokens.add(Library.tk_identificador);
		tokens.add(Library.tk_virgula);
		tokens.add(Library.tk_identificador);
		tokens.add(Library.tk_igual);
		tokens.add(Library.tk_numero);
		tokens.add(Library.tk_pontoevirgula);
		tokens.add(Library.tk_inteiro);
		tokens.add(Library.tk_identificador);
		tokens.add(Library.tk_igual);
		tokens.add(Library.tk_numero);
		tokens.add(Library.tk_virgula);
		tokens.add(Library.tk_identificador);
		tokens.add(Library.tk_igual);
		tokens.add(Library.tk_numero);
		tokens.add(Library.tk_pontoevirgula);
		tokens.add(Library.tk_fim);
		
		startMatriz();
		int valor;
		while(true){
			valor = pilha.peek();
			if(valor == Library.tk_dolar && tokens.isEmpty())
				Debuger.sysPrint("Sucess\n");
			else if(pilha.isEmpty())
				Debuger.sysPrint("Empty\n");
			else if(pilha.isEmpty() && tokens.size() != 0)
				Debuger.sysPrint("Error\n");
			else if(valor == Library.tk_dolar)
				Debuger.sysPrint("Sucess\n");
			
			else if (valor == tokenAtualId())
			{
				consumirToken();
				pilha.pop();
				Debuger.sysPrint("Consumir: "+valor);
			}
			else if(valor >= 44 && valor <= 200)
			{
				
				gerarProducao(valor);
				Debuger.sysPrint("gerar: "+valor);
			} else
			Debuger.sysPrint("LOL SAIU ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

	private void consumirToken() {
		i++;
	}

	private int tokenAtualId(){	
		if(i >= tokens.size())
		{ 
			return Library.tk_dolar;
		}
		Integer aux = tokens.get(i);
		return get_token_id(aux);
	}

	private int get_token_id(Integer aux) {
		
		return aux;
	}
	
	
	private int gerarProducao(int nt)
	{
		int nt_x = _matriz[nt][tokenAtualId()];
		//Debuger.sysPrint("RESULT: "+nt_x +" "+ nt + " " +tokenAtualId() );
		pilha.pop();
		switch(nt_x)
		{
		case Library.nt_inicio_const_var_func:
			pilha.push(Library.nt_dec_const_var_derivada);
			pilha.push(Library.nt_dec_const);
			break;
		case Library.nt_inicio_func:
			// <Decl_Func> <Decl_Main>
			pilha.push(Library.nt_dec_main);
			pilha.push(Library.nt_dec_func);
			break;
		case Library.nt_inicio_var_func:
			//<Decl_Var> <Decl_Func> <Decl_Main>
			pilha.push(Library.nt_dec_main);
			pilha.push(Library.nt_dec_func);
			pilha.push(Library.nt_dec_var);
			break;
			//'const' 'inicio' <Decl_Const_Continuo> 'fim'
		case Library.nt_dec_const:
			pilha.push(Library.tk_fim);
			pilha.push(Library.nt_dec_const_continuo);
			pilha.push(Library.tk_inicio);
			pilha.push(Library.tk_const);
			break;
		case Library.nt_dec_const_i:
			//',' Identificador '=' <Expr_Relacional_Booleana> <Decl_Const_I> | 
			pilha.push(Library.nt_dec_const_i);
			pilha.push(Library.nt_exp_relacional_bool); 
			pilha.push(Library.tk_igual);
			pilha.push(Library.tk_identificador);
			pilha.push(Library.tk_virgula);
			break;
		case Library.nt_dec_const_continuo:
			//Tipo Identificador '=' <Expr_Relacional_Booleana> <Decl_Const_I> ';' <Decl_Const_II>
			pilha.push(Library.nt_dec_const_ii);
			pilha.push(Library.tk_pontoevirgula);
			pilha.push(Library.nt_dec_const_i);
			pilha.push(Library.nt_exp_relacional_bool);
			pilha.push(Library.tk_igual);
			pilha.push(Library.tk_identificador);
			pilha.push(Library.nt_tipo_n);
			break;
		case Library.nt_dec_const_ii:
			//Tipo Identificador '=' <Expr_Relacional_Booleana> <Decl_Const_I> ';' <Decl_Const_II> |
			pilha.push(Library.nt_dec_const_ii);
			pilha.push(Library.tk_pontoevirgula);
			pilha.push(Library.nt_dec_const_i);
			pilha.push(Library.nt_exp_relacional_bool); 
			pilha.push(Library.tk_igual);
			pilha.push(Library.tk_identificador);
			pilha.push(Library.nt_tipo_n);
			break;
		case Library.nt_dec_const_var_derivada_c1:
			//<Decl_Var> <Decl_Func> <Decl_Main> 
			pilha.push(Library.nt_dec_main);
			pilha.push(Library.nt_dec_func);
			pilha.push(Library.nt_dec_var);
			break;
		case Library.nt_dec_const_var_derivada_c2:
			//<Decl_Func> <Decl_Main>
			pilha.push(Library.nt_dec_main);
			pilha.push(Library.nt_dec_func);
			break;
		case Library.nt_dec_var:
			//'var' 'inicio' <Decl_Var_Continuo> 'fim'
			pilha.push(Library.tk_fim);
			pilha.push(Library.nt_dec_var_continuo);
			pilha.push(Library.tk_inicio);
			pilha.push(Library.tk_var);
			break;
		case Library.nt_dec_var_continuo:
			//Tipo <Array> Identificador <Decl_Var_I> ';' <Decl_Var_II>
			pilha.push(Library.nt_dec_var_ii);
			pilha.push(Library.tk_pontoevirgula);
			pilha.push(Library.nt_dec_var_i);			
			pilha.push(Library.tk_identificador);
			pilha.push(Library.nt_array);
			pilha.push(Library.nt_tipo_n);
			break;
		case Library.nt_dec_var_i:
			//',' <Array> Identificador <Decl_Var_I> 
			pilha.push(Library.nt_dec_var_i);
			pilha.push(Library.tk_identificador);
			pilha.push(Library.nt_array);
			pilha.push(Library.tk_virgula);
			break;
		case Library.nt_dec_var_ii:
			// Tipo <Array> Identificador <Decl_Var_I> ';' <Decl_Var_II> | 
			pilha.push(Library.nt_dec_var_ii);
			pilha.push(Library.tk_pontoevirgula);
			pilha.push(Library.nt_dec_var_i);			
			pilha.push(Library.tk_identificador);
			pilha.push(Library.nt_array);
			pilha.push(Library.nt_tipo_n);
			break;
		case Library.nt_array:
			 pilha.push(Library.tk_maior);
			 pilha.push(Library.tk_maior);
			 pilha.push(Library.nt_array_i);
			 pilha.push(Library.nt_array_indexes);
			 pilha.push(Library.tk_menor);
             pilha.push(Library.tk_menor);
			break;
		case Library.nt_array_indexes:
			pilha.push(Library.nt_exp_simples);
			break;
		case Library.nt_array_i:
			pilha.push(Library.nt_array_i);
			pilha.push(Library.nt_array_indexes);
			pilha.push(Library.tk_virgula);
			break;
		case Library.nt_exp_relacional_bool:
			// <Expr_Conjunta> <Expr_Conjunta_I>
			pilha.push(Library.nt_exp_conjunta_i);
			pilha.push(Library.nt_exp_conjunta);
			break;
		case Library.nt_exp_conjunta:
			// <Expr_Relacional> <Expr_Relacional_I>  
			pilha.push(Library.nt_exp_relacional_i);
			pilha.push(Library.nt_exp_relacional);
			break;
		case Library.nt_exp_conjunta_i:
			//'ou' <Expr_Conjunta> <Expr_Conjunta_I> | 
			pilha.push(Library.nt_exp_conjunta_i);
			pilha.push(Library.nt_exp_conjunta);
			pilha.push(Library.tk_ou);
			break;
	
		case Library.nt_exp_relacional:
			// <Not_Opc> <Expr_Simples> <Operar_Relacionalmente>  
			pilha.push(Library.nt_operar_relacionalmente); 
			pilha.push(Library.nt_exp_simples);
			pilha.push(Library.nt_not_opc);
			Debuger.sysPrint("EMPILHOU");
			break;
		case Library.nt_operar_relacionalmente_c1:
			//<Not_Opc> <Expr_Simples> 
			pilha.push(Library.nt_exp_simples);
			pilha.push(Library.nt_not_opc);
			pilha.push(Library.tk_menor);
			break;
		case Library.nt_operar_relacionalmente_c2:
			//<Not_Opc> <Expr_Simples> 
			pilha.push(Library.nt_exp_simples);
			pilha.push(Library.nt_not_opc);
			pilha.push(Library.tk_maior);		
			break;
		case Library.nt_operar_relacionalmente_c3:
			//<Not_Opc> <Expr_Simples> 
			pilha.push(Library.nt_exp_simples);
			pilha.push(Library.nt_not_opc);
			pilha.push(Library.tk_menorigual);
			break;
		case Library.nt_operar_relacionalmente_c4:
			pilha.push(Library.nt_exp_simples);
			pilha.push(Library.nt_not_opc);
			pilha.push(Library.tk_maiorigual);	
			break;
		case Library.nt_operar_relacionalmente_c5:
			pilha.push(Library.nt_exp_simples);
			pilha.push(Library.nt_not_opc);
			pilha.push(Library.tk_diferente);	
			break;
		case Library.nt_exp_relacional_i:
			// 'e' <Expr_Relacional> <Expr_Relacional_I> | 
			pilha.push(Library.nt_exp_relacional_i);
			pilha.push(Library.nt_exp_relacional);
			pilha.push(Library.tk_e);
			break;
		case Library.nt_not_opc:
			//  'nao' <Not_Opc> |
			pilha.push(Library.nt_not_opc);
			pilha.push(Library.tk_nao);
			break;
		case Library.nt_exp_simples_c2:
			//<Operador_Mais_Menos> <Termo> <Termo_I> 
			pilha.push(Library.nt_termo_i);
			pilha.push(Library.nt_termo);
			pilha.push(Library.nt_op_mais_menos);
			break;
		case Library.nt_exp_simples:
			// <Termo> <Termo_I> 
			pilha.push(Library.nt_termo_i);
			pilha.push(Library.nt_termo);
			
			break;
		case Library.nt_op_mais_menos_c1:
			pilha.push(Library.tk_soma);
			break;
		case Library.nt_op_mais_menos_c2:
			pilha.push(Library.tk_subtracao);
			break;
		case Library.nt_op_multi_div_c1:
			pilha.push(Library.tk_multiplicacao);
			break;
		case Library.nt_op_multi_div_c2:
			pilha.push(Library.tk_divisao);
			break;
		case Library.nt_termo:
			//<Fator> <Fator_I>
			pilha.push(Library.nt_fator_i);
			pilha.push(Library.nt_fator);
			break;
		case Library.nt_termo_i:
			// <Operador_Mais_Menos> <Termo> <Termo_I>
			pilha.push(Library.nt_termo_i);
			pilha.push(Library.nt_termo);
			pilha.push(Library.nt_op_mais_menos);
			break;
		case Library.nt_fator_c1:
			pilha.push(Library.tk_numero);
			break;
		case Library.nt_fator_c2:
			pilha.push(Library.tk_verdadeiro);
			break;
		case Library.nt_fator_c6:
			pilha.push(Library.tk_falso);
			break;
		case Library.nt_fator_c3:
			pilha.push(Library.tk_cadeia_de_caracteres);
			break;
		case Library.nt_fator_c4:
			pilha.push(Library.tk_caractere_l);
			break;
		case Library.nt_fator_c5:
			pilha.push(Library.tk_fechar_parentese);
			pilha.push(Library.nt_exp_relacional_bool);
			pilha.push(Library.tk_abrir_parentese);
			break;
		case Library.nt_fator_i:
			//<Operador_Mult_Div> <Fator> <Fator_I>
			pilha.push(Library.nt_fator_i);
			pilha.push(Library.nt_fator);
			pilha.push(Library.nt_op_multi_div);
			break;
		case Library.nt_id_funcao_e_outros:
			//'<''<' <Array_Indexes> <Array_I> '>''>' Identificador
			pilha.push(Library.tk_identificador);
			pilha.push(Library.tk_maior);
			pilha.push(Library.tk_maior);
			pilha.push(Library.nt_array_i);
			pilha.push(Library.nt_array_indexes);
			pilha.push(Library.tk_menor);
			pilha.push(Library.tk_menor);
			break;
		case Library.nt_id_funcao_e_outros_derivado:
			//Identificador <Possible_Func>
			pilha.push(Library.nt_possible_func);
			pilha.push(Library.tk_identificador);
			break;
		case Library.nt_possible_func:
			//'(' <Passa_Param> ')'
			
			pilha.push(Library.tk_fechar_parentese);
			pilha.push(Library.nt_passa_param);
			pilha.push(Library.tk_abrir_parentese);
			break;
		case Library.nt_passa_param:
			//<Expr_Relacional_Booleana> <Passa_Param_I> | 
			pilha.push(Library.nt_passa_param_i);
			pilha.push(Library.nt_exp_relacional_bool);
			break;
		case Library.nt_passa_param_i:
			//',' <Expr_Relacional_Booleana> <Passa_Param_I> | 
			pilha.push(Library.nt_passa_param_i);
			pilha.push(Library.nt_exp_relacional_bool);
			pilha.push(Library.tk_virgula);
			break;	
		case Library.nt_dec_func:
		//	'funcao' <Decl_Func_I> <Decl_Func> |
			
			pilha.push(Library.nt_dec_func);
			pilha.push(Library.nt_dec_func_i);
			pilha.push(Library.tk_funcao);
			break;
		case Library.nt_dec_func_i:
			//Identificador '(' <Parametros> ')' 'inicio' <Corpo> 'fim' 
			pilha.push(Library.tk_fim);
			pilha.push(Library.nt_corpo);
			pilha.push(Library.tk_inicio);
			pilha.push(Library.tk_fechar_parentese);
			pilha.push(Library.nt_parametros);
			pilha.push(Library.tk_abrir_parentese);
			pilha.push(Library.tk_identificador);
			
			break;
		case Library.nt_dec_func_i_c2:
			// Tipo Identificador '(' <Parametros> ')' 'inicio' <Corpo> <Retorno_Func> 'fim'
			pilha.push(Library.tk_fim);
			pilha.push(Library.nt_retorno_func);
			pilha.push(Library.nt_corpo);
			pilha.push(Library.tk_inicio);
			pilha.push(Library.tk_fechar_parentese);
			pilha.push(Library.nt_parametros);
			pilha.push(Library.tk_abrir_parentese);
			pilha.push(Library.tk_identificador);
			pilha.push(Library.nt_tipo_n);
			break;
		case Library.nt_parametros:
			//Tipo <Array_Param> Identificador <Parametros_I> 
			pilha.push(Library.nt_parametros_i);
			pilha.push(Library.tk_identificador);
			pilha.push(Library.nt_array_param);
			pilha.push(Library.nt_tipo_n);
			break;
		case Library.nt_array_param:
			// '<''<' <Array_Indexes_Opt> <Array_Param_I> '>''>'
			pilha.push(Library.tk_maior);
			pilha.push(Library.tk_maior);
			pilha.push(Library.nt_array_param_i);
			pilha.push(Library.nt_array_indexes_opt);
			pilha.push(Library.tk_menor);
			pilha.push(Library.tk_menor);
			break;
		case Library.nt_array_param_i:
			//',' <Array_Indexes_Opt> <Array_Param_I> |
			pilha.push(Library.nt_array_param_i);
			pilha.push(Library.nt_array_indexes_opt);
			pilha.push(Library.tk_virgula);
			break;
		case Library.nt_parametros_i:
			//',' Tipo <Array_Param> Identificador <Parametros_I> |
			pilha.push(Library.nt_parametros_i);
			pilha.push(Library.tk_identificador);
			pilha.push(Library.nt_array_param);
			pilha.push(Library.nt_tipo_n);
			pilha.push(Library.tk_virgula);
			break;
		case Library.nt_retorno_func:
			//'=' '>' <Expr_Relacional_Booleana> '=' '>' ';'
			pilha.push(Library.tk_pontoevirgula);
			pilha.push(Library.tk_maior);
			pilha.push(Library.tk_igual);
			pilha.push(Library.nt_exp_relacional_bool);
			pilha.push(Library.tk_maior);
			pilha.push(Library.tk_igual);
			break;
		case Library.nt_corpo:
			//<Comandos> <Corpo> | 
			pilha.push(Library.nt_corpo);
			pilha.push(Library.nt_comandos);
			break;
		case Library.nt_comandos_c1:
			//'leia' '(' <Array> Identificador <Leitura_I> ')' ';'
			pilha.push(Library.tk_pontoevirgula);
			pilha.push(Library.tk_fechar_parentese);
			pilha.push(Library.nt_leitura_i);
			pilha.push(Library.tk_identificador);
			pilha.push(Library.nt_array);
			pilha.push(Library.tk_abrir_parentese);
			pilha.push(Library.tk_leia);
			break;
		case Library.nt_leitura_i:  
			//',' <Array> Identificador <Leitura_I> |
			pilha.push(Library.nt_leitura_i);
			pilha.push(Library.tk_identificador);
			pilha.push(Library.nt_array);
			pilha.push(Library.tk_virgula);
			break;			
		case Library.nt_comandos_c4:
			//'escreva' '(' <Escrevivel> <Escrevivel_I> ')' ';'
			pilha.push(Library.tk_pontoevirgula);
			pilha.push(Library.tk_fechar_parentese);
			pilha.push(Library.nt_escrevivel_i);
			pilha.push(Library.nt_escrevivel);
			pilha.push(Library.tk_abrir_parentese);
			pilha.push(Library.tk_escreva);
			break;
		case Library.nt_escrevivel: 
			 //<Operador_Mais_Menos> <Termo_E> <Termo_I_E>
			pilha.push(Library.nt_termo_i_e);
			pilha.push(Library.nt_termo_e);
			pilha.push(Library.nt_op_mais_menos);
			break;
		case Library.nt_escrevivel_c2: // TODO FAZER 
			//<Termo_E> <Termo_I_E>
			pilha.push(Library.nt_termo_i_e);
			pilha.push(Library.nt_termo_e);
			break;
		case Library.nt_termo_i_e:
			// <Operador_Mais_Menos> <Termo_E> <Termo_I_E> 
			pilha.push(Library.nt_fator_i_e);
			pilha.push(Library.nt_fator_e);
			pilha.push(Library.nt_op_mais_menos);
			break;
		case Library.nt_termo_e:	
			//<Fator_E> <Fator_I_E>
			pilha.push(Library.nt_fator_i_e);
			pilha.push(Library.nt_fator_e);
			break;
		case Library.nt_fator_e_c1:
			pilha.push(Library.tk_numero);
			break;
		case Library.nt_fator_e_c4:
			//<Array> Identificador
			pilha.push(Library.tk_identificador);
			pilha.push(Library.nt_array);
			break;
		case Library.nt_fator_e_c5:
			pilha.push(Library.tk_cadeia_de_caracteres);
			break;
		case Library.nt_fator_e_c6:
			pilha.push(Library.tk_caractere_l);
			break;
		case Library.nt_fator_e_c7:
			//'(' <Escrevivel> ')'
			pilha.push(Library.tk_fechar_parentese);
			pilha.push(Library.nt_escrevivel);
			pilha.push(Library.tk_abrir_parentese);
			break;
		case Library.nt_fator_i_e:
			pilha.push(Library.nt_fator_i_e);
			pilha.push(Library.nt_fator_e);
			pilha.push(Library.nt_op_multi_div);
			break;
		case Library.nt_escrevivel_i:
			//',' <Escrevivel> <Escrevivel_I>
			pilha.push(Library.nt_escrevivel_i);
			pilha.push(Library.nt_escrevivel);
			pilha.push(Library.tk_virgula);
			break;
		case Library.nt_comandos_c5:
			//'se' '(' <Expr_Relacional_Booleana> ')' 'entao' 'inicio' <Corpo> 'fim' <Else_Opc>
			pilha.push(Library.nt_else_opc);
			pilha.push(Library.tk_fim);
			pilha.push(Library.nt_corpo);
			pilha.push(Library.tk_inicio);
			pilha.push(Library.tk_entao);
			pilha.push(Library.tk_fechar_parentese);
			pilha.push(Library.nt_exp_relacional_bool);
			pilha.push(Library.tk_abrir_parentese);
			pilha.push(Library.tk_se);
			break;
		case Library.nt_else_opc:
			//'senao' 'inicio' <Corpo> 'fim' 
			pilha.push(Library.tk_fim);
			pilha.push(Library.nt_corpo);
			pilha.push(Library.tk_inicio);
			pilha.push(Library.tk_senao);
			break;
		case Library.nt_comandos_c6:
			//'enquanto' '(' <Expr_Relacional_Booleana> ')' 'faca' 'inicio' <Corpo> 'fim'
			pilha.push(Library.tk_fim);
			pilha.push(Library.nt_corpo);
			pilha.push(Library.tk_inicio);
			pilha.push(Library.tk_faca);
			pilha.push(Library.tk_fechar_parentese);
			pilha.push(Library.nt_exp_relacional_bool);
			pilha.push(Library.tk_abrir_parentese);
			pilha.push(Library.tk_enquanto);
			break;
		case Library.nt_comandos_c7:
			pilha.push(Library.nt_whos_next);
			pilha.push(Library.tk_identificador);
			break;
		case Library.nt_comandos_c2:
			pilha.push(Library.tk_fim);
			pilha.push(Library.nt_corpo);
			pilha.push(Library.tk_inicio);
			break;
		case Library.nt_comandos_c3:
			//'<''<' <Array_Indexes> <Array_I> '>''>' Identificador '=' <Expr_Relacional_Booleana> ';'			
			pilha.push(Library.tk_pontoevirgula);
			pilha.push(Library.nt_exp_relacional_bool);
			pilha.push(Library.tk_igual);
			pilha.push(Library.tk_identificador);
			pilha.push(Library.tk_maior);
			pilha.push(Library.tk_maior);
			pilha.push(Library.nt_array_i);
			pilha.push(Library.nt_array_indexes);
			pilha.push(Library.tk_menor);
			pilha.push(Library.tk_menor);
			break;
		case Library.nt_whos_next:
			//'=' <Expr_Relacional_Booleana> ';'
			pilha.push(Library.tk_pontoevirgula);
			pilha.push(Library.nt_exp_relacional_bool);
			pilha.push(Library.tk_igual);
			
			break;
		case Library.nt_whos_next_c2:
			// '(' <Passa_Param> ')' ';'
			pilha.push(Library.tk_pontoevirgula);
			pilha.push(Library.tk_fechar_parentese);
			pilha.push(Library.nt_passa_param);
			pilha.push(Library.tk_abrir_parentese);			
			break;
			// TODO AQUIIII 
		case Library.nt_tipo_c1:
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
		case Library.nt_epsilon:
			break;
		default:
			pilha.push(nt);
			return -1;
		}
		return 0;
	}
	
}