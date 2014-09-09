package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Reclamante;

import common.DTOs.ReclamanteDTO;

public class ReclamanteAssembler {
	
	private AccesoBD accesoBD;

	public ReclamanteAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public ReclamanteDTO getReclamanteDTO(Reclamante reclamante) {
		ReclamanteDTO reclamanteDTO = new ReclamanteDTO();
		reclamanteDTO.setId(reclamante.getId());
		reclamanteDTO.setNombre_apellido(reclamante.getNombre_apellido());
		reclamanteDTO.setDni(reclamante.getDni());
		reclamanteDTO.setEmail(reclamante.getEmail());
		return reclamanteDTO;
	}

	public Reclamante getReclamante(ReclamanteDTO reclamanteDTO) {
		Reclamante reclamante =  (Reclamante) accesoBD.buscarPorId(Reclamante.class, reclamanteDTO.getId());
		reclamante.setId(reclamanteDTO.getId());
		reclamante.setNombre_apellido(reclamanteDTO.getNombre_apellido());
		reclamante.setDni(reclamanteDTO.getDni());
		reclamante.setEmail(reclamanteDTO.getEmail());
		return reclamante;
	}

	public Reclamante getReclamanteNuevo(ReclamanteDTO reclamanteDTO) {
		Reclamante reclamante = new Reclamante();
		reclamante.setId(reclamanteDTO.getId());
		reclamante.setNombre_apellido(reclamanteDTO.getNombre_apellido());
		reclamante.setDni(reclamanteDTO.getDni());
		reclamante.setEmail(reclamanteDTO.getEmail());
		return reclamante;
	}

}