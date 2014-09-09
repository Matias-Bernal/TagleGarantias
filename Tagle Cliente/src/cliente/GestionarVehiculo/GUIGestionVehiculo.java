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
package cliente.GestionarVehiculo;

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

import common.DTOs.VehiculoDTO;

public class GUIGestionVehiculo extends JFrame{
	private static final long serialVersionUID = 1L;

	protected MediadorVehiculo mediador;
	
	private JPanel contentPane;
	
	private JTextField tFnombre_titular;
	private JTextField tFDominio;
	private JTextField tFVin;
	private JLabel lblVin;
	private JLabel lblMarca;
	private JLabel lblModelo;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnAgregar;
	private JButton btnImprimir;
	private JButton btnVolver;
	private JButton btnActualizar;	
	
	
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas;
	private JTable tablavehiculos;
	private DefaultTableModel modelo;
	
	private JComboBox<String> comboBox_marca;
	private DefaultComboBoxModel<String> cmbMarcas;
	private JComboBox<String> comboBox_modelo;
	private DefaultComboBoxModel<String> cmbModelos;
	private Vector<String> modelos;
	private Vector<String> marcas;
	private JButton btnExportarTabla;


	public GUIGestionVehiculo(MediadorVehiculo mediadorRegistrante) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIGestionVehiculo.class.getResource("/cliente/Resources/Icons/vehiculo.png")));
		this.mediador = mediadorRegistrante;
		cargarDatos();
		initialize();
		
	}

	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("GESTIONAR VEHICULO");
		setResizable(false);
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tFnombre_titular = new JTextField();
		tFnombre_titular.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFnombre_titular.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorNombre_titular();
			}
		});
		tFnombre_titular.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});
		tFnombre_titular.setBounds(190, 30, 230, 20);
		contentPane.add(tFnombre_titular);
		tFnombre_titular.setColumns(10);
		
		btnModificar = new GlossyButton("MODIFICAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar.setIcon(new ImageIcon(GUIGestionVehiculo.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(565, 70, 220, 23);
		contentPane.add(btnModificar);
		
		btnEliminar = new GlossyButton("ELIMINAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnEliminar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnEliminar.setIcon(new ImageIcon(GUIGestionVehiculo.class.getResource("/cliente/Resources/Icons/delete.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminar();
			}
		});
		btnEliminar.setBounds(565, 110, 220, 23);
		contentPane.add(btnEliminar);
		
		JLabel lbl_nombre_titular = new JLabel("Nombre del Titular");
		lbl_nombre_titular.setBorder(null);
		lbl_nombre_titular.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nombre_titular.setBounds(10, 31, 170, 20);
		contentPane.add(lbl_nombre_titular);
		
		JLabel lbl_dominio = new JLabel("Dominio");
		lbl_dominio.setBorder(null);
		lbl_dominio.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_dominio.setBounds(10, 61, 170, 20);
		contentPane.add(lbl_dominio);
		

		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		tablavehiculos = new JTable(modelo) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tablavehiculos.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablavehiculos.setRowSorter(ordenador);
		//
		tablavehiculos.getTableHeader().setReorderingAllowed(false);

		tablavehiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablavehiculos.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablavehiculos);
		scrollPaneTabla.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneTabla.setBounds(10, 182, 774, 335);
		contentPane.add(scrollPaneTabla);
		
		btnAgregar = new GlossyButton("AGREGAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnAgregar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregar.setIcon(new ImageIcon(GUIGestionVehiculo.class.getResource("/cliente/Resources/Icons/add.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.altaVehiculo(tFnombre_titular.getText(), tFDominio.getText(), tFVin.getText(), (String)comboBox_marca.getSelectedItem(), (String)comboBox_modelo.getSelectedItem());
			}
		});
		btnAgregar.setBounds(565, 30, 220, 23);
		contentPane.add(btnAgregar);
		
		btnImprimir = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnImprimir.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnImprimir.setIcon(new ImageIcon(GUIGestionVehiculo.class.getResource("/cliente/Resources/Icons/printer.png")));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tablavehiculos.print();
				} catch (PrinterException ex) {
					JOptionPane.showMessageDialog(contentPane,"Error al imprimir.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnImprimir.setBounds(710, 140, 32, 32);
		contentPane.add(btnImprimir);
		
		btnVolver = new GlossyButton("VOLVER",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUEGREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVolver.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnVolver.setIcon(new ImageIcon(GUIGestionVehiculo.class.getResource("/cliente/Resources/Icons/back.png")));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(322, 528, 150, 23);
		contentPane.add(btnVolver);
		
		btnActualizar = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnActualizar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnActualizar.setIcon(new ImageIcon(GUIGestionVehiculo.class.getResource("/cliente/Resources/Icons/1refresh.png")));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		btnActualizar.setBounds(750, 140, 32, 32);
		contentPane.add(btnActualizar);
		
		tFDominio = new JTextField();
		tFDominio.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFDominio.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorDominio();
			}
		});
		tFDominio.setColumns(10);
		tFDominio.setBounds(190, 60, 100, 20);
		contentPane.add(tFDominio);
		
		lblVin = new JLabel("VIN");
		lblVin.setHorizontalAlignment(SwingConstants.CENTER);
		lblVin.setBorder(null);
		lblVin.setBounds(10, 91, 170, 20);
		contentPane.add(lblVin);
		
		tFVin = new JTextField();
		tFVin.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFVin.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorVin();
			}
		});
		tFVin.setColumns(10);
		tFVin.setBounds(190, 90, 230, 20);
		contentPane.add(tFVin);
		
		lblMarca = new JLabel("Marca");
		lblMarca.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarca.setBorder(null);
		lblMarca.setBounds(10, 121, 170, 20);
		contentPane.add(lblMarca);
		
		lblModelo = new JLabel("Modelo");
		lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
		lblModelo.setBorder(null);
		lblModelo.setBounds(10, 151, 170, 20);
		contentPane.add(lblModelo);
		
		comboBox_marca = new JComboBox<String>();
		comboBox_marca.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cmbMarcas = new DefaultComboBoxModel<String>(marcas);
		comboBox_marca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filtrarPorMarca();
			}
		});
		comboBox_marca.setModel(cmbMarcas);
		comboBox_marca.setBounds(190, 120, 230, 20);
		contentPane.add(comboBox_marca);
		
		comboBox_modelo = new JComboBox<String>();
		comboBox_modelo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cmbModelos = new DefaultComboBoxModel<String>(modelos);
		comboBox_modelo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filtrarPorModelo();
			}
		});
		comboBox_modelo.setModel(cmbModelos);
		comboBox_modelo.setBounds(190, 150, 230, 20);
		contentPane.add(comboBox_modelo);
		
		btnExportarTabla = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_METALIC_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnExportarTabla.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnExportarTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablas();
			}
		});
		btnExportarTabla.setIcon(new ImageIcon(GUIGestionVehiculo.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTabla.setBounds(670, 140, 32, 32);
		contentPane.add(btnExportarTabla);
		


	}
	protected void exportarTablas() {
		try {
			ExportarExcel.exportarUnaTabla(tablavehiculos, "Vehiculos");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// Gestion
	
	protected void modificar() {
		int row = tablavehiculos.getSelectedRow();
		if (row >= 0) {
			int aux = tablavehiculos.convertRowIndexToModel(row);
			Long id = new Long (tablavehiculos.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Modificar Vehiculo [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionVehiculo.class.getResource("/cliente/Resources/Icons/edit.png"))) == JOptionPane.YES_OPTION){ 
				mediador.modificarVehiculo(id);;
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un vehiculo primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	protected void eliminar() {
		int row = tablavehiculos.getSelectedRow();
		if (row >= 0) {
			int aux = tablavehiculos.convertRowIndexToModel(row);
			Long id = new Long (tablavehiculos.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Eliminar Vehiculo [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionVehiculo.class.getResource("/cliente/Resources/Icons/delete.png"))) == JOptionPane.YES_OPTION){ 
				mediador.eliminarVehiculo(id);;
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un vehiculo primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	// Filtros
	protected void filtrarPorVin() {
		String filtro = tFVin.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(registrante.elementAt(3).toLowerCase());
			if (mat.find()) {
				datos.add(registrante);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
	}

	protected void filtrarPorDominio() {
		String filtro = tFDominio.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(registrante.elementAt(2).toLowerCase());
			if (mat.find()) {
				datos.add(registrante);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
	}

	protected void filtrarPorNombre_titular() {
		String filtro = tFnombre_titular.getText().toLowerCase();
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
	protected void filtrarPorMarca() {
		String filtro = comboBox_marca.getSelectedItem().toString().toLowerCase();
		Vector<Vector<String>> datos = new Vector<Vector<String>>();
		for (int i=0; i< datosTabla.size();i++){
			Vector<String> registrante = datosTabla.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(registrante.elementAt(4).toLowerCase());
			if (mat.find()) {
				datos.add(registrante);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
	}

	protected void filtrarPorModelo() {
		String filtro = comboBox_modelo.getSelectedItem().toString().toLowerCase();
		Vector<Vector<String>> datos = new Vector<Vector<String>>();
		for (int i=0; i< datosTabla.size();i++){
			Vector<String> registrante = datosTabla.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(registrante.elementAt(5).toLowerCase());
			if (mat.find()) {
				datos.add(registrante);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
	}
	
	
	private void cargarDatos() {
		
		marcas = new Vector<String>();
		marcas.add("");
		Vector<String> nombre_marcas = mediador.obtenerMarcas();
		for (int i=0; i< nombre_marcas.size();i++){
			marcas.add(nombre_marcas.elementAt(i));
		}
		
		modelos = new Vector<String>();
		modelos.add("");
		Vector<String> nombre_modelos = mediador.obtenerModelos();
		for (int i=0; i< nombre_modelos.size();i++){
			modelos.add(nombre_modelos.elementAt(i));
		}
		
		modelo = new DefaultTableModel();

		nombreColumnas = new Vector<String> ();
		nombreColumnas.add("ID Vehiculo");
		nombreColumnas.add("Nombre Titular");
		nombreColumnas.add("Dominio");
		nombreColumnas.add("VIN");
		nombreColumnas.add("Marca");
		nombreColumnas.add("Modelo");
	
		datosTabla = new Vector<Vector<String>>();
		Vector<VehiculoDTO> vehiculos = mediador.obtenerVehiculos();
		for (int i=0; i< vehiculos.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(vehiculos.elementAt(i).getId().toString());
			row.add(vehiculos.elementAt(i).getNombre_titular());
			row.add(vehiculos.elementAt(i).getDominio());
			row.add(vehiculos.elementAt(i).getVin());
			row.add(vehiculos.elementAt(i).getMarca().getNombre_marca());
			row.add(vehiculos.elementAt(i).getModelo().getNombre_modelo());
			datosTabla.add(row);
		}
		modelo.setDataVector(datosTabla, nombreColumnas);
		tablavehiculos = new JTable(modelo) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
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
		marcas = new Vector<String>();
		marcas.add("");
		Vector<String> nombre_marcas = mediador.obtenerMarcas();
		for (int i=0; i< nombre_marcas.size();i++){
			marcas.add(nombre_marcas.elementAt(i));
		}
		cmbMarcas = new DefaultComboBoxModel<String>(marcas);
		comboBox_marca.setModel(cmbMarcas);
		
		modelos = new Vector<String>();
		modelos.add("");
		Vector<String> nombre_modelos = mediador.obtenerModelos();
		for (int i=0; i< nombre_modelos.size();i++){
			modelos.add(nombre_modelos.elementAt(i));
		}
		cmbModelos = new DefaultComboBoxModel<String>(modelos);
		comboBox_modelo.setModel(cmbModelos);
		
		datosTabla = new Vector<Vector<String>>();
		Vector<VehiculoDTO> vehiculos = mediador.obtenerVehiculos();
		for (int i=0; i< vehiculos.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(vehiculos.elementAt(i).getId().toString());
			row.add(vehiculos.elementAt(i).getNombre_titular());
			row.add(vehiculos.elementAt(i).getDominio());
			row.add(vehiculos.elementAt(i).getVin());
			row.add(vehiculos.elementAt(i).getMarca().getNombre_marca());
			row.add(vehiculos.elementAt(i).getModelo().getNombre_modelo());
			datosTabla.add(row);
		}
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		tFnombre_titular.setText("");
		tFDominio.setText("");
		tFVin.setText("");
		comboBox_modelo.setSelectedIndex(0);
		comboBox_marca.setSelectedIndex(0);
	}

}