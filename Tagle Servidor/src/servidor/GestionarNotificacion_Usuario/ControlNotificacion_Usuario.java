package servidor.GestionarNotificacion_Usuario;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.NotificacionAssembler;
import servidor.assembler.Notificacion_UsuarioAssembler;
import servidor.assembler.UsuarioAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Notificacion_Usuario;

import common.DTOs.NotificacionDTO;
import common.DTOs.Notificacion_UsuarioDTO;
import common.DTOs.UsuarioDTO;
import common.GestionarNotificacion_Usuario.IControlNotificacion_Usuario;

public class ControlNotificacion_Usuario extends UnicastRemoteObject implements IControlNotificacion_Usuario {

	private static final long serialVersionUID = 1L;

	public ControlNotificacion_Usuario() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarNotificacion_Usuario(Notificacion_UsuarioDTO notificacion_usuarioDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Notificacion_UsuarioAssembler notificacion_usuarioAssem = new Notificacion_UsuarioAssembler(accesoBD);
			Notificacion_Usuario notificacion_Usuario = notificacion_usuarioAssem.getNotificacion_Usuario(notificacion_usuarioDTO);
			accesoBD.hacerPersistente(notificacion_Usuario);
			id = notificacion_Usuario.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarNotificacion_Usuario(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			Notificacion_UsuarioAssembler notificacion_usuarioAssem = new Notificacion_UsuarioAssembler(accesoBD);
			Notificacion_Usuario notificacion_Usuario = notificacion_usuarioAssem.getNotificacion_Usuario(buscarNotificacion_Usuario(id));
			accesoBD.eliminar(notificacion_Usuario);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarNotificacion_Usuario(Long id, Notificacion_UsuarioDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			Notificacion_UsuarioAssembler notificacion_usuarioAssem = new Notificacion_UsuarioAssembler(accesoBD);
			
			Notificacion_Usuario notificacion_Usuario = notificacion_usuarioAssem.getNotificacion_UsuarioNuevo(buscarNotificacion_Usuario(id));
			NotificacionAssembler notificacionAssemb = new NotificacionAssembler(accesoBD);
			notificacion_Usuario.setNotificacion(notificacionAssemb.getNotificacion(modificado.getNotificacion()));
			UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
			notificacion_Usuario.setUsuario(usuarioAssemb.getUsuario(modificado.getUsuario()));

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<Notificacion_UsuarioDTO> obtenerNotificacion_Usuario() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Notificacion_UsuarioDTO> notificacion_UsuariosDTO = new Vector<Notificacion_UsuarioDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Notificacion_Usuario> notificacion_Usuarios = new Vector<Notificacion_Usuario> (accesoBD.buscarPorFiltro(Notificacion_Usuario.class, ""));
			for (int i = 0; i < notificacion_Usuarios.size(); i++) {
				Notificacion_UsuarioDTO notificacion_UsuarioDTO = new Notificacion_UsuarioDTO();
				
				notificacion_UsuarioDTO.setId(notificacion_Usuarios.elementAt(i).getId());
				NotificacionAssembler notificacionAssemb = new NotificacionAssembler(accesoBD);
				notificacion_UsuarioDTO.setNotificacion(notificacionAssemb.getNotificacionDTO(notificacion_Usuarios.elementAt(i).getNotificacion()));
				UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
				notificacion_UsuarioDTO.setUsuario(usuarioAssemb.getUsuarioDTO(notificacion_Usuarios.elementAt(i).getUsuario()));
				
				notificacion_UsuariosDTO.add(notificacion_UsuarioDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return notificacion_UsuariosDTO;
	}

	@Override
	public Vector<Notificacion_UsuarioDTO> obtenerNotificacion_Usuario(UsuarioDTO usuarioDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Notificacion_UsuarioDTO> notificacion_UsuariosDTO = new Vector<Notificacion_UsuarioDTO>();
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "usuario.id == "+usuarioDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Notificacion_Usuario.class, filtro);
			Notificacion_UsuarioAssembler notificacion_UsuarioAssemb = new Notificacion_UsuarioAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				notificacion_UsuariosDTO.add(notificacion_UsuarioAssemb.getNotificacion_UsuarioDTO((Notificacion_Usuario)(movCol.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return notificacion_UsuariosDTO;
	}

	@Override
	public boolean existeNotificacion_Usuario(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Notificacion_Usuario) accesoBD.buscarPorId(Notificacion_Usuario.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeNotificacion_Usuario(NotificacionDTO notificacionDTO, UsuarioDTO usuarioDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "usuario.id == "+usuarioDTO.getId()+ " && notificacion.id == "+notificacionDTO.getId();
			movCol = accesoBD.buscarPorFiltro(Notificacion_Usuario.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public Notificacion_UsuarioDTO buscarNotificacion_Usuario(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Notificacion_UsuarioDTO notificacion_UsuarioDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			Notificacion_UsuarioAssembler notificacion_UsuarioAssemb = new Notificacion_UsuarioAssembler(accesoBD);
			notificacion_UsuarioDTO = notificacion_UsuarioAssemb.getNotificacion_UsuarioDTO((Notificacion_Usuario) accesoBD.buscarPorId(Notificacion_Usuario.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return notificacion_UsuarioDTO;
	}

	@Override
	public Notificacion_UsuarioDTO buscarNotificacion_Usuario(NotificacionDTO notificacionDTO, UsuarioDTO usuarioDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Notificacion_UsuarioDTO notificacion_UsuarioDTO = null;
		try {
			accesoBD.iniciarTransaccion();

			String filtro = "usuario.id == "+usuarioDTO.getId()+ " && notificacion.id == "+notificacionDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Notificacion_Usuario.class, filtro);
			Notificacion_UsuarioAssembler notificacion_UsuarioAssemb = new Notificacion_UsuarioAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				notificacion_UsuarioDTO = notificacion_UsuarioAssemb.getNotificacion_UsuarioDTO((Notificacion_Usuario)(movCol.toArray()[0]));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return notificacion_UsuarioDTO;
	}

}
