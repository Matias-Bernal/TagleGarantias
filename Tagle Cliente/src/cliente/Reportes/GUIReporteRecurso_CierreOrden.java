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

import java.awt.Font;
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
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cliente.excellexport.ExportarExcel;

import com.toedter.calendar.JDateChooser;
import common.DTOs.ReclamoDTO;

public class GUIReporteRecurso_CierreOrden extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private JPanel contentPane;
	private JPanel pnRecursoCierre;
	private JTable tabla_RC;
	private DefaultTableModel modelo_tabla_recurso_cierre;
	private Vector<Vector<String>> datos_tabla_recurso_cierre;
	private Vector<String> nombreColumnas_tabla_recurso_cierre;
	private Vector<Integer> anchos_tabla_recurso_cierre;
	private JScrollPane scrollPane_recurso_cierre;
	private float N_casos;
	private float sum_frecurso_fcierre;
	private JRadioButton rB_Anio_RC;
	private JRadioButton rB_MAnterior_RC;
	private JRadioButton rB_Mes_RC;
	private JRadioButton rB_USemana_RC;
	private JRadioButton rB_Hoy_RC;
	private JRadioButton rB_Intervalo_RC;
	private JDateChooser dC_FI_RC;
	private JButton btn_clear_FIRC;
	private JDateChooser dC_FF_RC;
	private JButton btn_clear_FFRC;
	private JButton btn_filtrar_RC;
	private JButton btnExportarTabla_RC;
	private JLabel lbl_PromFRecurso_FCierre_1;

	//tabla 
	public GUIReporteRecurso_CierreOrden(MediadorReportes mediadorReporte) {
		setMediador(mediadorReporte);
		cargarDatos();
		initialize();
	}

	@SuppressWarnings("unused")
	private void cargarDatos() {
		int mediano = 150;
		int grande = 230;
		//TABLA FECHA RECURSO - FECHA CIERRE///
		modelo_tabla_recurso_cierre = new DefaultTableModel();
		nombreColumnas_tabla_recurso_cierre = new Vector<String> ();
		anchos_tabla_recurso_cierre = new Vector<Integer>();
		nombreColumnas_tabla_recurso_cierre.add("ID Orden");//0
		anchos_tabla_recurso_cierre.add(mediano);
		nombreColumnas_tabla_recurso_cierre.add("ID Reclamo");//1
		anchos_tabla_recurso_cierre.add(mediano);
		nombreColumnas_tabla_recurso_cierre.add("Numero Orden");//3
		anchos_tabla_recurso_cierre.add(mediano);
		nombreColumnas_tabla_recurso_cierre.add("Fecha Apertura");//4
		anchos_tabla_recurso_cierre.add(mediano);
		nombreColumnas_tabla_recurso_cierre.add("Fecha Cierre Orden");//5
		anchos_tabla_recurso_cierre.add(mediano);
		nombreColumnas_tabla_recurso_cierre.add("ID Recurso");//6
		anchos_tabla_recurso_cierre.add(mediano);
		nombreColumnas_tabla_recurso_cierre.add("Numero Recurso");//7
		anchos_tabla_recurso_cierre.add(mediano);
		nombreColumnas_tabla_recurso_cierre.add("Fecha Recurso");//8
		anchos_tabla_recurso_cierre.add(mediano);
		nombreColumnas_tabla_recurso_cierre.add("Dias Desde Fecha Cierre Orden a Fecha Recurso");//9
		anchos_tabla_recurso_cierre.add(grande);
		datos_tabla_recurso_cierre = new Vector<Vector<String>>();
		Vector<ReclamoDTO> reclamos = mediador.obtenerReclamos();
		setN_casos(reclamos.size());
		setSum_frecurso_fcierre(0);
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		for (int i=0; i< reclamos.size();i++){
			Vector<String> row = new Vector<String> ();
			row.add(reclamos.elementAt(i).getOrden().getId().toString());//ID Orden
			row.add(reclamos.elementAt(i).getId().toString());//ID Orden
			row.add(reclamos.elementAt(i).getOrden().getNumero_orden());//Numero Orden
			java.sql.Date fapertura = null;
			if(reclamos.elementAt(i).getOrden().getFecha_apertura()!=null){
				fapertura = new java.sql.Date(reclamos.elementAt(i).getOrden().getFecha_apertura().getTime());
				row.add(format2.format(fapertura));//Fecha Apertura
			}else{
				row.add("");
			}
			java.sql.Date fcierre = null;
			if(reclamos.elementAt(i).getOrden().getFecha_cierre()!=null){
				fcierre = new java.sql.Date(reclamos.elementAt(i).getOrden().getFecha_cierre().getTime());
				row.add(format2.format(fcierre));//Fecha Cierre
			}else{
				row.add("");
			}
			java.sql.Date frecurso = null;
			if(reclamos.elementAt(i).getOrden().getRecurso()!=null){
				row.add(reclamos.elementAt(i).getOrden().getRecurso().getId().toString());//ID Recurso
				row.add(reclamos.elementAt(i).getOrden().getRecurso().getNumero_recurso());//numero Recurso
				
				frecurso = new java.sql.Date(reclamos.elementAt(i).getOrden().getRecurso().getFecha_recurso().getTime());
				row.add(format2.format(frecurso));//Fecha Recurso
			}
			if(fcierre!=null && frecurso!=null){
				final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
				@SuppressWarnings("deprecation")
				Calendar calendar = new GregorianCalendar(frecurso.getYear(), frecurso.getMonth()-1, frecurso.getDay());
				long diferencia = (frecurso.getTime() - fcierre.getTime())/MILLSECS_PER_DAY;
				row.add(String.valueOf(diferencia));
				setSum_frecurso_fcierre(getSum_frecurso_fcierre() + diferencia);
			}else{
				row.add("");
			}
			datos_tabla_recurso_cierre.add(row);
		}
		modelo_tabla_recurso_cierre.setDataVector(datos_tabla_recurso_cierre, nombreColumnas_tabla_recurso_cierre);
		modelo_tabla_recurso_cierre.fireTableStructureChanged();
		////////////////// FIN	/////////////////
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTE - FECHA RECURSO - FECHA CIERRE DE ORDEN");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReporteRecurso_CierreOrden.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
    	
        //RECURSO - CIERRE ORDEN//
        pnRecursoCierre = new JPanel();
        pnRecursoCierre.setBounds(0, 0, 1274, 681);
        contentPane.add(pnRecursoCierre);
        pnRecursoCierre.setLayout(null);
        modelo_tabla_recurso_cierre = new DefaultTableModel(datos_tabla_recurso_cierre , nombreColumnas_tabla_recurso_cierre);
        tabla_RC = new JTable(modelo_tabla_recurso_cierre) {
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
		TableRowSorter<TableModel> ordenador_tabla_recurso_cierre = new TableRowSorter<TableModel>(modelo_tabla_recurso_cierre);
		for(int i = 0; i < tabla_RC.getColumnCount(); i++) {
			tabla_RC.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_recurso_cierre.elementAt(i));
			tabla_RC.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_recurso_cierre.elementAt(i));
		}
        tabla_RC.setRowSorter(ordenador_tabla_recurso_cierre);
        tabla_RC.getTableHeader().setReorderingAllowed(false);
        tabla_RC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane_recurso_cierre= new JScrollPane(tabla_RC);
        scrollPane_recurso_cierre.setBounds(5, 160, 1255, 510);
        pnRecursoCierre.add(scrollPane_recurso_cierre);
        scrollPane_recurso_cierre.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_recurso_cierre.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        tabla_RC.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        JLabel lblPromediofrecurso = new JLabel("PROMEDIO (F.RECURSO - F.CIERRE ORDEN) / #CASOS");
        lblPromediofrecurso.setFont(new Font("Tahoma", Font.PLAIN, 8));
        lblPromediofrecurso.setBounds(933, 11, 212, 25);
        pnRecursoCierre.add(lblPromediofrecurso);
        lblPromediofrecurso.setHorizontalAlignment(SwingConstants.CENTER);
        lblPromediofrecurso.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    	 
 
    	lbl_PromFRecurso_FCierre_1 = new JLabel("");
    	lbl_PromFRecurso_FCierre_1.setBounds(1155, 11, 100, 25);
    	pnRecursoCierre.add(lbl_PromFRecurso_FCierre_1);
    	lbl_PromFRecurso_FCierre_1.setHorizontalAlignment(SwingConstants.CENTER);
    	lbl_PromFRecurso_FCierre_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    	
    	JLabel lblSumatoriaFrecurso = new JLabel("SUMATORIA (F.RECURSO - F.CIERRE)");
    	lblSumatoriaFrecurso.setBounds(933, 49, 212, 25);
    	pnRecursoCierre.add(lblSumatoriaFrecurso);
    	lblSumatoriaFrecurso.setHorizontalAlignment(SwingConstants.CENTER);
    	lblSumatoriaFrecurso.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    	
    	JLabel lbl_Sum_FRecurso_FCierre = new JLabel(String.valueOf(sum_frecurso_fcierre));
    	lbl_Sum_FRecurso_FCierre.setBounds(1155, 49, 100, 25);
    	pnRecursoCierre.add(lbl_Sum_FRecurso_FCierre);
    	lbl_Sum_FRecurso_FCierre.setHorizontalAlignment(SwingConstants.CENTER);
    	lbl_Sum_FRecurso_FCierre.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    	
    	JLabel lblcasos = new JLabel("#CASOS");
    	lblcasos.setBounds(933, 90, 212, 25);
    	pnRecursoCierre.add(lblcasos);
    	lblcasos.setHorizontalAlignment(SwingConstants.CENTER);
    	lblcasos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    	
    	JLabel lbl_N_Casos = new JLabel(String.valueOf(N_casos));
    	lbl_N_Casos.setBounds(1155, 90, 100, 25);
    	pnRecursoCierre.add(lbl_N_Casos);
    	lbl_N_Casos.setHorizontalAlignment(SwingConstants.CENTER);
    	lbl_N_Casos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    	
        if (sum_frecurso_fcierre!=0 && N_casos!=0){
        	lbl_PromFRecurso_FCierre_1.setText(String.valueOf(sum_frecurso_fcierre / N_casos));
        }else{
        	lbl_PromFRecurso_FCierre_1.setText("N/A");
        }
    	rB_Anio_RC = new JRadioButton("Ultimo A\u00F1o");
    	rB_Anio_RC.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Anio_RC.isSelected()){
    				rB_Intervalo_RC.setSelected(false);
    				dC_FF_RC.setEnabled(false);
    				dC_FI_RC.setEnabled(false);
    				rB_Hoy_RC.setSelected(false);
    				rB_USemana_RC.setSelected(false);
    				rB_Mes_RC.setSelected(false);
    				rB_MAnterior_RC.setSelected(false);
    				filtrarUAnioRC();
    			}
    		}
    	});
    	rB_Anio_RC.setBounds(5, 135, 150, 20);
    	pnRecursoCierre.add(rB_Anio_RC);
    	
    	rB_MAnterior_RC = new JRadioButton("Mes Anterior");
    	rB_MAnterior_RC.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_MAnterior_RC.isSelected()){
    				rB_Intervalo_RC.setSelected(false);
    				dC_FF_RC.setEnabled(false);
    				dC_FI_RC.setEnabled(false);
    				rB_Hoy_RC.setSelected(false);
    				rB_USemana_RC.setSelected(false);
    				rB_Mes_RC.setSelected(false);
    				rB_Anio_RC.setSelected(false);
    				filtrarMAnteriorRC();
    			}
    		}
    	});
    	rB_MAnterior_RC.setBounds(5, 110, 150, 20);
    	pnRecursoCierre.add(rB_MAnterior_RC);
    	
    	rB_Mes_RC = new JRadioButton("Este Mes");
    	rB_Mes_RC.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Mes_RC.isSelected()){
    				rB_Intervalo_RC.setSelected(false);
    				dC_FF_RC.setEnabled(false);
    				dC_FI_RC.setEnabled(false);
    				rB_Hoy_RC.setSelected(false);
    				rB_USemana_RC.setSelected(false);
    				rB_MAnterior_RC.setSelected(false);
    				rB_Anio_RC.setSelected(false);
    				filtrarMesRC();
    			}
    		}
    	});
    	rB_Mes_RC.setToolTipText("Piezas llegadas en el mes corriente.");
    	rB_Mes_RC.setBounds(5, 85, 150, 20);
    	pnRecursoCierre.add(rB_Mes_RC);
    	
    	rB_USemana_RC = new JRadioButton("Ultima Semana");
    	rB_USemana_RC.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_USemana_RC.isSelected()){
    				rB_Intervalo_RC.setSelected(false);
    				dC_FF_RC.setEnabled(false);
    				dC_FI_RC.setEnabled(false);
    				rB_Hoy_RC.setSelected(false);
    				rB_Mes_RC.setSelected(false);
    				rB_MAnterior_RC.setSelected(false);
    				rB_Anio_RC.setSelected(false);
    				filtrarUSemanaRC();
    			}
    		}
    	});
    	rB_USemana_RC.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
    	rB_USemana_RC.setBounds(5, 60, 150, 20);
    	pnRecursoCierre.add(rB_USemana_RC);
    	
    	rB_Hoy_RC = new JRadioButton("Hoy");
    	rB_Hoy_RC.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Hoy_RC.isSelected()){
					rB_Intervalo_RC.setSelected(false);
					dC_FF_RC.setEnabled(false);
					dC_FI_RC.setEnabled(false);
					rB_USemana_RC.setSelected(false);
					rB_Mes_RC.setSelected(false);
					rB_MAnterior_RC.setSelected(false);
					rB_Anio_RC.setSelected(false);
					filtrarHoyRC();
				}
			}
		});
    	rB_Hoy_RC.setToolTipText("Piezas llegadas el dia de hoy.");
    	rB_Hoy_RC.setBounds(5, 35, 150, 20);
    	pnRecursoCierre.add(rB_Hoy_RC);
    	
    	rB_Intervalo_RC = new JRadioButton("Intervalo de Fechas");
    	rB_Intervalo_RC.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Intervalo_RC.isSelected()){
    				dC_FF_RC.setEnabled(true);
    				dC_FI_RC.setEnabled(true);
    				btn_filtrar_RC.setEnabled(true);
    				rB_Hoy_RC.setSelected(false);
    				rB_USemana_RC.setSelected(false);
    				rB_Mes_RC.setSelected(false);
    				rB_MAnterior_RC.setSelected(false);
    				rB_Anio_RC.setSelected(false);
    			}else{
    				dC_FF_RC.setEnabled(false);
    				dC_FI_RC.setEnabled(false);
    				btn_filtrar_RC.setEnabled(false);
    			}
    		}
    	});
    	rB_Intervalo_RC.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
    	rB_Intervalo_RC.setBounds(5, 10, 150, 20);
    	pnRecursoCierre.add(rB_Intervalo_RC);
    	
    	JLabel label_16 = new JLabel("Fecha Inicio");
    	label_16.setHorizontalAlignment(SwingConstants.CENTER);
    	label_16.setEnabled(false);
    	label_16.setBounds(160, 10, 120, 20);
    	pnRecursoCierre.add(label_16);
    	
    	dC_FI_RC = new JDateChooser();
    	dC_FI_RC.setEnabled(false);
    	dC_FI_RC.setBounds(290, 10, 150, 20);
    	pnRecursoCierre.add(dC_FI_RC);
    	
    	btn_clear_FIRC = new JButton("");
    	btn_clear_FIRC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FI_RC.getDate()!=null)
					dC_FI_RC.setDate(null);
			}
		});
    	btn_clear_FIRC.setIcon(new ImageIcon(GUIReporteRecurso_CierreOrden.class.getResource("/cliente/Resources/Icons/clear.png")));
    	btn_clear_FIRC.setBounds(450, 10, 25, 20);
    	pnRecursoCierre.add(btn_clear_FIRC);
    	
    	JLabel label_17 = new JLabel("Fecha Fin");
    	label_17.setHorizontalAlignment(SwingConstants.CENTER);
    	label_17.setEnabled(false);
    	label_17.setBounds(485, 10, 120, 20);
    	pnRecursoCierre.add(label_17);
    	
    	dC_FF_RC = new JDateChooser();
    	dC_FF_RC.setEnabled(false);
    	dC_FF_RC.setBounds(615, 10, 150, 20);
    	pnRecursoCierre.add(dC_FF_RC);
    	
    	btn_clear_FFRC = new JButton("");
    	btn_clear_FFRC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_RC.getDate()!=null)
					dC_FF_RC.setDate(null);
			}
		});
    	btn_clear_FFRC.setIcon(new ImageIcon(GUIReporteRecurso_CierreOrden.class.getResource("/cliente/Resources/Icons/clear.png")));
    	btn_clear_FFRC.setBounds(775, 10, 25, 20);
    	pnRecursoCierre.add(btn_clear_FFRC);
    	
    	btn_filtrar_RC = new JButton("Filtrar");
    	btn_filtrar_RC.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				filtrarIntervaloRC();
 			}
 		});
    	btn_filtrar_RC.setEnabled(false);
    	btn_filtrar_RC.setBounds(810, 10, 110, 20);
    	pnRecursoCierre.add(btn_filtrar_RC);
    	
    	btnExportarTabla_RC = new JButton("");
    	btnExportarTabla_RC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaRC();
			}
		});
    	btnExportarTabla_RC.setIcon(new ImageIcon(GUIReporteRecurso_CierreOrden.class.getResource("/cliente/Resources/Icons/formulario.png")));
    	btnExportarTabla_RC.setBounds(1230, 120, 32, 32);
    	pnRecursoCierre.add(btnExportarTabla_RC);
	}

	
	protected void exportarTablaRC() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_RC, "Reclamo - Cierre de Orden");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}	
	}

	protected void filtrarIntervaloRC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyRC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaRC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesRC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorRC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioRC() {
		// TODO Auto-generated method stub
		
	}
	
	//gettes and setters
	public MediadorReportes getMediador() {
		return mediador;
	}

	public void setMediador(MediadorReportes mediador) {
		this.mediador = mediador;
	}

	public float getSum_frecurso_fcierre() {
		return sum_frecurso_fcierre;
	}

	public void setSum_frecurso_fcierre(float sum_frecurso_fcierre) {
		this.sum_frecurso_fcierre = sum_frecurso_fcierre;
	}

	public float getN_casos() {
		return N_casos;
	}

	public void setN_casos(float n_casos) {
		N_casos = n_casos;
	}
}
