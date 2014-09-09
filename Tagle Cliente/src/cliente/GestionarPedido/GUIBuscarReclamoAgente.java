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
package cliente.GestionarPedido;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import common.DTOs.ReclamoDTO;

public class GUIBuscarReclamoAgente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MediadorPedido mediador;
	
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas;
	private JTable tablaReclamos;
	private DefaultTableModel modelo;
	
	
	public GUIBuscarReclamoAgente(MediadorPedido mediadorPedido) {
		this.mediador = mediadorPedido;
		cargarDatos();
		initialize();
	}
	
	public void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIBuscarReclamoAgente.class.getResource("/cliente/Resources/Icons/find_reclamo.png")));
		setTitle("BUSCAR RECLAMO AGENTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 855, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		tablaReclamos = new JTable(modelo) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tablaReclamos.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablaReclamos.setRowSorter(ordenador);
		//
		tablaReclamos.getTableHeader().setReorderingAllowed(false);

		tablaReclamos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaReclamos.setBounds(0, 0, 765, 320);
		tablaReclamos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2)
					enviarReclamo();
			    else{
			    	e.consume();
			    }   
			}
		});
		JScrollPane scrollPaneTabla = new JScrollPane(tablaReclamos);
		scrollPaneTabla.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneTabla.setBounds(10, 182, 764, 318);
		contentPane.add(scrollPaneTabla);
		
		contentPane.setVisible(true);
	}

private void cargarDatos() {
		
	modelo = new DefaultTableModel();

	nombreColumnas = new Vector<String> ();
	nombreColumnas.add("ID Reclamo");
	nombreColumnas.add("Fecha Reclamo");
	nombreColumnas.add("Fecha Turno");
	nombreColumnas.add("Estado Reclamo");
	nombreColumnas.add("Agente");
	nombreColumnas.add("Nombre Reclamante");
	nombreColumnas.add("DNI Reclamante");
	nombreColumnas.add("Nombre Titular");
	nombreColumnas.add("Dominio");
	nombreColumnas.add("VIN");
	nombreColumnas.add("Numero Orden");


	modelo = new DefaultTableModel();
	datosTabla = new Vector<Vector<String>>();
	Vector<ReclamoDTO> reclamos = mediador.obtenerReclamosAgente();
	for (int i=0; i< reclamos.size();i++){
		if (reclamos.elementAt(i).getEstado_reclamo().equals("ABIERTO/SIN PEDIDO/SIN TURNO")||reclamos.elementAt(i).getEstado_reclamo().equals("ABIERTO/SIN PEDIDO/CON TURNO")){
			Vector<String> row = new Vector<String> ();
			
			row.add(reclamos.elementAt(i).getId().toString());
			if(reclamos.elementAt(i).getFecha_reclamo()!=null){
				row.add(reclamos.elementAt(i).getFecha_reclamo().toString());
			}else{
				row.add("");
			}
			if(reclamos.elementAt(i).getFecha_turno()!=null){
				row.add(reclamos.elementAt(i).getFecha_turno().toString());
			}else{
				row.add("");
			}
			row.add(reclamos.elementAt(i).getEstado_reclamo());
			row.add(reclamos.elementAt(i).getRegistrante().getNombre_registrante());
			if(reclamos.elementAt(i).getReclamante()!=null){
				row.add(reclamos.elementAt(i).getReclamante().getNombre_apellido());
				row.add(reclamos.elementAt(i).getReclamante().getDni());
			}else{
				row.add("");
				row.add("");
			}
			if(reclamos.elementAt(i).getVehiculo()!=null){
				row.add(reclamos.elementAt(i).getVehiculo().getNombre_titular());
				row.add(reclamos.elementAt(i).getVehiculo().getDominio());
				row.add(reclamos.elementAt(i).getVehiculo().getVin());
			}else{
				row.add("");
				row.add("");
				row.add("");
			}
			if(reclamos.elementAt(i).getOrden()!=null){
				row.add(reclamos.elementAt(i).getOrden().getNumero_orden());
			}else{
				row.add("");
			}
			datosTabla.add(row);
		}
	}	
	modelo.setDataVector(datosTabla, nombreColumnas);
	modelo.fireTableStructureChanged();
}

	public void enviarReclamo() {
		int row = tablaReclamos.getSelectedRow();
		if (row >= 0) {		
			mediador.setReclamoAgente(tablaReclamos.getValueAt(row, 0).toString());
			
			dispose();
		}
	}
}
