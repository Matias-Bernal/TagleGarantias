package servidor.persistencia.dominio;

public class Pedido_Pieza_Reclamo_Fabrica {

	protected Pedido pedido;
	protected Pieza pieza;
	protected Reclamo_Fabrica reclamo_fabrica;
	protected Long id;

	public Pedido_Pieza_Reclamo_Fabrica() {
	}

	public Pedido_Pieza_Reclamo_Fabrica(Pedido pedido, Pieza pieza,Reclamo_Fabrica reclamo_fabrica) {
		super();
		this.pedido = pedido;
		this.pieza = pieza;
		this.reclamo_fabrica = reclamo_fabrica;
	}

	public Reclamo_Fabrica getReclamo_fabrica() {
		return reclamo_fabrica;
	}

	public void setReclamo_fabrica(Reclamo_Fabrica reclamo_fabrica) {
		this.reclamo_fabrica = reclamo_fabrica;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}
	
}
