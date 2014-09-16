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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cliente.Recursos.Botones.ButtonType;
import cliente.Recursos.Botones.GlossyButton;
import cliente.Recursos.util.JPanel_Whit_Image;
import cliente.Recursos.util.Theme;
import cliente.excellexport.ExportarExcel;

import common.DTOs.Pedido_PiezaDTO;

public class GUIReclamoPiezas extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorReclamoPiezas mediador;
	private GUINuevoReclamoFabrica reclamo_fabrica;
	private GUINuevoReclamoAgente reclamo_agente;
	private JPanel contentPane;

	//tabla reclamos a fabrica
	private JPanel reclamos_Fabrica;
	private JTable tabla_reclamos_agentes;
	private Vector<Vector<String>> datosTabla_reclamos_agentes;
	private Vector<String> nombreColumnas_reclamos_agentes;
	private DefaultTableModel modelo_reclamos_agentes;
	private Vector<Integer> anchos_tabla_reclamo_agente;
	private Vector<Pedido_PiezaDTO> pedidos_piezas_reclamo_agentes;
	private JButton btnNuevoReclamoFabrica;
	private JButton btnVerReclamosFabrica;
	
	//tabla reclamos a agente
	private JPanel reclamos_Agente;
	private JTable tabla_reclamos_fabrica;
	private Vector<Vector<String>> datosTabla_reclamos_fabrica;
	private Vector<String> nombreColumnas_reclamos_fabrica;
	private DefaultTableModel modelo_reclamos_fabrica;
	private Vector<Integer> anchos_tabla_reclamo_fabrica;
	private Vector<Pedido_PiezaDTO> pedidos_piezas_reclamo_fabrica;
	private JButton btnNuevoReclamoAgente;
	private JButton btnVerReclamosAgente;
	private JButton btnActualizarReclamosAgente;
	private JButton btnActualizarReclamosFabrica;
	private JButton btnVolver;
	private JButton btnExportarTablaRF;
	private JButton btnExportarTablaRA;

	
	public GUIReclamoPiezas(MediadorReclamoPiezas mediadorRepuesto) {
		mediador = mediadorRepuesto;
		cargarDatos();
		initialize();
	}
	
	private void cargarDatos() {

		int chico = 100;
		int mediano = 150;
		int grande = 230;
		
		//TABLA RECLAMOS FABRICA
		anchos_tabla_reclamo_fabrica = new Vector<Integer>();	
		nombreColumnas_reclamos_fabrica = new Vector<String>();

		nombreColumnas_reclamos_fabrica.add("ID Pedido_Pieza");//0
		anchos_tabla_reclamo_fabrica.add(75);
		nombreColumnas_reclamos_fabrica.add("Numero Pedido");//1
		anchos_tabla_reclamo_fabrica.add(mediano);
		nombreColumnas_reclamos_fabrica.add("Numero Pieza");//2
		anchos_tabla_reclamo_fabrica.add(mediano);
		nombreColumnas_reclamos_fabrica.add("Descripcion");//3
		anchos_tabla_reclamo_fabrica.add(grande);
		nombreColumnas_reclamos_fabrica.add("Estado Pedido");//4
		anchos_tabla_reclamo_fabrica.add(grande);
		nombreColumnas_reclamos_fabrica.add("Numero Orden");//5
		anchos_tabla_reclamo_fabrica.add(mediano);
		nombreColumnas_reclamos_fabrica.add("Registrante");//6
		anchos_tabla_reclamo_fabrica.add(grande);
		nombreColumnas_reclamos_fabrica.add("Fecha Solicitud Fabrica");//7
		anchos_tabla_reclamo_fabrica.add(mediano);
		nombreColumnas_reclamos_fabrica.add("Fecha Recepcion Fabrica");//8
		anchos_tabla_reclamo_fabrica.add(mediano);
		nombreColumnas_reclamos_fabrica.add("Cantidad de Reclamos Fabrica");//9
		anchos_tabla_reclamo_fabrica.add(chico);
		nombreColumnas_reclamos_fabrica.add("Fecha Ultimo Reclamo Fabrica");//10
		anchos_tabla_reclamo_fabrica.add(grande);
		
		modelo_reclamos_fabrica = new DefaultTableModel();
		datosTabla_reclamos_fabrica = new Vector<Vector<String>>();
		pedidos_piezas_reclamo_fabrica = mediador.obtenerPedido_PiezaFabrica();
		
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");

		for (int i=0; i< pedidos_piezas_reclamo_fabrica.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(pedidos_piezas_reclamo_fabrica.elementAt(i).getId().toString());//ID Pedido_Pieza
			row.add(pedidos_piezas_reclamo_fabrica.elementAt(i).getNumero_pedido());//Numero Pedido
			row.add(pedidos_piezas_reclamo_fabrica.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
			row.add(pedidos_piezas_reclamo_fabrica.elementAt(i).getPieza().getDescripcion());//Descripcion
			row.add(pedidos_piezas_reclamo_fabrica.elementAt(i).getEstado_pedido());//Estado Pedido
			row.add(pedidos_piezas_reclamo_fabrica.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			row.add(pedidos_piezas_reclamo_fabrica.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
			
			if(pedidos_piezas_reclamo_fabrica.elementAt(i).getFecha_solicitud_fabrica()!=null){
				java.sql.Date fSf = new java.sql.Date(pedidos_piezas_reclamo_fabrica.elementAt(i).getFecha_solicitud_fabrica().getTime());
				row.add(format2.format(fSf));//Fecha Solicitud Fabrica
			}else{
				row.add("");
			}
			
			if(pedidos_piezas_reclamo_fabrica.elementAt(i).getFecha_recepcion_fabrica()!=null){
				java.sql.Date fRf = new java.sql.Date(pedidos_piezas_reclamo_fabrica.elementAt(i).getFecha_recepcion_fabrica().getTime());
				row.add(format2.format(fRf));//Fecha Recepcion Fabrica
			}else{
				row.add("");
			}
			
			row.add(mediador.cantidadReclamosFabrica(pedidos_piezas_reclamo_fabrica.elementAt(i)).toString());
						
			Date fecha_ultimo_reclamo_fabrica = mediador.obtenerUltimoReclamoFabrica(pedidos_piezas_reclamo_fabrica.elementAt(i));
			if(fecha_ultimo_reclamo_fabrica!=null){
				row.add(format2.format(fecha_ultimo_reclamo_fabrica.getTime()));//Fecha Ultimo Reclamo Fabrica
			}else{
				row.add("");
			}
			datosTabla_reclamos_fabrica.add(row);
		}
		modelo_reclamos_fabrica.setDataVector(datosTabla_reclamos_fabrica, nombreColumnas_reclamos_fabrica);
		modelo_reclamos_fabrica.fireTableStructureChanged();
		
		//TABLA RECLAMOS AGENTE
		
		anchos_tabla_reclamo_agente = new Vector<Integer>();
		nombreColumnas_reclamos_agentes = new Vector<String>();
		
		nombreColumnas_reclamos_agentes.add("ID Pedido_Pieza");//0
		anchos_tabla_reclamo_agente.add(75);
		nombreColumnas_reclamos_agentes.add("Numero Pedido");//1
		anchos_tabla_reclamo_agente.add(mediano);		
		nombreColumnas_reclamos_agentes.add("Numero Pieza");//2
		anchos_tabla_reclamo_agente.add(mediano);		
		nombreColumnas_reclamos_agentes.add("Descripcion");//3
		anchos_tabla_reclamo_agente.add(grande);
		nombreColumnas_reclamos_agentes.add("Estado Pedido");//4
		anchos_tabla_reclamo_agente.add(grande);
		nombreColumnas_reclamos_agentes.add("Numero Orden");//5
		anchos_tabla_reclamo_agente.add(mediano);
		nombreColumnas_reclamos_agentes.add("Registrante");//6
		anchos_tabla_reclamo_agente.add(grande);
		nombreColumnas_reclamos_agentes.add("Fecha Solicitud Fabrica");//7
		anchos_tabla_reclamo_agente.add(mediano);		
		nombreColumnas_reclamos_agentes.add("Fecha Recepcion Fabrica");//8
		anchos_tabla_reclamo_agente.add(mediano);
		nombreColumnas_reclamos_agentes.add("Fecha Envio Agente");//9
		anchos_tabla_reclamo_agente.add(mediano);
		nombreColumnas_reclamos_agentes.add("Fecha Recepcion Agente");//10
		anchos_tabla_reclamo_agente.add(mediano);		
		nombreColumnas_reclamos_agentes.add("Cantidad de Reclamos Agente");//11
		anchos_tabla_reclamo_agente.add(chico);
		nombreColumnas_reclamos_agentes.add("Fecha Ultimo Reclamo Agente");//12
		anchos_tabla_reclamo_agente.add(grande);
		
		datosTabla_reclamos_agentes = new Vector<Vector<String>>();
		modelo_reclamos_agentes = new DefaultTableModel();
		pedidos_piezas_reclamo_agentes = mediador.obtenerPedido_Pieza_Agente();

		for (int i=0; i< pedidos_piezas_reclamo_agentes.size();i++){
			Vector<String> row = new Vector<String> ();
						
			row.add(pedidos_piezas_reclamo_agentes.elementAt(i).getId().toString());//ID Pedido_Pieza
			row.add(pedidos_piezas_reclamo_agentes.elementAt(i).getNumero_pedido());//Numero Pedido
			row.add(pedidos_piezas_reclamo_agentes.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
			row.add(pedidos_piezas_reclamo_agentes.elementAt(i).getPieza().getDescripcion());//Descripcion
			row.add(pedidos_piezas_reclamo_agentes.elementAt(i).getEstado_pedido());//Estado Pedido
			row.add(pedidos_piezas_reclamo_agentes.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			row.add(pedidos_piezas_reclamo_agentes.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante

			if(pedidos_piezas_reclamo_agentes.elementAt(i).getFecha_solicitud_fabrica()!=null){
				java.sql.Date fSf = new java.sql.Date(pedidos_piezas_reclamo_agentes.elementAt(i).getFecha_solicitud_fabrica().getTime());
				row.add(format2.format(fSf));//Fecha Solicitud Fabrica
			}else{
				row.add("");
			}
			
			if(pedidos_piezas_reclamo_agentes.elementAt(i).getFecha_recepcion_fabrica()!=null){
				java.sql.Date fRf = new java.sql.Date(pedidos_piezas_reclamo_agentes.elementAt(i).getFecha_recepcion_fabrica().getTime());
				row.add(format2.format(fRf));//Fecha Recepcion Fabrica
			}else{
				row.add("");
			}
			
			if(pedidos_piezas_reclamo_agentes.elementAt(i).getFecha_envio_agente()!=null){
				java.sql.Date fEa = new java.sql.Date(pedidos_piezas_reclamo_agentes.elementAt(i).getFecha_envio_agente().getTime());
				row.add(format2.format(fEa));//Fecha Envio Agente
			}else{
				row.add("");
			}
			
			if(pedidos_piezas_reclamo_agentes.elementAt(i).getFecha_recepcion_agente()!=null){
				java.sql.Date fRa = new java.sql.Date(pedidos_piezas_reclamo_agentes.elementAt(i).getFecha_recepcion_agente().getTime());
				row.add(format2.format(fRa));//Fecha Recepcion Agente
			}else{
				row.add("");
			}
			
			row.add(mediador.cantidadReclamosAgente(pedidos_piezas_reclamo_agentes.elementAt(i)).toString());//Cantidad Reclamos Agente
			
			Date fecha_ultimo_reclamo_agente = mediador.obtenerUltimoReclamoAgente(pedidos_piezas_reclamo_agentes.elementAt(i));
			if(fecha_ultimo_reclamo_agente!=null){
				row.add(format2.format(fecha_ultimo_reclamo_agente.getTime()));//Fecha Ultimo Reclamo Agente
			}else{
				row.add("");
			}
			datosTabla_reclamos_agentes.add(row);
		}
		modelo_reclamos_agentes.setDataVector(datosTabla_reclamos_agentes, nombreColumnas_reclamos_agentes);
		modelo_reclamos_agentes.fireTableStructureChanged();
	}
	
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1161, 680);
		setTitle("RECLAMOS DE PIEZAS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReclamoPiezas.class.getResource("/cliente/Resources/Icons/reclamospie.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane =  new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1155, 609);
		contentPane.add(tabbedPane);
		
		reclamos_Fabrica = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		reclamos_Fabrica.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tabbedPane.addTab("RECLAMOS A FABRICA", new ImageIcon(GUIReclamoPiezas.class.getResource("/cliente/Resources/Icons/entidad.png")), reclamos_Fabrica, null);
		reclamos_Fabrica.setLayout(null);
			
		modelo_reclamos_fabrica = new DefaultTableModel(datosTabla_reclamos_fabrica, nombreColumnas_reclamos_fabrica);
		
		tabla_reclamos_fabrica = new JTable(modelo_reclamos_fabrica) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tabla_reclamos_fabrica.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tabla_reclamos_fabrica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2)
					
					verReclamosFabrica();
			    else{
			    	e.consume();
			    }   
			}
		});
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_reclamos_fabrica = new TableRowSorter<TableModel>(modelo_reclamos_fabrica);
		tabla_reclamos_fabrica.setRowSorter(ordenador_reclamos_fabrica);
		//
		tabla_reclamos_fabrica.getTableHeader().setReorderingAllowed(false);
		for(int i = 0; i < tabla_reclamos_fabrica.getColumnCount(); i++) {
			tabla_reclamos_fabrica.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_reclamo_fabrica.elementAt(i));
			tabla_reclamos_fabrica.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_reclamo_fabrica.elementAt(i));
		}
		tabla_reclamos_fabrica.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPaneTabla_reclamos_fabrica = new JScrollPane(tabla_reclamos_fabrica);
		scrollPaneTabla_reclamos_fabrica.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneTabla_reclamos_fabrica.setViewportView(tabla_reclamos_fabrica);
		tabla_reclamos_fabrica.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		scrollPaneTabla_reclamos_fabrica.setBounds(10, 50, 1130, 513);
		reclamos_Fabrica.add(scrollPaneTabla_reclamos_fabrica);
		
		btnNuevoReclamoFabrica = new GlossyButton("AGREGAR RECLAMO",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnNuevoReclamoFabrica.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNuevoReclamoFabrica.setIcon(new ImageIcon(GUIReclamoPiezas.class.getResource("/cliente/Resources/Icons/new_reclamo.png")));
		btnNuevoReclamoFabrica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearReclamoFabrica();
			}
		});
		btnNuevoReclamoFabrica.setBounds(10, 11, 215, 25);
		reclamos_Fabrica.add(btnNuevoReclamoFabrica);
		
		btnActualizarReclamosFabrica = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnActualizarReclamosFabrica.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnActualizarReclamosFabrica.setIcon(new ImageIcon(GUIReclamoPiezas.class.getResource("/cliente/Resources/Icons/1refresh.png")));
		btnActualizarReclamosFabrica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarReclamosFabrica();
			}
		});
		btnActualizarReclamosFabrica.setBounds(1108, 11, 32, 32);
		reclamos_Fabrica.add(btnActualizarReclamosFabrica);
		
		btnVerReclamosFabrica = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIME_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerReclamosFabrica.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnVerReclamosFabrica.setIcon(new ImageIcon(GUIReclamoPiezas.class.getResource("/cliente/Resources/Icons/find_reclamo.png")));
		btnVerReclamosFabrica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verReclamosFabrica();
			}
		});
		btnVerReclamosFabrica.setBounds(1024, 11, 32, 32);
		reclamos_Fabrica.add(btnVerReclamosFabrica);
		
		btnExportarTablaRF = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_METALIC_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnExportarTablaRF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnExportarTablaRF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaRF();
			}
		});
		btnExportarTablaRF.setIcon(new ImageIcon(GUIReclamoPiezas.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTablaRF.setBounds(1066, 11, 32, 32);
		reclamos_Fabrica.add(btnExportarTablaRF);
		
		reclamos_Agente = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/background.jpg");
		reclamos_Agente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tabbedPane.addTab("RECLAMOS A AGENTE", new ImageIcon(GUIReclamoPiezas.class.getResource("/cliente/Resources/Icons/registrante_solo.png")), reclamos_Agente, null);
		reclamos_Agente.setLayout(null);
				
		modelo_reclamos_agentes = new DefaultTableModel(datosTabla_reclamos_agentes, nombreColumnas_reclamos_agentes);
		
		tabla_reclamos_agentes = new JTable(modelo_reclamos_agentes) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tabla_reclamos_agentes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tabla_reclamos_agentes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2)
					verReclamosAgente();
			    else{
			    	e.consume();
			    }   
			}
		});
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_reclamos_agentes = new TableRowSorter<TableModel>(modelo_reclamos_agentes);
		tabla_reclamos_agentes.setRowSorter(ordenador_reclamos_agentes);
		//
		tabla_reclamos_agentes.getTableHeader().setReorderingAllowed(false);
		for(int i = 0; i < tabla_reclamos_agentes.getColumnCount(); i++) {
			tabla_reclamos_agentes.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_reclamo_agente.elementAt(i));
			tabla_reclamos_agentes.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_reclamo_agente.elementAt(i));
		}
		tabla_reclamos_agentes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPaneTabla_reclamos_agentes = new JScrollPane(tabla_reclamos_agentes);
		scrollPaneTabla_reclamos_agentes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneTabla_reclamos_agentes.setViewportView(tabla_reclamos_agentes);
		tabla_reclamos_agentes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		scrollPaneTabla_reclamos_agentes.setBounds(10, 50, 1130, 513);
		reclamos_Agente.add(scrollPaneTabla_reclamos_agentes);
		
		btnActualizarReclamosAgente = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_MULTIINDIGO_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnActualizarReclamosAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnActualizarReclamosAgente.setIcon(new ImageIcon(GUIReclamoPiezas.class.getResource("/cliente/Resources/Icons/1refresh.png")));
		btnActualizarReclamosAgente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarReclamosAgente();
			}
		});
		btnActualizarReclamosAgente.setBounds(1108, 11, 32, 32);
		reclamos_Agente.add(btnActualizarReclamosAgente);
		
		btnVerReclamosAgente = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_LIME_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVerReclamosAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnVerReclamosAgente.setIcon(new ImageIcon(GUIReclamoPiezas.class.getResource("/cliente/Resources/Icons/find_reclamo.png")));
		btnVerReclamosAgente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verReclamosAgente();
			}
		});
		btnVerReclamosAgente.setBounds(1024, 11, 32, 32);
		reclamos_Agente.add(btnVerReclamosAgente);
		
		btnNuevoReclamoAgente = new GlossyButton("AGREGAR RECLAMO",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_GREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnNuevoReclamoAgente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNuevoReclamoAgente.setIcon(new ImageIcon(GUIReclamoPiezas.class.getResource("/cliente/Resources/Icons/new_reclamo.png")));
		btnNuevoReclamoAgente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearReclamoAgente();
			}
		});
		btnNuevoReclamoAgente.setBounds(10, 11, 215, 25);
		reclamos_Agente.add(btnNuevoReclamoAgente);
		
		btnExportarTablaRA = new GlossyButton("",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_METALIC_BLUE_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnExportarTablaRA.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnExportarTablaRA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaRA();
			}
		});
		btnExportarTablaRA.setIcon(new ImageIcon(GUIReclamoPiezas.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTablaRA.setBounds(1066, 11, 32, 32);
		reclamos_Agente.add(btnExportarTablaRA);
		
		btnVolver = new GlossyButton("VOLVER",ButtonType.BUTTON_ROUNDED,Theme.GLOSSY_BLUEGREEN_THEME,Theme.GLOSSY_ORANGE_THEME,Theme.GLOSSY_BLACK_THEME);
		btnVolver.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnVolver.setIcon(new ImageIcon(GUIReclamoPiezas.class.getResource("/cliente/Resources/Icons/back.png")));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVolver.setBounds(515, 620, 125, 23);
		contentPane.add(btnVolver);
	}

	protected void exportarTablaRA() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_reclamos_agentes, "Reclamos Agentes");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void exportarTablaRF() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_reclamos_fabrica, "Reclamos Fabrica");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void actualizarReclamosAgente() {
		datosTabla_reclamos_agentes = new Vector<Vector<String>>();
		pedidos_piezas_reclamo_agentes = mediador.obtenerPedido_Pieza_Agente();

		for (int i=0; i< pedidos_piezas_reclamo_agentes.size();i++){
			Vector<String> row = new Vector<String> ();
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			
			row.add(pedidos_piezas_reclamo_agentes.elementAt(i).getId().toString());//ID Pedido_Pieza
			row.add(pedidos_piezas_reclamo_agentes.elementAt(i).getNumero_pedido());//Numero Pedido
			row.add(pedidos_piezas_reclamo_agentes.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
			row.add(pedidos_piezas_reclamo_agentes.elementAt(i).getPieza().getDescripcion());//Descripcion
			row.add(pedidos_piezas_reclamo_agentes.elementAt(i).getEstado_pedido());//Estado Pedido
			row.add(pedidos_piezas_reclamo_agentes.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			row.add(pedidos_piezas_reclamo_agentes.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante

			if(pedidos_piezas_reclamo_agentes.elementAt(i).getFecha_solicitud_fabrica()!=null){
				java.sql.Date fSf = new java.sql.Date(pedidos_piezas_reclamo_agentes.elementAt(i).getFecha_solicitud_fabrica().getTime());
				row.add(format2.format(fSf));//Fecha Solicitud Fabrica
			}else{
				row.add("");
			}
			
			if(pedidos_piezas_reclamo_agentes.elementAt(i).getFecha_recepcion_fabrica()!=null){
				java.sql.Date fRf = new java.sql.Date(pedidos_piezas_reclamo_agentes.elementAt(i).getFecha_recepcion_fabrica().getTime());
				row.add(format2.format(fRf));//Fecha Recepcion Fabrica
			}else{
				row.add("");
			}
			
			if(pedidos_piezas_reclamo_agentes.elementAt(i).getFecha_envio_agente()!=null){
				java.sql.Date fEa = new java.sql.Date(pedidos_piezas_reclamo_agentes.elementAt(i).getFecha_envio_agente().getTime());
				row.add(format2.format(fEa));//Fecha Envio Agente
			}else{
				row.add("");
			}
			
			if(pedidos_piezas_reclamo_agentes.elementAt(i).getFecha_recepcion_agente()!=null){
				java.sql.Date fRa = new java.sql.Date(pedidos_piezas_reclamo_agentes.elementAt(i).getFecha_recepcion_agente().getTime());
				row.add(format2.format(fRa));//Fecha Recepcion Agente
			}else{
				row.add("");
			}
			
			row.add(mediador.cantidadReclamosAgente(pedidos_piezas_reclamo_agentes.elementAt(i)).toString());//Cantidad Reclamos Agente
			
			Date fecha_ultimo_reclamo_agente = mediador.obtenerUltimoReclamoAgente(pedidos_piezas_reclamo_agentes.elementAt(i));
			if(fecha_ultimo_reclamo_agente!=null){
				row.add(format2.format(fecha_ultimo_reclamo_agente.getTime()));//Fecha Ultimo Reclamo Agente
			}else{
				row.add("");
			}
			datosTabla_reclamos_agentes.add(row);
		}
		modelo_reclamos_agentes.setDataVector(datosTabla_reclamos_agentes, nombreColumnas_reclamos_agentes);
		modelo_reclamos_agentes.fireTableStructureChanged();
		
		for(int i = 0; i < tabla_reclamos_fabrica.getColumnCount(); i++) {
			tabla_reclamos_agentes.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_reclamo_agente.elementAt(i));
			tabla_reclamos_agentes.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_reclamo_agente.elementAt(i));
		}
	}

	protected void actualizarReclamosFabrica() {
		datosTabla_reclamos_fabrica = new Vector<Vector<String>>();
		pedidos_piezas_reclamo_fabrica = mediador.obtenerPedido_PiezaFabrica();
		
		for (int i=0; i< pedidos_piezas_reclamo_fabrica.size();i++){
			Vector<String> row = new Vector<String> ();
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			
			row.add(pedidos_piezas_reclamo_fabrica.elementAt(i).getId().toString());//ID Pedido_Pieza
			row.add(pedidos_piezas_reclamo_fabrica.elementAt(i).getNumero_pedido());//Numero Pedido
			row.add(pedidos_piezas_reclamo_fabrica.elementAt(i).getPieza().getNumero_pieza());//Numero Pieza
			row.add(pedidos_piezas_reclamo_fabrica.elementAt(i).getPieza().getDescripcion());//Descripcion
			row.add(pedidos_piezas_reclamo_fabrica.elementAt(i).getEstado_pedido());//Estado Pedido
			row.add(pedidos_piezas_reclamo_fabrica.elementAt(i).getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			row.add(pedidos_piezas_reclamo_fabrica.elementAt(i).getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
			
			if(pedidos_piezas_reclamo_fabrica.elementAt(i).getFecha_solicitud_fabrica()!=null){
				java.sql.Date fSf = new java.sql.Date(pedidos_piezas_reclamo_fabrica.elementAt(i).getFecha_solicitud_fabrica().getTime());
				row.add(format2.format(fSf));//Fecha Solicitud Fabrica
			}else{
				row.add("");
			}
			
			if(pedidos_piezas_reclamo_fabrica.elementAt(i).getFecha_recepcion_fabrica()!=null){
				java.sql.Date fRf = new java.sql.Date(pedidos_piezas_reclamo_fabrica.elementAt(i).getFecha_recepcion_fabrica().getTime());
				row.add(format2.format(fRf));//Fecha Recepcion Fabrica
			}else{
				row.add("");
			}
			
			row.add(mediador.cantidadReclamosFabrica(pedidos_piezas_reclamo_fabrica.elementAt(i)).toString());
						
			Date fecha_ultimo_reclamo_fabrica = mediador.obtenerUltimoReclamoFabrica(pedidos_piezas_reclamo_fabrica.elementAt(i));
			if(fecha_ultimo_reclamo_fabrica!=null){
				row.add(format2.format(fecha_ultimo_reclamo_fabrica.getTime()));//Fecha Ultimo Reclamo Fabrica
			}else{
				row.add("");
			}
			datosTabla_reclamos_fabrica.add(row);
		}
		modelo_reclamos_fabrica.setDataVector(datosTabla_reclamos_fabrica, nombreColumnas_reclamos_fabrica);
		modelo_reclamos_fabrica.fireTableStructureChanged();
		
		for(int i = 0; i < tabla_reclamos_fabrica.getColumnCount(); i++) {
			tabla_reclamos_fabrica.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_reclamo_fabrica.elementAt(i));
			tabla_reclamos_fabrica.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_reclamo_fabrica.elementAt(i));
		}
		
	}
	protected void crearReclamoFabrica() {
		int row = tabla_reclamos_fabrica.getSelectedRow();
		if (row >= 0) {
			int aux = tabla_reclamos_fabrica.convertRowIndexToModel(row);
			Long id = new Long (tabla_reclamos_fabrica.getValueAt(aux, 0).toString());

			Pedido_PiezaDTO pedido_pieza = mediador.buscarPedido_Pieza(id);
			if(pedido_pieza!=null)
				reclamo_fabrica = new GUINuevoReclamoFabrica(mediador, pedido_pieza, mediador.getMediadorPrincipal().getUsuario());
				reclamo_fabrica.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(null,"Seleccione un reclamo primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	protected void crearReclamoAgente(){
		int row = tabla_reclamos_agentes.getSelectedRow();
		if (row >= 0) {
			int aux = tabla_reclamos_agentes.convertRowIndexToModel(row);
			Long id = new Long (tabla_reclamos_agentes.getValueAt(aux, 0).toString());

			Pedido_PiezaDTO pedido_pieza = mediador.buscarPedido_Pieza(id);
			if(pedido_pieza!=null)
				reclamo_agente = new GUINuevoReclamoAgente(mediador, pedido_pieza, mediador.getMediadorPrincipal().getUsuario());
				reclamo_agente.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(null,"Seleccione un reclamo primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void verReclamosFabrica(){
		int row = tabla_reclamos_fabrica.getSelectedRow();
		if (row >= 0) {
			int aux = tabla_reclamos_fabrica.convertRowIndexToModel(row);
			Long id = new Long (tabla_reclamos_fabrica.getValueAt(aux, 0).toString());
			mediador.verReclamosFabrica(id);
		}else{
			JOptionPane.showMessageDialog(null,"Seleccione un reclamo primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	protected void verReclamosAgente(){
		int row = tabla_reclamos_agentes.getSelectedRow();
		if (row >= 0) {
			int aux = tabla_reclamos_agentes.convertRowIndexToModel(row);
			Long id = new Long (tabla_reclamos_agentes.getValueAt(aux, 0).toString());
			mediador.verReclamosAgente(id);
		}else{
			JOptionPane.showMessageDialog(null,"Seleccione un reclamo primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}		
	}
}