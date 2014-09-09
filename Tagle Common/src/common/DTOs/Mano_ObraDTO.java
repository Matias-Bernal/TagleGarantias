package common.DTOs;

public class Mano_ObraDTO extends ClaseGeneralDTO {

	protected static final long serialVersionUID = 1L;
	protected float cantidad_horas;
	protected float valor_mano_obra;
	protected String codigo_mano_obra;
	protected ReclamoDTO reclamo;
	
	public Mano_ObraDTO(){}
	public Mano_ObraDTO(int id_mano_obra, float cantidad_horas,float valor_mano_obra, String codigo_mano_obra, ReclamoDTO reclamo) {
		super();
		this.cantidad_horas = cantidad_horas;
		this.valor_mano_obra = valor_mano_obra;
		this.codigo_mano_obra = codigo_mano_obra;
		this.reclamo = reclamo;
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
	public ReclamoDTO getReclamo() {
		return reclamo;
	}
	public void setReclamo(ReclamoDTO reclamo) {
		this.reclamo = reclamo;
	}

}
