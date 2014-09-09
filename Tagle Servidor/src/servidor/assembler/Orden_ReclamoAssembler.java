package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Orden_Reclamo;

import common.DTOs.Orden_ReclamoDTO;

public class Orden_ReclamoAssembler {
	private AccesoBD accesoBD;

	public Orden_ReclamoAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public Orden_ReclamoDTO getOrden_ReclamoDTO(Orden_Reclamo orden_Reclamo) {
		Orden_ReclamoDTO orden_reclamoDTO = new Orden_ReclamoDTO();
		orden_reclamoDTO.setId(orden_Reclamo.getId());
		OrdenAssembler ordenAssemb = new OrdenAssembler(accesoBD);
		if(orden_Reclamo.getOrden()!=null)
			orden_reclamoDTO.setOrden(ordenAssemb.getOrdenDTO(orden_Reclamo.getOrden()));
		ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
		if(orden_Reclamo.getReclamo()!=null)
			orden_reclamoDTO.setReclamo(reclamoAssemb.getReclamoDTO(orden_Reclamo.getReclamo()));
		return orden_reclamoDTO;
	}

	public Orden_Reclamo getOrden_Reclamo(Orden_ReclamoDTO orden_ReclamoDTO) {
		Orden_Reclamo orden_Reclamo = (Orden_Reclamo) accesoBD.buscarPorId(Orden_Reclamo.class, orden_ReclamoDTO.getId());
		orden_Reclamo.setId(orden_ReclamoDTO.getId());
		OrdenAssembler ordenAssemb = new OrdenAssembler(accesoBD);
		if(orden_ReclamoDTO.getOrden()!=null)
			orden_Reclamo.setOrden(ordenAssemb.getOrden(orden_ReclamoDTO.getOrden()));
		ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
		if(orden_ReclamoDTO.getReclamo()!=null)
			orden_Reclamo.setReclamo(reclamoAssemb.getReclamo(orden_ReclamoDTO.getReclamo()));
		return orden_Reclamo;
	}
	
	public Orden_Reclamo getOrden_ReclamoNuevo(Orden_ReclamoDTO orden_ReclamoDTO) {
		Orden_Reclamo orden_Reclamo = new Orden_Reclamo();
		orden_Reclamo.setId(orden_ReclamoDTO.getId());
		OrdenAssembler ordenAssemb = new OrdenAssembler(accesoBD);
		if(orden_ReclamoDTO.getOrden()!=null)
			orden_Reclamo.setOrden(ordenAssemb.getOrden(orden_ReclamoDTO.getOrden()));
		ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
		if(orden_ReclamoDTO.getReclamo()!=null)
			orden_Reclamo.setReclamo(reclamoAssemb.getReclamo(orden_ReclamoDTO.getReclamo()));
		return orden_Reclamo;
	}

}
