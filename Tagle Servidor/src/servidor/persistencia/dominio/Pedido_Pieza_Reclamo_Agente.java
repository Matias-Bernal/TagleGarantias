package servidor.persistencia.dominio;

public class Pedido_Pieza_Reclamo_Agente {
	
	protected Pedido pedido;
	protected Pieza pieza;
	protected Reclamo_Agente reclamo_agente;
	protected Long id;

	public Pedido_Pieza_Reclamo_Agente() {
	}

	public Pedido_Pieza_Reclamo_Agente(Pedido pedido, Pieza pieza,Reclamo_Agente reclamo_agente) {
		super();
		this.pedido = pedido;
		this.pieza = pieza;
		this.reclamo_agente = reclamo_agente;
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

	public Reclamo_Agente getReclamo_agente() {
		return reclamo_agente;
	}

	public void setReclamo_agente(Reclamo_Agente reclamo_agente) {
		this.reclamo_agente = reclamo_agente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
