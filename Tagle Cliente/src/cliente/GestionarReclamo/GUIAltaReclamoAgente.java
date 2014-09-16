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

import common.DTOs.AgenteDTO;
import common.DTOs.OrdenDTO;
import common.DTOs.ReclamanteDTO;
import common.DTOs.VehiculoDTO;

public class GUIAltaReclamoAgente extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MediadorReclamo mediador;
	private JPanel contentPane;

	
	private JDateChooser dp_fecha_reclamo;
	private JTextField tfReclamo;
	private JTextField tfVehiculo;
	private JTextArea  tP_Descripcion;
	private JTextField tfOrden;
	
	private JButton btn_cancelar;
	private JButton btn_crear;
	private JLabel lblAgente;
	private JLabel lbl_Vehiculo;
		
	private int limite = 124;
	private JTextField tfAgente;
	private JButton btn_agente;
	
	private AgenteDTO agente;
	private VehiculoDTO vehiculo;
	private ReclamanteDTO reclamante;
	private OrdenDTO orden;
	private JButton btn_clear_FR;
	
	public GUIAltaReclamoAgente(final MediadorReclamo mediador, String reclamo, String agente, String descripcion) {
		this.mediador = mediador;
		initialize();
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public GUIAltaReclamoAgente(final MediadorReclamo mediador) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIAltaReclamoAgente.class.getResource("/cliente/Resources/Icons/add_reclamo_agente.png")));
		this.mediador = mediador;		
		initialize();
	}
	
	private void initialize() {
		setTitle("AGREGAR RECLAMO AGENTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 470, 325);
		
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblFechaReclamo = new JLabel("Fecha Reclamo");
		lblFechaReclamo.setBorder(null);
		lblFechaReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaReclamo.setBounds(0, 11, 130, 20);
		contentPane.add(lblFechaReclamo);
		
		dp_fecha_reclamo = new JDateChooser();
		dp_fecha_reclamo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dp_fecha_reclamo.setDate(new Date());
		dp_fecha_reclamo.setBounds(138, 11, 160, 20);
		contentPane.add(dp_fecha_reclamo);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBorder(null);
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setBounds(0, 170, 133, 20);
		contentPane.add(lblDescripcion);
		
		tfReclamo = new JTextField();
		tfReclamo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		tfReclamo.setEditable(false);
		tfReclamo.setBounds(138, 68, 190, 20);
		getContentPane().add(tfReclamo);
		tfReclamo.setColumns(10);
		
		JButton btn_buscar_reclamante = new GlossyButton("BUSCAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_buscar_reclamante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_buscar_reclamante.setIcon(new ImageIcon(GUIAltaReclamoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btn_buscar_reclamante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarReclamante();
			}
		});
		btn_buscar_reclamante.setBounds(334, 68, 110, 20);
		contentPane.add(btn_buscar_reclamante);
		
		btn_cancelar = new GlossyButton("CANCELAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_cancelar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_cancelar.setIcon(new ImageIcon(GUIAltaReclamoAgente.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_cancelar.setBounds(74, 265, 120, 20);
		contentPane.add(btn_cancelar);
		
		btn_crear = new GlossyButton("CREAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_crear.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_crear.setIcon(new ImageIcon(GUIAltaReclamoAgente.class.getResource("/cliente/Resources/Icons/add.png")));
		btn_crear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoReclamoAgente();
			}
		});
		btn_crear.setBounds(268, 265, 120, 20);
		contentPane.add(btn_crear);
		
		lblAgente = new JLabel("Agente");
		lblAgente.setBorder(null);
		lblAgente.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgente.setBounds(0, 42, 130, 20);
		contentPane.add(lblAgente);
		
		JLabel lblReclamante = new JLabel("Reclamante");
		lblReclamante.setBorder(null);
		lblReclamante.setHorizontalAlignment(SwingConstants.CENTER);
		lblReclamante.setBounds(0, 68, 130, 20);
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
					nuevoReclamoAgente();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {				
			}
		});
		tP_Descripcion.setLineWrap(true);
		tP_Descripcion.setBounds(140, 170, 250, 70);
		contentPane.add(tP_Descripcion);
		
		lbl_Vehiculo = new JLabel("Vehiculo");
		lbl_Vehiculo.setBorder(null);
		lbl_Vehiculo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Vehiculo.setBounds(0, 94, 130, 20);
		contentPane.add(lbl_Vehiculo);
		
		tfVehiculo = new JTextField();
		tfVehiculo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfVehiculo.setHorizontalAlignment(SwingConstants.CENTER);
		tfVehiculo.setEditable(false);
		tfVehiculo.setColumns(10);
		tfVehiculo.setBounds(138, 94, 190, 20);
		contentPane.add(tfVehiculo);
		
		JButton btn_buscar_Vehivulo = new GlossyButton("BUSCAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_buscar_Vehivulo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_buscar_Vehivulo.setIcon(new ImageIcon(GUIAltaReclamoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btn_buscar_Vehivulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarVehiculo();
			}
		});
		btn_buscar_Vehivulo.setBounds(334, 94, 110, 20);
		contentPane.add(btn_buscar_Vehivulo);
		
		tfAgente = new JTextField();
		tfAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfAgente.setHorizontalAlignment(SwingConstants.CENTER);
		tfAgente.setEditable(false);
		tfAgente.setColumns(10);
		tfAgente.setBounds(138, 42, 190, 20);
		contentPane.add(tfAgente);
		
		btn_agente = new GlossyButton("BUSCAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btn_agente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_agente.setIcon(new ImageIcon(GUIAltaReclamoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btn_agente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarAgente();
			}
		});
		btn_agente.setBounds(334, 42, 110, 20);
		contentPane.add(btn_agente);
		
		JLabel lblOrden = new JLabel("Numero Orden");
		lblOrden.setBorder(null);
		lblOrden.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrden.setBounds(0, 120, 130, 20);
		contentPane.add(lblOrden);
		
		tfOrden = new JTextField();
		tfOrden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfOrden.setHorizontalAlignment(SwingConstants.CENTER);
		tfOrden.setEditable(false);
		tfOrden.setColumns(10);
		tfOrden.setBounds(138, 121, 190, 20);
		contentPane.add(tfOrden);
		
		JButton btnOrden = new GlossyButton("BUSCAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnOrden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnOrden.setIcon(new ImageIcon(GUIAltaReclamoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.buscarOrden();
			}
		});
		btnOrden.setBounds(334, 121, 110, 20);
		contentPane.add(btnOrden);
		
		btn_clear_FR = new JButton("");
		btn_clear_FR.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dp_fecha_reclamo.getDate()!=null)
					dp_fecha_reclamo.setDate(null);
			}
		});
		btn_clear_FR.setIcon(new ImageIcon(GUIAltaReclamoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FR.setBounds(308, 10, 25, 20);
		contentPane.add(btn_clear_FR);
	
		contentPane.setVisible(true);
	}
	
	public void setVehiculo(VehiculoDTO vehiculo){
		this.vehiculo = vehiculo;
		tfVehiculo.setText(vehiculo.getDominio()+" [ID: "+vehiculo.getId()+"]");
	}
	public void setReclamante(ReclamanteDTO reclamante) {
		this.reclamante = reclamante;
		tfReclamo.setText(reclamante.getNombre_apellido()+" [ID: "+reclamante.getId()+"]");
	}
	public void setAgente(AgenteDTO agente) {
		this.agente = agente;
		tfAgente.setText(agente.getNombre_registrante()+" [ID: "+agente.getId()+"]");		
	
	}
	public void setOrden(OrdenDTO orden) {
		this.orden = orden;
		tfOrden.setText(orden.getNumero_orden()+" [ID: "+orden.getId()+"]");		
	}
	
	@SuppressWarnings("unused")
	protected void nuevoReclamoAgente() {
		if (agente == null || reclamante == null || vehiculo==null){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String fecha = format2.format(dp_fecha_reclamo.getDate());
		    java.sql.Date sqlDate = new java.sql.Date(dp_fecha_reclamo.getDate().getTime());
		    
			if (mediador.nuevoReclamoAgente(sqlDate, agente.getId(), reclamante.getId(), vehiculo.getId(),orden.getId(), tP_Descripcion.getText())){
				JOptionPane.showMessageDialog(contentPane,"Reclamo Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
				mediador.actualizarDatosGestion();
				dispose();
			}else{
				JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

}