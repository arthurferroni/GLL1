package br.uefs.model;

public class Library {

	
	public static final int tk_programa             =  1;
    public static final int tk_const                =  2;
    public static final int tk_var                  =  3;
    public static final int tk_funcao               =  4;
    public static final int tk_inicio               =  5;
    public static final int tk_fim                  =  6;
    public static final int tk_se                   =  7;
    public static final int tk_entao                =  8;
    public static final int tk_senao                =  9;
    public static final int tk_enquanto             = 10;
    public static final int tk_faca                 = 11;
    public static final int tk_leia                 = 12;
    public static final int tk_escreva              = 13;
    public static final int tk_inteiro              = 14;
    public static final int tk_real                 = 15;
    public static final int tk_booleano             = 16;
    public static final int tk_verdadeiro           = 17;
    public static final int tk_falso                = 18;
    public static final int tk_cadeia               = 19;
    public static final int tk_caractere            = 20;
    public static final int tk_nao                  = 21;
    public static final int tk_e                    = 22;
    public static final int tk_ou                   = 23;
    public static final int tk_soma                 = 24;
    public static final int tk_subtracao            = 25;
    public static final int tk_multiplicacao        = 26;
    public static final int tk_divisao              = 27;
    public static final int tk_diferente            = 28;
    public static final int tk_igual                = 29;
    public static final int tk_menor                = 30;
    public static final int tk_menorigual           = 31;
    public static final int tk_maior                = 32;
    public static final int tk_maiorigual           = 33;
    public static final int tk_identificador        = 34;
    public static final int tk_numero               = 35;
    public static final int tk_pontoevirgula        = 36;
    public static final int tk_virgula              = 37;
    public static final int tk_abrir_parentese      = 38;
    public static final int tk_fechar_parentese     = 39;
    public static final int tk_cadeia_de_caracteres = 40;
    public static final int tk_caractere_l          = 41;
    
