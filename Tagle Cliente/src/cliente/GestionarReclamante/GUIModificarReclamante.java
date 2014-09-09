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
package cliente.GestionarReclamante;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cliente.Recursos.Botones.ButtonType;
import cliente.Recursos.Botones.GlossyButton;
import cliente.Recursos.util.JPanel_Whit_Image;
import cliente.Recursos.util.Theme;

import common.DTOs.ReclamanteDTO;

public class GUIModificarReclamante extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFnombre_reclamante;
	private MediadorReclamante mediador;
	private JTextField tFEmail;
	private JComboBox<String> comboBox_telefonos; 
	private DefaultComboBoxModel<String> comboBOX_Modelo;
	private Vector<String> telefonos;
	private JRadioButton rdbtnFijo;
	private JRadioButton rdbtnCelular;
	private JTextField tfDni;
	private int limite = 50;
	private int limiteDNI = 10;
	private ReclamanteDTO reclamante;

	public GUIModificarReclamante(MediadorReclamante mediadorReclamante, ReclamanteDTO reclamante, Vector<String> telefonos) {
		
		this.mediador = mediadorReclamante;
		this.reclamante = reclamante;
		this.telefonos = new Vector<String>();
		initialize();
		
		tFnombre_reclamante.setText(reclamante.getNombre_apellido());
		tFEmail.setText(reclamante.getEmail());
		tfDni.setText(reclamante.getDni());
		this.telefonos = telefonos;
		comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos);
		comboBox_telefonos.setModel(comboBOX_Modelo);
	}


	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIModificarReclamante.class.getResource("/cliente/Resources/Icons/edit_reclamante.png")));
		setTitle("MODIFICAR RECLAMANTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 270);
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreReclamante = new JLabel("Nombre Del Reclamante");
		lblNombreReclamante.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreReclamante.setBounds(5, 10, 170, 20);
		contentPane.add(lblNombreReclamante);
		
		tFnombre_reclamante = new JTextField();
		tFnombre_reclamante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFnombre_reclamante.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFnombre_reclamante.getText().length()>=limite){
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
		tFnombre_reclamante.setBounds(174, 10, 250, 20);
		contentPane.add(tFnombre_reclamante);
		tFnombre_reclamante.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(5, 41, 160, 20);
		contentPane.add(lblEmail);
		
		JButton btnModificar = new GlossyButton("MODIFICAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar.setIcon(new ImageIcon(GUIModificarReclamante.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		btnModificar.setBounds(282, 185, 120, 20);
		contentPane.add(btnModificar);
		
		JButton btnCancelar = new GlossyButton("CANCELAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCancelar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCancelar.setIcon(new ImageIcon(GUIModificarReclamante.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(81, 185, 120, 20);
		contentPane.add(btnCancelar);

		JLabel lblTelefonos = new JLabel("Telefonos");
		lblTelefonos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefonos.setBounds(5, 102, 170, 20);
		contentPane.add(lblTelefonos);
		contentPane.setVisible(true);
		
		comboBox_telefonos = new JComboBox<String>();
		comboBox_telefonos.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos);
		comboBox_telefonos.setModel(comboBOX_Modelo);
		comboBox_telefonos.setEditable(true);
		comboBox_telefonos.setBounds(174, 102, 150, 20);
		contentPane.add(comboBox_telefonos);
		
		JButton btnAgregar = new GlossyButton("AGREGAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnAgregar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnAgregar.setIcon(new ImageIcon(GUIModificarReclamante.class.getResource("/cliente/Resources/Icons/add.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nuevo_telefono = (String)comboBox_telefonos.getSelectedItem();
				if (!telefonos.contains(nuevo_telefono) && nuevo_telefono != null && nuevo_telefono!=""){
					if (rdbtnFijo.isSelected()){
						nuevo_telefono += " (Fijo)";
						telefonos.add(nuevo_telefono);
						DefaultComboBoxModel<String> comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos);
						comboBox_telefonos.setModel(comboBOX_Modelo);
						comboBox_telefonos.setSelectedIndex(-1);
						rdbtnFijo.setSelected(false);
					}else{
						if (rdbtnCelular.isSelected()){
							nuevo_telefono +=" (Celular)";
							telefonos.add(nuevo_telefono);
							DefaultComboBoxModel<String> comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos);
							comboBox_telefonos.setModel(comboBOX_Modelo);
							comboBox_telefonos.setSelectedIndex(-1);
							rdbtnCelular.setSelected(false);
						}else{
							JOptionPane.showMessageDialog(contentPane,"Seleccione el tipo de telefono.","Advertencia",JOptionPane.INFORMATION_MESSAGE);

						}
					}					
				}
			}
		});
		btnAgregar.setBounds(334, 99, 110, 23);
		contentPane.add(btnAgregar);
		
		JButton btnQuitar = new GlossyButton("QUITAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIGHTGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnQuitar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnQuitar.setIcon(new ImageIcon(GUIModificarReclamante.class.getResource("/cliente/Resources/Icons/delete.png")));
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (telefonos.contains((String)comboBox_telefonos.getSelectedItem())){
						telefonos.remove((String)comboBox_telefonos.getSelectedItem());
						DefaultComboBoxModel<String> comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos);
						comboBox_telefonos.setModel(comboBOX_Modelo);
						comboBox_telefonos.setSelectedIndex(-1);
						rdbtnFijo.setSelected(false);
						rdbtnCelular.setSelected(false);					
				}
			}
		});
		btnQuitar.setBounds(334, 125, 110, 23);
		contentPane.add(btnQuitar);
		
		tFEmail = new JTextField();
		tFEmail.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFEmail.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFEmail.getText().length()>=limite){
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
		tFEmail.setBounds(174, 40, 250, 20);
		contentPane.add(tFEmail);
		tFEmail.setColumns(10);
		
		rdbtnCelular = new JRadioButton("Celular");
		rdbtnCelular.setContentAreaFilled(false);
		rdbtnCelular.setBorder(null);
		rdbtnCelular.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnCelular.isSelected()){
					rdbtnFijo.setSelected(false);
				}
			}
		});

		
		rdbtnCelular.setBounds(174, 129, 150, 23);
		contentPane.add(rdbtnCelular);
		
		rdbtnFijo = new JRadioButton("Fijo");
		rdbtnFijo.setBorder(null);
		rdbtnFijo.setContentAreaFilled(false);
		rdbtnFijo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnFijo.isSelected()){
					rdbtnCelular.setSelected(false);
				}
			}
		});

		rdbtnFijo.setBounds(174, 155, 150, 23);
		contentPane.add(rdbtnFijo);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		lblDni.setBounds(5, 71, 170, 20);
		contentPane.add(lblDni);
		
		tfDni = new JTextField();
		tfDni.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfDni.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tfDni.getText().length()>=limiteDNI){
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
		tfDni.setColumns(10);
		tfDni.setBounds(174, 71, 150, 20);
		contentPane.add(tfDni);
		contentPane.setVisible(true);
	}
	
	private void modificar() {

		if (tFnombre_reclamante.getText().isEmpty() || tfDni.getText().isEmpty() || tFEmail.getText().isEmpty() || telefonos.isEmpty() ){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			reclamante.setNombre_apellido(tFnombre_reclamante.getText());
			reclamante.setDni(tfDni.getText());
			reclamante.setEmail(tFEmail.getText());
			if (mediador.modificar(reclamante,telefonos)){
				JOptionPane.showMessageDialog(contentPane,"Reclamante Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
				mediador.actualizarDatosGestion();
				dispose();
			}else{
				JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

}
