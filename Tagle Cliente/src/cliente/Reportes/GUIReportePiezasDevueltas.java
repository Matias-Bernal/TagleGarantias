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

public class GUIReportePiezasDevueltas extends JFrame {

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private Vector<Pedido_PiezaDTO> pedidos_piezas;
	private JPanel contentPane;
	private JPanel pnPiezasDevueltas;
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
	private JRadioButton rbMAnteriorPDev;
	private JButton btnFiltrarPDev;
	private JButton btn_clear_FIPD;
	private JButton btn_clear_FFPD;
	private JButton btnExportarTablaPD;
	
	//tabla 
	public GUIReportePiezasDevueltas(MediadorReportes mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
	}

	private void cargarDatos() {
		pedidos_piezas = mediador.obtenerPiezasDevueltas();
		int chico = 100;
		int mediano = 150;
		int grande = 230;	
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
		modelo_tabla_piezas_devueltas.setDataVector(datosTabla_piezas_devueltas, nombreColumnas_piezas_devueltas);
		modelo_tabla_piezas_devueltas.fireTableStructureChanged();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTE - PIEZAS DEVUELTAS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReportePiezasDevueltas.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Tabla Piezas Devueltas//
		modelo_tabla_piezas_devueltas = new DefaultTableModel(datosTabla_piezas_devueltas, nombreColumnas_piezas_devueltas);
		pnPiezasDevueltas = new JPanel();
		pnPiezasDevueltas.setBounds(0, 0, 1274, 681);
		contentPane.add(pnPiezasDevueltas);
		pnPiezasDevueltas.setLayout(null);
		tabla_PD = new JTable(modelo_tabla_piezas_devueltas) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false,false, false, 
					false, false, false, false, false,
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
		scrollPane_piezas_devueltas.setBounds(5, 160, 1255, 510);
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
		btn_clear_FIPD.setIcon(new ImageIcon(GUIReportePiezasDevueltas.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FIPD.setBounds(450, 10, 25, 20);
		pnPiezasDevueltas.add(btn_clear_FIPD);
		
		btn_clear_FFPD = new JButton("");
		btn_clear_FFPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_PD.getDate()!=null)
					dC_FF_PD.setDate(null);
			}
		});
		btn_clear_FFPD.setIcon(new ImageIcon(GUIReportePiezasDevueltas.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FFPD.setBounds(775, 10, 25, 20);
		pnPiezasDevueltas.add(btn_clear_FFPD);
		
		btnExportarTablaPD = new JButton("");
		btnExportarTablaPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaPD();
			}
		});
		btnExportarTablaPD.setIcon(new ImageIcon(GUIReportePiezasDevueltas.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTablaPD.setBounds(1230, 120, 32, 32);
		pnPiezasDevueltas.add(btnExportarTablaPD);
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
		modelo_tabla_piezas_devueltas.setDataVector(datosTabla_piezas_devueltas, nombreColumnas_piezas_devueltas);
		modelo_tabla_piezas_devueltas.fireTableStructureChanged();
		for(int i = 0; i < tabla_PD.getColumnCount(); i++) {
			tabla_PD.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_devueltas.elementAt(i));
			tabla_PD.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_devueltas.elementAt(i));
		}
	}

	@SuppressWarnings("deprecation")
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
		modelo_tabla_piezas_devueltas.setDataVector(datosTabla_piezas_devueltas, nombreColumnas_piezas_devueltas);
		modelo_tabla_piezas_devueltas.fireTableStructureChanged();
		for(int i = 0; i < tabla_PD.getColumnCount(); i++) {
			tabla_PD.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_devueltas.elementAt(i));
			tabla_PD.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_devueltas.elementAt(i));
		}	
	}

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
		modelo_tabla_piezas_devueltas.setDataVector(datosTabla_piezas_devueltas, nombreColumnas_piezas_devueltas);
		modelo_tabla_piezas_devueltas.fireTableStructureChanged();
		for(int i = 0; i < tabla_PD.getColumnCount(); i++) {
			tabla_PD.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_devueltas.elementAt(i));
			tabla_PD.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_devueltas.elementAt(i));
		}
	}

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
		modelo_tabla_piezas_devueltas.setDataVector(datosTabla_piezas_devueltas, nombreColumnas_piezas_devueltas);
		modelo_tabla_piezas_devueltas.fireTableStructureChanged();
		for(int i = 0; i < tabla_PD.getColumnCount(); i++) {
			tabla_PD.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_devueltas.elementAt(i));
			tabla_PD.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_devueltas.elementAt(i));
		}
	}

	protected void filtrarInervaloPDev() {
		if(dC_FI_PD.getDate()!=null && dC_FF_PD.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			datosTabla_piezas_devueltas= new Vector<Vector<String>>();
			
			Calendar c = new GregorianCalendar();
			c.setTime(dC_FI_PD.getDate());
			c.add(c.DAY_OF_MONTH, -1);

			for (int i=0; i< pedidos_piezas.size();i++){
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

}
