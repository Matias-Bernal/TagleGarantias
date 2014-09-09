package servidor.persistencia.dominio;

public class MTelefono {

	protected Reclamante reclamante;
	protected String telefono;
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MTelefono() {
	}

	public MTelefono(Reclamante reclamante, String telefono) {
		super();
		this.reclamante = reclamante;
		this.telefono = telefono;
	}

	public Reclamante getReclamante() {
		return reclamante;
	}

	public void setReclamante(Reclamante reclamante) {
		this.reclamante = reclamante;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
