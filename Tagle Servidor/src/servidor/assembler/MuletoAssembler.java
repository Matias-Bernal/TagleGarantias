package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Muleto;

import common.DTOs.MuletoDTO;

public class MuletoAssembler {

	private AccesoBD accesoBD;
	
	public MuletoAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public MuletoDTO getMuletoDTO(Muleto muleto) {
		MuletoDTO muletoDTO = new MuletoDTO();
		muletoDTO.setId(muleto.getId());
		muletoDTO.setDescripcion(muleto.getDescripcion());
		muletoDTO.setVin(muleto.getVin());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if (muleto.getPedido()!=null)
			muletoDTO.setPedido(pedidoAssemb.getPedidoDTO(muleto.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if(muleto.getPieza()!=null)
			muletoDTO.setPieza(piezaAssemb.getPiezaDTO(muleto.getPieza()));
		return muletoDTO;
	}

	public Muleto getMuleto(MuletoDTO muletoDTO) {
		Muleto muleto =  (Muleto) accesoBD.buscarPorId(Muleto.class, muletoDTO.getId());
		muleto.setId(muletoDTO.getId());
		muleto.setDescripcion(muletoDTO.getDescripcion());
		muleto.setVin(muletoDTO.getVin());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if (muletoDTO.getPedido()!=null)
			muleto.setPedido(pedidoAssemb.getPedido(muletoDTO.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if(muletoDTO.getPieza()!=null)
			muleto.setPieza(piezaAssemb.getPieza(muletoDTO.getPieza()));
		return muleto;
	}

	public Muleto getMuletoNuevo(MuletoDTO muletoDTO) {
		Muleto muleto =  new Muleto();
		muleto.setId(muletoDTO.getId());
		muleto.setDescripcion(muletoDTO.getDescripcion());
		muleto.setVin(muletoDTO.getVin());
		PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
		if (muletoDTO.getPedido()!=null)
			muleto.setPedido(pedidoAssemb.getPedido(muletoDTO.getPedido()));
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if(muletoDTO.getPieza()!=null)
			muleto.setPieza(piezaAssemb.getPieza(muletoDTO.getPieza()));
		return muleto;
	}

}