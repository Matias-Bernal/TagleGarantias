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
package cliente.GestionarReclamante;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import common.DTOs.ReclamanteDTO;

public class GUIGestionReclamante extends JFrame {


	private static final long serialVersionUID = 1L;

	protected MediadorReclamante mediador;
	
	private JPanel contentPane;
	
	private JTextField tFEmail;
	private JTextField tFDni;
	private JTextField tFnombre_reclamante;
	private JButton btnModificar;
	private JButton btnEliminar;
	
	//Tabla
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas;
	private JTable tablaReclamantes;
	private DefaultTableModel modelo;
	
	
	private JButton btnAgregar;
	private JButton btnImprimir;
	private JButton btnVolver;
	private JButton btnActualizar;

	private JLabel lblDni;
	private JButton btnExportarTablas;
	

	public GUIGestionReclamante(MediadorReclamante mediador) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIGestionReclamante.class.getResource("/cliente/Resources/Icons/reclamante.png")));
		this.mediador = mediador;
		cargarDatos();
		initialize();

	}
	
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("GESTIONAR RECLAMANTE");
		setResizable(false);
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tFnombre_reclamante = new JTextField();
		tFnombre_reclamante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFnombre_reclamante.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorNombre();
			}
		});
		tFnombre_reclamante.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});
		tFnombre_reclamante.setBounds(180, 35, 230, 20);
		contentPane.add(tFnombre_reclamante);
		tFnombre_reclamante.setColumns(10);
		
		btnModificar = new GlossyButton("MODIFICAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar.setIcon(new ImageIcon(GUIGestionReclamante.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(565, 70, 220, 23);
		contentPane.add(btnModificar);
		
		btnEliminar = new GlossyButton("ELIMINAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnEliminar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnEliminar.setIcon(new ImageIcon(GUIGestionReclamante.class.getResource("/cliente/Resources/Icons/delete.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminar();
			}
		});
		btnEliminar.setBounds(565, 110, 220, 23);
		contentPane.add(btnEliminar);
		
		JLabel lbl_nombre_reclamante = new JLabel("Nombre del Registrante");
		lbl_nombre_reclamante.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nombre_reclamante.setBounds(5, 35, 170, 20);
		contentPane.add(lbl_nombre_reclamante);
		
		JLabel lbl_email = new JLabel("Email");
		lbl_email.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_email.setBounds(5, 70, 170, 20);
		contentPane.add(lbl_email);
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		
		tablaReclamantes = new JTable(modelo) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tablaReclamantes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaReclamantes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2)
					verRegistrante();
			    else{
			    	e.consume();
			    }   
			}
		});
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablaReclamantes.setRowSorter(ordenador);
		//
		tablaReclamantes.getTableHeader().setReorderingAllowed(false);
		tablaReclamantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaReclamantes.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaReclamantes);
		scrollPaneTabla.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneTabla.setBounds(5, 182, 779, 342);
		contentPane.add(scrollPaneTabla);
		
		btnAgregar = new GlossyButton("AGREGAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnAgregar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregar.setIcon(new ImageIcon(GUIGestionReclamante.class.getResource("/cliente/Resources/Icons/add.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.altaReclamante(tFnombre_reclamante.getText(),tFEmail.getText(), tFDni.getText());
			}
		});
		btnAgregar.setBounds(565, 30, 220, 23);
		contentPane.add(btnAgregar);
		
		btnImprimir = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnImprimir.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnImprimir.setIcon(new ImageIcon(GUIGestionReclamante.class.getResource("/cliente/Resources/Icons/printer.png")));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tablaReclamantes.print();
				} catch (PrinterException ex) {
					JOptionPane.showMessageDialog(contentPane,"Error al imprimir.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnImprimir.setBounds(710, 140, 32, 32);
		contentPane.add(btnImprimir);
		
		btnVolver = new GlossyButton("VOLVER",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUEGREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVolver.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnVolver.setIcon(new ImageIcon(GUIGestionReclamante.class.getResource("/cliente/Resources/Icons/back.png")));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVolver.setBounds(322, 535, 150, 23);
		contentPane.add(btnVolver);
		
		btnActualizar = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnActualizar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnActualizar.setIcon(new ImageIcon(GUIGestionReclamante.class.getResource("/cliente/Resources/Icons/1refresh.png")));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		btnActualizar.setBounds(750, 140, 32, 32);
		contentPane.add(btnActualizar);
		
		tFEmail = new JTextField();
		tFEmail.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFEmail.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorEmail();
			}
		});
		tFEmail.setColumns(10);
		tFEmail.setBounds(180, 70, 230, 20);
		contentPane.add(tFEmail);
		
		JButton btnVer = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIME_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVer.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnVer.setIcon(new ImageIcon(GUIGestionReclamante.class.getResource("/cliente/Resources/Icons/find_reclamo.png")));
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verRegistrante();
			}
		});
		btnVer.setBounds(630, 140, 32, 32);
		contentPane.add(btnVer);
		
		lblDni = new JLabel("DNI");
		lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		lblDni.setBounds(5, 105, 170, 20);
		contentPane.add(lblDni);
		
		tFDni = new JTextField();
		tFDni.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFDni.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorDni();
			}
		});
		tFDni.setColumns(10);
		tFDni.setBounds(180, 105, 230, 20);
		contentPane.add(tFDni);
		
		btnExportarTablas = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_METALIC_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnExportarTablas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnExportarTablas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablas();
			}
		});
		btnExportarTablas.setIcon(new ImageIcon(GUIGestionReclamante.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTablas.setBounds(670, 140, 32, 32);
		contentPane.add(btnExportarTablas);
		
	}
	protected void exportarTablas() {
		try {
			ExportarExcel.exportarUnaTabla(tablaReclamantes, "Reclamantes");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	// Gestion
	protected void modificar() {
		int row = tablaReclamantes.getSelectedRow();
		if (row >= 0) {
			int aux = tablaReclamantes.convertRowIndexToModel(row);
			Long id = new Long (tablaReclamantes.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Modificar Reclamante [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionReclamante.class.getResource("/cliente/Resources/Icons/edit.png"))) == JOptionPane.YES_OPTION){ 
				mediador.modificarReclamante(id);
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un reclamante primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	protected void eliminar() {
		int row = tablaReclamantes.getSelectedRow();
		if (row >= 0) {
			int aux = tablaReclamantes.convertRowIndexToModel(row);
			Long id = new Long (tablaReclamantes.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Eliminar Reclamante [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionReclamante.class.getResource("/cliente/Resources/Icons/delete.png"))) == JOptionPane.YES_OPTION){ 
				mediador.eliminarReclamante(id);
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un reclamante primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	// Filtros
	protected void filtrarPorNombre() {
		String filtro = tFnombre_reclamante.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> reclamantes = datosTabla;
		for (int i=0; i< reclamantes.size();i++){
			Vector<String> reclamante = reclamantes.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(reclamante.elementAt(1).toLowerCase());
			if (mat.find()) {
				datos.add(reclamante);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
	}
	protected void filtrarPorDni() {
		String filtro = tFDni.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> reclamantes = datosTabla;
		for (int i=0; i< reclamantes.size();i++){
			Vector<String> reclamante = reclamantes.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(reclamante.elementAt(2).toLowerCase());
			if (mat.find()) {
				datos.add(reclamante);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
	}
	protected void filtrarPorEmail() {
		String filtro = tFEmail.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> reclamantes = datosTabla;
		for (int i=0; i< reclamantes.size();i++){
			Vector<String> reclamante = reclamantes.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(reclamante.elementAt(3).toLowerCase());
			if (mat.find()) {
				datos.add(reclamante);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
	}

	private void verRegistrante() {
		int row = tablaReclamantes.getSelectedRow();
		if (row >= 0) {
			Long id = new Long (tablaReclamantes.getValueAt(row, 0).toString());
			mediador.verRegistrante(id);
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un reclamante primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void cargarDatos(){
		
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
	
	public void actualizarDatos() {
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
		modelo.fireTableStructureChanged();
		tFnombre_reclamante.setText("");
		tFDni.setText("");
		tFEmail.setText("");
	}
	
}
