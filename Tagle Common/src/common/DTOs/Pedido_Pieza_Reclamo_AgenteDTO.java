package common.DTOs;

public class Pedido_Pieza_Reclamo_AgenteDTO extends ClaseGeneralDTO{

	protected static final long serialVersionUID = 1L;
	protected PedidoDTO pedido;
	protected PiezaDTO pieza;
	protected Reclamo_AgenteDTO reclamo_agente;

	public Pedido_Pieza_Reclamo_AgenteDTO(){}
	public Pedido_Pieza_Reclamo_AgenteDTO(PedidoDTO pedido, PiezaDTO pieza,
			Reclamo_AgenteDTO reclamo_agente) {
		super();
		this.pedido = pedido;
		this.pieza = pieza;
		this.reclamo_agente = reclamo_agente;
	}
	public PedidoDTO getPedido() {
		return pedido;
	}
	public void setPedido(PedidoDTO pedido) {
		this.pedido = pedido;
	}
	public PiezaDTO getPieza() {
		return pieza;
	}
	public void setPieza(PiezaDTO pieza) {
		this.pieza = pieza;
	}
	public Reclamo_AgenteDTO getReclamo_agente() {
		return reclamo_agente;
	}
	public void setReclamo_agente(Reclamo_AgenteDTO reclamo_agente) {
		this.reclamo_agente = reclamo_agente;
	}
}