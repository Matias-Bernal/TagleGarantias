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
package cliente.ReclamoPiezas;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cliente.Recursos.Botones.ButtonType;
import cliente.Recursos.Botones.GlossyButton;
import cliente.Recursos.util.JPanel_Whit_Image;
import cliente.Recursos.util.Theme;
import cliente.excellexport.ExportarExcel;

import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.Pedido_Pieza_Reclamo_FabricaDTO;
import common.DTOs.Reclamo_FabricaDTO;

public class GUIVerReclamosFabrica extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MediadorReclamoPiezas mediador;
	private Pedido_PiezaDTO pedido_pieza;
	private JTable tabla_reclamos;
	private JButton btnVerReclamo;
	private JButton btnVolver;
	private DefaultTableModel modelo_tabla_reclamos;
	private Vector<Vector<String>> datosTabla_tabla_reclamos;
	private Vector<String> nombreColumnas_tabla_reclamos;
	private Vector<Integer> anchos_tabla_reclamos;
	private Vector<Pedido_Pieza_Reclamo_FabricaDTO> reclamos_fabrica;
	private JButton btnExportarTabla;


	public GUIVerReclamosFabrica(MediadorReclamoPiezas mediador, Pedido_PiezaDTO pedido_pieza) {
		this.mediador = mediador;
		this.pedido_pieza = pedido_pieza;
		cargarDatos();
		initialize();
	}
	private void cargarDatos() {
		int mediano = 150;
		int grande = 230;
		
		//TABLA RECLAMOS FABRICA
		anchos_tabla_reclamos = new Vector<Integer>();	
		nombreColumnas_tabla_reclamos = new Vector<String>();

		nombreColumnas_tabla_reclamos.add("ID Reclamo Fabrica");//0
		anchos_tabla_reclamos.add(75);
		nombreColumnas_tabla_reclamos.add("Numero Pedido");//1
		anchos_tabla_reclamos.add(mediano);
		nombreColumnas_tabla_reclamos.add("Numero Pieza");//2
		anchos_tabla_reclamos.add(mediano);
		nombreColumnas_tabla_reclamos.add("Descripcion Pieza");//4
		anchos_tabla_reclamos.add(grande);
		nombreColumnas_tabla_reclamos.add("Fecha Reclamo");//3
		anchos_tabla_reclamos.add(grande);
		nombreColumnas_tabla_reclamos.add("Descripcion Reclamo");//4
		anchos_tabla_reclamos.add(grande);
		nombreColumnas_tabla_reclamos.add("Usuario");//5
		anchos_tabla_reclamos.add(grande);
		
		modelo_tabla_reclamos = new DefaultTableModel();
		datosTabla_tabla_reclamos = new Vector<Vector<String>>();
		reclamos_fabrica = mediador.obtenerReclamoFabrica(pedido_pieza);
		
		for (int i=0; i< reclamos_fabrica.size();i++){
			Vector<String> row = new Vector<String> ();
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			
			row.add(reclamos_fabrica.elementAt(i).getId().toString());//ID Reclamo Fabrica
			row.add(pedido_pieza.getNumero_pedido());//Numero Pedido
			row.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
			row.add(pedido_pieza.getPieza().getDescripcion());//Decripcion Pieza
			if(reclamos_fabrica.elementAt(i).getReclamo_fabrica().getFecha_reclamo_fabrica()!=null){
				java.sql.Date freclamo = new java.sql.Date(reclamos_fabrica.elementAt(i).getReclamo_fabrica().getFecha_reclamo_fabrica().getTime());
				row.add(format2.format(freclamo));//Fecha Reclamo
			}else{
				row.add("");
			}
			row.add(reclamos_fabrica.elementAt(i).getReclamo_fabrica().getDescripcion());//Decripcion Reclamo
			row.add(reclamos_fabrica.elementAt(i).getReclamo_fabrica().getUsuario().getNombre_usuario());//Usuario
			
			datosTabla_tabla_reclamos.add(row);
		}
		modelo_tabla_reclamos.setDataVector(datosTabla_tabla_reclamos, nombreColumnas_tabla_reclamos);
		modelo_tabla_reclamos.fireTableStructureChanged();

	}
	public void initialize(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIVerReclamosFabrica.class.getResource("/cliente/Resources/Icons/entidad.png")));
		setResizable(false);
		setTitle("RECLAMOS FABRICA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 501);
		contentPane = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		modelo_tabla_reclamos = new DefaultTableModel(datosTabla_tabla_reclamos, nombreColumnas_tabla_reclamos);
		
		tabla_reclamos = new JTable(modelo_tabla_reclamos) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tabla_reclamos.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tabla_reclamos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2)
					
					verReclamo();
			    else{
			    	e.consume();
			    }   
			}
		});
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_reclamos_fabrica = new TableRowSorter<TableModel>(modelo_tabla_reclamos);
		tabla_reclamos.setRowSorter(ordenador_reclamos_fabrica);
		//
		tabla_reclamos.getTableHeader().setReorderingAllowed(false);
		for(int i = 0; i < tabla_reclamos.getColumnCount(); i++) {
			tabla_reclamos.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_reclamos.elementAt(i));
			tabla_reclamos.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_reclamos.elementAt(i));
		}
		tabla_reclamos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPaneTabla_reclamos_fabrica = new JScrollPane(tabla_reclamos);
		scrollPaneTabla_reclamos_fabrica.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneTabla_reclamos_fabrica.setViewportView(tabla_reclamos);
		tabla_reclamos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		scrollPaneTabla_reclamos_fabrica.setBounds(10, 50, 674, 375);
		contentPane.add(scrollPaneTabla_reclamos_fabrica);
				
		btnVerReclamo = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIME_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerReclamo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnVerReclamo.setIcon(new ImageIcon(GUIVerReclamosFabrica.class.getResource("/cliente/Resources/Icons/find_reclamo.png")));
		btnVerReclamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verReclamo();
			}
		});
		btnVerReclamo.setBounds(610, 7, 32, 32);
		contentPane.add(btnVerReclamo);
		
		btnVolver = new GlossyButton("VOLVER",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUEGREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVolver.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnVolver.setIcon(new ImageIcon(GUIVerReclamosFabrica.class.getResource("/cliente/Resources/Icons/back.png")));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVolver.setBounds(282, 430, 130, 25);
		contentPane.add(btnVolver);
		
		btnExportarTabla = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_METALIC_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnExportarTabla.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnExportarTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablas();
			}
		});
		btnExportarTabla.setIcon(new ImageIcon(GUIVerReclamosFabrica.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTabla.setBounds(652, 7, 32, 32);
		contentPane.add(btnExportarTabla);
	}
	protected void exportarTablas() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_reclamos, "Reclamos Fabrica");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	protected void verReclamo() {
		int row = tabla_reclamos.getSelectedRow();
		if (row >= 0) {
			int aux = tabla_reclamos.convertRowIndexToModel(row);
			Long id = new Long (tabla_reclamos.getValueAt(aux, 0).toString());

			Reclamo_FabricaDTO reclamo_fabrica = mediador.buscarReclamoFabrica(id);
			mediador.verReclamoFabrica(reclamo_fabrica);
		}else{
			JOptionPane.showMessageDialog(null,"Seleccione un reclamo primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
