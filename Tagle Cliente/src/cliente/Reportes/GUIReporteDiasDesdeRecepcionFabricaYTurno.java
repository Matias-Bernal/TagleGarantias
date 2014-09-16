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

public class GUIReporteDiasDesdeRecepcionFabricaYTurno extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private JPanel contentPane;
	private JPanel pnDiasDesdeFRecepcionFabricaFTurno;
	private JScrollPane scrollPane_dias_transcurridos_desde_frf_fturno;
	private JTable tabla_DDRFT;
	private DefaultTableModel modelo_tabla_dias_desde_frf_fturno;
	private Vector<Vector<String>> datosTabla_dias_desde_frf_fturno;
	private Vector<String> nombreColumnas_tabla_dias_desde_frf_fturno;
	private Vector<Integer> anchos_tabla_dias_desde_frf_fturno;
	private JRadioButton rB_Intervalo_DDRFT;
	private JRadioButton rB_Hoy_DDRFT;
	private JRadioButton rB_USemana_DDRFT;
	private JRadioButton rB_Mes_DDRFT;
	private JRadioButton rB_MAnterior_DDRFT;
	private JRadioButton rB_Anio_DDRFT;
	private JDateChooser dC_FI_DDRFT;
	private JButton btn_clear_FIDDRFT;
	private JDateChooser dC_FF_DDRFT;
	private JButton btn_clear_FFDDRFT;
	private JButton btnExportarTabla_DDRFT;
	private JButton btn_filtrar_DDRFT;

	//tabla 
	public GUIReporteDiasDesdeRecepcionFabricaYTurno(MediadorReportes mediadorReporte) {
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
		//TABLA DIAS DESDE FRF A FTURNO //
		modelo_tabla_dias_desde_frf_fturno = new DefaultTableModel();
		nombreColumnas_tabla_dias_desde_frf_fturno = new Vector<String> ();
		anchos_tabla_dias_desde_frf_fturno = new Vector<Integer>();
		nombreColumnas_tabla_dias_desde_frf_fturno.add("ID Pedido");//0
		anchos_tabla_dias_desde_frf_fturno.add(75);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Numero Pedido");//1
		anchos_tabla_dias_desde_frf_fturno.add(chico);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Numero Pieza");//2
		anchos_tabla_dias_desde_frf_fturno.add(chico);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Descripcion");//3
		anchos_tabla_dias_desde_frf_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Numero Orden");//4
		anchos_tabla_dias_desde_frf_fturno.add(chico);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("VIN");//5
		anchos_tabla_dias_desde_frf_fturno.add(130);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Registrante");//6
		anchos_tabla_dias_desde_frf_fturno.add(grande);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Fecha Solicitud Pedido");//7
		anchos_tabla_dias_desde_frf_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Fecha Turno");//8
		anchos_tabla_dias_desde_frf_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Fecha Solicitud Fabrica");//9
		anchos_tabla_dias_desde_frf_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Fecha Recepcion Fabrica");//10
		anchos_tabla_dias_desde_frf_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Dias Desde Recepcion Fabrica a Fecha Turno");//11
		anchos_tabla_dias_desde_frf_fturno.add(grande);
		datosTabla_dias_desde_frf_fturno = new Vector<Vector<String>>();
		for (int i=0; i< pedidos_piezas.size();i++){
			boolean resp = false;
			if (mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				resp = pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza()==null && pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null && pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno()!=null;
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
				java.sql.Date fturno = null;
				if (pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno()!=null){
					fturno = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno().getTime());
					row.add(format2.format(fturno));//Fecha Turno
				}else{
					row.add("");
				}
				java.sql.Date fsf = null;
				if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
					fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
					row.add(format2.format(fsf));//Fecha solicitud Fabrica
				}else{
					row.add("");
				}
				java.sql.Date frf = null;
				if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
					frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
					row.add(format2.format(frf));//Fecha Recepcion Fabrica
				}else{
					row.add("");
				}
				if(fturno!=null && frf!=null){
					final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
					Calendar calendar = new GregorianCalendar(fturno.getYear(), fturno.getMonth()-1, fturno.getDay());
					long diferencia = (fturno.getTime() -  frf.getTime() )/MILLSECS_PER_DAY;
					row.add(String.valueOf(diferencia));
				}else{
					row.add("");
				}	
				datosTabla_dias_desde_frf_fturno.add(row);
			}
			
		}
		modelo_tabla_dias_desde_frf_fturno.setDataVector(datosTabla_dias_desde_frf_fturno, nombreColumnas_tabla_dias_desde_frf_fturno);
		modelo_tabla_dias_desde_frf_fturno.fireTableStructureChanged();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTE - DIAS DESDE RECEPCION DE FABRICA Y FECHA DE TURNO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReporteDiasDesdeRecepcionFabricaYTurno.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
        
		//DIAS DESDE FECHA RECEPCION FABRICA Y FECHA TURNO//
		pnDiasDesdeFRecepcionFabricaFTurno = new JPanel();
		pnDiasDesdeFRecepcionFabricaFTurno.setBounds(0, 0, 1274, 681);
		contentPane.add(pnDiasDesdeFRecepcionFabricaFTurno);
		pnDiasDesdeFRecepcionFabricaFTurno.setLayout(null);
		modelo_tabla_dias_desde_frf_fturno = new DefaultTableModel(datosTabla_dias_desde_frf_fturno, nombreColumnas_tabla_dias_desde_frf_fturno);
		tabla_DDRFT = new JTable(modelo_tabla_dias_desde_frf_fturno) {
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
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_tabla_dias_desde_frf_fturno = new TableRowSorter<TableModel>(modelo_tabla_dias_desde_frf_fturno);
		for(int i = 0; i < tabla_DDRFT.getColumnCount(); i++) {
			tabla_DDRFT.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_dias_desde_frf_fturno.elementAt(i));
			tabla_DDRFT.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_dias_desde_frf_fturno.elementAt(i));
		}
		//se crea instancia a clase FormatoTable y se indica columna patron
		FormatoTablaDiasDesdeFTrunoFCierre frfft = new FormatoTablaDiasDesdeFTrunoFCierre(11);
		tabla_DDRFT.setRowSorter(ordenador_tabla_dias_desde_frf_fturno);
		tabla_DDRFT.getTableHeader().setReorderingAllowed(false);
		tabla_DDRFT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla_DDRFT.setDefaultRenderer (Object.class, frfft );
		scrollPane_dias_transcurridos_desde_frf_fturno= new JScrollPane(tabla_DDRFT);
		scrollPane_dias_transcurridos_desde_frf_fturno.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_dias_transcurridos_desde_frf_fturno.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_dias_transcurridos_desde_frf_fturno.setBounds(5, 160, 1255, 510);
		pnDiasDesdeFRecepcionFabricaFTurno.add(scrollPane_dias_transcurridos_desde_frf_fturno);
		tabla_DDRFT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		rB_Intervalo_DDRFT = new JRadioButton("Intervalo de Fechas");
		rB_Intervalo_DDRFT.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Intervalo_DDRFT.isSelected()){
					dC_FF_DDRFT.setEnabled(true);
					dC_FI_DDRFT.setEnabled(true);
					btn_filtrar_DDRFT.setEnabled(true);
					
					rB_Hoy_DDRFT.setSelected(false);
					rB_USemana_DDRFT.setSelected(false);
					rB_Mes_DDRFT.setSelected(false);
					rB_MAnterior_DDRFT.setSelected(false);
					rB_Anio_DDRFT.setSelected(false);
				}else{
					dC_FF_DDRFT.setEnabled(false);
					dC_FI_DDRFT.setEnabled(false);
					btn_filtrar_DDRFT.setEnabled(false);
				}
			}
		});
		rB_Intervalo_DDRFT.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
		rB_Intervalo_DDRFT.setBounds(5, 10, 150, 20);
		pnDiasDesdeFRecepcionFabricaFTurno.add(rB_Intervalo_DDRFT);
		
		rB_Hoy_DDRFT = new JRadioButton("Hoy");
		rB_Hoy_DDRFT.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Hoy_DDRFT.isSelected()){
					rB_Intervalo_DDRFT.setSelected(false);
					dC_FF_DDRFT.setEnabled(false);
					dC_FI_DDRFT.setEnabled(false);
					rB_USemana_DDRFT.setSelected(false);
					rB_Mes_DDRFT.setSelected(false);
					rB_MAnterior_DDRFT.setSelected(false);
					rB_Anio_DDRFT.setSelected(false);
					filtrarHoyDDRFT();
				}
			}
		});
		rB_Hoy_DDRFT.setToolTipText("Piezas llegadas el dia de hoy.");
		rB_Hoy_DDRFT.setBounds(5, 35, 150, 20);
		pnDiasDesdeFRecepcionFabricaFTurno.add(rB_Hoy_DDRFT);
		
		rB_USemana_DDRFT = new JRadioButton("Ultima Semana");
		rB_USemana_DDRFT.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_USemana_DDRFT.isSelected()){
					rB_Intervalo_DDRFT.setSelected(false);
					dC_FF_DDRFT.setEnabled(false);
					dC_FI_DDRFT.setEnabled(false);
					rB_Hoy_DDRFT.setSelected(false);
					rB_Mes_DDRFT.setSelected(false);
					rB_MAnterior_DDRFT.setSelected(false);
					rB_Anio_DDRFT.setSelected(false);
					filtrarUSemanaDDRFT();
				}
			}
		});
		rB_USemana_DDRFT.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
		rB_USemana_DDRFT.setBounds(5, 60, 150, 20);
		pnDiasDesdeFRecepcionFabricaFTurno.add(rB_USemana_DDRFT);
		
		rB_Mes_DDRFT = new JRadioButton("Este Mes");
		rB_Mes_DDRFT.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Mes_DDRFT.isSelected()){
					rB_Intervalo_DDRFT.setSelected(false);
					dC_FF_DDRFT.setEnabled(false);
					dC_FI_DDRFT.setEnabled(false);
					rB_Hoy_DDRFT.setSelected(false);
					rB_USemana_DDRFT.setSelected(false);
					rB_MAnterior_DDRFT.setSelected(false);
					rB_Anio_DDRFT.setSelected(false);
					filtrarMesDDRFT();
				}
			}
		});
		rB_Mes_DDRFT.setToolTipText("Piezas llegadas en el mes corriente.");
		rB_Mes_DDRFT.setBounds(5, 85, 150, 20);
		pnDiasDesdeFRecepcionFabricaFTurno.add(rB_Mes_DDRFT);
		
		rB_MAnterior_DDRFT = new JRadioButton("Mes Anterior");
		rB_MAnterior_DDRFT.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_MAnterior_DDRFT.isSelected()){
					rB_Intervalo_DDRFT.setSelected(false);
					dC_FF_DDRFT.setEnabled(false);
					dC_FI_DDRFT.setEnabled(false);
					rB_Hoy_DDRFT.setSelected(false);
					rB_USemana_DDRFT.setSelected(false);
					rB_Mes_DDRFT.setSelected(false);
					rB_Anio_DDRFT.setSelected(false);
					filtrarMAnteriorDDRFT();
				}
			}
		});
		rB_MAnterior_DDRFT.setBounds(5, 110, 150, 20);
		pnDiasDesdeFRecepcionFabricaFTurno.add(rB_MAnterior_DDRFT);
		
		rB_Anio_DDRFT = new JRadioButton("Ultimo A\u00F1o");
		rB_Anio_DDRFT.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Anio_DDRFT.isSelected()){
					rB_Intervalo_DDRFT.setSelected(false);
					dC_FF_DDRFT.setEnabled(false);
					dC_FI_DDRFT.setEnabled(false);
					rB_Hoy_DDRFT.setSelected(false);
					rB_USemana_DDRFT.setSelected(false);
					rB_Mes_DDRFT.setSelected(false);
					rB_MAnterior_DDRFT.setSelected(false);
					filtrarUAnioDDRFT();
				}
			}
		});
		rB_Anio_DDRFT.setBounds(5, 135, 150, 20);
		pnDiasDesdeFRecepcionFabricaFTurno.add(rB_Anio_DDRFT);
		
		JLabel label_22 = new JLabel("Fecha Inicio");
		label_22.setHorizontalAlignment(SwingConstants.CENTER);
		label_22.setEnabled(false);
		label_22.setBounds(161, 11, 120, 20);
		pnDiasDesdeFRecepcionFabricaFTurno.add(label_22);
		
		dC_FI_DDRFT = new JDateChooser();
		dC_FI_DDRFT.setEnabled(false);
		dC_FI_DDRFT.setBounds(290, 10, 150, 20);
		pnDiasDesdeFRecepcionFabricaFTurno.add(dC_FI_DDRFT);
		
		btn_clear_FIDDRFT = new JButton("");
		btn_clear_FIDDRFT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FI_DDRFT.getDate()!=null)
					dC_FI_DDRFT.setDate(null);
			}
		});
		btn_clear_FIDDRFT.setIcon(new ImageIcon(GUIReporteDiasDesdeRecepcionFabricaYTurno.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FIDDRFT.setBounds(450, 10, 25, 20);
		pnDiasDesdeFRecepcionFabricaFTurno.add(btn_clear_FIDDRFT);
		
		JLabel label_23 = new JLabel("Fecha Fin");
		label_23.setHorizontalAlignment(SwingConstants.CENTER);
		label_23.setEnabled(false);
		label_23.setBounds(486, 11, 120, 20);
		pnDiasDesdeFRecepcionFabricaFTurno.add(label_23);
		
		dC_FF_DDRFT = new JDateChooser();
		dC_FF_DDRFT.setEnabled(false);
		dC_FF_DDRFT.setBounds(615, 10, 150, 20);
		pnDiasDesdeFRecepcionFabricaFTurno.add(dC_FF_DDRFT);
		
		btn_clear_FFDDRFT = new JButton("");
		btn_clear_FFDDRFT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_DDRFT.getDate()!=null)
					dC_FF_DDRFT.setDate(null);
			}
		});
		btn_clear_FFDDRFT.setIcon(new ImageIcon(GUIReporteDiasDesdeRecepcionFabricaYTurno.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FFDDRFT.setBounds(775, 10, 25, 20);
		pnDiasDesdeFRecepcionFabricaFTurno.add(btn_clear_FFDDRFT);
		
		btn_filtrar_DDRFT = new JButton("Filtrar");
		btn_filtrar_DDRFT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtrarIntervaloDDRFT();
			}
		});
		btn_filtrar_DDRFT.setEnabled(false);
		btn_filtrar_DDRFT.setBounds(810, 10, 110, 20);
		pnDiasDesdeFRecepcionFabricaFTurno.add(btn_filtrar_DDRFT);
		
		btnExportarTabla_DDRFT = new JButton("");
		btnExportarTabla_DDRFT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaDDRFT();
			}
		});
		btnExportarTabla_DDRFT.setIcon(new ImageIcon(GUIReporteDiasDesdeRecepcionFabricaYTurno.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTabla_DDRFT.setBounds(1230, 120, 32, 32);
		pnDiasDesdeFRecepcionFabricaFTurno.add(btnExportarTabla_DDRFT);
	}


	protected void exportarTablaDDRFT() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_DDRFT, "Dias Desde Reclamo a Fecha Turno");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void filtrarIntervaloDDRFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioDDRFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorDDRFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesDDRFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaDDRFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyDDRFT() {
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
