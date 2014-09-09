package common.DTOs;

import java.sql.Date;

public class Reclamo_AgenteDTO extends ClaseGeneralDTO {

	protected static final long serialVersionUID = 1L;
	protected Date fecha_reclamo_agente;
	protected String descripcion;
	protected PiezaDTO pieza;
	protected PedidoDTO pedido;
	protected UsuarioDTO usuario;

	public Reclamo_AgenteDTO(){}
	public Reclamo_AgenteDTO(Date fecha_reclamo_agente,String descripcion, PiezaDTO pieza,
			PedidoDTO pedido, UsuarioDTO usuario) {
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