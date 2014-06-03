package br.com.cabal.dsl.core;

import org.jpos.iso.ISOMsg;

public interface ISOBuilder {
	
	ISOBuilder mostrarDump();
	ISOBuilder semBits(int... unset);//unset
	ISOBuilder comBits();
	ISOBuilder comPackager();
	ISOMsg devolverMensagemISO();
	//gerarISOAutorizacao().inserirBits(Map<Integer, Object> bits).comPackager(string).retirarBits(int... unset).devolverMensagemISO();
}
