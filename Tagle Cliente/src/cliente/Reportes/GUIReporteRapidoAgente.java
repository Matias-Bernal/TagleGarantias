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
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
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
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import cliente.GestionarPedido.GUIModificarPedidoEntidad;
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
	
	private JTable tablaPiezasPedidasAFabrica;
	private DefaultTableModel modelo_tabla_piezas_pedidas_a_fabrica;
	private Vector<Vector<String>> datosTabla_piezas_pedidas_a_fabrica;
	private JButton btnVerCasosPiezasConOT;
	private String cantidad_pedidas_a_fabrica;
	private String prom_anticuacion_pedidas_a_fabrica;
	private String max_anticuacion_pedidas_a_fabrica;
	private String min_anticuacion_pedidas_a_fabrica;
	private String monto_pedidas_a_fabrica;
	private String prom_monto_pedidas_a_fabrica;
	private String max_monto_pedidas_a_fabrica;
	private String min_monto_pedidas_a_fabrica;
	
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
	private JPanel panelPiezasPedidasAFabrica;
	private JPanel panelPiezasEnviadasAgentes;
	private JPanel panelPiezasEnTransito;
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

	@SuppressWarnings({ "static-access"})
	private void completarCampos() {
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
	
	
	private String roundUp(Double a){
		if(a >= (Double.valueOf(a.intValue()+".5")))
			return String.valueOf(a.intValue()+1);
		else
			return String.valueOf(a.intValue());
	}

	@SuppressWarnings({ "static-access"})
	private void cargarDatos() {
		agentes = new Vector<String>();
		agentes.add("");
		agentes.addAll(mediador.obtenerNombresAgentes());

		anchos_tabla = new Vector<Integer>();
		anchos_tabla.add(115);
		anchos_tabla.add(60);
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
		
		Calendar desdeCalendar = Calendar.getInstance();
		desdeCalendar.setFirstDayOfWeek(desdeCalendar.SUNDAY);
		desdeCalendar.add(desdeCalendar.MONTH, -2);
		desdeCalendar.set(desdeCalendar.DAY_OF_MONTH,1);
		java.sql.Date desde = new java.sql.Date(desdeCalendar.getTime().getTime());
		
		Calendar hastaCalendar = Calendar.getInstance();
		hastaCalendar.setFirstDayOfWeek(hastaCalendar.SUNDAY);
		hastaCalendar.set(hastaCalendar.DAY_OF_MONTH,hastaCalendar.getMaximum(hastaCalendar.DAY_OF_MONTH));		
		java.sql.Date hasta = new java.sql.Date(hastaCalendar.getTime().getTime());
							
		cantidad_pedidas_a_fabrica =  mediador.cantidad_pedida_a_fabrica_agente(desde, hasta, "");
		Triple<Double,Double,Double> anticuacion_pedidas_a_fabrica = mediador.anticuacion_pedidas_a_fabrica_agente(desde, hasta, "");
		if(anticuacion_pedidas_a_fabrica.first()!=null)
			prom_anticuacion_pedidas_a_fabrica = roundUp(anticuacion_pedidas_a_fabrica.first());
		else
			prom_anticuacion_pedidas_a_fabrica = "";
		if(anticuacion_pedidas_a_fabrica.second()!=null)
			max_anticuacion_pedidas_a_fabrica = roundUp(anticuacion_pedidas_a_fabrica.second());
		else
			max_anticuacion_pedidas_a_fabrica = "";
		if(anticuacion_pedidas_a_fabrica.third()!=null)
			min_anticuacion_pedidas_a_fabrica = roundUp(anticuacion_pedidas_a_fabrica.third());
		else
			min_anticuacion_pedidas_a_fabrica = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_pedidas_a_fabrica = mediador.monto_pedidas_a_fabrica_agente(desde, hasta, "");
		if(_monto_pedidas_a_fabrica.first()!=null)
			monto_pedidas_a_fabrica = "$"+df_pesos.format(_monto_pedidas_a_fabrica.first());
		else
			monto_pedidas_a_fabrica = "";
		if(_monto_pedidas_a_fabrica.second()!=null)
			prom_monto_pedidas_a_fabrica = "$"+df_pesos.format(_monto_pedidas_a_fabrica.second());
		else
			prom_monto_pedidas_a_fabrica = "";
		if(_monto_pedidas_a_fabrica.third()!=null)
			max_monto_pedidas_a_fabrica = "$"+df_pesos.format(_monto_pedidas_a_fabrica.third());
		else
			max_monto_pedidas_a_fabrica = "";
		if(_monto_pedidas_a_fabrica.fourth()!=null)
			min_monto_pedidas_a_fabrica = "$"+df_pesos.format(_monto_pedidas_a_fabrica.fourth());
		else
			min_monto_pedidas_a_fabrica = "";
		
		cantidad_en_tranisto =  mediador.cantidad_en_transito_agente(desde, hasta, "");
		Triple<Double,Double,Double> anticuacion_en_transito = mediador.anticuacion_en_transito_agente(desde, hasta, "");
		if(anticuacion_en_transito.first()!=null)
			prom_anticuacion_en_transito = roundUp(anticuacion_en_transito.first());
		else
			prom_anticuacion_en_transito = "";
		if(anticuacion_en_transito.second()!=null)
			max_anticuacion_en_transito = roundUp(anticuacion_en_transito.second());
		else
			max_anticuacion_en_transito = "";
		if(anticuacion_en_transito.third()!=null)
			min_anticuacion_en_transito = roundUp(anticuacion_en_transito.third());
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
			prom_anticuacion_sin_enviar_agentes = roundUp(anticuacion_sin_enviar_agentes.first());
		else
			prom_anticuacion_sin_enviar_agentes = "";
		if(anticuacion_sin_enviar_agentes.second()!=null)
			max_anticuacion_sin_enviar_agentes = roundUp(anticuacion_sin_enviar_agentes.second());
		else
			max_anticuacion_sin_enviar_agentes = "";
		if(anticuacion_sin_enviar_agentes.third()!=null)
			min_anticuacion_sin_enviar_agentes = roundUp(anticuacion_sin_enviar_agentes.third());
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
			prom_anticuacion_enviadas_agentes = roundUp(anticuacion_enviado_a_agente.first());
		else
			prom_anticuacion_enviadas_agentes = "";
		if(anticuacion_enviado_a_agente.second()!=null)
			max_anticuacion_enviadas_agentes = roundUp(anticuacion_enviado_a_agente.second());
		else
			max_anticuacion_enviadas_agentes = "";
		if(anticuacion_enviado_a_agente.third()!=null)
			min_anticuacion_enviadas_agentes = roundUp(anticuacion_enviado_a_agente.third());
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
			prom_anticuacion_recibiadas_agentes = roundUp(anticuacion_recibidas_de_agente.first());
		else
			prom_anticuacion_recibiadas_agentes = "";
		if(anticuacion_recibidas_de_agente.second()!=null)
			max_anticuacion_recibiadas_agentes = roundUp(anticuacion_recibidas_de_agente.second());
		else
			max_anticuacion_recibiadas_agentes = "";
		if(anticuacion_recibidas_de_agente.third()!=null)
			min_anticuacion_recibiadas_agentes = roundUp(anticuacion_recibidas_de_agente.third());
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
			prom_anticuacion_recursadas = roundUp(anticuacion_recursadas.first());
		else
			prom_anticuacion_recursadas = "";
		if(anticuacion_recursadas.second()!=null)
			max_anticuacion_recursadas = roundUp(anticuacion_recursadas.second());
		else
			max_anticuacion_recursadas = "";
		if(anticuacion_recursadas.third()!=null)
			min_anticuacion_recursadas = roundUp(anticuacion_recursadas.third());
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
		
		cantidad_devueltas =  mediador.cantidad_devueltas_agente(desde, hasta, "");
		Triple<Double,Double,Double> anticuacion_devueltas = mediador.anticuacion_devueltas_agente(desde, hasta, "");
		if(anticuacion_devueltas.first()!=null)
			prom_anticuacion_devueltas = roundUp(anticuacion_devueltas.first());
		else
			prom_anticuacion_devueltas = "";
		if(anticuacion_devueltas.second()!=null)
			max_anticuacion_devueltas = roundUp(anticuacion_devueltas.second());
		else
			max_anticuacion_devueltas = "";
		if(anticuacion_devueltas.third()!=null)
			min_anticuacion_devueltas = roundUp(anticuacion_devueltas.third());
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
//		Tabla Piezas Pedidas a Fabrica	//
		modelo_tabla_piezas_pedidas_a_fabrica = new DefaultTableModel();
		datosTabla_piezas_pedidas_a_fabrica = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_pedidas_a_fabrica);//Q
		datosTabla_piezas_pedidas_a_fabrica.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_pedidas_a_fabrica);//X
		datosTabla_piezas_pedidas_a_fabrica.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_pedidas_a_fabrica);//MX
		datosTabla_piezas_pedidas_a_fabrica.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_pedidas_a_fabrica);//mX
		datosTabla_piezas_pedidas_a_fabrica.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_pedidas_a_fabrica);//R
		datosTabla_piezas_pedidas_a_fabrica.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_pedidas_a_fabrica);//XR
		datosTabla_piezas_pedidas_a_fabrica.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_pedidas_a_fabrica);//MR
		datosTabla_piezas_pedidas_a_fabrica.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
		row_min_monto.add(min_monto_pedidas_a_fabrica);//mR
		datosTabla_piezas_pedidas_a_fabrica.add(row_min_monto);
		modelo_tabla_piezas_pedidas_a_fabrica.setDataVector(datosTabla_piezas_pedidas_a_fabrica, nombreColumnas);
		modelo_tabla_piezas_pedidas_a_fabrica.fireTableStructureChanged();
		//		Fin Tabla Piezas Pedidas a Fabrica	//
		//		Tabla Piezas En Transito		//
		modelo_tabla_piezas_en_transito = new DefaultTableModel();
		datosTabla_piezas_en_transito = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_en_tranisto);//Q
		datosTabla_piezas_en_transito.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_en_transito);//X
		datosTabla_piezas_en_transito.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_en_transito);//MX
		datosTabla_piezas_en_transito.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_en_transito);//mX
		datosTabla_piezas_en_transito.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_en_transito);//R
		datosTabla_piezas_en_transito.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_en_transito);//XR
		datosTabla_piezas_en_transito.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_en_transito);//MR
		datosTabla_piezas_en_transito.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
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
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_sin_enviar_agentes);//X
		datosTabla_piezas_sin_enviar_agentes.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_sin_enviar_agentes);//MX
		datosTabla_piezas_sin_enviar_agentes.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_sin_enviar_agentes);//mX
		datosTabla_piezas_sin_enviar_agentes.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_sin_enviar_agentes);//R
		datosTabla_piezas_sin_enviar_agentes.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_sin_enviar_agentes);//XR
		datosTabla_piezas_sin_enviar_agentes.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_sin_enviar_agentes);//MR
		datosTabla_piezas_sin_enviar_agentes.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
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
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_enviadas_agentes);//X
		datosTabla_piezas_enviadas_agentes.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_enviadas_agentes);//MX
		datosTabla_piezas_enviadas_agentes.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_enviadas_agentes);//mX
		datosTabla_piezas_enviadas_agentes.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_enviadas_agentes);//R
		datosTabla_piezas_enviadas_agentes.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_enviadas_agentes);//XR
		datosTabla_piezas_enviadas_agentes.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_enviadas_agentes);//MR
		datosTabla_piezas_enviadas_agentes.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
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
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_recibiadas_agentes);//X
		datosTabla_piezas_recibiadas_agentes.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_recibiadas_agentes);//MX
		datosTabla_piezas_recibiadas_agentes.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_recibiadas_agentes);//mX
		datosTabla_piezas_recibiadas_agentes.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_recibiadas_agentes);//R
		datosTabla_piezas_recibiadas_agentes.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_recibiadas_agentes);//XR
		datosTabla_piezas_recibiadas_agentes.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_recibiadas_agentes);//MR
		datosTabla_piezas_recibiadas_agentes.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
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
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_recursadas);//X
		datosTabla_piezas_recursadas.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_recursadas);//MX
		datosTabla_piezas_recursadas.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_recursadas);//mX
		datosTabla_piezas_recursadas.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_recursadas);//R
		datosTabla_piezas_recursadas.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_recursadas);//XR
		datosTabla_piezas_recursadas.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_recursadas);//MR
		datosTabla_piezas_recursadas.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
		row_min_monto.add(min_monto_recursadas);//mR
		datosTabla_piezas_recursadas.add(row_min_monto);
		modelo_tabla_piezas_recursadas.setDataVector(datosTabla_piezas_recursadas, nombreColumnas);
		modelo_tabla_piezas_recursadas.fireTableStructureChanged();
		//		Fin Tabla Piezas Recursadas		//	
		//		Tabla Piezas Devueltas		//
		modelo_tabla_piezas_devueltas = new DefaultTableModel();
		datosTabla_piezas_devueltas = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_devueltas);//Q
		datosTabla_piezas_devueltas.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_devueltas);//X
		datosTabla_piezas_devueltas.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_devueltas);//MX
		datosTabla_piezas_devueltas.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_devueltas);//mX
		datosTabla_piezas_devueltas.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_devueltas);//R
		datosTabla_piezas_devueltas.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_devueltas);//XR
		datosTabla_piezas_devueltas.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_devueltas);//MR
		datosTabla_piezas_devueltas.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
		row_min_monto.add(min_monto_devueltas);//mR
		datosTabla_piezas_devueltas.add(row_min_monto);
		modelo_tabla_piezas_devueltas.setDataVector(datosTabla_piezas_devueltas, nombreColumnas);
		modelo_tabla_piezas_devueltas.fireTableStructureChanged();
		//		Fin Tabla Piezas Aprobada Devolucion		//
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0,1382,768);
		//setExtendedState(Frame.MAXIMIZED_BOTH);
		//setLocationRelativeTo(null);
		setTitle("REPORTES AGENTES");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/edit_pedido_entidad.png")));
		
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBounds(new Rectangle(0, 0, 1366, 768));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JPanel panelFechas = new TransparentPanel();
		panelFechas.setLayout(null);
		panelFechas.setBounds(20, 0, 1300, 40);
		contentPane.add(panelFechas);
		
		JLabel lblDesdeFReclamo = new JLabel("Desde F. Pedido");
		lblDesdeFReclamo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblDesdeFReclamo.setFont(new Font("Tahoma", Font.BOLD, 11));
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
		lblHastaFDevolucion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblHastaFDevolucion.setFont(new Font("Tahoma", Font.BOLD, 11));
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
		btnFiltrar.setBounds(1180, 9, 110, 20);
		panelFechas.add(btnFiltrar);
		
		JLabel lblNombreDelAgente = new JLabel("Nombre del Agente");
		lblNombreDelAgente.setBounds(660, 10, 121, 20);
		panelFechas.add(lblNombreDelAgente);
		lblNombreDelAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNombreDelAgente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombreDelAgente.setHorizontalAlignment(SwingConstants.CENTER);
		
		cBAgentes = new JComboBox<String>();
		cBAgentes.setBounds(800, 10, 370, 20);
		panelFechas.add(cBAgentes);
		cBAgentes.setModel(new DefaultComboBoxModel<String>(agentes));
		
		JPanel panelReportes = new TransparentPanel();
		panelReportes.setLayout(null);
		panelReportes.setBounds(33, 45, 1300, 645);
		contentPane.add(panelReportes);
		
		panelPiezasPedidasAFabrica = new TransparentPanel();
		panelPiezasPedidasAFabrica.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelPiezasPedidasAFabrica.setLayout(null);
		panelPiezasPedidasAFabrica.setBounds(66, 11, 345, 200);
		panelReportes.add(panelPiezasPedidasAFabrica);
		
		modelo_tabla_piezas_pedidas_a_fabrica = new DefaultTableModel(datosTabla_piezas_pedidas_a_fabrica, nombreColumnas);
		tablaPiezasPedidasAFabrica = new JTable(modelo_tabla_piezas_pedidas_a_fabrica) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tablaPiezasPedidasAFabrica.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPiezasPedidasAFabrica.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaPiezasPedidasAFabrica.setBounds(145, 20, 200, 130);
		panelPiezasPedidasAFabrica.add(tablaPiezasPedidasAFabrica);
		for(int i = 0; i < tablaPiezasPedidasAFabrica.getColumnCount(); i++) {
			tablaPiezasPedidasAFabrica.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasPedidasAFabrica.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		
		JLabel lblPiezasPedidasAFabricas = new JLabel("Stock Pedido a Fabrica");
		lblPiezasPedidasAFabricas.setToolTipText("Piezas pedidas a fabrica son aquellas que tienen fecha de pedido a fabrica.");
		lblPiezasPedidasAFabricas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPiezasPedidasAFabricas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasPedidasAFabricas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasPedidasAFabricas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasPedidasAFabricas.setBounds(145, 0, 200, 20);
		panelPiezasPedidasAFabrica.add(lblPiezasPedidasAFabricas);
		
		btnVerCasosPiezasConOT = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasConOT.setIcon(new ImageIcon(GUIReporteRapidoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnVerCasosPiezasConOT.setText("Ver Casos");
		btnVerCasosPiezasConOT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosPedidas_a_Fabrica();
			}
		});
		btnVerCasosPiezasConOT.setBounds(72, 160, 200, 30);
		panelPiezasPedidasAFabrica.add(btnVerCasosPiezasConOT);
		
		JLabel label = new JLabel("Valor Stock");
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label.setBounds(0, 85, 145, 66);
		panelPiezasPedidasAFabrica.add(label);
		
		JLabel label_1 = new JLabel("Anticuacion de Stock");
		label_1.setHorizontalTextPosition(SwingConstants.CENTER);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_1.setAutoscrolls(true);
		label_1.setBounds(0, 37, 145, 48);
		panelPiezasPedidasAFabrica.add(label_1);
		
		JLabel label_2 = new JLabel("Stock");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_2.setBounds(0, 20, 145, 17);
		panelPiezasPedidasAFabrica.add(label_2);
		
		panelPiezasEnTransito = new TransparentPanel();
		panelPiezasEnTransito.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelPiezasEnTransito.setLayout(null);
		panelPiezasEnTransito.setBounds(477, 11, 345, 200);
		panelReportes.add(panelPiezasEnTransito);
		
		JLabel lblFOt = new JLabel("F. Pedido Fabrica");
		lblFOt.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFOt.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFOt.setHorizontalAlignment(SwingConstants.CENTER);
		lblFOt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFOt.setBounds(0, 0, 145, 20);
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
		tablaPiezasEnTransito.setBounds(145, 20, 200, 130);
		panelPiezasEnTransito.add(tablaPiezasEnTransito);
		
		JLabel lblPiezasConOt = new JLabel("Stock En Transito");
		lblPiezasConOt.setToolTipText("Piezas en transito son aquellas que poseen fecha de pedido a fabrica pero no poseen fecha de recepcion de fabrica.");
		lblPiezasConOt.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPiezasConOt.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasConOt.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasConOt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasConOt.setBounds(145, 0, 200, 20);
		panelPiezasEnTransito.add(lblPiezasConOt);
		
		btnVerCasosPiezasEnTransito = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasEnTransito.setIcon(new ImageIcon(GUIReporteRapidoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnVerCasosPiezasEnTransito.setText("Ver Casos");
		btnVerCasosPiezasEnTransito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosEnTransito();
			}
		});
		btnVerCasosPiezasEnTransito.setBounds(72, 160, 200, 30);
		panelPiezasEnTransito.add(btnVerCasosPiezasEnTransito);
		
		JLabel label_3 = new JLabel("Valor Stock");
		label_3.setHorizontalTextPosition(SwingConstants.CENTER);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_3.setBounds(0, 84, 145, 66);
		panelPiezasEnTransito.add(label_3);
		
		JLabel label_4 = new JLabel("Anticuacion de Stock");
		label_4.setHorizontalTextPosition(SwingConstants.CENTER);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_4.setAutoscrolls(true);
		label_4.setBounds(0, 36, 145, 48);
		panelPiezasEnTransito.add(label_4);
		
		JLabel label_5 = new JLabel("Stock");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_5.setBounds(0, 19, 145, 17);
		panelPiezasEnTransito.add(label_5);
		
		panelPiezasSinTurno = new TransparentPanel();
		panelPiezasSinTurno.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelPiezasSinTurno.setLayout(null);
		panelPiezasSinTurno.setBounds(888, 11, 345, 200);
		panelReportes.add(panelPiezasSinTurno);
		
		JLabel lblFrecepcion = new JLabel("F. Recepcion Fabrica");
		lblFrecepcion.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFrecepcion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFrecepcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrecepcion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFrecepcion.setBounds(0, 0, 145, 20);
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
		tablaPiezasSinEnviarAgentes.setBounds(145, 20, 200, 130);
		panelPiezasSinTurno.add(tablaPiezasSinEnviarAgentes);
		
		JLabel lblPiezasSinEnviarAgentes = new JLabel("Stock Sin Enviar Agente/s");
		lblPiezasSinEnviarAgentes.setToolTipText("Piezas  sin enviar a agente son las que poseen fecha de recepcion a fabrica pero no poseen fecha de envio al agente.");
		lblPiezasSinEnviarAgentes.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPiezasSinEnviarAgentes.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasSinEnviarAgentes.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasSinEnviarAgentes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasSinEnviarAgentes.setBounds(145, 0, 200, 20);
		panelPiezasSinTurno.add(lblPiezasSinEnviarAgentes);
		
		btnVerCasosPiezasSinEnviarAgentes = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasSinEnviarAgentes.setIcon(new ImageIcon(GUIReporteRapidoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnVerCasosPiezasSinEnviarAgentes.setText("Ver Casos");
		btnVerCasosPiezasSinEnviarAgentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosSinEnviarAgente();
			}
		});
		btnVerCasosPiezasSinEnviarAgentes.setBounds(72, 160, 200, 30);
		panelPiezasSinTurno.add(btnVerCasosPiezasSinEnviarAgentes);
		
		JLabel label_6 = new JLabel("Valor Stock");
		label_6.setHorizontalTextPosition(SwingConstants.CENTER);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_6.setBounds(0, 84, 145, 66);
		panelPiezasSinTurno.add(label_6);
		
		JLabel label_7 = new JLabel("Anticuacion de Stock");
		label_7.setHorizontalTextPosition(SwingConstants.CENTER);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_7.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_7.setAutoscrolls(true);
		label_7.setBounds(0, 36, 145, 48);
		panelPiezasSinTurno.add(label_7);
		
		JLabel label_8 = new JLabel("Stock");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_8.setBounds(0, 19, 145, 17);
		panelPiezasSinTurno.add(label_8);
		
		panelPiezasEnviadasAgentes = new TransparentPanel();
		panelPiezasEnviadasAgentes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelPiezasEnviadasAgentes.setLayout(null);
		panelPiezasEnviadasAgentes.setBounds(66, 222, 345, 200);
		panelReportes.add(panelPiezasEnviadasAgentes);
		
		JLabel lblFechaEnvioAgente = new JLabel("F. Envio Agente");
		lblFechaEnvioAgente.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFechaEnvioAgente.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFechaEnvioAgente.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaEnvioAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFechaEnvioAgente.setBounds(0, 0, 145, 20);
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
		tablaPiezasEnviadasAgente.setBounds(145, 20, 200, 130);
		panelPiezasEnviadasAgentes.add(tablaPiezasEnviadasAgente);
		
		JLabel lblPiezasEnviadasAgentes = new JLabel("Stock Enviado a Agente/s");
		lblPiezasEnviadasAgentes.setToolTipText("Piezas  enviadas a agente son las que poseen fecha de envio a agente pero no poseen fecha de recepcion del agente.");
		lblPiezasEnviadasAgentes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPiezasEnviadasAgentes.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasEnviadasAgentes.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasEnviadasAgentes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasEnviadasAgentes.setBounds(145, 0, 200, 20);
		panelPiezasEnviadasAgentes.add(lblPiezasEnviadasAgentes);
		
		btnVerCasosPiezasEnviadasAgentes = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasEnviadasAgentes.setIcon(new ImageIcon(GUIReporteRapidoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnVerCasosPiezasEnviadasAgentes.setText("Ver Casos");
		btnVerCasosPiezasEnviadasAgentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosEnviadasAgente();
			}
		});
		btnVerCasosPiezasEnviadasAgentes.setBounds(72, 160, 200, 30);
		panelPiezasEnviadasAgentes.add(btnVerCasosPiezasEnviadasAgentes);
		
		JLabel label_18 = new JLabel("Valor Stock");
		label_18.setHorizontalTextPosition(SwingConstants.CENTER);
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_18.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_18.setBounds(0, 84, 145, 66);
		panelPiezasEnviadasAgentes.add(label_18);
		
		JLabel label_19 = new JLabel("Anticuacion de Stock");
		label_19.setHorizontalTextPosition(SwingConstants.CENTER);
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_19.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_19.setAutoscrolls(true);
		label_19.setBounds(0, 36, 145, 48);
		panelPiezasEnviadasAgentes.add(label_19);
		
		JLabel label_20 = new JLabel("Stock");
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		label_20.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_20.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_20.setBounds(0, 19, 145, 17);
		panelPiezasEnviadasAgentes.add(label_20);
		
		panelPiezasRecibidasAgentes = new TransparentPanel();
		panelPiezasRecibidasAgentes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelPiezasRecibidasAgentes.setLayout(null);
		panelPiezasRecibidasAgentes.setBounds(477, 222, 345, 200);
		panelReportes.add(panelPiezasRecibidasAgentes);
		
		JLabel lblFRecepcionAgente = new JLabel("F. Recepcion Agente");
		lblFRecepcionAgente.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFRecepcionAgente.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFRecepcionAgente.setHorizontalAlignment(SwingConstants.CENTER);
		lblFRecepcionAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFRecepcionAgente.setBounds(0, 0, 145, 20);
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
		tablaPiezasRecibidasAgentes.setBounds(145, 20, 200, 130);
		panelPiezasRecibidasAgentes.add(tablaPiezasRecibidasAgentes);
		
		JLabel lblPiezasRecibidasAgentes = new JLabel("Stock Recibido de Agente/s");
		lblPiezasRecibidasAgentes.setToolTipText("Piezas  recibidas de agente son las que poseen fecha de recepcion de agente pero no poseen fecha de recursado.");
		lblPiezasRecibidasAgentes.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPiezasRecibidasAgentes.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasRecibidasAgentes.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasRecibidasAgentes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasRecibidasAgentes.setBounds(145, 0, 200, 20);
		panelPiezasRecibidasAgentes.add(lblPiezasRecibidasAgentes);
		
		btnVerCasosPiezasRecibidasAgentes = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasRecibidasAgentes.setIcon(new ImageIcon(GUIReporteRapidoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnVerCasosPiezasRecibidasAgentes.setText("Ver Casos");
		btnVerCasosPiezasRecibidasAgentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosRecibidasDeAgente();
			}
		});
		btnVerCasosPiezasRecibidasAgentes.setBounds(72, 160, 200, 30);
		panelPiezasRecibidasAgentes.add(btnVerCasosPiezasRecibidasAgentes);
		
		JLabel label_9 = new JLabel("Valor Stock");
		label_9.setHorizontalTextPosition(SwingConstants.CENTER);
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_9.setBounds(0, 85, 145, 66);
		panelPiezasRecibidasAgentes.add(label_9);
		
		JLabel label_10 = new JLabel("Anticuacion de Stock");
		label_10.setHorizontalTextPosition(SwingConstants.CENTER);
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_10.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_10.setAutoscrolls(true);
		label_10.setBounds(0, 37, 145, 48);
		panelPiezasRecibidasAgentes.add(label_10);
		
		JLabel label_11 = new JLabel("Stock");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_11.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_11.setBounds(0, 20, 145, 17);
		panelPiezasRecibidasAgentes.add(label_11);
		
		panelPiezasRecursadas = new TransparentPanel();
		panelPiezasRecursadas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelPiezasRecursadas.setLayout(null);
		panelPiezasRecursadas.setBounds(888, 222, 345, 200);
		panelReportes.add(panelPiezasRecursadas);
		
		JLabel lblFrecurso = new JLabel("F. Recurso");
		lblFrecurso.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFrecurso.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFrecurso.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrecurso.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFrecurso.setBounds(0, 0, 145, 20);
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
		tablaPiezasRecursadas.setBounds(145, 20, 200, 130);
		panelPiezasRecursadas.add(tablaPiezasRecursadas);
		
		JLabel lblPiezasRecursadas = new JLabel("Stock Recursado");
		lblPiezasRecursadas.setToolTipText("Piezas  recursadas son las que poseen fecha de recursado pero no poseen fecha de envio a fabrica.");
		lblPiezasRecursadas.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPiezasRecursadas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasRecursadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasRecursadas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasRecursadas.setBounds(145, 0, 200, 20);
		panelPiezasRecursadas.add(lblPiezasRecursadas);
		
		btnVerCasosPiezasRecursadas = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasRecursadas.setIcon(new ImageIcon(GUIReporteRapidoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnVerCasosPiezasRecursadas.setText("Ver Casos");
		btnVerCasosPiezasRecursadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosRecursadas();
			}
		});
		btnVerCasosPiezasRecursadas.setBounds(72, 160, 200, 30);
		panelPiezasRecursadas.add(btnVerCasosPiezasRecursadas);
		
		JLabel label_12 = new JLabel("Valor Stock");
		label_12.setHorizontalTextPosition(SwingConstants.CENTER);
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_12.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_12.setBounds(0, 85, 145, 66);
		panelPiezasRecursadas.add(label_12);
		
		JLabel label_13 = new JLabel("Anticuacion de Stock");
		label_13.setHorizontalTextPosition(SwingConstants.CENTER);
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_13.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_13.setAutoscrolls(true);
		label_13.setBounds(0, 37, 145, 48);
		panelPiezasRecursadas.add(label_13);
		
		JLabel label_14 = new JLabel("Stock");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_14.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_14.setBounds(0, 20, 145, 17);
		panelPiezasRecursadas.add(label_14);

		panelPiezasDevueltas = new TransparentPanel();
		panelPiezasDevueltas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelPiezasDevueltas.setLayout(null);
		panelPiezasDevueltas.setBounds(477, 433, 345, 200);
		panelReportes.add(panelPiezasDevueltas);
		
		JLabel lblFdevolucion = new JLabel("F. Devolucion Fabrica");
		lblFdevolucion.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFdevolucion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFdevolucion.setHorizontalAlignment(SwingConstants.CENTER);
		lblFdevolucion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFdevolucion.setBounds(0, 0, 145, 20);
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
		tablaPiezasDevueltas.setBounds(145, 20, 200, 130);
		panelPiezasDevueltas.add(tablaPiezasDevueltas);
		
		JLabel lblPiezasDevueltas = new JLabel("Stock Devuelto");
		lblPiezasDevueltas.setToolTipText("Piezas devueltas son aquellas que poseen fecha de devolucion a fabrica.");
		lblPiezasDevueltas.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPiezasDevueltas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasDevueltas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasDevueltas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasDevueltas.setBounds(145, 0, 200, 20);
		panelPiezasDevueltas.add(lblPiezasDevueltas);
		
		btnVerCasosPiezasDevueltas = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasDevueltas.setIcon(new ImageIcon(GUIReporteRapidoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnVerCasosPiezasDevueltas.setText("Ver Casos");
		btnVerCasosPiezasDevueltas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosDevueltasAgente();
			}
		});
		btnVerCasosPiezasDevueltas.setBounds(72, 160, 200, 30);
		panelPiezasDevueltas.add(btnVerCasosPiezasDevueltas);
		
		JLabel label_15 = new JLabel("Valor Stock");
		label_15.setHorizontalTextPosition(SwingConstants.CENTER);
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_15.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_15.setBounds(0, 84, 145, 66);
		panelPiezasDevueltas.add(label_15);
		
		JLabel label_16 = new JLabel("Anticuacion de Stock");
		label_16.setHorizontalTextPosition(SwingConstants.CENTER);
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_16.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_16.setAutoscrolls(true);
		label_16.setBounds(0, 36, 145, 48);
		panelPiezasDevueltas.add(label_16);
		
		JLabel label_17 = new JLabel("Stock");
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_17.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_17.setBounds(0, 19, 145, 17);
		panelPiezasDevueltas.add(label_17);
		
		btnVolver = new GlossyButton("VOLVER",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUEGREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVolver.setText("Volver");
		btnVolver.setBounds(583, 685, 200, 30);
		contentPane.add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVolver.setIcon(new ImageIcon(GUIReporteRapidoAgente.class.getResource("/cliente/Resources/Icons/back.png")));
		
		setVisible(true);
	}


	protected void verCasosPedidas_a_Fabrica() {
		java.sql.Date desde = null;
		String nombre_agente = cBAgentes.getSelectedItem().toString();
		if(dCDesdeFReclamo.getDate()!=null)
			desde = new java.sql.Date(dCDesdeFReclamo.getDate().getTime());
		java.sql.Date hasta = null;
		if(dCHastaFDevolucion.getDate()!=null)
			hasta = new java.sql.Date(dCHastaFDevolucion.getDate().getTime());
		mediador.verCasosPedidas_a_FabricaAgente(desde, hasta, nombre_agente);
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
		cantidad_pedidas_a_fabrica =  mediador.cantidad_pedida_a_fabrica_agente(desde,hasta,filtro_agente);
		Triple<Double,Double,Double> anticuacion_con_ot = mediador.anticuacion_pedidas_a_fabrica_agente(desde, hasta, filtro_agente);
		if(anticuacion_con_ot.first()!=null)
			prom_anticuacion_pedidas_a_fabrica = roundUp(anticuacion_con_ot.first());
		else
			prom_anticuacion_pedidas_a_fabrica = "";
		if(anticuacion_con_ot.second()!=null)
			max_anticuacion_pedidas_a_fabrica = roundUp(anticuacion_con_ot.second());
		else
			max_anticuacion_pedidas_a_fabrica = "";
		if(anticuacion_con_ot.third()!=null)
			min_anticuacion_pedidas_a_fabrica = roundUp(anticuacion_con_ot.third());
		else
			min_anticuacion_pedidas_a_fabrica = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_con_ot = mediador.monto_pedidas_a_fabrica_agente(desde, hasta, filtro_agente);
		if(_monto_con_ot.first()!=null)
			monto_pedidas_a_fabrica = "$"+df_pesos.format(_monto_con_ot.first());
		else
			monto_pedidas_a_fabrica = "";
		if(_monto_con_ot.second()!=null)
			prom_monto_pedidas_a_fabrica = "$"+df_pesos.format(_monto_con_ot.second());
		else
			prom_monto_pedidas_a_fabrica = "";
		if(_monto_con_ot.third()!=null)
			max_monto_pedidas_a_fabrica = "$"+df_pesos.format(_monto_con_ot.third());
		else
			max_monto_pedidas_a_fabrica = "";
		if(_monto_con_ot.fourth()!=null)
			min_monto_pedidas_a_fabrica = "$"+df_pesos.format(_monto_con_ot.fourth());
		else
			min_monto_pedidas_a_fabrica = "";
		
		cantidad_en_tranisto =  mediador.cantidad_en_transito_agente(desde, hasta, filtro_agente);
		Triple<Double,Double,Double> anticuacion_en_transito = mediador.anticuacion_en_transito_agente(desde, hasta, filtro_agente);
		if(anticuacion_en_transito.first()!=null)
			prom_anticuacion_en_transito = roundUp(anticuacion_en_transito.first());
		else
			prom_anticuacion_en_transito = "";
		if(anticuacion_en_transito.second()!=null)
			max_anticuacion_en_transito = roundUp(anticuacion_en_transito.second());
		else
			max_anticuacion_en_transito = "";
		if(anticuacion_en_transito.third()!=null)
			min_anticuacion_en_transito = roundUp(anticuacion_en_transito.third());
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
			prom_anticuacion_sin_enviar_agentes = roundUp(anticuacion_sin_enviar_agentes.first());
		else
			prom_anticuacion_sin_enviar_agentes = "";
		if(anticuacion_sin_enviar_agentes.second()!=null)
			max_anticuacion_sin_enviar_agentes = roundUp(anticuacion_sin_enviar_agentes.second());
		else
			max_anticuacion_sin_enviar_agentes = "";
		if(anticuacion_sin_enviar_agentes.third()!=null)
			min_anticuacion_sin_enviar_agentes =roundUp( anticuacion_sin_enviar_agentes.third());
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
			prom_anticuacion_enviadas_agentes = roundUp(anticuacion_enviadas_agentes.first());
		else
			prom_anticuacion_enviadas_agentes = "";
		if(anticuacion_enviadas_agentes.second()!=null)
			max_anticuacion_enviadas_agentes = roundUp(anticuacion_enviadas_agentes.second());
		else
			max_anticuacion_enviadas_agentes = "";
		if(anticuacion_enviadas_agentes.third()!=null)
			min_anticuacion_enviadas_agentes = roundUp(anticuacion_enviadas_agentes.third());
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
			prom_anticuacion_recibiadas_agentes = roundUp(anticuacion_recibidas_agentes.first());
		else
			prom_anticuacion_recibiadas_agentes = "";
		if(anticuacion_recibidas_agentes.second()!=null)
			max_anticuacion_recibiadas_agentes = roundUp(anticuacion_recibidas_agentes.second());
		else
			max_anticuacion_recibiadas_agentes = "";
		if(anticuacion_recibidas_agentes.third()!=null)
			min_anticuacion_recibiadas_agentes = roundUp(anticuacion_recibidas_agentes.third());
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
			prom_anticuacion_recursadas = roundUp(anticuacion_recursadas.first());
		else
			prom_anticuacion_recursadas = "";
		if(anticuacion_recursadas.second()!=null)
			max_anticuacion_recursadas = roundUp(anticuacion_recursadas.second());
		else
			max_anticuacion_recursadas = "";
		if(anticuacion_recursadas.third()!=null)
			min_anticuacion_recursadas = roundUp(anticuacion_recursadas.third());
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
		
		cantidad_devueltas =  mediador.cantidad_devueltas_agente(desde, hasta, filtro_agente);
		Triple<Double,Double,Double> anticuacion_devueltas = mediador.anticuacion_devueltas_agente(desde, hasta, filtro_agente);
		if(anticuacion_devueltas.first()!=null)
			prom_anticuacion_devueltas = roundUp(anticuacion_devueltas.first());
		else
			prom_anticuacion_devueltas = "";
		if(anticuacion_devueltas.second()!=null)
			max_anticuacion_devueltas = roundUp(anticuacion_devueltas.second());
		else
			max_anticuacion_devueltas = "";
		if(anticuacion_devueltas.third()!=null)
			min_anticuacion_devueltas = roundUp(anticuacion_devueltas.third());
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

	private void filtrar() {
		String nombre_agente = cBAgentes.getSelectedItem().toString();
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
		modelo_tabla_piezas_pedidas_a_fabrica = new DefaultTableModel();
		datosTabla_piezas_pedidas_a_fabrica = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_pedidas_a_fabrica);//Q
		datosTabla_piezas_pedidas_a_fabrica.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_pedidas_a_fabrica);//X
		datosTabla_piezas_pedidas_a_fabrica.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_pedidas_a_fabrica);//MX
		datosTabla_piezas_pedidas_a_fabrica.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_pedidas_a_fabrica);//mX
		datosTabla_piezas_pedidas_a_fabrica.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_pedidas_a_fabrica);//R
		datosTabla_piezas_pedidas_a_fabrica.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_pedidas_a_fabrica);//XR
		datosTabla_piezas_pedidas_a_fabrica.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_pedidas_a_fabrica);//MR
		datosTabla_piezas_pedidas_a_fabrica.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
		row_min_monto.add(min_monto_pedidas_a_fabrica);//mR
		datosTabla_piezas_pedidas_a_fabrica.add(row_min_monto);
		modelo_tabla_piezas_pedidas_a_fabrica.setDataVector(datosTabla_piezas_pedidas_a_fabrica, nombreColumnas);
		modelo_tabla_piezas_pedidas_a_fabrica.fireTableStructureChanged();
		tablaPiezasPedidasAFabrica.setModel(modelo_tabla_piezas_pedidas_a_fabrica);
		for(int i = 0; i < tablaPiezasPedidasAFabrica.getColumnCount(); i++) {
			tablaPiezasPedidasAFabrica.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasPedidasAFabrica.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
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
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_en_transito);//X
		datosTabla_piezas_en_transito.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_en_transito);//MX
		datosTabla_piezas_en_transito.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_en_transito);//mX
		datosTabla_piezas_en_transito.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_en_transito);//R
		datosTabla_piezas_en_transito.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_en_transito);//XR
		datosTabla_piezas_en_transito.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_en_transito);//MR
		datosTabla_piezas_en_transito.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
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
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_sin_enviar_agentes);//X
		datosTabla_piezas_sin_enviar_agentes.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_sin_enviar_agentes);//MX
		datosTabla_piezas_sin_enviar_agentes.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_sin_enviar_agentes);//mX
		datosTabla_piezas_sin_enviar_agentes.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_sin_enviar_agentes);//R
		datosTabla_piezas_sin_enviar_agentes.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_sin_enviar_agentes);//XR
		datosTabla_piezas_sin_enviar_agentes.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_sin_enviar_agentes);//MR
		datosTabla_piezas_sin_enviar_agentes.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
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
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_enviadas_agentes);//X
		datosTabla_piezas_enviadas_agentes.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_enviadas_agentes);//MX
		datosTabla_piezas_enviadas_agentes.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_enviadas_agentes);//mX
		datosTabla_piezas_enviadas_agentes.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_enviadas_agentes);//R
		datosTabla_piezas_enviadas_agentes.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_enviadas_agentes);//XR
		datosTabla_piezas_enviadas_agentes.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_enviadas_agentes);//MR
		datosTabla_piezas_enviadas_agentes.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
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
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_recibiadas_agentes);//X
		datosTabla_piezas_recibiadas_agentes.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_recibiadas_agentes);//MX
		datosTabla_piezas_recibiadas_agentes.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_recibiadas_agentes);//mX
		datosTabla_piezas_recibiadas_agentes.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_recibiadas_agentes);//R
		datosTabla_piezas_recibiadas_agentes.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_recibiadas_agentes);//XR
		datosTabla_piezas_recibiadas_agentes.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_recibiadas_agentes);//MR
		datosTabla_piezas_recibiadas_agentes.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
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
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_recursadas);//X
		datosTabla_piezas_recursadas.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_recursadas);//MX
		datosTabla_piezas_recursadas.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_recursadas);//mX
		datosTabla_piezas_recursadas.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_recursadas);//R
		datosTabla_piezas_recursadas.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_recursadas);//XR
		datosTabla_piezas_recursadas.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_recursadas);//MR
		datosTabla_piezas_recursadas.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
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
		//		Tabla Piezas Devueltas		//
		modelo_tabla_piezas_devueltas = new DefaultTableModel();
		datosTabla_piezas_devueltas = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_devueltas);//Q
		datosTabla_piezas_devueltas.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_devueltas);//X
		datosTabla_piezas_devueltas.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_devueltas);//MX
		datosTabla_piezas_devueltas.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_devueltas);//mX
		datosTabla_piezas_devueltas.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_devueltas);//R
		datosTabla_piezas_devueltas.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_devueltas);//XR
		datosTabla_piezas_devueltas.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_devueltas);//MR
		datosTabla_piezas_devueltas.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
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
