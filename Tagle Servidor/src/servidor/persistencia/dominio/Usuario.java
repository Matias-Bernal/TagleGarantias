package servidor.persistencia.dominio;

public class Usuario {

	protected String nombre_usuario;
	protected String clave;
	protected String email;
	protected Long id;
	protected String tipo;

	public Usuario() {
	}

	public Usuario(String nombre_usuario, String clave, String email, String tipo) {
		super();
		this.nombre_usuario = nombre_usuario;
		this.clave = clave;
		this.email = email;
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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
}
