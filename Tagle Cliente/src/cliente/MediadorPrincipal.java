/********************************************************
  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *********************************************************/
package cliente;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JOptionPane;

import cliente.GestionOrden.MediadorOrden;
import cliente.GestionarNotificacion.MediadorEjecutarNotificacion;
import cliente.GestionarNotificacion.MediadorNotificacion;
import cliente.GestionarPedido.MediadorPedido;
import cliente.GestionarReclamante.MediadorReclamante;
import cliente.GestionarReclamo.MediadorReclamo;
import cliente.GestionarRegistrante.MediadorRegistrante;
import cliente.GestionarUsuario.MediadorUsuario;
import cliente.GestionarVehiculo.MediadorVehiculo;
import cliente.ReclamoPiezas.MediadorReclamoPiezas;
import cliente.ReclamoRapido.MediadoReclamoRapido;
import cliente.Reportes.MediadorReportes;
import common.RootAndIp;
import common.DTOs.MTelefonoDTO;
import common.DTOs.NotificacionDTO;
import common.DTOs.Notificacion_ReclamoDTO;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.UsuarioDTO;
import common.GestionarMTelefono.IControlMTelefono;
import common.GestionarNotificacion.IControlNotificacion;
import common.GestionarNotificacion_Reclamo.IControlNotificacion_Reclamo;
import common.GestionarPedido_Pieza.IControlPedido_Pieza;
import common.GestionarUsuario.IControlUsuario;


@SuppressWarnings("unused")
public class MediadorPrincipal{

	protected GUIMenu_Principal gui_menu_Principal;
	protected GUILogin gui_login; 
	protected UsuarioDTO usuario;
	protected boolean notificacionesTurnos;
	protected boolean notificacionesContencion;
	protected boolean notificacionesReclamosAgentes;
	protected boolean notificacionesReclamosFabrica;
	protected boolean notificacionesSugerencias;
	protected Vector<MediadorEjecutarNotificacion> notificacionesLanzadas;
	protected Vector<Notificacion_ReclamoDTO> notificaciones;
	protected MediadorUsuario mediadorUsuario;
	protected MediadorRegistrante mediadorRegistrante; 
	protected MediadorReclamante mediadorReclamante;
	protected MediadorVehiculo mediadorVehiculos;
	protected MediadorPedido mediadorPedidos;
	protected MediadorReclamo mediadorReclamos;
	protected MediadorNotificacion mediadorNotificaciones;
	protected MediadorOrden mediadorOrden;
	protected MediadorReclamoPiezas mediadorRepuestos;
	protected MediadoReclamoRapido mediadorReclamoRapido;
	protected MediadorReportes mediadorReporte;	
	protected MediadorReclamoPiezas mediadorRepuesto;
	protected int limiteNotificacionesTurno;
	protected int limiteNotificacionesContencion;
	protected int limiteNotificacionesReclamoAgentes;
	protected int limiteNotificacionesReclamoFabrica;
	protected int limiteNotificacionesSugerencias;
	
	
	public MediadorPrincipal() throws Exception{
		gui_login = new GUILogin(this);
		gui_login.setVisible(true);
		limiteNotificacionesTurno = 10;
		limiteNotificacionesContencion = 10;
		limiteNotificacionesReclamoAgentes = 10;
		limiteNotificacionesReclamoFabrica = 10;
		limiteNotificacionesSugerencias = 5;
	}

