package br.com.cabal.dsl.erro;

public class InvalidTLVException extends Exception {
	private static final long serialVersionUID = -636511754803648756L;
	
	public InvalidTLVException(Exception e){
		super(e);
	}
	public InvalidTLVException(String e){
		super(e);
	}
}
