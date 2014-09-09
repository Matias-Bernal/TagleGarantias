package servidor.GestionarReclamante;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.ReclamanteAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Reclamante;

import common.DTOs.ReclamanteDTO;
import common.GestionarReclamante.IControlReclamante;

public class ControlReclamante extends UnicastRemoteObject implements IControlReclamante {

	private static final long serialVersionUID = 1L;

	public ControlReclamante() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarReclamante(ReclamanteDTO reclamanteDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
			Reclamante reclamante = reclamanteAssemb.getReclamanteNuevo(reclamanteDTO);
			accesoBD.hacerPersistente(reclamante);
			id = reclamante.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarReclamante(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
			Reclamante reclamante = reclamanteAssemb.getReclamante(buscarReclamante(id));
			accesoBD.eliminar(reclamante);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarReclamante(Long id, ReclamanteDTO modificado)
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
			Reclamante reclamante = reclamanteAssemb.getReclamante(buscarReclamante(id));

			reclamante.setEmail(modificado.getEmail());
			reclamante.setDni(modificado.getDni());
			reclamante.setNombre_apellido(modificado.getNombre_apellido());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<ReclamanteDTO> obtenerReclamantes() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<ReclamanteDTO> reclamantesDTO = new Vector<ReclamanteDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Reclamante> reclamantes = new Vector<Reclamante>(accesoBD.buscarPorFiltro(Reclamante.class, ""));
			for (int i = 0; i < reclamantes.size(); i++) {
				ReclamanteDTO reclamanteDTO = new ReclamanteDTO();

				reclamanteDTO.setId(reclamantes.elementAt(i).getId());
				reclamanteDTO.setNombre_apellido(reclamantes.elementAt(i).getNombre_apellido());
				reclamanteDTO.setDni(reclamantes.elementAt(i).getDni());
				reclamanteDTO.setEmail(reclamantes.elementAt(i).getEmail());

				reclamantesDTO.add(reclamanteDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamantesDTO;
	}

	@Override
	public boolean existeReclamante(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Reclamante) accesoBD.buscarPorId(Reclamante.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeReclamante(String nombre_apellido) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre_apellido.equals(\""+nombre_apellido+"\")";
			movCol = accesoBD.buscarPorFiltro(Reclamante.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public ReclamanteDTO buscarReclamante(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		ReclamanteDTO reclamanteDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
			reclamanteDTO = reclamanteAssemb.getReclamanteDTO((Reclamante) accesoBD.buscarPorId(Reclamante.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamanteDTO;
	}

	@Override
	public ReclamanteDTO buscarReclamante(String nombre_apellido) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		ReclamanteDTO reclamanteDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre_apellido.equals(\""+nombre_apellido+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Reclamante.class, filtro);
			ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
			if (movCol.size()>=1){
				reclamanteDTO = reclamanteAssemb.getReclamanteDTO((Reclamante)(movCol.toArray())[0]);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamanteDTO;
	}

	@Override
	public boolean existeReclamanteDni(String dni) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "dni.equals(\""+dni+"\")";
			movCol = accesoBD.buscarPorFiltro(Reclamante.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public ReclamanteDTO buscarReclamanteDni(String dni)
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		ReclamanteDTO reclamanteDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "dni.equals(\""+dni+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Reclamante.class, filtro);
			ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
			if (movCol.size()>=1){
				reclamanteDTO = reclamanteAssemb.getReclamanteDTO((Reclamante)(movCol.toArray())[0]);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamanteDTO;
	}
}