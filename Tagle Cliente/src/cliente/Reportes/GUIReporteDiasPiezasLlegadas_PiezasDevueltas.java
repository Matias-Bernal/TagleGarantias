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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cliente.excellexport.ExportarExcel;

import com.toedter.calendar.JDateChooser;
import common.DTOs.Pedido_PiezaDTO;

public class GUIReporteDiasPiezasLlegadas_PiezasDevueltas extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private JPanel contentPane;
	private JPanel pnPiezasLlegadas_PiezasDevueltas;
	private JScrollPane scrollPane_tabla_Pllegadas_PDevuletas_LLegadas;
	private JScrollPane scrollPane_tabla_Pllegadas_PDevuletas_Devueltas;
	private JLabel lblNum_PDevueltas;
	private JLabel lblNumPLlegadas;
	private JTable tabla_PLLPDL;
	private JTable tabla_PLLPDD;
	private JLabel lblLlegadaDevueltas;
	private DefaultTableModel modelo_tabla_Pllegadas_PDevuletas_Devueltas;
	private Vector<Vector<String>> datos_tabla_Pllegadas_PDevuletas_Devueltas;
	private Vector<String> nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas;
	private Vector<Integer> anchos_tabla_Pllegadas_PDevuletas_Devueltas;
	private DefaultTableModel modelo_tabla_Pllegadas_PDevuletas_LLegadas;
	private Vector<Vector<String>> datos_tabla_Pllegadas_PDevuletas_LLegadas;
	private Vector<String> nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas;
	private Vector<Integer> anchos_tabla_Pllegadas_PDevuletas_LLegadas;
	private float  numero_PiezasLlegadas;
	private float numero_PiezasDevueltas;
	private JLabel lblPiezasLLegadas_PiezasDevueltas;
	private JRadioButton rB_Anio_PLLPD;
	private JRadioButton rB_MAnterior_PLLPD;
	private JRadioButton rB_Mes_PLLPD;
	private JRadioButton rB_USemana_PLLPD;
	private JRadioButton rB_Hoy_PLLPD;
	private JRadioButton rB_Intervalo_PLLPD;
	private JDateChooser dC_FI_PLLPD;
	private JButton btn_clear_FIPLLPD;
	private JDateChooser dC_FF_PLLPD;
	private JButton btn_clear_FFPLLPD;
	private JButton btn_filtrar_PLLPD;
	private JButton btnExportarTabla_PLLPD;

	//tabla 
	public GUIReporteDiasPiezasLlegadas_PiezasDevueltas(MediadorReportes mediadorReporte) {
		setMediador(mediadorReporte);
		cargarDatos();
		initialize();
	}

	private void cargarDatos() {
		Vector<Pedido_PiezaDTO> pedidos_piezas = mediador.obtenerPedido_Piezas();
		int chico = 100;
		int mediano = 150;
		int grande = 230;
		//TABLA PIEZAS LLEGADAS PIEZAS DEVUELTAS//
		modelo_tabla_Pllegadas_PDevuletas_LLegadas = new DefaultTableModel();
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas = new Vector<String> ();
		anchos_tabla_Pllegadas_PDevuletas_LLegadas = new Vector<Integer>();
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("ID Pedido");//0
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(75);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Numero Pedido");//1
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(chico);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Numero Pieza");//2
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(chico);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Descripcion");//3
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Numero Orden");//4
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(chico);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("VIN");//5
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(130);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Registrante");//6
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(grande);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Fecha Reclamo");//7
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Fecha Turno");//8
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Fecha Solicitud a Fabrica");//9
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Fecha Recepcion de Fabrica");//10
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Fecha Devolucion a Fabrica");//11
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(mediano);
		datos_tabla_Pllegadas_PDevuletas_LLegadas = new Vector<Vector<String>>();
		modelo_tabla_Pllegadas_PDevuletas_Devueltas = new DefaultTableModel();
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas = new Vector<String> ();
		anchos_tabla_Pllegadas_PDevuletas_Devueltas = new Vector<Integer>();
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("ID Pedido");//0
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(75);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Numero Pedido");//1
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(chico);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Numero Pieza");//2
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(chico);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Descripcion");//3
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Numero Orden");//4
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(chico);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("VIN");//5
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(130);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Registrante");//6
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(grande);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Fecha Reclamo");//7
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Fecha Turno");//8
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Fecha Solicitud a Fabrica");//9
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Fecha Recepcion de Fabrica");//10
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Fecha Devolucion a Fabrica");//11
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(mediano);
		datos_tabla_Pllegadas_PDevuletas_Devueltas = new Vector<Vector<String>>();
		numero_PiezasLlegadas = 0;
		numero_PiezasDevueltas = 0;
		
		for (int i=0; i< pedidos_piezas.size();i++){
			Vector<String> rowPLlegadas = new Vector<String> ();
			if(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
				rowPLlegadas.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
				rowPLlegadas.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
				rowPLlegadas.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
				rowPLlegadas.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
				rowPLlegadas.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
				rowPLlegadas.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
				rowPLlegadas.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
				
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 

				if(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_reclamo()!=null){
					java.sql.Date fr = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_reclamo().getTime());
					rowPLlegadas.add(format2.format(fr));//Fecha Reclamo
				}else{
					rowPLlegadas.add("");
				}			    
				java.sql.Date fturno = null;
				if (pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno()!=null){
					fturno = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno().getTime());
					rowPLlegadas.add(format2.format(fturno));//Fecha Turno
				}else{
					rowPLlegadas.add("");
				}
				java.sql.Date fsf = null;
				if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
					fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
					rowPLlegadas.add(format2.format(fsf));//Fecha solicitud Fabrica
				}else{
					rowPLlegadas.add("");
				}
				java.sql.Date frf = null;
				if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
					frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
					rowPLlegadas.add(format2.format(frf));//Fecha Recepcion Fabrica
				}else{
					rowPLlegadas.add("");
				}
				java.sql.Date fdf = null;
				if (pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){
					fdf = new java.sql.Date(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().getTime());
					rowPLlegadas.add(format2.format(fdf));//Fecha Devolucion Fabrica 
				}else{
					rowPLlegadas.add("");
				}
				datos_tabla_Pllegadas_PDevuletas_LLegadas.add(rowPLlegadas);
				numero_PiezasLlegadas++;
			}
			Vector<String> rowPDevueltas = new Vector<String> ();
			if(pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){
				rowPDevueltas.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
				rowPDevueltas.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
				rowPDevueltas.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
				rowPDevueltas.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
				rowPDevueltas.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
				rowPDevueltas.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
				rowPDevueltas.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
				
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 

				if(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_reclamo()!=null){
					java.sql.Date fr = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_reclamo().getTime());
					rowPDevueltas.add(format2.format(fr));//Fecha Reclamo
				}else{
					rowPDevueltas.add("");
				}			    
				java.sql.Date fturno = null;
				if (pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno()!=null){
					fturno = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno().getTime());
					rowPDevueltas.add(format2.format(fturno));//Fecha Turno
				}else{
					rowPDevueltas.add("");
				}
				java.sql.Date fsf = null;
				if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
					fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
					rowPDevueltas.add(format2.format(fsf));//Fecha solicitud Fabrica
				}else{
					rowPDevueltas.add("");
				}
				java.sql.Date frf = null;
				if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
					frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
					rowPDevueltas.add(format2.format(frf));//Fecha Recepcion Fabrica
				}else{
					rowPDevueltas.add("");
				}
				java.sql.Date fdf = null;
				if (pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){
					fdf = new java.sql.Date(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().getTime());
					rowPDevueltas.add(format2.format(fdf));//Fecha Devolucion Fabrica 
				}else{
					rowPDevueltas.add("");
				}
				datos_tabla_Pllegadas_PDevuletas_Devueltas.add(rowPDevueltas);
				numero_PiezasDevueltas++;
			}

		}
		modelo_tabla_Pllegadas_PDevuletas_Devueltas.setDataVector(datos_tabla_Pllegadas_PDevuletas_Devueltas, nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas);
		modelo_tabla_Pllegadas_PDevuletas_Devueltas.fireTableStructureChanged();
		modelo_tabla_Pllegadas_PDevuletas_LLegadas.setDataVector(datos_tabla_Pllegadas_PDevuletas_LLegadas, nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas);
		modelo_tabla_Pllegadas_PDevuletas_LLegadas.fireTableStructureChanged();
	}
	
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTE - PIEZAS LLEGADAS - PIEZAS DEVUELTAS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReporteDiasPiezasLlegadas_PiezasDevueltas.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

 	    //PIEZAS LLEGADAS - PIEZAS DEVUELTAS//        
        pnPiezasLlegadas_PiezasDevueltas = new JPanel();
        pnPiezasLlegadas_PiezasDevueltas.setBounds(0, 0, 1274, 681);
        contentPane.add(pnPiezasLlegadas_PiezasDevueltas);
        pnPiezasLlegadas_PiezasDevueltas.setLayout(null);
		//PIEZAS LLEGADAS PIEZAS DEVUELTAS (LLEGADAS)//
        modelo_tabla_Pllegadas_PDevuletas_LLegadas = new DefaultTableModel(datos_tabla_Pllegadas_PDevuletas_LLegadas , nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas );
        tabla_PLLPDL = new JTable(modelo_tabla_Pllegadas_PDevuletas_LLegadas) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false,false, false,
					false, false, false,false, false,
			};
			public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_tabla_Pllegadas_PDevuletas_LLegadas = new TableRowSorter<TableModel>(modelo_tabla_Pllegadas_PDevuletas_LLegadas);
		for(int i = 0; i < tabla_PLLPDL.getColumnCount(); i++) {
			tabla_PLLPDL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_Pllegadas_PDevuletas_LLegadas.elementAt(i));
			tabla_PLLPDL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_Pllegadas_PDevuletas_LLegadas.elementAt(i));
		}
        tabla_PLLPDL.setRowSorter(ordenador_tabla_Pllegadas_PDevuletas_LLegadas);
        tabla_PLLPDL.getTableHeader().setReorderingAllowed(false);
        tabla_PLLPDL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane_tabla_Pllegadas_PDevuletas_LLegadas= new JScrollPane(tabla_PLLPDL);
        scrollPane_tabla_Pllegadas_PDevuletas_LLegadas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_tabla_Pllegadas_PDevuletas_LLegadas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_tabla_Pllegadas_PDevuletas_LLegadas.setBounds(5, 205, 1255, 200);
        pnPiezasLlegadas_PiezasDevueltas.add(scrollPane_tabla_Pllegadas_PDevuletas_LLegadas);	
        tabla_PLLPDL.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //PIEZAS LLEGADAS PIEZAS DEVUELTAS (DEVUELTAS)//
		modelo_tabla_Pllegadas_PDevuletas_Devueltas = new DefaultTableModel(datos_tabla_Pllegadas_PDevuletas_Devueltas , nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas );     
        tabla_PLLPDD = new JTable(modelo_tabla_Pllegadas_PDevuletas_Devueltas) {
        	private static final long serialVersionUID = 1L;
        	boolean[] columnEditables = new boolean[] {
        			false, false, false, false, false,
        			false, false, false,false, false,
        			false, false, false,false, false,
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        };
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_tabla_Pllegadas_PDevuletas_Devueltas = new TableRowSorter<TableModel>(modelo_tabla_Pllegadas_PDevuletas_Devueltas);
		for(int i = 0; i < tabla_PLLPDD.getColumnCount(); i++) {
			tabla_PLLPDD.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_Pllegadas_PDevuletas_Devueltas.elementAt(i));
			tabla_PLLPDD.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_Pllegadas_PDevuletas_Devueltas.elementAt(i));
		}
        tabla_PLLPDD.setRowSorter(ordenador_tabla_Pllegadas_PDevuletas_Devueltas);
        tabla_PLLPDD.getTableHeader().setReorderingAllowed(false);
        tabla_PLLPDD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane_tabla_Pllegadas_PDevuletas_Devueltas= new JScrollPane(tabla_PLLPDD);
        scrollPane_tabla_Pllegadas_PDevuletas_Devueltas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_tabla_Pllegadas_PDevuletas_Devueltas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_tabla_Pllegadas_PDevuletas_Devueltas.setBounds(5, 470, 1255, 200);
        pnPiezasLlegadas_PiezasDevueltas.add(scrollPane_tabla_Pllegadas_PDevuletas_Devueltas);
        tabla_PLLPDD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        JLabel lblPiezasLlegadas = new JLabel("PIEZAS DEVUELTAS / PIEZAS LLEGADAS");
        lblPiezasLlegadas.setHorizontalAlignment(SwingConstants.CENTER);
        lblPiezasLlegadas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblPiezasLlegadas.setBounds(933, 11, 200, 25);
        pnPiezasLlegadas_PiezasDevueltas.add(lblPiezasLlegadas);
        
        lblLlegadaDevueltas = new JLabel("");
        lblLlegadaDevueltas.setHorizontalAlignment(SwingConstants.CENTER);
        lblLlegadaDevueltas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblLlegadaDevueltas.setBounds(1155, 11, 100, 25);
        pnPiezasLlegadas_PiezasDevueltas.add(lblLlegadaDevueltas);
        
        JLabel lblNumeroDePiezas_1 = new JLabel("NUMERO DE  PIEZAS DEVUELTAS");
        lblNumeroDePiezas_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumeroDePiezas_1.setBounds(5, 439, 230, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(lblNumeroDePiezas_1);
        
        lblNum_PDevueltas = new JLabel("");
        lblNum_PDevueltas.setText(String.valueOf(numero_PiezasDevueltas));
        lblNum_PDevueltas.setHorizontalAlignment(SwingConstants.CENTER);
        lblNum_PDevueltas.setBounds(250, 439, 45, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(lblNum_PDevueltas);
        
        lblNumPLlegadas = new JLabel("");
        lblNumPLlegadas.setText(String.valueOf(numero_PiezasLlegadas));
        lblNumPLlegadas.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumPLlegadas.setBounds(250, 180, 45, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(lblNumPLlegadas);
        
        JLabel lblNumeroDePiezas = new JLabel("NUMERO DE PIEZAS LLEGADAS");
        lblNumeroDePiezas.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumeroDePiezas.setBounds(5, 180, 230, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(lblNumeroDePiezas);
        
        lblPiezasLLegadas_PiezasDevueltas = new JLabel("");
        lblPiezasLLegadas_PiezasDevueltas.setText(String.valueOf(numero_PiezasDevueltas - numero_PiezasLlegadas));
        lblPiezasLLegadas_PiezasDevueltas.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblPiezasLLegadas_PiezasDevueltas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblPiezasLLegadas_PiezasDevueltas.setBounds(1155, 49, 100, 25);
        pnPiezasLlegadas_PiezasDevueltas.add(lblPiezasLLegadas_PiezasDevueltas);
    	        
        JLabel lblPiezasLlegadas_1 = new JLabel("PIEZAS LLEGADAS - PIEZAS DEVUELTAS");
        lblPiezasLlegadas_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblPiezasLlegadas_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblPiezasLlegadas_1.setBounds(933, 49, 200, 25);
        pnPiezasLlegadas_PiezasDevueltas.add(lblPiezasLlegadas_1);
        
		if(numero_PiezasDevueltas==0 && numero_PiezasLlegadas==0){
			lblLlegadaDevueltas.setText("N/A");
			lblLlegadaDevueltas.setBackground(Color.WHITE);
		}else{
			lblLlegadaDevueltas.setText(String.valueOf(numero_PiezasDevueltas / numero_PiezasLlegadas));
			if((numero_PiezasDevueltas/numero_PiezasLlegadas) <= 0.90){
				lblLlegadaDevueltas.setBackground(Color.RED);

			}else{
				if((numero_PiezasDevueltas/numero_PiezasLlegadas)>0.90 && (numero_PiezasDevueltas/numero_PiezasLlegadas)<1.10){
					lblLlegadaDevueltas.setBackground(Color.GREEN);
				}else{
					lblLlegadaDevueltas.setBackground(Color.YELLOW);
				}
			}
		}
		if((numero_PiezasLlegadas - numero_PiezasDevueltas)> 5){
			lblPiezasLLegadas_PiezasDevueltas.setBackground(Color.RED);
		}else{
			if((numero_PiezasLlegadas - numero_PiezasDevueltas)<= 0){
				lblPiezasLLegadas_PiezasDevueltas.setBackground(Color.GREEN);
			}else{
				lblPiezasLLegadas_PiezasDevueltas.setBackground(Color.YELLOW);
			}
		}
		
        rB_Anio_PLLPD = new JRadioButton("Ultimo A\u00F1o");
        rB_Anio_PLLPD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Anio_PLLPD.isSelected()){
        			rB_Intervalo_PLLPD.setSelected(false);
        			dC_FF_PLLPD.setEnabled(false);
        			dC_FI_PLLPD.setEnabled(false);
        			rB_Hoy_PLLPD.setSelected(false);
        			rB_USemana_PLLPD.setSelected(false);
        			rB_Mes_PLLPD.setSelected(false);
        			rB_MAnterior_PLLPD.setSelected(false);
        			filtrarUAnioPLLPD();
        		}
        	}
        });
        rB_Anio_PLLPD.setBounds(5, 135, 150, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(rB_Anio_PLLPD);
        
        rB_MAnterior_PLLPD = new JRadioButton("Mes Anterior");
        rB_MAnterior_PLLPD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_MAnterior_PLLPD.isSelected()){
        			rB_Intervalo_PLLPD.setSelected(false);
        			dC_FF_PLLPD.setEnabled(false);
        			dC_FI_PLLPD.setEnabled(false);
        			rB_Hoy_PLLPD.setSelected(false);
        			rB_USemana_PLLPD.setSelected(false);
        			rB_Mes_PLLPD.setSelected(false);
        			rB_Anio_PLLPD.setSelected(false);
        			filtrarMAnteriorPLLPD();
        		}
        	}
        });
        rB_MAnterior_PLLPD.setBounds(5, 110, 150, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(rB_MAnterior_PLLPD);
        
        rB_Mes_PLLPD = new JRadioButton("Este Mes");
        rB_Mes_PLLPD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Mes_PLLPD.isSelected()){
        			rB_Intervalo_PLLPD.setSelected(false);
        			dC_FF_PLLPD.setEnabled(false);
        			dC_FI_PLLPD.setEnabled(false);
        			rB_Hoy_PLLPD.setSelected(false);
        			rB_USemana_PLLPD.setSelected(false);
        			rB_MAnterior_PLLPD.setSelected(false);
        			rB_Anio_PLLPD.setSelected(false);
        			filtrarMesPLLPD();
        		}
        	}
        });
        rB_Mes_PLLPD.setToolTipText("Piezas llegadas en el mes corriente.");
        rB_Mes_PLLPD.setBounds(5, 85, 150, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(rB_Mes_PLLPD);
        
        rB_USemana_PLLPD = new JRadioButton("Ultima Semana");
        rB_USemana_PLLPD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_USemana_PLLPD.isSelected()){
        			rB_Intervalo_PLLPD.setSelected(false);
        			dC_FF_PLLPD.setEnabled(false);
        			dC_FI_PLLPD.setEnabled(false);
        			rB_Hoy_PLLPD.setSelected(false);
        			rB_Mes_PLLPD.setSelected(false);
        			rB_MAnterior_PLLPD.setSelected(false);
        			rB_Anio_PLLPD.setSelected(false);
        			filtrarUSemanaPLLPD();
        		}
        	}
        });
        rB_USemana_PLLPD.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
        rB_USemana_PLLPD.setBounds(5, 60, 150, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(rB_USemana_PLLPD);
        
        rB_Hoy_PLLPD = new JRadioButton("Hoy");
        rB_Hoy_PLLPD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Hoy_PLLPD.isSelected()){
        			rB_Intervalo_PLLPD.setSelected(false);
        			dC_FF_PLLPD.setEnabled(false);
        			dC_FI_PLLPD.setEnabled(false);
        			rB_USemana_PLLPD.setSelected(false);
        			rB_Mes_PLLPD.setSelected(false);
        			rB_MAnterior_PLLPD.setSelected(false);
        			rB_Anio_PLLPD.setSelected(false);
        			filtrarHoyPLLPD();
        		}
        	}
        });
        rB_Hoy_PLLPD.setToolTipText("Piezas llegadas el dia de hoy.");
        rB_Hoy_PLLPD.setBounds(5, 35, 150, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(rB_Hoy_PLLPD);
        
        rB_Intervalo_PLLPD = new JRadioButton("Intervalo de Fechas");
        rB_Intervalo_PLLPD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Intervalo_PLLPD.isSelected()){
        			dC_FF_PLLPD.setEnabled(true);
        			dC_FI_PLLPD.setEnabled(true);
        			btn_filtrar_PLLPD.setEnabled(true);
        			rB_Hoy_PLLPD.setSelected(false);
        			rB_USemana_PLLPD.setSelected(false);
        			rB_Mes_PLLPD.setSelected(false);
        			rB_MAnterior_PLLPD.setSelected(false);
        			rB_Anio_PLLPD.setSelected(false);
        		}else{
        			dC_FF_PLLPD.setEnabled(false);
        			dC_FI_PLLPD.setEnabled(false);
        			btn_filtrar_PLLPD.setEnabled(false);
        		}
        	}
        });
        rB_Intervalo_PLLPD.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
        rB_Intervalo_PLLPD.setBounds(5, 10, 150, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(rB_Intervalo_PLLPD);
        
        JLabel label_12 = new JLabel("Fecha Inicio");
        label_12.setHorizontalAlignment(SwingConstants.CENTER);
        label_12.setEnabled(false);
        label_12.setBounds(160, 10, 120, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(label_12);
        
        dC_FI_PLLPD = new JDateChooser();
        dC_FI_PLLPD.setEnabled(false);
        dC_FI_PLLPD.setBounds(290, 10, 150, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(dC_FI_PLLPD);
        
        btn_clear_FIPLLPD = new JButton("");
        btn_clear_FIPLLPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FI_PLLPD.getDate()!=null)
					dC_FI_PLLPD.setDate(null);
			}
		});
        btn_clear_FIPLLPD.setIcon(new ImageIcon(GUIReporteDiasPiezasLlegadas_PiezasDevueltas.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FIPLLPD.setBounds(450, 10, 25, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(btn_clear_FIPLLPD);
        
        JLabel label_13 = new JLabel("Fecha Fin");
        label_13.setHorizontalAlignment(SwingConstants.CENTER);
        label_13.setEnabled(false);
        label_13.setBounds(485, 10, 120, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(label_13);
        
        dC_FF_PLLPD = new JDateChooser();
        dC_FF_PLLPD.setEnabled(false);
        dC_FF_PLLPD.setBounds(615, 10, 150, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(dC_FF_PLLPD);
        
        btn_clear_FFPLLPD = new JButton("");
        btn_clear_FFPLLPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_PLLPD.getDate()!=null)
					dC_FF_PLLPD.setDate(null);
			}
		});
        btn_clear_FFPLLPD.setIcon(new ImageIcon(GUIReporteDiasPiezasLlegadas_PiezasDevueltas.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FFPLLPD.setBounds(775, 10, 25, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(btn_clear_FFPLLPD);
        
        btn_filtrar_PLLPD = new JButton("Filtrar");
        btn_filtrar_PLLPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarIntervaloPLLPD();
			}
		});
        btn_filtrar_PLLPD.setEnabled(false);
        btn_filtrar_PLLPD.setBounds(810, 10, 110, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(btn_filtrar_PLLPD);
        
        btnExportarTabla_PLLPD = new JButton("");
        btnExportarTabla_PLLPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaPLLPD();
			}
		});
        btnExportarTabla_PLLPD.setIcon(new ImageIcon(GUIReporteDiasPiezasLlegadas_PiezasDevueltas.class.getResource("/cliente/Resources/Icons/formulario.png")));
        btnExportarTabla_PLLPD.setBounds(1230, 165, 32, 32);
        pnPiezasLlegadas_PiezasDevueltas.add(btnExportarTabla_PLLPD);
	}

	protected void exportarTablaPLLPD() {
		 try {
				List<JTable> t = new ArrayList<JTable>();
				List<String> n = new ArrayList<String>();
				t.add(tabla_PLLPDD);
				t.add(tabla_PLLPDL);
				n.add("Piezas LLegadas");
				n.add("Piezas Devueltas");
				ExportarExcel.exportarTablas(t, n);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
			}
		
	}

	protected void filtrarIntervaloPLLPD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyPLLPD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaPLLPD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesPLLPD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorPLLPD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioPLLPD() {
		// TODO Auto-generated method stub
		
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
