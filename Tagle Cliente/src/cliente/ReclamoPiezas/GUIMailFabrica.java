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
import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;

import common.RootAndIp;
import common.DTOs.UsuarioDTO;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class GUIMailFabrica extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private GUINuevoReclamoFabrica reclamo;
	private UsuarioDTO usuario;
	private JPanel contentPane;
	private JEditorPane epCuerpo;
	private JTextField tfFrom;
	private JTextField tfTo;
	private JTextField tfAsunto;
	private JTextField tfPathAdjunto;
	private JPasswordField pw_email;

	public GUIMailFabrica (GUINuevoReclamoFabrica reclamo, UsuarioDTO usuario, String path_formulario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIMailFabrica.class.getResource("/cliente/Resources/Icons/mail_fabrica.png")));
		setResizable(false);
		this.reclamo = reclamo;
		this.setUsuario(usuario);
		initialize();
		tfPathAdjunto.setText(path_formulario);
		tfFrom.setText(RootAndIp.getMailrepuestos());
		pw_email.setText(RootAndIp.getPassrepuestos());
		tfTo.setText(RootAndIp.getMailfabirca());
	}
	
	public void initialize(){
		setTitle("ENVIAR RECLAMO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 547, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(260, 10, 90, 20);
		contentPane.add(lblContrasea);
		
		pw_email = new JPasswordField();
		pw_email.setBounds(360, 10, 140, 20);
		contentPane.add(pw_email);
	
		epCuerpo = new JEditorPane();
		epCuerpo.setText("");
		//TODO aca agregar el cuerpo del mensaje
		epCuerpo.setBounds(0, 0, 106, 20);
		
		JScrollPane scrollPane = new JScrollPane(epCuerpo);
		scrollPane.setBounds(10, 138, 511, 159);
		contentPane.add(scrollPane);
		
		JLabel lblDe = new JLabel("De:");
		lblDe.setBounds(10, 10, 60, 20);
		contentPane.add(lblDe);
		
		JLabel lblA = new JLabel("A:");
		lblA.setBounds(10, 35, 60, 20);
		contentPane.add(lblA);
		
		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setBounds(10, 60, 60, 20);
		contentPane.add(lblAsunto);
		
		tfFrom = new JTextField();
		tfFrom.setBounds(80, 10, 140, 20);
		contentPane.add(tfFrom);
		tfFrom.setColumns(10);
		
		tfTo = new JTextField();
		tfTo.setText("");
		tfTo.setBounds(80, 35, 180, 20);
		contentPane.add(tfTo);
		tfTo.setColumns(10);
		
		tfAsunto = new JTextField();
		tfAsunto.setText("");
		//TODO aca agregar el asunto del mensaje
		tfAsunto.setBounds(80, 60, 250, 20);
		contentPane.add(tfAsunto);
		tfAsunto.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setIcon(new ImageIcon(GUIMailFabrica.class.getResource("/cliente/Resources/Icons/mail.png")));
		btnEnviar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(tfFrom.getText().isEmpty() || pw_email.getText().isEmpty() || tfTo.getText().isEmpty() || tfAsunto.getText().isEmpty() || epCuerpo.getText().isEmpty()){
					JOptionPane.showMessageDialog(contentPane,"Faltan campos..","Error",JOptionPane.ERROR_MESSAGE);
				}else{
					if(enviarMail()){
						JOptionPane.showMessageDialog(contentPane,"Formulario enviado.","Informacion",JOptionPane.INFORMATION_MESSAGE);
						reclamo.guardarReclamo();
						dispose();
					}else{
						JOptionPane.showMessageDialog(contentPane,"No se ha podido enviar..","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnEnviar.setBounds(330, 305, 110, 25);
		contentPane.add(btnEnviar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(GUIMailFabrica.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(100, 305, 110, 25);
		contentPane.add(btnCancelar);
		
		JLabel lblAdjunto = new JLabel("Adjunto:");
		lblAdjunto.setBounds(10, 85, 60, 20);
		contentPane.add(lblAdjunto);
		
		tfPathAdjunto = new JTextField();
		tfPathAdjunto.setEditable(false);
		tfPathAdjunto.setColumns(10);
		tfPathAdjunto.setBounds(80, 85, 180, 20);
		contentPane.add(tfPathAdjunto);
	}

    @SuppressWarnings("deprecation")
	public boolean enviarMail (){
        /* Se obtienen las propiedades del Sistema */
        Properties props = new Properties();
        props.put("mail.smtp.user", tfFrom.getText());
        props.put("mail.smtp.password", pw_email.getText());
        props.put("mail.smtp.host", RootAndIp.getSmtpHost());
        props.put("mail.smtp.port", RootAndIp.getSmtpserverport());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.checkserveridentity", "true");
        props.put("mail.smtp.connectiontimeout", 30000);
        props.put("mail.transport.protocol", "smtp");

        /*Se Obtiene una seesion con las propiedades anteririor mente definidas*/
        Session session = Session.getDefaultInstance(props,null);
        session.setDebug(false);
        try {
            /* Se crea el mensaje vacio */
            MimeMessage mensaje = new MimeMessage(session);
            
			String destinos[] = tfTo.getText().split(",");
			Address [] receptores = new Address [ destinos.length ];
			int j = 0;
			while(j<destinos.length){ 
				receptores[j] = new InternetAddress ( destinos[j] ) ; 
				j++; 
			}
			mensaje.addRecipients(Message.RecipientType.TO, receptores);

            
            mensaje.setFrom(new InternetAddress( tfFrom.getText() ));
			mensaje.setSubject(tfAsunto.getText() );

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(epCuerpo.getText());
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			
			 //Se adjuntan los archivos al correo
			if( tfPathAdjunto!=null && !tfPathAdjunto.getText().isEmpty() ){
			
				messageBodyPart = new MimeBodyPart();
				File f = new File(tfPathAdjunto.getText());
				if( f.exists() ){
					DataSource source = new FileDataSource( tfPathAdjunto.getText() );
					messageBodyPart.setDataHandler( new DataHandler(source) );
					messageBodyPart.setFileName( f.getName() );
					multipart.addBodyPart(messageBodyPart);
				}
			}
			mensaje.setContent(multipart);
            
            Transport t = session.getTransport("smtp");
            t.connect(RootAndIp.getSmtpHost(),tfFrom.getText(), pw_email.getText());
            t.sendMessage(mensaje,mensaje.getAllRecipients());
            t.close();
            return true;
        }
        catch (MessagingException e){
        	e.printStackTrace();
            System.err.println(e.getMessage());
            return false;
        }
    }

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

}
