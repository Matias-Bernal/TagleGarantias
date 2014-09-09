package common.DTOs;

public class PiezaDTO extends ClaseGeneralDTO {

	protected static final long serialVersionUID = 1L;
	protected String numero_pieza;
	protected String descripcion;
	protected ProveedorDTO proveedor;

	public PiezaDTO() {
	}

	public PiezaDTO(String numero_pieza, String descripcion, ProveedorDTO proveedor) {
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

	public ProveedorDTO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorDTO proveedor) {
		this.proveedor = proveedor;
	}

}
