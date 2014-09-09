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

public class GUIReportePiezasSinLlegar extends JFrame {

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	//
	private Vector<Pedido_PiezaDTO> pedidos_piezas;
	//
	private JPanel contentPane;
	private JPanel pnPiezasPedidasSinLLegar;
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
	private JButton btnFiltrar_SLL;
	private JButton btn_clear_FFSLL;
	private JButton btnExportarTablaSLL;
	private JButton btn_clear_FISLL;
	
	//tabla 
	public GUIReportePiezasSinLlegar(MediadorReportes mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
	}

	private void cargarDatos() {

		pedidos_piezas = mediador.obtenerPiezasSinLlegar();
		int chico = 100;
		int mediano = 150;
		int grande = 230;
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
			datosTabla_piezas_sin_llegar.add(row);
		}
		modelo_tabla_piezas_sin_llegar.setDataVector(datosTabla_piezas_sin_llegar, nombreColumnas_piezas_sin_llegar);
		modelo_tabla_piezas_sin_llegar.fireTableStructureChanged();
	}
	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTE - PIEZAS SIN LLEGAR");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Tabla Piezas Sin Llegar//
		pnPiezasPedidasSinLLegar = new JPanel();
		pnPiezasPedidasSinLLegar.setBounds(0, 0, 1274, 681);
		contentPane.add(pnPiezasPedidasSinLLegar);
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
		scrollPane_pedidas_sin_llegar.setBounds(5, 160, 1255, 510);
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
	}

	//Piezas Sin Llegar
	protected void exportarTablaSLL() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_SLL, "Piezas Sin Llegar");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
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
		modelo_tabla_piezas_sin_llegar.setDataVector(datosTabla_piezas_sin_llegar, nombreColumnas_piezas_sin_llegar);
		modelo_tabla_piezas_sin_llegar.fireTableStructureChanged();
		for(int i = 0; i < tabla_SLL.getColumnCount(); i++) {
			tabla_SLL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
			tabla_SLL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
		}
	}

	@SuppressWarnings("deprecation")
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
		modelo_tabla_piezas_sin_llegar.setDataVector(datosTabla_piezas_sin_llegar, nombreColumnas_piezas_sin_llegar);
		modelo_tabla_piezas_sin_llegar.fireTableStructureChanged();
		for(int i = 0; i < tabla_SLL.getColumnCount(); i++) {
			tabla_SLL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
			tabla_SLL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
		}		
	}

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
		modelo_tabla_piezas_sin_llegar.setDataVector(datosTabla_piezas_sin_llegar, nombreColumnas_piezas_sin_llegar);
		modelo_tabla_piezas_sin_llegar.fireTableStructureChanged();
		for(int i = 0; i < tabla_SLL.getColumnCount(); i++) {
			tabla_SLL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
			tabla_SLL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_piezas_sin_llegar.elementAt(i));
		}
	}

	protected void filtrarIntervaloSLL() {
		if(dC_FI_SLL.getDate()!=null && dC_FF_SLL.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			datosTabla_piezas_sin_llegar= new Vector<Vector<String>>();			
			Calendar c = new GregorianCalendar();
			c.setTime(dC_FI_SLL.getDate());
			c.add(c.DAY_OF_MONTH, -1);
			for (int i=0; i< pedidos_piezas.size();i++){
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
}