package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Reclamo_Agente;
import servidor.persistencia.dominio.Reclamo_Fabrica;
import common.DTOs.Reclamo_AgenteDTO;
import common.DTOs.Reclamo_FabricaDTO;

public class Reclamo_AgenteAssembler {
	private AccesoBD accesoBD;

	public Reclamo_AgenteAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public Reclamo_AgenteDTO getReclamo_AgenteDTO(Reclamo_Agente reclamo_agente) {
		Reclamo_AgenteDTO reclamo_agenteDTO = new Reclamo_AgenteDTO();
		reclamo_agenteDTO.setId(reclamo_agente.getId());
		reclamo_agenteDTO.setFecha_reclamo_agente(reclamo_agente.getFecha_reclamo_agente());
		reclamo_agenteDTO.setDescripcion(reclamo_agente.getDescripcion());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if(reclamo_agente.getPedido()!=null)
			reclamo_agenteDTO.setPedido(pedidoAssemb.getPedidoDTO(reclamo_agente.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if(reclamo_agente.getPieza()!=null)
			reclamo_agenteDTO.setPieza(piezaAssemb.getPiezaDTO(reclamo_agente.getPieza()));
		UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
		if(reclamo_agente.getUsuario()!=null)
			reclamo_agenteDTO.setUsuario(usuarioAssemb.getUsuarioDTO(reclamo_agente.getUsuario()));
		return reclamo_agenteDTO;
	}

	public Reclamo_Agente getReclamo_Agente(Reclamo_AgenteDTO reclamo_agenteDTO) {
		Reclamo_Agente reclamo_agente = (Reclamo_Agente) accesoBD.buscarPorId(Reclamo_Agente.class, reclamo_agenteDTO.getId());
		reclamo_agente.setId(reclamo_agenteDTO.getId());
		reclamo_agente.setFecha_reclamo_agente(reclamo_agenteDTO.getFecha_reclamo_agente());
		reclamo_agente.setDescripcion(reclamo_agenteDTO.getDescripcion());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if(reclamo_agenteDTO.getPedido()!=null)
			reclamo_agente.setPedido(pedidoAssemb.getPedido(reclamo_agenteDTO.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if(reclamo_agenteDTO.getPieza()!=null)
			reclamo_agente.setPieza(piezaAssemb.getPieza(reclamo_agenteDTO.getPieza()));
		UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
		if(reclamo_agenteDTO.getUsuario()!=null)
			reclamo_agente.setUsuario(usuarioAssemb.getUsuario(reclamo_agenteDTO.getUsuario()));
		return reclamo_agente;
	}

	public Reclamo_Agente getReclamo_AgenteNuevo( Reclamo_AgenteDTO reclamo_agenteDTO) {
		Reclamo_Agente reclamo_agente = new Reclamo_Agente();
		reclamo_agente.setId(reclamo_agenteDTO.getId());
		reclamo_agente.setFecha_reclamo_agente(reclamo_agenteDTO.getFecha_reclamo_agente());
		reclamo_agente.setDescripcion(reclamo_agenteDTO.getDescripcion());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if(reclamo_agenteDTO.getPedido()!=null)
			reclamo_agente.setPedido(pedidoAssemb.getPedido(reclamo_agenteDTO.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if(reclamo_agenteDTO.getPieza()!=null)
			reclamo_agente.setPieza(piezaAssemb.getPieza(reclamo_agenteDTO.getPieza()));
		UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
		if(reclamo_agenteDTO.getUsuario()!=null)
			reclamo_agente.setUsuario(usuarioAssemb.getUsuario(reclamo_agenteDTO.getUsuario()));
		return reclamo_agente;
	}

}