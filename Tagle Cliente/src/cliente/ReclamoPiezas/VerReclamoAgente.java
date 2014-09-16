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
import java.text.SimpleDateFormat;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import cliente.Recursos.util.JPanel_Whit_Image;

import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.Reclamo_AgenteDTO;

public class VerReclamoAgente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JEditorPane ePMotivo;
	private JLabel lbl_num_pedido;
	private JLabel lbl_num_Pieza;
	private JLabel lbl_desc_pieza;
	private JLabel lbl_num_ot;
	private JLabel lbl_fsf;
	private JLabel lblFReclamo;
	private MediadorReclamoPiezas mediadorReclamoPiezas;
	private Reclamo_AgenteDTO reclamo_fabrica;
	private Pedido_PiezaDTO pedido_piezaDTO;
	private JLabel lblAgente;
	
	public VerReclamoAgente(MediadorReclamoPiezas mediadorReclamoPiezas, Reclamo_AgenteDTO reclamo_agente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VerReclamoAgente.class.getResource("/cliente/Resources/Icons/reclamos.png")));
		this.mediadorReclamoPiezas = mediadorReclamoPiezas;
		this.reclamo_fabrica = reclamo_agente;
		this.pedido_piezaDTO = this.mediadorReclamoPiezas.obtenerPedido_Pieza(reclamo_agente.getPedido(),reclamo_agente.getPieza());
		initialize();
	}
	private void initialize(){
		setTitle("RECLAMO A AGENTE");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 680, 425);
		contentPane = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ePMotivo = new JEditorPane();
		ePMotivo.setEditable(false);
		ePMotivo.setText(reclamo_fabrica.getDescripcion());
		ePMotivo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		ePMotivo.setBounds(10, 246, 654, 140);
		contentPane.add(ePMotivo);
		
		JLabel lblMotivoReclamo = new JLabel("MOTIVO DEL RECLAMO");
		lblMotivoReclamo.setBorder(null);
		lblMotivoReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotivoReclamo.setBounds(268, 199, 135, 25);
		contentPane.add(lblMotivoReclamo);
		
		JLabel lblNewLabel = new JLabel("FECHA RECLAMO");
		lblNewLabel.setBorder(null);
		lblNewLabel.setBounds(10, 43, 170, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNumeroPedido = new JLabel("NUMERO PEDIDO");
		lblNumeroPedido.setBorder(null);
		lblNumeroPedido.setBounds(10, 73, 170, 25);
		contentPane.add(lblNumeroPedido);
		
		JLabel lblNumeroPieza = new JLabel("NUMERO PIEZA");
		lblNumeroPieza.setBorder(null);
		lblNumeroPieza.setBounds(10, 103, 170, 25);
		contentPane.add(lblNumeroPieza);
		
		JLabel lblDescripcion = new JLabel("DESCRIPCION PIEZA");
		lblDescripcion.setBorder(null);
		lblDescripcion.setBounds(386, 43, 170, 23);
		contentPane.add(lblDescripcion);
		
		JLabel lblNumeroOrden = new JLabel("NUMERO ORDEN");
		lblNumeroOrden.setBorder(null);
		lblNumeroOrden.setBounds(10, 133, 170, 25);
		contentPane.add(lblNumeroOrden);
		
		JLabel lblFechaSolicitudFabrica = new JLabel("FECHA ENVIO AGENTE");
		lblFechaSolicitudFabrica.setBorder(null);
		lblFechaSolicitudFabrica.setBounds(10, 163, 170, 25);
		contentPane.add(lblFechaSolicitudFabrica);
		
		lbl_num_pedido = new JLabel("");
		lbl_num_pedido.setText(pedido_piezaDTO.getNumero_pedido());
		lbl_num_pedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lbl_num_pedido.setBounds(190, 73, 170, 25);
		contentPane.add(lbl_num_pedido);
		
		lbl_num_Pieza = new JLabel("");
		lbl_num_Pieza.setText(reclamo_fabrica.getPieza().getNumero_pieza());
		lbl_num_Pieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lbl_num_Pieza.setBounds(190, 103, 170, 25);
		contentPane.add(lbl_num_Pieza);
		
		lbl_desc_pieza = new JLabel("");
		lbl_desc_pieza.setText(reclamo_fabrica.getPieza().getDescripcion());
		lbl_desc_pieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lbl_desc_pieza.setBounds(386, 63, 278, 82);
		contentPane.add(lbl_desc_pieza);
		
		lbl_num_ot = new JLabel("");
		lbl_num_ot.setText(reclamo_fabrica.getPedido().getReclamo().getOrden().getNumero_orden());
		lbl_num_ot.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lbl_num_ot.setBounds(190, 133, 170, 25);
		contentPane.add(lbl_num_ot);
		
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		lbl_fsf = new JLabel("");
		if(pedido_piezaDTO.getFecha_envio_agente()!=null){
			java.sql.Date fsf = new java.sql.Date(pedido_piezaDTO.getFecha_envio_agente().getTime());
			lbl_fsf.setText(format2.format(fsf));
		}
		lbl_fsf.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lbl_fsf.setBounds(190, 163, 170, 25);
		contentPane.add(lbl_fsf);
		
		lblFReclamo = new JLabel("");
		java.sql.Date fReclamo = new java.sql.Date(reclamo_fabrica.getFecha_reclamo_agente().getTime());
		lblFReclamo.setText(format2.format(fReclamo));
		lblFReclamo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFReclamo.setBounds(190, 43, 170, 25);
		contentPane.add(lblFReclamo);
		
		lblAgente = new JLabel("");
		lblAgente.setText(reclamo_fabrica.getPedido().getReclamo().getRegistrante().getNombre_registrante());
		lblAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblAgente.setBounds(190, 11, 170, 25);
		contentPane.add(lblAgente);
		
		JLabel lblAgente_1 = new JLabel("AGENTE");
		lblAgente_1.setBorder(null);
		lblAgente_1.setBounds(10, 11, 170, 25);
		contentPane.add(lblAgente_1);

	}
}