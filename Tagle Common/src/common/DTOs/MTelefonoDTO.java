package common.DTOs;

public class MTelefonoDTO extends ClaseGeneralDTO {

	protected static final long serialVersionUID = 1L;
	protected ReclamanteDTO reclamante;
	protected String telefono;

	public MTelefonoDTO(){}
	public MTelefonoDTO(ReclamanteDTO reclamante, String telefono) {
		super();
		this.reclamante = reclamante;
		this.telefono = telefono;
	}
	public ReclamanteDTO getReclamante() {
		return reclamante;
	}
	public void setReclamante(ReclamanteDTO reclamante) {
		this.reclamante = reclamante;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
