package common.DTOs;

public class ModeloDTO extends ClaseGeneralDTO {

	protected static final long serialVersionUID = 1L;
	protected String nombre_modelo;
	protected MarcaDTO marca;
	
	public ModeloDTO(){}
	public ModeloDTO(String nombre_modelo, MarcaDTO marca) {
		super();
		this.nombre_modelo = nombre_modelo;
		this.marca = marca;
	}
	public String getNombre_modelo() {
		return nombre_modelo;
	}
	public void setNombre_modelo(String nombre_modelo) {
		this.nombre_modelo = nombre_modelo;
	}
	public MarcaDTO getMarca() {
		return marca;
	}
	public void setMarca(MarcaDTO marca) {
		this.marca = marca;
	}

}
