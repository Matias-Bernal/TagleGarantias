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

public class GUIReporteDiasDesdeCierreOrdenYTurno extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private JPanel contentPane;
	private JPanel pnDiasDesdeFCierreFTurno;
	private JScrollPane scrollPane_dias_trasncurridos_desde_fcierre_fturno;
	private DefaultTableModel modelo_tabla_dias_desde_fcierre_fturno;
	private Vector<String> nombreColumnas_tabla_dias_desde_fcierre_fturno;
	private Vector<Vector<String>> datosTabla_dias_desde_fcierre_fturno;
	private Vector<Integer> anchos_tabla_dias_desde_fcierre_fturno;
	private JTable tabla_DDFCT;
	private JRadioButton rB_Intervalo_DDFCFT;
	private JRadioButton rB_Hoy_DDFCFT;
	private JRadioButton rB_USemana_DDFCFT;
	private JRadioButton rB_Mes_DDFCFT;
	private JRadioButton rB_MAnterior_DDFCFT;
	private JRadioButton rB_Anio_DDFCFT;
	private JDateChooser dC_FI_DDFCFT;
	private JButton btn_clear_FIDDFCFT;
	private JDateChooser dC_FF_DDFCFT;
	private JButton btn_clear_FFDDFCFT;
	private JButton btn_filtrar_DDFCFT;
	private JButton btnExportarTablaDDFCFT;

	//tabla 
	public GUIReporteDiasDesdeCierreOrdenYTurno(MediadorReportes mediadorReporte) {
		setMediador(mediadorReporte);
		cargarDatos();
		initialize();
	}

	private void cargarDatos() {
		Vector<Pedido_PiezaDTO> pedidos_piezas = mediador.obtenerPedido_Piezas();
		int chico = 100;
		int mediano = 150;
		int grande = 230;
		modelo_tabla_dias_desde_fcierre_fturno = new DefaultTableModel();
		nombreColumnas_tabla_dias_desde_fcierre_fturno = new Vector<String> ();
		anchos_tabla_dias_desde_fcierre_fturno = new Vector<Integer>();
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("ID Pedido");//0
		anchos_tabla_dias_desde_fcierre_fturno.add(75);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Numero Pedido");//1
		anchos_tabla_dias_desde_fcierre_fturno.add(chico);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Numero Pieza");//2
		anchos_tabla_dias_desde_fcierre_fturno.add(chico);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Descripcion");//3
		anchos_tabla_dias_desde_fcierre_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Numero Orden");//4
		anchos_tabla_dias_desde_fcierre_fturno.add(chico);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("VIN");//5
		anchos_tabla_dias_desde_fcierre_fturno.add(130);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Registrante");//6
		anchos_tabla_dias_desde_fcierre_fturno.add(grande);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Fecha Solicitud Pedido");//7
		anchos_tabla_dias_desde_fcierre_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Fecha Turno");//8
		anchos_tabla_dias_desde_fcierre_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Fecha Cierre Orden");//9
		anchos_tabla_dias_desde_fcierre_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Dias Desde Turno a Cierre Orden");//10
		anchos_tabla_dias_desde_fcierre_fturno.add(grande);		
		datosTabla_dias_desde_fcierre_fturno = new Vector<Vector<String>>();
		for (int i=0; i< pedidos_piezas.size();i++){
			if(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getFecha_cierre()!=null && (pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno()!=null)){
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
				java.sql.Date fturno = null;
				if (pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno()!=null){
					fturno = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno().getTime());
					row.add(format2.format(fturno));//Fecha Turno
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
				if(fturno!=null && fcierre!=null){
					final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
					@SuppressWarnings("deprecation")
					Calendar calendar = new GregorianCalendar(fturno.getYear(), fturno.getMonth()-1, fturno.getDay());
					long diferencia = ( fcierre.getTime() - fturno.getTime() )/MILLSECS_PER_DAY;
					row.add(String.valueOf(diferencia));
				}else{
					row.add("");
				}	
				datosTabla_dias_desde_fcierre_fturno.add(row);
			}
		}
		modelo_tabla_dias_desde_fcierre_fturno.setDataVector(datosTabla_dias_desde_fcierre_fturno, nombreColumnas_tabla_dias_desde_fcierre_fturno);
		modelo_tabla_dias_desde_fcierre_fturno.fireTableStructureChanged();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTE - DIAS DESDE FECHA CIERRE ORDEN Y FECHA TURNO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReporteDiasDesdeCierreOrdenYTurno.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
    	pnDiasDesdeFCierreFTurno = new JPanel();
    	pnDiasDesdeFCierreFTurno.setBounds(0, 0, 1274, 681);
    	contentPane.add(pnDiasDesdeFCierreFTurno);
    	pnDiasDesdeFCierreFTurno.setLayout(null);
    	modelo_tabla_dias_desde_fcierre_fturno = new DefaultTableModel(datosTabla_dias_desde_fcierre_fturno, nombreColumnas_tabla_dias_desde_fcierre_fturno);
    	//DIAS DESDE FECHA CIERRE Y FECHA TURNO//
    	tabla_DDFCT = new JTable(modelo_tabla_dias_desde_fcierre_fturno) {
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
    	TableRowSorter<TableModel> ordenador_tabla_dias_desde_fcierre_fturno = new TableRowSorter<TableModel>(modelo_tabla_dias_desde_fcierre_fturno);
		for(int i = 0; i < tabla_DDFCT.getColumnCount(); i++) {
			tabla_DDFCT.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_dias_desde_fcierre_fturno.elementAt(i));
			tabla_DDFCT.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_dias_desde_fcierre_fturno.elementAt(i));
		}
		//se crea instancia a clase FormatoTable y se indica columna patron
		FormatoTablaDiasDesdeFTrunoFCierre fcft = new FormatoTablaDiasDesdeFTrunoFCierre(10);
    	tabla_DDFCT.setRowSorter(ordenador_tabla_dias_desde_fcierre_fturno);
    	tabla_DDFCT.getTableHeader().setReorderingAllowed(false);
    	tabla_DDFCT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	tabla_DDFCT.setDefaultRenderer (Object.class, fcft );
    	scrollPane_dias_trasncurridos_desde_fcierre_fturno= new JScrollPane(tabla_DDFCT);
    	scrollPane_dias_trasncurridos_desde_fcierre_fturno.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	scrollPane_dias_trasncurridos_desde_fcierre_fturno.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    	scrollPane_dias_trasncurridos_desde_fcierre_fturno.setBounds(5, 160, 1255, 510);
    	pnDiasDesdeFCierreFTurno.add(scrollPane_dias_trasncurridos_desde_fcierre_fturno);
    	tabla_DDFCT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
    	rB_Intervalo_DDFCFT = new JRadioButton("Intervalo de Fechas");
    	rB_Intervalo_DDFCFT.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Intervalo_DDFCFT.isSelected()){
    				dC_FF_DDFCFT.setEnabled(true);
    				dC_FI_DDFCFT.setEnabled(true);
    				btn_filtrar_DDFCFT.setEnabled(true);
    				rB_Hoy_DDFCFT.setSelected(false);
    				rB_USemana_DDFCFT.setSelected(false);
    				rB_Mes_DDFCFT.setSelected(false);
    				rB_MAnterior_DDFCFT.setSelected(false);
    				rB_Anio_DDFCFT.setSelected(false);
    			}else{
    				dC_FF_DDFCFT.setEnabled(false);
    				dC_FI_DDFCFT.setEnabled(false);
    				btn_filtrar_DDFCFT.setEnabled(false);
    			}
    		}
    	});
    	rB_Intervalo_DDFCFT.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
    	rB_Intervalo_DDFCFT.setBounds(5, 10, 150, 20);
    	pnDiasDesdeFCierreFTurno.add(rB_Intervalo_DDFCFT);
    	
    	rB_Hoy_DDFCFT = new JRadioButton("Hoy");
    	rB_Hoy_DDFCFT.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Hoy_DDFCFT.isSelected()){
    				rB_Intervalo_DDFCFT.setSelected(false);
    				dC_FF_DDFCFT.setEnabled(false);
    				dC_FI_DDFCFT.setEnabled(false);
    				rB_USemana_DDFCFT.setSelected(false);
    				rB_Mes_DDFCFT.setSelected(false);
    				rB_MAnterior_DDFCFT.setSelected(false);
    				rB_Anio_DDFCFT.setSelected(false);
    				filtrarHoyDDFCFT();
    			}
    		}
    	});
    	rB_Hoy_DDFCFT.setToolTipText("Piezas llegadas el dia de hoy.");
    	rB_Hoy_DDFCFT.setBounds(5, 35, 150, 20);
    	pnDiasDesdeFCierreFTurno.add(rB_Hoy_DDFCFT);
    	
    	rB_USemana_DDFCFT = new JRadioButton("Ultima Semana");
    	rB_USemana_DDFCFT.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_USemana_DDFCFT.isSelected()){
    				rB_Intervalo_DDFCFT.setSelected(false);
    				dC_FF_DDFCFT.setEnabled(false);
    				dC_FI_DDFCFT.setEnabled(false);
    				rB_Hoy_DDFCFT.setSelected(false);
    				rB_Mes_DDFCFT.setSelected(false);
    				rB_MAnterior_DDFCFT.setSelected(false);
    				rB_Anio_DDFCFT.setSelected(false);
    				filtrarUSemanaDDFCFT();
    			}
    		}
    	});
    	rB_USemana_DDFCFT.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
    	rB_USemana_DDFCFT.setBounds(5, 60, 150, 20);
    	pnDiasDesdeFCierreFTurno.add(rB_USemana_DDFCFT);
    	
    	rB_Mes_DDFCFT = new JRadioButton("Este Mes");
    	rB_Mes_DDFCFT.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Mes_DDFCFT.isSelected()){
    				rB_Intervalo_DDFCFT.setSelected(false);
    				dC_FF_DDFCFT.setEnabled(false);
    				dC_FI_DDFCFT.setEnabled(false);
    				rB_Hoy_DDFCFT.setSelected(false);
    				rB_USemana_DDFCFT.setSelected(false);
    				rB_MAnterior_DDFCFT.setSelected(false);
    				rB_Anio_DDFCFT.setSelected(false);
    				filtrarMesDDFCFT();
    			}
    		}
    	});
    	rB_Mes_DDFCFT.setToolTipText("Piezas llegadas en el mes corriente.");
    	rB_Mes_DDFCFT.setBounds(5, 85, 150, 20);
    	pnDiasDesdeFCierreFTurno.add(rB_Mes_DDFCFT);
    	
    	rB_MAnterior_DDFCFT = new JRadioButton("Mes Anterior");
    	rB_MAnterior_DDFCFT.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_MAnterior_DDFCFT.isSelected()){
    				rB_Intervalo_DDFCFT.setSelected(false);
    				dC_FF_DDFCFT.setEnabled(false);
    				dC_FI_DDFCFT.setEnabled(false);
    				rB_Hoy_DDFCFT.setSelected(false);
    				rB_USemana_DDFCFT.setSelected(false);
    				rB_Mes_DDFCFT.setSelected(false);
    				rB_Anio_DDFCFT.setSelected(false);
    				filtrarMAnteriorDDFCFT();
    			}
    		}
    	});
    	rB_MAnterior_DDFCFT.setBounds(5, 110, 150, 20);
    	pnDiasDesdeFCierreFTurno.add(rB_MAnterior_DDFCFT);
    	
    	rB_Anio_DDFCFT = new JRadioButton("Ultimo A\u00F1o");
    	rB_Anio_DDFCFT.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Anio_DDFCFT.isSelected()){
    				rB_Intervalo_DDFCFT.setSelected(false);
    				dC_FF_DDFCFT.setEnabled(false);
    				dC_FI_DDFCFT.setEnabled(false);
    				rB_Hoy_DDFCFT.setSelected(false);
    				rB_USemana_DDFCFT.setSelected(false);
    				rB_Mes_DDFCFT.setSelected(false);
    				rB_MAnterior_DDFCFT.setSelected(false);
    				filtrarUAnioDDFCFT();
    			}
    		}
    	});
    	rB_Anio_DDFCFT.setBounds(5, 135, 150, 20);
    	pnDiasDesdeFCierreFTurno.add(rB_Anio_DDFCFT);
    	
    	JLabel label_24 = new JLabel("Fecha Inicio");
    	label_24.setHorizontalAlignment(SwingConstants.CENTER);
    	label_24.setEnabled(false);
    	label_24.setBounds(161, 11, 120, 20);
    	pnDiasDesdeFCierreFTurno.add(label_24);
    	
    	dC_FI_DDFCFT = new JDateChooser();
    	dC_FI_DDFCFT.setEnabled(false);
    	dC_FI_DDFCFT.setBounds(290, 10, 150, 20);
    	pnDiasDesdeFCierreFTurno.add(dC_FI_DDFCFT);
    	
    	btn_clear_FIDDFCFT = new JButton("");
    	btn_clear_FIDDFCFT.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(dC_FI_DDFCFT.getDate()!=null)
    				dC_FI_DDFCFT.setDate(null);
    		}
    	});
    	btn_clear_FIDDFCFT.setIcon(new ImageIcon(GUIReporteDiasDesdeCierreOrdenYTurno.class.getResource("/cliente/Resources/Icons/clear.png")));
    	btn_clear_FIDDFCFT.setBounds(450, 10, 25, 20);
    	pnDiasDesdeFCierreFTurno.add(btn_clear_FIDDFCFT);
    	
    	JLabel label_25 = new JLabel("Fecha Fin");
    	label_25.setHorizontalAlignment(SwingConstants.CENTER);
    	label_25.setEnabled(false);
    	label_25.setBounds(486, 11, 120, 20);
    	pnDiasDesdeFCierreFTurno.add(label_25);
    	
    	dC_FF_DDFCFT = new JDateChooser();
    	dC_FF_DDFCFT.setEnabled(false);
    	dC_FF_DDFCFT.setBounds(615, 10, 150, 20);
    	pnDiasDesdeFCierreFTurno.add(dC_FF_DDFCFT);
    	
    	btn_clear_FFDDFCFT = new JButton("");
    	btn_clear_FFDDFCFT.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(dC_FF_DDFCFT.getDate()!=null)
    				dC_FF_DDFCFT.setDate(null);
    		}
    	});
    	btn_clear_FFDDFCFT.setIcon(new ImageIcon(GUIReporteDiasDesdeCierreOrdenYTurno.class.getResource("/cliente/Resources/Icons/clear.png")));
    	btn_clear_FFDDFCFT.setBounds(775, 10, 25, 20);
    	pnDiasDesdeFCierreFTurno.add(btn_clear_FFDDFCFT);
    	
    	btn_filtrar_DDFCFT = new JButton("Filtrar");
    	btn_filtrar_DDFCFT.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			filtrarIntervaloDDFCFT();
    		}
    	});
    	btn_filtrar_DDFCFT.setEnabled(false);
    	btn_filtrar_DDFCFT.setBounds(810, 10, 110, 20);
    	pnDiasDesdeFCierreFTurno.add(btn_filtrar_DDFCFT);
    	
    	btnExportarTablaDDFCFT = new JButton("");
    	btnExportarTablaDDFCFT.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			exportarTablaDDFCFT();
    		}
    	});
    	btnExportarTablaDDFCFT.setIcon(new ImageIcon(GUIReporteDiasDesdeCierreOrdenYTurno.class.getResource("/cliente/Resources/Icons/formulario.png")));
    	btnExportarTablaDDFCFT.setBounds(1230, 120, 32, 32);
    	pnDiasDesdeFCierreFTurno.add(btnExportarTablaDDFCFT);
	}

	protected void exportarTablaDDFCFT() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_DDFCT, "Dias Desde Fecha Cierre a Fecha Turno");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void filtrarIntervaloDDFCFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioDDFCFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorDDFCFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesDDFCFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaDDFCFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyDDFCFT() {
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
