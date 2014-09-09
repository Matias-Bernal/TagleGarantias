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

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import common.RootAndIp;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.UsuarioDTO;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class GUINuevoReclamoFabrica extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MediadorReclamoPiezas mediador;
	private UsuarioDTO usuario;
	private Pedido_PiezaDTO pedido_pieza;
	private String formulario;
	private GUIMailFabrica guiMail;
	
	private JPanel contentPane;
	private JButton btnCrearFormulario;
	private JButton btnEnviarFormulario;
	private JEditorPane ePMotivo;
	private JLabel lbl_num_pedido;
	private JLabel lbl_num_Pieza;
	private JLabel lbl_desc_pieza;
	private JLabel lbl_num_ot;
	private JLabel lbl_fsf;
	private JButton btnVerFormulario;
	private JButton btnNewButton;

	
	
	public GUINuevoReclamoFabrica(MediadorReclamoPiezas mediador, Pedido_PiezaDTO pedido_pieza, UsuarioDTO usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUINuevoReclamoFabrica.class.getResource("/cliente/Resources/Icons/entidad.png")));
		setResizable(false);
		this.mediador = mediador;
		this.pedido_pieza = pedido_pieza;
		this.usuario = usuario;
		inicialize();
	}
	public void inicialize(){
		setTitle("NUEVO RECLAMO A FABRICA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCrearFormulario = new JButton("Crear Formulario");
		btnCrearFormulario.setIcon(new ImageIcon(GUINuevoReclamoFabrica.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnCrearFormulario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				llenarFormulario();
			}
		});
		btnCrearFormulario.setBounds(15, 375, 165, 23);
		contentPane.add(btnCrearFormulario);
		
		btnEnviarFormulario = new JButton("Enviar Formulario");
		btnEnviarFormulario.setIcon(new ImageIcon(GUINuevoReclamoFabrica.class.getResource("/cliente/Resources/Icons/1_mail_fabrica.png")));
		btnEnviarFormulario.setEnabled(false);
		btnEnviarFormulario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enviarFormulario();
			}
		});
		btnEnviarFormulario.setBounds(529, 375, 155, 23);
		contentPane.add(btnEnviarFormulario);
		
		ePMotivo = new JEditorPane();
		ePMotivo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		ePMotivo.setBounds(15, 205, 669, 159);
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
		
		JLabel lblFechaSolicitudFabrica = new JLabel("FECHA SOLICITUD FABRICA");
		lblFechaSolicitudFabrica.setBounds(10, 125, 170, 25);
		contentPane.add(lblFechaSolicitudFabrica);
		
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
		lbl_desc_pieza.setBounds(386, 68, 298, 82);
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
		
		btnVerFormulario = new JButton("Ver Formulario");
		btnVerFormulario.setIcon(new ImageIcon(GUINuevoReclamoFabrica.class.getResource("/cliente/Resources/Icons/ver_formulario.png")));
    	btnVerFormulario.setEnabled(false);
		btnVerFormulario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(formulario==null){
					JOptionPane.showMessageDialog(contentPane,"Falta el formulario.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
				}else{
					try {
						File formulario_completado = new File(formulario);
						Desktop.getDesktop().open(formulario_completado);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnVerFormulario.setBounds(189, 375, 155, 23);
		contentPane.add(btnVerFormulario);
		
		btnNewButton = new JButton("Guardar Sin Enviar");
		btnNewButton.setIcon(new ImageIcon(GUINuevoReclamoFabrica.class.getResource("/cliente/Resources/Icons/save.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mediador.guardarReclamoFabrica(pedido_pieza,ePMotivo.getText())){
					JOptionPane.showMessageDialog(contentPane,"Reclamo Guardado.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
					mediador.actualizarReclamosFabrica();
					dispose();
				}else{
					JOptionPane.showMessageDialog(contentPane,"Error al Guardar Reclamo.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(354, 375, 165, 23);
		contentPane.add(btnNewButton);
	}

	protected void enviarFormulario() {
		if(formulario==null){
			JOptionPane.showMessageDialog(contentPane,"Falta el formulario.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			guiMail = new GUIMailFabrica(this, usuario, formulario);
			guiMail.setVisible(true);
		}
		
	}
	protected void llenarFormulario() {
        String archivo="Formulario_SRC.xls";
        Date hoy = new Date();
    	String nombre_archivo= pedido_pieza.getPedido().getReclamo().getReclamante().getNombre_apellido()+"_pedido_"+pedido_pieza.getNumero_pedido();
    	Boolean inmovilizado = pedido_pieza.getPedido().getReclamo().getInmovilizado();
    	String vin = pedido_pieza.getPedido().getReclamo().getVehiculo().getVin();
    	String dominio = pedido_pieza.getPedido().getReclamo().getVehiculo().getDominio();
    	String nombre_cliente = pedido_pieza.getPedido().getReclamo().getReclamante().getNombre_apellido();
    	String telefono = mediador.obtenerTelefono(pedido_pieza);
        try{
        	  		      
    		InputStream ExcelFileToRead = new FileInputStream("Formulario_SRC.xls");
    		
    		HSSFWorkbook  formulario_src = new HSSFWorkbook(ExcelFileToRead);
    		     		
    		HSSFSheet sheet = formulario_src.getSheetAt(0) ;

    		HSSFRow row;
    		HSSFCell cell;
    		//tipo reclamo
   			row = sheet.getRow(10);
   			cell = row.getCell(4);
   			cell.setCellValue("Repuestos");
   			//inmovilizado
   			if(inmovilizado){
   				row = sheet.getRow(11);
   	   			cell = row.getCell(4);
   	   			cell.setCellValue("Si");
   			}else{
   				row = sheet.getRow(11);
   	   			cell = row.getCell(4);
   	   			cell.setCellValue("No");
   			}
   			//garantia
			row = sheet.getRow(12);
   	   		cell = row.getCell(4);
   	   		cell.setCellValue("Si");
   			//Vin
   			row = sheet.getRow(15);
   			cell = row.getCell(4);
   			cell.setCellValue(vin);
   			//dominio
   			row = sheet.getRow(16);
   			cell = row.getCell(4);
   			cell.setCellValue(dominio);
   			//modelo
   			LinkedList<String> modelos = new LinkedList<String>();
   			modelos.add("FLUENCE");
   			modelos.add("CLIO");
   			modelos.add("KANGOO");
   			modelos.add("SYMBOL");
   			modelos.add("LOGAN");
   			modelos.add("SANDERO");
   			modelos.add("MASTER");
   			modelos.add("KOLEOS");
   			modelos.add("MEGANE III");
   			modelos.add("DUSTER");
   			modelos.add("LATITUDE");
   			if(modelos.contains(pedido_pieza.getPedido().getReclamo().getVehiculo().getModelo().getNombre_modelo().toUpperCase())){
   	   			row = sheet.getRow(17);
   	   			cell = row.getCell(4);
   	   			cell.setCellValue(pedido_pieza.getPedido().getReclamo().getVehiculo().getModelo().getNombre_modelo().toUpperCase());
   			}else{
   	   			row = sheet.getRow(17);
   	   			cell = row.getCell(4);
   	   			cell.setCellValue("OTROS");
   			}
   			//nombre cliente
   			row = sheet.getRow(11);
   			cell = row.getCell(8);
   			cell.setCellValue(nombre_cliente);
   			//telefono
   			row = sheet.getRow(12);
   			cell = row.getCell(8);
   			cell.setCellValue(telefono);
   			//motivo
   			row = sheet.getRow(21);
   			cell = row.getCell(2);
   			cell.setCellValue(ePMotivo.getText());
   			//pieza
   			row = sheet.getRow(33);
   			cell = row.getCell(3);
   			cell.setCellValue(pedido_pieza.getPieza().getNumero_pieza());
   			row = sheet.getRow(33);
   			cell = row.getCell(4);
   			cell.setCellValue(pedido_pieza.getPieza().getDescripcion());
   			row = sheet.getRow(33);
   			cell = row.getCell(7);
   			cell.setCellValue("1");
   			row = sheet.getRow(33);
   			cell = row.getCell(8);
   			cell.setCellValue(pedido_pieza.getNumero_pedido());
   			
    		//write this workbook to an Outputstream.
   			int i =0;
   			String aux = nombre_archivo;
   			File formulario_guardado = new File(RootAndIp.getPath_reportes()+aux+".xls");
   			while (formulario_guardado.exists()){
   				aux = nombre_archivo+"_"+i;
   				formulario_guardado = new File(RootAndIp.getPath_reportes()+aux+".xls");
   				i++;
   			}
   			
    		FileOutputStream fileOut = new FileOutputStream(formulario_guardado);
   			formulario_src.write(fileOut);
    		fileOut.flush();
    		fileOut.close();
    		
        	Desktop.getDesktop().open(formulario_guardado);
        	
        	formulario = formulario_guardado.getPath();
        	btnVerFormulario.setEnabled(true);
        	btnEnviarFormulario.setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void guardarReclamo() {
		if(mediador.guardarReclamoFabrica(pedido_pieza,ePMotivo.getText())){
			JOptionPane.showMessageDialog(contentPane,"Reclamo Guardado.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
			mediador.actualizarReclamosFabrica();
			dispose();
		}else{
			JOptionPane.showMessageDialog(contentPane,"Error al Guardar Reclamo.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}
