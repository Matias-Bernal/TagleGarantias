package servidor.GestionarUsuario;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.UsuarioAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Usuario;

import common.DTOs.UsuarioDTO;
import common.GestionarUsuario.IControlUsuario;

public class ControlUsuario extends UnicastRemoteObject implements IControlUsuario {
	
	private static final long serialVersionUID = 1L;

	public ControlUsuario() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarUsuario(UsuarioDTO usuarioDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
			Usuario nuevoUsuario = usuarioAssemb.getUsuarioNuevo(usuarioDTO);
			accesoBD.hacerPersistente(nuevoUsuario);
			id = nuevoUsuario.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			System.out.println("ERRROOORRR se rompio todo guaso");
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarUsuario(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
			Usuario usuario = usuarioAssemb.getUsuario(buscarUsuario(id));
			accesoBD.eliminar(usuario);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarUsuario(Long id, UsuarioDTO modificado)
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
			Usuario usuario = usuarioAssemb.getUsuario(buscarUsuario(id));

			usuario.setNombre_usuario(modificado.getNombre_usuario());
			usuario.setClave(modificado.getClave());
			usuario.setEmail(modificado.getEmail());
			usuario.setTipo(modificado.getTipo());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<UsuarioDTO> obtenerUsuarios() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<UsuarioDTO> usuariosDTO = new Vector<UsuarioDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Usuario> Usuarios = new Vector<Usuario> (accesoBD.buscarPorFiltro(Usuario.class, ""));
			for (int i = 0; i < Usuarios.size(); i++) {
				UsuarioDTO usuarioDTO = new UsuarioDTO();
				usuarioDTO.setId(Usuarios.elementAt(i).getId());
				usuarioDTO.setNombre_usuario(Usuarios.elementAt(i).getNombre_usuario());
				usuarioDTO.setClave(Usuarios.elementAt(i).getClave());
				usuarioDTO.setEmail(Usuarios.elementAt(i).getEmail());
				usuarioDTO.setTipo(Usuarios.elementAt(i).getTipo());
				usuariosDTO.add(usuarioDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return usuariosDTO;
	}

	@Override
	public Vector<UsuarioDTO> obtenerUsuarios(String tipo) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<UsuarioDTO> usuariosDTO = new Vector<UsuarioDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "tipo.equals(\""+tipo+"\")";
			Collection usuarios = accesoBD.buscarPorFiltro(Usuario.class, filtro);
			UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
			for (int i = 0; i < usuarios.size(); i++) {
				usuariosDTO.add(usuarioAssemb.getUsuarioDTO((Usuario)(usuarios.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return usuariosDTO;
	}
	
	@Override
	public boolean login(String nombre_usuario, String password) throws Exception {
		if (existeUsuario(nombre_usuario)){
			UsuarioDTO usuarioDTO = buscarUsuario(nombre_usuario);
			return (usuarioDTO.getClave().equals(password));
		}else{
			return false;
		}

	}

	@Override
	public boolean existeUsuario(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Boolean existe = null;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Usuario) accesoBD.buscarPorId(Usuario.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeUsuario(String nombre_usuario) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection usuarios = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre_usuario.equals(\""+nombre_usuario+"\")";
			usuarios = accesoBD.buscarPorFiltro(Usuario.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (usuarios!=null && usuarios.size()>=1);
	}

	@Override
	public UsuarioDTO buscarUsuario(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		UsuarioDTO usuarioDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
			usuarioDTO = usuarioAssemb.getUsuarioDTO((Usuario) accesoBD.buscarPorId(Usuario.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return usuarioDTO;
	}

	@Override
	public UsuarioDTO buscarUsuario(String nombre_usuario) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		UsuarioDTO usuarioDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre_usuario.equals(\""+nombre_usuario+"\")";
			Collection usuarios = accesoBD.buscarPorFiltro(Usuario.class, filtro);
			UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
			if (usuarios.size()>=1 ) {
				usuarioDTO = usuarioAssemb.getUsuarioDTO((Usuario)(usuarios.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return usuarioDTO;
	}
}