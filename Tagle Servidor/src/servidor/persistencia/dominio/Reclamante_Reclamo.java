package servidor.persistencia.dominio;

public class Reclamante_Reclamo {

	protected Reclamante reclamante;
	protected Reclamo reclamo;
	protected Long id;

	public Reclamante_Reclamo() {
	}

	public Reclamante_Reclamo(Reclamante reclamante, Reclamo reclamo) {
		super();
		this.reclamante = reclamante;
		this.reclamo = reclamo;
	}

	public Reclamante getReclamante() {
		return reclamante;
	}

	public void setReclamante(Reclamante reclamante) {
		this.reclamante = reclamante;
	}

	public Reclamo getReclamo() {
		return reclamo;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
