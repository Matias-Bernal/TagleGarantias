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
package cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import cliente.Recursos.Botones.ButtonType;
import cliente.Recursos.Botones.GlossyButton;
import cliente.Recursos.util.JPanel_Whit_Image;
import cliente.Recursos.util.Theme;
import cliente.Recursos.util.TransparentPanel;
import common.DTOs.Notificacion_ReclamoDTO;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Frame;

public class GUIMenu_Principal extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorPrincipal mediadorPrincipal;
	private JPanel contentPane;
	private JPanel panelNotificaciones;

	private JTable tablaNotificaciones;
	private JScrollPane scrollPaneTabla;
	private DefaultTableModel modelo;

	private Vector<Integer> anchos;
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas;

	private JButton btnReclamos_Piezas;
	private JButton btnReclamos;
	private JButton glsbtnReporteRapidoEntidad;
	private JButton glsbtnReporteRapidoAgente;
	private JButton btnPedidosAgentes;
	private JButton btnPedidosEntidades;
	
	public GUIMenu_Principal(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal= mediadorPrincipal;
		cargarDatos();
		initialize();
	}

	private void cargarDatos() {
		nombreColumnas = new Vector<String>();
		anchos = new Vector<Integer>();
		nombreColumnas.add("TIPO");
		anchos.add(150);
		nombreColumnas.add("DETALLES");
		anchos.add(447);
		datosTabla = new Vector<Vector<String>>();
	}

	private void initialize() {
		setTitle("USUARIO: "+mediadorPrincipal.getUsuario().getNombre_usuario().toString() +" [ID: "+mediadorPrincipal.getUsuario().getId().toString()+" ]");
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/tagle.ico")));
		setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, 1366, 750);
		setExtendedState(Frame.MAXIMIZED_BOTH);

		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		setContentPane(contentPane);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				int eleccion = JOptionPane.showConfirmDialog(null, "Desea salir?","Salir",0,0,new ImageIcon(GUILogin.class.getResource("/cliente/Resources/Icons/exit_1.png")));
				if ( eleccion == 0) {
					mediadorPrincipal.matarThreads();
					System.exit(0);
				}	
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		mnInicio.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/inicio.png")));
		menuBar.add(mnInicio);
		
		JMenuItem mntmDesconectar = new JMenuItem("Desconectar");
		mntmDesconectar.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/disconect.png")));
		mntmDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mediadorPrincipal.reiniciar();
			}
		});
		mnInicio.add(mntmDesconectar);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/exit.png")));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mediadorPrincipal.salir();
			}
		});
		mnInicio.add(mntmSalir);
		
		
		// MENU USUARIOS //
		JMenu mnUsuarios = new JMenu("Usuarios");
		mnUsuarios.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/usuarios.png")));
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmAltaUsuario = new JMenuItem("Alta Usuario");
		mntmAltaUsuario.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/add_users.png")));

		mntmAltaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.altaUsuario();
			}
		});
		mnUsuarios.add(mntmAltaUsuario);
		
		JMenuItem mntmGestionUsuario = new JMenuItem("Gestionar Usuarios");
		mntmGestionUsuario.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/edit_users.png")));
		mntmGestionUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.gestionarUsuario();
			}
		});
		mnUsuarios.add(mntmGestionUsuario);
		
		// MENU REGISTRANTES //
		JMenu mnRegistrantes = new JMenu("Registrantes");
		mnRegistrantes.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/registrantes.png")));
		menuBar.add(mnRegistrantes);
		
		JMenuItem mntmAltaRegistrante = new JMenuItem("Alta Registrante");
		mntmAltaRegistrante.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/add_registrante.png")));
		mntmAltaRegistrante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.altaRegistrante();
			}
		});
		mnRegistrantes.add(mntmAltaRegistrante);
		
		JMenuItem mntmGestionRegistrante = new JMenuItem("Gestionar Registrantes");
		mntmGestionRegistrante.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/edit_registrante.png")));
		mntmGestionRegistrante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.gestionarRegistrante();
			}
		});
		mnRegistrantes.add(mntmGestionRegistrante);
		
		JMenu mnReclamantes = new JMenu("Reclamantes");
		mnReclamantes.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/reclamante.png")));
		menuBar.add(mnReclamantes);
		
		JMenuItem mntmAltaReclamante = new JMenuItem("Alta Reclamante");
		mntmAltaReclamante.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/add_reclamante.png")));
		mntmAltaReclamante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.altaReclamante();
			}
		});
		mnReclamantes.add(mntmAltaReclamante);
		
		JMenuItem mntmGestionReclamante = new JMenuItem("Gestionar Reclamantes");
		mntmGestionReclamante.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/edit_reclamante.png")));
		mntmGestionReclamante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.gestionarReclamante();
			}
		});
		mnReclamantes.add(mntmGestionReclamante);
		
		JMenu mnVehiculos = new JMenu("Vehiculos");
		mnVehiculos.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/vehiculo.png")));
		menuBar.add(mnVehiculos);
		
		JMenuItem mntmAltaVehiculo = new JMenuItem("Alta Vehiculo");
		mntmAltaVehiculo.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/add_vehiculo.png")));
		mntmAltaVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.altaVehiculo();
			}
		});
		mnVehiculos.add(mntmAltaVehiculo);
		
		JMenuItem mntmGestionVehiculo = new JMenuItem("Gestionar Vehiculos");
		mntmGestionVehiculo.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/edit_vehiculo.png")));
		mntmGestionVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.gestionarVehiculo();
			}
		});
		mnVehiculos.add(mntmGestionVehiculo);
		
		JMenu mnOrden = new JMenu("Ordenes");
		mnOrden.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/ordenen.png")));
		menuBar.add(mnOrden);
		
		JMenuItem mntmAltaOrden = new JMenuItem("Alta Orden");
		mntmAltaOrden.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/add_orden.png")));
		mntmAltaOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.altaOrden();
			}
		});
		mnOrden.add(mntmAltaOrden);
		
		JMenuItem mntmGestionOrden = new JMenuItem("Gestion Orden");
		mntmGestionOrden.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/edit_orden.png")));
		mntmGestionOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.gestionarOrden();
			}
		});
		mnOrden.add(mntmGestionOrden);
		
		JMenu mnReclamos = new JMenu("Reclamos");
		mnReclamos.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/reclamo.png")));
		menuBar.add(mnReclamos);
		
		JMenuItem mntmAltaReclamoAgente = new JMenuItem("Alta Reclamo Agente");
		mntmAltaReclamoAgente.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/add_reclamo_agente.png")));
		mntmAltaReclamoAgente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.altaReclamoAgente();
			}
		});
		mnReclamos.add(mntmAltaReclamoAgente);
		
		JMenuItem mntmGestionReclamoAgente = new JMenuItem("Gestionar Reclamos Agente");
		mntmGestionReclamoAgente.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/eddit_reclamo_agente.png")));
		mntmGestionReclamoAgente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.gestionarReclamoAgente();
			}
		});
		
		JMenuItem mntmAltaReclamoEntidad = new JMenuItem("Alta Reclamo Entidad");
		mntmAltaReclamoEntidad.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/add_reclamo_entidad.png")));
		mntmAltaReclamoEntidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.altaReclamoEntidad();
			}
		});
		mnReclamos.add(mntmAltaReclamoEntidad);
		mnReclamos.add(mntmGestionReclamoAgente);
		
		JMenuItem mntmGestionarReclamosEntidad = new JMenuItem("Gestionar Reclamos Entidad");
		mntmGestionarReclamosEntidad.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/eddit_reclamo_entidad.png")));
		mntmGestionarReclamosEntidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.gestionarReclamoEntidad();
			}
		});
		mnReclamos.add(mntmGestionarReclamosEntidad);
		
		JMenu mnReportes = new JMenu("Reportes");
		mnReportes.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/tablas.png")));
		menuBar.add(mnReportes);
		
		JMenu mnReportesGestion = new JMenu("Reportes Gestion");
		mnReportesGestion.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/gestion1.png")));
		mnReportesGestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reportesGestion();
			}
		});
		mnReportes.add(mnReportesGestion);
		
		JMenuItem mntmPiezasLlegadas = new JMenuItem("Piezas Llegadas");
		mntmPiezasLlegadas.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/arrow_down.png")));
		mntmPiezasLlegadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reportePiezasLlegadas();
			}
		});
		mnReportesGestion.add(mntmPiezasLlegadas);
		
		JMenuItem mntmPiezasDevueltas = new JMenuItem("Piezas Devueltas");
		mntmPiezasDevueltas.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/arrow_up.png")));
		mntmPiezasDevueltas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reportePiezasDevueltas();
			}
		});
		mnReportesGestion.add(mntmPiezasDevueltas);
		
		JMenuItem mntmPiezasSinLlegar = new JMenuItem("Piezas Sin Llegar");
		mntmPiezasSinLlegar.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/wait_image.png")));
		mntmPiezasSinLlegar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reportePiezasSinLlegar();
			}
		});
		mnReportesGestion.add(mntmPiezasSinLlegar);
		
		JMenuItem mntmPiezasLlegadasSin = new JMenuItem("Piezas Llegadas Sin Turno");
		mntmPiezasLlegadasSin.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/sin_turno.png")));
		mntmPiezasLlegadasSin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reportePiezasLlegadasSinTurno();
			}
		});
		mnReportesGestion.add(mntmPiezasLlegadasSin);
		
		JMenuItem mntmOrdenSinSolicitud = new JMenuItem("Orden Sin Solicitud De Pedido");
		mntmOrdenSinSolicitud.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/orden_sin_sdp.png")));
		mntmOrdenSinSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reporteOrdenSinSDP();
			}
		});
		mnReportesGestion.add(mntmOrdenSinSolicitud);
		
		JMenuItem mntmSolicitudDePedido = new JMenuItem("Solicitud De Pedido Sin Numero De Pediddo");
		mntmSolicitudDePedido.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/sdp_sin_numero.png")));
		mntmSolicitudDePedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reporteSDPSinNP();
			}
		});
		mnReportesGestion.add(mntmSolicitudDePedido);
		
		JMenu mnReportesControl = new JMenu("Reportes Control");
		mnReportesControl.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/control.png")));
		mnReportesControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reportesControl();
			}
		});
		mnReportes.add(mnReportesControl);
		
		JMenuItem mntmDiasDesdePedido = new JMenuItem("Dias Desde Pedido Fabrica");
		mntmDiasDesdePedido.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/arrow_up.png")));
		mntmDiasDesdePedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reporteDiasDesdePedidoFabrica();
			}
		});
		mnReportesControl.add(mntmDiasDesdePedido);
		
		JMenuItem mntmDiasDesdeRecepcion = new JMenuItem("Dias Desde Recepcion Pedido Fabrica");
		mntmDiasDesdeRecepcion.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/arrow_down.png")));
		mntmDiasDesdeRecepcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reporteDiasDesdeRecepcionFabrica();
			}
		});
		mnReportesControl.add(mntmDiasDesdeRecepcion);
		
		JMenuItem mntmDiasDesdeRecepcion_1 = new JMenuItem("Dias Desde Recepcion de Fabrica y Fecha Turno");
		mntmDiasDesdeRecepcion_1.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/entidad.png")));
		mntmDiasDesdeRecepcion_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reporteDiasDesdeRecepcionFabricaYTurno();
			}
		});
		mnReportesControl.add(mntmDiasDesdeRecepcion_1);
		
		JMenuItem mntmDiasDesdeFecha = new JMenuItem("Dias Desde Fecha Cierre Orden y Fecha Turno");
		mntmDiasDesdeFecha.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/sin_turno.png")));
		mntmDiasDesdeFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reporteDiasDesdeCierreOrdenYTurno();
			}
		});
		mnReportesControl.add(mntmDiasDesdeFecha);
		
		JMenuItem mntmDiasDesdeFecha_1 = new JMenuItem("Dias Desde Fecha Recurso y Fecha Cierre Orden");
		mntmDiasDesdeFecha_1.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/orden_sin_sdp.png")));
		mntmDiasDesdeFecha_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reporteDiasFechaRecursoYCierreOrden();
			}
		});
		mnReportesControl.add(mntmDiasDesdeFecha_1);
		
		JMenuItem mntmDiasDesdeFecha_2 = new JMenuItem("Dias Desde Fecha Reclamo y Fecha Devolucion");
		mntmDiasDesdeFecha_2.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/reclamo.png")));
		mntmDiasDesdeFecha_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reporteDiasFechaReclamoYFechaDevolucion();
			}
		});
		mnReportesControl.add(mntmDiasDesdeFecha_2);
		
		JMenuItem mntmReclamosTurnos = new JMenuItem("Reclamos - Turnos");
		mntmReclamosTurnos.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/wait_image.png")));
		mntmReclamosTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reporteDiasFechaReclamo_Turnos();
			}
		});
		mnReportesControl.add(mntmReclamosTurnos);
		
		JMenuItem mntmPiezasLlegadas_1 = new JMenuItem("Piezas Llegadas - Piezas Devueltas");
		mntmPiezasLlegadas_1.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/llegadas_dev.png")));
		mntmPiezasLlegadas_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reporteDiasPiezasLlegadas_PiezasDevueltas();
			}
		});
		mnReportesControl.add(mntmPiezasLlegadas_1);
		
		JMenuItem mntmManoDeObra = new JMenuItem("Mano De Obra");
		mntmManoDeObra.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/mano_obra.png")));
		mntmManoDeObra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reporteManoDeObra();
			}
		});
		mnReportesControl.add(mntmManoDeObra);
		
		JMenuItem mntmRecursoCierre = new JMenuItem("Recurso - Cierre Orden");
		mntmRecursoCierre.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/ordenen.png")));
		mntmRecursoCierre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reporteRecurso_CierreOrden();
			}
		});
		mnReportesControl.add(mntmRecursoCierre);
		
		JMenu mnNotificaciones = new JMenu("Notificaciones");
		mnNotificaciones.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/notificaciones.png")));
		menuBar.add(mnNotificaciones);
		
		JMenuItem mntmModificarNotificacio = new JMenuItem("Modificar Notificaciones");
		mntmModificarNotificacio.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/edit_notificaciones.png")));
		mntmModificarNotificacio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.gestionarNotificaciones();
			}
		});
		mnNotificaciones.add(mntmModificarNotificacio);
		
		JMenuItem mntmActualizar = new JMenuItem("Actualizar");
		mntmActualizar.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/refresh.png")));
		mntmActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.actualizarNotificaciones();
			}
		});
		mnNotificaciones.add(mntmActualizar);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/help.png")));
		menuBar.add(mnAyuda);
		
		JMenuItem mntmManual = new JMenuItem("Manual");
		mntmManual.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/manual.png")));
		mntmManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.ayuda();
			}
		});
		mnAyuda.add(mntmManual);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de..");
		mntmAcercaDe.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/hm-about.png")));
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(contentPane,"IT10 Cooperativa - www.it10coop.com.ar","Acerca de..",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/it10.png")));
			}
		});
		mnAyuda.add(mntmAcercaDe);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{50, 75, 50, 75, 50, 878, 50, 0};
		gbl_contentPane.rowHeights = new int[]{50, 70, 50, 70, 50, 70, 61, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		glsbtnReporteRapidoEntidad = new GlossyButton("REPORTE RAPIDO",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_METALLICGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		glsbtnReporteRapidoEntidad.setHorizontalAlignment(SwingConstants.LEADING);
		glsbtnReporteRapidoEntidad.setFont(new Font("Arial Black", Font.BOLD, 14));
		glsbtnReporteRapidoEntidad.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/gestion.png")));
		glsbtnReporteRapidoEntidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reporteRapido();
			}
		});
		
		btnReclamos = new GlossyButton("RECLAMO RAPIDO",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_METALLICGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnReclamos.setHorizontalAlignment(SwingConstants.LEADING);
		btnReclamos.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnReclamos.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/reclamo_rapido.png")));
		btnReclamos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reclamos();
			}
		});
		
		JLabel lblNotificaciones = new JLabel("NOTIFICACIONES");
		lblNotificaciones.setForeground(Color.WHITE);
		lblNotificaciones.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNotificaciones.setBorder(null);
		lblNotificaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotificaciones.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNotificaciones.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		GridBagConstraints gbc_lblNotificaciones = new GridBagConstraints();
		gbc_lblNotificaciones.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNotificaciones.insets = new Insets(0, 0, 5, 5);
		gbc_lblNotificaciones.gridx = 5;
		gbc_lblNotificaciones.gridy = 0;
		contentPane.add(lblNotificaciones, gbc_lblNotificaciones);
		GridBagConstraints gbc_btnReclamos = new GridBagConstraints();
		gbc_btnReclamos.fill = GridBagConstraints.BOTH;
		gbc_btnReclamos.insets = new Insets(0, 0, 5, 5);
		gbc_btnReclamos.gridx = 1;
		gbc_btnReclamos.gridy = 1;
		contentPane.add(btnReclamos, gbc_btnReclamos);
		
		btnReclamos_Piezas = new GlossyButton("RECLAMOS DE PIEZAS",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_METALLICGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnReclamos_Piezas.setHorizontalAlignment(SwingConstants.LEADING);
		btnReclamos_Piezas.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/reclamospie.png")));
		btnReclamos_Piezas.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnReclamos_Piezas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.repuestos();	
			}
		});
		GridBagConstraints gbc_btnReclamos_Piezas = new GridBagConstraints();
		gbc_btnReclamos_Piezas.fill = GridBagConstraints.BOTH;
		gbc_btnReclamos_Piezas.insets = new Insets(0, 0, 5, 5);
		gbc_btnReclamos_Piezas.gridx = 3;
		gbc_btnReclamos_Piezas.gridy = 1;
		contentPane.add(btnReclamos_Piezas, gbc_btnReclamos_Piezas);
		
		panelNotificaciones = new TransparentPanel();
		panelNotificaciones.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		GridBagConstraints gbc_panelNotificaciones = new GridBagConstraints();
		gbc_panelNotificaciones.insets = new Insets(0, 0, 0, 5);
		gbc_panelNotificaciones.fill = GridBagConstraints.BOTH;
		gbc_panelNotificaciones.gridheight = 6;
		gbc_panelNotificaciones.gridx = 5;
		gbc_panelNotificaciones.gridy = 1;
		contentPane.add(panelNotificaciones, gbc_panelNotificaciones);
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		
		tablaNotificaciones = new JTable(modelo) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tablaNotificaciones.setGridColor(Color.BLACK);
		tablaNotificaciones.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaNotificaciones.getTableHeader().setReorderingAllowed(false);
		tablaNotificaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaNotificaciones.setBounds(0, 0, 665, 450);
		tablaNotificaciones.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		for(int i = 0; i < tablaNotificaciones.getColumnCount(); i++) {
			tablaNotificaciones.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaNotificaciones.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
		
		scrollPaneTabla = new JScrollPane(tablaNotificaciones);
		scrollPaneTabla.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneTabla.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneTabla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		tablaNotificaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2)
					verNotificacion();
			    else{
			    	e.consume();
			    }   
			}
		});
		panelNotificaciones.setLayout(new BorderLayout(0, 0));
		panelNotificaciones.add(scrollPaneTabla);
				
		btnPedidosEntidades = new GlossyButton("RECLAMOS ENTIDADES",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_METALLICGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnPedidosEntidades.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/edit_pedido_entidad.png")));
		btnPedidosEntidades.setHorizontalAlignment(SwingConstants.LEADING);
		btnPedidosEntidades.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnPedidosEntidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.gestionarPedidoEntidad();
			}
		});
		
		GridBagConstraints gbc_btnPedidosEntidades = new GridBagConstraints();
		gbc_btnPedidosEntidades.fill = GridBagConstraints.BOTH;
		gbc_btnPedidosEntidades.insets = new Insets(0, 0, 5, 5);
		gbc_btnPedidosEntidades.gridx = 1;
		gbc_btnPedidosEntidades.gridy = 3;
		contentPane.add(btnPedidosEntidades, gbc_btnPedidosEntidades);
		
		btnPedidosAgentes = new GlossyButton("RECLAMOS AGENTES",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_METALLICGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnPedidosAgentes.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/edit_pedido_agente.png")));
		btnPedidosAgentes.setHorizontalAlignment(SwingConstants.LEADING);
		btnPedidosAgentes.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnPedidosAgentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.gestionarPedidoAgente();
			}
		});

		GridBagConstraints gbc_btnPedidosAgentes = new GridBagConstraints();
		gbc_btnPedidosAgentes.fill = GridBagConstraints.BOTH;
		gbc_btnPedidosAgentes.insets = new Insets(0, 0, 5, 5);
		gbc_btnPedidosAgentes.gridx = 3;
		gbc_btnPedidosAgentes.gridy = 3;
		contentPane.add(btnPedidosAgentes, gbc_btnPedidosAgentes);
		glsbtnReporteRapidoEntidad.setHorizontalAlignment(SwingConstants.LEADING);
		glsbtnReporteRapidoEntidad.setFont(new Font("Arial Black", Font.BOLD, 14));
		
		GridBagConstraints gbc_glsbtnReporteRapidoEntidad = new GridBagConstraints();
		gbc_glsbtnReporteRapidoEntidad.fill = GridBagConstraints.BOTH;
		gbc_glsbtnReporteRapidoEntidad.insets = new Insets(0, 0, 5, 5);
		gbc_glsbtnReporteRapidoEntidad.gridx = 1;
		gbc_glsbtnReporteRapidoEntidad.gridy = 5;
		contentPane.add(glsbtnReporteRapidoEntidad, gbc_glsbtnReporteRapidoEntidad);
		
		glsbtnReporteRapidoAgente = new GlossyButton("REPORTE RAPIDO",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_METALLICGRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		glsbtnReporteRapidoAgente.setHorizontalAlignment(SwingConstants.LEADING);
		glsbtnReporteRapidoAgente.setFont(new Font("Arial Black", Font.BOLD, 14));
		glsbtnReporteRapidoAgente.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/control1.png")));
		glsbtnReporteRapidoAgente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.reporteRapidoAgetes();
			}
		});

		GridBagConstraints gbc_glsbtnReporteRapidoAgente = new GridBagConstraints();
		gbc_glsbtnReporteRapidoAgente.fill = GridBagConstraints.BOTH;
		gbc_glsbtnReporteRapidoAgente.insets = new Insets(0, 0, 5, 5);
		gbc_glsbtnReporteRapidoAgente.gridx = 3;
		gbc_glsbtnReporteRapidoAgente.gridy = 5;
		contentPane.add(glsbtnReporteRapidoAgente, gbc_glsbtnReporteRapidoAgente);
		
		contentPane.setVisible(true);
	}

	protected void verNotificacion() {
		int row = tablaNotificaciones.getSelectedRow();
		if (row >= 0) {
			mediadorPrincipal.verNotificacion(row);
		}else{
			JOptionPane.showMessageDialog(tablaNotificaciones,"Seleccione una notificacion primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public MediadorPrincipal getMediadorPrincipal() {
		return mediadorPrincipal;
	}

	public void setMediadorPrincipal(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}

	public void actualizarTabla(Vector<Notificacion_ReclamoDTO> notificaciones) {
		datosTabla = new Vector<Vector<String>>();
		for(int i =0; i<notificaciones.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(notificaciones.elementAt(i).getNotificacion().getTipo_notificacion());
			row.add(notificaciones.elementAt(i).getNotificacion().getTexto_notificacion());
			
			datosTabla.add(row);
		}
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
		for(int i = 0; i < tablaNotificaciones.getColumnCount(); i++) {
			tablaNotificaciones.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaNotificaciones.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
}
