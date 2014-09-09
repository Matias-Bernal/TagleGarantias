package servidor.persistencia.dominio;

public class Reclamante {
	
	protected Long id;
	protected String nombre_apellido;
	protected String dni;
	protected String email;

	public Reclamante() {
	}

	public Reclamante(String nombre_apellido, String dni, String email) {
		id = new Long(0);
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

}
