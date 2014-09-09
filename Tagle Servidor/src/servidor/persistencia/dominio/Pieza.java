package servidor.persistencia.dominio;

public class Pieza {

	protected Long id;
	protected String numero_pieza;
	protected String descripcion;
	protected Proveedor proveedor;

	public Pieza() {
	}

	public Pieza(String numero_pieza, String descripcion, Proveedor proveedor) {
		super();
		this.numero_pieza = numero_pieza;
		this.descripcion = descripcion;
		this.proveedor = proveedor;
	}

	public String getNumero_pieza() {
		return numero_pieza;
	}

	public void setNumero_pieza(String numero_pieza) {
		this.numero_pieza = numero_pieza;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
}
