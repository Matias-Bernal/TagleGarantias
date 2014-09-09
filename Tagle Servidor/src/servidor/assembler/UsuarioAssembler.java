package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Usuario;

import common.DTOs.UsuarioDTO;

public class UsuarioAssembler {

	private AccesoBD accesoBD;

	public UsuarioAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public UsuarioDTO getUsuarioDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setNombre_usuario(usuario.getNombre_usuario());
		usuarioDTO.setClave(usuario.getClave());
		usuarioDTO.setEmail(usuario.getEmail());
		usuarioDTO.setTipo(usuario.getTipo());
		return usuarioDTO;
	}

	public  Usuario getUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = (Usuario) accesoBD.buscarPorId(Usuario.class, usuarioDTO.getId());
		usuario.setId(usuarioDTO.getId());
		usuario.setNombre_usuario(usuarioDTO.getNombre_usuario());
		usuario.setClave(usuarioDTO.getClave());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setTipo(usuarioDTO.getTipo());

		return usuario;
	}

	public Usuario getUsuarioNuevo(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setId(usuarioDTO.getId());
		usuario.setNombre_usuario(usuarioDTO.getNombre_usuario());
		usuario.setClave(usuarioDTO.getClave());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setTipo(usuarioDTO.getTipo());

		return usuario;
	}

}