package servidor.GestionarRegistrante;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.RegistranteAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Registrante;

import common.DTOs.RegistranteDTO;
import common.GestionarRegistrante.IControlRegistrante;

public class ControlRegistrante extends UnicastRemoteObject implements IControlRegistrante {

	private static final long serialVersionUID = 1L;

	public ControlRegistrante() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarRegistranteDTO(RegistranteDTO regitranteDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			RegistranteAssembler registranteAssemb = new RegistranteAssembler(accesoBD);
			Registrante registrante = registranteAssemb.getRegistranteNuevo(regitranteDTO);
			accesoBD.hacerPersistente(registrante);
			id = registrante.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarRegistranteDTO(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			RegistranteAssembler registranteAssemb = new RegistranteAssembler(accesoBD);
			Registrante registrante = registranteAssemb.getRegistrante(buscarRegistranteDTO(id));
			
			accesoBD.eliminar(registrante);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarRegistrante(Long id, RegistranteDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			RegistranteAssembler registranteAssemb = new RegistranteAssembler(accesoBD);
			Registrante registrante = registranteAssemb.getRegistrante(buscarRegistranteDTO(id));
			
			registrante.setNombre_registrante(modificado.getNombre_registrante());
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<RegistranteDTO> obtenerRegistrantes() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<RegistranteDTO> registratesDTO = new Vector<RegistranteDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Registrante> registrantes = new Vector<Registrante> (accesoBD.buscarPorFiltro(Registrante.class, ""));
			for (int i = 0; i < registrantes.size(); i++) {
				RegistranteDTO registranteDTO = new RegistranteDTO();
				
				registranteDTO.setId(registrantes.elementAt(i).getId());
				registranteDTO.setNombre_registrante(registrantes.elementAt(i).getNombre_registrante());
				
				registratesDTO.add(registranteDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return registratesDTO;
	}

	@Override
	public boolean existeRegistrante(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Registrante) accesoBD.buscarPorId(Registrante.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeRegistrante(String nombre_registrante) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection registrantes = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre_registrante.equals(\""+nombre_registrante+"\")";
			registrantes = accesoBD.buscarPorFiltro(Registrante.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (registrantes!=null && registrantes.size()>=1);
	}

	@Override
	public RegistranteDTO buscarRegistranteDTO(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		RegistranteDTO registranteDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			RegistranteAssembler registranteAssemb = new RegistranteAssembler(accesoBD);
			registranteDTO = registranteAssemb.getRegistranteDTO((Registrante) accesoBD.buscarPorId(Registrante.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return registranteDTO;
	}

	@Override
	public RegistranteDTO buscarRegistranteDTO(String nombre_registrante) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		RegistranteDTO registranteDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre_registrante.equals(\""+nombre_registrante+"\")";
			Collection registrantes = accesoBD.buscarPorFiltro(Registrante.class, filtro);
			RegistranteAssembler registranteAssemb = new RegistranteAssembler(accesoBD);
			if (registrantes.size()>=1 ) {
				registranteDTO = registranteAssemb.getRegistranteDTO((Registrante)(registrantes.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return registranteDTO;
	}

}