package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Entidad;

import common.DTOs.EntidadDTO;

public class EntidadAssembler {
	
	private AccesoBD accesoBD;
	
	public EntidadAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public EntidadDTO getEntidadDTO(Entidad entidad) {
		EntidadDTO entidadDTO = new EntidadDTO();
		entidadDTO.setId(entidad.getId());
		entidadDTO.setNombre_registrante(entidad.getNombre_registrante());
		return entidadDTO;
	}

	public Entidad getEntidad(EntidadDTO entidadDTO) {
		Entidad entidad = (Entidad) accesoBD.buscarPorId(Entidad.class, entidadDTO.getId());
		entidad.setId(entidadDTO.getId());
		entidad.setNombre_registrante(entidadDTO.getNombre_registrante());
		return entidad;
	}

	public Entidad getEntidadNueva(EntidadDTO entidadDTO) {
		Entidad entidad = new Entidad();
		entidad.setId(entidadDTO.getId());
		entidad.setNombre_registrante(entidadDTO.getNombre_registrante());
		return entidad;
	}

}