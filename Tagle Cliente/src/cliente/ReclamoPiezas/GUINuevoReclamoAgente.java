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
package cliente.ReclamoPiezas;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
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

import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.UsuarioDTO;

public class GUINuevoReclamoAgente extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MediadorReclamoPiezas mediador;
	private Pedido_PiezaDTO pedido_pieza;
	
	private JPanel contentPane;
	private JButton btnEnviarReclamo;
	private JEditorPane ePMotivo;
	private JLabel lbl_num_pedido;
	private JLabel lbl_num_Pieza;
	private JLabel lbl_desc_pieza;
	private JLabel lbl_num_ot;
	private JLabel lbl_fsf;
	private JButton btnNewButton;
	protected GUIMailAgente guiMail;
	protected UsuarioDTO usuario;

	
	
	public GUINuevoReclamoAgente(MediadorReclamoPiezas mediador, Pedido_PiezaDTO pedido_pieza, UsuarioDTO usuario) {
		this.mediador = mediador;
		this.pedido_pieza = pedido_pieza;
		this.usuario = usuario;
		inicialize();
	}
	public void inicialize(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUINuevoReclamoAgente.class.getResource("/cliente/Resources/Icons/registrante_solo.png")));
		setResizable(false);
		setTitle("NUEVO RECLAMO A AGENTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 680, 459);
		contentPane = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnEnviarReclamo = new GlossyButton("ENVIAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnEnviarReclamo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnEnviarReclamo.setIcon(new ImageIcon(GUINuevoReclamoAgente.class.getResource("/cliente/Resources/Icons/1_mail_agente.png")));
		btnEnviarReclamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enviarFormulario();
			}
		});
		btnEnviarReclamo.setBounds(392, 400, 170, 20);
		contentPane.add(btnEnviarReclamo);
		
		ePMotivo = new JEditorPane();
		ePMotivo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		ePMotivo.setBounds(10, 205, 654, 184);
		contentPane.add(ePMotivo);
		
		JLabel lblMotivoReclamo = new JLabel("MOTIVO DEL RECLAMO");
		lblMotivoReclamo.setBorder(null);
		lblMotivoReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotivoReclamo.setBounds(269, 171, 135, 25);
		contentPane.add(lblMotivoReclamo);
		
		JLabel lblNewLabel = new JLabel("PEDIDO- PIEZA");
		lblNewLabel.setBorder(null);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(269, 0, 135, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNumeroPedido = new JLabel("NUMERO PEDIDO");
		lblNumeroPedido.setBorder(null);
		lblNumeroPedido.setBounds(10, 35, 170, 25);
		contentPane.add(lblNumeroPedido);
		
		JLabel lblNumeroPieza = new JLabel("NUMERO PIEZA");
		lblNumeroPieza.setBorder(null);
		lblNumeroPieza.setBounds(10, 65, 170, 25);
		contentPane.add(lblNumeroPieza);
		
		JLabel lblDescripcion = new JLabel("DESCRIPCION PIEZA");
		lblDescripcion.setBorder(null);
		lblDescripcion.setBounds(386, 34, 170, 23);
		contentPane.add(lblDescripcion);
		
		JLabel lblNumeroOrden = new JLabel("NUMERO ORDEN");
		lblNumeroOrden.setBorder(null);
		lblNumeroOrden.setBounds(10, 95, 170, 25);
		contentPane.add(lblNumeroOrden);
		
		JLabel lblFechaEnvioAgente = new JLabel("FECHA ENVIO AGENTE");
		lblFechaEnvioAgente.setBorder(null);
		lblFechaEnvioAgente.setBounds(10, 125, 170, 25);
		contentPane.add(lblFechaEnvioAgente);
		
		lbl_num_pedido = new JLabel("");
		lbl_num_pedido.setText(pedido_pieza.getNumero_pedido());
		lbl_num_pedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lbl_num_pedido.setBounds(190, 35, 170, 25);
		contentPane.add(lbl_num_pedido);
		
		lbl_num_Pieza = new JLabel("");
		lbl_num_Pieza.setText(pedido_pieza.getPieza().getNumero_pieza());
		lbl_num_Pieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lbl_num_Pieza.setBounds(190, 65, 170, 25);
		contentPane.add(lbl_num_Pieza);
		
		lbl_desc_pieza = new JLabel("");
		lbl_desc_pieza.setText(pedido_pieza.getPieza().getDescripcion());
		lbl_desc_pieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lbl_desc_pieza.setBounds(386, 68, 278, 82);
		contentPane.add(lbl_desc_pieza);
		
		lbl_num_ot = new JLabel("");
		lbl_num_ot.setText(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());
		lbl_num_ot.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lbl_num_ot.setBounds(190, 95, 170, 25);
		contentPane.add(lbl_num_ot);
		
		lbl_fsf = new JLabel("");
		lbl_fsf.setText(pedido_pieza.getPedido().getFecha_solicitud_pedido().toString());
		lbl_fsf.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lbl_fsf.setBounds(190, 125, 170, 25);
		contentPane.add(lbl_fsf);
		
		btnNewButton = new GlossyButton("GUARDAR SIN ENVIAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUEGREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnNewButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton.setIcon(new ImageIcon(GUINuevoReclamoAgente.class.getResource("/cliente/Resources/Icons/save.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				if(mediador.guardarReclamoAgente(pedido_pieza,ePMotivo.getText())){
					JOptionPane.showMessageDialog(contentPane,"Reclamo Guardado.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
					mediador.actualizarReclamosAgente();
					dispose();
				}else{
					JOptionPane.showMessageDialog(contentPane,"Error al Guardar Reclamo.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(111, 400, 170, 20);
		contentPane.add(btnNewButton);
	}

	public void guardarReclamo() {
		if(mediador.guardarReclamoAgente(pedido_pieza,ePMotivo.getText())){
			JOptionPane.showMessageDialog(contentPane,"Reclamo Guardado.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
			dispose();
			mediador.actualizarReclamosAgente();
		}else{
			JOptionPane.showMessageDialog(contentPane,"Error al Guardar Reclamo.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	protected void enviarFormulario() {
		guiMail = new GUIMailAgente(this, usuario);
		guiMail.setVisible(true);
	}
}
