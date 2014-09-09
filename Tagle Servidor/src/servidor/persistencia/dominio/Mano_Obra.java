package servidor.persistencia.dominio;

public class Mano_Obra {

	protected float cantidad_horas;
	protected float valor_mano_obra;
	protected String codigo_mano_obra;
	protected Reclamo reclamo;
	protected Long id;

	public Mano_Obra() {
	}

	public Mano_Obra(float cantidad_horas, float valor_mano_obra,
			String codigo_mano_obra, Reclamo reclamo) {
		super();
		this.cantidad_horas = cantidad_horas;
		this.valor_mano_obra = valor_mano_obra;
		this.codigo_mano_obra = codigo_mano_obra;
		this.setReclamo(reclamo);
	}

	public float getCantidad_horas() {
		return cantidad_horas;
	}

	public void setCantidad_horas(float cantidad_horas) {
		this.cantidad_horas = cantidad_horas;
	}

	public float getValor_mano_obra() {
		return valor_mano_obra;
	}

	public void setValor_mano_obra(float valor_mano_obra) {
		this.valor_mano_obra = valor_mano_obra;
	}

	public String getCodigo_mano_obra() {
		return codigo_mano_obra;
	}

	public void setCodigo_mano_obra(String codigo_mano_obra) {
		this.codigo_mano_obra = codigo_mano_obra;
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
