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
import java.text.SimpleDateFormat;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import cliente.Recursos.util.JPanel_Whit_Image;

import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.Reclamo_FabricaDTO;

public class VerReclamoFabrica extends JFrame {

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
	private Reclamo_FabricaDTO reclamo_fabrica;
	private Pedido_PiezaDTO pedido_piezaDTO;
	
	public VerReclamoFabrica(MediadorReclamoPiezas mediadorReclamoPiezas, Reclamo_FabricaDTO reclamo_fabrica) {
		this.mediadorReclamoPiezas = mediadorReclamoPiezas;
		this.reclamo_fabrica = reclamo_fabrica;
		this.pedido_piezaDTO = this.mediadorReclamoPiezas.obtenerPedido_Pieza(reclamo_fabrica.getPedido(),reclamo_fabrica.getPieza());
		initialize();
	}
	private void initialize(){
		setTitle("RECLAMO A FABRICA");
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
		ePMotivo.setBounds(10, 202, 654, 184);
		contentPane.add(ePMotivo);
		
		JLabel lblMotivoReclamo = new JLabel("MOTIVO DEL RECLAMO");
		lblMotivoReclamo.setBorder(null);
		lblMotivoReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotivoReclamo.setBounds(269, 166, 135, 25);
		contentPane.add(lblMotivoReclamo);
		
		JLabel lblNewLabel = new JLabel("FECHA RECLAMO");
		lblNewLabel.setBorder(null);
		lblNewLabel.setBounds(10, 10, 170, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNumeroPedido = new JLabel("NUMERO PEDIDO");
		lblNumeroPedido.setBorder(null);
		lblNumeroPedido.setBounds(10, 40, 170, 25);
		contentPane.add(lblNumeroPedido);
		
		JLabel lblNumeroPieza = new JLabel("NUMERO PIEZA");
		lblNumeroPieza.setBorder(null);
		lblNumeroPieza.setBounds(10, 70, 170, 25);
		contentPane.add(lblNumeroPieza);
		
		JLabel lblDescripcion = new JLabel("DESCRIPCION PIEZA");
		lblDescripcion.setBorder(null);
		lblDescripcion.setBounds(386, 10, 170, 23);
		contentPane.add(lblDescripcion);
		
		JLabel lblNumeroOrden = new JLabel("NUMERO ORDEN");
		lblNumeroOrden.setBorder(null);
		lblNumeroOrden.setBounds(10, 100, 170, 25);
		contentPane.add(lblNumeroOrden);
		
		JLabel lblFechaSolicitudFabrica = new JLabel("FECHA SOLICITUD FABRICA");
		lblFechaSolicitudFabrica.setBorder(null);
		lblFechaSolicitudFabrica.setBounds(10, 130, 170, 25);
		contentPane.add(lblFechaSolicitudFabrica);
		
		lbl_num_pedido = new JLabel("");
		lbl_num_pedido.setText(pedido_piezaDTO.getNumero_pedido());
		lbl_num_pedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lbl_num_pedido.setBounds(190, 40, 170, 25);
		contentPane.add(lbl_num_pedido);
		
		lbl_num_Pieza = new JLabel("");
		lbl_num_Pieza.setText(reclamo_fabrica.getPieza().getNumero_pieza());
		lbl_num_Pieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lbl_num_Pieza.setBounds(190, 70, 170, 25);
		contentPane.add(lbl_num_Pieza);
		
		lbl_desc_pieza = new JLabel("");
		lbl_desc_pieza.setText(reclamo_fabrica.getPieza().getDescripcion());
		lbl_desc_pieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lbl_desc_pieza.setBounds(386, 30, 278, 82);
		contentPane.add(lbl_desc_pieza);
		
		lbl_num_ot = new JLabel("");
		lbl_num_ot.setText(reclamo_fabrica.getPedido().getReclamo().getOrden().getNumero_orden());
		lbl_num_ot.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lbl_num_ot.setBounds(190, 100, 170, 25);
		contentPane.add(lbl_num_ot);
		
		lbl_fsf = new JLabel("");	
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date fsf = new java.sql.Date(pedido_piezaDTO.getFecha_solicitud_fabrica().getTime());
		lbl_fsf.setText(format2.format(fsf));
		lbl_fsf.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lbl_fsf.setBounds(190, 130, 170, 25);
		contentPane.add(lbl_fsf);
		
		lblFReclamo = new JLabel("");
		java.sql.Date fReclamo = new java.sql.Date(reclamo_fabrica.getFecha_reclamo_fabrica().getTime());
		lblFReclamo.setText(format2.format(fReclamo));
		lblFReclamo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblFReclamo.setBounds(190, 10, 170, 25);
		contentPane.add(lblFReclamo);

	}
}
