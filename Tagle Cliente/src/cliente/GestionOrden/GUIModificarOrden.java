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
package cliente.GestionOrden;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import com.toedter.calendar.JDateChooser;

import common.DTOs.OrdenDTO;
import common.DTOs.RecursoDTO;

public class GUIModificarOrden extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private MediadorOrden mediador;
	private JDateChooser fecha_apertura;
	private JDateChooser fecha_cierre;
	private JTextField tfNumero_Orden;
	private JButton btnCancelar;
	private JButton btnModificar;

	private OrdenDTO orden;
	private RecursoDTO recurso;
	private JLabel lblFcierre;
	private JTextField tfNumeroRecurso;
	private JTextField tfEstado_Orden;
	private JDateChooser fecha_recurso;
	private JButton btn_clear_FA;
	private JButton btn_clear_FC;
	private JButton btn_clear_FR;
	
	public GUIModificarOrden(final MediadorOrden medidador, OrdenDTO orden) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIModificarOrden.class.getResource("/cliente/Resources/Icons/edit_orden.png")));
		this.orden = orden;
		this.mediador = medidador;
		initialize();
		tfNumero_Orden.setText(orden.getNumero_orden());
		fecha_apertura.setDate(orden.getFecha_apertura());
		if (orden.getFecha_cierre()!=null)
			fecha_cierre.setDate(orden.getFecha_cierre());
		tfEstado_Orden.setText(orden.getEstado());		
	}
	
	private void initialize() {
		setTitle("MODIFICAR ORDEN");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 453, 280);
		
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btn_clear_FA = new JButton("");
		btn_clear_FA.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(fecha_apertura.getDate()!=null)
					fecha_apertura.setDate(null);
			}
		});
		btn_clear_FA.setIcon(new ImageIcon(GUIModificarOrden.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FA.setBounds(342, 41, 25, 20);
		contentPane.add(btn_clear_FA);
		
		btn_clear_FC = new JButton("");
		btn_clear_FC.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fecha_cierre.getDate()!=null)
					fecha_cierre.setDate(null);
			}
		});
		btn_clear_FC.setIcon(new ImageIcon(GUIModificarOrden.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FC.setBounds(342, 72, 25, 20);
		contentPane.add(btn_clear_FC);
		
		btn_clear_FR = new JButton("");
		btn_clear_FR.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btn_clear_FR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fecha_recurso.getDate()!=null)
					fecha_recurso.setDate(null);
			}
		});
		btn_clear_FR.setIcon(new ImageIcon(GUIModificarOrden.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FR.setBounds(340, 164, 25, 20);
		contentPane.add(btn_clear_FR);

		tfNumeroRecurso = new JTextField();
		tfNumeroRecurso.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumeroRecurso.setColumns(10);
		tfNumeroRecurso.setBounds(167, 133, 256, 20);
		contentPane.add(tfNumeroRecurso);
		
		fecha_recurso = new JDateChooser();
		fecha_recurso.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		fecha_recurso.setBounds(167, 164, 163, 20);
		contentPane.add(fecha_recurso);

		if (orden.getRecurso()!=null){
			tfNumeroRecurso.setText(orden.getRecurso().getNumero_recurso());
			if(orden.getRecurso().getFecha_recurso()!=null)
				fecha_recurso.setDate(orden.getRecurso().getFecha_recurso());
		}
		
		JLabel lblNumeroOrden = new JLabel("Numero Orden");
		lblNumeroOrden.setBorder(null);
		lblNumeroOrden.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroOrden.setBounds(0, 10, 159, 20);
		contentPane.add(lblNumeroOrden);
		
		tfNumero_Orden = new JTextField();
		tfNumero_Orden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfNumero_Orden.setBounds(169, 10, 254, 20);
		contentPane.add(tfNumero_Orden);
		tfNumero_Orden.setColumns(10);
		
		JLabel lblFechaApertura = new JLabel("Fecha Apertura");
		lblFechaApertura.setBorder(null);
		lblFechaApertura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaApertura.setBounds(0, 41, 159, 20);
		contentPane.add(lblFechaApertura);
		
		fecha_apertura = new JDateChooser();
		fecha_apertura.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		fecha_apertura.setDate(new Date());
		fecha_apertura.setBounds(169, 41, 163, 20);
		contentPane.add(fecha_apertura);
		
		btnCancelar = new GlossyButton("CANCELAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_RED_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnCancelar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnCancelar.setIcon(new ImageIcon(GUIModificarOrden.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(69, 207, 120, 20);
		contentPane.add(btnCancelar);
		
		btnModificar = new GlossyButton("MODIFICAR",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnModificar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnModificar.setIcon(new ImageIcon(GUIModificarOrden.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarOrden();
			}
		});
		btnModificar.setBounds(258, 207, 120, 20);
		contentPane.add(btnModificar);
		
		lblFcierre = new JLabel("Fecha Cierre");
		lblFcierre.setBorder(null);
		lblFcierre.setHorizontalAlignment(SwingConstants.CENTER);
		lblFcierre.setBounds(0, 72, 159, 20);
		contentPane.add(lblFcierre);
		
		fecha_cierre = new JDateChooser();
		fecha_cierre.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		fecha_cierre.setBounds(169, 72, 163, 20);
		contentPane.add(fecha_cierre);
		
		JLabel lblEstado = new JLabel("Estado Orden");
		lblEstado.setBorder(null);
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(0, 102, 159, 20);
		contentPane.add(lblEstado);
		
		JLabel label = new JLabel("Numero Recurso");
		label.setBorder(null);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 133, 159, 20);
		contentPane.add(label);
		
		
		
		JLabel label_1 = new JLabel("Fecha Recurso");
		label_1.setBorder(null);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(0, 164, 159, 20);
		contentPane.add(label_1);
	
		tfEstado_Orden = new JTextField();
		tfEstado_Orden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfEstado_Orden.setEditable(false);
		tfEstado_Orden.setText((String) null);
		tfEstado_Orden.setColumns(10);
		tfEstado_Orden.setBounds(169, 103, 163, 20);
		contentPane.add(tfEstado_Orden);

		contentPane.setVisible(true);
	}
		

	@SuppressWarnings("unused")
	protected void modificarOrden(){
		if (tfNumero_Orden.getText().isEmpty() || fecha_apertura.getDate()==null){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{ // ORDEN Y FECHA APERTURA EXISTEN
			if ((fecha_cierre.getDate()!=null && (tfNumeroRecurso.getText().isEmpty() || fecha_recurso.getDate()==null))){
				//CON FECHA CIERRE PERO SIN RECURSO
				JOptionPane.showMessageDialog(contentPane,"Falta el Recurso. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
			}else{
				if (fecha_cierre.getDate()==null && !tfNumeroRecurso.getText().isEmpty() && fecha_recurso.getDate()!=null){
					//SOLO ACTUALIZA EL RECURSO
					
					orden.setNumero_orden(tfNumero_Orden.getText());
				
					SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
					String fecha2 = format2.format(fecha_apertura.getDate());
				    java.sql.Date fechaApertura = new java.sql.Date(fecha_apertura.getDate().getTime());
				    orden.setFecha_apertura(fechaApertura);
					
				    orden.setFecha_cierre(null);
					orden.setEstado("ABIERTA/CON RECURSO");
					
					String fecha = format2.format(fecha_recurso.getDate());
				    java.sql.Date fechaRecurso = new java.sql.Date(fecha_recurso.getDate().getTime());
					recurso = mediador.nuevoRecurso(tfNumeroRecurso.getText(),fechaRecurso);
					orden.setRecurso(recurso);					
										
					if (mediador.modificarOrden(orden)){
						JOptionPane.showMessageDialog(contentPane,"Orden Modificada.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
						mediador.actualizarDatosGestion();
						dispose();
					}else{
						JOptionPane.showMessageDialog(contentPane,"Error al modificar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
					}	
				}else{
					//TODO FALTA CHEQUEAR QUE SI TIENE PEDIDO TIENE QUE ESTAR DEVUELTA LA PIEZA.
					if(fecha_cierre.getDate()!=null && !tfNumeroRecurso.getText().isEmpty() && fecha_recurso.getDate()!=null){
						//ACTUALIZA EL RECURSO Y EL RECURSO
						orden.setNumero_orden(tfNumero_Orden.getText());
						
						SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
						String fecha = format2.format(fecha_apertura.getDate());
					    java.sql.Date fechaApertura = new java.sql.Date(fecha_apertura.getDate().getTime());
					    orden.setFecha_apertura(fechaApertura);
			    
						String fecha2 = format2.format(fecha_cierre.getDate());
						java.sql.Date fechacierre = new java.sql.Date(fecha_cierre.getDate().getTime());
						orden.setFecha_cierre(fechacierre);
						
						orden.setEstado("CERRADA");
						
						String fecha3 = format2.format(fecha_recurso.getDate());
					    java.sql.Date fechaRecurso = new java.sql.Date(fecha_recurso.getDate().getTime());
						recurso = mediador.nuevoRecurso(tfNumeroRecurso.getText(),fechaRecurso);
						orden.setRecurso(recurso);
	
						if (mediador.modificarOrden(orden)){
							JOptionPane.showMessageDialog(contentPane,"Orden Modificada.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
							mediador.actualizarDatosGestion();
							dispose();
						}else{
							JOptionPane.showMessageDialog(contentPane,"Error al modificar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
						}
					}else{
						orden.setNumero_orden(tfNumero_Orden.getText());
						
						SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
						String fecha = format2.format(fecha_apertura.getDate());
					    java.sql.Date fechaApertura = new java.sql.Date(fecha_apertura.getDate().getTime());
					    
					    orden.setFecha_apertura(fechaApertura);
					    orden.setFecha_cierre(null);
					    
					    if(mediador.obtenerReclamo(orden.getId()) != null){
					    	orden.setEstado("ABIERTA/SIN RECURSO");
					    }else{
					    	orden.setEstado("SIN RECLAMO");
					    }
					    boolean eliminarRecurso = orden.getRecurso()!=null;
					    Long id_viejoRecurso = null;
					    if(eliminarRecurso){
					    	id_viejoRecurso = orden.getRecurso().getId();
					    	orden.setRecurso(null);
					    }
						if (mediador.modificarOrden(orden)){
							if(eliminarRecurso)
								mediador.eliminarRecurso(id_viejoRecurso);
							JOptionPane.showMessageDialog(contentPane,"Orden Modificada.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
							mediador.actualizarDatosGestion();
							dispose();
						}else{
							JOptionPane.showMessageDialog(contentPane,"Error al modificar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
				}	
			}
		}
	}
	
	public OrdenDTO getOrden() {
		return orden;
	}

	public void setOrden(OrdenDTO orden) {
		this.orden = orden;
		tfNumero_Orden.setText(orden.getNumero_orden());
	}
}
