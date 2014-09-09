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
package cliente.GestionarRegistrante;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cliente.Recursos.Botones.ButtonType;
import cliente.Recursos.Botones.GlossyButton;
import cliente.Recursos.util.JPanel_Whit_Image;
import cliente.Recursos.util.Theme;
import common.DTOs.AgenteDTO;
import common.DTOs.EntidadDTO;
import common.DTOs.RegistranteDTO;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

import java.awt.Color;

public class GUIModificarRegistrante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFnombre_usuario;
	private MediadorRegistrante mediador;
	private JComboBox<String> comboBox;
	private Vector<String> tiposRegistrantes;
	private int limite = 35;

	private RegistranteDTO registrante;
	
	/**
	 * @wbp.parser.constructor
	 */
	public GUIModificarRegistrante(final MediadorRegistrante mediadorRegistrante, AgenteDTO agente) {
		this.mediador = mediadorRegistrante;
		registrante = agente;
		setTitle("MODIFICAR REGISTRANTE");
		initialize();
		tFnombre_usuario.setText(registrante.getNombre_registrante());
		comboBox.setSelectedItem("Agente");
	}

	public GUIModificarRegistrante(final MediadorRegistrante mediadorRegistrante,	EntidadDTO entidad) {
		this.mediador = mediadorRegistrante;
		registrante = entidad;
		setTitle("MODIFICAR ENTIDAD");
		initialize();
		tFnombre_usuario.setText(registrante.getNombre_registrante());
		comboBox.setSelectedItem("Entidad");		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIModificarRegistrante.class.getResource("/cliente/Resources/Icons/edit_registrante.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 410, 210);
		setResizable(false);
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDelRegistrante = new JLabel("Nombre Del Registrante");
		lblNombreDelRegistrante.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDelRegistrante.setBounds(0, 10, 156, 20);
		contentPane.add(lblNombreDelRegistrante);
		
		tFnombre_usuario = new JTextField();
		tFnombre_usuario.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFnombre_usuario.setBounds(155, 10, 230, 20);
		tFnombre_usuario.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFnombre_usuario.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					modificar();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {				
			}
		});
		contentPane.add(tFnombre_usuario);
		tFnombre_usuario.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setBounds(0, 40, 156, 20);
		contentPane.add(lblTipo);
		
		JButton btnModificar = new GlossyButton("MODIFICAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar.setIcon(new ImageIcon(GUIModificarRegistrante.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		btnModificar.setBounds(228, 140, 120, 20);
		contentPane.add(btnModificar);
		
		JButton btnCancelar = new GlossyButton("CANCELAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCancelar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCancelar.setIcon(new ImageIcon(GUIModificarRegistrante.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(54, 140, 120, 20);
		contentPane.add(btnCancelar);
		
		
		tiposRegistrantes = new Vector<String>();
		tiposRegistrantes.add("");
		tiposRegistrantes.add("Agente");
		tiposRegistrantes.add("Entidad");
		
		comboBox = new JComboBox<String>();
		comboBox.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		comboBox.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel<String>(tiposRegistrantes));
		comboBox.setBounds(155, 41, 233, 20);
		contentPane.add(comboBox);
		
		contentPane.setVisible(true);
	
	}


	private void modificar() {
		if (tFnombre_usuario.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			registrante.setNombre_registrante(tFnombre_usuario.getText());
				if (mediador.esAgente(registrante.getId().toString())){
					if (mediador.modificarAgente((AgenteDTO)registrante)){
						JOptionPane.showMessageDialog(contentPane,"Agente Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
						mediador.actualizarDatosGestion();
						dispose();
					}else{
						JOptionPane.showMessageDialog(contentPane,"Error al modificar agente","Error",JOptionPane.ERROR_MESSAGE);
					}
				}else{
					if (mediador.modificarEntidad((EntidadDTO) registrante)){
						JOptionPane.showMessageDialog(contentPane,"Entidad Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
						mediador.actualizarDatosGestion();
						dispose();
					}else{
						JOptionPane.showMessageDialog(contentPane,"Error al modificar entidad","Error",JOptionPane.ERROR_MESSAGE);
					}
				}

		}
		
	}

}
