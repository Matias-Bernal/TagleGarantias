package servidor.persistencia.dominio;

public class Orden_Reclamo {

	protected Orden orden;
	protected Reclamo reclamo;
	protected Long id;
	
	public Orden_Reclamo(){}

	public Orden_Reclamo(Orden orden, Reclamo reclamo, Long id) {
		super();
		this.orden = orden;
		this.reclamo = reclamo;
		this.id = id;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reclamo getReclamo() {
		return reclamo;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

}
