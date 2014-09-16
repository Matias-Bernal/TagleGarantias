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

public class GUIReporteDiasFechaRecursoYCierreOrden extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private JPanel contentPane;
	private JPanel pnDiasDesdeFRecursoFCierreOT;
	private DefaultTableModel modelo_tabla_dias_desde_fcierre_fturno;
	private JScrollPane scrollPane_dias_desde_frecurso_fcierre;
	private JTable tabla_DDFRFC;
	private DefaultTableModel modelo_tabla_dias_desde_frecurso_fcierre;
	private Vector<Vector<String>> datosTabla_dias_desde_frecurso_fcierre;
	private Vector<String> nombreColumnas_tabla_dias_desde_frecurso_fcierre;
	private Vector<Integer> anchos_tabla_dias_desde_frecurso_fcierre;
	private JRadioButton rB_Intervalo_DDFRFC;
	private JRadioButton rB_Hoy_DDFRFC;
	private JRadioButton rB_USemana_DDFRFC;
	private JRadioButton rB_Mes_DDFRFC;
	private JRadioButton rB_MAnterior_DDFRFC;
	private JRadioButton rB_Anio_DDFRFC;
	private JDateChooser dC_FI_DDFRFC;
	private JButton btn_clear_FIDDFRFC;
	private JDateChooser dC_FF_DDFRFC;
	private JButton btn_clear_FFDDFRFC;
	private JButton btn_filtrar_DDFRFC;
	private JButton btnExportarTabla_DDFRFC;

	//tabla 
	public GUIReporteDiasFechaRecursoYCierreOrden(MediadorReportes mediadorReporte) {
		setMediador(mediadorReporte);
		cargarDatos();
		initialize();
	}

	@SuppressWarnings({ "deprecation" })
	private void cargarDatos() {
		Vector<Pedido_PiezaDTO> pedidos_piezas = mediador.obtenerPedido_Piezas();
		int chico = 100;
		int mediano = 150;
		int grande = 230;
		modelo_tabla_dias_desde_frecurso_fcierre = new DefaultTableModel();
		nombreColumnas_tabla_dias_desde_frecurso_fcierre = new Vector<String> ();
		anchos_tabla_dias_desde_frecurso_fcierre = new Vector<Integer>();
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("ID Pedido");//0
		anchos_tabla_dias_desde_frecurso_fcierre.add(75);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Numero Pedido");//1
		anchos_tabla_dias_desde_frecurso_fcierre.add(chico);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Numero Pieza");//2
		anchos_tabla_dias_desde_frecurso_fcierre.add(chico);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Descripcion");//3
		anchos_tabla_dias_desde_frecurso_fcierre.add(mediano);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Numero Orden");//4
		anchos_tabla_dias_desde_frecurso_fcierre.add(chico);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("VIN");//5
		anchos_tabla_dias_desde_frecurso_fcierre.add(130);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Registrante");//6
		anchos_tabla_dias_desde_frecurso_fcierre.add(grande);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Fecha Solicitud Pedido");//7
		anchos_tabla_dias_desde_frecurso_fcierre.add(mediano);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Fecha Recurso");//8
		anchos_tabla_dias_desde_frecurso_fcierre.add(mediano);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Fecha Cierre Orden");//9
		anchos_tabla_dias_desde_frecurso_fcierre.add(mediano);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Dias Desde Recurso a Cierre Orden");//
		anchos_tabla_dias_desde_frecurso_fcierre.add(grande);
		datosTabla_dias_desde_frecurso_fcierre = new Vector<Vector<String>>();
	
		for (int i=0; i< pedidos_piezas.size();i++){
			if(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getFecha_cierre()!=null && pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden()!=null && pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getRecurso()!=null && pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getRecurso().getFecha_recurso()!=null ){
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
				java.sql.Date frecurso = null;
				if (pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getRecurso().getFecha_recurso()!=null){
					frecurso = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getRecurso().getFecha_recurso().getTime());
					row.add(format2.format(frecurso));//Fecha recurso
				}else{
					row.add("");
				}
				java.sql.Date fcierre = null;
				if (pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getFecha_cierre()!=null){
					fcierre = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getFecha_cierre().getTime());
					row.add(format2.format(fcierre));//Fecha Cierre
				}else{
					row.add("");
				}
				if(frecurso!=null && fcierre!=null){
					final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
					Calendar calendar = new GregorianCalendar(frecurso.getYear(), frecurso.getMonth()-1, frecurso.getDay());
					long diferencia = ( fcierre.getTime() - frecurso.getTime() )/MILLSECS_PER_DAY;
					row.add(String.valueOf(diferencia));
				}else{
					row.add("");
				}	
				datosTabla_dias_desde_frecurso_fcierre.add(row);
			}

		}
		modelo_tabla_dias_desde_frecurso_fcierre.setDataVector(datosTabla_dias_desde_frecurso_fcierre, nombreColumnas_tabla_dias_desde_frecurso_fcierre);
		modelo_tabla_dias_desde_frecurso_fcierre.fireTableStructureChanged();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTE - DIAS DESDE FECHA RECURSO Y FECHA CIERRE ORDEN");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReporteDiasFechaRecursoYCierreOrden.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

    	pnDiasDesdeFRecursoFCierreOT = new JPanel();
    	pnDiasDesdeFRecursoFCierreOT.setBounds(0, 0, 1274, 681);
    	contentPane.add(pnDiasDesdeFRecursoFCierreOT);
    	pnDiasDesdeFRecursoFCierreOT.setLayout(null);
    	modelo_tabla_dias_desde_frecurso_fcierre = new DefaultTableModel(datosTabla_dias_desde_frecurso_fcierre, nombreColumnas_tabla_dias_desde_frecurso_fcierre);
    	tabla_DDFRFC = new JTable(modelo_tabla_dias_desde_frecurso_fcierre) {
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
    	TableRowSorter<TableModel> ordenador_tabla_dias_desde_frecurso_fcierre = new TableRowSorter<TableModel>(modelo_tabla_dias_desde_fcierre_fturno);
		for(int i = 0; i < tabla_DDFRFC.getColumnCount(); i++) {
			tabla_DDFRFC.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_dias_desde_frecurso_fcierre.elementAt(i));
			tabla_DDFRFC.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_dias_desde_frecurso_fcierre.elementAt(i));
		}
		//se crea instancia a clase FormatoTable y se indica columna patron
		FormatoTablaDiasDesdeFTrunoFCierre frfc = new FormatoTablaDiasDesdeFTrunoFCierre(10);
    	tabla_DDFRFC.setRowSorter(ordenador_tabla_dias_desde_frecurso_fcierre);
    	tabla_DDFRFC.getTableHeader().setReorderingAllowed(false);
    	tabla_DDFRFC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	tabla_DDFRFC.setDefaultRenderer (Object.class, frfc );
    	scrollPane_dias_desde_frecurso_fcierre= new JScrollPane(tabla_DDFRFC);
    	scrollPane_dias_desde_frecurso_fcierre.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	scrollPane_dias_desde_frecurso_fcierre.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    	scrollPane_dias_desde_frecurso_fcierre.setBounds(5, 160, 1255, 510);
    	pnDiasDesdeFRecursoFCierreOT.add(scrollPane_dias_desde_frecurso_fcierre);
    	tabla_DDFRFC.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
    	rB_Intervalo_DDFRFC = new JRadioButton("Intervalo de Fechas");
    	rB_Intervalo_DDFRFC.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Intervalo_DDFRFC.isSelected()){
    				dC_FF_DDFRFC.setEnabled(true);
    				dC_FI_DDFRFC.setEnabled(true);
    				btn_filtrar_DDFRFC.setEnabled(true);
    				
    				rB_Hoy_DDFRFC.setSelected(false);
    				rB_USemana_DDFRFC.setSelected(false);
    				rB_Mes_DDFRFC.setSelected(false);
    				rB_MAnterior_DDFRFC.setSelected(false);
    				rB_Anio_DDFRFC.setSelected(false);
    			}else{
    				dC_FF_DDFRFC.setEnabled(false);
    				dC_FI_DDFRFC.setEnabled(false);
    				btn_filtrar_DDFRFC.setEnabled(false);
    			}
    		}
    	});
    	rB_Intervalo_DDFRFC.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
    	rB_Intervalo_DDFRFC.setBounds(5, 10, 150, 20);
    	pnDiasDesdeFRecursoFCierreOT.add(rB_Intervalo_DDFRFC);
    	
    	rB_Hoy_DDFRFC = new JRadioButton("Hoy");
    	rB_Hoy_DDFRFC.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Hoy_DDFRFC.isSelected()){
    				rB_Intervalo_DDFRFC.setSelected(false);
    				dC_FF_DDFRFC.setEnabled(false);
    				dC_FI_DDFRFC.setEnabled(false);
    				rB_USemana_DDFRFC.setSelected(false);
    				rB_Mes_DDFRFC.setSelected(false);
    				rB_MAnterior_DDFRFC.setSelected(false);
    				rB_Anio_DDFRFC.setSelected(false);
    				filtrarHoyDDFRFC();
    			}
    		}
    	});
    	rB_Hoy_DDFRFC.setToolTipText("Piezas llegadas el dia de hoy.");
    	rB_Hoy_DDFRFC.setBounds(5, 35, 150, 20);
    	pnDiasDesdeFRecursoFCierreOT.add(rB_Hoy_DDFRFC);
    	
    	rB_USemana_DDFRFC = new JRadioButton("Ultima Semana");
    	rB_USemana_DDFRFC.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_USemana_DDFRFC.isSelected()){
    				rB_Intervalo_DDFRFC.setSelected(false);
    				dC_FF_DDFRFC.setEnabled(false);
    				dC_FI_DDFRFC.setEnabled(false);
    				rB_Hoy_DDFRFC.setSelected(false);
    				rB_Mes_DDFRFC.setSelected(false);
    				rB_MAnterior_DDFRFC.setSelected(false);
    				rB_Anio_DDFRFC.setSelected(false);
    				filtrarUSemanaDDFRFC();
    			}
    		}
    	});
    	rB_USemana_DDFRFC.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
    	rB_USemana_DDFRFC.setBounds(5, 60, 150, 20);
    	pnDiasDesdeFRecursoFCierreOT.add(rB_USemana_DDFRFC);
    	
    	rB_Mes_DDFRFC = new JRadioButton("Este Mes");
    	rB_Mes_DDFRFC.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Mes_DDFRFC.isSelected()){
    				rB_Intervalo_DDFRFC.setSelected(false);
    				dC_FF_DDFRFC.setEnabled(false);
    				dC_FI_DDFRFC.setEnabled(false);
    				rB_Hoy_DDFRFC.setSelected(false);
    				rB_USemana_DDFRFC.setSelected(false);
    				rB_MAnterior_DDFRFC.setSelected(false);
    				rB_Anio_DDFRFC.setSelected(false);
    				filtrarMesDDFRFC();
    			}
    		}
    	});
    	rB_Mes_DDFRFC.setToolTipText("Piezas llegadas en el mes corriente.");
    	rB_Mes_DDFRFC.setBounds(5, 85, 150, 20);
    	pnDiasDesdeFRecursoFCierreOT.add(rB_Mes_DDFRFC);
    	
    	rB_MAnterior_DDFRFC = new JRadioButton("Mes Anterior");
    	rB_MAnterior_DDFRFC.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_MAnterior_DDFRFC.isSelected()){
    				rB_Intervalo_DDFRFC.setSelected(false);
    				dC_FF_DDFRFC.setEnabled(false);
    				dC_FI_DDFRFC.setEnabled(false);
    				rB_Hoy_DDFRFC.setSelected(false);
    				rB_USemana_DDFRFC.setSelected(false);
    				rB_Mes_DDFRFC.setSelected(false);
    				rB_Anio_DDFRFC.setSelected(false);
    				filtrarMAnteriorDDFRFC();
    			}
    		}
    	});
    	rB_MAnterior_DDFRFC.setBounds(5, 110, 150, 20);
    	pnDiasDesdeFRecursoFCierreOT.add(rB_MAnterior_DDFRFC);
    	
    	rB_Anio_DDFRFC = new JRadioButton("Ultimo A\u00F1o");
    	rB_Anio_DDFRFC.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Anio_DDFRFC.isSelected()){
    				rB_Intervalo_DDFRFC.setSelected(false);
    				dC_FF_DDFRFC.setEnabled(false);
    				dC_FI_DDFRFC.setEnabled(false);
    				rB_Hoy_DDFRFC.setSelected(false);
    				rB_USemana_DDFRFC.setSelected(false);
    				rB_Mes_DDFRFC.setSelected(false);
    				rB_MAnterior_DDFRFC.setSelected(false);
    				filtrarUAnioDDFRFC();
    			}
    		}
    	});
    	rB_Anio_DDFRFC.setBounds(5, 135, 150, 20);
    	pnDiasDesdeFRecursoFCierreOT.add(rB_Anio_DDFRFC);
    	
    	JLabel label_26 = new JLabel("Fecha Inicio");
    	label_26.setHorizontalAlignment(SwingConstants.CENTER);
    	label_26.setEnabled(false);
    	label_26.setBounds(161, 11, 120, 20);
    	pnDiasDesdeFRecursoFCierreOT.add(label_26);
    	
    	dC_FI_DDFRFC = new JDateChooser();
    	dC_FI_DDFRFC.setEnabled(false);
    	dC_FI_DDFRFC.setBounds(290, 10, 150, 20);
    	pnDiasDesdeFRecursoFCierreOT.add(dC_FI_DDFRFC);
    	
    	btn_clear_FIDDFRFC = new JButton("");
    	btn_clear_FIDDFRFC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FI_DDFRFC.getDate()!=null)
					dC_FI_DDFRFC.setDate(null);
			}
		});
    	btn_clear_FIDDFRFC.setIcon(new ImageIcon(GUIReporteDiasFechaRecursoYCierreOrden.class.getResource("/cliente/Resources/Icons/clear.png")));
    	btn_clear_FIDDFRFC.setBounds(450, 10, 25, 20);
    	pnDiasDesdeFRecursoFCierreOT.add(btn_clear_FIDDFRFC);
    	
    	JLabel label_27 = new JLabel("Fecha Fin");
    	label_27.setHorizontalAlignment(SwingConstants.CENTER);
    	label_27.setEnabled(false);
    	label_27.setBounds(486, 11, 120, 20);
    	pnDiasDesdeFRecursoFCierreOT.add(label_27);
    	
    	dC_FF_DDFRFC = new JDateChooser();
    	dC_FF_DDFRFC.setEnabled(false);
    	dC_FF_DDFRFC.setBounds(615, 10, 150, 20);
    	pnDiasDesdeFRecursoFCierreOT.add(dC_FF_DDFRFC);
    	
    	btn_clear_FFDDFRFC = new JButton("");
    	btn_clear_FFDDFRFC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_DDFRFC.getDate()!=null)
					dC_FF_DDFRFC.setDate(null);
			}
		});
    	btn_clear_FFDDFRFC.setIcon(new ImageIcon(GUIReporteDiasFechaRecursoYCierreOrden.class.getResource("/cliente/Resources/Icons/clear.png")));
    	btn_clear_FFDDFRFC.setBounds(775, 10, 25, 20);
    	pnDiasDesdeFRecursoFCierreOT.add(btn_clear_FFDDFRFC);
    	
    	btn_filtrar_DDFRFC = new JButton("Filtrar");
    	btn_filtrar_DDFRFC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarIntervaloDDFRFC();
			}
		});
    	btn_filtrar_DDFRFC.setEnabled(false);
    	btn_filtrar_DDFRFC.setBounds(810, 10, 110, 20);
    	pnDiasDesdeFRecursoFCierreOT.add(btn_filtrar_DDFRFC);
    	
    	btnExportarTabla_DDFRFC = new JButton("");
    	btnExportarTabla_DDFRFC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaDDFRFC();
			}
		});
    	btnExportarTabla_DDFRFC.setIcon(new ImageIcon(GUIReporteDiasFechaRecursoYCierreOrden.class.getResource("/cliente/Resources/Icons/formulario.png")));
    	btnExportarTabla_DDFRFC.setBounds(1230, 120, 32, 32);
    	pnDiasDesdeFRecursoFCierreOT.add(btnExportarTabla_DDFRFC);
	}

	protected void exportarTablaDDFRFC() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_DDFRFC, "Dias Desde Fecha Recurso a Fecha Cierre");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void filtrarIntervaloDDFRFC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioDDFRFC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorDDFRFC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesDDFRFC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaDDFRFC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyDDFRFC() {
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
