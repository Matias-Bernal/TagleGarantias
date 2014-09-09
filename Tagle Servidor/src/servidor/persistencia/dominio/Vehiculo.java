package servidor.persistencia.dominio;

public class Vehiculo {

	protected String dominio;
	protected String vin;
	protected String nombre_titular;
	protected Marca marca;
	protected Modelo modelo;
	protected Long id;

	public Vehiculo() {
	}

	public Vehiculo(String dominio, String vin, String nombre_titular,
			Marca marca, Modelo modelo) {
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

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}