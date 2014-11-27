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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import cliente.Recursos.Botones.ButtonType;
import cliente.Recursos.Botones.GlossyButton;
import cliente.Recursos.util.JPanel_Whit_Image;
import cliente.Recursos.util.Theme;
import cliente.Recursos.util.TransparentPanel;

import com.toedter.calendar.JDateChooser;
import common.DTOs.OrdenDTO;
import common.DTOs.PiezaDTO;
import common.DTOs.ProveedorDTO;
import common.DTOs.ReclamanteDTO;
import common.DTOs.VehiculoDTO;

public class GUIAltaReclamoRapidoEntidad extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadoReclamoRapido mediador;
	
	private JTextField tfEmail_E;
	private JTextField tfNombreReclamante_E;
	private JTextField tfDni_E;
	private JTextField tfNombreTitular_E;
	private JTextField tfDominio_E;
	private JTextField tfVin_E;
	private JTextField tfNumeroOrden_E;


	private JButton btnCrear_E;
	private JButton btnCancelar_E;
	

	
	private JComboBox<String> cbEntidad;
	private Vector<String> numeros_piezas_R;
	
	private Vector<String> entidades;
	private JComboBox<String> cbTelefonos_E;
	private Vector<String> telefonos_E;
	private JButton btnAgregarTelefono_E;
	private JButton btnBuscarReclamante_E;
	private boolean reclamante_desdeEntidad;
	private JButton btnQuitarTelefono_E;
	private JRadioButton rbCelular_E;
	private JRadioButton rbFijo_E;

	private JButton btnBuscarVehiculo_E;
	private boolean vehiculo_desdeEntidad;
	private JComboBox<String> cbMarca_E;
	private Vector<String> marcas;
	private JCheckBox cBPeligroso;
	private JComboBox<String> cbModelo_E;
	private Vector<String> modelos;
	private JCheckBox cBInmovilizado;

	private JDateChooser dCFecha_Apertura_E;
	private JButton btnBuscarOrden_E;
	private boolean orden_desdeEntidad;
	private JDateChooser dCFecha_Reclamo_E;
	private JTextArea tADescripcion_E;
	private JButton btnLimpiar_E;
	private JTextField tfNumero_pedido_E;
	private JTextField tfNumero_Pieza_E;
	private JButton btn_Quitar_Pieza_E;
	private JButton btnAgregar_Pieza_E;
	private JDateChooser dCFSP_E;
	private JComboBox<String> cB_Piezas_E;
	private JComboBox<String> cb_proveedor_E;
	private JTextArea taDesc_Pieza_E;
	private Vector<String> proveedores;
	private DefaultComboBoxModel<String> cmbProveedor_R;
	private Vector<PiezaDTO> piezas_R;
	private DefaultComboBoxModel<String> cmbPieza_R;
	private JButton btnCopy_E;
	private JButton btn_clear_FSP_E;
	private JButton btn_clear_FA_E;
	private JButton btn_clear_FR_E;
	private JPanel contentPane;


	public GUIAltaReclamoRapidoEntidad(MediadoReclamoRapido mediadoReclamoRapido) {
		this.mediador = mediadoReclamoRapido;
		cargarDatos();
		initialize();
	}
	
	private void cargarDatos() {
		entidades = mediador.obtenerNombresEntidades();
		marcas = mediador.obtenerNombresMarcas();
		modelos = mediador.obtenerNombresModelos();
		proveedores = mediador.obtenerProveedores();
		piezas_R = new Vector<PiezaDTO>();
		numeros_piezas_R = new Vector<String>();
		telefonos_E = new Vector<String>();
	}
	
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0,1382,768);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setTitle("ALTA RECLAMO RAPIDO ENTIDAD");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIAltaReclamoRapidoEntidad.class.getResource("/cliente/Resources/Icons/reclamo_rapido.png")));

		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBounds(new Rectangle(0, 0, 1366, 768));
		getContentPane().setLayout(null);
		setContentPane(contentPane);

		cmbProveedor_R = new DefaultComboBoxModel<String>(proveedores);
		
		btnCrear_E = new GlossyButton("Crear",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCrear_E.setPreferredSize(new Dimension(150, 25));
		btnCrear_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCrear_E.setIcon(new ImageIcon(GUIAltaReclamoRapidoEntidad.class.getResource("/cliente/Resources/Icons/check.png")));
		btnCrear_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearReclamoEntidad();
			}
		});
		contentPane.setLayout(null);
		
		JPanel selecEntidad = new TransparentPanel();
		selecEntidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		selecEntidad.setBounds(48, 30, 610, 59);
		getContentPane().add(selecEntidad);
		selecEntidad.setLayout(null);
		
		JLabel lblEntidad = new JLabel("ENTIDAD");
		lblEntidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntidad.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEntidad.setBounds(10, 11, 140, 19);
		selecEntidad.add(lblEntidad);
		
		cbEntidad = new JComboBox<String>();
		cbEntidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbEntidad.setModel(new DefaultComboBoxModel<String>(entidades));
		cbEntidad.setBounds(160, 11, 200, 20);
		selecEntidad.add(cbEntidad);
		
		JPanel selectReclamoEntidad = new TransparentPanel();
		selectReclamoEntidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		selectReclamoEntidad.setBounds(706, 30, 610, 150);
		getContentPane().add(selectReclamoEntidad);
		selectReclamoEntidad.setLayout(null);
		
		dCFecha_Reclamo_E = new JDateChooser();
		dCFecha_Reclamo_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFecha_Reclamo_E.setDate(new Date());
		dCFecha_Reclamo_E.setBounds(160, 11, 160, 20);
		selectReclamoEntidad.add(dCFecha_Reclamo_E);
		
		JLabel label_11 = new JLabel("Fecha Reclamo");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(10, 11, 130, 20);
		selectReclamoEntidad.add(label_11);
		
		JLabel label_12 = new JLabel("Descripcion");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(10, 44, 130, 20);
		selectReclamoEntidad.add(label_12);
		
		tADescripcion_E = new JTextArea(4, 31);
		tADescripcion_E.setToolTipText("Max 125 Caracteres");
		tADescripcion_E.addKeyListener(new KeyListener() {
		private int limite = 125;
		public void keyTyped(KeyEvent e) {
			if (tADescripcion_E.getText().length()>=limite){
				e.consume();					
			}
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
				//buscar();
			}
		}
		@Override
		public void keyReleased(KeyEvent arg0) {			
		}
		});
		tADescripcion_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tADescripcion_E.setLineWrap(true);
		tADescripcion_E.setBounds(160, 42, 400, 74);
		selectReclamoEntidad.add(tADescripcion_E);
		
		btn_clear_FR_E = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_clear_FR_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FR_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dCFecha_Reclamo_E.getDate()!=null)
					dCFecha_Reclamo_E.setDate(null);
			}
		});
		btn_clear_FR_E.setIcon(new ImageIcon(GUIAltaReclamoRapidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FR_E.setBounds(335, 11, 25, 20);
		selectReclamoEntidad.add(btn_clear_FR_E);
		
		JPanel selectReclamanteEntidad = new TransparentPanel();
		selectReclamanteEntidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		selectReclamanteEntidad.setLayout(null);
		selectReclamanteEntidad.setBounds(48, 94, 610, 206);
		getContentPane().add(selectReclamanteEntidad);
		
		JLabel lbnombreReclamante_E = new JLabel("Nombre Del Reclamante");
		lbnombreReclamante_E.setHorizontalAlignment(SwingConstants.CENTER);
		lbnombreReclamante_E.setBounds(10, 11, 139, 20);
		selectReclamanteEntidad.add(lbnombreReclamante_E);
		
		JLabel lblEmail_E = new JLabel("Email");
		lblEmail_E.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail_E.setBounds(10, 42, 139, 20);
		selectReclamanteEntidad.add(lblEmail_E);
		
		tfEmail_E = new JTextField();
		tfEmail_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfEmail_E.setColumns(10);
		tfEmail_E.setBounds(160, 42, 200, 20);
		selectReclamanteEntidad.add(tfEmail_E);
		
		tfNombreReclamante_E = new JTextField();
		tfNombreReclamante_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNombreReclamante_E.setColumns(10);
		tfNombreReclamante_E.setBounds(160, 11, 200, 20);
		selectReclamanteEntidad.add(tfNombreReclamante_E);
		
		JLabel lblDni_E = new JLabel("DNI");
		lblDni_E.setHorizontalAlignment(SwingConstants.CENTER);
		lblDni_E.setBounds(10, 72, 139, 20);
		selectReclamanteEntidad.add(lblDni_E);
		
		JLabel lblTelefonos_E = new JLabel("Telefonos");
		lblTelefonos_E.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefonos_E.setBounds(10, 103, 139, 20);
		selectReclamanteEntidad.add(lblTelefonos_E);
		
		cbTelefonos_E = new JComboBox<String>();
		cbTelefonos_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbTelefonos_E.setEditable(true);
		cbTelefonos_E.setBounds(160, 103, 200, 20);
		selectReclamanteEntidad.add(cbTelefonos_E);
		
		tfDni_E = new JTextField();
		tfDni_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfDni_E.setToolTipText("Ej 12345678");
		tfDni_E.addKeyListener(new KeyListener() {
		private int limite = 8;
		public void keyTyped(KeyEvent e) {
			if (tfDni_E.getText().length()>=limite){
				e.consume();					
			}
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
				//buscar();
			}
		}
		@Override
		public void keyReleased(KeyEvent arg0) {			
		}
		});
		tfDni_E.setColumns(10);
		tfDni_E.setBounds(160, 72, 200, 20);
		selectReclamanteEntidad.add(tfDni_E);
		
		btnAgregarTelefono_E = new GlossyButton("Agregar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnAgregarTelefono_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregarTelefono_E.setIcon(new ImageIcon(GUIAltaReclamoRapidoEntidad.class.getResource("/cliente/Resources/Icons/add.png")));
		btnAgregarTelefono_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nuevo_telefono = (String)cbTelefonos_E.getSelectedItem();
				if (!telefonos_E.contains(nuevo_telefono) && nuevo_telefono != null && nuevo_telefono!=""){
					if (rbFijo_E.isSelected()){
						nuevo_telefono += " (Fijo)";
						telefonos_E.add(nuevo_telefono);
						DefaultComboBoxModel<String> comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos_E);
						cbTelefonos_E.setModel(comboBOX_Modelo);
						cbTelefonos_E.setSelectedIndex(-1);
						rbFijo_E.setSelected(false);
					}else{
						if (rbCelular_E.isSelected()){
							nuevo_telefono +=" (Celular)";
							telefonos_E.add(nuevo_telefono);
							DefaultComboBoxModel<String> comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos_E);
							cbTelefonos_E.setModel(comboBOX_Modelo);
							cbTelefonos_E.setSelectedIndex(-1);
							rbCelular_E.setSelected(false);
						}else{
							JOptionPane.showMessageDialog(null,"Seleccione el tipo de telefono.","Advertencia",JOptionPane.INFORMATION_MESSAGE);

						}
					}					
				}
			}
		});
		btnAgregarTelefono_E.setBounds(370, 103, 110, 20);
		selectReclamanteEntidad.add(btnAgregarTelefono_E);
		
		btnQuitarTelefono_E = new GlossyButton("Quitar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnQuitarTelefono_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnQuitarTelefono_E.setIcon(new ImageIcon(GUIAltaReclamoRapidoEntidad.class.getResource("/cliente/Resources/Icons/delete.png")));
		btnQuitarTelefono_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (telefonos_E.contains((String)cbTelefonos_E.getSelectedItem())){
					telefonos_E.remove((String)cbTelefonos_E.getSelectedItem());
					DefaultComboBoxModel<String> comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos_E);
					cbTelefonos_E.setModel(comboBOX_Modelo);
					cbTelefonos_E.setSelectedIndex(-1);
					rbFijo_E.setSelected(false);
					rbCelular_E.setSelected(false);					
				}
			}
		});
		btnQuitarTelefono_E.setBounds(491, 103, 110, 20);
		selectReclamanteEntidad.add(btnQuitarTelefono_E);
		
		rbCelular_E = new JRadioButton("Celular");
		rbCelular_E.setContentAreaFilled(false);
		rbCelular_E.setBorder(null);
		rbCelular_E.setBounds(160, 129, 120, 23);
		selectReclamanteEntidad.add(rbCelular_E);
		
		rbFijo_E = new JRadioButton("Fijo");
		rbFijo_E.setBorder(null);
		rbFijo_E.setContentAreaFilled(false);
		rbFijo_E.setBounds(160, 155, 120, 23);
		selectReclamanteEntidad.add(rbFijo_E);
		
		btnBuscarReclamante_E = new GlossyButton("Buscar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnBuscarReclamante_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnBuscarReclamante_E.setIcon(new ImageIcon(GUIAltaReclamoRapidoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnBuscarReclamante_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reclamante_desdeEntidad = true;
				mediador.buscarReclamante();
			}
		});
		btnBuscarReclamante_E.setBounds(430, 11, 155, 20);
		selectReclamanteEntidad.add(btnBuscarReclamante_E);
		
		JPanel selectOrdenEntidad = new TransparentPanel();
		selectOrdenEntidad.setBounds(706, 185, 610, 115);
		getContentPane().add(selectOrdenEntidad);
		selectOrdenEntidad.setLayout(null);
		selectOrdenEntidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JLabel label_2 = new JLabel("Fecha Apertura");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(10, 40, 130, 20);
		selectOrdenEntidad.add(label_2);
		
		dCFecha_Apertura_E = new JDateChooser();
		dCFecha_Apertura_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFecha_Apertura_E.setBounds(160, 40, 160, 20);
		selectOrdenEntidad.add(dCFecha_Apertura_E);
		
		tfNumeroOrden_E = new JTextField();
		tfNumeroOrden_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumeroOrden_E.setColumns(10);
		tfNumeroOrden_E.setBounds(160, 10, 250, 20);
		selectOrdenEntidad.add(tfNumeroOrden_E);
		
		JLabel label_10 = new JLabel("Numero Orden");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(10, 10, 130, 20);
		selectOrdenEntidad.add(label_10);
		
		btnBuscarOrden_E = new GlossyButton("Buscar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnBuscarOrden_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnBuscarOrden_E.setIcon(new ImageIcon(GUIAltaReclamoRapidoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnBuscarOrden_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orden_desdeEntidad = true;
				mediador.buscarOrden();
			}
		});
		btnBuscarOrden_E.setBounds(445, 10, 155, 23);
		selectOrdenEntidad.add(btnBuscarOrden_E);
		
		btn_clear_FA_E = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_clear_FA_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FA_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFecha_Apertura_E.getDate()!=null)
					dCFecha_Apertura_E.setDate(null);
			}
		});
		btn_clear_FA_E.setIcon(new ImageIcon(GUIAltaReclamoRapidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FA_E.setBounds(335, 40, 25, 20);
		selectOrdenEntidad.add(btn_clear_FA_E);
		
		JPanel selectVehiculoEntidad = new TransparentPanel();
		selectVehiculoEntidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		selectVehiculoEntidad.setBounds(48, 311, 610, 299);
		getContentPane().add(selectVehiculoEntidad);
		selectVehiculoEntidad.setLayout(null);
		
		JLabel lblNombreTitular_E = new JLabel("Nombre del Titular");
		lblNombreTitular_E.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreTitular_E.setBounds(10, 12, 130, 20);
		selectVehiculoEntidad.add(lblNombreTitular_E);
		
		tfNombreTitular_E = new JTextField();
		tfNombreTitular_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNombreTitular_E.setColumns(10);
		tfNombreTitular_E.setBounds(160, 12, 200, 20);
		selectVehiculoEntidad.add(tfNombreTitular_E);
		
		tfDominio_E = new JTextField();
		tfDominio_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfDominio_E.setToolTipText("Ej XYZ123");
		tfDominio_E.addKeyListener(new KeyListener() {
		private int limite = 6;
		public void keyTyped(KeyEvent e) {
			if (tfDominio_E.getText().length()>=limite){
				e.consume();					
			}
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
				//buscar();
			}
		}
		@Override
		public void keyReleased(KeyEvent arg0) {			
		}
		});
		tfDominio_E.setColumns(10);
		tfDominio_E.setBounds(160, 42, 79, 20);
		selectVehiculoEntidad.add(tfDominio_E);
		
		JLabel lblDominio_E = new JLabel("Dominio");
		lblDominio_E.setHorizontalAlignment(SwingConstants.CENTER);
		lblDominio_E.setBounds(10, 42, 130, 20);
		selectVehiculoEntidad.add(lblDominio_E);
		
		JLabel lvlVin_E = new JLabel("VIN");
		lvlVin_E.setHorizontalAlignment(SwingConstants.CENTER);
		lvlVin_E.setBounds(10, 72, 130, 20);
		selectVehiculoEntidad.add(lvlVin_E);
		
		tfVin_E = new JTextField();
		tfVin_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfVin_E.setToolTipText("Ej 12345678901234567");
		tfVin_E.addKeyListener(new KeyListener() {
		private int limite = 17;
		public void keyTyped(KeyEvent e) {
			if (tfVin_E.getText().length()>=limite){
				e.consume();					
			}
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
				//buscar();
			}
		}
		@Override
		public void keyReleased(KeyEvent arg0) {			
		}
		});
		tfVin_E.setColumns(10);
		tfVin_E.setBounds(160, 73, 200, 20);
		selectVehiculoEntidad.add(tfVin_E);
		
		JLabel label_3 = new JLabel("Marca");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(10, 103, 130, 20);
		selectVehiculoEntidad.add(label_3);
		
		cbMarca_E = new JComboBox<String>();
		cbMarca_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbMarca_E.setModel(new DefaultComboBoxModel<String>(marcas));
		cbMarca_E.setBounds(160, 103, 200, 20);
		selectVehiculoEntidad.add(cbMarca_E);
		
		JLabel label_4 = new JLabel("Modelo");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(10, 133, 130, 20);
		selectVehiculoEntidad.add(label_4);
		
		cbModelo_E = new JComboBox<String>();
		cbModelo_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbModelo_E.setModel(new DefaultComboBoxModel<String>(modelos));
		cbModelo_E.setBounds(160, 133, 200, 20);
		selectVehiculoEntidad.add(cbModelo_E);
		
		btnBuscarVehiculo_E = new GlossyButton("Buscar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnBuscarVehiculo_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnBuscarVehiculo_E.setIcon(new ImageIcon(GUIAltaReclamoRapidoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnBuscarVehiculo_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vehiculo_desdeEntidad = true;
				mediador.buscarVehiculo();
			}
		});
		btnBuscarVehiculo_E.setBounds(430, 12, 155, 20);
		selectVehiculoEntidad.add(btnBuscarVehiculo_E);
		
		cBPeligroso = new JCheckBox("PELIGROSO");
		cBPeligroso.setBorder(null);
		cBPeligroso.setContentAreaFilled(false);
		cBPeligroso.setBounds(160, 164, 126, 23);
		selectVehiculoEntidad.add(cBPeligroso);
		
		cBInmovilizado = new JCheckBox("INMOVILIZADO");
		cBInmovilizado.setBorder(null);
		cBInmovilizado.setContentAreaFilled(false);
		cBInmovilizado.setBounds(160, 194, 126, 23);
		selectVehiculoEntidad.add(cBInmovilizado);
		
		btnCopy_E = new JButton("");
		btnCopy_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCopy_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!tfNombreReclamante_E.getText().isEmpty())
					tfNombreTitular_E.setText(tfNombreReclamante_E.getText());
			}
		});
		btnCopy_E.setIcon(new ImageIcon(GUIAltaReclamoRapidoEntidad.class.getResource("/cliente/Resources/Icons/copy.png")));
		btnCopy_E.setBounds(360, 12, 25, 20);
		selectVehiculoEntidad.add(btnCopy_E);
		
		JPanel selectPiezasEntidad = new TransparentPanel();
		selectPiezasEntidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		selectPiezasEntidad.setBounds(706, 311, 610, 299);
		getContentPane().add(selectPiezasEntidad);
		selectPiezasEntidad.setLayout(null);
		
		tfNumero_pedido_E = new JTextField();
		tfNumero_pedido_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_pedido_E.setColumns(10);
		tfNumero_pedido_E.setBounds(154, 10, 256, 20);
		selectPiezasEntidad.add(tfNumero_pedido_E);
		
		dCFSP_E = new JDateChooser();
		dCFSP_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFSP_E.setBounds(154, 35, 163, 20);
		selectPiezasEntidad.add(dCFSP_E);
		
		cB_Piezas_E = new JComboBox<String>();
		cB_Piezas_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cB_Piezas_E.setBounds(154, 60, 163, 20);
		selectPiezasEntidad.add(cB_Piezas_E);
		
		btnAgregar_Pieza_E = new GlossyButton("Agregar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnAgregar_Pieza_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregar_Pieza_E.setIcon(new ImageIcon(GUIAltaReclamoRapidoEntidad.class.getResource("/cliente/Resources/Icons/add.png")));
		btnAgregar_Pieza_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarPieza_R();
			}
		});
		btnAgregar_Pieza_E.setBounds(327, 60, 110, 20);
		selectPiezasEntidad.add(btnAgregar_Pieza_E);
		
		btn_Quitar_Pieza_E = new GlossyButton("Quitar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_Quitar_Pieza_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_Quitar_Pieza_E.setIcon(new ImageIcon(GUIAltaReclamoRapidoEntidad.class.getResource("/cliente/Resources/Icons/delete.png")));
		btn_Quitar_Pieza_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitar_R(); 
			}
		});
		btn_Quitar_Pieza_E.setBounds(447, 59, 110, 20);
		selectPiezasEntidad.add(btn_Quitar_Pieza_E);
		
		tfNumero_Pieza_E = new JTextField();
		tfNumero_Pieza_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_Pieza_E.addKeyListener(new KeyListener() {
		private int limite = 12;
		public void keyTyped(KeyEvent e) {
			if (tfNumero_Pieza_E.getText().length()>=limite){
				e.consume();					
			}
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
				//buscar();
			}
		}
		@Override
		public void keyReleased(KeyEvent arg0) {			
		}
		});
		tfNumero_Pieza_E.setColumns(10);
		tfNumero_Pieza_E.setBounds(154, 85, 163, 20);
		selectPiezasEntidad.add(tfNumero_Pieza_E);
		
		cb_proveedor_E = new JComboBox<String>();
		cb_proveedor_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cb_proveedor_E.setModel(cmbProveedor_R);
		cb_proveedor_E.setBounds(154, 110, 163, 20);
		selectPiezasEntidad.add(cb_proveedor_E);
		
		taDesc_Pieza_E = new JTextArea(4, 31);
		taDesc_Pieza_E.setToolTipText("Max 125 Caracteres");
		taDesc_Pieza_E.addKeyListener(new KeyListener() {
		private int limite = 125;
		public void keyTyped(KeyEvent e) {
			if (taDesc_Pieza_E.getText().length()>=limite){
				e.consume();					
			}
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
				//buscar();
			}
		}
		@Override
		public void keyReleased(KeyEvent arg0) {			
		}
		});
		taDesc_Pieza_E.setLineWrap(true);
		taDesc_Pieza_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		taDesc_Pieza_E.setBounds(154, 135, 400, 72);
		selectPiezasEntidad.add(taDesc_Pieza_E);
		
		JLabel label = new JLabel("Numero Pedido");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 10, 130, 20);
		selectPiezasEntidad.add(label);
		
		JLabel label_1 = new JLabel("Fecha Solicitud Pedido");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 35, 130, 20);
		selectPiezasEntidad.add(label_1);
		
		JLabel label_7 = new JLabel("Piezas");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(10, 60, 130, 20);
		selectPiezasEntidad.add(label_7);
		
		JLabel label_20 = new JLabel("Numero Pieza");
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		label_20.setBounds(10, 85, 130, 20);
		selectPiezasEntidad.add(label_20);
		
		JLabel label_21 = new JLabel("Proveedor");
		label_21.setHorizontalAlignment(SwingConstants.CENTER);
		label_21.setBounds(10, 110, 130, 20);
		selectPiezasEntidad.add(label_21);
		
		JLabel label_24 = new JLabel("Descripcion");
		label_24.setHorizontalAlignment(SwingConstants.CENTER);
		label_24.setBounds(10, 135, 130, 20);
		selectPiezasEntidad.add(label_24);
		
		btn_clear_FSP_E = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_clear_FSP_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FSP_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFSP_E.getDate()!=null)
					dCFSP_E.setDate(null);
			}
		});
		btn_clear_FSP_E.setIcon(new ImageIcon(GUIAltaReclamoRapidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FSP_E.setBounds(327, 35, 25, 20);
		selectPiezasEntidad.add(btn_clear_FSP_E);
		btnCrear_E.setBounds(191, 639, 200, 30);
		getContentPane().add(btnCrear_E);
		
		btnLimpiar_E = new GlossyButton("Limpiar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnLimpiar_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnLimpiar_E.setIcon(new ImageIcon(GUIAltaReclamoRapidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btnLimpiar_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
			}
		});
		
		btnCancelar_E = new GlossyButton("Cancelar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCancelar_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCancelar_E.setIcon(new ImageIcon(GUIAltaReclamoRapidoEntidad.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar_E.setBounds(582, 639, 200, 30);
		getContentPane().add(btnCancelar_E);
		btnLimpiar_E.setBounds(973, 639, 200, 30);
		getContentPane().add(btnLimpiar_E);
	}
	protected void agregarPieza_R() {
		if (tfNumero_Pieza_E.getText().isEmpty()|| cb_proveedor_E.getSelectedItem()==null ){
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			
			String numero_pieza = tfNumero_Pieza_E.getText();
			
			if (!numeros_piezas_R.contains(numero_pieza)){
				ProveedorDTO proveedor = mediador.obtenerProveedor(cb_proveedor_E.getSelectedItem().toString());
				PiezaDTO pieza = new PiezaDTO(numero_pieza, taDesc_Pieza_E.getText(), proveedor);
				piezas_R.add(pieza);
				numeros_piezas_R.add(numero_pieza);
				cmbPieza_R = new DefaultComboBoxModel<String>(numeros_piezas_R);
				cB_Piezas_E.setModel(cmbPieza_R);
				cB_Piezas_E.setSelectedIndex(-1);
				cb_proveedor_E.setSelectedIndex(0);
				tfNumero_Pieza_E.setText("");
				taDesc_Pieza_E.setText("");
			}else{
				JOptionPane.showMessageDialog(this,"Ya existe la pieza.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
			}
				
		}	
	}
	protected void quitar_R() {
		if (numeros_piezas_R.contains((String)cB_Piezas_E.getSelectedItem())){
			numeros_piezas_R.remove((String)cB_Piezas_E.getSelectedItem());
			for (int i=0; i<piezas_R.size();i++){
				if (piezas_R.elementAt(i).getNumero_pieza().equals((String)cB_Piezas_E.getSelectedItem())){
					piezas_R.remove(i);
				}
			}
			cmbPieza_R = new DefaultComboBoxModel<String>(numeros_piezas_R);
			cB_Piezas_E.setModel(cmbPieza_R);
			cB_Piezas_E.setSelectedIndex(-1);					
		}
	}
	public boolean chequearCamposEntidad(){
		boolean res = false;
		res = (cbEntidad.getSelectedItem()==null || tfNombreReclamante_E.getText().isEmpty() || tfDni_E.getText().isEmpty() 
				|| cbTelefonos_E.getModel().getSize()<1 || tfNombreTitular_E.getText().isEmpty() || tfDominio_E.getText().isEmpty()
				|| tfVin_E.getText().isEmpty() || cbMarca_E.getModel().getSize()<1 || cbModelo_E.getModel().getSize()<1 
				|| tfNumeroOrden_E.getText().isEmpty() || dCFecha_Apertura_E.getDate()==null || dCFecha_Reclamo_E.getDate()==null
				|| (piezas_R.size()>0 && dCFSP_E.getDate()==null)
				);
		return res;
	}

	protected void crearReclamoEntidad() {
		if (chequearCamposEntidad()){
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String fecha = format2.format(dCFecha_Apertura_E.getDate());
		    java.sql.Date fechaApertura = new java.sql.Date(dCFecha_Apertura_E.getDate().getTime());
		    
			fecha = format2.format(dCFecha_Reclamo_E.getDate());
		    java.sql.Date fechaReclamo = new java.sql.Date(dCFecha_Reclamo_E.getDate().getTime());
		    
		    java.sql.Date fechaSP = null;
		    if(dCFSP_E.getDate()!=null){
				fecha = format2.format(dCFSP_E.getDate());
				fechaSP = new java.sql.Date(dCFSP_E.getDate().getTime());
		    }
		    
		    Vector<String> telefonos_reclamante = new Vector<String>();
		    for (int i = 0;i< cbTelefonos_E.getModel().getSize(); i++){
		    	telefonos_reclamante.add(cbTelefonos_E.getModel().getElementAt(i).toString());
		    }
			if (mediador.nuevoReclamo(cbEntidad.getSelectedItem().toString(),tfNombreReclamante_E.getText(),tfDni_E.getText(), tfEmail_E.getText(),
				telefonos_reclamante, tfNombreTitular_E.getText(),tfDominio_E.getText(),tfVin_E.getText(),
				cbMarca_E.getSelectedItem().toString(), cbModelo_E.getSelectedItem().toString(),tfNumeroOrden_E.getText(),
				fechaApertura,fechaReclamo,tADescripcion_E.getText(),piezas_R,tfNumero_pedido_E.getText(),fechaSP,cBPeligroso.isSelected(),cBInmovilizado.isSelected())){
				JOptionPane.showMessageDialog(this,"Reclamo Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}else{
				JOptionPane.showMessageDialog(this,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public boolean isReclamante_desdeEntidad() {
		return reclamante_desdeEntidad;
	}

	public void setReclamante_desdeEntidad(boolean reclamante_desdeEntidad) {
		this.reclamante_desdeEntidad = reclamante_desdeEntidad;
	}

	public boolean isVehiculo_desdeEntidad() {
		return vehiculo_desdeEntidad;
	}

	public void setVehiculo_desdeEntidad(boolean vehiculo_desdeEntidad) {
		this.vehiculo_desdeEntidad = vehiculo_desdeEntidad;
	}

	public boolean isOrden_desdeEntidad() {
		return orden_desdeEntidad;
	}

	public void setOrden_desdeEntidad(boolean orden_desdeEntidad) {
		this.orden_desdeEntidad = orden_desdeEntidad;
	}

	public void setOrdenEntidad(OrdenDTO orden) {
		tfNumeroOrden_E.setText(orden.getNumero_orden());
		dCFecha_Apertura_E.setDate(orden.getFecha_apertura());	
	}

	public void setReclamanteEntidad(ReclamanteDTO reclamante) {
		tfNombreReclamante_E.setText(reclamante.getNombre_apellido());
		tfEmail_E.setText(reclamante.getEmail());
		tfDni_E.setText(reclamante.getDni());
		telefonos_E = mediador.obtenerTelefonos(reclamante);
		DefaultComboBoxModel<String> comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos_E);
		cbTelefonos_E.setModel(comboBOX_Modelo);
	}

	public void setVehiculoEntidad(VehiculoDTO vehiculo) {
		tfNombreTitular_E.setText(vehiculo.getNombre_titular());
		tfDominio_E.setText(vehiculo.getDominio());
		tfVin_E.setText(vehiculo.getVin());
		cbMarca_E.setSelectedItem(vehiculo.getMarca().getNombre_marca());
		cbModelo_E.setSelectedItem(vehiculo.getModelo().getNombre_modelo());
	}

	public Vector<String> getTelefonos() {
		return telefonos_E;
	}

	public void setTelefonos(Vector<String> telefonos) {
		this.telefonos_E = telefonos;
	}
	
	private void limpiar() {
		//ENTIDAD
		cbEntidad.setSelectedIndex(0);
		
		tfNombreReclamante_E.setText("");
		tfEmail_E.setText("");
		tfDni_E.setText("");

		telefonos_E = new Vector<String>();

		DefaultComboBoxModel<String> comboBOX_Modelo_E = new DefaultComboBoxModel<String>(telefonos_E);
		cbTelefonos_E.setModel(comboBOX_Modelo_E);
		
		rbCelular_E.setSelected(false);
		rbFijo_E.setSelected(false);
		reclamante_desdeEntidad=false;
				
		tfNombreTitular_E.setText("");
		tfDominio_E.setText("");
		tfVin_E.setText("");
		cBInmovilizado.setSelected(false);
		cBPeligroso.setSelected(false);
		cbMarca_E.setSelectedIndex(0);
		cbModelo_E.setSelectedIndex(0);
		vehiculo_desdeEntidad=false;
		
		tfNumeroOrden_E.setText("");
		dCFecha_Apertura_E.setDate(null);
		orden_desdeEntidad=false;
		
		dCFecha_Reclamo_E.setDate(new Date());
		tADescripcion_E.setText("");
		
		piezas_R = new Vector<PiezaDTO>();
		
		tfNumero_pedido_E.setText("");

		dCFSP_E.setDate(null);
		
		tfNumero_Pieza_E.setText("");
		
		numeros_piezas_R = new Vector<String>();
		DefaultComboBoxModel<String> comboBOX_Pieza_R = new DefaultComboBoxModel<String>(numeros_piezas_R);
		
		taDesc_Pieza_E.setText("");
		cb_proveedor_E.setSelectedIndex(0);
		
	}
}
