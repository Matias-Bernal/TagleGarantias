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
import javax.swing.JTabbedPane;
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

public class GUIAltaReclamoRapido extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadoReclamoRapido mediador;
	
	private JTextField tfEmail_E;
	private JTextField tfNombreReclamante_E;
	private JTextField tfDni_E;
	private JTextField tfNombreTitular_E;
	private JTextField tfDominio_E;
	private JTextField tfVin_E;
	private JTextField tfNumeroOrden_E;
	private JTextField tfNumeroOrden_A;
	private JTextField tfEmail_A;
	private JTextField tfNombreReclamante_A;
	private JTextField tfDni_A;
	private JTextField tfNombreTitular_A;
	private JTextField tfDominio_A;
	private JTextField tfVin_A;


	private JButton btnCrear_E;
	private JButton btnCancelar_E;
	private JButton btnCrear_A;
	private JButton btnCancelar_A;
	

	
	private JComboBox<String> cbEntidad;
	private Vector<String> numeros_piezas_A;
	private Vector<String> numeros_piezas_R;
	
	private Vector<String> entidades;
	private JComboBox<String> cbTelefonos_E;
	private Vector<String> telefonos_E;
	private Vector<String> telefonos_A;
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
	private JComboBox<String> cbAgente;
	private Vector<String> agentes;
	private JButton btnBuscarReclamante_A;
	private JComboBox<String> cbTelefonos_A;
	private JButton btnAgregarTelefono_A;
	private JButton btnQuitarTelefono_A;
	private JRadioButton rbCelular_A;
	private JRadioButton rbFijo_A;
	private JButton btnBuscarVehiculo_A;
	private JComboBox<String> cbMarca_A;
	private JComboBox<String> cbModelo_A;
	private JDateChooser dCFecha_Apertura_A;
	private JDateChooser dCFecha_Reclamo_A;
	private JTextArea tADescripcion_A;
	private JButton btnLimpiar_E;
	private JButton btnLimpiar_A;
	private JTextField tfNumero_pedido_E;
	private JTextField tfNumero_Pieza_R;
	private JButton btn_Quitar_Pieza_R;
	private JButton btnAgregar_Pieza_R;
	private JDateChooser dCFSP_R;
	private JComboBox<String> cB_Piezas_R;
	private JComboBox<String> cb_proveedor_R;
	private JTextArea taDesc_Pieza_R;
	private JTextField tf_Num_Pedido_A;
	private JTextField tfNumero_Pieza_A;
	private JDateChooser dcFSP_A;
	private JComboBox<String> cbPiezas_A;
	private JButton btnAgregar_PiezaA;
	private JButton btnQuitar_Piezas_A;
	private JComboBox<String> cbProveedores_A;
	private JTextArea tADesc_Pieza_A;
	private Vector<String> proveedores;
	private DefaultComboBoxModel<String> cmbProveedor_A;
	private DefaultComboBoxModel<String> cmbProveedor_R;
	private Vector<PiezaDTO> piezas_A;
	private Vector<PiezaDTO> piezas_R;
	private DefaultComboBoxModel<String> cmbPieza_R;
	private DefaultComboBoxModel<String> cmbPieza_A;
	private JButton btnCopy_E;
	private JButton btnCopy_A;
	private JButton btn_clearFA_A;
	private JButton btn_clear_FSP_A;
	private JButton btn_clear_FSP_E;
	private JButton btn_clear_FA_E;
	private JButton btn_clear_FR_A;
	private JButton btn_clear_FR_E;

	public GUIAltaReclamoRapido(MediadoReclamoRapido mediadoReclamoRapido) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/reclamo_rapido.png")));
		this.mediador = mediadoReclamoRapido;
		cargarDatos();
		initialize();
	}
	
	private void cargarDatos() {
		entidades = mediador.obtenerNombresEntidades();
		agentes = mediador.obtenerNombresAgentes();
		marcas = mediador.obtenerNombresMarcas();
		modelos = mediador.obtenerNombresModelos();
		proveedores = mediador.obtenerProveedores();
		piezas_A = new Vector<PiezaDTO>();
		piezas_R = new Vector<PiezaDTO>();
		numeros_piezas_A = new Vector<String>();
		numeros_piezas_R = new Vector<String>();
		telefonos_E = new Vector<String>();
		telefonos_A = new Vector<String>();
	}
	
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1037, 571);
		setTitle("ALTA RECLAMO RAPIDO");
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setSize(new Dimension(111, 0));
		tabbedPane.setBounds(0, 0, 1031, 543);
		getContentPane().add(tabbedPane);
		cmbProveedor_A = new DefaultComboBoxModel<String>(proveedores);
		
		JPanel entidad = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		entidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tabbedPane.addTab("ENTIDAD", new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/entidad.png")), entidad, null);
		entidad.setLayout(null);
		
		JPanel selecEntidad = new TransparentPanel();
		selecEntidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		selecEntidad.setBounds(10, 11, 505, 42);
		entidad.add(selecEntidad);
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
		
		JPanel selectReclamanteEntidad = new TransparentPanel();
		selectReclamanteEntidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		selectReclamanteEntidad.setLayout(null);
		selectReclamanteEntidad.setBounds(10, 55, 505, 181);
		entidad.add(selectReclamanteEntidad);
		
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
		cbTelefonos_E.setBounds(160, 103, 120, 20);
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
		tfDni_E.setBounds(160, 72, 120, 20);
		selectReclamanteEntidad.add(tfDni_E);
		
		btnAgregarTelefono_E = new GlossyButton("Agregar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnAgregarTelefono_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregarTelefono_E.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/add.png")));
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
		btnAgregarTelefono_E.setBounds(290, 105, 110, 20);
		selectReclamanteEntidad.add(btnAgregarTelefono_E);
		
		btnQuitarTelefono_E = new GlossyButton("Quitar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnQuitarTelefono_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnQuitarTelefono_E.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/delete.png")));
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
		btnQuitarTelefono_E.setBounds(290, 130, 110, 20);
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
		btnBuscarReclamante_E.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnBuscarReclamante_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reclamante_desdeEntidad = true;
				mediador.buscarReclamante();
			}
		});
		btnBuscarReclamante_E.setBounds(385, 11, 110, 20);
		selectReclamanteEntidad.add(btnBuscarReclamante_E);
		
		JPanel selectVehiculoEntidad = new TransparentPanel();
		selectVehiculoEntidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		selectVehiculoEntidad.setBounds(10, 240, 505, 215);
		entidad.add(selectVehiculoEntidad);
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
		tfVin_E.setBounds(160, 73, 155, 20);
		selectVehiculoEntidad.add(tfVin_E);
		
		JLabel label_3 = new JLabel("Marca");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(10, 103, 130, 20);
		selectVehiculoEntidad.add(label_3);
		
		cbMarca_E = new JComboBox<String>();
		cbMarca_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbMarca_E.setModel(new DefaultComboBoxModel<String>(marcas));
		cbMarca_E.setBounds(160, 103, 155, 20);
		selectVehiculoEntidad.add(cbMarca_E);
		
		JLabel label_4 = new JLabel("Modelo");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(10, 133, 130, 20);
		selectVehiculoEntidad.add(label_4);
		
		cbModelo_E = new JComboBox<String>();
		cbModelo_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbModelo_E.setModel(new DefaultComboBoxModel<String>(modelos));
		cbModelo_E.setBounds(160, 133, 155, 20);
		selectVehiculoEntidad.add(cbModelo_E);
		
		btnBuscarVehiculo_E = new GlossyButton("Buscar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnBuscarVehiculo_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnBuscarVehiculo_E.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnBuscarVehiculo_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vehiculo_desdeEntidad = true;
				mediador.buscarVehiculo();
			}
		});
		btnBuscarVehiculo_E.setBounds(385, 12, 110, 20);
		selectVehiculoEntidad.add(btnBuscarVehiculo_E);
		
		cBPeligroso = new JCheckBox("PELIGROSO");
		cBPeligroso.setBorder(null);
		cBPeligroso.setContentAreaFilled(false);
		cBPeligroso.setBounds(358, 103, 126, 23);
		selectVehiculoEntidad.add(cBPeligroso);
		
		cBInmovilizado = new JCheckBox("INMOVILIZADO");
		cBInmovilizado.setBorder(null);
		cBInmovilizado.setContentAreaFilled(false);
		cBInmovilizado.setBounds(358, 133, 126, 23);
		selectVehiculoEntidad.add(cBInmovilizado);
		
		btnCopy_E = new JButton("");
		btnCopy_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCopy_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!tfNombreReclamante_E.getText().isEmpty())
					tfNombreTitular_E.setText(tfNombreReclamante_E.getText());
			}
		});
		btnCopy_E.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/copy.png")));
		btnCopy_E.setBounds(360, 12, 25, 20);
		selectVehiculoEntidad.add(btnCopy_E);
		
		JPanel selectOrdenEntidad = new TransparentPanel();
		selectOrdenEntidad.setBounds(526, 11, 490, 85);
		entidad.add(selectOrdenEntidad);
		selectOrdenEntidad.setLayout(null);
		selectOrdenEntidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JLabel label_2 = new JLabel("Fecha Apertura");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(10, 42, 130, 20);
		selectOrdenEntidad.add(label_2);
		
		dCFecha_Apertura_E = new JDateChooser();
		dCFecha_Apertura_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFecha_Apertura_E.setBounds(160, 42, 155, 20);
		selectOrdenEntidad.add(dCFecha_Apertura_E);
		
		tfNumeroOrden_E = new JTextField();
		tfNumeroOrden_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumeroOrden_E.setColumns(10);
		tfNumeroOrden_E.setBounds(160, 11, 200, 20);
		selectOrdenEntidad.add(tfNumeroOrden_E);
		
		JLabel label_10 = new JLabel("Numero Orden");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(10, 12, 130, 20);
		selectOrdenEntidad.add(label_10);
		
		btnBuscarOrden_E = new GlossyButton("Buscar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnBuscarOrden_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnBuscarOrden_E.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnBuscarOrden_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orden_desdeEntidad = true;
				mediador.buscarOrden();
			}
		});
		btnBuscarOrden_E.setBounds(366, 10, 110, 23);
		selectOrdenEntidad.add(btnBuscarOrden_E);
		
		btn_clear_FA_E = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_clear_FA_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FA_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFecha_Apertura_E.getDate()!=null)
					dCFecha_Apertura_E.setDate(null);
			}
		});
		btn_clear_FA_E.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FA_E.setBounds(325, 42, 25, 20);
		selectOrdenEntidad.add(btn_clear_FA_E);
		
		JPanel selectReclamoEntidad = new TransparentPanel();
		selectReclamoEntidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		selectReclamoEntidad.setBounds(526, 100, 490, 135);
		entidad.add(selectReclamoEntidad);
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
		tADescripcion_E.setBounds(160, 42, 256, 72);
		selectReclamoEntidad.add(tADescripcion_E);
		
		btn_clear_FR_E = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_clear_FR_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FR_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dCFecha_Reclamo_E.getDate()!=null)
					dCFecha_Reclamo_E.setDate(null);
			}
		});
		btn_clear_FR_E.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FR_E.setBounds(330, 11, 25, 20);
		selectReclamoEntidad.add(btn_clear_FR_E);
		
		btnCrear_E = new GlossyButton("Crear",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCrear_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCrear_E.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/check.png")));
		btnCrear_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearReclamoEntidad();
			}
		});
		
		JPanel selectPiezasEntidad = new TransparentPanel();
		selectPiezasEntidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		selectPiezasEntidad.setBounds(526, 240, 490, 215);
		entidad.add(selectPiezasEntidad);
		selectPiezasEntidad.setLayout(null);
		
		tfNumero_pedido_E = new JTextField();
		tfNumero_pedido_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_pedido_E.setColumns(10);
		tfNumero_pedido_E.setBounds(154, 10, 256, 20);
		selectPiezasEntidad.add(tfNumero_pedido_E);
		
		dCFSP_R = new JDateChooser();
		dCFSP_R.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFSP_R.setBounds(154, 35, 163, 20);
		selectPiezasEntidad.add(dCFSP_R);
		
		cB_Piezas_R = new JComboBox<String>();
		cB_Piezas_R.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cB_Piezas_R.setBounds(154, 60, 163, 20);
		selectPiezasEntidad.add(cB_Piezas_R);
		
		btnAgregar_Pieza_R = new GlossyButton("Agregar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnAgregar_Pieza_R.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregar_Pieza_R.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/add.png")));
		btnAgregar_Pieza_R.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarPieza_R();
			}
		});
		btnAgregar_Pieza_R.setBounds(327, 60, 110, 20);
		selectPiezasEntidad.add(btnAgregar_Pieza_R);
		
		btn_Quitar_Pieza_R = new GlossyButton("Quitar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_Quitar_Pieza_R.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_Quitar_Pieza_R.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/delete.png")));
		btn_Quitar_Pieza_R.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitar_R(); 
			}
		});
		btn_Quitar_Pieza_R.setBounds(327, 85, 110, 20);
		selectPiezasEntidad.add(btn_Quitar_Pieza_R);
		
		tfNumero_Pieza_R = new JTextField();
		tfNumero_Pieza_R.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_Pieza_R.addKeyListener(new KeyListener() {
		private int limite = 12;
		public void keyTyped(KeyEvent e) {
			if (tfNumero_Pieza_R.getText().length()>=limite){
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
		tfNumero_Pieza_R.setColumns(10);
		tfNumero_Pieza_R.setBounds(154, 85, 163, 20);
		selectPiezasEntidad.add(tfNumero_Pieza_R);
		
		cb_proveedor_R = new JComboBox<String>();
		cb_proveedor_R.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cmbProveedor_R = new DefaultComboBoxModel<String>(proveedores);
		cb_proveedor_R.setModel(cmbProveedor_R);
		cb_proveedor_R.setBounds(154, 110, 163, 20);
		selectPiezasEntidad.add(cb_proveedor_R);
		
		taDesc_Pieza_R = new JTextArea(4, 31);
		taDesc_Pieza_R.setToolTipText("Max 125 Caracteres");
		taDesc_Pieza_R.addKeyListener(new KeyListener() {
		private int limite = 125;
		public void keyTyped(KeyEvent e) {
			if (taDesc_Pieza_R.getText().length()>=limite){
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
		taDesc_Pieza_R.setLineWrap(true);
		taDesc_Pieza_R.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		taDesc_Pieza_R.setBounds(154, 135, 256, 72);
		selectPiezasEntidad.add(taDesc_Pieza_R);
		
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
				if (dCFSP_R.getDate()!=null)
					dCFSP_R.setDate(null);
			}
		});
		btn_clear_FSP_E.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FSP_E.setBounds(327, 35, 25, 20);
		selectPiezasEntidad.add(btn_clear_FSP_E);
		btnCrear_E.setBounds(290, 465, 130, 20);
		entidad.add(btnCrear_E);
		
		btnCancelar_E = new GlossyButton("Cancelar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCancelar_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCancelar_E.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar_E.setBounds(440, 465, 130, 20);
		entidad.add(btnCancelar_E);
		
		btnLimpiar_E = new GlossyButton("Limpiar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnLimpiar_E.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnLimpiar_E.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/clear.png")));
		btnLimpiar_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
			}
		});
		btnLimpiar_E.setBounds(590, 465, 130, 20);
		entidad.add(btnLimpiar_E);
		
		JPanel agente = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		agente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tabbedPane.addTab("AGENTE", new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/registrante_solo.png")), agente, null);
		agente.setLayout(null);
		
		JPanel selectAgente = new TransparentPanel();
		selectAgente.setBounds(10, 11, 505, 42);
		agente.add(selectAgente);
		selectAgente.setLayout(null);
		selectAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JLabel lblAgente = new JLabel("AGENTE");
		lblAgente.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAgente.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgente.setBounds(10, 11, 140, 19);
		selectAgente.add(lblAgente);
		
		cbAgente = new JComboBox<String>();
		cbAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbAgente.setModel(new DefaultComboBoxModel<String>(agentes));
		cbAgente.setBounds(160, 11, 200, 20);
		selectAgente.add(cbAgente);
		
		JPanel selectReclamanteAgente = new TransparentPanel();
		selectReclamanteAgente.setBounds(10, 55, 505, 181);
		agente.add(selectReclamanteAgente);
		selectReclamanteAgente.setLayout(null);
		selectReclamanteAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JLabel label_8 = new JLabel("Nombre Del Reclamante");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(10, 11, 139, 20);
		selectReclamanteAgente.add(label_8);
		
		JLabel label_9 = new JLabel("Email");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(10, 42, 139, 20);
		selectReclamanteAgente.add(label_9);
		
		tfEmail_A = new JTextField();
		tfEmail_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfEmail_A.setColumns(10);
		tfEmail_A.setBounds(160, 42, 200, 20);
		selectReclamanteAgente.add(tfEmail_A);
		
		tfNombreReclamante_A = new JTextField();
		tfNombreReclamante_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNombreReclamante_A.setColumns(10);
		tfNombreReclamante_A.setBounds(160, 11, 200, 20);
		selectReclamanteAgente.add(tfNombreReclamante_A);
		
		JLabel label_13 = new JLabel("DNI");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setBounds(10, 72, 139, 20);
		selectReclamanteAgente.add(label_13);
		
		JLabel label_14 = new JLabel("Telefonos");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setBounds(10, 103, 139, 20);
		selectReclamanteAgente.add(label_14);
		
		cbTelefonos_A = new JComboBox<String>();
		cbTelefonos_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbTelefonos_A.setEditable(true);
		cbTelefonos_A.setBounds(160, 103, 120, 20);
		selectReclamanteAgente.add(cbTelefonos_A);
		
		tfDni_A = new JTextField();
		tfDni_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfDni_A.setToolTipText("Ej 12345678");
		tfDni_A.addKeyListener(new KeyListener() {
		private int limite = 8;
		public void keyTyped(KeyEvent e) {
			if (tfDni_A.getText().length()>=limite){
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
		tfDni_A.setColumns(10);
		tfDni_A.setBounds(160, 72, 120, 20);
		selectReclamanteAgente.add(tfDni_A);
		
		btnAgregarTelefono_A = new GlossyButton("Agregar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnAgregarTelefono_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregarTelefono_A.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/add.png")));
		btnAgregarTelefono_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nuevo_telefono = (String)cbTelefonos_A.getSelectedItem();
				if (!telefonos_A.contains(nuevo_telefono) && nuevo_telefono != null && nuevo_telefono!=""){
					if (rbFijo_A.isSelected()){
						nuevo_telefono += " (Fijo)";
						telefonos_A.add(nuevo_telefono);
						DefaultComboBoxModel<String> comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos_A);
						cbTelefonos_A.setModel(comboBOX_Modelo);
						cbTelefonos_A.setSelectedIndex(-1);
						rbFijo_A.setSelected(false);
					}else{
						if (rbCelular_A.isSelected()){
							nuevo_telefono +=" (Celular)";
							telefonos_A.add(nuevo_telefono);
							DefaultComboBoxModel<String> comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos_A);
							cbTelefonos_A.setModel(comboBOX_Modelo);
							cbTelefonos_A.setSelectedIndex(-1);
							rbCelular_A.setSelected(false);
						}else{
							JOptionPane.showMessageDialog(null,"Seleccione el tipo de telefono.","Advertencia",JOptionPane.INFORMATION_MESSAGE);

						}
					}					
				}
			}
		});
		btnAgregarTelefono_A.setBounds(290, 105, 110, 20);
		selectReclamanteAgente.add(btnAgregarTelefono_A);
		
		btnQuitarTelefono_A = new GlossyButton("Quitar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnQuitarTelefono_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnQuitarTelefono_A.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/delete.png")));
		btnQuitarTelefono_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (telefonos_A.contains((String)cbTelefonos_A.getSelectedItem())){
					telefonos_A.remove((String)cbTelefonos_A.getSelectedItem());
					DefaultComboBoxModel<String> comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos_A);
					cbTelefonos_A.setModel(comboBOX_Modelo);
					cbTelefonos_A.setSelectedIndex(-1);
					rbFijo_A.setSelected(false);
					rbCelular_A.setSelected(false);					
				}
			}
		});
		btnQuitarTelefono_A.setBounds(290, 130, 110, 20);
		selectReclamanteAgente.add(btnQuitarTelefono_A);
		
		rbCelular_A = new JRadioButton("Celular");
		rbCelular_A.setBorder(null);
		rbCelular_A.setContentAreaFilled(false);
		rbCelular_A.setBounds(160, 129, 120, 23);
		selectReclamanteAgente.add(rbCelular_A);
		
		rbFijo_A = new JRadioButton("Fijo");
		rbFijo_A.setContentAreaFilled(false);
		rbFijo_A.setBorder(null);
		rbFijo_A.setBounds(160, 155, 120, 23);
		selectReclamanteAgente.add(rbFijo_A);
		
		btnBuscarReclamante_A = new GlossyButton("Buscar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnBuscarReclamante_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnBuscarReclamante_A.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnBuscarReclamante_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reclamante_desdeEntidad = false;
				mediador.buscarReclamante();
			}
		});
		btnBuscarReclamante_A.setBounds(385, 11, 110, 20);
		selectReclamanteAgente.add(btnBuscarReclamante_A);
		
		JPanel selectVehiculoAgente = new TransparentPanel();
		selectVehiculoAgente.setBounds(10, 240, 505, 215);
		agente.add(selectVehiculoAgente);
		selectVehiculoAgente.setLayout(null);
		selectVehiculoAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JLabel label_15 = new JLabel("Nombre del Titular");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setBounds(10, 12, 130, 20);
		selectVehiculoAgente.add(label_15);
		
		tfNombreTitular_A = new JTextField();
		tfNombreTitular_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNombreTitular_A.setColumns(10);
		tfNombreTitular_A.setBounds(160, 12, 200, 20);
		selectVehiculoAgente.add(tfNombreTitular_A);
		
		tfDominio_A = new JTextField();
		tfDominio_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfDominio_A.setToolTipText("Ej XYZ123");
		tfDominio_A.addKeyListener(new KeyListener() {
		private int limite = 6;
		public void keyTyped(KeyEvent e) {
			if (tfDominio_A.getText().length()>=limite){
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
		tfDominio_A.setColumns(10);
		tfDominio_A.setBounds(160, 42, 79, 20);
		selectVehiculoAgente.add(tfDominio_A);
		
		JLabel label_16 = new JLabel("Dominio");
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setBounds(10, 42, 130, 20);
		selectVehiculoAgente.add(label_16);
		
		JLabel label_17 = new JLabel("VIN");
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setBounds(10, 72, 130, 20);
		selectVehiculoAgente.add(label_17);
		
		tfVin_A = new JTextField();
		tfVin_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfVin_A.setToolTipText("Ej 12345678901234567");
		tfVin_A.addKeyListener(new KeyListener() {
		private int limite = 17;
		public void keyTyped(KeyEvent e) {
			if (tfVin_A.getText().length()>=limite){
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
		tfVin_A.setColumns(10);
		tfVin_A.setBounds(160, 73, 155, 20);
		selectVehiculoAgente.add(tfVin_A);
		
		JLabel label_18 = new JLabel("Marca");
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setBounds(10, 103, 130, 20);
		selectVehiculoAgente.add(label_18);
		
		cbMarca_A = new JComboBox<String>();
		cbMarca_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbMarca_A.setModel(new DefaultComboBoxModel<String>(marcas));
		cbMarca_A.setBounds(160, 103, 155, 20);
		selectVehiculoAgente.add(cbMarca_A);
		
		JLabel label_19 = new JLabel("Modelo");
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setBounds(10, 133, 130, 20);
		selectVehiculoAgente.add(label_19);
		
		cbModelo_A = new JComboBox<String>();
		cbModelo_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbModelo_A.setModel(new DefaultComboBoxModel<String>(modelos));
		cbModelo_A.setBounds(160, 133, 155, 20);
		selectVehiculoAgente.add(cbModelo_A);
		
		btnBuscarVehiculo_A = new GlossyButton("Buscar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnBuscarVehiculo_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnBuscarVehiculo_A.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnBuscarVehiculo_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vehiculo_desdeEntidad = false;
				mediador.buscarVehiculo();
			}
		});
		btnBuscarVehiculo_A.setBounds(385, 12, 110, 20);
		selectVehiculoAgente.add(btnBuscarVehiculo_A);
		
		btnCopy_A = new JButton("");
		btnCopy_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCopy_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tfNombreReclamante_A.getText().isEmpty())
					tfNombreTitular_A.setText(tfNombreReclamante_A.getText());
			}
		});
		btnCopy_A.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/copy.png")));
		btnCopy_A.setBounds(360, 12, 25, 20);
		selectVehiculoAgente.add(btnCopy_A);
		
		JPanel selectOrdenAgente = new TransparentPanel();
		selectOrdenAgente.setLayout(null);
		selectOrdenAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		selectOrdenAgente.setBounds(526, 11, 490, 85);
		agente.add(selectOrdenAgente);
		
		JLabel label_5 = new JLabel("Fecha Apertura");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(10, 42, 130, 20);
		selectOrdenAgente.add(label_5);
		
		dCFecha_Apertura_A = new JDateChooser();
		dCFecha_Apertura_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFecha_Apertura_A.setBounds(160, 42, 155, 20);
		selectOrdenAgente.add(dCFecha_Apertura_A);
		
		tfNumeroOrden_A = new JTextField();
		tfNumeroOrden_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumeroOrden_A.setColumns(10);
		tfNumeroOrden_A.setBounds(160, 11, 200, 20);
		selectOrdenAgente.add(tfNumeroOrden_A);
		
		JLabel label_6 = new JLabel("Numero Orden");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(10, 12, 130, 20);
		selectOrdenAgente.add(label_6);
		
		JButton btnBuscarOrden_A = new GlossyButton("Buscar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnBuscarOrden_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnBuscarOrden_A.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnBuscarOrden_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orden_desdeEntidad = false;
				mediador.buscarOrden();
			}
		});
		btnBuscarOrden_A.setBounds(366, 10, 110, 23);
		selectOrdenAgente.add(btnBuscarOrden_A);
		
		btn_clearFA_A = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_clearFA_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clearFA_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFecha_Apertura_A.getDate()!=null)
					dCFecha_Apertura_A.setDate(null);
			}
		});
		btn_clearFA_A.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clearFA_A.setBounds(325, 42, 25, 20);
		selectOrdenAgente.add(btn_clearFA_A);
		
		JPanel selectReclamoAgente = new TransparentPanel();
		selectReclamoAgente.setBounds(526, 100, 490, 135);
		agente.add(selectReclamoAgente);
		selectReclamoAgente.setLayout(null);
		selectReclamoAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		dCFecha_Reclamo_A = new JDateChooser();
		dCFecha_Reclamo_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFecha_Reclamo_A.setDate(new Date());
		dCFecha_Reclamo_A.setBounds(160, 11, 160, 20);
		selectReclamoAgente.add(dCFecha_Reclamo_A);
		
		JLabel label_22 = new JLabel("Fecha Reclamo");
		label_22.setHorizontalAlignment(SwingConstants.CENTER);
		label_22.setBounds(10, 11, 130, 20);
		selectReclamoAgente.add(label_22);
		
		JLabel label_23 = new JLabel("Descripcion");
		label_23.setHorizontalAlignment(SwingConstants.CENTER);
		label_23.setBounds(10, 44, 130, 20);
		selectReclamoAgente.add(label_23);
		
		tADescripcion_A = new JTextArea(4, 31);
		tADescripcion_A.setToolTipText("Max 125 Caracteres");
		tADescripcion_A.addKeyListener(new KeyListener() {
		private int limite = 125;
		public void keyTyped(KeyEvent e) {
			if (tADescripcion_A.getText().length()>=limite){
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
		tADescripcion_A.setLineWrap(true);
		tADescripcion_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tADescripcion_A.setBounds(160, 42, 256, 72);
		selectReclamoAgente.add(tADescripcion_A);
		
		btn_clear_FR_A = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_clear_FR_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FR_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dCFecha_Reclamo_A.getDate()!=null)
					dCFecha_Reclamo_A.setDate(null);
			}
		});
		btn_clear_FR_A.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FR_A.setBounds(330, 11, 25, 20);
		selectReclamoAgente.add(btn_clear_FR_A);
		
		JPanel selectPiezasAgente = new TransparentPanel();
		selectPiezasAgente.setLayout(null);
		selectPiezasAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		selectPiezasAgente.setBounds(526, 240, 490, 215);
		agente.add(selectPiezasAgente);
		
		tf_Num_Pedido_A = new JTextField();
		tf_Num_Pedido_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tf_Num_Pedido_A.setColumns(10);
		tf_Num_Pedido_A.setBounds(154, 10, 256, 20);
		selectPiezasAgente.add(tf_Num_Pedido_A);
		
		dcFSP_A = new JDateChooser();
		dcFSP_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFSP_A.setBounds(154, 35, 163, 20);
		selectPiezasAgente.add(dcFSP_A);
		
		cbPiezas_A = new JComboBox<String>();
		cbPiezas_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbPiezas_A.setBounds(154, 60, 163, 20);
		selectPiezasAgente.add(cbPiezas_A);
		
		btnAgregar_PiezaA = new GlossyButton("Agregar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnAgregar_PiezaA.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregar_PiezaA.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/add.png")));
		btnAgregar_PiezaA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarPieza_A();
			}
		});
		btnAgregar_PiezaA.setBounds(327, 60, 110, 20);
		selectPiezasAgente.add(btnAgregar_PiezaA);
		
		btnQuitar_Piezas_A = new GlossyButton("Quitar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnQuitar_Piezas_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnQuitar_Piezas_A.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/delete.png")));
		btnQuitar_Piezas_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quitar_A();
			}
		});
		btnQuitar_Piezas_A.setBounds(327, 85, 110, 20);
		selectPiezasAgente.add(btnQuitar_Piezas_A);
		
		tfNumero_Pieza_A = new JTextField();
		tfNumero_Pieza_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_Pieza_A.addKeyListener(new KeyListener() {
		private int limite = 12;
		public void keyTyped(KeyEvent e) {
			if (tfNumero_Pieza_A.getText().length()>=limite){
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
		tfNumero_Pieza_A.setColumns(10);
		tfNumero_Pieza_A.setBounds(154, 85, 163, 20);
		selectPiezasAgente.add(tfNumero_Pieza_A);
		
		cbProveedores_A = new JComboBox<String>();
		cbProveedores_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbProveedores_A.setModel(cmbProveedor_A);
		cbProveedores_A.setBounds(154, 110, 163, 20);
		selectPiezasAgente.add(cbProveedores_A);
		
		tADesc_Pieza_A = new JTextArea(4, 31);
		tADesc_Pieza_A.setToolTipText("Max 125 Caracteres");
		tADesc_Pieza_A.addKeyListener(new KeyListener() {
		private int limite = 125;
		public void keyTyped(KeyEvent e) {
			if (tADesc_Pieza_A.getText().length()>=limite){
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
		tADesc_Pieza_A.setLineWrap(true);
		tADesc_Pieza_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tADesc_Pieza_A.setBounds(154, 135, 256, 72);
		selectPiezasAgente.add(tADesc_Pieza_A);
		
		JLabel label_25 = new JLabel("Numero Pedido");
		label_25.setHorizontalAlignment(SwingConstants.CENTER);
		label_25.setBounds(10, 10, 130, 20);
		selectPiezasAgente.add(label_25);
		
		JLabel label_26 = new JLabel("Fecha Solicitud Pedido");
		label_26.setHorizontalAlignment(SwingConstants.CENTER);
		label_26.setBounds(10, 35, 130, 20);
		selectPiezasAgente.add(label_26);
		
		JLabel label_27 = new JLabel("Piezas");
		label_27.setHorizontalAlignment(SwingConstants.CENTER);
		label_27.setBounds(10, 60, 130, 20);
		selectPiezasAgente.add(label_27);
		
		JLabel label_28 = new JLabel("Numero Pieza");
		label_28.setHorizontalAlignment(SwingConstants.CENTER);
		label_28.setBounds(10, 85, 130, 20);
		selectPiezasAgente.add(label_28);
		
		JLabel label_29 = new JLabel("Proveedor");
		label_29.setHorizontalAlignment(SwingConstants.CENTER);
		label_29.setBounds(10, 110, 130, 20);
		selectPiezasAgente.add(label_29);
		
		JLabel label_30 = new JLabel("Descripcion");
		label_30.setHorizontalAlignment(SwingConstants.CENTER);
		label_30.setBounds(10, 135, 130, 20);
		selectPiezasAgente.add(label_30);
		
		btn_clear_FSP_A = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_clear_FSP_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FSP_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFSP_A.getDate()!=null)
					dcFSP_A.setDate(null);
			}
		});
		btn_clear_FSP_A.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FSP_A.setBounds(327, 35, 25, 20);
		selectPiezasAgente.add(btn_clear_FSP_A);
		
		btnCrear_A = new GlossyButton("Crear",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCrear_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCrear_A.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/check.png")));
		btnCrear_A.setBounds(290, 465, 130, 20);
		agente.add(btnCrear_A);
		
		btnCancelar_A = new GlossyButton("Cancelar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCancelar_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCancelar_A.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar_A.setBounds(440, 465, 130, 20);
		agente.add(btnCancelar_A);
		
		btnLimpiar_A = new GlossyButton("Limpiar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnLimpiar_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnLimpiar_A.setIcon(new ImageIcon(GUIAltaReclamoRapido.class.getResource("/cliente/Resources/Icons/clear.png")));
		btnLimpiar_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar_A.setBounds(590, 465, 130, 20);
		agente.add(btnLimpiar_A);
		btnCancelar_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCrear_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearReclamoAgente();
			}
		});
	}
	
	protected void agregarPieza_A() {
		if (tfNumero_Pieza_A.getText().isEmpty()|| cbProveedores_A.getSelectedItem()==null ){
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			
			String numero_pieza = tfNumero_Pieza_A.getText();
			
			if (!numeros_piezas_A.contains(numero_pieza)){
				ProveedorDTO proveedor = mediador.obtenerProveedor(cbProveedores_A.getSelectedItem().toString());
				PiezaDTO pieza = new PiezaDTO(numero_pieza, tADesc_Pieza_A.getText(), proveedor);
				piezas_A.add(pieza);
				numeros_piezas_A.add(numero_pieza);
				cmbPieza_A = new DefaultComboBoxModel<String>(numeros_piezas_A);
				cbPiezas_A.setModel(cmbPieza_A);
				cbPiezas_A.setSelectedIndex(-1);
				cbProveedores_A.setSelectedIndex(0);
				tfNumero_Pieza_A.setText("");
				tADesc_Pieza_A.setText("");
			}else{
				JOptionPane.showMessageDialog(this,"Ya existe la pieza.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
			}
				
		}	
	}
	protected void quitar_A() {
		if (numeros_piezas_A.contains((String)cbPiezas_A.getSelectedItem())){
			numeros_piezas_A.remove((String)cbPiezas_A.getSelectedItem());
			for (int i=0; i<piezas_A.size();i++){
				if (piezas_A.elementAt(i).getNumero_pieza().equals((String)cbPiezas_A.getSelectedItem())){
					piezas_A.remove(i);
				}
			}
			cmbPieza_A = new DefaultComboBoxModel<String>(numeros_piezas_A);
			cbPiezas_A.setModel(cmbPieza_A);
			cbPiezas_A.setSelectedIndex(-1);					
		}
	}
	protected void agregarPieza_R() {
		if (tfNumero_Pieza_R.getText().isEmpty()|| cb_proveedor_R.getSelectedItem()==null ){
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			
			String numero_pieza = tfNumero_Pieza_R.getText();
			
			if (!numeros_piezas_R.contains(numero_pieza)){
				ProveedorDTO proveedor = mediador.obtenerProveedor(cb_proveedor_R.getSelectedItem().toString());
				PiezaDTO pieza = new PiezaDTO(numero_pieza, taDesc_Pieza_R.getText(), proveedor);
				piezas_R.add(pieza);
				numeros_piezas_R.add(numero_pieza);
				cmbPieza_R = new DefaultComboBoxModel<String>(numeros_piezas_R);
				cB_Piezas_R.setModel(cmbPieza_R);
				cB_Piezas_R.setSelectedIndex(-1);
				cb_proveedor_R.setSelectedIndex(0);
				tfNumero_Pieza_R.setText("");
				taDesc_Pieza_R.setText("");
			}else{
				JOptionPane.showMessageDialog(this,"Ya existe la pieza.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
			}
				
		}	
	}
	protected void quitar_R() {
		if (numeros_piezas_R.contains((String)cB_Piezas_R.getSelectedItem())){
			numeros_piezas_R.remove((String)cB_Piezas_R.getSelectedItem());
			for (int i=0; i<piezas_R.size();i++){
				if (piezas_R.elementAt(i).getNumero_pieza().equals((String)cB_Piezas_R.getSelectedItem())){
					piezas_R.remove(i);
				}
			}
			cmbPieza_R = new DefaultComboBoxModel<String>(numeros_piezas_R);
			cB_Piezas_R.setModel(cmbPieza_R);
			cB_Piezas_R.setSelectedIndex(-1);					
		}
	}
	public boolean chequearCamposEntidad(){
		boolean res = false;
		res = (cbEntidad.getSelectedItem()==null || tfNombreReclamante_E.getText().isEmpty() || tfDni_E.getText().isEmpty() 
				|| cbTelefonos_E.getModel().getSize()<1 || tfNombreTitular_E.getText().isEmpty() || tfDominio_E.getText().isEmpty()
				|| tfVin_E.getText().isEmpty() || cbMarca_E.getModel().getSize()<1 || cbModelo_E.getModel().getSize()<1 
				|| tfNumeroOrden_E.getText().isEmpty() || dCFecha_Apertura_E.getDate()==null || dCFecha_Reclamo_E.getDate()==null
				|| (piezas_R.size()>0 && dCFSP_R.getDate()==null)
				);
		return res;
	}

	@SuppressWarnings("unused")
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
		    if(dCFSP_R.getDate()!=null){
				fecha = format2.format(dCFSP_R.getDate());
				fechaSP = new java.sql.Date(dCFSP_R.getDate().getTime());
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
	public boolean chequearCamposAgente(){
		boolean res = false;
		res = (cbAgente.getSelectedItem()==null || tfNombreReclamante_A.getText().isEmpty() || tfDni_A.getText().isEmpty()
				|| cbTelefonos_A.getModel().getSize()<1 || tfNombreTitular_A.getText().isEmpty() || tfDominio_A.getText().isEmpty()
				|| tfVin_A.getText().isEmpty() || cbMarca_A.getModel().getSize()<1 || cbModelo_A.getModel().getSize()<1 
				|| tfNumeroOrden_A.getText().isEmpty() || dCFecha_Apertura_A.getDate()==null || dCFecha_Reclamo_A.getDate()==null
				|| (piezas_A.size()>0 && dcFSP_A.getDate()==null)
				);
		return res;
	}
	@SuppressWarnings("unused")
	protected void crearReclamoAgente() {
		if (chequearCamposAgente()){
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String fecha = format2.format(dCFecha_Apertura_A.getDate());
		    java.sql.Date fechaApertura = new java.sql.Date(dCFecha_Apertura_A.getDate().getTime());
		    
			fecha = format2.format(dCFecha_Reclamo_A.getDate());
		    java.sql.Date fechaReclamo = new java.sql.Date(dCFecha_Reclamo_A.getDate().getTime());
		   
		    java.sql.Date fechaSP = null;
		    if(dcFSP_A.getDate()!=null){
				fecha = format2.format(dcFSP_A.getDate());
				fechaSP = new java.sql.Date(dcFSP_A.getDate().getTime());
		    }
		    
		    Vector<String> telefonos_reclamante = new Vector<String>();
		    for (int i = 0;i< cbTelefonos_A.getModel().getSize(); i++){
		    	telefonos_reclamante.add(cbTelefonos_A.getModel().getElementAt(i).toString());
		    }
			if (mediador.nuevoReclamo(cbAgente.getSelectedItem().toString(),tfNombreReclamante_A.getText(),tfDni_A.getText(), tfEmail_A.getText(),
				telefonos_reclamante, tfNombreTitular_A.getText(),tfDominio_A.getText(),tfVin_A.getText(),
				cbMarca_A.getSelectedItem().toString(), cbModelo_A.getSelectedItem().toString(),tfNumeroOrden_A.getText(),
				fechaApertura,fechaReclamo,tADescripcion_A.getText(),piezas_A,tf_Num_Pedido_A.getText(),fechaSP,null,null)){
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

	public void setOrdenAgente(OrdenDTO orden) {
		tfNumeroOrden_A.setText(orden.getNumero_orden());
		dCFecha_Apertura_A.setDate(orden.getFecha_apertura());			
	}

	public void setReclamanteEntidad(ReclamanteDTO reclamante) {
		tfNombreReclamante_E.setText(reclamante.getNombre_apellido());
		tfEmail_E.setText(reclamante.getEmail());
		tfDni_E.setText(reclamante.getDni());
		telefonos_E = mediador.obtenerTelefonos(reclamante);
		DefaultComboBoxModel<String> comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos_E);
		cbTelefonos_E.setModel(comboBOX_Modelo);
	}

	public void setReclamanteAgente(ReclamanteDTO reclamante) {
		tfNombreReclamante_A.setText(reclamante.getNombre_apellido());
		tfEmail_A.setText(reclamante.getEmail());
		tfDni_A.setText(reclamante.getDni());
		telefonos_E = mediador.obtenerTelefonos(reclamante);
		DefaultComboBoxModel<String> comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos_E);
		cbTelefonos_A.setModel(comboBOX_Modelo);
	}

	public void setVehiculoEntidad(VehiculoDTO vehiculo) {
		tfNombreTitular_E.setText(vehiculo.getNombre_titular());
		tfDominio_E.setText(vehiculo.getDominio());
		tfVin_E.setText(vehiculo.getVin());
		cbMarca_E.setSelectedItem(vehiculo.getMarca().getNombre_marca());
		cbModelo_E.setSelectedItem(vehiculo.getModelo().getNombre_modelo());
	}

	public void setVehiculoAgente(VehiculoDTO vehiculo) {
		tfNombreTitular_A.setText(vehiculo.getNombre_titular());
		tfDominio_A.setText(vehiculo.getDominio());
		tfVin_A.setText(vehiculo.getVin());
		cbMarca_A.setSelectedItem(vehiculo.getMarca().getNombre_marca());
		cbModelo_A.setSelectedItem(vehiculo.getModelo().getNombre_modelo());
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
		//AGENTE
		cbAgente.setSelectedIndex(0);
		
		tfNombreReclamante_A.setText("");
		tfEmail_A.setText("");
		tfDni_A.setText("");
		telefonos_A = new Vector<String>();
		DefaultComboBoxModel<String> comboBOX_Modelo_A = new DefaultComboBoxModel<String>(telefonos_A);
		cbTelefonos_A.setModel(comboBOX_Modelo_A);
		rbCelular_A.setSelected(false);
		rbFijo_A.setSelected(false);
				
		tfNombreTitular_A.setText("");
		tfDominio_A.setText("");
		tfVin_A.setText("");
		cbMarca_A.setSelectedIndex(0);
		cbModelo_A.setSelectedIndex(0);
				
		tfNumeroOrden_A.setText("");
		dCFecha_Apertura_A.setDate(null);
				
		dCFecha_Reclamo_A.setDate(new Date());
		tADescripcion_A.setText("");
		
		piezas_A = new Vector<PiezaDTO>();
		piezas_R = new Vector<PiezaDTO>();
		
		tfNumero_pedido_E.setText("");
		tf_Num_Pedido_A.setText("");

		dCFSP_R.setDate(null);
		dcFSP_A.setDate(null);
		
		tfNumero_Pieza_R.setText("");
		tfNumero_Pieza_A.setText("");
		
		numeros_piezas_A = new Vector<String>();
		DefaultComboBoxModel<String> comboBOX_Pieza_A = new DefaultComboBoxModel<String>(numeros_piezas_A);
		cB_Piezas_R.setModel(comboBOX_Pieza_A);
		numeros_piezas_R = new Vector<String>();
		DefaultComboBoxModel<String> comboBOX_Pieza_R = new DefaultComboBoxModel<String>(numeros_piezas_R);
		cbPiezas_A.setModel(comboBOX_Pieza_R);
		
		taDesc_Pieza_R.setText("");
		tADesc_Pieza_A.setText("");

		cbProveedores_A.setSelectedIndex(0);
		cb_proveedor_R.setSelectedIndex(0);
		
	}
}
