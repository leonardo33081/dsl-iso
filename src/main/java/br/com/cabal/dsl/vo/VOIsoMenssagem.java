package br.com.cabal.dsl.vo;

import java.util.List;
import java.util.Map;
/*
 * Nome alterado de VOIsoMenssagem para VOIsoMensagem 
 * */
public class VOIsoMenssagem {

	//basta usar quando genérico
	private String packager;
	private Map<Integer, String> bits;
	
	//usar quando tivermos um refinamento
	private Map<Integer, List<VOIsoTLV>> tlv; 
	private boolean gerarNSU;//bit 11
	private boolean gerarRRN;//bit 37
	private boolean gerarNumeroAutorizacao;//bit 38

	public Map<Integer, List<VOIsoTLV>> getTlv() {
		return tlv;
	}
	public void setTlv(Map<Integer, List<VOIsoTLV>> tlv) {
		this.tlv = tlv;
	}

	/*
	private List<VOIsoTLV> tlv;
	public List<VOIsoTLV> getTlv() {
		return tlv;
	}
	public void setTlv(List<VOIsoTLV> tlv) {
		this.tlv = tlv;
	}
	*/
	public boolean isGerarNSU() {
		return gerarNSU;
	}
	public void setGerarNSU(boolean gerarNSU) {
		this.gerarNSU = gerarNSU;
	}
	public boolean isGerarRRN() {
		return gerarRRN;
	}
	public void setGerarRRN(boolean gerarRRN) {
		this.gerarRRN = gerarRRN;
	}
	public boolean isGerarNumeroAutorizacao() {
		return gerarNumeroAutorizacao;
	}
	public void setGerarNumeroAutorizacao(boolean gerarNumeroAutorizacao) {
		this.gerarNumeroAutorizacao = gerarNumeroAutorizacao;
	}
	
	public Map<Integer, String> getBits() {
		return bits;
	}
	public void setBits(Map<Integer, String> bits) {
		this.bits = bits;
	}
	public String getPackager() {
		return packager;
	}
	public void setPackager(String packager) {
		this.packager = packager;
	}
	
}
