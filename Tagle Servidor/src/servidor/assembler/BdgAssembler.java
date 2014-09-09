package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Bdg;

import common.DTOs.BdgDTO;

public class BdgAssembler {

	private AccesoBD accesoBD;

	public BdgAssembler (AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public BdgDTO getBdgDTO(Bdg bdg) {
		BdgDTO bdgDTO = new BdgDTO();
		bdgDTO.setId(bdg.getId());
		bdgDTO.setFecha_bdg(bdg.getFecha_bdg());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if (bdg.getPedido()!=null)
			bdgDTO.setPedido(pedidoAssemb.getPedidoDTO(bdg.getPedido()));
		PiezaAssembler piezaAssem = new PiezaAssembler(accesoBD);
		if (bdg.getPieza()!=null)
			bdgDTO.setPieza(piezaAssem.getPiezaDTO(bdg.getPieza()));
		bdgDTO.setNumero_bdg(bdg.getNumero_bdg());
		return bdgDTO;
	}

	public Bdg getBdg(BdgDTO bdgDTO) {
		Bdg bdg = (Bdg) accesoBD.buscarPorId(Bdg.class, bdgDTO.getId());
		bdg.setId(bdgDTO.getId());
		bdg.setFecha_bdg(bdgDTO.getFecha_bdg());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if (bdgDTO.getPedido()!=null)
			bdg.setPedido(pedidoAssemb.getPedido(bdgDTO.getPedido()));
		PiezaAssembler piezaAssem = new PiezaAssembler(accesoBD);
		if (bdgDTO.getPieza()!=null)
			bdg.setPieza(piezaAssem.getPieza(bdgDTO.getPieza()));
		bdg.setNumero_bdg(bdgDTO.getNumero_bdg());
		return bdg;
	}

	public Bdg getBdgNuevo(BdgDTO bdgDTO) {
		Bdg bdg = new Bdg();
		bdg.setId(bdgDTO.getId());
		bdg.setFecha_bdg(bdgDTO.getFecha_bdg());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if (bdgDTO.getPedido()!=null)
			bdg.setPedido(pedidoAssemb.getPedido(bdgDTO.getPedido()));
		PiezaAssembler piezaAssem = new PiezaAssembler(accesoBD);
		if (bdgDTO.getPieza()!=null)
			bdg.setPieza(piezaAssem.getPieza(bdgDTO.getPieza()));
		bdg.setNumero_bdg(bdgDTO.getNumero_bdg());
		return bdg;
	}

}