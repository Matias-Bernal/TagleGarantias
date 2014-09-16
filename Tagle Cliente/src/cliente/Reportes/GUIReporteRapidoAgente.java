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

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import cliente.Recursos.Botones.ButtonType;
import cliente.Recursos.Botones.GlossyButton;
import cliente.Recursos.util.JPanel_Whit_Image;
import cliente.Recursos.util.Theme;
import cliente.Recursos.util.TransparentPanel;

import com.toedter.calendar.JDateChooser;

import common.Cuadruple;
import common.Triple;


public class GUIReporteRapidoAgente extends JFrame {

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private JPanel contentPane;
	
	private JDateChooser dCDesdeFReclamo;
	private JButton btnClearFReclamo;
	private JDateChooser dCHastaFDevolucion;
	private JButton btnClearFDevolucion;
	private JButton btnFiltrar;
	
	private Vector<Integer> anchos_tabla;
	private Vector<String> nombreColumnas;
	
	private JTable tablaPiezasConOT;
	private DefaultTableModel modelo_tabla_piezas_con_ot;
	private Vector<Vector<String>> datosTabla_piezas_con_ot;
	private JButton btnVerCasosPiezasConOT;
	private String cantidad_con_ot;
	private String prom_anticuacion_con_ot;
	private String max_anticuacion_con_ot;
	private String min_anticuacion_con_ot;
	private String monto_con_ot;
	private String prom_monto_con_ot;
	private String max_monto_con_ot;
	private String min_monto_con_ot;
	
	private JTable tablaPiezasEnTransito;
	private DefaultTableModel modelo_tabla_piezas_en_transito;
	private Vector<Vector<String>> datosTabla_piezas_en_transito;
	private JButton btnVerCasosPiezasEnTransito;
	private String cantidad_en_tranisto;
	private String prom_anticuacion_en_transito;
	private String max_anticuacion_en_transito;
	private String min_anticuacion_en_transito;
	private String monto_en_transito;
	private String prom_monto_en_transito;
	private String max_monto_en_transito;
	private String min_monto_en_transito;
	
	private JTable tablaPiezasSinEnviarAgentes;
	private DefaultTableModel modelo_tabla_piezas_sin_enviar_agentes;
	private Vector<Vector<String>> datosTabla_piezas_sin_enviar_agentes;
	private JButton btnVerCasosPiezasSinEnviarAgentes;
	private String cantidad_sin_enviar_agentes;
	private String prom_anticuacion_sin_enviar_agentes;
	private String max_anticuacion_sin_enviar_agentes;
	private String min_anticuacion_sin_enviar_agentes;
	private String monto_sin_enviar_agentes;
	private String prom_monto_sin_enviar_agentes;
	private String max_monto_sin_enviar_agentes;
	private String min_monto_sin_enviar_agentes;
	
	private JTable tablaPiezasEnviadasAgente;
	private DefaultTableModel modelo_tabla_piezas_enviadas_agentes;
	private Vector<Vector<String>> datosTabla_piezas_enviadas_agentes;
	private JButton btnVerCasosPiezasEnviadasAgentes;
	private String cantidad_enviadas_agentes;
	private String prom_anticuacion_enviadas_agentes;
	private String max_anticuacion_enviadas_agentes;
	private String min_anticuacion_enviadas_agentes;
	private String monto_enviadas_agentes;
	private String prom_monto_enviadas_agentes;
	private String max_monto_enviadas_agentes;
	private String min_monto_enviadas_agentes;
	
	private JTable tablaPiezasRecibidasAgentes;
	private DefaultTableModel modelo_tabla_recibiadas_agentes;
	private Vector<Vector<String>> datosTabla_piezas_recibiadas_agentes;
	private JButton btnVerCasosPiezasRecibidasAgentes;
	private String cantidad_recibiadas_agentes;
	private String prom_anticuacion_recibiadas_agentes;
	private String max_anticuacion_recibiadas_agentes;
	private String min_anticuacion_recibiadas_agentes;
	private String monto_recibiadas_agentes;
	private String prom_monto_recibiadas_agentes;
	private String max_monto_recibiadas_agentes;
	private String min_monto_recibiadas_agentes;
	
	private JTable tablaPiezasRecursadas;
	private DefaultTableModel modelo_tabla_piezas_recursadas;
	private Vector<Vector<String>> datosTabla_piezas_recursadas;
	private JButton btnVerCasosPiezasRecursadas;
	private String cantidad_recursadas;
	private String prom_anticuacion_recursadas;
	private String max_anticuacion_recursadas;
	private String min_anticuacion_recursadas;
	private String monto_recursadas;
	private String prom_monto_recursadas;
	private String max_monto_recursadas;
	private String min_monto_recursadas;
	
	private JTable tablaPiezasConSDevolucion;
	private DefaultTableModel modelo_tabla_piezas_con_sdevolucion;
	private Vector<Vector<String>> datosTabla_piezas_con_sdevolucion;
	private JButton btnVerCasosPiezasConSDevolucion;
	private String cantidad_con_sdevolucion;
	private String prom_anticuacion_con_sdevolucion;
	private String max_anticuacion_con_sdevolucion;
	private String min_anticuacion_con_sdevolucion;
	private String monto_con_sdevolucion;
	private String prom_monto_con_sdevolucion;
	private String max_monto_con_sdevolucion;
	private String min_monto_con_sdevolucion;

	private JTable tablaPiezasAprobDevolucion;
	private DefaultTableModel modelo_tabla_piezas_aprobacion_devolucion;
	private Vector<Vector<String>> datosTabla_piezas_aprobacion_devolucion;
	private JButton btnVerCasosPiezasAprobDevolucion;
	private String cantidad_aprobadas_devolucion;
	private String prom_anticuacion_aprobadas_devolucion;
	private String max_anticuacion_aprobadas_devolucion;
	private String min_anticuacion_aprobadas_devolucion;
	private String monto_aprobadas_devolucion;
	private String prom_monto_aprobadas_devolucion;
	private String max_monto_aprobadas_devolucion;
	private String min_monto_aprobadas_devolucion;

	private JTable tablaPiezasDevueltas;
	private DefaultTableModel modelo_tabla_piezas_devueltas;
	private Vector<Vector<String>> datosTabla_piezas_devueltas;
	private JButton btnVerCasosPiezasDevueltas;
	private String cantidad_devueltas;
	private String prom_anticuacion_devueltas;
	private String max_anticuacion_devueltas;
	private String min_anticuacion_devueltas;
	private String monto_devueltas;
	private String prom_monto_devueltas;
	private String max_monto_devueltas;
	private String min_monto_devueltas;
	
	private JButton btnVolver;
	private JPanel panelPiezasConOT;
	private JPanel panelPiezasEnviadasAgentes;
	private JPanel panelPiezasEnTransito;
	private JPanel panelPiezasConSDevolucion;
	private JPanel panelPiezasAprobDevolucion;
	private JPanel panelPiezasRecibidasAgentes;
	private JPanel panelPiezasSinTurno;
	private JPanel panelPiezasRecursadas;
	private JPanel panelPiezasDevueltas;
	private JComboBox<String> cBAgentes;
	private Vector<String> agentes;


	public GUIReporteRapidoAgente(MediadorReportes mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
		completarCampos();
	}

