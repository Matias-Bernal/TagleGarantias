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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.UsuarioDTO;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUINuevoReclamoAgente.class.getResource("/cliente/Resources/Icons/registrante_solo.png")));
		setResizable(false);
		this.mediador = mediador;
		this.pedido_pieza = pedido_pieza;
		this.usuario = usuario;
		inicialize();
	}
	public void inicialize(){
		setTitle("NUEVO RECLAMO A AGENTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 680, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnEnviarReclamo = new JButton("Enviar Reclamo");
		btnEnviarReclamo.setIcon(new ImageIcon(GUINuevoReclamoAgente.class.getResource("/cliente/Resources/Icons/1_mail_agente.png")));
		btnEnviarReclamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enviarFormulario();
			}
		});
		btnEnviarReclamo.setBounds(422, 375, 170, 23);
		contentPane.add(btnEnviarReclamo);
		
		ePMotivo = new JEditorPane();
		ePMotivo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		ePMotivo.setBounds(15, 205, 644, 159);
		contentPane.add(ePMotivo);
		
		JLabel lblMotivoReclamo = new JLabel("MOTIVO DEL RECLAMO");
		lblMotivoReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotivoReclamo.setBounds(269, 171, 135, 25);
		contentPane.add(lblMotivoReclamo);
		
		JLabel lblNewLabel = new JLabel("PEDIDO- PIEZA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(269, 0, 135, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNumeroPedido = new JLabel("NUMERO PEDIDO");
		lblNumeroPedido.setBounds(10, 35, 170, 25);
		contentPane.add(lblNumeroPedido);
		
		JLabel lblNumeroPieza = new JLabel("NUMERO PIEZA");
		lblNumeroPieza.setBounds(10, 65, 170, 25);
		contentPane.add(lblNumeroPieza);
		
		JLabel lblDescripcion = new JLabel("DESCRIPCION PIEZA");
		lblDescripcion.setBounds(386, 34, 170, 23);
		contentPane.add(lblDescripcion);
		
		JLabel lblNumeroOrden = new JLabel("NUMERO ORDEN");
		lblNumeroOrden.setBounds(10, 95, 170, 25);
		contentPane.add(lblNumeroOrden);
		
		JLabel lblFechaEnvioAgente = new JLabel("FECHA ENVIO AGENTE");
		lblFechaEnvioAgente.setBounds(10, 125, 170, 25);
		contentPane.add(lblFechaEnvioAgente);
		
		lbl_num_pedido = new JLabel("");
		lbl_num_pedido.setText(pedido_pieza.getNumero_pedido());
		lbl_num_pedido.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lbl_num_pedido.setBounds(190, 35, 170, 25);
		contentPane.add(lbl_num_pedido);
		
		lbl_num_Pieza = new JLabel("");
		lbl_num_Pieza.setText(pedido_pieza.getPieza().getNumero_pieza());
		lbl_num_Pieza.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lbl_num_Pieza.setBounds(190, 65, 170, 25);
		contentPane.add(lbl_num_Pieza);
		
		lbl_desc_pieza = new JLabel("");
		lbl_desc_pieza.setText(pedido_pieza.getPieza().getDescripcion());
		lbl_desc_pieza.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lbl_desc_pieza.setBounds(386, 68, 268, 82);
		contentPane.add(lbl_desc_pieza);
		
		lbl_num_ot = new JLabel("");
		lbl_num_ot.setText(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());
		lbl_num_ot.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lbl_num_ot.setBounds(190, 95, 170, 25);
		contentPane.add(lbl_num_ot);
		
		lbl_fsf = new JLabel("");
		lbl_fsf.setText(pedido_pieza.getPedido().getFecha_solicitud_pedido().toString());
		lbl_fsf.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lbl_fsf.setBounds(190, 125, 170, 25);
		contentPane.add(lbl_fsf);
		
		btnNewButton = new JButton("Guardar Sin Enviar");
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
		btnNewButton.setBounds(82, 375, 170, 23);
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
