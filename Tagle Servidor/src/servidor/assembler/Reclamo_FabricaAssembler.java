package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Reclamo_Fabrica;

import common.DTOs.Reclamo_FabricaDTO;

public class Reclamo_FabricaAssembler {
	
	private AccesoBD accesoBD;

	public Reclamo_FabricaAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public Reclamo_FabricaDTO getReclamo_FabricaDTO(Reclamo_Fabrica reclamo_fabrica) {
		Reclamo_FabricaDTO reclamo_fabricaDTO = new Reclamo_FabricaDTO();
		reclamo_fabricaDTO.setId(reclamo_fabrica.getId());
		reclamo_fabricaDTO.setFecha_reclamo_fabrica(reclamo_fabrica.getFecha_reclamo_fabrica());
		reclamo_fabricaDTO.setDescripcion(reclamo_fabrica.getDescripcion());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if(reclamo_fabrica.getPedido()!=null)
			reclamo_fabricaDTO.setPedido(pedidoAssemb.getPedidoDTO(reclamo_fabrica.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if(reclamo_fabrica.getPieza()!=null)
			reclamo_fabricaDTO.setPieza(piezaAssemb.getPiezaDTO(reclamo_fabrica.getPieza()));
		UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
		if(reclamo_fabrica.getUsuario()!=null)
			reclamo_fabricaDTO.setUsuario(usuarioAssemb.getUsuarioDTO(reclamo_fabrica.getUsuario()));
		return reclamo_fabricaDTO;
	}

	public Reclamo_Fabrica getReclamo_Fabrica(Reclamo_FabricaDTO reclamo_fabricaDTO) {
		Reclamo_Fabrica reclamo_fabrica = (Reclamo_Fabrica) accesoBD.buscarPorId(Reclamo_Fabrica.class, reclamo_fabricaDTO.getId());
		reclamo_fabrica.setId(reclamo_fabricaDTO.getId());
		reclamo_fabrica.setFecha_reclamo_fabrica(reclamo_fabricaDTO.getFecha_reclamo_fabrica());
		reclamo_fabrica.setDescripcion(reclamo_fabricaDTO.getDescripcion());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if(reclamo_fabricaDTO.getPedido()!=null)
			reclamo_fabrica.setPedido(pedidoAssemb.getPedido(reclamo_fabricaDTO.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if(reclamo_fabricaDTO.getPieza()!=null)
			reclamo_fabrica.setPieza(piezaAssemb.getPieza(reclamo_fabricaDTO.getPieza()));
		UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
		if(reclamo_fabricaDTO.getUsuario()!=null)
			reclamo_fabrica.setUsuario(usuarioAssemb.getUsuario(reclamo_fabricaDTO.getUsuario()));
		return reclamo_fabrica;
	}

	public Reclamo_Fabrica getReclamo_FabricaNuevo( Reclamo_FabricaDTO reclamo_fabricaDTO) {
		Reclamo_Fabrica reclamo_fabrica = new Reclamo_Fabrica();
		reclamo_fabrica.setId(reclamo_fabricaDTO.getId());
		reclamo_fabrica.setFecha_reclamo_fabrica(reclamo_fabricaDTO.getFecha_reclamo_fabrica());
		reclamo_fabrica.setDescripcion(reclamo_fabricaDTO.getDescripcion());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if(reclamo_fabricaDTO.getPedido()!=null)
			reclamo_fabrica.setPedido(pedidoAssemb.getPedido(reclamo_fabricaDTO.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if(reclamo_fabricaDTO.getPieza()!=null)
			reclamo_fabrica.setPieza(piezaAssemb.getPieza(reclamo_fabricaDTO.getPieza()));
		UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
		if(reclamo_fabricaDTO.getUsuario()!=null)
			reclamo_fabrica.setUsuario(usuarioAssemb.getUsuario(reclamo_fabricaDTO.getUsuario()));
		return reclamo_fabrica;
	}

}