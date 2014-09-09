package servidor.GestionarAgente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.AgenteAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Agente;

import common.DTOs.AgenteDTO;
import common.GestionarAgente.IControlAgente;

public class ControlAgente extends UnicastRemoteObject implements IControlAgente {

	private static final long serialVersionUID = 1L;

	public ControlAgente() throws RemoteException {
		super();
	}

	@Override
	public Long agregarAgente(AgenteDTO agenteDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			AgenteAssembler agenteAssemb = new AgenteAssembler(accesoBD);
			Agente agente = agenteAssemb.getAgenteNuevo(agenteDTO);
			accesoBD.hacerPersistente(agente);
			id = agente.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarAgente(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			AgenteAssembler agenteAssemb = new AgenteAssembler(accesoBD);
			Agente agente = agenteAssemb.getAgente(buscarAgente(id));
			accesoBD.eliminar(agente);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarAgente(Long id, AgenteDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			AgenteAssembler agenteAssemb = new AgenteAssembler(accesoBD);
			Agente agente = agenteAssemb.getAgente(buscarAgente(id));
			
			agente.setNombre_registrante(modificado.getNombre_registrante());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<AgenteDTO> obtenerAgentes() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<AgenteDTO> agentesDTO = new Vector<AgenteDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Agente> agentes = new Vector<Agente> (accesoBD.buscarPorFiltro(Agente.class, ""));
			for (int i = 0; i < agentes.size(); i++) {
				AgenteDTO agenteDTO = new AgenteDTO();
				
				agenteDTO.setId(agentes.elementAt(i).getId());
				agenteDTO.setNombre_registrante(agentes.elementAt(i).getNombre_registrante());
				
				agentesDTO.add(agenteDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return agentesDTO;
	}

	@Override
	public boolean existeAgente(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Agente) accesoBD.buscarPorId(Agente.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeAgente(String nombre_registrante) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		@SuppressWarnings("rawtypes")
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre_registrante.equals(\""+nombre_registrante+"\")";
			movCol = accesoBD.buscarPorFiltro(Agente.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public AgenteDTO buscarAgente(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		AgenteDTO agenteDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			AgenteAssembler agenteAssemb = new AgenteAssembler(accesoBD);
			agenteDTO = agenteAssemb.getAgenteDTO((Agente) accesoBD.buscarPorId(Agente.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return agenteDTO;
	}

	@Override
	public AgenteDTO buscarAgente(String nombre_registrante) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		AgenteDTO agenteDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre_registrante.equals(\""+nombre_registrante+"\")";
			@SuppressWarnings("rawtypes")
			Collection movCol = accesoBD.buscarPorFiltro(Agente.class, filtro);
			AgenteAssembler agenteAssemb = new AgenteAssembler(accesoBD);
			if (movCol.size()>=1){
				agenteDTO = agenteAssemb.getAgenteDTO((Agente)(movCol.toArray())[0]);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return agenteDTO;
	}
}