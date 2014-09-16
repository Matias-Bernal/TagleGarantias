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
package cliente.GestionarUsuario;

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
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cliente.Recursos.Botones.ButtonType;
import cliente.Recursos.Botones.GlossyButton;
import cliente.Recursos.util.Theme;
import cliente.Recursos.util.JPanel_Whit_Image;
import cliente.excellexport.ExportarExcel;
import common.DTOs.UsuarioDTO;

import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class GUIGestionUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MediadorUsuario mediador;
	
	private JPanel contentPane;
	
	private int limite = 35;
	private JTextField tFnombre_usuario;
	private JTextField tFemail;
	private JButton btnModificar; 
	private JButton btnEliminar; 
	private JButton btnAgregar; 
	private JButton btnImprimir; 
	private JButton btnVolver; 
	private JButton btnActualizar; 
	private JLabel labelTipoUsuario;
	
	//Tabla
	private DefaultTableModel modelo;
	private JTable tableUsuarios;
	private Vector<Vector<String>> datosTabla;
	private Vector<Integer> anchos;

	private Vector<String> nombreColumnas; 
	//Combobox de Usuarios
	private JComboBox<String> comboBox;
	private Vector<String> tiposUsuarios;	
	private JButton btnExportExcell;

	public GUIGestionUsuario(MediadorUsuario mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
	}
	
	@SuppressWarnings("serial")
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIGestionUsuario.class.getResource("/cliente/Resources/Icons/usuarios.png")));
		setTitle("GESTIONAR USUARIOS");
		setResizable(false);
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelTipoUsuario = new JLabel("Tipo Usuario");
		labelTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		labelTipoUsuario.setBounds(10, 109, 172, 24);
		contentPane.add(labelTipoUsuario);
		
		
		tFnombre_usuario = new JTextField();
		tFnombre_usuario.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFnombre_usuario.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorNombre();
			}
		});
		tFnombre_usuario.setBounds(192, 37, 230, 20);
		tFnombre_usuario.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFnombre_usuario.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {				
			}
		});
		contentPane.add(tFnombre_usuario);
		tFnombre_usuario.setColumns(10);
		
		tFemail = new JTextField();
		tFemail.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFemail.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorEmail();
			}
		});
		tFemail.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFemail.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {		
			}
		});
		tFemail.setBounds(192, 72, 230, 20);
		contentPane.add(tFemail);
		tFemail.setColumns(10);
		
		btnModificar = new GlossyButton("MODIFICAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar.setIcon(new ImageIcon(GUIGestionUsuario.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(565, 70, 220, 23);
		contentPane.add(btnModificar);
		
		btnEliminar = new GlossyButton("ELIMINAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnEliminar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnEliminar.setIcon(new ImageIcon(GUIGestionUsuario.class.getResource("/cliente/Resources/Icons/delete.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		btnEliminar.setBounds(565, 110, 220, 23);
		contentPane.add(btnEliminar);
		
		JLabel lbl_nombre_usuario = new JLabel("Nombre de Usuario");
		lbl_nombre_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nombre_usuario.setBounds(10, 35, 172, 24);
		contentPane.add(lbl_nombre_usuario);
		
		JLabel lbl_email = new JLabel("Email");
		lbl_email.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_email.setBounds(10, 72, 172, 24);
		contentPane.add(lbl_email);
		
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		
		tableUsuarios = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableUsuarios.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tableUsuarios.setRowSorter(ordenador);
		//
		tableUsuarios.getTableHeader().setReorderingAllowed(false);
		for(int i = 0; i < tableUsuarios.getColumnCount(); i++) {
			tableUsuarios.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tableUsuarios.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
		tableUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableUsuarios.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tableUsuarios);
		scrollPaneTabla.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneTabla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTabla.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tableUsuarios.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		scrollPaneTabla.setBounds(10, 180, 775, 340);
		contentPane.add(scrollPaneTabla);

		comboBox = new JComboBox<String>();
		comboBox.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filtrarPorTipo();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<String>(tiposUsuarios));
		comboBox.setBounds(192, 111, 172, 20);
		contentPane.add(comboBox);
		
		btnAgregar = new GlossyButton("AGREGAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnAgregar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregar.setIcon(new ImageIcon(GUIGestionUsuario.class.getResource("/cliente/Resources/Icons/add.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.altaUsuario(tFnombre_usuario.getText(), tFemail.getText(),(String) comboBox.getSelectedItem());
			}
		});
		btnAgregar.setBounds(565, 30, 220, 23);
		contentPane.add(btnAgregar);
		
		btnImprimir = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnImprimir.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnImprimir.setIcon(new ImageIcon(GUIGestionUsuario.class.getResource("/cliente/Resources/Icons/printer.png")));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tableUsuarios.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			}
		});
		btnImprimir.setBounds(710, 140, 32, 32);
		contentPane.add(btnImprimir);
		
		btnVolver = new GlossyButton("VOLVER",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUEGREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVolver.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnVolver.setIcon(new ImageIcon(GUIGestionUsuario.class.getResource("/cliente/Resources/Icons/back.png")));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVolver.setBounds(322, 535, 150, 23);
		contentPane.add(btnVolver);
		
		btnActualizar = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnActualizar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnActualizar.setIcon(new ImageIcon(GUIGestionUsuario.class.getResource("/cliente/Resources/Icons/1refresh.png")));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		btnActualizar.setBounds(750, 140, 32, 32);
		contentPane.add(btnActualizar);
		
		btnExportExcell = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_METALIC_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnExportExcell.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnExportExcell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablas();
			}
		});
		btnExportExcell.setIcon(new ImageIcon(GUIGestionUsuario.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportExcell.setBounds(670, 140, 32, 32);
		contentPane.add(btnExportExcell);
	}
	protected void exportarTablas() {
		try {
			ExportarExcel.exportarUnaTabla(tableUsuarios, "Usuarios");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	//Gestion
	public void modificar(){
		int row = tableUsuarios.getSelectedRow();
		if (row >= 0) {
			int aux = tableUsuarios.convertRowIndexToModel(row);
			Long id = new Long (tableUsuarios.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Modificar Usuario [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionUsuario.class.getResource("/cliente/Resources/Icons/edit.png"))) == JOptionPane.YES_OPTION){ 
				mediador.modificarUsuario(id);
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un Usuario primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void eliminar(){
		int row = tableUsuarios.getSelectedRow();
		if (row >= 0) {
			int aux = tableUsuarios.convertRowIndexToModel(row);
			Long id = new Long (tableUsuarios.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Eliminar Usuario [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionUsuario.class.getResource("/cliente/Resources/Icons/delete.png"))) == JOptionPane.YES_OPTION){ 
				if (mediador.eliminarUsuario(id)){
					JOptionPane.showMessageDialog(contentPane,"Usuario eliminado.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
					actualizarDatos();
				}
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un usuario primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	// Filtros //
	public void filtrarPorNombre(){
		String filtro = tFnombre_usuario.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>>usuarios = datosTabla;
		for (int i=0; i< usuarios.size();i++){
			Vector<String> usuario = usuarios.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(usuario.elementAt(1).toLowerCase());
			if (mat.find()) {
				datos.add(usuario);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tableUsuarios.getColumnCount(); i++) {
			tableUsuarios.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tableUsuarios.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	public void filtrarPorEmail(){
		String filtro = tFemail.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>>usuarios = datosTabla;
		for (int i=0; i< usuarios.size();i++){
			Vector<String> usuario = usuarios.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(usuario.elementAt(2).toLowerCase());
			if (mat.find()) {
				datos.add(usuario);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tableUsuarios.getColumnCount(); i++) {
			tableUsuarios.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tableUsuarios.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	public void filtrarPorTipo(){
		String filtro = comboBox.getSelectedItem().toString().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>>usuarios = datosTabla;
		for (int i=0; i< usuarios.size();i++){
			Vector<String> usuario = usuarios.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(usuario.elementAt(3).toLowerCase());
			if (mat.find()) {
				datos.add(usuario);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tableUsuarios.getColumnCount(); i++) {
			tableUsuarios.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tableUsuarios.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	
	public void cargarDatos(){
		
		modelo = new DefaultTableModel();

		tiposUsuarios = new Vector<String>();
		tiposUsuarios.add("");
		tiposUsuarios.add("Administrativo");
		tiposUsuarios.add("Encargado Repuesto");

		nombreColumnas = new Vector<String> ();
		anchos = new Vector<Integer>();

		int grande = 227;
		
		nombreColumnas.add("ID Usuario");
		anchos.add(75);
		nombreColumnas.add("Nombre Usuario");
		anchos.add(grande);
		nombreColumnas.add("Email");
		anchos.add(grande);
		nombreColumnas.add("Tipo");
		anchos.add(grande);
		
		datosTabla = new Vector<Vector<String>>();
		Vector<UsuarioDTO> usuarios = mediador.obtenerUsuarios();
		for (int i=0; i< usuarios.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(usuarios.elementAt(i).getId().toString());
			row.add(usuarios.elementAt(i).getNombre_usuario());
			row.add(usuarios.elementAt(i).getEmail());
			row.add(usuarios.elementAt(i).getTipo());
			
			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
	}

	public void actualizarDatos(){
		datosTabla = new Vector<Vector<String>>();
		Vector<UsuarioDTO> usuarios = mediador.obtenerUsuarios();
		for (int i=0; i< usuarios.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(usuarios.elementAt(i).getId().toString());
			row.add(usuarios.elementAt(i).getNombre_usuario());
			row.add(usuarios.elementAt(i).getEmail());
			row.add(usuarios.elementAt(i).getTipo());
			
			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tableUsuarios.getColumnCount(); i++) {
			tableUsuarios.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tableUsuarios.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
		
		tFnombre_usuario.setText("");
		tFemail.setText("");
		comboBox.setSelectedIndex(0);
	}
	
}
