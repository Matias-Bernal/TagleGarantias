package servidor.persistencia.dominio;

import java.sql.Date;

public class Reclamo_Agente {
	protected Date fecha_reclamo_agente;
	protected String descripcion;
	protected Pieza pieza;
	protected Pedido pedido;
	protected Usuario usuario;
	protected Long id;

	public Reclamo_Agente() {
	}

	public Reclamo_Agente(Date fecha_reclamo_agente,String descripcion, Pieza pieza,
			Pedido pedido, Usuario usuario) {
		super();
		this.fecha_reclamo_agente = fecha_reclamo_agente;
		this.descripcion = descripcion;
		this.pieza = pieza;
		this.pedido = pedido;
		this.usuario = usuario;
	}

	public Date getFecha_reclamo_agente() {
		return fecha_reclamo_agente;
	}

	public void setFecha_reclamo_agente(Date fecha_reclamo_agente) {
		this.fecha_reclamo_agente = fecha_reclamo_agente;
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

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}