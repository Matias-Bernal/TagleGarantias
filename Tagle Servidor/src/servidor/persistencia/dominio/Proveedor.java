package servidor.persistencia.dominio;

public class Proveedor {
	
	protected Long id;
	protected String nombre;
	
	public Proveedor() {}
	
	public Proveedor(String nombre) {
		id = new Long(0);
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
