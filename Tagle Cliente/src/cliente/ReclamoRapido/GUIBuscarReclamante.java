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
package cliente.ReclamoRapido;

import java.awt.BorderLayout;
import java.awt.Color;
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

import cliente.Recursos.util.JPanel_Whit_Image;

import common.DTOs.ReclamanteDTO;
import java.awt.Toolkit;

public class GUIBuscarReclamante extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MediadoReclamoRapido mediador;
	
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas;
	private JTable tablaReclamantes;
	private DefaultTableModel modelo;
	
	
	public GUIBuscarReclamante(MediadoReclamoRapido mediadorReclamo) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIBuscarReclamante.class.getResource("/cliente/Resources/Icons/reclamante.png")));

		this.mediador = mediadorReclamo;
		cargarDatos();
		initialize();
		SetVisible(true);
	}
	
	public void initialize() {
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 459);
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		tablaReclamantes = new JTable(modelo) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tablaReclamantes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablaReclamantes.setRowSorter(ordenador);
		//
		tablaReclamantes.getTableHeader().setReorderingAllowed(false);

		tablaReclamantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaReclamantes.setBounds(0, 0, 765, 320);
		tablaReclamantes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2)
					buscarVehiculo();
			    else{
			    	e.consume();
			    }   
			}
		});
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaReclamantes);
		scrollPaneTabla.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneTabla.setBounds(10, 182, 764, 318);
		contentPane.add(scrollPaneTabla);
		
	}

private void cargarDatos() {
		
	modelo = new DefaultTableModel();

	nombreColumnas = new Vector<String> ();
	nombreColumnas.add("ID Reclamante");
	nombreColumnas.add("Nombre Reclamante");
	nombreColumnas.add("DNI");
	nombreColumnas.add("Email");

	datosTabla = new Vector<Vector<String>>();
	Vector<ReclamanteDTO> reclamantes = mediador.obtenerReclamantes();
	for (int i=0; i< reclamantes.size();i++){
		Vector<String> row = new Vector<String> ();
		
		row.add(reclamantes.elementAt(i).getId().toString());
		row.add(reclamantes.elementAt(i).getNombre_apellido());
		row.add(reclamantes.elementAt(i).getDni());
		row.add(reclamantes.elementAt(i).getEmail());
		datosTabla.add(row);
	};
	modelo.setDataVector(datosTabla, nombreColumnas);
	tablaReclamantes = new JTable(modelo) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		boolean[] columnEditables = new boolean[] {
			false, false, false, false
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};
}
public void actualizarPantalla(){ 
	modelo.fireTableStructureChanged();
}

	public void buscarVehiculo() {
		int row = tablaReclamantes.getSelectedRow();
		if (row >= 0) {		
			mediador.setReclamante(tablaReclamantes.getValueAt(row, 0).toString());
			dispose();
		}
	}
	
	private void SetVisible(boolean b) {
		setVisible(true);
	}


}
