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

public class GUIReporteManoDeObra extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private JPanel contentPane;
	private JPanel pnManoObra;

	//tabla mano obra
	private float hs_mano_obra;
	private float preico_mano_obra;
	private JTable tabla_MO;
	private JScrollPane scrollPane_mano_obra;
	private DefaultTableModel modelo_tabla_mano_obra;
	private Vector<Vector<String>> datos_tabla_mano_obra;
	private Vector<String> nombreColumnas_tabla_mano_obra;
	private Vector<Integer> anchos_tabla_mano_obra;
	private JLabel lblValorTotalDe;
	private JLabel lblHorasTotalesDe;
	private JLabel lbl_HsTotalesMO;
	private JLabel lbl_ValorTotalMO;
	private JRadioButton rB_Anio_MO;
	private JRadioButton rB_MAnterior_MO;
	private JRadioButton rB_Mes_MO;
	private JRadioButton rB_USemana_MO;
	private JRadioButton rB_Hoy_MO;
	private JRadioButton rB_Intervalo_MO;
	private JDateChooser dC_FI_MO;
	private JButton btn_clear_FIMO;
	private JDateChooser dC_FF_MO;
	private JButton btn_clear_FFMO;
	private JButton btn_filtrar_MO;
	private JButton btnExportarTabla_MO;

	//tabla 
	public GUIReporteManoDeObra(MediadorReportes mediadorReporte) {
		setMediador(mediadorReporte);
		cargarDatos();
		initialize();
	}

	private void cargarDatos() {
		Vector<Pedido_PiezaDTO> pedidos_piezas = mediador.obtenerPedido_Piezas();
		int chico = 100;
		int mediano = 150;
		int grande = 230;
		modelo_tabla_mano_obra = new DefaultTableModel();
		nombreColumnas_tabla_mano_obra = new Vector<String> ();
		anchos_tabla_mano_obra = new Vector<Integer>();
		nombreColumnas_tabla_mano_obra.add("ID Pedido");//0
		anchos_tabla_mano_obra.add(75);
		nombreColumnas_tabla_mano_obra.add("Numero Pedido");//1
		anchos_tabla_mano_obra.add(chico);
		nombreColumnas_tabla_mano_obra.add("Numero Pieza");//2
		anchos_tabla_mano_obra.add(chico);
		nombreColumnas_tabla_mano_obra.add("Descripcion");//3
		anchos_tabla_mano_obra.add(mediano);
		nombreColumnas_tabla_mano_obra.add("Numero Orden");//4
		anchos_tabla_mano_obra.add(chico);
		nombreColumnas_tabla_mano_obra.add("VIN");//5
		anchos_tabla_mano_obra.add(130);
		nombreColumnas_tabla_mano_obra.add("Registrante");//6
		anchos_tabla_mano_obra.add(grande);
		nombreColumnas_tabla_mano_obra.add("ID Mano Obra");//8
		anchos_tabla_mano_obra.add(mediano);
		nombreColumnas_tabla_mano_obra.add("Codigo Mano Obra");//9
		anchos_tabla_mano_obra.add(mediano);
		nombreColumnas_tabla_mano_obra.add("Cantidad Mano Obra");//10
		anchos_tabla_mano_obra.add(mediano);
		nombreColumnas_tabla_mano_obra.add("Valor Mano Obra");//11
		anchos_tabla_mano_obra.add(mediano);	
		datos_tabla_mano_obra = new Vector<Vector<String>>();
		hs_mano_obra = 0;
		preico_mano_obra = 0;
		
		for (int i=0; i< pedidos_piezas.size();i++){
			if(pedidos_piezas.elementAt(i).getMano_obra()!=null && mediador.esEntidad(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante())){		
				Vector<String> row = new Vector<String> ();
				row.add(pedidos_piezas.elementAt(i).getPedido().getId().toString());//ID Pedido
				row.add(pedidos_piezas.elementAt(i).getNumero_pedido());//Numero Pedido
				row.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
				row.add(pedidos_piezas.elementAt(i).getPieza().getDescripcion());//Descripcion Pieza
				row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
				row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo().getVin());//VIN
				row.add(pedidos_piezas.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
				if (pedidos_piezas.elementAt(i).getMano_obra()!=null){
					row.add(pedidos_piezas.elementAt(i).getMano_obra().getId().toString());
					row.add(pedidos_piezas.elementAt(i).getMano_obra().getCodigo_mano_obra());
					row.add(String.valueOf(pedidos_piezas.elementAt(i).getMano_obra().getCantidad_horas()));
					hs_mano_obra += pedidos_piezas.elementAt(i).getMano_obra().getCantidad_horas(); 
					row.add(String.valueOf(pedidos_piezas.elementAt(i).getMano_obra().getValor_mano_obra()));
					preico_mano_obra += pedidos_piezas.elementAt(i).getMano_obra().getValor_mano_obra() * pedidos_piezas.elementAt(i).getMano_obra().getCantidad_horas();
				}else{
					row.add("");
					row.add("");
					row.add("");
					row.add("");
				}
				datos_tabla_mano_obra.add(row);
			}
		}
		modelo_tabla_mano_obra.setDataVector(datos_tabla_mano_obra, nombreColumnas_tabla_mano_obra);
		modelo_tabla_mano_obra.fireTableStructureChanged();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTE - MANO DE OBRA");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReporteManoDeObra.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
  
    	pnManoObra = new JPanel();
    	pnManoObra.setBounds(0, 0, 1274, 681);
    	contentPane.add(pnManoObra);
    	pnManoObra.setLayout(null);
        modelo_tabla_mano_obra = new DefaultTableModel(datos_tabla_mano_obra , nombreColumnas_tabla_mano_obra );
    	tabla_MO = new JTable(modelo_tabla_mano_obra) {
    		private static final long serialVersionUID = 1L;
    		boolean[] columnEditables = new boolean[] {
    				false, false, false, false, false,
    				false, false, false,false, false,
    				false, false, false,false, false,
    		};
    		public boolean isCellEditable(int row, int column) {
    			return columnEditables[column];
    		}
    	};
        // Agregamos el ordenador para las tablas de los usuarios 
		TableRowSorter<TableModel> ordenador_tabla_mano_obra = new TableRowSorter<TableModel>(modelo_tabla_mano_obra);
		for(int i = 0; i < tabla_MO.getColumnCount(); i++) {
			tabla_MO.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_mano_obra.elementAt(i));
			tabla_MO.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_mano_obra.elementAt(i));
		}
    	tabla_MO.setRowSorter(ordenador_tabla_mano_obra);
    	tabla_MO.getTableHeader().setReorderingAllowed(false);
    	tabla_MO.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	scrollPane_mano_obra= new JScrollPane(tabla_MO);
    	scrollPane_mano_obra.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	scrollPane_mano_obra.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    	scrollPane_mano_obra.setBounds(5, 160, 1255, 510);
    	pnManoObra.add(scrollPane_mano_obra);
    	tabla_MO.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
    	lblValorTotalDe = new JLabel("VALOR TOTAL DE MANO DE OBRA");
    	lblValorTotalDe.setHorizontalAlignment(SwingConstants.CENTER);
    	lblValorTotalDe.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    	lblValorTotalDe.setBounds(943, 11, 190, 25);
    	pnManoObra.add(lblValorTotalDe);
    	
    	lblHorasTotalesDe = new JLabel("HORAS TOTALES DE MANO DE OBRA");
    	lblHorasTotalesDe.setHorizontalAlignment(SwingConstants.CENTER);
    	lblHorasTotalesDe.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    	lblHorasTotalesDe.setBounds(943, 49, 190, 25);
    	pnManoObra.add(lblHorasTotalesDe);
    	
    	lbl_HsTotalesMO = new JLabel("");
    	lbl_HsTotalesMO.setText(String.valueOf(hs_mano_obra)+"HS");
    	lbl_HsTotalesMO.setHorizontalAlignment(SwingConstants.CENTER);
    	lbl_HsTotalesMO.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    	lbl_HsTotalesMO.setBounds(1155, 49, 100, 25);
    	pnManoObra.add(lbl_HsTotalesMO);
    	
    	lbl_ValorTotalMO = new JLabel("");
    	lbl_ValorTotalMO.setText("$"+String.valueOf(preico_mano_obra));
    	lbl_ValorTotalMO.setHorizontalAlignment(SwingConstants.CENTER);
    	lbl_ValorTotalMO.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    	lbl_ValorTotalMO.setBounds(1155, 11, 100, 25);
    	pnManoObra.add(lbl_ValorTotalMO);
    	
    	rB_Anio_MO = new JRadioButton("Ultimo A\u00F1o");
    	rB_Anio_MO.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Anio_MO.isSelected()){
    				rB_Intervalo_MO.setSelected(false);
    				dC_FF_MO.setEnabled(false);
    				dC_FI_MO.setEnabled(false);
    				rB_Hoy_MO.setSelected(false);
    				rB_USemana_MO.setSelected(false);
    				rB_Mes_MO.setSelected(false);
    				rB_MAnterior_MO.setSelected(false);
    				filtrarUAnioMO();
    			}
    		}
    	});
    	rB_Anio_MO.setBounds(5, 135, 150, 20);
    	pnManoObra.add(rB_Anio_MO);
    	
    	rB_MAnterior_MO = new JRadioButton("Mes Anterior");
    	rB_MAnterior_MO.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_MAnterior_MO.isSelected()){
					rB_Intervalo_MO.setSelected(false);
					dC_FF_MO.setEnabled(false);
					dC_FI_MO.setEnabled(false);
					rB_Hoy_MO.setSelected(false);
					rB_USemana_MO.setSelected(false);
					rB_Mes_MO.setSelected(false);
					rB_Anio_MO.setSelected(false);
					filtrarMAnteriorMO();
				}
			}
		});
    	rB_MAnterior_MO.setBounds(5, 110, 150, 20);
    	pnManoObra.add(rB_MAnterior_MO);
    	
    	rB_Mes_MO = new JRadioButton("Este Mes");
    	rB_Mes_MO.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Mes_MO.isSelected()){
    				rB_Intervalo_MO.setSelected(false);
    				dC_FF_MO.setEnabled(false);
    				dC_FI_MO.setEnabled(false);
    				rB_Hoy_MO.setSelected(false);
    				rB_USemana_MO.setSelected(false);
    				rB_MAnterior_MO.setSelected(false);
    				rB_Anio_MO.setSelected(false);
    				filtrarMesMO();
    			}
    		}
    	});
    	rB_Mes_MO.setToolTipText("Piezas llegadas en el mes corriente.");
    	rB_Mes_MO.setBounds(5, 85, 150, 20);
    	pnManoObra.add(rB_Mes_MO);
    	
    	rB_USemana_MO = new JRadioButton("Ultima Semana");
    	rB_USemana_MO.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_USemana_MO.isSelected()){
    				rB_Intervalo_MO.setSelected(false);
    				dC_FF_MO.setEnabled(false);
    				dC_FI_MO.setEnabled(false);
    				rB_Hoy_MO.setSelected(false);
    				rB_Mes_MO.setSelected(false);
    				rB_MAnterior_MO.setSelected(false);
    				rB_Anio_MO.setSelected(false);
    				filtrarUSemanaMO();
    			}
    		}
    	});
    	rB_USemana_MO.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
    	rB_USemana_MO.setBounds(5, 60, 150, 20);
    	pnManoObra.add(rB_USemana_MO);
    	
    	rB_Hoy_MO = new JRadioButton("Hoy");
    	rB_Hoy_MO.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Hoy_MO.isSelected()){
    				rB_Intervalo_MO.setSelected(false);
    				dC_FF_MO.setEnabled(false);
    				dC_FI_MO.setEnabled(false);
    				rB_USemana_MO.setSelected(false);
    				rB_Mes_MO.setSelected(false);
    				rB_MAnterior_MO.setSelected(false);
    				rB_Anio_MO.setSelected(false);
    				filtrarHoyMO();
    			}
    		}
    	});
    	rB_Hoy_MO.setToolTipText("Piezas llegadas el dia de hoy.");
    	rB_Hoy_MO.setBounds(5, 35, 150, 20);
    	pnManoObra.add(rB_Hoy_MO);
    	
    	rB_Intervalo_MO = new JRadioButton("Intervalo de Fechas");
    	rB_Intervalo_MO.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Intervalo_MO.isSelected()){
					dC_FF_MO.setEnabled(true);
					dC_FI_MO.setEnabled(true);
					btn_filtrar_MO.setEnabled(true);
					rB_Hoy_MO.setSelected(false);
					rB_USemana_MO.setSelected(false);
					rB_Mes_MO.setSelected(false);
					rB_MAnterior_MO.setSelected(false);
					rB_Anio_MO.setSelected(false);
				}else{
					dC_FF_MO.setEnabled(false);
					dC_FI_MO.setEnabled(false);
					btn_filtrar_MO.setEnabled(false);
				}
			}
		});
    	rB_Intervalo_MO.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
    	rB_Intervalo_MO.setBounds(5, 10, 150, 20);
    	pnManoObra.add(rB_Intervalo_MO);
    	
    	JLabel label_14 = new JLabel("Fecha Inicio");
    	label_14.setHorizontalAlignment(SwingConstants.CENTER);
    	label_14.setEnabled(false);
    	label_14.setBounds(160, 10, 120, 20);
    	pnManoObra.add(label_14);
    	
    	dC_FI_MO = new JDateChooser();
    	dC_FI_MO.setEnabled(false);
    	dC_FI_MO.setBounds(290, 10, 150, 20);
    	pnManoObra.add(dC_FI_MO);
    	
    	btn_clear_FIMO = new JButton("");
    	btn_clear_FIMO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FI_MO.getDate()!=null)
					dC_FI_MO.setDate(null);
			}
		});
    	btn_clear_FIMO.setIcon(new ImageIcon(GUIReporteManoDeObra.class.getResource("/cliente/Resources/Icons/clear.png")));
    	btn_clear_FIMO.setBounds(450, 10, 25, 20);
    	pnManoObra.add(btn_clear_FIMO);
    	
    	JLabel label_15 = new JLabel("Fecha Fin");
    	label_15.setHorizontalAlignment(SwingConstants.CENTER);
    	label_15.setEnabled(false);
    	label_15.setBounds(485, 10, 120, 20);
    	pnManoObra.add(label_15);
    	
    	dC_FF_MO = new JDateChooser();
    	dC_FF_MO.setEnabled(false);
    	dC_FF_MO.setBounds(615, 10, 150, 20);
    	pnManoObra.add(dC_FF_MO);
    	
    	btn_clear_FFMO = new JButton("");
    	btn_clear_FFMO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_MO.getDate()!=null)
					dC_FF_MO.setDate(null);
			}
		});
    	btn_clear_FFMO.setIcon(new ImageIcon(GUIReporteManoDeObra.class.getResource("/cliente/Resources/Icons/clear.png")));
    	btn_clear_FFMO.setBounds(775, 10, 25, 20);
    	pnManoObra.add(btn_clear_FFMO);
    	
    	btn_filtrar_MO = new JButton("Filtrar");
    	btn_filtrar_MO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarIntervaloMO();
			}
		});
    	btn_filtrar_MO.setEnabled(false);
    	btn_filtrar_MO.setBounds(810, 10, 110, 20);
    	pnManoObra.add(btn_filtrar_MO);
    	
    	btnExportarTabla_MO = new JButton("");
    	btnExportarTabla_MO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaMO();
			}
		});
    	btnExportarTabla_MO.setIcon(new ImageIcon(GUIReporteManoDeObra.class.getResource("/cliente/Resources/Icons/formulario.png")));
    	btnExportarTabla_MO.setBounds(1230, 120, 32, 32);
    	pnManoObra.add(btnExportarTabla_MO);
	}

	protected void exportarTablaMO() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_MO, "Mano de Obra");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void filtrarIntervaloMO() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyMO() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaMO() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesMO() {

	}

	protected void filtrarMAnteriorMO() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioMO() {
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
