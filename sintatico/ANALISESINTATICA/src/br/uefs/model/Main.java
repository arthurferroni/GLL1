package br.uefs.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		try {
			Main.criandotb_predicao();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	static void enderecar_producoes() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("producoes.in"));
		String prod = br.readLine();
		int i = 44;
		while(prod!=null)
		{
			prod = prod.trim();
			System.err.println("public static final int "+ prod + "\t=\t"+i+++";");
			prod = br.readLine();
		}
		
	}
	static void criandotb_predicao() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("tb.in"));
		String linha;
		int i = 0;
		linha = br.readLine();
		while(linha !=null)
		{
			String []tk_tb =  linha.split(";");
			//Arrays.fill(tb_predicao[i], -1); // 
			for(int x = 0; x < tk_tb.length; x++)
				System.err.println(tk_tb[x]);
			
			linha = br.readLine();
			i++;
		}
		br.close();
	}

}
