package servidor;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class GUIServidor extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmServidorTagle;
	private JTextField txtIp;
	private String ip;

	public GUIServidor() throws UnknownHostException {
		ip = InetAddress.getLocalHost().getHostAddress();
		initialize();
		frmServidorTagle.setVisible(true);
	}

	private void initialize() throws UnknownHostException {
		frmServidorTagle = new JFrame();

		frmServidorTagle.setResizable(false);
		frmServidorTagle.setTitle("SERVIDOR TAGLE");
		frmServidorTagle.setBounds(100, 100, 400, 250);
		frmServidorTagle.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frmServidorTagle.getContentPane().setLayout(null);
		frmServidorTagle.setLocationRelativeTo(null);


		JLabel lblIniciado = new JLabel("El servidor ha iniciado correctamente");
		lblIniciado.setFont(new Font("Consolas", Font.BOLD, 15));
		lblIniciado.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciado.setBounds(15, 11, 363, 36);
		frmServidorTagle.getContentPane().add(lblIniciado);

		JLabel lblEjecucionCliente = new JLabel(
				"Habilitado para ejecucion en red");
		lblEjecucionCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblEjecucionCliente.setFont(new Font("Consolas", Font.BOLD, 16));
		lblEjecucionCliente.setBounds(15, 58, 363, 36);
		frmServidorTagle.getContentPane().add(lblEjecucionCliente);

		JLabel lblcliente = new JLabel(
				"Ya se puede ejecutar el programa cliente");
		lblcliente.setFont(new Font("Consolas", Font.BOLD, 16));
		lblcliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblcliente.setBounds(15, 136, 363, 36);
		frmServidorTagle.getContentPane().add(lblcliente);

		JLabel lblIPservidor = new JLabel("IP:");
		lblIPservidor.setHorizontalAlignment(SwingConstants.CENTER);
		lblIPservidor.setFont(new Font("Consolas", Font.BOLD, 16));
		lblIPservidor.setBounds(82, 105, 45, 26);
		frmServidorTagle.getContentPane().add(lblIPservidor);

		txtIp = new JTextField();
		txtIp.setEditable(false);
		txtIp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				StringSelection copiar = new StringSelection(ip);
				Clipboard cp = Toolkit.getDefaultToolkit().getSystemClipboard();
				cp.setContents(copiar, null);
				JOptionPane.showMessageDialog(frmServidorTagle, "Copiado al portapapeles.", "Copiado...", JOptionPane.PLAIN_MESSAGE);
			}
		});
		txtIp.setText(ip);
		txtIp.setBounds(137, 105, 119, 26);
		frmServidorTagle.getContentPane().add(txtIp);
		txtIp.setColumns(10);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		btnSalir.setBounds(147, 183, 89, 23);
		frmServidorTagle.getContentPane().add(btnSalir);
	}
	
	public void salir(){
		if (JOptionPane.showConfirmDialog(null, "¿Salir?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){ 
			System.out.println("El servidor se ha apagado..");
			System.exit(0);
		}
	}
}
