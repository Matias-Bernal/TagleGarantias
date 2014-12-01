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
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
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
import common.DTOs.RecursoDTO;


public class GUIModificarPedidoAgente extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorPedido mediador;
	private JPanel contentPane;
	private JDateChooser dcFSF;
	private JDateChooser dcFRF;
	private JDateChooser dcFRA;
	private JDateChooser dcFEA;
	private JDateChooser dcFDF;
	private JTextField tfPNC;
	private JTextField tfNumeroOrden;
	private JTextField tfNum_Pieza;
	private JTextField tfVIN_Muleto;
	private JTextField tfNumero_Retiro;
	private JTextField tfTransporte;
	private JTextField tfNumero_Remito;
	private JTextField tfEstado_Pedido;	

	private JTextArea taDesc_Muleto;
	private JTextArea taDesc_Pedido;
	
	private JCheckBox cbxPieza_Usada;

	private JComboBox<String> cbPiezas;
	private DefaultComboBoxModel<String> cmbPiezas;
	private Vector<String> numeros_piezas;

	private JComboBox<String> cbProveedor;
	private DefaultComboBoxModel<String> cmbProveedor;
	private Vector<String> proveedores;
	
	private Vector<Pedido_PiezaDTO> pedidos_piezas;
	private Vector<PiezaDTO> piezasDTO;
	
	private PedidoDTO pedido;
	private Devolucion_PiezaDTO devolucion;
	private BdgDTO bdg;
	private MuletoDTO muleto;
	private JButton btn_clear_FEA;
	private JButton btn_clear_FSF;
	private JButton btnModificar_Pieza;
	private JButton btnModificar;
	private JButton btnCancelar;
	private JTextField tFNombreReclamante;
	private JTextField tFAgente;
	private JTextField tfNumero_Pedido;
	private JTextField tFVinVehiculo;
	private JTextField tFDominio;
	private JTextField tFMarca;
	private JTextField tFModelo;
	private JTextField tFNombreTitular;
	
	private JPanel panel_orden;
	private JDateChooser dCFAperturaOrden;
	private JTextField tFNumeroRecurso;
	private JButton btn_clear_fcorden;
	private JButton btn_clear_frecurso;
	private JDateChooser dCFCierreOrden;
	private JDateChooser dCFechaRecurso;
	private JDateChooser dcFReclamo;
	private JButton btnVerReclamante;
	private JDateChooser dCFSP;

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
		tFAgente.setText(pedido.getReclamo().getRegistrante().getNombre_registrante());
		tFNombreReclamante.setText(pedido.getReclamo().getReclamante().getNombre_apellido());
		dcFReclamo.setDate(pedido.getReclamo().getFecha_reclamo());
		dCFSP.setDate(pedido.getFecha_solicitud_pedido());
		if (pedidos_piezas.size()>0)
			tfNumero_Pedido.setText(pedidos_piezas.elementAt(0).getNumero_pedido());

		tFNombreTitular.setText(pedido.getReclamo().getVehiculo().getNombre_titular());
		tFDominio.setText(pedido.getReclamo().getVehiculo().getDominio());
		tFVinVehiculo.setText(pedido.getReclamo().getVehiculo().getVin());
		tFMarca.setText(pedido.getReclamo().getVehiculo().getMarca().getNombre_marca());
		tFModelo.setText(pedido.getReclamo().getVehiculo().getModelo().getNombre_modelo());
		
		tfNumeroOrden.setText(pedido.getReclamo().getOrden().getNumero_orden());
		dCFAperturaOrden.setDate(pedido.getReclamo().getOrden().getFecha_apertura());
		dCFCierreOrden.setDate(pedido.getReclamo().getOrden().getFecha_cierre());
		if(pedido.getReclamo().getOrden().getRecurso()!=null){
			tFNumeroRecurso.setText(pedido.getReclamo().getOrden().getRecurso().getNumero_recurso());
			dCFechaRecurso.setDate(pedido.getReclamo().getOrden().getRecurso().getFecha_recurso());
		}
		//pedido_pieza
		if(cmbPiezas.getSize()>0)
			cbPiezas.setSelectedIndex(0);
		actualizarPiezas();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0,1382,768);
		//setExtendedState(Frame.MAXIMIZED_BOTH);
		//setLocationRelativeTo(null);
		setTitle("MODIFICAR PEDIDO AGENTE");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/edit_pedido_entidad.png")));
		
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBounds(new Rectangle(0, 0, 1366, 768));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		btnCancelar = new GlossyButton("CANCELAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCancelar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCancelar.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/delete.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(322, 670, 200, 30);
		contentPane.add(btnCancelar);
		
		btnModificar = new GlossyButton("MODIFICAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(844, 670, 200, 30);
		contentPane.add(btnModificar);
		
		panel_orden = new TransparentPanel();
		panel_orden.setBounds(919, 11, 418, 170);
		contentPane.add(panel_orden);
		panel_orden.setLayout(null);
		
		JLabel lblOrden = new JLabel("Numero Orden");
		lblOrden.setBounds(10, 10, 150, 20);
		panel_orden.add(lblOrden);
		lblOrden.setBorder(null);
		lblOrden.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblOrden.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfNumeroOrden = new JTextField();
		tfNumeroOrden.setBounds(160, 10, 160, 20);
		panel_orden.add(tfNumeroOrden);
		tfNumeroOrden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumeroOrden.setEditable(false);
		tfNumeroOrden.setColumns(10);
		
		JLabel lblFechaAperturaOrden = new JLabel("Fecha Apertura Orden");
		lblFechaAperturaOrden.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaAperturaOrden.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaAperturaOrden.setBorder(null);
		lblFechaAperturaOrden.setBounds(10, 40, 150, 20);
		panel_orden.add(lblFechaAperturaOrden);
		
		dCFAperturaOrden = new JDateChooser();
		dCFAperturaOrden.setFont(new Font("Tahoma", Font.BOLD, 11));
		dCFAperturaOrden.getCalendarButton().setVisible(false);
		dCFAperturaOrden.getCalendarButton().setEnabled(false);
		dCFAperturaOrden.setEnabled(false);
		dCFAperturaOrden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFAperturaOrden.setBounds(160, 40, 160, 20);
		panel_orden.add(dCFAperturaOrden);
		
		JLabel lblFechaCierreOrden = new JLabel("Fecha Cierre Orden");
		lblFechaCierreOrden.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaCierreOrden.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaCierreOrden.setBorder(null);
		lblFechaCierreOrden.setBounds(10, 70, 150, 20);
		panel_orden.add(lblFechaCierreOrden);
		
		dCFCierreOrden = new JDateChooser();
		dCFCierreOrden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFCierreOrden.setBounds(160, 70, 160, 20);
		panel_orden.add(dCFCierreOrden);
		
		JLabel lblNumeroDeRecurso = new JLabel("Numero de Recurso");
		lblNumeroDeRecurso.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNumeroDeRecurso.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroDeRecurso.setBorder(null);
		lblNumeroDeRecurso.setBounds(10, 100, 150, 20);
		panel_orden.add(lblNumeroDeRecurso);
		
		tFNumeroRecurso = new JTextField();
		tFNumeroRecurso.setColumns(10);
		tFNumeroRecurso.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFNumeroRecurso.setBounds(160, 100, 160, 20);
		panel_orden.add(tFNumeroRecurso);
		
		JLabel lblFechaRecurso = new JLabel("Fecha Recurso");
		lblFechaRecurso.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaRecurso.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaRecurso.setBorder(null);
		lblFechaRecurso.setBounds(10, 130, 150, 20);
		panel_orden.add(lblFechaRecurso);
		
		dCFechaRecurso = new JDateChooser();
		dCFechaRecurso.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFechaRecurso.setBounds(160, 130, 160, 20);
		panel_orden.add(dCFechaRecurso);
		
		btn_clear_fcorden = new JButton("");
		btn_clear_fcorden.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_fcorden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFCierreOrden.getDate()!=null)
					dCFCierreOrden.setDate(null);
			}
		});
		btn_clear_fcorden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_fcorden.setBounds(330, 70, 25, 20);
		panel_orden.add(btn_clear_fcorden);
		
		btn_clear_frecurso = new JButton("");
		btn_clear_frecurso.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_frecurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFechaRecurso.getDate()!=null)
					dCFechaRecurso.setDate(null);
			}
		});
		btn_clear_frecurso.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_frecurso.setBounds(330, 130, 25, 20);
		panel_orden.add(btn_clear_frecurso);
		cmbPiezas = new DefaultComboBoxModel<String>(numeros_piezas);
		cmbProveedor = new DefaultComboBoxModel<String>(proveedores);
		
		TransparentPanel panel_reclamo = new TransparentPanel();
		panel_reclamo.setLayout(null);
		panel_reclamo.setBounds(27, 11, 420, 170);
		contentPane.add(panel_reclamo);
		
		btnVerReclamante = new JButton("");
		btnVerReclamante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.verRegistrante(pedido.getId().toString());
			}
		});
		btnVerReclamante.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnVerReclamante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnVerReclamante.setBounds(330, 40, 25, 20);
		panel_reclamo.add(btnVerReclamante);
		
		tFNombreReclamante = new JTextField();
		tFNombreReclamante.setText((String) null);
		tFNombreReclamante.setEditable(false);
		tFNombreReclamante.setColumns(10);
		tFNombreReclamante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFNombreReclamante.setBackground(Color.LIGHT_GRAY);
		tFNombreReclamante.setBounds(160, 40, 160, 20);
		panel_reclamo.add(tFNombreReclamante);
		
		JLabel label_9 = new JLabel("Nombre Reclamante");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_9.setBounds(10, 40, 150, 20);
		panel_reclamo.add(label_9);
		
		JLabel lblAgente_1 = new JLabel("Agente");
		lblAgente_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgente_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblAgente_1.setBounds(10, 10, 150, 20);
		panel_reclamo.add(lblAgente_1);
		
		tFAgente = new JTextField();
		tFAgente.setText((String) null);
		tFAgente.setEditable(false);
		tFAgente.setColumns(10);
		tFAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFAgente.setBackground(Color.LIGHT_GRAY);
		tFAgente.setBounds(160, 10, 160, 20);
		panel_reclamo.add(tFAgente);
		
		JLabel label_11 = new JLabel("Fecha Reclamo");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_11.setBorder(null);
		label_11.setBounds(10, 70, 150, 20);
		panel_reclamo.add(label_11);
		
		dcFReclamo = new JDateChooser();
		dcFReclamo.getCalendarButton().setEnabled(false);
		dcFReclamo.setFont(new Font("Tahoma", Font.BOLD, 11));
		dcFReclamo.setEnabled(false);
		dcFReclamo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFReclamo.setBounds(160, 70, 160, 20);
		panel_reclamo.add(dcFReclamo);
		
		JLabel label_12 = new JLabel("Fecha Solicitud Pedido");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_12.setBorder(null);
		label_12.setBounds(10, 100, 150, 20);
		panel_reclamo.add(label_12);
		
		dCFSP = new JDateChooser();
		dCFSP.getCalendarButton().setEnabled(false);
		dCFSP.setFont(new Font("Tahoma", Font.BOLD, 11));
		dCFSP.setEnabled(false);
		dCFSP.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFSP.setBounds(160, 100, 160, 20);
		panel_reclamo.add(dCFSP);
		
		JLabel label_13 = new JLabel("Numero Pedido");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_13.setBorder(null);
		label_13.setBounds(10, 130, 150, 20);
		panel_reclamo.add(label_13);
		
		tfNumero_Pedido = new JTextField();
		tfNumero_Pedido.setEditable(false);
		tfNumero_Pedido.setDisabledTextColor(Color.BLACK);
		tfNumero_Pedido.setColumns(10);
		tfNumero_Pedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_Pedido.setBackground(Color.LIGHT_GRAY);
		tfNumero_Pedido.setBounds(160, 130, 160, 20);
		panel_reclamo.add(tfNumero_Pedido);
		
		TransparentPanel panel_vehiculo = new TransparentPanel();
		panel_vehiculo.setLayout(null);
		panel_vehiculo.setBounds(474, 11, 418, 170);
		contentPane.add(panel_vehiculo);
		
		JLabel label_14 = new JLabel("Modelo");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setBounds(10, 130, 130, 20);
		panel_vehiculo.add(label_14);
		
		JLabel label_15 = new JLabel("Marca");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setBounds(10, 100, 130, 20);
		panel_vehiculo.add(label_15);
		
		tFVinVehiculo = new JTextField();
		tFVinVehiculo.setToolTipText("Ej 12345678901234567");
		tFVinVehiculo.setText((String) null);
		tFVinVehiculo.setEditable(false);
		tFVinVehiculo.setDisabledTextColor(Color.BLACK);
		tFVinVehiculo.setColumns(10);
		tFVinVehiculo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFVinVehiculo.setBackground(Color.LIGHT_GRAY);
		tFVinVehiculo.setBounds(160, 70, 160, 20);
		panel_vehiculo.add(tFVinVehiculo);
		
		JLabel label_16 = new JLabel("VIN");
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setBounds(10, 70, 130, 20);
		panel_vehiculo.add(label_16);
		
		JLabel label_17 = new JLabel("Dominio");
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setBounds(10, 40, 130, 20);
		panel_vehiculo.add(label_17);
		
		tFDominio = new JTextField();
		tFDominio.setToolTipText("Ej XYZ123");
		tFDominio.setText((String) null);
		tFDominio.setEditable(false);
		tFDominio.setDisabledTextColor(Color.BLACK);
		tFDominio.setColumns(10);
		tFDominio.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFDominio.setBackground(Color.LIGHT_GRAY);
		tFDominio.setBounds(160, 40, 80, 20);
		panel_vehiculo.add(tFDominio);
		
		tFMarca = new JTextField();
		tFMarca.setToolTipText("Ej 12345678901234567");
		tFMarca.setText((String) null);
		tFMarca.setEditable(false);
		tFMarca.setDisabledTextColor(Color.BLACK);
		tFMarca.setColumns(10);
		tFMarca.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFMarca.setBackground(Color.LIGHT_GRAY);
		tFMarca.setBounds(160, 100, 160, 20);
		panel_vehiculo.add(tFMarca);
		
		tFModelo = new JTextField();
		tFModelo.setToolTipText("Ej 12345678901234567");
		tFModelo.setText((String) null);
		tFModelo.setEditable(false);
		tFModelo.setDisabledTextColor(Color.BLACK);
		tFModelo.setColumns(10);
		tFModelo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFModelo.setBackground(Color.LIGHT_GRAY);
		tFModelo.setBounds(160, 130, 160, 20);
		panel_vehiculo.add(tFModelo);
		
		tFNombreTitular = new JTextField();
		tFNombreTitular.setText((String) null);
		tFNombreTitular.setEditable(false);
		tFNombreTitular.setDisabledTextColor(Color.BLACK);
		tFNombreTitular.setColumns(10);
		tFNombreTitular.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFNombreTitular.setBackground(Color.LIGHT_GRAY);
		tFNombreTitular.setBounds(160, 10, 160, 20);
		panel_vehiculo.add(tFNombreTitular);
		
		JLabel label_18 = new JLabel("Nombre Titular");
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_18.setBounds(10, 10, 150, 20);
		panel_vehiculo.add(label_18);
		
		JPanel piezas = new TransparentPanel();
		piezas.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		piezas.setBounds(0, 230, 1366, 430);
		contentPane.add(piezas);
		piezas.setLayout(null);
		
		JPanel panel_piezas = new TransparentPanel();
		panel_piezas.setBounds(26, 22, 420, 340);
		piezas.add(panel_piezas);
		panel_piezas.setBorder(null);
		panel_piezas.setLayout(null);
		
		JLabel lblPiezas = new JLabel("Piezas");
		lblPiezas.setBorder(null);
		lblPiezas.setBounds(10, 40, 140, 20);
		panel_piezas.add(lblPiezas);
		lblPiezas.setHorizontalAlignment(SwingConstants.CENTER);
		
		cbPiezas = new JComboBox<String>();
		cbPiezas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbPiezas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				actualizarPiezas();
			}
		});
		cbPiezas.setModel(cmbPiezas);
		cbPiezas.setBounds(160, 40, 160, 20);
		panel_piezas.add(cbPiezas);
		
		JLabel lblPnc = new JLabel("PNC");
		lblPnc.setBorder(null);
		lblPnc.setBounds(10, 210, 140, 20);
		panel_piezas.add(lblPnc);
		lblPnc.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfPNC = new JTextField();
		tfPNC.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfPNC.setBounds(160, 210, 160, 20);
		panel_piezas.add(tfPNC);
		tfPNC.setColumns(10);
		
		tfNum_Pieza = new JTextField();
		tfNum_Pieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNum_Pieza.setColumns(10);
		tfNum_Pieza.setBounds(160, 70, 160, 20);
		panel_piezas.add(tfNum_Pieza);
		
		cbProveedor = new JComboBox<String>();
		cbProveedor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbProveedor.setModel(cmbProveedor);
		cbProveedor.setBounds(160, 100, 160, 20);
		panel_piezas.add(cbProveedor);
		
		taDesc_Pedido = new JTextArea(4, 31);
		taDesc_Pedido.setLineWrap(true);
		taDesc_Pedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		taDesc_Pedido.setBounds(160, 130, 250, 70);
		panel_piezas.add(taDesc_Pedido);
		
		JLabel label = new JLabel("Descripcion");
		label.setBorder(null);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 130, 140, 20);
		panel_piezas.add(label);
		
		JLabel label_1 = new JLabel("Proveedor");
		label_1.setBorder(null);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 100, 140, 20);
		panel_piezas.add(label_1);
		
		JLabel label_2 = new JLabel("Numero Pieza");
		label_2.setBorder(null);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(10, 70, 140, 20);
		panel_piezas.add(label_2);
		
		cbxPieza_Usada = new JCheckBox("Pieza Usada");
		cbxPieza_Usada.setContentAreaFilled(false);
		cbxPieza_Usada.setBorder(null);
		cbxPieza_Usada.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cbxPieza_Usada.setBounds(160, 240, 130, 20);
		panel_piezas.add(cbxPieza_Usada);
		cbxPieza_Usada.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel label_8 = new JLabel("PIEZAS");
		label_8.setBorder(null);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(0, 10, 420, 20);
		panel_piezas.add(label_8);
		
		JPanel panel_claves_foraneas = new TransparentPanel();
		panel_claves_foraneas.setBounds(472, 22, 420, 340);
		piezas.add(panel_claves_foraneas);
		panel_claves_foraneas.setBorder(null);
		panel_claves_foraneas.setLayout(null);
		
		taDesc_Muleto = new JTextArea(4, 31);
		taDesc_Muleto.setLineWrap(true);
		taDesc_Muleto.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		taDesc_Muleto.setBounds(160, 250, 250, 70);
		panel_claves_foraneas.add(taDesc_Muleto);
		
		JLabel lblDescripcionMuleto = new JLabel("Descripcion Muleto");
		lblDescripcionMuleto.setBorder(null);
		lblDescripcionMuleto.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcionMuleto.setBounds(10, 250, 150, 20);
		panel_claves_foraneas.add(lblDescripcionMuleto);
		
		JLabel lblVin = new JLabel("VIN Muleto");
		lblVin.setBorder(null);
		lblVin.setHorizontalAlignment(SwingConstants.CENTER);
		lblVin.setBounds(10, 220, 150, 20);
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
		tfVIN_Muleto.setBounds(160, 220, 160, 20);
		panel_claves_foraneas.add(tfVIN_Muleto);
		
		JLabel lblMuleto = new JLabel("MULETO");
		lblMuleto.setBorder(null);
		lblMuleto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMuleto.setHorizontalAlignment(SwingConstants.CENTER);
		lblMuleto.setBounds(0, 190, 420, 20);
		panel_claves_foraneas.add(lblMuleto);
		
		JLabel lblFechaSolicitudFabrica = new JLabel("Fecha Solicitud Fabrica");
		lblFechaSolicitudFabrica.setBounds(10, 10, 150, 20);
		panel_claves_foraneas.add(lblFechaSolicitudFabrica);
		lblFechaSolicitudFabrica.setBorder(null);
		lblFechaSolicitudFabrica.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaSolicitudFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		
		dcFSF = new JDateChooser();
		dcFSF.setBounds(160, 10, 160, 20);
		panel_claves_foraneas.add(dcFSF);
		dcFSF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		btn_clear_FSF = new JButton("");
		btn_clear_FSF.setBounds(330, 10, 25, 20);
		panel_claves_foraneas.add(btn_clear_FSF);
		btn_clear_FSF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FSF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFSF.getDate()!=null)
					dcFSF.setDate(null);
			}
		});
		btn_clear_FSF.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		
		JButton btn_clear_FRF = new JButton("");
		btn_clear_FRF.setBounds(330, 40, 25, 20);
		panel_claves_foraneas.add(btn_clear_FRF);
		btn_clear_FRF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FRF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFRF.getDate()!=null)
					dcFRF.setDate(null);
			}
		});
		btn_clear_FRF.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		
		dcFRF = new JDateChooser();
		dcFRF.setBounds(160, 40, 160, 20);
		panel_claves_foraneas.add(dcFRF);
		dcFRF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JLabel lblFechaRecepcionFabrica = new JLabel("Fecha Recepcion Fabrica");
		lblFechaRecepcionFabrica.setBounds(10, 40, 150, 20);
		panel_claves_foraneas.add(lblFechaRecepcionFabrica);
		lblFechaRecepcionFabrica.setBorder(null);
		lblFechaRecepcionFabrica.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaRecepcionFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblEstadoPedido = new JLabel("Estado Pedido");
		lblEstadoPedido.setBounds(10, 70, 150, 20);
		panel_claves_foraneas.add(lblEstadoPedido);
		lblEstadoPedido.setBorder(null);
		lblEstadoPedido.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfEstado_Pedido = new JTextField();
		tfEstado_Pedido.setBounds(160, 70, 250, 20);
		panel_claves_foraneas.add(tfEstado_Pedido);
		tfEstado_Pedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfEstado_Pedido.setEditable(false);
		tfEstado_Pedido.setColumns(10);
		
		JLabel lblAgente = new JLabel("AGENTE");
		lblAgente.setBounds(0, 100, 420, 20);
		panel_claves_foraneas.add(lblAgente);
		lblAgente.setBorder(null);
		lblAgente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAgente.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblFechaEnvioAgente = new JLabel("Fecha Envio Agente");
		lblFechaEnvioAgente.setBounds(10, 130, 150, 20);
		panel_claves_foraneas.add(lblFechaEnvioAgente);
		lblFechaEnvioAgente.setBorder(null);
		lblFechaEnvioAgente.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaEnvioAgente.setHorizontalAlignment(SwingConstants.CENTER);
		
		dcFEA = new JDateChooser();
		dcFEA.setBounds(160, 130, 160, 20);
		panel_claves_foraneas.add(dcFEA);
		dcFEA.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		btn_clear_FEA = new JButton("");
		btn_clear_FEA.setBounds(330, 130, 25, 20);
		panel_claves_foraneas.add(btn_clear_FEA);
		btn_clear_FEA.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FEA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFEA.getDate()!=null)
					dcFEA.setDate(null);
			}
		});
		btn_clear_FEA.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		
		JLabel lblFechaRecepcionAgente = new JLabel("Fecha Recepcion Agente");
		lblFechaRecepcionAgente.setBounds(10, 160, 150, 20);
		panel_claves_foraneas.add(lblFechaRecepcionAgente);
		lblFechaRecepcionAgente.setBorder(null);
		lblFechaRecepcionAgente.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaRecepcionAgente.setHorizontalAlignment(SwingConstants.CENTER);
		
		dcFRA = new JDateChooser();
		dcFRA.setBounds(160, 160, 160, 20);
		panel_claves_foraneas.add(dcFRA);
		dcFRA.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JButton btn_clear_FRA = new JButton("");
		btn_clear_FRA.setBounds(330, 160, 25, 20);
		panel_claves_foraneas.add(btn_clear_FRA);
		btn_clear_FRA.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FRA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFRA.getDate()!=null)
					dcFRA.setDate(null);
			}
		});
		btn_clear_FRA.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		
		JPanel panel = new TransparentPanel();
		panel.setBounds(918, 22, 420, 340);
		piezas.add(panel);
		panel.setLayout(null);
		
		JLabel label_6 = new JLabel("Fecha Devolucion");
		label_6.setBounds(10, 40, 150, 20);
		panel.add(label_6);
		label_6.setBorder(null);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		
		dcFDF = new JDateChooser();
		dcFDF.setBounds(160, 40, 160, 20);
		panel.add(dcFDF);
		dcFDF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JButton btn_clear_FD = new JButton("");
		btn_clear_FD.setBounds(330, 40, 25, 20);
		panel.add(btn_clear_FD);
		btn_clear_FD.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFDF.getDate()!=null)
					dcFDF.setDate(null);
			}
		});
		btn_clear_FD.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		
		tfNumero_Remito = new JTextField();
		tfNumero_Remito.setBounds(160, 70, 160, 20);
		panel.add(tfNumero_Remito);
		tfNumero_Remito.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_Remito.setColumns(10);
		
		JLabel label_5 = new JLabel("Numero Remito");
		label_5.setBounds(10, 70, 150, 20);
		panel.add(label_5);
		label_5.setBorder(null);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_4 = new JLabel("Transporte");
		label_4.setBounds(10, 100, 150, 20);
		panel.add(label_4);
		label_4.setBorder(null);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfTransporte = new JTextField();
		tfTransporte.setBounds(160, 100, 160, 20);
		panel.add(tfTransporte);
		tfTransporte.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfTransporte.setColumns(10);
		
		tfNumero_Retiro = new JTextField();
		tfNumero_Retiro.setBounds(160, 130, 160, 20);
		panel.add(tfNumero_Retiro);
		tfNumero_Retiro.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_Retiro.setColumns(10);
		
		JLabel label_3 = new JLabel("Numero Retiro");
		label_3.setBounds(10, 130, 150, 20);
		panel.add(label_3);
		label_3.setBorder(null);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_7 = new JLabel("DEVOLUCION");
		label_7.setBounds(0, 10, 420, 20);
		panel.add(label_7);
		label_7.setBorder(null);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnModificar_Pieza = new GlossyButton("GUARDAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar_Pieza.setBounds(583, 600, 200, 30);
		getContentPane().add(btnModificar_Pieza);
		btnModificar_Pieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar_Pieza.setIcon(new ImageIcon(GUIModificarPedidoAgente.class.getResource("/cliente/Resources/Icons/save.png")));
		btnModificar_Pieza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar_pieza();
			}
		});			
	}
		

	@SuppressWarnings("static-access")
	protected void modificar_pieza() {
		if (tfNum_Pieza.getText().isEmpty() || cbProveedor.getSelectedItem()==null){
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{	
				//pieza
				ProveedorDTO proveedor = mediador.obtenerProveedor(cbProveedor.getSelectedItem().toString());

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
						pedidos_piezas.elementAt(i).setPieza_usada(cbxPieza_Usada.isSelected());
						//pnc 
						pedidos_piezas.elementAt(i).setPnc(tfPNC.getText());
						
						//fecha solicitud fabrica
						if (dcFSF.getDate()!=null){
							java.sql.Date fsf = new java.sql.Date(dcFSF.getDate().getTime());
							pedidos_piezas.elementAt(i).setFecha_solicitud_fabrica(fsf);
						}else{
							pedidos_piezas.elementAt(i).setFecha_solicitud_fabrica(null);
						}
						//fecha recepcion fabrica
						if(dcFRF.getDate()!=null){
							java.sql.Date frf = new java.sql.Date(dcFRF.getDate().getTime());
							pedidos_piezas.elementAt(i).setFecha_recepcion_fabrica(frf);
						}else{
							pedidos_piezas.elementAt(i).setFecha_recepcion_fabrica(null);
						}
						//agente
						if(dcFEA.getDate()!=null){
							java.sql.Date fea = new java.sql.Date(dcFEA.getDate().getTime());
							pedidos_piezas.elementAt(i).setFecha_envio_agente(fea);
						}else{
							pedidos_piezas.elementAt(i).setFecha_envio_agente(null);
						}
						if(dcFRA.getDate()!=null){
							java.sql.Date fra = new java.sql.Date(dcFRA.getDate().getTime());
							pedidos_piezas.elementAt(i).setFecha_recepcion_agente(fra);
						}else{
							pedidos_piezas.elementAt(i).setFecha_recepcion_agente(null);
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
						//devolucion
						if(dcFDF.getDate()!=null || !tfNumero_Remito.getText().isEmpty() || !tfTransporte.getText().isEmpty() || !tfNumero_Retiro.getText().isEmpty()){
							if(pedidos_piezas.elementAt(i).getDevolucion_pieza()==null){
								Devolucion_PiezaDTO devolucion = new Devolucion_PiezaDTO();
								java.sql.Date fdf = new java.sql.Date(dcFDF.getDate().getTime());
								devolucion.setFecha_devolucion(fdf);
								devolucion.setNumero_remito(tfNumero_Remito.getText());
								devolucion.setTransporte(tfTransporte.getText());
								devolucion.setNumero_retiro(tfNumero_Retiro.getText());
								pedidos_piezas.elementAt(i).setDevolucion_pieza(devolucion);
								
								Calendar c = Calendar.getInstance();
								c.setTime(dcFDF.getDate());
								c.add(c.DAY_OF_YEAR, -5);
								java.sql.Date fsdf = new java.sql.Date(c.getTime().getTime());
								pedidos_piezas.elementAt(i).setFecha_solicitud_devolucion(fsdf);
								
								Calendar d = Calendar.getInstance();
								d.setTime(dcFDF.getDate());
								d.add(d.DAY_OF_YEAR, -4);
								java.sql.Date fasdf = new java.sql.Date(d.getTime().getTime());
								pedidos_piezas.elementAt(i).setFecha_aprobacion_solicitud_devolucion(fasdf);
							}else{
								java.sql.Date fdf = new java.sql.Date(dcFDF.getDate().getTime());
								pedidos_piezas.elementAt(i).getDevolucion_pieza().setFecha_devolucion(fdf);
								pedidos_piezas.elementAt(i).getDevolucion_pieza().setNumero_remito(tfNumero_Remito.getText());
								pedidos_piezas.elementAt(i).getDevolucion_pieza().setTransporte(tfTransporte.getText());
								pedidos_piezas.elementAt(i).getDevolucion_pieza().setNumero_retiro(tfNumero_Retiro.getText());
								
								Calendar c = Calendar.getInstance();
								c.setTime(dcFDF.getDate());
								c.add(c.DAY_OF_YEAR, -5);
								java.sql.Date fsdf = new java.sql.Date(c.getTime().getTime());
								pedidos_piezas.elementAt(i).setFecha_solicitud_devolucion(fsdf);
								
								Calendar d = Calendar.getInstance();
								d.setTime(dcFDF.getDate());
								d.add(d.DAY_OF_YEAR, -4);
								java.sql.Date fasdf = new java.sql.Date(d.getTime().getTime());
								pedidos_piezas.elementAt(i).setFecha_aprobacion_solicitud_devolucion(fasdf);
							}
						}
						
						//Recurso y Bdg
						if(!tFNumeroRecurso.getText().isEmpty() && dCFechaRecurso.getDate()!=null){
							if(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getRecurso()==null){
								RecursoDTO recurso = new RecursoDTO();
								recurso.setNumero_recurso(tFNumeroRecurso.getText());
								java.sql.Date frecurso = new java.sql.Date(dCFechaRecurso.getDate().getTime());
								recurso.setFecha_recurso(frecurso);
								pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().setRecurso(recurso);
								
								if(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getFecha_cierre()==null || dCFCierreOrden.getDate()==null)
									pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().setEstado("ABIERTA/CON RECURSO");
								else
									pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().setEstado("CERRADA");
								
								if(pedidos_piezas.elementAt(i).getBdg()!=null){
									java.sql.Date fbdg = new java.sql.Date(dCFechaRecurso.getDate().getTime());
									pedidos_piezas.elementAt(i).getBdg().setFecha_bdg(fbdg);
									pedidos_piezas.elementAt(i).getBdg().setNumero_bdg(tFNumeroRecurso.getText());
									pedidos_piezas.elementAt(i).getBdg().setPedido(pedido);
									pedidos_piezas.elementAt(i).getBdg().setPieza(pedidos_piezas.elementAt(i).getPieza());
								}else{
									BdgDTO bdg = new BdgDTO();
									java.sql.Date fbdg = new java.sql.Date(dCFechaRecurso.getDate().getTime());
									bdg.setFecha_bdg(fbdg);
									bdg.setNumero_bdg(tFNumeroRecurso.getText());
									bdg.setPedido(pedido);
									bdg.setPieza(pedidos_piezas.elementAt(i).getPieza());
									pedidos_piezas.elementAt(i).setBdg(bdg);
								}
							}else{
								pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getRecurso().setNumero_recurso(tFNumeroRecurso.getText());
								java.sql.Date frecurso = new java.sql.Date(dCFechaRecurso.getDate().getTime());
								pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getRecurso().setFecha_recurso(frecurso);
								
								if(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getFecha_cierre()==null || dCFCierreOrden.getDate()==null)
									pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().setEstado("ABIERTA/CON RECURSO");
								else
									pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().setEstado("CERRADA");
								if(pedidos_piezas.elementAt(i).getBdg()!=null){
									java.sql.Date fbdg = new java.sql.Date(dCFechaRecurso.getDate().getTime());
									pedidos_piezas.elementAt(i).getBdg().setFecha_bdg(fbdg);
									pedidos_piezas.elementAt(i).getBdg().setNumero_bdg(tFNumeroRecurso.getText());
									pedidos_piezas.elementAt(i).getBdg().setPedido(pedido);
									pedidos_piezas.elementAt(i).getBdg().setPieza(pedidos_piezas.elementAt(i).getPieza());
								}else{
									BdgDTO bdg = new BdgDTO();
									java.sql.Date fbdg = new java.sql.Date(dCFechaRecurso.getDate().getTime());
									bdg.setFecha_bdg(fbdg);
									bdg.setNumero_bdg(tFNumeroRecurso.getText());
									bdg.setPedido(pedido);
									bdg.setPieza(pedidos_piezas.elementAt(i).getPieza());
									pedidos_piezas.elementAt(i).setBdg(bdg);
								}
							}
						}
						
						if(dCFCierreOrden.getDate()!=null){
							java.sql.Date fcot = new java.sql.Date(dCFCierreOrden.getDate().getTime());
							pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().setFecha_cierre(fcot);
							pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().setEstado("CERRADA");
							pedidos_piezas.elementAt(i).setFecha_cambio(fcot);
						}else{
							pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().setFecha_cierre(null);
							pedidos_piezas.elementAt(i).setFecha_cambio(null);
							if(pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().getRecurso()==null)
								pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().setEstado("ABIERTA/CON RECURSO");
							else
								pedidos_piezas.elementAt(i).getPedido().getReclamo().getOrden().setEstado("ABIERTA/SIN RECURSO");
						}
						
						JOptionPane.showMessageDialog(this,"Pieza Modificada.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
						actualizarPiezas();
						break;
				}
			}
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
				cbxPieza_Usada.setSelected(false);
				tfPNC.setText("");
				dcFSF.setDate(null);
				dcFRF.setDate(null);
				tfEstado_Pedido.setText("");
				//muleto
				tfVIN_Muleto.setText("");
				taDesc_Muleto.setText("");
				//agente
				dcFEA.setDate(null);
				dcFRA.setDate(null);
				//devolucion
				dcFDF.setDate(null);
				tfNumero_Remito.setText("");
				tfTransporte.setText("");
				tfNumero_Retiro.setText("");
				//muleto
				tfVIN_Muleto.setText("");
				taDesc_Muleto.setText("");
									
				for(int i=0;i< pedidos_piezas.size();i++){
					if(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza().equals(cbPiezas.getSelectedItem().toString())){

						tfNum_Pieza.setText(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());
						cbProveedor.setSelectedItem(pedidos_piezas.elementAt(i).getPieza().getProveedor().getNombre());
						taDesc_Pedido.setText(pedidos_piezas.elementAt(i).getPieza().getDescripcion());
						if(pedidos_piezas.elementAt(i).getPieza_usada()!=null){
							cbxPieza_Usada.setSelected(pedidos_piezas.elementAt(i).getPieza_usada());
						}
						tfPNC.setText(pedidos_piezas.elementAt(i).getPnc());
						
						if(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null)
							dcFSF.setDate(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica());
						if(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null)
							dcFRF.setDate(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica());
						tfEstado_Pedido.setText(pedidos_piezas.elementAt(i).getEstado_pedido());
						//agente
						if(pedidos_piezas.elementAt(i).getFecha_envio_agente()!=null)
							dcFEA.setDate(pedidos_piezas.elementAt(i).getFecha_envio_agente());
						if(pedidos_piezas.elementAt(i).getFecha_recepcion_agente()!=null){
							dcFRA.setDate(pedidos_piezas.elementAt(i).getFecha_recepcion_agente());
						}
						if(pedidos_piezas.elementAt(i).getMuleto()!=null){
							setMuleto(pedidos_piezas.elementAt(i).getMuleto());
							tfVIN_Muleto.setText(pedidos_piezas.elementAt(i).getMuleto().getVin());
							taDesc_Muleto.setText(pedidos_piezas.elementAt(i).getMuleto().getDescripcion());
						}
						
						//devolucion
						if(pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null){
							setDevolucion(pedidos_piezas.elementAt(i).getDevolucion_pieza());
							if(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null)
								dcFDF.setDate(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion());
							tfNumero_Remito.setText(pedidos_piezas.elementAt(i).getDevolucion_pieza().getNumero_remito());
							tfTransporte.setText(pedidos_piezas.elementAt(i).getDevolucion_pieza().getTransporte());
							tfNumero_Retiro.setText(pedidos_piezas.elementAt(i).getDevolucion_pieza().getNumero_retiro());
						}
						break;
					}
				}
			}else{
				//piezas
				tfNum_Pieza.setText("");
				cbProveedor.setSelectedIndex(0);
				taDesc_Pedido.setText("");
				cbxPieza_Usada.setSelected(false);
				tfPNC.setText("");
				dcFSF.setDate(null);
				dcFRF.setDate(null);
				tfEstado_Pedido.setText("");
				//muleto
				tfVIN_Muleto.setText("");
				taDesc_Muleto.setText("");
				//agente
				dcFEA.setDate(null);
				dcFRA.setDate(null);
				//devolucion
				dcFDF.setDate(null);
				tfNumero_Remito.setText("");
				tfTransporte.setText("");
				tfNumero_Retiro.setText("");
				//muleto
				tfVIN_Muleto.setText("");
				taDesc_Muleto.setText("");
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
