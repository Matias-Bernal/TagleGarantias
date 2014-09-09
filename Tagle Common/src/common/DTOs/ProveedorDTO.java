package common.DTOs;

public class ProveedorDTO  extends ClaseGeneralDTO {

	protected static final long serialVersionUID = 1L;
	protected String nombre;
	
	public ProveedorDTO() {}
	
	public ProveedorDTO(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
