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
package cliente.Reportes;

import java.sql.Date;
import java.util.Vector;

import cliente.MediadorAccionesIniciarPrograma;
import cliente.MediadorPrincipal;
import common.Cuadruple;
import common.Triple;
import common.DTOs.AgenteDTO;
import common.DTOs.Orden_ReclamoDTO;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.ReclamoDTO;
import common.DTOs.RegistranteDTO;
import common.GestionarAgente.IControlAgente;
import common.GestionarEntidad.IControlEntidad;
import common.GestionarOrden_Reclamo.IControlOrden_Reclamo;
import common.GestionarPedido_Pieza.IControlPedido_Pieza;
import common.GestionarReclamo.IControlReclamo;

public class MediadorReportes {
	
	private MediadorPrincipal mediadorPrincipal;

	private GUIReportesControl guiReporteControl;
	private GUIReportesGestion guiReporteGestion;
	private GUIReportePiezasLlegadas guiReportePiezasLlegadas;
	private GUIReportePiezasDevueltas guiReportePiezasDevueltas;
	private GUIReportePiezasSinLlegar guiReportePiezasSinLlegar;
	private GUIReportePiezasLlegadasSinTurno guiReportePiezasLlegadasSinTurno;
	private GUIReporteSDPSinNP guiReporteSDPSinNP;
	private GUIReporteDiasDesdePedidoFabrica guiReporteDiasDesdePedidoFabrica; 
	private GUIReporteDiasDesdeRecepcionFabrica guiReporteDiasDesdeRecepcionFabrica;
	private GUIReporteDiasDesdeRecepcionFabricaYTurno guiReporteDiasDesdeRecepcionFabricaYTurno;
	private GUIReporteDiasDesdeCierreOrdenYTurno guiReporteDiasDesdeCierreOrdenYTurno;
	private GUIReporteDiasFechaRecursoYCierreOrden guiReporteDiasFechaRecursoYCierreOrden;
	private GUIReporteDiasFechaReclamoYFechaDevolucion guiReporteDiasFechaReclamoYFechaDevolucion;
	private GUIReporteDiasFechaReclamo_Turnos guiReporteDiasFechaReclamo_Turnos;
	private GUIReporteDiasPiezasLlegadas_PiezasDevueltas guiReporteDiasPiezasLlegadas_PiezasDevueltas;
	private GUIReporteManoDeObra guiReporteManoDeObra;
	private GUIReporteRecurso_CierreOrden guiReporteRecurso_CierreOrden;
	private GUIReporteOrdenSinSDP guiReporteOrdenSinSDP;
	private GUIReporteRapidoEntidad guiReporteRapido;
	private GUIReporteRapidoAgente guiReporteRapidoAgentes;
	
	public MediadorReportes(MediadorPrincipal mediadorPrincipal) {
		this.setMediadorPrincipal(mediadorPrincipal);
	}
	
	public void mostrarReportesControl(){
		guiReporteControl = new GUIReportesControl(this);
		guiReporteControl.setVisible(true);
	}

	public void mostrarReportesGestion(){
		guiReporteGestion = new GUIReportesGestion(this);
		guiReporteGestion.setVisible(true);
	}

	public void mostrarReportePiezasLlegadas() {
		guiReportePiezasLlegadas = new GUIReportePiezasLlegadas(this);
		guiReportePiezasLlegadas.setVisible(true);		
	}

	public void mostrarReportePiezasDevueltas() {
		guiReportePiezasDevueltas = new GUIReportePiezasDevueltas(this);
		guiReportePiezasDevueltas.setVisible(true);			
	}

	public void mostrarReportePiezasSinLlegar() {
		guiReportePiezasSinLlegar = new GUIReportePiezasSinLlegar(this);
		guiReportePiezasSinLlegar.setVisible(true);		
	}

	public void mostrarPiezasLlegadasSinTurno() {
		guiReportePiezasLlegadasSinTurno = new GUIReportePiezasLlegadasSinTurno(this);
		guiReportePiezasLlegadasSinTurno.setVisible(true);	
	}

