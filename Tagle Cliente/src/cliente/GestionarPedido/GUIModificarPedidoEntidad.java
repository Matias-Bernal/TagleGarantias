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
import common.DTOs.Mano_ObraDTO;
import common.DTOs.MuletoDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.PiezaDTO;
import common.DTOs.ProveedorDTO;
import common.DTOs.ReclamoDTO;

public class GUIModificarPedidoEntidad extends JFrame {

	private static final long serialVersionUID = 1L;
	private MediadorPedido mediador;
	private JPanel contentPane;
	private JDateChooser dcFSF;
	private JDateChooser dcFRF;
	private JDateChooser dcFBDG;	
	private JDateChooser dcFDF;
	
	private JTextField tfNumero_Pedido;
	private JTextField tfPNC;
	private JTextField tfNumeroOrden;
	private JTextField tfNum_Pieza;
	private JTextField tfVIN_Muleto;
	private JTextField tfHs_Mano_Obra;
	private JTextField tfVal_Mano_Obra;
	private JTextField tfCod_Mano_Obra;
	private JTextField tfNumero_BDG;
	private JTextField tfEstado_Pedido;
	private JTextField tfNumero_Remito;
	private JTextField tfTransporte;
	private JTextField tfNumero_Retiro;
	private JTextField tfFSP;

	private JTextArea taDesc_Muleto;
	private JTextArea taDesc_Pedido;

	private JCheckBox cbxPropio;
	private JCheckBox cbxStrock;
	
	private JComboBox<String> cbPiezas;
	private DefaultComboBoxModel<String> cmbPiezas;
	private Vector<String> numeros_piezas;

	private JComboBox<String> cbProveedor;
	private DefaultComboBoxModel<String> cmbProveedor;
	private Vector<String> proveedores;


	private Vector<PiezaDTO> piezasDTO;
	private Vector<Pedido_PiezaDTO> pedidos_piezas;

	private PedidoDTO pedido;
	private ReclamoDTO reclamo;
	private Devolucion_PiezaDTO devolucion;
	private Mano_ObraDTO mano_obra;
	private BdgDTO bdg;
	private MuletoDTO muleto;
	private JButton btn_clear_FSF;
	private JButton btn_clear_FRF;
	private JButton btn_clear_FDF;
	private JButton btn_clear_FBDG;
	private JButton btn_clear_FAS;
	private JButton btn_clear_FSD;
	private JDateChooser dcFAS;
	private JDateChooser dcFSD;
	private JDateChooser dcFCambio;
	private JButton btn_clear_FCambio;
	private JButton btnModificar;
		
	public GUIModificarPedidoEntidad(final MediadorPedido mediador, PedidoDTO pedido) {
		this.setMediador(mediador);
		this.setPedido(pedido);
		cargarDatos();
		initialize();
		completarCampos();
	}
	
	private void cargarDatos() {
		proveedores = mediador.obtenerProveedores();
		pedidos_piezas = mediador.obtenerPedidos_PiezasEntidad(pedido.getId());
		
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
		setTitle("MODIFICAR PEDIDO ENTIDAD");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 865, 700);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/edit_pedido_entidad.png")));
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		JLabel lblFechaSolicitud = new JLabel("Fecha Solicitud Pedido");
		lblFechaSolicitud.setBorder(null);
		lblFechaSolicitud.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaSolicitud.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSolicitud.setBounds(0, 40, 150, 20);
		contentPane.add(lblFechaSolicitud);
		
		JLabel lblOrden = new JLabel("Numero Orden");
		lblOrden.setBorder(null);
		lblOrden.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblOrden.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrden.setBounds(0, 70, 150, 20);
		contentPane.add(lblOrden);
		
		tfNumeroOrden = new JTextField();
		tfNumeroOrden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumeroOrden.setEditable(false);
		tfNumeroOrden.setBounds(150, 70, 160, 20);
		contentPane.add(tfNumeroOrden);
		tfNumeroOrden.setColumns(10);
		
