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

public class GUIReportePiezasLlegadas extends JFrame {

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private Vector<Pedido_PiezaDTO> pedidos_piezas;
	private JPanel contentPane;
	private JPanel pnPiezaLLegadas;
	private DefaultTableModel modelo_tabla_piezas_llegadas;
	private JTable tabla_PLL;
	private Vector<Vector<String>> datosTabla_piezas_llegadas;
	private Vector<String> nombreColumnas_piezas_llegadas;
	private Vector<Integer> anchos_tabla_piezas_llegadas;
	private JRadioButton rBMAnteriorPLL;
	private JRadioButton rB_Mes_PLL;
	private JRadioButton rB_Itervalo_PLL;
	private JRadioButton rB_Hoy_PLL;
	private JRadioButton rB_USemana_PLL;
	private JRadioButton rB_Anio_PLL;
	private JDateChooser dC_FInicioPLL;
	private JDateChooser dC_FFinPLL;
	private JButton btnFiltrar_PLL;
	private JButton btn_clear_FIPLL;
	private JButton btn_clear_FFPLL;
	private JButton btnExportarTablaPLL;
	//tabla 
	public GUIReportePiezasLlegadas(MediadorReportes mediadorReporte) {
		setMediador(mediadorReporte);
		cargarDatos();
		initialize();
	}

	private void cargarDatos() {
		pedidos_piezas = mediador.obtenerPiezasLlegadas();
		int chico = 100;
		int mediano = 150;
		int grande = 230;	
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
		for (int i=0; i< pedidos_piezas.size();i++){
			Vector<String> row = new Vector<String> ();
			row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
			row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
			row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
			row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
			row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
			row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
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
		modelo_tabla_piezas_llegadas.setDataVector(datosTabla_piezas_llegadas, nombreColumnas_piezas_llegadas);
		modelo_tabla_piezas_llegadas.fireTableStructureChanged();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTE - PIEZAS LLEGADAS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReportePiezasLlegadas.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Tabla Piezas Llegadas//
		modelo_tabla_piezas_llegadas = new DefaultTableModel(datosTabla_piezas_llegadas, nombreColumnas_piezas_llegadas);
		pnPiezaLLegadas = new JPanel();
		pnPiezaLLegadas.setBounds(0, 0, 1274, 681);
		contentPane.add(pnPiezaLLegadas);
		pnPiezaLLegadas.setLayout(null);
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
		scrollPane_piezas_llegadas.setBounds(5, 160, 1257, 510);
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
		btn_clear_FIPLL.setIcon(new ImageIcon(GUIReportePiezasLlegadas.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FIPLL.setBounds(450, 10, 25, 20);
		pnPiezaLLegadas.add(btn_clear_FIPLL);
		
		btn_clear_FFPLL = new JButton("");
		btn_clear_FFPLL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FFinPLL.getDate()!=null)
					dC_FFinPLL.setDate(null);
			}
		});
		btn_clear_FFPLL.setIcon(new ImageIcon(GUIReportePiezasLlegadas.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FFPLL.setBounds(775, 10, 25, 20);
		pnPiezaLLegadas.add(btn_clear_FFPLL);
		
		btnExportarTablaPLL = new JButton("");
		btnExportarTablaPLL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaPLL();
			}
		});
		btnExportarTablaPLL.setIcon(new ImageIcon(GUIReportePiezasLlegadas.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTablaPLL.setBounds(1230, 120, 32, 32);
		pnPiezaLLegadas.add(btnExportarTablaPLL);
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
			Calendar c = new GregorianCalendar();
			c.setTime(dC_FInicioPLL.getDate());
			c.add(c.DAY_OF_MONTH, -1);			
			for (int i=0; i< pedidos_piezas.size();i++){
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
	//gettes and setters
	public MediadorReportes getMediador() {
		return mediador;
	}

	public void setMediador(MediadorReportes mediador) {
		this.mediador = mediador;
	}

}
