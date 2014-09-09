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
package cliente.GestionarVehiculo;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import cliente.Recursos.Botones.ButtonType;
import cliente.Recursos.Botones.GlossyButton;
import cliente.Recursos.util.JPanel_Whit_Image;
import cliente.Recursos.util.Theme;

import common.DTOs.MarcaDTO;
import common.DTOs.ModeloDTO;
import common.DTOs.VehiculoDTO;

public class GUIModificarVehiculo extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField tFnombre_titular;
	private JTextField tFDominio;
	private JTextField tFVin;
	
	private DefaultComboBoxModel<String> cmbMarcas;
	private DefaultComboBoxModel<String> cmbModelos;
	private JComboBox<String> comboBox_marca;
	private JComboBox<String> comboBox_modelo;
	private Vector<String> marcas;
	private Vector<String> modelos;
	
	private MediadorVehiculo mediador;
	private int limiteVin = 17;
	private int limiteDominio = 6;
	private VehiculoDTO vehiculo;


	public GUIModificarVehiculo(MediadorVehiculo mediadorVehiculo, VehiculoDTO vehiculo) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIModificarVehiculo.class.getResource("/cliente/Resources/Icons/edit_vehiculo.png")));
		this.mediador = mediadorVehiculo;
		this.vehiculo = vehiculo;
		cargarDatos();
		initialize();
		
		tFnombre_titular.setText(vehiculo.getNombre_titular());
		if (vehiculo.getDominio().length()<= 6)
			tFDominio.setText(vehiculo.getDominio());
		if(vehiculo.getVin().length()<=17)
			tFVin.setText(vehiculo.getVin());
		comboBox_marca.setSelectedItem(vehiculo.getMarca().getNombre_marca());
		comboBox_modelo.setSelectedItem(vehiculo.getModelo().getNombre_modelo());
		
		SetVisible(true);
	}

	private void initialize() {
		setResizable(false);
		setTitle("MODIFICAR VEHICULO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 410, 240);
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreTitular = new JLabel("Nombre del Titular");
		lblNombreTitular.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreTitular.setBounds(0, 10, 130, 20);
		contentPane.add(lblNombreTitular);
		
		tFnombre_titular = new JTextField();
		tFnombre_titular.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFnombre_titular.setBounds(135, 10, 250, 20);
		contentPane.add(tFnombre_titular);
		tFnombre_titular.setColumns(10);
		
		JLabel lblDominio = new JLabel("Dominio");
		lblDominio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDominio.setBounds(0, 40, 130, 20);
		contentPane.add(lblDominio);
		
		JLabel lblVIN = new JLabel("VIN");
		lblVIN.setHorizontalAlignment(SwingConstants.CENTER);
		lblVIN.setBounds(0, 70, 130, 20);
		contentPane.add(lblVIN);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
		lblModelo.setBounds(0, 131, 130, 20);
		contentPane.add(lblModelo);
		
		JButton btnModificar = new GlossyButton("MODIFICAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar.setIcon(new ImageIcon(GUIModificarVehiculo.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		btnModificar.setBounds(228, 169, 120, 20);
		contentPane.add(btnModificar);
		
		JButton btnCancelar = new GlossyButton("CANCELAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCancelar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCancelar.setIcon(new ImageIcon(GUIModificarVehiculo.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(54, 169, 120, 20);
		contentPane.add(btnCancelar);

		JLabel lblTipoMarca = new JLabel("Marca");
		lblTipoMarca.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoMarca.setBounds(0, 101, 130, 20);
		contentPane.add(lblTipoMarca);
		
		comboBox_modelo = new JComboBox<String>();
		comboBox_modelo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		comboBox_modelo.setModel(new DefaultComboBoxModel<String>(modelos));
		comboBox_modelo.setBounds(135, 101, 154, 20);
		contentPane.add(comboBox_modelo);
		
		tFDominio = new JTextField();
		tFDominio.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFDominio.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFDominio.getText().length()>=limiteDominio){
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
		tFDominio.setBounds(135, 40, 79, 20);
		contentPane.add(tFDominio);
		tFDominio.setColumns(10);
		
		tFVin = new JTextField();
		tFVin.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFVin.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFVin.getText().length()>=limiteVin){
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
		tFVin.setBounds(135, 71, 154, 20);
		contentPane.add(tFVin);
		tFVin.setColumns(10);
		
		comboBox_marca = new JComboBox<String>();
		comboBox_marca.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		comboBox_marca.setModel(new DefaultComboBoxModel<String>(marcas));
		comboBox_marca.setBounds(135, 131, 154, 20);
		contentPane.add(comboBox_marca);
	}
	
	
	protected void modificar() {
		if (tFnombre_titular.getText().isEmpty() || tFDominio.getText().isEmpty() || tFVin.getText().isEmpty() || comboBox_marca.getSelectedItem().toString() == "" || comboBox_modelo.getSelectedItem().toString() == "" ){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			vehiculo.setNombre_titular(tFnombre_titular.getText());
			vehiculo.setDominio(tFDominio.getText());
			vehiculo.setVin(tFVin.getText());
			MarcaDTO marca = new MarcaDTO(comboBox_marca.getSelectedItem().toString());
			ModeloDTO modelo = new ModeloDTO(comboBox_modelo.getSelectedItem().toString(),marca);
			vehiculo.setMarca(marca);
			vehiculo.setModelo(modelo);
			if (mediador.modificar(vehiculo)){ //TODO Arreglar el modificar no lo hace bien!!!
				mediador.actualizarDatosGestion();
				JOptionPane.showMessageDialog(contentPane,"Reclamante Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}else{
				JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void cargarDatos() {
		modelos = new Vector<String>();
		marcas = new Vector<String>();
		marcas.add("");
		Vector<String> nombre_marcas = mediador.obtenerMarcas();
		for (int i=0; i< nombre_marcas.size();i++){
			marcas.add(nombre_marcas.elementAt(i));
		}
		modelos = new Vector<String>();
		modelos.add("");
		Vector<String> nombre_modelos = mediador.obtenerModelos();
		for (int i=0; i< nombre_modelos.size();i++){
			modelos.add(nombre_modelos.elementAt(i));
		}
		
	}
	
	public void SetVisible(boolean visible){
		contentPane.setVisible(visible);
	}

	public DefaultComboBoxModel<String> getCmbModelos() {
		return cmbModelos;
	}

	public void setCmbModelos(DefaultComboBoxModel<String> cmbModelos) {
		this.cmbModelos = cmbModelos;
	}

	public DefaultComboBoxModel<String> getCmbMarcas() {
		return cmbMarcas;
	}

	public void setCmbMarcas(DefaultComboBoxModel<String> cmbMarcas) {
		this.cmbMarcas = cmbMarcas;
	}
}