	@SuppressWarnings({ "static-access", "unused" })
	private void completarCampos() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		c.set(c.DAY_OF_MONTH,1);
		c.add(c.MONTH, -2);
		dCDesdeFReclamo.setDate(c.getTime());
		Calendar d = Calendar.getInstance();
		d.setFirstDayOfWeek(d.SUNDAY);
		d.set(d.DAY_OF_MONTH,d.getMaximum(d.DAY_OF_MONTH));
		dCHastaFDevolucion.setDate(d.getTime());
	}

	@SuppressWarnings({ "static-access", "unused" })
	private void cargarDatos() {
		agentes = new Vector<String>();
		agentes.add("");
		agentes.addAll(mediador.obtenerNombresAgentes());

		anchos_tabla = new Vector<Integer>();
		anchos_tabla.add(125);
		anchos_tabla.add(50);
		Vector<String> row_cantidad= new Vector<String> ();
		Vector<String> row_prom_anticuacion= new Vector<String> ();
		Vector<String> row_max_anticuacion= new Vector<String> ();
		Vector<String> row_min_anticuacion= new Vector<String> ();
		Vector<String> row_monto= new Vector<String> ();
		Vector<String> row_prom_monto= new Vector<String> ();
		Vector<String> row_max_monto= new Vector<String> ();
		Vector<String> row_min_monto= new Vector<String> ();
		nombreColumnas = new Vector<String> ();
		nombreColumnas.add("DATO");
		nombreColumnas.add("VALOR");
		
		DecimalFormat df_pesos = new DecimalFormat("0.00"); 
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar desdeCalendar = Calendar.getInstance();
		desdeCalendar.setFirstDayOfWeek(desdeCalendar.SUNDAY);
		desdeCalendar.add(desdeCalendar.MONTH, -2);
		desdeCalendar.set(desdeCalendar.DAY_OF_MONTH,1);
		java.sql.Date desde = new java.sql.Date(desdeCalendar.getTime().getTime());
		
		Calendar hastaCalendar = Calendar.getInstance();
		hastaCalendar.setFirstDayOfWeek(hastaCalendar.SUNDAY);
		hastaCalendar.set(hastaCalendar.DAY_OF_MONTH,hastaCalendar.getMaximum(hastaCalendar.DAY_OF_MONTH));		
		java.sql.Date hasta = new java.sql.Date(hastaCalendar.getTime().getTime());
							
		cantidad_con_ot =  mediador.cantidad_con_ot_agente(desde, hasta, "");
		Triple<Double,Double,Double> anticuacion_con_ot = mediador.anticuacion_con_ot_agente(desde, hasta, "");
		if(anticuacion_con_ot.first()!=null)
			prom_anticuacion_con_ot = anticuacion_con_ot.first().toString();
		else
			prom_anticuacion_con_ot = "";
		if(anticuacion_con_ot.second()!=null)
			max_anticuacion_con_ot = anticuacion_con_ot.second().toString();
		else
			max_anticuacion_con_ot = "";
		if(anticuacion_con_ot.third()!=null)
			min_anticuacion_con_ot = anticuacion_con_ot.third().toString();
		else
			min_anticuacion_con_ot = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_con_ot = mediador.monto_con_ot_agente(desde, hasta, "");
		if(_monto_con_ot.first()!=null)
			monto_con_ot = "$"+df_pesos.format(_monto_con_ot.first());
		else
			monto_con_ot = "";
		if(_monto_con_ot.second()!=null)
			prom_monto_con_ot = "$"+df_pesos.format(_monto_con_ot.second());
		else
			prom_monto_con_ot = "";
		if(_monto_con_ot.third()!=null)
			max_monto_con_ot = "$"+df_pesos.format(_monto_con_ot.third());
		else
			max_monto_con_ot = "";
		if(_monto_con_ot.fourth()!=null)
			min_monto_con_ot = "$"+df_pesos.format(_monto_con_ot.fourth());
		else
			min_monto_con_ot = "";
		
		cantidad_en_tranisto =  mediador.cantidad_en_transito_agente(desde, hasta, "");
		Triple<Double,Double,Double> anticuacion_en_transito = mediador.anticuacion_en_transito_agente(desde, hasta, "");
		if(anticuacion_en_transito.first()!=null)
			prom_anticuacion_en_transito = anticuacion_en_transito.first().toString();
		else
			prom_anticuacion_en_transito = "";
		if(anticuacion_en_transito.second()!=null)
			max_anticuacion_en_transito = anticuacion_en_transito.second().toString();
		else
			max_anticuacion_en_transito = "";
		if(anticuacion_en_transito.third()!=null)
			min_anticuacion_en_transito = anticuacion_en_transito.third().toString();
		else
			min_anticuacion_en_transito = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_en_transito = mediador.monto_en_transito_agente(desde, hasta, "");
		if(_monto_en_transito.first()!=null)
			monto_en_transito = "$"+df_pesos.format(_monto_en_transito.first());
		else
			monto_en_transito = "";
		if(_monto_en_transito.second()!=null)
			prom_monto_en_transito = "$"+df_pesos.format(_monto_en_transito.second());
		else
			prom_monto_en_transito = "";
		if(_monto_en_transito.third()!=null)
			max_monto_en_transito = "$"+df_pesos.format(_monto_en_transito.third());
		else
			max_monto_en_transito = "";
		if(_monto_en_transito.fourth()!=null)
			min_monto_en_transito = "$"+df_pesos.format(_monto_en_transito.fourth());
		else
			min_monto_en_transito = "";
		
		cantidad_sin_enviar_agentes =  mediador.cantidad_sin_enviar_agente(desde, hasta, "");
		Triple<Double,Double,Double> anticuacion_sin_enviar_agentes = mediador.anticuacion_sin_enviar_agente(desde, hasta, "");
		if(anticuacion_sin_enviar_agentes.first()!=null)
			prom_anticuacion_sin_enviar_agentes = anticuacion_sin_enviar_agentes.first().toString();
		else
			prom_anticuacion_sin_enviar_agentes = "";
		if(anticuacion_sin_enviar_agentes.second()!=null)
			max_anticuacion_sin_enviar_agentes = anticuacion_sin_enviar_agentes.second().toString();
		else
			max_anticuacion_sin_enviar_agentes = "";
		if(anticuacion_sin_enviar_agentes.third()!=null)
			min_anticuacion_sin_enviar_agentes = anticuacion_sin_enviar_agentes.third().toString();
		else
			min_anticuacion_sin_enviar_agentes = "";
		
		Cuadruple<Double,Double,Double,Double> monto_sin_enviar_agentes_ = mediador.monto_sin_enviar_agente(desde, hasta, "");
		if(monto_sin_enviar_agentes_.first()!=null)
			monto_sin_enviar_agentes = "$"+df_pesos.format(monto_sin_enviar_agentes_.first());
		else
			monto_sin_enviar_agentes = "";
		if(monto_sin_enviar_agentes_.second()!=null)
			prom_monto_sin_enviar_agentes = "$"+df_pesos.format(monto_sin_enviar_agentes_.second());
		else
			prom_monto_sin_enviar_agentes = "";
		if(monto_sin_enviar_agentes_.third()!=null)
			max_monto_sin_enviar_agentes = "$"+df_pesos.format(monto_sin_enviar_agentes_.third());
		else
			max_monto_sin_enviar_agentes = "";
		if(monto_sin_enviar_agentes_.fourth()!=null)
			min_monto_sin_enviar_agentes = "$"+df_pesos.format(monto_sin_enviar_agentes_.fourth());
		else
			min_monto_sin_enviar_agentes = "";
		
		cantidad_enviadas_agentes =  mediador.cantidad_enviado_a_agente(desde, hasta, "");
		Triple<Double,Double,Double> anticuacion_enviado_a_agente = mediador.anticuacion_enviado_a_agente(desde, hasta, "");
		if(anticuacion_enviado_a_agente.first()!=null)
			prom_anticuacion_enviadas_agentes = anticuacion_enviado_a_agente.first().toString();
		else
			prom_anticuacion_enviadas_agentes = "";
		if(anticuacion_enviado_a_agente.second()!=null)
			max_anticuacion_enviadas_agentes = anticuacion_enviado_a_agente.second().toString();
		else
			max_anticuacion_enviadas_agentes = "";
		if(anticuacion_enviado_a_agente.third()!=null)
			min_anticuacion_enviadas_agentes = anticuacion_enviado_a_agente.third().toString();
		else
			min_anticuacion_enviadas_agentes = "";
		
		Cuadruple<Double,Double,Double,Double> monto_enviadas_a_agente_ = mediador.monto_enviado_a_agente(desde, hasta, "");
		if(monto_enviadas_a_agente_.first()!=null)
			monto_enviadas_agentes = "$"+df_pesos.format(monto_enviadas_a_agente_.first());
		else
			monto_enviadas_agentes = "";
		if(monto_enviadas_a_agente_.second()!=null)
			prom_monto_enviadas_agentes = "$"+df_pesos.format(monto_enviadas_a_agente_.second());
		else
			prom_monto_enviadas_agentes = "";
		if(monto_enviadas_a_agente_.third()!=null)
			max_monto_enviadas_agentes = "$"+df_pesos.format(monto_enviadas_a_agente_.third());
		else
			max_monto_enviadas_agentes = "";
		if(monto_enviadas_a_agente_.fourth()!=null)
			min_monto_enviadas_agentes = "$"+df_pesos.format(monto_enviadas_a_agente_.fourth());
		else
			min_monto_enviadas_agentes = "";
		
		cantidad_recibiadas_agentes =  mediador.cantidad_recibidas_de_agente(desde, hasta, "");
		Triple<Double,Double,Double> anticuacion_recibidas_de_agente = mediador.anticuacion_recibidas_de_agente(desde, hasta, "");
		if(anticuacion_recibidas_de_agente.first()!=null)
			prom_anticuacion_recibiadas_agentes = anticuacion_recibidas_de_agente.first().toString();
		else
			prom_anticuacion_recibiadas_agentes = "";
		if(anticuacion_recibidas_de_agente.second()!=null)
			max_anticuacion_recibiadas_agentes = anticuacion_recibidas_de_agente.second().toString();
		else
			max_anticuacion_recibiadas_agentes = "";
		if(anticuacion_recibidas_de_agente.third()!=null)
			min_anticuacion_recibiadas_agentes = anticuacion_recibidas_de_agente.third().toString();
		else
			min_anticuacion_recibiadas_agentes = "";
		
		Cuadruple<Double,Double,Double,Double> monto_recibidas_de_agente_ = mediador.monto_recibidas_de_agente(desde, hasta, "");
		if(monto_recibidas_de_agente_.first()!=null)
			monto_recibiadas_agentes = "$"+df_pesos.format(monto_recibidas_de_agente_.first());
		else
			monto_recibiadas_agentes = "";
		if(monto_recibidas_de_agente_.second()!=null)
			prom_monto_recibiadas_agentes = "$"+df_pesos.format(monto_recibidas_de_agente_.second());
		else
			prom_monto_recibiadas_agentes = "";
		if(monto_recibidas_de_agente_.third()!=null)
			max_monto_recibiadas_agentes = "$"+df_pesos.format(monto_recibidas_de_agente_.third());
		else
			max_monto_recibiadas_agentes = "";
		if(monto_recibidas_de_agente_.fourth()!=null)
			min_monto_recibiadas_agentes = "$"+df_pesos.format(monto_recibidas_de_agente_.fourth());
		else
			min_monto_recibiadas_agentes = "";
		
		cantidad_recursadas =  mediador.cantidad_recursadas_agente(desde, hasta, "");
		Triple<Double,Double,Double> anticuacion_recursadas = mediador.anticuacion_recursadas_agente(desde, hasta, "");
		if(anticuacion_recursadas.first()!=null)
			prom_anticuacion_recursadas = anticuacion_recursadas.first().toString();
		else
			prom_anticuacion_recursadas = "";
		if(anticuacion_recursadas.second()!=null)
			max_anticuacion_recursadas = anticuacion_recursadas.second().toString();
		else
			max_anticuacion_recursadas = "";
		if(anticuacion_recursadas.third()!=null)
			min_anticuacion_recursadas = anticuacion_recursadas.third().toString();
		else
			min_anticuacion_recursadas = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_recursadas = mediador.monto_recursadas_agente(desde, hasta, "");
		if(_monto_recursadas.first()!=null)
			monto_recursadas = "$"+df_pesos.format(_monto_recursadas.first());
		else
			monto_recursadas = "";
		if(_monto_recursadas.second()!=null)
			prom_monto_recursadas = "$"+df_pesos.format(_monto_recursadas.second());
		else
			prom_monto_recursadas = "";
		if(_monto_recursadas.third()!=null)
			max_monto_recursadas = "$"+df_pesos.format(_monto_recursadas.third());
		else
			max_monto_recursadas = "";
		if(_monto_recursadas.fourth()!=null)
			min_monto_recursadas = "$"+df_pesos.format(_monto_recursadas.fourth());
		else
			min_monto_recursadas = "";
		
		cantidad_con_sdevolucion =  mediador.cantidad_con_sdevolucion_agente(desde, hasta, "");
		Triple<Double,Double,Double> anticuacion_con_sdevolucion = mediador.anticuacion_con_sdevolucion_agente(desde, hasta, "");
		if(anticuacion_con_sdevolucion.first()!=null)
			prom_anticuacion_con_sdevolucion = anticuacion_con_sdevolucion.first().toString();
		else
			prom_anticuacion_con_sdevolucion = "";
		if(anticuacion_con_sdevolucion.second()!=null)
			max_anticuacion_con_sdevolucion = anticuacion_con_sdevolucion.second().toString();
		else
			max_anticuacion_con_sdevolucion = "";
		if(anticuacion_con_sdevolucion.third()!=null)
			min_anticuacion_con_sdevolucion = anticuacion_con_sdevolucion.third().toString();
		else
			min_anticuacion_con_sdevolucion = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_con_sdevolucion = mediador.monto_con_sdevolucion_agente(desde, hasta, "");
		if(_monto_con_sdevolucion.first()!=null)
			monto_con_sdevolucion = "$"+df_pesos.format(_monto_con_sdevolucion.first());
		else
			monto_con_sdevolucion = "";
		if(_monto_con_sdevolucion.second()!=null)
			prom_monto_con_sdevolucion = "$"+df_pesos.format(_monto_con_sdevolucion.second());
		else
			prom_monto_con_sdevolucion = "";
		if(_monto_con_sdevolucion.third()!=null)
			max_monto_con_sdevolucion = "$"+df_pesos.format(_monto_con_sdevolucion.third());
		else
			max_monto_con_sdevolucion = "";
		if(_monto_con_sdevolucion.fourth()!=null)
			min_monto_con_sdevolucion = "$"+df_pesos.format(_monto_con_sdevolucion.fourth());
		else
			min_monto_con_sdevolucion = "";
		
		cantidad_aprobadas_devolucion =  mediador.cantidad_aprobada_devolucion_agente(desde, hasta, "");
		Triple<Double,Double,Double> anticuacion_aprobadas_devolucion = mediador.anticuacion_aprobada_devolucion_agente(desde, hasta, "");
		if(anticuacion_aprobadas_devolucion.first()!=null)
			prom_anticuacion_aprobadas_devolucion = anticuacion_aprobadas_devolucion.first().toString();
		else
			prom_anticuacion_aprobadas_devolucion = "";
		if(anticuacion_aprobadas_devolucion.second()!=null)
			max_anticuacion_aprobadas_devolucion = anticuacion_aprobadas_devolucion.second().toString();
		else
			max_anticuacion_aprobadas_devolucion = "";
		if(anticuacion_aprobadas_devolucion.third()!=null)
			min_anticuacion_aprobadas_devolucion = anticuacion_aprobadas_devolucion.third().toString();
		else
			min_anticuacion_aprobadas_devolucion = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_aprobadas_devolucion = mediador.monto_aprobada_devolucion_agente(desde, hasta, "");
		if(_monto_aprobadas_devolucion.first()!=null)
			monto_aprobadas_devolucion = "$"+df_pesos.format(_monto_aprobadas_devolucion.first());
		else
			monto_aprobadas_devolucion = "";
		if(_monto_aprobadas_devolucion.second()!=null)
			prom_monto_aprobadas_devolucion = "$"+df_pesos.format(_monto_aprobadas_devolucion.second());
		else
			prom_monto_aprobadas_devolucion = "";
		if(_monto_aprobadas_devolucion.third()!=null)
			max_monto_aprobadas_devolucion = "$"+df_pesos.format(_monto_aprobadas_devolucion.third());
		else
			max_monto_aprobadas_devolucion = "";
		if(_monto_aprobadas_devolucion.fourth()!=null)
			min_monto_aprobadas_devolucion = "$"+df_pesos.format(_monto_aprobadas_devolucion.fourth());
		else
			min_monto_aprobadas_devolucion = "";
		
		cantidad_devueltas =  mediador.cantidad_devueltas_agente(desde, hasta, "");
		Triple<Double,Double,Double> anticuacion_devueltas = mediador.anticuacion_devueltas_agente(desde, hasta, "");
		if(anticuacion_devueltas.first()!=null)
			prom_anticuacion_devueltas = anticuacion_devueltas.first().toString();
		else
			prom_anticuacion_devueltas = "";
		if(anticuacion_devueltas.second()!=null)
			max_anticuacion_devueltas = anticuacion_devueltas.second().toString();
		else
			max_anticuacion_devueltas = "";
		if(anticuacion_devueltas.third()!=null)
			min_anticuacion_devueltas = anticuacion_devueltas.third().toString();
		else
			min_anticuacion_devueltas = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_devueltas = mediador.monto_devueltas_agente(desde, hasta, "");
		if(_monto_devueltas.first()!=null)
			monto_devueltas = "$"+df_pesos.format(_monto_devueltas.first());
		else
			monto_devueltas = "";
		if(_monto_devueltas.second()!=null)
			prom_monto_devueltas = "$"+df_pesos.format(_monto_devueltas.second());
		else
			prom_monto_devueltas = "";
		if(_monto_devueltas.third()!=null)
			max_monto_devueltas = "$"+df_pesos.format(_monto_devueltas.third());
		else
			max_monto_devueltas = "";
		if(_monto_devueltas.fourth()!=null)
			min_monto_devueltas = "$"+df_pesos.format(_monto_devueltas.fourth());
		else
			min_monto_devueltas = "";
//		Tabla Piezas Con OT		//
		modelo_tabla_piezas_con_ot = new DefaultTableModel();
		datosTabla_piezas_con_ot = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_con_ot);//Q
		datosTabla_piezas_con_ot.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_con_ot);//X
		datosTabla_piezas_con_ot.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_con_ot);//MX
		datosTabla_piezas_con_ot.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_con_ot);//mX
		datosTabla_piezas_con_ot.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_con_ot);//R
		datosTabla_piezas_con_ot.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_con_ot);//XR
		datosTabla_piezas_con_ot.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_con_ot);//MR
		datosTabla_piezas_con_ot.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_con_ot);//mR
		datosTabla_piezas_con_ot.add(row_min_monto);
		modelo_tabla_piezas_con_ot.setDataVector(datosTabla_piezas_con_ot, nombreColumnas);
		modelo_tabla_piezas_con_ot.fireTableStructureChanged();
		//		Fin Tabla Piezas Con OT		//
		//		Tabla Piezas En Transito		//
		modelo_tabla_piezas_en_transito = new DefaultTableModel();
		datosTabla_piezas_en_transito = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_en_tranisto);//Q
		datosTabla_piezas_en_transito.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_en_transito);//X
		datosTabla_piezas_en_transito.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_en_transito);//MX
		datosTabla_piezas_en_transito.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_en_transito);//mX
		datosTabla_piezas_en_transito.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_en_transito);//R
		datosTabla_piezas_en_transito.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_en_transito);//XR
		datosTabla_piezas_en_transito.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_en_transito);//MR
		datosTabla_piezas_en_transito.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_en_transito);//mR
		datosTabla_piezas_en_transito.add(row_min_monto);
		modelo_tabla_piezas_en_transito.setDataVector(datosTabla_piezas_en_transito, nombreColumnas);
		modelo_tabla_piezas_en_transito.fireTableStructureChanged();
		//		Fin Tabla Piezas En Transito	//
		//		Tabla Piezas Sin Turno		//
		modelo_tabla_piezas_sin_enviar_agentes = new DefaultTableModel();
		datosTabla_piezas_sin_enviar_agentes = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_sin_enviar_agentes);//Q
		datosTabla_piezas_sin_enviar_agentes.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_sin_enviar_agentes);//X
		datosTabla_piezas_sin_enviar_agentes.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_sin_enviar_agentes);//MX
		datosTabla_piezas_sin_enviar_agentes.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_sin_enviar_agentes);//mX
		datosTabla_piezas_sin_enviar_agentes.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_sin_enviar_agentes);//R
		datosTabla_piezas_sin_enviar_agentes.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_sin_enviar_agentes);//XR
		datosTabla_piezas_sin_enviar_agentes.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_sin_enviar_agentes);//MR
		datosTabla_piezas_sin_enviar_agentes.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_sin_enviar_agentes);//mR
		datosTabla_piezas_sin_enviar_agentes.add(row_min_monto);
		modelo_tabla_piezas_sin_enviar_agentes.setDataVector(datosTabla_piezas_sin_enviar_agentes, nombreColumnas);
		modelo_tabla_piezas_sin_enviar_agentes.fireTableStructureChanged();
		//		Fin Tabla Piezas Sin Enviar Agente	//
		//		Tabla Piezas Enviadas Agente		//
		modelo_tabla_piezas_enviadas_agentes = new DefaultTableModel();
		datosTabla_piezas_enviadas_agentes = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_enviadas_agentes);//Q
		datosTabla_piezas_enviadas_agentes.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_enviadas_agentes);//X
		datosTabla_piezas_enviadas_agentes.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_enviadas_agentes);//MX
		datosTabla_piezas_enviadas_agentes.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_enviadas_agentes);//mX
		datosTabla_piezas_enviadas_agentes.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_enviadas_agentes);//R
		datosTabla_piezas_enviadas_agentes.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_enviadas_agentes);//XR
		datosTabla_piezas_enviadas_agentes.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_enviadas_agentes);//MR
		datosTabla_piezas_enviadas_agentes.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_enviadas_agentes);//mR
		datosTabla_piezas_enviadas_agentes.add(row_min_monto);
		modelo_tabla_piezas_enviadas_agentes.setDataVector(datosTabla_piezas_enviadas_agentes, nombreColumnas);
		modelo_tabla_piezas_enviadas_agentes.fireTableStructureChanged();
		//		Fin Tabla Piezas Enviadas Agente			//
		//		Tabla Piezas Devueltas por Agentes			//
		modelo_tabla_recibiadas_agentes = new DefaultTableModel();
		datosTabla_piezas_recibiadas_agentes = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_recibiadas_agentes);//Q
		datosTabla_piezas_recibiadas_agentes.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_recibiadas_agentes);//X
		datosTabla_piezas_recibiadas_agentes.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_recibiadas_agentes);//MX
		datosTabla_piezas_recibiadas_agentes.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_recibiadas_agentes);//mX
		datosTabla_piezas_recibiadas_agentes.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_recibiadas_agentes);//R
		datosTabla_piezas_recibiadas_agentes.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_recibiadas_agentes);//XR
		datosTabla_piezas_recibiadas_agentes.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_recibiadas_agentes);//MR
		datosTabla_piezas_recibiadas_agentes.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_recibiadas_agentes);//mR
		datosTabla_piezas_recibiadas_agentes.add(row_min_monto);
		modelo_tabla_recibiadas_agentes.setDataVector(datosTabla_piezas_recibiadas_agentes, nombreColumnas);
		modelo_tabla_recibiadas_agentes.fireTableStructureChanged();
		//		Fin Tabla Piezas Cambiadas		//
		//		Tabla Piezas Recursadas		//
		modelo_tabla_piezas_recursadas = new DefaultTableModel();
		datosTabla_piezas_recursadas = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_recursadas);//Q
		datosTabla_piezas_recursadas.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_recursadas);//X
		datosTabla_piezas_recursadas.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_recursadas);//MX
		datosTabla_piezas_recursadas.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_recursadas);//mX
		datosTabla_piezas_recursadas.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_recursadas);//R
		datosTabla_piezas_recursadas.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_recursadas);//XR
		datosTabla_piezas_recursadas.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_recursadas);//MR
		datosTabla_piezas_recursadas.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_recursadas);//mR
		datosTabla_piezas_recursadas.add(row_min_monto);
		modelo_tabla_piezas_recursadas.setDataVector(datosTabla_piezas_recursadas, nombreColumnas);
		modelo_tabla_piezas_recursadas.fireTableStructureChanged();
		//		Fin Tabla Piezas Recursadas		//
		//		Tabla Piezas Con Solicitud Devolucion		//
		modelo_tabla_piezas_con_sdevolucion = new DefaultTableModel();
		datosTabla_piezas_con_sdevolucion = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_con_sdevolucion);//Q
		datosTabla_piezas_con_sdevolucion.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_con_sdevolucion);//X
		datosTabla_piezas_con_sdevolucion.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_con_sdevolucion);//MX
		datosTabla_piezas_con_sdevolucion.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_con_sdevolucion);//mX
		datosTabla_piezas_con_sdevolucion.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_con_sdevolucion);//R
		datosTabla_piezas_con_sdevolucion.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_con_sdevolucion);//XR
		datosTabla_piezas_con_sdevolucion.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_con_sdevolucion);//MR
		datosTabla_piezas_con_sdevolucion.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_con_sdevolucion);//mR
		datosTabla_piezas_con_sdevolucion.add(row_min_monto);
		modelo_tabla_piezas_con_sdevolucion.setDataVector(datosTabla_piezas_con_sdevolucion, nombreColumnas);
		modelo_tabla_piezas_con_sdevolucion.fireTableStructureChanged();
		//		Fin Tabla Piezas Con Solicitud Devolucion		//
		//		Tabla Piezas Aprobada Devolucion		//
		modelo_tabla_piezas_aprobacion_devolucion = new DefaultTableModel();
		datosTabla_piezas_aprobacion_devolucion = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_aprobadas_devolucion);//Q
		datosTabla_piezas_aprobacion_devolucion.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_aprobadas_devolucion);//X
		datosTabla_piezas_aprobacion_devolucion.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_aprobadas_devolucion);//MX
		datosTabla_piezas_aprobacion_devolucion.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_aprobadas_devolucion);//mX
		datosTabla_piezas_aprobacion_devolucion.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_aprobadas_devolucion);//R
		datosTabla_piezas_aprobacion_devolucion.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_aprobadas_devolucion);//XR
		datosTabla_piezas_aprobacion_devolucion.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_aprobadas_devolucion);//MR
		datosTabla_piezas_aprobacion_devolucion.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_aprobadas_devolucion);//mR
		datosTabla_piezas_aprobacion_devolucion.add(row_min_monto);
		modelo_tabla_piezas_aprobacion_devolucion.setDataVector(datosTabla_piezas_aprobacion_devolucion, nombreColumnas);
		modelo_tabla_piezas_aprobacion_devolucion.fireTableStructureChanged();
		//		Fin Tabla Piezas Aprobada Devolucion		//		
		//		Tabla Piezas Devueltas		//
		modelo_tabla_piezas_devueltas = new DefaultTableModel();
		datosTabla_piezas_devueltas = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_devueltas);//Q
		datosTabla_piezas_devueltas.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_devueltas);//X
		datosTabla_piezas_devueltas.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_devueltas);//MX
		datosTabla_piezas_devueltas.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_devueltas);//mX
		datosTabla_piezas_devueltas.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_devueltas);//R
		datosTabla_piezas_devueltas.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_devueltas);//XR
		datosTabla_piezas_devueltas.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_devueltas);//MR
		datosTabla_piezas_devueltas.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_devueltas);//mR
		datosTabla_piezas_devueltas.add(row_min_monto);
		modelo_tabla_piezas_devueltas.setDataVector(datosTabla_piezas_devueltas, nombreColumnas);
		modelo_tabla_piezas_devueltas.fireTableStructureChanged();
		//		Fin Tabla Piezas Aprobada Devolucion		//
	}
	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1300, 720);
		setTitle("Reporte Rapido Agente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReporteRapidoAgente.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelFechas = new TransparentPanel();
		panelFechas.setLayout(null);
		panelFechas.setBounds(257, 0, 780, 40);
		contentPane.add(panelFechas);
		
		JLabel lblDesdeFReclamo = new JLabel("Desde F. Reclamo");
		lblDesdeFReclamo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDesdeFReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesdeFReclamo.setBounds(10, 10, 130, 20);
		panelFechas.add(lblDesdeFReclamo);
		
		dCDesdeFReclamo = new JDateChooser();
		dCDesdeFReclamo.setBounds(140, 10, 150, 20);
		panelFechas.add(dCDesdeFReclamo);
		
		btnClearFReclamo = new JButton("");
		btnClearFReclamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dCDesdeFReclamo.getDate()!=null)
					dCDesdeFReclamo.setDate(null);
			}
		});
		btnClearFReclamo.setIcon(new ImageIcon(GUIReporteRapidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btnClearFReclamo.setBounds(300, 10, 25, 20);
		panelFechas.add(btnClearFReclamo);
		
		JLabel lblHastaFDevolucion = new JLabel("Hasta F. Devolucion");
		lblHastaFDevolucion.setHorizontalAlignment(SwingConstants.CENTER);
		lblHastaFDevolucion.setBounds(335, 10, 130, 20);
		panelFechas.add(lblHastaFDevolucion);
		
		dCHastaFDevolucion = new JDateChooser();
		dCHastaFDevolucion.setBounds(465, 10, 150, 20);
		panelFechas.add(dCHastaFDevolucion);
		
		btnClearFDevolucion = new JButton("");
		btnClearFDevolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dCHastaFDevolucion.getDate()!=null)
					dCHastaFDevolucion.setDate(null);
			}
		});
		btnClearFDevolucion.setIcon(new ImageIcon(GUIReporteRapidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btnClearFDevolucion.setBounds(625, 10, 25, 20);
		panelFechas.add(btnClearFDevolucion);
		
		btnFiltrar = new GlossyButton("FILTRAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnFiltrar.setText("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrar();
			}
		});
		btnFiltrar.setBounds(660, 10, 110, 20);
		panelFechas.add(btnFiltrar);
		
		JPanel panelReportes = new TransparentPanel();
		panelReportes.setLayout(null);
		panelReportes.setBounds(0, 40, 1294, 610);
		contentPane.add(panelReportes);
		
		panelPiezasConOT = new TransparentPanel();
		panelPiezasConOT.setLayout(null);
		panelPiezasConOT.setBounds(98, 35, 300, 180);
		panelReportes.add(panelPiezasConOT);
		
		JLabel lblFechaOt = new JLabel("Fecha O.T.");
		lblFechaOt.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFechaOt.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaOt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFechaOt.setBounds(0, 0, 100, 20);
		panelPiezasConOT.add(lblFechaOt);
		
		modelo_tabla_piezas_con_ot = new DefaultTableModel(datosTabla_piezas_con_ot, nombreColumnas);
		tablaPiezasConOT = new JTable(modelo_tabla_piezas_con_ot) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tablaPiezasConOT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPiezasConOT.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaPiezasConOT.setBounds(100, 20, 200, 130);
		panelPiezasConOT.add(tablaPiezasConOT);
		for(int i = 0; i < tablaPiezasConOT.getColumnCount(); i++) {
			tablaPiezasConOT.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasConOT.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		
		JLabel lblPiezasConOt_1 = new JLabel("Stock Con O.T.");
		lblPiezasConOt_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasConOt_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasConOt_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasConOt_1.setBounds(100, 0, 200, 20);
		panelPiezasConOT.add(lblPiezasConOt_1);
		
		btnVerCasosPiezasConOT = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasConOT.setText("Ver Casos");
		btnVerCasosPiezasConOT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosOT();
			}
		});
		btnVerCasosPiezasConOT.setBounds(100, 155, 200, 20);
		panelPiezasConOT.add(btnVerCasosPiezasConOT);
		
		panelPiezasEnTransito = new TransparentPanel();
		panelPiezasEnTransito.setLayout(null);
		panelPiezasEnTransito.setBounds(496, 35, 300, 180);
		panelReportes.add(panelPiezasEnTransito);
		
		JLabel lblFOt = new JLabel("F. Pedido Fabrica");
		lblFOt.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblFOt.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFOt.setHorizontalAlignment(SwingConstants.CENTER);
		lblFOt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFOt.setBounds(0, 0, 100, 20);
		panelPiezasEnTransito.add(lblFOt);
		
		modelo_tabla_piezas_en_transito = new DefaultTableModel(datosTabla_piezas_en_transito, nombreColumnas);
		tablaPiezasEnTransito = new JTable(modelo_tabla_piezas_en_transito) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		for(int i = 0; i < tablaPiezasEnTransito.getColumnCount(); i++) {
			tablaPiezasEnTransito.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasEnTransito.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		tablaPiezasEnTransito.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPiezasEnTransito.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaPiezasEnTransito.setBounds(100, 20, 200, 130);
		panelPiezasEnTransito.add(tablaPiezasEnTransito);
		
		JLabel lblPiezasConOt = new JLabel("Stock En Transito");
		lblPiezasConOt.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasConOt.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasConOt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasConOt.setBounds(100, 0, 200, 20);
		panelPiezasEnTransito.add(lblPiezasConOt);
		
		btnVerCasosPiezasEnTransito = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasEnTransito.setText("Ver Casos");
		btnVerCasosPiezasEnTransito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosEnTransito();
			}
		});
		btnVerCasosPiezasEnTransito.setBounds(100, 155, 200, 20);
		panelPiezasEnTransito.add(btnVerCasosPiezasEnTransito);
		
		panelPiezasSinTurno = new TransparentPanel();
		panelPiezasSinTurno.setLayout(null);
		panelPiezasSinTurno.setBounds(894, 35, 300, 180);
		panelReportes.add(panelPiezasSinTurno);
		
		JLabel lblFrecepcion = new JLabel("F. Recepcion Fabrica");
		lblFrecepcion.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblFrecepcion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFrecepcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrecepcion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFrecepcion.setBounds(0, 0, 100, 20);
		panelPiezasSinTurno.add(lblFrecepcion);
		
		modelo_tabla_piezas_sin_enviar_agentes = new DefaultTableModel(datosTabla_piezas_sin_enviar_agentes, nombreColumnas);
		tablaPiezasSinEnviarAgentes = new JTable(modelo_tabla_piezas_sin_enviar_agentes) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		for(int i = 0; i < tablaPiezasSinEnviarAgentes.getColumnCount(); i++) {
			tablaPiezasSinEnviarAgentes.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasSinEnviarAgentes.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		tablaPiezasSinEnviarAgentes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPiezasSinEnviarAgentes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaPiezasSinEnviarAgentes.setBounds(100, 20, 200, 130);
		panelPiezasSinTurno.add(tablaPiezasSinEnviarAgentes);
		
		JLabel lblPiezasSinEnviarAgentes = new JLabel("Stock Sin Enviar Agente/s");
		lblPiezasSinEnviarAgentes.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasSinEnviarAgentes.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasSinEnviarAgentes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasSinEnviarAgentes.setBounds(100, 0, 200, 20);
		panelPiezasSinTurno.add(lblPiezasSinEnviarAgentes);
		
		btnVerCasosPiezasSinEnviarAgentes = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasSinEnviarAgentes.setText("Ver Casos");
		btnVerCasosPiezasSinEnviarAgentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosSinEnviarAgente();
			}
		});
		btnVerCasosPiezasSinEnviarAgentes.setBounds(100, 155, 200, 20);
		panelPiezasSinTurno.add(btnVerCasosPiezasSinEnviarAgentes);
		
		panelPiezasEnviadasAgentes = new TransparentPanel();
		panelPiezasEnviadasAgentes.setLayout(null);
		panelPiezasEnviadasAgentes.setBounds(98, 230, 300, 180);
		panelReportes.add(panelPiezasEnviadasAgentes);
		
		JLabel lblFechaEnvioAgente = new JLabel("F. Envio Agente");
		lblFechaEnvioAgente.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFechaEnvioAgente.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaEnvioAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFechaEnvioAgente.setBounds(0, 0, 100, 20);
		panelPiezasEnviadasAgentes.add(lblFechaEnvioAgente);
		
		modelo_tabla_piezas_enviadas_agentes = new DefaultTableModel(datosTabla_piezas_enviadas_agentes, nombreColumnas);
		tablaPiezasEnviadasAgente = new JTable(modelo_tabla_piezas_enviadas_agentes) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		for(int i = 0; i < tablaPiezasEnviadasAgente.getColumnCount(); i++) {
			tablaPiezasEnviadasAgente.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasEnviadasAgente.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		tablaPiezasEnviadasAgente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPiezasEnviadasAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaPiezasEnviadasAgente.setBounds(100, 20, 200, 130);
		panelPiezasEnviadasAgentes.add(tablaPiezasEnviadasAgente);
		
		JLabel lblPiezasEnviadasAgentes = new JLabel("Stock Enviado a Agente/s");
		lblPiezasEnviadasAgentes.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasEnviadasAgentes.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasEnviadasAgentes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasEnviadasAgentes.setBounds(100, 0, 200, 20);
		panelPiezasEnviadasAgentes.add(lblPiezasEnviadasAgentes);
		
		btnVerCasosPiezasEnviadasAgentes = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasEnviadasAgentes.setText("Ver Casos");
		btnVerCasosPiezasEnviadasAgentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosEnviadasAgente();
			}
		});
		btnVerCasosPiezasEnviadasAgentes.setBounds(100, 155, 200, 20);
		panelPiezasEnviadasAgentes.add(btnVerCasosPiezasEnviadasAgentes);
		
		panelPiezasRecibidasAgentes = new TransparentPanel();
		panelPiezasRecibidasAgentes.setLayout(null);
		panelPiezasRecibidasAgentes.setBounds(496, 230, 300, 180);
		panelReportes.add(panelPiezasRecibidasAgentes);
		
		JLabel lblFRecepcionAgente = new JLabel("F. Recepcion Agente");
		lblFRecepcionAgente.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblFRecepcionAgente.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFRecepcionAgente.setHorizontalAlignment(SwingConstants.CENTER);
		lblFRecepcionAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFRecepcionAgente.setBounds(0, 0, 100, 20);
		panelPiezasRecibidasAgentes.add(lblFRecepcionAgente);
		
		modelo_tabla_recibiadas_agentes = new DefaultTableModel(datosTabla_piezas_recibiadas_agentes, nombreColumnas);
		tablaPiezasRecibidasAgentes = new JTable(modelo_tabla_recibiadas_agentes) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		for(int i = 0; i < tablaPiezasRecibidasAgentes.getColumnCount(); i++) {
			tablaPiezasRecibidasAgentes.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasRecibidasAgentes.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		tablaPiezasRecibidasAgentes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPiezasRecibidasAgentes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaPiezasRecibidasAgentes.setBounds(100, 20, 200, 130);
		panelPiezasRecibidasAgentes.add(tablaPiezasRecibidasAgentes);
		
		JLabel lblPiezasRecibidasAgentes = new JLabel("Stock Recibido de Agente/s");
		lblPiezasRecibidasAgentes.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasRecibidasAgentes.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasRecibidasAgentes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasRecibidasAgentes.setBounds(100, 0, 200, 20);
		panelPiezasRecibidasAgentes.add(lblPiezasRecibidasAgentes);
		
		btnVerCasosPiezasRecibidasAgentes = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasRecibidasAgentes.setText("Ver Casos");
		btnVerCasosPiezasRecibidasAgentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosRecibidasDeAgente();
			}
		});
		btnVerCasosPiezasRecibidasAgentes.setBounds(100, 155, 200, 20);
		panelPiezasRecibidasAgentes.add(btnVerCasosPiezasRecibidasAgentes);
		
		panelPiezasRecursadas = new TransparentPanel();
		panelPiezasRecursadas.setLayout(null);
		panelPiezasRecursadas.setBounds(894, 230, 300, 180);
		panelReportes.add(panelPiezasRecursadas);
		
		JLabel lblFrecurso = new JLabel("F. Recurso");
		lblFrecurso.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFrecurso.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrecurso.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFrecurso.setBounds(0, 0, 100, 20);
		panelPiezasRecursadas.add(lblFrecurso);
		
		modelo_tabla_piezas_recursadas = new DefaultTableModel(datosTabla_piezas_recursadas, nombreColumnas);
		tablaPiezasRecursadas = new JTable(modelo_tabla_piezas_recursadas) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		for(int i = 0; i < tablaPiezasRecursadas.getColumnCount(); i++) {
			tablaPiezasRecursadas.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasRecursadas.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		tablaPiezasRecursadas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPiezasRecursadas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaPiezasRecursadas.setBounds(100, 20, 200, 130);
		panelPiezasRecursadas.add(tablaPiezasRecursadas);
		
		JLabel lblPiezasRecursadas = new JLabel("Stock Recursado");
		lblPiezasRecursadas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasRecursadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasRecursadas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasRecursadas.setBounds(100, 0, 200, 20);
		panelPiezasRecursadas.add(lblPiezasRecursadas);
		
		btnVerCasosPiezasRecursadas = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasRecursadas.setText("Ver Casos");
		btnVerCasosPiezasRecursadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosRecursadas();
			}
		});
		btnVerCasosPiezasRecursadas.setBounds(100, 155, 200, 20);
		panelPiezasRecursadas.add(btnVerCasosPiezasRecursadas);
		
		panelPiezasConSDevolucion = new TransparentPanel();
		panelPiezasConSDevolucion.setLayout(null);
		panelPiezasConSDevolucion.setBounds(98, 425, 300, 180);
		panelReportes.add(panelPiezasConSDevolucion);
		
		JLabel lblFsolcitudDev = new JLabel("F. Solicitud Devolucion");
		lblFsolcitudDev.setFont(new Font("Tahoma", Font.PLAIN, 7));
		lblFsolcitudDev.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFsolcitudDev.setHorizontalAlignment(SwingConstants.CENTER);
		lblFsolcitudDev.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFsolcitudDev.setBounds(0, 0, 100, 20);
		panelPiezasConSDevolucion.add(lblFsolcitudDev);
		
		modelo_tabla_piezas_con_sdevolucion = new DefaultTableModel(datosTabla_piezas_con_sdevolucion, nombreColumnas);
		tablaPiezasConSDevolucion = new JTable(modelo_tabla_piezas_con_sdevolucion) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		for(int i = 0; i < tablaPiezasConSDevolucion.getColumnCount(); i++) {
			tablaPiezasConSDevolucion.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasConSDevolucion.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		tablaPiezasConSDevolucion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPiezasConSDevolucion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaPiezasConSDevolucion.setBounds(100, 20, 200, 130);
		panelPiezasConSDevolucion.add(tablaPiezasConSDevolucion);
		
		JLabel lblPiezasConSolic = new JLabel("Stock Con Solicitud Devolucion");
		lblPiezasConSolic.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblPiezasConSolic.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasConSolic.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasConSolic.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasConSolic.setBounds(100, 0, 200, 20);
		panelPiezasConSDevolucion.add(lblPiezasConSolic);
		
		btnVerCasosPiezasConSDevolucion = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasConSDevolucion.setText("Ver Casos");
		btnVerCasosPiezasConSDevolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosConSDevolucion();
			}
		});
		btnVerCasosPiezasConSDevolucion.setBounds(100, 155, 200, 20);
		panelPiezasConSDevolucion.add(btnVerCasosPiezasConSDevolucion);
		
		panelPiezasAprobDevolucion = new TransparentPanel();
		panelPiezasAprobDevolucion.setLayout(null);
		panelPiezasAprobDevolucion.setBounds(496, 425, 300, 180);
		panelReportes.add(panelPiezasAprobDevolucion);
		
		JLabel lblFAprobacionDevolucion = new JLabel("F. Aprobado Devolucion");
		lblFAprobacionDevolucion.setFont(new Font("Tahoma", Font.PLAIN, 7));
		lblFAprobacionDevolucion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFAprobacionDevolucion.setHorizontalAlignment(SwingConstants.CENTER);
		lblFAprobacionDevolucion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFAprobacionDevolucion.setBounds(0, 0, 100, 20);
		panelPiezasAprobDevolucion.add(lblFAprobacionDevolucion);
		
		modelo_tabla_piezas_aprobacion_devolucion = new DefaultTableModel(datosTabla_piezas_aprobacion_devolucion, nombreColumnas);
		tablaPiezasAprobDevolucion = new JTable(modelo_tabla_piezas_aprobacion_devolucion) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		for(int i = 0; i < tablaPiezasAprobDevolucion.getColumnCount(); i++) {
			tablaPiezasAprobDevolucion.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasAprobDevolucion.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		tablaPiezasAprobDevolucion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPiezasAprobDevolucion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaPiezasAprobDevolucion.setBounds(100, 20, 200, 130);
		panelPiezasAprobDevolucion.add(tablaPiezasAprobDevolucion);
		
		JLabel lblPiezasAprobadasDevolucion = new JLabel("Stock Aprobado Para Devolucion");
		lblPiezasAprobadasDevolucion.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblPiezasAprobadasDevolucion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasAprobadasDevolucion.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasAprobadasDevolucion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasAprobadasDevolucion.setBounds(100, 0, 200, 20);
		panelPiezasAprobDevolucion.add(lblPiezasAprobadasDevolucion);
		
		btnVerCasosPiezasAprobDevolucion = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasAprobDevolucion.setText("Ver Casos");
		btnVerCasosPiezasAprobDevolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosAprobDevolucion();
			}
		});
		btnVerCasosPiezasAprobDevolucion.setBounds(100, 155, 200, 20);
		panelPiezasAprobDevolucion.add(btnVerCasosPiezasAprobDevolucion);
		
		panelPiezasDevueltas = new TransparentPanel();
		panelPiezasDevueltas.setLayout(null);
		panelPiezasDevueltas.setBounds(894, 425, 300, 180);
		panelReportes.add(panelPiezasDevueltas);
		
		JLabel lblFdevolucion = new JLabel("F. Devolucion Fabrica");
		lblFdevolucion.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblFdevolucion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFdevolucion.setHorizontalAlignment(SwingConstants.CENTER);
		lblFdevolucion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFdevolucion.setBounds(0, 0, 100, 20);
		panelPiezasDevueltas.add(lblFdevolucion);
		
		modelo_tabla_piezas_devueltas = new DefaultTableModel(datosTabla_piezas_devueltas, nombreColumnas);
		tablaPiezasDevueltas = new JTable(modelo_tabla_piezas_devueltas) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		for(int i = 0; i < tablaPiezasDevueltas.getColumnCount(); i++) {
			tablaPiezasDevueltas.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasDevueltas.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		tablaPiezasDevueltas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPiezasDevueltas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaPiezasDevueltas.setBounds(100, 20, 200, 130);
		panelPiezasDevueltas.add(tablaPiezasDevueltas);
		
		JLabel lblPiezasDevueltas = new JLabel("Stock Devuelto");
		lblPiezasDevueltas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasDevueltas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasDevueltas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasDevueltas.setBounds(100, 0, 200, 20);
		panelPiezasDevueltas.add(lblPiezasDevueltas);
		
		btnVerCasosPiezasDevueltas = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasDevueltas.setText("Ver Casos");
		btnVerCasosPiezasDevueltas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosDevueltasAgente();
			}
		});
		btnVerCasosPiezasDevueltas.setBounds(100, 155, 200, 20);
		panelPiezasDevueltas.add(btnVerCasosPiezasDevueltas);
		
		JLabel lblNombreDelAgente = new JLabel("Nombre del Agente");
		lblNombreDelAgente.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDelAgente.setBounds(0, 0, 110, 20);
		panelReportes.add(lblNombreDelAgente);
		
		cBAgentes = new JComboBox<String>();
		cBAgentes.setBounds(120, 0, 250, 20);
		cBAgentes.setModel(new DefaultComboBoxModel<String>(agentes));
		panelReportes.add(cBAgentes);
		
		btnVolver = new GlossyButton("VOLVER",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUEGREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVolver.setText("Volver");
		btnVolver.setBounds(587, 661, 120, 23);
		contentPane.add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVolver.setIcon(new ImageIcon(GUIReporteRapidoAgente.class.getResource("/cliente/Resources/Icons/back.png")));
		
		setVisible(true);
	}


	protected void verCasosOT() {
		java.sql.Date desde = null;
		String nombre_agente = cBAgentes.getSelectedItem().toString();
		if(dCDesdeFReclamo.getDate()!=null)
			desde = new java.sql.Date(dCDesdeFReclamo.getDate().getTime());
		java.sql.Date hasta = null;
		if(dCHastaFDevolucion.getDate()!=null)
			hasta = new java.sql.Date(dCHastaFDevolucion.getDate().getTime());
		mediador.verCasosOTAgente(desde, hasta, nombre_agente);
	}

	protected void verCasosRecibidasDeAgente() {
		java.sql.Date desde = null;
		String nombre_agente = cBAgentes.getSelectedItem().toString();
		if(dCDesdeFReclamo.getDate()!=null)
			desde = new java.sql.Date(dCDesdeFReclamo.getDate().getTime());
		java.sql.Date hasta = null;
		if(dCHastaFDevolucion.getDate()!=null)
			hasta = new java.sql.Date(dCHastaFDevolucion.getDate().getTime());
		mediador.verCasosRecibidasDeAgente(desde, hasta, nombre_agente);
	}

	protected void verCasosAprobDevolucion() {
		java.sql.Date desde = null;
		String nombre_agente = cBAgentes.getSelectedItem().toString();
		if(dCDesdeFReclamo.getDate()!=null)
			desde = new java.sql.Date(dCDesdeFReclamo.getDate().getTime());
		java.sql.Date hasta = null;
		if(dCHastaFDevolucion.getDate()!=null)
			hasta = new java.sql.Date(dCHastaFDevolucion.getDate().getTime());
		mediador.verCasosAprobDevolucionAgente(desde, hasta, nombre_agente);
	}

	protected void verCasosConSDevolucion() {
		java.sql.Date desde = null;
		String nombre_agente = cBAgentes.getSelectedItem().toString();
		if(dCDesdeFReclamo.getDate()!=null)
			desde = new java.sql.Date(dCDesdeFReclamo.getDate().getTime());
		java.sql.Date hasta = null;
		if(dCHastaFDevolucion.getDate()!=null)
			hasta = new java.sql.Date(dCHastaFDevolucion.getDate().getTime());
		mediador.verCasosConSDevolucionAgente(desde, hasta, nombre_agente);
	}

	protected void verCasosRecursadas() {
		java.sql.Date desde = null;
		String nombre_agente = cBAgentes.getSelectedItem().toString();
		if(dCDesdeFReclamo.getDate()!=null)
			desde = new java.sql.Date(dCDesdeFReclamo.getDate().getTime());
		java.sql.Date hasta = null;
		if(dCHastaFDevolucion.getDate()!=null)
			hasta = new java.sql.Date(dCHastaFDevolucion.getDate().getTime());
		mediador.verCasosRecursadasAgente(desde, hasta, nombre_agente);
	}

	protected void verCasosDevueltasAgente() {
		java.sql.Date desde = null;
		String nombre_agente = cBAgentes.getSelectedItem().toString();
		if(dCDesdeFReclamo.getDate()!=null)
			desde = new java.sql.Date(dCDesdeFReclamo.getDate().getTime());
		java.sql.Date hasta = null;
		if(dCHastaFDevolucion.getDate()!=null)
			hasta = new java.sql.Date(dCHastaFDevolucion.getDate().getTime());
		mediador.verCasosDevueltasAgente(desde, hasta, nombre_agente);
	}

	protected void verCasosEnviadasAgente() {
		java.sql.Date desde = null;
		String nombre_agente = cBAgentes.getSelectedItem().toString();
		if(dCDesdeFReclamo.getDate()!=null)
			desde = new java.sql.Date(dCDesdeFReclamo.getDate().getTime());
		java.sql.Date hasta = null;
		if(dCHastaFDevolucion.getDate()!=null)
			hasta = new java.sql.Date(dCHastaFDevolucion.getDate().getTime());
		mediador.verCasosEnvidasAgente(desde, hasta, nombre_agente);
	}

	protected void verCasosSinEnviarAgente() {
		java.sql.Date desde = null;
		String nombre_agente = cBAgentes.getSelectedItem().toString();
		if(dCDesdeFReclamo.getDate()!=null)
			desde = new java.sql.Date(dCDesdeFReclamo.getDate().getTime());
		java.sql.Date hasta = null;
		if(dCHastaFDevolucion.getDate()!=null)
			hasta = new java.sql.Date(dCHastaFDevolucion.getDate().getTime());
		mediador.verCasosSinEnviarAgente(desde, hasta, nombre_agente);
	}

	protected void verCasosEnTransito() {
		java.sql.Date desde = null;
		String nombre_agente = cBAgentes.getSelectedItem().toString();
		if(dCDesdeFReclamo.getDate()!=null)
			desde = new java.sql.Date(dCDesdeFReclamo.getDate().getTime());
		java.sql.Date hasta = null;
		if(dCHastaFDevolucion.getDate()!=null)
			hasta = new java.sql.Date(dCHastaFDevolucion.getDate().getTime());
		mediador.verCasosEnTransitoAgente(desde, hasta, nombre_agente);
	}
	
	private void actualizarDatos(java.sql.Date desde, java.sql.Date hasta, String filtro_agente){
		DecimalFormat df_pesos = new DecimalFormat("0.00"); 
		cantidad_con_ot =  mediador.cantidad_con_ot_agente(desde,hasta,filtro_agente);
		Triple<Double,Double,Double> anticuacion_con_ot = mediador.anticuacion_con_ot_agente(desde, hasta, filtro_agente);
		if(anticuacion_con_ot.first()!=null)
			prom_anticuacion_con_ot = anticuacion_con_ot.first().toString();
		else
			prom_anticuacion_con_ot = "";
		if(anticuacion_con_ot.second()!=null)
			max_anticuacion_con_ot = anticuacion_con_ot.second().toString();
		else
			max_anticuacion_con_ot = "";
		if(anticuacion_con_ot.third()!=null)
			min_anticuacion_con_ot = anticuacion_con_ot.third().toString();
		else
			min_anticuacion_con_ot = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_con_ot = mediador.monto_con_ot_agente(desde, hasta, filtro_agente);
		if(_monto_con_ot.first()!=null)
			monto_con_ot = "$"+df_pesos.format(_monto_con_ot.first());
		else
			monto_con_ot = "";
		if(_monto_con_ot.second()!=null)
			prom_monto_con_ot = "$"+df_pesos.format(_monto_con_ot.second());
		else
			prom_monto_con_ot = "";
		if(_monto_con_ot.third()!=null)
			max_monto_con_ot = "$"+df_pesos.format(_monto_con_ot.third());
		else
			max_monto_con_ot = "";
		if(_monto_con_ot.fourth()!=null)
			min_monto_con_ot = "$"+df_pesos.format(_monto_con_ot.fourth());
		else
			min_monto_con_ot = "";
		
		cantidad_en_tranisto =  mediador.cantidad_en_transito_agente(desde, hasta, filtro_agente);
		Triple<Double,Double,Double> anticuacion_en_transito = mediador.anticuacion_en_transito_agente(desde, hasta, filtro_agente);
		if(anticuacion_en_transito.first()!=null)
			prom_anticuacion_en_transito = anticuacion_en_transito.first().toString();
		else
			prom_anticuacion_en_transito = "";
		if(anticuacion_en_transito.second()!=null)
			max_anticuacion_en_transito = anticuacion_en_transito.second().toString();
		else
			max_anticuacion_en_transito = "";
		if(anticuacion_en_transito.third()!=null)
			min_anticuacion_en_transito = anticuacion_en_transito.third().toString();
		else
			min_anticuacion_en_transito = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_en_transito = mediador.monto_en_transito_agente(desde, hasta, filtro_agente);
		if(_monto_en_transito.first()!=null)
			monto_en_transito = "$"+df_pesos.format(_monto_en_transito.first());
		else
			monto_en_transito = "";
		if(_monto_en_transito.second()!=null)
			prom_monto_en_transito = "$"+df_pesos.format(_monto_en_transito.second());
		else
			prom_monto_en_transito = "";
		if(_monto_en_transito.third()!=null)
			max_monto_en_transito = "$"+df_pesos.format(_monto_en_transito.third());
		else
			max_monto_en_transito = "";
		if(_monto_en_transito.fourth()!=null)
			min_monto_en_transito = "$"+df_pesos.format(_monto_en_transito.fourth());
		else
			min_monto_en_transito = "";
		
		cantidad_sin_enviar_agentes =  mediador.cantidad_sin_enviar_agente(desde, hasta, filtro_agente);
		Triple<Double,Double,Double> anticuacion_sin_enviar_agentes = mediador.anticuacion_sin_enviar_agente(desde, hasta, filtro_agente);
		if(anticuacion_sin_enviar_agentes.first()!=null)
			prom_anticuacion_sin_enviar_agentes = anticuacion_sin_enviar_agentes.first().toString();
		else
			prom_anticuacion_sin_enviar_agentes = "";
		if(anticuacion_sin_enviar_agentes.second()!=null)
			max_anticuacion_sin_enviar_agentes = anticuacion_sin_enviar_agentes.second().toString();
		else
			max_anticuacion_sin_enviar_agentes = "";
		if(anticuacion_sin_enviar_agentes.third()!=null)
			min_anticuacion_sin_enviar_agentes = anticuacion_sin_enviar_agentes.third().toString();
		else
			min_anticuacion_sin_enviar_agentes = "";
		
		Cuadruple<Double,Double,Double,Double> monto_sin_enviar_agentes_ = mediador.monto_sin_enviar_agente(desde, hasta, filtro_agente);
		if(monto_sin_enviar_agentes_.first()!=null)
			monto_sin_enviar_agentes = "$"+df_pesos.format(monto_sin_enviar_agentes_.first());
		else
			monto_sin_enviar_agentes = "";
		if(monto_sin_enviar_agentes_.second()!=null)
			prom_monto_sin_enviar_agentes = "$"+df_pesos.format(monto_sin_enviar_agentes_.second());
		else
			prom_monto_sin_enviar_agentes = "";
		if(monto_sin_enviar_agentes_.third()!=null)
			max_monto_sin_enviar_agentes = "$"+df_pesos.format(monto_sin_enviar_agentes_.third());
		else
			max_monto_sin_enviar_agentes = "";
		if(monto_sin_enviar_agentes_.fourth()!=null)
			min_monto_sin_enviar_agentes = "$"+df_pesos.format(monto_sin_enviar_agentes_.fourth());
		else
			min_monto_sin_enviar_agentes = "";
		
		cantidad_enviadas_agentes =  mediador.cantidad_enviado_a_agente(desde, hasta, filtro_agente);
		Triple<Double,Double,Double> anticuacion_enviadas_agentes = mediador.anticuacion_enviado_a_agente(desde, hasta, filtro_agente);
		if(anticuacion_enviadas_agentes.first()!=null)
			prom_anticuacion_enviadas_agentes = anticuacion_enviadas_agentes.first().toString();
		else
			prom_anticuacion_enviadas_agentes = "";
		if(anticuacion_enviadas_agentes.second()!=null)
			max_anticuacion_enviadas_agentes = anticuacion_enviadas_agentes.second().toString();
		else
			max_anticuacion_enviadas_agentes = "";
		if(anticuacion_enviadas_agentes.third()!=null)
			min_anticuacion_enviadas_agentes = anticuacion_enviadas_agentes.third().toString();
		else
			min_anticuacion_enviadas_agentes = "";
		
		Cuadruple<Double,Double,Double,Double> monto_enviadas_agentes_ = mediador.monto_enviado_a_agente(desde, hasta, filtro_agente);
		if(monto_enviadas_agentes_.first()!=null)
			monto_enviadas_agentes = "$"+df_pesos.format(monto_enviadas_agentes_.first());
		else
			monto_enviadas_agentes = "";
		if(monto_enviadas_agentes_.second()!=null)
			prom_monto_enviadas_agentes = "$"+df_pesos.format(monto_enviadas_agentes_.second());
		else
			prom_monto_enviadas_agentes = "";
		if(monto_enviadas_agentes_.third()!=null)
			max_monto_enviadas_agentes = "$"+df_pesos.format(monto_enviadas_agentes_.third());
		else
			max_monto_enviadas_agentes = "";
		if(monto_enviadas_agentes_.fourth()!=null)
			min_monto_enviadas_agentes = "$"+df_pesos.format(monto_enviadas_agentes_.fourth());
		else
			min_monto_enviadas_agentes = "";
		
		cantidad_recibiadas_agentes =  mediador.cantidad_recibidas_de_agente(desde, hasta, filtro_agente);
		Triple<Double,Double,Double> anticuacion_recibidas_agentes = mediador.anticuacion_recibidas_de_agente(desde, hasta, filtro_agente);
		if(anticuacion_recibidas_agentes.first()!=null)
			prom_anticuacion_recibiadas_agentes = anticuacion_recibidas_agentes.first().toString();
		else
			prom_anticuacion_recibiadas_agentes = "";
		if(anticuacion_recibidas_agentes.second()!=null)
			max_anticuacion_recibiadas_agentes = anticuacion_recibidas_agentes.second().toString();
		else
			max_anticuacion_recibiadas_agentes = "";
		if(anticuacion_recibidas_agentes.third()!=null)
			min_anticuacion_recibiadas_agentes = anticuacion_recibidas_agentes.third().toString();
		else
			min_anticuacion_recibiadas_agentes = "";
		
		Cuadruple<Double,Double,Double,Double> monto_recibidas_agentes_ = mediador.monto_recibidas_de_agente(desde, hasta, filtro_agente);
		if(monto_recibidas_agentes_.first()!=null)
			monto_recibiadas_agentes = "$"+df_pesos.format(monto_recibidas_agentes_.first());
		else
			monto_recibiadas_agentes = "";
		if(monto_recibidas_agentes_.second()!=null)
			prom_monto_recibiadas_agentes = "$"+df_pesos.format(monto_recibidas_agentes_.second());
		else
			prom_monto_recibiadas_agentes = "";
		if(monto_recibidas_agentes_.third()!=null)
			max_monto_recibiadas_agentes = "$"+df_pesos.format(monto_recibidas_agentes_.third());
		else
			max_monto_recibiadas_agentes = "";
		if(monto_recibidas_agentes_.fourth()!=null)
			min_monto_recibiadas_agentes = "$"+df_pesos.format(monto_recibidas_agentes_.fourth());
		else
			min_monto_recibiadas_agentes = "";
		
		cantidad_recursadas =  mediador.cantidad_recursadas_agente(desde, hasta, filtro_agente);
		Triple<Double,Double,Double> anticuacion_recursadas = mediador.anticuacion_recursadas_agente(desde, hasta, filtro_agente);
		if(anticuacion_recursadas.first()!=null)
			prom_anticuacion_recursadas = anticuacion_recursadas.first().toString();
		else
			prom_anticuacion_recursadas = "";
		if(anticuacion_recursadas.second()!=null)
			max_anticuacion_recursadas = anticuacion_recursadas.second().toString();
		else
			max_anticuacion_recursadas = "";
		if(anticuacion_recursadas.third()!=null)
			min_anticuacion_recursadas = anticuacion_recursadas.third().toString();
		else
			min_anticuacion_recursadas = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_recursadas = mediador.monto_recursadas_agente(desde, hasta, filtro_agente);
		if(_monto_recursadas.first()!=null)
			monto_recursadas = "$"+df_pesos.format(_monto_recursadas.first());
		else
			monto_recursadas = "";
		if(_monto_recursadas.second()!=null)
			prom_monto_recursadas = "$"+df_pesos.format(_monto_recursadas.second());
		else
			prom_monto_recursadas = "";
		if(_monto_recursadas.third()!=null)
			max_monto_recursadas = "$"+df_pesos.format(_monto_recursadas.third());
		else
			max_monto_recursadas = "";
		if(_monto_recursadas.fourth()!=null)
			min_monto_recursadas = "$"+df_pesos.format(_monto_recursadas.fourth());
		else
			min_monto_recursadas = "";
		
		cantidad_con_sdevolucion =  mediador.cantidad_con_sdevolucion_agente(desde, hasta, filtro_agente);
		Triple<Double,Double,Double> anticuacion_con_sdevolucion = mediador.anticuacion_con_sdevolucion_agente(desde, hasta, filtro_agente);
		if(anticuacion_con_sdevolucion.first()!=null)
			prom_anticuacion_con_sdevolucion = anticuacion_con_sdevolucion.first().toString();
		else
			prom_anticuacion_con_sdevolucion = "";
		if(anticuacion_con_sdevolucion.second()!=null)
			max_anticuacion_con_sdevolucion = anticuacion_con_sdevolucion.second().toString();
		else
			max_anticuacion_con_sdevolucion = "";
		if(anticuacion_con_sdevolucion.third()!=null)
			min_anticuacion_con_sdevolucion = anticuacion_con_sdevolucion.third().toString();
		else
			min_anticuacion_con_sdevolucion = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_con_sdevolucion = mediador.monto_con_sdevolucion_agente(desde, hasta, filtro_agente);
		if(_monto_con_sdevolucion.first()!=null)
			monto_con_sdevolucion = "$"+df_pesos.format(_monto_con_sdevolucion.first());
		else
			monto_con_sdevolucion = "";
		if(_monto_con_sdevolucion.second()!=null)
			prom_monto_con_sdevolucion = "$"+df_pesos.format(_monto_con_sdevolucion.second());
		else
			prom_monto_con_sdevolucion = "";
		if(_monto_con_sdevolucion.third()!=null)
			max_monto_con_sdevolucion = "$"+df_pesos.format(_monto_con_sdevolucion.third());
		else
			max_monto_con_sdevolucion = "";
		if(_monto_con_sdevolucion.fourth()!=null)
			min_monto_con_sdevolucion = "$"+df_pesos.format(_monto_con_sdevolucion.fourth());
		else
			min_monto_con_sdevolucion = "";
		
		cantidad_aprobadas_devolucion =  mediador.cantidad_aprobada_devolucion_agente(desde, hasta, filtro_agente);
		Triple<Double,Double,Double> anticuacion_aprobadas_devolucion = mediador.anticuacion_aprobada_devolucion_agente(desde, hasta, filtro_agente);
		if(anticuacion_aprobadas_devolucion.first()!=null)
			prom_anticuacion_aprobadas_devolucion = anticuacion_aprobadas_devolucion.first().toString();
		else
			prom_anticuacion_aprobadas_devolucion = "";
		if(anticuacion_aprobadas_devolucion.second()!=null)
			max_anticuacion_aprobadas_devolucion = anticuacion_aprobadas_devolucion.second().toString();
		else
			max_anticuacion_aprobadas_devolucion = "";
		if(anticuacion_aprobadas_devolucion.third()!=null)
			min_anticuacion_aprobadas_devolucion = anticuacion_aprobadas_devolucion.third().toString();
		else
			min_anticuacion_aprobadas_devolucion = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_aprobadas_devolucion = mediador.monto_aprobada_devolucion_agente(desde, hasta, filtro_agente);
		if(_monto_aprobadas_devolucion.first()!=null)
			monto_aprobadas_devolucion = "$"+df_pesos.format(_monto_aprobadas_devolucion.first());
		else
			monto_aprobadas_devolucion = "";
		if(_monto_aprobadas_devolucion.second()!=null)
			prom_monto_aprobadas_devolucion = "$"+df_pesos.format(_monto_aprobadas_devolucion.second());
		else
			prom_monto_aprobadas_devolucion = "";
		if(_monto_aprobadas_devolucion.third()!=null)
			max_monto_aprobadas_devolucion = "$"+df_pesos.format(_monto_aprobadas_devolucion.third());
		else
			max_monto_aprobadas_devolucion = "";
		if(_monto_aprobadas_devolucion.fourth()!=null)
			min_monto_aprobadas_devolucion = "$"+df_pesos.format(_monto_aprobadas_devolucion.fourth());
		else
			min_monto_aprobadas_devolucion = "";
		
		cantidad_devueltas =  mediador.cantidad_devueltas_agente(desde, hasta, filtro_agente);
		Triple<Double,Double,Double> anticuacion_devueltas = mediador.anticuacion_devueltas_agente(desde, hasta, filtro_agente);
		if(anticuacion_devueltas.first()!=null)
			prom_anticuacion_devueltas = anticuacion_devueltas.first().toString();
		else
			prom_anticuacion_devueltas = "";
		if(anticuacion_devueltas.second()!=null)
			max_anticuacion_devueltas = anticuacion_devueltas.second().toString();
		else
			max_anticuacion_devueltas = "";
		if(anticuacion_devueltas.third()!=null)
			min_anticuacion_devueltas = anticuacion_devueltas.third().toString();
		else
			min_anticuacion_devueltas = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_devueltas = mediador.monto_devueltas_agente(desde, hasta, filtro_agente);
		if(_monto_devueltas.first()!=null)
			monto_devueltas = "$"+df_pesos.format(_monto_devueltas.first());
		else
			monto_devueltas = "";
		if(_monto_devueltas.second()!=null)
			prom_monto_devueltas = "$"+df_pesos.format(_monto_devueltas.second());
		else
			prom_monto_devueltas = "";
		if(_monto_devueltas.third()!=null)
			max_monto_devueltas = "$"+df_pesos.format(_monto_devueltas.third());
		else
			max_monto_devueltas = "";
		if(_monto_devueltas.fourth()!=null)
			min_monto_devueltas = "$"+df_pesos.format(_monto_devueltas.fourth());
		else
			min_monto_devueltas = "";
		
		actualizarTablas();
	}

	@SuppressWarnings("unused")
	private void filtrar() {
		String nombre_agente = cBAgentes.getSelectedItem().toString();
		SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
		if(dCDesdeFReclamo.getDate()==null || dCHastaFDevolucion.getDate()==null){ 
			if(dCDesdeFReclamo.getDate()==null){ 
				if(dCHastaFDevolucion.getDate()!=null){//NULL DESDE NO NULL HASTA
					if (JOptionPane.showConfirmDialog(null, "¿Mostrar desde el primer reclamo cargado?, Esto puede demorar.", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIReporteRapidoAgente.class.getResource("/cliente/Resources/Icons/delete.png"))) == JOptionPane.YES_OPTION){
						actualizarDatos(null, new java.sql.Date(dCHastaFDevolucion.getDate().getTime()),nombre_agente);
					}
				}else{ //NULL AMBAS
					if (JOptionPane.showConfirmDialog(null, "¿Mostrar todos los reclamos cargados?, Esto puede demorar.", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIReporteRapidoAgente.class.getResource("/cliente/Resources/Icons/delete.png"))) == JOptionPane.YES_OPTION){
						actualizarDatos(null, null,nombre_agente);
					}
				}
			}else{ //NO NULL DESDE NULL HASTA
				if (JOptionPane.showConfirmDialog(null, "¿Mostrar hasta la ultima devolucion cargada?, Esto puede demorar.", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIReporteRapidoAgente.class.getResource("/cliente/Resources/Icons/delete.png"))) == JOptionPane.YES_OPTION){					
					actualizarDatos(new java.sql.Date(dCDesdeFReclamo.getDate().getTime()), null,nombre_agente);
				}
			}
		}else{ //NO NULL AMBAS
			actualizarDatos(new java.sql.Date(dCDesdeFReclamo.getDate().getTime()), new java.sql.Date(dCHastaFDevolucion.getDate().getTime()),nombre_agente);
		}
	}
	
	private void actualizarTablas(){
		Vector<String> row_cantidad= new Vector<String> ();
		Vector<String> row_prom_anticuacion= new Vector<String> ();
		Vector<String> row_max_anticuacion= new Vector<String> ();
		Vector<String> row_min_anticuacion= new Vector<String> ();
		Vector<String> row_monto= new Vector<String> ();
		Vector<String> row_prom_monto= new Vector<String> ();
		Vector<String> row_max_monto= new Vector<String> ();
		Vector<String> row_min_monto= new Vector<String> ();
		nombreColumnas = new Vector<String> ();
		nombreColumnas.add("DATO");
		nombreColumnas.add("VALOR");
		//		Tabla Piezas Con OT		//
		modelo_tabla_piezas_con_ot = new DefaultTableModel();
		datosTabla_piezas_con_ot = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_con_ot);//Q
		datosTabla_piezas_con_ot.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_con_ot);//X
		datosTabla_piezas_con_ot.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_con_ot);//MX
		datosTabla_piezas_con_ot.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_con_ot);//mX
		datosTabla_piezas_con_ot.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_con_ot);//R
		datosTabla_piezas_con_ot.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_con_ot);//XR
		datosTabla_piezas_con_ot.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_con_ot);//MR
		datosTabla_piezas_con_ot.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_con_ot);//mR
		datosTabla_piezas_con_ot.add(row_min_monto);
		modelo_tabla_piezas_con_ot.setDataVector(datosTabla_piezas_con_ot, nombreColumnas);
		modelo_tabla_piezas_con_ot.fireTableStructureChanged();
		tablaPiezasConOT.setModel(modelo_tabla_piezas_con_ot);
		for(int i = 0; i < tablaPiezasConOT.getColumnCount(); i++) {
			tablaPiezasConOT.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasConOT.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		//		Fin Tabla Piezas Con OT		//
		//		Tabla Piezas En Transito		//
		modelo_tabla_piezas_en_transito = new DefaultTableModel();
		datosTabla_piezas_en_transito = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_en_tranisto);//Q
		datosTabla_piezas_en_transito.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_en_transito);//X
		datosTabla_piezas_en_transito.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_en_transito);//MX
		datosTabla_piezas_en_transito.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_en_transito);//mX
		datosTabla_piezas_en_transito.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_en_transito);//R
		datosTabla_piezas_en_transito.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_en_transito);//XR
		datosTabla_piezas_en_transito.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_en_transito);//MR
		datosTabla_piezas_en_transito.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_en_transito);//mR
		datosTabla_piezas_en_transito.add(row_min_monto);
		modelo_tabla_piezas_en_transito.setDataVector(datosTabla_piezas_en_transito, nombreColumnas);
		modelo_tabla_piezas_en_transito.fireTableStructureChanged();
		tablaPiezasEnTransito.setModel(modelo_tabla_piezas_en_transito);
		for(int i = 0; i < tablaPiezasEnTransito.getColumnCount(); i++) {
			tablaPiezasEnTransito.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasEnTransito.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		//		Fin Tabla Piezas En Transito	//
		//		Tabla Piezas Sin Turno		//
		modelo_tabla_piezas_sin_enviar_agentes = new DefaultTableModel();
		datosTabla_piezas_sin_enviar_agentes = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_sin_enviar_agentes);//Q
		datosTabla_piezas_sin_enviar_agentes.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_sin_enviar_agentes);//X
		datosTabla_piezas_sin_enviar_agentes.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_sin_enviar_agentes);//MX
		datosTabla_piezas_sin_enviar_agentes.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_sin_enviar_agentes);//mX
		datosTabla_piezas_sin_enviar_agentes.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_sin_enviar_agentes);//R
		datosTabla_piezas_sin_enviar_agentes.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_sin_enviar_agentes);//XR
		datosTabla_piezas_sin_enviar_agentes.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_sin_enviar_agentes);//MR
		datosTabla_piezas_sin_enviar_agentes.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_sin_enviar_agentes);//mR
		datosTabla_piezas_sin_enviar_agentes.add(row_min_monto);
		modelo_tabla_piezas_sin_enviar_agentes.setDataVector(datosTabla_piezas_sin_enviar_agentes, nombreColumnas);
		modelo_tabla_piezas_sin_enviar_agentes.fireTableStructureChanged();
		tablaPiezasSinEnviarAgentes.setModel(modelo_tabla_piezas_sin_enviar_agentes);
		for(int i = 0; i < tablaPiezasSinEnviarAgentes.getColumnCount(); i++) {
			tablaPiezasSinEnviarAgentes.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasSinEnviarAgentes.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		//		Fin Tabla Piezas Sin Turno		//
		//		Tabla Piezas Con Turno		//
		modelo_tabla_piezas_enviadas_agentes = new DefaultTableModel();
		datosTabla_piezas_enviadas_agentes = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_enviadas_agentes);//Q
		datosTabla_piezas_enviadas_agentes.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_enviadas_agentes);//X
		datosTabla_piezas_enviadas_agentes.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_enviadas_agentes);//MX
		datosTabla_piezas_enviadas_agentes.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_enviadas_agentes);//mX
		datosTabla_piezas_enviadas_agentes.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_enviadas_agentes);//R
		datosTabla_piezas_enviadas_agentes.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_enviadas_agentes);//XR
		datosTabla_piezas_enviadas_agentes.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_enviadas_agentes);//MR
		datosTabla_piezas_enviadas_agentes.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_enviadas_agentes);//mR
		datosTabla_piezas_enviadas_agentes.add(row_min_monto);
		modelo_tabla_piezas_enviadas_agentes.setDataVector(datosTabla_piezas_enviadas_agentes, nombreColumnas);
		modelo_tabla_piezas_enviadas_agentes.fireTableStructureChanged();
		tablaPiezasEnviadasAgente.setModel(modelo_tabla_piezas_enviadas_agentes);
		for(int i = 0; i < tablaPiezasEnviadasAgente.getColumnCount(); i++) {
			tablaPiezasEnviadasAgente.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasEnviadasAgente.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		//		Fin Tabla Piezas Con Turno		//
		//		Tabla Piezas Cambiadas		//
		modelo_tabla_recibiadas_agentes = new DefaultTableModel();
		datosTabla_piezas_recibiadas_agentes = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_recibiadas_agentes);//Q
		datosTabla_piezas_recibiadas_agentes.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_recibiadas_agentes);//X
		datosTabla_piezas_recibiadas_agentes.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_recibiadas_agentes);//MX
		datosTabla_piezas_recibiadas_agentes.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_recibiadas_agentes);//mX
		datosTabla_piezas_recibiadas_agentes.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_recibiadas_agentes);//R
		datosTabla_piezas_recibiadas_agentes.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_recibiadas_agentes);//XR
		datosTabla_piezas_recibiadas_agentes.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_recibiadas_agentes);//MR
		datosTabla_piezas_recibiadas_agentes.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_recibiadas_agentes);//mR
		datosTabla_piezas_recibiadas_agentes.add(row_min_monto);
		modelo_tabla_recibiadas_agentes.setDataVector(datosTabla_piezas_recibiadas_agentes, nombreColumnas);
		modelo_tabla_recibiadas_agentes.fireTableStructureChanged();
		tablaPiezasRecibidasAgentes.setModel(modelo_tabla_recibiadas_agentes);
		for(int i = 0; i < tablaPiezasRecibidasAgentes.getColumnCount(); i++) {
			tablaPiezasRecibidasAgentes.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasRecibidasAgentes.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		//		Fin Tabla Piezas Cambiadas		//
		//		Tabla Piezas Recursadas		//
		modelo_tabla_piezas_recursadas = new DefaultTableModel();
		datosTabla_piezas_recursadas = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_recursadas);//Q
		datosTabla_piezas_recursadas.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_recursadas);//X
		datosTabla_piezas_recursadas.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_recursadas);//MX
		datosTabla_piezas_recursadas.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_recursadas);//mX
		datosTabla_piezas_recursadas.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_recursadas);//R
		datosTabla_piezas_recursadas.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_recursadas);//XR
		datosTabla_piezas_recursadas.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_recursadas);//MR
		datosTabla_piezas_recursadas.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_recursadas);//mR
		datosTabla_piezas_recursadas.add(row_min_monto);
		modelo_tabla_piezas_recursadas.setDataVector(datosTabla_piezas_recursadas, nombreColumnas);
		modelo_tabla_piezas_recursadas.fireTableStructureChanged();
		tablaPiezasRecursadas.setModel(modelo_tabla_piezas_recursadas);
		for(int i = 0; i < tablaPiezasRecursadas.getColumnCount(); i++) {
			tablaPiezasRecursadas.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasRecursadas.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		//		Fin Tabla Piezas Recursadas		//
		//		Tabla Piezas Con Solicitud Devolucion		//
		modelo_tabla_piezas_con_sdevolucion = new DefaultTableModel();
		datosTabla_piezas_con_sdevolucion = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_con_sdevolucion);//Q
		datosTabla_piezas_con_sdevolucion.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_con_sdevolucion);//X
		datosTabla_piezas_con_sdevolucion.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_con_sdevolucion);//MX
		datosTabla_piezas_con_sdevolucion.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_con_sdevolucion);//mX
		datosTabla_piezas_con_sdevolucion.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_con_sdevolucion);//R
		datosTabla_piezas_con_sdevolucion.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_con_sdevolucion);//XR
		datosTabla_piezas_con_sdevolucion.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_con_sdevolucion);//MR
		datosTabla_piezas_con_sdevolucion.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_con_sdevolucion);//mR
		datosTabla_piezas_con_sdevolucion.add(row_min_monto);
		modelo_tabla_piezas_con_sdevolucion.setDataVector(datosTabla_piezas_con_sdevolucion, nombreColumnas);
		modelo_tabla_piezas_con_sdevolucion.fireTableStructureChanged();
		tablaPiezasConSDevolucion.setModel(modelo_tabla_piezas_con_sdevolucion);
		for(int i = 0; i < tablaPiezasConSDevolucion.getColumnCount(); i++) {
			tablaPiezasConSDevolucion.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasConSDevolucion.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		//		Fin Tabla Piezas Con Solicitud Devolucion		//
		//		Tabla Piezas Aprobada Devolucion		//
		modelo_tabla_piezas_aprobacion_devolucion = new DefaultTableModel();
		datosTabla_piezas_aprobacion_devolucion = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_aprobadas_devolucion);//Q
		datosTabla_piezas_aprobacion_devolucion.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_aprobadas_devolucion);//X
		datosTabla_piezas_aprobacion_devolucion.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_aprobadas_devolucion);//MX
		datosTabla_piezas_aprobacion_devolucion.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_aprobadas_devolucion);//mX
		datosTabla_piezas_aprobacion_devolucion.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_aprobadas_devolucion);//R
		datosTabla_piezas_aprobacion_devolucion.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_aprobadas_devolucion);//XR
		datosTabla_piezas_aprobacion_devolucion.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_aprobadas_devolucion);//MR
		datosTabla_piezas_aprobacion_devolucion.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_aprobadas_devolucion);//mR
		datosTabla_piezas_aprobacion_devolucion.add(row_min_monto);
		modelo_tabla_piezas_aprobacion_devolucion.setDataVector(datosTabla_piezas_aprobacion_devolucion, nombreColumnas);
		modelo_tabla_piezas_aprobacion_devolucion.fireTableStructureChanged();
		tablaPiezasAprobDevolucion.setModel(modelo_tabla_piezas_aprobacion_devolucion);
		for(int i = 0; i < tablaPiezasAprobDevolucion.getColumnCount(); i++) {
			tablaPiezasAprobDevolucion.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasAprobDevolucion.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		//		Fin Tabla Piezas Aprobada Devolucion		//		
		//		Tabla Piezas Devueltas		//
		modelo_tabla_piezas_devueltas = new DefaultTableModel();
		datosTabla_piezas_devueltas = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_devueltas);//Q
		datosTabla_piezas_devueltas.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Anticuacion Prom");
		row_prom_anticuacion.add(prom_anticuacion_devueltas);//X
		datosTabla_piezas_devueltas.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Max Anticuacion");
		row_max_anticuacion.add(max_anticuacion_devueltas);//MX
		datosTabla_piezas_devueltas.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Min Anticuacion");
		row_min_anticuacion.add(min_anticuacion_devueltas);//mX
		datosTabla_piezas_devueltas.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Monto Total");
		row_monto.add(monto_devueltas);//R
		datosTabla_piezas_devueltas.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Monto Prom");
		row_prom_monto.add(prom_monto_devueltas);//XR
		datosTabla_piezas_devueltas.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Max Monto");
		row_max_monto.add(max_monto_devueltas);//MR
		datosTabla_piezas_devueltas.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Min Monto");
		row_min_monto.add(min_monto_devueltas);//mR
		datosTabla_piezas_devueltas.add(row_min_monto);
		modelo_tabla_piezas_devueltas.setDataVector(datosTabla_piezas_devueltas, nombreColumnas);
		modelo_tabla_piezas_devueltas.fireTableStructureChanged();
		tablaPiezasDevueltas.setModel(modelo_tabla_piezas_devueltas);
		for(int i = 0; i < tablaPiezasDevueltas.getColumnCount(); i++) {
			tablaPiezasDevueltas.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasDevueltas.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		//		Fin Tabla Piezas Aprobada Devolucion		//
	}
	
	public MediadorReportes getMediador() {
		return mediador;
	}

	public void setMediador(MediadorReportes mediador) {
		this.mediador = mediador;
	}
}
