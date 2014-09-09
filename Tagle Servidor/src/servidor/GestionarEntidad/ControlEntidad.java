package servidor.GestionarEntidad;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.EntidadAssembler;
import servidor.assembler.PiezaAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Agente;
import servidor.persistencia.dominio.Entidad;
import servidor.persistencia.dominio.Pieza;
import servidor.persistencia.dominio.Registrante;
import common.DTOs.AgenteDTO;
import common.DTOs.EntidadDTO;
import common.GestionarEntidad.IControlEntidad;

public class ControlEntidad extends UnicastRemoteObject implements IControlEntidad {

	private static final long serialVersionUID = 1L;

	public ControlEntidad() throws RemoteException {
		super();
	}

	@Override
	public Long agregarEntidad(EntidadDTO entidad) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			EntidadAssembler entidadAssemb = new EntidadAssembler(accesoBD);
			Entidad nuevoEntidad = entidadAssemb.getEntidadNueva(entidad);
			accesoBD.hacerPersistente(nuevoEntidad);
			id = nuevoEntidad.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarEntidad(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			EntidadAssembler entidadAssemb = new EntidadAssembler(accesoBD);
			Entidad entidad = entidadAssemb.getEntidad(buscarEntidad(id));
			accesoBD.eliminar(entidad);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarEntidad(Long id, EntidadDTO modificado)
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			EntidadAssembler entidadAssemb = new EntidadAssembler(accesoBD);
			Entidad entidad = entidadAssemb.getEntidad(buscarEntidad(id));
			entidad.setNombre_registrante(modificado.getNombre_registrante());
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<EntidadDTO> obtenerEntidad() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<EntidadDTO> entidadesDTO = new Vector<EntidadDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			
			Collection movCol = accesoBD.buscarPorFiltro(Entidad.class, "");
			for (int i = 0; i < movCol.size(); i++) {
				EntidadDTO entidadDTO = new EntidadDTO();

				entidadDTO.setId( ((Registrante) movCol.toArray()[i]).getId());
				entidadDTO.setNombre_registrante( ((Registrante) movCol.toArray()[i]).getNombre_registrante());
				entidadesDTO.add(entidadDTO);
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return entidadesDTO;
	}

	@Override
	public boolean existeEntidad(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Entidad) accesoBD.buscarPorId(Entidad.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeEntidad(String nombre_registrante) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		@SuppressWarnings("rawtypes")
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre_registrante.equals(\""+nombre_registrante+"\")";
			movCol = accesoBD.buscarPorFiltro(Entidad.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public EntidadDTO buscarEntidad(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		EntidadDTO entidadDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			EntidadAssembler entidadAssemb = new EntidadAssembler(accesoBD);
			entidadDTO = entidadAssemb.getEntidadDTO((Entidad) accesoBD.buscarPorId(Entidad.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return entidadDTO;
	}

	@Override
	public EntidadDTO buscarEntidad(String nombre_registrante) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		EntidadDTO entidadDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre_registrante.equals(\""+nombre_registrante+"\")";
			@SuppressWarnings("rawtypes")
			Collection entidades = accesoBD.buscarPorFiltro(Entidad.class, filtro);
			EntidadAssembler entidadAssemb = new EntidadAssembler(accesoBD);
			if (entidades.size()>=1 ) {
				entidadDTO = entidadAssemb.getEntidadDTO((Entidad)(entidades.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return entidadDTO;
	}

}