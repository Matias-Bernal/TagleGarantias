package common.DTOs;

import java.sql.Date;

public class Reclamo_FabricaDTO extends ClaseGeneralDTO {

	protected static final long serialVersionUID = 1L;
	protected Date fecha_reclamo_fabrica;
	protected String descripcion;
	protected PiezaDTO pieza;
	protected PedidoDTO pedido;
	protected UsuarioDTO usuario;

	public Reclamo_FabricaDTO(){}
	public Reclamo_FabricaDTO(Date fecha_reclamo_fabrica,String descripcion, PiezaDTO pieza,
			PedidoDTO pedido, UsuarioDTO usuario) {
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
	public PiezaDTO getPieza() {
		return pieza;
	}
	public void setPieza(PiezaDTO pieza) {
		this.pieza = pieza;
	}
	public PedidoDTO getPedido() {
		return pedido;
	}
	public void setPedido(PedidoDTO pedido) {
		this.pedido = pedido;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