	// Geters y Seters // 
	
	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
	public boolean acceso(String usuario, String contrasenia) throws Exception {
		boolean result = false;
		try{
			IControlUsuario iControlUsuario = MediadorAccionesIniciarPrograma.getControlUsuarios();
			if (iControlUsuario.login(usuario, contrasenia)){
				UsuarioDTO usuarioDTO = iControlUsuario.buscarUsuario(usuario);
				iniciarMenuPrincipal(usuarioDTO);
				result = true;
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(gui_login,"Error de conexion.","Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(0);
		}
		return result;
	}
	public void iniciarMenuPrincipal(UsuarioDTO usuario){
		setUsuario(usuario);
		if(usuario.getTipo().equals("Administrativo")){
			notificacionesContencion = true;
			notificacionesTurnos = true;
		}else{
			//if(usuario.getTipo().equals("Encargado Repuesto")){
				notificacionesReclamosAgentes = true;
				notificacionesReclamosFabrica = true;
				notificacionesSugerencias = true;
			//}
		}
		gui_menu_Principal = new GUIMenu_Principal(this);
		gui_menu_Principal.setVisible(true);
		actualizarTablaNotificaciones();
	}
	
	public void reiniciar(){
		gui_menu_Principal.dispose();
		gui_login = new GUILogin(this);
		gui_login.setVisible(true);
	}

	public void salir() {
		gui_menu_Principal.dispose();
		gui_login.dispose();
		System.exit(0);
	}
	
	// Usuarios //
	
	public void altaUsuario(){
		mediadorUsuario = new MediadorUsuario(this);
		mediadorUsuario.altaUsuario();
	}
	public void altaUsuario(String nombre_usuario, String email, String tipo){
		mediadorUsuario = new MediadorUsuario(this);
		mediadorUsuario.altaUsuario(nombre_usuario, email, tipo);
	}
	public void gestionarUsuario(){
		mediadorUsuario = new MediadorUsuario(this);
		mediadorUsuario.gestioUsuario();
	}
	
	// Registrantes //
	public void altaRegistrante(){
		mediadorRegistrante = new MediadorRegistrante(this);
		mediadorRegistrante.altaRegistrante();
	}
	public void altaRegistrante(String nombre_registrante, String tipo){
		mediadorRegistrante = new MediadorRegistrante(this);
		mediadorRegistrante.altaRegistrante(nombre_registrante, tipo);
	}
	public void gestionarRegistrante(){
		mediadorRegistrante = new MediadorRegistrante(this);
		mediadorRegistrante.gestioRegistrante();
	}

	// Reclamantes //
	public void altaReclamante(){
		mediadorReclamante = new MediadorReclamante(this);
		mediadorReclamante.altaReclamante();
	}
	public void altaReclamante(String nombre_reclamante, String email, String dni){
		mediadorReclamante = new MediadorReclamante(this);
		mediadorReclamante.altaReclamante(nombre_reclamante,email, dni);
	}
	public void gestionarReclamante(){
		mediadorReclamante = new MediadorReclamante(this);
		mediadorReclamante.gestionReclamante();
	}

	// Vehiculo //
	public void altaVehiculo(){
		mediadorVehiculos = new MediadorVehiculo(this);
		mediadorVehiculos.altaVehiculo();
	}
	public void altaVehiculo(String nombre_titular, String dominio, String vin, String marca, String modelo){
		mediadorVehiculos = new MediadorVehiculo(this);
		mediadorVehiculos.altaVehiculo(nombre_titular,dominio,vin,marca,modelo);
	}
	public void gestionarVehiculo(){
		mediadorVehiculos = new MediadorVehiculo(this);
		mediadorVehiculos.gestionVehiculo();
	}

	// Pedido //
	public void altaPedidoEntidad() {
		mediadorPedidos = new MediadorPedido(this);
		mediadorPedidos.altaPedidoEntidad();
	}
	public void altaPedidoAgente() {
		mediadorPedidos = new MediadorPedido(this);
		mediadorPedidos.altaPedidoAgente();
	}
	public void gestionarPedidoEntidad() {
		mediadorPedidos = new MediadorPedido(this);
		mediadorPedidos.gestionarPedidoEntidad();
	}
	public void gestionarPedidoAgente() {
		mediadorPedidos = new MediadorPedido(this);
		mediadorPedidos.gestionarPedidoAgente();
	}

	// Reclamo //
	public void altaReclamoAgente(){
		mediadorReclamos = new MediadorReclamo(this);
		mediadorReclamos.altaReclamoAgente();
	}
	public void altaReclamoEntidad() {
		mediadorReclamos = new MediadorReclamo(this);
		mediadorReclamos.altaReclamoEntidad();		
	}
	public void gestionarReclamoAgente(){
		mediadorReclamos = new MediadorReclamo(this);
		mediadorReclamos.gestionReclamoAgente();
	}
	public void gestionarReclamoEntidad(){
		mediadorReclamos = new MediadorReclamo(this);
		mediadorReclamos.gestionReclamoEntidad();
	}

	// Notificacion //
	public void gestionarNotificaciones(){
		mediadorNotificaciones = new MediadorNotificacion(this);
		mediadorNotificaciones.gestionNotificacion();
	}
	public void actualizarNotificaciones(){
		mediadorNotificaciones = new MediadorNotificacion(this);
		mediadorNotificaciones.actualizarNotificaciones();
	}

	// Ayuda //
	public void ayuda(){
		File manual = new File(RootAndIp.getPath_manual()+"Manual.pdf");
		if(manual.exists()){
			try {
				Desktop.getDesktop().open(manual);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"Error al cargar el manual.","Error",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}else{
			JOptionPane.showMessageDialog(null,"No hay manual actualizar.","Error",JOptionPane.INFORMATION_MESSAGE);

		}
	}

	//ORDEN//
	public void altaOrden() {
		mediadorOrden = new MediadorOrden(this);
		mediadorOrden.altaOrden();
	}
	public void gestionarOrden() {
		mediadorOrden = new MediadorOrden(this);
		mediadorOrden.gestionarOrden();		
	}
	
	// RECLAMOS //
	public void reclamoRapidoEntidad() {
		mediadorReclamoRapido= new MediadoReclamoRapido(this);
		mediadorReclamoRapido.altaReclamoRapidoEntidad();
	}
	public void reclamoRapidoAgente() {
		mediadorReclamoRapido= new MediadoReclamoRapido(this);
		mediadorReclamoRapido.altaReclamoRapidoAgente();
	}
	
	// REPUESTOS //
	public void repuestos() {
		mediadorRepuesto = new MediadorReclamoPiezas(this);
		mediadorRepuesto.mostrarRepuesto();
	}

	// REPORTES //
	public void reportesControl() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarReportesControl();
	}
	public void reportesGestion() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarReportesGestion();
	}
	
	public void setTiposNotificaciones(boolean turnos, boolean contencion,boolean agentes, boolean reclamos, boolean sugerencias) {
		notificacionesTurnos = turnos;
		notificacionesContencion = contencion;
		notificacionesReclamosAgentes = agentes;
		notificacionesReclamosFabrica = reclamos;
		notificacionesSugerencias = sugerencias;
		actualizarTablaNotificaciones();
	}

	public void borrarNotificacion(Notificacion_ReclamoDTO notificacion){
		for (int i=0; i<notificaciones.size();i++){
			if (notificacion.getId().equals(notificaciones.elementAt(i).getId())){
				notificaciones.remove(i);
				break;
			}
		}
		gui_menu_Principal.actualizarTabla(notificaciones);
	}

	public void actualizarTablaNotificaciones() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date fechaHoy = new java.util.Date();
		String fecha = format2.format(fechaHoy);
	    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());

