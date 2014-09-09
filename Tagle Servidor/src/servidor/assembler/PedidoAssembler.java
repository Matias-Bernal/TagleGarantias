package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Pedido;

import common.DTOs.PedidoDTO;

public class PedidoAssembler {

	private AccesoBD accesoBD;

	public PedidoAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public PedidoDTO getPedidoDTO(Pedido pedido) {
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setId(pedido.getId());
		pedidoDTO.setFecha_solicitud_pedido(pedido.getFecha_solicitud_pedido());
		ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
		if(pedido.getReclamo()!=null)
			pedidoDTO.setReclamo(reclamoAssemb.getReclamoDTO(pedido.getReclamo()));
		return pedidoDTO;
	}

	public Pedido getPedido(PedidoDTO pedidoDTO) {
		Pedido pedido = (Pedido) accesoBD.buscarPorId(Pedido.class, pedidoDTO.getId());
		pedido.setId(pedidoDTO.getId());
		pedido.setFecha_solicitud_pedido(pedidoDTO.getFecha_solicitud_pedido());
		ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
		if(pedidoDTO.getReclamo()!=null)
			pedido.setReclamo(reclamoAssemb.getReclamo(pedidoDTO.getReclamo()));
		return pedido;
	}

	public Pedido getPedidoNuevo(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		pedido.setId(pedidoDTO.getId());
		pedido.setFecha_solicitud_pedido(pedidoDTO.getFecha_solicitud_pedido());
		ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
		if(pedidoDTO.getReclamo()!=null)
			pedido.setReclamo(reclamoAssemb.getReclamo(pedidoDTO.getReclamo()));
		return pedido;
	}

}