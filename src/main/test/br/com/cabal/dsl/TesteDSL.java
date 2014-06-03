package br.com.cabal.dsl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.com.cabal.dsl.core.ISO8583Generico;
import br.com.cabal.dsl.vo.VOIsoMenssagem;
import br.com.cabal.dsl.vo.VOIsoTLV;

public class TesteDSL {
//Teste para recriação do jar
	public static void main(String[] args) {
		VOIsoMenssagem i = new VOIsoMenssagem();
		
		Map<Integer, String> b = new HashMap<Integer, String>();
		b.put(0, "0200");
		b.put(3, "002000");
		b.put(4, "000000000122");
		b.put(41, "TGGV1235");
		b.put(48, "12203123");
		
		i.setBits(b);
		i.setPackager("conf/iso87ascii.xml");
		
		int[] unset = {3,41};
		
		//montando uma TLV
		List<VOIsoTLV> tlv = montarTLV();
		Map<Integer, List<VOIsoTLV>> mapaTLV = new HashMap<Integer, List<VOIsoTLV>>();
		mapaTLV.put(48, tlv);
		
		i.setTlv(mapaTLV);
		
		try {
			new ISO8583Generico(i).comPackager().comBits().mostrarDump().devolverMensagemISO();
			//comPackager().comBits().mostrarDump().devolverMensagemISO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static List<VOIsoTLV> montarTLV() {
		List<VOIsoTLV> lista = new LinkedList<VOIsoTLV>();
		
		
		VOIsoTLV tlv = new VOIsoTLV();
		tlv.setTamanho("3");
		tlv.setTag("122");
		tlv.setValor("ABC");
		
		lista.add(tlv);
		
		VOIsoTLV tlv2 = new VOIsoTLV();
		tlv2.setTamanho("3");
		tlv2.setTag("123");
		tlv2.setValor("DEF");
		lista.add(tlv2);
		
		VOIsoTLV tlv3 = new VOIsoTLV();
		tlv3.setTamanho("3");
		tlv3.setTag("124");
		tlv3.setValor("GHI0");
		lista.add(tlv3);
		
		return lista;
	}

}
