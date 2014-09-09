package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Reclamante_Reclamo;

import common.DTOs.Reclamante_ReclamoDTO;

public class Reclamante_ReclamoAssembler {
	
	private AccesoBD accesoBD;

	public Reclamante_ReclamoAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public Reclamante_ReclamoDTO getReclamante_ReclamoDTO(Reclamante_Reclamo reclamante_Reclamo) {
		Reclamante_ReclamoDTO reclamante_ReclamoDTO = new Reclamante_ReclamoDTO();
		reclamante_ReclamoDTO.setId(reclamante_Reclamo.getId());
		ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
		if(reclamante_Reclamo.getReclamante()!=null)
			reclamante_ReclamoDTO.setReclamante(reclamanteAssemb.getReclamanteDTO(reclamante_Reclamo.getReclamante()));
		ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
		if(reclamante_Reclamo.getReclamo()!=null)
			reclamante_ReclamoDTO.setReclamo(reclamoAssemb.getReclamoDTO(reclamante_Reclamo.getReclamo()));
		return reclamante_ReclamoDTO;
	}

	public Reclamante_Reclamo getReclamante_Reclamo(Reclamante_ReclamoDTO reclamante_ReclamoDTO) {
		Reclamante_Reclamo reclamante_Reclamo = (Reclamante_Reclamo) accesoBD.buscarPorId(Reclamante_Reclamo.class, reclamante_ReclamoDTO.getId());
		reclamante_Reclamo.setId(reclamante_ReclamoDTO.getId());
		ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
		if(reclamante_ReclamoDTO.getReclamante()!=null)
			reclamante_Reclamo.setReclamante(reclamanteAssemb.getReclamante(reclamante_ReclamoDTO.getReclamante()));
		ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
		if(reclamante_ReclamoDTO.getReclamo()!=null)
			reclamante_Reclamo.setReclamo(reclamoAssemb.getReclamo(reclamante_ReclamoDTO.getReclamo()));
		return reclamante_Reclamo;
	}

	public Reclamante_Reclamo getReclamante_ReclamoNuevo(Reclamante_ReclamoDTO reclamante_ReclamoDTO) {
		Reclamante_Reclamo reclamante_Reclamo = new Reclamante_Reclamo();
		reclamante_Reclamo.setId(reclamante_ReclamoDTO.getId());
		ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
		if(reclamante_ReclamoDTO.getReclamante()!=null)
			reclamante_Reclamo.setReclamante(reclamanteAssemb.getReclamante(reclamante_ReclamoDTO.getReclamante()));
		ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
		if(reclamante_ReclamoDTO.getReclamo()!=null)
			reclamante_Reclamo.setReclamo(reclamoAssemb.getReclamo(reclamante_ReclamoDTO.getReclamo()));
		return reclamante_Reclamo;
	}

}