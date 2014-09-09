package servidor.persistencia.dominio;

public class Notificacion_Usuario {

	protected Notificacion notificacion;
	protected Usuario usuario;
	protected Long id;

	public Notificacion_Usuario() {
	}

	public Notificacion_Usuario(Notificacion notificacion, Usuario usuario) {
		super();
		this.notificacion = notificacion;
		this.usuario = usuario;
	}

	public Notificacion getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
