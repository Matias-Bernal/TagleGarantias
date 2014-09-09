package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Recurso;

import common.DTOs.RecursoDTO;

public class RecursoAssembler {
	
	private AccesoBD accesoBD;

	public RecursoAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public RecursoDTO getRecursoDTO(Recurso recurso) {
		RecursoDTO recursoDTO = new RecursoDTO();
		recursoDTO.setId(recurso.getId());
		recursoDTO.setFecha_recurso(recurso.getFecha_recurso());
		recursoDTO.setNumero_recurso(recurso.getNumero_recurso());
		return recursoDTO;
	}

	public Recurso getRecurso(RecursoDTO recursoDTO) {
		Recurso recurso = (Recurso) accesoBD.buscarPorId(Recurso.class, recursoDTO.getId());
		recurso.setId(recursoDTO.getId());
		recurso.setFecha_recurso(recursoDTO.getFecha_recurso());
		recurso.setNumero_recurso(recursoDTO.getNumero_recurso());
		return recurso;
	}
	
	public Recurso getRecursoNuevo(RecursoDTO recursoDTO) {
		Recurso recurso = new Recurso();
		recurso.setId(recursoDTO.getId());
		recurso.setFecha_recurso(recursoDTO.getFecha_recurso());
		recurso.setNumero_recurso(recursoDTO.getNumero_recurso());
		return recurso;
	}

}