package common.DTOs;

public class MuletoDTO extends ClaseGeneralDTO {

	protected static final long serialVersionUID = 1L;
	protected String descripcion;
	protected String vin;
	protected PedidoDTO pedido;
	protected PiezaDTO pieza;
	
	public MuletoDTO(){}
	public MuletoDTO(int id_muleto, String descripcion, String vin,PedidoDTO pedido, PiezaDTO pieza) {
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

}