		JButton btnCancelar = new GlossyButton("CANCELAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCancelar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCancelar.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(203, 630, 120, 20);
		contentPane.add(btnCancelar);
		
		btnModificar = new GlossyButton("MODIFICAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar.setEnabled(false);
		btnModificar.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(526, 630, 120, 20);
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
		panel_piezas.setBounds(10, 105, 424, 515);
		contentPane.add(panel_piezas);
		panel_piezas.setLayout(null);
		
		JLabel lblPiezas = new JLabel("Piezas");
		lblPiezas.setBorder(null);
		lblPiezas.setBounds(0, 40, 140, 20);
		panel_piezas.add(lblPiezas);
		lblPiezas.setHorizontalAlignment(SwingConstants.CENTER);
		
		cbPiezas = new JComboBox<String>();
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
		
		cbxPropio = new JCheckBox("Propio");
		cbxPropio.setBorder(null);
		cbxPropio.setContentAreaFilled(false);
		cbxPropio.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cbxPropio.setBounds(138, 210, 130, 23);
		panel_piezas.add(cbxPropio);
		cbxPropio.setHorizontalAlignment(SwingConstants.LEFT);
		
		cbxStrock = new JCheckBox("Stock");
		cbxStrock.setBorder(null);
		cbxStrock.setContentAreaFilled(false);
		cbxStrock.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cbxStrock.setBounds(270, 210, 126, 23);
		panel_piezas.add(cbxStrock);
		cbxStrock.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblPnc = new JLabel("PNC");
		lblPnc.setBorder(null);
		lblPnc.setBounds(0, 330, 140, 20);
		panel_piezas.add(lblPnc);
		lblPnc.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfPNC = new JTextField();
		tfPNC.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

		tfPNC.setBounds(140, 330, 160, 20);
		panel_piezas.add(tfPNC);
		tfPNC.setColumns(10);
		
		dcFSF = new JDateChooser();
		dcFSF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFSF.setBounds(140, 240, 160, 20);
		panel_piezas.add(dcFSF);
		
		JLabel lblFechaSolicitudFabrica = new JLabel("Fecha Solicitud Fabrica");
		lblFechaSolicitudFabrica.setBorder(null);
		lblFechaSolicitudFabrica.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaSolicitudFabrica.setBounds(0, 240, 140, 20);
		panel_piezas.add(lblFechaSolicitudFabrica);
		lblFechaSolicitudFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblFechaRecepcionFabrica = new JLabel("Fecha Recepcion Fabrica");
		lblFechaRecepcionFabrica.setBorder(null);
		lblFechaRecepcionFabrica.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaRecepcionFabrica.setBounds(0, 270, 140, 20);
		panel_piezas.add(lblFechaRecepcionFabrica);
		lblFechaRecepcionFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		
		dcFRF = new JDateChooser();
		dcFRF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFRF.setBounds(140, 270, 160, 20);
		panel_piezas.add(dcFRF);
		
		tfNum_Pieza = new JTextField();
		tfNum_Pieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNum_Pieza.setColumns(10);
		tfNum_Pieza.setBounds(140, 70, 160, 20);
		panel_piezas.add(tfNum_Pieza);
		
		cbProveedor = new JComboBox<String>();
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
		lblEstadoPedido.setBounds(0, 300, 140, 20);
		panel_piezas.add(lblEstadoPedido);
		
		JButton btnModificarPieza = new GlossyButton("GUARDAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificarPieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificarPieza.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/save.png")));
		btnModificarPieza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarPieza();
			}
		});
		btnModificarPieza.setBounds(305, 40, 110, 20);
		panel_piezas.add(btnModificarPieza);
		
		tfEstado_Pedido = new JTextField();
		tfEstado_Pedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfEstado_Pedido.setEditable(false);
		tfEstado_Pedido.setColumns(10);
		tfEstado_Pedido.setBounds(140, 300, 256, 20);
		panel_piezas.add(tfEstado_Pedido);
		
		JLabel lblPiezas_1 = new JLabel("PIEZAS");
		lblPiezas_1.setBorder(null);
		lblPiezas_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPiezas_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezas_1.setBounds(142, 10, 130, 20);
		panel_piezas.add(lblPiezas_1);
		
		btn_clear_FSF = new JButton("");
		btn_clear_FSF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FSF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFSF.getDate()!=null)
					dcFSF.setDate(null);
			}
		});
		btn_clear_FSF.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FSF.setBounds(310, 240, 25, 20);
		panel_piezas.add(btn_clear_FSF);
		
		btn_clear_FRF = new JButton("");
		btn_clear_FRF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FRF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFRF.getDate()!=null)
					dcFRF.setDate(null);
			}
		});
		btn_clear_FRF.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FRF.setBounds(310, 270, 25, 20);
		panel_piezas.add(btn_clear_FRF);
		
		JLabel lblFechaCambio = new JLabel("Fecha Cambio");
		lblFechaCambio.setBorder(null);
		lblFechaCambio.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaCambio.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaCambio.setBounds(0, 361, 140, 20);
		panel_piezas.add(lblFechaCambio);
		
		dcFCambio = new JDateChooser();
		dcFCambio.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFCambio.setBounds(140, 361, 160, 20);
		panel_piezas.add(dcFCambio);
		
		btn_clear_FCambio = new JButton("");
		btn_clear_FCambio.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FCambio.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FCambio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (dcFCambio.getDate()!=null)
					dcFCambio.setDate(null);
			}
		});
		btn_clear_FCambio.setBounds(310, 361, 25, 20);
		panel_piezas.add(btn_clear_FCambio);
		
		JPanel panel_claves_foraneas = new TransparentPanel();
		panel_claves_foraneas.setBorder(null);
		panel_claves_foraneas.setBounds(438, 105, 402, 515);
		contentPane.add(panel_claves_foraneas);
		panel_claves_foraneas.setLayout(null);
		
		taDesc_Muleto = new JTextArea(4, 31);
		taDesc_Muleto.setLineWrap(true);
		taDesc_Muleto.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		taDesc_Muleto.setBounds(140, 240, 260, 70);
		panel_claves_foraneas.add(taDesc_Muleto);
		
		JLabel lblDescripcionMuleto = new JLabel("Descripcion Muleto");
		lblDescripcionMuleto.setBorder(null);
		lblDescripcionMuleto.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcionMuleto.setBounds(0, 240, 140, 20);
		panel_claves_foraneas.add(lblDescripcionMuleto);
		
		JLabel lblVin = new JLabel("VIN Muleto");
		lblVin.setBorder(null);
		lblVin.setHorizontalAlignment(SwingConstants.CENTER);
		lblVin.setBounds(0, 215, 140, 20);
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
		tfVIN_Muleto.setBounds(140, 215, 160, 20);
		panel_claves_foraneas.add(tfVIN_Muleto);
		
		JLabel lblMuleto = new JLabel("MULETO");
		lblMuleto.setBorder(null);
		lblMuleto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMuleto.setHorizontalAlignment(SwingConstants.CENTER);
		lblMuleto.setBounds(142, 190, 130, 20);
		panel_claves_foraneas.add(lblMuleto);
		
		JLabel lblCantidadHs = new JLabel("Horas de Mano Obra");
		lblCantidadHs.setBorder(null);
		lblCantidadHs.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidadHs.setBounds(0, 335, 140, 20);
		panel_claves_foraneas.add(lblCantidadHs);
		
		tfHs_Mano_Obra = new JTextField();
		tfHs_Mano_Obra.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfHs_Mano_Obra.setColumns(10);
		tfHs_Mano_Obra.setBounds(140, 335, 160, 20);
		panel_claves_foraneas.add(tfHs_Mano_Obra);
		
		JLabel lblValor = new JLabel(" Valor de Mano Obra");
		lblValor.setBorder(null);
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblValor.setBounds(0, 360, 140, 20);
		panel_claves_foraneas.add(lblValor);
		
		tfVal_Mano_Obra = new JTextField();
		tfVal_Mano_Obra.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfVal_Mano_Obra.setColumns(10);
		tfVal_Mano_Obra.setBounds(140, 360, 160, 20);
		panel_claves_foraneas.add(tfVal_Mano_Obra);
		
		JLabel lblCodigoManoObra = new JLabel("Codigo de Mano Obra");
		lblCodigoManoObra.setBorder(null);
		lblCodigoManoObra.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigoManoObra.setBounds(0, 385, 140, 20);
		panel_claves_foraneas.add(lblCodigoManoObra);
		
		tfCod_Mano_Obra = new JTextField();
		tfCod_Mano_Obra.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfCod_Mano_Obra.setColumns(10);
		tfCod_Mano_Obra.setBounds(140, 385, 160, 20);
		panel_claves_foraneas.add(tfCod_Mano_Obra);
		
		JLabel lblManoObra = new JLabel("MANO OBRA");
		lblManoObra.setBorder(null);
		lblManoObra.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblManoObra.setHorizontalAlignment(SwingConstants.CENTER);
		lblManoObra.setBounds(142, 310, 130, 20);
		panel_claves_foraneas.add(lblManoObra);
		
		JLabel lblBdg = new JLabel("BDG");
		lblBdg.setBorder(null);
		lblBdg.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBdg.setHorizontalAlignment(SwingConstants.CENTER);
		lblBdg.setBounds(142, 405, 130, 20);
		panel_claves_foraneas.add(lblBdg);
		
		JLabel lblFechaBdg = new JLabel("Fecha Carga BDG");
		lblFechaBdg.setBorder(null);
		lblFechaBdg.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaBdg.setBounds(0, 430, 140, 20);
		panel_claves_foraneas.add(lblFechaBdg);
		lblFechaBdg.setHorizontalAlignment(SwingConstants.CENTER);
		
		dcFBDG = new JDateChooser();
		dcFBDG.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFBDG.setBounds(140, 430, 160, 20);
		panel_claves_foraneas.add(dcFBDG);
		
		JLabel lblNumeroDeBdg = new JLabel("Numero de BDG");
		lblNumeroDeBdg.setBorder(null);
		lblNumeroDeBdg.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroDeBdg.setBounds(0, 455, 140, 20);
		panel_claves_foraneas.add(lblNumeroDeBdg);
		
		tfNumero_BDG = new JTextField();
		tfNumero_BDG.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_BDG.setColumns(10);
		tfNumero_BDG.setBounds(140, 455, 160, 20);
		panel_claves_foraneas.add(tfNumero_BDG);
		
		JLabel lblDevolucion = new JLabel("DEVOLUCION");
		lblDevolucion.setBorder(null);
		lblDevolucion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDevolucion.setBounds(142, 10, 130, 20);
		panel_claves_foraneas.add(lblDevolucion);
		lblDevolucion.setHorizontalAlignment(SwingConstants.CENTER);
		
		dcFDF = new JDateChooser();
		dcFDF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFDF.setBounds(140, 90, 160, 20);
		panel_claves_foraneas.add(dcFDF);
		
		JLabel label_3 = new JLabel("Fecha Devolucion");
		label_3.setBorder(null);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(0, 90, 140, 20);
		panel_claves_foraneas.add(label_3);
		
		JLabel label_4 = new JLabel("Numero Remito");
		label_4.setBorder(null);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(0, 115, 140, 20);
		panel_claves_foraneas.add(label_4);
		
		tfNumero_Remito = new JTextField();
		tfNumero_Remito.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_Remito.setColumns(10);
		tfNumero_Remito.setBounds(140, 115, 160, 20);
		panel_claves_foraneas.add(tfNumero_Remito);
		
		JLabel label_5 = new JLabel("Transporte");
		label_5.setBorder(null);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(0, 140, 140, 20);
		panel_claves_foraneas.add(label_5);
		
		tfTransporte = new JTextField();
		tfTransporte.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfTransporte.setColumns(10);
		tfTransporte.setBounds(140, 140, 160, 20);
		panel_claves_foraneas.add(tfTransporte);
		
		JLabel label_6 = new JLabel("Numero Retiro");
		label_6.setBorder(null);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(0, 165, 140, 20);
		panel_claves_foraneas.add(label_6);
		
		tfNumero_Retiro = new JTextField();
		tfNumero_Retiro.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_Retiro.setColumns(10);
		tfNumero_Retiro.setBounds(140, 165, 160, 20);
		panel_claves_foraneas.add(tfNumero_Retiro);
		
		btn_clear_FDF = new JButton("");
		btn_clear_FDF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFDF.getDate()!=null)
					dcFDF.setDate(null);
			}
		});
		btn_clear_FDF.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FDF.setBounds(310, 90, 25, 20);
		panel_claves_foraneas.add(btn_clear_FDF);
		
		btn_clear_FBDG = new JButton("");
		btn_clear_FBDG.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FBDG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFBDG.getDate()!=null)
					dcFBDG.setDate(null);
			}
		});
		btn_clear_FBDG.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FBDG.setBounds(310, 430, 25, 20);
		panel_claves_foraneas.add(btn_clear_FBDG);
		
		JLabel label_7 = new JLabel("Fecha Aprobacion Solicitud");
		label_7.setBorder(null);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label_7.setBounds(0, 65, 140, 20);
		panel_claves_foraneas.add(label_7);
		
		dcFAS = new JDateChooser();
		dcFAS.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFAS.setBounds(140, 65, 160, 20);
		panel_claves_foraneas.add(dcFAS);
		
		btn_clear_FAS = new JButton("");
		btn_clear_FAS.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFAS.getDate()!=null)
					dcFAS.setDate(null);
			}
		});
		btn_clear_FAS.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FAS.setBounds(310, 65, 25, 20);
		panel_claves_foraneas.add(btn_clear_FAS);
		
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
		btn_clear_FSD.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FSD.setBounds(310, 40, 25, 20);
		panel_claves_foraneas.add(btn_clear_FSD);
		
		
		JLabel label_8 = new JLabel("Fecha Solicitud Devolucion");
		label_8.setBorder(null);
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label_8.setBounds(0, 40, 140, 20);
		panel_claves_foraneas.add(label_8);
		
		tfFSP = new JTextField();
		tfFSP.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfFSP.setEditable(false);
		tfFSP.setColumns(10);
		tfFSP.setBounds(150, 40, 160, 20);
		contentPane.add(tfFSP);
		
		contentPane.setVisible(true);	
	}

	@SuppressWarnings("unused")
	protected void modificarPieza() {
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
					
					pedidos_piezas.elementAt(i).setPropio(cbxPropio.isSelected());
					pedidos_piezas.elementAt(i).setStock(cbxStrock.isSelected());
					
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
					//fecha cambio
					if(dcFCambio.getDate()!=null){
						String fecha = format2.format(dcFCambio.getDate());
						java.sql.Date fcambio = new java.sql.Date(dcFCambio.getDate().getTime());
						pedidos_piezas.elementAt(i).setFecha_cambio(fcambio);
					}else{
						pedidos_piezas.elementAt(i).setFecha_cambio(null);
					}
					pedidos_piezas.elementAt(i).setPnc(tfPNC.getText());

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
					//mano obra
					if(!tfVal_Mano_Obra.getText().isEmpty() && !tfCod_Mano_Obra.getText().isEmpty() && !tfHs_Mano_Obra.getText().isEmpty()){
						if(pedidos_piezas.elementAt(i).getMano_obra()==null){
							Mano_ObraDTO mano_obra = new Mano_ObraDTO();
							try{
								mano_obra.setCantidad_horas(Float.parseFloat(tfHs_Mano_Obra.getText()));
								mano_obra.setValor_mano_obra(Float.parseFloat(tfVal_Mano_Obra.getText()));
								mano_obra.setCodigo_mano_obra(tfCod_Mano_Obra.getText());
								pedidos_piezas.elementAt(i).setMano_obra(mano_obra);
							}catch(Exception e){
								System.out.println("Error de parseo de floats");
							}
						}else{
							try{
								pedidos_piezas.elementAt(i).getMano_obra().setCantidad_horas(Float.parseFloat(tfHs_Mano_Obra.getText()));
								pedidos_piezas.elementAt(i).getMano_obra().setValor_mano_obra(Float.parseFloat(tfVal_Mano_Obra.getText()));
								pedidos_piezas.elementAt(i).getMano_obra().setCodigo_mano_obra(tfCod_Mano_Obra.getText());
							}catch(Exception e){
								System.out.println("Error de parseo de floats");
							}
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

	protected void modificar() {
		if(!tfNumero_Pedido.getText().isEmpty()){
			for (int i= 0;i<pedidos_piezas.size();i++){
				pedidos_piezas.elementAt(i).setNumero_pedido(tfNumero_Pedido.getText());
			}
		}
		if (mediador.modificarPedidoEntidad(pedido,pedidos_piezas)){
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
				cbxPropio.setSelected(false);
				cbxStrock.setSelected(false);
				dcFSF.setDate(null);
				dcFRF.setDate(null);
				dcFCambio.setDate(null);

				tfEstado_Pedido.setText("");
				tfPNC.setText("");
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
				//mano obra
				tfHs_Mano_Obra.setText("");
				tfVal_Mano_Obra.setText("");
				tfCod_Mano_Obra.setText("");
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
						if(pedidos_piezas.elementAt(i).getFecha_cambio()!=null)
							dcFCambio.setDate(pedidos_piezas.elementAt(i).getFecha_cambio());
						tfEstado_Pedido.setText(pedidos_piezas.elementAt(i).getEstado_pedido());
						tfPNC.setText(pedidos_piezas.elementAt(i).getPnc());
						//
						if(pedidos_piezas.elementAt(i).getPropio()!=null){
							cbxPropio.setSelected(pedidos_piezas.elementAt(i).getPropio());
						}
						if(pedidos_piezas.elementAt(i).getStock()!=null){
							cbxStrock.setSelected(pedidos_piezas.elementAt(i).getStock());
						}
						//devolucion
						if(pedidos_piezas.elementAt(i).getFecha_solicitud_devolucion()!=null)
							dcFSD.setDate(pedidos_piezas.elementAt(i).getFecha_solicitud_devolucion());
						if(pedidos_piezas.elementAt(i).getFecha_aprobacion_solicitud_devolucion()!=null)
							dcFAS.setDate(pedidos_piezas.elementAt(i).getFecha_aprobacion_solicitud_devolucion());
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
						//mano obra
						if(pedidos_piezas.elementAt(i).getMano_obra()!=null){
							setMano_obra(pedidos_piezas.elementAt(i).getMano_obra());
							try{
								tfHs_Mano_Obra.setText(String.valueOf(pedidos_piezas.elementAt(i).getMano_obra().getCantidad_horas()));
								tfVal_Mano_Obra.setText(String.valueOf(pedidos_piezas.elementAt(i).getMano_obra().getValor_mano_obra()));
								tfCod_Mano_Obra.setText(pedidos_piezas.elementAt(i).getMano_obra().getCodigo_mano_obra());
								
							}catch (Exception e){
								tfHs_Mano_Obra.setText("");
								tfVal_Mano_Obra.setText("");
								tfCod_Mano_Obra.setText("");
							}
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
				cbxPropio.setSelected(false);
				cbxStrock.setSelected(false);
				dcFSF.setDate(null);
				dcFRF.setDate(null);
				dcFCambio.setDate(null);
				tfEstado_Pedido.setText("");
				tfPNC.setText("");
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
				//mano obra
				tfHs_Mano_Obra.setText("");
				tfVal_Mano_Obra.setText("");
				tfCod_Mano_Obra.setText("");
				//bdg
				dcFBDG.setDate(null);
				tfNumero_BDG.setText("");
			}
		}
		
	}
	@SuppressWarnings("unused")
	private Pedido_PiezaDTO buscarPedido_Pieza(String numero_pieza) {
		Pedido_PiezaDTO pedido = null;
		for(int i=0;i< pedidos_piezas.size();i++){
			if(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza().equals(numero_pieza))
				pedido = pedidos_piezas.elementAt(i);
		}
		return pedido;
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

	public Devolucion_PiezaDTO getDevolucion() {
		return devolucion;
	}

	public void setDevolucion(Devolucion_PiezaDTO devolucion) {
		this.devolucion = devolucion;
	}

	public Mano_ObraDTO getMano_obra() {
		return mano_obra;
	}

	public void setMano_obra(Mano_ObraDTO mano_obra) {
		this.mano_obra = mano_obra;
	}

	public BdgDTO getBdg() {
		return bdg;
	}

	public void setBdg(BdgDTO bdg) {
		this.bdg = bdg;
	}

	public MuletoDTO getMuleto() {
		return muleto;
	}

	public void setMuleto(MuletoDTO muleto) {
		this.muleto = muleto;
	}
}