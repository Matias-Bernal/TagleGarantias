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

public class GUIReportePiezasLlegadasSinTurno extends JFrame {

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private Vector<Pedido_PiezaDTO> pedidos_piezas;
	private JPanel contentPane;
	private JPanel pnPiezasLLegadasSinTurno;
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
	private JRadioButton rBMesPasado_PLLST;
	private JButton btnFiltrarPLLST;
	private JButton btn_clear_FILLST;
	private JButton btn_clear_FFLLST;
	private JButton btnExportarTablaLLST;
	
	//tabla 
	public GUIReportePiezasLlegadasSinTurno(MediadorReportes mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
	}

	private void cargarDatos() {
		pedidos_piezas = mediador.obtenerPiezasLlegadasSinTurno(); 
		int chico = 100;
		int mediano = 150;
		int grande = 230;	
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
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
		for (int i=0; i< pedidos_piezas.size();i++){
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
		modelo_tabla_piezas_llegadas_sin_turno.setDataVector(datosTabla_piezas_llegadas_sin_turno, nombreColumnas_piezas_llegadas_sin_turno);
		modelo_tabla_piezas_llegadas_sin_turno.fireTableStructureChanged();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTE - PIEZAS LLEGADAS SIN TURNO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Tabla Piezas Llegadas Sin Turno //
		modelo_tabla_piezas_llegadas_sin_turno = new DefaultTableModel(datosTabla_piezas_llegadas_sin_turno, nombreColumnas_piezas_llegadas_sin_turno);
		pnPiezasLLegadasSinTurno = new JPanel();
		pnPiezasLLegadasSinTurno.setBounds(0, 0, 1274, 681);
		contentPane.add(pnPiezasLLegadasSinTurno);
		pnPiezasLLegadasSinTurno.setLayout(null);
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
		scrollPane_piezas_llegadas_sin_turno.setBounds(5, 160, 1255, 510);
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
	}

	//Piezas Llegadas Sin Turno
	protected void exportarTablaPLLST() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_PLLST, "Piezas Llegadas Sin Turno");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void filtrarPorIntervaloPLLST() {
		if(dC_FI_PLLST.getDate()!=null && dC_FF_PLLST.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			datosTabla_piezas_llegadas_sin_turno= new Vector<Vector<String>>();
			Calendar c = new GregorianCalendar();
			c.setTime(dC_FI_PLLST.getDate());
			c.add(c.DAY_OF_MONTH, -1);
			for (int i=0; i< pedidos_piezas.size();i++){
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
		modelo_tabla_piezas_llegadas_sin_turno.setDataVector(datosTabla_piezas_llegadas_sin_turno, nombreColumnas_piezas_llegadas_sin_turno);
		modelo_tabla_piezas_llegadas_sin_turno.fireTableStructureChanged();
		for(int i = 0; i < tabla_PLLST.getColumnCount(); i++) {
			tabla_PLLST.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
			tabla_PLLST.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
		}
	}

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
		modelo_tabla_piezas_llegadas_sin_turno.setDataVector(datosTabla_piezas_llegadas_sin_turno, nombreColumnas_piezas_llegadas_sin_turno);
		modelo_tabla_piezas_llegadas_sin_turno.fireTableStructureChanged();
		for(int i = 0; i < tabla_PLLST.getColumnCount(); i++) {
			tabla_PLLST.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
			tabla_PLLST.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
		}
	}

	@SuppressWarnings("deprecation")
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
		modelo_tabla_piezas_llegadas_sin_turno.setDataVector(datosTabla_piezas_llegadas_sin_turno, nombreColumnas_piezas_llegadas_sin_turno);
		modelo_tabla_piezas_llegadas_sin_turno.fireTableStructureChanged();
		for(int i = 0; i < tabla_PLLST.getColumnCount(); i++) {
			tabla_PLLST.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
			tabla_PLLST.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
		}	
	}

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
		modelo_tabla_piezas_llegadas_sin_turno.setDataVector(datosTabla_piezas_llegadas_sin_turno, nombreColumnas_piezas_llegadas_sin_turno);
		modelo_tabla_piezas_llegadas_sin_turno.fireTableStructureChanged();
		for(int i = 0; i < tabla_PLLST.getColumnCount(); i++) {
			tabla_PLLST.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
			tabla_PLLST.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_llegadas_sin_turno.elementAt(i));
		}		
	}
}
