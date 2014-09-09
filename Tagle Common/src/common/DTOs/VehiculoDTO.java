package common.DTOs;

public class VehiculoDTO extends ClaseGeneralDTO {

	protected static final long serialVersionUID = 1L;
	protected String dominio;
	protected String vin;
	protected String nombre_titular;
	protected MarcaDTO marca;
	protected ModeloDTO modelo;

	public VehiculoDTO(){}
	public VehiculoDTO(String dominio, String vin, String nombre_titular,
			MarcaDTO marca, ModeloDTO modelo) {
		super();
		this.dominio = dominio;
		this.vin = vin;
		this.nombre_titular = nombre_titular;
		this.marca = marca;
		this.modelo = modelo;
	}
	public String getDominio() {
		return dominio;
	}
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getNombre_titular() {
		return nombre_titular;
	}
	public void setNombre_titular(String nombre_titular) {
		this.nombre_titular = nombre_titular;
	}
	public MarcaDTO getMarca() {
		return marca;
	}
	public void setMarca(MarcaDTO marca) {
		this.marca = marca;
	}
	public ModeloDTO getModelo() {
		return modelo;
	}
	public void setModelo(ModeloDTO modelo) {
		this.modelo = modelo;
	}

}