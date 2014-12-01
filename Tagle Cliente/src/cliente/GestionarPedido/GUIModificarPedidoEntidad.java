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
import java.awt.Component;
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

import org.eclipse.wb.swing.FocusTraversalOnArray;

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
import common.DTOs.RecursoDTO;

public class GUIModificarPedidoEntidad extends JFrame {

	private static final long serialVersionUID = 1L;
	private MediadorPedido mediador;
	private JPanel contentPane;
	private JDateChooser dcFSF;
	private JDateChooser dcFRF;
	private JDateChooser dcFDF;
	
	private JTextField tfNumero_Pedido;
	private JTextField tfPNC;
	private JTextField tfNumeroOrden;
	private JTextField tfNum_Pieza;
	private JTextField tfVIN_Muleto;
	private JTextField tfHs_Mano_Obra;
	private JTextField tfVal_Mano_Obra;
	private JTextField tfCod_Mano_Obra;
	private JTextField tfEstado_Pedido;
	private JTextField tfNumero_Remito;
	private JTextField tfTransporte;
	private JTextField tfNumero_Retiro;

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
	private Devolucion_PiezaDTO devolucion;
	private Mano_ObraDTO mano_obra;
	private BdgDTO bdg;
	private MuletoDTO muleto;
	private JButton btn_clear_FSF;
	private JButton btn_clear_FRF;
	private JButton btn_clear_FDF;
	private JDateChooser dcFTurno;
	private JButton btn_clear_FCambio;
	private JButton btnModificar;
	private JLabel lblEntidad;
	private JTextField tFEntidad;
	private JLabel lblNombreReclamante;
	private JTextField tFNombreReclamante;
	private JButton btnVerReclamante;
	private JTextField tFNombreTitular;
	private JTextField tFDominio;
	private JTextField tFVinVehiculo;
	private JTextField tFMarca;
	private JTextField tFModelo;
	private JPanel panel;
	private JPanel orden;
	private JLabel lblFechaReclamo;
	private JDateChooser dcFReclamo;
	private JDateChooser dCFAperturaOrden;
	private JTextField tFNumeroRecurso;
	private JButton btn_clear_fcorden;
	private JButton btn_clear_frecurso;
	private JDateChooser dCFCierreOrden;
	private JDateChooser dCFechaRecurso;
	private JDateChooser dCFSP;
	private JButton btnModificarPieza;
		
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
		tFEntidad.setText(pedido.getReclamo().getRegistrante().getNombre_registrante());
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
		setTitle("MODIFICAR PEDIDO ENTIDAD");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/edit_pedido_entidad.png")));
		
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBounds(new Rectangle(0, 0, 1366, 768));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JButton btnCancelar = new GlossyButton("CANCELAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCancelar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCancelar.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(322, 670, 200, 30);
		contentPane.add(btnCancelar);
		
		btnModificar = new GlossyButton("MODIFICAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(844, 670, 200, 30);
		contentPane.add(btnModificar);
		
		JPanel panel_piezas = new TransparentPanel();
		panel_piezas.setBorder(null);
		panel_piezas.setBounds(26, 245, 420, 340);
		contentPane.add(panel_piezas);
		panel_piezas.setLayout(null);
		
		JLabel lblPiezas = new JLabel("Piezas");
		lblPiezas.setBorder(null);
		lblPiezas.setBounds(10, 40, 150, 20);
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
		cbPiezas.setBounds(160, 40, 160, 20);
		panel_piezas.add(cbPiezas);
		
		cbxPropio = new JCheckBox("Propio");
		cbxPropio.setBorder(null);
		cbxPropio.setContentAreaFilled(false);
		cbxPropio.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cbxPropio.setBounds(160, 210, 130, 23);
		panel_piezas.add(cbxPropio);
		cbxPropio.setHorizontalAlignment(SwingConstants.LEFT);
		
		cbxStrock = new JCheckBox("Stock");
		cbxStrock.setBorder(null);
		cbxStrock.setContentAreaFilled(false);
		cbxStrock.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cbxStrock.setBounds(290, 210, 126, 23);
		panel_piezas.add(cbxStrock);
		cbxStrock.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblPnc = new JLabel("PNC");
		lblPnc.setBorder(null);
		lblPnc.setBounds(10, 240, 150, 20);
		panel_piezas.add(lblPnc);
		lblPnc.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfPNC = new JTextField();
		tfPNC.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

		tfPNC.setBounds(160, 240, 160, 20);
		panel_piezas.add(tfPNC);
		tfPNC.setColumns(10);
		
		tfNum_Pieza = new JTextField();
		tfNum_Pieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNum_Pieza.setColumns(10);
		tfNum_Pieza.setBounds(160, 70, 160, 20);
		panel_piezas.add(tfNum_Pieza);
		
		cbProveedor = new JComboBox<String>();
		cbProveedor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cmbProveedor = new DefaultComboBoxModel<String>(proveedores);
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
		label.setBounds(10, 130, 150, 20);
		panel_piezas.add(label);
		
		JLabel label_1 = new JLabel("Proveedor");
		label_1.setBorder(null);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 100, 150, 20);
		panel_piezas.add(label_1);
		
		JLabel label_2 = new JLabel("Numero Pieza");
		label_2.setBorder(null);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(10, 70, 150, 20);
		panel_piezas.add(label_2);
		
		JLabel lblPiezas_1 = new JLabel("PIEZAS");
		lblPiezas_1.setBorder(null);
		lblPiezas_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPiezas_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezas_1.setBounds(0, 10, 420, 20);
		panel_piezas.add(lblPiezas_1);
		
		JPanel vehiculo = new TransparentPanel();
		vehiculo.setBounds(472, 45, 420, 170);
		contentPane.add(vehiculo);
		vehiculo.setLayout(null);
		
		JLabel label_10 = new JLabel("Modelo");
		label_10.setBounds(10, 130, 130, 20);
		vehiculo.add(label_10);
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_9 = new JLabel("Marca");
		label_9.setBounds(10, 100, 130, 20);
		vehiculo.add(label_9);
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		
		tFVinVehiculo = new JTextField();
		tFVinVehiculo.setBackground(Color.LIGHT_GRAY);
		tFVinVehiculo.setDisabledTextColor(Color.BLACK);
		tFVinVehiculo.setEditable(false);
		tFVinVehiculo.setBounds(160, 70, 160, 20);
		vehiculo.add(tFVinVehiculo);
		tFVinVehiculo.setToolTipText("Ej 12345678901234567");
		tFVinVehiculo.setColumns(10);
		tFVinVehiculo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JLabel label_8 = new JLabel("VIN");
		label_8.setBounds(10, 70, 130, 20);
		vehiculo.add(label_8);
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_6 = new JLabel("Dominio");
		label_6.setBounds(10, 40, 130, 20);
		vehiculo.add(label_6);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		
		tFDominio = new JTextField();
		tFDominio.setDisabledTextColor(Color.BLACK);
		tFDominio.setBackground(Color.LIGHT_GRAY);
		tFDominio.setEditable(false);
		tFDominio.setBounds(160, 40, 80, 20);
		vehiculo.add(tFDominio);
		tFDominio.setToolTipText("Ej XYZ123");
		tFDominio.setColumns(10);
		tFDominio.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		tFMarca = new JTextField();
		tFMarca.setDisabledTextColor(Color.BLACK);
		tFMarca.setBackground(Color.LIGHT_GRAY);
		tFMarca.setEditable(false);
		tFMarca.setToolTipText("Ej 12345678901234567");
		tFMarca.setColumns(10);
		tFMarca.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFMarca.setBounds(160, 100, 160, 20);
		vehiculo.add(tFMarca);
		
		tFModelo = new JTextField();
		tFModelo.setBackground(Color.LIGHT_GRAY);
		tFModelo.setDisabledTextColor(Color.BLACK);
		tFModelo.setToolTipText("Ej 12345678901234567");
		tFModelo.setEditable(false);
		tFModelo.setColumns(10);
		tFModelo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFModelo.setBounds(160, 130, 160, 20);
		vehiculo.add(tFModelo);
		
		tFNombreTitular = new JTextField();
		tFNombreTitular.setDisabledTextColor(Color.BLACK);
		tFNombreTitular.setBounds(160, 10, 160, 20);
		vehiculo.add(tFNombreTitular);
		tFNombreTitular.setEditable(false);
		tFNombreTitular.setColumns(10);
		tFNombreTitular.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFNombreTitular.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNombreTitular = new JLabel("Nombre Titular");
		lblNombreTitular.setBounds(10, 10, 150, 20);
		vehiculo.add(lblNombreTitular);
		lblNombreTitular.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreTitular.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		panel = new TransparentPanel();
		panel.setBounds(26, 45, 420, 170);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnVerReclamante = new JButton("");
		btnVerReclamante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.verRegistrante(pedido.getId().toString());
			}
		});
		btnVerReclamante.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnVerReclamante.setBounds(330, 40, 25, 20);
		panel.add(btnVerReclamante);
		btnVerReclamante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		tFNombreReclamante = new JTextField();
		tFNombreReclamante.setBounds(160, 40, 160, 20);
		panel.add(tFNombreReclamante);
		tFNombreReclamante.setEditable(false);
		tFNombreReclamante.setColumns(10);
		tFNombreReclamante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFNombreReclamante.setBackground(Color.LIGHT_GRAY);
		
		lblNombreReclamante = new JLabel("Nombre Reclamante");
		lblNombreReclamante.setBounds(10, 40, 150, 20);
		panel.add(lblNombreReclamante);
		lblNombreReclamante.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreReclamante.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		lblEntidad = new JLabel("Entidad");
		lblEntidad.setBounds(10, 10, 150, 20);
		panel.add(lblEntidad);
		lblEntidad.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEntidad.setHorizontalAlignment(SwingConstants.CENTER);
		
		tFEntidad = new JTextField();
		tFEntidad.setBounds(160, 10, 160, 20);
		panel.add(tFEntidad);
		tFEntidad.setBackground(Color.LIGHT_GRAY);
		tFEntidad.setEditable(false);
		tFEntidad.setColumns(10);
		tFEntidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		lblFechaReclamo = new JLabel("Fecha Reclamo");
		lblFechaReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaReclamo.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaReclamo.setBorder(null);
		lblFechaReclamo.setBounds(10, 70, 150, 20);
		panel.add(lblFechaReclamo);
		
		dcFReclamo = new JDateChooser();
		dcFReclamo.setFont(new Font("Tahoma", Font.BOLD, 11));
		dcFReclamo.getCalendarButton().setEnabled(false);
		dcFReclamo.setEnabled(false);
		dcFReclamo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dcFReclamo.setBounds(160, 70, 160, 20);
		panel.add(dcFReclamo);
		
		JLabel lblFechaSolicitud = new JLabel("Fecha Solicitud Pedido");
		lblFechaSolicitud.setBounds(10, 100, 150, 20);
		panel.add(lblFechaSolicitud);
		lblFechaSolicitud.setBorder(null);
		lblFechaSolicitud.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaSolicitud.setHorizontalAlignment(SwingConstants.CENTER);
		
		dCFSP = new JDateChooser();
		dCFSP.setFont(new Font("Tahoma", Font.BOLD, 11));
		dCFSP.setEnabled(false);
		dCFSP.getCalendarButton().setEnabled(false);
		dCFSP.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFSP.setBounds(160, 100, 160, 20);
		panel.add(dCFSP);
		
		JLabel lblNumeroPedido = new JLabel("Numero Pedido");
		lblNumeroPedido.setBounds(10, 130, 150, 20);
		panel.add(lblNumeroPedido);
		lblNumeroPedido.setBorder(null);
		lblNumeroPedido.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNumeroPedido.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfNumero_Pedido = new JTextField();
		tfNumero_Pedido.setEditable(false);
		tfNumero_Pedido.setDisabledTextColor(Color.BLACK);
		tfNumero_Pedido.setBackground(Color.LIGHT_GRAY);
		tfNumero_Pedido.setBounds(160, 130, 160, 20);
		panel.add(tfNumero_Pedido);
		tfNumero_Pedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_Pedido.setColumns(10);
		
		orden = new TransparentPanel();
		orden.setBounds(918, 45, 420, 170);
		contentPane.add(orden);
		orden.setLayout(null);
		
		JLabel lblOrden = new JLabel("Numero Orden");
		lblOrden.setBounds(10, 10, 150, 20);
		orden.add(lblOrden);
		lblOrden.setBorder(null);
		lblOrden.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblOrden.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfNumeroOrden = new JTextField();
		tfNumeroOrden.setBounds(160, 10, 160, 20);
		orden.add(tfNumeroOrden);
		tfNumeroOrden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumeroOrden.setEditable(false);
		tfNumeroOrden.setColumns(10);
		
		JLabel lblFechaAperturaOrden = new JLabel("Fecha Apertura Orden");
		lblFechaAperturaOrden.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaAperturaOrden.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaAperturaOrden.setBorder(null);
		lblFechaAperturaOrden.setBounds(10, 40, 150, 20);
		orden.add(lblFechaAperturaOrden);
		
		dCFAperturaOrden = new JDateChooser();
		dCFAperturaOrden.setFont(new Font("Tahoma", Font.BOLD, 11));
		dCFAperturaOrden.getCalendarButton().setVisible(false);
		dCFAperturaOrden.getCalendarButton().setEnabled(false);
		dCFAperturaOrden.setEnabled(false);
		dCFAperturaOrden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFAperturaOrden.setBounds(160, 40, 160, 20);
		orden.add(dCFAperturaOrden);
		
		JLabel lblFechaCierreOrden = new JLabel("Fecha Cierre Orden");
		lblFechaCierreOrden.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaCierreOrden.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaCierreOrden.setBorder(null);
		lblFechaCierreOrden.setBounds(10, 70, 150, 20);
		orden.add(lblFechaCierreOrden);
		
		dCFCierreOrden = new JDateChooser();
		dCFCierreOrden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFCierreOrden.setBounds(160, 70, 160, 20);
		orden.add(dCFCierreOrden);
		
		JLabel lblNumeroDeRecurso = new JLabel("Numero de Recurso");
		lblNumeroDeRecurso.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNumeroDeRecurso.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroDeRecurso.setBorder(null);
		lblNumeroDeRecurso.setBounds(10, 100, 150, 20);
		orden.add(lblNumeroDeRecurso);
		
		tFNumeroRecurso = new JTextField();
		tFNumeroRecurso.setColumns(10);
		tFNumeroRecurso.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFNumeroRecurso.setBounds(160, 100, 160, 20);
		orden.add(tFNumeroRecurso);
		
		JLabel lblFechaRecurso = new JLabel("Fecha Recurso");
		lblFechaRecurso.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaRecurso.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaRecurso.setBorder(null);
		lblFechaRecurso.setBounds(10, 130, 150, 20);
		orden.add(lblFechaRecurso);
		
		dCFechaRecurso = new JDateChooser();
		dCFechaRecurso.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFechaRecurso.setBounds(160, 130, 160, 20);
		orden.add(dCFechaRecurso);
		
		btn_clear_fcorden = new JButton("");
		btn_clear_fcorden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFCierreOrden.getDate()!=null)
					dCFCierreOrden.setDate(null);
			}
		});
		btn_clear_fcorden.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_fcorden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_fcorden.setBounds(330, 70, 25, 20);
		orden.add(btn_clear_fcorden);
		
		btn_clear_frecurso = new JButton("");
		btn_clear_frecurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFechaRecurso.getDate()!=null)
					dCFechaRecurso.setDate(null);
			}
		});
		btn_clear_frecurso.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_frecurso.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_frecurso.setBounds(330, 130, 25, 20);
		orden.add(btn_clear_frecurso);
		
		JPanel fabrica = new TransparentPanel();
		fabrica.setBounds(472, 245, 420, 340);
		contentPane.add(fabrica);
		fabrica.setLayout(null);
		
		taDesc_Muleto = new JTextArea(4, 31);
		taDesc_Muleto.setBounds(160, 190, 250, 70);
		fabrica.add(taDesc_Muleto);
		taDesc_Muleto.setLineWrap(true);
		taDesc_Muleto.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		tfVIN_Muleto = new JTextField();
		tfVIN_Muleto.setBounds(160, 160, 160, 20);
		fabrica.add(tfVIN_Muleto);
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
		
		JLabel lblMuleto = new JLabel("MULETO");
		lblMuleto.setBounds(0, 130, 420, 20);
		fabrica.add(lblMuleto);
		lblMuleto.setBorder(null);
		lblMuleto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMuleto.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblVin = new JLabel("VIN Muleto");
		lblVin.setBounds(10, 160, 150, 20);
		fabrica.add(lblVin);
		lblVin.setBorder(null);
		lblVin.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblDescripcionMuleto = new JLabel("Descripcion Muleto");
		lblDescripcionMuleto.setBounds(10, 190, 150, 20);
		fabrica.add(lblDescripcionMuleto);
		lblDescripcionMuleto.setBorder(null);
		lblDescripcionMuleto.setHorizontalAlignment(SwingConstants.CENTER);
		
		dcFSF = new JDateChooser();
		dcFSF.setBounds(160, 10, 160, 20);
		fabrica.add(dcFSF);
		dcFSF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JLabel lblFechaSolicitudFabrica = new JLabel("Fecha Solicitud Fabrica");
		lblFechaSolicitudFabrica.setBounds(10, 10, 150, 20);
		fabrica.add(lblFechaSolicitudFabrica);
		lblFechaSolicitudFabrica.setBorder(null);
		lblFechaSolicitudFabrica.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaSolicitudFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		
		btn_clear_FSF = new JButton("");
		btn_clear_FSF.setBounds(330, 10, 25, 20);
		fabrica.add(btn_clear_FSF);
		btn_clear_FSF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FSF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFSF.getDate()!=null)
					dcFSF.setDate(null);
			}
		});
		btn_clear_FSF.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		
		JLabel lblFechaRecepcionFabrica = new JLabel("Fecha Recepcion Fabrica");
		lblFechaRecepcionFabrica.setBounds(10, 40, 150, 20);
		fabrica.add(lblFechaRecepcionFabrica);
		lblFechaRecepcionFabrica.setBorder(null);
		lblFechaRecepcionFabrica.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaRecepcionFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		
		dcFRF = new JDateChooser();
		dcFRF.setBounds(160, 40, 160, 20);
		fabrica.add(dcFRF);
		dcFRF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		btn_clear_FRF = new JButton("");
		btn_clear_FRF.setBounds(330, 40, 25, 20);
		fabrica.add(btn_clear_FRF);
		btn_clear_FRF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FRF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFRF.getDate()!=null)
					dcFRF.setDate(null);
			}
		});
		btn_clear_FRF.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		
		JLabel lblFechaCambio = new JLabel("Fecha Turno");
		lblFechaCambio.setBounds(10, 100, 150, 20);
		fabrica.add(lblFechaCambio);
		lblFechaCambio.setBorder(null);
		lblFechaCambio.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaCambio.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		dcFTurno = new JDateChooser();
		dcFTurno.setBounds(160, 100, 160, 20);
		fabrica.add(dcFTurno);
		dcFTurno.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		btn_clear_FCambio = new JButton("");
		btn_clear_FCambio.setBounds(330, 100, 25, 20);
		fabrica.add(btn_clear_FCambio);
		btn_clear_FCambio.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FCambio.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		
		JLabel lblEstadoPedido = new JLabel("Estado Pedido");
		lblEstadoPedido.setBounds(10, 70, 150, 20);
		fabrica.add(lblEstadoPedido);
		lblEstadoPedido.setBorder(null);
		lblEstadoPedido.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfEstado_Pedido = new JTextField();
		tfEstado_Pedido.setBackground(Color.LIGHT_GRAY);
		tfEstado_Pedido.setBounds(160, 70, 250, 20);
		fabrica.add(tfEstado_Pedido);
		tfEstado_Pedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfEstado_Pedido.setEditable(false);
		tfEstado_Pedido.setColumns(10);
		btn_clear_FCambio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (dcFTurno.getDate()!=null)
					dcFTurno.setDate(null);
			}
		});
		
		JPanel devoluciones = new TransparentPanel();
		devoluciones.setBounds(918, 245, 420, 340);
		contentPane.add(devoluciones);
		devoluciones.setLayout(null);
		
		JLabel lblManoObra = new JLabel("MANO OBRA");
		lblManoObra.setBounds(0, 10, 418, 20);
		devoluciones.add(lblManoObra);
		lblManoObra.setBorder(null);
		lblManoObra.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblManoObra.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblCantidadHs = new JLabel("Horas de Mano Obra");
		lblCantidadHs.setBounds(10, 40, 150, 20);
		devoluciones.add(lblCantidadHs);
		lblCantidadHs.setBorder(null);
		lblCantidadHs.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfHs_Mano_Obra = new JTextField();
		tfHs_Mano_Obra.setBounds(160, 40, 160, 20);
		devoluciones.add(tfHs_Mano_Obra);
		tfHs_Mano_Obra.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfHs_Mano_Obra.setColumns(10);
		
		JLabel lblValor = new JLabel(" Valor de Mano Obra");
		lblValor.setBounds(10, 70, 150, 20);
		devoluciones.add(lblValor);
		lblValor.setBorder(null);
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfVal_Mano_Obra = new JTextField();
		tfVal_Mano_Obra.setBounds(160, 70, 160, 20);
		devoluciones.add(tfVal_Mano_Obra);
		tfVal_Mano_Obra.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfVal_Mano_Obra.setColumns(10);
		
		JLabel lblCodigoManoObra = new JLabel("Codigo de Mano Obra");
		lblCodigoManoObra.setBounds(10, 100, 150, 20);
		devoluciones.add(lblCodigoManoObra);
		lblCodigoManoObra.setBorder(null);
		lblCodigoManoObra.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfCod_Mano_Obra = new JTextField();
		tfCod_Mano_Obra.setBounds(160, 100, 160, 20);
		devoluciones.add(tfCod_Mano_Obra);
		tfCod_Mano_Obra.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfCod_Mano_Obra.setColumns(10);
		
		JLabel lblDevolucion = new JLabel("DEVOLUCION");
		lblDevolucion.setBounds(0, 130, 418, 20);
		devoluciones.add(lblDevolucion);
		lblDevolucion.setBorder(null);
		lblDevolucion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDevolucion.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_3 = new JLabel("Fecha Devolucion");
		label_3.setBounds(10, 160, 140, 20);
		devoluciones.add(label_3);
		label_3.setBorder(null);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		dcFDF = new JDateChooser();
		dcFDF.setBounds(150, 160, 160, 20);
		devoluciones.add(dcFDF);
		dcFDF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		btn_clear_FDF = new JButton("");
		btn_clear_FDF.setBounds(320, 160, 25, 20);
		devoluciones.add(btn_clear_FDF);
		btn_clear_FDF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFDF.getDate()!=null)
					dcFDF.setDate(null);
			}
		});
		btn_clear_FDF.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		
		tfNumero_Remito = new JTextField();
		tfNumero_Remito.setBounds(150, 190, 160, 20);
		devoluciones.add(tfNumero_Remito);
		tfNumero_Remito.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_Remito.setColumns(10);
		
		JLabel label_4 = new JLabel("Numero Remito");
		label_4.setBounds(10, 190, 140, 20);
		devoluciones.add(label_4);
		label_4.setBorder(null);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_5 = new JLabel("Transporte");
		label_5.setBounds(10, 220, 140, 20);
		devoluciones.add(label_5);
		label_5.setBorder(null);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfTransporte = new JTextField();
		tfTransporte.setBounds(150, 220, 160, 20);
		devoluciones.add(tfTransporte);
		tfTransporte.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfTransporte.setColumns(10);
		
		JLabel lblNumeroGuia = new JLabel("Numero Guia");
		lblNumeroGuia.setBounds(10, 250, 140, 20);
		devoluciones.add(lblNumeroGuia);
		lblNumeroGuia.setBorder(null);
		lblNumeroGuia.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfNumero_Retiro = new JTextField();
		tfNumero_Retiro.setBounds(150, 250, 160, 20);
		devoluciones.add(tfNumero_Retiro);
		tfNumero_Retiro.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_Retiro.setColumns(10);
		
		btnModificarPieza = new GlossyButton("GUARDAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificarPieza.setBounds(583, 600, 200, 30);
		contentPane.add(btnModificarPieza);
		btnModificarPieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificarPieza.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/save.png")));
		
		JPanel piezas = new TransparentPanel();
		piezas.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		piezas.setBounds(0, 230, 1366, 429);
		contentPane.add(piezas);
		piezas.setLayout(null);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tFEntidad, tFNombreReclamante, dcFReclamo, dCFSP, tfNumero_Pedido, tFNombreTitular, tFDominio, tFVinVehiculo, tFMarca, tFModelo, tfNumeroOrden, dCFAperturaOrden, dCFCierreOrden, tFNumeroRecurso, dCFechaRecurso, cbPiezas, tfNum_Pieza, cbProveedor, taDesc_Pedido, cbxPropio, cbxStrock, tfPNC, dcFSF, dcFRF, tfEstado_Pedido, dcFTurno, tfVIN_Muleto, taDesc_Muleto, tfHs_Mano_Obra, tfVal_Mano_Obra, tfCod_Mano_Obra, dcFDF, tfNumero_Remito, tfTransporte, tfNumero_Retiro, btnModificarPieza, btnCancelar, btnModificar, panel_piezas, lblPiezas, lblPnc, dcFSF.getCalendarButton(), lblFechaSolicitudFabrica, lblFechaRecepcionFabrica, dcFRF.getCalendarButton(), label, label_1, label_2, lblEstadoPedido, lblPiezas_1, btn_clear_FSF, btn_clear_FRF, lblFechaCambio, dcFTurno.getCalendarButton(), btn_clear_FCambio, lblVin, lblMuleto, lblDescripcionMuleto, lblManoObra, lblCantidadHs, lblValor, lblCodigoManoObra, lblDevolucion, dcFDF.getCalendarButton(), btn_clear_FDF, label_3, label_4, label_5, lblNumeroGuia, vehiculo, label_10, label_9, label_8, label_6, lblNombreTitular, panel, btnVerReclamante, lblNombreReclamante, lblEntidad, lblFechaReclamo, dcFReclamo.getCalendarButton(), lblFechaSolicitud, dCFSP.getCalendarButton(), lblNumeroPedido, orden, lblOrden, lblFechaAperturaOrden, dCFAperturaOrden.getCalendarButton(), lblFechaCierreOrden, dCFCierreOrden.getCalendarButton(), lblNumeroDeRecurso, lblFechaRecurso, dCFechaRecurso.getCalendarButton(), btn_clear_fcorden, btn_clear_frecurso}));
		btnModificarPieza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarPieza();
			}
		});
		
		contentPane.setVisible(true);	
	}


	@SuppressWarnings("static-access")
	protected void modificarPieza() {
		if (tfNum_Pieza.getText().isEmpty() || cbProveedor.getSelectedItem()==null){
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{	
			//pieza
			ProveedorDTO proveedor = mediador.obtenerProveedor(cbProveedor.getSelectedItem().toString());

			for(int i=0;i< pedidos_piezas.size();i++){
				if(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza().equals(cbPiezas.getSelectedItem().toString())){

					pedidos_piezas.elementAt(i).getPieza().setNumero_pieza(tfNum_Pieza.getText());
					pedidos_piezas.elementAt(i).getPieza().setProveedor(proveedor);
					pedidos_piezas.elementAt(i).getPieza().setDescripcion(taDesc_Pedido.getText());
					if (!numeros_piezas.contains(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza())){
						numeros_piezas.remove(cbPiezas.getSelectedItem().toString());
						numeros_piezas.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());
					}
					cmbPiezas = new DefaultComboBoxModel<String>(numeros_piezas);
					cbPiezas.setModel(cmbPiezas);
					pedidos_piezas.elementAt(i).setPropio(cbxPropio.isSelected());
					pedidos_piezas.elementAt(i).setStock(cbxStrock.isSelected());
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
					
					//fecha turno
					if(dcFTurno.getDate()!=null){
						java.sql.Date fturno = new java.sql.Date(dcFTurno.getDate().getTime());
						Calendar c = Calendar.getInstance();
						c.setTime(dcFTurno.getDate());
						c.add(c.DAY_OF_YEAR, -3);
						java.sql.Date fllamdoturno = new java.sql.Date(c.getTime().getTime());					
						
						pedidos_piezas.elementAt(i).getPedido().getReclamo().setFecha_turno(fturno);
						pedidos_piezas.elementAt(i).getPedido().getReclamo().setFecha_llamado_turno(fllamdoturno);
					}else{
						pedidos_piezas.elementAt(i).getPedido().getReclamo().setFecha_turno(null);
						pedidos_piezas.elementAt(i).getPedido().getReclamo().setFecha_llamado_turno(null);
					}
					//muleto
					if(!tfVIN_Muleto.getText().isEmpty() || !taDesc_Muleto.getText().isEmpty()){
						if(pedidos_piezas.elementAt(i).getMuleto()==null){
							MuletoDTO muleto = new MuletoDTO();
							muleto.setVin(tfVIN_Muleto.getText());
							muleto.setDescripcion(taDesc_Muleto.getText());
							muleto.setPedido(pedidos_piezas.elementAt(i).getPedido());
							muleto.setPieza(pedidos_piezas.elementAt(i).getPieza());
							pedidos_piezas.elementAt(i).setMuleto(muleto);
						}else{
							pedidos_piezas.elementAt(i).getMuleto().setVin(tfVIN_Muleto.getText());
							pedidos_piezas.elementAt(i).getMuleto().setDescripcion(taDesc_Muleto.getText());
							pedidos_piezas.elementAt(i).getMuleto().setPedido(pedidos_piezas.elementAt(i).getPedido());
							pedidos_piezas.elementAt(i).getMuleto().setPieza(pedidos_piezas.elementAt(i).getPieza());
						}
					}
					
					//mano obra
					if(!tfVal_Mano_Obra.getText().isEmpty() && !tfCod_Mano_Obra.getText().isEmpty() && !tfHs_Mano_Obra.getText().isEmpty()){
						if(pedidos_piezas.elementAt(i).getMano_obra()==null){
							Mano_ObraDTO mano_obra = new Mano_ObraDTO();
							try{
								mano_obra.setCantidad_horas(Float.parseFloat(tfHs_Mano_Obra.getText()));
							}catch(Exception e){
								mano_obra.setCantidad_horas(new Float(0.00));
							}
							try{
								mano_obra.setValor_mano_obra(Float.parseFloat(tfVal_Mano_Obra.getText()));
							}catch(Exception e){
								mano_obra.setValor_mano_obra(new Float(0.00));
							}
							mano_obra.setCodigo_mano_obra(tfCod_Mano_Obra.getText());
							pedidos_piezas.elementAt(i).setMano_obra(mano_obra);
							
						}else{
							try{
								pedidos_piezas.elementAt(i).getMano_obra().setCantidad_horas(Float.parseFloat(tfHs_Mano_Obra.getText()));
								pedidos_piezas.elementAt(i).getMano_obra().setValor_mano_obra(Float.parseFloat(tfVal_Mano_Obra.getText()));
							}catch(Exception e){
								mano_obra.setCantidad_horas(new Float(0.00));
								mano_obra.setValor_mano_obra(new Float(0.00));
							}
							pedidos_piezas.elementAt(i).getMano_obra().setCodigo_mano_obra(tfCod_Mano_Obra.getText());
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
				tfPNC.setText("");
				
				dcFSF.setDate(null);
				dcFRF.setDate(null);
				tfEstado_Pedido.setText("");
				dcFTurno.setDate(null);
				//muleto
				tfVIN_Muleto.setText("");
				taDesc_Muleto.setText("");
				
				//mano obra
				tfHs_Mano_Obra.setText("");
				tfVal_Mano_Obra.setText("");
				tfCod_Mano_Obra.setText("");
				//devolucion
				dcFDF.setDate(null);
				tfNumero_Remito.setText("");
				tfTransporte.setText("");
				tfNumero_Retiro.setText("");
												
				for(int i=0;i< pedidos_piezas.size();i++){
					if(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza().equals(cbPiezas.getSelectedItem().toString())){

						tfNum_Pieza.setText(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());
						cbProveedor.setSelectedItem(pedidos_piezas.elementAt(i).getPieza().getProveedor().getNombre());
						taDesc_Pedido.setText(pedidos_piezas.elementAt(i).getPieza().getDescripcion());
						if(pedidos_piezas.elementAt(i).getPropio()!=null){
							cbxPropio.setSelected(pedidos_piezas.elementAt(i).getPropio());
						}
						if(pedidos_piezas.elementAt(i).getStock()!=null){
							cbxStrock.setSelected(pedidos_piezas.elementAt(i).getStock());
						}
						tfPNC.setText(pedidos_piezas.elementAt(i).getPnc());

						if(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null)
							dcFSF.setDate(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica());
						if(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null)
							dcFRF.setDate(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica());
						tfEstado_Pedido.setText(pedidos_piezas.elementAt(i).getEstado_pedido());
						if(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno()!=null)
							dcFTurno.setDate(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno());
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
				cbxPropio.setSelected(false);
				cbxStrock.setSelected(false);
				tfPNC.setText("");
				
				dcFSF.setDate(null);
				dcFRF.setDate(null);
				tfEstado_Pedido.setText("");
				dcFTurno.setDate(null);
				//muleto
				tfVIN_Muleto.setText("");
				taDesc_Muleto.setText("");
				
				//mano obra
				tfHs_Mano_Obra.setText("");
				tfVal_Mano_Obra.setText("");
				tfCod_Mano_Obra.setText("");
				//devolucion
				dcFDF.setDate(null);
				tfNumero_Remito.setText("");
				tfTransporte.setText("");
				tfNumero_Retiro.setText("");
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