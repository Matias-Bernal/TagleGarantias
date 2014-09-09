package servidor.persistencia.dominio;

import java.sql.Date;

public class Orden {

	protected String numero_orden;
	protected Date fecha_apertura;
	protected Date fecha_cierre;
	protected String estado;
	protected Recurso recurso;
	protected Long id;

	public Orden(){}

	public Orden(String numero_orden, Date fecha_apertura, Date fecha_cierre, String estado, Long id, Recurso recurso) {
		super();
		this.id = id;
		this.numero_orden = numero_orden;
		this.fecha_apertura = fecha_apertura;
		this.fecha_cierre = fecha_cierre;
		this.estado = estado;
		this.recurso = recurso;
	}

	public Date getFecha_apertura() {
		return fecha_apertura;
	}

	public void setFecha_apertura(Date fecha_apertura) {
		this.fecha_apertura = fecha_apertura;
	}

	public Date getFecha_cierre() {
		return fecha_cierre;
	}

	public void setFecha_cierre(Date fecha_cierre) {
		this.fecha_cierre = fecha_cierre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero_orden() {
		return numero_orden;
	}

	public void setNumero_orden(String numero_orden) {
		this.numero_orden = numero_orden;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}
	
}
