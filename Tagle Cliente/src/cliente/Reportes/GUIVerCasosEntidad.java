package cliente.Reportes;

import java.awt.Color;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cliente.Recursos.Botones.ButtonType;
import cliente.Recursos.Botones.GlossyButton;
import cliente.Recursos.util.JPanel_Whit_Image;
import cliente.Recursos.util.Theme;
import cliente.Recursos.util.TransparentPanel;
import cliente.excellexport.ExportarExcel;

import common.DTOs.Pedido_PiezaDTO;

public class GUIVerCasosEntidad extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private String titulo;
	private Vector<Pedido_PiezaDTO> pedidos_piezas;
	private Date FDesde;
	private Date FHasta;
	
	private JButton btnImprimir;
	private JButton btnExportar;
	private JButton btnActualizar;
	private JButton btnVolver;
	private TextField dCFReclamo;
	private TextField dCFDevolucion;
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas;
	private DefaultTableModel modelo;
	private JTable tablaPedidos;
	private Vector<Integer> anchos;	
	
	
	
	public GUIVerCasosEntidad(MediadorReportes mediador, Vector<Pedido_PiezaDTO> pedidos_piezas, String titulo, Date FDesde, Date FHasta ) {
		this.setMediador(mediador);
		this.pedidos_piezas = pedidos_piezas;
		this.titulo = titulo;
		this.FDesde = FDesde;
		this.FHasta = FHasta;
		cargarDatos();
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIVerCasosEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		setBounds(100, 100, 1280, 720);
		setTitle(titulo);
		setResizable(false);
		contentPane = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		tablaPedidos = new JTable(modelo) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false, false, false,
					false, false, false, false, false,
					false, false, false, false, false,
					false, false, false, false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablaPedidos.setRowSorter(ordenador);
		//
		tablaPedidos.getTableHeader().setReorderingAllowed(false);
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
		tablaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPedidos.setBounds(0, 0, 765, 320);
		tablaPedidos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JScrollPane scrollPane =  new JScrollPane(tablaPedidos);
		scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 54, 1274, 601);
		contentPane.add(scrollPane);
		
		btnVolver = new GlossyButton("VOLVER",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUEGREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnVolver.setIcon(new ImageIcon(GUIVerCasosEntidad.class.getResource("/cliente/Resources/Icons/back.png")));
		btnVolver.setBounds(562, 665, 150, 23);
		contentPane.add(btnVolver);
		
		JPanel panel = new TransparentPanel();
		panel.setBounds(1124, 11, 150, 32);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnImprimir = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_METALIC_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tablaPedidos.print();
				} catch (PrinterException ex) {
					JOptionPane.showMessageDialog(contentPane,"Error al imprimir.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnImprimir.setBounds(13, 0, 32, 32);
		panel.add(btnImprimir);
		btnImprimir.setIcon(new ImageIcon(GUIVerCasosEntidad.class.getResource("/cliente/Resources/Icons/printer.png")));
		
		btnExportar = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GRAY_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablas();
			}
		});
		btnExportar.setIcon(new ImageIcon(GUIVerCasosEntidad.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportar.setBounds(58, 0, 32, 32);
		panel.add(btnExportar);
		
		btnActualizar = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		btnActualizar.setIcon(new ImageIcon(GUIVerCasosEntidad.class.getResource("/cliente/Resources/Icons/1refresh.png")));
		btnActualizar.setBounds(103, 0, 32, 32);
		panel.add(btnActualizar);
		
		TransparentPanel transparentPanel = new TransparentPanel();
		transparentPanel.setLayout(null);
		transparentPanel.setBounds(322, 10, 630, 40);
		contentPane.add(transparentPanel);
		
		JLabel lblDesdeFPedido = new JLabel("DESDE F. PEDIDO");
		lblDesdeFPedido.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDesdeFPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesdeFPedido.setBounds(10, 10, 130, 20);
		transparentPanel.add(lblDesdeFPedido);
		
		dCFReclamo = new TextField();
		dCFReclamo.setEditable(false);
		if(FDesde!=null)
			dCFReclamo.setText(FDesde.toString());
		dCFReclamo.setBounds(140, 10, 150, 20);
		transparentPanel.add(dCFReclamo);
		
		JLabel label_1 = new JLabel("HASTA F. DEVOLUCION");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(335, 10, 130, 20);
		transparentPanel.add(label_1);
		
		dCFDevolucion = new TextField();
		dCFDevolucion.setEditable(false);
		if(FHasta!=null)
			dCFDevolucion.setText(FHasta.toString());
		dCFDevolucion.setBounds(465, 10, 150, 20);
		transparentPanel.add(dCFDevolucion);
		
		setVisible(true);
	}


	protected void actualizarDatos() {
		datosTabla = new Vector<Vector<String>>();
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		if(pedidos_piezas!=null){
			for (int i=0; i< pedidos_piezas.size();i++){
				Pedido_PiezaDTO pedido_pieza = pedidos_piezas.elementAt(i);
				Vector<String> row = new Vector<String> ();
				row.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
				row.add(pedido_pieza.getEstado_pedido());//Estado Pedido
				row.add(pedido_pieza.getNumero_pedido());//Numero Pedido
				row.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//nombre entidad
				if (pedido_pieza.getPedido().getReclamo().getOrden()!=null)
					row.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
				else
					row.add("");
				row.add(pedido_pieza.getPedido().getReclamo().getReclamante().getNombre_apellido());//Nombre Reclamante	
				if (pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo()!=null){
					row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getNombre_titular());//Nombre Titular
					row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getDominio());//Dominio
					row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
				}else{
					row.add("");
					row.add("");
					row.add("");
				}
				if (pedido_pieza.getPieza()!=null)
					row.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
				else
					row.add("");
				if (pedido_pieza.getPedido().getReclamo().getFecha_reclamo()!=null){
					java.sql.Date fr = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getFecha_reclamo().getTime());
					row.add(format2.format(fr));//Fecha Reclamo
				}else
					row.add("");
				if (pedido_pieza.getPedido().getFecha_solicitud_pedido()!=null){
					java.sql.Date fsp = new java.sql.Date(pedido_pieza.getPedido().getFecha_solicitud_pedido().getTime());
					row.add(format2.format(fsp));//Fecha Solicitud Pedido					
				}else
					row.add("");
				if (pedido_pieza.getPedido().getReclamo().getFecha_turno()!=null){
					java.sql.Date ft = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno().getTime());
					row.add(format2.format(ft));//Fecha turno
				}else
					row.add("");
				if (pedido_pieza.getFecha_solicitud_fabrica()!=null){
					java.sql.Date fsf = new java.sql.Date(pedido_pieza.getFecha_solicitud_fabrica().getTime());
					row.add(format2.format(fsf));//Fecha Solicitud Fabrica
				}else
					row.add("");
				if (pedido_pieza.getFecha_recepcion_fabrica()!=null){
					java.sql.Date frf = new java.sql.Date(pedido_pieza.getFecha_recepcion_fabrica().getTime());
					row.add(format2.format(frf));//Fecha Recepcion Fabrica
				}else
					row.add("");
				if (pedido_pieza.getFecha_cambio()!=null){
					java.sql.Date fcambio = new java.sql.Date(pedido_pieza.getFecha_cambio().getTime());
					row.add(format2.format(fcambio));//Fecha Cambio
				}else
					row.add("");
				if (pedido_pieza.getPedido().getReclamo().getOrden().getRecurso()!=null && pedido_pieza.getPedido().getReclamo().getOrden().getRecurso().getFecha_recurso()!=null){
					java.sql.Date frecurso = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getOrden().getRecurso().getFecha_recurso().getTime());
					row.add(format2.format(frecurso));//Fecha Recurso
				}else
					row.add("");
				if (pedido_pieza.getFecha_solicitud_devolucion()!=null){
					java.sql.Date fsd = new java.sql.Date(pedido_pieza.getFecha_solicitud_devolucion().getTime());
					row.add(format2.format(fsd));//Fecha Solicitud Devolucion
				}else
					row.add("");
				if (pedido_pieza.getFecha_aprobacion_solicitud_devolucion()!=null){
					java.sql.Date fas = new java.sql.Date(pedido_pieza.getFecha_aprobacion_solicitud_devolucion().getTime());
					row.add(format2.format(fas));//Fecha Aceptacion Devolucion
				}else
					row.add("");
				if (pedido_pieza.getDevolucion_pieza()!=null && pedido_pieza.getDevolucion_pieza().getFecha_devolucion()!=null){
					java.sql.Date fdf = new java.sql.Date(pedido_pieza.getDevolucion_pieza().getFecha_devolucion().getTime());
					row.add(format2.format(fdf));//Fecha Devolucion Fabrica
					row.add(pedido_pieza.getDevolucion_pieza().getNumero_remito());//numero remito
				}else{
					row.add("");
					row.add("");
				}
				if (pedido_pieza.getPnc()!=null)
					row.add(pedido_pieza.getPnc());// PCN
				else
					row.add("");
				if (pedido_pieza.getBdg()!=null){
					row.add(pedido_pieza.getBdg().getNumero_bdg());//Numero BDG
					if(pedido_pieza.getBdg().getFecha_bdg()!=null){
						java.sql.Date fbdg = new java.sql.Date(pedido_pieza.getBdg().getFecha_bdg().getTime());
						row.add(format2.format(fbdg));//Fecha BDG
					}else
						row.add("");
				}else{
					row.add("");
					row.add("");
				}		
				datosTabla.add(row);
			}
			modelo.setDataVector(datosTabla, nombreColumnas);
			modelo.fireTableStructureChanged();
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
			dCFReclamo = new TextField();
			if(FDesde!=null)
				dCFReclamo.setText(FDesde.toString());
			dCFDevolucion = new TextField();
			if(FHasta!=null)
				dCFDevolucion.setText(FHasta.toString());

		}
	}

	private void cargarDatos() {
		modelo = new DefaultTableModel();
		nombreColumnas = new Vector<String> ();
		anchos = new Vector<Integer>();
		int chico = 100;
		int mediano = 150;
		int grande = 230;
		nombreColumnas.add("ID Pedido");//0
		anchos.add(75);
		nombreColumnas.add("Estado Pedido");//1
		anchos.add(grande);
		nombreColumnas.add("Numero Pedido");//2
		anchos.add(chico);
		nombreColumnas.add("Nombre Entidad");//4
		anchos.add(grande);
		nombreColumnas.add("Numero Orden");//5
		anchos.add(chico);
		nombreColumnas.add("Nombre Reclamante");//8
		anchos.add(grande);
		nombreColumnas.add("Nombre Titular");//9
		anchos.add(grande);
		nombreColumnas.add("Dominio");//10
		anchos.add(70);
		nombreColumnas.add("VIN");//11
		anchos.add(130);
		nombreColumnas.add("Numero Pieza");//12
		anchos.add(chico);
		nombreColumnas.add("Fecha Reclamo");//6
		anchos.add(chico);
		nombreColumnas.add("Fecha Solicitud Pedido");//3
		anchos.add(mediano);
		nombreColumnas.add("Fecha Turno");//7
		anchos.add(chico);
		nombreColumnas.add("Fecha Solicitud Fabrica");//13
		anchos.add(mediano);
		nombreColumnas.add("Fecha Recepcion Fabrica");//14
		anchos.add(mediano);
		nombreColumnas.add("Fecha Cambio");//14
		anchos.add(mediano);
		nombreColumnas.add("Fecha Recurso");//14
		anchos.add(mediano);
		nombreColumnas.add("Fecha Solicitud Devolucion");//15
		anchos.add(mediano);
		nombreColumnas.add("Fecha Aprobada Solicitud");//16
		anchos.add(mediano);
		nombreColumnas.add("Fecha Devolucion Fabrica");//17
		anchos.add(mediano);
		nombreColumnas.add("Numero Remito");//18
		anchos.add(chico);
		nombreColumnas.add("PNC");//19
		anchos.add(chico);
		nombreColumnas.add("Numero BDG");//22
		anchos.add(chico);
		nombreColumnas.add("Fecha BDG");//23
		anchos.add(chico);
		//tabla
		datosTabla = new Vector<Vector<String>>();
		
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		if(pedidos_piezas!=null){
			for (int i=0; i< pedidos_piezas.size();i++){
				Pedido_PiezaDTO pedido_pieza = pedidos_piezas.elementAt(i);
				Vector<String> row = new Vector<String> ();
				row.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
				row.add(pedido_pieza.getEstado_pedido());//Estado Pedido
				row.add(pedido_pieza.getNumero_pedido());//Numero Pedido
				row.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//nombre entidad
				if (pedido_pieza.getPedido().getReclamo().getOrden()!=null)
					row.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
				else
					row.add("");
				row.add(pedido_pieza.getPedido().getReclamo().getReclamante().getNombre_apellido());//Nombre Reclamante	
				if (pedidos_piezas.elementAt(i).getPedido().getReclamo().getVehiculo()!=null){
					row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getNombre_titular());//Nombre Titular
					row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getDominio());//Dominio
					row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
				}else{
					row.add("");
					row.add("");
					row.add("");
				}
				if (pedido_pieza.getPieza()!=null)
					row.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
				else
					row.add("");
				if (pedido_pieza.getPedido().getReclamo().getFecha_reclamo()!=null){
					java.sql.Date fr = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getFecha_reclamo().getTime());
					row.add(format2.format(fr));//Fecha Reclamo
				}else
					row.add("");
				if (pedido_pieza.getPedido().getFecha_solicitud_pedido()!=null){
					java.sql.Date fsp = new java.sql.Date(pedido_pieza.getPedido().getFecha_solicitud_pedido().getTime());
					row.add(format2.format(fsp));//Fecha Solicitud Pedido					
				}else
					row.add("");
				if (pedido_pieza.getPedido().getReclamo().getFecha_turno()!=null){
					java.sql.Date ft = new java.sql.Date(pedidos_piezas.elementAt(i).getPedido().getReclamo().getFecha_turno().getTime());
					row.add(format2.format(ft));//Fecha turno
				}else
					row.add("");
				if (pedido_pieza.getFecha_solicitud_fabrica()!=null){
					java.sql.Date fsf = new java.sql.Date(pedido_pieza.getFecha_solicitud_fabrica().getTime());
					row.add(format2.format(fsf));//Fecha Solicitud Fabrica
				}else
					row.add("");
				if (pedido_pieza.getFecha_recepcion_fabrica()!=null){
					java.sql.Date frf = new java.sql.Date(pedido_pieza.getFecha_recepcion_fabrica().getTime());
					row.add(format2.format(frf));//Fecha Recepcion Fabrica
				}else
					row.add("");
				if (pedido_pieza.getFecha_cambio()!=null){
					java.sql.Date fcambio = new java.sql.Date(pedido_pieza.getFecha_cambio().getTime());
					row.add(format2.format(fcambio));//Fecha Cambio
				}else
					row.add("");
				if (pedido_pieza.getPedido().getReclamo().getOrden().getRecurso()!=null && pedido_pieza.getPedido().getReclamo().getOrden().getRecurso().getFecha_recurso()!=null){
					java.sql.Date frecurso = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getOrden().getRecurso().getFecha_recurso().getTime());
					row.add(format2.format(frecurso));//Fecha Recurso
				}else
					row.add("");
				if (pedido_pieza.getFecha_solicitud_devolucion()!=null){
					java.sql.Date fsd = new java.sql.Date(pedido_pieza.getFecha_solicitud_devolucion().getTime());
					row.add(format2.format(fsd));//Fecha Solicitud Devolucion
				}else
					row.add("");
				if (pedido_pieza.getFecha_aprobacion_solicitud_devolucion()!=null){
					java.sql.Date fas = new java.sql.Date(pedido_pieza.getFecha_aprobacion_solicitud_devolucion().getTime());
					row.add(format2.format(fas));//Fecha Aceptacion Devolucion
				}else
					row.add("");
				if (pedido_pieza.getDevolucion_pieza()!=null && pedido_pieza.getDevolucion_pieza().getFecha_devolucion()!=null){
					java.sql.Date fdf = new java.sql.Date(pedido_pieza.getDevolucion_pieza().getFecha_devolucion().getTime());
					row.add(format2.format(fdf));//Fecha Devolucion Fabrica
					row.add(pedido_pieza.getDevolucion_pieza().getNumero_remito());//numero remito
				}else{
					row.add("");
					row.add("");
				}
				if (pedido_pieza.getPnc()!=null)
					row.add(pedido_pieza.getPnc());// PCN
				else
					row.add("");
				if (pedido_pieza.getBdg()!=null){
					row.add(pedido_pieza.getBdg().getNumero_bdg());//Numero BDG
					if(pedido_pieza.getBdg().getFecha_bdg()!=null){
						java.sql.Date fbdg = new java.sql.Date(pedido_pieza.getBdg().getFecha_bdg().getTime());
						row.add(format2.format(fbdg));//Fecha BDG
					}else
						row.add("");
				}else{
					row.add("");
					row.add("");
				}		
				datosTabla.add(row);
			}
			modelo.setDataVector(datosTabla, nombreColumnas);
			modelo.fireTableStructureChanged();
		}		
	}

	protected void exportarTablas() {
		try {
			ExportarExcel.exportarUnaTabla(tablaPedidos, titulo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}


	public MediadorReportes getMediador() {
		return mediador;
	}


	public void setMediador(MediadorReportes mediador) {
		this.mediador = mediador;
	}
}