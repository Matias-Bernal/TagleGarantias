package common.DTOs;

public class Pedido_Pieza_Reclamo_FabricaDTO extends ClaseGeneralDTO{

	protected static final long serialVersionUID = 1L;
	protected PedidoDTO pedido;
	protected PiezaDTO pieza;
	protected Reclamo_FabricaDTO reclamo_fabrica;

	public Pedido_Pieza_Reclamo_FabricaDTO(){}
	public Pedido_Pieza_Reclamo_FabricaDTO(PedidoDTO pedido, PiezaDTO pieza,
			Reclamo_FabricaDTO reclamo_fabrica) {
		super();
		this.pedido = pedido;
		this.pieza = pieza;
		this.reclamo_fabrica = reclamo_fabrica;
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
	public Reclamo_FabricaDTO getReclamo_fabrica() {
		return reclamo_fabrica;
	}
	public void setReclamo_fabrica(Reclamo_FabricaDTO reclamo_fabrica) {
		this.reclamo_fabrica = reclamo_fabrica;
	}

}