	    notificaciones = new Vector<Notificacion_ReclamoDTO>();
	    notificaciones = cargarNotificaciones(hoy);
	    lanzarNotificaciones(notificaciones);
		
		gui_menu_Principal.actualizarTabla(notificaciones);
	    
	}

	private void lanzarNotificaciones(Vector<Notificacion_ReclamoDTO> notificaciones_reclamos) {
		matarThreads();
		notificacionesLanzadas = new Vector<MediadorEjecutarNotificacion>();
		for(int i = 0 ; i<notificaciones_reclamos.size();i++){
			MediadorEjecutarNotificacion mn = new MediadorEjecutarNotificacion(this, notificaciones_reclamos.elementAt(i), new Long(900000*(i+1)),this.usuario);
			mn.start();
			notificacionesLanzadas.add(mn);
		}
	}

	private Vector<Notificacion_ReclamoDTO> cargarNotificaciones(Date hoy) {
		Vector<Notificacion_ReclamoDTO> notificaciones = new Vector<Notificacion_ReclamoDTO>();
		Vector<Notificacion_ReclamoDTO> notificacionesGuardadas = new Vector<Notificacion_ReclamoDTO>();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlNotificacion_Reclamo iControlNotificacion_Reclamo = MediadorAccionesIniciarPrograma.getControlNotificacion_Reclamo();
		IControlMTelefono iControlMTelefono = MediadorAccionesIniciarPrograma.getControlMTelefono();
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 

		try {
			if(notificacionesTurnos){
				Vector<Pedido_PiezaDTO> pedidos_piezas_sin_turno = iControlPedido_Pieza.obtenerPedido_PiezaSinTurno();
				int notificacionesTurno = 0;
				for(int i = 0; i<pedidos_piezas_sin_turno.size() && notificacionesTurno<=limiteNotificacionesTurno ;i++){
					NotificacionDTO notificacion = new NotificacionDTO();
					Notificacion_ReclamoDTO notificacion_reclamoDTO = new Notificacion_ReclamoDTO();
					notificacion.setConcretada_notificacion(false);
					notificacion.setFecha_notificacion(hoy);
					notificacion.setTipo_notificacion("TURNO");
					String telefono = "";
					Vector<MTelefonoDTO> telefonosDTO = iControlMTelefono.obtenerMTelefono(pedidos_piezas_sin_turno.elementAt(i).getPedido().getReclamo().getReclamante());
					for (int j=0; j<telefonosDTO.size();j++){
						telefono += telefonosDTO.elementAt(j).getTelefono()+" ";
					}
					notificacion.setTexto_notificacion(pedidos_piezas_sin_turno.elementAt(i).getPedido().getReclamo().getReclamante().getNombre_apellido()+" [ID: "+pedidos_piezas_sin_turno.elementAt(i).getPedido().getReclamo().getReclamante().getId()+"]\n"
														+"TELEFONO/S: "+telefono+"\n"
														+"ORDEN DE TRABAJO: "+pedidos_piezas_sin_turno.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden()+" [ID: "+pedidos_piezas_sin_turno.elementAt(i).getPedido().getReclamo().getOrden().getId()+"]\n"
														+"VIN: "+pedidos_piezas_sin_turno.elementAt(i).getPedido().getReclamo().getVehiculo().getVin()+"\n"
														+"NUM PEDIDO: "+pedidos_piezas_sin_turno.elementAt(i).getNumero_pedido()+"\n"
														+"DESCRIPCION RECLAMO: "+pedidos_piezas_sin_turno.elementAt(i).getPedido().getReclamo().getDescripcion());
					notificacion_reclamoDTO.setNotificacion(notificacion);
					notificacion_reclamoDTO.setReclamo(pedidos_piezas_sin_turno.elementAt(i).getPedido().getReclamo());
					notificaciones.add(notificacion_reclamoDTO);
					notificacionesTurno++;
				}
			}
			notificacionesGuardadas = iControlNotificacion_Reclamo.obtenerNotificaciones_Reclamos();
			if(notificacionesContencion){
				Vector<Pedido_PiezaDTO> pedidos_piezas_contencion = iControlPedido_Pieza.obtenerPedido_PiezaContencion();
				Vector<Pedido_PiezaDTO> auxiliar = new Vector<Pedido_PiezaDTO>();
				for(int i = 0; i<pedidos_piezas_contencion.size();i++){
					java.sql.Date fUltimaNotificacion = null;
					for(int j = 0; j<notificacionesGuardadas.size();j++){
						if(notificacionesGuardadas.elementAt(j).getReclamo().getId().equals(pedidos_piezas_contencion.elementAt(i).getPedido().getReclamo().getId()) && notificacionesGuardadas.elementAt(j).getNotificacion().getTipo_notificacion().equals("CONTENCION")){
							java.sql.Date fnotificacion = new java.sql.Date(notificacionesGuardadas.elementAt(j).getNotificacion().getFecha_notificacion().getTime());
							@SuppressWarnings("deprecation")
							Calendar calendar = new GregorianCalendar(fnotificacion.getYear(), fnotificacion.getMonth()-1, fnotificacion.getDay());
							if (fUltimaNotificacion != null){
								long diferencia = ( fUltimaNotificacion.getTime() - fnotificacion.getTime() )/MILLSECS_PER_DAY;
								if(diferencia>=0)
									fUltimaNotificacion = fnotificacion;
							}else{
								fUltimaNotificacion = fnotificacion;
							}
						}
					}
					if(fUltimaNotificacion!=null){
						@SuppressWarnings("deprecation")
						Calendar calendar = new GregorianCalendar(fUltimaNotificacion.getYear(), fUltimaNotificacion.getMonth()-1, fUltimaNotificacion.getDay());
						long diferencia = ( hoy.getTime() - fUltimaNotificacion.getTime() )/MILLSECS_PER_DAY;
						if(diferencia>=10)
							auxiliar.add(pedidos_piezas_contencion.elementAt(i));
					}else{
						auxiliar.add(pedidos_piezas_contencion.elementAt(i));
					}
				}
				int notificacionesContencion = 0;
				for(int i = 0; i<auxiliar.size() && notificacionesContencion<=limiteNotificacionesContencion;i++){
					NotificacionDTO notificacion = new NotificacionDTO();
					Notificacion_ReclamoDTO notificacion_reclamoDTO = new Notificacion_ReclamoDTO();
					notificacion.setConcretada_notificacion(false);
					notificacion.setFecha_notificacion(hoy);
					notificacion.setTipo_notificacion("CONTENCION");
					String telefono = "";
					Vector<MTelefonoDTO> telefonosDTO = iControlMTelefono.obtenerMTelefono(auxiliar.elementAt(i).getPedido().getReclamo().getReclamante());
					for (int j=0; j<telefonosDTO.size();j++){
						telefono += telefonosDTO.elementAt(j).getTelefono()+" ";
					}
					notificacion.setTexto_notificacion(auxiliar.elementAt(i).getPedido().getReclamo().getReclamante().getNombre_apellido()+" [ID: "+auxiliar.elementAt(i).getPedido().getReclamo().getReclamante().getId()+"]\n"
							+"TELEFONO/S: "+telefono+"\n"
							+"ORDEN DE TRABAJO: "+auxiliar.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden()+" [ID: "+auxiliar.elementAt(i).getPedido().getReclamo().getOrden().getId()+"]\n"
							+"VIN: "+auxiliar.elementAt(i).getPedido().getReclamo().getVehiculo().getVin()+"\n"
							+"NUM PEDIDO: "+auxiliar.elementAt(i).getNumero_pedido()+"\n"
							+"DESCRIPCION RECLAMO: "+auxiliar.elementAt(i).getPedido().getReclamo().getDescripcion());
					notificacion_reclamoDTO.setNotificacion(notificacion);
					notificacion_reclamoDTO.setReclamo(auxiliar.elementAt(i).getPedido().getReclamo());
					notificaciones.add(notificacion_reclamoDTO);
					notificacionesContencion++;
				}
			}
			if(notificacionesReclamosAgentes){
				Vector<Pedido_PiezaDTO> pedidos_piezas_reclamo_agentes = iControlPedido_Pieza.obtenerPedido_PiezaReclamoAgente();
				Vector<Pedido_PiezaDTO> auxiliar = new Vector<Pedido_PiezaDTO>();
				for(int i = 0; i<pedidos_piezas_reclamo_agentes.size();i++){
					java.sql.Date fUltimaNotificacion = null;
					for(int j = 0; j<notificacionesGuardadas.size();j++){
						if(notificacionesGuardadas.elementAt(j).getReclamo().getId().equals(pedidos_piezas_reclamo_agentes.elementAt(i).getPedido().getReclamo().getId()) && notificacionesGuardadas.elementAt(j).getNotificacion().getTipo_notificacion().equals("RECLAMO AGENTE")){
							java.sql.Date fnotificacion = new java.sql.Date(notificacionesGuardadas.elementAt(j).getNotificacion().getFecha_notificacion().getTime());
							@SuppressWarnings("deprecation")
							Calendar calendar = new GregorianCalendar(fnotificacion.getYear(), fnotificacion.getMonth()-1, fnotificacion.getDay());
							
							if (fUltimaNotificacion != null){
								long diferencia = ( fUltimaNotificacion.getTime() - fnotificacion.getTime() )/MILLSECS_PER_DAY;
								if(diferencia>=0)
									fUltimaNotificacion = fnotificacion;
							}else{
								fUltimaNotificacion = fnotificacion;
							}
						}
					}
					if(fUltimaNotificacion!=null){
						@SuppressWarnings("deprecation")
						Calendar calendar = new GregorianCalendar(fUltimaNotificacion.getYear(), fUltimaNotificacion.getMonth()-1, fUltimaNotificacion.getDay());
						long diferencia = ( hoy.getTime() - fUltimaNotificacion.getTime() )/MILLSECS_PER_DAY;
						if(diferencia>=10)
							auxiliar.add(pedidos_piezas_reclamo_agentes.elementAt(i));
					}else{
						auxiliar.add(pedidos_piezas_reclamo_agentes.elementAt(i));
					}
				}
				int notificacionesReclamoAgentes = 0;
				for(int i = 0; i<auxiliar.size() && notificacionesReclamoAgentes<=limiteNotificacionesReclamoAgentes;i++){
					NotificacionDTO notificacion = new NotificacionDTO();
					Notificacion_ReclamoDTO notificacion_reclamoDTO = new Notificacion_ReclamoDTO();
					notificacion.setConcretada_notificacion(false);
					notificacion.setFecha_notificacion(hoy);
					notificacion.setTipo_notificacion("RECLAMO AGENTE");
					String telefono = "";
					Vector<MTelefonoDTO> telefonosDTO = iControlMTelefono.obtenerMTelefono(auxiliar.elementAt(i).getPedido().getReclamo().getReclamante());
					for (int j=0; j<telefonosDTO.size();j++){
						telefono += telefonosDTO.elementAt(j).getTelefono()+" ";
					}
					notificacion.setTexto_notificacion("AGENTE: "+auxiliar.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante()+" [ID: "+auxiliar.elementAt(i).getPedido().getReclamo().getRegistrante().getId()+"]\n"
														+"VIN: "+auxiliar.elementAt(i).getPedido().getReclamo().getVehiculo().getVin()+"\n"
														+"NUM PEDIDO: "+auxiliar.elementAt(i).getNumero_pedido()+"\n"
														+"NUM PIEZA: "+auxiliar.elementAt(i).getPieza().getNumero_pieza()+"\n"
														+"DESCRIPCION RECLAMO: "+auxiliar.elementAt(i).getPedido().getReclamo().getDescripcion());
					notificacion_reclamoDTO.setNotificacion(notificacion);
					notificacion_reclamoDTO.setReclamo(auxiliar.elementAt(i).getPedido().getReclamo());
					notificaciones.add(notificacion_reclamoDTO);
					notificacionesReclamoAgentes++;
				}
			}
			if(notificacionesReclamosFabrica){
				Vector<Pedido_PiezaDTO> pedidos_piezas_reclamo_fabrica = iControlPedido_Pieza.obtenerPedido_PiezaReclamoFabrica();
				Vector<Pedido_PiezaDTO> auxiliar = new Vector<Pedido_PiezaDTO>();
				for(int i = 0; i<pedidos_piezas_reclamo_fabrica.size();i++){
					java.sql.Date fUltimaNotificacion = null;
					for(int j = 0; j<notificacionesGuardadas.size();j++){
						if(notificacionesGuardadas.elementAt(j).getReclamo().getId().equals(pedidos_piezas_reclamo_fabrica.elementAt(i).getPedido().getReclamo().getId()) && notificacionesGuardadas.elementAt(j).getNotificacion().getTipo_notificacion().equals("RECLAMO FABRICA")){
							java.sql.Date fnotificacion = new java.sql.Date(notificacionesGuardadas.elementAt(j).getNotificacion().getFecha_notificacion().getTime());
							@SuppressWarnings("deprecation")
							Calendar calendar = new GregorianCalendar(fnotificacion.getYear(), fnotificacion.getMonth()-1, fnotificacion.getDay());
							
							if (fUltimaNotificacion != null){
								long diferencia = ( fUltimaNotificacion.getTime() - fnotificacion.getTime() )/MILLSECS_PER_DAY;
								if(diferencia>=0)
									fUltimaNotificacion = fnotificacion;
							}else{
								fUltimaNotificacion = fnotificacion;
							}
						}
					}
					if(fUltimaNotificacion!=null){
						@SuppressWarnings("deprecation")
						Calendar calendar = new GregorianCalendar(fUltimaNotificacion.getYear(), fUltimaNotificacion.getMonth()-1, fUltimaNotificacion.getDay());
						long diferencia = ( hoy.getTime() - fUltimaNotificacion.getTime() )/MILLSECS_PER_DAY;
						if(diferencia>=7)
							auxiliar.add(pedidos_piezas_reclamo_fabrica.elementAt(i));
					}else{
						auxiliar.add(pedidos_piezas_reclamo_fabrica.elementAt(i));
					}
				}
				int notificacionesReclamoFabrica = 0;
				for(int i = 0; i<auxiliar.size() && notificacionesReclamoFabrica<=limiteNotificacionesReclamoFabrica;i++){
					NotificacionDTO notificacion = new NotificacionDTO();
					Notificacion_ReclamoDTO notificacion_reclamoDTO = new Notificacion_ReclamoDTO();
					notificacion.setConcretada_notificacion(false);
					notificacion.setFecha_notificacion(hoy);
					notificacion.setTipo_notificacion("RECLAMO FABRICA");
					notificacion.setTexto_notificacion("REGISTRANTE: "+auxiliar.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante()+" [ID: "+auxiliar.elementAt(i).getPedido().getReclamo().getRegistrante().getId()+"]\n"
														+"NUMERO PEDIDO: "+auxiliar.elementAt(i).getNumero_pedido()+" [ID: "+auxiliar.elementAt(i).getId()+"]\n"
														+"PIEZA: "+auxiliar.elementAt(i).getPieza().getNumero_pieza()+" [ID: "+auxiliar.elementAt(i).getPieza().getId()+"]\n"
														+"FECHA SOLICITUD FABRICA: "+auxiliar.elementAt(i).getFecha_solicitud_fabrica().toString()+"\n"
														+"NUMERO ORDEN: "+auxiliar.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden()+" [ID: "+auxiliar.elementAt(i).getPedido().getReclamo().getOrden().getId()+"]\n"
														);
					notificacion_reclamoDTO.setNotificacion(notificacion);
					notificacion_reclamoDTO.setReclamo(auxiliar.elementAt(i).getPedido().getReclamo());
					notificaciones.add(notificacion_reclamoDTO);
					notificacionesReclamoFabrica++;
				}
			}
			if(notificacionesSugerencias){
				Vector<Pedido_PiezaDTO> pedidos_piezas_sugerencias = iControlPedido_Pieza.obtenerPedido_PiezaSugerencia();
				Vector<Pedido_PiezaDTO> auxiliar = new Vector<Pedido_PiezaDTO>();
				for(int i = 0; i<pedidos_piezas_sugerencias.size();i++){
					java.sql.Date fUltimaNotificacion = null;
					for(int j = 0; j<notificacionesGuardadas.size();j++){
						if(notificacionesGuardadas.elementAt(j).getReclamo().getId().equals(pedidos_piezas_sugerencias.elementAt(i).getPedido().getReclamo().getId()) && notificacionesGuardadas.elementAt(j).getNotificacion().getTipo_notificacion().equals("SUGERENCIA")){
							java.sql.Date fnotificacion = new java.sql.Date(notificacionesGuardadas.elementAt(j).getNotificacion().getFecha_notificacion().getTime());
							@SuppressWarnings("deprecation")
							Calendar calendar = new GregorianCalendar(fnotificacion.getYear(), fnotificacion.getMonth()-1, fnotificacion.getDay());
							
							if (fUltimaNotificacion != null){
								long diferencia = ( fUltimaNotificacion.getTime() - fnotificacion.getTime() )/MILLSECS_PER_DAY;
								if(diferencia>=0)
									fUltimaNotificacion = fnotificacion;
							}else{
								fUltimaNotificacion = fnotificacion;
							}
						}
					}
					if(fUltimaNotificacion!=null){
						@SuppressWarnings("deprecation")
						Calendar calendar = new GregorianCalendar(fUltimaNotificacion.getYear(), fUltimaNotificacion.getMonth()-1, fUltimaNotificacion.getDay());
						long diferencia = ( hoy.getTime() - fUltimaNotificacion.getTime() )/MILLSECS_PER_DAY;
						if(diferencia>=7)
							auxiliar.add(pedidos_piezas_sugerencias.elementAt(i));
					}else{
						auxiliar.add(pedidos_piezas_sugerencias.elementAt(i));
					}
				}
				int notificacionesSugerencia = 0;
				for(int i = 0; i<auxiliar.size() && notificacionesSugerencia<=limiteNotificacionesSugerencias;i++){
					NotificacionDTO notificacion = new NotificacionDTO();
					Notificacion_ReclamoDTO notificacion_reclamoDTO = new Notificacion_ReclamoDTO();
					notificacion.setConcretada_notificacion(false);
					notificacion.setFecha_notificacion(hoy);
					notificacion.setTipo_notificacion("SUGERENCIA");
					notificacion.setTexto_notificacion("PUEDE USAR UN MULETO PARA ESTE PEDIDO?\n"
														+"REGISTRANTE: "+auxiliar.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante()+" [ID: "+auxiliar.elementAt(i).getPedido().getReclamo().getRegistrante().getId()+"]\n"
														+"NUMERO PEDIDO: "+auxiliar.elementAt(i).getNumero_pedido()+" [ID: "+auxiliar.elementAt(i).getId()+"]\n"
														+"PIEZA: "+auxiliar.elementAt(i).getPieza().getNumero_pieza()+" [ID: "+auxiliar.elementAt(i).getPieza().getId()+"]\n"
														+"FECHA SOLICITUD FABRICA: "+auxiliar.elementAt(i).getFecha_solicitud_fabrica().toString()+"\n"
														+"NUMERO ORDEN: "+auxiliar.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden()+" [ID: "+auxiliar.elementAt(i).getPedido().getReclamo().getOrden().getId()+"]\n"
														);
					notificacion_reclamoDTO.setNotificacion(notificacion);
					notificacion_reclamoDTO.setReclamo(auxiliar.elementAt(i).getPedido().getReclamo());
					notificaciones.add(notificacion_reclamoDTO);
					notificacionesSugerencia++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notificaciones;
	}

	public boolean isNotificacionesTurnos() {
		return notificacionesTurnos;
	}

	public void setNotificacionesTurnos(boolean notificacionesTurnos) {
		this.notificacionesTurnos = notificacionesTurnos;
	}

	public boolean isNotificacionesContencion() {
		return notificacionesContencion;
	}

	public void setNotificacionesContencion(boolean notificacionesContencion) {
		this.notificacionesContencion = notificacionesContencion;
	}

	public boolean isNotificacionesReclamosAgentes() {
		return notificacionesReclamosAgentes;
	}

	public void setNotificacionesReclamosAgentes(
			boolean notificacionesReclamosAgentes) {
		this.notificacionesReclamosAgentes = notificacionesReclamosAgentes;
	}

	public boolean isNotificacionesReclamosFabrica() {
		return notificacionesReclamosFabrica;
	}

	public void setNotificacionesReclamosFabrica(
			boolean notificacionesReclamosFabrica) {
		this.notificacionesReclamosFabrica = notificacionesReclamosFabrica;
	}

	public boolean isNotificacionesSugerencias() {
		return notificacionesSugerencias;
	}

	public void setNotificacionesSugerencias(boolean notificacionesSugerencias) {
		this.notificacionesSugerencias = notificacionesSugerencias;
	}

	public void matarThreads() {
		if (notificacionesLanzadas==null){
			notificacionesLanzadas = new Vector<MediadorEjecutarNotificacion>();
		}else{
			for (int i = 0; i<notificacionesLanzadas.size();i++){
				notificacionesLanzadas.elementAt(i).destruir();
			}
		}
	}

	public void verNotificacion(int row) {
		notificacionesLanzadas.elementAt(row).verGuiNotificacion();		
	}

	public void reportePiezasLlegadas() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarReportePiezasLlegadas();		
	}

	public void reportePiezasDevueltas() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarReportePiezasDevueltas();			
	}

	public void reportePiezasSinLlegar() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarReportePiezasSinLlegar();	
	}

	public void reportePiezasLlegadasSinTurno() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarPiezasLlegadasSinTurno();		
	}

	public void reporteSDPSinNP() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarSDPSinNP();		
	}

	public void reporteDiasDesdePedidoFabrica() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarDiasDesdePedidoFabrica();	
	}

	public void reporteDiasDesdeRecepcionFabrica() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarDiasDesdeRecepcionFabrica();
	}

	public void reporteDiasDesdeRecepcionFabricaYTurno() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarDiasDesdeRecepcionFabricaYTurno();	
	}

	public void reporteDiasDesdeCierreOrdenYTurno() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarDiasDesdeCierreOrdenYTurno();	
	}

	public void reporteDiasFechaRecursoYCierreOrden() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarDiasFechaRecursoYCierreOrden();	
	}

	public void reporteDiasFechaReclamoYFechaDevolucion() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarDiasFechaReclamoYFechaDevolucion();		
	}

	public void reporteDiasFechaReclamo_Turnos() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarDiasFechaReclamo_Turnos();	
	}

	public void reporteDiasPiezasLlegadas_PiezasDevueltas() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarDiasPiezasLlegadas_PiezasDevueltas();	
	}

	public void reporteManoDeObra() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarManoDeObra();	
	}

	public void reporteRecurso_CierreOrden() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarRecurso_CierreOrden();
	}

	public void reporteOrdenSinSDP() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.mostrarOrdenSinSDP();		
	}

	public void reporteRapido() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.reporteRapido();
	}

	public void reporteRapidoAgetes() {
		mediadorReporte = new MediadorReportes(this);
		mediadorReporte.reporteRapidoAgentes();	
	}
}
