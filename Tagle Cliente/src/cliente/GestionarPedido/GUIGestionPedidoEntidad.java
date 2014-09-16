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

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import cliente.Recursos.util.TransparentPanel;
import cliente.excellexport.ExportarExcel;

import com.toedter.calendar.JDateChooser;

import common.DTOs.EntidadDTO;
import common.DTOs.Pedido_PiezaDTO;

public class GUIGestionPedidoEntidad extends JFrame {

	private static final long serialVersionUID = 1L;

	protected MediadorPedido mediador;
	
	private JPanel contentPane;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnAgregar;
	private JButton btnImprimir;
	private JButton btnVolver;
	private JButton btnActualizar;
	private JButton btnVer;
	private JTextField tfpedido;
	private JTextField tfpnc;
	
	//Tabla
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas;
	private DefaultTableModel modelo;
	private JTable tablaPedidos;
	private Vector<Integer> anchos;
	
	private JTextField tfReclamante;
	private JTextField tfTitular;
	private JTextField tfDominio;
	private JTextField tfPieza;
	private JTextField tfMuleto;
	private JTextField tfManoObra;
	private JTextField tfNumeroBDG;
	private JTextField tfRemito;
	private JTextField tfVin;
	private JDateChooser dc_fReclamo;
	private JComboBox<String> cBEstadoPedido;
	private JDateChooser dc_fsp;
	private JDateChooser dc_fturno;
	private JDateChooser dCFSF;
	private JDateChooser dCFRF;
	private JDateChooser dcFEF;
	private JComboBox<String> cbentidad;

	private Vector<String> estados_pedidos;

	private DefaultComboBoxModel<String> cmbEstadoPedido;

	private DefaultComboBoxModel<String> cmbcbentidad;

	private Vector<String> entidades;
	private JTextField tfOrden;
	private JLabel lblIdNumeroBdg;
	private JDateChooser dCFBDG;
	private JButton btnExportarTabla;
	private JButton btn_clear_FSP;
	private JButton btn_clear_FT;
	private JButton btn_clear_FR;
	private JButton btn_clear_FBDG;
	private JButton btn_clear_FSF;
	private JButton btn_clear_FRF;
	private JButton btn_clear_FDF;
	private JLabel lblFechaSolicitudDevolucion;
	private JDateChooser dCFSD;
	private JButton btn_clear_FSD;
	private JLabel lblFechaAprobadaSolicitud;
	private JDateChooser dCFAS;
	private JButton btn_clear_FAS;

