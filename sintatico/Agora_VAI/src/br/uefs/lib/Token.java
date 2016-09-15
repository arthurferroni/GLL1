package br.uefs.lib;

public class Token {
	private String linha;
	private String token;
	private String lexema;
	
	public Token(int linha, String tken, String lexema){
		if (linha < 10){
			this.linha = "0" + linha;
		}
		else {
			this.linha = "" + linha;
		}
		this.token = tken;
		this.lexema = lexema;
	}
	
	public String getToken(){
		return token;
	}
	
	public String getLexema(){
		return lexema;
	}
	
	public String getLinha(){
		return linha;
	}
	
	public void setId(String tken){
		this.token = tken;
	}
	
	public void setLexema(String lexema){
		this.lexema = lexema;
	}
}