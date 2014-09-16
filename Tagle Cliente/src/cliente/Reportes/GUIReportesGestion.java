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

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cliente.excellexport.ExportarExcel;

import com.toedter.calendar.JDateChooser;

import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.ReclamoDTO;

public class GUIReportesGestion extends JFrame {

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	//
	Vector<Pedido_PiezaDTO> pedidos_piezas;
	Vector<ReclamoDTO> reclamos;
	//
	private JPanel contentPane;
	private JPanel pnPiezaLLegadas;
	private JPanel pnPiezasLLegadasSinTurno;
	private JPanel pnOrdenSinSDP;
	private JPanel pnSDPsinNumPedido;
	private JPanel pnPiezasPedidasSinLLegar;
	private JPanel pnPiezasDevueltas;
	//tabla piezas sin llegar
	private JDateChooser dC_FF_SLL;
	private JDateChooser dC_FI_SLL;
	private JRadioButton rB_Intervalo_SLL;
	private JRadioButton rB_Hoy_SLL;
	private JRadioButton rB_USemana_SLL;
	private JRadioButton rB_Mes_SLL;
	private JRadioButton rB_Anio_SLL;
	private JRadioButton rB_MAnterior_SLL;
	private JScrollPane scrollPane_pedidas_sin_llegar;
	private JTable tabla_SLL;
	private DefaultTableModel modelo_tabla_piezas_sin_llegar;
	private Vector<Vector<String>> datosTabla_piezas_sin_llegar;
	private Vector<String> nombreColumnas_piezas_sin_llegar;
	private Vector<Integer> anchos_tabla_piezas_sin_llegar;
	
	//tabla piezas devueltas
	private JDateChooser dC_FI_PD;
	private JDateChooser dC_FF_PD;
	private JRadioButton rB_Intervalo_PD;
	private JRadioButton rB_Anio_PD;
	private JRadioButton rB_Hoy_PD;
	private JRadioButton rB_USemana_PD;
	private JRadioButton rB_Mes_PD;
	private JTable tabla_PD;
	private DefaultTableModel modelo_tabla_piezas_devueltas;
	private Vector<Vector<String>> datosTabla_piezas_devueltas;
	private Vector<String> nombreColumnas_piezas_devueltas;
	private Vector<Integer> anchos_tabla_piezas_devueltas;
	private JScrollPane scrollPane_piezas_devueltas;
	
	//tabla piezas llegadas
	private JRadioButton rB_Mes_PLL;
	private JRadioButton rB_Itervalo_PLL;
	private JRadioButton rB_Hoy_PLL;
	private JRadioButton rB_USemana_PLL;
	private JRadioButton rB_Anio_PLL;
	private DefaultTableModel modelo_tabla_piezas_llegadas;
	private JTable tabla_PLL;
	private Vector<Vector<String>> datosTabla_piezas_llegadas;
	private Vector<String> nombreColumnas_piezas_llegadas;
	private Vector<Integer> anchos_tabla_piezas_llegadas;
	
	//tabla llegadas sin turno
	private JDateChooser dC_FI_PLLST;
	private JDateChooser dC_FF_PLLST;
	private JRadioButton rB_Intervalo_PLLST;
	private JRadioButton rB_Hoy_PLLST;
	private JRadioButton rB_UMes_PLLST;
	private JRadioButton rB_USemana_PLLST;
	private JRadioButton rB_Anio_PLLST;
	private JScrollPane scrollPane_piezas_llegadas_sin_turno;
	private DefaultTableModel modelo_tabla_piezas_llegadas_sin_turno;
	private Vector<Vector<String>> datosTabla_piezas_llegadas_sin_turno;
	private Vector<String> nombreColumnas_piezas_llegadas_sin_turno;
	private JTable tabla_PLLST;
	private Vector<Integer> anchos_tabla_piezas_llegadas_sin_turno;
	
	//tabla orden sin sdp
	private JDateChooser dC_FI_OSSDP;
	private JDateChooser dC_FF_OSSDP;
	private JRadioButton rB_Intervalo_OSSDP;
	private JRadioButton rB_Hoy_OSSDP;
	private JRadioButton rB_USemana_OSSDP;
	private JRadioButton rB_UMes_OSSDP;
	private JRadioButton rB_UAnio_OSSDP;
	private JScrollPane scrollPane_orden_sin_sdp;
	private DefaultTableModel modelo_tabla_orden_sin_solicitud_pedido;
	private Vector<Vector<String>> datosTabla_orden_sin_solicitud_pedido;
	private Vector<String> nombreColumnas_orden_sin_solicitud_pedido;
	private JTable tabla_OSSDP;
	private Vector<Integer> anchos_tabla_orden_sin_solicitud_pedido;
	//Tabla sdp sin solicitud pedido
	private JDateChooser dC_FI_PSNP;
	private JDateChooser dC_FF_PSNP;
	private JRadioButton rB_Inetervalo_SDPSN;
	private JRadioButton rB_Hoy_SDPSN;
	private JRadioButton rB_USemana_SDPSN;
	private JRadioButton rB_UMes_SDPSN;
	private JRadioButton rB_UAnio_SDPSN;
	private JScrollPane scrollPane_sdp_sin_numero;
	private DefaultTableModel modelo_tabla_sdp_sin_numero_pedido;
	private Vector<Vector<String>> datosTabla_sdp_sin_numero_pedido;
	private Vector<String> nombreColumnas_sdp_sin_numero_pedido;
	private JTable tabla_SDPSN;
	private Vector<Integer> anchos_tabla_sdp_sin_numero_pedido;
	//tabla mano obra
	private JRadioButton rBMAnteriorPLL;
	private JDateChooser dC_FInicioPLL;
	private JDateChooser dC_FFinPLL;
	private JRadioButton rbMAnteriorPDev;
	private JRadioButton rBMesPasado_PLLST;
	private JRadioButton rB_MesAnterior_OSSDP;
	private JRadioButton rB_MesPasado_SDPSN;
	private JButton btnFiltrar_PLL;
	private JButton btnFiltrarPDev;
	private JButton btnFiltrar_SLL;
	private JButton btnFiltrarPLLST;
	private JButton btnFiltrarOSSDP;
	private JButton btnFiltrarSDPSN;
	private JButton btn_clear_FIPLL;
	private JButton btn_clear_FFPLL;
	private JButton btnExportarTablaPLL;
	private JButton btn_clear_FIPD;
	private JButton btn_clear_FFPD;
	private JButton btn_clear_FFSLL;
	private JButton btnExportarTablaSLL;
	private JButton btn_clear_FILLST;
	private JButton btn_clear_FFLLST;
	private JButton btnExportarTablaLLST;
	private JButton btn_clear_FISSP;
	private JButton btn_clear_FFSSP;
	private JButton btnExportarTablaSSP;
	private JButton btn_clear_FISPSNP;
	private JButton btn_clear_FFSPSNP;
	private JButton btnExportarTablaSPSNP;
	private JButton btnExportarTablaPD;
	private JButton btn_clear_FISLL;
	
	//tabla 
	public GUIReportesGestion(MediadorReportes mediadorReporte) {
		setMediador(mediadorReporte);
		cargarDatos();
		initialize();
	}

	private void cargarDatos() {

		pedidos_piezas = mediador.obtenerPedido_Piezas();
		reclamos = mediador.obtenerReclamos(); 
		int chico = 100;
		int mediano = 150;
		int grande = 230;	

		//tablas Piezas Llegadas
		modelo_tabla_piezas_llegadas = new DefaultTableModel();
		nombreColumnas_piezas_llegadas = new Vector<String> ();
		anchos_tabla_piezas_llegadas = new Vector<Integer>();
		nombreColumnas_piezas_llegadas.add("ID Pedido");//0
		anchos_tabla_piezas_llegadas.add(75);
		nombreColumnas_piezas_llegadas.add("Numero Pedido");//2
		anchos_tabla_piezas_llegadas.add(chico);
		nombreColumnas_piezas_llegadas.add("Numero Pieza");//3
		anchos_tabla_piezas_llegadas.add(chico);
		nombreColumnas_piezas_llegadas.add("Descripcion");//4
		anchos_tabla_piezas_llegadas.add(mediano);
		nombreColumnas_piezas_llegadas.add("Numero Orden");//5
		anchos_tabla_piezas_llegadas.add(chico);
		nombreColumnas_piezas_llegadas.add("VIN");//6
		anchos_tabla_piezas_llegadas.add(130);
		nombreColumnas_piezas_llegadas.add("Registrante");//7
		anchos_tabla_piezas_llegadas.add(grande);
		nombreColumnas_piezas_llegadas.add("Fecha Solicitud Pedido");//8
		anchos_tabla_piezas_llegadas.add(mediano);
		nombreColumnas_piezas_llegadas.add("Fecha Solicitud Fabrica");//9
		anchos_tabla_piezas_llegadas.add(mediano);
		nombreColumnas_piezas_llegadas.add("Fecha Recepcion Fabrica");//10
		anchos_tabla_piezas_llegadas.add(mediano);
		datosTabla_piezas_llegadas= new Vector<Vector<String>>();
		//tablas Piezas Devueltas
		modelo_tabla_piezas_devueltas = new DefaultTableModel();
		nombreColumnas_piezas_devueltas = new Vector<String> ();
		anchos_tabla_piezas_devueltas = new Vector<Integer>();		
		nombreColumnas_piezas_devueltas.add("ID Pedido");//0
		anchos_tabla_piezas_devueltas.add(75);
		nombreColumnas_piezas_devueltas.add("Numero Pedido");//2
		anchos_tabla_piezas_devueltas.add(chico);
		nombreColumnas_piezas_devueltas.add("Numero Pieza");//3
		anchos_tabla_piezas_devueltas.add(chico);
		nombreColumnas_piezas_devueltas.add("Descripcion");//4
		anchos_tabla_piezas_devueltas.add(mediano);
		nombreColumnas_piezas_devueltas.add("Numero Orden");//5
		anchos_tabla_piezas_devueltas.add(chico);
		nombreColumnas_piezas_devueltas.add("VIN");//6
		anchos_tabla_piezas_devueltas.add(130);
		nombreColumnas_piezas_devueltas.add("Registrante");//7
		anchos_tabla_piezas_devueltas.add(grande);
		nombreColumnas_piezas_devueltas.add("Fecha Solicitud Pedido");//8
		anchos_tabla_piezas_devueltas.add(mediano);
		nombreColumnas_piezas_devueltas.add("Fecha Solicitud Fabrica");//9
		anchos_tabla_piezas_devueltas.add(mediano);
		nombreColumnas_piezas_devueltas.add("Fecha Recepcion Fabrica");//10
		anchos_tabla_piezas_devueltas.add(mediano);
		nombreColumnas_piezas_devueltas.add("Fecha Envio Agente");//11
		anchos_tabla_piezas_devueltas.add(mediano);
		nombreColumnas_piezas_devueltas.add("Fecha Recepcion Agente");//12
		anchos_tabla_piezas_devueltas.add(mediano);
		nombreColumnas_piezas_devueltas.add("Fecha Envio Fabrica");//13
		anchos_tabla_piezas_devueltas.add(mediano);
		datosTabla_piezas_devueltas = new Vector<Vector<String>>();
		//Tabla piezas sin llegar
		modelo_tabla_piezas_sin_llegar = new DefaultTableModel();
		nombreColumnas_piezas_sin_llegar = new Vector<String> ();
		anchos_tabla_piezas_sin_llegar = new Vector<Integer>();
		nombreColumnas_piezas_sin_llegar.add("ID Pedido");//0
		anchos_tabla_piezas_sin_llegar.add(75);
		nombreColumnas_piezas_sin_llegar.add("Numero Pedido");//2
		anchos_tabla_piezas_sin_llegar.add(chico);
		nombreColumnas_piezas_sin_llegar.add("Numero Pieza");//3
		anchos_tabla_piezas_sin_llegar.add(chico);
		nombreColumnas_piezas_sin_llegar.add("Descripcion");//4
		anchos_tabla_piezas_sin_llegar.add(mediano);
		nombreColumnas_piezas_sin_llegar.add("Numero Orden");//5
		anchos_tabla_piezas_sin_llegar.add(chico);
		nombreColumnas_piezas_sin_llegar.add("VIN");//6
		anchos_tabla_piezas_sin_llegar.add(130);
		nombreColumnas_piezas_sin_llegar.add("Registrante");//7
		anchos_tabla_piezas_sin_llegar.add(grande);
		nombreColumnas_piezas_sin_llegar.add("Fecha Solicitud Pedido");//8
		anchos_tabla_piezas_sin_llegar.add(mediano);
		nombreColumnas_piezas_sin_llegar.add("Fecha Solicitud Fabrica");//9
		anchos_tabla_piezas_sin_llegar.add(mediano);
		datosTabla_piezas_sin_llegar = new Vector<Vector<String>>();
		//Tabla piezas LLegadas sin Turno
		modelo_tabla_piezas_llegadas_sin_turno = new DefaultTableModel();
		nombreColumnas_piezas_llegadas_sin_turno = new Vector<String> ();
		anchos_tabla_piezas_llegadas_sin_turno = new Vector<Integer>();
		nombreColumnas_piezas_llegadas_sin_turno.add("ID Pedido");//0
		anchos_tabla_piezas_llegadas_sin_turno.add(75);
		nombreColumnas_piezas_llegadas_sin_turno.add("Numero Pedido");//2
		anchos_tabla_piezas_llegadas_sin_turno.add(chico);
		nombreColumnas_piezas_llegadas_sin_turno.add("Numero Pieza");//3
		anchos_tabla_piezas_llegadas_sin_turno.add(chico);
		nombreColumnas_piezas_llegadas_sin_turno.add("Descripcion");//4
		anchos_tabla_piezas_llegadas_sin_turno.add(mediano);
		nombreColumnas_piezas_llegadas_sin_turno.add("Numero Orden");//5
		anchos_tabla_piezas_llegadas_sin_turno.add(chico);
		nombreColumnas_piezas_llegadas_sin_turno.add("VIN");//6
		anchos_tabla_piezas_llegadas_sin_turno.add(130);
		nombreColumnas_piezas_llegadas_sin_turno.add("Registrante");//7
		anchos_tabla_piezas_llegadas_sin_turno.add(grande);
		nombreColumnas_piezas_llegadas_sin_turno.add("Fecha Solicitud Pedido");//8
		anchos_tabla_piezas_llegadas_sin_turno.add(mediano);
		nombreColumnas_piezas_llegadas_sin_turno.add("Fecha Solicitud Fabrica");//9
		anchos_tabla_piezas_llegadas_sin_turno.add(mediano);
		nombreColumnas_piezas_llegadas_sin_turno.add("Fecha Recepcion Fabrica");//10
		anchos_tabla_piezas_llegadas_sin_turno.add(mediano);
		datosTabla_piezas_llegadas_sin_turno= new Vector<Vector<String>>();
		//Tabla sdp sin numero pedido
		modelo_tabla_sdp_sin_numero_pedido = new DefaultTableModel();
		nombreColumnas_sdp_sin_numero_pedido = new Vector<String> ();
		anchos_tabla_sdp_sin_numero_pedido = new Vector<Integer>();
		nombreColumnas_sdp_sin_numero_pedido.add("ID Pedido");//0
		anchos_tabla_sdp_sin_numero_pedido.add(75);
		nombreColumnas_sdp_sin_numero_pedido.add("Numero Pieza");//3
		anchos_tabla_sdp_sin_numero_pedido.add(chico);
		nombreColumnas_sdp_sin_numero_pedido.add("Descripcion");//4
		anchos_tabla_sdp_sin_numero_pedido.add(mediano);
		nombreColumnas_sdp_sin_numero_pedido.add("Numero Orden");//5
		anchos_tabla_sdp_sin_numero_pedido.add(chico);
		nombreColumnas_sdp_sin_numero_pedido.add("VIN");//6
		anchos_tabla_sdp_sin_numero_pedido.add(130);
		nombreColumnas_sdp_sin_numero_pedido.add("Registrante");//7
		anchos_tabla_sdp_sin_numero_pedido.add(grande);
		nombreColumnas_sdp_sin_numero_pedido.add("Fecha Solicitud Pedido");//8
		anchos_tabla_sdp_sin_numero_pedido.add(mediano);
		nombreColumnas_sdp_sin_numero_pedido.add("Fecha Solicitud Fabrica");//9
		anchos_tabla_sdp_sin_numero_pedido.add(mediano);
		datosTabla_sdp_sin_numero_pedido = new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			cargarLineaPLL(pedidos_piezas.elementAt(i));
			cargarLineaPDev(pedidos_piezas.elementAt(i));
			cargarLineaPSLL(pedidos_piezas.elementAt(i));
			cargarLineaPLLST(pedidos_piezas.elementAt(i));
			cargarLineaSDPSNP(pedidos_piezas.elementAt(i));
		}
		
		modelo_tabla_piezas_llegadas.setDataVector(datosTabla_piezas_llegadas, nombreColumnas_piezas_llegadas);
		modelo_tabla_piezas_llegadas.fireTableStructureChanged();
		modelo_tabla_piezas_devueltas.setDataVector(datosTabla_piezas_devueltas, nombreColumnas_piezas_devueltas);
		modelo_tabla_piezas_devueltas.fireTableStructureChanged();
		modelo_tabla_piezas_sin_llegar.setDataVector(datosTabla_piezas_sin_llegar, nombreColumnas_piezas_sin_llegar);
		modelo_tabla_piezas_sin_llegar.fireTableStructureChanged();
		modelo_tabla_piezas_llegadas_sin_turno.setDataVector(datosTabla_piezas_llegadas_sin_turno, nombreColumnas_piezas_llegadas_sin_turno);
		modelo_tabla_piezas_llegadas_sin_turno.fireTableStructureChanged();