	public GUIGestionPedidoEntidad(MediadorPedido mediadorRegistrante) {
		this.mediador = mediadorRegistrante;
		cargarDatos();
		initialize();
	}
	
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("GESTIONAR PEDIDO ENTIDAD");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/pedido.png")));
		setResizable(false);
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		btnModificar =  new GlossyButton("MODIFICAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar.setIcon(new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.setBounds(1039, 70, 215, 23);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnModificar);
		
		btnEliminar = new GlossyButton("ELIMINAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnEliminar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnEliminar.setIcon(new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/delete.png")));
		btnEliminar.setBounds(1039, 104, 215, 23);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminar();
			}
		});
		contentPane.add(btnEliminar);
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);

		tablaPedidos = new JTable(modelo) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false, false, false,
					false, false, false, false, false,
					false, false, false, false, false,
					false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tablaPedidos.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaPedidos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2)
					verReclamante();
			    else{
			    	e.consume();
			    }   
			}
		});
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablaPedidos.setRowSorter(ordenador);
		//
		tablaPedidos.getTableHeader().setReorderingAllowed(false);
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
		tablaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPedidos.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaPedidos);
		scrollPaneTabla.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaPedidos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		
		scrollPaneTabla.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneTabla.setBounds(10, 226, 1254, 421);
		scrollPaneTabla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPaneTabla);
		
		btnAgregar = new GlossyButton("AGREGAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnAgregar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregar.setIcon(new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/add.png")));
		btnAgregar.setBounds(1039, 35, 215, 23);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.altaPedidoEntidad();
			}
		});
		contentPane.add(btnAgregar);
		
		btnImprimir = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnImprimir.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnImprimir.setIcon(new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/printer.png")));
		btnImprimir.setBounds(1190, 183, 32, 32);
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tablaPedidos.print();
				} catch (PrinterException ex) {
					JOptionPane.showMessageDialog(contentPane,"Error al imprimir.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPane.add(btnImprimir);
		
		btnVolver = new GlossyButton("VOLVER",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUEGREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVolver.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnVolver.setIcon(new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/back.png")));
		btnVolver.setBounds(562, 658, 150, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnVolver);
		
		btnActualizar = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnActualizar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnActualizar.setIcon(new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/1refresh.png")));
		btnActualizar.setBounds(1230, 183, 32, 32);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizarDatos();
			}
		});
		contentPane.add(btnActualizar);
		
		btnVer = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIME_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVer.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnVer.setIcon(new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/find_reclamo.png")));
		btnVer.setBounds(1110, 183, 32, 32);
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verReclamante();
			}
		});
		contentPane.add(btnVer);
		
		JPanel primer_panel = new TransparentPanel();
		primer_panel.setBorder(null);
		primer_panel.setBounds(10, 11, 345, 209);
		contentPane.add(primer_panel);
		primer_panel.setLayout(null);
		
		JLabel lblNumero_pedido = new JLabel("Numero Pedido");
		lblNumero_pedido.setBorder(null);
		lblNumero_pedido.setBounds(0, 10, 135, 20);
		primer_panel.add(lblNumero_pedido);
		lblNumero_pedido.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfpedido = new JTextField();
		tfpedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfpedido.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPedido();
			}
		});
		tfpedido.setBounds(135, 10, 128, 20);
		primer_panel.add(tfpedido);
		tfpedido.setColumns(10);
		
		JLabel lblEstadoPedido = new JLabel("Estado Pedido");
		lblEstadoPedido.setBorder(null);
		lblEstadoPedido.setBounds(0, 35, 135, 20);
		primer_panel.add(lblEstadoPedido);
		lblEstadoPedido.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbl_FTurno = new JLabel("Fecha Turno");
		lbl_FTurno.setBorder(null);
		lbl_FTurno.setBounds(0, 110, 135, 20);
		primer_panel.add(lbl_FTurno);
		lbl_FTurno.setHorizontalAlignment(SwingConstants.CENTER);
		
		dc_fturno = new JDateChooser();
		dc_fturno.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dc_fturno.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFTurno();
	        }
		});
		dc_fturno.setBounds(135, 110, 128, 20);
		primer_panel.add(dc_fturno);
		
		cBEstadoPedido = new JComboBox<String>();
		cBEstadoPedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cmbEstadoPedido = new DefaultComboBoxModel<String>(estados_pedidos);
		cBEstadoPedido.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filtrarPorEstado_Pedido();
			}
		});
		cBEstadoPedido.setModel(cmbEstadoPedido);
		cBEstadoPedido.setBounds(135, 35, 210, 20);
		primer_panel.add(cBEstadoPedido);
		
		JLabel lbl_FSP = new JLabel("Fecha Solicitud Pedido");
		lbl_FSP.setBorder(null);
		lbl_FSP.setBounds(0, 60, 135, 20);
		primer_panel.add(lbl_FSP);
		lbl_FSP.setHorizontalAlignment(SwingConstants.CENTER);
		
		dc_fsp = new JDateChooser();
		dc_fsp.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dc_fsp.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFSP();
	        }
		});
		dc_fsp.setBounds(135, 60, 128, 20);
		primer_panel.add(dc_fsp);
		
		JLabel lbl_nom_registrante = new JLabel("Nombre Entidad");
		lbl_nom_registrante.setBorder(null);
		lbl_nom_registrante.setBounds(0, 85, 135, 20);
		primer_panel.add(lbl_nom_registrante);
		lbl_nom_registrante.setHorizontalAlignment(SwingConstants.CENTER);
		
		cbentidad = new JComboBox<String>();
		cbentidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cmbcbentidad = new DefaultComboBoxModel<String>(entidades);
		cbentidad.setModel(cmbcbentidad);
		cbentidad.setBounds(135, 85, 210, 20);
		primer_panel.add(cbentidad);
		cbentidad.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filtrarPorEntidades();
			}
		});

		tfOrden = new JTextField();
		tfOrden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfOrden.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorOrden();
			}
		});
		tfOrden.setColumns(10);
		tfOrden.setBounds(135, 135, 128, 20);
		primer_panel.add(tfOrden);
		
		JLabel label = new JLabel("Numero Orden");
		label.setBorder(null);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 135, 135, 20);
		primer_panel.add(label);
		
		JLabel lbl_FReclamo = new JLabel("Fecha Reclamo");
		lbl_FReclamo.setBorder(null);
		lbl_FReclamo.setBounds(0, 160, 135, 20);
		primer_panel.add(lbl_FReclamo);
		lbl_FReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		
		dc_fReclamo = new JDateChooser();
		dc_fReclamo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dc_fReclamo.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFreclamo();
	        }
		});
		dc_fReclamo.setBounds(135, 160, 128, 20);
		primer_panel.add(dc_fReclamo);
		
		JLabel lblReclamante = new JLabel("Nombre Reclamante");
		lblReclamante.setBorder(null);
		lblReclamante.setBounds(0, 185, 135, 20);
		primer_panel.add(lblReclamante);
		lblReclamante.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfReclamante = new JTextField();
		tfReclamante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfReclamante.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorReclamante();
			}
		});
		tfReclamante.setBounds(135, 185, 128, 20);
		primer_panel.add(tfReclamante);
		tfReclamante.setColumns(10);
		
		btn_clear_FSP = new JButton("");
		btn_clear_FSP.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dc_fsp.getDate()!=null){
					dc_fsp.setDate(null);
					actualizarDatos();
				}
			}
		});
		btn_clear_FSP.setIcon(new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FSP.setBounds(272, 60, 25, 20);
		primer_panel.add(btn_clear_FSP);
		
		btn_clear_FT = new JButton("");
		btn_clear_FT.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dc_fturno.getDate()!=null){
					dc_fturno.setDate(null);
					actualizarDatos();
				}
			}
		});
		btn_clear_FT.setIcon(new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FT.setBounds(272, 110, 25, 20);
		primer_panel.add(btn_clear_FT);
		
		btn_clear_FR = new JButton("");
		btn_clear_FR.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dc_fReclamo.getDate()!=null){
					dc_fReclamo.setDate(null);
					actualizarDatos();
				}
			}
		});
		btn_clear_FR.setIcon(new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FR.setBounds(273, 160, 25, 20);
		primer_panel.add(btn_clear_FR);
		
		JPanel segundo_panel = new TransparentPanel();
		segundo_panel.setBorder(null);
		segundo_panel.setBounds(360, 11, 329, 209);
		contentPane.add(segundo_panel);
		segundo_panel.setLayout(null);
		
		JLabel lbl_PNC = new JLabel("PNC");
		lbl_PNC.setBorder(null);
		lbl_PNC.setBounds(-1, 110, 121, 20);
		segundo_panel.add(lbl_PNC);
		lbl_PNC.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblIdMuleto = new JLabel("ID Muleto");
		lblIdMuleto.setBorder(null);
		lblIdMuleto.setBounds(-1, 135, 121, 20);
		segundo_panel.add(lblIdMuleto);
		lblIdMuleto.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfMuleto = new JTextField();
		tfMuleto.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfMuleto.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorMuleto();;
			}
		});
		tfMuleto.setBounds(120, 135, 128, 20);
		segundo_panel.add(tfMuleto);
		tfMuleto.setColumns(10);
		
		tfpnc = new JTextField();
		tfpnc.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfpnc.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorPnc();
			}
		});
		tfpnc.setBounds(120, 110, 128, 20);
		segundo_panel.add(tfpnc);
		tfpnc.setColumns(10);
		
		tfManoObra = new JTextField();
		tfManoObra.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfManoObra.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorMObra();
			}
		});
		tfManoObra.setColumns(10);
		tfManoObra.setBounds(120, 160, 128, 20);
		segundo_panel.add(tfManoObra);
		
		JLabel lblIdManoObra = new JLabel("ID Mano Obra");
		lblIdManoObra.setBorder(null);
		lblIdManoObra.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdManoObra.setBounds(-1, 160, 121, 20);
		segundo_panel.add(lblIdManoObra);

		
		JLabel lblVin = new JLabel("VIN");
		lblVin.setBorder(null);
		lblVin.setBounds(1, 60, 120, 20);
		segundo_panel.add(lblVin);
		lblVin.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfVin = new JTextField();
		tfVin.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfVin.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorVin();
			}
		});
		tfVin.setBounds(120, 60, 128, 20);
		segundo_panel.add(tfVin);
		tfVin.setColumns(10);
		
		JLabel lblTitular = new JLabel("Nombre Titular");
		lblTitular.setBorder(null);
		lblTitular.setBounds(0, 10, 120, 20);
		segundo_panel.add(lblTitular);
		lblTitular.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfTitular = new JTextField();
		tfTitular.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfTitular.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPortitular();;
			}
		});
		tfTitular.setBounds(120, 10, 128, 20);
		segundo_panel.add(tfTitular);
		tfTitular.setColumns(10);
		
		JLabel lblDominio = new JLabel("Dominio");
		lblDominio.setBorder(null);
		lblDominio.setBounds(0, 35, 120, 20);
		segundo_panel.add(lblDominio);
		lblDominio.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfDominio = new JTextField();
		tfDominio.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfDominio.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorDominio();
			}
		});
		tfDominio.setBounds(120, 35, 128, 20);
		segundo_panel.add(tfDominio);
		tfDominio.setColumns(10);
		
		JLabel lblNumeroPieza = new JLabel("Numero Pieza");
		lblNumeroPieza.setBorder(null);
		lblNumeroPieza.setBounds(0, 85, 120, 20);
		segundo_panel.add(lblNumeroPieza);
		lblNumeroPieza.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfPieza = new JTextField();
		tfPieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfPieza.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorPieza();
			}
		});
		tfPieza.setBounds(120, 85, 128, 20);
		segundo_panel.add(tfPieza);
		tfPieza.setColumns(10);
		
		JPanel tercer_panel = new TransparentPanel();
		tercer_panel.setBorder(null);
		tercer_panel.setBounds(694, 11, 335, 209);
		contentPane.add(tercer_panel);
		tercer_panel.setLayout(null);
		
		JLabel lblFechaSolicitudFabrica = new JLabel("Fecha Solicitud Fabrica");
		lblFechaSolicitudFabrica.setBorder(null);
		lblFechaSolicitudFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSolicitudFabrica.setBounds(0, 60, 164, 20);
		tercer_panel.add(lblFechaSolicitudFabrica);
		
		dCFSF = new JDateChooser();
		dCFSF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFSF.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFSF();
	        }
		});
		dCFSF.setBounds(163, 60, 128, 20);
		tercer_panel.add(dCFSF);
		
		dCFRF = new JDateChooser();
		dCFRF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFRF.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFRF();
	        }
		});
		dCFRF.setBounds(163, 85, 128, 20);
		tercer_panel.add(dCFRF);
		
		JLabel lblFechaRecepcionFabrica = new JLabel("Fecha Recepcion Fabrica");
		lblFechaRecepcionFabrica.setBorder(null);
		lblFechaRecepcionFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaRecepcionFabrica.setBounds(0, 85, 164, 20);
		tercer_panel.add(lblFechaRecepcionFabrica);
				
		tfNumeroBDG = new JTextField();
		tfNumeroBDG.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumeroBDG.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorNumBDG();
			}
		});
		tfNumeroBDG.setColumns(10);
		tfNumeroBDG.setBounds(163, 10, 128, 20);
		tercer_panel.add(tfNumeroBDG);
		
		lblIdNumeroBdg = new JLabel("Numero BDG");
		lblIdNumeroBdg.setBorder(null);
		lblIdNumeroBdg.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdNumeroBdg.setBounds(0, 10, 164, 20);
		tercer_panel.add(lblIdNumeroBdg);
		
		JLabel lblFechaDevolucionFabrica = new JLabel("Fecha Devolucion Fabrica");
		lblFechaDevolucionFabrica.setBorder(null);
		lblFechaDevolucionFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaDevolucionFabrica.setBounds(0, 160, 164, 20);
		tercer_panel.add(lblFechaDevolucionFabrica);
		
		dcFEF = new JDateChooser();
		dcFEF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFEF.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFEF();
	        }
		});
		dcFEF.setBounds(163, 160, 128, 20);
		tercer_panel.add(dcFEF);
		
		JLabel lblNumeroRemito = new JLabel("Numero Remito");
		lblNumeroRemito.setBorder(null);
		lblNumeroRemito.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroRemito.setBounds(0, 185, 164, 20);
		tercer_panel.add(lblNumeroRemito);
		
		tfRemito = new JTextField();
		tfRemito.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfRemito.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorRemito();
			}
		});
		tfRemito.setColumns(10);
		tfRemito.setBounds(163, 185, 128, 20);
		tercer_panel.add(tfRemito);
		
		JLabel lblFechaBdg = new JLabel("Fecha Bdg");
		lblFechaBdg.setBorder(null);
		lblFechaBdg.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaBdg.setBounds(0, 35, 164, 20);
		tercer_panel.add(lblFechaBdg);
		
		dCFBDG = new JDateChooser();
		dCFBDG.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFBDG.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFBDG();
	        }
		});
		dCFBDG.setBounds(163, 35, 128, 20);
		tercer_panel.add(dCFBDG);
		
		btn_clear_FBDG = new JButton("");
		btn_clear_FBDG.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FBDG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFBDG.getDate()!=null){
					dCFBDG.setDate(null);
					actualizarDatos();
				}
			}
		});
		btn_clear_FBDG.setIcon(new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FBDG.setBounds(301, 35, 25, 20);
		tercer_panel.add(btn_clear_FBDG);
		
		btn_clear_FSF = new JButton("");
		btn_clear_FSF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FSF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFSF.getDate()!=null){
					dCFSF.setDate(null);
					actualizarDatos();
				}
			}
		});
		btn_clear_FSF.setIcon(new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FSF.setBounds(301, 60, 25, 20);
		tercer_panel.add(btn_clear_FSF);
		
		btn_clear_FRF = new JButton("");
		btn_clear_FRF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FRF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFRF.getDate()!=null){
					dCFRF.setDate(null);
					actualizarDatos();
				}
			}
		});
		btn_clear_FRF.setIcon(new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FRF.setBounds(301, 85, 25, 20);
		tercer_panel.add(btn_clear_FRF);
		
		btn_clear_FDF = new JButton("");
		btn_clear_FDF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFEF.getDate()!=null){
					dcFEF.setDate(null);
					actualizarDatos();
				}
			}
		});
		btn_clear_FDF.setIcon(new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FDF.setBounds(301, 160, 25, 20);
		tercer_panel.add(btn_clear_FDF);
		
		lblFechaSolicitudDevolucion = new JLabel("Fecha Solicitud Devolucion");
		lblFechaSolicitudDevolucion.setBorder(null);
		lblFechaSolicitudDevolucion.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSolicitudDevolucion.setBounds(0, 110, 164, 20);
		tercer_panel.add(lblFechaSolicitudDevolucion);
		
		dCFSD = new JDateChooser();
		dCFSD.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFSD.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFSD();
	        }
		});
		dCFSD.setBounds(163, 110, 128, 20);
		tercer_panel.add(dCFSD);
		
		btn_clear_FSD = new JButton("");
		btn_clear_FSD.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FSD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFSD.getDate()!=null){
					dCFSD.setDate(null);
					actualizarDatos();
				}
			}
		});
		btn_clear_FSD.setIcon(new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FSD.setBounds(301, 110, 25, 20);
		tercer_panel.add(btn_clear_FSD);
		
		lblFechaAprobadaSolicitud = new JLabel("Fecha Aprobada Solicitud");
		lblFechaAprobadaSolicitud.setBorder(null);
		lblFechaAprobadaSolicitud.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaAprobadaSolicitud.setBounds(0, 135, 164, 20);
		tercer_panel.add(lblFechaAprobadaSolicitud);
		
		dCFAS = new JDateChooser();
		dCFAS.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFAS.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFAS();
	        }
		});
		dCFAS.setBounds(163, 135, 128, 20);
		tercer_panel.add(dCFAS);
		
		btn_clear_FAS = new JButton("");
		btn_clear_FAS.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFAS.getDate()!=null){
					dCFAS.setDate(null);
					actualizarDatos();
				}
			}
		});
		btn_clear_FAS.setIcon(new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FAS.setBounds(301, 133, 25, 20);
		tercer_panel.add(btn_clear_FAS);
		
		btnExportarTabla = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_METALIC_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnExportarTabla.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnExportarTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablas();
			}
		});
		btnExportarTabla.setIcon(new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTabla.setBounds(1150, 183, 32, 32);
		contentPane.add(btnExportarTabla);
	}
	protected void exportarTablas() {
		try {
			ExportarExcel.exportarUnaTabla(tablaPedidos, "Pedidos Entidad");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	//FILTROS
	
	protected void filtrarPorEstado_Pedido() {
		String filtro = cBEstadoPedido.getSelectedItem().toString().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(1)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(1).toLowerCase());
				if (mat.find()) {
					datos.add(registrante);
				}
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	protected void filtrarPedido() {
		String filtro = tfpedido.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(2)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(2).toLowerCase());
				if (mat.find()) {
					datos.add(registrante);
				}
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	protected void filtrarPorFSP() {
		if(dc_fsp.getDate()!=null){
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dc_fsp.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				
				if(registrante.elementAt(3)!=null){
					Pattern pat = Pattern.compile(".*"+filtro+".*");
					Matcher mat = pat.matcher(registrante.elementAt(3).toLowerCase());
					if (mat.find()) {
						datos.add(registrante);
					}
				}
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}
	protected void filtrarPorEntidades() {
		String filtro = cbentidad.getSelectedItem().toString().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			
			if(registrante.elementAt(4)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(4).toLowerCase());
				if (mat.find()) {
					datos.add(registrante);
				}
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	protected void filtrarPorOrden() {
		String filtro = tfOrden.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			
			if(registrante.elementAt(5)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(5).toLowerCase());
				if (mat.find()) {
					datos.add(registrante);
				}
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	protected void filtrarPorFreclamo() {
		if(dc_fReclamo.getDate()!=null){
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dc_fReclamo.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				if(registrante.elementAt(6)!=null){
					Pattern pat = Pattern.compile(".*"+filtro+".*");
					Matcher mat = pat.matcher(registrante.elementAt(6).toLowerCase());
					if (mat.find()) {
						datos.add(registrante);
					}
				}
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}
	protected void filtrarPorFTurno() {
		if(dc_fturno.getDate()!=null){
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dc_fturno.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				if(registrante.elementAt(7)!=null){
					Pattern pat = Pattern.compile(".*"+filtro+".*");
					Matcher mat = pat.matcher(registrante.elementAt(7).toLowerCase());
					if (mat.find()) {
						datos.add(registrante);
					}
				}
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}
	protected void filtrarPorReclamante() {
		String filtro = tfReclamante.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(8)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(8).toLowerCase());
				if (mat.find()) {
					datos.add(registrante);
				}
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	protected void filtrarPortitular() {
		String filtro = tfTitular.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(9)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(9).toLowerCase());
				if (mat.find()) {
					datos.add(registrante);
				}
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	protected void filtrarPorDominio() {
		String filtro = tfDominio.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(10)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(10).toLowerCase());
				if (mat.find()) {
					datos.add(registrante);
				}
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	protected void filtrarPorVin() {
		String filtro = tfVin.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(11)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(11).toLowerCase());
				if (mat.find()) {
					datos.add(registrante);
				}
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	protected void filtrarPorPieza() {
		String filtro = tfPieza.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(12)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(12).toLowerCase());
				if (mat.find()) {
					datos.add(registrante);
				}
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	protected void filtrarPorFSF() {
		if(dCFSF.getDate()!=null){
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dCFSF.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				if(registrante.elementAt(13)!=null){
					Pattern pat = Pattern.compile(".*"+filtro+".*");
					Matcher mat = pat.matcher(registrante.elementAt(13).toLowerCase());
					if (mat.find()) {
						datos.add(registrante);
					}
				}
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}
	protected void filtrarPorFRF() {
		if(dCFRF.getDate()!=null){
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dCFRF.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				if(registrante.elementAt(14)!=null){
					Pattern pat = Pattern.compile(".*"+filtro+".*");
					Matcher mat = pat.matcher(registrante.elementAt(14).toLowerCase());
					if (mat.find()) {
						datos.add(registrante);
					}
				}
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}
	protected void filtrarPorFSD() {
		if(dCFSD.getDate()!=null){
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dCFSD.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				if(registrante.elementAt(15)!=null){
					Pattern pat = Pattern.compile(".*"+filtro+".*");
					Matcher mat = pat.matcher(registrante.elementAt(15).toLowerCase());
					if (mat.find()) {
						datos.add(registrante);
					}
				}
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}
	protected void filtrarPorFAS() {
		if(dCFAS.getDate()!=null){
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dCFAS.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				if(registrante.elementAt(16)!=null){
					Pattern pat = Pattern.compile(".*"+filtro+".*");
					Matcher mat = pat.matcher(registrante.elementAt(16).toLowerCase());
					if (mat.find()) {
						datos.add(registrante);
					}
				}
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}
	protected void filtrarPorFEF() {
		if(dcFEF.getDate()!=null){
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dcFEF.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				if(registrante.elementAt(17)!=null){
					Pattern pat = Pattern.compile(".*"+filtro+".*");
					Matcher mat = pat.matcher(registrante.elementAt(17).toLowerCase());
					if (mat.find()) {
						datos.add(registrante);
					}
				}
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}
	protected void filtrarPorRemito() {
		String filtro = tfRemito.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(18)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(18).toLowerCase());
				if (mat.find()) {
					datos.add(registrante);
				}
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	protected void filtrarPorPnc() {
		String filtro = tfpnc.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(19)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(19).toLowerCase());
				if (mat.find()) {
					datos.add(registrante);
				}
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	protected void filtrarPorMuleto() {
		String filtro = tfMuleto.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(20)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(20).toLowerCase());
				if (mat.find()) {
					datos.add(registrante);
				}
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	protected void filtrarPorMObra() {
		String filtro = tfManoObra.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(21)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(21).toLowerCase());
				if (mat.find()) {
					datos.add(registrante);
				}
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	protected void filtrarPorNumBDG() {
		String filtro = tfNumeroBDG.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(22)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(22).toLowerCase());
				if (mat.find()) {
					datos.add(registrante);
				}
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	protected void filtrarPorFBDG() {
		if(dCFBDG.getDate()!=null){
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dCFBDG.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				if(registrante.elementAt(23)!=null){
					Pattern pat = Pattern.compile(".*"+filtro+".*");
					Matcher mat = pat.matcher(registrante.elementAt(23).toLowerCase());
					if (mat.find()) {
						datos.add(registrante);
					}
				}
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}
	
	//GESTION
	protected void eliminar() {
		int row = tablaPedidos.getSelectedRow();
		if (row >= 0) {
			Long id = new Long (tablaPedidos.getValueAt(row, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Eliminar Pedido [ID:"+id+"]?, Esto eliminara todas sus piezas.", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/delete.png"))) == JOptionPane.YES_OPTION){ 
				mediador.eliminarPedido(id);
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un pedido primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void modificar() {
		int row = tablaPedidos.getSelectedRow();
		if (row >= 0) {
			int aux = tablaPedidos.convertRowIndexToModel(row);
			Long id = new Long (tablaPedidos.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Modificar Pedido [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionPedidoEntidad.class.getResource("/cliente/Resources/Icons/edit.png"))) == JOptionPane.YES_OPTION){ 
				mediador.modificarPedidoEntidad(id);
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un pedido primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void cargarDatos() {
		modelo = new DefaultTableModel();
		nombreColumnas = new Vector<String> ();
		anchos = new Vector<Integer>();
		int chico = 100;
		int mediano = 150;
		int grande = 230;
		nombreColumnas.add("ID Pedido");//0
		anchos.add(75);
		nombreColumnas.add("Estado Pedido");//1
		anchos.add(grande);
		nombreColumnas.add("Numero Pedido");//2
		anchos.add(chico);
		nombreColumnas.add("Fecha Solicitud Pedido");//3
		anchos.add(mediano);
		nombreColumnas.add("Nombre Entidad");//4
		anchos.add(grande);
		nombreColumnas.add("Numero Orden");//5
		anchos.add(chico);
		nombreColumnas.add("Fecha Reclamo");//6
		anchos.add(chico);
		nombreColumnas.add("Fecha Turno");//7
		anchos.add(chico);
		nombreColumnas.add("Nombre Reclamante");//8
		anchos.add(grande);
		nombreColumnas.add("Nombre Titular");//9
		anchos.add(grande);
		nombreColumnas.add("Dominio");//10
		anchos.add(70);
		nombreColumnas.add("VIN");//11
		anchos.add(130);
		nombreColumnas.add("Numero Pieza");//12
		anchos.add(chico);
		nombreColumnas.add("Fecha Solicitud Fabrica");//13
		anchos.add(mediano);
		nombreColumnas.add("Fecha Recepcion Fabrica");//14
		anchos.add(mediano);
		nombreColumnas.add("Fecha Solicitud Devolucion");//15
		anchos.add(mediano);
		nombreColumnas.add("Fecha Aprobada Solicitud");//16
		anchos.add(mediano);
		nombreColumnas.add("Fecha Devolucion Fabrica");//17
		anchos.add(mediano);
		nombreColumnas.add("Numero Remito");//18
		anchos.add(chico);
		nombreColumnas.add("PNC");//19
		anchos.add(chico);
		nombreColumnas.add("ID Muleto");//20
		anchos.add(60);
		nombreColumnas.add("ID Mano Obra");//21
		anchos.add(chico);
		nombreColumnas.add("Numero BDG");//22
		anchos.add(chico);
		nombreColumnas.add("Fecha BDG");//23
		anchos.add(chico);
		//
		entidades = new Vector<String>();
		entidades.add("");
		Vector<EntidadDTO> entidadDTO = mediador.obtenerEntidades();
		for (int i=0; i< entidadDTO.size();i++){
			entidades.add(entidadDTO.elementAt(i).getNombre_registrante());
		};
		estados_pedidos = new Vector<String>();
		estados_pedidos.add("");
		estados_pedidos.add("SIN SOLICITUD A FABRICA");
		estados_pedidos.add("EN ESPERA DE RECEPCION FABRICA");
		estados_pedidos.add("SIN ENVIAR A FABRICA");
		estados_pedidos.add("ENVIADO A FABRICA");
		//tabla
		datosTabla = new Vector<Vector<String>>();
		Vector<Pedido_PiezaDTO> pididos_piezas = mediador.obtenerPedidosPiezasEntidades();
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		for (int i=0; i< pididos_piezas.size();i++){
			Pedido_PiezaDTO pedido_pieza = pididos_piezas.elementAt(i);
			Vector<String> row = new Vector<String> ();
			row.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
			row.add(pedido_pieza.getEstado_pedido());//Estado Pedido
			row.add(pedido_pieza.getNumero_pedido());//Numero Pedido		
			java.sql.Date fsp = new java.sql.Date(pedido_pieza.getPedido().getFecha_solicitud_pedido().getTime());
			row.add(format2.format(fsp));//Fecha Solicitud Pedido
			row.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//nombre entidad
			if (pedido_pieza.getPedido().getReclamo().getOrden()!=null)
				row.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			else
				row.add("");
			if (pedido_pieza.getPedido().getReclamo().getFecha_reclamo()!=null){
				java.sql.Date fr = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getFecha_reclamo().getTime());
				row.add(format2.format(fr));//Fecha Reclamo
			}else
				row.add("");
			if (pedido_pieza.getPedido().getReclamo().getFecha_turno()!=null){
				java.sql.Date ft = new java.sql.Date(pididos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno().getTime());
				row.add(format2.format(ft));//Fecha turno
			}else
				row.add("");
			row.add(pedido_pieza.getPedido().getReclamo().getReclamante().getNombre_apellido());//Nombre Reclamante	
			if (pididos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo()!=null){
				row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getNombre_titular());//Nombre Titular
				row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getDominio());//Dominio
				row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
			}else{
				row.add("");
				row.add("");
				row.add("");
			}
			if (pedido_pieza.getPieza()!=null)
				row.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
			else
				row.add("");
			if (pedido_pieza.getFecha_solicitud_fabrica()!=null){
				java.sql.Date fsf = new java.sql.Date(pedido_pieza.getFecha_solicitud_fabrica().getTime());
				row.add(format2.format(fsf));//Fecha Solicitud Fabrica
			}else
				row.add("");
			if (pedido_pieza.getFecha_recepcion_fabrica()!=null){
				java.sql.Date frf = new java.sql.Date(pedido_pieza.getFecha_recepcion_fabrica().getTime());
				row.add(format2.format(frf));//Fecha Recepcion Fabrica
			}else
				row.add("");
			if (pedido_pieza.getFecha_solicitud_devolucion()!=null){
				java.sql.Date fsd = new java.sql.Date(pedido_pieza.getFecha_solicitud_devolucion().getTime());
				row.add(format2.format(fsd));//Fecha Solicitud Devolucion
			}else
				row.add("");
			if (pedido_pieza.getFecha_aprobacion_solicitud_devolucion()!=null){
				java.sql.Date fas = new java.sql.Date(pedido_pieza.getFecha_aprobacion_solicitud_devolucion().getTime());
				row.add(format2.format(fas));//Fecha Aceptacion Devolucion
			}else
				row.add("");
			if (pedido_pieza.getDevolucion_pieza()!=null && pedido_pieza.getDevolucion_pieza().getFecha_devolucion()!=null){
				java.sql.Date fdf = new java.sql.Date(pedido_pieza.getDevolucion_pieza().getFecha_devolucion().getTime());
				row.add(format2.format(fdf));//Fecha Devolucion Fabrica
				row.add(pedido_pieza.getDevolucion_pieza().getNumero_remito());//numero remito
			}else{
				row.add("");
				row.add("");
			}
			if (pedido_pieza.getPnc()!=null)
				row.add(pedido_pieza.getPnc());// PCN
			else
				row.add("");
			if (pedido_pieza.getMuleto()!=null)
				row.add(pedido_pieza.getMuleto().getId().toString());//Id Muleto
			else
				row.add("");
			if (pedido_pieza.getMano_obra()!=null)
						row.add(pedido_pieza.getMano_obra().getId().toString());//Id Mano Obra
			else
				row.add("");
			if (pedido_pieza.getBdg()!=null){
				row.add(pedido_pieza.getBdg().getNumero_bdg());//Numero BDG
				if(pedido_pieza.getBdg().getFecha_bdg()!=null){
					java.sql.Date fbdg = new java.sql.Date(pedido_pieza.getBdg().getFecha_bdg().getTime());
					row.add(format2.format(fbdg));//Fecha BDG
				}else
					row.add("");
			}else{
				row.add("");
				row.add("");
			}		
			datosTabla.add(row);
		}
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
		
//		Vector<PedidoDTO> pedidos = mediador.obtenerPedidosEntidades();
//		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
//
//		for (int i=0; i< pedidos.size();i++){
//				Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
//				pedidos_piezas = mediador.buscarPedidoPieza(pedidos.elementAt(i).getId());
//				for (int j=0; j< pedidos_piezas.size();j++){
//					Vector<String> row = new Vector<String> ();
//
//					row.add(pedidos.elementAt(i).getId().toString());//ID Pedido
//					row.add(pedidos_piezas.elementAt(j).getEstado_pedido());//Estado Pedido
//					row.add(pedidos_piezas.elementAt(j).getNumero_pedido());//Numero Pedido
//					
//					java.sql.Date fsp = new java.sql.Date(pedidos.elementAt(i).getFecha_solicitud_pedido().getTime());
//					row.add(format2.format(fsp));//Fecha Solicitud Pedido
//					
//					row.add(pedidos.elementAt(i).getReclamo().getRegistrante().getNombre_registrante());//nombre entidad
//					if (pedidos.elementAt(i).getReclamo().getOrden()!=null){
//						row.add(pedidos.elementAt(i).getReclamo().getOrden().getNumero_orden());//Numero Orden
//					}else{
//						row.add("");
//					}
//					if (pedidos.elementAt(i).getReclamo().getFecha_reclamo()!=null){
//						java.sql.Date fr = new java.sql.Date(pedidos.elementAt(i).getReclamo().getFecha_reclamo().getTime());
//						row.add(format2.format(fr));//Fecha Reclamo
//					}else{
//						row.add("");
//					}
//					if (pedidos.elementAt(i).getReclamo().getFecha_turno()!=null){
//						java.sql.Date ft = new java.sql.Date(pedidos.elementAt(i).getReclamo().getFecha_turno().getTime());
//						row.add(format2.format(ft));//Fecha turno
//					}else{
//						row.add("");
//					}
//					row.add(pedidos.elementAt(i).getReclamo().getReclamante().getNombre_apellido());//Nombre Reclamante
//					
//					if (pedidos.elementAt(i).getReclamo().getVehiculo()!=null){
//						row.add(pedidos.elementAt(i).getReclamo().getVehiculo().getNombre_titular());//Nombre Titular
//						row.add(pedidos.elementAt(i).getReclamo().getVehiculo().getDominio());//Dominio
//						row.add(pedidos.elementAt(i).getReclamo().getVehiculo().getVin());//VIN
//					}else{
//						row.add("");
//						row.add("");
//						row.add("");
//					}
//					
//					if (pedidos_piezas.elementAt(j).getPieza()!=null){
//						row.add(pedidos_piezas.elementAt(j).getPieza().getNumero_pieza());//Numero Pieza
//					}else{
//						row.add("");
//					}
//					if (pedidos_piezas.elementAt(j).getFecha_solicitud_fabrica()!=null){
//						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(j).getFecha_solicitud_fabrica().getTime());
//						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
//					}else{
//						row.add("");
//					}
//					if (pedidos_piezas.elementAt(j).getFecha_recepcion_fabrica()!=null){
//						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(j).getFecha_recepcion_fabrica().getTime());
//						row.add(format2.format(frf));//Fecha Recepcion Fabrica
//					}else{
//						row.add("");
//					}
//					if (pedidos_piezas.elementAt(j).getFecha_solicitud_devolucion()!=null){
//						java.sql.Date fsd = new java.sql.Date(pedidos_piezas.elementAt(j).getFecha_solicitud_devolucion().getTime());
//						row.add(format2.format(fsd));//Fecha Solicitud Devolucion
//					}else{
//						row.add("");
//					}
//					if (pedidos_piezas.elementAt(j).getFecha_aprobacion_solicitud_devolucion()!=null){
//						java.sql.Date fas = new java.sql.Date(pedidos_piezas.elementAt(j).getFecha_aprobacion_solicitud_devolucion().getTime());
//						row.add(format2.format(fas));//Fecha Aceptacion Devolucion
//					}else{
//						row.add("");
//					}
//					if (pedidos_piezas.elementAt(j).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(j).getDevolucion_pieza().getFecha_devolucion()!=null){
//						java.sql.Date fdf = new java.sql.Date(pedidos_piezas.elementAt(j).getDevolucion_pieza().getFecha_devolucion().getTime());
//						row.add(format2.format(fdf));//Fecha Devolucion Fabrica
//						row.add(pedidos_piezas.elementAt(j).getDevolucion_pieza().getNumero_remito());//numero remito						row.add(pedidos_piezas.elementAt(j).getDevolucion_pieza().getNumero_remito());//Numero Remito
//					}else{
//						row.add("");
//						row.add("");
//					}
//					if (pedidos_piezas.elementAt(j).getPnc()!=null){
//						row.add(pedidos_piezas.elementAt(j).getPnc());// PCN
//					}else{
//						row.add("");
//					}
//					if (pedidos_piezas.elementAt(j).getMuleto()!=null){
//						row.add(pedidos_piezas.elementAt(j).getMuleto().getId().toString());//Id Muleto
//					}else{
//						row.add("");
//					}
//					if (pedidos_piezas.elementAt(j).getMano_obra()!=null){
//						row.add(pedidos_piezas.elementAt(j).getMano_obra().getId().toString());//Id Mano Obra
//					}else{
//						row.add("");
//					}
//					if (pedidos_piezas.elementAt(j).getBdg()!=null){
//						row.add(pedidos_piezas.elementAt(j).getBdg().getNumero_bdg());//Numero BDG
//						if(pedidos_piezas.elementAt(j).getBdg().getFecha_bdg()!=null){
//							java.sql.Date fbdg = new java.sql.Date(pedidos_piezas.elementAt(j).getBdg().getFecha_bdg().getTime());
//							row.add(format2.format(fbdg));//Fecha BDG
//						}else{
//							row.add("");
//						}
//					}else{
//						row.add("");
//						row.add("");
//					}
//					
//					datosTabla.add(row);
//			}
//		}
//		modelo.setDataVector(datosTabla, nombreColumnas);
//		modelo.fireTableStructureChanged();
	}

	public void actualizarDatos() {
		datosTabla = new Vector<Vector<String>>();
		Vector<Pedido_PiezaDTO> pididos_piezas = mediador.obtenerPedidosPiezasEntidades();
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		for (int i=0; i< pididos_piezas.size();i++){
			Pedido_PiezaDTO pedido_pieza = pididos_piezas.elementAt(i);
			Vector<String> row = new Vector<String> ();
			
			row.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
			row.add(pedido_pieza.getEstado_pedido());//Estado Pedido
			row.add(pedido_pieza.getNumero_pedido());//Numero Pedido
					
			java.sql.Date fsp = new java.sql.Date(pedido_pieza.getPedido().getFecha_solicitud_pedido().getTime());
			row.add(format2.format(fsp));//Fecha Solicitud Pedido
					
			row.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//nombre entidad
			if (pedido_pieza.getPedido().getReclamo().getOrden()!=null)
				row.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			else
				row.add("");
			if (pedido_pieza.getPedido().getReclamo().getFecha_reclamo()!=null){
				java.sql.Date fr = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getFecha_reclamo().getTime());
				row.add(format2.format(fr));//Fecha Reclamo
			}else
				row.add("");
			if (pedido_pieza.getPedido().getReclamo().getFecha_turno()!=null){
				java.sql.Date ft = new java.sql.Date(pididos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno().getTime());
				row.add(format2.format(ft));//Fecha turno
			}else
				row.add("");
			row.add(pedido_pieza.getPedido().getReclamo().getReclamante().getNombre_apellido());//Nombre Reclamante	
			if (pididos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo()!=null){
				row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getNombre_titular());//Nombre Titular
				row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getDominio());//Dominio
				row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
			}else{
				row.add("");
				row.add("");
				row.add("");
			}
			if (pedido_pieza.getPieza()!=null)
				row.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
			else
				row.add("");
			if (pedido_pieza.getFecha_solicitud_fabrica()!=null){
				java.sql.Date fsf = new java.sql.Date(pedido_pieza.getFecha_solicitud_fabrica().getTime());
				row.add(format2.format(fsf));//Fecha Solicitud Fabrica
			}else
				row.add("");
			if (pedido_pieza.getFecha_recepcion_fabrica()!=null){
				java.sql.Date frf = new java.sql.Date(pedido_pieza.getFecha_recepcion_fabrica().getTime());
				row.add(format2.format(frf));//Fecha Recepcion Fabrica
			}else
				row.add("");
			if (pedido_pieza.getFecha_solicitud_devolucion()!=null){
				java.sql.Date fsd = new java.sql.Date(pedido_pieza.getFecha_solicitud_devolucion().getTime());
				row.add(format2.format(fsd));//Fecha Solicitud Devolucion
			}else
				row.add("");
			if (pedido_pieza.getFecha_aprobacion_solicitud_devolucion()!=null){
				java.sql.Date fas = new java.sql.Date(pedido_pieza.getFecha_aprobacion_solicitud_devolucion().getTime());
				row.add(format2.format(fas));//Fecha Aceptacion Devolucion
			}else
				row.add("");
			if (pedido_pieza.getDevolucion_pieza()!=null && pedido_pieza.getDevolucion_pieza().getFecha_devolucion()!=null){
				java.sql.Date fdf = new java.sql.Date(pedido_pieza.getDevolucion_pieza().getFecha_devolucion().getTime());
				row.add(format2.format(fdf));//Fecha Devolucion Fabrica
				row.add(pedido_pieza.getDevolucion_pieza().getNumero_remito());//numero remito
			}else{
				row.add("");
				row.add("");
			}
			if (pedido_pieza.getPnc()!=null)
				row.add(pedido_pieza.getPnc());// PCN
			else
				row.add("");
			if (pedido_pieza.getMuleto()!=null)
				row.add(pedido_pieza.getMuleto().getId().toString());//Id Muleto
			else
				row.add("");
			if (pedido_pieza.getMano_obra()!=null)
						row.add(pedido_pieza.getMano_obra().getId().toString());//Id Mano Obra
			else
				row.add("");
			if (pedido_pieza.getBdg()!=null){
				row.add(pedido_pieza.getBdg().getNumero_bdg());//Numero BDG
				if(pedido_pieza.getBdg().getFecha_bdg()!=null){
					java.sql.Date fbdg = new java.sql.Date(pedido_pieza.getBdg().getFecha_bdg().getTime());
					row.add(format2.format(fbdg));//Fecha BDG
				}else
					row.add("");
			}else{
				row.add("");
				row.add("");
			}		
			datosTabla.add(row);
		}
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
		tfpedido.setText("");
		dc_fsp.setDate(null);
		cBEstadoPedido.setSelectedIndex(0);
		dc_fturno.setDate(null);
		tfTitular.setText("");
		tfDominio.setText("");
		tfVin.setText("");
		tfPieza.setText("");
		dc_fReclamo.setDate(null);
		cbentidad.setSelectedIndex(0);
		tfReclamante.setText("");
		tfpnc.setText("");
		tfMuleto.setText("");
		tfManoObra.setText("");
		tfOrden.setText("");
		tfNumeroBDG.setText("");
		dCFBDG.setDate(null);
		dCFSF.setDate(null);
		dCFRF.setDate(null);
		dCFSD.setDate(null);
		dCFAS.setDate(null);
		dcFEF.setDate(null);
		tfRemito.setText("");
	}	
	
	protected void verReclamante() {
		int row = tablaPedidos.getSelectedRow();
		if (row >= 0) {
			String id_pedido = tablaPedidos.getValueAt(row, 0).toString();
			mediador.verRegistrante(id_pedido);
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un reclamante primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}