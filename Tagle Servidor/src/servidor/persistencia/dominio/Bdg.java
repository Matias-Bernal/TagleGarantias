package servidor.persistencia.dominio;

import java.sql.Date;

public class Bdg {

	protected Date fecha_bdg;
	protected Pieza pieza;
	protected Pedido pedido;	
	protected String numero_bdg;
	protected Long id;

	public Bdg() {
	}

	public Bdg(Date fecha_bdg, Pieza pieza, Pedido pedido, String numero_bdg) {
		super();
		this.fecha_bdg = fecha_bdg;
		this.pieza = pieza;
		this.pedido = pedido;
		this.numero_bdg = numero_bdg;
	}

	public Date getFecha_bdg() {
		return fecha_bdg;
	}

	public void setFecha_bdg(Date fecha_bdg) {
		this.fecha_bdg = fecha_bdg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero_bdg() {
		return numero_bdg;
	}

	public void setNumero_bdg(String numero_bdg) {
		this.numero_bdg = numero_bdg;
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

}
