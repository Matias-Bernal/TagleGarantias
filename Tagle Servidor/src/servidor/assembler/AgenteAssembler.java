package servidor.assembler;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import javax.jdo.Extent;
import javax.jdo.Query;

import servidor.GestionarAgente.ControlAgente;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Agente;
import servidor.persistencia.dominio.Registrante;
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
		Agente agent = null;
		try {
			ControlAgente controlAgente = new ControlAgente();
			accesoBD.iniciarTransaccion();

			Collection movCol = null;
			String filtro = "id == "+agenteDTO.getId().toString();
				
			Extent e0 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, filtro);
			movCol = (Collection) q0.execute();
			
			Iterator iter = movCol.iterator();
			while (iter.hasNext()){
				Registrante registrante = (Registrante) iter.next();
				agent = new Agente();
				
				agent.setNombre_registrante(registrante.getNombre_registrante());
				agent.setId(registrante.getId());
				
				accesoBD.concretarTransaccion();
				break;
			}
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return agent;
	}
	
	public Agente getAgenteNuevo(AgenteDTO agenteDTO) {
		Agente agent =	new Agente();
		agent.setId(agenteDTO.getId());
		agent.setNombre_registrante(agenteDTO.getNombre_registrante());
		return agent;
	}

}