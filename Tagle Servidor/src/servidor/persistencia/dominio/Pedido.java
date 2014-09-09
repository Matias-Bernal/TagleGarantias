package servidor.persistencia.dominio;

import java.sql.Date;

public class Pedido {

	protected Long id;
	protected Date fecha_solicitud_pedido;
	protected Reclamo reclamo;

	public Pedido() {
	}

	public Pedido(Date fecha_solicitud_pedido, Reclamo reclamo) {
		super();
		this.fecha_solicitud_pedido = fecha_solicitud_pedido;
		this.reclamo = reclamo;
	}

	public Date getFecha_solicitud_pedido() {
		return fecha_solicitud_pedido;
	}

	public void setFecha_solicitud_pedido(Date fecha_solicitud_pedido) {
		this.fecha_solicitud_pedido = fecha_solicitud_pedido;
	}

	public Reclamo getReclamo() {
		return reclamo;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}