package servidor.persistencia.dominio;

import java.sql.Date;

public class Reclamo {

	protected Date fecha_reclamo;
	protected Date fecha_llamado_turno;
	protected Date fecha_turno;
	protected String estado_reclamo;
	protected Registrante registrante;
	protected Reclamante reclamante;
	protected Vehiculo vehiculo;
	protected Boolean inmovilizado;
	protected Boolean peligroso;
	protected Usuario usuario;
	protected String descripcion;
	protected Orden orden;
	protected Long id;

	public Reclamo() {
	}

	public Reclamo(Date fecha_reclamo,	Date fecha_llamado_turno, Date fecha_turno, String estado_reclamo,
			Registrante registrante, Reclamante reclamante, Vehiculo vehiculo,
			Boolean inmovilizado, Boolean peligroso, Usuario usuario,
			String descripcion,Orden orden) {
		super();
		this.fecha_reclamo = fecha_reclamo;
		this.fecha_llamado_turno = fecha_llamado_turno;
		this.fecha_turno = fecha_turno;
		this.estado_reclamo = estado_reclamo;
		this.registrante = registrante;
		this.reclamante = reclamante;
		this.vehiculo = vehiculo;
		this.inmovilizado = inmovilizado;
		this.peligroso = peligroso;
		this.usuario = usuario;
		this.descripcion = descripcion;
		this.orden = orden;
	}

	public Date getFecha_reclamo() {
		return fecha_reclamo;
	}

	public void setFecha_reclamo(Date fecha_reclamo) {
		this.fecha_reclamo = fecha_reclamo;
	}

	public Date getFecha_turno() {
		return fecha_turno;
	}

	public void setFecha_turno(Date fecha_turno) {
		this.fecha_turno = fecha_turno;
	}

	public String getEstado_reclamo() {
		return estado_reclamo;
	}

	public void setEstado_reclamo(String estado_reclamo) {
		this.estado_reclamo = estado_reclamo;
	}

	public Registrante getRegistrante() {
		return registrante;
	}

	public void setRegistrante(Registrante registrante) {
		this.registrante = registrante;
	}

	public Reclamante getReclamante() {
		return reclamante;
	}

	public void setReclamante(Reclamante reclamante) {
		this.reclamante = reclamante;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Boolean getInmovilizado() {
		return inmovilizado;
	}

	public void setInmovilizado(Boolean inmovilizado) {
		this.inmovilizado = inmovilizado;
	}

	public Boolean getPeligroso() {
		return peligroso;
	}

	public void setPeligroso(Boolean peligroso) {
		this.peligroso = peligroso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha_llamado_turno() {
		return fecha_llamado_turno;
	}

	public void setFecha_llamado_turno(Date fecha_llamado_turno) {
		this.fecha_llamado_turno = fecha_llamado_turno;
	}
	
}