	public void mostrarOrdenSinSDP() {
		guiReporteOrdenSinSDP = new GUIReporteOrdenSinSDP(this);
		guiReporteOrdenSinSDP.setVisible(true);	
	}
	
	public void mostrarSDPSinNP() {
		guiReporteSDPSinNP = new GUIReporteSDPSinNP(this);
		guiReporteSDPSinNP.setVisible(true);	
	}

	public void mostrarDiasDesdePedidoFabrica() {
		guiReporteDiasDesdePedidoFabrica = new GUIReporteDiasDesdePedidoFabrica(this);
		guiReporteDiasDesdePedidoFabrica.setVisible(true);	
	}

	public void mostrarDiasDesdeRecepcionFabrica() {
		guiReporteDiasDesdeRecepcionFabrica = new GUIReporteDiasDesdeRecepcionFabrica(this);
		guiReporteDiasDesdeRecepcionFabrica.setVisible(true);		
	}

	public void mostrarDiasDesdeRecepcionFabricaYTurno() {
		guiReporteDiasDesdeRecepcionFabricaYTurno = new GUIReporteDiasDesdeRecepcionFabricaYTurno(this);
		guiReporteDiasDesdeRecepcionFabricaYTurno.setVisible(true);	
	}

	public void mostrarDiasDesdeCierreOrdenYTurno() {
		guiReporteDiasDesdeCierreOrdenYTurno = new GUIReporteDiasDesdeCierreOrdenYTurno(this);
		guiReporteDiasDesdeCierreOrdenYTurno.setVisible(true);	
	}

	public void mostrarDiasFechaRecursoYCierreOrden() {
		guiReporteDiasFechaRecursoYCierreOrden = new GUIReporteDiasFechaRecursoYCierreOrden(this);
		guiReporteDiasFechaRecursoYCierreOrden.setVisible(true);	
	}

	public void mostrarDiasFechaReclamoYFechaDevolucion() {
		guiReporteDiasFechaReclamoYFechaDevolucion = new GUIReporteDiasFechaReclamoYFechaDevolucion(this);
		guiReporteDiasFechaReclamoYFechaDevolucion.setVisible(true);	
	}

	public void mostrarDiasFechaReclamo_Turnos() {
		guiReporteDiasFechaReclamo_Turnos = new GUIReporteDiasFechaReclamo_Turnos(this);
		guiReporteDiasFechaReclamo_Turnos.setVisible(true);	
	}

	public void mostrarDiasPiezasLlegadas_PiezasDevueltas() {
		guiReporteDiasPiezasLlegadas_PiezasDevueltas = new GUIReporteDiasPiezasLlegadas_PiezasDevueltas(this);
		guiReporteDiasPiezasLlegadas_PiezasDevueltas.setVisible(true);	
	}

	public void mostrarManoDeObra() {
		guiReporteManoDeObra = new GUIReporteManoDeObra(this);
		guiReporteManoDeObra.setVisible(true);	
	}

	public void mostrarRecurso_CierreOrden() {
		guiReporteRecurso_CierreOrden = new GUIReporteRecurso_CierreOrden(this);
		guiReporteRecurso_CierreOrden.setVisible(true);	
	}

	
	public MediadorPrincipal getMediadorPrincipal() {
		return mediadorPrincipal;
	}

	public void setMediadorPrincipal(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}

