package common.DTOs;

public class Reclamante_ReclamoDTO extends ClaseGeneralDTO {

	protected static final long serialVersionUID = 1L;
	protected ReclamanteDTO reclamante;
	protected ReclamoDTO reclamo;

	public Reclamante_ReclamoDTO(){}
	public Reclamante_ReclamoDTO(ReclamanteDTO reclamante, ReclamoDTO reclamo) {
		super();
		this.reclamante = reclamante;
		this.reclamo = reclamo;
	}
	public ReclamanteDTO getReclamante() {
		return reclamante;
	}
	public void setReclamante(ReclamanteDTO reclamante) {
		this.reclamante = reclamante;
	}
	public ReclamoDTO getReclamo() {
		return reclamo;
	}
	public void setReclamo(ReclamoDTO reclamo) {
		this.reclamo = reclamo;
	}

}