		//Tabla orden sin sdp
		modelo_tabla_orden_sin_solicitud_pedido = new DefaultTableModel();
		nombreColumnas_orden_sin_solicitud_pedido = new Vector<String> ();
		anchos_tabla_orden_sin_solicitud_pedido = new Vector<Integer>();
		nombreColumnas_orden_sin_solicitud_pedido.add("ID Orden");//0
		anchos_tabla_orden_sin_solicitud_pedido.add(75);
		nombreColumnas_orden_sin_solicitud_pedido.add("Numero Orden");//1
		anchos_tabla_orden_sin_solicitud_pedido.add(chico);
		nombreColumnas_orden_sin_solicitud_pedido.add("Fecha Apertura Orden");//2
		anchos_tabla_orden_sin_solicitud_pedido.add(mediano);
		nombreColumnas_orden_sin_solicitud_pedido.add("ID Reclamo");//3
		anchos_tabla_orden_sin_solicitud_pedido.add(75);
		nombreColumnas_orden_sin_solicitud_pedido.add("Fecha Reclamo");//4
		anchos_tabla_orden_sin_solicitud_pedido.add(mediano);
		nombreColumnas_orden_sin_solicitud_pedido.add("Registrante");//5
		anchos_tabla_orden_sin_solicitud_pedido.add(grande);
		datosTabla_orden_sin_solicitud_pedido = new Vector<Vector<String>>();
		modelo_tabla_sdp_sin_numero_pedido.setDataVector(datosTabla_sdp_sin_numero_pedido, nombreColumnas_sdp_sin_numero_pedido);
		modelo_tabla_sdp_sin_numero_pedido.fireTableStructureChanged();	
		
