package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Agente;

import common.DTOs.AgenteDTO;

public class AgenteAssembler {
	
	private AccesoBD accesoBD;

	public AgenteAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public AgenteDTO getAgenteDTO(Agente agente) {
		AgenteDTO agentDTO = new AgenteDTO();
		agentDTO.setId(agente.getId());
		agentDTO.setNombre_registrante(agente.getNombre_registrante());
		return agentDTO;
	}

	public Agente getAgente(AgenteDTO agenteDTO) {
		Agente agent =	(Agente) accesoBD.buscarPorId(Agente.class, agenteDTO.getId());
		agent.setId(agenteDTO.getId());
		agent.setNombre_registrante(agenteDTO.getNombre_registrante());
		return agent;
	}
	
	public Agente getAgenteNuevo(AgenteDTO agenteDTO) {
		Agente agent =	new Agente();
		agent.setId(agenteDTO.getId());
		agent.setNombre_registrante(agenteDTO.getNombre_registrante());
		return agent;
	}

}