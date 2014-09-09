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
package cliente;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class GUILogin extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorPrincipal mediadorPrincipal;
	private JTextField tf_nombre_usuario;
	private JPasswordField pf_contrasenia;
	private int limite  = 35;
	private JPanel contentPane;

	public GUILogin(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
		initialize();
	}
	
	private void initialize() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUILogin.class.getResource("/cliente/Resources/Icons/login.png")));
		setTitle("LOGIN");
		setResizable(false);
		setMinimumSize(new Dimension(375, 225));
		setLocationRelativeTo(null);
		
		contentPane = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeUsuario.setBounds(109, 10, 150, 20);
		getContentPane().add(lblNombreDeUsuario);
		
		tf_nombre_usuario = new JTextField(limite);
		tf_nombre_usuario.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tf_nombre_usuario.setBounds(59, 35, 250, 20);
		tf_nombre_usuario.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tf_nombre_usuario.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					login();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		tf_nombre_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(tf_nombre_usuario);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setBounds(109, 65, 150, 20);
		getContentPane().add(lblContrasea);
		
		pf_contrasenia = new JPasswordField(limite);
		pf_contrasenia.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pf_contrasenia.setBounds(59, 89, 250, 20);
		pf_contrasenia.addKeyListener(new KeyListener() {
			@SuppressWarnings("deprecation")
			public void keyTyped(KeyEvent e) {
				if (pf_contrasenia.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					login();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		pf_contrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(pf_contrasenia);
		
		JButton btnAceptar = new GlossyButton("ACEPTAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME); 
		btnAceptar.setBounds(60, 145, 110, 25);
		contentPane.add(btnAceptar);
		btnAceptar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					login();
			}
		});
		btnAceptar.setIcon(new ImageIcon(GUILogin.class.getResource("/cliente/Resources/Icons/check.png")));
		btnAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton btnSalir = new GlossyButton("SALIR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME); 
		btnSalir.setBounds(200, 145, 110, 25);
		contentPane.add(btnSalir);
		btnSalir.setIcon(new ImageIcon(GUILogin.class.getResource("/cliente/Resources/Icons/exit_1.png")));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		setVisible(true);
		toFront();
	}
	
	public void login (){
		String usuario = tf_nombre_usuario.getText();
		@SuppressWarnings("deprecation")
		String contrasenia = pf_contrasenia.getText();
		if (usuario.isEmpty() || usuario=="" || contrasenia.isEmpty() || contrasenia == ""){
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUILogin.class.getResource("/cliente/Resources/Icons/informacion.png")));
		}else{
			try {
				if (mediadorPrincipal.acceso(usuario,contrasenia)) {
					setVisible(false);
				}else{
					JOptionPane.showMessageDialog(this,"Usuario/Contraseña no validos.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUILogin.class.getResource("/cliente/Resources/Icons/error.png")));
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this,"Error al iniciar sesion.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUILogin.class.getResource("/cliente/Resources/Icons/error.png")));
				e.printStackTrace();
			}
		}
	}
	
	public void reiniciar() {
		tf_nombre_usuario.setText("");
		pf_contrasenia.setText("");
		setVisible(true);
	}
}
