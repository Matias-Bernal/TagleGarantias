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

public class GUIReporteSDPSinNP extends JFrame {

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private Vector<Pedido_PiezaDTO> pedidos_piezas;
	private JPanel contentPane;
	private JPanel pnSDPsinNumPedido;
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
	private JRadioButton rB_MesPasado_SDPSN;
	private JButton btnFiltrarSDPSN;
	private JButton btn_clear_FISPSNP;
	private JButton btn_clear_FFSPSNP;
	private JButton btnExportarTablaSPSNP;
	
	//tabla 
	public GUIReporteSDPSinNP(MediadorReportes mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
	}

	private void cargarDatos() {
		pedidos_piezas = mediador.obtenerSDPSinNP(); 
		int chico = 100;
		int mediano = 150;
		int grande = 230;	
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
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 		
		for (int i=0; i< pedidos_piezas.size();i++){
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
		modelo_tabla_sdp_sin_numero_pedido.setDataVector(datosTabla_sdp_sin_numero_pedido, nombreColumnas_sdp_sin_numero_pedido);
		modelo_tabla_sdp_sin_numero_pedido.fireTableStructureChanged();	
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTE - SOLICITUD DE PEDIDO SIN NUMERO DE PEDIDO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReportesGestion.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Tabla Solicitud De Pedido Sin Numero Pedido //		
		pnSDPsinNumPedido = new JPanel();
		pnSDPsinNumPedido.setBounds(0, 0, 1274, 681);
		contentPane.add(pnSDPsinNumPedido);
		pnSDPsinNumPedido.setLayout(null);
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
		scrollPane_sdp_sin_numero.setBounds(5, 160, 1255, 510);
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

	protected void filtrarPorIntervaloSDPSN() {
		if(dC_FI_PSNP.getDate()!=null && dC_FF_PSNP.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = new GregorianCalendar();
			c.setTime(dC_FI_PSNP.getDate());
			c.add(c.DAY_OF_MONTH, -1);	
			datosTabla_sdp_sin_numero_pedido = new Vector<Vector<String>>();
			for (int i=0; i< pedidos_piezas.size();i++){				
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
		modelo_tabla_sdp_sin_numero_pedido.setDataVector(datosTabla_sdp_sin_numero_pedido, nombreColumnas_sdp_sin_numero_pedido);
		modelo_tabla_sdp_sin_numero_pedido.fireTableStructureChanged();
		for(int i = 0; i < tabla_SDPSN.getColumnCount(); i++) {
			tabla_SDPSN.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
			tabla_SDPSN.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
		}
	}

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
		modelo_tabla_sdp_sin_numero_pedido.setDataVector(datosTabla_sdp_sin_numero_pedido, nombreColumnas_sdp_sin_numero_pedido);
		modelo_tabla_sdp_sin_numero_pedido.fireTableStructureChanged();
		for(int i = 0; i < tabla_SDPSN.getColumnCount(); i++) {
			tabla_SDPSN.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
			tabla_SDPSN.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
		}
	}

	@SuppressWarnings("deprecation")
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
		modelo_tabla_sdp_sin_numero_pedido.setDataVector(datosTabla_sdp_sin_numero_pedido, nombreColumnas_sdp_sin_numero_pedido);
		modelo_tabla_sdp_sin_numero_pedido.fireTableStructureChanged();
		for(int i = 0; i < tabla_SDPSN.getColumnCount(); i++) {
			tabla_SDPSN.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
			tabla_SDPSN.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
		}	
	}

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
		modelo_tabla_sdp_sin_numero_pedido.setDataVector(datosTabla_sdp_sin_numero_pedido, nombreColumnas_sdp_sin_numero_pedido);
		modelo_tabla_sdp_sin_numero_pedido.fireTableStructureChanged();
		for(int i = 0; i < tabla_SDPSN.getColumnCount(); i++) {
			tabla_SDPSN.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
			tabla_SDPSN.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_sdp_sin_numero_pedido.elementAt(i));
		}
	}
}
