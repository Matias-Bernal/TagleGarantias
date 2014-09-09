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
package cliente.GestionarNotificacion;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import cliente.Recursos.Botones.ButtonType;
import cliente.Recursos.Botones.GlossyButton;
import cliente.Recursos.util.JPanel_Whit_Image;
import cliente.Recursos.util.Theme;

import common.DTOs.UsuarioDTO;

public class GUIGestionNotificacion extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorNotificacion mediadorNotificacion;
	private JPanel contentPane;
	private UsuarioDTO usuario;
	
	private JCheckBox chTurnos;
	private JCheckBox chContencion;
	private JCheckBox chReclamoFabrica;
	private JCheckBox chSugerencias;
	private JCheckBox cbReclamoAgente;
	
	public GUIGestionNotificacion(MediadorNotificacion mediadorNotificacion, UsuarioDTO usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIGestionNotificacion.class.getResource("/cliente/Resources/Icons/edit_notificaciones.png")));
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 260);
		setMediadorNotificacion(mediadorNotificacion);
		setUsuario(usuario);
		setLocationRelativeTo(null);
		
		setTitle("MODIFICAR NOTIFICACIONES");
		
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
			
		JLabel lblNotificacionesAdministrativas = new JLabel("NOTIFICACIONES ADMINISTRATIVAS");
		lblNotificacionesAdministrativas.setBorder(null);
		lblNotificacionesAdministrativas.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotificacionesAdministrativas.setBounds(10, 11, 207, 20);
		contentPane.add(lblNotificacionesAdministrativas);
		
		JLabel lblNotificacionesRepuestos = new JLabel("NOTIFICACIONES AREA REPUESTOS");
		lblNotificacionesRepuestos.setBorder(null);
		lblNotificacionesRepuestos.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotificacionesRepuestos.setBounds(10, 92, 207, 20);
		contentPane.add(lblNotificacionesRepuestos);
		
		chTurnos = new JCheckBox("Gestion Turnos");
		chTurnos.setContentAreaFilled(false);
		chTurnos.setBorder(null);
		chTurnos.setBounds(109, 38, 207, 23);
		contentPane.add(chTurnos);
		
		chContencion = new JCheckBox("Contencion Cliente");
		chContencion.setContentAreaFilled(false);
		chContencion.setBorder(null);
		chContencion.setBounds(109, 64, 207, 23);
		contentPane.add(chContencion);
		
		chReclamoFabrica = new JCheckBox("Reclamos a Fabrica");
		chReclamoFabrica.setContentAreaFilled(false);
		chReclamoFabrica.setBorder(null);
		chReclamoFabrica.setBounds(109, 136, 207, 23);
		contentPane.add(chReclamoFabrica);
		
		chSugerencias = new JCheckBox("Sugerencias");
		chSugerencias.setContentAreaFilled(false);
		chSugerencias.setBorder(null);
		chSugerencias.setBounds(109, 162, 207, 23);
		contentPane.add(chSugerencias);
		
		JButton btnModificar = new GlossyButton("MODIFICAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar.setIcon(new ImageIcon(GUIGestionNotificacion.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarNotificaciones();
			}
		});
		btnModificar.setBounds(24, 192, 120, 23);
		contentPane.add(btnModificar);
		
		JButton btnCancelar = new GlossyButton("CANCELAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCancelar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCancelar.setIcon(new ImageIcon(GUIGestionNotificacion.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(168, 192, 120, 23);
		contentPane.add(btnCancelar);
		
		cbReclamoAgente = new JCheckBox("Reclamos a Agente");
		cbReclamoAgente.setContentAreaFilled(false);
		cbReclamoAgente.setBorder(null);
		cbReclamoAgente.setBounds(109, 110, 207, 23);
		contentPane.add(cbReclamoAgente);
		
		if (mediadorNotificacion.esAdministrativo(usuario)){
			chTurnos.setSelected(true);
			chContencion.setSelected(true);
		}else{
			chReclamoFabrica.setSelected(true);
			chSugerencias.setSelected(true);
		}
		
		contentPane.setVisible(true);
	}

	protected void actualizarNotificaciones() {
		if (mediadorNotificacion.setTiposNotificaciones(chTurnos.isSelected(),chContencion.isSelected(),cbReclamoAgente.isSelected(),chReclamoFabrica.isSelected(),chSugerencias.isSelected())){
			JOptionPane.showMessageDialog(this,"Notificaciones Modificadas.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}else{
			JOptionPane.showMessageDialog(this,"Error al modificar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public MediadorNotificacion getMediadorNotificacion() {
		return mediadorNotificacion;
	}

	public void setMediadorNotificacion(MediadorNotificacion mediadorNotificacion) {
		this.mediadorNotificacion = mediadorNotificacion;
	}
}
