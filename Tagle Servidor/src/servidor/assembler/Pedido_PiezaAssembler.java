package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Pedido_Pieza;

import common.DTOs.Pedido_PiezaDTO;

public class Pedido_PiezaAssembler {
	
	private AccesoBD accesoBD;

	public Pedido_PiezaAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public Pedido_PiezaDTO getPedido_PiezaDTO(Pedido_Pieza pedido_Pieza) {
		Pedido_PiezaDTO pedido_PiezaDTO = new Pedido_PiezaDTO();
		pedido_PiezaDTO.setId(pedido_Pieza.getId());
		pedido_PiezaDTO.setNumero_pedido(pedido_Pieza.getNumero_pedido());
		pedido_PiezaDTO.setPropio(pedido_Pieza.getPropio());
		pedido_PiezaDTO.setStock(pedido_Pieza.getStock());
		/***************************/
		pedido_PiezaDTO.setFecha_solicitud_fabrica(pedido_Pieza.getFecha_solicitud_fabrica());
		pedido_PiezaDTO.setFecha_recepcion_fabrica(pedido_Pieza.getFecha_recepcion_fabrica());
		pedido_PiezaDTO.setFecha_cambio(pedido_Pieza.getFecha_cambio());
		pedido_PiezaDTO.setPnc(pedido_Pieza.getPnc());
		pedido_PiezaDTO.setFecha_envio_agente(pedido_Pieza.getFecha_envio_agente());
		pedido_PiezaDTO.setFecha_recepcion_agente(pedido_Pieza.getFecha_recepcion_agente());
		pedido_PiezaDTO.setPieza_usada(pedido_Pieza.getPieza_usada());
		/***************************/
		BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
		if (pedido_Pieza.getBdg()!=null)
			pedido_PiezaDTO.setBdg(bdgAssemb.getBdgDTO(pedido_Pieza.getBdg()));
		pedido_PiezaDTO.setFecha_solicitud_devolucion(pedido_Pieza.getFecha_solicitud_devolucion());
		pedido_PiezaDTO.setFecha_aprobacion_solicitud_devolucion(pedido_Pieza.getFecha_aprobacion_solicitud_devolucion());
		Devolucion_PiezaAssembler devolucion_piezaAssemb = new Devolucion_PiezaAssembler(accesoBD);
		if (pedido_Pieza.getDevolucion_pieza()!=null)
			pedido_PiezaDTO.setDevolucion_pieza(devolucion_piezaAssemb.getDevolucion_PiezaDTO(pedido_Pieza.getDevolucion_pieza()));
		pedido_PiezaDTO.setEstado_pedido(pedido_Pieza.getEstado_pedido());
		
		Mano_ObraAssembler mano_obraAssemb = new Mano_ObraAssembler(accesoBD);
		if (pedido_Pieza.getMano_obra()!=null)
			pedido_PiezaDTO.setMano_obra(mano_obraAssemb.getMano_ObraDTO(pedido_Pieza.getMano_obra()));
		MuletoAssembler muletoAssemb = new MuletoAssembler(accesoBD);
		if (pedido_Pieza.getMuleto()!=null)
			pedido_PiezaDTO.setMuleto(muletoAssemb.getMuletoDTO(pedido_Pieza.getMuleto()));
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if (pedido_Pieza.getPedido()!=null)
			pedido_PiezaDTO.setPedido(pedidoAssemb.getPedidoDTO(pedido_Pieza.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if (pedido_Pieza.getPieza()!=null)
			pedido_PiezaDTO.setPieza(piezaAssemb.getPiezaDTO(pedido_Pieza.getPieza()));
		return pedido_PiezaDTO;
	}

	public Pedido_Pieza getPedido_Pieza(Pedido_PiezaDTO pedido_PiezaDTO) {
		Pedido_Pieza pedido_Pieza = (Pedido_Pieza) accesoBD.buscarPorId(Pedido_Pieza.class, pedido_PiezaDTO.getId());
		pedido_Pieza.setId(pedido_PiezaDTO.getId());
		pedido_Pieza.setNumero_pedido(pedido_PiezaDTO.getNumero_pedido());
		pedido_Pieza.setPropio(pedido_PiezaDTO.getPropio());
		pedido_Pieza.setStock(pedido_PiezaDTO.getStock());
		/***************************/
		pedido_Pieza.setFecha_solicitud_fabrica(pedido_PiezaDTO.getFecha_solicitud_fabrica());
		pedido_Pieza.setFecha_recepcion_fabrica(pedido_PiezaDTO.getFecha_recepcion_fabrica());
		pedido_Pieza.setFecha_cambio(pedido_PiezaDTO.getFecha_cambio());
		pedido_Pieza.setPnc(pedido_PiezaDTO.getPnc());
		pedido_Pieza.setFecha_envio_agente(pedido_PiezaDTO.getFecha_envio_agente());
		pedido_Pieza.setFecha_recepcion_agente(pedido_PiezaDTO.getFecha_recepcion_agente());
		pedido_Pieza.setPieza_usada(pedido_PiezaDTO.getPieza_usada());
		/***************************/
		BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
		if (pedido_PiezaDTO.getBdg()!=null)
			pedido_Pieza.setBdg(bdgAssemb.getBdg(pedido_PiezaDTO.getBdg()));
		pedido_Pieza.setFecha_solicitud_devolucion(pedido_PiezaDTO.getFecha_solicitud_devolucion());
		pedido_Pieza.setFecha_aprobacion_solicitud_devolucion(pedido_PiezaDTO.getFecha_aprobacion_solicitud_devolucion());
		Devolucion_PiezaAssembler devolucion_piezaAssemb = new Devolucion_PiezaAssembler(accesoBD);
		if (pedido_PiezaDTO.getDevolucion_pieza()!=null)
			pedido_Pieza.setDevolucion_pieza(devolucion_piezaAssemb.getDevolucion_Pieza(pedido_PiezaDTO.getDevolucion_pieza()));
		pedido_Pieza.setEstado_pedido(pedido_PiezaDTO.getEstado_pedido());
		Mano_ObraAssembler mano_obraAssemb = new Mano_ObraAssembler(accesoBD);
		if (pedido_PiezaDTO.getMano_obra()!=null)
			pedido_Pieza.setMano_obra(mano_obraAssemb.getMano_Obra(pedido_PiezaDTO.getMano_obra()));
		MuletoAssembler muletoAssemb = new MuletoAssembler(accesoBD);
		if (pedido_PiezaDTO.getMuleto()!=null)
			pedido_Pieza.setMuleto(muletoAssemb.getMuleto(pedido_PiezaDTO.getMuleto()));
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if (pedido_PiezaDTO.getPedido()!=null)
			pedido_Pieza.setPedido(pedidoAssemb.getPedido(pedido_PiezaDTO.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if (pedido_PiezaDTO.getPieza()!=null)
			pedido_Pieza.setPieza(piezaAssemb.getPieza(pedido_PiezaDTO.getPieza()));

		return pedido_Pieza;
	}

	public Pedido_Pieza getPedido_PiezaNuevo(Pedido_PiezaDTO pedido_PiezaDTO) {
		Pedido_Pieza pedido_Pieza = new Pedido_Pieza();
		pedido_Pieza.setId(pedido_PiezaDTO.getId());
		pedido_Pieza.setNumero_pedido(pedido_PiezaDTO.getNumero_pedido());
		pedido_Pieza.setPropio(pedido_PiezaDTO.getPropio());
		pedido_Pieza.setStock(pedido_PiezaDTO.getStock());
		/***************************/
		pedido_Pieza.setFecha_solicitud_fabrica(pedido_PiezaDTO.getFecha_solicitud_fabrica());
		pedido_Pieza.setFecha_recepcion_fabrica(pedido_PiezaDTO.getFecha_recepcion_fabrica());
		pedido_Pieza.setFecha_cambio(pedido_PiezaDTO.getFecha_cambio());
		pedido_Pieza.setPnc(pedido_PiezaDTO.getPnc());
		pedido_Pieza.setFecha_envio_agente(pedido_PiezaDTO.getFecha_envio_agente());
		pedido_Pieza.setFecha_recepcion_agente(pedido_PiezaDTO.getFecha_recepcion_agente());
		pedido_Pieza.setPieza_usada(pedido_PiezaDTO.getPieza_usada());
		/***************************/
		BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
		if (pedido_PiezaDTO.getBdg()!=null)
			pedido_Pieza.setBdg(bdgAssemb.getBdg(pedido_PiezaDTO.getBdg()));
		pedido_Pieza.setFecha_solicitud_devolucion(pedido_PiezaDTO.getFecha_solicitud_devolucion());
		pedido_Pieza.setFecha_aprobacion_solicitud_devolucion(pedido_PiezaDTO.getFecha_aprobacion_solicitud_devolucion());
		Devolucion_PiezaAssembler devolucion_piezaAssemb = new Devolucion_PiezaAssembler(accesoBD);
		if (pedido_PiezaDTO.getDevolucion_pieza()!=null)
			pedido_Pieza.setDevolucion_pieza(devolucion_piezaAssemb.getDevolucion_Pieza(pedido_PiezaDTO.getDevolucion_pieza()));
		pedido_Pieza.setEstado_pedido(pedido_PiezaDTO.getEstado_pedido());
		Mano_ObraAssembler mano_obraAssemb = new Mano_ObraAssembler(accesoBD);
		if (pedido_PiezaDTO.getMano_obra()!=null)
			pedido_Pieza.setMano_obra(mano_obraAssemb.getMano_Obra(pedido_PiezaDTO.getMano_obra()));
		MuletoAssembler muletoAssemb = new MuletoAssembler(accesoBD);
		if (pedido_PiezaDTO.getMuleto()!=null)
			pedido_Pieza.setMuleto(muletoAssemb.getMuleto(pedido_PiezaDTO.getMuleto()));
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if (pedido_PiezaDTO.getPedido()!=null)
			pedido_Pieza.setPedido(pedidoAssemb.getPedido(pedido_PiezaDTO.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if (pedido_PiezaDTO.getPieza()!=null)
			pedido_Pieza.setPieza(piezaAssemb.getPieza(pedido_PiezaDTO.getPieza()));

		return pedido_Pieza;
	}

}