package common.DTOs;

import java.sql.Date;

public class PedidoDTO extends ClaseGeneralDTO {

	protected static final long serialVersionUID = 1L;
	protected Date fecha_solicitud_pedido;
	protected ReclamoDTO reclamo;

	public PedidoDTO() {}
	public PedidoDTO(Date fecha_solicitud_pedido,ReclamoDTO reclamo) {
		super();
		this.fecha_solicitud_pedido = fecha_solicitud_pedido;
		this.reclamo = reclamo;
	}
	public Date getFecha_solicitud_pedido() {
		return fecha_solicitud_pedido;
	}
	public void setFecha_solicitud_pedido(Date fecha_solicitud_pedido) {
		this.fecha_solicitud_pedido = fecha_solicitud_pedido;
	}
	public ReclamoDTO getReclamo() {
		return reclamo;
	}
	public void setReclamo(ReclamoDTO reclamo) {
		this.reclamo = reclamo;
	}

}
