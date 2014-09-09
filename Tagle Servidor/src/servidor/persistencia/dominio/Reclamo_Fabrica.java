package servidor.persistencia.dominio;

import java.sql.Date;

public class Reclamo_Fabrica {

	protected Date fecha_reclamo_fabrica;
	protected String descripcion;
	protected Pieza pieza;
	protected Pedido pedido;
	protected Usuario usuario;
	protected Long id;

	public Reclamo_Fabrica() {
	}

	public Reclamo_Fabrica(Date fecha_reclamo_fabrica,String descripcion, Pieza pieza,
			Pedido pedido, Usuario usuario) {
		super();
		this.fecha_reclamo_fabrica = fecha_reclamo_fabrica;
		this.descripcion = descripcion;
		this.pieza = pieza;
		this.pedido = pedido;
		this.usuario = usuario;
	}

	public Date getFecha_reclamo_fabrica() {
		return fecha_reclamo_fabrica;
	}

	public void setFecha_reclamo_fabrica(Date fecha_reclamo_fabrica) {
		this.fecha_reclamo_fabrica = fecha_reclamo_fabrica;
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