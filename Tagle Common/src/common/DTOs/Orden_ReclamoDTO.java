package common.DTOs;

public class Orden_ReclamoDTO  extends ClaseGeneralDTO{

	private static final long serialVersionUID = 1L;
	protected OrdenDTO orden;
	protected ReclamoDTO reclamo;
	
	public Orden_ReclamoDTO() {}

	public Orden_ReclamoDTO(OrdenDTO orden, ReclamoDTO reclamo) {
		super();
		this.orden = orden;
		this.reclamo = reclamo;
	}
	
	public OrdenDTO getOrden() {
		return orden;
	}

	public void setOrden(OrdenDTO orden) {
		this.orden = orden;
	}

	public ReclamoDTO getReclamo() {
		return reclamo;
	}

	public void setReclamo(ReclamoDTO reclamo) {
		this.reclamo = reclamo;
	}

}
