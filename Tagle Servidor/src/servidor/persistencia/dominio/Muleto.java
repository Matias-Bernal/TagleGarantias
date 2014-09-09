package servidor.persistencia.dominio;

public class Muleto {

	protected String descripcion;
	protected String vin;
	protected Pedido pedido;
	protected Pieza pieza;
	protected Long id;

	public Muleto() {
	}

	public Muleto(String descripcion, String vin, Pedido pedido, Pieza pieza) {
		super();
		this.descripcion = descripcion;
		this.vin = vin;
		this.pedido = pedido;
		this.pieza = pieza;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
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
