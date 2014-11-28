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
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import common.Cuadruple;
import common.Tupla;
import common.DTOs.MuletoDTO;
import common.DTOs.PiezaDTO;
import common.DTOs.ProveedorDTO;
import common.DTOs.ReclamanteDTO;
import common.DTOs.VehiculoDTO;

public class GUIAltaReclamoRapidoAgente extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadoReclamoRapido mediador;
	private JTextField tfNumeroOrden_A;
	private JTextField tfEmail_A;
	private JTextField tfNombreReclamante_A;
	private JTextField tfDni_A;
	private JTextField tfNombreTitular_A;
	private JTextField tfDominio_A;
	private JTextField tfVin_A;
	private JButton btnCrear_A;
	private JButton btnCancelar_A;
	private Vector<String> numeros_piezas_A;
	private boolean reclamante_desdeAgente;
	private boolean vehiculo_desdeAgente;
	private Vector<String> telefonos_A;

	private Vector<String> marcas;
	private Vector<String> modelos;
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
	private JDateChooser dCFecha_Reclamo_A;
	private JTextArea tADescripcion_A;
	private JButton btnLimpiar_A;
	private JTextField tf_Num_Pedido_A;
	private JTextField tfNumero_Pieza_A;
	private JDateChooser dcFSF_A;
	private JComboBox<String> cbPiezas_A;
	private JButton btnAgregar_PiezaA;
	private JButton btnQuitar_Piezas_A;
	private JComboBox<String> cbProveedores_A;
	private JTextArea tADesc_Pieza_A;
	private Vector<String> proveedores;
	private DefaultComboBoxModel<String> cmbProveedor_A;
	private Vector<Cuadruple<PiezaDTO,MuletoDTO,String,Tupla<Boolean,Boolean>>> piezas_A;
	private DefaultComboBoxModel<String> cmbPieza_A;
	private JButton btnCopy_A;
	private JButton btn_clear_FSP_A;
	private JButton btn_clear_FR_A;
	private JPanel contentPane;
	private JTextField tFVinMuleto;
	private JTextField tFPnc;
	private JTextArea tADescMuleto;

	public GUIAltaReclamoRapidoAgente(MediadoReclamoRapido mediadoReclamoRapido) {
		this.mediador = mediadoReclamoRapido;
		cargarDatos();
		initialize();
	}
	
	private void cargarDatos() {
		agentes = mediador.obtenerNombresAgentes();
		marcas = mediador.obtenerNombresMarcas();
		modelos = mediador.obtenerNombresModelos();
		proveedores = mediador.obtenerProveedores();
		piezas_A = new Vector<Cuadruple<PiezaDTO,MuletoDTO,String,Tupla<Boolean,Boolean>>>();
		numeros_piezas_A = new Vector<String>();
		telefonos_A = new Vector<String>();
	}
	
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0,1382,768);
		//setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setTitle("ALTA RECLAMO RAPIDO AGENTE");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIAltaReclamoRapidoEntidad.class.getResource("/cliente/Resources/Icons/reclamo_rapido.png")));
		
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBounds(new Rectangle(0, 0, 1366, 768));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		cmbProveedor_A = new DefaultComboBoxModel<String>(proveedores);
		
		
		JPanel selectAgente = new TransparentPanel();
		selectAgente.setBounds(48, 30, 610, 59);
		getContentPane().add(selectAgente);
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
		cbAgente.setBounds(160, 11, 400, 20);
		selectAgente.add(cbAgente);
		
		JPanel selectReclamanteAgente = new TransparentPanel();
		selectReclamanteAgente.setBounds(48, 94, 610, 206);
		getContentPane().add(selectReclamanteAgente);
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
		cbTelefonos_A.setBounds(160, 103, 200, 20);
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
		tfDni_A.setBounds(160, 72, 200, 20);
		selectReclamanteAgente.add(tfDni_A);
		
		btnAgregarTelefono_A = new GlossyButton("Agregar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnAgregarTelefono_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregarTelefono_A.setIcon(new ImageIcon(GUIAltaReclamoRapidoAgente.class.getResource("/cliente/Resources/Icons/add.png")));
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
		btnAgregarTelefono_A.setBounds(370, 103, 110, 20);
		selectReclamanteAgente.add(btnAgregarTelefono_A);
		
		btnQuitarTelefono_A = new GlossyButton("Quitar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnQuitarTelefono_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnQuitarTelefono_A.setIcon(new ImageIcon(GUIAltaReclamoRapidoAgente.class.getResource("/cliente/Resources/Icons/delete.png")));
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
		btnQuitarTelefono_A.setBounds(491, 103, 110, 20);
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
		btnBuscarReclamante_A.setIcon(new ImageIcon(GUIAltaReclamoRapidoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnBuscarReclamante_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reclamante_desdeAgente = true;
				mediador.buscarReclamante();
			}
		});
		btnBuscarReclamante_A.setBounds(395, 10, 165, 20);
		selectReclamanteAgente.add(btnBuscarReclamante_A);
		
		JPanel selectVehiculoAgente = new TransparentPanel();
		selectVehiculoAgente.setBounds(48, 311, 610, 334);
		getContentPane().add(selectVehiculoAgente);
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
		tfVin_A.setBounds(160, 73, 200, 20);
		selectVehiculoAgente.add(tfVin_A);
		
		JLabel label_18 = new JLabel("Marca");
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setBounds(10, 103, 130, 20);
		selectVehiculoAgente.add(label_18);
		
		cbMarca_A = new JComboBox<String>();
		cbMarca_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbMarca_A.setModel(new DefaultComboBoxModel<String>(marcas));
		cbMarca_A.setBounds(160, 103, 200, 20);
		selectVehiculoAgente.add(cbMarca_A);
		
		JLabel label_19 = new JLabel("Modelo");
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setBounds(10, 133, 130, 20);
		selectVehiculoAgente.add(label_19);
		
		cbModelo_A = new JComboBox<String>();
		cbModelo_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbModelo_A.setModel(new DefaultComboBoxModel<String>(modelos));
		cbModelo_A.setBounds(160, 133, 200, 20);
		selectVehiculoAgente.add(cbModelo_A);
		
		btnBuscarVehiculo_A = new GlossyButton("Buscar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnBuscarVehiculo_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnBuscarVehiculo_A.setIcon(new ImageIcon(GUIAltaReclamoRapidoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnBuscarVehiculo_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vehiculo_desdeAgente = true;
				mediador.buscarVehiculo();
			}
		});
		btnBuscarVehiculo_A.setBounds(395, 11, 165, 20);
		selectVehiculoAgente.add(btnBuscarVehiculo_A);
		
		btnCopy_A = new JButton("");
		btnCopy_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCopy_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tfNombreReclamante_A.getText().isEmpty())
					tfNombreTitular_A.setText(tfNombreReclamante_A.getText());
			}
		});
		btnCopy_A.setIcon(new ImageIcon(GUIAltaReclamoRapidoAgente.class.getResource("/cliente/Resources/Icons/copy.png")));
		btnCopy_A.setBounds(360, 12, 25, 20);
		selectVehiculoAgente.add(btnCopy_A);
		
		JPanel selectOrdenAgente = new TransparentPanel();
		selectOrdenAgente.setLayout(null);
		selectOrdenAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		selectOrdenAgente.setBounds(706, 185, 610, 59);
		getContentPane().add(selectOrdenAgente);
		
		tfNumeroOrden_A = new JTextField();
		tfNumeroOrden_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumeroOrden_A.setColumns(10);
		tfNumeroOrden_A.setBounds(160, 10, 250, 20);
		selectOrdenAgente.add(tfNumeroOrden_A);
		
		JLabel label_6 = new JLabel("Numero Orden");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(10, 10, 130, 20);
		selectOrdenAgente.add(label_6);
		
		JPanel selectReclamoAgente = new TransparentPanel();
		selectReclamoAgente.setBounds(706, 30, 610, 150);
		getContentPane().add(selectReclamoAgente);
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
		tADescripcion_A.setBounds(160, 42, 400, 74);
		selectReclamoAgente.add(tADescripcion_A);
		
		btn_clear_FR_A = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_clear_FR_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FR_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dCFecha_Reclamo_A.getDate()!=null)
					dCFecha_Reclamo_A.setDate(null);
			}
		});
		btn_clear_FR_A.setIcon(new ImageIcon(GUIAltaReclamoRapidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FR_A.setBounds(330, 11, 25, 20);
		selectReclamoAgente.add(btn_clear_FR_A);
		
		JPanel selectPiezasAgente = new TransparentPanel();
		selectPiezasAgente.setLayout(null);
		selectPiezasAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		selectPiezasAgente.setBounds(706, 255, 610, 390);
		getContentPane().add(selectPiezasAgente);
		
		tf_Num_Pedido_A = new JTextField();
		tf_Num_Pedido_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tf_Num_Pedido_A.setColumns(10);
		tf_Num_Pedido_A.setBounds(154, 10, 256, 20);
		selectPiezasAgente.add(tf_Num_Pedido_A);
		
		dcFSF_A = new JDateChooser(new Date());
		dcFSF_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFSF_A.setBounds(154, 35, 163, 20);
		selectPiezasAgente.add(dcFSF_A);
		
		cbPiezas_A = new JComboBox<String>();
		cbPiezas_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbPiezas_A.setBounds(154, 60, 163, 20);
		selectPiezasAgente.add(cbPiezas_A);
		
		btnAgregar_PiezaA = new GlossyButton("Agregar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnAgregar_PiezaA.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregar_PiezaA.setIcon(new ImageIcon(GUIAltaReclamoRapidoAgente.class.getResource("/cliente/Resources/Icons/add.png")));
		btnAgregar_PiezaA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarPieza_A();
			}
		});
		btnAgregar_PiezaA.setBounds(327, 60, 110, 20);
		selectPiezasAgente.add(btnAgregar_PiezaA);
		
		btnQuitar_Piezas_A = new GlossyButton("Quitar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnQuitar_Piezas_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnQuitar_Piezas_A.setIcon(new ImageIcon(GUIAltaReclamoRapidoAgente.class.getResource("/cliente/Resources/Icons/delete.png")));
		btnQuitar_Piezas_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quitar_A();
			}
		});
		btnQuitar_Piezas_A.setBounds(447, 59, 110, 20);
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
		tADesc_Pieza_A.setBounds(154, 135, 400, 72);
		selectPiezasAgente.add(tADesc_Pieza_A);
		
		JLabel label_25 = new JLabel("Numero Pedido");
		label_25.setHorizontalAlignment(SwingConstants.CENTER);
		label_25.setBounds(10, 10, 130, 20);
		selectPiezasAgente.add(label_25);
		
		JLabel lblFechaSolicitudFabrica = new JLabel("Fecha Solicitud Fabrica");
		lblFechaSolicitudFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSolicitudFabrica.setBounds(0, 35, 153, 20);
		selectPiezasAgente.add(lblFechaSolicitudFabrica);
		
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
				if (dcFSF_A.getDate()!=null)
					dcFSF_A.setDate(null);
			}
		});
		btn_clear_FSP_A.setIcon(new ImageIcon(GUIAltaReclamoRapidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FSP_A.setBounds(327, 35, 25, 20);
		selectPiezasAgente.add(btn_clear_FSP_A);
		
		tADescMuleto = new JTextArea(4, 31);
		tADescMuleto.setLineWrap(true);
		tADescMuleto.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tADescMuleto.setBounds(154, 300, 260, 70);
		selectPiezasAgente.add(tADescMuleto);
		
		JLabel label = new JLabel("Descripcion Muleto");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(null);
		label.setBounds(10, 300, 140, 20);
		selectPiezasAgente.add(label);
		
		JLabel label_1 = new JLabel("VIN Muleto");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBorder(null);
		label_1.setBounds(10, 275, 140, 20);
		selectPiezasAgente.add(label_1);
		
		tFVinMuleto = new JTextField();
		tFVinMuleto.setToolTipText("Ej 12345678901234567");
		tFVinMuleto.setColumns(10);
		tFVinMuleto.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFVinMuleto.setBounds(154, 275, 160, 20);
		selectPiezasAgente.add(tFVinMuleto);
		
		JLabel label_2 = new JLabel("MULETO");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBorder(null);
		label_2.setBounds(154, 250, 130, 20);
		selectPiezasAgente.add(label_2);
		
		tFPnc = new JTextField();
		tFPnc.setColumns(10);
		tFPnc.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFPnc.setBounds(154, 220, 160, 20);
		selectPiezasAgente.add(tFPnc);
		
		JLabel label_3 = new JLabel("PNC");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBorder(null);
		label_3.setBounds(10, 220, 140, 20);
		selectPiezasAgente.add(label_3);
		
		btnCrear_A = new GlossyButton("Crear",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCrear_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCrear_A.setIcon(new ImageIcon(GUIAltaReclamoRapidoAgente.class.getResource("/cliente/Resources/Icons/check.png")));
		btnCrear_A.setBounds(190, 673, 200, 30);
		getContentPane().add(btnCrear_A);
		
		btnCancelar_A = new GlossyButton("Cancelar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCancelar_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCancelar_A.setIcon(new ImageIcon(GUIAltaReclamoRapidoAgente.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar_A.setBounds(581, 673, 200, 30);
		getContentPane().add(btnCancelar_A);
		
		btnLimpiar_A = new GlossyButton("Limpiar",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnLimpiar_A.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnLimpiar_A.setIcon(new ImageIcon(GUIAltaReclamoRapidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btnLimpiar_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar_A.setBounds(972, 673, 200, 30);
		getContentPane().add(btnLimpiar_A);
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
				MuletoDTO muleto = null;
				if(!tFVinMuleto.getText().isEmpty() && !tADescMuleto.getText().isEmpty()){
					muleto = new MuletoDTO();
					muleto.setVin(tFVinMuleto.getText());
					muleto.setDescripcion(tADescMuleto.getText());
				}
				Tupla<Boolean,Boolean> stock_propio = new Tupla<Boolean, Boolean>(null,null);
				Cuadruple<PiezaDTO,MuletoDTO,String,Tupla<Boolean,Boolean>> pieza_nueva = new Cuadruple<PiezaDTO, MuletoDTO, String,Tupla<Boolean,Boolean>>(pieza, muleto, tFPnc.getText(),stock_propio);
				piezas_A.add(pieza_nueva);
				
				numeros_piezas_A.add(numero_pieza);
				cmbPieza_A = new DefaultComboBoxModel<String>(numeros_piezas_A);
				cbPiezas_A.setModel(cmbPieza_A);
				cbPiezas_A.setSelectedIndex(-1);
				cbProveedores_A.setSelectedIndex(0);
				tfNumero_Pieza_A.setText("");
				tADesc_Pieza_A.setText("");
				tFPnc.setText("");
				tFVinMuleto.setText("");
				tADescMuleto.setText("");
				
			}else{
				JOptionPane.showMessageDialog(this,"Ya existe la pieza.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
			}
				
		}	
	}
	protected void quitar_A() {
		if (numeros_piezas_A.contains((String)cbPiezas_A.getSelectedItem())){
			numeros_piezas_A.remove((String)cbPiezas_A.getSelectedItem());
			for (int i=0; i<piezas_A.size();i++){
				if (piezas_A.elementAt(i).first().getNumero_pieza().equals((String)cbPiezas_A.getSelectedItem())){
					piezas_A.remove(i);
				}
			}
			cmbPieza_A = new DefaultComboBoxModel<String>(numeros_piezas_A);
			cbPiezas_A.setModel(cmbPieza_A);
			cbPiezas_A.setSelectedIndex(-1);					
		}
	}
	
	public boolean chequearCamposAgente(){
		boolean res = false;
		res = (cbAgente.getSelectedItem()==null || tfNombreReclamante_A.getText().isEmpty() || tfDni_A.getText().isEmpty()
				|| cbTelefonos_A.getModel().getSize()<1 || tfNombreTitular_A.getText().isEmpty() || tfDominio_A.getText().isEmpty()
				|| tfVin_A.getText().isEmpty() || cbMarca_A.getModel().getSize()<1 || cbModelo_A.getModel().getSize()<1 
				|| tfNumeroOrden_A.getText().isEmpty() || dCFecha_Reclamo_A.getDate()==null
				|| (piezas_A.size()>0 && dcFSF_A.getDate()==null)
				);
		return res;
	}

	protected void crearReclamoAgente() {
		if (chequearCamposAgente()){
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
		    java.sql.Date fechaApertura = new java.sql.Date(dcFSF_A.getDate().getTime());
		    java.sql.Date fechaReclamo = new java.sql.Date(dCFecha_Reclamo_A.getDate().getTime());
		    java.sql.Date fechaSP = null;
		    if(dcFSF_A.getDate()!=null){
				fechaSP = new java.sql.Date(dcFSF_A.getDate().getTime());
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
	public boolean isReclamante_desdeAgente() {
		return reclamante_desdeAgente;
	}

	public void setReclamante_desdeAgente(boolean reclamante_desdeAgente) {
		this.reclamante_desdeAgente = reclamante_desdeAgente;
	}

	public boolean isVehiculo_desdeAgente() {
		return vehiculo_desdeAgente;
	}

	public void setVehiculo_desdeAgente(boolean vehiculo_desdeAgente) {
		this.vehiculo_desdeAgente = vehiculo_desdeAgente;
	}

	public void setReclamanteAgente(ReclamanteDTO reclamante) {
		tfNombreReclamante_A.setText(reclamante.getNombre_apellido());
		tfEmail_A.setText(reclamante.getEmail());
		tfDni_A.setText(reclamante.getDni());
		telefonos_A = mediador.obtenerTelefonos(reclamante);
		DefaultComboBoxModel<String> comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos_A);
		cbTelefonos_A.setModel(comboBOX_Modelo);
	}

	public void setVehiculoAgente(VehiculoDTO vehiculo) {
		tfNombreTitular_A.setText(vehiculo.getNombre_titular());
		tfDominio_A.setText(vehiculo.getDominio());
		tfVin_A.setText(vehiculo.getVin());
		cbMarca_A.setSelectedItem(vehiculo.getMarca().getNombre_marca());
		cbModelo_A.setSelectedItem(vehiculo.getModelo().getNombre_modelo());
	}

	public Vector<String> getTelefonos() {
		return telefonos_A;
	}

	public void setTelefonos(Vector<String> telefonos) {
		this.telefonos_A = telefonos;
	}
	
	private void limpiar() {
		//ENTIDAD
		reclamante_desdeAgente=false;
		vehiculo_desdeAgente=false;
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
		dCFecha_Reclamo_A.setDate(new Date());
		tADescripcion_A.setText("");
		piezas_A = new Vector<Cuadruple<PiezaDTO,MuletoDTO,String,Tupla<Boolean,Boolean>>>();
		tf_Num_Pedido_A.setText("");
		dcFSF_A.setDate(null);
		tfNumero_Pieza_A.setText("");
		numeros_piezas_A = new Vector<String>();
		DefaultComboBoxModel<String> comboBOX_Pieza_A = new DefaultComboBoxModel<String>(numeros_piezas_A);
		cbPiezas_A.setModel(comboBOX_Pieza_A);
		tADesc_Pieza_A.setText("");
		cbProveedores_A.setSelectedIndex(0);
		tFPnc.setText("");
		tFVinMuleto.setText("");
		tADescMuleto.setText("");
		
	}
}
