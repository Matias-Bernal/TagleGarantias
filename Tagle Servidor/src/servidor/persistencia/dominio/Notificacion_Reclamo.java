package servidor.persistencia.dominio;

public class Notificacion_Reclamo {

	private static final long serialVersionUID = 1L;
	protected Long id;
	private Reclamo reclamo;
	private Notificacion notificacion;
	
	public Notificacion_Reclamo(Reclamo reclamo,Notificacion notificacion, Long id) {
		super();
		this.reclamo = reclamo;
		this.notificacion = notificacion;
		this.id = id;
	}

	public Notificacion_Reclamo() {
	}

	public Reclamo getReclamo() {
		return reclamo;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

	public Notificacion getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
