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
package cliente.GestionarReclamo;

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

import cliente.Recursos.util.JPanel_Whit_Image;

import common.DTOs.AgenteDTO;

public class GUIBuscarAgente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MediadorReclamo mediador;
	
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas;
	private JTable tablaagentes;
	private DefaultTableModel modelo;
	
	
	public GUIBuscarAgente(MediadorReclamo mediadorReclamo) {
		this.mediador = mediadorReclamo;
		cargarDatos();
		initialize();
	}
	
	public void initialize() {
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIBuscarAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 459);
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		tablaagentes = new JTable(modelo) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tablaagentes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablaagentes.setRowSorter(ordenador);
		tablaagentes.getTableHeader().setReorderingAllowed(false);

		tablaagentes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaagentes.setBounds(0, 0, 765, 320);
		tablaagentes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2)
					buscarAgente();
			    else{
			    	e.consume();
			    }   
			}
		});
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaagentes);
		scrollPaneTabla.setBounds(10, 182, 764, 318);
		contentPane.add(scrollPaneTabla);
		
		contentPane.setVisible(true);
		
	}

private void cargarDatos() {
		
	modelo = new DefaultTableModel();

	nombreColumnas = new Vector<String> ();
	nombreColumnas.add("ID Registrante");
	nombreColumnas.add("Nombre Registrante");

	datosTabla = new Vector<Vector<String>>();
	Vector<AgenteDTO> agentes = mediador.obtenerAgentes();
	for (int i=0; i< agentes.size();i++){
		Vector<String> row = new Vector<String> ();
		row.add(agentes.elementAt(i).getId().toString());
		row.add(agentes.elementAt(i).getNombre_registrante());
		datosTabla.add(row);
	};
	modelo.setDataVector(datosTabla, nombreColumnas);
	tablaagentes = new JTable(modelo) {
		private static final long serialVersionUID = 1L;
		boolean[] columnEditables = new boolean[] {
			false, false, false, false
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};
}


	public void buscarAgente() {
		int row = tablaagentes.getSelectedRow();
		if (row >= 0) {		
			mediador.setAgente(tablaagentes.getValueAt(row, 0).toString());
			dispose();
		}
	}

}
