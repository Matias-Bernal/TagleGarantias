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

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
import common.DTOs.Pedido_PiezaDTO;

public class GUIReporteDiasFechaReclamo_Turnos extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private JPanel contentPane;
	private JPanel pnReclamos_Turnos;
	private JScrollPane scrollPane_tabla_reclamos_turno_Reclamos;
	private JScrollPane scrollPane_tabla_reclamos_turnos_Turno;
	private JLabel lblReclamoTurnos;
	private JTable tabla_RTR;
	private JTable tabla_RTT;
	private DefaultTableModel modelo_tabla_reclamos_turno_Reclamos;
	private Vector<Vector<String>> datos_tabla_reclamos_turno_Reclamos;
	private Vector<String> nombreColumnas_tabla_reclamos_turno_Reclamos;
	private Vector<Integer> anchos_tabla_reclamos_turno_Reclamos;
	private DefaultTableModel modelo_tabla_reclamos_turno_Turno;
	private Vector<Vector<String>> datos_tabla_reclamos_turno_Turno;	
	private Vector<String> nombreColumnas_tabla_reclamos_turno_Turno;
	private Vector<Integer> anchos_tabla_reclamos_turno_Turno;
	private float numero_reclamos;
	private float numero_turnos;
	private JLabel lblNum_ReclmaosConTurno;
	private JLabel lblNum_ReclamosSinTurno;
	private JRadioButton rB_Intervalo_RT;
	private JRadioButton rB_Hoy_RT;
	private JRadioButton rB_USemana_RT;
	private JRadioButton rB_Mes_RT;
	private JRadioButton rB_MAnterior_RT;
	private JRadioButton rB_Anio_RT;
	private JDateChooser dC_FI_RT;
	private JButton btn_clear_FIRT;
	private JDateChooser dC_FF_RT;
	private JButton btn_clear_FFRT;
	private JButton btn_filtrar_RT;
	private JButton btnExportarTabla_RT;

	//tabla 
	public GUIReporteDiasFechaReclamo_Turnos(MediadorReportes mediadorReporte) {
		setMediador(mediadorReporte);
		cargarDatos();
		initialize();
	}

	private void cargarDatos() {
		Vector<Pedido_PiezaDTO> pedidos_piezas = mediador.obtenerPedido_Piezas();
		int chico = 100;
		int mediano = 150;
		int grande = 230;
		modelo_tabla_reclamos_turno_Reclamos = new DefaultTableModel();
		nombreColumnas_tabla_reclamos_turno_Reclamos = new Vector<String> ();
		anchos_tabla_reclamos_turno_Reclamos = new Vector<Integer>();
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("ID Pedido");//0
		anchos_tabla_reclamos_turno_Reclamos.add(75);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Numero Pedido");//1
		anchos_tabla_reclamos_turno_Reclamos.add(chico);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Numero Pieza");//2
		anchos_tabla_reclamos_turno_Reclamos.add(chico);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Descripcion");//3
		anchos_tabla_reclamos_turno_Reclamos.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Numero Orden");//4
		anchos_tabla_reclamos_turno_Reclamos.add(chico);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("VIN");//5
		anchos_tabla_reclamos_turno_Reclamos.add(130);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Registrante");//6
		anchos_tabla_reclamos_turno_Reclamos.add(grande);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Fecha Reclamo");//7
		anchos_tabla_reclamos_turno_Reclamos.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Fecha Turno");//8
		anchos_tabla_reclamos_turno_Reclamos.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Fecha Solicitud a Fabrica");//9
		anchos_tabla_reclamos_turno_Reclamos.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Fecha Recepcion de Fabrica");//10
		anchos_tabla_reclamos_turno_Reclamos.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Fecha Devolucion a Fabrica");//11
		anchos_tabla_reclamos_turno_Reclamos.add(mediano);
		datos_tabla_reclamos_turno_Reclamos = new Vector<Vector<String>>();
		modelo_tabla_reclamos_turno_Turno = new DefaultTableModel();
		nombreColumnas_tabla_reclamos_turno_Turno = new Vector<String> ();
		anchos_tabla_reclamos_turno_Turno = new Vector<Integer>();
		nombreColumnas_tabla_reclamos_turno_Turno.add("ID Pedido");//0
		anchos_tabla_reclamos_turno_Turno.add(75);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Numero Pedido");//1
		anchos_tabla_reclamos_turno_Turno.add(chico);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Numero Pieza");//2
		anchos_tabla_reclamos_turno_Turno.add(chico);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Descripcion");//3
		anchos_tabla_reclamos_turno_Turno.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Numero Orden");//4
		anchos_tabla_reclamos_turno_Turno.add(chico);
		nombreColumnas_tabla_reclamos_turno_Turno.add("VIN");//5
		anchos_tabla_reclamos_turno_Turno.add(130);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Registrante");//6
		anchos_tabla_reclamos_turno_Turno.add(grande);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Fecha Reclamo");//7
		anchos_tabla_reclamos_turno_Turno.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Fecha Turno");//8
		anchos_tabla_reclamos_turno_Turno.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Fecha Solicitud a Fabrica");//9
		anchos_tabla_reclamos_turno_Turno.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Fecha Recepcion de Fabrica");//10
		anchos_tabla_reclamos_turno_Turno.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Fecha Devolucion a Fabrica");//11
		anchos_tabla_reclamos_turno_Turno.add(mediano);	
		datos_tabla_reclamos_turno_Turno = new Vector<Vector<String>>();
		numero_reclamos = 0;
		numero_turnos = 0;
		
		for (int i=0; i< pedidos_piezas.size();i++){
			if(mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){
				Vector<String> rowReclamo = new Vector<String> ();
				Vector<String> rowTurno = new Vector<String> ();
				if(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno()!=null){
					rowReclamo.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					rowTurno.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					rowReclamo.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					rowTurno.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					rowReclamo.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					rowTurno.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					rowReclamo.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					rowTurno.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					rowReclamo.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					rowTurno.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					rowReclamo.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					rowTurno.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					rowReclamo.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					rowTurno.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
					if(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_reclamo()!=null){
						java.sql.Date fr = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_reclamo().getTime());
						rowReclamo.add(format2.format(fr));//Fecha Reclamo
						rowTurno.add(format2.format(fr));//Fecha Reclamo
					}else{
						rowReclamo.add("");
						rowTurno.add("");
					}			    
					java.sql.Date fturno = null;
					if (pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno()!=null){
						fturno = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno().getTime());
						rowReclamo.add(format2.format(fturno));//Fecha Turno
						rowTurno.add(format2.format(fturno));//Fecha Turno
					}else{
						rowReclamo.add("");
						rowTurno.add("");
					}
					java.sql.Date fsf = null;
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						rowReclamo.add(format2.format(fsf));//Fecha solicitud Fabrica
						rowTurno.add(format2.format(fsf));//Fecha solicitud Fabrica
					}else{
						rowReclamo.add("");
						rowTurno.add("");
					}
					java.sql.Date frf = null;
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						rowReclamo.add(format2.format(frf));//Fecha Recepcion Fabrica
						rowTurno.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						rowReclamo.add("");
						rowTurno.add("");
					}
					java.sql.Date fdf = null;
					if (pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){
						fdf = new java.sql.Date(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().getTime());
						rowReclamo.add(format2.format(fdf));//Fecha Devolucion Fabrica 
						rowTurno.add(format2.format(fdf));//Fecha Devolucion Fabrica 
					}else{
						rowReclamo.add("");
						rowTurno.add("");
					}
					numero_reclamos++;
					numero_turnos++;
					datos_tabla_reclamos_turno_Turno.add(rowTurno);
					datos_tabla_reclamos_turno_Reclamos.add(rowReclamo);
				}else{
					rowReclamo.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
					rowReclamo.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
					rowReclamo.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
					rowReclamo.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
					rowReclamo.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
					rowReclamo.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
					rowReclamo.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
					SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
					if(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_reclamo()!=null){
						java.sql.Date fr = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_reclamo().getTime());
						rowReclamo.add(format2.format(fr));//Fecha Reclamo
					}else{
						rowReclamo.add("");
					}			    
					java.sql.Date fturno = null;
					if (pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno()!=null){
						fturno = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno().getTime());
						rowReclamo.add(format2.format(fturno));//Fecha Turno
					}else{
						rowReclamo.add("");
					}
					java.sql.Date fsf = null;
					if (pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null){
						fsf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica().getTime());
						rowReclamo.add(format2.format(fsf));//Fecha solicitud Fabrica
					}else{
						rowReclamo.add("");
					}
					java.sql.Date frf = null;
					if (pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null){
						frf = new java.sql.Date(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica().getTime());
						rowReclamo.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						rowReclamo.add("");
					}
					java.sql.Date fdf = null;
					if (pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null){
						fdf = new java.sql.Date(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion().getTime());
						rowReclamo.add(format2.format(fdf));//Fecha Devolucion Fabrica 
					}else{
						rowReclamo.add("");
					}
					numero_reclamos++;
					datos_tabla_reclamos_turno_Reclamos.add(rowReclamo);
				}
			}
		}
		modelo_tabla_reclamos_turno_Reclamos.setDataVector(datos_tabla_reclamos_turno_Reclamos, nombreColumnas_tabla_reclamos_turno_Reclamos);
		modelo_tabla_reclamos_turno_Reclamos.fireTableStructureChanged();
		modelo_tabla_reclamos_turno_Turno.setDataVector(datos_tabla_reclamos_turno_Turno, nombreColumnas_tabla_reclamos_turno_Turno);
		modelo_tabla_reclamos_turno_Turno.fireTableStructureChanged();
	}
	
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTE - RECLAMOS - TURNOS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReporteDiasFechaReclamo_Turnos.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

    	//RECLAMOS - TURNOS//
    	pnReclamos_Turnos = new JPanel();
    	pnReclamos_Turnos.setBounds(0, 0, 1274, 681);
    	contentPane.add(pnReclamos_Turnos);
    	pnReclamos_Turnos.setLayout(null);
		//TURNOS - RECLAMOS	(RECLAMOS)//
        modelo_tabla_reclamos_turno_Reclamos = new DefaultTableModel(datos_tabla_reclamos_turno_Reclamos , nombreColumnas_tabla_reclamos_turno_Reclamos );        
    	tabla_RTR = new JTable(modelo_tabla_reclamos_turno_Reclamos) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false,false, false,false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_tabla_reclamos_turno_Reclamos = new TableRowSorter<TableModel>(modelo_tabla_reclamos_turno_Reclamos);
		for(int i = 0; i < tabla_RTR.getColumnCount(); i++) {
			tabla_RTR.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_reclamos_turno_Reclamos.elementAt(i));
			tabla_RTR.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_reclamos_turno_Reclamos.elementAt(i));
		}
    	tabla_RTR.setRowSorter(ordenador_tabla_reclamos_turno_Reclamos);
    	tabla_RTR.getTableHeader().setReorderingAllowed(false);
    	tabla_RTR.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	scrollPane_tabla_reclamos_turno_Reclamos= new JScrollPane(tabla_RTR);
    	scrollPane_tabla_reclamos_turno_Reclamos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	scrollPane_tabla_reclamos_turno_Reclamos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    	scrollPane_tabla_reclamos_turno_Reclamos.setBounds(5, 205, 1255, 200);
    	pnReclamos_Turnos.add(scrollPane_tabla_reclamos_turno_Reclamos);	 
    	tabla_RTR.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	// Agregamos el ordenador para las tablas de los usuarios 
    	//TURNOS - RECLAMOS	(TURNOS)//
    	modelo_tabla_reclamos_turno_Turno = new DefaultTableModel(datos_tabla_reclamos_turno_Turno , nombreColumnas_tabla_reclamos_turno_Turno );
    	tabla_RTT = new JTable(modelo_tabla_reclamos_turno_Turno) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false,false, false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		TableRowSorter<TableModel> ordenador_tabla_reclamos_turno_Turno = new TableRowSorter<TableModel>(modelo_tabla_reclamos_turno_Turno);
		for(int i = 0; i < tabla_RTT.getColumnCount(); i++) {
			tabla_RTT.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_reclamos_turno_Turno.elementAt(i));
			tabla_RTT.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_reclamos_turno_Turno.elementAt(i));
		}
    	tabla_RTT.setRowSorter(ordenador_tabla_reclamos_turno_Turno);
    	tabla_RTT.getTableHeader().setReorderingAllowed(false);
    	tabla_RTT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	scrollPane_tabla_reclamos_turnos_Turno= new JScrollPane(tabla_RTT);
    	scrollPane_tabla_reclamos_turnos_Turno.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	scrollPane_tabla_reclamos_turnos_Turno.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    	scrollPane_tabla_reclamos_turnos_Turno.setBounds(5, 470, 1255, 200);
    	pnReclamos_Turnos.add(scrollPane_tabla_reclamos_turnos_Turno);
    	tabla_RTT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);      
    	
        JLabel lblReclamos_Turnos = new JLabel("RECLAMOS / TURNOS");
        lblReclamos_Turnos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblReclamos_Turnos.setHorizontalAlignment(SwingConstants.CENTER);
        lblReclamos_Turnos.setBounds(935, 11, 200, 25);
        pnReclamos_Turnos.add(lblReclamos_Turnos);
        
        lblReclamoTurnos = new JLabel("");
        lblReclamoTurnos.setHorizontalAlignment(SwingConstants.CENTER);
        lblReclamoTurnos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblReclamoTurnos.setBounds(1157, 11, 100, 25);
        pnReclamos_Turnos.add(lblReclamoTurnos);
        
        JLabel lblReclamosConTurno = new JLabel("NUMERO DE RECLAMOS CON TURNO");
        lblReclamosConTurno.setHorizontalAlignment(SwingConstants.CENTER);
        lblReclamosConTurno.setBounds(5, 439, 240, 20);
        pnReclamos_Turnos.add(lblReclamosConTurno);
        
        lblNum_ReclmaosConTurno = new JLabel("");
        lblNum_ReclmaosConTurno.setText(String.valueOf(numero_turnos));
        lblNum_ReclmaosConTurno.setHorizontalAlignment(SwingConstants.CENTER);
        lblNum_ReclmaosConTurno.setBounds(250, 439, 46, 20);
        pnReclamos_Turnos.add(lblNum_ReclmaosConTurno);
        
        lblNum_ReclamosSinTurno = new JLabel("");
        lblNum_ReclamosSinTurno.setText(String.valueOf(numero_reclamos));
        lblNum_ReclamosSinTurno.setHorizontalAlignment(SwingConstants.CENTER);
        lblNum_ReclamosSinTurno.setBounds(250, 180, 45, 20);
        pnReclamos_Turnos.add(lblNum_ReclamosSinTurno);
        
        JLabel lblNumeroDeReclamos = new JLabel("NUMERO DE RECLAMOS");
        lblNumeroDeReclamos.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumeroDeReclamos.setBounds(5, 180, 230, 20);
        pnReclamos_Turnos.add(lblNumeroDeReclamos);
        
		if(numero_reclamos==0 && numero_turnos==0){
			lblReclamoTurnos.setText("N/A");
	        lblReclamoTurnos.setBackground(Color.WHITE);
		}else{
			lblReclamoTurnos.setText(String.valueOf(numero_reclamos/numero_turnos));
			if((numero_reclamos/numero_turnos)<= 1){
				lblReclamoTurnos.setBackground(Color.GREEN);

			}else{
				if((numero_reclamos/numero_turnos)> 2){
					lblReclamoTurnos.setBackground(Color.RED);
				}else{
					lblReclamoTurnos.setBackground(Color.YELLOW);
				}
			}
		}
		
        rB_Intervalo_RT = new JRadioButton("Intervalo de Fechas");
        rB_Intervalo_RT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Intervalo_RT.isSelected()){
        			dC_FF_RT.setEnabled(true);
        			dC_FI_RT.setEnabled(true);
        			btn_filtrar_RT.setEnabled(true);
        			
        			rB_Hoy_RT.setSelected(false);
        			rB_USemana_RT.setSelected(false);
        			rB_Mes_RT.setSelected(false);
        			rB_MAnterior_RT.setSelected(false);
        			rB_Anio_RT.setSelected(false);
        		}else{
        			dC_FF_RT.setEnabled(false);
        			dC_FI_RT.setEnabled(false);
        			btn_filtrar_RT.setEnabled(false);
        		}
        	}
        });
        rB_Intervalo_RT.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
        rB_Intervalo_RT.setBounds(5, 10, 150, 20);
        pnReclamos_Turnos.add(rB_Intervalo_RT);
        
        rB_Hoy_RT = new JRadioButton("Hoy");
        rB_Hoy_RT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Hoy_RT.isSelected()){
        			rB_Intervalo_RT.setSelected(false);
        			dC_FF_RT.setEnabled(false);
        			dC_FI_RT.setEnabled(false);
        			rB_USemana_RT.setSelected(false);
        			rB_Mes_RT.setSelected(false);
        			rB_MAnterior_RT.setSelected(false);
        			rB_Anio_RT.setSelected(false);
        			filtrarHoyRT();
        		}
        	}
        });
        rB_Hoy_RT.setToolTipText("Piezas llegadas el dia de hoy.");
        rB_Hoy_RT.setBounds(5, 35, 150, 20);
        pnReclamos_Turnos.add(rB_Hoy_RT);
        
        rB_USemana_RT = new JRadioButton("Ultima Semana");
        rB_USemana_RT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_USemana_RT.isSelected()){
        			rB_Intervalo_RT.setSelected(false);
        			dC_FF_RT.setEnabled(false);
        			dC_FI_RT.setEnabled(false);
        			rB_Hoy_RT.setSelected(false);
        			rB_Mes_RT.setSelected(false);
        			rB_MAnterior_RT.setSelected(false);
        			rB_Anio_RT.setSelected(false);
        			filtrarUSemanaRT();
        		}
        	}
        });
        rB_USemana_RT.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
        rB_USemana_RT.setBounds(5, 60, 150, 20);
        pnReclamos_Turnos.add(rB_USemana_RT);
        
        rB_Mes_RT = new JRadioButton("Este Mes");
        rB_Mes_RT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Mes_RT.isSelected()){
        			rB_Intervalo_RT.setSelected(false);
        			dC_FF_RT.setEnabled(false);
        			dC_FI_RT.setEnabled(false);
        			rB_Hoy_RT.setSelected(false);
        			rB_USemana_RT.setSelected(false);
        			rB_MAnterior_RT.setSelected(false);
        			rB_Anio_RT.setSelected(false);
        			filtrarMesRT();
        		}
        	}
        });
        rB_Mes_RT.setToolTipText("Piezas llegadas en el mes corriente.");
        rB_Mes_RT.setBounds(5, 85, 150, 20);
        pnReclamos_Turnos.add(rB_Mes_RT);
        
        rB_MAnterior_RT = new JRadioButton("Mes Anterior");
        rB_MAnterior_RT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_MAnterior_RT.isSelected()){
        			rB_Intervalo_RT.setSelected(false);
        			dC_FF_RT.setEnabled(false);
        			dC_FI_RT.setEnabled(false);
        			rB_Hoy_RT.setSelected(false);
        			rB_USemana_RT.setSelected(false);
        			rB_Mes_RT.setSelected(false);
        			rB_Anio_RT.setSelected(false);
        			filtrarMAnteriorRT();
        		}
        	}
        });
        rB_MAnterior_RT.setBounds(5, 110, 150, 20);
        pnReclamos_Turnos.add(rB_MAnterior_RT);
        
        rB_Anio_RT = new JRadioButton("Ultimo A\u00F1o");
        rB_Anio_RT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Anio_RT.isSelected()){
        			rB_Intervalo_RT.setSelected(false);
        			dC_FF_RT.setEnabled(false);
        			dC_FI_RT.setEnabled(false);
        			rB_Hoy_RT.setSelected(false);
        			rB_USemana_RT.setSelected(false);
        			rB_Mes_RT.setSelected(false);
        			rB_MAnterior_RT.setSelected(false);
        			filtrarUAnioRT();
        		}
        	}
        });
        rB_Anio_RT.setBounds(5, 135, 150, 20);
        pnReclamos_Turnos.add(rB_Anio_RT);
        
        JLabel label = new JLabel("Fecha Inicio");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setEnabled(false);
        label.setBounds(160, 10, 120, 20);
        pnReclamos_Turnos.add(label);
        
        dC_FI_RT = new JDateChooser();
        dC_FI_RT.setEnabled(false);
        dC_FI_RT.setBounds(290, 10, 150, 20);
        pnReclamos_Turnos.add(dC_FI_RT);
        
        btn_clear_FIRT = new JButton("");
        btn_clear_FIRT.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(dC_FI_RT.getDate()!=null)
				dC_FI_RT.setDate(null);
			}
        });
        btn_clear_FIRT.setIcon(new ImageIcon(GUIReporteDiasFechaReclamo_Turnos.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FIRT.setBounds(450, 10, 25, 20);
        pnReclamos_Turnos.add(btn_clear_FIRT);
        
        JLabel label_1 = new JLabel("Fecha Fin");
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setEnabled(false);
        label_1.setBounds(485, 10, 120, 20);
        pnReclamos_Turnos.add(label_1);
        
        dC_FF_RT = new JDateChooser();
        dC_FF_RT.setEnabled(false);
        dC_FF_RT.setBounds(615, 10, 150, 20);
        pnReclamos_Turnos.add(dC_FF_RT);
        
        btn_clear_FFRT = new JButton("");
        btn_clear_FFRT.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(dC_FF_RT.getDate()!=null)
				dC_FF_RT.setDate(null);
			}
        });
        btn_clear_FFRT.setIcon(new ImageIcon(GUIReporteDiasFechaReclamo_Turnos.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FFRT.setBounds(775, 10, 25, 20);
        pnReclamos_Turnos.add(btn_clear_FFRT);
        
        btn_filtrar_RT = new JButton("Filtrar");
        btn_filtrar_RT.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			filtrarIntervaloRT();
			}
        });
        btn_filtrar_RT.setEnabled(false);
        btn_filtrar_RT.setBounds(810, 10, 110, 20);
        pnReclamos_Turnos.add(btn_filtrar_RT);
        
        btnExportarTabla_RT = new JButton("");
        btnExportarTabla_RT.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			exportarTablaRT();
			}
        });
        btnExportarTabla_RT.setIcon(new ImageIcon(GUIReporteDiasFechaReclamo_Turnos.class.getResource("/cliente/Resources/Icons/formulario.png")));
        btnExportarTabla_RT.setBounds(1230, 165, 32, 32);
        pnReclamos_Turnos.add(btnExportarTabla_RT);
	}

	protected void exportarTablaRT() {
		 try {
				List<JTable> t = new ArrayList<JTable>();
				List<String> n = new ArrayList<String>();
				t.add(tabla_RTR);
				t.add(tabla_RTT);
				n.add("Reclmamos");
				n.add("Turnos");
				ExportarExcel.exportarTablas(t, n);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
			}
	}

	protected void filtrarIntervaloRT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioRT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorRT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesRT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaRT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyRT() {
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
