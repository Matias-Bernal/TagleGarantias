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

import javax.swing.ImageIcon;
import javax.swing.JButton;
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


public class GUIReporteRapidoEntidad extends JFrame {

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	
	private JPanel contentPane;
	
	private JDateChooser dCDesdeFPedido;
	private JButton btnClearFReclamo;
	private JDateChooser dCHastaFDevolucion;
	private JButton btnClearFDevolucion;
	private JButton btnFiltrar;
	
	private Vector<Integer> anchos_tabla;
	private Vector<String> nombreColumnas;
	
	private JTable tablaPiezasPedidas_a_Fabrica;
	private DefaultTableModel modelo_tabla_piezas_pedidas_a_fabrica;
	private Vector<Vector<String>> datosTabla_pedidas_a_fabrica;
	private JButton btnVerCasosPedidas_a_Fabrica;
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
	
	private JTable tablaPiezasSinTurno;
	private DefaultTableModel modelo_tabla_piezas_sin_turno;
	private Vector<Vector<String>> datosTabla_piezas_sin_turno;
	private JButton btnVerCasosPiezasSinTurno;
	private String cantidad_sin_turno;
	private String prom_anticuacion_sin_turno;
	private String max_anticuacion_sin_turno;
	private String min_anticuacion_sin_turno;
	private String monto_sin_turno;
	private String prom_monto_sin_turno;
	private String max_monto_sin_turno;
	private String min_monto_sin_turno;
	
	private JTable tablaPiezasConTurno;
	private DefaultTableModel modelo_tabla_piezas_con_turno;
	private Vector<Vector<String>> datosTabla_piezas_con_turno;
	private JButton btnVerCasosPiezasConTurno;
	private String cantidad_con_turno;
	private String prom_anticuacion_con_turno;
	private String max_anticuacion_con_turno;
	private String min_anticuacion_con_turno;
	private String monto_con_turno;
	private String prom_monto_con_turno;
	private String max_monto_con_turno;
	private String min_monto_con_turno;
	
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
	private JPanel panelPiezasPedidas_a_Fabrica;
	private JPanel panelPiezasConTurno;
	private JPanel panelPiezasEnTransito;
	private JPanel panelPiezasSinTurno;
	private JPanel panelPiezasRecursadas;
	private JPanel panelPiezasDevueltas;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_11;
	private JLabel label_12;
	private JLabel label_13;
	private JLabel label_14;

	public GUIReporteRapidoEntidad(MediadorReportes mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
		completarCampos();
	}

