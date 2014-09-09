package common.DTOs;

import java.sql.Date;

public class ReclamoDTO extends ClaseGeneralDTO{

	protected static final long serialVersionUID = 1L;
	protected Date fecha_reclamo;
	protected Date fecha_llamado_turno;
	protected Date fecha_turno;
	protected String estado_reclamo;
	protected RegistranteDTO registrante;
	protected ReclamanteDTO reclamante;
	protected VehiculoDTO vehiculo;
	protected Boolean inmovilizado;
	protected Boolean peligroso;
	protected OrdenDTO orden;
	protected UsuarioDTO usuario;
	protected String descripcion;

	public ReclamoDTO(){}
	public ReclamoDTO(Date fecha_reclamo, Date fecha_llamado_turno, Date fecha_turno,
			String estado_reclamo, RegistranteDTO registrante,
			ReclamanteDTO reclamante, VehiculoDTO vehiculo,
			Boolean inmovilizado, Boolean peligroso, UsuarioDTO usuario,String descripcion, OrdenDTO orden) {
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
	public RegistranteDTO getRegistrante() {
		return registrante;
	}
	public void setRegistrante(RegistranteDTO registrante) {
		this.registrante = registrante;
	}
	public ReclamanteDTO getReclamante() {
		return reclamante;
	}
	public void setReclamante(ReclamanteDTO reclamante) {
		this.reclamante = reclamante;
	}
	public VehiculoDTO getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(VehiculoDTO vehiculo) {
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
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public OrdenDTO getOrden() {
		return orden;
	}
	public void setOrden(OrdenDTO orden) {
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