package common.DTOs;

public class ReclamanteDTO extends ClaseGeneralDTO{

	protected static final long serialVersionUID = 1L;
	protected String nombre_apellido;
	protected String dni;
	protected String email;
	
	public ReclamanteDTO(){}
	
	public ReclamanteDTO(String nombre_apellido, String dni, String email) {
		this.nombre_apellido = nombre_apellido;
		this.dni = dni;
		this.email = email;
	}
	public String getNombre_apellido() {
		return nombre_apellido;
	}
	public void setNombre_apellido(String nombre_apellido) {
		this.nombre_apellido = nombre_apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

}
