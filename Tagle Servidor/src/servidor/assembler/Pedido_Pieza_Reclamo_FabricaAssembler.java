package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Pedido_Pieza_Reclamo_Fabrica;

import common.DTOs.Pedido_Pieza_Reclamo_FabricaDTO;

public class Pedido_Pieza_Reclamo_FabricaAssembler {
	
	private AccesoBD accesoBD;

	public Pedido_Pieza_Reclamo_FabricaAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public Pedido_Pieza_Reclamo_FabricaDTO getPedido_Pieza_Reclamo_FabricaDTO(Pedido_Pieza_Reclamo_Fabrica pedido_Pieza_Reclamo_Fabrica) {
		Pedido_Pieza_Reclamo_FabricaDTO pedido_Pieza_Reclamo_FabricaDTO = new Pedido_Pieza_Reclamo_FabricaDTO();
		pedido_Pieza_Reclamo_FabricaDTO.setId(pedido_Pieza_Reclamo_Fabrica.getId());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_Fabrica.getPedido()!=null)
			pedido_Pieza_Reclamo_FabricaDTO.setPedido(pedidoAssemb.getPedidoDTO(pedido_Pieza_Reclamo_Fabrica.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_Fabrica.getPieza()!=null)
			pedido_Pieza_Reclamo_FabricaDTO.setPieza(piezaAssemb.getPiezaDTO(pedido_Pieza_Reclamo_Fabrica.getPieza()));
		Reclamo_FabricaAssembler reclamo_FabricaAssemb = new Reclamo_FabricaAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_Fabrica.getReclamo_fabrica()!=null)
			pedido_Pieza_Reclamo_FabricaDTO	.setReclamo_fabrica(reclamo_FabricaAssemb.getReclamo_FabricaDTO(pedido_Pieza_Reclamo_Fabrica.getReclamo_fabrica()));
		return pedido_Pieza_Reclamo_FabricaDTO;
	}

	public Pedido_Pieza_Reclamo_Fabrica getPedido_Pieza_Reclamo_Fabrica(Pedido_Pieza_Reclamo_FabricaDTO pedido_Pieza_Reclamo_FabricaDTO) {
		Pedido_Pieza_Reclamo_Fabrica pedido_Pieza_Reclamo_Fabrica = (Pedido_Pieza_Reclamo_Fabrica) accesoBD.buscarPorId(Pedido_Pieza_Reclamo_Fabrica.class, pedido_Pieza_Reclamo_FabricaDTO.getId());
		pedido_Pieza_Reclamo_Fabrica.setId(pedido_Pieza_Reclamo_FabricaDTO.getId());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_FabricaDTO.getPedido()!=null)
			pedido_Pieza_Reclamo_Fabrica.setPedido(pedidoAssemb.getPedido(pedido_Pieza_Reclamo_FabricaDTO.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_FabricaDTO.getPieza()!=null)
			pedido_Pieza_Reclamo_Fabrica.setPieza(piezaAssemb.getPieza(pedido_Pieza_Reclamo_FabricaDTO.getPieza()));
		Reclamo_FabricaAssembler reclamo_FabricaAssemb = new Reclamo_FabricaAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_FabricaDTO.getReclamo_fabrica()!=null)
			pedido_Pieza_Reclamo_Fabrica.setReclamo_fabrica(reclamo_FabricaAssemb.getReclamo_Fabrica(pedido_Pieza_Reclamo_FabricaDTO.getReclamo_fabrica()));
		return pedido_Pieza_Reclamo_Fabrica;
	}

	public Pedido_Pieza_Reclamo_Fabrica getPedido_Pieza_Reclamo_FabricaNuevo(Pedido_Pieza_Reclamo_FabricaDTO pedido_Pieza_Reclamo_FabricaDTO) {
		Pedido_Pieza_Reclamo_Fabrica pedido_Pieza_Reclamo_Fabrica = new Pedido_Pieza_Reclamo_Fabrica();
		pedido_Pieza_Reclamo_Fabrica.setId(pedido_Pieza_Reclamo_FabricaDTO.getId());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_FabricaDTO.getPedido()!=null)
			pedido_Pieza_Reclamo_Fabrica.setPedido(pedidoAssemb.getPedido(pedido_Pieza_Reclamo_FabricaDTO.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_FabricaDTO.getPieza()!=null)
			pedido_Pieza_Reclamo_Fabrica.setPieza(piezaAssemb.getPieza(pedido_Pieza_Reclamo_FabricaDTO.getPieza()));
		Reclamo_FabricaAssembler reclamo_FabricaAssemb = new Reclamo_FabricaAssembler(accesoBD);
		if(pedido_Pieza_Reclamo_FabricaDTO.getReclamo_fabrica()!=null)
			pedido_Pieza_Reclamo_Fabrica.setReclamo_fabrica(reclamo_FabricaAssemb.getReclamo_Fabrica(pedido_Pieza_Reclamo_FabricaDTO.getReclamo_fabrica()));
		return pedido_Pieza_Reclamo_Fabrica;
	}

}