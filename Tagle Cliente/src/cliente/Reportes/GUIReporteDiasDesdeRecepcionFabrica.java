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
import java.util.Date;
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

public class GUIReporteDiasDesdeRecepcionFabrica extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private JPanel contentPane;
	private JPanel pnDiasDesdeFRecepcionFabirca;
	private JTable tabla_DDRF;
	private JScrollPane scrollPane_dias_trascurridos_desde_frf;
	private DefaultTableModel modelo_tabla_dias_desde_frf;
	private Vector<Vector<String>> datosTabla_dias_desde_frf;
	private Vector<String> nombreColumnas_tabla_dias_desde_frf;
	private Vector<Integer> anchos_tabla_dias_desde_frf;
	private JRadioButton rB_Intervalo_DDRF;
	private JRadioButton rB_Hoy_DDRF;
	private JRadioButton rB_USemana_DDRF;
	private JRadioButton rB_Mes_DDRF;
	private JRadioButton rB_MAnterior_DDRF;
	private JRadioButton rB_Anio_DDRF;
	private JDateChooser dC_FI_DDRF;
	private JButton btn_clear_FIDDRF;
	private JDateChooser dC_FF_DDRF;
	private JButton btnFiltrar_DDRF;
	private JButton btnExportarTablaDDRF;
	private JButton btn_clear_FFDDRF;

	//tabla 
	public GUIReporteDiasDesdeRecepcionFabrica(MediadorReportes mediadorReporte) {
		setMediador(mediadorReporte);
		cargarDatos();
		initialize();
	}

	@SuppressWarnings({ "unused", "deprecation" })
	private void cargarDatos() {
		Vector<Pedido_PiezaDTO> pedidos_piezas = mediador.obtenerPedido_Piezas();
		int chico = 100;
		int mediano = 150;
		int grande = 230;
		//Tabla dias desde frf
		modelo_tabla_dias_desde_frf = new DefaultTableModel();
		nombreColumnas_tabla_dias_desde_frf = new Vector<String> ();
		anchos_tabla_dias_desde_frf = new Vector<Integer>();
		nombreColumnas_tabla_dias_desde_frf.add("ID Pedido");//0
		anchos_tabla_dias_desde_frf.add(75);
		nombreColumnas_tabla_dias_desde_frf.add("Numero Pedido");//1
		anchos_tabla_dias_desde_frf.add(chico);
		nombreColumnas_tabla_dias_desde_frf.add("Numero Pieza");//2
		anchos_tabla_dias_desde_frf.add(chico);
		nombreColumnas_tabla_dias_desde_frf.add("Descripcion");//3
		anchos_tabla_dias_desde_frf.add(mediano);
		nombreColumnas_tabla_dias_desde_frf.add("Numero Orden");//4
		anchos_tabla_dias_desde_frf.add(chico);
		nombreColumnas_tabla_dias_desde_frf.add("VIN");//5
		anchos_tabla_dias_desde_frf.add(130);
		nombreColumnas_tabla_dias_desde_frf.add("Registrante");//6
		anchos_tabla_dias_desde_frf.add(grande);
		nombreColumnas_tabla_dias_desde_frf.add("Fecha Solicitud Pedido");//7
		anchos_tabla_dias_desde_frf.add(mediano);
		nombreColumnas_tabla_dias_desde_frf.add("Fecha Solicitud Fabrica");//8
		anchos_tabla_dias_desde_frf.add(mediano);
		nombreColumnas_tabla_dias_desde_frf.add("Fecha Recepcion Fabrica");//9
		anchos_tabla_dias_desde_frf.add(mediano);
		nombreColumnas_tabla_dias_desde_frf.add("Dias Desde FRF");//10
		anchos_tabla_dias_desde_frf.add(chico);
		datosTabla_dias_desde_frf = new Vector<Vector<String>>();
	
		for (int i=0; i< pedidos_piezas.size();i++){
			boolean resp = false;
			if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}else{
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getFecha_envio_agente()==null  && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null;
			}	
			if(resp){
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
					if(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
						final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
						java.util.Date hoy = new Date(); //Fecha de hoy 
						Calendar calendar = new GregorianCalendar(frf.getYear(), frf.getMonth()-1, frf.getDay());
						long diferencia = ( hoy.getTime() - frf.getTime() )/MILLSECS_PER_DAY;
						row.add(String.valueOf(diferencia));
					}else{
						row.add("");
						row.add("");
					}	
					datosTabla_dias_desde_frf.add(row);
				}
		}
		modelo_tabla_dias_desde_frf.setDataVector(datosTabla_dias_desde_frf, nombreColumnas_tabla_dias_desde_frf);
		modelo_tabla_dias_desde_frf.fireTableStructureChanged();
	}


	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTE - DIAS DESDE FECHA RECEPCION FABRICA");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReporteDiasDesdeRecepcionFabrica.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		    	
    	//DIAS DESDE FECHA RECEPCION FABRICA//
    	pnDiasDesdeFRecepcionFabirca = new JPanel();
    	pnDiasDesdeFRecepcionFabirca.setBounds(0, 0, 1274, 681);
    	contentPane.add(pnDiasDesdeFRecepcionFabirca);
    	modelo_tabla_dias_desde_frf = new DefaultTableModel(datosTabla_dias_desde_frf, nombreColumnas_tabla_dias_desde_frf);
    	tabla_DDRF = new JTable(modelo_tabla_dias_desde_frf) {
    		private static final long serialVersionUID = 1L;
    		boolean[] columnEditables = new boolean[] {
    				false, false, false, false, false,
    				false, false, false,false, false,
    				false, false,false,
    		};
    		public boolean isCellEditable(int row, int column) {
    			return columnEditables[column];
    		}
    	};
		TableRowSorter<TableModel> ordenador_tabla_dias_desde_frf = new TableRowSorter<TableModel>(modelo_tabla_dias_desde_frf);
		for(int i = 0; i < tabla_DDRF.getColumnCount(); i++) {
			tabla_DDRF.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_dias_desde_frf.elementAt(i));
			tabla_DDRF.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_dias_desde_frf.elementAt(i));
		}
		//se crea instancia a clase FormatoTable y se indica columna patron
        FormatoTablaDiasDesdeFRF fr = new FormatoTablaDiasDesdeFRF(10);
    	tabla_DDRF.setRowSorter(ordenador_tabla_dias_desde_frf);
    	tabla_DDRF.getTableHeader().setReorderingAllowed(false);
    	tabla_DDRF.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	tabla_DDRF.setDefaultRenderer (Object.class, fr );
    	pnDiasDesdeFRecepcionFabirca.setLayout(null);
    	scrollPane_dias_trascurridos_desde_frf= new JScrollPane(tabla_DDRF);
    	scrollPane_dias_trascurridos_desde_frf.setBounds(5, 160, 1255, 510);
    	scrollPane_dias_trascurridos_desde_frf.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	scrollPane_dias_trascurridos_desde_frf.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    	pnDiasDesdeFRecepcionFabirca.add(scrollPane_dias_trascurridos_desde_frf);
    	tabla_DDRF.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
    	rB_Intervalo_DDRF = new JRadioButton("Intervalo de Fechas");
    	rB_Intervalo_DDRF.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Intervalo_DDRF.isSelected()){
    				dC_FF_DDRF.setEnabled(true);
    				dC_FI_DDRF.setEnabled(true);
    				btnFiltrar_DDRF.setEnabled(true);
    				
    				rB_Hoy_DDRF.setSelected(false);
    				rB_USemana_DDRF.setSelected(false);
    				rB_Mes_DDRF.setSelected(false);
    				rB_MAnterior_DDRF.setSelected(false);
    				rB_Anio_DDRF.setSelected(false);
    			}else{
    				dC_FF_DDRF.setEnabled(false);
    				dC_FI_DDRF.setEnabled(false);
    				btnFiltrar_DDRF.setEnabled(false);
    			}
    		}
    	});
    	rB_Intervalo_DDRF.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
    	rB_Intervalo_DDRF.setBounds(5, 10, 150, 20);
    	pnDiasDesdeFRecepcionFabirca.add(rB_Intervalo_DDRF);
    	
    	rB_Hoy_DDRF = new JRadioButton("Hoy");
    	rB_Hoy_DDRF.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Hoy_DDRF.isSelected()){
    				rB_Intervalo_DDRF.setSelected(false);
    				dC_FF_DDRF.setEnabled(false);
    				dC_FI_DDRF.setEnabled(false);
    				rB_USemana_DDRF.setSelected(false);
    				rB_Mes_DDRF.setSelected(false);
    				rB_MAnterior_DDRF.setSelected(false);
    				rB_Anio_DDRF.setSelected(false);
    				filtrarHoyDDRF();
    			}
    		}
    	});
    	rB_Hoy_DDRF.setToolTipText("Piezas llegadas el dia de hoy.");
    	rB_Hoy_DDRF.setBounds(5, 35, 150, 20);
    	pnDiasDesdeFRecepcionFabirca.add(rB_Hoy_DDRF);
    	
    	rB_USemana_DDRF = new JRadioButton("Ultima Semana");
    	rB_USemana_DDRF.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_USemana_DDRF.isSelected()){
    				rB_Intervalo_DDRF.setSelected(false);
    				dC_FF_DDRF.setEnabled(false);
    				dC_FI_DDRF.setEnabled(false);
    				rB_Hoy_DDRF.setSelected(false);
    				rB_Mes_DDRF.setSelected(false);
    				rB_MAnterior_DDRF.setSelected(false);
    				rB_Anio_DDRF.setSelected(false);
    				filtrarUSemanaDDRF();
    			}
    		}
    	});
    	rB_USemana_DDRF.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
    	rB_USemana_DDRF.setBounds(5, 60, 150, 20);
    	pnDiasDesdeFRecepcionFabirca.add(rB_USemana_DDRF);
    	
    	rB_Mes_DDRF = new JRadioButton("Este Mes");
    	rB_Mes_DDRF.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Mes_DDRF.isSelected()){
    				rB_Intervalo_DDRF.setSelected(false);
    				dC_FF_DDRF.setEnabled(false);
    				dC_FI_DDRF.setEnabled(false);
    				rB_Hoy_DDRF.setSelected(false);
    				rB_USemana_DDRF.setSelected(false);
    				rB_MAnterior_DDRF.setSelected(false);
    				rB_Anio_DDRF.setSelected(false);
    				filtrarMesDDRF();
    			}
    		}
    	});
    	rB_Mes_DDRF.setToolTipText("Piezas llegadas en el mes corriente.");
    	rB_Mes_DDRF.setBounds(5, 85, 150, 20);
    	pnDiasDesdeFRecepcionFabirca.add(rB_Mes_DDRF);
    	
    	rB_MAnterior_DDRF = new JRadioButton("Mes Anterior");
    	rB_MAnterior_DDRF.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_MAnterior_DDRF.isSelected()){
    				rB_Intervalo_DDRF.setSelected(false);
    				dC_FF_DDRF.setEnabled(false);
    				dC_FI_DDRF.setEnabled(false);
    				rB_Hoy_DDRF.setSelected(false);
    				rB_USemana_DDRF.setSelected(false);
    				rB_Mes_DDRF.setSelected(false);
    				rB_Anio_DDRF.setSelected(false);
    				filtrarMAnteriorDDRF();
    			}
    		}
    	});
    	rB_MAnterior_DDRF.setBounds(5, 110, 150, 20);
    	pnDiasDesdeFRecepcionFabirca.add(rB_MAnterior_DDRF);
    	
    	rB_Anio_DDRF = new JRadioButton("Ultimo A\u00F1o");
    	rB_Anio_DDRF.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Anio_DDRF.isSelected()){
    				rB_Intervalo_DDRF.setSelected(false);
    				dC_FF_DDRF.setEnabled(false);
    				dC_FI_DDRF.setEnabled(false);
    				rB_Hoy_DDRF.setSelected(false);
    				rB_USemana_DDRF.setSelected(false);
    				rB_Mes_DDRF.setSelected(false);
    				rB_MAnterior_DDRF.setSelected(false);
    				filtrarUAnioDDRF();
    			}
    		}
    	});
    	rB_Anio_DDRF.setBounds(5, 135, 150, 20);
    	pnDiasDesdeFRecepcionFabirca.add(rB_Anio_DDRF);
    	
    	JLabel label_20 = new JLabel("Fecha Inicio");
    	label_20.setHorizontalAlignment(SwingConstants.CENTER);
    	label_20.setEnabled(false);
    	label_20.setBounds(160, 10, 120, 20);
    	pnDiasDesdeFRecepcionFabirca.add(label_20);
    	
    	dC_FI_DDRF = new JDateChooser();
    	dC_FI_DDRF.setEnabled(false);
    	dC_FI_DDRF.setBounds(290, 10, 150, 20);
    	pnDiasDesdeFRecepcionFabirca.add(dC_FI_DDRF);
    	
    	btn_clear_FIDDRF = new JButton("");
    	btn_clear_FIDDRF.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(dC_FI_DDRF.getDate()!=null)
    				dC_FI_DDRF.setDate(null);
    		}
    	});
    	btn_clear_FIDDRF.setIcon(new ImageIcon(GUIReporteDiasDesdeRecepcionFabrica.class.getResource("/cliente/Resources/Icons/clear.png")));
    	btn_clear_FIDDRF.setBounds(450, 10, 25, 20);
    	pnDiasDesdeFRecepcionFabirca.add(btn_clear_FIDDRF);
    	
    	JLabel label_21 = new JLabel("Fecha Fin");
    	label_21.setHorizontalAlignment(SwingConstants.CENTER);
    	label_21.setEnabled(false);
    	label_21.setBounds(485, 10, 120, 20);
    	pnDiasDesdeFRecepcionFabirca.add(label_21);
    	
    	dC_FF_DDRF = new JDateChooser();
    	dC_FF_DDRF.setEnabled(false);
    	dC_FF_DDRF.setBounds(615, 10, 150, 20);
    	pnDiasDesdeFRecepcionFabirca.add(dC_FF_DDRF);
    	
    	btn_clear_FFDDRF = new JButton("");
    	btn_clear_FFDDRF.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(dC_FF_DDRF.getDate()!=null)
    				dC_FF_DDRF.setDate(null);
    		}
    	});
    	btn_clear_FFDDRF.setIcon(new ImageIcon(GUIReporteDiasDesdeRecepcionFabrica.class.getResource("/cliente/Resources/Icons/clear.png")));
    	btn_clear_FFDDRF.setBounds(775, 10, 25, 20);
    	pnDiasDesdeFRecepcionFabirca.add(btn_clear_FFDDRF);
    	
    	btnFiltrar_DDRF = new JButton("Filtrar");
    	btnFiltrar_DDRF.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			filtaraPorIntervaloDDRF();
    		}
    	});
    	btnFiltrar_DDRF.setEnabled(false);
    	btnFiltrar_DDRF.setBounds(810, 10, 110, 20);
    	pnDiasDesdeFRecepcionFabirca.add(btnFiltrar_DDRF);
    	
    	btnExportarTablaDDRF = new JButton("");
    	btnExportarTablaDDRF.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			exportarTablaDDRF();
    		}
    	});
    	btnExportarTablaDDRF.setIcon(new ImageIcon(GUIReporteDiasDesdeRecepcionFabrica.class.getResource("/cliente/Resources/Icons/formulario.png")));
    	btnExportarTablaDDRF.setBounds(1230, 120, 32, 32);
    	pnDiasDesdeFRecepcionFabirca.add(btnExportarTablaDDRF);
	}

	protected void exportarTablaDDRF() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_DDRF, "Dias Desde Recepcion Pedido a Fabrica");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void filtaraPorIntervaloDDRF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioDDRF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorDDRF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesDDRF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaDDRF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyDDRF() {
		// TODO Auto-generated method stub
		
	}

	//Fin filtros
	
	//gettes and setters
	public MediadorReportes getMediador() {
		return mediador;
	}

	public void setMediador(MediadorReportes mediador) {
		this.mediador = mediador;
	}

}
