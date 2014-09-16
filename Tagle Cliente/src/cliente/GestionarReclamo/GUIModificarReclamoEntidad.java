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
package cliente.GestionarReclamo;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import common.DTOs.EntidadDTO;
import common.DTOs.OrdenDTO;
import common.DTOs.ReclamanteDTO;
import common.DTOs.ReclamoDTO;
import common.DTOs.RegistranteDTO;
import common.DTOs.VehiculoDTO;

public class GUIModificarReclamoEntidad extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MediadorReclamo mediador;
	private JPanel contentPane;

	
	private JDateChooser fecha_reclamo;
	private JTextField tfReclamante;
	private JTextField tfVehiculo;
	private JTextArea  tP_Descripcion;

	private JButton btnCancelar;
	private JButton btnModificar;
	private JLabel lblEntidad;
	private JLabel lbl_Vehiculo;
	private int limite = 124;
	private JTextField tfEntidad;
	private JButton btn_entidad;
	private JButton btnBuscarOrden;
	private JLabel lblNumeroOrden;
	private JTextField tfNumeroOrden;
	private JCheckBox chckbxInmovilizado;
	private JCheckBox chckbxPeligroso;
	
	private ReclamoDTO reclamo;
	private RegistranteDTO entidad;
	private VehiculoDTO vehiculo;
	private ReclamanteDTO reclamante;
	private OrdenDTO orden;
	private JButton btn_buscar_reclamante;
	private JButton btn_buscar_Vehivulo;
	private JDateChooser fecha_turno;
	private JButton btn_clear_FR;
	private JButton btn_clear_FT;
	private JDateChooser fecha_llamado_turno;

	private JButton btn_clear_fllamado_turno;
	
	public GUIModificarReclamoEntidad(final MediadorReclamo mediador, ReclamoDTO reclamo) {
		this.mediador = mediador;
		this.reclamo = reclamo;
		initialize();
		cargarDatos();
	}
	
	private void cargarDatos() {
		fecha_reclamo.setDate(reclamo.getFecha_reclamo());
		if (reclamo.getFecha_llamado_turno()!=null)
			fecha_llamado_turno.setDate(reclamo.getFecha_llamado_turno());
		if (reclamo.getFecha_turno()!=null)
			fecha_turno.setDate(reclamo.getFecha_turno());
		if(reclamo.getRegistrante()!=null){
			entidad = reclamo.getRegistrante();
			tfEntidad.setText(entidad.getNombre_registrante()+" [ID: "+entidad.getId()+"]");
		}
		if(reclamo.getReclamante()!=null){
			reclamante = reclamo.getReclamante();
			tfReclamante.setText(reclamante.getNombre_apellido()+" [ID: "+reclamante.getId()+"]");
		}
		if(reclamo.getVehiculo()!=null){
			vehiculo = reclamo.getVehiculo();
			tfVehiculo.setText(vehiculo.getDominio()+" [ID: "+vehiculo.getId()+"]");
		}
		if(reclamo.getOrden()!=null){
			orden = reclamo.getOrden();
			tfNumeroOrden.setText(orden.getNumero_orden()+" [ID: "+orden.getId()+"]");
		}
		chckbxInmovilizado.setSelected(reclamo.getInmovilizado());
		chckbxPeligroso.setSelected(reclamo.getPeligroso());
		
		tP_Descripcion.setText(reclamo.getDescripcion());
	}

	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIModificarReclamoEntidad.class.getResource("/cliente/Resources/Icons/eddit_reclamo_entidad.png")));
		setTitle("MODIFICAR RECLAMO ENTIDAD");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 470, 400);
		
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblFechaReclamo = new JLabel("Fecha Reclamo");
		lblFechaReclamo.setBorder(null);
		lblFechaReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaReclamo.setBounds(0, 10, 130, 20);
		contentPane.add(lblFechaReclamo);
		
		fecha_reclamo = new JDateChooser();
		fecha_reclamo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		fecha_reclamo.setDate(new Date());
		fecha_reclamo.setBounds(140, 10, 160, 20);
		contentPane.add(fecha_reclamo);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBorder(null);
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setBounds(0, 250, 130, 20);
		contentPane.add(lblDescripcion);
		
		tfReclamante = new JTextField();
		tfReclamante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfReclamante.setHorizontalAlignment(SwingConstants.CENTER);
		tfReclamante.setEditable(false);
		tfReclamante.setBounds(140, 130, 190, 20);
		getContentPane().add(tfReclamante);
		tfReclamante.setColumns(10);
		
		btn_buscar_reclamante = new GlossyButton("BUSCAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_buscar_reclamante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_buscar_reclamante.setIcon(new ImageIcon(GUIModificarReclamoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btn_buscar_reclamante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarReclamante();
			}
		});
		btn_buscar_reclamante.setBounds(335, 130, 110, 20);
		contentPane.add(btn_buscar_reclamante);
		
		btnCancelar = new GlossyButton("CANCELAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCancelar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCancelar.setIcon(new ImageIcon(GUIModificarReclamoEntidad.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(74, 330, 120, 20);
		contentPane.add(btnCancelar);
		
		btnModificar = new GlossyButton("MODIFICAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar.setIcon(new ImageIcon(GUIModificarReclamoEntidad.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarReclamoEntidad();
			}
		});
		btnModificar.setBounds(268, 330, 120, 20);
		contentPane.add(btnModificar);
		
		lblEntidad = new JLabel("Entidad");
		lblEntidad.setBorder(null);
		lblEntidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntidad.setBounds(0, 100, 130, 20);
		contentPane.add(lblEntidad);
		
		JLabel lblReclamante = new JLabel("Reclamante");
		lblReclamante.setBorder(null);
		lblReclamante.setHorizontalAlignment(SwingConstants.CENTER);
		lblReclamante.setBounds(0, 130, 130, 20);
		contentPane.add(lblReclamante);
		
		tP_Descripcion =  new JTextArea (4,31);
		tP_Descripcion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tP_Descripcion.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tP_Descripcion.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					modificarReclamoEntidad();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {		
			}
		});
		tP_Descripcion.setLineWrap(true);
		tP_Descripcion.setBounds(140, 250, 250, 70);
		contentPane.add(tP_Descripcion);
		
		lbl_Vehiculo = new JLabel("Vehiculo");
		lbl_Vehiculo.setBorder(null);
		lbl_Vehiculo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Vehiculo.setBounds(0, 160, 130, 20);
		contentPane.add(lbl_Vehiculo);
		
		tfVehiculo = new JTextField();
		tfVehiculo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfVehiculo.setHorizontalAlignment(SwingConstants.CENTER);
		tfVehiculo.setEditable(false);
		tfVehiculo.setColumns(10);
		tfVehiculo.setBounds(140, 160, 190, 20);
		contentPane.add(tfVehiculo);
		
		btn_buscar_Vehivulo = new GlossyButton("BUSCAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_buscar_Vehivulo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_buscar_Vehivulo.setIcon(new ImageIcon(GUIModificarReclamoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btn_buscar_Vehivulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarVehiculo();
			}
		});
		btn_buscar_Vehivulo.setBounds(335, 160, 110, 20);
		contentPane.add(btn_buscar_Vehivulo);
		
		chckbxPeligroso = new JCheckBox("Peligroso");
		chckbxPeligroso.setContentAreaFilled(false);
		chckbxPeligroso.setBorder(null);
		chckbxPeligroso.setBounds(140, 190, 100, 20);
		contentPane.add(chckbxPeligroso);
		
		chckbxInmovilizado = new JCheckBox("Inmovilizado");
		chckbxInmovilizado.setContentAreaFilled(false);
		chckbxInmovilizado.setBorder(null);
		chckbxInmovilizado.setBounds(250, 190, 100, 20);
		contentPane.add(chckbxInmovilizado);
		
		tfEntidad = new JTextField();
		tfEntidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfEntidad.setHorizontalAlignment(SwingConstants.CENTER);
		tfEntidad.setEditable(false);
		tfEntidad.setColumns(10);
		tfEntidad.setBounds(140, 100, 190, 20);
		contentPane.add(tfEntidad);
		
		btn_entidad = new GlossyButton("BUSCAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_entidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_entidad.setIcon(new ImageIcon(GUIModificarReclamoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btn_entidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarEntidad();
			}
		});
		btn_entidad.setBounds(335, 100, 110, 20);
		contentPane.add(btn_entidad);
		
		btnBuscarOrden = new GlossyButton("BUSCAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnBuscarOrden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnBuscarOrden.setIcon(new ImageIcon(GUIModificarReclamoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnBuscarOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarOrden();
			}
		});
		btnBuscarOrden.setBounds(335, 220, 110, 20);
		contentPane.add(btnBuscarOrden);
		
		lblNumeroOrden = new JLabel("Numero Orden");
		lblNumeroOrden.setBorder(null);
		lblNumeroOrden.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroOrden.setBounds(0, 220, 130, 20);
		contentPane.add(lblNumeroOrden);
		
		tfNumeroOrden = new JTextField();
		tfNumeroOrden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumeroOrden.setHorizontalAlignment(SwingConstants.CENTER);
		tfNumeroOrden.setEditable(false);
		tfNumeroOrden.setColumns(10);
		tfNumeroOrden.setBounds(140, 220, 190, 20);
		contentPane.add(tfNumeroOrden);
		
		JLabel lblFechaTurno = new JLabel("Fecha Turno");
		lblFechaTurno.setBorder(null);
		lblFechaTurno.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaTurno.setBounds(0, 70, 130, 20);
		contentPane.add(lblFechaTurno);
		
		fecha_turno = new JDateChooser();
		fecha_turno.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		fecha_turno.setBounds(138, 70, 160, 20);
		contentPane.add(fecha_turno);
		
		btn_clear_FR = new JButton("");
		btn_clear_FR.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fecha_reclamo.getDate()!=null)
					fecha_reclamo.setDate(null);
			}
		});
		btn_clear_FR.setIcon(new ImageIcon(GUIModificarReclamoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FR.setBounds(310, 10, 25, 20);
		contentPane.add(btn_clear_FR);
		
		btn_clear_FT = new JButton("");
		btn_clear_FT.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fecha_turno.getDate()!=null)
					fecha_turno.setDate(null);
			}
		});
		btn_clear_FT.setIcon(new ImageIcon(GUIModificarReclamoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FT.setBounds(310, 70, 25, 20);
		contentPane.add(btn_clear_FT);
		
		JLabel lblFechaLlamadaTurno = new JLabel("Fecha Llamado Turno");
		lblFechaLlamadaTurno.setBorder(null);
		lblFechaLlamadaTurno.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaLlamadaTurno.setBounds(0, 40, 130, 20);
		contentPane.add(lblFechaLlamadaTurno);
		
		fecha_llamado_turno = new JDateChooser();
		fecha_llamado_turno.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		fecha_llamado_turno.setBounds(140, 40, 160, 20);
		contentPane.add(fecha_llamado_turno);
		
		btn_clear_fllamado_turno = new JButton("");
		btn_clear_fllamado_turno.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_fllamado_turno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fecha_llamado_turno.getDate()!=null)
					fecha_llamado_turno.setDate(null);
			}
		});
		btn_clear_fllamado_turno.setIcon(new ImageIcon(GUIModificarReclamoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_fllamado_turno.setBounds(310, 40, 25, 20);
		contentPane.add(btn_clear_fllamado_turno);
		
		contentPane.setVisible(true);
	}
	
	public void setVehiculo(VehiculoDTO vehiculo){
		this.vehiculo = vehiculo;
		tfVehiculo.setText(vehiculo.getDominio()+" [ID: "+vehiculo.getId()+"]");
	}
	public void setReclamante(ReclamanteDTO reclamante) {
		this.reclamante = reclamante;
		tfReclamante.setText(reclamante.getNombre_apellido()+" [ID: "+reclamante.getId()+"]");
	}
	public void setEntidad(EntidadDTO entidad) {
		this.entidad = entidad;
		tfEntidad.setText(entidad.getNombre_registrante()+" [ID: "+entidad.getId()+"]");		
	
	}
	public void setOrden(OrdenDTO orden) {
		this.orden = orden;
		tfNumeroOrden.setText(orden.getNumero_orden()+" [ID: "+orden.getId()+"]");
	}
	

	protected void modificarReclamoEntidad() {
		if (entidad == null || reclamante == null || vehiculo==null || orden==null || fecha_reclamo.getDate()==null){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String fecha = format2.format(fecha_reclamo.getDate());
			java.sql.Date f_reclamo = new java.sql.Date(fecha_reclamo.getDate().getTime());
			reclamo.setFecha_reclamo(f_reclamo);
			
			if(fecha_llamado_turno.getDate()!=null){
				String fecha_llamado = format2.format(fecha_llamado_turno.getDate());
			    java.sql.Date f_llamado_turno= new java.sql.Date(fecha_llamado_turno.getDate().getTime());
				reclamo.setFecha_llamado_turno(f_llamado_turno);
			}else{
				reclamo.setFecha_llamado_turno(null);
			}
		    if (fecha_turno.getDate()!=null){
		    	fecha = format2.format(fecha_turno.getDate());
		    	java.sql.Date f_turno = new java.sql.Date(fecha_turno.getDate().getTime());
		    	reclamo.setFecha_turno(f_turno);
		    	if(mediador.tienePedido(reclamo)){
		    		reclamo.setEstado_reclamo("ABIERTO/CON PEDIDO/CON TURNO");
		    	}else{
		    		reclamo.setEstado_reclamo("ABIERTO/SIN PEDIDO/CON TURNO");
		    	}
		    }else{
		    	reclamo.setFecha_turno(null);
		    	if(mediador.tienePedido(reclamo)){
		    		reclamo.setEstado_reclamo("ABIERTO/CON PEDIDO/SIN TURNO");
		    	}else{
		    		reclamo.setEstado_reclamo("ABIERTO/SIN PEDIDO/SIN TURNO");
		    	}
		    }
		    reclamo.setRegistrante(entidad);
		    reclamo.setReclamante(reclamante);
		    reclamo.setVehiculo(vehiculo);
		    reclamo.setInmovilizado(chckbxInmovilizado.isSelected());
		    reclamo.setPeligroso(chckbxPeligroso.isSelected());
		    reclamo.setOrden(orden);
		    reclamo.setDescripcion(tP_Descripcion.getText());
		    
			if (mediador.modificarReclamoEntidad(reclamo)){
				JOptionPane.showMessageDialog(contentPane,"Reclamo Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
				mediador.actualizarDatosGestion();
				dispose();
			}else{
				JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
}