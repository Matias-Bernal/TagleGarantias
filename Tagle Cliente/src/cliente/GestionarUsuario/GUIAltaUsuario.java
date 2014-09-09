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
package cliente.GestionarUsuario;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import cliente.Recursos.Botones.ButtonType;
import cliente.Recursos.Botones.GlossyButton;
import cliente.Recursos.util.JPanel_Whit_Image;
import cliente.Recursos.util.Theme;

public class GUIAltaUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFnombre_usuario;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirm;
	private JTextField tFemail;
	private MediadorUsuario medidador;
	private JComboBox<String> comboBox;
	private String[] tiposUsuarios;
	private int limite = 35;

	/**
	 * @wbp.parser.constructor
	 */
	public GUIAltaUsuario(final MediadorUsuario medidador, String nombre_usuario, String email, String tipo) {
		this.medidador = medidador;
		tiposUsuarios = new String[] {"Administrativo", "Encargado Repuesto"};
		initialize();
		if (nombre_usuario.length()>limite)
			nombre_usuario = nombre_usuario.substring(0, 36);
		tFnombre_usuario.setText(nombre_usuario);
		if (email.length()>limite)
			email = email.substring(0, 36);
		tFemail.setText(email);
		comboBox.setSelectedItem(tipo);		
	}
	
	public GUIAltaUsuario(final MediadorUsuario medidador) {
		this.medidador = medidador;
		tiposUsuarios = new String[] {"Administrativo", "Encargado Repuesto"};

		initialize();
	}
	
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIAltaUsuario.class.getResource("/cliente/Resources/Icons/add_users.png")));
		setTitle("AGREGAR USUARIO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 410, 240);
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre De Usuario");
		lblNombreDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeUsuario.setBounds(0, 10, 145, 20);
		contentPane.add(lblNombreDeUsuario);
		
		tFnombre_usuario = new JTextField();
		tFnombre_usuario.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFnombre_usuario.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFnombre_usuario.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					crear();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {				
			}
		});
		tFnombre_usuario.setBounds(145, 10, 250, 20);
		contentPane.add(tFnombre_usuario);
		tFnombre_usuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrasena");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setBounds(0, 40, 145, 20);
		contentPane.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		passwordField.addKeyListener(new KeyListener() {
			@SuppressWarnings("deprecation")
			public void keyTyped(KeyEvent e) {
				if (passwordField.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					crear();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {				
			}
		});
		passwordField.setBounds(145, 40, 250, 20);
		contentPane.add(passwordField);
		
		passwordFieldConfirm = new JPasswordField();
		passwordFieldConfirm.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		passwordFieldConfirm.addKeyListener(new KeyListener() {
			@SuppressWarnings("deprecation")
			public void keyTyped(KeyEvent e) {
				if (passwordFieldConfirm.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					crear();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {				
			}
		});
		passwordFieldConfirm.setBounds(145, 70, 250, 20);
		contentPane.add(passwordFieldConfirm);
		
		JLabel lblConfirmar = new JLabel("Confirmar Contrasena");
		lblConfirmar.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmar.setBounds(0, 70, 145, 20);
		contentPane.add(lblConfirmar);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(0, 100, 145, 20);
		contentPane.add(lblEmail);
		
		tFemail = new JTextField();
		tFemail.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFemail.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFemail.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					crear();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {				
			}
		});
		tFemail.setBounds(145, 100, 250, 20);
		contentPane.add(tFemail);
		tFemail.setColumns(10);
		
		JButton btnCrearUsuario = new GlossyButton("CREAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCrearUsuario.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCrearUsuario.setIcon(new ImageIcon(GUIAltaUsuario.class.getResource("/cliente/Resources/Icons/add.png")));
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crear();
			}
		});
		btnCrearUsuario.setBounds(228, 169, 120, 20);
		contentPane.add(btnCrearUsuario);
		
		JButton btnCancelar = new GlossyButton("CANCELAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCancelar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCancelar.setIcon(new ImageIcon(GUIAltaUsuario.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(54, 169, 120, 20);
		contentPane.add(btnCancelar);

		JLabel lblTipoUsuario = new JLabel("Tipo Usuario");
		lblTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoUsuario.setBounds(0, 131, 145, 20);
		contentPane.add(lblTipoUsuario);
		contentPane.setVisible(true);
		
		comboBox = new JComboBox<String>();
		comboBox.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		comboBox.setModel(new DefaultComboBoxModel<String>(tiposUsuarios));
		comboBox.setBounds(145, 131, 154, 20);
		contentPane.add(comboBox);
		
		contentPane.setVisible(true);
	}
		
	@SuppressWarnings("deprecation")
	private boolean chequearContrasenia() {
		return (passwordField.getText().equals(passwordFieldConfirm.getText()));
	}

	public String[] getTiposUsuarios() {
		return tiposUsuarios;
	}

	public void setTiposUsuarios(String[] tiposUsuarios) {
		this.tiposUsuarios = tiposUsuarios;
	}
	
	@SuppressWarnings("deprecation")
	public void crear(){
		if (tFnombre_usuario.getText().isEmpty() || passwordField.getText().isEmpty() || tFemail.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			if (chequearContrasenia()){
				if (medidador.nuevoUsuario(tFnombre_usuario.getText(), passwordField.getText(), tFemail.getText(),comboBox.getSelectedItem().toString())){
					JOptionPane.showMessageDialog(contentPane,"Usuario Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
					medidador.actualizarDatosGestion();
					dispose();
				}else{
					JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(contentPane,"Las contrase�as no coinciden.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
