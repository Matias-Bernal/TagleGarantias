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
import common.DTOs.ReclamoDTO;


public class GUIReporteOrdenSinSDP extends JFrame {

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private Vector<ReclamoDTO> reclamos;
	private JPanel contentPane;
	private JPanel pnOrdenSinSDP;
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
	private JRadioButton rB_MesAnterior_OSSDP;
	private JButton btnFiltrarOSSDP;
	private JButton btn_clear_FISSP;
	private JButton btn_clear_FFSSP;
	private JButton btnExportarTablaSSP;
	
	//tabla 
	public GUIReporteOrdenSinSDP(MediadorReportes mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
	}

	private void cargarDatos() {
		reclamos = mediador.obtenerOrdenSinSDP();
		int chico = 100;
		int mediano = 150;
		int grande = 230;	
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
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 	
		for (int i=0; i< reclamos.size();i++){
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
		modelo_tabla_orden_sin_solicitud_pedido.setDataVector(datosTabla_orden_sin_solicitud_pedido, nombreColumnas_orden_sin_solicitud_pedido);
		modelo_tabla_orden_sin_solicitud_pedido.fireTableStructureChanged();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTE - ORDEN SIN SOLICITUD DE PEDIDO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Tabla Orden Sin Solicitud De Pedido //
		pnOrdenSinSDP = new JPanel();
		pnOrdenSinSDP.setBounds(0, 0, 1274, 681);
		contentPane.add(pnOrdenSinSDP);
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
		scrollPane_orden_sin_sdp.setBounds(5, 160, 1255, 510);
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
	}


	//Orden Sin Solicitud de Pedido
	protected void exportarTablaSSP() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_OSSDP, "Ordenes Sin Solicitud de Pedidos");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void filtrarPorIntervaloOSSDP() {
		if(dC_FI_OSSDP.getDate()!=null && dC_FF_OSSDP.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = new GregorianCalendar();
			c.setTime(dC_FI_OSSDP.getDate());
			c.add(c.DAY_OF_MONTH, -1);
			datosTabla_orden_sin_solicitud_pedido= new Vector<Vector<String>>();
			for (int i=0; i< reclamos.size();i++){
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
		modelo_tabla_orden_sin_solicitud_pedido.setDataVector(datosTabla_orden_sin_solicitud_pedido, nombreColumnas_orden_sin_solicitud_pedido);
		modelo_tabla_orden_sin_solicitud_pedido.fireTableStructureChanged();
		for(int i = 0; i < tabla_OSSDP.getColumnCount(); i++) {
			tabla_OSSDP.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
			tabla_OSSDP.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
		}
	}

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
		modelo_tabla_orden_sin_solicitud_pedido.setDataVector(datosTabla_orden_sin_solicitud_pedido, nombreColumnas_orden_sin_solicitud_pedido);
		modelo_tabla_orden_sin_solicitud_pedido.fireTableStructureChanged();
		for(int i = 0; i < tabla_OSSDP.getColumnCount(); i++) {
			tabla_OSSDP.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
			tabla_OSSDP.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
		}
	}

	@SuppressWarnings("deprecation")
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
		modelo_tabla_orden_sin_solicitud_pedido.setDataVector(datosTabla_orden_sin_solicitud_pedido, nombreColumnas_orden_sin_solicitud_pedido);
		modelo_tabla_orden_sin_solicitud_pedido.fireTableStructureChanged();
		for(int i = 0; i < tabla_OSSDP.getColumnCount(); i++) {
			tabla_OSSDP.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
			tabla_OSSDP.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
		}
	}

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
		modelo_tabla_orden_sin_solicitud_pedido.setDataVector(datosTabla_orden_sin_solicitud_pedido, nombreColumnas_orden_sin_solicitud_pedido);
		modelo_tabla_orden_sin_solicitud_pedido.fireTableStructureChanged();
		for(int i = 0; i < tabla_OSSDP.getColumnCount(); i++) {
			tabla_OSSDP.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
			tabla_OSSDP.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_orden_sin_solicitud_pedido.elementAt(i));
		}	
	}
}
