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
	public void producoes(int naoterminal){
		switch(naoterminal){
			case Library.nt_programa:
				pilha.pop();
				pilha.push(Library.nt_inicio_const_var_func);
				break;
		
		}
	}
	

}
