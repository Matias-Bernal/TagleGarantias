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
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
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
import common.DTOs.BdgDTO;
import common.DTOs.Devolucion_PiezaDTO;
import common.DTOs.MuletoDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.PiezaDTO;
import common.DTOs.ProveedorDTO;
import common.DTOs.ReclamoDTO;


public class GUIModificarPedidoAgente extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorPedido mediador;
	private JPanel contentPane;
	private JDateChooser dcFSF;
	private JDateChooser dcFRF;
	private JDateChooser dcFRA;
	private JDateChooser dcFEA;
	private JDateChooser dcFBDG;
	private JDateChooser dcFDF;
	
	private JTextField tfNumero_Pedido;
	private JTextField tfPNC;
	private JTextField tfNumeroOrden;
	private JTextField tfNum_Pieza;
	private JTextField tfVIN_Muleto;
	private JTextField tfNumero_BDG;
	private JTextField tfNumero_Retiro;
	private JTextField tfTransporte;
	private JTextField tfNumero_Remito;
	private JTextField tfEstado_Pedido;	

	private JTextArea taDesc_Muleto;
	private JTextArea taDesc_Pedido;
	
	private JCheckBox cbxPieza_Usada;

	private JComboBox cbPiezas;
	private DefaultComboBoxModel<String> cmbPiezas;
	private Vector<String> numeros_piezas;

	private JComboBox cbProveedor;
	private DefaultComboBoxModel<String> cmbProveedor;
	private Vector<String> proveedores;
	
	private Vector<Pedido_PiezaDTO> pedidos_piezas;
	private Vector<PiezaDTO> piezasDTO;
	
	private PedidoDTO pedido;
	private ReclamoDTO reclamo;
	private Devolucion_PiezaDTO devolucion;
	private BdgDTO bdg;
	private MuletoDTO muleto;
	private JTextField tfFSP;
	private JButton btn_clear_FEA;
	private JButton btn_clear_FSF;
	private JButton btn_clear_FSD;
	private JButton btn_clear_FAS;
	private JDateChooser dcFAS;
	private JDateChooser dcFSD;
	private JButton btnModificar_Pieza;
	private JButton btnModificar;

	public GUIModificarPedidoAgente(final MediadorPedido mediador, PedidoDTO pedido) {
		this.setMediador(mediador);
		this.setPedido(pedido);
		cargarDatos();
		initialize();
		completarCampos();
	}
	
	private void cargarDatos() {
		proveedores = mediador.obtenerProveedores();
		pedidos_piezas = mediador.obtenerPedidos_PiezasAgente(pedido.getId());
		
		numeros_piezas = new Vector<String>();
		piezasDTO = new Vector<PiezaDTO>();
		for (int i=0; i< pedidos_piezas.size(); i++){
			if(pedidos_piezas.elementAt(i).getPieza()!=null)
				numeros_piezas.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());
				piezasDTO.add(pedidos_piezas.elementAt(i).getPieza());
		}
	}

	private void completarCampos() {
		if (pedidos_piezas.size()>0)
			tfNumero_Pedido.setText(pedidos_piezas.elementAt(0).getNumero_pedido());
		if(pedido.getFecha_solicitud_pedido()!=null)
			tfFSP.setText(pedido.getFecha_solicitud_pedido().toString());
		reclamo = pedido.getReclamo();
		if(reclamo.getOrden()!=null)
			tfNumeroOrden.setText(reclamo.getOrden().getNumero_orden());
		//pedido_pieza
		if(cmbPiezas.getSize()>0)
			cbPiezas.setSelectedIndex(0);
		actualizarPiezas();
	}
	
	private void initialize() {
		setTitle("MODIFICAR PEDIDO AGENTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 865, 700);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/edit_pedido_agente.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFechaSolicitud = new JLabel("Fecha Solicitud Pedido");
		lblFechaSolicitud.setBorder(null);
		lblFechaSolicitud.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaSolicitud.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSolicitud.setBounds(0, 40, 150, 20);
		contentPane.add(lblFechaSolicitud);
		
		JLabel lblNumeroOT = new JLabel("Numero de Orden");
		lblNumeroOT.setBorder(null);
		lblNumeroOT.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNumeroOT.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroOT.setBounds(0, 70, 150, 20);
		contentPane.add(lblNumeroOT);
		
		tfNumeroOrden = new JTextField();
		tfNumeroOrden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumeroOrden.setEditable(false);
		tfNumeroOrden.setBounds(150, 70, 160, 20);
		contentPane.add(tfNumeroOrden);
		tfNumeroOrden.setColumns(10);
		
		JButton btnCancelar = new GlossyButton("CANCELAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCancelar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCancelar.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/delete.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(206, 630, 120, 20);
		contentPane.add(btnCancelar);
		
		btnModificar = new GlossyButton("MODIFICAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar.setEnabled(false);
		btnModificar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(532, 630, 120, 20);
		contentPane.add(btnModificar);
		
		JLabel lblNumeroPedido = new JLabel("Numero Pedido");
		lblNumeroPedido.setBorder(null);
		lblNumeroPedido.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNumeroPedido.setBounds(0, 10, 150, 20);
		contentPane.add(lblNumeroPedido);
		lblNumeroPedido.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfNumero_Pedido = new JTextField();
		tfNumero_Pedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_Pedido.setBounds(150, 10, 160, 20);
		contentPane.add(tfNumero_Pedido);
		tfNumero_Pedido.setColumns(10);
		
		JPanel panel_piezas = new TransparentPanel();
		panel_piezas.setBorder(null);
		panel_piezas.setBounds(10, 105, 425, 515);
		contentPane.add(panel_piezas);
		panel_piezas.setLayout(null);
		
		JLabel lblPiezas = new JLabel("Piezas");
		lblPiezas.setBorder(null);
		lblPiezas.setBounds(0, 40, 140, 20);
		panel_piezas.add(lblPiezas);
		lblPiezas.setHorizontalAlignment(SwingConstants.CENTER);
		
		cbPiezas = new JComboBox();
		cbPiezas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbPiezas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				actualizarPiezas();
			}
		});
		cmbPiezas = new DefaultComboBoxModel<String>(numeros_piezas);
		cbPiezas.setModel(cmbPiezas);
		cbPiezas.setBounds(140, 40, 160, 20);
		panel_piezas.add(cbPiezas);
		
		JLabel lblPnc = new JLabel("PNC");
		lblPnc.setBorder(null);
		lblPnc.setBounds(0, 300, 140, 20);
		panel_piezas.add(lblPnc);
		lblPnc.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfPNC = new JTextField();
		tfPNC.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfPNC.setBounds(140, 300, 160, 20);
		panel_piezas.add(tfPNC);
		tfPNC.setColumns(10);
		
		dcFSF = new JDateChooser();
		dcFSF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFSF.setBounds(140, 210, 160, 20);
		panel_piezas.add(dcFSF);
		
		JLabel lblFechaSolicitudFabrica = new JLabel("Fecha Solicitud Fabrica");
		lblFechaSolicitudFabrica.setBorder(null);
		lblFechaSolicitudFabrica.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaSolicitudFabrica.setBounds(0, 210, 140, 20);
		panel_piezas.add(lblFechaSolicitudFabrica);
		lblFechaSolicitudFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblFechaRecepcionFabrica = new JLabel("Fecha Recepcion Fabrica");
		lblFechaRecepcionFabrica.setBorder(null);
		lblFechaRecepcionFabrica.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaRecepcionFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaRecepcionFabrica.setBounds(0, 240, 140, 20);
		panel_piezas.add(lblFechaRecepcionFabrica);
		
		dcFRF = new JDateChooser();
		dcFRF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFRF.setBounds(140, 240, 160, 20);
		panel_piezas.add(dcFRF);
		
		tfNum_Pieza = new JTextField();
		tfNum_Pieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNum_Pieza.setColumns(10);
		tfNum_Pieza.setBounds(140, 70, 160, 20);
		panel_piezas.add(tfNum_Pieza);
		
		cbProveedor = new JComboBox();
		cbProveedor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cmbProveedor = new DefaultComboBoxModel<String>(proveedores);
		cbProveedor.setModel(cmbProveedor);
		cbProveedor.setBounds(140, 100, 160, 20);
		panel_piezas.add(cbProveedor);
		
		taDesc_Pedido = new JTextArea(4, 31);
		taDesc_Pedido.setLineWrap(true);
		taDesc_Pedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		taDesc_Pedido.setBounds(140, 130, 260, 70);
		panel_piezas.add(taDesc_Pedido);
		
		JLabel label = new JLabel("Descripcion");
		label.setBorder(null);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 130, 140, 20);
		panel_piezas.add(label);
		
		JLabel label_1 = new JLabel("Proveedor");
		label_1.setBorder(null);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(0, 100, 140, 20);
		panel_piezas.add(label_1);
		
		JLabel label_2 = new JLabel("Numero Pieza");
		label_2.setBorder(null);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(0, 70, 140, 20);
		panel_piezas.add(label_2);
		
		JLabel lblEstadoPedido = new JLabel("Estado Pedido");
		lblEstadoPedido.setBorder(null);
		lblEstadoPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoPedido.setBounds(0, 270, 140, 20);
		panel_piezas.add(lblEstadoPedido);
		
		btnModificar_Pieza = new GlossyButton("GUARDAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar_Pieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar_Pieza.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/save.png")));
		btnModificar_Pieza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar_pieza();
			}
		});
		btnModificar_Pieza.setBounds(305, 40, 110, 20);
		panel_piezas.add(btnModificar_Pieza);
		
		JLabel lblFechaEnvioAgente = new JLabel("Fecha Envio Agente");
		lblFechaEnvioAgente.setBorder(null);
		lblFechaEnvioAgente.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaEnvioAgente.setBounds(0, 390, 140, 20);
		panel_piezas.add(lblFechaEnvioAgente);
		lblFechaEnvioAgente.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblAgente = new JLabel("AGENTE");
		lblAgente.setBorder(null);
		lblAgente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAgente.setBounds(142, 360, 130, 20);
		panel_piezas.add(lblAgente);
		lblAgente.setHorizontalAlignment(SwingConstants.CENTER);
		
		dcFEA = new JDateChooser();
		dcFEA.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFEA.setBounds(140, 390, 160, 20);
		panel_piezas.add(dcFEA);
		
		JLabel lblFechaRecepcionAgente = new JLabel("Fecha Recepcion Agente");
		lblFechaRecepcionAgente.setBorder(null);
		lblFechaRecepcionAgente.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaRecepcionAgente.setBounds(0, 420, 140, 20);
		panel_piezas.add(lblFechaRecepcionAgente);
		lblFechaRecepcionAgente.setHorizontalAlignment(SwingConstants.CENTER);
		
		dcFRA = new JDateChooser();
		dcFRA.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFRA.setBounds(140, 420, 160, 20);
		panel_piezas.add(dcFRA);
		
		cbxPieza_Usada = new JCheckBox("Pieza Usada");
		cbxPieza_Usada.setContentAreaFilled(false);
		cbxPieza_Usada.setBorder(null);
		cbxPieza_Usada.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cbxPieza_Usada.setBounds(140, 330, 130, 20);
		panel_piezas.add(cbxPieza_Usada);
		cbxPieza_Usada.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel label_8 = new JLabel("PIEZAS");
		label_8.setBorder(null);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(142, 10, 130, 20);
		panel_piezas.add(label_8);
		
		tfEstado_Pedido = new JTextField();
		tfEstado_Pedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfEstado_Pedido.setEditable(false);
		tfEstado_Pedido.setColumns(10);
		tfEstado_Pedido.setBounds(140, 270, 260, 20);
		panel_piezas.add(tfEstado_Pedido);
		
		btn_clear_FSF = new JButton("");
		btn_clear_FSF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FSF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFSF.getDate()!=null)
					dcFSF.setDate(null);
			}
		});
		btn_clear_FSF.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FSF.setBounds(310, 210, 25, 20);
		panel_piezas.add(btn_clear_FSF);
		
		JButton btn_clear_FRF = new JButton("");
		btn_clear_FRF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FRF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFRF.getDate()!=null)
					dcFRF.setDate(null);
			}
		});
		btn_clear_FRF.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FRF.setBounds(310, 241, 25, 20);
		panel_piezas.add(btn_clear_FRF);
		
		btn_clear_FEA = new JButton("");
		btn_clear_FEA.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FEA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFEA.getDate()!=null)
					dcFEA.setDate(null);
			}
		});
		btn_clear_FEA.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FEA.setBounds(305, 390, 25, 20);
		panel_piezas.add(btn_clear_FEA);
		
		JButton btn_clear_FRA = new JButton("");
		btn_clear_FRA.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FRA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFRA.getDate()!=null)
					dcFRA.setDate(null);
			}
		});
		btn_clear_FRA.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FRA.setBounds(305, 421, 25, 20);
		panel_piezas.add(btn_clear_FRA);
		
		JPanel panel_claves_foraneas = new TransparentPanel();
		panel_claves_foraneas.setBorder(null);
		panel_claves_foraneas.setBounds(439, 105, 410, 514);
		contentPane.add(panel_claves_foraneas);
		panel_claves_foraneas.setLayout(null);
		
		taDesc_Muleto = new JTextArea(4, 31);
		taDesc_Muleto.setLineWrap(true);
		taDesc_Muleto.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		taDesc_Muleto.setBounds(140, 280, 260, 70);
		panel_claves_foraneas.add(taDesc_Muleto);
		
		JLabel lblDescripcionMuleto = new JLabel("Descripcion Muleto");
		lblDescripcionMuleto.setBorder(null);
		lblDescripcionMuleto.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcionMuleto.setBounds(0, 280, 140, 20);
		panel_claves_foraneas.add(lblDescripcionMuleto);
		
		JLabel lblVin = new JLabel("VIN Muleto");
		lblVin.setBorder(null);
		lblVin.setHorizontalAlignment(SwingConstants.CENTER);
		lblVin.setBounds(0, 250, 140, 20);
		panel_claves_foraneas.add(lblVin);
		
		tfVIN_Muleto = new JTextField();
		tfVIN_Muleto.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfVIN_Muleto.setToolTipText("Ej 12345678901234567");
		tfVIN_Muleto.addKeyListener(new KeyListener() {
		private int limite = 17;
		public void keyTyped(KeyEvent e) {
			if (tfVIN_Muleto.getText().length()>=limite){
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
		tfVIN_Muleto.setColumns(10);
		tfVIN_Muleto.setBounds(140, 250, 160, 20);
		panel_claves_foraneas.add(tfVIN_Muleto);
		
		JLabel lblMuleto = new JLabel("MULETO");
		lblMuleto.setBorder(null);
		lblMuleto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMuleto.setHorizontalAlignment(SwingConstants.CENTER);
		lblMuleto.setBounds(142, 220, 130, 20);
		panel_claves_foraneas.add(lblMuleto);
		
		JLabel lblBdg = new JLabel("BDG");
		lblBdg.setBorder(null);
		lblBdg.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBdg.setHorizontalAlignment(SwingConstants.CENTER);
		lblBdg.setBounds(142, 360, 130, 20);
		panel_claves_foraneas.add(lblBdg);
		
		JLabel lblFechaBdg = new JLabel("Fecha Carga BDG");
		lblFechaBdg.setBorder(null);
		lblFechaBdg.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaBdg.setBounds(0, 390, 140, 20);
		panel_claves_foraneas.add(lblFechaBdg);
		lblFechaBdg.setHorizontalAlignment(SwingConstants.CENTER);
		
		dcFBDG = new JDateChooser();
		dcFBDG.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFBDG.setBounds(140, 390, 160, 20);
		panel_claves_foraneas.add(dcFBDG);
		
		JLabel lblNumeroDeBdg = new JLabel("Numero de BDG");
		lblNumeroDeBdg.setBorder(null);
		lblNumeroDeBdg.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroDeBdg.setBounds(0, 420, 140, 20);
		panel_claves_foraneas.add(lblNumeroDeBdg);
		
		tfNumero_BDG = new JTextField();
		tfNumero_BDG.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_BDG.setColumns(10);
		tfNumero_BDG.setBounds(140, 420, 160, 20);
		panel_claves_foraneas.add(tfNumero_BDG);
		
		tfNumero_Retiro = new JTextField();
		tfNumero_Retiro.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_Retiro.setColumns(10);
		tfNumero_Retiro.setBounds(140, 190, 160, 20);
		panel_claves_foraneas.add(tfNumero_Retiro);
		
		JLabel label_3 = new JLabel("Numero Retiro");
		label_3.setBorder(null);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(0, 190, 140, 20);
		panel_claves_foraneas.add(label_3);
		
		JLabel label_4 = new JLabel("Transporte");
		label_4.setBorder(null);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(0, 160, 140, 20);
		panel_claves_foraneas.add(label_4);
		
		tfTransporte = new JTextField();
		tfTransporte.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfTransporte.setColumns(10);
		tfTransporte.setBounds(140, 160, 160, 20);
		panel_claves_foraneas.add(tfTransporte);
		
		tfNumero_Remito = new JTextField();
		tfNumero_Remito.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_Remito.setColumns(10);
		tfNumero_Remito.setBounds(140, 130, 160, 20);
		panel_claves_foraneas.add(tfNumero_Remito);
		
		JLabel label_5 = new JLabel("Numero Remito");
		label_5.setBorder(null);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(0, 130, 140, 20);
		panel_claves_foraneas.add(label_5);
		
		JLabel label_6 = new JLabel("Fecha Devolucion");
		label_6.setBorder(null);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(0, 100, 140, 20);
		panel_claves_foraneas.add(label_6);
		
		dcFDF = new JDateChooser();
		dcFDF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFDF.setBounds(140, 100, 160, 20);
		panel_claves_foraneas.add(dcFDF);
		
		JLabel label_7 = new JLabel("DEVOLUCION");
		label_7.setBorder(null);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(142, 10, 130, 20);
		panel_claves_foraneas.add(label_7);
		
		JButton btn_clear_FD = new JButton("");
		btn_clear_FD.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFDF.getDate()!=null)
					dcFDF.setDate(null);
			}
		});
		btn_clear_FD.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FD.setBounds(310, 100, 25, 20);
		panel_claves_foraneas.add(btn_clear_FD);
		
		JButton btn_clear_bdg = new JButton("");
		btn_clear_bdg.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_bdg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFBDG.getDate()!=null)
					dcFBDG.setDate(null);
			}
		});
		btn_clear_bdg.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_bdg.setBounds(310, 390, 25, 20);
		panel_claves_foraneas.add(btn_clear_bdg);
		
		JLabel lblFechaAprobacionDevolucion = new JLabel("Fecha Aprobacion Solicitud");
		lblFechaAprobacionDevolucion.setBorder(null);
		lblFechaAprobacionDevolucion.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblFechaAprobacionDevolucion.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaAprobacionDevolucion.setBounds(0, 70, 140, 20);
		panel_claves_foraneas.add(lblFechaAprobacionDevolucion);
		
		dcFAS = new JDateChooser();
		dcFAS.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFAS.setBounds(140, 70, 160, 20);
		panel_claves_foraneas.add(dcFAS);
		
		btn_clear_FAS = new JButton("");
		btn_clear_FAS.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFAS.getDate()!=null)
					dcFAS.setDate(null);
			}
		});
		btn_clear_FAS.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FAS.setBounds(310, 70, 25, 20);
		panel_claves_foraneas.add(btn_clear_FAS);
		
		JLabel lblFechaSolicitudDevolucion = new JLabel("Fecha Solicitud Devolucion");
		lblFechaSolicitudDevolucion.setBorder(null);
		lblFechaSolicitudDevolucion.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblFechaSolicitudDevolucion.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSolicitudDevolucion.setBounds(0, 40, 140, 20);
		panel_claves_foraneas.add(lblFechaSolicitudDevolucion);
		
		dcFSD = new JDateChooser();
		dcFSD.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFSD.setBounds(140, 40, 160, 20);
		panel_claves_foraneas.add(dcFSD);
		
		btn_clear_FSD = new JButton("");
		btn_clear_FSD.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FSD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFSD.getDate()!=null)
					dcFSD.setDate(null);
			}
		});
		btn_clear_FSD.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FSD.setBounds(310, 40, 25, 20);
		panel_claves_foraneas.add(btn_clear_FSD);
		
		tfFSP = new JTextField();
		tfFSP.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfFSP.setEditable(false);
		tfFSP.setColumns(10);
		tfFSP.setBounds(150, 40, 160, 20);
		contentPane.add(tfFSP);
		
		contentPane.setVisible(true);
			
	}
		
	protected void modificar_pieza() {
		if (tfNum_Pieza.getText().isEmpty() || cbProveedor.getSelectedItem()==null){
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{	
				//pieza
				ProveedorDTO proveedor = mediador.obtenerProveedor(cbProveedor.getSelectedItem().toString());
				SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy"); 

				for(int i=0;i< pedidos_piezas.size();i++){
					if(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza().equals(cbPiezas.getSelectedItem().toString())){

						pedidos_piezas.elementAt(i).getPieza().setNumero_pieza(tfNum_Pieza.getText());
						pedidos_piezas.elementAt(i).getPieza().setDescripcion(taDesc_Pedido.getText());
						pedidos_piezas.elementAt(i).getPieza().setProveedor(proveedor);

						if (!numeros_piezas.contains(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza())){
							numeros_piezas.remove(cbPiezas.getSelectedItem().toString());
							numeros_piezas.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());
						}
						cmbPiezas = new DefaultComboBoxModel<String>(numeros_piezas);
						cbPiezas.setModel(cmbPiezas);
						
						//fecha solicitud fabrica
						if (dcFSF.getDate()!=null){
							String fecha = format2.format(dcFSF.getDate());
							java.sql.Date fsf = new java.sql.Date(dcFSF.getDate().getTime());
							pedidos_piezas.elementAt(i).setFecha_solicitud_fabrica(fsf);
						}else{
							pedidos_piezas.elementAt(i).setFecha_solicitud_fabrica(null);
						}
						//fecha recepcion fabrica
						if(dcFRF.getDate()!=null){
							String fecha = format2.format(dcFRF.getDate());
							java.sql.Date frf = new java.sql.Date(dcFRF.getDate().getTime());
							pedidos_piezas.elementAt(i).setFecha_recepcion_fabrica(frf);
						}else{
							pedidos_piezas.elementAt(i).setFecha_recepcion_fabrica(null);
						}
						//pnc 
						pedidos_piezas.elementAt(i).setPnc(tfPNC.getText());
						//agente
						pedidos_piezas.elementAt(i).setPieza_usada(cbxPieza_Usada.isSelected());
							if(dcFEA.getDate()!=null){
								String fecha = format2.format(dcFEA.getDate());
								java.sql.Date fea = new java.sql.Date(dcFEA.getDate().getTime());
								pedidos_piezas.elementAt(i).setFecha_envio_agente(fea);
							}else{
								pedidos_piezas.elementAt(i).setFecha_envio_agente(null);
							}
							if(dcFRA.getDate()!=null){
								String fecha = format2.format(dcFRA.getDate());
								java.sql.Date fra = new java.sql.Date(dcFRA.getDate().getTime());
								pedidos_piezas.elementAt(i).setFecha_recepcion_agente(fra);
							}else{
								pedidos_piezas.elementAt(i).setFecha_recepcion_agente(null);
							}
						//devolucion
						if(dcFSD.getDate()!=null){
							String fecha = format2.format(dcFSD.getDate());
							java.sql.Date fsd = new java.sql.Date(dcFSD.getDate().getTime());
							pedidos_piezas.elementAt(i).setFecha_solicitud_devolucion(fsd);
						}else{
							pedidos_piezas.elementAt(i).setFecha_solicitud_devolucion(null);
						}
						if(dcFAS.getDate()!=null){
							String fecha = format2.format(dcFAS.getDate());
							java.sql.Date fas = new java.sql.Date(dcFAS.getDate().getTime());
							pedidos_piezas.elementAt(i).setFecha_aprobacion_solicitud_devolucion(fas);
						}else{
							pedidos_piezas.elementAt(i).setFecha_aprobacion_solicitud_devolucion(null);
						}
						if(dcFDF.getDate()!=null || !tfNumero_Remito.getText().isEmpty() || !tfTransporte.getText().isEmpty() || !tfNumero_Retiro.getText().isEmpty()){
							if(pedidos_piezas.elementAt(i).getDevolucion_pieza()==null){
								Devolucion_PiezaDTO devolucion = new Devolucion_PiezaDTO();
								String fecha = format2.format(dcFDF.getDate());
								java.sql.Date fdf = new java.sql.Date(dcFDF.getDate().getTime());
								devolucion.setFecha_devolucion(fdf);
								devolucion.setNumero_remito(tfNumero_Remito.getText());
								devolucion.setTransporte(tfTransporte.getText());
								devolucion.setNumero_retiro(tfNumero_Retiro.getText());
								pedidos_piezas.elementAt(i).setDevolucion_pieza(devolucion);
							}else{
								String fecha = format2.format(dcFDF.getDate());
								java.sql.Date fdf = new java.sql.Date(dcFDF.getDate().getTime());
								pedidos_piezas.elementAt(i).getDevolucion_pieza().setFecha_devolucion(fdf);
								pedidos_piezas.elementAt(i).getDevolucion_pieza().setNumero_remito(tfNumero_Remito.getText());
								pedidos_piezas.elementAt(i).getDevolucion_pieza().setTransporte(tfTransporte.getText());
								pedidos_piezas.elementAt(i).getDevolucion_pieza().setNumero_retiro(tfNumero_Retiro.getText());
		
							}
						}
						//muleto
						if(!tfVIN_Muleto.getText().isEmpty() || !taDesc_Muleto.getText().isEmpty()){
							if(pedidos_piezas.elementAt(i).getMuleto()==null){
								MuletoDTO muleto = new MuletoDTO();
								muleto.setVin(tfVIN_Muleto.getText());
								muleto.setDescripcion(taDesc_Muleto.getText());
								muleto.setPedido(pedido);
								muleto.setPieza(pedidos_piezas.elementAt(i).getPieza());
								pedidos_piezas.elementAt(i).setMuleto(muleto);
							}else{
								pedidos_piezas.elementAt(i).getMuleto().setVin(tfVIN_Muleto.getText());
								pedidos_piezas.elementAt(i).getMuleto().setDescripcion(taDesc_Muleto.getText());
								pedidos_piezas.elementAt(i).getMuleto().setPedido(pedido);
								pedidos_piezas.elementAt(i).getMuleto().setPieza(pedidos_piezas.elementAt(i).getPieza());
							}
						}
		
						//bdg
						if(!tfNumero_BDG.getText().isEmpty() && dcFBDG.getDate()!=null){
							if(pedidos_piezas.elementAt(i).getBdg()==null){
								BdgDTO bdg = new BdgDTO();
								String fecha = format2.format(dcFBDG.getDate());
								java.sql.Date fbdg = new java.sql.Date(dcFBDG.getDate().getTime());
								bdg.setFecha_bdg(fbdg);
								bdg.setNumero_bdg(tfNumero_BDG.getText());
								bdg.setPedido(pedido);
								bdg.setPieza(pedidos_piezas.elementAt(i).getPieza());
								pedidos_piezas.elementAt(i).setBdg(bdg);
							}else{
								String fecha = format2.format(dcFBDG.getDate());
								java.sql.Date fbdg = new java.sql.Date(dcFBDG.getDate().getTime());
								pedidos_piezas.elementAt(i).getBdg().setFecha_bdg(fbdg);
								pedidos_piezas.elementAt(i).getBdg().setNumero_bdg(tfNumero_BDG.getText());
								pedidos_piezas.elementAt(i).getBdg().setPedido(pedido);
								pedidos_piezas.elementAt(i).getBdg().setPieza(pedidos_piezas.elementAt(i).getPieza());
							}
						}
						//pedidos_piezas.add(pedido_pieza);
						JOptionPane.showMessageDialog(this,"Pieza Modificada.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
						actualizarPiezas();
						
						break;
				}
			}
			btnModificar.setEnabled(true);
		}
	}

	@SuppressWarnings("unused")
	private void quitarPieza(PiezaDTO piezaDTO) {
		for (int i=0; i<pedidos_piezas.size();i++){
			if (pedidos_piezas.elementAt(i).getPieza().getNumero_pieza().equals(piezaDTO.getNumero_pieza())){
				pedidos_piezas.remove(i);
			}
		}
	}

	protected void modificar() {
			if(!tfNumero_Pedido.getText().isEmpty()){
				for (int i= 0;i<pedidos_piezas.size();i++){
					pedidos_piezas.elementAt(i).setNumero_pedido(tfNumero_Pedido.getText());
				}
			}
			if (mediador.modificarPedidoAgente(pedido,pedidos_piezas)){
				JOptionPane.showMessageDialog(null,"Pedido Modificada.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
				mediador.actualizarDatosGestion();
				dispose();
			}else{
				JOptionPane.showMessageDialog(null,"Error al modificar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
			}
	}

	protected void actualizarPiezas() {
		if (cbPiezas.getSelectedIndex()!=-1){
			if(!cbPiezas.getSelectedItem().toString().isEmpty() && cbPiezas.getSelectedItem()!=null ){

				//piezas
				tfNum_Pieza.setText("");
				cbProveedor.setSelectedIndex(0);
				taDesc_Pedido.setText("");
				dcFSF.setDate(null);
				dcFRF.setDate(null);
				tfEstado_Pedido.setText("");
				tfPNC.setText("");
				//agente
				dcFEA.setDate(null);
				dcFRA.setDate(null);
				cbxPieza_Usada.setSelected(false);
				//devolucion
				dcFSD.setDate(null);
				dcFAS.setDate(null);
				dcFDF.setDate(null);
				tfNumero_Remito.setText("");
				tfTransporte.setText("");
				tfNumero_Retiro.setText("");
				//muleto
				tfVIN_Muleto.setText("");
				taDesc_Muleto.setText("");
				//bdg
				dcFBDG.setDate(null);
				tfNumero_BDG.setText("");
				
												
				for(int i=0;i< pedidos_piezas.size();i++){
					if(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza().equals(cbPiezas.getSelectedItem().toString())){

						tfNum_Pieza.setText(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());
						cbProveedor.setSelectedItem(pedidos_piezas.elementAt(i).getPieza().getProveedor().getNombre());
						taDesc_Pedido.setText(pedidos_piezas.elementAt(i).getPieza().getDescripcion());
						if(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null)
							dcFSF.setDate(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica());
						if(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null)
							dcFRF.setDate(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica());
						tfEstado_Pedido.setText(pedidos_piezas.elementAt(i).getEstado_pedido());
						tfPNC.setText(pedidos_piezas.elementAt(i).getPnc());
						//agente
						if(pedidos_piezas.elementAt(i).getFecha_envio_agente()!=null)
							dcFEA.setDate(pedidos_piezas.elementAt(i).getFecha_envio_agente());
						if(pedidos_piezas.elementAt(i).getFecha_recepcion_agente()!=null){
							dcFRA.setDate(pedidos_piezas.elementAt(i).getFecha_recepcion_agente());
						}
						if(pedidos_piezas.elementAt(i).getPieza_usada()!=null){
							cbxPieza_Usada.setSelected(pedidos_piezas.elementAt(i).getPieza_usada());
						}
						//devolucion
						if(pedidos_piezas.elementAt(i).getFecha_solicitud_devolucion()!=null)
							dcFSD.setDate(pedidos_piezas.elementAt(i).getFecha_solicitud_devolucion());
						else
							dcFSD.setDate(null);
						if(pedidos_piezas.elementAt(i).getFecha_aprobacion_solicitud_devolucion()!=null)
							dcFAS.setDate(pedidos_piezas.elementAt(i).getFecha_aprobacion_solicitud_devolucion());
						else
							dcFAS.setDate(null);
						if(pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null){
							setDevolucion(pedidos_piezas.elementAt(i).getDevolucion_pieza());
							if(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null)
								dcFDF.setDate(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion());
							tfNumero_Remito.setText(pedidos_piezas.elementAt(i).getDevolucion_pieza().getNumero_remito());
							tfTransporte.setText(pedidos_piezas.elementAt(i).getDevolucion_pieza().getTransporte());
							tfNumero_Retiro.setText(pedidos_piezas.elementAt(i).getDevolucion_pieza().getNumero_retiro());
						}
						//muleto
						if(pedidos_piezas.elementAt(i).getMuleto()!=null){
							setMuleto(pedidos_piezas.elementAt(i).getMuleto());
							tfVIN_Muleto.setText(pedidos_piezas.elementAt(i).getMuleto().getVin());
							taDesc_Muleto.setText(pedidos_piezas.elementAt(i).getMuleto().getDescripcion());
						}
						//bdg
						if(pedidos_piezas.elementAt(i).getBdg()!=null){
							setBdg(pedidos_piezas.elementAt(i).getBdg());
							if(pedidos_piezas.elementAt(i).getBdg().getFecha_bdg()!=null)
								dcFBDG.setDate(pedidos_piezas.elementAt(i).getBdg().getFecha_bdg());
							tfNumero_BDG.setText(pedidos_piezas.elementAt(i).getBdg().getNumero_bdg());
						}
						break;
					}
				}
			}else{
				//piezas
				tfNum_Pieza.setText("");
				cbProveedor.setSelectedIndex(0);
				taDesc_Pedido.setText("");
				dcFSF.setDate(null);
				dcFRF.setDate(null);
				tfEstado_Pedido.setText("");
				tfPNC.setText("");
				//agente
				dcFEA.setDate(null);
				dcFRA.setDate(null);
				cbxPieza_Usada.setSelected(false);
				//devolucion
				dcFSD.setDate(null);
				dcFAS.setDate(null);
				dcFDF.setDate(null);
				tfNumero_Remito.setText("");
				tfTransporte.setText("");
				tfNumero_Retiro.setText("");
				//muleto
				tfVIN_Muleto.setText("");
				taDesc_Muleto.setText("");
				//bdg
				dcFBDG.setDate(null);
				tfNumero_BDG.setText("");
			}
		}
	}

	//GETTERS AND SETTERS//
	public MediadorPedido getMediador() {
		return mediador;
	}

	public void setMediador(MediadorPedido mediador) {
		this.mediador = mediador;
	}

	public PedidoDTO getPedido() {
		return pedido;
	}

	public void setPedido(PedidoDTO pedido) {
		this.pedido = pedido;
	}

	public void setReclamo(ReclamoDTO reclamo) {
		this.reclamo = reclamo;
		tfNumeroOrden.setText(" [ID: "+reclamo.getId()+"]");
	}

	public Devolucion_PiezaDTO getDevolucion() {
		return devolucion;
	}

	public void setDevolucion(Devolucion_PiezaDTO devolucion) {
		this.devolucion = devolucion;
	}

	public MuletoDTO getMuleto() {
		return muleto;
	}

	public void setMuleto(MuletoDTO muleto) {
		this.muleto = muleto;
	}

	public BdgDTO getBdg() {
		return bdg;
	}

	public void setBdg(BdgDTO bdg) {
		this.bdg = bdg;
	}
}
