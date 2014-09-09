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
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import com.toedter.calendar.JDateChooser;
import common.DTOs.PiezaDTO;
import common.DTOs.ProveedorDTO;
import common.DTOs.ReclamoDTO;

public class GUIAltaPedidoEntidad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MediadorPedido mediador;
	private JDateChooser fecha_solicitud_pedido;
	private JTextField tfNumero_Pedido;
	private JTextField tfReclamo;
	private JButton btnCancelar;
	private JButton btnCrear;
	private JButton btnBuscarReclamo;
	private JLabel lblNumeroPieza;
	private JTextField tfNumero_Pieza;
	private JTextArea taDescripcion;
	private JComboBox cb_proveedor;
	private JComboBox cbPiezas;
	private JButton btnAgregar;
	private JButton btnQuitar;
	
	private Vector<String> proveedores;
	private Vector<String> numeros_piezas;
 	private DefaultComboBoxModel<String> cmbProveedor;
 	private DefaultComboBoxModel<String> cmbPieza;
	private Vector<PiezaDTO> piezas;
	private ReclamoDTO reclamo;
	private JButton btn_clear_FSP;
	
	public GUIAltaPedidoEntidad(final MediadorPedido medidador) {
		this.setMedidador(medidador);
		cargarDatos();
		initialize();
	}
	
	private void cargarDatos() {
		proveedores = new Vector<String> ();
		numeros_piezas = new Vector<String>();
		piezas = new Vector<PiezaDTO>();
		proveedores = mediador.obtenerProveedores();
	}

	private void initialize() {
		setTitle("AGREGAR PEDIDO ENTIDAD");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIAltaPedidoEntidad.class.getResource("/cliente/Resources/Icons/add_pedido_entidad.png")));
		setLocationRelativeTo(null);
		setBounds(100, 100, 446, 362);
		
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		
		JLabel lblNumeroPedido = new JLabel("Numero Pedido");
		lblNumeroPedido.setBorder(null);
		lblNumeroPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroPedido.setBounds(0, 10, 130, 20);
		contentPane.add(lblNumeroPedido);
		
		tfNumero_Pedido = new JTextField();
		tfNumero_Pedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_Pedido.setBounds(138, 10, 256, 20);
		contentPane.add(tfNumero_Pedido);
		tfNumero_Pedido.setColumns(10);
		
		JLabel lblFechaSolicitud = new JLabel("Fecha Solicitud Pedido");
		lblFechaSolicitud.setBorder(null);
		lblFechaSolicitud.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSolicitud.setBounds(0, 41, 130, 20);
		contentPane.add(lblFechaSolicitud);
		
		fecha_solicitud_pedido = new JDateChooser();
		fecha_solicitud_pedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		//fecha_solicitud_pedido.setDate(new Date());
		fecha_solicitud_pedido.setBounds(138, 41, 163, 20);
		contentPane.add(fecha_solicitud_pedido);
		
		JLabel lblReclamo = new JLabel("Reclamo");
		lblReclamo.setBorder(null);
		lblReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblReclamo.setBounds(0, 72, 130, 20);
		contentPane.add(lblReclamo);
		
		tfReclamo = new JTextField();
		tfReclamo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfReclamo.setEditable(false);
		tfReclamo.setBounds(138, 72, 163, 20);
		contentPane.add(tfReclamo);
		tfReclamo.setColumns(10);
		
		btnBuscarReclamo = new GlossyButton("BUSCAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnBuscarReclamo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnBuscarReclamo.setIcon(new ImageIcon(GUIAltaPedidoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnBuscarReclamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarReclamoEntidad();
			}
		});
		btnBuscarReclamo.setBounds(307, 72, 110, 20);
		contentPane.add(btnBuscarReclamo);
		
		JLabel lblPiezas = new JLabel("Piezas");
		lblPiezas.setBorder(null);
		lblPiezas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezas.setBounds(0, 103, 130, 20);
		contentPane.add(lblPiezas);
		
		cbPiezas = new JComboBox();
		cbPiezas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cmbPieza = new DefaultComboBoxModel<String>(numeros_piezas);
		cbPiezas.setModel(cmbPieza);
		cbPiezas.setBounds(138, 103, 163, 20);
		contentPane.add(cbPiezas);
		
		btnAgregar = new GlossyButton("AGREGAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnAgregar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregar.setIcon(new ImageIcon(GUIAltaPedidoEntidad.class.getResource("/cliente/Resources/Icons/add.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar();
			}
		});
		btnAgregar.setBounds(307, 103, 110, 20);
		contentPane.add(btnAgregar);
		
		btnQuitar = new GlossyButton("QUITAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnQuitar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnQuitar.setIcon(new ImageIcon(GUIAltaPedidoEntidad.class.getResource("/cliente/Resources/Icons/delete.png")));
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quitar();
			}
		});
		btnQuitar.setBounds(307, 127, 110, 20);
		contentPane.add(btnQuitar);
		
		btnCancelar = new GlossyButton("CANCELAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCancelar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCancelar.setIcon(new ImageIcon(GUIAltaPedidoEntidad.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(66, 283, 120, 20);
		contentPane.add(btnCancelar);
		
		btnCrear = new GlossyButton("CREAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCrear.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCrear.setIcon(new ImageIcon(GUIAltaPedidoEntidad.class.getResource("/cliente/Resources/Icons/add.png")));
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crear();
			}
		});
		btnCrear.setBounds(252, 283, 120, 20);
		contentPane.add(btnCrear);
		
		lblNumeroPieza = new JLabel("Numero Pieza");
		lblNumeroPieza.setBorder(null);
		lblNumeroPieza.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroPieza.setBounds(0, 134, 130, 20);
		contentPane.add(lblNumeroPieza);
		
		tfNumero_Pieza = new JTextField();
		tfNumero_Pieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_Pieza.setColumns(10);
		tfNumero_Pieza.setBounds(138, 134, 163, 20);
		contentPane.add(tfNumero_Pieza);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBorder(null);
		lblProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblProveedor.setBounds(0, 165, 130, 20);
		contentPane.add(lblProveedor);
		
		cb_proveedor = new JComboBox();
		cb_proveedor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cmbProveedor = new DefaultComboBoxModel<String>(proveedores);
		cb_proveedor.setModel(cmbProveedor);
		cb_proveedor.setBounds(138, 165, 163, 20);
		contentPane.add(cb_proveedor);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBorder(null);
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setBounds(0, 196, 130, 20);
		contentPane.add(lblDescripcion);
		
		taDescripcion = new JTextArea(4, 31);
		taDescripcion.setLineWrap(true);
		taDescripcion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		taDescripcion.setBounds(138, 196, 256, 72);
		contentPane.add(taDescripcion);
		
		btn_clear_FSP = new JButton("");
		btn_clear_FSP.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fecha_solicitud_pedido.getDate()!=null)
					fecha_solicitud_pedido.setDate(null);
			}
		});
		btn_clear_FSP.setIcon(new ImageIcon(GUIAltaPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FSP.setBounds(307, 40, 25, 20);
		contentPane.add(btn_clear_FSP);
		contentPane.setVisible(true);
	}
		
	protected void crear() {
		if(tfNumero_Pedido.getText().isEmpty()||fecha_solicitud_pedido.getDate()==null||tfReclamo.getText().isEmpty()|| cbPiezas.getItemCount()<1){
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String fecha = format2.format(fecha_solicitud_pedido.getDate());
		    java.sql.Date fsp = new java.sql.Date(fecha_solicitud_pedido.getDate().getTime());
	
			if (mediador.nuevoPedido(tfNumero_Pedido.getText(),fsp,reclamo,piezas)){
				JOptionPane.showMessageDialog(null,"Pedido Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
				mediador.actualizarDatosGestion();
				dispose();
			}else{
				JOptionPane.showMessageDialog(null,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

	protected void agregar() {		
		if (tfNumero_Pieza.getText().isEmpty()|| cb_proveedor.getSelectedItem()==null){
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			
			String numero_pieza = tfNumero_Pieza.getText();
			
			if (!numeros_piezas.contains(numero_pieza)){
				ProveedorDTO proveedor = mediador.obtenerProveedor(cb_proveedor.getSelectedItem().toString());
				PiezaDTO pieza = new PiezaDTO(numero_pieza, taDescripcion.getText(), proveedor);
				piezas.add(pieza);
				numeros_piezas.add(numero_pieza);
				cmbPieza = new DefaultComboBoxModel<String>(numeros_piezas);
				cbPiezas.setModel(cmbPieza);
				cbPiezas.setSelectedIndex(-1);
				cb_proveedor.setSelectedIndex(0);
				tfNumero_Pieza.setText("");
				taDescripcion.setText("");
			}else{
				JOptionPane.showMessageDialog(this,"Ya existe la pieza.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
			}
				
		}
		
	}

	protected void quitar() {
		if (numeros_piezas.contains((String)cbPiezas.getSelectedItem())){
			numeros_piezas.remove((String)cbPiezas.getSelectedItem());
			for (int i=0; i<piezas.size();i++){
				if (piezas.elementAt(i).getNumero_pieza().equals((String)cbPiezas.getSelectedItem())){
					piezas.remove(i);
				}
			}
			cmbPieza = new DefaultComboBoxModel<String>(numeros_piezas);
			cbPiezas.setModel(cmbPieza);
			cbPiezas.setSelectedIndex(-1);					
		}
		
	}

	public void SetVisible(boolean visible){
	}

	public MediadorPedido getMedidador() {
		return mediador;
	}

	public void setMedidador(MediadorPedido medidador) {
		this.mediador = medidador;
	}

	public void setReclamo(ReclamoDTO reclamo) {
		this.reclamo = reclamo;
		tfReclamo.setText(" [ID: "+reclamo.getId()+"]");
	}
}