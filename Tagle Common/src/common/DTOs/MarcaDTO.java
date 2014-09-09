package common.DTOs;

public class MarcaDTO extends ClaseGeneralDTO {

	protected static final long serialVersionUID = 1L;
	protected String nombre_marca;

	public MarcaDTO(){
	}
	public MarcaDTO(String nombre_marca) {
		super();
		this.nombre_marca = nombre_marca;
	}
	public String getNombre_marca() {
		return nombre_marca;
	}
	public void setNombre_marca(String nombre_marca) {
		this.nombre_marca = nombre_marca;
	}

}