	@SuppressWarnings({ "static-access" })
	private void completarCampos() {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		c.set(c.DAY_OF_MONTH,1);
		c.add(c.MONTH, -2);
		dCDesdeFPedido.setDate(c.getTime());
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
	
	@SuppressWarnings({ "static-access" })
	private void cargarDatos() {
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
		
		DecimalFormat df = new DecimalFormat("0.00"); 
		
		Calendar desdeCalendar = Calendar.getInstance();
		desdeCalendar.setFirstDayOfWeek(desdeCalendar.SUNDAY);
		desdeCalendar.add(desdeCalendar.MONTH, -2);
		desdeCalendar.set(desdeCalendar.DAY_OF_MONTH,1);
		java.sql.Date desde = new java.sql.Date(desdeCalendar.getTime().getTime());
		
		Calendar hastaCalendar = Calendar.getInstance();
		hastaCalendar.setFirstDayOfWeek(hastaCalendar.SUNDAY);
		hastaCalendar.set(hastaCalendar.DAY_OF_MONTH,hastaCalendar.getMaximum(hastaCalendar.DAY_OF_MONTH));		
		java.sql.Date hasta = new java.sql.Date(hastaCalendar.getTime().getTime());
							
		cantidad_pedidas_a_fabrica =  mediador.cantidad_pedidas_a_fabrica_entidad(desde,hasta);
		Triple<Double,Double,Double> anticuacion_pedidas_a_fabrica = mediador.anticuacion_pedidas_a_fabrica_entidad(desde,hasta);
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
		
		Cuadruple<Double,Double,Double,Double> _monto_pedidas_a_fabrica = mediador.monto_pedidas_a_fabrica_entidad(desde, hasta);
		if(_monto_pedidas_a_fabrica.first()!=null)
			monto_pedidas_a_fabrica = "$"+df.format(_monto_pedidas_a_fabrica.first());
		else
			monto_pedidas_a_fabrica = "";
		if(_monto_pedidas_a_fabrica.second()!=null)
			prom_monto_pedidas_a_fabrica = "$"+df.format(_monto_pedidas_a_fabrica.second());
		else
			prom_monto_pedidas_a_fabrica = "";
		if(_monto_pedidas_a_fabrica.third()!=null)
			max_monto_pedidas_a_fabrica = "$"+df.format(_monto_pedidas_a_fabrica.third());
		else
			max_monto_pedidas_a_fabrica = "";
		if(_monto_pedidas_a_fabrica.fourth()!=null)
			min_monto_pedidas_a_fabrica = "$"+df.format(_monto_pedidas_a_fabrica.fourth());
		else
			min_monto_pedidas_a_fabrica = "";
		
		cantidad_en_tranisto =  mediador.cantidad_en_transito_entidad(desde,hasta);
		Triple<Double,Double,Double> anticuacion_en_transito = mediador.anticuacion_en_transito_entidad(desde,hasta);
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
		
		Cuadruple<Double,Double,Double,Double> _monto_en_transito = mediador.monto_en_transito_entidad(desde, hasta);
		if(_monto_en_transito.first()!=null)
			monto_en_transito = "$"+df.format(_monto_en_transito.first());
		else
			monto_en_transito = "";
		if(_monto_en_transito.second()!=null)
			prom_monto_en_transito = "$"+df.format(_monto_en_transito.second());
		else
			prom_monto_en_transito = "";
		if(_monto_en_transito.third()!=null)
			max_monto_en_transito = "$"+df.format(_monto_en_transito.third());
		else
			max_monto_en_transito = "";
		if(_monto_en_transito.fourth()!=null)
			min_monto_en_transito = "$"+df.format(_monto_en_transito.fourth());
		else
			min_monto_en_transito = "";
		
		cantidad_sin_turno =  mediador.cantidad_sin_turno(desde,hasta);
		Triple<Double,Double,Double> anticuacion_sin_turno = mediador.anticuacion_sin_turno(desde,hasta);
		if(anticuacion_sin_turno.first()!=null)
			prom_anticuacion_sin_turno = roundUp(anticuacion_sin_turno.first());
		else
			prom_anticuacion_sin_turno = "";
		if(anticuacion_sin_turno.second()!=null)
			max_anticuacion_sin_turno = roundUp(anticuacion_sin_turno.second());
		else
			max_anticuacion_sin_turno = "";
		if(anticuacion_sin_turno.third()!=null)
			min_anticuacion_sin_turno = roundUp(anticuacion_sin_turno.third());
		else
			min_anticuacion_sin_turno = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_sin_turno = mediador.monto_sin_turno(desde, hasta);
		if(_monto_sin_turno.first()!=null)
			monto_sin_turno = "$"+df.format(_monto_sin_turno.first());
		else
			monto_sin_turno = "";
		if(_monto_sin_turno.second()!=null)
			prom_monto_sin_turno = "$"+df.format(_monto_sin_turno.second());
		else
			prom_monto_sin_turno = "";
		if(_monto_sin_turno.third()!=null)
			max_monto_sin_turno = "$"+df.format(_monto_sin_turno.third());
		else
			max_monto_sin_turno = "";
		if(_monto_sin_turno.fourth()!=null)
			min_monto_sin_turno = "$"+df.format(_monto_sin_turno.fourth());
		else
			min_monto_sin_turno = "";
		
		cantidad_con_turno =  mediador.cantidad_con_turno(desde,hasta);
		Triple<Double,Double,Double> anticuacion_con_turno = mediador.anticuacion_con_turno(desde,hasta);
		if(anticuacion_con_turno.first()!=null)
			prom_anticuacion_con_turno = roundUp(anticuacion_con_turno.first());
		else
			prom_anticuacion_con_turno = "";
		if(anticuacion_con_turno.second()!=null)
			max_anticuacion_con_turno = roundUp(anticuacion_con_turno.second());
		else
			max_anticuacion_con_turno = "";
		if(anticuacion_con_turno.third()!=null)
			min_anticuacion_con_turno = roundUp(anticuacion_con_turno.third());
		else
			min_anticuacion_con_turno = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_con_turno = mediador.monto_con_turno(desde, hasta);
		if(_monto_con_turno.first()!=null)
			monto_con_turno = "$"+df.format(_monto_con_turno.first());
		else
			monto_con_turno = "";
		if(_monto_con_turno.second()!=null)
			prom_monto_con_turno = "$"+df.format(_monto_con_turno.second());
		else
			prom_monto_con_turno = "";
		if(_monto_con_turno.third()!=null)
			max_monto_con_turno = "$"+df.format(_monto_con_turno.third());
		else
			max_monto_con_turno = "";
		if(_monto_con_turno.fourth()!=null)
			min_monto_con_turno = "$"+df.format(_monto_con_turno.fourth());
		else
			min_monto_con_turno = "";
		
		cantidad_recursadas =  mediador.cantidad_recursadas_entidad(desde,hasta);
		Triple<Double,Double,Double> anticuacion_recursadas = mediador.anticuacion_recursadas_entidad(desde,hasta);
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
		
		Cuadruple<Double,Double,Double,Double> _monto_recursadas = mediador.monto_recursadas_entidad(desde, hasta);
		if(_monto_recursadas.first()!=null)
			monto_recursadas = "$"+df.format(_monto_recursadas.first());
		else
			monto_recursadas = "";
		if(_monto_recursadas.second()!=null)
			prom_monto_recursadas = "$"+df.format(_monto_recursadas.second());
		else
			prom_monto_recursadas = "";
		if(_monto_recursadas.third()!=null)
			max_monto_recursadas = "$"+df.format(_monto_recursadas.third());
		else
			max_monto_recursadas = "";
		if(_monto_recursadas.fourth()!=null)
			min_monto_recursadas = "$"+df.format(_monto_recursadas.fourth());
		else
			min_monto_recursadas = "";
		
		cantidad_devueltas =  mediador.cantidad_devueltas_entidad(desde,hasta);
		Triple<Double,Double,Double> anticuacion_devueltas = mediador.anticuacion_devueltas_entidad(desde,hasta);
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
		
		Cuadruple<Double,Double,Double,Double> _monto_devueltas = mediador.monto_devueltas_entidad(desde, hasta);
		if(_monto_devueltas.first()!=null)
			monto_devueltas = "$"+df.format(_monto_devueltas.first());
		else
			monto_devueltas = "";
		if(_monto_devueltas.second()!=null)
			prom_monto_devueltas = "$"+df.format(_monto_devueltas.second());
		else
			prom_monto_devueltas = "";
		if(_monto_devueltas.third()!=null)
			max_monto_devueltas = "$"+df.format(_monto_devueltas.third());
		else
			max_monto_devueltas = "";
		if(_monto_devueltas.fourth()!=null)
			min_monto_devueltas = "$"+df.format(_monto_devueltas.fourth());
		else
			min_monto_devueltas = "";
//		Tabla Piezas Con OT		//
		modelo_tabla_piezas_pedidas_a_fabrica = new DefaultTableModel();
		datosTabla_pedidas_a_fabrica = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_pedidas_a_fabrica);//Q
		datosTabla_pedidas_a_fabrica.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_pedidas_a_fabrica);//X
		datosTabla_pedidas_a_fabrica.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_pedidas_a_fabrica);//MX
		datosTabla_pedidas_a_fabrica.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_pedidas_a_fabrica);//mX
		datosTabla_pedidas_a_fabrica.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_pedidas_a_fabrica);//R
		datosTabla_pedidas_a_fabrica.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_pedidas_a_fabrica);//XR
		datosTabla_pedidas_a_fabrica.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_pedidas_a_fabrica);//MR
		datosTabla_pedidas_a_fabrica.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
		row_min_monto.add(min_monto_pedidas_a_fabrica);//mR
		datosTabla_pedidas_a_fabrica.add(row_min_monto);
		modelo_tabla_piezas_pedidas_a_fabrica.setDataVector(datosTabla_pedidas_a_fabrica, nombreColumnas);
		modelo_tabla_piezas_pedidas_a_fabrica.fireTableStructureChanged();
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
		//		Fin Tabla Piezas En Transito	//
		//		Tabla Piezas Sin Turno		//
		modelo_tabla_piezas_sin_turno = new DefaultTableModel();
		datosTabla_piezas_sin_turno = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_sin_turno);//Q
		datosTabla_piezas_sin_turno.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_sin_turno);//X
		datosTabla_piezas_sin_turno.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_sin_turno);//MX
		datosTabla_piezas_sin_turno.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_sin_turno);//mX
		datosTabla_piezas_sin_turno.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_sin_turno);//R
		datosTabla_piezas_sin_turno.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_sin_turno);//XR
		datosTabla_piezas_sin_turno.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_sin_turno);//MR
		datosTabla_piezas_sin_turno.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
		row_min_monto.add(min_monto_sin_turno);//mR
		datosTabla_piezas_sin_turno.add(row_min_monto);
		modelo_tabla_piezas_sin_turno.setDataVector(datosTabla_piezas_sin_turno, nombreColumnas);
		modelo_tabla_piezas_sin_turno.fireTableStructureChanged();
		//		Fin Tabla Piezas Sin Turno		//
		//		Tabla Piezas Con Turno		//
		modelo_tabla_piezas_con_turno = new DefaultTableModel();
		datosTabla_piezas_con_turno = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_con_turno);//Q
		datosTabla_piezas_con_turno.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_con_turno);//X
		datosTabla_piezas_con_turno.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_con_turno);//MX
		datosTabla_piezas_con_turno.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_con_turno);//mX
		datosTabla_piezas_con_turno.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_con_turno);//R
		datosTabla_piezas_con_turno.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_con_turno);//XR
		datosTabla_piezas_con_turno.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_con_turno);//MR
		datosTabla_piezas_con_turno.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
		row_min_monto.add(min_monto_con_turno);//mR
		datosTabla_piezas_con_turno.add(row_min_monto);
		modelo_tabla_piezas_con_turno.setDataVector(datosTabla_piezas_con_turno, nombreColumnas);
		modelo_tabla_piezas_con_turno.fireTableStructureChanged();
		//		Fin Tabla Piezas Con Turno		//
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
		setTitle("REPORTES ENTIDADES");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/edit_pedido_entidad.png")));
		
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBounds(new Rectangle(0, 0, 1366, 768));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JPanel panelFechas = new TransparentPanel();
		panelFechas.setLayout(null);
		panelFechas.setBounds(257, 0, 780, 40);
		contentPane.add(panelFechas);
		
		JLabel lblDesdeFReclamo = new JLabel("Desde F. Pedido");
		lblDesdeFReclamo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDesdeFReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesdeFReclamo.setBounds(10, 10, 130, 20);
		panelFechas.add(lblDesdeFReclamo);
		
		dCDesdeFPedido = new JDateChooser();
		dCDesdeFPedido.setBounds(140, 10, 150, 20);
		panelFechas.add(dCDesdeFPedido);
		
		btnClearFReclamo = new JButton("");
		btnClearFReclamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dCDesdeFPedido.getDate()!=null)
					dCDesdeFPedido.setDate(null);
			}
		});
		btnClearFReclamo.setIcon(new ImageIcon(GUIReporteRapidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
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
		btnClearFDevolucion.setIcon(new ImageIcon(GUIReporteRapidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
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
		panelReportes.setBounds(36, 40, 1294, 610);
		contentPane.add(panelReportes);
		
		panelPiezasPedidas_a_Fabrica = new TransparentPanel();
		panelPiezasPedidas_a_Fabrica.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelPiezasPedidas_a_Fabrica.setLayout(null);
		panelPiezasPedidas_a_Fabrica.setBounds(64, 70, 345, 200);
		panelReportes.add(panelPiezasPedidas_a_Fabrica);
		
		modelo_tabla_piezas_pedidas_a_fabrica = new DefaultTableModel(datosTabla_pedidas_a_fabrica, nombreColumnas);
		tablaPiezasPedidas_a_Fabrica = new JTable(modelo_tabla_piezas_pedidas_a_fabrica) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tablaPiezasPedidas_a_Fabrica.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPiezasPedidas_a_Fabrica.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaPiezasPedidas_a_Fabrica.setBounds(145, 20, 200, 130);
		panelPiezasPedidas_a_Fabrica.add(tablaPiezasPedidas_a_Fabrica);
		for(int i = 0; i < tablaPiezasPedidas_a_Fabrica.getColumnCount(); i++) {
			tablaPiezasPedidas_a_Fabrica.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasPedidas_a_Fabrica.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		
		JLabel lblPiezasPedidas_a_Fabrica = new JLabel("Stock Pedido a Fabrica");
		lblPiezasPedidas_a_Fabrica.setToolTipText("Piezas pedidas a fabrica son aquellas que tienen fecha de pedido a fabrica.");
		lblPiezasPedidas_a_Fabrica.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPiezasPedidas_a_Fabrica.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasPedidas_a_Fabrica.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasPedidas_a_Fabrica.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasPedidas_a_Fabrica.setBounds(145, 0, 200, 20);
		panelPiezasPedidas_a_Fabrica.add(lblPiezasPedidas_a_Fabrica);
		
		btnVerCasosPedidas_a_Fabrica = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPedidas_a_Fabrica.setIcon(new ImageIcon(GUIReporteRapidoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnVerCasosPedidas_a_Fabrica.setText("Ver Casos");
		btnVerCasosPedidas_a_Fabrica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosPedidas_a_Fabrica();
			}
		});
		btnVerCasosPedidas_a_Fabrica.setBounds(72, 160, 200, 30);
		panelPiezasPedidas_a_Fabrica.add(btnVerCasosPedidas_a_Fabrica);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStock.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblStock.setBounds(0, 19, 145, 17);
		panelPiezasPedidas_a_Fabrica.add(lblStock);
		
		JLabel lblAnticuacionDeStock = new JLabel("Anticuacion de Stock");
		lblAnticuacionDeStock.setAutoscrolls(true);
		lblAnticuacionDeStock.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblAnticuacionDeStock.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAnticuacionDeStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnticuacionDeStock.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAnticuacionDeStock.setBounds(0, 36, 145, 48);
		panelPiezasPedidas_a_Fabrica.add(lblAnticuacionDeStock);
		
		JLabel lblValorStock = new JLabel("Valor Stock");
		lblValorStock.setHorizontalTextPosition(SwingConstants.CENTER);
		lblValorStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorStock.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValorStock.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblValorStock.setBounds(0, 84, 145, 66);
		panelPiezasPedidas_a_Fabrica.add(lblValorStock);
		
		panelPiezasEnTransito = new TransparentPanel();
		panelPiezasEnTransito.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelPiezasEnTransito.setLayout(null);
		panelPiezasEnTransito.setBounds(473, 70, 345, 200);
		panelReportes.add(panelPiezasEnTransito);
		
		JLabel lblFOt = new JLabel("F. Pedido");
		lblFOt.setFont(new Font("Tahoma", Font.BOLD, 11));
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
		lblPiezasConOt.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPiezasConOt.setToolTipText("Piezas en transito son aquellas que poseen fecha de pedido a fabrica pero no poseen fecha de recepcion de fabrica.");
		lblPiezasConOt.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasConOt.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasConOt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasConOt.setBounds(145, 0, 200, 20);
		panelPiezasEnTransito.add(lblPiezasConOt);
		
		btnVerCasosPiezasEnTransito = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasEnTransito.setIcon(new ImageIcon(GUIReporteRapidoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnVerCasosPiezasEnTransito.setText("Ver Casos");
		btnVerCasosPiezasEnTransito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosEnTransito();
			}
		});
		btnVerCasosPiezasEnTransito.setBounds(72, 160, 200, 30);
		panelPiezasEnTransito.add(btnVerCasosPiezasEnTransito);
		
		label_3 = new JLabel("Valor Stock");
		label_3.setHorizontalTextPosition(SwingConstants.CENTER);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_3.setBounds(0, 84, 145, 66);
		panelPiezasEnTransito.add(label_3);
		
		label_4 = new JLabel("Anticuacion de Stock");
		label_4.setHorizontalTextPosition(SwingConstants.CENTER);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_4.setAutoscrolls(true);
		label_4.setBounds(0, 36, 145, 48);
		panelPiezasEnTransito.add(label_4);
		
		label_5 = new JLabel("Stock");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_5.setBounds(0, 19, 145, 17);
		panelPiezasEnTransito.add(label_5);
		
		panelPiezasSinTurno = new TransparentPanel();
		panelPiezasSinTurno.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelPiezasSinTurno.setLayout(null);
		panelPiezasSinTurno.setBounds(882, 70, 345, 200);
		panelReportes.add(panelPiezasSinTurno);
		
		JLabel lblFrecepcion = new JLabel("F. Recepcion");
		lblFrecepcion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFrecepcion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFrecepcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrecepcion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFrecepcion.setBounds(0, 0, 145, 20);
		panelPiezasSinTurno.add(lblFrecepcion);
		
		modelo_tabla_piezas_sin_turno = new DefaultTableModel(datosTabla_piezas_sin_turno, nombreColumnas);
		tablaPiezasSinTurno = new JTable(modelo_tabla_piezas_sin_turno) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		for(int i = 0; i < tablaPiezasSinTurno.getColumnCount(); i++) {
			tablaPiezasSinTurno.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasSinTurno.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		tablaPiezasSinTurno.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPiezasSinTurno.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaPiezasSinTurno.setBounds(145, 20, 200, 130);
		panelPiezasSinTurno.add(tablaPiezasSinTurno);
		
		JLabel lblPiezasSinTurno = new JLabel("Stock Sin Turno");
		lblPiezasSinTurno.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPiezasSinTurno.setToolTipText("Piezas  sin turno son las que poseen fecha de recepcion a fabrica pero no poseen fecha de turno.");
		lblPiezasSinTurno.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasSinTurno.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasSinTurno.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasSinTurno.setBounds(145, 0, 200, 20);
		panelPiezasSinTurno.add(lblPiezasSinTurno);
		
		btnVerCasosPiezasSinTurno = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasSinTurno.setIcon(new ImageIcon(GUIReporteRapidoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnVerCasosPiezasSinTurno.setText("Ver Casos");
		btnVerCasosPiezasSinTurno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosSinTurno();
			}
		});
		btnVerCasosPiezasSinTurno.setBounds(72, 160, 200, 30);
		panelPiezasSinTurno.add(btnVerCasosPiezasSinTurno);
		
		label_6 = new JLabel("Valor Stock");
		label_6.setHorizontalTextPosition(SwingConstants.CENTER);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_6.setBounds(0, 85, 145, 66);
		panelPiezasSinTurno.add(label_6);
		
		label_7 = new JLabel("Anticuacion de Stock");
		label_7.setHorizontalTextPosition(SwingConstants.CENTER);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_7.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_7.setAutoscrolls(true);
		label_7.setBounds(0, 37, 145, 48);
		panelPiezasSinTurno.add(label_7);
		
		label_8 = new JLabel("Stock");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_8.setBounds(0, 20, 145, 17);
		panelPiezasSinTurno.add(label_8);
		
		panelPiezasConTurno = new TransparentPanel();
		panelPiezasConTurno.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelPiezasConTurno.setLayout(null);
		panelPiezasConTurno.setBounds(64, 340, 345, 200);
		panelReportes.add(panelPiezasConTurno);
		
		JLabel lblFechaTurno = new JLabel("F. Turno");
		lblFechaTurno.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaTurno.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFechaTurno.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaTurno.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFechaTurno.setBounds(0, 0, 145, 20);
		panelPiezasConTurno.add(lblFechaTurno);
		
		modelo_tabla_piezas_con_turno = new DefaultTableModel(datosTabla_piezas_con_turno, nombreColumnas);
		tablaPiezasConTurno = new JTable(modelo_tabla_piezas_con_turno) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		for(int i = 0; i < tablaPiezasConTurno.getColumnCount(); i++) {
			tablaPiezasConTurno.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasConTurno.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		tablaPiezasConTurno.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPiezasConTurno.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaPiezasConTurno.setBounds(145, 20, 200, 130);
		panelPiezasConTurno.add(tablaPiezasConTurno);
		
		JLabel lblPiezasConTurno = new JLabel("Stock Con Turno");
		lblPiezasConTurno.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPiezasConTurno.setToolTipText("Piezas con turno son aquellas que poseen fecha de turno pero no poseen fecha de recursado.");
		lblPiezasConTurno.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasConTurno.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasConTurno.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasConTurno.setBounds(145, 0, 200, 20);
		panelPiezasConTurno.add(lblPiezasConTurno);
		
		btnVerCasosPiezasConTurno = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasConTurno.setIcon(new ImageIcon(GUIReporteRapidoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnVerCasosPiezasConTurno.setText("Ver Casos");
		btnVerCasosPiezasConTurno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosConTurno();
			}
		});
		btnVerCasosPiezasConTurno.setBounds(72, 160, 200, 30);
		panelPiezasConTurno.add(btnVerCasosPiezasConTurno);
		
		label = new JLabel("Valor Stock");
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label.setBounds(0, 84, 145, 66);
		panelPiezasConTurno.add(label);
		
		label_1 = new JLabel("Anticuacion de Stock");
		label_1.setHorizontalTextPosition(SwingConstants.CENTER);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_1.setAutoscrolls(true);
		label_1.setBounds(0, 36, 145, 48);
		panelPiezasConTurno.add(label_1);
		
		label_2 = new JLabel("Stock");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_2.setBounds(0, 19, 145, 17);
		panelPiezasConTurno.add(label_2);
		
		panelPiezasRecursadas = new TransparentPanel();
		panelPiezasRecursadas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelPiezasRecursadas.setLayout(null);
		panelPiezasRecursadas.setBounds(473, 340, 345, 200);
		panelReportes.add(panelPiezasRecursadas);
		
		JLabel lblFrecurso = new JLabel("F. Recurso");
		lblFrecurso.setFont(new Font("Tahoma", Font.BOLD, 11));
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
		lblPiezasRecursadas.setToolTipText("Piezas recursadas son aquellas que poseen fecha de recurso pero no poseen fecha de devolucion a fabrica.");
		lblPiezasRecursadas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPiezasRecursadas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasRecursadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasRecursadas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasRecursadas.setBounds(145, 0, 200, 20);
		panelPiezasRecursadas.add(lblPiezasRecursadas);
		
		btnVerCasosPiezasRecursadas = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasRecursadas.setIcon(new ImageIcon(GUIReporteRapidoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnVerCasosPiezasRecursadas.setText("Ver Casos");
		btnVerCasosPiezasRecursadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosRecursadas();
			}
		});
		btnVerCasosPiezasRecursadas.setBounds(72, 160, 200, 30);
		panelPiezasRecursadas.add(btnVerCasosPiezasRecursadas);
		
		label_12 = new JLabel("Valor Stock");
		label_12.setHorizontalTextPosition(SwingConstants.CENTER);
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_12.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_12.setBounds(0, 84, 145, 66);
		panelPiezasRecursadas.add(label_12);
		
		label_13 = new JLabel("Anticuacion de Stock");
		label_13.setHorizontalTextPosition(SwingConstants.CENTER);
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_13.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_13.setAutoscrolls(true);
		label_13.setBounds(0, 36, 145, 48);
		panelPiezasRecursadas.add(label_13);
		
		label_14 = new JLabel("Stock");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_14.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_14.setBounds(0, 19, 145, 17);
		panelPiezasRecursadas.add(label_14);
		
		panelPiezasDevueltas = new TransparentPanel();
		panelPiezasDevueltas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelPiezasDevueltas.setLayout(null);
		panelPiezasDevueltas.setBounds(882, 340, 345, 200);
		panelReportes.add(panelPiezasDevueltas);
		
		JLabel lblFdevolucion = new JLabel("F. Devolucion");
		lblFdevolucion.setFont(new Font("Tahoma", Font.BOLD, 11));
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
		lblPiezasDevueltas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPiezasDevueltas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPiezasDevueltas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezasDevueltas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPiezasDevueltas.setBounds(145, 0, 200, 20);
		panelPiezasDevueltas.add(lblPiezasDevueltas);
		
		btnVerCasosPiezasDevueltas = new GlossyButton("VER CASOS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerCasosPiezasDevueltas.setIcon(new ImageIcon(GUIReporteRapidoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnVerCasosPiezasDevueltas.setText("Ver Casos");
		btnVerCasosPiezasDevueltas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCasosDevueltas();
			}
		});
		btnVerCasosPiezasDevueltas.setBounds(72, 160, 200, 30);
		panelPiezasDevueltas.add(btnVerCasosPiezasDevueltas);
		
		label_9 = new JLabel("Valor Stock");
		label_9.setHorizontalTextPosition(SwingConstants.CENTER);
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_9.setBounds(0, 85, 145, 66);
		panelPiezasDevueltas.add(label_9);
		
		label_10 = new JLabel("Anticuacion de Stock");
		label_10.setHorizontalTextPosition(SwingConstants.CENTER);
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_10.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_10.setAutoscrolls(true);
		label_10.setBounds(0, 37, 145, 48);
		panelPiezasDevueltas.add(label_10);
		
		label_11 = new JLabel("Stock");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_11.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_11.setBounds(0, 20, 145, 17);
		panelPiezasDevueltas.add(label_11);
		
		btnVolver = new GlossyButton("VOLVER",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUEGREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVolver.setText("Volver");
		btnVolver.setBounds(587, 661, 200, 30);
		contentPane.add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVolver.setIcon(new ImageIcon(GUIReporteRapidoEntidad.class.getResource("/cliente/Resources/Icons/back.png")));
		
		setVisible(true);
	}


	protected void verCasosPedidas_a_Fabrica() {
		java.sql.Date desde = null;
		if(dCDesdeFPedido.getDate()!=null)
			desde = new java.sql.Date(dCDesdeFPedido.getDate().getTime());
		java.sql.Date hasta = null;
		if(dCHastaFDevolucion.getDate()!=null)
			hasta = new java.sql.Date(dCHastaFDevolucion.getDate().getTime());
		mediador.verCasosPedidas_a_FabricaEntidad(desde,hasta);
	}

	protected void verCasosDevueltas() {
		java.sql.Date desde = null;
		if(dCDesdeFPedido.getDate()!=null)
			desde = new java.sql.Date(dCDesdeFPedido.getDate().getTime());
		java.sql.Date hasta = null;
		if(dCHastaFDevolucion.getDate()!=null)
			hasta = new java.sql.Date(dCHastaFDevolucion.getDate().getTime());
		mediador.verCasosDevueltasEntidad(desde,hasta);
	}

	protected void verCasosRecursadas() {
		java.sql.Date desde = null;
		if(dCDesdeFPedido.getDate()!=null)
			desde = new java.sql.Date(dCDesdeFPedido.getDate().getTime());
		java.sql.Date hasta = null;
		if(dCHastaFDevolucion.getDate()!=null)
			hasta = new java.sql.Date(dCHastaFDevolucion.getDate().getTime());
		mediador.verCasosRecursadasEntidad(desde,hasta);
	}

	protected void verCasosConTurno() {
		java.sql.Date desde = null;
		if(dCDesdeFPedido.getDate()!=null)
			desde = new java.sql.Date(dCDesdeFPedido.getDate().getTime());
		java.sql.Date hasta = null;
		if(dCHastaFDevolucion.getDate()!=null)
			hasta = new java.sql.Date(dCHastaFDevolucion.getDate().getTime());
		mediador.verCasosConTurnoEntidad(desde,hasta);
	}

	protected void verCasosSinTurno() {
		java.sql.Date desde = null;
		if(dCDesdeFPedido.getDate()!=null)
			desde = new java.sql.Date(dCDesdeFPedido.getDate().getTime());
		java.sql.Date hasta = null;
		if(dCHastaFDevolucion.getDate()!=null)
			hasta = new java.sql.Date(dCHastaFDevolucion.getDate().getTime());
		mediador.verCasosSinTurnoEntidad(desde,hasta);
	}

	protected void verCasosEnTransito() {
		java.sql.Date desde = null;
		if(dCDesdeFPedido.getDate()!=null)
			desde = new java.sql.Date(dCDesdeFPedido.getDate().getTime());
		java.sql.Date hasta = null;
		if(dCHastaFDevolucion.getDate()!=null)
			hasta = new java.sql.Date(dCHastaFDevolucion.getDate().getTime());
		mediador.verCasosEnTransitoEntidad(desde,hasta);
	}
	
	private void actualizarDatos(java.sql.Date desde, java.sql.Date hasta){
		DecimalFormat df = new DecimalFormat("0.00"); 
		cantidad_pedidas_a_fabrica =  mediador.cantidad_pedidas_a_fabrica_entidad(desde,hasta);
		Triple<Double,Double,Double> anticuacion_con_ot = mediador.anticuacion_pedidas_a_fabrica_entidad(desde,hasta);
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
		
		Cuadruple<Double,Double,Double,Double> _monto_con_ot = mediador.monto_pedidas_a_fabrica_entidad(desde, hasta);
		if(_monto_con_ot.first()!=null)
			monto_pedidas_a_fabrica = "$"+df.format(_monto_con_ot.first());
		else
			monto_pedidas_a_fabrica = "";
		if(_monto_con_ot.second()!=null)
			prom_monto_pedidas_a_fabrica = "$"+df.format(_monto_con_ot.second());
		else
			prom_monto_pedidas_a_fabrica = "";
		if(_monto_con_ot.third()!=null)
			max_monto_pedidas_a_fabrica = "$"+df.format(_monto_con_ot.third());
		else
			max_monto_pedidas_a_fabrica = "";
		if(_monto_con_ot.fourth()!=null)
			min_monto_pedidas_a_fabrica = "$"+df.format(_monto_con_ot.fourth());
		else
			min_monto_pedidas_a_fabrica = "";
		
		cantidad_en_tranisto =  mediador.cantidad_en_transito_entidad(desde,hasta);
		Triple<Double,Double,Double> anticuacion_en_transito = mediador.anticuacion_en_transito_entidad(desde,hasta);
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
		
		Cuadruple<Double,Double,Double,Double> _monto_en_transito = mediador.monto_en_transito_entidad(desde, hasta);
		if(_monto_en_transito.first()!=null)
			monto_en_transito = "$"+df.format(_monto_en_transito.first());
		else
			monto_en_transito = "";
		if(_monto_en_transito.second()!=null)
			prom_monto_en_transito = "$"+df.format(_monto_en_transito.second());
		else
			prom_monto_en_transito = "";
		if(_monto_en_transito.third()!=null)
			max_monto_en_transito = "$"+df.format(_monto_en_transito.third());
		else
			max_monto_en_transito = "";
		if(_monto_en_transito.fourth()!=null)
			min_monto_en_transito = "$"+df.format(_monto_en_transito.fourth());
		else
			min_monto_en_transito = "";
		
		cantidad_sin_turno =  mediador.cantidad_sin_turno(desde,hasta);
		Triple<Double,Double,Double> anticuacion_sin_turno = mediador.anticuacion_sin_turno(desde,hasta);
		if(anticuacion_sin_turno.first()!=null)
			prom_anticuacion_sin_turno = roundUp(anticuacion_sin_turno.first());
		else
			prom_anticuacion_sin_turno = "";
		if(anticuacion_sin_turno.second()!=null)
			max_anticuacion_sin_turno = roundUp(anticuacion_sin_turno.second());
		else
			max_anticuacion_sin_turno = "";
		if(anticuacion_sin_turno.third()!=null)
			min_anticuacion_sin_turno = roundUp(anticuacion_sin_turno.third());
		else
			min_anticuacion_sin_turno = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_sin_turno = mediador.monto_sin_turno(desde, hasta);
		if(_monto_sin_turno.first()!=null)
			monto_sin_turno = "$"+df.format(_monto_sin_turno.first());
		else
			monto_sin_turno = "";
		if(_monto_sin_turno.second()!=null)
			prom_monto_sin_turno = "$"+df.format(_monto_sin_turno.second());
		else
			prom_monto_sin_turno = "";
		if(_monto_sin_turno.third()!=null)
			max_monto_sin_turno = "$"+df.format(_monto_sin_turno.third());
		else
			max_monto_sin_turno = "";
		if(_monto_sin_turno.fourth()!=null)
			min_monto_sin_turno = "$"+df.format(_monto_sin_turno.fourth());
		else
			min_monto_sin_turno = "";
		
		cantidad_con_turno =  mediador.cantidad_con_turno(desde,hasta);
		Triple<Double,Double,Double> anticuacion_con_turno = mediador.anticuacion_con_turno(desde,hasta);
		if(anticuacion_con_turno.first()!=null)
			prom_anticuacion_con_turno = roundUp(anticuacion_con_turno.first());
		else
			prom_anticuacion_con_turno = "";
		if(anticuacion_con_turno.second()!=null)
			max_anticuacion_con_turno = roundUp(anticuacion_con_turno.second());
		else
			max_anticuacion_con_turno = "";
		if(anticuacion_con_turno.third()!=null)
			min_anticuacion_con_turno = roundUp(anticuacion_con_turno.third());
		else
			min_anticuacion_con_turno = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_con_turno = mediador.monto_con_turno(desde, hasta);
		if(_monto_con_turno.first()!=null)
			monto_con_turno = "$"+df.format(_monto_con_turno.first());
		else
			monto_con_turno = "";
		if(_monto_con_turno.second()!=null)
			prom_monto_con_turno = "$"+df.format(_monto_con_turno.second());
		else
			prom_monto_con_turno = "";
		if(_monto_con_turno.third()!=null)
			max_monto_con_turno = "$"+df.format(_monto_con_turno.third());
		else
			max_monto_con_turno = "";
		if(_monto_con_turno.fourth()!=null)
			min_monto_con_turno = "$"+df.format(_monto_con_turno.fourth());
		else
			min_monto_con_turno = "";
		
		cantidad_recursadas =  mediador.cantidad_recursadas_entidad(desde,hasta);
		Triple<Double,Double,Double> anticuacion_recursadas = mediador.anticuacion_recursadas_entidad(desde,hasta);
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
		
		Cuadruple<Double,Double,Double,Double> _monto_recursadas = mediador.monto_recursadas_entidad(desde, hasta);
		if(_monto_recursadas.first()!=null)
			monto_recursadas = "$"+df.format(_monto_recursadas.first());
		else
			monto_recursadas = "";
		if(_monto_recursadas.second()!=null)
			prom_monto_recursadas = "$"+df.format(_monto_recursadas.second());
		else
			prom_monto_recursadas = "";
		if(_monto_recursadas.third()!=null)
			max_monto_recursadas = "$"+df.format(_monto_recursadas.third());
		else
			max_monto_recursadas = "";
		if(_monto_recursadas.fourth()!=null)
			min_monto_recursadas = "$"+df.format(_monto_recursadas.fourth());
		else
			min_monto_recursadas = "";
		
		Cuadruple<Double,Double,Double,Double> _monto_devueltas = mediador.monto_devueltas_entidad(desde, hasta);
		if(_monto_devueltas.first()!=null)
			monto_devueltas = "$"+df.format(_monto_devueltas.first());
		else
			monto_devueltas = "";
		if(_monto_devueltas.second()!=null)
			prom_monto_devueltas = "$"+df.format(_monto_devueltas.second());
		else
			prom_monto_devueltas = "";
		if(_monto_devueltas.third()!=null)
			max_monto_devueltas = "$"+df.format(_monto_devueltas.third());
		else
			max_monto_devueltas = "";
		if(_monto_devueltas.fourth()!=null)
			min_monto_devueltas = "$"+df.format(_monto_devueltas.fourth());
		else
			min_monto_devueltas = "";
		
		actualizarTablas();
	}

	private void filtrar() {
		if(dCDesdeFPedido.getDate()==null || dCHastaFDevolucion.getDate()==null){ 
			if(dCDesdeFPedido.getDate()==null){ 
				if(dCHastaFDevolucion.getDate()!=null){//NULL DESDE NO NULL HASTA
					if (JOptionPane.showConfirmDialog(null, "¿Mostrar desde el primer reclamo cargado?, Esto puede demorar.", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIReporteRapidoEntidad.class.getResource("/cliente/Resources/Icons/delete.png"))) == JOptionPane.YES_OPTION){
						actualizarDatos(null, new java.sql.Date(dCHastaFDevolucion.getDate().getTime()));
					}
				}else{ //NULL AMBAS
					if (JOptionPane.showConfirmDialog(null, "¿Mostrar todos los reclamos cargados?, Esto puede demorar.", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIReporteRapidoEntidad.class.getResource("/cliente/Resources/Icons/delete.png"))) == JOptionPane.YES_OPTION){
						actualizarDatos(null, null);
					}
				}
			}else{ //NO NULL DESDE NULL HASTA
				if (JOptionPane.showConfirmDialog(null, "¿Mostrar hasta la ultima devolucion cargada?, Esto puede demorar.", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIReporteRapidoEntidad.class.getResource("/cliente/Resources/Icons/delete.png"))) == JOptionPane.YES_OPTION){					
					actualizarDatos(new java.sql.Date(dCDesdeFPedido.getDate().getTime()), null);
				}
			}
		}else{ //NO NULL AMBAS
			actualizarDatos(new java.sql.Date(dCDesdeFPedido.getDate().getTime()), new java.sql.Date(dCHastaFDevolucion.getDate().getTime()));
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
		datosTabla_pedidas_a_fabrica = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_pedidas_a_fabrica);//Q
		datosTabla_pedidas_a_fabrica.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_pedidas_a_fabrica);//X
		datosTabla_pedidas_a_fabrica.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_pedidas_a_fabrica);//MX
		datosTabla_pedidas_a_fabrica.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_pedidas_a_fabrica);//mX
		datosTabla_pedidas_a_fabrica.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_pedidas_a_fabrica);//R
		datosTabla_pedidas_a_fabrica.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_pedidas_a_fabrica);//XR
		datosTabla_pedidas_a_fabrica.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_pedidas_a_fabrica);//MR
		datosTabla_pedidas_a_fabrica.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
		row_min_monto.add(min_monto_pedidas_a_fabrica);//mR
		datosTabla_pedidas_a_fabrica.add(row_min_monto);
		modelo_tabla_piezas_pedidas_a_fabrica.setDataVector(datosTabla_pedidas_a_fabrica, nombreColumnas);
		modelo_tabla_piezas_pedidas_a_fabrica.fireTableStructureChanged();
		tablaPiezasPedidas_a_Fabrica.setModel(modelo_tabla_piezas_pedidas_a_fabrica);
		for(int i = 0; i < tablaPiezasPedidas_a_Fabrica.getColumnCount(); i++) {
			tablaPiezasPedidas_a_Fabrica.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasPedidas_a_Fabrica.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
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
		modelo_tabla_piezas_sin_turno = new DefaultTableModel();
		datosTabla_piezas_sin_turno = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_sin_turno);//Q
		datosTabla_piezas_sin_turno.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_sin_turno);//X
		datosTabla_piezas_sin_turno.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_sin_turno);//MX
		datosTabla_piezas_sin_turno.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_sin_turno);//mX
		datosTabla_piezas_sin_turno.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_sin_turno);//R
		datosTabla_piezas_sin_turno.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_sin_turno);//XR
		datosTabla_piezas_sin_turno.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_sin_turno);//MR
		datosTabla_piezas_sin_turno.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
		row_min_monto.add(min_monto_sin_turno);//mR
		datosTabla_piezas_sin_turno.add(row_min_monto);
		modelo_tabla_piezas_sin_turno.setDataVector(datosTabla_piezas_sin_turno, nombreColumnas);
		modelo_tabla_piezas_sin_turno.fireTableStructureChanged();
		tablaPiezasSinTurno.setModel(modelo_tabla_piezas_sin_turno);
		for(int i = 0; i < tablaPiezasSinTurno.getColumnCount(); i++) {
			tablaPiezasSinTurno.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasSinTurno.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		//		Fin Tabla Piezas Sin Turno		//
		//		Tabla Piezas Con Turno		//
		modelo_tabla_piezas_con_turno = new DefaultTableModel();
		datosTabla_piezas_con_turno = new Vector<Vector<String>>();
		row_cantidad= new Vector<String> ();
		row_cantidad.add("Cantidad");
		row_cantidad.add(cantidad_con_turno);//Q
		datosTabla_piezas_con_turno.add(row_cantidad);
		row_prom_anticuacion= new Vector<String> ();
		row_prom_anticuacion.add("Promedio");
		row_prom_anticuacion.add(prom_anticuacion_con_turno);//X
		datosTabla_piezas_con_turno.add(row_prom_anticuacion);
		row_max_anticuacion= new Vector<String> ();
		row_max_anticuacion.add("Maximo");
		row_max_anticuacion.add(max_anticuacion_con_turno);//MX
		datosTabla_piezas_con_turno.add(row_max_anticuacion);
		row_min_anticuacion= new Vector<String> ();
		row_min_anticuacion.add("Minimo");
		row_min_anticuacion.add(min_anticuacion_con_turno);//mX
		datosTabla_piezas_con_turno.add(row_min_anticuacion);
		row_monto= new Vector<String> ();
		row_monto.add("Total");
		row_monto.add(monto_con_turno);//R
		datosTabla_piezas_con_turno.add(row_monto);
		row_prom_monto= new Vector<String> ();
		row_prom_monto.add("Promedio");
		row_prom_monto.add(prom_monto_con_turno);//XR
		datosTabla_piezas_con_turno.add(row_prom_monto);
		row_max_monto= new Vector<String> ();
		row_max_monto.add("Maximo");
		row_max_monto.add(max_monto_con_turno);//MR
		datosTabla_piezas_con_turno.add(row_max_monto);
		row_min_monto= new Vector<String> ();
		row_min_monto.add("Minimo");
		row_min_monto.add(min_monto_con_turno);//mR
		datosTabla_piezas_con_turno.add(row_min_monto);
		modelo_tabla_piezas_con_turno.setDataVector(datosTabla_piezas_con_turno, nombreColumnas);
		modelo_tabla_piezas_con_turno.fireTableStructureChanged();
		tablaPiezasConTurno.setModel(modelo_tabla_piezas_con_turno);
		for(int i = 0; i < tablaPiezasConTurno.getColumnCount(); i++) {
			tablaPiezasConTurno.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla.elementAt(i));
			tablaPiezasConTurno.getColumnModel().getColumn(i).setMinWidth(anchos_tabla.elementAt(i));
		}
		//		Fin Tabla Piezas Con Turno		//
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