		for (int i=0; i< reclamos.size();i++){
			if(reclamos.elementAt(i).getOrden()!=null && !conPedido(reclamos.elementAt(i))){
				Vector<String> row = new Vector<String> ();
				row.add(reclamos.elementAt(i).getOrden().getId().toString());//ID Orden
				row.add(reclamos.elementAt(i).getOrden().getNumero_orden());//Numero Orden				
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
				if(reclamos.elementAt(i).getOrden().getFecha_apertura()!=null){
					java.sql.Date fao = new java.sql.Date(reclamos.elementAt(i).getOrden().getFecha_apertura().getTime());
					row.add(format2.format(fao));//Fecha Apertura Orden
				}else{
					row.add("");
				}
				row.add(reclamos.elementAt(i).getId().toString());//ID Reclamo
				if(reclamos.elementAt(i).getFecha_reclamo()!=null){
					java.sql.Date fr = new java.sql.Date(reclamos.elementAt(i).getFecha_reclamo().getTime());
					row.add(format2.format(fr));//Fecha Reclamo
				}else{
					row.add("");
				}
				row.add(reclamos.elementAt(i).getRegistrante().getNombre_registrante());//Registrante
				
				datosTabla_orden_sin_solicitud_pedido.add(row);
			}
		}
		modelo_tabla_orden_sin_solicitud_pedido.setDataVector(datosTabla_orden_sin_solicitud_pedido, nombreColumnas_orden_sin_solicitud_pedido);
		modelo_tabla_orden_sin_solicitud_pedido.fireTableStructureChanged();
	}

	private boolean conPedido(ReclamoDTO reclamo) {
		boolean resp = false;
		for (int i = 0; i< pedidos_piezas.size();i++){
			if(pedidos_piezas.elementAt(i).getPedido().getReclamo().getId().equals(reclamo.getId()))
				resp = true;
		}
		return resp;
	}
	
	private void cargarLineaPLL(Pedido_PiezaDTO pedido_pieza){
		boolean resp = false;
		if (mediador.esEntidad(pedido_pieza.getPedido().getReclamo().getRegistrante())){
			resp = pedido_pieza.getFecha_recepcion_fabrica()!=null && pedido_pieza.getDevolucion_pieza()==null && pedido_pieza.getFecha_solicitud_fabrica()!=null;
		}else{
			resp = pedido_pieza.getFecha_recepcion_fabrica()!=null && pedido_pieza.getFecha_envio_agente()==null  && pedido_pieza.getDevolucion_pieza()==null && pedido_pieza.getFecha_solicitud_fabrica()!=null;
		}					
		if(resp){
			Vector<String> row = new Vector<String> ();

			row.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
			row.add(pedido_pieza.getNumero_pedido());//Numero Pedido
			row.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
			row.add(pedido_pieza.getPieza().getDescripcion());//Descripcion Pieza
			row.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
			row.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 

			if(pedido_pieza.getPedido().getFecha_solicitud_pedido()!=null){
				java.sql.Date fsp = new java.sql.Date(pedido_pieza.getPedido().getFecha_solicitud_pedido().getTime());
				row.add(format2.format(fsp));//Fecha Solicitud Pedido
			}else{
				row.add("");
			}			    
			
			if (pedido_pieza.getFecha_solicitud_fabrica()!=null){
				java.sql.Date fsf = new java.sql.Date(pedido_pieza.getFecha_solicitud_fabrica().getTime());
				row.add(format2.format(fsf));//Fecha Solicitud Fabrica
			}else{
				row.add("");
			}
			if (pedido_pieza.getFecha_recepcion_fabrica()!=null){
				java.sql.Date frf = new java.sql.Date(pedido_pieza.getFecha_recepcion_fabrica().getTime());
				row.add(format2.format(frf));//Fecha Recepcion Fabrica
			}else{
				row.add("");
			}
			datosTabla_piezas_llegadas.add(row);
		}
	}
	
	private void cargarLineaPDev(Pedido_PiezaDTO pedido_pieza){
		if(pedido_pieza.getDevolucion_pieza()!=null && pedido_pieza.getDevolucion_pieza().getFecha_devolucion()!=null){
			Vector<String> row = new Vector<String> ();

			row.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
			row.add(pedido_pieza.getNumero_pedido());//Numero Pedido
			row.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
			row.add(pedido_pieza.getPieza().getDescripcion());//Descripcion Pieza
			row.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
			row.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 

			if(pedido_pieza.getPedido().getFecha_solicitud_pedido()!=null){
				java.sql.Date fsp = new java.sql.Date(pedido_pieza.getPedido().getFecha_solicitud_pedido().getTime());
				row.add(format2.format(fsp));//Fecha Solicitud Pedido
			}else{
				row.add("");
			}			    
			
			if (pedido_pieza.getFecha_solicitud_fabrica()!=null){
				java.sql.Date fsf = new java.sql.Date(pedido_pieza.getFecha_solicitud_fabrica().getTime());
				row.add(format2.format(fsf));//Fecha Solicitud Fabrica
			}else{
				row.add("");
			}
			if (pedido_pieza.getFecha_recepcion_fabrica()!=null){
				java.sql.Date frf = new java.sql.Date(pedido_pieza.getFecha_recepcion_fabrica().getTime());
				row.add(format2.format(frf));//Fecha Recepcion Fabrica
			}else{
				row.add("");
			}
			if (pedido_pieza.getFecha_envio_agente()!=null){
				java.sql.Date fea = new java.sql.Date(pedido_pieza.getFecha_envio_agente().getTime());
				row.add(format2.format(fea));//Fecha Envio Agente
			}else{
				row.add("");
			}
			if (pedido_pieza.getFecha_recepcion_agente()!=null){
				java.sql.Date fra = new java.sql.Date(pedido_pieza.getFecha_recepcion_agente().getTime());
				row.add(format2.format(fra));//Fecha Recepcion Agente
			}else{
				row.add("");
			}
			if (pedido_pieza.getDevolucion_pieza()!=null && pedido_pieza.getDevolucion_pieza().getFecha_devolucion()!=null){
				java.sql.Date fef = new java.sql.Date(pedido_pieza.getDevolucion_pieza().getFecha_devolucion().getTime());
				row.add(format2.format(fef));//Fecha Envio Fabrica
			}else{
				row.add("");
			}
			datosTabla_piezas_devueltas.add(row);
		}
	}

	private void cargarLineaPSLL(Pedido_PiezaDTO pedido_pieza){
		if(pedido_pieza.getFecha_recepcion_fabrica()==null && pedido_pieza.getFecha_solicitud_fabrica()!=null ){
			Vector<String> row = new Vector<String> ();

			row.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
			row.add(pedido_pieza.getNumero_pedido());//Numero Pedido
			row.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
			row.add(pedido_pieza.getPieza().getDescripcion());//Descripcion Pieza
			row.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
			row.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 

			if(pedido_pieza.getPedido().getFecha_solicitud_pedido()!=null){
				java.sql.Date fsp = new java.sql.Date(pedido_pieza.getPedido().getFecha_solicitud_pedido().getTime());
				row.add(format2.format(fsp));//Fecha Solicitud Pedido
			}else{
				row.add("");
			}			    
			
			if (pedido_pieza.getFecha_solicitud_fabrica()!=null){
				java.sql.Date fsf = new java.sql.Date(pedido_pieza.getFecha_solicitud_fabrica().getTime());
				row.add(format2.format(fsf));//Fecha Solicitud Fabrica
			}else{
				row.add("");
			}
			datosTabla_piezas_sin_llegar.add(row);
		}
	}
	
	private void cargarLineaPLLST(Pedido_PiezaDTO pedido_pieza){
		boolean resp = false;
		if (mediador.esEntidad(pedido_pieza.getPedido().getReclamo().getRegistrante())){
			resp = pedido_pieza.getFecha_recepcion_fabrica()!=null && pedido_pieza.getDevolucion_pieza()==null && pedido_pieza.getFecha_solicitud_fabrica()!=null;
		}				
		if(resp && pedido_pieza.getPedido().getReclamo().getFecha_turno()==null){
			Vector<String> row = new Vector<String> ();

			row.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
			row.add(pedido_pieza.getNumero_pedido());//Numero Pedido
			row.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
			row.add(pedido_pieza.getPieza().getDescripcion());//Descripcion Pieza
			row.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
			row.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 

			if(pedido_pieza.getPedido().getFecha_solicitud_pedido()!=null){
				java.sql.Date fsp = new java.sql.Date(pedido_pieza.getPedido().getFecha_solicitud_pedido().getTime());
				row.add(format2.format(fsp));//Fecha Solicitud Pedido
			}else{
				row.add("");
			}			    
			
			if (pedido_pieza.getFecha_solicitud_fabrica()!=null){
				java.sql.Date fsf = new java.sql.Date(pedido_pieza.getFecha_solicitud_fabrica().getTime());
				row.add(format2.format(fsf));//Fecha Solicitud Fabrica
			}else{
				row.add("");
			}
			if (pedido_pieza.getFecha_recepcion_fabrica()!=null){
				java.sql.Date frf = new java.sql.Date(pedido_pieza.getFecha_recepcion_fabrica().getTime());
				row.add(format2.format(frf));//Fecha Recepcion Fabrica
			}else{
				row.add("");
			}
			datosTabla_piezas_llegadas_sin_turno.add(row);
		}
	}
	
	private void cargarLineaSDPSNP(Pedido_PiezaDTO pedido_pieza){
		if(pedido_pieza.getNumero_pedido()==null){
			Vector<String> row = new Vector<String> ();
	
			row.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
			row.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
			row.add(pedido_pieza.getPieza().getDescripcion());//Descripcion Pieza
			row.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
			row.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
	
			if(pedido_pieza.getPedido().getFecha_solicitud_pedido()!=null){
				java.sql.Date fsp = new java.sql.Date(pedido_pieza.getPedido().getFecha_solicitud_pedido().getTime());
				row.add(format2.format(fsp));//Fecha Solicitud Pedido
			}else{
				row.add("");
			}			    
			
			if (pedido_pieza.getFecha_solicitud_fabrica()!=null){
				java.sql.Date fsf = new java.sql.Date(pedido_pieza.getFecha_solicitud_fabrica().getTime());
				row.add(format2.format(fsf));//Fecha Solicitud Fabrica
			}else{
				row.add("");
			}
	
			datosTabla_sdp_sin_numero_pedido.add(row);
		}
	}
	
	private void initialize() {
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTES GESTION");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//GESTION

		JPanel pnGestion = new JPanel();
		pnGestion.setBounds(0, 0, 1270, 672);
		contentPane.add(pnGestion);
		pnGestion.setLayout(null);
		
		JTabbedPane gestion = new JTabbedPane(JTabbedPane.TOP);
		gestion.setBounds(0, 0, 1270, 665);
		pnGestion.add(gestion);
		
		
		//////////////////////////////
		//		Piezas Llegadas		//
		//////////////////////////////
		
		//Tabla Piezas Llegadas//
		pnPiezaLLegadas = new JPanel();
		gestion.addTab("PIEZAS LLEGADAS", null, pnPiezaLLegadas, null);
		pnPiezaLLegadas.setLayout(null);
		modelo_tabla_piezas_llegadas = new DefaultTableModel(datosTabla_piezas_llegadas, nombreColumnas_piezas_llegadas);
		tabla_PLL = new JTable(modelo_tabla_piezas_llegadas) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false,false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_tabla_piezas_llegadas = new TableRowSorter<TableModel>(modelo_tabla_piezas_llegadas);
		for(int i = 0; i < tabla_PLL.getColumnCount(); i++) {
			tabla_PLL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas.elementAt(i));
			tabla_PLL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas.elementAt(i));
		}
		tabla_PLL.setRowSorter(ordenador_tabla_piezas_llegadas);
		tabla_PLL.getTableHeader().setReorderingAllowed(false);
		tabla_PLL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane_piezas_llegadas= new JScrollPane(tabla_PLL);
		scrollPane_piezas_llegadas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_piezas_llegadas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_piezas_llegadas.setBounds(5, 160, 1255, 470);
		tabla_PLL.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		pnPiezaLLegadas.add(scrollPane_piezas_llegadas);
		
		//Filtros Piezas LLegadas//
		rB_Itervalo_PLL = new JRadioButton("Intervalo de Fechas");
		rB_Itervalo_PLL.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
		rB_Itervalo_PLL.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
					if(rB_Itervalo_PLL.isSelected()){
						dC_FInicioPLL.setEnabled(true);
						dC_FFinPLL.setEnabled(true);
						btnFiltrar_PLL.setEnabled(true);
						
						rBMAnteriorPLL.setSelected(false);
						rB_Hoy_PLL.setSelected(false);
						rB_USemana_PLL.setSelected(false);
						rB_Mes_PLL.setSelected(false);
						rB_Anio_PLL.setSelected(false);
					}else{
						dC_FInicioPLL.setEnabled(false);
						dC_FFinPLL.setEnabled(false);
						btnFiltrar_PLL.setEnabled(false);
					}
				}
		});	
		rB_Itervalo_PLL.setBounds(5, 10, 150, 20);
		pnPiezaLLegadas.add(rB_Itervalo_PLL);
		
		rB_Hoy_PLL = new JRadioButton("Hoy");
		rB_Hoy_PLL.setToolTipText("Piezas llegadas el dia de hoy.");
		rB_Hoy_PLL.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
					if(rB_Hoy_PLL.isSelected()){
						rBMAnteriorPLL.setSelected(false);
						dC_FInicioPLL.setEnabled(true);
						dC_FFinPLL.setEnabled(true);
						rB_Itervalo_PLL.setSelected(false);
						rB_USemana_PLL.setSelected(false);
						rB_Mes_PLL.setSelected(false);
						rB_Anio_PLL.setSelected(false);
						btnFiltrar_PLL.setEnabled(false);

						filtrarHoyPLL();
										
					}
			}
		});		
		rB_Hoy_PLL.setBounds(5, 35, 150, 20);
		pnPiezaLLegadas.add(rB_Hoy_PLL);
		
		rB_USemana_PLL = new JRadioButton("Ultima Semana");
		rB_USemana_PLL.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
		rB_USemana_PLL.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
					if(rB_USemana_PLL.isSelected()){
						rB_Itervalo_PLL.setSelected(false);
						dC_FInicioPLL.setEnabled(false);
						dC_FFinPLL.setEnabled(false);
						rB_Hoy_PLL.setSelected(false);
						rB_Mes_PLL.setSelected(false);
						rBMAnteriorPLL.setSelected(false);
						rB_Anio_PLL.setSelected(false);
						btnFiltrar_PLL.setEnabled(false);
						filtrarUSemanaPLL();
					}
				}
		});
		rB_USemana_PLL.setBounds(5, 60, 150, 20);
		pnPiezaLLegadas.add(rB_USemana_PLL);
		
		rB_Mes_PLL = new JRadioButton("Este Mes");
		rB_Mes_PLL.setToolTipText("Piezas llegadas en el mes corriente.");
		rB_Mes_PLL.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
					if(rB_Mes_PLL.isSelected()){
						rB_Itervalo_PLL.setSelected(false);
						dC_FInicioPLL.setEnabled(false);
						dC_FFinPLL.setEnabled(false);
						rB_Hoy_PLL.setSelected(false);
						rB_USemana_PLL.setSelected(false);
						rBMAnteriorPLL.setSelected(false);
						rB_Anio_PLL.setSelected(false);
						btnFiltrar_PLL.setEnabled(false);
						filtrarUMesPLL();
					}
				}
		});
		rB_Mes_PLL.setBounds(5, 85, 150, 20);
		pnPiezaLLegadas.add(rB_Mes_PLL);
		
		rBMAnteriorPLL = new JRadioButton("Mes Anterior");
		rBMAnteriorPLL.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rBMAnteriorPLL.isSelected()){
					rB_Itervalo_PLL.setSelected(false);
					dC_FInicioPLL.setEnabled(false);
					dC_FFinPLL.setEnabled(false);
					rB_Hoy_PLL.setSelected(false);
					rB_USemana_PLL.setSelected(false);
					rB_Mes_PLL.setSelected(false);
					rB_Anio_PLL.setSelected(false);
					btnFiltrar_PLL.setEnabled(false);
					filtrarMesAnteriorPLL();
				}
			}
		});
		rBMAnteriorPLL.setBounds(5, 110, 150, 20);
		pnPiezaLLegadas.add(rBMAnteriorPLL);
		
		rB_Anio_PLL = new JRadioButton("Ultimo Año");
		rB_Anio_PLL.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
					if(rB_Anio_PLL.isSelected()){
						rB_Itervalo_PLL.setSelected(false);
						dC_FInicioPLL.setEnabled(false);
						dC_FFinPLL.setEnabled(false);
						rB_Hoy_PLL.setSelected(false);
						rB_USemana_PLL.setSelected(false);
						rB_Mes_PLL.setSelected(false);
						rBMAnteriorPLL.setSelected(false);
						btnFiltrar_PLL.setEnabled(false);
						filtrarUAnioPLL();
					}
				}
		});
		rB_Anio_PLL.setBounds(5, 135, 150, 20);
		pnPiezaLLegadas.add(rB_Anio_PLL);
								
		JLabel label_2 = new JLabel("Fecha Inicio");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setEnabled(false);
		label_2.setBounds(160, 10, 120, 20);
		pnPiezaLLegadas.add(label_2);
		
		dC_FInicioPLL = new JDateChooser();
		dC_FInicioPLL.setEnabled(false);
		dC_FInicioPLL.setBounds(290, 10, 150, 20);
		pnPiezaLLegadas.add(dC_FInicioPLL);
		
		JLabel label_3 = new JLabel("Fecha Fin");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setEnabled(false);
		label_3.setBounds(485, 10, 120, 20);
		pnPiezaLLegadas.add(label_3);
		
		dC_FFinPLL = new JDateChooser();
		dC_FFinPLL.setEnabled(false);
		dC_FFinPLL.setBounds(615, 10, 150, 20);
		pnPiezaLLegadas.add(dC_FFinPLL);
		
		btnFiltrar_PLL = new JButton("Filtrar");
		btnFiltrar_PLL.setEnabled(false);
		btnFiltrar_PLL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtrarIntervaloPLL();
			}
		});
		btnFiltrar_PLL.setBounds(810, 10, 110, 20);
		pnPiezaLLegadas.add(btnFiltrar_PLL);
		
		btn_clear_FIPLL = new JButton("");
		btn_clear_FIPLL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FInicioPLL.getDate()!=null)
					dC_FInicioPLL.setDate(null);
			}
		});
		btn_clear_FIPLL.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FIPLL.setBounds(450, 10, 25, 20);
		pnPiezaLLegadas.add(btn_clear_FIPLL);
		
		btn_clear_FFPLL = new JButton("");
		btn_clear_FFPLL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FFinPLL.getDate()!=null)
					dC_FFinPLL.setDate(null);
			}
		});
		btn_clear_FFPLL.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FFPLL.setBounds(775, 10, 25, 20);
		pnPiezaLLegadas.add(btn_clear_FFPLL);
		
		btnExportarTablaPLL = new JButton("");
		btnExportarTablaPLL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaPLL();
			}
		});
		btnExportarTablaPLL.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTablaPLL.setBounds(1230, 120, 32, 32);
		pnPiezaLLegadas.add(btnExportarTablaPLL);
	
		//////////////////////////////////
		//		Piezas Devueltas 		//
		/////////////////////////////////

		//Tabla Piezas Devueltas//
		pnPiezasDevueltas = new JPanel();
		gestion.addTab("PIEZAS DEVUELTAS", null, pnPiezasDevueltas, null);
		pnPiezasDevueltas.setLayout(null);
		modelo_tabla_piezas_devueltas = new DefaultTableModel(datosTabla_piezas_devueltas, nombreColumnas_piezas_devueltas);
		tabla_PD = new JTable(modelo_tabla_piezas_devueltas) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false,false, false, 
					false, false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_tabla_piezas_devueltas = new TableRowSorter<TableModel>(modelo_tabla_piezas_devueltas);
		for(int i = 0; i < tabla_PD.getColumnCount(); i++) {
			tabla_PD.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_devueltas.elementAt(i));
			tabla_PD.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_devueltas.elementAt(i));
		}
		tabla_PD.setRowSorter(ordenador_tabla_piezas_devueltas);
		tabla_PD.getTableHeader().setReorderingAllowed(false);
		tabla_PD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_piezas_devueltas = new JScrollPane(tabla_PD);
		scrollPane_piezas_devueltas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_piezas_devueltas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_piezas_devueltas.setBounds(5, 160, 1255, 470);
		pnPiezasDevueltas.add(scrollPane_piezas_devueltas);
		tabla_PD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//Filtros Piezas LLegadas//		
		dC_FI_PD = new JDateChooser();
		dC_FI_PD.setEnabled(false);
		dC_FI_PD.setBounds(290, 10, 150, 20);
		pnPiezasDevueltas.add(dC_FI_PD);
		
		JLabel lblFechaDeste = new JLabel("Fecha Inicio");
		lblFechaDeste.setEnabled(false);
		lblFechaDeste.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaDeste.setBounds(160, 10, 120, 20);
		pnPiezasDevueltas.add(lblFechaDeste);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setEnabled(false);
		lblFechaFin.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaFin.setBounds(485, 10, 120, 20);
		pnPiezasDevueltas.add(lblFechaFin);
		
		dC_FF_PD = new JDateChooser();
		dC_FF_PD.setEnabled(false);
		dC_FF_PD.setBounds(615, 10, 150, 20);
		pnPiezasDevueltas.add(dC_FF_PD);
		
		rB_Intervalo_PD = new JRadioButton("Intervalo de Fechas");
		rB_Intervalo_PD.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Intervalo_PD.isSelected()){
					dC_FF_PD.setEnabled(true);
					dC_FI_PD.setEnabled(true);
					btnFiltrarPDev.setEnabled(true);
					rB_Hoy_PD.setSelected(false);
					rB_USemana_PD.setSelected(false);
					rB_Mes_PD.setSelected(false);
					rbMAnteriorPDev.setSelected(false);
					rB_Anio_PD.setSelected(false);
				}else{
					dC_FF_PD.setEnabled(false);
					dC_FI_PD.setEnabled(false);
					btnFiltrarPDev.setEnabled(false);
				}
			}
		});
		rB_Intervalo_PD.setBounds(5, 10, 150, 20);
		pnPiezasDevueltas.add(rB_Intervalo_PD);
		
		rB_Hoy_PD = new JRadioButton("Hoy");
		rB_Hoy_PD.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Hoy_PD.isSelected()){
					rB_Intervalo_PD.setSelected(false);
					dC_FF_PD.setEnabled(false);
					dC_FI_PD.setEnabled(false);
					rB_USemana_PD.setSelected(false);
					rB_Mes_PD.setSelected(false);
					rbMAnteriorPDev.setSelected(false);
					rB_Anio_PD.setSelected(false);
					filtrarHoyPDev();
				}
			}
		});
		rB_Hoy_PD.setBounds(5, 35, 150, 20);
		pnPiezasDevueltas.add(rB_Hoy_PD);
		
		rB_USemana_PD = new JRadioButton("Ultima Semana");
		rB_USemana_PD.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_USemana_PD.isSelected()){
					rB_Intervalo_PD.setSelected(false);
					dC_FF_PD.setEnabled(false);
					dC_FI_PD.setEnabled(false);
					rB_Hoy_PD.setSelected(false);
					rB_Mes_PD.setSelected(false);
					rbMAnteriorPDev.setSelected(false);
					rB_Anio_PD.setSelected(false);
					filtrarUSemanaPDev();
				}
			}
		});
		rB_USemana_PD.setBounds(5, 60, 150, 20);
		pnPiezasDevueltas.add(rB_USemana_PD);
		
		rbMAnteriorPDev = new JRadioButton("Mes Anterior");
		rbMAnteriorPDev.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rbMAnteriorPDev.isSelected()){
					rB_Intervalo_PD.setSelected(false);
					dC_FF_PD.setEnabled(false);
					dC_FI_PD.setEnabled(false);
					rB_Hoy_PD.setSelected(false);
					rB_USemana_PD.setSelected(false);
					rB_Mes_PD.setSelected(false);
					rB_Anio_PD.setSelected(false);
					filtrarManteriorPDev();
				}
			}
		});
		rbMAnteriorPDev.setBounds(5, 110, 150, 20);
		pnPiezasDevueltas.add(rbMAnteriorPDev);
		
		rB_Mes_PD = new JRadioButton("Este Mes");
		rB_Mes_PD.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Mes_PD.isSelected()){
					rB_Intervalo_PD.setSelected(false);
					dC_FF_PD.setEnabled(false);
					dC_FI_PD.setEnabled(false);
					rB_Hoy_PD.setSelected(false);
					rB_USemana_PD.setSelected(false);
					rbMAnteriorPDev.setSelected(false);
					rB_Anio_PD.setSelected(false);
					filtrarUMesPDev();
				}
			}
		});
		rB_Mes_PD.setBounds(5, 85, 150, 20);
		pnPiezasDevueltas.add(rB_Mes_PD);
		
		rB_Anio_PD = new JRadioButton("Ultimo A\u00F1o");
		rB_Anio_PD.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Anio_PD.isSelected()){
					rB_Intervalo_PD.setSelected(false);
					dC_FF_PD.setEnabled(false);
					dC_FI_PD.setEnabled(false);
					rB_Hoy_PD.setSelected(false);
					rB_USemana_PD.setSelected(false);
					rB_Mes_PD.setSelected(false);
					rbMAnteriorPDev.setSelected(false);
					filtrarUAnioPDev();
				}
			}
		});
		rB_Anio_PD.setBounds(5, 135, 150, 20);
		pnPiezasDevueltas.add(rB_Anio_PD);
					
		btnFiltrarPDev = new JButton("Filtrar");
		btnFiltrarPDev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtrarInervaloPDev();
			}
		});
		btnFiltrarPDev.setEnabled(false);
		btnFiltrarPDev.setBounds(810, 10, 110, 20);
		pnPiezasDevueltas.add(btnFiltrarPDev);
		
		btn_clear_FIPD = new JButton("");
		btn_clear_FIPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(dC_FI_PD.getDate()!=null)
					dC_FI_PD.setDate(null);
			}
		});
		btn_clear_FIPD.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FIPD.setBounds(450, 10, 25, 20);
		pnPiezasDevueltas.add(btn_clear_FIPD);
		
		btn_clear_FFPD = new JButton("");
		btn_clear_FFPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_PD.getDate()!=null)
					dC_FF_PD.setDate(null);
			}
		});
		btn_clear_FFPD.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FFPD.setBounds(775, 10, 25, 20);
		pnPiezasDevueltas.add(btn_clear_FFPD);
		
		btnExportarTablaPD = new JButton("");
		btnExportarTablaPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaPD();
			}
		});
		btnExportarTablaPD.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTablaPD.setBounds(1230, 120, 32, 32);
		pnPiezasDevueltas.add(btnExportarTablaPD);
		
		///////////////////////////////
		//		Piezas Sin Llegar	 //
		//////////////////////////////
		
		//Tabla Piezas Sin Llegar//
		pnPiezasPedidasSinLLegar = new JPanel();
		gestion.addTab("PIEZAS SIN LLEGAR", null, pnPiezasPedidasSinLLegar, null);
		pnPiezasPedidasSinLLegar.setLayout(null);
		modelo_tabla_piezas_sin_llegar = new DefaultTableModel(datosTabla_piezas_sin_llegar, nombreColumnas_piezas_sin_llegar);
		tabla_SLL = new JTable(modelo_tabla_piezas_sin_llegar) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_tabla_piezas_sin_llegar = new TableRowSorter<TableModel>(modelo_tabla_piezas_sin_llegar);
		for(int i = 0; i < tabla_SLL.getColumnCount(); i++) {
			tabla_SLL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
			tabla_SLL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
		}
		tabla_SLL.setRowSorter(ordenador_tabla_piezas_sin_llegar);
		tabla_SLL.getTableHeader().setReorderingAllowed(false);
		tabla_SLL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_pedidas_sin_llegar = new JScrollPane(tabla_SLL);
		scrollPane_pedidas_sin_llegar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_pedidas_sin_llegar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_pedidas_sin_llegar.setBounds(5, 160, 1255, 470);
		pnPiezasPedidasSinLLegar.add(scrollPane_pedidas_sin_llegar);
		//tabla_piezas_sin_llegar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//Filtros Piezas Sin Llegar//
		dC_FI_SLL = new JDateChooser();
		dC_FI_SLL.setBounds(290, 10, 150, 20);
		dC_FI_SLL.setEnabled(false);
		pnPiezasPedidasSinLLegar.add(dC_FI_SLL);
		
		JLabel label_4 = new JLabel("Fecha Inicio");
		label_4.setBounds(160, 10, 120, 20);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setEnabled(false);
		pnPiezasPedidasSinLLegar.add(label_4);
		
		JLabel label_5 = new JLabel("Fecha Fin");
		label_5.setBounds(485, 10, 120, 20);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setEnabled(false);
		pnPiezasPedidasSinLLegar.add(label_5);
		
		dC_FF_SLL = new JDateChooser();
		dC_FF_SLL.setBounds(615, 10, 150, 20);
		dC_FF_SLL.setEnabled(false);
		pnPiezasPedidasSinLLegar.add(dC_FF_SLL);
		
		rB_Intervalo_SLL = new JRadioButton("Intervalo de Fechas");
		rB_Intervalo_SLL.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Intervalo_SLL.isSelected()){
					dC_FI_SLL.setEnabled(true);
					dC_FF_SLL.setEnabled(true);
					btnFiltrar_SLL.setEnabled(true);
					rB_Hoy_SLL.setSelected(false);
					rB_USemana_SLL.setSelected(false);
					rB_Mes_SLL.setSelected(false);
					rB_MAnterior_SLL.setSelected(false);
					rB_Anio_SLL.setSelected(false);
				}else{
					dC_FI_SLL.setEnabled(false);
					dC_FF_SLL.setEnabled(false);
					btnFiltrar_SLL.setEnabled(false);
				}
			}
		});
		rB_Intervalo_SLL.setBounds(5, 10, 150, 20);
		pnPiezasPedidasSinLLegar.add(rB_Intervalo_SLL);
		
		rB_Hoy_SLL = new JRadioButton("Hoy");
		rB_Hoy_SLL.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Hoy_SLL.isSelected()){
					rB_Intervalo_SLL.setSelected(false);
					dC_FI_SLL.setEnabled(false);
					dC_FF_SLL.setEnabled(false);
					rB_USemana_SLL.setSelected(false);
					rB_Mes_SLL.setSelected(false);
					rB_MAnterior_SLL.setSelected(false);
					rB_Anio_SLL.setSelected(false);
					filtrarHoySLL();
				}
			}
		});
		rB_Hoy_SLL.setBounds(5, 35, 150, 20);
		pnPiezasPedidasSinLLegar.add(rB_Hoy_SLL);
		
		rB_USemana_SLL = new JRadioButton("Ultima Semana");
		rB_USemana_SLL.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_USemana_SLL.isSelected()){
					rB_Intervalo_SLL.setSelected(false);
					dC_FI_SLL.setEnabled(false);
					dC_FF_SLL.setEnabled(false);
					rB_Hoy_SLL.setSelected(false);
					rB_Mes_SLL.setSelected(false);
					rB_MAnterior_SLL.setSelected(false);
					rB_Anio_SLL.setSelected(false);
					filtrarSemanaSLL();
				}
			}
		});
		rB_USemana_SLL.setBounds(5, 60, 150, 20);
		pnPiezasPedidasSinLLegar.add(rB_USemana_SLL);
		
		rB_Mes_SLL = new JRadioButton("Este Mes");
		rB_Mes_SLL.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Mes_SLL.isSelected()){
					rB_Intervalo_SLL.setSelected(false);
					dC_FI_SLL.setEnabled(false);
					dC_FF_SLL.setEnabled(false);
					rB_Hoy_SLL.setSelected(false);
					rB_USemana_SLL.setSelected(false);
					rB_MAnterior_SLL.setSelected(false);
					rB_Anio_SLL.setSelected(false);
					filtrarMesSLL();
				}
			}
		});
		rB_Mes_SLL.setBounds(5, 85, 150, 20);
		pnPiezasPedidasSinLLegar.add(rB_Mes_SLL);
		
		rB_Anio_SLL = new JRadioButton("Ultimo A\u00F1o");
		rB_Anio_SLL.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Anio_SLL.isSelected()){
					rB_Intervalo_SLL.setSelected(false);
					dC_FI_SLL.setEnabled(false);
					dC_FF_SLL.setEnabled(false);
					rB_Hoy_SLL.setSelected(false);
					rB_USemana_SLL.setSelected(false);
					rB_Mes_SLL.setSelected(false);
					rB_MAnterior_SLL.setSelected(false);
					filtrarAnioSLL();
				}
			}
		});
		rB_Anio_SLL.setBounds(5, 135, 150, 20);
		pnPiezasPedidasSinLLegar.add(rB_Anio_SLL);
		
		rB_MAnterior_SLL = new JRadioButton("Mes Anterior");
		rB_MAnterior_SLL.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_MAnterior_SLL.isSelected()){
					rB_Intervalo_SLL.setSelected(false);
					dC_FI_SLL.setEnabled(false);
					dC_FF_SLL.setEnabled(false);
					rB_Hoy_SLL.setSelected(false);
					rB_USemana_SLL.setSelected(false);
					rB_Mes_SLL.setSelected(false);
					rB_Anio_SLL.setSelected(false);
					filtrarMPasadoSLL();
				}
			}
		});
		rB_MAnterior_SLL.setBounds(5, 110, 150, 20);
		pnPiezasPedidasSinLLegar.add(rB_MAnterior_SLL);
		
		btnFiltrar_SLL = new JButton("Filtrar");
		btnFiltrar_SLL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarIntervaloSLL();
			}
		});
		btnFiltrar_SLL.setEnabled(false);
		btnFiltrar_SLL.setBounds(810, 10, 110, 20);
		pnPiezasPedidasSinLLegar.add(btnFiltrar_SLL);
		
		btn_clear_FISLL = new JButton("");
		btn_clear_FISLL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(dC_FI_SLL.getDate()!=null)
					dC_FI_SLL.setDate(null);
			}
		});
		btn_clear_FISLL.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FISLL.setBounds(450, 10, 25, 20);
		pnPiezasPedidasSinLLegar.add(btn_clear_FISLL);
		
		btn_clear_FFSLL = new JButton("");
		btn_clear_FFSLL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_SLL.getDate()!=null)
					dC_FF_SLL.setDate(null);
			}
		});
		btn_clear_FFSLL.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FFSLL.setBounds(775, 10, 25, 20);
		pnPiezasPedidasSinLLegar.add(btn_clear_FFSLL);
		
		btnExportarTablaSLL = new JButton("");
		btnExportarTablaSLL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaSLL();
			}
		});
		btnExportarTablaSLL.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTablaSLL.setBounds(1230, 120, 32, 32);
		pnPiezasPedidasSinLLegar.add(btnExportarTablaSLL);
		
		////////////////////////////////////
		//Piezas Llegadas Sin Turno //
		///////////////////////////////////
		
		//Tabla Piezas Llegadas Sin Turno //
		pnPiezasLLegadasSinTurno = new JPanel();
		gestion.addTab("PIEZAS LLEGADAS SIN TURNO", null, pnPiezasLLegadasSinTurno, null);
		pnPiezasLLegadasSinTurno.setLayout(null);
		modelo_tabla_piezas_llegadas_sin_turno = new DefaultTableModel(datosTabla_piezas_llegadas_sin_turno, nombreColumnas_piezas_llegadas_sin_turno);
		tabla_PLLST = new JTable(modelo_tabla_piezas_llegadas_sin_turno) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_tabla_piezas_llegadas_sin_turno = new TableRowSorter<TableModel>(modelo_tabla_piezas_llegadas_sin_turno);
		for(int i = 0; i < tabla_PLLST.getColumnCount(); i++) {
			tabla_PLLST.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
			tabla_PLLST.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
		}
		tabla_PLLST.setRowSorter(ordenador_tabla_piezas_llegadas_sin_turno);
		tabla_PLLST.getTableHeader().setReorderingAllowed(false);
		tabla_PLLST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_piezas_llegadas_sin_turno = new JScrollPane(tabla_PLLST);
		scrollPane_piezas_llegadas_sin_turno.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_piezas_llegadas_sin_turno.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_piezas_llegadas_sin_turno.setBounds(5, 160, 1255, 470);
		pnPiezasLLegadasSinTurno.add(scrollPane_piezas_llegadas_sin_turno);
		tabla_PLLST.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//Filtros Piezas Llegadas Sin Turno //
		dC_FI_PLLST = new JDateChooser();
		dC_FI_PLLST.setEnabled(false);
		dC_FI_PLLST.setBounds(290, 10, 150, 20);
		pnPiezasLLegadasSinTurno.add(dC_FI_PLLST);
		
		JLabel label_6 = new JLabel("Fecha Inicio");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setEnabled(false);
		label_6.setBounds(160, 10, 120, 20);
		pnPiezasLLegadasSinTurno.add(label_6);
		
		JLabel label_7 = new JLabel("Fecha Fin");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setEnabled(false);
		label_7.setBounds(485, 10, 120, 20);
		pnPiezasLLegadasSinTurno.add(label_7);
		
		dC_FF_PLLST = new JDateChooser();
		dC_FF_PLLST.setEnabled(false);
		dC_FF_PLLST.setBounds(615, 10, 150, 20);
		pnPiezasLLegadasSinTurno.add(dC_FF_PLLST);
		
		rB_Intervalo_PLLST = new JRadioButton("Intervalo de Fechas");
		rB_Intervalo_PLLST.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Intervalo_PLLST.isSelected()){
					dC_FI_PLLST.setEnabled(true);
					dC_FF_PLLST.setEnabled(true);
					btnFiltrarPLLST.setEnabled(true);

					rB_Hoy_PLLST.setSelected(false);
					rB_USemana_PLLST.setSelected(false);
					rB_UMes_PLLST.setSelected(false);
					rBMesPasado_PLLST.setSelected(false);
					rB_Anio_PLLST.setSelected(false);
				}else{
					dC_FI_PLLST.setEnabled(false);
					dC_FF_PLLST.setEnabled(false);
					btnFiltrarPLLST.setEnabled(false);
				}
			}
		});
		rB_Intervalo_PLLST.setBounds(5, 10, 150, 20);
		pnPiezasLLegadasSinTurno.add(rB_Intervalo_PLLST);
		
		rB_Hoy_PLLST = new JRadioButton("Hoy");
		rB_Hoy_PLLST.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Hoy_PLLST.isSelected()){
					rB_Intervalo_PLLST.setSelected(false);
					dC_FI_PLLST.setEnabled(false);
					dC_FF_PLLST.setEnabled(false);
					rB_USemana_PLLST.setSelected(false);
					rB_UMes_PLLST.setSelected(false);
					rBMesPasado_PLLST.setSelected(false);
					rB_Anio_PLLST.setSelected(false);
					filtraHoyPLLST();
				}
			}
		});
		rB_Hoy_PLLST.setBounds(5, 35, 150, 20);
		pnPiezasLLegadasSinTurno.add(rB_Hoy_PLLST);
		
		rB_USemana_PLLST = new JRadioButton("Ultima Semana");
		rB_USemana_PLLST.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_USemana_PLLST.isSelected()){
					rB_Intervalo_PLLST.setSelected(false);
					dC_FI_PLLST.setEnabled(false);
					dC_FF_PLLST.setEnabled(false);
					rB_Hoy_PLLST.setSelected(false);
					rB_UMes_PLLST.setSelected(false);
					rBMesPasado_PLLST.setSelected(false);
					rB_Anio_PLLST.setSelected(false);
					filtrarSemanaPLLST();
				}
			}
		});
		rB_USemana_PLLST.setBounds(5, 60, 150, 20);
		pnPiezasLLegadasSinTurno.add(rB_USemana_PLLST);
		
		rB_UMes_PLLST = new JRadioButton("Este Mes");
		rB_UMes_PLLST.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_UMes_PLLST.isSelected()){
					rB_Intervalo_PLLST.setSelected(false);
					dC_FI_PLLST.setEnabled(false);
					dC_FF_PLLST.setEnabled(false);
					rB_Hoy_PLLST.setSelected(false);
					rB_USemana_PLLST.setSelected(false);
					rBMesPasado_PLLST.setSelected(false);
					rB_Anio_PLLST.setSelected(false);
					filtrarUMes();
				}
			}
		});
		rB_UMes_PLLST.setBounds(5, 85, 150, 20);
		pnPiezasLLegadasSinTurno.add(rB_UMes_PLLST);
		
		rBMesPasado_PLLST = new JRadioButton("Mes Anterior");
		rBMesPasado_PLLST.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rBMesPasado_PLLST.isSelected()){
					rB_Intervalo_PLLST.setSelected(false);
					dC_FI_PLLST.setEnabled(false);
					dC_FF_PLLST.setEnabled(false);
					rB_Hoy_PLLST.setSelected(false);
					rB_USemana_PLLST.setSelected(false);
					rB_UMes_PLLST.setSelected(false);
					rB_Anio_PLLST.setSelected(false);
					filtrarMesPasadoPLLST();
				}
			}
		});
		rBMesPasado_PLLST.setBounds(5, 110, 150, 20);
		pnPiezasLLegadasSinTurno.add(rBMesPasado_PLLST);
		
		rB_Anio_PLLST = new JRadioButton("Ultimo Año");
		rB_Anio_PLLST.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Anio_PLLST.isSelected()){
					rB_Intervalo_PLLST.setSelected(false);
					dC_FI_PLLST.setEnabled(false);
					dC_FF_PLLST.setEnabled(false);
					rB_Hoy_PLLST.setSelected(false);
					rB_USemana_PLLST.setSelected(false);
					rB_UMes_PLLST.setSelected(false);
					rBMesPasado_PLLST.setSelected(false);
					filtrarAnioPLLST();
				}
			}
		});
		rB_Anio_PLLST.setBounds(5, 135, 150, 20);
		pnPiezasLLegadasSinTurno.add(rB_Anio_PLLST);
		
		btnFiltrarPLLST = new JButton("Filtrar");
		btnFiltrarPLLST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarPorIntervaloPLLST();
			}
		});
		btnFiltrarPLLST.setEnabled(false);
		btnFiltrarPLLST.setBounds(810, 10, 110, 20);
		pnPiezasLLegadasSinTurno.add(btnFiltrarPLLST);
		
		btn_clear_FILLST = new JButton("");
		btn_clear_FILLST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FI_PLLST.getDate()!=null)
					dC_FI_PLLST.setDate(null);
			}
		});
		btn_clear_FILLST.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FILLST.setBounds(450, 10, 25, 20);
		pnPiezasLLegadasSinTurno.add(btn_clear_FILLST);
		
		btn_clear_FFLLST = new JButton("");
		btn_clear_FFLLST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_PLLST.getDate()!=null)
					dC_FF_PLLST.setDate(null);
			}
		});
		btn_clear_FFLLST.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FFLLST.setBounds(775, 10, 25, 20);
		pnPiezasLLegadasSinTurno.add(btn_clear_FFLLST);
		
		btnExportarTablaLLST = new JButton("");
		btnExportarTablaLLST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaPLLST();
			}
		});
		btnExportarTablaLLST.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTablaLLST.setBounds(1230, 120, 32, 32);
		pnPiezasLLegadasSinTurno.add(btnExportarTablaLLST);
		
		
		//////////////////////////////////////////
		//		Orden Sin Solicitud De Pedido 	//
		//////////////////////////////////////////
		
		//Tabla Orden Sin Solicitud De Pedido //
		pnOrdenSinSDP = new JPanel();
		gestion.addTab("ORDEN SIN SOLICITUD DE PEDIDO", null, pnOrdenSinSDP, null);
		pnOrdenSinSDP.setLayout(null);
		modelo_tabla_orden_sin_solicitud_pedido = new DefaultTableModel(datosTabla_orden_sin_solicitud_pedido, nombreColumnas_orden_sin_solicitud_pedido);
		tabla_OSSDP = new JTable(modelo_tabla_orden_sin_solicitud_pedido) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_tabla_orden_sin_solicitud_pedido = new TableRowSorter<TableModel>(modelo_tabla_orden_sin_solicitud_pedido);
		for(int i = 0; i < tabla_OSSDP.getColumnCount(); i++) {
			tabla_OSSDP.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
			tabla_OSSDP.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
		}
		tabla_OSSDP.setRowSorter(ordenador_tabla_orden_sin_solicitud_pedido);
		tabla_OSSDP.getTableHeader().setReorderingAllowed(false);
		tabla_OSSDP.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_orden_sin_sdp = new JScrollPane(tabla_OSSDP);
		scrollPane_orden_sin_sdp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_orden_sin_sdp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_orden_sin_sdp.setBounds(5, 160, 1255, 470);
		pnOrdenSinSDP.add(scrollPane_orden_sin_sdp);
		//tabla_orden_sin_solicitud_pedido.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//Filtros Orden Sin Solicitud De Pedido //
		dC_FI_OSSDP = new JDateChooser();
		dC_FI_OSSDP.setEnabled(false);
		dC_FI_OSSDP.setBounds(290, 10, 150, 20);
		pnOrdenSinSDP.add(dC_FI_OSSDP);
		
		JLabel label_8 = new JLabel("Fecha Inicio");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setEnabled(false);
		label_8.setBounds(160, 10, 120, 20);
		pnOrdenSinSDP.add(label_8);
		
		JLabel label_9 = new JLabel("Fecha Fin");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setEnabled(false);
		label_9.setBounds(485, 10, 120, 20);
		pnOrdenSinSDP.add(label_9);
		
		dC_FF_OSSDP = new JDateChooser();
		dC_FF_OSSDP.setEnabled(false);
		dC_FF_OSSDP.setBounds(615, 10, 150, 20);
		pnOrdenSinSDP.add(dC_FF_OSSDP);
		
		rB_Intervalo_OSSDP = new JRadioButton("Intervalo de Fechas");
		rB_Intervalo_OSSDP.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Intervalo_OSSDP.isSelected()){
					dC_FI_OSSDP.setEnabled(true);
					dC_FF_OSSDP.setEnabled(true);
					btnFiltrarOSSDP.setEnabled(true);
					rB_Hoy_OSSDP.setSelected(false);
					rB_USemana_OSSDP.setSelected(false);
					rB_UMes_OSSDP.setSelected(false);
					rB_MesAnterior_OSSDP.setSelected(false);
					rB_UAnio_OSSDP.setSelected(false);
				}else{
					dC_FI_OSSDP.setEnabled(false);
					dC_FF_OSSDP.setEnabled(false);
					btnFiltrarOSSDP.setEnabled(false);
				}
			}
		});
		rB_Intervalo_OSSDP.setBounds(5, 10, 150, 20);
		pnOrdenSinSDP.add(rB_Intervalo_OSSDP);
		
		rB_Hoy_OSSDP = new JRadioButton("Hoy");
		rB_Hoy_OSSDP.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Hoy_OSSDP.isSelected()){
					rB_Intervalo_OSSDP.setSelected(false);
					dC_FI_OSSDP.setEnabled(false);
					dC_FF_OSSDP.setEnabled(false);
					rB_USemana_OSSDP.setSelected(false);
					rB_UMes_OSSDP.setSelected(false);
					rB_MesAnterior_OSSDP.setSelected(false);
					rB_UAnio_OSSDP.setSelected(false);
					filtarHoyOSSDP();
				}
			}
		});
		rB_Hoy_OSSDP.setBounds(5, 35, 150, 20);
		pnOrdenSinSDP.add(rB_Hoy_OSSDP);
		
		rB_USemana_OSSDP = new JRadioButton("Ultima Semana");
		rB_USemana_OSSDP.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_USemana_OSSDP.isSelected()){
					rB_Intervalo_OSSDP.setSelected(false);
					dC_FI_OSSDP.setEnabled(false);
					dC_FF_OSSDP.setEnabled(false);
					rB_Hoy_OSSDP.setSelected(false);
					rB_UMes_OSSDP.setSelected(false);
					rB_MesAnterior_OSSDP.setSelected(false);
					rB_UAnio_OSSDP.setSelected(false);
					filtrarUSemanaOSSDP();
				}
			}
		});
		rB_USemana_OSSDP.setBounds(5, 60, 150, 20);
		pnOrdenSinSDP.add(rB_USemana_OSSDP);
		
		rB_UMes_OSSDP = new JRadioButton("Este Mes");
		rB_UMes_OSSDP.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_UMes_OSSDP.isSelected()){
					rB_Intervalo_OSSDP.setSelected(false);
					dC_FI_OSSDP.setEnabled(false);
					dC_FF_OSSDP.setEnabled(false);
					rB_Hoy_OSSDP.setSelected(false);
					rB_USemana_OSSDP.setSelected(false);
					rB_MesAnterior_OSSDP.setSelected(false);
					rB_UAnio_OSSDP.setSelected(false);
					filtrarUMesOSSDP();
				}
			}
		});
		rB_UMes_OSSDP.setBounds(5, 85, 150, 20);
		pnOrdenSinSDP.add(rB_UMes_OSSDP);
		
		rB_MesAnterior_OSSDP = new JRadioButton("Mes Anterior");
		rB_MesAnterior_OSSDP.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_MesAnterior_OSSDP.isSelected()){
					rB_Intervalo_OSSDP.setSelected(false);
					dC_FI_OSSDP.setEnabled(false);
					dC_FF_OSSDP.setEnabled(false);
					rB_Hoy_OSSDP.setSelected(false);
					rB_USemana_OSSDP.setSelected(false);
					rB_UMes_OSSDP.setSelected(false);
					rB_UAnio_OSSDP.setSelected(false);
					filtrarMesAnteriorOSSDP();
				}
			}
		});
		rB_MesAnterior_OSSDP.setBounds(5, 110, 150, 20);
		pnOrdenSinSDP.add(rB_MesAnterior_OSSDP);
		
		rB_UAnio_OSSDP = new JRadioButton("Ultimo Año");
		rB_UAnio_OSSDP.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_UAnio_OSSDP.isSelected()){
					rB_Intervalo_OSSDP.setSelected(false);
					dC_FI_OSSDP.setEnabled(false);
					dC_FF_OSSDP.setEnabled(false);
					rB_Hoy_OSSDP.setSelected(false);
					rB_USemana_OSSDP.setSelected(false);
					rB_UMes_OSSDP.setSelected(false);
					rB_MesAnterior_OSSDP.setSelected(false);
					filtrarAnioOSSDP();
				}
			}
		});
		rB_UAnio_OSSDP.setBounds(5, 135, 150, 20);
		pnOrdenSinSDP.add(rB_UAnio_OSSDP);
		
		btnFiltrarOSSDP = new JButton("Filtrar");
		btnFiltrarOSSDP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarPorIntervaloOSSDP();
			}
		});
		btnFiltrarOSSDP.setEnabled(false);
		btnFiltrarOSSDP.setBounds(810, 10, 110, 20);
		pnOrdenSinSDP.add(btnFiltrarOSSDP);
		
		btn_clear_FISSP = new JButton("");
		btn_clear_FISSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FI_OSSDP.getDate()!=null)
					dC_FI_OSSDP.setDate(null);
			}
		});
		btn_clear_FISSP.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FISSP.setBounds(450, 10, 25, 20);
		pnOrdenSinSDP.add(btn_clear_FISSP);
		
		btn_clear_FFSSP = new JButton("");
		btn_clear_FFSSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_OSSDP.getDate()!=null)
					dC_FF_OSSDP.setDate(null);
			}
		});
		btn_clear_FFSSP.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FFSSP.setBounds(775, 10, 25, 20);
		pnOrdenSinSDP.add(btn_clear_FFSSP);
		
		btnExportarTablaSSP = new JButton("");
		btnExportarTablaSSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaSSP();
			}
		});
		btnExportarTablaSSP.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTablaSSP.setBounds(1230, 120, 32, 32);
		pnOrdenSinSDP.add(btnExportarTablaSSP);
		
		//////////////////////////////////////////////
		//	Solicitud De Pedido Sin Numero Pedido 	//
		//////////////////////////////////////////////
		
		//Tabla Solicitud De Pedido Sin Numero Pedido //		
		pnSDPsinNumPedido = new JPanel();
		pnSDPsinNumPedido.setLayout(null);
		gestion.addTab("SOLICITUD DE PEDIDO SIN NUMERO DE PEDIDO", null, pnSDPsinNumPedido, null);
		modelo_tabla_sdp_sin_numero_pedido = new DefaultTableModel(datosTabla_sdp_sin_numero_pedido, nombreColumnas_sdp_sin_numero_pedido);
		tabla_SDPSN = new JTable(modelo_tabla_sdp_sin_numero_pedido) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false,false, false, 
					false, false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_tabla_sdp_sin_numero_pedido = new TableRowSorter<TableModel>(modelo_tabla_sdp_sin_numero_pedido);
		for(int i = 0; i < tabla_SDPSN.getColumnCount(); i++) {
			tabla_SDPSN.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
			tabla_SDPSN.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
		}
		tabla_SDPSN.setRowSorter(ordenador_tabla_sdp_sin_numero_pedido);
		tabla_SDPSN.getTableHeader().setReorderingAllowed(false);
		tabla_SDPSN.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		scrollPane_sdp_sin_numero = new JScrollPane(tabla_SDPSN);
		scrollPane_sdp_sin_numero.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_sdp_sin_numero.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_sdp_sin_numero.setBounds(5, 160, 1255, 470);
		pnSDPsinNumPedido.add(scrollPane_sdp_sin_numero);					
		//tablaSdp_sin_numero_pedido.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//Filtros Solicitud De Pedido Sin Numero Pedido //
		dC_FI_PSNP = new JDateChooser();
		dC_FI_PSNP.setEnabled(false);
		dC_FI_PSNP.setBounds(290, 10, 150, 20);
		pnSDPsinNumPedido.add(dC_FI_PSNP);
		
		JLabel label_10 = new JLabel("Fecha Inicio");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setEnabled(false);
		label_10.setBounds(160, 10, 120, 20);
		pnSDPsinNumPedido.add(label_10);
		
		JLabel label_11 = new JLabel("Fecha Fin");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setEnabled(false);
		label_11.setBounds(485, 10, 120, 20);
		pnSDPsinNumPedido.add(label_11);
		
		dC_FF_PSNP = new JDateChooser();
		dC_FF_PSNP.setEnabled(false);
		dC_FF_PSNP.setBounds(615, 10, 150, 20);
		pnSDPsinNumPedido.add(dC_FF_PSNP);
		
		rB_Inetervalo_SDPSN = new JRadioButton("Intervalo de Fechas");
		rB_Inetervalo_SDPSN.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Inetervalo_SDPSN.isSelected()){
					dC_FI_PSNP.setEnabled(true);
					dC_FF_PSNP.setEnabled(true);
					btnFiltrarSDPSN.setEnabled(true);
					rB_Hoy_SDPSN.setSelected(false);
					rB_USemana_SDPSN.setSelected(false);
					rB_UMes_SDPSN.setSelected(false);
					rB_MesPasado_SDPSN.setSelected(false);
					rB_UAnio_SDPSN.setSelected(false);
				}else{
					dC_FI_PSNP.setEnabled(false);
					dC_FF_PSNP.setEnabled(false);
					btnFiltrarSDPSN.setEnabled(false);
				}
			}
		});
		rB_Inetervalo_SDPSN.setBounds(5, 10, 150, 20);
		pnSDPsinNumPedido.add(rB_Inetervalo_SDPSN);
		
		rB_Hoy_SDPSN = new JRadioButton("Hoy");
		rB_Hoy_SDPSN.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Hoy_SDPSN.isSelected()){
					rB_Inetervalo_SDPSN.setSelected(false);
					dC_FI_PSNP.setEnabled(false);
					dC_FF_PSNP.setEnabled(false);;
					rB_USemana_SDPSN.setSelected(false);
					rB_UMes_SDPSN.setSelected(false);
					rB_MesPasado_SDPSN.setSelected(false);
					rB_UAnio_SDPSN.setSelected(false);
					filtrarHoySDPSN();
				}
			}
		});
		rB_Hoy_SDPSN.setBounds(5, 35, 150, 20);
		pnSDPsinNumPedido.add(rB_Hoy_SDPSN);
		
		rB_USemana_SDPSN = new JRadioButton("Ultima Semana");
		rB_USemana_SDPSN.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_USemana_SDPSN.isSelected()){
					rB_Inetervalo_SDPSN.setSelected(false);
					dC_FI_PSNP.setEnabled(false);
					dC_FF_PSNP.setEnabled(false);;
					rB_Hoy_SDPSN.setSelected(false);
					rB_UMes_SDPSN.setSelected(false);
					rB_MesPasado_SDPSN.setSelected(false);
					rB_UAnio_SDPSN.setSelected(false);
					filtrarUSemanaSDPSN();
				}
			}
		});
		rB_USemana_SDPSN.setBounds(5, 60, 150, 20);
		pnSDPsinNumPedido.add(rB_USemana_SDPSN);
		
		rB_UMes_SDPSN = new JRadioButton("Este Mes");
		rB_UMes_SDPSN.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_UMes_SDPSN.isSelected()){
					rB_Inetervalo_SDPSN.setSelected(false);
					dC_FI_PSNP.setEnabled(false);
					dC_FF_PSNP.setEnabled(false);;
					rB_Hoy_SDPSN.setSelected(false);
					rB_USemana_SDPSN.setSelected(false);
					rB_MesPasado_SDPSN.setSelected(false);
					rB_UAnio_SDPSN.setSelected(false);			
					filtrarMesSDPSN();
				}
			}
		});
		rB_UMes_SDPSN.setBounds(5, 85, 150, 20);
		pnSDPsinNumPedido.add(rB_UMes_SDPSN);
		
		rB_MesPasado_SDPSN = new JRadioButton("Mes Anterior");
		rB_MesPasado_SDPSN.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_MesPasado_SDPSN.isSelected()){
					rB_Inetervalo_SDPSN.setSelected(false);
					dC_FI_PSNP.setEnabled(false);
					dC_FF_PSNP.setEnabled(false);;
					rB_Hoy_SDPSN.setSelected(false);
					rB_USemana_SDPSN.setSelected(false);
					rB_UMes_SDPSN.setSelected(false);
					rB_UAnio_SDPSN.setSelected(false);				
					filtarMesPasadoSDPSN();
				}
			}
		});
		rB_MesPasado_SDPSN.setBounds(5, 110, 150, 20);
		pnSDPsinNumPedido.add(rB_MesPasado_SDPSN);
		
		rB_UAnio_SDPSN = new JRadioButton("Ultimo A\u00F1o");
		rB_UAnio_SDPSN.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_UAnio_SDPSN.isSelected()){
					rB_Inetervalo_SDPSN.setSelected(false);
					dC_FI_PSNP.setEnabled(false);
					dC_FF_PSNP.setEnabled(false);;
					rB_Hoy_SDPSN.setSelected(false);
					rB_USemana_SDPSN.setSelected(false);
					rB_UMes_SDPSN.setSelected(false);
					rB_MesPasado_SDPSN.setSelected(false);			
					filtrarAnioSDPSN();
				}
			}
		});
		rB_UAnio_SDPSN.setBounds(5, 135, 150, 20);
		pnSDPsinNumPedido.add(rB_UAnio_SDPSN);
		
		btnFiltrarSDPSN = new JButton("Filtrar");
		btnFiltrarSDPSN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarPorIntervaloSDPSN();
			}
		});
		btnFiltrarSDPSN.setEnabled(false);
		btnFiltrarSDPSN.setBounds(810, 10, 110, 20);
		pnSDPsinNumPedido.add(btnFiltrarSDPSN);
		
		btn_clear_FISPSNP = new JButton("");
		btn_clear_FISPSNP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FI_PSNP.getDate()!=null)
					dC_FI_PSNP.setDate(null);
			}
		});
		btn_clear_FISPSNP.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FISPSNP.setBounds(450, 10, 25, 20);
		pnSDPsinNumPedido.add(btn_clear_FISPSNP);
		
		btn_clear_FFSPSNP = new JButton("");
		btn_clear_FFSPSNP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_PSNP.getDate()!=null)
					dC_FF_PSNP.setDate(null);
			}
		});
		btn_clear_FFSPSNP.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FFSPSNP.setBounds(775, 10, 25, 20);
		pnSDPsinNumPedido.add(btn_clear_FFSPSNP);
		
		btnExportarTablaSPSNP = new JButton("");
		btnExportarTablaSPSNP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaSPSNP();
			}
		});
		btnExportarTablaSPSNP.setIcon(new ImageIcon(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTablaSPSNP.setBounds(1230, 120, 32, 32);
		pnSDPsinNumPedido.add(btnExportarTablaSPSNP);	
	}

	//Filtros//
	//Solicitud De Pedido Sin Numero De Pedido
	protected void exportarTablaSPSNP() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_SDPSN, "Solicitud de Pedidos Sin Numero de Pedido");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@SuppressWarnings("static-access")
	protected void filtrarPorIntervaloSDPSN() {
		if(dC_FI_PSNP.getDate()!=null && dC_FF_PSNP.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			
			Calendar c = new GregorianCalendar();
			c.setTime(dC_FI_PSNP.getDate());
			c.add(c.DAY_OF_MONTH, -1);	

			datosTabla_sdp_sin_numero_pedido = new Vector<Vector<String>>();
						
			for (int i=0; i< pedidos_piezas.size();i++){
						
				if(pedidos_piezas.elementAt(i).getNumero_pedido()==null && pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().after(c.getTime()) &&  pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().before(dC_FF_PSNP.getDate())){

						Vector<String> row = new Vector<String> ();
			
						row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
						row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
						row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
						row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
						row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
						row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante

						if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
							java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
							row.add(format2.format(fsp));//Fecha Solicitud Pedido
						}else{
							row.add("");
						}			    
						
						if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
							java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
							row.add(format2.format(fsf));//Fecha Solicitud Fabrica
						}else{
							row.add("");
						}
			
						datosTabla_sdp_sin_numero_pedido.add(row);
					}
				}
			}
			modelo_tabla_sdp_sin_numero_pedido.setDataVector(datosTabla_sdp_sin_numero_pedido, nombreColumnas_sdp_sin_numero_pedido);
			modelo_tabla_sdp_sin_numero_pedido.fireTableStructureChanged();

			for(int i = 0; i < tabla_SDPSN.getColumnCount(); i++) {
				tabla_SDPSN.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
				tabla_SDPSN.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
			}	
		}else{
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}		
	}

	@SuppressWarnings("deprecation")
	protected void filtrarAnioSDPSN() {
		java.util.Date hoy = new java.util.Date();
		java.util.Date iniAnioPasado = new java.util.Date();
		iniAnioPasado.setYear(hoy.getYear()-1);
		iniAnioPasado.setMonth(1);
		iniAnioPasado.setDate(1);
		java.util.Date finAnioPasado = new java.util.Date();
		finAnioPasado.setYear(hoy.getYear()-1);
		finAnioPasado.setMonth(12);
		finAnioPasado.setDate(31);
		
		
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
		datosTabla_sdp_sin_numero_pedido = new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
					
			if(pedidos_piezas.elementAt(i).getNumero_pedido()==null && pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
				
				if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().after(iniAnioPasado) &&  pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().before(finAnioPasado)){
					
					Vector<String> row = new Vector<String> ();
					
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante

					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
		
					datosTabla_sdp_sin_numero_pedido.add(row);
				}
			}
		}
		modelo_tabla_sdp_sin_numero_pedido.setDataVector(datosTabla_sdp_sin_numero_pedido, nombreColumnas_sdp_sin_numero_pedido);
		modelo_tabla_sdp_sin_numero_pedido.fireTableStructureChanged();

		for(int i = 0; i < tabla_SDPSN.getColumnCount(); i++) {
			tabla_SDPSN.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
			tabla_SDPSN.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
		}
	}

	@SuppressWarnings("static-access")
	protected void filtarMesPasadoSDPSN() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		c.add(c.MONTH, -1);
		Calendar d = Calendar.getInstance();
		d.setFirstDayOfWeek(d.SUNDAY);
		d.add(d.MONTH, -1);

		c.set(c.DAY_OF_MONTH,1);
		java.util.Date iniMesPasado = c.getTime();
		
		d.set(d.DAY_OF_MONTH,d.getMaximum(d.DAY_OF_MONTH));
		
		java.util.Date finMesPasado = d.getTime();
		 
		datosTabla_sdp_sin_numero_pedido = new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
					
			if(pedidos_piezas.elementAt(i).getNumero_pedido()==null && pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){

				if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().after(iniMesPasado) &&  pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().before(finMesPasado)){
					
					
					Vector<String> row = new Vector<String> ();
					
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante

					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
		
					datosTabla_sdp_sin_numero_pedido.add(row);
				}
			}
		}
		modelo_tabla_sdp_sin_numero_pedido.setDataVector(datosTabla_sdp_sin_numero_pedido, nombreColumnas_sdp_sin_numero_pedido);
		modelo_tabla_sdp_sin_numero_pedido.fireTableStructureChanged();

		for(int i = 0; i < tabla_SDPSN.getColumnCount(); i++) {
			tabla_SDPSN.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
			tabla_SDPSN.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
		}
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	protected void filtrarMesSDPSN() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		//c.add(c.MONTH, -1);
		
		java.util.Date iniUltimoMes = c.getTime();
		iniUltimoMes.setDate(1);
		
		java.util.Date finUltimoMes = new java.util.Date();
		
		datosTabla_sdp_sin_numero_pedido = new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
					
			if(pedidos_piezas.elementAt(i).getNumero_pedido()==null && pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){

				if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().after(iniUltimoMes) &&  pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().before(finUltimoMes)){
					
					Vector<String> row = new Vector<String> ();
					
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante

					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
		
					datosTabla_sdp_sin_numero_pedido.add(row);
				}
			}
		}
		modelo_tabla_sdp_sin_numero_pedido.setDataVector(datosTabla_sdp_sin_numero_pedido, nombreColumnas_sdp_sin_numero_pedido);
		modelo_tabla_sdp_sin_numero_pedido.fireTableStructureChanged();

		for(int i = 0; i < tabla_SDPSN.getColumnCount(); i++) {
			tabla_SDPSN.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
			tabla_SDPSN.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
		}	
	}

	@SuppressWarnings("static-access")
	protected void filtrarUSemanaSDPSN() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		c.add(c.WEEK_OF_YEAR, -1);
		
		Calendar d = Calendar.getInstance();
		d.setFirstDayOfWeek(d.SUNDAY);
		d.add(d.WEEK_OF_YEAR, -1);

		c.set(c.DAY_OF_WEEK,c.MONDAY);
		java.util.Date iniSemanaPasado = c.getTime();
		
		d.set(d.DAY_OF_WEEK,d.FRIDAY);
		
		java.util.Date finSemanaPasado = d.getTime();

		datosTabla_sdp_sin_numero_pedido = new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
					
			if(pedidos_piezas.elementAt(i).getNumero_pedido()==null && pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){

				if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().after(iniSemanaPasado) &&  pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().before(finSemanaPasado)){

					Vector<String> row = new Vector<String> ();
					
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante

					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
		
					datosTabla_sdp_sin_numero_pedido.add(row);
				}
			}
		}
		modelo_tabla_sdp_sin_numero_pedido.setDataVector(datosTabla_sdp_sin_numero_pedido, nombreColumnas_sdp_sin_numero_pedido);
		modelo_tabla_sdp_sin_numero_pedido.fireTableStructureChanged();

		for(int i = 0; i < tabla_SDPSN.getColumnCount(); i++) {
			tabla_SDPSN.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
			tabla_SDPSN.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
		}
	}

	@SuppressWarnings("deprecation")
	protected void filtrarHoySDPSN() {
		java.sql.Date hoy = new java.sql.Date(new java.util.Date().getTime());
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 

		datosTabla_sdp_sin_numero_pedido = new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
					
			if(pedidos_piezas.elementAt(i).getNumero_pedido()==null && pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
				
				if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getDate()==hoy.getDate() &&
						pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getMonth()==hoy.getMonth() &&
								pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getYear()==hoy.getYear()){

					Vector<String> row = new Vector<String> ();
					
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante

					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
		
					datosTabla_sdp_sin_numero_pedido.add(row);
				}
			}
		}
		modelo_tabla_sdp_sin_numero_pedido.setDataVector(datosTabla_sdp_sin_numero_pedido, nombreColumnas_sdp_sin_numero_pedido);
		modelo_tabla_sdp_sin_numero_pedido.fireTableStructureChanged();

		for(int i = 0; i < tabla_SDPSN.getColumnCount(); i++) {
			tabla_SDPSN.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
			tabla_SDPSN.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
		}		
	}

	//Orden Sin Solicitud de Pedido
	protected void exportarTablaSSP() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_OSSDP, "Ordenes Sin Solicitud de Pedidos");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@SuppressWarnings("static-access")
	protected void filtrarPorIntervaloOSSDP() {
		if(dC_FI_OSSDP.getDate()!=null && dC_FF_OSSDP.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			
			Calendar c = new GregorianCalendar();
			c.setTime(dC_FI_OSSDP.getDate());
			c.add(c.DAY_OF_MONTH, -1);
			
			datosTabla_orden_sin_solicitud_pedido= new Vector<Vector<String>>();
			for (int i=0; i< reclamos.size();i++){
				if(reclamos.elementAt(i).getOrden()!=null && !conPedido(reclamos.elementAt(i)) && reclamos.elementAt(i).getFecha_reclamo()!=null){
					
					if(reclamos.elementAt(i).getFecha_reclamo().after(c.getTime()) &&  reclamos.elementAt(i).getFecha_reclamo().before(dC_FF_OSSDP.getDate())){

						Vector<String> row = new Vector<String> ();
	
						row.add(reclamos.elementAt(i).getOrden().getId().toString());//ID Orden
						row.add(reclamos.elementAt(i).getOrden().getNumero_orden());//Numero Orden
						
						if(reclamos.elementAt(i).getOrden().getFecha_apertura()!=null){
							java.sql.Date fao = new java.sql.Date(reclamos.elementAt(i).getOrden().getFecha_apertura().getTime());
							row.add(format2.format(fao));//Fecha Apertura Orden
						}else{
							row.add("");
						}
						row.add(reclamos.elementAt(i).getId().toString());//ID Reclamo
						if(reclamos.elementAt(i).getFecha_reclamo()!=null){
							java.sql.Date fr = new java.sql.Date(reclamos.elementAt(i).getFecha_reclamo().getTime());
							row.add(format2.format(fr));//Fecha Reclamo
						}else{
							row.add("");
						}
						row.add(reclamos.elementAt(i).getRegistrante().getNombre_registrante());//Registrante
						
						datosTabla_orden_sin_solicitud_pedido.add(row);
					}
				}
			}
			
			modelo_tabla_orden_sin_solicitud_pedido.setDataVector(datosTabla_orden_sin_solicitud_pedido, nombreColumnas_orden_sin_solicitud_pedido);
			modelo_tabla_orden_sin_solicitud_pedido.fireTableStructureChanged();

			for(int i = 0; i < tabla_OSSDP.getColumnCount(); i++) {
				tabla_OSSDP.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
				tabla_OSSDP.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
			}	
		}else{
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}		
	}
	
	@SuppressWarnings("deprecation")
	protected void filtrarAnioOSSDP() {
		java.util.Date hoy = new java.util.Date();
		java.util.Date iniAnioPasado = new java.util.Date();
		iniAnioPasado.setYear(hoy.getYear()-1);
		iniAnioPasado.setMonth(1);
		iniAnioPasado.setDate(1);
		java.util.Date finAnioPasado = new java.util.Date();
		finAnioPasado.setYear(hoy.getYear()-1);
		finAnioPasado.setMonth(12);
		finAnioPasado.setDate(31);
		
		
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
		datosTabla_orden_sin_solicitud_pedido= new Vector<Vector<String>>();
		for (int i=0; i< reclamos.size();i++){

			if(reclamos.elementAt(i).getOrden()!=null && !conPedido(reclamos.elementAt(i)) && reclamos.elementAt(i).getFecha_reclamo()!=null){
					
				if(reclamos.elementAt(i).getFecha_reclamo().after(iniAnioPasado) &&  reclamos.elementAt(i).getFecha_reclamo().before(finAnioPasado)){
					
					Vector<String> row = new Vector<String> ();
					
					row.add(reclamos.elementAt(i).getOrden().getId().toString());//ID Orden
					row.add(reclamos.elementAt(i).getOrden().getNumero_orden());//Numero Orden
					
					if(reclamos.elementAt(i).getOrden().getFecha_apertura()!=null){
						java.sql.Date fao = new java.sql.Date(reclamos.elementAt(i).getOrden().getFecha_apertura().getTime());
						row.add(format2.format(fao));//Fecha Apertura Orden
					}else{
						row.add("");
					}
					row.add(reclamos.elementAt(i).getId().toString());//ID Reclamo
					if(reclamos.elementAt(i).getFecha_reclamo()!=null){
						java.sql.Date fr = new java.sql.Date(reclamos.elementAt(i).getFecha_reclamo().getTime());
						row.add(format2.format(fr));//Fecha Reclamo
					}else{
						row.add("");
					}
					row.add(reclamos.elementAt(i).getRegistrante().getNombre_registrante());//Registrante
					
					datosTabla_orden_sin_solicitud_pedido.add(row);
				}
			}
		}
		
		modelo_tabla_orden_sin_solicitud_pedido.setDataVector(datosTabla_orden_sin_solicitud_pedido, nombreColumnas_orden_sin_solicitud_pedido);
		modelo_tabla_orden_sin_solicitud_pedido.fireTableStructureChanged();

		for(int i = 0; i < tabla_OSSDP.getColumnCount(); i++) {
			tabla_OSSDP.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
			tabla_OSSDP.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
		}
	}

	@SuppressWarnings("static-access")
	protected void filtrarMesAnteriorOSSDP() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		c.add(c.MONTH, -1);
		Calendar d = Calendar.getInstance();
		d.setFirstDayOfWeek(d.SUNDAY);
		d.add(d.MONTH, -1);

		c.set(c.DAY_OF_MONTH,1);
		java.util.Date iniMesPasado = c.getTime();
		
		d.set(d.DAY_OF_MONTH,d.getMaximum(d.DAY_OF_MONTH));
		
		java.util.Date finMesPasado = d.getTime();
		 
		datosTabla_orden_sin_solicitud_pedido= new Vector<Vector<String>>();
		for (int i=0; i< reclamos.size();i++){

			if(reclamos.elementAt(i).getOrden()!=null && !conPedido(reclamos.elementAt(i)) && reclamos.elementAt(i).getFecha_reclamo()!=null){

				if(reclamos.elementAt(i).getFecha_reclamo().after(iniMesPasado) &&  reclamos.elementAt(i).getFecha_reclamo().before(finMesPasado)){
					
					Vector<String> row = new Vector<String> ();
					
					row.add(reclamos.elementAt(i).getOrden().getId().toString());//ID Orden
					row.add(reclamos.elementAt(i).getOrden().getNumero_orden());//Numero Orden
					
					if(reclamos.elementAt(i).getOrden().getFecha_apertura()!=null){
						java.sql.Date fao = new java.sql.Date(reclamos.elementAt(i).getOrden().getFecha_apertura().getTime());
						row.add(format2.format(fao));//Fecha Apertura Orden
					}else{
						row.add("");
					}
					row.add(reclamos.elementAt(i).getId().toString());//ID Reclamo
					if(reclamos.elementAt(i).getFecha_reclamo()!=null){
						java.sql.Date fr = new java.sql.Date(reclamos.elementAt(i).getFecha_reclamo().getTime());
						row.add(format2.format(fr));//Fecha Reclamo
					}else{
						row.add("");
					}
					row.add(reclamos.elementAt(i).getRegistrante().getNombre_registrante());//Registrante
					
					datosTabla_orden_sin_solicitud_pedido.add(row);
				}
			}
		}
		
		modelo_tabla_orden_sin_solicitud_pedido.setDataVector(datosTabla_orden_sin_solicitud_pedido, nombreColumnas_orden_sin_solicitud_pedido);
		modelo_tabla_orden_sin_solicitud_pedido.fireTableStructureChanged();

		for(int i = 0; i < tabla_OSSDP.getColumnCount(); i++) {
			tabla_OSSDP.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
			tabla_OSSDP.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
		}
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	protected void filtrarUMesOSSDP() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		//c.add(c.MONTH, -1);
		
		java.util.Date iniUltimoMes = c.getTime();
		iniUltimoMes.setDate(1);
		
		java.util.Date finUltimoMes = new java.util.Date();
		
		datosTabla_orden_sin_solicitud_pedido= new Vector<Vector<String>>();
		for (int i=0; i< reclamos.size();i++){

			if(reclamos.elementAt(i).getOrden()!=null && !conPedido(reclamos.elementAt(i)) && reclamos.elementAt(i).getFecha_reclamo()!=null){
				if(reclamos.elementAt(i).getFecha_reclamo().after(iniUltimoMes) &&  reclamos.elementAt(i).getFecha_reclamo().before(finUltimoMes)){
					
					Vector<String> row = new Vector<String> ();
					
					row.add(reclamos.elementAt(i).getOrden().getId().toString());//ID Orden
					row.add(reclamos.elementAt(i).getOrden().getNumero_orden());//Numero Orden
					
					if(reclamos.elementAt(i).getOrden().getFecha_apertura()!=null){
						java.sql.Date fao = new java.sql.Date(reclamos.elementAt(i).getOrden().getFecha_apertura().getTime());
						row.add(format2.format(fao));//Fecha Apertura Orden
					}else{
						row.add("");
					}
					row.add(reclamos.elementAt(i).getId().toString());//ID Reclamo
					if(reclamos.elementAt(i).getFecha_reclamo()!=null){
						java.sql.Date fr = new java.sql.Date(reclamos.elementAt(i).getFecha_reclamo().getTime());
						row.add(format2.format(fr));//Fecha Reclamo
					}else{
						row.add("");
					}
					row.add(reclamos.elementAt(i).getRegistrante().getNombre_registrante());//Registrante
					
					datosTabla_orden_sin_solicitud_pedido.add(row);
				}
			}
		}
		
		modelo_tabla_orden_sin_solicitud_pedido.setDataVector(datosTabla_orden_sin_solicitud_pedido, nombreColumnas_orden_sin_solicitud_pedido);
		modelo_tabla_orden_sin_solicitud_pedido.fireTableStructureChanged();

		for(int i = 0; i < tabla_OSSDP.getColumnCount(); i++) {
			tabla_OSSDP.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
			tabla_OSSDP.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
		}
	}

	@SuppressWarnings("static-access")
	protected void filtrarUSemanaOSSDP() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		c.add(c.WEEK_OF_YEAR, -1);
		
		Calendar d = Calendar.getInstance();
		d.setFirstDayOfWeek(d.SUNDAY);
		d.add(d.WEEK_OF_YEAR, -1);

		c.set(c.DAY_OF_WEEK,c.MONDAY);
		java.util.Date iniSemanaPasado = c.getTime();
		
		d.set(d.DAY_OF_WEEK,d.FRIDAY);
		
		java.util.Date finSemanaPasado = d.getTime();

		datosTabla_orden_sin_solicitud_pedido= new Vector<Vector<String>>();
		for (int i=0; i< reclamos.size();i++){

			if(reclamos.elementAt(i).getOrden()!=null && !conPedido(reclamos.elementAt(i)) && reclamos.elementAt(i).getFecha_reclamo()!=null){

				if(reclamos.elementAt(i).getFecha_reclamo().after(iniSemanaPasado) &&  reclamos.elementAt(i).getFecha_reclamo().before(finSemanaPasado)){

					Vector<String> row = new Vector<String> ();
					
					row.add(reclamos.elementAt(i).getOrden().getId().toString());//ID Orden
					row.add(reclamos.elementAt(i).getOrden().getNumero_orden());//Numero Orden
					
					if(reclamos.elementAt(i).getOrden().getFecha_apertura()!=null){
						java.sql.Date fao = new java.sql.Date(reclamos.elementAt(i).getOrden().getFecha_apertura().getTime());
						row.add(format2.format(fao));//Fecha Apertura Orden
					}else{
						row.add("");
					}
					row.add(reclamos.elementAt(i).getId().toString());//ID Reclamo
					if(reclamos.elementAt(i).getFecha_reclamo()!=null){
						java.sql.Date fr = new java.sql.Date(reclamos.elementAt(i).getFecha_reclamo().getTime());
						row.add(format2.format(fr));//Fecha Reclamo
					}else{
						row.add("");
					}
					row.add(reclamos.elementAt(i).getRegistrante().getNombre_registrante());//Registrante
					
					datosTabla_orden_sin_solicitud_pedido.add(row);
				}
			}
		}
		
		modelo_tabla_orden_sin_solicitud_pedido.setDataVector(datosTabla_orden_sin_solicitud_pedido, nombreColumnas_orden_sin_solicitud_pedido);
		modelo_tabla_orden_sin_solicitud_pedido.fireTableStructureChanged();

		for(int i = 0; i < tabla_OSSDP.getColumnCount(); i++) {
			tabla_OSSDP.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
			tabla_OSSDP.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
		}	
	}

	@SuppressWarnings("deprecation")
	protected void filtarHoyOSSDP() {
		java.sql.Date hoy = new java.sql.Date(new java.util.Date().getTime());
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 

		datosTabla_orden_sin_solicitud_pedido= new Vector<Vector<String>>();
		for (int i=0; i< reclamos.size();i++){

			if(reclamos.elementAt(i).getOrden()!=null && !conPedido(reclamos.elementAt(i)) && reclamos.elementAt(i).getFecha_reclamo()!=null){
				if(reclamos.elementAt(i).getFecha_reclamo().getDate()==hoy.getDate() &&
						reclamos.elementAt(i).getFecha_reclamo().getMonth()==hoy.getMonth() &&
								reclamos.elementAt(i).getFecha_reclamo().getYear()==hoy.getYear()){
					Vector<String> row = new Vector<String> ();
					
					row.add(reclamos.elementAt(i).getOrden().getId().toString());//ID Orden
					row.add(reclamos.elementAt(i).getOrden().getNumero_orden());//Numero Orden
					
					if(reclamos.elementAt(i).getOrden().getFecha_apertura()!=null){
						java.sql.Date fao = new java.sql.Date(reclamos.elementAt(i).getOrden().getFecha_apertura().getTime());
						row.add(format2.format(fao));//Fecha Apertura Orden
					}else{
						row.add("");
					}
					row.add(reclamos.elementAt(i).getId().toString());//ID Reclamo
					if(reclamos.elementAt(i).getFecha_reclamo()!=null){
						java.sql.Date fr = new java.sql.Date(reclamos.elementAt(i).getFecha_reclamo().getTime());
						row.add(format2.format(fr));//Fecha Reclamo
					}else{
						row.add("");
					}
					row.add(reclamos.elementAt(i).getRegistrante().getNombre_registrante());//Registrante
					
					datosTabla_orden_sin_solicitud_pedido.add(row);
				}
			}
		}
		
		modelo_tabla_orden_sin_solicitud_pedido.setDataVector(datosTabla_orden_sin_solicitud_pedido, nombreColumnas_orden_sin_solicitud_pedido);
		modelo_tabla_orden_sin_solicitud_pedido.fireTableStructureChanged();

		for(int i = 0; i < tabla_OSSDP.getColumnCount(); i++) {
			tabla_OSSDP.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
			tabla_OSSDP.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
		}	
	}
	
	//Piezas Llegadas Sin Turno
	protected void exportarTablaPLLST() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_PLLST, "Piezas Llegadas Sin Turno");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@SuppressWarnings("static-access")
	protected void filtrarPorIntervaloPLLST() {
		if(dC_FI_PLLST.getDate()!=null && dC_FF_PLLST.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			datosTabla_piezas_llegadas_sin_turno= new Vector<Vector<String>>();
			
			for (int i=0; i< pedidos_piezas.size();i++){
				
				boolean resp = false;
				if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
					resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null && pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno()==null;
				}				
				if(resp){
					Calendar c = new GregorianCalendar();
					c.setTime(dC_FI_PLLST.getDate());
					c.add(c.DAY_OF_MONTH, -1);
					if(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().after(c.getTime()) &&  pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().before(dC_FF_PLLST.getDate())){
						Vector<String> row = new Vector<String> ();
						
						row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
						row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
						row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
						row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
						row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
						row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
						row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
						
						if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
							java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
							row.add(format2.format(fsp));//Fecha Solicitud Pedido
						}else{
							row.add("");
						}			    
						
						if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
							java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
							row.add(format2.format(fsf));//Fecha Solicitud Fabrica
						}else{
							row.add("");
						}
						if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
							java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
							row.add(format2.format(frf));//Fecha Recepcion Fabrica
						}else{
							row.add("");
						}

						datosTabla_piezas_llegadas_sin_turno.add(row);
					}
				}
			}
			modelo_tabla_piezas_llegadas_sin_turno.setDataVector(datosTabla_piezas_llegadas_sin_turno, nombreColumnas_piezas_llegadas_sin_turno);
			modelo_tabla_piezas_llegadas_sin_turno.fireTableStructureChanged();

			for(int i = 0; i < tabla_PLLST.getColumnCount(); i++) {
				tabla_PLLST.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
				tabla_PLLST.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
			}	
		}else{
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	@SuppressWarnings("deprecation")
	protected void filtrarAnioPLLST() {
		java.util.Date hoy = new java.util.Date();
		java.util.Date iniAnioPasado = new java.util.Date();
		iniAnioPasado.setYear(hoy.getYear()-1);
		iniAnioPasado.setMonth(1);
		iniAnioPasado.setDate(1);
		java.util.Date finAnioPasado = new java.util.Date();
		finAnioPasado.setYear(hoy.getYear()-1);
		finAnioPasado.setMonth(12);
		finAnioPasado.setDate(31);
		
		
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
		datosTabla_piezas_llegadas_sin_turno= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			
			boolean resp = false;
			if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null && pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno()==null;
			}				
			if(resp){
				if(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().after(iniAnioPasado) &&  pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().before(finAnioPasado)){
					Vector<String> row = new Vector<String> ();
					
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						row.add("");
					}

					datosTabla_piezas_llegadas_sin_turno.add(row);
				}
			}
		}
		modelo_tabla_piezas_llegadas_sin_turno.setDataVector(datosTabla_piezas_llegadas_sin_turno, nombreColumnas_piezas_llegadas_sin_turno);
		modelo_tabla_piezas_llegadas_sin_turno.fireTableStructureChanged();

		for(int i = 0; i < tabla_PLLST.getColumnCount(); i++) {
			tabla_PLLST.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
			tabla_PLLST.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
		}
	}

	@SuppressWarnings("static-access")
	protected void filtrarMesPasadoPLLST() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		c.add(c.MONTH, -1);
		Calendar d = Calendar.getInstance();
		d.setFirstDayOfWeek(d.SUNDAY);
		d.add(d.MONTH, -1);

		c.set(c.DAY_OF_MONTH,1);
		java.util.Date iniMesPasado = c.getTime();
		
		d.set(d.DAY_OF_MONTH,d.getMaximum(d.DAY_OF_MONTH));
		
		java.util.Date finMesPasado = d.getTime();
		 
		datosTabla_piezas_llegadas_sin_turno= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			
			boolean resp = false;
			if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null && pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno()==null;
			}				
			if(resp){
				if(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().after(iniMesPasado) &&  pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().before(finMesPasado)){
				Vector<String> row = new Vector<String> ();
					
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						row.add("");
					}

					datosTabla_piezas_llegadas_sin_turno.add(row);
				}
			}
		}
		modelo_tabla_piezas_llegadas_sin_turno.setDataVector(datosTabla_piezas_llegadas_sin_turno, nombreColumnas_piezas_llegadas_sin_turno);
		modelo_tabla_piezas_llegadas_sin_turno.fireTableStructureChanged();

		for(int i = 0; i < tabla_PLLST.getColumnCount(); i++) {
			tabla_PLLST.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
			tabla_PLLST.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
		}
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	protected void filtrarUMes() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		//c.add(c.MONTH, -1);
		
		java.util.Date iniUltimoMes = c.getTime();
		iniUltimoMes.setDate(1);
		
		java.util.Date finUltimoMes = new java.util.Date();
		
		datosTabla_piezas_llegadas_sin_turno= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			
			boolean resp = false;
			if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null && pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno()==null;
			}				
			if(resp){
				if(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().after(iniUltimoMes) &&  pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().before(finUltimoMes)){
					
					Vector<String> row = new Vector<String> ();
					
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						row.add("");
					}

					datosTabla_piezas_llegadas_sin_turno.add(row);
				}
			}
		}
		modelo_tabla_piezas_llegadas_sin_turno.setDataVector(datosTabla_piezas_llegadas_sin_turno, nombreColumnas_piezas_llegadas_sin_turno);
		modelo_tabla_piezas_llegadas_sin_turno.fireTableStructureChanged();

		for(int i = 0; i < tabla_PLLST.getColumnCount(); i++) {
			tabla_PLLST.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
			tabla_PLLST.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
		}	
	}

	@SuppressWarnings("static-access")
	protected void filtrarSemanaPLLST() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		c.add(c.WEEK_OF_YEAR, -1);
		
		Calendar d = Calendar.getInstance();
		d.setFirstDayOfWeek(d.SUNDAY);
		d.add(d.WEEK_OF_YEAR, -1);

		c.set(c.DAY_OF_WEEK,c.MONDAY);
		java.util.Date iniSemanaPasado = c.getTime();
		
		d.set(d.DAY_OF_WEEK,d.FRIDAY);
		
		java.util.Date finSemanaPasado = d.getTime();
		
		
		datosTabla_piezas_llegadas_sin_turno= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			
			boolean resp = false;
			if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null && pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno()==null;
			}				
			if(resp){
				if(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().after(iniSemanaPasado) &&  pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().before(finSemanaPasado)){

					Vector<String> row = new Vector<String> ();
				
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						row.add("");
					}

					datosTabla_piezas_llegadas_sin_turno.add(row);
				}
			}
		}
		modelo_tabla_piezas_llegadas_sin_turno.setDataVector(datosTabla_piezas_llegadas_sin_turno, nombreColumnas_piezas_llegadas_sin_turno);
		modelo_tabla_piezas_llegadas_sin_turno.fireTableStructureChanged();

		for(int i = 0; i < tabla_PLLST.getColumnCount(); i++) {
			tabla_PLLST.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
			tabla_PLLST.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
		}
	}

	@SuppressWarnings("deprecation")
	protected void filtraHoyPLLST() {
		java.sql.Date hoy = new java.sql.Date(new java.util.Date().getTime());
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
		datosTabla_piezas_llegadas_sin_turno= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			boolean resp = false;
			if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null && pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno()==null;
			}				
			if(resp){
				if(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getDate()==hoy.getDate() &&
						pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getMonth()==hoy.getMonth() &&
								pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getYear()==hoy.getYear()){
					Vector<String> row = new Vector<String> ();
					
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						row.add("");
					}
	
					datosTabla_piezas_llegadas_sin_turno.add(row);
				}
			}
		}
		modelo_tabla_piezas_llegadas_sin_turno.setDataVector(datosTabla_piezas_llegadas_sin_turno, nombreColumnas_piezas_llegadas_sin_turno);
		modelo_tabla_piezas_llegadas_sin_turno.fireTableStructureChanged();

		for(int i = 0; i < tabla_PLLST.getColumnCount(); i++) {
			tabla_PLLST.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
			tabla_PLLST.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
		}		
	}

	//Piezas Sin Llegar
	protected void exportarTablaSLL() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_SLL, "Piezas Sin Llegar");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	@SuppressWarnings("static-access")
	protected void filtrarMPasadoSLL() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		c.add(c.MONTH, -1);
		Calendar d = Calendar.getInstance();
		d.setFirstDayOfWeek(d.SUNDAY);
		d.add(d.MONTH, -1);

		c.set(c.DAY_OF_MONTH,1);
		java.util.Date iniMesPasado = c.getTime();
		
		d.set(d.DAY_OF_MONTH,d.getMaximum(d.DAY_OF_MONTH));
		
		java.util.Date finMesPasado = d.getTime();
		 
		datosTabla_piezas_sin_llegar= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			boolean resp = false;
			if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()==null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}else{
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()==null && pedidos_piezas.elementAt(i).getFecha_envio_agente()==null  && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}					
			if(resp){
				if(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().after(iniMesPasado) &&  pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().before(finMesPasado)){
					Vector<String> row = new Vector<String> ();
					
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}

					datosTabla_piezas_sin_llegar.add(row);
				}
			}
		}
		modelo_tabla_piezas_sin_llegar.setDataVector(datosTabla_piezas_sin_llegar, nombreColumnas_piezas_sin_llegar);
		modelo_tabla_piezas_sin_llegar.fireTableStructureChanged();

		for(int i = 0; i < tabla_SLL.getColumnCount(); i++) {
			tabla_SLL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
			tabla_SLL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
		}		
	}

	@SuppressWarnings("deprecation")
	protected void filtrarAnioSLL() {
		java.util.Date hoy = new java.util.Date();
		java.util.Date iniAnioPasado = new java.util.Date();
		iniAnioPasado.setYear(hoy.getYear()-1);
		iniAnioPasado.setMonth(1);
		iniAnioPasado.setDate(1);
		java.util.Date finAnioPasado = new java.util.Date();
		finAnioPasado.setYear(hoy.getYear()-1);
		finAnioPasado.setMonth(12);
		finAnioPasado.setDate(31);
		
		
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
		datosTabla_piezas_sin_llegar= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			boolean resp = false;
			if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()==null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}else{
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()==null && pedidos_piezas.elementAt(i).getFecha_envio_agente()==null  && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}					
			if(resp){
				if(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().after(iniAnioPasado) &&  pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().before(finAnioPasado)){
					Vector<String> row = new Vector<String> ();
					
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}

					datosTabla_piezas_sin_llegar.add(row);
				}
			}
		}
		modelo_tabla_piezas_sin_llegar.setDataVector(datosTabla_piezas_sin_llegar, nombreColumnas_piezas_sin_llegar);
		modelo_tabla_piezas_sin_llegar.fireTableStructureChanged();

		for(int i = 0; i < tabla_SLL.getColumnCount(); i++) {
			tabla_SLL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
			tabla_SLL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
		}
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	protected void filtrarMesSLL() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		//c.add(c.MONTH, -1);
		
		java.util.Date iniUltimoMes = c.getTime();
		iniUltimoMes.setDate(1);
		
		java.util.Date finUltimoMes = new java.util.Date();
		
		datosTabla_piezas_sin_llegar= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			boolean resp = false;
			if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()==null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}else{
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()==null && pedidos_piezas.elementAt(i).getFecha_envio_agente()==null  && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}					
			if(resp){
				if(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().after(iniUltimoMes) &&  pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().before(finUltimoMes)){
					
					Vector<String> row = new Vector<String> ();
					
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}

					datosTabla_piezas_sin_llegar.add(row);
				}
			}
		}
		modelo_tabla_piezas_sin_llegar.setDataVector(datosTabla_piezas_sin_llegar, nombreColumnas_piezas_sin_llegar);
		modelo_tabla_piezas_sin_llegar.fireTableStructureChanged();

		for(int i = 0; i < tabla_SLL.getColumnCount(); i++) {
			tabla_SLL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
			tabla_SLL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
		}		
	}

	@SuppressWarnings("static-access")
	protected void filtrarSemanaSLL() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		c.add(c.WEEK_OF_YEAR, -1);
		
		Calendar d = Calendar.getInstance();
		d.setFirstDayOfWeek(d.SUNDAY);
		d.add(d.WEEK_OF_YEAR, -1);

		c.set(c.DAY_OF_WEEK,c.MONDAY);
		java.util.Date iniSemanaPasado = c.getTime();
		
		d.set(d.DAY_OF_WEEK,d.FRIDAY);
		
		java.util.Date finSemanaPasado = d.getTime();
		
		
		datosTabla_piezas_sin_llegar= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			boolean resp = false;
			if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()==null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}else{
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()==null && pedidos_piezas.elementAt(i).getFecha_envio_agente()==null  && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}					
			if(resp){
				if(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().after(iniSemanaPasado) &&  pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().before(finSemanaPasado)){
	
					Vector<String> row = new Vector<String> ();
					
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}

					datosTabla_piezas_sin_llegar.add(row);
				}
			}
		}
		modelo_tabla_piezas_sin_llegar.setDataVector(datosTabla_piezas_sin_llegar, nombreColumnas_piezas_sin_llegar);
		modelo_tabla_piezas_sin_llegar.fireTableStructureChanged();

		for(int i = 0; i < tabla_SLL.getColumnCount(); i++) {
			tabla_SLL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
			tabla_SLL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
		}	
	}

	@SuppressWarnings("deprecation")
	protected void filtrarHoySLL() {
		java.sql.Date hoy = new java.sql.Date(new java.util.Date().getTime());
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
		datosTabla_piezas_sin_llegar= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			boolean resp = false;
			if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()==null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}else{
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()==null && pedidos_piezas.elementAt(i).getFecha_envio_agente()==null  && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}					
			if(resp){
				if(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getDate()==hoy.getDate() &&
						pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getMonth()==hoy.getMonth() &&
								pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getYear()==hoy.getYear()){
					Vector<String> row = new Vector<String> ();
	
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}

					datosTabla_piezas_sin_llegar.add(row);
				}
			}
		}
		modelo_tabla_piezas_sin_llegar.setDataVector(datosTabla_piezas_sin_llegar, nombreColumnas_piezas_sin_llegar);
		modelo_tabla_piezas_sin_llegar.fireTableStructureChanged();

		for(int i = 0; i < tabla_SLL.getColumnCount(); i++) {
			tabla_SLL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
			tabla_SLL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
		}
	}

	@SuppressWarnings("static-access")
	protected void filtrarIntervaloSLL() {
		if(dC_FI_SLL.getDate()!=null && dC_FF_SLL.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			datosTabla_piezas_sin_llegar= new Vector<Vector<String>>();
			
			for (int i=0; i< pedidos_piezas.size();i++){
				boolean resp = false;
				if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
					resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()==null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
				}else{
					resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()==null && pedidos_piezas.elementAt(i).getFecha_envio_agente()==null  && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
				}					
				if(resp){
					Calendar c = new GregorianCalendar();
					c.setTime(dC_FI_SLL.getDate());
					c.add(c.DAY_OF_MONTH, -1);
					if(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().after(c.getTime()) &&  pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().before(dC_FF_SLL.getDate())){
						Vector<String> row = new Vector<String> ();

						row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
						row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
						row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
						row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
						row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
						row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
						row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
						
						if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
							java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
							row.add(format2.format(fsp));//Fecha Solicitud Pedido
						}else{
							row.add("");
						}			    
						
						if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
							java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
							row.add(format2.format(fsf));//Fecha Solicitud Fabrica
						}else{
							row.add("");
						}

						datosTabla_piezas_sin_llegar.add(row);
					}
				}
			}
			modelo_tabla_piezas_sin_llegar.setDataVector(datosTabla_piezas_sin_llegar, nombreColumnas_piezas_sin_llegar);
			modelo_tabla_piezas_sin_llegar.fireTableStructureChanged();

			for(int i = 0; i < tabla_SLL.getColumnCount(); i++) {
				tabla_SLL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
				tabla_SLL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
			}	
		}else{
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}		
	}

	//Piezas Devueltas
	@SuppressWarnings("deprecation")
	protected void filtrarUAnioPDev() {
		java.util.Date hoy = new java.util.Date();
		java.util.Date iniAnioPasado = new java.util.Date();
		iniAnioPasado.setYear(hoy.getYear()-1);
		iniAnioPasado.setMonth(1);
		iniAnioPasado.setDate(1);
		java.util.Date finAnioPasado = new java.util.Date();
		finAnioPasado.setYear(hoy.getYear()-1);
		finAnioPasado.setMonth(12);
		finAnioPasado.setDate(31);
		
		
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
		datosTabla_piezas_devueltas= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			if(pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){		
				if(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().after(iniAnioPasado) &&  pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().before(finAnioPasado)){
					Vector<String> row = new Vector<String> ();
	
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_envio_agente()!=null){
						java.sql.Date fea = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_envio_agente().getTime());
						row.add(format2.format(fea));//Fecha Envio Agente
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_agente()!=null){
						java.sql.Date fra = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_agente().getTime());
						row.add(format2.format(fra));//Fecha Recepcion Agente
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){
						java.sql.Date fef = new java.sql.Date(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().getTime());
						row.add(format2.format(fef));//Fecha Envio Fabrica
					}else{
						row.add("");
					}

					datosTabla_piezas_devueltas.add(row);
				}
			}
		}
		modelo_tabla_piezas_devueltas.setDataVector(datosTabla_piezas_devueltas, nombreColumnas_piezas_devueltas);
		modelo_tabla_piezas_devueltas.fireTableStructureChanged();
		
		for(int i = 0; i < tabla_PD.getColumnCount(); i++) {
			tabla_PD.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_devueltas.elementAt(i));
			tabla_PD.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_devueltas.elementAt(i));
		}
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	protected void filtrarUMesPDev() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		//c.add(c.MONTH, -1);
		
		java.util.Date iniUltimoMes = c.getTime();
		iniUltimoMes.setDate(1);
		
		java.util.Date finUltimoMes = new java.util.Date();
		
		datosTabla_piezas_devueltas= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			if(pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){		
				if(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().after(iniUltimoMes) &&  pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().before(finUltimoMes)){
					Vector<String> row = new Vector<String> ();
	
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_envio_agente()!=null){
						java.sql.Date fea = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_envio_agente().getTime());
						row.add(format2.format(fea));//Fecha Envio Agente
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_agente()!=null){
						java.sql.Date fra = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_agente().getTime());
						row.add(format2.format(fra));//Fecha Recepcion Agente
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){
						java.sql.Date fef = new java.sql.Date(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().getTime());
						row.add(format2.format(fef));//Fecha Envio Fabrica
					}else{
						row.add("");
					}

					datosTabla_piezas_devueltas.add(row);
				}
			}
		}
		modelo_tabla_piezas_devueltas.setDataVector(datosTabla_piezas_devueltas, nombreColumnas_piezas_devueltas);
		modelo_tabla_piezas_devueltas.fireTableStructureChanged();
		
		for(int i = 0; i < tabla_PD.getColumnCount(); i++) {
			tabla_PD.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_devueltas.elementAt(i));
			tabla_PD.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_devueltas.elementAt(i));
		}	
	}

	@SuppressWarnings("static-access")
	protected void filtrarManteriorPDev() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		c.add(c.MONTH, -1);
		Calendar d = Calendar.getInstance();
		d.setFirstDayOfWeek(d.SUNDAY);
		d.add(d.MONTH, -1);

		c.set(c.DAY_OF_MONTH,1);
		java.util.Date iniMesPasado = c.getTime();
		
		d.set(d.DAY_OF_MONTH,d.getMaximum(d.DAY_OF_MONTH));
		
		java.util.Date finMesPasado = d.getTime();
		 
		datosTabla_piezas_devueltas= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			if(pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){			
				if(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().after(iniMesPasado) &&  pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().before(finMesPasado)){
					Vector<String> row = new Vector<String> ();
	
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_envio_agente()!=null){
						java.sql.Date fea = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_envio_agente().getTime());
						row.add(format2.format(fea));//Fecha Envio Agente
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_agente()!=null){
						java.sql.Date fra = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_agente().getTime());
						row.add(format2.format(fra));//Fecha Recepcion Agente
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){
						java.sql.Date fef = new java.sql.Date(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().getTime());
						row.add(format2.format(fef));//Fecha Envio Fabrica
					}else{
						row.add("");
					}

					datosTabla_piezas_devueltas.add(row);
				}
			}
		}
		modelo_tabla_piezas_devueltas.setDataVector(datosTabla_piezas_devueltas, nombreColumnas_piezas_devueltas);
		modelo_tabla_piezas_devueltas.fireTableStructureChanged();
		
		for(int i = 0; i < tabla_PD.getColumnCount(); i++) {
			tabla_PD.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_devueltas.elementAt(i));
			tabla_PD.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_devueltas.elementAt(i));
		}
	}

	@SuppressWarnings("static-access")
	protected void filtrarUSemanaPDev() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		c.add(c.WEEK_OF_YEAR, -1);
		
		Calendar d = Calendar.getInstance();
		d.setFirstDayOfWeek(d.SUNDAY);
		d.add(d.WEEK_OF_YEAR, -1);

		c.set(c.DAY_OF_WEEK,c.MONDAY);
		java.util.Date iniSemanaPasado = c.getTime();
		
		d.set(d.DAY_OF_WEEK,d.FRIDAY);
		
		java.util.Date finSemanaPasado = d.getTime();
		
		
		datosTabla_piezas_devueltas= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			if(pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){
				if(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().after(iniSemanaPasado) &&  pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().before(finSemanaPasado)){
					Vector<String> row = new Vector<String> ();
	
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_envio_agente()!=null){
						java.sql.Date fea = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_envio_agente().getTime());
						row.add(format2.format(fea));//Fecha Envio Agente
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_agente()!=null){
						java.sql.Date fra = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_agente().getTime());
						row.add(format2.format(fra));//Fecha Recepcion Agente
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){
						java.sql.Date fef = new java.sql.Date(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().getTime());
						row.add(format2.format(fef));//Fecha Envio Fabrica
					}else{
						row.add("");
					}

					datosTabla_piezas_devueltas.add(row);
				}
			}
		}
		modelo_tabla_piezas_devueltas.setDataVector(datosTabla_piezas_devueltas, nombreColumnas_piezas_devueltas);
		modelo_tabla_piezas_devueltas.fireTableStructureChanged();
		
		for(int i = 0; i < tabla_PD.getColumnCount(); i++) {
			tabla_PD.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_devueltas.elementAt(i));
			tabla_PD.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_devueltas.elementAt(i));
		}
	}

	@SuppressWarnings("deprecation")
	protected void filtrarHoyPDev() {
		java.sql.Date hoy = new java.sql.Date(new java.util.Date().getTime());
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
		datosTabla_piezas_devueltas= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){			
			if(pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){
				if(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().getDate()==hoy.getDate() &&
						pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().getMonth()==hoy.getMonth() &&
							pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().getYear()==hoy.getYear()){
					Vector<String> row = new Vector<String> ();
	
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_envio_agente()!=null){
						java.sql.Date fea = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_envio_agente().getTime());
						row.add(format2.format(fea));//Fecha Envio Agente
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_agente()!=null){
						java.sql.Date fra = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_agente().getTime());
						row.add(format2.format(fra));//Fecha Recepcion Agente
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){
						java.sql.Date fef = new java.sql.Date(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().getTime());
						row.add(format2.format(fef));//Fecha Envio Fabrica
					}else{
						row.add("");
					}

					datosTabla_piezas_devueltas.add(row);
				}
			}
		}
		modelo_tabla_piezas_devueltas.setDataVector(datosTabla_piezas_devueltas, nombreColumnas_piezas_devueltas);
		modelo_tabla_piezas_devueltas.fireTableStructureChanged();
		
		for(int i = 0; i < tabla_PD.getColumnCount(); i++) {
			tabla_PD.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_devueltas.elementAt(i));
			tabla_PD.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_devueltas.elementAt(i));
		}
	}

	@SuppressWarnings("static-access")
	protected void filtrarInervaloPDev() {
		if(dC_FI_PD.getDate()!=null && dC_FF_PD.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			datosTabla_piezas_devueltas= new Vector<Vector<String>>();
			
			for (int i=0; i< pedidos_piezas.size();i++){			
				if(pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){
					Calendar c = new GregorianCalendar();
					c.setTime(dC_FI_PD.getDate());
					c.add(c.DAY_OF_MONTH, -1);
					if(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().after(c.getTime()) &&  pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().before(dC_FF_PD.getDate())){
						Vector<String> row = new Vector<String> ();
		
						row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
						row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
						row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
						row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
						row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
						row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
						row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
						
						if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
							java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
							row.add(format2.format(fsp));//Fecha Solicitud Pedido
						}else{
							row.add("");
						}			    
						
						if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
							java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
							row.add(format2.format(fsf));//Fecha Solicitud Fabrica
						}else{
							row.add("");
						}
						if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
							java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
							row.add(format2.format(frf));//Fecha Recepcion Fabrica
						}else{
							row.add("");
						}
						if (pedidos_piezas.elementAt(i).getFecha_envio_agente()!=null){
							java.sql.Date fea = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_envio_agente().getTime());
							row.add(format2.format(fea));//Fecha Envio Agente
						}else{
							row.add("");
						}
						if (pedidos_piezas.elementAt(i).getFecha_recepcion_agente()!=null){
							java.sql.Date fra = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_agente().getTime());
							row.add(format2.format(fra));//Fecha Recepcion Agente
						}else{
							row.add("");
						}
						if (pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){
							java.sql.Date fef = new java.sql.Date(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().getTime());
							row.add(format2.format(fef));//Fecha Envio Fabrica
						}else{
							row.add("");
						}

						datosTabla_piezas_devueltas.add(row);
					}
				}
			}
			modelo_tabla_piezas_devueltas.setDataVector(datosTabla_piezas_devueltas, nombreColumnas_piezas_devueltas);
			modelo_tabla_piezas_devueltas.fireTableStructureChanged();
			
			for(int i = 0; i < tabla_PD.getColumnCount(); i++) {
				tabla_PD.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_devueltas.elementAt(i));
				tabla_PD.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_devueltas.elementAt(i));
			}	
		}else{
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void exportarTablaPD() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_PD, "Piezas Devueltas");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	//Piezas Llegadas
	protected void exportarTablaPLL() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_PLL, "Piezas Llegadas");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	@SuppressWarnings("deprecation")
	protected void filtrarUAnioPLL() {
		java.util.Date hoy = new java.util.Date();
		java.util.Date iniAnioPasado = new java.util.Date();
		iniAnioPasado.setYear(hoy.getYear()-1);
		iniAnioPasado.setMonth(1);
		iniAnioPasado.setDate(1);
		java.util.Date finAnioPasado = new java.util.Date();
		finAnioPasado.setYear(hoy.getYear()-1);
		finAnioPasado.setMonth(12);
		finAnioPasado.setDate(31);
		
		
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
		datosTabla_piezas_llegadas= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			boolean resp = false;
			if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}else{
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getFecha_envio_agente()==null  && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}					
			if(resp){
				
				if(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().after(iniAnioPasado) &&  pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().before(finAnioPasado)){
					Vector<String> row = new Vector<String> ();
	
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						row.add("");
					}
	
					datosTabla_piezas_llegadas.add(row);
				}
			}
		}
		modelo_tabla_piezas_llegadas.setDataVector(datosTabla_piezas_llegadas, nombreColumnas_piezas_llegadas);
		modelo_tabla_piezas_llegadas.fireTableStructureChanged();
		
		for(int i = 0; i < tabla_PLL.getColumnCount(); i++) {
			tabla_PLL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas.elementAt(i));
			tabla_PLL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas.elementAt(i));
		}		
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	protected void filtrarUMesPLL() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		//c.add(c.MONTH, -1);
		
		java.util.Date iniUltimoMes = c.getTime();
		iniUltimoMes.setDate(1);
		
		java.util.Date finUltimoMes = new java.util.Date();
		
		datosTabla_piezas_llegadas= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			boolean resp = false;
			if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}else{
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getFecha_envio_agente()==null  && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}					
			if(resp){
				
				if(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().after(iniUltimoMes) &&  pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().before(finUltimoMes)){
					Vector<String> row = new Vector<String> ();
	
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						row.add("");
					}
	
					datosTabla_piezas_llegadas.add(row);
				}
			}
		}
		modelo_tabla_piezas_llegadas.setDataVector(datosTabla_piezas_llegadas, nombreColumnas_piezas_llegadas);
		modelo_tabla_piezas_llegadas.fireTableStructureChanged();
		
		for(int i = 0; i < tabla_PLL.getColumnCount(); i++) {
			tabla_PLL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas.elementAt(i));
			tabla_PLL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas.elementAt(i));
		}
	}

	@SuppressWarnings("static-access")
	protected void filtrarUSemanaPLL() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		c.add(c.WEEK_OF_YEAR, -1);
		
		Calendar d = Calendar.getInstance();
		d.setFirstDayOfWeek(d.SUNDAY);
		d.add(d.WEEK_OF_YEAR, -1);

		c.set(c.DAY_OF_WEEK,c.MONDAY);
		java.util.Date iniSemanaPasado = c.getTime();
		
		d.set(d.DAY_OF_WEEK,d.FRIDAY);
		
		java.util.Date finSemanaPasado = d.getTime();
		
		
		datosTabla_piezas_llegadas= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			boolean resp = false;
			if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}else{
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getFecha_envio_agente()==null  && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}					
			if(resp){
				
				if(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().after(iniSemanaPasado) &&  pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().before(finSemanaPasado)){
					Vector<String> row = new Vector<String> ();
	
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						row.add("");
					}
	
					datosTabla_piezas_llegadas.add(row);
				}
			}
		}
		modelo_tabla_piezas_llegadas.setDataVector(datosTabla_piezas_llegadas, nombreColumnas_piezas_llegadas);
		modelo_tabla_piezas_llegadas.fireTableStructureChanged();
		
		for(int i = 0; i < tabla_PLL.getColumnCount(); i++) {
			tabla_PLL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas.elementAt(i));
			tabla_PLL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas.elementAt(i));
		}	
		
	}

	@SuppressWarnings("deprecation")
	protected void filtrarHoyPLL() {
		java.sql.Date hoy = new java.sql.Date(new java.util.Date().getTime());
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
		datosTabla_piezas_llegadas= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			boolean resp = false;
			if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}else{
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getFecha_envio_agente()==null  && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}					
			if(resp){
				if(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getDate()==hoy.getDate() &&
						pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getMonth()==hoy.getMonth() &&
							pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getYear()==hoy.getYear()){
					Vector<String> row = new Vector<String> ();
	
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						row.add("");
					}
	
					datosTabla_piezas_llegadas.add(row);
				}
			}
		}
		modelo_tabla_piezas_llegadas.setDataVector(datosTabla_piezas_llegadas, nombreColumnas_piezas_llegadas);
		modelo_tabla_piezas_llegadas.fireTableStructureChanged();
		
		for(int i = 0; i < tabla_PLL.getColumnCount(); i++) {
			tabla_PLL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas.elementAt(i));
			tabla_PLL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas.elementAt(i));
		}
	}

	@SuppressWarnings("static-access")
	protected void filtrarMesAnteriorPLL() {
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(c.SUNDAY);
		c.add(c.MONTH, -1);
		Calendar d = Calendar.getInstance();
		d.setFirstDayOfWeek(d.SUNDAY);
		d.add(d.MONTH, -1);

		c.set(c.DAY_OF_MONTH,1);
		java.util.Date iniMesPasado = c.getTime();
		
		d.set(d.DAY_OF_MONTH,d.getMaximum(d.DAY_OF_MONTH));
		
		java.util.Date finMesPasado = d.getTime();
		 
		datosTabla_piezas_llegadas= new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			boolean resp = false;
			if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}else{
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getFecha_envio_agente()==null  && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}					
			if(resp){
				
				if(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().after(iniMesPasado) &&  pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().before(finMesPasado)){
					Vector<String> row = new Vector<String> ();
	
					row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					
					if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						row.add("");
					}
	
					datosTabla_piezas_llegadas.add(row);
				}
			}
		}
		modelo_tabla_piezas_llegadas.setDataVector(datosTabla_piezas_llegadas, nombreColumnas_piezas_llegadas);
		modelo_tabla_piezas_llegadas.fireTableStructureChanged();
		
		for(int i = 0; i < tabla_PLL.getColumnCount(); i++) {
			tabla_PLL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas.elementAt(i));
			tabla_PLL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas.elementAt(i));
		}		
	}
	
	@SuppressWarnings("static-access")
	private void filtrarIntervaloPLL() {
		if(dC_FInicioPLL.getDate()!=null && dC_FFinPLL.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			datosTabla_piezas_llegadas= new Vector<Vector<String>>();
			
			for (int i=0; i< pedidos_piezas.size();i++){
				boolean resp = false;
				if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
					resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
				}else{
					resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getFecha_envio_agente()==null  && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
				}					
				if(resp){
					Calendar c = new GregorianCalendar();
					c.setTime(dC_FInicioPLL.getDate());
					c.add(c.DAY_OF_MONTH, -1);
					if(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().after(c.getTime()) &&  pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().before(dC_FFinPLL.getDate())){
						Vector<String> row = new Vector<String> ();
		
						row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
						row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
						row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
						row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
						row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
						row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
						row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
						
						if(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido()!=null){
							java.sql.Date fsp = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getFecha_solicitud_pedido().getTime());
							row.add(format2.format(fsp));//Fecha Solicitud Pedido
						}else{
							row.add("");
						}			    
						
						if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
							java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
							row.add(format2.format(fsf));//Fecha Solicitud Fabrica
						}else{
							row.add("");
						}
						if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
							java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
							row.add(format2.format(frf));//Fecha Recepcion Fabrica
						}else{
							row.add("");
						}
						datosTabla_piezas_llegadas.add(row);
					}
				}
			}
			modelo_tabla_piezas_llegadas.setDataVector(datosTabla_piezas_llegadas, nombreColumnas_piezas_llegadas);
			modelo_tabla_piezas_llegadas.fireTableStructureChanged();
			
			for(int i = 0; i < tabla_PLL.getColumnCount(); i++) {
				tabla_PLL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas.elementAt(i));
				tabla_PLL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas.elementAt(i));
			}	
		}else{
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	//Fin filtros
	
	//gettes and setters
	public MediadorReportes getMediador() {
		return mediador;
	}

	public void setMediador(MediadorReportes mediador) {
		this.mediador = mediador;
	}

}
