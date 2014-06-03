package br.com.cabal.dsl.utl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

import br.com.cabal.dsl.erro.InvalidTLVException;
import br.com.cabal.dsl.vo.VOIsoMenssagem;
import br.com.cabal.dsl.vo.VOIsoTLV;

public class ManipuladorBits {

	/*
	 * Retorna true caso ok e false quando algo estiver errado
	 * */
	public boolean validarDadosVO(VOIsoMenssagem voIso) {
		return (voIso.getBits().isEmpty() || (voIso.getPackager() == null || voIso.getPackager().trim().length() == 0));
				
	}

	public int[] recuperarBits(Map<Integer, String> bits, ISOMsg msgISO) {
		
		int [] bitPresente = new int[bits.size()];
		int indice = 0;
		for (Entry<Integer, String> dados : bits.entrySet()) {
			try {
				msgISO.set(dados.getKey(), dados.getValue());
				System.out.println("Bit " + dados.getKey() + " recebendo o valor " + dados.getValue());
				
				bitPresente[indice] = dados.getKey();
				indice ++;
			} catch (ISOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		}
		return bitPresente;
	}

	public void retirarBits(int[] unset, ISOMsg msgISO) throws Exception{
		msgISO.unset(unset);
	}

	private boolean isNull(Object o){
		return o == null;
	}
	public boolean existeBitTLVNaMensagem(VOIsoMenssagem vo) {
		if(isNull(vo.getTlv()) || vo.getTlv().isEmpty())
			return false;
		else
			return true;
	}

	public void criarTLV(Map<Integer, List<VOIsoTLV>> tlv, ISOMsg msgISO) {
		for (Entry<Integer, List<VOIsoTLV>> tlvs : tlv.entrySet()) {
			try {
				//retirar do objeto iso os bits que existirem no map
				desativarBit(tlvs.getKey(),msgISO);
				//gerar e setar a TLV no objeto ISO
				msgISO.set(tlvs.getKey(), montarTLV(tlvs.getValue()));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String montarTLV(List<VOIsoTLV> value) throws InvalidTLVException {
		StringBuilder tlv = new StringBuilder();
		for (VOIsoTLV voIsoTLV : value) {
			
			if(!validarTLV(voIsoTLV))
				throw new InvalidTLVException("TLV invalida! <TAG=" + voIsoTLV.getTag()
						+"|Tamanho=" + voIsoTLV.getTamanho()
						+"|Valor=" + voIsoTLV.getValor()
						+">");
			
			tlv.append(voIsoTLV.getTag());
			tlv.append(voIsoTLV.getTamanho());
			tlv.append(voIsoTLV.getValor());

		}
		return tlv.toString();
	}

	
	private boolean validarTLV(VOIsoTLV vo){
		return vo.getTamanho().length() == vo.getValor().length();
			
	}

	private void desativarBit(Integer key, ISOMsg msgISO) throws Exception {
		int b [] = {key};
		retirarBits(b, msgISO);
	}

}
