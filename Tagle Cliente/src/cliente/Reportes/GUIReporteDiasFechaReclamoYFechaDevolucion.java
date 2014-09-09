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

public class GUIReporteDiasFechaReclamoYFechaDevolucion extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private JPanel contentPane;
	private JPanel pnDiasDesdeFReclamoFDevolucion;
	private JScrollPane scrollPane_dias_desde_freclamo_fdevolucion;
	private DefaultTableModel modelo_tabla_dias_desde_freclamo_fdevolucion;
	private Vector<Vector<String>> datosTabla_dias_desde_freclamo_fdevolucion;
	private Vector<String> nombreColumnas_tabla_dias_desde_freclamo_fdevolucion;
	private JTable tabla_DDFRFD;
	private Vector<Integer> anchos_tabla_dias_desde_freclamo_fdevolucion;
	private JRadioButton rB_Intervalo_DDFRFD;
	private JRadioButton rB_Hoy_DDFRFD;
	private JRadioButton rB_USemana_DDFRFD;
	private JRadioButton rB_Mes_DDFRFD;
	private JRadioButton rB_MAnterior_DDFRFD;
	private JRadioButton rB_Anio_DDFRFD;
	private JDateChooser dC_FI_DDFRFD;
	private JButton btn_clear_FIDDFRFD;
	private JDateChooser dC_FF_DDFRFD;
	private JButton btn_clear_FFDDFRFD;
	private JButton btn_filtrar_DDFRFD;
	private JButton btnExportarTabla_DDFRFD;

	//tabla 
	public GUIReporteDiasFechaReclamoYFechaDevolucion(MediadorReportes mediadorReporte) {
		setMediador(mediadorReporte);
		cargarDatos();
		initialize();
	}

	private void cargarDatos() {
		Vector<Pedido_PiezaDTO> pedidos_piezas = mediador.obtenerPedido_Piezas();
		int chico = 100;
		int mediano = 150;
		int grande = 230;
		//TABLA DIAS DESDE FRECLAMO A FDEVOLUCION//
		modelo_tabla_dias_desde_freclamo_fdevolucion = new DefaultTableModel();
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion = new Vector<String> ();
		anchos_tabla_dias_desde_freclamo_fdevolucion = new Vector<Integer>();
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("ID Pedido");//0
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(75);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Numero Pedido");//1
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(chico);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Numero Pieza");//2
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(chico);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Descripcion");//3
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(mediano);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Numero Orden");//4
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(chico);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("VIN");//5
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(130);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Registrante");//6
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(grande);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Fecha Reclamo");//7
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(mediano);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Fecha Pedido a Fabrica");//8
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(mediano);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Fecha Recepcion de Fabrica");//9
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(mediano);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Fecha Devolucion a Fabrica");//10
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(mediano);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Dias Desde Fecha Reclamo a Fecha Devolucion");//11
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(grande);		
		datosTabla_dias_desde_freclamo_fdevolucion = new Vector<Vector<String>>();
		
		for (int i=0; i< pedidos_piezas.size();i++){
			if(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_reclamo()!=null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null && 
					pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){
				Vector<String> row = new Vector<String> ();
				row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
				row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
				row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
				row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
				row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
				row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
				row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
				java.sql.Date freclamo = null;
				if(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_reclamo()!=null){
					freclamo = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_reclamo().getTime());
					row.add(format2.format(freclamo));//Fecha Reclamo
				}else{
					row.add("");
				}			    
				if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
					java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
					row.add(format2.format(fsf));//Fecha Pedido Fabrica 
				}else{
					row.add("");
				}
				if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
					java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
					row.add(format2.format(frf));//Fecha Recepcion Fabrica 
				}else{
					row.add("");
				}
				java.sql.Date fdf = null;
				if (pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){
					fdf = new java.sql.Date(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().getTime());
					row.add(format2.format(fdf));//Fecha Devolucion Fabrica 
				}else{
					row.add("");
				}
				if(fdf!=null && freclamo!=null){
					final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
					@SuppressWarnings("deprecation")
					Calendar calendar = new GregorianCalendar(fdf.getYear(), fdf.getMonth()-1, fdf.getDay());
					long diferencia = (fdf.getTime() - freclamo.getTime())/MILLSECS_PER_DAY;
					row.add(String.valueOf(diferencia));
				}else{
					row.add("");
				}	
				datosTabla_dias_desde_freclamo_fdevolucion.add(row);
			}
		}
		modelo_tabla_dias_desde_freclamo_fdevolucion.setDataVector(datosTabla_dias_desde_freclamo_fdevolucion, nombreColumnas_tabla_dias_desde_freclamo_fdevolucion);
		modelo_tabla_dias_desde_freclamo_fdevolucion.fireTableStructureChanged();
		
	}
	
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTE - DIAS DESDE FECHA RECLAMO Y FECHA DEVOLUCION");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReporteDiasFechaReclamoYFechaDevolucion.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
    	pnDiasDesdeFReclamoFDevolucion = new JPanel();
    	pnDiasDesdeFReclamoFDevolucion.setBounds(0, 0, 1274, 681);
    	contentPane.add(pnDiasDesdeFReclamoFDevolucion);
    	pnDiasDesdeFReclamoFDevolucion.setLayout(null);
    	modelo_tabla_dias_desde_freclamo_fdevolucion = new DefaultTableModel(datosTabla_dias_desde_freclamo_fdevolucion, nombreColumnas_tabla_dias_desde_freclamo_fdevolucion);
    	tabla_DDFRFD = new JTable(modelo_tabla_dias_desde_freclamo_fdevolucion) {
    		private static final long serialVersionUID = 1L;
    		boolean[] columnEditables = new boolean[] {
    				false, false, false, false, false,
    				false, false, false,false, false,
    				false, false,false,false,false
    		};
    		public boolean isCellEditable(int row, int column) {
    			return columnEditables[column];
    		}
    	};
    	TableRowSorter<TableModel> ordenador_tabla_dias_desde_freclamo_fdevolucion = new TableRowSorter<TableModel>(modelo_tabla_dias_desde_freclamo_fdevolucion);
		for(int i = 0; i < tabla_DDFRFD.getColumnCount(); i++) {
			tabla_DDFRFD.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_dias_desde_freclamo_fdevolucion.elementAt(i));
			tabla_DDFRFD.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_dias_desde_freclamo_fdevolucion.elementAt(i));
		}
		//se crea instancia a clase FormatoTable y se indica columna patron
		FormatoTablaDiasDesdeFTrunoFCierre frfd = new FormatoTablaDiasDesdeFTrunoFCierre(11);
    	tabla_DDFRFD.setRowSorter(ordenador_tabla_dias_desde_freclamo_fdevolucion);
    	tabla_DDFRFD.getTableHeader().setReorderingAllowed(false);
    	tabla_DDFRFD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	tabla_DDFRFD.setDefaultRenderer (Object.class, frfd );
    	scrollPane_dias_desde_freclamo_fdevolucion= new JScrollPane(tabla_DDFRFD);
    	scrollPane_dias_desde_freclamo_fdevolucion.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	scrollPane_dias_desde_freclamo_fdevolucion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    	scrollPane_dias_desde_freclamo_fdevolucion.setBounds(5, 160, 1255, 510);
    	pnDiasDesdeFReclamoFDevolucion.add(scrollPane_dias_desde_freclamo_fdevolucion);
    	tabla_DDFRFD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
    	rB_Intervalo_DDFRFD = new JRadioButton("Intervalo de Fechas");
    	rB_Intervalo_DDFRFD.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Intervalo_DDFRFD.isSelected()){
    				dC_FF_DDFRFD.setEnabled(true);
    				dC_FI_DDFRFD.setEnabled(true);
    				btn_filtrar_DDFRFD.setEnabled(true);
    				
    				rB_Hoy_DDFRFD.setSelected(false);
    				rB_USemana_DDFRFD.setSelected(false);
    				rB_Mes_DDFRFD.setSelected(false);
    				rB_MAnterior_DDFRFD.setSelected(false);
    				rB_Anio_DDFRFD.setSelected(false);
    			}else{
    				dC_FF_DDFRFD.setEnabled(false);
    				dC_FI_DDFRFD.setEnabled(false);
    				btn_filtrar_DDFRFD.setEnabled(false);
    			}
    		}
    	});
    	rB_Intervalo_DDFRFD.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
    	rB_Intervalo_DDFRFD.setBounds(5, 10, 150, 20);
    	pnDiasDesdeFReclamoFDevolucion.add(rB_Intervalo_DDFRFD);
    	
    	rB_Hoy_DDFRFD = new JRadioButton("Hoy");
    	rB_Hoy_DDFRFD.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Hoy_DDFRFD.isSelected()){
    				rB_Intervalo_DDFRFD.setSelected(false);
    				dC_FF_DDFRFD.setEnabled(false);
    				dC_FI_DDFRFD.setEnabled(false);
    				rB_USemana_DDFRFD.setSelected(false);
    				rB_Mes_DDFRFD.setSelected(false);
    				rB_MAnterior_DDFRFD.setSelected(false);
    				rB_Anio_DDFRFD.setSelected(false);
    				filtrarHoyDDFRFD();
    			}
    		}
    	});
    	rB_Hoy_DDFRFD.setToolTipText("Piezas llegadas el dia de hoy.");
    	rB_Hoy_DDFRFD.setBounds(5, 35, 150, 20);
    	pnDiasDesdeFReclamoFDevolucion.add(rB_Hoy_DDFRFD);
    	
    	rB_USemana_DDFRFD = new JRadioButton("Ultima Semana");
    	rB_USemana_DDFRFD.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_USemana_DDFRFD.isSelected()){
    				rB_Intervalo_DDFRFD.setSelected(false);
    				dC_FF_DDFRFD.setEnabled(false);
    				dC_FI_DDFRFD.setEnabled(false);
    				rB_Hoy_DDFRFD.setSelected(false);
    				rB_Mes_DDFRFD.setSelected(false);
    				rB_MAnterior_DDFRFD.setSelected(false);
    				rB_Anio_DDFRFD.setSelected(false);
    				filtrarUSemanaDDFRFD();
    			}
    		}
    	});
    	rB_USemana_DDFRFD.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
    	rB_USemana_DDFRFD.setBounds(5, 60, 150, 20);
    	pnDiasDesdeFReclamoFDevolucion.add(rB_USemana_DDFRFD);
    	
    	rB_Mes_DDFRFD = new JRadioButton("Este Mes");
    	rB_Mes_DDFRFD.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Mes_DDFRFD.isSelected()){
    				rB_Intervalo_DDFRFD.setSelected(false);
    				dC_FF_DDFRFD.setEnabled(false);
    				dC_FI_DDFRFD.setEnabled(false);
    				rB_Hoy_DDFRFD.setSelected(false);
    				rB_USemana_DDFRFD.setSelected(false);
    				rB_MAnterior_DDFRFD.setSelected(false);
    				rB_Anio_DDFRFD.setSelected(false);
    				filtrarMesDDFRFD();
    			}
    		}
    	});
    	rB_Mes_DDFRFD.setToolTipText("Piezas llegadas en el mes corriente.");
    	rB_Mes_DDFRFD.setBounds(5, 85, 150, 20);
    	pnDiasDesdeFReclamoFDevolucion.add(rB_Mes_DDFRFD);
    	
    	rB_MAnterior_DDFRFD = new JRadioButton("Mes Anterior");
    	rB_MAnterior_DDFRFD.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_MAnterior_DDFRFD.isSelected()){
    				rB_Intervalo_DDFRFD.setSelected(false);
    				dC_FF_DDFRFD.setEnabled(false);
    				dC_FI_DDFRFD.setEnabled(false);
    				rB_Hoy_DDFRFD.setSelected(false);
    				rB_USemana_DDFRFD.setSelected(false);
    				rB_Mes_DDFRFD.setSelected(false);
    				rB_Anio_DDFRFD.setSelected(false);
    				filtrarMAnteriorDDFRFD();
    			}
    		}
    	});
    	rB_MAnterior_DDFRFD.setBounds(5, 110, 150, 20);
    	pnDiasDesdeFReclamoFDevolucion.add(rB_MAnterior_DDFRFD);
    	
    	rB_Anio_DDFRFD = new JRadioButton("Ultimo A\u00F1o");
    	rB_Anio_DDFRFD.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Anio_DDFRFD.isSelected()){
    				rB_Intervalo_DDFRFD.setSelected(false);
    				dC_FF_DDFRFD.setEnabled(false);
    				dC_FI_DDFRFD.setEnabled(false);
    				rB_Hoy_DDFRFD.setSelected(false);
    				rB_USemana_DDFRFD.setSelected(false);
    				rB_Mes_DDFRFD.setSelected(false);
    				rB_MAnterior_DDFRFD.setSelected(false);
    				filtrarUAnioDDFRFD();
    			}
    		}
    	});
    	rB_Anio_DDFRFD.setBounds(5, 135, 150, 20);
    	pnDiasDesdeFReclamoFDevolucion.add(rB_Anio_DDFRFD);
    	
    	JLabel label_28 = new JLabel("Fecha Inicio");
    	label_28.setHorizontalAlignment(SwingConstants.CENTER);
    	label_28.setEnabled(false);
    	label_28.setBounds(160, 11, 120, 20);
    	pnDiasDesdeFReclamoFDevolucion.add(label_28);
    	
    	dC_FI_DDFRFD = new JDateChooser();
    	dC_FI_DDFRFD.setEnabled(false);
    	dC_FI_DDFRFD.setBounds(290, 10, 150, 20);
    	pnDiasDesdeFReclamoFDevolucion.add(dC_FI_DDFRFD);
    	
    	btn_clear_FIDDFRFD = new JButton("");
    	btn_clear_FIDDFRFD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FI_DDFRFD.getDate()!=null)
					dC_FI_DDFRFD.setDate(null);
			}
		});
    	btn_clear_FIDDFRFD.setIcon(new ImageIcon(GUIReporteDiasFechaReclamoYFechaDevolucion.class.getResource("/cliente/Resources/Icons/clear.png")));
    	btn_clear_FIDDFRFD.setBounds(450, 10, 25, 20);
    	pnDiasDesdeFReclamoFDevolucion.add(btn_clear_FIDDFRFD);
    	
    	JLabel label_29 = new JLabel("Fecha Fin");
    	label_29.setHorizontalAlignment(SwingConstants.CENTER);
    	label_29.setEnabled(false);
    	label_29.setBounds(485, 11, 120, 20);
    	pnDiasDesdeFReclamoFDevolucion.add(label_29);
    	
    	dC_FF_DDFRFD = new JDateChooser();
    	dC_FF_DDFRFD.setEnabled(false);
    	dC_FF_DDFRFD.setBounds(615, 10, 150, 20);
    	pnDiasDesdeFReclamoFDevolucion.add(dC_FF_DDFRFD);
    	
    	btn_clear_FFDDFRFD = new JButton("");
    	btn_clear_FFDDFRFD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_DDFRFD.getDate()!=null)
					dC_FF_DDFRFD.setDate(null);
			}
		});
    	btn_clear_FFDDFRFD.setIcon(new ImageIcon(GUIReporteDiasFechaReclamoYFechaDevolucion.class.getResource("/cliente/Resources/Icons/clear.png")));
    	btn_clear_FFDDFRFD.setBounds(775, 10, 25, 20);
    	pnDiasDesdeFReclamoFDevolucion.add(btn_clear_FFDDFRFD);
    	
    	btn_filtrar_DDFRFD = new JButton("Filtrar");
    	btn_filtrar_DDFRFD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtrarIntervaloDDFRFD();
			}
		});
    	btn_filtrar_DDFRFD.setEnabled(false);
    	btn_filtrar_DDFRFD.setBounds(810, 10, 110, 20);
    	pnDiasDesdeFReclamoFDevolucion.add(btn_filtrar_DDFRFD);
    	
    	btnExportarTabla_DDFRFD = new JButton("");
    	btnExportarTabla_DDFRFD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaDDFRFD();
			}
		});
    	btnExportarTabla_DDFRFD.setIcon(new ImageIcon(GUIReporteDiasFechaReclamoYFechaDevolucion.class.getResource("/cliente/Resources/Icons/formulario.png")));
    	btnExportarTabla_DDFRFD.setBounds(1230, 120, 32, 32);
    	pnDiasDesdeFReclamoFDevolucion.add(btnExportarTabla_DDFRFD);
	}

	protected void exportarTablaDDFRFD() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_DDFRFD, "Dias Desde Fecha Reclamo a Fecha Devolucion a Fabrica");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void filtrarIntervaloDDFRFD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioDDFRFD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorDDFRFD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesDDFRFD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaDDFRFD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyDDFRFD() {
		// TODO Auto-generated method stub
		
	}

	
	//gettes and setters
	public MediadorReportes getMediador() {
		return mediador;
	}

	public void setMediador(MediadorReportes mediador) {
		this.mediador = mediador;
	}

}
