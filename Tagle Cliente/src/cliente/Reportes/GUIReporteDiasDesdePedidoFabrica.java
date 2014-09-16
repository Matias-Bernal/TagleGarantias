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

public class GUIReporteDiasDesdePedidoFabrica extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private JPanel contentPane;
	private JPanel pnDiasDesdeFSolicitudFabirca;
	private JScrollPane scrollPane_dias_trascurridos_desde_pedido_fabrica;
	private DefaultTableModel modelo_tabla_dias_desde_fsf;
	private JTable tabla_DDPF;
	private Vector<String> nombreColumnas_tabla_dias_desde_fsf;
	private Vector<Vector<String>> datosTabla_dias_desde_fsf;
	private Vector<Integer> anchos_tabla_dias_desde_fsf;
	private JRadioButton rB_Intervalo_DDPF;
	private JRadioButton rB_Hoy_DDPF;
	private JRadioButton rB_USemana_DDPF;
	private JRadioButton rB_Mes_DDPF;
	private JRadioButton rB_MAnterior_DDPF;
	private JRadioButton rB_Anio_DDPF;
	private JDateChooser dC_FI_DDPF;
	private JButton btn_clear_FIDDPF;
	private JDateChooser dC_FF_DDPF;
	private JButton btn_clear_FFDDPF;
	private JButton btn_filtrar_DDPF;
	private JButton btnExportarTablaDDPF;

	public GUIReporteDiasDesdePedidoFabrica(MediadorReportes mediadorReporte) {
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
		modelo_tabla_dias_desde_fsf = new DefaultTableModel();
		nombreColumnas_tabla_dias_desde_fsf = new Vector<String> ();
		anchos_tabla_dias_desde_fsf = new Vector<Integer>();
		nombreColumnas_tabla_dias_desde_fsf.add("ID Pedido");//0
		anchos_tabla_dias_desde_fsf.add(75);
		nombreColumnas_tabla_dias_desde_fsf.add("Numero Pedido");//1
		anchos_tabla_dias_desde_fsf.add(chico);
		nombreColumnas_tabla_dias_desde_fsf.add("Numero Pieza");//2
		anchos_tabla_dias_desde_fsf.add(chico);
		nombreColumnas_tabla_dias_desde_fsf.add("Descripcion");//3
		anchos_tabla_dias_desde_fsf.add(mediano);
		nombreColumnas_tabla_dias_desde_fsf.add("Numero Orden");//4
		anchos_tabla_dias_desde_fsf.add(chico);
		nombreColumnas_tabla_dias_desde_fsf.add("VIN");//5
		anchos_tabla_dias_desde_fsf.add(130);
		nombreColumnas_tabla_dias_desde_fsf.add("Registrante");//6
		anchos_tabla_dias_desde_fsf.add(grande);
		nombreColumnas_tabla_dias_desde_fsf.add("Fecha Solicitud Pedido");//7
		anchos_tabla_dias_desde_fsf.add(mediano);
		nombreColumnas_tabla_dias_desde_fsf.add("Fecha Solicitud Fabrica");//8
		anchos_tabla_dias_desde_fsf.add(mediano);
		nombreColumnas_tabla_dias_desde_fsf.add("Dias Desde FSF");//9
		anchos_tabla_dias_desde_fsf.add(chico);
		datosTabla_dias_desde_fsf = new Vector<Vector<String>>();
		for (int i=0; i< pedidos_piezas.size();i++){
			if(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null && pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()==null){
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
					final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
					java.util.Date hoy = new Date(); //Fecha de hoy 
					Calendar calendar = new GregorianCalendar(fsf.getYear(), fsf.getMonth()-1, fsf.getDay());
					long diferencia = ( hoy.getTime() - fsf.getTime() )/MILLSECS_PER_DAY;
					row.add(String.valueOf(diferencia));	
				}else{
					row.add("");
					row.add("");
				}
				datosTabla_dias_desde_fsf.add(row);
			}
		}
		modelo_tabla_dias_desde_fsf.setDataVector(datosTabla_dias_desde_fsf, nombreColumnas_tabla_dias_desde_fsf);
		modelo_tabla_dias_desde_fsf.fireTableStructureChanged();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTE - DIAS DESDE PEDIDO A FABRICA");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReporteDiasDesdePedidoFabrica.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

    	//DIAS DESDE FECHA SOLICITUD FABRICA//
    	pnDiasDesdeFSolicitudFabirca = new JPanel();
    	pnDiasDesdeFSolicitudFabirca.setBounds(0, 0, 1274, 681);
    	contentPane.add(pnDiasDesdeFSolicitudFabirca);
    	pnDiasDesdeFSolicitudFabirca.setLayout(null);
    	modelo_tabla_dias_desde_fsf = new DefaultTableModel(datosTabla_dias_desde_fsf, nombreColumnas_tabla_dias_desde_fsf);
    	tabla_DDPF = new JTable(modelo_tabla_dias_desde_fsf) {
    		private static final long serialVersionUID = 1L;
    		boolean[] columnEditables = new boolean[] {
    				false, false, false, false, false,
    				false, false, false,false, false,
    				false, false,
    		};
    		public boolean isCellEditable(int row, int column) {
    			return columnEditables[column];
    		}
    	};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_tabla_dias_desde_fsf = new TableRowSorter<TableModel>(modelo_tabla_dias_desde_fsf);
		for(int i = 0; i < tabla_DDPF.getColumnCount(); i++) {
			tabla_DDPF.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_dias_desde_fsf.elementAt(i));
			tabla_DDPF.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_dias_desde_fsf.elementAt(i));
		}
		//se crea instancia a clase FormatoTable y se indica columna patron
		FormatoTablaDiasDesdeFSF ft = new FormatoTablaDiasDesdeFSF(9);
    	tabla_DDPF.setRowSorter(ordenador_tabla_dias_desde_fsf);
    	tabla_DDPF.getTableHeader().setReorderingAllowed(false);
    	tabla_DDPF.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	tabla_DDPF.setDefaultRenderer (Object.class, ft );
    	scrollPane_dias_trascurridos_desde_pedido_fabrica= new JScrollPane(tabla_DDPF);
    	scrollPane_dias_trascurridos_desde_pedido_fabrica.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	scrollPane_dias_trascurridos_desde_pedido_fabrica.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    	scrollPane_dias_trascurridos_desde_pedido_fabrica.setBounds(5, 160, 1255, 510);
    	tabla_DDPF.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	pnDiasDesdeFSolicitudFabirca.add(scrollPane_dias_trascurridos_desde_pedido_fabrica);
    	
    	rB_Anio_DDPF = new JRadioButton("Ultimo A\u00F1o");
    	rB_Anio_DDPF.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Anio_DDPF.isSelected()){
    				rB_Intervalo_DDPF.setSelected(false);
    				dC_FF_DDPF.setEnabled(false);
    				dC_FI_DDPF.setEnabled(false);
    				rB_Hoy_DDPF.setSelected(false);
    				rB_USemana_DDPF.setSelected(false);
    				rB_Mes_DDPF.setSelected(false);
    				rB_MAnterior_DDPF.setSelected(false);
    				filtrarUAnioDDPF();
    			}
    		}
    	});
    	rB_Anio_DDPF.setBounds(5, 135, 150, 20);
    	pnDiasDesdeFSolicitudFabirca.add(rB_Anio_DDPF);
    	
    	rB_MAnterior_DDPF = new JRadioButton("Mes Anterior");
    	rB_MAnterior_DDPF.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_MAnterior_DDPF.isSelected()){
    				rB_Intervalo_DDPF.setSelected(false);
    				dC_FF_DDPF.setEnabled(false);
    				dC_FI_DDPF.setEnabled(false);
    				rB_Hoy_DDPF.setSelected(false);
    				rB_USemana_DDPF.setSelected(false);
    				rB_Mes_DDPF.setSelected(false);
    				rB_Anio_DDPF.setSelected(false);
    				filtrarMAnteriorDDPF();
    			}
    		}
    	});
    	rB_MAnterior_DDPF.setBounds(5, 110, 150, 20);
    	pnDiasDesdeFSolicitudFabirca.add(rB_MAnterior_DDPF);
    	
    	rB_Mes_DDPF = new JRadioButton("Este Mes");
    	rB_Mes_DDPF.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Mes_DDPF.isSelected()){
    				rB_Intervalo_DDPF.setSelected(false);
    				dC_FF_DDPF.setEnabled(false);
    				dC_FI_DDPF.setEnabled(false);
    				rB_Hoy_DDPF.setSelected(false);
    				rB_USemana_DDPF.setSelected(false);
    				rB_MAnterior_DDPF.setSelected(false);
    				rB_Anio_DDPF.setSelected(false);
    				filtrarMesDDPF();
    			}
    		}
    	});
    	rB_Mes_DDPF.setToolTipText("Piezas llegadas en el mes corriente.");
    	rB_Mes_DDPF.setBounds(5, 85, 150, 20);
    	pnDiasDesdeFSolicitudFabirca.add(rB_Mes_DDPF);
    	
    	rB_USemana_DDPF = new JRadioButton("Ultima Semana");
    	rB_USemana_DDPF.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_USemana_DDPF.isSelected()){
    				rB_Intervalo_DDPF.setSelected(false);
    				dC_FF_DDPF.setEnabled(false);
    				dC_FI_DDPF.setEnabled(false);
    				rB_Hoy_DDPF.setSelected(false);
    				rB_Mes_DDPF.setSelected(false);
    				rB_MAnterior_DDPF.setSelected(false);
    				rB_Anio_DDPF.setSelected(false);
    				filtrarUSemanaDDPF();
    			}
    		}
    	});
    	rB_USemana_DDPF.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
    	rB_USemana_DDPF.setBounds(5, 60, 150, 20);
    	pnDiasDesdeFSolicitudFabirca.add(rB_USemana_DDPF);
    	
    	rB_Hoy_DDPF = new JRadioButton("Hoy");
    	rB_Hoy_DDPF.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Hoy_DDPF.isSelected()){
    				rB_Intervalo_DDPF.setSelected(false);
    				dC_FF_DDPF.setEnabled(false);
    				dC_FI_DDPF.setEnabled(false);
    				rB_USemana_DDPF.setSelected(false);
    				rB_Mes_DDPF.setSelected(false);
    				rB_MAnterior_DDPF.setSelected(false);
    				rB_Anio_DDPF.setSelected(false);
    				filtrarHoyDDPF();
    			}
    		}
    	});
    	rB_Hoy_DDPF.setToolTipText("Piezas llegadas el dia de hoy.");
    	rB_Hoy_DDPF.setBounds(5, 35, 150, 20);
    	pnDiasDesdeFSolicitudFabirca.add(rB_Hoy_DDPF);
    	
    	rB_Intervalo_DDPF = new JRadioButton("Intervalo de Fechas");
    	rB_Intervalo_DDPF.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Intervalo_DDPF.isSelected()){
    				dC_FF_DDPF.setEnabled(true);
    				dC_FI_DDPF.setEnabled(true);
    				btn_filtrar_DDPF.setEnabled(true);
    				
    				rB_Hoy_DDPF.setSelected(false);
    				rB_USemana_DDPF.setSelected(false);
    				rB_Mes_DDPF.setSelected(false);
    				rB_MAnterior_DDPF.setSelected(false);
    				rB_Anio_DDPF.setSelected(false);
    			}else{
    				dC_FF_DDPF.setEnabled(false);
    				dC_FI_DDPF.setEnabled(false);
    				btn_filtrar_DDPF.setEnabled(false);
    			}
    		}
    	});
    	rB_Intervalo_DDPF.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
    	rB_Intervalo_DDPF.setBounds(5, 10, 150, 20);
    	pnDiasDesdeFSolicitudFabirca.add(rB_Intervalo_DDPF);
    	
    	JLabel label_18 = new JLabel("Fecha Inicio");
    	label_18.setHorizontalAlignment(SwingConstants.CENTER);
    	label_18.setEnabled(false);
    	label_18.setBounds(160, 10, 120, 20);
    	pnDiasDesdeFSolicitudFabirca.add(label_18);
    	
    	dC_FI_DDPF = new JDateChooser();
    	dC_FI_DDPF.setEnabled(false);
    	dC_FI_DDPF.setBounds(290, 10, 150, 20);
    	pnDiasDesdeFSolicitudFabirca.add(dC_FI_DDPF);
    	
    	btn_clear_FIDDPF = new JButton("");
    	btn_clear_FIDDPF.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(dC_FI_DDPF.getDate()!=null)
    				dC_FI_DDPF.setDate(null);
    		}
    	});
    	btn_clear_FIDDPF.setIcon(new ImageIcon(GUIReporteDiasDesdePedidoFabrica.class.getResource("/cliente/Resources/Icons/clear.png")));
    	btn_clear_FIDDPF.setBounds(450, 10, 25, 20);
    	pnDiasDesdeFSolicitudFabirca.add(btn_clear_FIDDPF);
    	
    	JLabel label_19 = new JLabel("Fecha Fin");
    	label_19.setHorizontalAlignment(SwingConstants.CENTER);
    	label_19.setEnabled(false);
    	label_19.setBounds(485, 10, 120, 20);
    	pnDiasDesdeFSolicitudFabirca.add(label_19);
    	
    	dC_FF_DDPF = new JDateChooser();
    	dC_FF_DDPF.setEnabled(false);
    	dC_FF_DDPF.setBounds(615, 10, 150, 20);
    	pnDiasDesdeFSolicitudFabirca.add(dC_FF_DDPF);
    	
    	btn_clear_FFDDPF = new JButton("");
    	btn_clear_FFDDPF.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(dC_FF_DDPF.getDate()!=null)
    				dC_FF_DDPF.setDate(null);
    		}
    	});
    	btn_clear_FFDDPF.setIcon(new ImageIcon(GUIReporteDiasDesdePedidoFabrica.class.getResource("/cliente/Resources/Icons/clear.png")));
    	btn_clear_FFDDPF.setBounds(775, 10, 25, 20);
    	pnDiasDesdeFSolicitudFabirca.add(btn_clear_FFDDPF);
    	
    	btn_filtrar_DDPF = new JButton("Filtrar");
    	btn_filtrar_DDPF.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			filtaraPorIntervaloDDPF();
    		}
    	});
    	btn_filtrar_DDPF.setEnabled(false);
    	btn_filtrar_DDPF.setBounds(810, 10, 110, 20);
    	pnDiasDesdeFSolicitudFabirca.add(btn_filtrar_DDPF);
    	
    	btnExportarTablaDDPF = new JButton("");
    	btnExportarTablaDDPF.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			exportarTablaDDPF();
    		}
    	});
    	btnExportarTablaDDPF.setIcon(new ImageIcon(GUIReporteDiasDesdePedidoFabrica.class.getResource("/cliente/Resources/Icons/formulario.png")));
    	btnExportarTablaDDPF.setBounds(1230, 120, 32, 32);
    	pnDiasDesdeFSolicitudFabirca.add(btnExportarTablaDDPF);
	}

	protected void filtrarHoyDDPF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaDDPF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorDDPF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesDDPF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioDDPF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtaraPorIntervaloDDPF() {
		// TODO Auto-generated method stub
		
	}
	
	protected void exportarTablaDDPF() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_DDPF, "Dias Desde Pedido a Fabrica");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
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
