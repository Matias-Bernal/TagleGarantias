package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Pedido_Pieza_Reclamo_Agente;
import common.DTOs.Pedido_Pieza_Reclamo_AgenteDTO;

public class Pedido_Pieza_Reclamo_AgenteAssembler {

	private AccesoBD accesoBD;

	public Pedido_Pieza_Reclamo_AgenteAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public Pedido_Pieza_Reclamo_AgenteDTO getPedido_Pieza_Reclamo_AgenteDTO(Pedido_Pieza_Reclamo_Agente pedido_Pieza_Reclamo_Agente) {
		Pedido_Pieza_Reclamo_AgenteDTO pedido_Pieza_Reclamo_AgenteDTO = new Pedido_Pieza_Reclamo_AgenteDTO();
		pedido_Pieza_Reclamo_AgenteDTO.setId(pedido_Pieza_Reclamo_Agente.getId());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_Agente.getPedido()!=null)
			pedido_Pieza_Reclamo_AgenteDTO.setPedido(pedidoAssemb.getPedidoDTO(pedido_Pieza_Reclamo_Agente.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_Agente.getPieza()!=null)
			pedido_Pieza_Reclamo_AgenteDTO.setPieza(piezaAssemb.getPiezaDTO(pedido_Pieza_Reclamo_Agente.getPieza()));
		Reclamo_AgenteAssembler reclamo_AgenteAssemb = new Reclamo_AgenteAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_Agente.getReclamo_agente()!=null)
			pedido_Pieza_Reclamo_AgenteDTO.setReclamo_agente(reclamo_AgenteAssemb.getReclamo_AgenteDTO(pedido_Pieza_Reclamo_Agente.getReclamo_agente()));
		return pedido_Pieza_Reclamo_AgenteDTO;
	}

	public Pedido_Pieza_Reclamo_Agente getPedido_Pieza_Reclamo_Agente(Pedido_Pieza_Reclamo_AgenteDTO pedido_Pieza_Reclamo_AgenteDTO) {
		Pedido_Pieza_Reclamo_Agente pedido_Pieza_Reclamo_Agente = (Pedido_Pieza_Reclamo_Agente) accesoBD.buscarPorId(Pedido_Pieza_Reclamo_Agente.class, pedido_Pieza_Reclamo_AgenteDTO.getId());
		pedido_Pieza_Reclamo_Agente.setId(pedido_Pieza_Reclamo_AgenteDTO.getId());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_AgenteDTO.getPedido()!=null)
			pedido_Pieza_Reclamo_Agente.setPedido(pedidoAssemb.getPedido(pedido_Pieza_Reclamo_AgenteDTO.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_AgenteDTO.getPieza()!=null)
			pedido_Pieza_Reclamo_Agente.setPieza(piezaAssemb.getPieza(pedido_Pieza_Reclamo_AgenteDTO.getPieza()));
		Reclamo_AgenteAssembler reclamo_AgenteAssemb = new Reclamo_AgenteAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_AgenteDTO.getReclamo_agente()!=null)
			pedido_Pieza_Reclamo_Agente.setReclamo_agente(reclamo_AgenteAssemb.getReclamo_Agente(pedido_Pieza_Reclamo_AgenteDTO.getReclamo_agente()));
		return pedido_Pieza_Reclamo_Agente;
	}

	public Pedido_Pieza_Reclamo_Agente getPedido_Pieza_Reclamo_AgenteNuevo(Pedido_Pieza_Reclamo_AgenteDTO pedido_Pieza_Reclamo_AgenteDTO) {
		Pedido_Pieza_Reclamo_Agente pedido_Pieza_Reclamo_Agente = new Pedido_Pieza_Reclamo_Agente();
		pedido_Pieza_Reclamo_Agente.setId(pedido_Pieza_Reclamo_AgenteDTO.getId());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_AgenteDTO.getPedido()!=null)
			pedido_Pieza_Reclamo_Agente.setPedido(pedidoAssemb.getPedido(pedido_Pieza_Reclamo_AgenteDTO.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_AgenteDTO.getPieza()!=null)
			pedido_Pieza_Reclamo_Agente.setPieza(piezaAssemb.getPieza(pedido_Pieza_Reclamo_AgenteDTO.getPieza()));
		Reclamo_AgenteAssembler reclamo_AgenteAssemb = new Reclamo_AgenteAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_AgenteDTO.getReclamo_agente()!=null)
			pedido_Pieza_Reclamo_Agente.setReclamo_agente(reclamo_AgenteAssemb.getReclamo_Agente(pedido_Pieza_Reclamo_AgenteDTO.getReclamo_agente()));
		return pedido_Pieza_Reclamo_Agente;	}
}
