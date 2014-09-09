package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Registrante;

import common.DTOs.RegistranteDTO;

public class RegistranteAssembler {
	
	private AccesoBD accesoBD;

	public RegistranteAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public RegistranteDTO getRegistranteDTO(Registrante registrante) {
		RegistranteDTO registranteDTO = new RegistranteDTO();
		registranteDTO.setId(registrante.getId());
		registranteDTO.setNombre_registrante(registrante.getNombre_registrante());
		return registranteDTO;
	}

	public Registrante getRegistrante(RegistranteDTO registranteDTO) {
		Registrante registrante = (Registrante) accesoBD.buscarPorId(Registrante.class, registranteDTO.getId());
		registrante.setId(registranteDTO.getId());
		registrante.setNombre_registrante(registranteDTO.getNombre_registrante());
		return registrante;
	}

	public Registrante getRegistranteNuevo(RegistranteDTO registranteDTO) {
		Registrante registrante = new Registrante();
		registrante.setId(registranteDTO.getId());
		registrante.setNombre_registrante(registranteDTO.getNombre_registrante());
		return registrante;
	}

}