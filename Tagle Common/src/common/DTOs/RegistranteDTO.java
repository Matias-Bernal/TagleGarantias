package common.DTOs;

public class RegistranteDTO extends ClaseGeneralDTO {

	protected static final long serialVersionUID = 1L;
	protected String nombre_registrante;

	public RegistranteDTO() {}
	public RegistranteDTO(String nombre_registrante) {
		super();
		this.nombre_registrante = nombre_registrante;
	}
	public String getNombre_registrante() {
		return nombre_registrante;
	}
	public void setNombre_registrante(String nombre_registrante) {
		this.nombre_registrante = nombre_registrante;
	}

}
