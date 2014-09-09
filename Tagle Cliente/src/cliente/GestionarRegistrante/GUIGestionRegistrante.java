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
package cliente.GestionarRegistrante;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cliente.Recursos.Botones.ButtonType;
import cliente.Recursos.Botones.GlossyButton;
import cliente.Recursos.util.JPanel_Whit_Image;
import cliente.Recursos.util.Theme;
import cliente.excellexport.ExportarExcel;

import common.DTOs.RegistranteDTO;

public class GUIGestionRegistrante extends JFrame {

	private static final long serialVersionUID = 1L;

	protected MediadorRegistrante mediador;
	
	private JPanel contentPane;
	
	private JTextField tFnombre_registrante;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnAgregar;
	private JButton btnImprimir;
	private JButton btnVolver;
	private JButton btnActualizar;
	
	//Tabla
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas;
	private JTable tableRegistrantes;
	private DefaultTableModel modelo;
	// ComboBox de Registrantes
	private JComboBox<String> comboBox;
	private Vector<String> tiposRegistrantes;
	private JButton btnExportarTablas;

	public GUIGestionRegistrante(MediadorRegistrante mediadorRegistrante) {
		
		this.mediador = mediadorRegistrante;
		cargarDatos();
		initialize();

	}
	
	@SuppressWarnings("serial")
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIGestionRegistrante.class.getResource("/cliente/Resources/Icons/registrantes.png")));
		setBounds(100, 100, 800, 600);
		setTitle("GESTIONAR REGISTRANTE");
		setResizable(false);
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tFnombre_registrante = new JTextField();
		tFnombre_registrante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFnombre_registrante.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorNombre();
			}
		});
		tFnombre_registrante.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub				
			}
		});
		tFnombre_registrante.setBounds(192, 37, 230, 20);
		contentPane.add(tFnombre_registrante);
		tFnombre_registrante.setColumns(10);
		
		btnModificar = new GlossyButton("MODIFICAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar.setIcon(new ImageIcon(GUIGestionRegistrante.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(565, 70, 220, 23);
		contentPane.add(btnModificar);
		
		btnEliminar = new GlossyButton("ELIMINAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnEliminar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnEliminar.setIcon(new ImageIcon(GUIGestionRegistrante.class.getResource("/cliente/Resources/Icons/delete.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminar();
			}
		});
		btnEliminar.setBounds(565, 110, 220, 23);
		contentPane.add(btnEliminar);
		
		JLabel lbl_nombre_registrante = new JLabel("Nombre del Registrante");
		lbl_nombre_registrante.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nombre_registrante.setBounds(10, 35, 182, 24);
		contentPane.add(lbl_nombre_registrante);
		
		JLabel lbl_tipo_registrante = new JLabel("Tipo");
		lbl_tipo_registrante.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_tipo_registrante.setBounds(10, 70, 182, 24);
		contentPane.add(lbl_tipo_registrante);
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		
		tableRegistrantes = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableRegistrantes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tableRegistrantes.setRowSorter(ordenador);
		//
		tableRegistrantes.getTableHeader().setReorderingAllowed(false);
		tableRegistrantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableRegistrantes.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tableRegistrantes);
		scrollPaneTabla.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneTabla.setBounds(10, 182, 775, 342);
		contentPane.add(scrollPaneTabla);
		
		comboBox = new JComboBox<String>();
		comboBox.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filtrarPorTipo();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<String>(tiposRegistrantes));
		comboBox.setBounds(192, 72, 230, 20);
		contentPane.add(comboBox);
		
		btnAgregar = new GlossyButton("AGREGAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnAgregar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregar.setIcon(new ImageIcon(GUIGestionRegistrante.class.getResource("/cliente/Resources/Icons/add.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.altaRegistrante(tFnombre_registrante.getText(), (String) comboBox.getSelectedItem());
			}
		});
		btnAgregar.setBounds(565, 30, 220, 23);
		contentPane.add(btnAgregar);
		
		btnImprimir = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnImprimir.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnImprimir.setIcon(new ImageIcon(GUIGestionRegistrante.class.getResource("/cliente/Resources/Icons/printer.png")));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tableRegistrantes.print();
				} catch (PrinterException ex) {
					JOptionPane.showMessageDialog(contentPane,"Error al imprimir.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnImprimir.setBounds(710, 140, 32, 32);
		contentPane.add(btnImprimir);
		
		btnVolver = new GlossyButton("VOLVER",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUEGREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVolver.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnVolver.setIcon(new ImageIcon(GUIGestionRegistrante.class.getResource("/cliente/Resources/Icons/back.png")));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(322, 535, 150, 23);
		contentPane.add(btnVolver);
		
		btnActualizar = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnActualizar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnActualizar.setIcon(new ImageIcon(GUIGestionRegistrante.class.getResource("/cliente/Resources/Icons/1refresh.png")));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		btnActualizar.setBounds(750, 140, 32, 32);
		contentPane.add(btnActualizar);
		
		btnExportarTablas = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_METALIC_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnExportarTablas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnExportarTablas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablas();
			}
		});
		btnExportarTablas.setIcon(new ImageIcon(GUIGestionRegistrante.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTablas.setBounds(670, 140, 32, 32);
		contentPane.add(btnExportarTablas);
	}

	protected void exportarTablas() {
		try {
			ExportarExcel.exportarUnaTabla(tableRegistrantes, "Registrantes");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	//Gestion
	protected void modificar() {
		int row = tableRegistrantes.getSelectedRow();
		if (row >= 0) {
			int aux = tableRegistrantes.convertRowIndexToModel(row);
			Long id = new Long (tableRegistrantes.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Modificar Registrante [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionRegistrante.class.getResource("/cliente/Resources/Icons/edit.png")) ) == JOptionPane.YES_OPTION){ 
				if (tableRegistrantes.getValueAt(aux, 2).toString().equals("Agente")){
					mediador.modificarAgente(id);
				}else{
					mediador.modificarEntidad(id);
				}
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un regsitrante primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	protected void eliminar() {
		int row = tableRegistrantes.getSelectedRow();
		if (row >= 0) {
			int aux = tableRegistrantes.convertRowIndexToModel(row);
			Long id = new Long (tableRegistrantes.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Eliminar Registrante [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionRegistrante.class.getResource("/cliente/Resources/Icons/delete.png"))) == JOptionPane.YES_OPTION){ 
				if (tableRegistrantes.getValueAt(aux, 2).toString().equals("Agente")){
					if (mediador.eliminarAgente(id)){
						JOptionPane.showMessageDialog(contentPane,"Registrante eliminado.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
						actualizarDatos();
					}

				}else{
					if (mediador.eliminarEntidad(id)){
						JOptionPane.showMessageDialog(contentPane,"Registrante eliminado.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
						actualizarDatos();
					}
				}
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un regsitrante primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

	//Filtros
	protected void filtrarPorNombre() {
		String filtro = tFnombre_registrante.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(registrante.elementAt(1).toLowerCase());
			if (mat.find()) {
				datos.add(registrante);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
	}
	public void filtrarPorTipo(){
		String filtro = comboBox.getSelectedItem().toString().toLowerCase();
		Vector<Vector<String>> datos = new Vector<Vector<String>>();
		for (int i=0; i< datosTabla.size();i++){
			Vector<String> registrante = datosTabla.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(registrante.elementAt(2).toLowerCase());
			if (mat.find()) {
				datos.add(registrante);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
	}

	public void cargarDatos(){
		modelo = new DefaultTableModel();

		tiposRegistrantes = new Vector<String>();
		tiposRegistrantes.add("");
		tiposRegistrantes.add("Agente");
		tiposRegistrantes.add("Entidad");

		nombreColumnas = new Vector<String> ();
		nombreColumnas.add("ID Registrante");
		nombreColumnas.add("Nombre Registrante");
		nombreColumnas.add("Tipo");

		datosTabla = new Vector<Vector<String>>();
		Vector<RegistranteDTO> registrantes = mediador.obtenerRegistrantes();
		for (int i=0; i< registrantes.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(registrantes.elementAt(i).getId().toString());
			row.add(registrantes.elementAt(i).getNombre_registrante());
			if (mediador.esAgente(registrantes.elementAt(i).getId().toString())){
				row.add("Agente");
			}else{
				row.add("Entidad");
			}
			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		tableRegistrantes = new JTable(modelo) {
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
	public void actualizarDatos() {
		datosTabla = new Vector<Vector<String>>();
		Vector<RegistranteDTO> registrantes = mediador.obtenerRegistrantes();
		for (int i=0; i< registrantes.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(registrantes.elementAt(i).getId().toString());
			row.add(registrantes.elementAt(i).getNombre_registrante());
			if (mediador.esAgente(registrantes.elementAt(i).getId().toString())){
				row.add("Agente");
			}else{
				row.add("Entidad");
			}
			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
		tFnombre_registrante.setText("");
		comboBox.setSelectedIndex(0);
	}

}
