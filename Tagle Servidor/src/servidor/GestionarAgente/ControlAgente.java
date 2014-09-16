package servidor.GestionarAgente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.jdo.Extent;
import javax.jdo.Query;

import servidor.assembler.AgenteAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Agente;
import servidor.persistencia.dominio.Entidad;
import servidor.persistencia.dominio.Pedido_Pieza;
import servidor.persistencia.dominio.Registrante;
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
			
			String filtro = "id == "+modificado.getId().toString();
			Extent e0 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, filtro);
			Collection agentes = (Collection) q0.execute();
			Iterator iter = agentes.iterator();
			while (iter.hasNext()){
				Registrante registrante = (Registrante) iter.next();
				registrante.setId(registrante.getId());
				registrante.setNombre_registrante(modificado.getNombre_registrante());	
				break;
			}
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
			Extent e0 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, "");
			Collection agentes = (Collection) q0.execute();
			Iterator iter = agentes.iterator();
			while (iter.hasNext()){
				Registrante registrante = (Registrante) iter.next();
				AgenteDTO current = new AgenteDTO(); 
				current.setId(registrante.getId());
				current.setNombre_registrante(registrante.getNombre_registrante());
				agentesDTO.add(current);
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
		@SuppressWarnings("rawtypes")
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "id == "+id.toString();
			
			Extent e0 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, filtro);
			movCol = (Collection) q0.execute();
						
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public boolean existeAgente(String nombre_registrante) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		@SuppressWarnings("rawtypes")
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre_registrante.equals(\""+nombre_registrante+"\")";
			
			Extent e0 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, filtro);
			movCol = (Collection) q0.execute();
						
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
			String filtro = "id == "+id.toString();
			Extent e0 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, filtro);
			Collection agentes = (Collection) q0.execute();
			Iterator iter = agentes.iterator();
			while (iter.hasNext()){
				Registrante registrante = (Registrante) iter.next();
				agenteDTO = new AgenteDTO();
				agenteDTO.setId(registrante.getId());
				agenteDTO.setNombre_registrante(registrante.getNombre_registrante());
				break;
			}
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
			Extent e0 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q0 = accesoBD.getPersistencia().newQuery(e0, filtro);
			Collection agentes = (Collection) q0.execute();
			Iterator iter = agentes.iterator();
			while (iter.hasNext()){
				Registrante registrante = (Registrante) iter.next();
				agenteDTO = new AgenteDTO();
				agenteDTO.setId(registrante.getId());
				agenteDTO.setNombre_registrante(registrante.getNombre_registrante());
				break;
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return agenteDTO;
	}
}