	public Vector<Pedido_PiezaDTO> obtenerPedido_Piezas() {
		Vector<Pedido_PiezaDTO> result = new Vector<Pedido_PiezaDTO>();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		try {
			result = iControlPedido_Pieza.obtenerPedido_Pieza();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Vector<Pedido_PiezaDTO> obtenerPiezasLlegadas() {
		Vector<Pedido_PiezaDTO> result = new Vector<Pedido_PiezaDTO>();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		try {
			result = iControlPedido_Pieza.obtenerPiezasLlegadas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Vector<Pedido_PiezaDTO> obtenerPiezasDevueltas() {
		Vector<Pedido_PiezaDTO> result = new Vector<Pedido_PiezaDTO>();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		try {
			result = iControlPedido_Pieza.obtenerPiezasDevueltas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Vector<Pedido_PiezaDTO> obtenerPiezasSinLlegar() {
		Vector<Pedido_PiezaDTO> result = new Vector<Pedido_PiezaDTO>();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		try {
			result = iControlPedido_Pieza.obtenerPiezasSinLlegar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Vector<Pedido_PiezaDTO> obtenerPiezasLlegadasSinTurno() {
		Vector<Pedido_PiezaDTO> result = new Vector<Pedido_PiezaDTO>();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		try {
			result = iControlPedido_Pieza.obtenerPiezasLlegadasSinTurno();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Vector<ReclamoDTO> obtenerOrdenSinSDP() {
		Vector<ReclamoDTO> result = new Vector<ReclamoDTO>();
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		try {
			result = iControlReclamo.obtenerOrdenSinSDP();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Vector<Pedido_PiezaDTO> obtenerSDPSinNP() {
		Vector<Pedido_PiezaDTO> result = new Vector<Pedido_PiezaDTO>();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		try {
			result = iControlPedido_Pieza.obtenerSDPSinNP();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean esEntidad(RegistranteDTO registrante) {
		boolean res = false;
		try {
			IControlEntidad iControlEntidad = MediadorAccionesIniciarPrograma.getControlEntidad();
			res = iControlEntidad.existeEntidad(registrante.getNombre_registrante());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean esAgente(RegistranteDTO registrante) {
		boolean res = false;
		try {
			IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
			res = iControlAgente.existeAgente(registrante.getNombre_registrante());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Vector<ReclamoDTO> obtenerReclamos() {
		Vector<ReclamoDTO> result = new Vector<ReclamoDTO>();
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		try {
			result = iControlReclamo.obtenerReclamos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Vector<Orden_ReclamoDTO> obtenerOrdenes() {
		Vector<Orden_ReclamoDTO> result = new Vector<Orden_ReclamoDTO>();
		IControlOrden_Reclamo iControlOrden_Reclamo = MediadorAccionesIniciarPrograma.getControlOrden_Reclamo();
		try {
			result = iControlOrden_Reclamo.obtenerOrdenes_Reclamos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void reporteRapido() {
		guiReporteRapido = new GUIReporteRapidoEntidad(this);
		guiReporteRapido.setVisible(true);
	}

	/*---------------------------------------------------------------*/
	public String cantidad_pedidas_a_fabrica_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		try {
			Long cantidad = iControlPedido_Pieza.piezas_pedidas_a_fabrica_entidad(desde,hasta);
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public Triple<Double,Double,Double> anticuacion_pedidas_a_fabrica_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			return iControlPedido_Pieza.anticuacion_pedidas_a_fabrica_entidad(desde, hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}

	public Cuadruple<Double,Double,Double,Double> monto_pedidas_a_fabrica_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			return iControlPedido_Pieza.monto_pedidas_a_fabrica_entidad(desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}
	
	public String cantidad_pedida_a_fabrica_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			Long cantidad = iControlPedido_Pieza.cantidad_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null");
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public Triple<Double,Double,Double> anticuacion_pedidas_a_fabrica_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.anticuacion_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}
	
	public Cuadruple<Double,Double,Double,Double> monto_pedidas_a_fabrica_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.monto_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}
	
	/*---------------------------------------------------------------*/
	
	public String cantidad_en_transito_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		try {
			Long cantidad = iControlPedido_Pieza.piezas_en_transito(desde,hasta);
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public Triple<Double,Double,Double> anticuacion_en_transito_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			return iControlPedido_Pieza.anticuacion_en_transito(desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}

	public Cuadruple<Double,Double,Double,Double> monto_en_transito_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			return iControlPedido_Pieza.monto_en_transito(desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}
	
	public String cantidad_en_transito_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			Long cantidad = iControlPedido_Pieza.cantidad_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica==null");
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public Triple<Double,Double,Double> anticuacion_en_transito_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.anticuacion_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica==null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}
	
	public Cuadruple<Double,Double,Double,Double> monto_en_transito_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.monto_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica==null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}

	/*---------------------------------------------------------------*/
	
	public String cantidad_sin_turno(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		try {
			Long cantidad = iControlPedido_Pieza.piezas_sin_turno(desde,hasta);
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public Triple<Double,Double,Double> anticuacion_sin_turno(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			return iControlPedido_Pieza.anticuacion_sin_turno(desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}

	public Cuadruple<Double,Double,Double,Double> monto_sin_turno(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			return iControlPedido_Pieza.monto_sin_turno(desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}
	
	/*---------------------------------------------------------------*/
	
	public String cantidad_sin_enviar_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			Long cantidad = iControlPedido_Pieza.cantidad_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente==null");
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public Triple<Double,Double,Double> anticuacion_sin_enviar_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.anticuacion_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente==null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}
	
	public Cuadruple<Double,Double,Double,Double> monto_sin_enviar_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.monto_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente==null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}
	
	/*---------------------------------------------------------------*/
	
	public String cantidad_con_turno(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		try {
			Long cantidad = iControlPedido_Pieza.piezas_con_turno(desde,hasta);
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public Triple<Double,Double,Double> anticuacion_con_turno(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			return iControlPedido_Pieza.anticuacion_con_turno(desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}

	public Cuadruple<Double,Double,Double,Double> monto_con_turno(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			return iControlPedido_Pieza.monto_con_turno(desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}
	
	/*---------------------------------------------------------------*/
	
	public String cantidad_enviado_a_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			Long cantidad = iControlPedido_Pieza.cantidad_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente==null");
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public Triple<Double,Double,Double> anticuacion_enviado_a_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.anticuacion_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente==null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}
	
	public Cuadruple<Double,Double,Double,Double> monto_enviado_a_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.monto_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente==null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}
	
	/*---------------------------------------------------------------*/
		
	public String cantidad_cambiadas(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		try {
			Long cantidad = iControlPedido_Pieza.piezas_cambiadas(desde,hasta);
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public Triple<Double,Double,Double> anticuacion_cambiadas(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			return iControlPedido_Pieza.anticuacion_cambiadas(desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}

	public Cuadruple<Double,Double,Double,Double> monto_cambiadas(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			return iControlPedido_Pieza.monto_cambiadas(desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}
	
	/*---------------------------------------------------------------*/
	
	public String cantidad_recibidas_de_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			Long cantidad = iControlPedido_Pieza.cantidad_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso==null");
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public Triple<Double,Double,Double> anticuacion_recibidas_de_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.anticuacion_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso==null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}
	
	public Cuadruple<Double,Double,Double,Double> monto_recibidas_de_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.monto_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso==null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}
	
	/*---------------------------------------------------------------*/
	
	public String cantidad_recursadas_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		try {
			Long cantidad = iControlPedido_Pieza.piezas_recursadas(desde,hasta);
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public Triple<Double,Double,Double> anticuacion_recursadas_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			return iControlPedido_Pieza.anticuacion_recursadas(desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}

	public Cuadruple<Double,Double,Double,Double> monto_recursadas_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			return iControlPedido_Pieza.monto_recursadas(desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}
	
	public String cantidad_recursadas_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			Long cantidad = iControlPedido_Pieza.cantidad_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion==null");
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public Triple<Double,Double,Double> anticuacion_recursadas_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.anticuacion_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion==null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}
	
	public Cuadruple<Double,Double,Double,Double> monto_recursadas_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.monto_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion==null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}
	
	/*---------------------------------------------------------------*/
	
	public String cantidad_con_sdevolucion_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		try {
			Long cantidad = iControlPedido_Pieza.piezas_con_sdevolucion(desde,hasta);
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public Triple<Double,Double,Double> anticuacion_con_sdevolucion_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			return iControlPedido_Pieza.anticuacion_con_sdevolucion(desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}

	public Cuadruple<Double,Double,Double,Double> monto_con_sdevolucion_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			return iControlPedido_Pieza.monto_con_sdevolucion(desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}
	
	public String cantidad_con_sdevolucion_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			Long cantidad = iControlPedido_Pieza.cantidad_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion==null");
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public Triple<Double,Double,Double> anticuacion_con_sdevolucion_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.anticuacion_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion==null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}
	
	public Cuadruple<Double,Double,Double,Double> monto_con_sdevolucion_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.monto_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion==null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}
	
	/*---------------------------------------------------------------*/
	
	public String cantidad_aprobada_devolucion_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		try {
			Long cantidad = iControlPedido_Pieza.piezas_aprobada_devolucion(desde,hasta);
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public Triple<Double,Double,Double> anticuacion_aprobada_devolucion_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			return iControlPedido_Pieza.anticuacion_aprobada_devolucion(desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}

	public Cuadruple<Double,Double,Double,Double> monto_aprobada_devolucion_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			return iControlPedido_Pieza.monto_aprobada_devolucion(desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}
	
	public String cantidad_aprobada_devolucion_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			Long cantidad = iControlPedido_Pieza.cantidad_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && devolucion_pieza==null");
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public Triple<Double,Double,Double> anticuacion_aprobada_devolucion_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.anticuacion_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && devolucion_pieza==null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}
	
	public Cuadruple<Double,Double,Double,Double> monto_aprobada_devolucion_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.monto_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && devolucion_pieza==null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}
	
	/*---------------------------------------------------------------*/
	
	public String cantidad_devueltas_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		try {
			Long cantidad = iControlPedido_Pieza.piezas_devueltas(desde,hasta);
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public Triple<Double,Double,Double> anticuacion_devueltas_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			return iControlPedido_Pieza.anticuacion_devueltas(desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}

	public Cuadruple<Double,Double,Double,Double> monto_devueltas_entidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			return iControlPedido_Pieza.monto_devueltas(desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}
	
	public String cantidad_devueltas_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			Long cantidad = iControlPedido_Pieza.cantidad_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && devolucion_pieza!=null");
			return cantidad.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public Triple<Double,Double,Double> anticuacion_devueltas_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();		
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Triple<Double,Double,Double> anticuacion = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.anticuacion_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && devolucion_pieza!=null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anticuacion;
	}
	
	public Cuadruple<Double,Double,Double,Double> monto_devueltas_agente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Cuadruple<Double,Double,Double,Double> monto = null;
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			return iControlPedido_Pieza.monto_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && devolucion_pieza!=null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}

	/*---------------------------------------------------------------*/
	
	public void verCasosPedidas_a_FabricaEntidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_pedidas_a_fabrica_entidad(desde, hasta);
			GUIVerCasosEntidad guiVerCasos = new GUIVerCasosEntidad(this, pedidos_piezas, "STOCK PEDIDO A FABRICA", desde, hasta);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verCasosPedidas_a_FabricaAgente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null");
			GUIVerCasosAgente guiVerCasos = new GUIVerCasosAgente(this, pedidos_piezas, "STOCK PEDIDO A FABRICA", desde, hasta,nombre_agente);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verCasosEnTransitoEntidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_en_transito(desde, hasta);
			GUIVerCasosEntidad guiVerCasos = new GUIVerCasosEntidad(this, pedidos_piezas, "STOCK EN TRANSITO", desde, hasta);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verCasosEnTransitoAgente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica==null");
			GUIVerCasosAgente guiVerCasos = new GUIVerCasosAgente(this, pedidos_piezas, "STOCK EN TRANSITO", desde, hasta,nombre_agente);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verCasosSinTurnoEntidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_sin_turno(desde, hasta);
			GUIVerCasosEntidad guiVerCasos = new GUIVerCasosEntidad(this, pedidos_piezas, "STOCK SIN TURNO", desde, hasta);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verCasosSinEnviarAgente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente==null");
			GUIVerCasosAgente guiVerCasos = new GUIVerCasosAgente(this, pedidos_piezas, "STOCK SIN ENVIAR AGENTE", desde, hasta,nombre_agente);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verCasosConTurnoEntidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_con_turno(desde, hasta);
			GUIVerCasosEntidad guiVerCasos = new GUIVerCasosEntidad(this, pedidos_piezas, "STOCK CON TURNO", desde, hasta);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verCasosEnvidasAgente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente==null");
			GUIVerCasosAgente guiVerCasos = new GUIVerCasosAgente(this, pedidos_piezas, "STOCK ENVIADO A AGENTE/S", desde, hasta,nombre_agente);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void verCasosCambiadasEntidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_cambiadas(desde, hasta);
			GUIVerCasosEntidad guiVerCasos = new GUIVerCasosEntidad(this, pedidos_piezas, "STOCK CAMBIADO", desde, hasta);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verCasosRecibidasDeAgente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso==null");
			GUIVerCasosAgente guiVerCasos = new GUIVerCasosAgente(this, pedidos_piezas, "STOCK RECIBIDO DE AGENTE/S", desde, hasta,nombre_agente);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verCasosRecursadasEntidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_recursadas(desde, hasta);
			GUIVerCasosEntidad guiVerCasos = new GUIVerCasosEntidad(this, pedidos_piezas, "STOCK RECURSADO", desde, hasta);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verCasosRecursadasAgente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion==null");
			GUIVerCasosAgente guiVerCasos = new GUIVerCasosAgente(this, pedidos_piezas, "STOCK RECURSADO", desde, hasta,nombre_agente);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verCasosConSDevolucionEntidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_con_sdevolucion(desde, hasta);
			GUIVerCasosEntidad guiVerCasos = new GUIVerCasosEntidad(this, pedidos_piezas, "STOCK CON SOLICITUD DE DEVOLUCION", desde, hasta);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verCasosConSDevolucionAgente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion==null");
			GUIVerCasosAgente guiVerCasos = new GUIVerCasosAgente(this, pedidos_piezas, "STOCK CON SOLICITUD DE DEVOLUCION", desde, hasta,nombre_agente);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verCasosAprobDevolucionEntidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_aprobada_devolucion(desde, hasta);
			GUIVerCasosEntidad guiVerCasos = new GUIVerCasosEntidad(this, pedidos_piezas, "STOCK APROBADO PARA DEVOLUCION", desde, hasta);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verCasosAprobDevolucionAgente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && devolucion_pieza==null");
			GUIVerCasosAgente guiVerCasos = new GUIVerCasosAgente(this, pedidos_piezas, "STOCK APROBADO PARA DEVOLUCION", desde, hasta,nombre_agente);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verCasosDevueltasEntidad(Date desde, Date hasta) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_devueltas(desde, hasta);
			GUIVerCasosEntidad guiVerCasos = new GUIVerCasosEntidad(this, pedidos_piezas, "STOCK DEVUELTO", desde, hasta);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verCasosDevueltasAgente(Date desde, Date hasta, String nombre_agente) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();		
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			AgenteDTO agente = iControlAgente.buscarAgente(nombre_agente);
			pedidos_piezas = iControlPedido_Pieza.obtener_piezas_agente(desde, hasta, agente, "fecha_solicitud_fabrica!=null && fecha_recepcion_fabrica!=null && fecha_envio_agente!=null && fecha_recepcion_agente!=null && pedido.reclamo.orden.recurso.fecha_recurso!=null && fecha_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && fecha_aprobacion_solicitud_devolucion!=null && devolucion_pieza!=null");
			GUIVerCasosAgente guiVerCasos = new GUIVerCasosAgente(this, pedidos_piezas, "STOCK DEVUELTO", desde, hasta,nombre_agente);
			guiVerCasos.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reporteRapidoAgentes() {
		guiReporteRapidoAgentes = new GUIReporteRapidoAgente(this);
		guiReporteRapidoAgentes.setVisible(true);
	}

	public Vector<String> obtenerNombresAgentes() {
		Vector<AgenteDTO> agentesDTO = new Vector<AgenteDTO>();
		Vector<String> agentes = new Vector<String>();
		try {
			IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
			agentesDTO = iControlAgente.obtenerAgentes();
			for (int i=0;i<agentesDTO.size();i++){
				agentes.add(agentesDTO.elementAt(i).getNombre_registrante());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agentes;
	}

	public AgenteDTO buscarAgente(String nombre_agente) {
		AgenteDTO agente = null;
		try {
			IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
			agente = iControlAgente.buscarAgente(nombre_agente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agente;
	}
	
}
