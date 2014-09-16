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
package cliente.GestionOrden;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
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
import javax.swing.ScrollPaneConstants;
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

import com.toedter.calendar.JDateChooser;

import common.DTOs.Orden_ReclamoDTO;

public class GUIGestionarOrden extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MediadorOrden mediador;
	
	private JPanel contentPane;
	
	private int limite = 35;
	private JTextField tFnumero_orden;
	private JTextField tfRecurso;
	private JDateChooser dcFapertura;
	private JDateChooser dcFcierre;
	
	private JButton btnModificar; 
	private JButton btnEliminar; 
	private JButton btnAgregar; 
	private JButton btnImprimir; 
	private JButton btnVolver; 
	private JButton btnActualizar;

	//Tabla
	private DefaultTableModel modelo;
	private JTable tablaOrdenes;
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas; 
	//Combobox de Usuarios
	private JComboBox<String> cbEstadoOrden;
	private Vector<String> tiposEstados;
	private Vector<Integer> anchos;

	private Vector<String> estados_reclamo;
	private JComboBox<String> cbEstadoReclamo;

	private DefaultComboBoxModel<String> cmbEstado_reclamo;
	private JButton btn_clear_FA;
	private JButton btn_clear_FC;
	private JButton btnExportarTabla;
	
	public GUIGestionarOrden(MediadorOrden mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
	}
	
	@SuppressWarnings("serial")
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 770, 600);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIGestionarOrden.class.getResource("/cliente/Resources/Icons/ordenen.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("GESTIONAR ORDEN");
		
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tFnumero_orden = new JTextField();
		tFnumero_orden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFnumero_orden.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorOrden();
			}
		});
		tFnumero_orden.setBounds(170, 15, 160, 20);
		tFnumero_orden.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFnumero_orden.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
			@Override
			public void keyReleased(KeyEvent arg0) {			
			}
		});
		contentPane.add(tFnumero_orden);
		tFnumero_orden.setColumns(10);
		
		btnModificar = new GlossyButton("MODIFICAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar.setIcon(new ImageIcon(GUIGestionarOrden.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(534, 50, 220, 23);
		contentPane.add(btnModificar);
		
		btnEliminar = new GlossyButton("ELIMINAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnEliminar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnEliminar.setIcon(new ImageIcon(GUIGestionarOrden.class.getResource("/cliente/Resources/Icons/delete.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		
		btnEliminar.setBounds(534, 84, 220, 23);
		contentPane.add(btnEliminar);
		
		JLabel lbl_numero_orden = new JLabel("Numero Orden");
		lbl_numero_orden.setBorder(null);
		lbl_numero_orden.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_numero_orden.setBounds(0, 15, 165, 20);
		contentPane.add(lbl_numero_orden);
		
		JLabel lbl_fapertura = new JLabel("Fecha Apertura");
		lbl_fapertura.setBorder(null);
		lbl_fapertura.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_fapertura.setBounds(0, 40, 165, 20);
		contentPane.add(lbl_fapertura);
		
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		
		tablaOrdenes = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false,false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tablaOrdenes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablaOrdenes.setRowSorter(ordenador);
		//
		for(int i = 0; i < tablaOrdenes.getColumnCount(); i++) {
			tablaOrdenes.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaOrdenes.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
		
		
		tablaOrdenes.getTableHeader().setReorderingAllowed(false);
		tablaOrdenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaOrdenes.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaOrdenes);
		scrollPaneTabla.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneTabla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTabla.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tablaOrdenes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		scrollPaneTabla.setBounds(10, 182, 744, 335);
		contentPane.add(scrollPaneTabla);

		JLabel lblEstado = new JLabel("Estado Orden");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBorder(null);
		lblEstado.setBounds(0, 90, 165, 20);
		contentPane.add(lblEstado);
		
		cbEstadoOrden = new JComboBox<String>();
		cbEstadoOrden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbEstadoOrden.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filtrarPorEstadoOrden();
			}
		});
		cbEstadoOrden.setModel(new DefaultComboBoxModel<String>(tiposEstados));
		cbEstadoOrden.setBounds(170, 90, 160, 20);
		contentPane.add(cbEstadoOrden);
		
		btnAgregar = new GlossyButton("AGREGAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnAgregar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregar.setIcon(new ImageIcon(GUIGestionarOrden.class.getResource("/cliente/Resources/Icons/add.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.altaOrden();
			}
		});
		btnAgregar.setBounds(534, 20, 220, 23);
		contentPane.add(btnAgregar);
		
		btnImprimir = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnImprimir.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnImprimir.setIcon(new ImageIcon(GUIGestionarOrden.class.getResource("/cliente/Resources/Icons/printer.png")));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tablaOrdenes.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			}
		});
		btnImprimir.setBounds(682, 140, 32, 32);
		contentPane.add(btnImprimir);
		
		btnVolver = new GlossyButton("VOLVER",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUEGREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVolver.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnVolver.setIcon(new ImageIcon(GUIGestionarOrden.class.getResource("/cliente/Resources/Icons/back.png")));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVolver.setBounds(307, 528, 150, 23);
		contentPane.add(btnVolver);
		
		btnActualizar = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnActualizar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnActualizar.setIcon(new ImageIcon(GUIGestionarOrden.class.getResource("/cliente/Resources/Icons/1refresh.png")));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		btnActualizar.setBounds(722, 140, 32, 32);
		contentPane.add(btnActualizar);
		
		JLabel lblfcierrre = new JLabel("Fecha Cierre");
		lblfcierrre.setHorizontalAlignment(SwingConstants.CENTER);
		lblfcierrre.setBorder(null);
		lblfcierrre.setBounds(0, 65, 165, 20);
		contentPane.add(lblfcierrre);
		
		dcFapertura = new JDateChooser();
		dcFapertura.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFapertura.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFApertura();
	        }
		});
		dcFapertura.setBounds(170, 40, 160, 20);
		contentPane.add(dcFapertura);
		
		dcFcierre = new JDateChooser();
		dcFcierre.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFcierre.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFCierre();
	        }
		});
		dcFcierre.setBounds(170, 65, 160, 20);
		contentPane.add(dcFcierre);
		
		JLabel lblReclamo = new JLabel("Estado Reclamo");
		lblReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblReclamo.setBorder(null);
		lblReclamo.setBounds(0, 115, 165, 20);
		contentPane.add(lblReclamo);
		
		JLabel lblRecurso = new JLabel("Numero Recurso");
		lblRecurso.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecurso.setBorder(null);
		lblRecurso.setBounds(0, 140, 165, 20);
		contentPane.add(lblRecurso);
		
		tfRecurso = new JTextField();
		tfRecurso.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfRecurso.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorRecurso();
			}
		});
		tfRecurso.setColumns(10);
		tfRecurso.setBounds(170, 140, 160, 20);
		contentPane.add(tfRecurso);
			
		cbEstadoReclamo = new JComboBox<String>();
		cbEstadoReclamo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cmbEstado_reclamo = new DefaultComboBoxModel<String>(estados_reclamo);
		cbEstadoReclamo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filtrarPorEstado_Reclamo();
			}
		});
		cbEstadoReclamo.setModel(cmbEstado_reclamo);
		cbEstadoReclamo.setBounds(170, 115, 220, 20);
		contentPane.add(cbEstadoReclamo);
		
		btn_clear_FA = new JButton("");
		btn_clear_FA.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(dcFapertura.getDate()!=null){
					dcFapertura.setDate(null);
					actualizarDatos();			
				}
			}
		});
		btn_clear_FA.setIcon(new ImageIcon(GUIGestionarOrden.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FA.setBounds(340, 39, 25, 20);
		contentPane.add(btn_clear_FA);
		
		btn_clear_FC = new JButton("");
		btn_clear_FC.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dcFcierre.getDate()!=null){
					dcFcierre.setDate(null);
					actualizarDatos();			
				}
			}
		});
		btn_clear_FC.setIcon(new ImageIcon(GUIGestionarOrden.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FC.setBounds(340, 64, 25, 20);
		contentPane.add(btn_clear_FC);
		
		btnExportarTabla = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_METALIC_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnExportarTabla.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnExportarTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablas();
			}
		});
		btnExportarTabla.setIcon(new ImageIcon(GUIGestionarOrden.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTabla.setBounds(642, 140, 32, 32);
		contentPane.add(btnExportarTabla);
	}

	protected void exportarTablas() {
		try {
			ExportarExcel.exportarUnaTabla(tablaOrdenes, "Ordenes");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	//Gestion
	public void modificar(){
		int row = tablaOrdenes.getSelectedRow();
		if (row >= 0) {
			int aux = tablaOrdenes.convertRowIndexToModel(row);
			Long id = new Long (tablaOrdenes.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Modificar Orden [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionarOrden.class.getResource("/cliente/Resources/Icons/edit.png"))) == JOptionPane.YES_OPTION){ 
				mediador.modificarOrden(id);
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un Orden primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void eliminar(){
		int row = tablaOrdenes.getSelectedRow();
		if (row >= 0) {
			int aux = tablaOrdenes.convertRowIndexToModel(row);
			Long id = new Long (tablaOrdenes.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Eliminar Orden [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionarOrden.class.getResource("/cliente/Resources/Icons/delete.png"))) == JOptionPane.YES_OPTION){ 
				if (mediador.eliminarOrden(id)){
					JOptionPane.showMessageDialog(contentPane,"Orden eliminado.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
					actualizarDatos();
				}
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un Orden primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	// Filtros //
	protected void filtrarPorOrden() {
		String filtro = tFnumero_orden.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>>usuarios = datosTabla;
		for (int i=0; i< usuarios.size();i++){
			Vector<String> usuario = usuarios.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(usuario.elementAt(1).toLowerCase());
			if (mat.find())
				datos.add(usuario); 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaOrdenes.getColumnCount(); i++) {
			tablaOrdenes.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaOrdenes.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	
	protected void filtrarPorEstado_Reclamo() {
		String filtro = cbEstadoReclamo.getSelectedItem().toString().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>>usuarios = datosTabla;
		for (int i=0; i< usuarios.size();i++){
			Vector<String> usuario = usuarios.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(usuario.elementAt(3).toLowerCase());
			if (mat.find())
				datos.add(usuario); 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaOrdenes.getColumnCount(); i++) {
			tablaOrdenes.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaOrdenes.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	
	protected void filtrarPorReclamo() {
		String filtro = cbEstadoReclamo.getSelectedItem().toString().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>>usuarios = datosTabla;
		for (int i=0; i< usuarios.size();i++){
			Vector<String> usuario = usuarios.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(usuario.elementAt(2).toLowerCase());
			if (mat.find())
				datos.add(usuario);
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaOrdenes.getColumnCount(); i++) {
			tablaOrdenes.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaOrdenes.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	protected void filtrarPorFApertura() {
		if(dcFapertura.getDate()!=null){
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dcFapertura.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(4).toLowerCase());
				if (mat.find())
					datos.add(registrante);
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaOrdenes.getColumnCount(); i++) {
				tablaOrdenes.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaOrdenes.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}
	protected void filtrarPorFCierre() {
		if(dcFcierre.getDate()!=null){
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dcFcierre.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(5).toLowerCase());
				if (mat.find())
					datos.add(registrante);
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaOrdenes.getColumnCount(); i++) {
				tablaOrdenes.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaOrdenes.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}
	public void filtrarPorEstadoOrden(){
		String filtro = cbEstadoOrden.getSelectedItem().toString().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>>usuarios = datosTabla;
		for (int i=0; i< usuarios.size();i++){
			Vector<String> usuario = usuarios.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(usuario.elementAt(6).toLowerCase());
			if (mat.find())
				datos.add(usuario);
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaOrdenes.getColumnCount(); i++) {
			tablaOrdenes.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaOrdenes.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	protected void filtrarPorRecurso() {
		String filtro = tfRecurso.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>>usuarios = datosTabla;
		for (int i=0; i< usuarios.size();i++){
			Vector<String> usuario = usuarios.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(usuario.elementAt(7).toLowerCase());
			if (mat.find())
				datos.add(usuario);
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaOrdenes.getColumnCount(); i++) {
			tablaOrdenes.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaOrdenes.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	
	public void cargarDatos(){
		
		modelo = new DefaultTableModel();
		anchos = new Vector<Integer>();
		
		int chico = 100;
		int mediano = 150;
		int grande = 230;
		
		tiposEstados = new Vector<String>();
		tiposEstados.add("");
		tiposEstados.add("SIN RECLAMO");
		tiposEstados.add("SIN PEDIDO");
		tiposEstados.add("ABIERTA/SIN RECURSO");
		tiposEstados.add("ABIERTA/CON RECURSO");
		tiposEstados.add("CERRADA");
		
		estados_reclamo = new Vector<String>();
		estados_reclamo.add("");
		estados_reclamo.add("ABIERTO/SIN PEDIDO/SIN TURNO");
		estados_reclamo.add("ABIERTO/SIN PEDIDO/CON TURNO");
		estados_reclamo.add("ABIERTO/CON PEDIDO/SIN TURNO");
		estados_reclamo.add("ABIERTO/CON PEDIDO/CON TURNO");
		estados_reclamo.add("CERRADO");
		
		nombreColumnas = new Vector<String> ();
		nombreColumnas.add("ID Orden");//0
		anchos.add(75);
		nombreColumnas.add("Numero Orden");//1
		anchos.add(chico);
		nombreColumnas.add("ID Reclmamo");//2
		anchos.add(chico);
		nombreColumnas.add("Estado Reclmamo");//3
		anchos.add(grande);
		nombreColumnas.add("Fecha Apertura");//4
		anchos.add(chico);
		nombreColumnas.add("Fecha Cierre");//5
		anchos.add(chico);
		nombreColumnas.add("Estado Orden");//6
		anchos.add(mediano);
		nombreColumnas.add("Numero Recurso");//7
		anchos.add(chico);
		datosTabla = new Vector<Vector<String>>();
		Vector<Orden_ReclamoDTO> ordenes = mediador.obtenerOrdenes_Reclamos();
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 

		for (int i=0; i< ordenes.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(ordenes.elementAt(i).getOrden().getId().toString());
			row.add(ordenes.elementAt(i).getOrden().getNumero_orden());
			row.add(ordenes.elementAt(i).getReclamo().getId().toString());
			row.add(ordenes.elementAt(i).getReclamo().getEstado_reclamo());

			java.sql.Date fapertura = new java.sql.Date(ordenes.elementAt(i).getOrden().getFecha_apertura().getTime());
			row.add(format2.format(fapertura));//Fecha apertura
			if (ordenes.elementAt(i).getOrden().getFecha_cierre()!=null){
				java.sql.Date fcierre = new java.sql.Date(ordenes.elementAt(i).getOrden().getFecha_cierre().getTime());
				row.add(format2.format(fcierre));//Fecha cierrre
			}else{
				row.add("");
			}
			row.add(ordenes.elementAt(i).getOrden().getEstado());
			if (ordenes.elementAt(i).getOrden().getRecurso() !=null){
				row.add(ordenes.elementAt(i).getOrden().getRecurso().getNumero_recurso());
			}else{
				row.add("");
			}
			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
	}

	public void actualizarDatos(){
		datosTabla = new Vector<Vector<String>>();
		Vector<Orden_ReclamoDTO> ordenes = mediador.obtenerOrdenes_Reclamos();
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
		for (int i=0; i< ordenes.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(ordenes.elementAt(i).getOrden().getId().toString());
			row.add(ordenes.elementAt(i).getOrden().getNumero_orden());
			row.add(ordenes.elementAt(i).getReclamo().getId().toString());
			row.add(ordenes.elementAt(i).getReclamo().getEstado_reclamo());

			java.sql.Date fapertura = new java.sql.Date(ordenes.elementAt(i).getOrden().getFecha_apertura().getTime());
			row.add(format2.format(fapertura));//Fecha apertura
			if (ordenes.elementAt(i).getOrden().getFecha_cierre()!=null){
				java.sql.Date fcierre = new java.sql.Date(ordenes.elementAt(i).getOrden().getFecha_cierre().getTime());
				row.add(format2.format(fcierre));//Fecha cierrre
			}else{
				row.add("");
			}
			row.add(ordenes.elementAt(i).getOrden().getEstado());
			if (ordenes.elementAt(i).getOrden().getRecurso() !=null){
				row.add(ordenes.elementAt(i).getOrden().getRecurso().getNumero_recurso());
			}else{
				row.add("");
			}
			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaOrdenes.getColumnCount(); i++) {
			tablaOrdenes.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaOrdenes.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
		
		tFnumero_orden.setText("");
		cbEstadoOrden.setSelectedIndex(0);
	}
}