    public static final int tk_dolar             	=  0;
	public static final int tk_fimarq               = 42;
	public static final int tk_epsilon              = 43;

// N�O-TERMINAIS //
	public static final int nt_programa	=	44;
	public static final int nt_programa_c2	=	45;
	public static final int nt_programa_c3	=	46;
	public static final int nt_inicio_const_var_func	=	47;
	public static final int nt_dec_const_var_derivada	=	48;
	public static final int nt_dec_const_var_derivada_c2	=	49;
	public static final int nt_inicio_var_func	=	50;
	public static final int nt_inicio_func	=	51;
	public static final int nt_dec_const	=	52;
	public static final int nt_dec_const_continuo	=	53;
	public static final int nt_dec_const_i	=	54;
	public static final int nt_dec_const_i_c2	=	55;
	public static final int nt_dec_const_ii	=	56;
	public static final int nt_dec_const_ii_c2	=	57;
	public static final int nt_dec_var	=	58;
	public static final int nt_dec_var_continuo	=	59;
	public static final int nt_dec_var_i	=	60;
	public static final int nt_dec_var_i_c2	=	61;
	public static final int nt_dec_var_ii	=	62;
	public static final int nt_dec_var_ii_c2	=	63;
	public static final int nt_dec_func	=	64;
	public static final int nt_dec_func_c2	=	65;
	public static final int nt_dec_func_i	=	66;
	public static final int nt_dec_func_i_c2	=	67;
	public static final int nt_parametros	=	68;
	public static final int nt_parametros_c2	=	69;
	public static final int nt_parametros_i	=	70;
	public static final int nt_parametros_i_c2	=	71;
	public static final int nt_dec_main	=	72;
	public static final int nt_exp_relacional_bool	=	73;
	public static final int nt_exp_conjunta	=	74;
	public static final int nt_exp_conjunta_i	=	75;
	public static final int nt_exp_conjunta_i_c2	=	76;
	public static final int nt_exp_relacional	=	77;
	public static final int nt_exp_relacional_i	=	78;
	public static final int nt_exp_relacional_i_c2	=	79;
	public static final int nt_operar_relacionalmente	=	80;
	public static final int nt_operar_relacionalmente_c2	=	81;
	public static final int nt_op_relacional	=	82;
	public static final int nt_op_relacional_c2	=	83;
	public static final int nt_op_relacional_c3	=	84;
	public static final int nt_op_relacional_c4	=	85;
	public static final int nt_op_relacional_c5	=	86;
	public static final int nt_not_opc	=	87;
	public static final int nt_not_opc_c2	=	88;
	public static final int nt_exp_simples	=	89;
	public static final int nt_exp_simples_c2	=	90;
	public static final int nt_termo	=	91;
	public static final int nt_termo_i	=	92;
	public static final int nt_termo_i_c2	=	93;
	public static final int nt_fator	=	94;
	public static final int nt_fator_c2	=	95;
	public static final int nt_fator_c3	=	96;
	public static final int nt_fator_c4	=	97;
	public static final int nt_fator_c5	=	98;
	public static final int nt_fator_c6	=	99;
	public static final int nt_fator_c7	=	100;
	public static final int nt_fator_i	=	101;
	public static final int nt_fator_i_c2	=	102;
	public static final int nt_op_mais_menos	=	103;
	public static final int nt_op_mais_menos_c2	=	104;
	public static final int nt_op_multi_div	=	105;
	public static final int nt_op_multi_div_c2	=	106;
	public static final int nt_id_funcao_e_outros	=	107;
	public static final int nt_id_funcao_e_outros_c2	=	108;
	public static final int nt_id_funcao_e_outros_derivado	=	109;
	public static final int nt_possible_func	=	110;
	public static final int nt_possible_func_c2	=	111;
	public static final int nt_retorno_func	=	112;
	public static final int nt_corpo	=	113;
	public static final int nt_corpo_c2	=	114;
	public static final int nt_comandos	=	115;
	public static final int nt_comandos_c2	=	116;
	public static final int nt_comandos_c3	=	117;
	public static final int nt_comandos_c4	=	118;
	public static final int nt_comandos_c5	=	119;
	public static final int nt_comandos_c6	=	120;
	public static final int nt_comandos_c7	=	121;
	public static final int nt_chama_ou_atribui	=	122;
	public static final int nt_chama_ou_atribui_c2	=	123;
	public static final int nt_whos_next	=	124;
	public static final int nt_whos_next_c2	=	125;
	public static final int nt_novo_escopo	=	126;
	public static final int nt_array	=	127;
	public static final int nt_array_c2	=	128;
	public static final int nt_array_i	=	129;
	public static final int nt_array_i_c2	=	130;
	public static final int nt_array_indexes	=	131;
	public static final int nt_array_param	=	132;
	public static final int nt_array_param_c2	=	133;
	public static final int nt_array_param_i	=	134;
	public static final int nt_array_param_i_c2	=	135;
	public static final int nt_array_indexes_opt	=	136;
	public static final int nt_array_indexes_opt_c2	=	137;
	public static final int nt_dec_leitura	=	138;
	public static final int nt_leitura_i	=	139;
	public static final int nt_leitura_i_c2	=	140;
	public static final int nt_dec_escrita	=	141;
	public static final int nt_escrevivel_i	=	142;
	public static final int nt_escrevivel_i_c2	=	143;
	public static final int nt_escrevivel	=	144;
	public static final int nt_escrevivel_c2	=	145;
	public static final int nt_termo_e	=	146;
	public static final int nt_termo_i_e	=	147;
	public static final int nt_termo_i_e_c2	=	148;
	public static final int nt_fator_e	=	149;
	public static final int nt_fator_e_c2	=	150;
	public static final int nt_fator_e_c3	=	151;
	public static final int nt_fator_e_c4	=	152;
	public static final int nt_fator_e_c5	=	153;
	public static final int nt_fator_i_e	=	154;
	public static final int nt_fator_i_e_c2	=	155;
	public static final int nt_dec_se	=	156;
	public static final int nt_else_opc	=	157;
	public static final int nt_else_opc_c2	=	158;
	public static final int nt_dec_enquanto	=	159;
	public static final int nt_passa_param	=	160;
	public static final int nt_passa_param_c2	=	161;
	public static final int nt_passa_param_i	=	162;
	public static final int nt_passa_param_i_c2	=	163;
	public static final int nt_epsilon	=	164;
	public static final int nt_tipo	=	165;
	public static final int nt_tipo_c2	=	166;
	public static final int nt_tipo_c3	=	167;
	public static final int nt_tipo_c4	=	168;
	public static final int nt_tipo_c5	=	169;
	
}
