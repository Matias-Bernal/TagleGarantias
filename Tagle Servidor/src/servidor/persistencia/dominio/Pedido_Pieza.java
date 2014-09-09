package servidor.persistencia.dominio;

import java.sql.Date;

public class Pedido_Pieza {

	protected Long id;
	protected Pedido pedido;
	protected Pieza pieza;
	protected String numero_pedido;
	protected Date fecha_solicitud_fabrica;
	protected Date fecha_recepcion_fabrica;
	protected Date fecha_cambio;
	protected String pnc;
	protected Muleto muleto;
	protected Date fecha_solicitud_devolucion;
	protected Date fecha_aprobacion_solicitud_devolucion;
	protected Devolucion_Pieza devolucion_pieza;
	protected String estado_pedido ;
	protected Bdg bdg;
	//Entidad//
	protected Mano_Obra mano_obra;
	protected Boolean stock;
	protected Boolean propio;
	//Agente//
	protected Date fecha_envio_agente;
	protected Date fecha_recepcion_agente;
	protected Boolean pieza_usada;

	public Pedido_Pieza() {
	}

	public Pedido_Pieza(Pedido pedido, Pieza pieza) {
		super();
		this.pedido = pedido;
		this.pieza = pieza;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	public Boolean getStock() {
		return stock;
	}

	public void setStock(Boolean stock) {
		this.stock = stock;
	}

	public Boolean getPropio() {
		return propio;
	}

	public void setPropio(Boolean propio) {
		this.propio = propio;
	}

	public Muleto getMuleto() {
		return muleto;
	}

	public void setMuleto(Muleto muleto) {
		this.muleto = muleto;
	}

	public Devolucion_Pieza getDevolucion_pieza() {
		return devolucion_pieza;
	}

	public void setDevolucion_pieza(Devolucion_Pieza devolucion_pieza) {
		this.devolucion_pieza = devolucion_pieza;
	}

	public Bdg getBdg() {
		return bdg;
	}

	public void setBdg(Bdg bdg) {
		this.bdg = bdg;
	}

	public Mano_Obra getMano_obra() {
		return mano_obra;
	}

	public void setMano_obra(Mano_Obra mano_obra) {
		this.mano_obra = mano_obra;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha_solicitud_fabrica() {
		return fecha_solicitud_fabrica;
	}

	public void setFecha_solicitud_fabrica(Date fecha_solicitud_fabrica) {
		this.fecha_solicitud_fabrica = fecha_solicitud_fabrica;
	}

	public Date getFecha_recepcion_fabrica() {
		return fecha_recepcion_fabrica;
	}

	public void setFecha_recepcion_fabrica(Date fecha_recepcion_fabrica) {
		this.fecha_recepcion_fabrica = fecha_recepcion_fabrica;
	}

	public String getPnc() {
		return pnc;
	}

	public void setPnc(String pnc) {
		this.pnc = pnc;
	}

	public Date getFecha_envio_agente() {
		return fecha_envio_agente;
	}

	public void setFecha_envio_agente(Date fecha_envio_agente) {
		this.fecha_envio_agente = fecha_envio_agente;
	}

	public Date getFecha_recepcion_agente() {
		return fecha_recepcion_agente;
	}

	public void setFecha_recepcion_agente(Date fecha_recepcion_agente) {
		this.fecha_recepcion_agente = fecha_recepcion_agente;
	}

	public Boolean getPieza_usada() {
		return pieza_usada;
	}

	public void setPieza_usada(Boolean pieza_usada) {
		this.pieza_usada = pieza_usada;
	}

	public String getNumero_pedido() {
		return numero_pedido;
	}

	public void setNumero_pedido(String numero_pedido) {
		this.numero_pedido = numero_pedido;
	}

	public String getEstado_pedido() {
		return estado_pedido;
	}

	public void setEstado_pedido(String estado_pedido) {
		this.estado_pedido = estado_pedido;
	}

	public Date getFecha_solicitud_devolucion() {
		return fecha_solicitud_devolucion;
	}

	public void setFecha_solicitud_devolucion(Date fecha_solicitud_devolucion) {
		this.fecha_solicitud_devolucion = fecha_solicitud_devolucion;
	}

	public Date getFecha_aprobacion_solicitud_devolucion() {
		return fecha_aprobacion_solicitud_devolucion;
	}

	public void setFecha_aprobacion_solicitud_devolucion(
			Date fecha_aprobacion_solicitud_devolucion) {
		this.fecha_aprobacion_solicitud_devolucion = fecha_aprobacion_solicitud_devolucion;
	}

	public Date getFecha_cambio() {
		return fecha_cambio;
	}

	public void setFecha_cambio(Date fecha_cambio) {
		this.fecha_cambio = fecha_cambio;
	}
	
}
