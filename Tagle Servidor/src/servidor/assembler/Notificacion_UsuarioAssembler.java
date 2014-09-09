package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Notificacion_Usuario;

import common.DTOs.Notificacion_UsuarioDTO;

public class Notificacion_UsuarioAssembler {
	
	private AccesoBD accesoBD;

	public Notificacion_UsuarioAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public Notificacion_UsuarioDTO getNotificacion_UsuarioDTO(Notificacion_Usuario notificacion_Usuario) {
		Notificacion_UsuarioDTO notificacion_UsuarioDTO = new Notificacion_UsuarioDTO();
		notificacion_UsuarioDTO.setId(notificacion_Usuario.getId());
		NotificacionAssembler notificacionAssemb = new NotificacionAssembler(accesoBD);
		if(notificacion_Usuario.getNotificacion()!=null)
			notificacion_UsuarioDTO.setNotificacion(notificacionAssemb.getNotificacionDTO(notificacion_Usuario.getNotificacion()));
		UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
		if(notificacion_Usuario.getUsuario()!=null)
			notificacion_UsuarioDTO.setUsuario(usuarioAssemb.getUsuarioDTO(notificacion_Usuario.getUsuario()));
		return notificacion_UsuarioDTO;
	}

	public Notificacion_Usuario getNotificacion_Usuario(Notificacion_UsuarioDTO notificacion_UsusarioDTO) {
		Notificacion_Usuario notificacion_Ususario =  (Notificacion_Usuario) accesoBD.buscarPorId(Notificacion_Usuario.class, notificacion_UsusarioDTO.getId());
		notificacion_Ususario.setId(notificacion_UsusarioDTO.getId());
		NotificacionAssembler notificacionAssemb = new NotificacionAssembler(accesoBD);
		if(notificacion_UsusarioDTO.getNotificacion()!=null)
			notificacion_Ususario.setNotificacion(notificacionAssemb.getNotificacion(notificacion_UsusarioDTO.getNotificacion()));
		UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
		if(notificacion_UsusarioDTO.getUsuario()!=null)
			notificacion_Ususario.setUsuario(usuarioAssemb.getUsuario(notificacion_UsusarioDTO.getUsuario()));
		return notificacion_Ususario;
	}

	public Notificacion_Usuario getNotificacion_UsuarioNuevo(Notificacion_UsuarioDTO notificacion_UsusarioDTO) {
		Notificacion_Usuario notificacion_Ususario =  new Notificacion_Usuario();
		notificacion_Ususario.setId(notificacion_UsusarioDTO.getId());
		NotificacionAssembler notificacionAssemb = new NotificacionAssembler(accesoBD);
		if(notificacion_UsusarioDTO.getNotificacion()!=null)
			notificacion_Ususario.setNotificacion(notificacionAssemb.getNotificacion(notificacion_UsusarioDTO.getNotificacion()));
		UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
		if(notificacion_UsusarioDTO.getUsuario()!=null)
			notificacion_Ususario.setUsuario(usuarioAssemb.getUsuario(notificacion_UsusarioDTO.getUsuario()));
		return notificacion_Ususario;
	}

}