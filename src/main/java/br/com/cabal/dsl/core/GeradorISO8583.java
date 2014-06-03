package br.com.cabal.dsl.core;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

import br.com.cabal.dsl.utl.ManipuladorBits;
import br.com.cabal.dsl.vo.VOIsoMenssagem;

public class GeradorISO8583 implements ISOBuilder {
	private ISOMsg msgISO = null;
	private ManipuladorBits manipulador = null;
	private VOIsoMenssagem vo = null;
	Object tipoTXN = null;
	
	public GeradorISO8583(VOIsoMenssagem voIso) throws Exception {
		msgISO = new ISOMsg();
		manipulador = new ManipuladorBits();
		
		if(manipulador.validarDadosVO(voIso))
			throw new Exception("É obrigatorio enviar um VO válido!");
		else
			vo = voIso;
	}
	@Override
	public ISOBuilder mostrarDump() {
		System.out.println("mostrarDump...");
		msgISO.dump(System.out, "");
		return this;
	}

	@Override
	public ISOBuilder semBits(int... unset) {
		try{
			System.out.println("retirarBits...");
			manipulador.retirarBits(unset, msgISO);
		}catch(Exception e){
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public ISOBuilder comBits() {
		try{
			System.out.println("inserirBits...");
			int [] bitsPresentes = manipulador.recuperarBits(vo.getBits(),msgISO);
			if(manipulador.existeBitTLVNaMensagem(vo)){
				manipulador.criarTLV(vo.getTlv(),msgISO);
			}
				
		}catch(Exception e){
			e.printStackTrace();
		}
		return this;
	}

	
	@Override
	public ISOBuilder comPackager() {
		try {
			this.msgISO.setPackager(new GenericPackager(vo.getPackager()));
		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public ISOMsg devolverMensagemISO() {
		System.out.println("devolverMensagemISO...");
		return msgISO;
	}

}
