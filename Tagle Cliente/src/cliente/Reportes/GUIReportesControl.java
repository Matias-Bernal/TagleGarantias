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
package cliente.Reportes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import cliente.excellexport.ExportarExcel;
import com.toedter.calendar.JDateChooser;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.ReclamoDTO;

public class GUIReportesControl extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorReportes mediador;
	private JPanel contentPane;
	private JPanel pnDiasDesdeFSolicitudFabirca;
	private JPanel pnDiasDesdeFRecepcionFabirca;
	private JPanel pnDiasDesdeFRecepcionFabricaFTurno;
	private JPanel pnDiasDesdeFCierreFTurno;
	private JTable tabla_DDRF;
	private JPanel pnDiasDesdeFRecursoFCierreOT;
	private JPanel pnReclamos_Turnos;
	private JPanel pnDiasDesdeFReclamoFDevolucion;
	private JPanel pnManoObra;
	private JPanel pnRecursoCierre;
	private JPanel pnPiezasLlegadas_PiezasDevueltas;
	//Tabla dias desde fsf
	private JScrollPane scrollPane_dias_trascurridos_desde_pedido_fabrica;
	private DefaultTableModel modelo_tabla_dias_desde_fsf;
	private JTable tabla_DDPF;
	private Vector<String> nombreColumnas_tabla_dias_desde_fsf;
	private Vector<Vector<String>> datosTabla_dias_desde_fsf;
	private Vector<Integer> anchos_tabla_dias_desde_fsf;
	//Tabla dias desde frf
	private JScrollPane scrollPane_dias_trascurridos_desde_frf;
	private DefaultTableModel modelo_tabla_dias_desde_frf;
	private Vector<Vector<String>> datosTabla_dias_desde_frf;
	private Vector<String> nombreColumnas_tabla_dias_desde_frf;
	private Vector<Integer> anchos_tabla_dias_desde_frf;
	//Tabla dias desde fcierre a fturno
	private JScrollPane scrollPane_dias_trasncurridos_desde_fcierre_fturno;
	private DefaultTableModel modelo_tabla_dias_desde_fcierre_fturno;
	private Vector<String> nombreColumnas_tabla_dias_desde_fcierre_fturno;
	private Vector<Vector<String>> datosTabla_dias_desde_fcierre_fturno;
	private Vector<Integer> anchos_tabla_dias_desde_fcierre_fturno;
	private JTable tabla_DDFCT;
	//tabla dias desde frecurso fcierre
	private JScrollPane scrollPane_dias_desde_frecurso_fcierre;
	private JTable tabla_DDFRFC;
	private DefaultTableModel modelo_tabla_dias_desde_frecurso_fcierre;
	private Vector<Vector<String>> datosTabla_dias_desde_frecurso_fcierre;
	private Vector<String> nombreColumnas_tabla_dias_desde_frecurso_fcierre;
	private Vector<Integer> anchos_tabla_dias_desde_frecurso_fcierre;
	//tabla dias desde frf fturno
	private JScrollPane scrollPane_dias_transcurridos_desde_frf_fturno;
	private JTable tabla_DDRFT;
	private DefaultTableModel modelo_tabla_dias_desde_frf_fturno;
	private Vector<Vector<String>> datosTabla_dias_desde_frf_fturno;
	private Vector<String> nombreColumnas_tabla_dias_desde_frf_fturno;
	private Vector<Integer> anchos_tabla_dias_desde_frf_fturno;
	//tabla dias desde freclamo fdevolucion
	private JScrollPane scrollPane_dias_desde_freclamo_fdevolucion;
	private DefaultTableModel modelo_tabla_dias_desde_freclamo_fdevolucion;
	private Vector<Vector<String>> datosTabla_dias_desde_freclamo_fdevolucion;
	private Vector<String> nombreColumnas_tabla_dias_desde_freclamo_fdevolucion;
	private JTable tabla_DDFRFD;
	private Vector<Integer> anchos_tabla_dias_desde_freclamo_fdevolucion;
	//tabla reclamos/turnos
	private JScrollPane scrollPane_tabla_reclamos_turno_Reclamos;
	private JScrollPane scrollPane_tabla_reclamos_turnos_Turno;
	private JLabel lblReclamoTurnos;
	private JTable tabla_RTR;
	private JTable tabla_RTT;
	private DefaultTableModel modelo_tabla_reclamos_turno_Reclamos;
	private Vector<Vector<String>> datos_tabla_reclamos_turno_Reclamos;
	private Vector<String> nombreColumnas_tabla_reclamos_turno_Reclamos;
	private Vector<Integer> anchos_tabla_reclamos_turno_Reclamos;
	private DefaultTableModel modelo_tabla_reclamos_turno_Turno;
	private Vector<Vector<String>> datos_tabla_reclamos_turno_Turno;	
	private Vector<String> nombreColumnas_tabla_reclamos_turno_Turno;
	private Vector<Integer> anchos_tabla_reclamos_turno_Turno;
	private float numero_reclamos;
	private float numero_turnos;
	private JLabel lblNum_ReclmaosConTurno;
	private JLabel lblNum_ReclamosSinTurno;
	private JScrollPane scrollPane_tabla_Pllegadas_PDevuletas_LLegadas;
	private JScrollPane scrollPane_tabla_Pllegadas_PDevuletas_Devueltas;
	private JLabel lblNum_PDevueltas;
	private JLabel lblNumPLlegadas;
	private JTable tabla_PLLPDL;
	private JTable tabla_PLLPDD;
	private JLabel lblLlegadaDevueltas;
	private DefaultTableModel modelo_tabla_Pllegadas_PDevuletas_Devueltas;
	private Vector<Vector<String>> datos_tabla_Pllegadas_PDevuletas_Devueltas;
	private Vector<String> nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas;
	private Vector<Integer> anchos_tabla_Pllegadas_PDevuletas_Devueltas;
	private DefaultTableModel modelo_tabla_Pllegadas_PDevuletas_LLegadas;
	private Vector<Vector<String>> datos_tabla_Pllegadas_PDevuletas_LLegadas;
	private Vector<String> nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas;
	private Vector<Integer> anchos_tabla_Pllegadas_PDevuletas_LLegadas;
	private float  numero_PiezasLlegadas;
	private float numero_PiezasDevueltas;
	private JLabel lblPiezasLLegadas_PiezasDevueltas;
	//tabla mano obra
	private float hs_mano_obra;
	private float preico_mano_obra;
	private JTable tabla_MO;
	private JScrollPane scrollPane_mano_obra;
	private DefaultTableModel modelo_tabla_mano_obra;
	private Vector<Vector<String>> datos_tabla_mano_obra;
	private Vector<String> nombreColumnas_tabla_mano_obra;
	private Vector<Integer> anchos_tabla_mano_obra;
	//Tabla recurso cierre
	private JTable tabla_RC;
	private DefaultTableModel modelo_tabla_recurso_cierre;
	private Vector<Vector<String>> datos_tabla_recurso_cierre;
	private Vector<String> nombreColumnas_tabla_recurso_cierre;
	private Vector<Integer> anchos_tabla_recurso_cierre;
	private JScrollPane scrollPane_recurso_cierre;
	private float N_casos;
	private float sum_frecurso_fcierre;
	private JLabel lblValorTotalDe;
	private JLabel lblHorasTotalesDe;
	private JLabel lbl_HsTotalesMO;
	private JLabel lbl_ValorTotalMO;
	private JRadioButton rB_Intervalo_DDPF;
	private JRadioButton rB_Hoy_DDPF;
	private JRadioButton rB_USemana_DDPF;
	private JRadioButton rB_Mes_DDPF;
	private JRadioButton rB_MAnterior_DDPF;
	private JRadioButton rB_Anio_DDPF;
	private JDateChooser dC_FI_DDPF;
	private JButton btn_clear_FIDDPF;
	private JDateChooser dC_FF_DDPF;
	private JButton btn_clear_FFDDPF;
	private JButton btn_filtrar_DDPF;
	private JButton btnExportarTablaDDPF;
	private JRadioButton rB_Intervalo_DDRF;
	private JRadioButton rB_Hoy_DDRF;
	private JRadioButton rB_USemana_DDRF;
	private JRadioButton rB_Mes_DDRF;
	private JRadioButton rB_MAnterior_DDRF;
	private JRadioButton rB_Anio_DDRF;
	private JDateChooser dC_FI_DDRF;
	private JButton btn_clear_FIDDRF;
	private JDateChooser dC_FF_DDRF;
	private JButton btnFiltrar_DDRF;
	private JButton btnExportarTablaDDRF;
	private JButton btn_clear_FFDDRF;
	private JRadioButton rB_Intervalo_DDRFT;
	private JRadioButton rB_Hoy_DDRFT;
	private JRadioButton rB_USemana_DDRFT;
	private JRadioButton rB_Mes_DDRFT;
	private JRadioButton rB_MAnterior_DDRFT;
	private JRadioButton rB_Anio_DDRFT;
	private JDateChooser dC_FI_DDRFT;
	private JButton btn_clear_FIDDRFT;
	private JDateChooser dC_FF_DDRFT;
	private JButton btn_clear_FFDDRFT;
	private JButton btnExportarTabla_DDRFT;
	private JRadioButton rB_Intervalo_DDFCFT;
	private JButton btn_filtrar_DDRFT;
	private JRadioButton rB_Hoy_DDFCFT;
	private JRadioButton rB_USemana_DDFCFT;
	private JRadioButton rB_Mes_DDFCFT;
	private JRadioButton rB_MAnterior_DDFCFT;
	private JRadioButton rB_Anio_DDFCFT;
	private JDateChooser dC_FI_DDFCFT;
	private JButton btn_clear_FIDDFCFT;
	private JDateChooser dC_FF_DDFCFT;
	private JButton btn_clear_FFDDFCFT;
	private JButton btn_filtrar_DDFCFT;
	private JButton btnExportarTablaDDFCFT;
	private JRadioButton rB_Intervalo_DDFRFC;
	private JRadioButton rB_Hoy_DDFRFC;
	private JRadioButton rB_USemana_DDFRFC;
	private JRadioButton rB_Mes_DDFRFC;
	private JRadioButton rB_MAnterior_DDFRFC;
	private JRadioButton rB_Anio_DDFRFC;
	private JDateChooser dC_FI_DDFRFC;
	private JButton btn_clear_FIDDFRFC;
	private JDateChooser dC_FF_DDFRFC;
	private JButton btn_clear_FFDDFRFC;
	private JButton btn_filtrar_DDFRFC;
	private JButton btnExportarTabla_DDFRFC;
	private JRadioButton rB_Intervalo_DDFRFD;
	private JRadioButton rB_Hoy_DDFRFD;
	private JRadioButton rB_USemana_DDFRFD;
	private JRadioButton rB_Mes_DDFRFD;
	private JRadioButton rB_MAnterior_DDFRFD;
	private JRadioButton rB_Anio_DDFRFD;
	private JDateChooser dC_FI_DDFRFD;
	private JButton btn_clear_FIDDFRFD;
	private JDateChooser dC_FF_DDFRFD;
	private JButton btn_clear_FFDDFRFD;
	private JButton btn_filtrar_DDFRFD;
	private JButton btnExportarTabla_DDFRFD;
	private JRadioButton rB_Intervalo_RT;
	private JRadioButton rB_Hoy_RT;
	private JRadioButton rB_USemana_RT;
	private JRadioButton rB_Mes_RT;
	private JRadioButton rB_MAnterior_RT;
	private JRadioButton rB_Anio_RT;
	private JDateChooser dC_FI_RT;
	private JButton btn_clear_FIRT;
	private JDateChooser dC_FF_RT;
	private JButton btn_clear_FFRT;
	private JButton btn_filtrar_RT;
	private JButton btnExportarTabla_RT;
	private JRadioButton rB_Anio_PLLPD;
	private JRadioButton rB_MAnterior_PLLPD;
	private JRadioButton rB_Mes_PLLPD;
	private JRadioButton rB_USemana_PLLPD;
	private JRadioButton rB_Hoy_PLLPD;
	private JRadioButton rB_Intervalo_PLLPD;
	private JDateChooser dC_FI_PLLPD;
	private JButton btn_clear_FIPLLPD;
	private JDateChooser dC_FF_PLLPD;
	private JButton btn_clear_FFPLLPD;
	private JButton btn_filtrar_PLLPD;
	private JButton btnExportarTabla_PLLPD;
	private JRadioButton rB_Anio_MO;
	private JRadioButton rB_MAnterior_MO;
	private JRadioButton rB_Mes_MO;
	private JRadioButton rB_USemana_MO;
	private JRadioButton rB_Hoy_MO;
	private JRadioButton rB_Intervalo_MO;
	private JDateChooser dC_FI_MO;
	private JButton btn_clear_FIMO;
	private JDateChooser dC_FF_MO;
	private JButton btn_clear_FFMO;
	private JButton btn_filtrar_MO;
	private JButton btnExportarTabla_MO;
	private JRadioButton rB_Anio_RC;
	private JRadioButton rB_MAnterior_RC;
	private JRadioButton rB_Mes_RC;
	private JRadioButton rB_USemana_RC;
	private JRadioButton rB_Hoy_RC;
	private JRadioButton rB_Intervalo_RC;
	private JDateChooser dC_FI_RC;
	private JButton btn_clear_FIRC;
	private JDateChooser dC_FF_RC;
	private JButton btn_clear_FFRC;
	private JButton btn_filtrar_RC;
	private JButton btnExportarTabla_RC;

	//tabla 
	public GUIReportesControl(MediadorReportes mediadorReporte) {
		setMediador(mediadorReporte);
		cargarDatos();
		initialize();
	}

	private void cargarDatos() {
		Vector<Pedido_PiezaDTO> pedidos_piezas = mediador.obtenerPedido_Piezas();
		int chico = 100;
		int mediano = 150;
		int grande = 230;
		//Tabla Dias Desde FSF//
		modelo_tabla_dias_desde_fsf = new DefaultTableModel();
		nombreColumnas_tabla_dias_desde_fsf = new Vector<String> ();
		anchos_tabla_dias_desde_fsf = new Vector<Integer>();
		nombreColumnas_tabla_dias_desde_fsf.add("ID Pedido");//0
		anchos_tabla_dias_desde_fsf.add(75);
		nombreColumnas_tabla_dias_desde_fsf.add("Numero Pedido");//1
		anchos_tabla_dias_desde_fsf.add(chico);
		nombreColumnas_tabla_dias_desde_fsf.add("Numero Pieza");//2
		anchos_tabla_dias_desde_fsf.add(chico);
		nombreColumnas_tabla_dias_desde_fsf.add("Descripcion");//3
		anchos_tabla_dias_desde_fsf.add(mediano);
		nombreColumnas_tabla_dias_desde_fsf.add("Numero Orden");//4
		anchos_tabla_dias_desde_fsf.add(chico);
		nombreColumnas_tabla_dias_desde_fsf.add("VIN");//5
		anchos_tabla_dias_desde_fsf.add(130);
		nombreColumnas_tabla_dias_desde_fsf.add("Registrante");//6
		anchos_tabla_dias_desde_fsf.add(grande);
		nombreColumnas_tabla_dias_desde_fsf.add("Fecha Solicitud Pedido");//7
		anchos_tabla_dias_desde_fsf.add(mediano);
		nombreColumnas_tabla_dias_desde_fsf.add("Fecha Solicitud Fabrica");//8
		anchos_tabla_dias_desde_fsf.add(mediano);
		nombreColumnas_tabla_dias_desde_fsf.add("Dias Desde FSF");//9
		anchos_tabla_dias_desde_fsf.add(chico);
		datosTabla_dias_desde_fsf = new Vector<Vector<String>>();
		//Tabla dias desde frf
		modelo_tabla_dias_desde_frf = new DefaultTableModel();
		nombreColumnas_tabla_dias_desde_frf = new Vector<String> ();
		anchos_tabla_dias_desde_frf = new Vector<Integer>();
		nombreColumnas_tabla_dias_desde_frf.add("ID Pedido");//0
		anchos_tabla_dias_desde_frf.add(75);
		nombreColumnas_tabla_dias_desde_frf.add("Numero Pedido");//1
		anchos_tabla_dias_desde_frf.add(chico);
		nombreColumnas_tabla_dias_desde_frf.add("Numero Pieza");//2
		anchos_tabla_dias_desde_frf.add(chico);
		nombreColumnas_tabla_dias_desde_frf.add("Descripcion");//3
		anchos_tabla_dias_desde_frf.add(mediano);
		nombreColumnas_tabla_dias_desde_frf.add("Numero Orden");//4
		anchos_tabla_dias_desde_frf.add(chico);
		nombreColumnas_tabla_dias_desde_frf.add("VIN");//5
		anchos_tabla_dias_desde_frf.add(130);
		nombreColumnas_tabla_dias_desde_frf.add("Registrante");//6
		anchos_tabla_dias_desde_frf.add(grande);
		nombreColumnas_tabla_dias_desde_frf.add("Fecha Solicitud Pedido");//7
		anchos_tabla_dias_desde_frf.add(mediano);
		nombreColumnas_tabla_dias_desde_frf.add("Fecha Solicitud Fabrica");//8
		anchos_tabla_dias_desde_frf.add(mediano);
		nombreColumnas_tabla_dias_desde_frf.add("Fecha Recepcion Fabrica");//9
		anchos_tabla_dias_desde_frf.add(mediano);
		nombreColumnas_tabla_dias_desde_frf.add("Dias Desde FRF");//10
		anchos_tabla_dias_desde_frf.add(chico);
		datosTabla_dias_desde_frf = new Vector<Vector<String>>();
		//Tabla dias desde fcierrre fturno
		modelo_tabla_dias_desde_fcierre_fturno = new DefaultTableModel();
		nombreColumnas_tabla_dias_desde_fcierre_fturno = new Vector<String> ();
		anchos_tabla_dias_desde_fcierre_fturno = new Vector<Integer>();
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("ID Pedido");//0
		anchos_tabla_dias_desde_fcierre_fturno.add(75);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Numero Pedido");//1
		anchos_tabla_dias_desde_fcierre_fturno.add(chico);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Numero Pieza");//2
		anchos_tabla_dias_desde_fcierre_fturno.add(chico);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Descripcion");//3
		anchos_tabla_dias_desde_fcierre_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Numero Orden");//4
		anchos_tabla_dias_desde_fcierre_fturno.add(chico);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("VIN");//5
		anchos_tabla_dias_desde_fcierre_fturno.add(130);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Registrante");//6
		anchos_tabla_dias_desde_fcierre_fturno.add(grande);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Fecha Solicitud Pedido");//7
		anchos_tabla_dias_desde_fcierre_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Fecha Turno");//8
		anchos_tabla_dias_desde_fcierre_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Fecha Cierre Orden");//9
		anchos_tabla_dias_desde_fcierre_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_fcierre_fturno.add("Dias Desde Turno a Cierre Orden");//10
		anchos_tabla_dias_desde_fcierre_fturno.add(grande);		
		datosTabla_dias_desde_fcierre_fturno = new Vector<Vector<String>>();
		//Tabla dias desde frecurso fcierrre
		modelo_tabla_dias_desde_frecurso_fcierre = new DefaultTableModel();
		nombreColumnas_tabla_dias_desde_frecurso_fcierre = new Vector<String> ();
		anchos_tabla_dias_desde_frecurso_fcierre = new Vector<Integer>();
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("ID Pedido");//0
		anchos_tabla_dias_desde_frecurso_fcierre.add(75);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Numero Pedido");//1
		anchos_tabla_dias_desde_frecurso_fcierre.add(chico);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Numero Pieza");//2
		anchos_tabla_dias_desde_frecurso_fcierre.add(chico);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Descripcion");//3
		anchos_tabla_dias_desde_frecurso_fcierre.add(mediano);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Numero Orden");//4
		anchos_tabla_dias_desde_frecurso_fcierre.add(chico);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("VIN");//5
		anchos_tabla_dias_desde_frecurso_fcierre.add(130);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Registrante");//6
		anchos_tabla_dias_desde_frecurso_fcierre.add(grande);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Fecha Solicitud Pedido");//7
		anchos_tabla_dias_desde_frecurso_fcierre.add(mediano);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Fecha Recurso");//8
		anchos_tabla_dias_desde_frecurso_fcierre.add(mediano);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Fecha Cierre Orden");//9
		anchos_tabla_dias_desde_frecurso_fcierre.add(mediano);
		nombreColumnas_tabla_dias_desde_frecurso_fcierre.add("Dias Desde Recurso a Cierre Orden");//
		anchos_tabla_dias_desde_frecurso_fcierre.add(grande);
		datosTabla_dias_desde_frecurso_fcierre = new Vector<Vector<String>>();
		//TABLA DIAS DESDE FRF A FTURNO //
		modelo_tabla_dias_desde_frf_fturno = new DefaultTableModel();
		nombreColumnas_tabla_dias_desde_frf_fturno = new Vector<String> ();
		anchos_tabla_dias_desde_frf_fturno = new Vector<Integer>();
		nombreColumnas_tabla_dias_desde_frf_fturno.add("ID Pedido");//0
		anchos_tabla_dias_desde_frf_fturno.add(75);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Numero Pedido");//1
		anchos_tabla_dias_desde_frf_fturno.add(chico);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Numero Pieza");//2
		anchos_tabla_dias_desde_frf_fturno.add(chico);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Descripcion");//3
		anchos_tabla_dias_desde_frf_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Numero Orden");//4
		anchos_tabla_dias_desde_frf_fturno.add(chico);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("VIN");//5
		anchos_tabla_dias_desde_frf_fturno.add(130);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Registrante");//6
		anchos_tabla_dias_desde_frf_fturno.add(grande);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Fecha Solicitud Pedido");//7
		anchos_tabla_dias_desde_frf_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Fecha Turno");//8
		anchos_tabla_dias_desde_frf_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Fecha Solicitud Fabrica");//9
		anchos_tabla_dias_desde_frf_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Fecha Recepcion Fabrica");//10
		anchos_tabla_dias_desde_frf_fturno.add(mediano);
		nombreColumnas_tabla_dias_desde_frf_fturno.add("Dias Desde Recepcion Fabrica a Fecha Turno");//11
		anchos_tabla_dias_desde_frf_fturno.add(grande);
		datosTabla_dias_desde_frf_fturno = new Vector<Vector<String>>();
		//TABLA DIAS DESDE FRECLAMO A FDEVOLUCION//
		modelo_tabla_dias_desde_freclamo_fdevolucion = new DefaultTableModel();
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion = new Vector<String> ();
		anchos_tabla_dias_desde_freclamo_fdevolucion = new Vector<Integer>();
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("ID Pedido");//0
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(75);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Numero Pedido");//1
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(chico);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Numero Pieza");//2
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(chico);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Descripcion");//3
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(mediano);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Numero Orden");//4
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(chico);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("VIN");//5
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(130);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Registrante");//6
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(grande);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Fecha Reclamo");//7
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(mediano);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Fecha Pedido a Fabrica");//8
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(mediano);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Fecha Recepcion de Fabrica");//9
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(mediano);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Fecha Devolucion a Fabrica");//10
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(mediano);
		nombreColumnas_tabla_dias_desde_freclamo_fdevolucion.add("Dias Desde Fecha Reclamo a Fecha Devolucion");//11
		anchos_tabla_dias_desde_freclamo_fdevolucion.add(grande);		
		datosTabla_dias_desde_freclamo_fdevolucion = new Vector<Vector<String>>();
		//TABLA RECLAMOS TURNOS//
		modelo_tabla_reclamos_turno_Reclamos = new DefaultTableModel();
		nombreColumnas_tabla_reclamos_turno_Reclamos = new Vector<String> ();
		anchos_tabla_reclamos_turno_Reclamos = new Vector<Integer>();
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("ID Pedido");//0
		anchos_tabla_reclamos_turno_Reclamos.add(75);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Numero Pedido");//1
		anchos_tabla_reclamos_turno_Reclamos.add(chico);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Numero Pieza");//2
		anchos_tabla_reclamos_turno_Reclamos.add(chico);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Descripcion");//3
		anchos_tabla_reclamos_turno_Reclamos.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Numero Orden");//4
		anchos_tabla_reclamos_turno_Reclamos.add(chico);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("VIN");//5
		anchos_tabla_reclamos_turno_Reclamos.add(130);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Registrante");//6
		anchos_tabla_reclamos_turno_Reclamos.add(grande);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Fecha Reclamo");//7
		anchos_tabla_reclamos_turno_Reclamos.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Fecha Turno");//8
		anchos_tabla_reclamos_turno_Reclamos.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Fecha Solicitud a Fabrica");//9
		anchos_tabla_reclamos_turno_Reclamos.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Fecha Recepcion de Fabrica");//10
		anchos_tabla_reclamos_turno_Reclamos.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Reclamos.add("Fecha Devolucion a Fabrica");//11
		anchos_tabla_reclamos_turno_Reclamos.add(mediano);
		datos_tabla_reclamos_turno_Reclamos = new Vector<Vector<String>>();
		modelo_tabla_reclamos_turno_Turno = new DefaultTableModel();
		nombreColumnas_tabla_reclamos_turno_Turno = new Vector<String> ();
		anchos_tabla_reclamos_turno_Turno = new Vector<Integer>();
		nombreColumnas_tabla_reclamos_turno_Turno.add("ID Pedido");//0
		anchos_tabla_reclamos_turno_Turno.add(75);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Numero Pedido");//1
		anchos_tabla_reclamos_turno_Turno.add(chico);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Numero Pieza");//2
		anchos_tabla_reclamos_turno_Turno.add(chico);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Descripcion");//3
		anchos_tabla_reclamos_turno_Turno.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Numero Orden");//4
		anchos_tabla_reclamos_turno_Turno.add(chico);
		nombreColumnas_tabla_reclamos_turno_Turno.add("VIN");//5
		anchos_tabla_reclamos_turno_Turno.add(130);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Registrante");//6
		anchos_tabla_reclamos_turno_Turno.add(grande);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Fecha Reclamo");//7
		anchos_tabla_reclamos_turno_Turno.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Fecha Turno");//8
		anchos_tabla_reclamos_turno_Turno.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Fecha Solicitud a Fabrica");//9
		anchos_tabla_reclamos_turno_Turno.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Fecha Recepcion de Fabrica");//10
		anchos_tabla_reclamos_turno_Turno.add(mediano);
		nombreColumnas_tabla_reclamos_turno_Turno.add("Fecha Devolucion a Fabrica");//11
		anchos_tabla_reclamos_turno_Turno.add(mediano);	
		datos_tabla_reclamos_turno_Turno = new Vector<Vector<String>>();
		numero_reclamos = 0;
		numero_turnos = 0;
		//TABLA PIEZAS LLEGADAS PIEZAS DEVUELTAS//
		modelo_tabla_Pllegadas_PDevuletas_LLegadas = new DefaultTableModel();
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas = new Vector<String> ();
		anchos_tabla_Pllegadas_PDevuletas_LLegadas = new Vector<Integer>();
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("ID Pedido");//0
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(75);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Numero Pedido");//1
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(chico);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Numero Pieza");//2
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(chico);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Descripcion");//3
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Numero Orden");//4
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(chico);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("VIN");//5
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(130);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Registrante");//6
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(grande);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Fecha Reclamo");//7
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Fecha Turno");//8
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Fecha Solicitud a Fabrica");//9
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Fecha Recepcion de Fabrica");//10
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas.add("Fecha Devolucion a Fabrica");//11
		anchos_tabla_Pllegadas_PDevuletas_LLegadas.add(mediano);
		datos_tabla_Pllegadas_PDevuletas_LLegadas = new Vector<Vector<String>>();
		modelo_tabla_Pllegadas_PDevuletas_Devueltas = new DefaultTableModel();
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas = new Vector<String> ();
		anchos_tabla_Pllegadas_PDevuletas_Devueltas = new Vector<Integer>();
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("ID Pedido");//0
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(75);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Numero Pedido");//1
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(chico);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Numero Pieza");//2
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(chico);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Descripcion");//3
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Numero Orden");//4
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(chico);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("VIN");//5
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(130);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Registrante");//6
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(grande);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Fecha Reclamo");//7
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Fecha Turno");//8
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Fecha Solicitud a Fabrica");//9
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Fecha Recepcion de Fabrica");//10
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(mediano);
		nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas.add("Fecha Devolucion a Fabrica");//11
		anchos_tabla_Pllegadas_PDevuletas_Devueltas.add(mediano);
		datos_tabla_Pllegadas_PDevuletas_Devueltas = new Vector<Vector<String>>();
		numero_PiezasLlegadas = 0;
		numero_PiezasDevueltas = 0;
		modelo_tabla_mano_obra = new DefaultTableModel();
		nombreColumnas_tabla_mano_obra = new Vector<String> ();
		anchos_tabla_mano_obra = new Vector<Integer>();
		nombreColumnas_tabla_mano_obra.add("ID Pedido");//0
		anchos_tabla_mano_obra.add(75);
		nombreColumnas_tabla_mano_obra.add("Numero Pedido");//1
		anchos_tabla_mano_obra.add(chico);
		nombreColumnas_tabla_mano_obra.add("Numero Pieza");//2
		anchos_tabla_mano_obra.add(chico);
		nombreColumnas_tabla_mano_obra.add("Descripcion");//3
		anchos_tabla_mano_obra.add(mediano);
		nombreColumnas_tabla_mano_obra.add("Numero Orden");//4
		anchos_tabla_mano_obra.add(chico);
		nombreColumnas_tabla_mano_obra.add("VIN");//5
		anchos_tabla_mano_obra.add(130);
		nombreColumnas_tabla_mano_obra.add("Registrante");//6
		anchos_tabla_mano_obra.add(grande);
		nombreColumnas_tabla_mano_obra.add("ID Mano Obra");//8
		anchos_tabla_mano_obra.add(mediano);
		nombreColumnas_tabla_mano_obra.add("Codigo Mano Obra");//9
		anchos_tabla_mano_obra.add(mediano);
		nombreColumnas_tabla_mano_obra.add("Cantidad Mano Obra");//10
		anchos_tabla_mano_obra.add(mediano);
		nombreColumnas_tabla_mano_obra.add("Valor Mano Obra");//11
		anchos_tabla_mano_obra.add(mediano);	
		datos_tabla_mano_obra = new Vector<Vector<String>>();
		hs_mano_obra = 0;
		preico_mano_obra = 0;
		
		for (int i=0; i< pedidos_piezas.size();i++){
			cargarLineaDDFSF(pedidos_piezas.elementAt(i));
			cargarLineaDDFRF(pedidos_piezas.elementAt(i));
			cargarLineaDDFCFT(pedidos_piezas.elementAt(i));
			cargarLineaDDFRFC(pedidos_piezas.elementAt(i));
			cargarLineaDDFRFFT(pedidos_piezas.elementAt(i));
			cargarLineaDDFRFD(pedidos_piezas.elementAt(i));
			cargarLineaRT(pedidos_piezas.elementAt(i));
			cargarLineaPLLPD(pedidos_piezas.elementAt(i));
			cargarLineaMO(pedidos_piezas.elementAt(i));
		}
		modelo_tabla_dias_desde_fsf.setDataVector(datosTabla_dias_desde_fsf, nombreColumnas_tabla_dias_desde_fsf);
		modelo_tabla_dias_desde_fsf.fireTableStructureChanged();
		modelo_tabla_dias_desde_frf.setDataVector(datosTabla_dias_desde_frf, nombreColumnas_tabla_dias_desde_frf);
		modelo_tabla_dias_desde_frf.fireTableStructureChanged();
		modelo_tabla_dias_desde_fcierre_fturno.setDataVector(datosTabla_dias_desde_fcierre_fturno, nombreColumnas_tabla_dias_desde_fcierre_fturno);
		modelo_tabla_dias_desde_fcierre_fturno.fireTableStructureChanged();
		modelo_tabla_dias_desde_frecurso_fcierre.setDataVector(datosTabla_dias_desde_frecurso_fcierre, nombreColumnas_tabla_dias_desde_frecurso_fcierre);
		modelo_tabla_dias_desde_frecurso_fcierre.fireTableStructureChanged();
		modelo_tabla_dias_desde_frf_fturno.setDataVector(datosTabla_dias_desde_frf_fturno, nombreColumnas_tabla_dias_desde_frf_fturno);
		modelo_tabla_dias_desde_frf_fturno.fireTableStructureChanged();
		modelo_tabla_dias_desde_freclamo_fdevolucion.setDataVector(datosTabla_dias_desde_freclamo_fdevolucion, nombreColumnas_tabla_dias_desde_freclamo_fdevolucion);
		modelo_tabla_dias_desde_freclamo_fdevolucion.fireTableStructureChanged();
		modelo_tabla_reclamos_turno_Reclamos.setDataVector(datos_tabla_reclamos_turno_Reclamos, nombreColumnas_tabla_reclamos_turno_Reclamos);
		modelo_tabla_reclamos_turno_Reclamos.fireTableStructureChanged();
		modelo_tabla_reclamos_turno_Turno.setDataVector(datos_tabla_reclamos_turno_Turno, nombreColumnas_tabla_reclamos_turno_Turno);
		modelo_tabla_reclamos_turno_Turno.fireTableStructureChanged();
		modelo_tabla_Pllegadas_PDevuletas_Devueltas.setDataVector(datos_tabla_Pllegadas_PDevuletas_Devueltas, nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas);
		modelo_tabla_Pllegadas_PDevuletas_Devueltas.fireTableStructureChanged();
		modelo_tabla_Pllegadas_PDevuletas_LLegadas.setDataVector(datos_tabla_Pllegadas_PDevuletas_LLegadas, nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas);
		modelo_tabla_Pllegadas_PDevuletas_LLegadas.fireTableStructureChanged();
		modelo_tabla_mano_obra.setDataVector(datos_tabla_mano_obra, nombreColumnas_tabla_mano_obra);
		modelo_tabla_mano_obra.fireTableStructureChanged();
		//TABLA FECHA RECURSO - FECHA CIERRE///
		modelo_tabla_recurso_cierre = new DefaultTableModel();
		nombreColumnas_tabla_recurso_cierre = new Vector<String> ();
		anchos_tabla_recurso_cierre = new Vector<Integer>();
		nombreColumnas_tabla_recurso_cierre.add("ID Orden");//0
		anchos_tabla_recurso_cierre.add(mediano);
		nombreColumnas_tabla_recurso_cierre.add("ID Reclamo");//1
		anchos_tabla_recurso_cierre.add(mediano);
		nombreColumnas_tabla_recurso_cierre.add("Numero Orden");//3
		anchos_tabla_recurso_cierre.add(mediano);
		nombreColumnas_tabla_recurso_cierre.add("Fecha Apertura");//4
		anchos_tabla_recurso_cierre.add(mediano);
		nombreColumnas_tabla_recurso_cierre.add("Fecha Cierre Orden");//5
		anchos_tabla_recurso_cierre.add(mediano);
		nombreColumnas_tabla_recurso_cierre.add("ID Recurso");//6
		anchos_tabla_recurso_cierre.add(mediano);
		nombreColumnas_tabla_recurso_cierre.add("Numero Recurso");//7
		anchos_tabla_recurso_cierre.add(mediano);
		nombreColumnas_tabla_recurso_cierre.add("Fecha Recurso");//8
		anchos_tabla_recurso_cierre.add(mediano);
		nombreColumnas_tabla_recurso_cierre.add("Dias Desde Fecha Cierre Orden a Fecha Recurso");//9
		anchos_tabla_recurso_cierre.add(grande);
		datos_tabla_recurso_cierre = new Vector<Vector<String>>();
		Vector<ReclamoDTO> reclamos = mediador.obtenerReclamos();
		setN_casos(reclamos.size());
		setSum_frecurso_fcierre(0);
		SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
		for (int i=0; i< reclamos.size();i++){
			Vector<String> row = new Vector<String> ();
			row.add(reclamos.elementAt(i).getOrden().getId().toString());//ID Orden
			row.add(reclamos.elementAt(i).getId().toString());//ID Orden
			row.add(reclamos.elementAt(i).getOrden().getNumero_orden());//Numero Orden
			java.sql.Date fapertura = null;
			if(reclamos.elementAt(i).getOrden().getFecha_apertura()!=null){
				fapertura = new java.sql.Date(reclamos.elementAt(i).getOrden().getFecha_apertura().getTime());
				row.add(format2.format(fapertura));//Fecha Apertura
			}else{
				row.add("");
			}
			java.sql.Date fcierre = null;
			if(reclamos.elementAt(i).getOrden().getFecha_cierre()!=null){
				fcierre = new java.sql.Date(reclamos.elementAt(i).getOrden().getFecha_cierre().getTime());
				row.add(format2.format(fcierre));//Fecha Cierre
			}else{
				row.add("");
			}
			java.sql.Date frecurso = null;
			if(reclamos.elementAt(i).getOrden().getRecurso()!=null){
				row.add(reclamos.elementAt(i).getOrden().getRecurso().getId().toString());//ID Recurso
				row.add(reclamos.elementAt(i).getOrden().getRecurso().getNumero_recurso());//numero Recurso
				
				frecurso = new java.sql.Date(reclamos.elementAt(i).getOrden().getRecurso().getFecha_recurso().getTime());
				row.add(format2.format(frecurso));//Fecha Recurso
			}
			if(fcierre!=null && frecurso!=null){
				final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
				@SuppressWarnings("deprecation")
				Calendar calendar = new GregorianCalendar(frecurso.getYear(), frecurso.getMonth()-1, frecurso.getDay());
				long diferencia = (frecurso.getTime() - fcierre.getTime())/MILLSECS_PER_DAY;
				row.add(String.valueOf(diferencia));
				setSum_frecurso_fcierre(getSum_frecurso_fcierre() + diferencia);
			}else{
				row.add("");
			}
			datos_tabla_recurso_cierre.add(row);
		}
		modelo_tabla_recurso_cierre.setDataVector(datos_tabla_recurso_cierre, nombreColumnas_tabla_recurso_cierre);
		modelo_tabla_recurso_cierre.fireTableStructureChanged();
		////////////////// FIN	/////////////////
	}
	
	private void cargarLineaDDFSF(Pedido_PiezaDTO pedido_pieza){
		if(pedido_pieza.getFecha_solicitud_fabrica()!=null && pedido_pieza.getFecha_recepcion_fabrica()==null){
			Vector<String> row = new Vector<String> ();
			row.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
			row.add(pedido_pieza.getNumero_pedido());//Numero Pedido
			row.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
			row.add(pedido_pieza.getPieza().getDescripcion());//Descripcion Pieza
			row.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
			row.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			if(pedido_pieza.getPedido().getFecha_solicitud_pedido()!=null){
				java.sql.Date fsp = new java.sql.Date(pedido_pieza.getPedido().getFecha_solicitud_pedido().getTime());
				row.add(format2.format(fsp));//Fecha Solicitud Pedido
			}else{
				row.add("");
			}
			if (pedido_pieza.getFecha_solicitud_fabrica()!=null){
				java.sql.Date fsf = new java.sql.Date(pedido_pieza.getFecha_solicitud_fabrica().getTime());
				row.add(format2.format(fsf));//Fecha Solicitud Fabrica
				final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
				java.util.Date hoy = new Date(); //Fecha de hoy 
				@SuppressWarnings("deprecation")
				Calendar calendar = new GregorianCalendar(fsf.getYear(), fsf.getMonth()-1, fsf.getDay());
				long diferencia = ( hoy.getTime() - fsf.getTime() )/MILLSECS_PER_DAY;
				row.add(String.valueOf(diferencia));	
			}else{
				row.add("");
				row.add("");
			}
			datosTabla_dias_desde_fsf.add(row);
		}
	}
	private void cargarLineaDDFRF(Pedido_PiezaDTO pedido_pieza){
		boolean resp = false;
		if (mediador.esEntidad(pedido_pieza.getPedido().getReclamo().getRegistrante())){
			resp = pedido_pieza.getFecha_recepcion_fabrica()!=null && pedido_pieza.getDevolucion_pieza()==null && pedido_pieza.getFecha_solicitud_fabrica()!=null;
		}else{
			resp = pedido_pieza.getFecha_recepcion_fabrica()!=null && pedido_pieza.getFecha_envio_agente()==null  && pedido_pieza.getDevolucion_pieza()==null && pedido_pieza.getFecha_solicitud_fabrica()!=null;
		}	
		if(resp){
				Vector<String> row = new Vector<String> ();
				row.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
				row.add(pedido_pieza.getNumero_pedido());//Numero Pedido
				row.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
				row.add(pedido_pieza.getPieza().getDescripcion());//Descripcion Pieza
				row.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
				row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
				row.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
				if(pedido_pieza.getPedido().getFecha_solicitud_pedido()!=null){
					java.sql.Date fsp = new java.sql.Date(pedido_pieza.getPedido().getFecha_solicitud_pedido().getTime());
					row.add(format2.format(fsp));//Fecha Solicitud Pedido
				}else{
					row.add("");
				}
				if (pedido_pieza.getFecha_solicitud_fabrica()!=null){
					java.sql.Date fsf = new java.sql.Date(pedido_pieza.getFecha_solicitud_fabrica().getTime());
					row.add(format2.format(fsf));//Fecha Solicitud Fabrica
				}else{
					row.add("");
				}
				if(pedido_pieza.getFecha_recepcion_fabrica()!=null){
					java.sql.Date frf = new java.sql.Date(pedido_pieza.getFecha_recepcion_fabrica().getTime());
					row.add(format2.format(frf));//Fecha Recepcion Fabrica
					final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
					java.util.Date hoy = new Date(); //Fecha de hoy 
					@SuppressWarnings("deprecation")
					Calendar calendar = new GregorianCalendar(frf.getYear(), frf.getMonth()-1, frf.getDay());
					long diferencia = ( hoy.getTime() - frf.getTime() )/MILLSECS_PER_DAY;
					row.add(String.valueOf(diferencia));
				}else{
					row.add("");
					row.add("");
				}	
				datosTabla_dias_desde_frf.add(row);
			}
	}
	private void cargarLineaDDFCFT(Pedido_PiezaDTO pedido_pieza){
		if(pedido_pieza.getPedido().getReclamo().getOrden().getFecha_cierre()!=null && (pedido_pieza.getPedido().getReclamo().getFecha_turno()!=null)){
			Vector<String> row = new Vector<String> ();
			row.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
			row.add(pedido_pieza.getNumero_pedido());//Numero Pedido
			row.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
			row.add(pedido_pieza.getPieza().getDescripcion());//Descripcion Pieza
			row.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
			row.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			if(pedido_pieza.getPedido().getFecha_solicitud_pedido()!=null){
				java.sql.Date fsp = new java.sql.Date(pedido_pieza.getPedido().getFecha_solicitud_pedido().getTime());
				row.add(format2.format(fsp));//Fecha Solicitud Pedido
			}else{
				row.add("");
			}			    
			java.sql.Date fturno = null;
			if (pedido_pieza.getPedido().getReclamo().getFecha_turno()!=null){
				fturno = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getFecha_turno().getTime());
				row.add(format2.format(fturno));//Fecha Turno
			}else{
				row.add("");
			}
			java.sql.Date fcierre = null;
			if (pedido_pieza.getPedido().getReclamo().getOrden().getFecha_cierre()!=null){
				fcierre = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getOrden().getFecha_cierre().getTime());
				row.add(format2.format(fcierre));//Fecha Cierre
			}else{
				row.add("");
			}
			if(fturno!=null && fcierre!=null){
				final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
				@SuppressWarnings("deprecation")
				Calendar calendar = new GregorianCalendar(fturno.getYear(), fturno.getMonth()-1, fturno.getDay());
				long diferencia = ( fcierre.getTime() - fturno.getTime() )/MILLSECS_PER_DAY;
				row.add(String.valueOf(diferencia));
			}else{
				row.add("");
			}	
			datosTabla_dias_desde_fcierre_fturno.add(row);
		}
	}
	private void cargarLineaDDFRFC(Pedido_PiezaDTO pedido_pieza){
		if(pedido_pieza.getPedido().getReclamo().getOrden().getFecha_cierre()!=null && pedido_pieza.getPedido().getReclamo().getOrden()!=null && pedido_pieza.getPedido().getReclamo().getOrden().getRecurso()!=null && pedido_pieza.getPedido().getReclamo().getOrden().getRecurso().getFecha_recurso()!=null ){
			Vector<String> row = new Vector<String> ();			
			row.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
			row.add(pedido_pieza.getNumero_pedido());//Numero Pedido
			row.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
			row.add(pedido_pieza.getPieza().getDescripcion());//Descripcion Pieza
			row.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
			row.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 

			if(pedido_pieza.getPedido().getFecha_solicitud_pedido()!=null){
				java.sql.Date fsp = new java.sql.Date(pedido_pieza.getPedido().getFecha_solicitud_pedido().getTime());
				row.add(format2.format(fsp));//Fecha Solicitud Pedido
			}else{
				row.add("");
			}			    
			java.sql.Date frecurso = null;
			if (pedido_pieza.getPedido().getReclamo().getOrden().getRecurso().getFecha_recurso()!=null){
				frecurso = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getOrden().getRecurso().getFecha_recurso().getTime());
				row.add(format2.format(frecurso));//Fecha recurso
			}else{
				row.add("");
			}
			java.sql.Date fcierre = null;
			if (pedido_pieza.getPedido().getReclamo().getOrden().getFecha_cierre()!=null){
				fcierre = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getOrden().getFecha_cierre().getTime());
				row.add(format2.format(fcierre));//Fecha Cierre
			}else{
				row.add("");
			}
			if(frecurso!=null && fcierre!=null){
				final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
				@SuppressWarnings("deprecation")
				Calendar calendar = new GregorianCalendar(frecurso.getYear(), frecurso.getMonth()-1, frecurso.getDay());
				long diferencia = ( fcierre.getTime() - frecurso.getTime() )/MILLSECS_PER_DAY;
				row.add(String.valueOf(diferencia));
			}else{
				row.add("");
			}	
			datosTabla_dias_desde_frecurso_fcierre.add(row);
		}
	}
	private void cargarLineaDDFRFFT(Pedido_PiezaDTO pedido_pieza){
		boolean resp = false;
		if (mediador.esEntidad(pedido_pieza.getPedido().getReclamo().getRegistrante())){
			resp = pedido_pieza.getFecha_recepcion_fabrica()!=null && pedido_pieza.getDevolucion_pieza()==null && pedido_pieza.getFecha_solicitud_fabrica()!=null && pedido_pieza.getPedido().getReclamo().getFecha_turno()!=null;
		}	
		if(resp){
			
			Vector<String> row = new Vector<String> ();

			row.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
			row.add(pedido_pieza.getNumero_pedido());//Numero Pedido
			row.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
			row.add(pedido_pieza.getPieza().getDescripcion());//Descripcion Pieza
			row.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
			row.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 

			if(pedido_pieza.getPedido().getFecha_solicitud_pedido()!=null){
				java.sql.Date fsp = new java.sql.Date(pedido_pieza.getPedido().getFecha_solicitud_pedido().getTime());
				row.add(format2.format(fsp));//Fecha Solicitud Pedido
			}else{
				row.add("");
			}			    
			java.sql.Date fturno = null;
			if (pedido_pieza.getPedido().getReclamo().getFecha_turno()!=null){
				fturno = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getFecha_turno().getTime());
				row.add(format2.format(fturno));//Fecha Turno
			}else{
				row.add("");
			}
			java.sql.Date fsf = null;
			if (pedido_pieza.getFecha_solicitud_fabrica()!=null){
				fsf = new java.sql.Date(pedido_pieza.getFecha_solicitud_fabrica().getTime());
				row.add(format2.format(fsf));//Fecha solicitud Fabrica
			}else{
				row.add("");
			}
			java.sql.Date frf = null;
			if (pedido_pieza.getFecha_recepcion_fabrica()!=null){
				frf = new java.sql.Date(pedido_pieza.getFecha_recepcion_fabrica().getTime());
				row.add(format2.format(frf));//Fecha Recepcion Fabrica
			}else{
				row.add("");
			}
			if(fturno!=null && frf!=null){
				final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
				@SuppressWarnings("deprecation")
				Calendar calendar = new GregorianCalendar(fturno.getYear(), fturno.getMonth()-1, fturno.getDay());
				long diferencia = (fturno.getTime() -  frf.getTime() )/MILLSECS_PER_DAY;
				row.add(String.valueOf(diferencia));
			}else{
				row.add("");
			}	
			datosTabla_dias_desde_frf_fturno.add(row);
		}
	}
	private void cargarLineaDDFRFD(Pedido_PiezaDTO pedido_pieza){
		if(pedido_pieza.getPedido().getReclamo().getFecha_reclamo()!=null && pedido_pieza.getFecha_solicitud_fabrica()!=null && 
				pedido_pieza.getFecha_recepcion_fabrica()!=null && pedido_pieza.getDevolucion_pieza()!=null && pedido_pieza.getDevolucion_pieza().getFecha_devolucion()!=null){
			Vector<String> row = new Vector<String> ();
			row.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
			row.add(pedido_pieza.getNumero_pedido());//Numero Pedido
			row.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
			row.add(pedido_pieza.getPieza().getDescripcion());//Descripcion Pieza
			row.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
			row.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			java.sql.Date freclamo = null;
			if(pedido_pieza.getPedido().getReclamo().getFecha_reclamo()!=null){
				freclamo = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getFecha_reclamo().getTime());
				row.add(format2.format(freclamo));//Fecha Reclamo
			}else{
				row.add("");
			}			    
			if (pedido_pieza.getFecha_solicitud_fabrica()!=null){
				java.sql.Date fsf = new java.sql.Date(pedido_pieza.getFecha_solicitud_fabrica().getTime());
				row.add(format2.format(fsf));//Fecha Pedido Fabrica 
			}else{
				row.add("");
			}
			if (pedido_pieza.getFecha_recepcion_fabrica()!=null){
				java.sql.Date frf = new java.sql.Date(pedido_pieza.getFecha_recepcion_fabrica().getTime());
				row.add(format2.format(frf));//Fecha Recepcion Fabrica 
			}else{
				row.add("");
			}
			java.sql.Date fdf = null;
			if (pedido_pieza.getDevolucion_pieza().getFecha_devolucion()!=null){
				fdf = new java.sql.Date(pedido_pieza.getDevolucion_pieza().getFecha_devolucion().getTime());
				row.add(format2.format(fdf));//Fecha Devolucion Fabrica 
			}else{
				row.add("");
			}
			if(fdf!=null && freclamo!=null){
				final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
				@SuppressWarnings("deprecation")
				Calendar calendar = new GregorianCalendar(fdf.getYear(), fdf.getMonth()-1, fdf.getDay());
				long diferencia = (fdf.getTime() - freclamo.getTime())/MILLSECS_PER_DAY;
				row.add(String.valueOf(diferencia));
			}else{
				row.add("");
			}	
			datosTabla_dias_desde_freclamo_fdevolucion.add(row);
		}
	}
	private void cargarLineaRT(Pedido_PiezaDTO pedido_pieza){
		if(mediador.esEntidad(pedido_pieza.getPedido().getReclamo().getRegistrante())){
			Vector<String> rowReclamo = new Vector<String> ();
			Vector<String> rowTurno = new Vector<String> ();
			if(pedido_pieza.getPedido().getReclamo().getFecha_turno()!=null){
				rowReclamo.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
				rowTurno.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
				rowReclamo.add(pedido_pieza.getNumero_pedido());//Numero Pedido
				rowTurno.add(pedido_pieza.getNumero_pedido());//Numero Pedido
				rowReclamo.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
				rowTurno.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
				rowReclamo.add(pedido_pieza.getPieza().getDescripcion());//Descripcion Pieza
				rowTurno.add(pedido_pieza.getPieza().getDescripcion());//Descripcion Pieza
				rowReclamo.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
				rowTurno.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
				rowReclamo.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
				rowTurno.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
				rowReclamo.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
				rowTurno.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
				if(pedido_pieza.getPedido().getReclamo().getFecha_reclamo()!=null){
					java.sql.Date fr = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getFecha_reclamo().getTime());
					rowReclamo.add(format2.format(fr));//Fecha Reclamo
					rowTurno.add(format2.format(fr));//Fecha Reclamo
				}else{
					rowReclamo.add("");
					rowTurno.add("");
				}			    
				java.sql.Date fturno = null;
				if (pedido_pieza.getPedido().getReclamo().getFecha_turno()!=null){
					fturno = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getFecha_turno().getTime());
					rowReclamo.add(format2.format(fturno));//Fecha Turno
					rowTurno.add(format2.format(fturno));//Fecha Turno
				}else{
					rowReclamo.add("");
					rowTurno.add("");
				}
				java.sql.Date fsf = null;
				if (pedido_pieza.getFecha_solicitud_fabrica()!=null){
					fsf = new java.sql.Date(pedido_pieza.getFecha_solicitud_fabrica().getTime());
					rowReclamo.add(format2.format(fsf));//Fecha solicitud Fabrica
					rowTurno.add(format2.format(fsf));//Fecha solicitud Fabrica
				}else{
					rowReclamo.add("");
					rowTurno.add("");
				}
				java.sql.Date frf = null;
				if (pedido_pieza.getFecha_recepcion_fabrica()!=null){
					frf = new java.sql.Date(pedido_pieza.getFecha_recepcion_fabrica().getTime());
					rowReclamo.add(format2.format(frf));//Fecha Recepcion Fabrica
					rowTurno.add(format2.format(frf));//Fecha Recepcion Fabrica
				}else{
					rowReclamo.add("");
					rowTurno.add("");
				}
				java.sql.Date fdf = null;
				if (pedido_pieza.getDevolucion_pieza()!=null && pedido_pieza.getDevolucion_pieza().getFecha_devolucion()!=null){
					fdf = new java.sql.Date(pedido_pieza.getDevolucion_pieza().getFecha_devolucion().getTime());
					rowReclamo.add(format2.format(fdf));//Fecha Devolucion Fabrica 
					rowTurno.add(format2.format(fdf));//Fecha Devolucion Fabrica 
				}else{
					rowReclamo.add("");
					rowTurno.add("");
				}
				numero_reclamos++;
				numero_turnos++;
				datos_tabla_reclamos_turno_Turno.add(rowTurno);
				datos_tabla_reclamos_turno_Reclamos.add(rowReclamo);
			}else{
				rowReclamo.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
				rowReclamo.add(pedido_pieza.getNumero_pedido());//Numero Pedido
				rowReclamo.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
				rowReclamo.add(pedido_pieza.getPieza().getDescripcion());//Descripcion Pieza
				rowReclamo.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
				rowReclamo.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
				rowReclamo.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
				if(pedido_pieza.getPedido().getReclamo().getFecha_reclamo()!=null){
					java.sql.Date fr = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getFecha_reclamo().getTime());
					rowReclamo.add(format2.format(fr));//Fecha Reclamo
				}else{
					rowReclamo.add("");
				}			    
				java.sql.Date fturno = null;
				if (pedido_pieza.getPedido().getReclamo().getFecha_turno()!=null){
					fturno = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getFecha_turno().getTime());
					rowReclamo.add(format2.format(fturno));//Fecha Turno
				}else{
					rowReclamo.add("");
				}
				java.sql.Date fsf = null;
				if (pedido_pieza.getFecha_solicitud_fabrica()!=null){
					fsf = new java.sql.Date(pedido_pieza.getFecha_solicitud_fabrica().getTime());
					rowReclamo.add(format2.format(fsf));//Fecha solicitud Fabrica
				}else{
					rowReclamo.add("");
				}
				java.sql.Date frf = null;
				if (pedido_pieza.getFecha_recepcion_fabrica()!=null){
					frf = new java.sql.Date(pedido_pieza.getFecha_recepcion_fabrica().getTime());
					rowReclamo.add(format2.format(frf));//Fecha Recepcion Fabrica
				}else{
					rowReclamo.add("");
				}
				java.sql.Date fdf = null;
				if (pedido_pieza.getDevolucion_pieza()!=null && pedido_pieza.getDevolucion_pieza().getFecha_devolucion()!=null){
					fdf = new java.sql.Date(pedido_pieza.getDevolucion_pieza().getFecha_devolucion().getTime());
					rowReclamo.add(format2.format(fdf));//Fecha Devolucion Fabrica 
				}else{
					rowReclamo.add("");
				}
				numero_reclamos++;
				datos_tabla_reclamos_turno_Reclamos.add(rowReclamo);
			}
		}
	}
	private void cargarLineaPLLPD(Pedido_PiezaDTO pedido_pieza){
		Vector<String> rowPLlegadas = new Vector<String> ();
		if(pedido_pieza.getFecha_recepcion_fabrica()!=null){
			rowPLlegadas.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
			rowPLlegadas.add(pedido_pieza.getNumero_pedido());//Numero Pedido
			rowPLlegadas.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
			rowPLlegadas.add(pedido_pieza.getPieza().getDescripcion());//Descripcion Pieza
			rowPLlegadas.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			rowPLlegadas.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
			rowPLlegadas.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 

			if(pedido_pieza.getPedido().getReclamo().getFecha_reclamo()!=null){
				java.sql.Date fr = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getFecha_reclamo().getTime());
				rowPLlegadas.add(format2.format(fr));//Fecha Reclamo
			}else{
				rowPLlegadas.add("");
			}			    
			java.sql.Date fturno = null;
			if (pedido_pieza.getPedido().getReclamo().getFecha_turno()!=null){
				fturno = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getFecha_turno().getTime());
				rowPLlegadas.add(format2.format(fturno));//Fecha Turno
			}else{
				rowPLlegadas.add("");
			}
			java.sql.Date fsf = null;
			if (pedido_pieza.getFecha_solicitud_fabrica()!=null){
				fsf = new java.sql.Date(pedido_pieza.getFecha_solicitud_fabrica().getTime());
				rowPLlegadas.add(format2.format(fsf));//Fecha solicitud Fabrica
			}else{
				rowPLlegadas.add("");
			}
			java.sql.Date frf = null;
			if (pedido_pieza.getFecha_recepcion_fabrica()!=null){
				frf = new java.sql.Date(pedido_pieza.getFecha_recepcion_fabrica().getTime());
				rowPLlegadas.add(format2.format(frf));//Fecha Recepcion Fabrica
			}else{
				rowPLlegadas.add("");
			}
			java.sql.Date fdf = null;
			if (pedido_pieza.getDevolucion_pieza()!=null && pedido_pieza.getDevolucion_pieza().getFecha_devolucion()!=null){
				fdf = new java.sql.Date(pedido_pieza.getDevolucion_pieza().getFecha_devolucion().getTime());
				rowPLlegadas.add(format2.format(fdf));//Fecha Devolucion Fabrica 
			}else{
				rowPLlegadas.add("");
			}
			datos_tabla_Pllegadas_PDevuletas_LLegadas.add(rowPLlegadas);
			numero_PiezasLlegadas++;
		}
		Vector<String> rowPDevueltas = new Vector<String> ();
		if(pedido_pieza.getDevolucion_pieza()!=null && pedido_pieza.getDevolucion_pieza().getFecha_devolucion()!=null){
			rowPDevueltas.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
			rowPDevueltas.add(pedido_pieza.getNumero_pedido());//Numero Pedido
			rowPDevueltas.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
			rowPDevueltas.add(pedido_pieza.getPieza().getDescripcion());//Descripcion Pieza
			rowPDevueltas.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			rowPDevueltas.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
			rowPDevueltas.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 

			if(pedido_pieza.getPedido().getReclamo().getFecha_reclamo()!=null){
				java.sql.Date fr = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getFecha_reclamo().getTime());
				rowPDevueltas.add(format2.format(fr));//Fecha Reclamo
			}else{
				rowPDevueltas.add("");
			}			    
			java.sql.Date fturno = null;
			if (pedido_pieza.getPedido().getReclamo().getFecha_turno()!=null){
				fturno = new java.sql.Date(pedido_pieza.getPedido().getReclamo().getFecha_turno().getTime());
				rowPDevueltas.add(format2.format(fturno));//Fecha Turno
			}else{
				rowPDevueltas.add("");
			}
			java.sql.Date fsf = null;
			if (pedido_pieza.getFecha_solicitud_fabrica()!=null){
				fsf = new java.sql.Date(pedido_pieza.getFecha_solicitud_fabrica().getTime());
				rowPDevueltas.add(format2.format(fsf));//Fecha solicitud Fabrica
			}else{
				rowPDevueltas.add("");
			}
			java.sql.Date frf = null;
			if (pedido_pieza.getFecha_recepcion_fabrica()!=null){
				frf = new java.sql.Date(pedido_pieza.getFecha_recepcion_fabrica().getTime());
				rowPDevueltas.add(format2.format(frf));//Fecha Recepcion Fabrica
			}else{
				rowPDevueltas.add("");
			}
			java.sql.Date fdf = null;
			if (pedido_pieza.getDevolucion_pieza()!=null && pedido_pieza.getDevolucion_pieza().getFecha_devolucion()!=null){
				fdf = new java.sql.Date(pedido_pieza.getDevolucion_pieza().getFecha_devolucion().getTime());
				rowPDevueltas.add(format2.format(fdf));//Fecha Devolucion Fabrica 
			}else{
				rowPDevueltas.add("");
			}
			datos_tabla_Pllegadas_PDevuletas_Devueltas.add(rowPDevueltas);
			numero_PiezasDevueltas++;
		}
	}
	private void cargarLineaMO(Pedido_PiezaDTO pedido_pieza){
		if(pedido_pieza.getMano_obra()!=null && mediador.esEntidad(pedido_pieza.getPedido().getReclamo().getRegistrante())){		
			Vector<String> row = new Vector<String> ();
			row.add(pedido_pieza.getPedido().getId().toString());//ID Pedido
			row.add(pedido_pieza.getNumero_pedido());//Numero Pedido
			row.add(pedido_pieza.getPieza().getNumero_pieza());//Numero Pieza
			row.add(pedido_pieza.getPieza().getDescripcion());//Descripcion Pieza
			row.add(pedido_pieza.getPedido().getReclamo().getOrden().getNumero_orden());//Numero Orden
			row.add(pedido_pieza.getPedido().getReclamo().getVehiculo().getVin());//VIN
			row.add(pedido_pieza.getPedido().getReclamo().getRegistrante().getNombre_registrante());//Registrante
			if (pedido_pieza.getMano_obra()!=null){
				row.add(pedido_pieza.getMano_obra().getId().toString());
				row.add(pedido_pieza.getMano_obra().getCodigo_mano_obra());
				row.add(String.valueOf(pedido_pieza.getMano_obra().getCantidad_horas()));
				hs_mano_obra += pedido_pieza.getMano_obra().getCantidad_horas(); 
				row.add(String.valueOf(pedido_pieza.getMano_obra().getValor_mano_obra()));
				preico_mano_obra += pedido_pieza.getMano_obra().getValor_mano_obra() * pedido_pieza.getMano_obra().getCantidad_horas();
			}else{
				row.add("");
				row.add("");
				row.add("");
				row.add("");
			}
			datos_tabla_mano_obra.add(row);
		}
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTES");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIReportesControl.class.getResource("/cliente/Resources/Icons/tablas.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnControl = new JPanel();
		pnControl.setBounds(0, 0, 1270, 672);
		contentPane.add(pnControl);
		pnControl.setLayout(null);
		
		JTabbedPane control = new JTabbedPane(JTabbedPane.TOP);
		control.setBounds(0, 0, 1270, 688);
		pnControl.add(control);
		
		//DIAS DESDE FECHA SOLICITUD FABRICA//
		pnDiasDesdeFSolicitudFabirca = new JPanel();
		control.addTab("DIAS DESDE PEDIDO A FABRICA", null, pnDiasDesdeFSolicitudFabirca, null);
		pnDiasDesdeFSolicitudFabirca.setLayout(null);
		modelo_tabla_dias_desde_fsf = new DefaultTableModel(datosTabla_dias_desde_fsf, nombreColumnas_tabla_dias_desde_fsf);
		tabla_DDPF = new JTable(modelo_tabla_dias_desde_fsf) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false,false, false,
					false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_tabla_dias_desde_fsf = new TableRowSorter<TableModel>(modelo_tabla_dias_desde_fsf);
		for(int i = 0; i < tabla_DDPF.getColumnCount(); i++) {
			tabla_DDPF.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_dias_desde_fsf.elementAt(i));
			tabla_DDPF.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_dias_desde_fsf.elementAt(i));
		}
		//se crea instancia a clase FormatoTable y se indica columna patron
		FormatoTablaDiasDesdeFSF ft = new FormatoTablaDiasDesdeFSF(9);
		tabla_DDPF.setRowSorter(ordenador_tabla_dias_desde_fsf);
		tabla_DDPF.getTableHeader().setReorderingAllowed(false);
		tabla_DDPF.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla_DDPF.setDefaultRenderer (Object.class, ft );
		scrollPane_dias_trascurridos_desde_pedido_fabrica= new JScrollPane(tabla_DDPF);
		scrollPane_dias_trascurridos_desde_pedido_fabrica.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_dias_trascurridos_desde_pedido_fabrica.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_dias_trascurridos_desde_pedido_fabrica.setBounds(5, 160, 1255, 435);
		tabla_DDPF.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		pnDiasDesdeFSolicitudFabirca.add(scrollPane_dias_trascurridos_desde_pedido_fabrica);
		
		rB_Anio_DDPF = new JRadioButton("Ultimo A\u00F1o");
		rB_Anio_DDPF.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Anio_DDPF.isSelected()){
					rB_Intervalo_DDPF.setSelected(false);
					dC_FF_DDPF.setEnabled(false);
					dC_FI_DDPF.setEnabled(false);
					rB_Hoy_DDPF.setSelected(false);
					rB_USemana_DDPF.setSelected(false);
					rB_Mes_DDPF.setSelected(false);
					rB_MAnterior_DDPF.setSelected(false);
					filtrarUAnioDDPF();
				}
			}
		});
		rB_Anio_DDPF.setBounds(5, 135, 150, 20);
		pnDiasDesdeFSolicitudFabirca.add(rB_Anio_DDPF);
		
		rB_MAnterior_DDPF = new JRadioButton("Mes Anterior");
		rB_MAnterior_DDPF.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_MAnterior_DDPF.isSelected()){
					rB_Intervalo_DDPF.setSelected(false);
					dC_FF_DDPF.setEnabled(false);
					dC_FI_DDPF.setEnabled(false);
					rB_Hoy_DDPF.setSelected(false);
					rB_USemana_DDPF.setSelected(false);
					rB_Mes_DDPF.setSelected(false);
					rB_Anio_DDPF.setSelected(false);
					filtrarMAnteriorDDPF();
				}
			}
		});
		rB_MAnterior_DDPF.setBounds(5, 110, 150, 20);
		pnDiasDesdeFSolicitudFabirca.add(rB_MAnterior_DDPF);
		
		rB_Mes_DDPF = new JRadioButton("Este Mes");
		rB_Mes_DDPF.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Mes_DDPF.isSelected()){
					rB_Intervalo_DDPF.setSelected(false);
					dC_FF_DDPF.setEnabled(false);
					dC_FI_DDPF.setEnabled(false);
					rB_Hoy_DDPF.setSelected(false);
					rB_USemana_DDPF.setSelected(false);
					rB_MAnterior_DDPF.setSelected(false);
					rB_Anio_DDPF.setSelected(false);
					filtrarMesDDPF();
				}
			}
		});
		rB_Mes_DDPF.setToolTipText("Piezas llegadas en el mes corriente.");
		rB_Mes_DDPF.setBounds(5, 85, 150, 20);
		pnDiasDesdeFSolicitudFabirca.add(rB_Mes_DDPF);
		
		rB_USemana_DDPF = new JRadioButton("Ultima Semana");
		rB_USemana_DDPF.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_USemana_DDPF.isSelected()){
					rB_Intervalo_DDPF.setSelected(false);
					dC_FF_DDPF.setEnabled(false);
					dC_FI_DDPF.setEnabled(false);
					rB_Hoy_DDPF.setSelected(false);
					rB_Mes_DDPF.setSelected(false);
					rB_MAnterior_DDPF.setSelected(false);
					rB_Anio_DDPF.setSelected(false);
					filtrarUSemanaDDPF();
				}
			}
		});
		rB_USemana_DDPF.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
		rB_USemana_DDPF.setBounds(5, 60, 150, 20);
		pnDiasDesdeFSolicitudFabirca.add(rB_USemana_DDPF);
		
		rB_Hoy_DDPF = new JRadioButton("Hoy");
		rB_Hoy_DDPF.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Hoy_DDPF.isSelected()){
					rB_Intervalo_DDPF.setSelected(false);
					dC_FF_DDPF.setEnabled(false);
					dC_FI_DDPF.setEnabled(false);
					rB_USemana_DDPF.setSelected(false);
					rB_Mes_DDPF.setSelected(false);
					rB_MAnterior_DDPF.setSelected(false);
					rB_Anio_DDPF.setSelected(false);
					filtrarHoyDDPF();
				}
			}
		});
		rB_Hoy_DDPF.setToolTipText("Piezas llegadas el dia de hoy.");
		rB_Hoy_DDPF.setBounds(5, 35, 150, 20);
		pnDiasDesdeFSolicitudFabirca.add(rB_Hoy_DDPF);
		
		rB_Intervalo_DDPF = new JRadioButton("Intervalo de Fechas");
		rB_Intervalo_DDPF.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Intervalo_DDPF.isSelected()){
					dC_FF_DDPF.setEnabled(true);
					dC_FI_DDPF.setEnabled(true);
					btn_filtrar_DDPF.setEnabled(true);
					
					rB_Hoy_DDPF.setSelected(false);
					rB_USemana_DDPF.setSelected(false);
					rB_Mes_DDPF.setSelected(false);
					rB_MAnterior_DDPF.setSelected(false);
					rB_Anio_DDPF.setSelected(false);
				}else{
					dC_FF_DDPF.setEnabled(false);
					dC_FI_DDPF.setEnabled(false);
					btn_filtrar_DDPF.setEnabled(false);
				}
			}
		});
		rB_Intervalo_DDPF.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
		rB_Intervalo_DDPF.setBounds(5, 10, 150, 20);
		pnDiasDesdeFSolicitudFabirca.add(rB_Intervalo_DDPF);
		
		JLabel label_18 = new JLabel("Fecha Inicio");
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setEnabled(false);
		label_18.setBounds(160, 10, 120, 20);
		pnDiasDesdeFSolicitudFabirca.add(label_18);
		
		dC_FI_DDPF = new JDateChooser();
		dC_FI_DDPF.setEnabled(false);
		dC_FI_DDPF.setBounds(290, 10, 150, 20);
		pnDiasDesdeFSolicitudFabirca.add(dC_FI_DDPF);
		
		btn_clear_FIDDPF = new JButton("");
		btn_clear_FIDDPF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FI_DDPF.getDate()!=null)
					dC_FI_DDPF.setDate(null);
			}
		});
		btn_clear_FIDDPF.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FIDDPF.setBounds(450, 10, 25, 20);
		pnDiasDesdeFSolicitudFabirca.add(btn_clear_FIDDPF);
		
		JLabel label_19 = new JLabel("Fecha Fin");
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setEnabled(false);
		label_19.setBounds(485, 10, 120, 20);
		pnDiasDesdeFSolicitudFabirca.add(label_19);
		
		dC_FF_DDPF = new JDateChooser();
		dC_FF_DDPF.setEnabled(false);
		dC_FF_DDPF.setBounds(615, 10, 150, 20);
		pnDiasDesdeFSolicitudFabirca.add(dC_FF_DDPF);
		
		btn_clear_FFDDPF = new JButton("");
		btn_clear_FFDDPF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_DDPF.getDate()!=null)
					dC_FF_DDPF.setDate(null);
			}
		});
		btn_clear_FFDDPF.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FFDDPF.setBounds(775, 10, 25, 20);
		pnDiasDesdeFSolicitudFabirca.add(btn_clear_FFDDPF);
		
		btn_filtrar_DDPF = new JButton("Filtrar");
		btn_filtrar_DDPF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtaraPorIntervaloDDPF();
			}
		});
		btn_filtrar_DDPF.setEnabled(false);
		btn_filtrar_DDPF.setBounds(810, 10, 110, 20);
		pnDiasDesdeFSolicitudFabirca.add(btn_filtrar_DDPF);
		
		btnExportarTablaDDPF = new JButton("");
		btnExportarTablaDDPF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaDDPF();
			}
		});
		btnExportarTablaDDPF.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTablaDDPF.setBounds(1230, 120, 32, 32);
		pnDiasDesdeFSolicitudFabirca.add(btnExportarTablaDDPF);
		
		//DIAS DESDE FECHA RECEPCION FABRICA//
		pnDiasDesdeFRecepcionFabirca = new JPanel();
		control.addTab("DIAS DESDE RECEPCION DE PEDIDO A FABRICA", null, pnDiasDesdeFRecepcionFabirca, null);
		//Tabla diase desde FRF
		modelo_tabla_dias_desde_frf = new DefaultTableModel(datosTabla_dias_desde_frf, nombreColumnas_tabla_dias_desde_frf);
		// Agregamos el ordenador para las tablas de los usuarios
		tabla_DDRF = new JTable(modelo_tabla_dias_desde_frf) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false,false, false,
					false, false,false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		TableRowSorter<TableModel> ordenador_tabla_dias_desde_frf = new TableRowSorter<TableModel>(modelo_tabla_dias_desde_frf);
		for(int i = 0; i < tabla_DDRF.getColumnCount(); i++) {
			tabla_DDRF.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_dias_desde_frf.elementAt(i));
			tabla_DDRF.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_dias_desde_frf.elementAt(i));
		}
		//se crea instancia a clase FormatoTable y se indica columna patron
        FormatoTablaDiasDesdeFRF fr = new FormatoTablaDiasDesdeFRF(10);	
		tabla_DDRF.setRowSorter(ordenador_tabla_dias_desde_frf);
		tabla_DDRF.getTableHeader().setReorderingAllowed(false);
		tabla_DDRF.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla_DDRF.setDefaultRenderer (Object.class, fr );
		pnDiasDesdeFRecepcionFabirca.setLayout(null);
		scrollPane_dias_trascurridos_desde_frf= new JScrollPane(tabla_DDRF);
		scrollPane_dias_trascurridos_desde_frf.setBounds(5, 160, 1255, 435);
		scrollPane_dias_trascurridos_desde_frf.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_dias_trascurridos_desde_frf.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnDiasDesdeFRecepcionFabirca.add(scrollPane_dias_trascurridos_desde_frf);
		tabla_DDRF.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		rB_Intervalo_DDRF = new JRadioButton("Intervalo de Fechas");
		rB_Intervalo_DDRF.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Intervalo_DDRF.isSelected()){
					dC_FF_DDRF.setEnabled(true);
					dC_FI_DDRF.setEnabled(true);
					btnFiltrar_DDRF.setEnabled(true);
					
					rB_Hoy_DDRF.setSelected(false);
					rB_USemana_DDRF.setSelected(false);
					rB_Mes_DDRF.setSelected(false);
					rB_MAnterior_DDRF.setSelected(false);
					rB_Anio_DDRF.setSelected(false);
				}else{
					dC_FF_DDRF.setEnabled(false);
					dC_FI_DDRF.setEnabled(false);
					btnFiltrar_DDRF.setEnabled(false);
				}
			}
		});
		rB_Intervalo_DDRF.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
		rB_Intervalo_DDRF.setBounds(5, 10, 150, 20);
		pnDiasDesdeFRecepcionFabirca.add(rB_Intervalo_DDRF);
		
		rB_Hoy_DDRF = new JRadioButton("Hoy");
		rB_Hoy_DDRF.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Hoy_DDRF.isSelected()){
					rB_Intervalo_DDRF.setSelected(false);
					dC_FF_DDRF.setEnabled(false);
					dC_FI_DDRF.setEnabled(false);
					rB_USemana_DDRF.setSelected(false);
					rB_Mes_DDRF.setSelected(false);
					rB_MAnterior_DDRF.setSelected(false);
					rB_Anio_DDRF.setSelected(false);
					filtrarHoyDDRF();
				}
			}
		});
		rB_Hoy_DDRF.setToolTipText("Piezas llegadas el dia de hoy.");
		rB_Hoy_DDRF.setBounds(5, 35, 150, 20);
		pnDiasDesdeFRecepcionFabirca.add(rB_Hoy_DDRF);
		
		rB_USemana_DDRF = new JRadioButton("Ultima Semana");
		rB_USemana_DDRF.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_USemana_DDRF.isSelected()){
					rB_Intervalo_DDRF.setSelected(false);
					dC_FF_DDRF.setEnabled(false);
					dC_FI_DDRF.setEnabled(false);
					rB_Hoy_DDRF.setSelected(false);
					rB_Mes_DDRF.setSelected(false);
					rB_MAnterior_DDRF.setSelected(false);
					rB_Anio_DDRF.setSelected(false);
					filtrarUSemanaDDRF();
				}
			}
		});
		rB_USemana_DDRF.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
		rB_USemana_DDRF.setBounds(5, 60, 150, 20);
		pnDiasDesdeFRecepcionFabirca.add(rB_USemana_DDRF);
		
		rB_Mes_DDRF = new JRadioButton("Este Mes");
		rB_Mes_DDRF.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Mes_DDRF.isSelected()){
					rB_Intervalo_DDRF.setSelected(false);
					dC_FF_DDRF.setEnabled(false);
					dC_FI_DDRF.setEnabled(false);
					rB_Hoy_DDRF.setSelected(false);
					rB_USemana_DDRF.setSelected(false);
					rB_MAnterior_DDRF.setSelected(false);
					rB_Anio_DDRF.setSelected(false);
					filtrarMesDDRF();
				}
			}
		});
		rB_Mes_DDRF.setToolTipText("Piezas llegadas en el mes corriente.");
		rB_Mes_DDRF.setBounds(5, 85, 150, 20);
		pnDiasDesdeFRecepcionFabirca.add(rB_Mes_DDRF);
		
		rB_MAnterior_DDRF = new JRadioButton("Mes Anterior");
		rB_MAnterior_DDRF.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_MAnterior_DDRF.isSelected()){
					rB_Intervalo_DDRF.setSelected(false);
					dC_FF_DDRF.setEnabled(false);
					dC_FI_DDRF.setEnabled(false);
					rB_Hoy_DDRF.setSelected(false);
					rB_USemana_DDRF.setSelected(false);
					rB_Mes_DDRF.setSelected(false);
					rB_Anio_DDRF.setSelected(false);
					filtrarMAnteriorDDRF();
				}
			}
		});
		rB_MAnterior_DDRF.setBounds(5, 110, 150, 20);
		pnDiasDesdeFRecepcionFabirca.add(rB_MAnterior_DDRF);
		
		rB_Anio_DDRF = new JRadioButton("Ultimo A\u00F1o");
		rB_Anio_DDRF.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Anio_DDRF.isSelected()){
					rB_Intervalo_DDRF.setSelected(false);
					dC_FF_DDRF.setEnabled(false);
					dC_FI_DDRF.setEnabled(false);
					rB_Hoy_DDRF.setSelected(false);
					rB_USemana_DDRF.setSelected(false);
					rB_Mes_DDRF.setSelected(false);
					rB_MAnterior_DDRF.setSelected(false);
					filtrarUAnioDDRF();
				}
			}
		});
		rB_Anio_DDRF.setBounds(5, 135, 150, 20);
		pnDiasDesdeFRecepcionFabirca.add(rB_Anio_DDRF);
		
		JLabel label_20 = new JLabel("Fecha Inicio");
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		label_20.setEnabled(false);
		label_20.setBounds(160, 10, 120, 20);
		pnDiasDesdeFRecepcionFabirca.add(label_20);
		
		dC_FI_DDRF = new JDateChooser();
		dC_FI_DDRF.setEnabled(false);
		dC_FI_DDRF.setBounds(290, 10, 150, 20);
		pnDiasDesdeFRecepcionFabirca.add(dC_FI_DDRF);
		
		btn_clear_FIDDRF = new JButton("");
		btn_clear_FIDDRF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FI_DDRF.getDate()!=null)
					dC_FI_DDRF.setDate(null);
			}
		});
		btn_clear_FIDDRF.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FIDDRF.setBounds(450, 10, 25, 20);
		pnDiasDesdeFRecepcionFabirca.add(btn_clear_FIDDRF);
		
		JLabel label_21 = new JLabel("Fecha Fin");
		label_21.setHorizontalAlignment(SwingConstants.CENTER);
		label_21.setEnabled(false);
		label_21.setBounds(485, 10, 120, 20);
		pnDiasDesdeFRecepcionFabirca.add(label_21);
		
		dC_FF_DDRF = new JDateChooser();
		dC_FF_DDRF.setEnabled(false);
		dC_FF_DDRF.setBounds(615, 10, 150, 20);
		pnDiasDesdeFRecepcionFabirca.add(dC_FF_DDRF);
		
		btn_clear_FFDDRF = new JButton("");
		btn_clear_FFDDRF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_DDRF.getDate()!=null)
					dC_FF_DDRF.setDate(null);
			}
		});
		btn_clear_FFDDRF.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FFDDRF.setBounds(775, 10, 25, 20);
		pnDiasDesdeFRecepcionFabirca.add(btn_clear_FFDDRF);
		
		btnFiltrar_DDRF = new JButton("Filtrar");
		btnFiltrar_DDRF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtaraPorIntervaloDDRF();
			}
		});
		btnFiltrar_DDRF.setEnabled(false);
		btnFiltrar_DDRF.setBounds(810, 10, 110, 20);
		pnDiasDesdeFRecepcionFabirca.add(btnFiltrar_DDRF);
		
		btnExportarTablaDDRF = new JButton("");
		btnExportarTablaDDRF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaDDRF();
			}
		});
		btnExportarTablaDDRF.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTablaDDRF.setBounds(1230, 120, 32, 32);
		pnDiasDesdeFRecepcionFabirca.add(btnExportarTablaDDRF);
			      
		//DIAS DESDE FECHA RECEPCION FABRICA Y FECHA TURNO//
		pnDiasDesdeFRecepcionFabricaFTurno = new JPanel();
		control.addTab("DIAS DESDE RECEPCION DE FABRICA Y  FECHA TURNO", null, pnDiasDesdeFRecepcionFabricaFTurno, null);
		pnDiasDesdeFRecepcionFabricaFTurno.setLayout(null);
		//tabla dias desde frf a fturno
		modelo_tabla_dias_desde_frf_fturno = new DefaultTableModel(datosTabla_dias_desde_frf_fturno, nombreColumnas_tabla_dias_desde_frf_fturno);
		tabla_DDRFT = new JTable(modelo_tabla_dias_desde_frf_fturno) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false,false, false,
					false, false,false,false,false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_tabla_dias_desde_frf_fturno = new TableRowSorter<TableModel>(modelo_tabla_dias_desde_frf_fturno);
		for(int i = 0; i < tabla_DDRFT.getColumnCount(); i++) {
			tabla_DDRFT.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_dias_desde_frf_fturno.elementAt(i));
			tabla_DDRFT.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_dias_desde_frf_fturno.elementAt(i));
		}
		//se crea instancia a clase FormatoTable y se indica columna patron
		FormatoTablaDiasDesdeFTrunoFCierre frfft = new FormatoTablaDiasDesdeFTrunoFCierre(11);	
		tabla_DDRFT.setRowSorter(ordenador_tabla_dias_desde_frf_fturno);
		tabla_DDRFT.getTableHeader().setReorderingAllowed(false);
		tabla_DDRFT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla_DDRFT.setDefaultRenderer (Object.class, frfft );
        scrollPane_dias_transcurridos_desde_frf_fturno= new JScrollPane(tabla_DDRFT);
        scrollPane_dias_transcurridos_desde_frf_fturno.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_dias_transcurridos_desde_frf_fturno.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_dias_transcurridos_desde_frf_fturno.setBounds(5, 160, 1255, 435);
        pnDiasDesdeFRecepcionFabricaFTurno.add(scrollPane_dias_transcurridos_desde_frf_fturno);
        tabla_DDRFT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        rB_Intervalo_DDRFT = new JRadioButton("Intervalo de Fechas");
        rB_Intervalo_DDRFT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Intervalo_DDRFT.isSelected()){
        			dC_FF_DDRFT.setEnabled(true);
        			dC_FI_DDRFT.setEnabled(true);
        			btn_filtrar_DDRFT.setEnabled(true);
        			
        			rB_Hoy_DDRFT.setSelected(false);
        			rB_USemana_DDRFT.setSelected(false);
        			rB_Mes_DDRFT.setSelected(false);
        			rB_MAnterior_DDRFT.setSelected(false);
        			rB_Anio_DDRFT.setSelected(false);
        		}else{
        			dC_FF_DDRFT.setEnabled(false);
        			dC_FI_DDRFT.setEnabled(false);
        			btn_filtrar_DDRFT.setEnabled(false);
        		}
        	}
        });
        rB_Intervalo_DDRFT.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
        rB_Intervalo_DDRFT.setBounds(5, 10, 150, 20);
        pnDiasDesdeFRecepcionFabricaFTurno.add(rB_Intervalo_DDRFT);
        
        rB_Hoy_DDRFT = new JRadioButton("Hoy");
        rB_Hoy_DDRFT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Hoy_DDRFT.isSelected()){
        			rB_Intervalo_DDRFT.setSelected(false);
        			dC_FF_DDRFT.setEnabled(false);
        			dC_FI_DDRFT.setEnabled(false);
        			rB_USemana_DDRFT.setSelected(false);
        			rB_Mes_DDRFT.setSelected(false);
        			rB_MAnterior_DDRFT.setSelected(false);
        			rB_Anio_DDRFT.setSelected(false);
        			filtrarHoyDDRFT();
        		}
        	}
        });
        rB_Hoy_DDRFT.setToolTipText("Piezas llegadas el dia de hoy.");
        rB_Hoy_DDRFT.setBounds(5, 35, 150, 20);
        pnDiasDesdeFRecepcionFabricaFTurno.add(rB_Hoy_DDRFT);
        
        rB_USemana_DDRFT = new JRadioButton("Ultima Semana");
        rB_USemana_DDRFT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_USemana_DDRFT.isSelected()){
        			rB_Intervalo_DDRFT.setSelected(false);
        			dC_FF_DDRFT.setEnabled(false);
        			dC_FI_DDRFT.setEnabled(false);
        			rB_Hoy_DDRFT.setSelected(false);
        			rB_Mes_DDRFT.setSelected(false);
        			rB_MAnterior_DDRFT.setSelected(false);
        			rB_Anio_DDRFT.setSelected(false);
        			filtrarUSemanaDDRFT();
        		}
        	}
        });
        rB_USemana_DDRFT.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
        rB_USemana_DDRFT.setBounds(5, 60, 150, 20);
        pnDiasDesdeFRecepcionFabricaFTurno.add(rB_USemana_DDRFT);
        
        rB_Mes_DDRFT = new JRadioButton("Este Mes");
        rB_Mes_DDRFT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Mes_DDRFT.isSelected()){
        			rB_Intervalo_DDRFT.setSelected(false);
        			dC_FF_DDRFT.setEnabled(false);
        			dC_FI_DDRFT.setEnabled(false);
        			rB_Hoy_DDRFT.setSelected(false);
        			rB_USemana_DDRFT.setSelected(false);
        			rB_MAnterior_DDRFT.setSelected(false);
        			rB_Anio_DDRFT.setSelected(false);
        			filtrarMesDDRFT();
        		}
        	}
        });
        rB_Mes_DDRFT.setToolTipText("Piezas llegadas en el mes corriente.");
        rB_Mes_DDRFT.setBounds(5, 85, 150, 20);
        pnDiasDesdeFRecepcionFabricaFTurno.add(rB_Mes_DDRFT);
        
        rB_MAnterior_DDRFT = new JRadioButton("Mes Anterior");
        rB_MAnterior_DDRFT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_MAnterior_DDRFT.isSelected()){
        			rB_Intervalo_DDRFT.setSelected(false);
        			dC_FF_DDRFT.setEnabled(false);
        			dC_FI_DDRFT.setEnabled(false);
        			rB_Hoy_DDRFT.setSelected(false);
        			rB_USemana_DDRFT.setSelected(false);
        			rB_Mes_DDRFT.setSelected(false);
        			rB_Anio_DDRFT.setSelected(false);
        			filtrarMAnteriorDDRFT();
        		}
        	}
        });
        rB_MAnterior_DDRFT.setBounds(5, 110, 150, 20);
        pnDiasDesdeFRecepcionFabricaFTurno.add(rB_MAnterior_DDRFT);
        
        rB_Anio_DDRFT = new JRadioButton("Ultimo A\u00F1o");
        rB_Anio_DDRFT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Anio_DDRFT.isSelected()){
        			rB_Intervalo_DDRFT.setSelected(false);
        			dC_FF_DDRFT.setEnabled(false);
        			dC_FI_DDRFT.setEnabled(false);
        			rB_Hoy_DDRFT.setSelected(false);
        			rB_USemana_DDRFT.setSelected(false);
        			rB_Mes_DDRFT.setSelected(false);
        			rB_MAnterior_DDRFT.setSelected(false);
        			filtrarUAnioDDRFT();
        		}
        	}
        });
        rB_Anio_DDRFT.setBounds(5, 135, 150, 20);
        pnDiasDesdeFRecepcionFabricaFTurno.add(rB_Anio_DDRFT);
        
        JLabel label_22 = new JLabel("Fecha Inicio");
        label_22.setHorizontalAlignment(SwingConstants.CENTER);
        label_22.setEnabled(false);
        label_22.setBounds(161, 11, 120, 20);
        pnDiasDesdeFRecepcionFabricaFTurno.add(label_22);
        
        dC_FI_DDRFT = new JDateChooser();
        dC_FI_DDRFT.setEnabled(false);
        dC_FI_DDRFT.setBounds(290, 10, 150, 20);
        pnDiasDesdeFRecepcionFabricaFTurno.add(dC_FI_DDRFT);
        
        btn_clear_FIDDRFT = new JButton("");
        btn_clear_FIDDRFT.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(dC_FI_DDRFT.getDate()!=null)
        			dC_FI_DDRFT.setDate(null);
        	}
        });
        btn_clear_FIDDRFT.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FIDDRFT.setBounds(450, 10, 25, 20);
        pnDiasDesdeFRecepcionFabricaFTurno.add(btn_clear_FIDDRFT);
        
        JLabel label_23 = new JLabel("Fecha Fin");
        label_23.setHorizontalAlignment(SwingConstants.CENTER);
        label_23.setEnabled(false);
        label_23.setBounds(486, 11, 120, 20);
        pnDiasDesdeFRecepcionFabricaFTurno.add(label_23);
        
        dC_FF_DDRFT = new JDateChooser();
        dC_FF_DDRFT.setEnabled(false);
        dC_FF_DDRFT.setBounds(615, 10, 150, 20);
        pnDiasDesdeFRecepcionFabricaFTurno.add(dC_FF_DDRFT);
        
        btn_clear_FFDDRFT = new JButton("");
        btn_clear_FFDDRFT.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(dC_FF_DDRFT.getDate()!=null)
        			dC_FF_DDRFT.setDate(null);
        	}
        });
        btn_clear_FFDDRFT.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FFDDRFT.setBounds(775, 10, 25, 20);
        pnDiasDesdeFRecepcionFabricaFTurno.add(btn_clear_FFDDRFT);
        
        btn_filtrar_DDRFT = new JButton("Filtrar");
        btn_filtrar_DDRFT.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		filtrarIntervaloDDRFT();
        	}
        });
        btn_filtrar_DDRFT.setEnabled(false);
        btn_filtrar_DDRFT.setBounds(810, 10, 110, 20);
        pnDiasDesdeFRecepcionFabricaFTurno.add(btn_filtrar_DDRFT);
        
        btnExportarTabla_DDRFT = new JButton("");
        btnExportarTabla_DDRFT.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		exportarTablaDDRFT();
        	}
        });
        btnExportarTabla_DDRFT.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/formulario.png")));
        btnExportarTabla_DDRFT.setBounds(1230, 120, 32, 32);
        pnDiasDesdeFRecepcionFabricaFTurno.add(btnExportarTabla_DDRFT);
        
		//DIAS DESDE FECHA CIERRE Y FECHA TURNO//
        pnDiasDesdeFCierreFTurno = new JPanel();
        control.addTab("DIAS DESDE FECHA CIERRE ORDEN Y FECHA TURNO", null, pnDiasDesdeFCierreFTurno, null);
        pnDiasDesdeFCierreFTurno.setLayout(null);
        modelo_tabla_dias_desde_fcierre_fturno = new DefaultTableModel(datosTabla_dias_desde_fcierre_fturno, nombreColumnas_tabla_dias_desde_fcierre_fturno);
        // Agregamos el ordenador para las tablas de los usuarios
        tabla_DDFCT = new JTable(modelo_tabla_dias_desde_fcierre_fturno) {
        	private static final long serialVersionUID = 1L;
        	boolean[] columnEditables = new boolean[] {
        			false, false, false, false, false,
        			false, false, false,false, false,
        			false, false,false,false,false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        };
		TableRowSorter<TableModel> ordenador_tabla_dias_desde_fcierre_fturno = new TableRowSorter<TableModel>(modelo_tabla_dias_desde_fcierre_fturno);
		for(int i = 0; i < tabla_DDFCT.getColumnCount(); i++) {
			tabla_DDFCT.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_dias_desde_fcierre_fturno.elementAt(i));
			tabla_DDFCT.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_dias_desde_fcierre_fturno.elementAt(i));
		}
		//se crea instancia a clase FormatoTable y se indica columna patron
		FormatoTablaDiasDesdeFTrunoFCierre fcft = new FormatoTablaDiasDesdeFTrunoFCierre(10);
        tabla_DDFCT.setRowSorter(ordenador_tabla_dias_desde_fcierre_fturno);
        tabla_DDFCT.getTableHeader().setReorderingAllowed(false);
        tabla_DDFCT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla_DDFCT.setDefaultRenderer (Object.class, fcft );
        scrollPane_dias_trasncurridos_desde_fcierre_fturno= new JScrollPane(tabla_DDFCT);
        scrollPane_dias_trasncurridos_desde_fcierre_fturno.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_dias_trasncurridos_desde_fcierre_fturno.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_dias_trasncurridos_desde_fcierre_fturno.setBounds(5, 160, 1255, 435);
        pnDiasDesdeFCierreFTurno.add(scrollPane_dias_trasncurridos_desde_fcierre_fturno);
        tabla_DDFCT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        rB_Intervalo_DDFCFT = new JRadioButton("Intervalo de Fechas");
        rB_Intervalo_DDFCFT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Intervalo_DDFCFT.isSelected()){
        			dC_FF_DDFCFT.setEnabled(true);
        			dC_FI_DDFCFT.setEnabled(true);
        			btn_filtrar_DDFCFT.setEnabled(true);
        			rB_Hoy_DDFCFT.setSelected(false);
        			rB_USemana_DDFCFT.setSelected(false);
        			rB_Mes_DDFCFT.setSelected(false);
        			rB_MAnterior_DDFCFT.setSelected(false);
        			rB_Anio_DDFCFT.setSelected(false);
        		}else{
        			dC_FF_DDFCFT.setEnabled(false);
        			dC_FI_DDFCFT.setEnabled(false);
        			btn_filtrar_DDFCFT.setEnabled(false);
        		}
        	}
        });
        rB_Intervalo_DDFCFT.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
        rB_Intervalo_DDFCFT.setBounds(5, 10, 150, 20);
        pnDiasDesdeFCierreFTurno.add(rB_Intervalo_DDFCFT);
        
        rB_Hoy_DDFCFT = new JRadioButton("Hoy");
        rB_Hoy_DDFCFT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Hoy_DDFCFT.isSelected()){
        			rB_Intervalo_DDFCFT.setSelected(false);
        			dC_FF_DDFCFT.setEnabled(false);
        			dC_FI_DDFCFT.setEnabled(false);
        			rB_USemana_DDFCFT.setSelected(false);
        			rB_Mes_DDFCFT.setSelected(false);
        			rB_MAnterior_DDFCFT.setSelected(false);
        			rB_Anio_DDFCFT.setSelected(false);
        			filtrarHoyDDFCFT();
        		}
        	}
        });
        rB_Hoy_DDFCFT.setToolTipText("Piezas llegadas el dia de hoy.");
        rB_Hoy_DDFCFT.setBounds(5, 35, 150, 20);
        pnDiasDesdeFCierreFTurno.add(rB_Hoy_DDFCFT);
        
        rB_USemana_DDFCFT = new JRadioButton("Ultima Semana");
        rB_USemana_DDFCFT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_USemana_DDFCFT.isSelected()){
        			rB_Intervalo_DDFCFT.setSelected(false);
        			dC_FF_DDFCFT.setEnabled(false);
        			dC_FI_DDFCFT.setEnabled(false);
        			rB_Hoy_DDFCFT.setSelected(false);
        			rB_Mes_DDFCFT.setSelected(false);
        			rB_MAnterior_DDFCFT.setSelected(false);
        			rB_Anio_DDFCFT.setSelected(false);
        			filtrarUSemanaDDFCFT();
        		}
        	}
        });
        rB_USemana_DDFCFT.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
        rB_USemana_DDFCFT.setBounds(5, 60, 150, 20);
        pnDiasDesdeFCierreFTurno.add(rB_USemana_DDFCFT);
        
        rB_Mes_DDFCFT = new JRadioButton("Este Mes");
        rB_Mes_DDFCFT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Mes_DDFCFT.isSelected()){
        			rB_Intervalo_DDFCFT.setSelected(false);
        			dC_FF_DDFCFT.setEnabled(false);
        			dC_FI_DDFCFT.setEnabled(false);
        			rB_Hoy_DDFCFT.setSelected(false);
        			rB_USemana_DDFCFT.setSelected(false);
        			rB_MAnterior_DDFCFT.setSelected(false);
        			rB_Anio_DDFCFT.setSelected(false);
        			filtrarMesDDFCFT();
        		}
        	}
        });
        rB_Mes_DDFCFT.setToolTipText("Piezas llegadas en el mes corriente.");
        rB_Mes_DDFCFT.setBounds(5, 85, 150, 20);
        pnDiasDesdeFCierreFTurno.add(rB_Mes_DDFCFT);
        
        rB_MAnterior_DDFCFT = new JRadioButton("Mes Anterior");
        rB_MAnterior_DDFCFT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_MAnterior_DDFCFT.isSelected()){
        			rB_Intervalo_DDFCFT.setSelected(false);
        			dC_FF_DDFCFT.setEnabled(false);
        			dC_FI_DDFCFT.setEnabled(false);
        			rB_Hoy_DDFCFT.setSelected(false);
        			rB_USemana_DDFCFT.setSelected(false);
        			rB_Mes_DDFCFT.setSelected(false);
        			rB_Anio_DDFCFT.setSelected(false);
        			filtrarMAnteriorDDFCFT();
        		}
        	}
        });
        rB_MAnterior_DDFCFT.setBounds(5, 110, 150, 20);
        pnDiasDesdeFCierreFTurno.add(rB_MAnterior_DDFCFT);
        
        rB_Anio_DDFCFT = new JRadioButton("Ultimo A\u00F1o");
        rB_Anio_DDFCFT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Anio_DDFCFT.isSelected()){
        			rB_Intervalo_DDFCFT.setSelected(false);
        			dC_FF_DDFCFT.setEnabled(false);
        			dC_FI_DDFCFT.setEnabled(false);
        			rB_Hoy_DDFCFT.setSelected(false);
        			rB_USemana_DDFCFT.setSelected(false);
        			rB_Mes_DDFCFT.setSelected(false);
        			rB_MAnterior_DDFCFT.setSelected(false);
        			filtrarUAnioDDFCFT();
        		}
        	}
        });
        rB_Anio_DDFCFT.setBounds(5, 135, 150, 20);
        pnDiasDesdeFCierreFTurno.add(rB_Anio_DDFCFT);
        
        JLabel label_24 = new JLabel("Fecha Inicio");
        label_24.setHorizontalAlignment(SwingConstants.CENTER);
        label_24.setEnabled(false);
        label_24.setBounds(161, 11, 120, 20);
        pnDiasDesdeFCierreFTurno.add(label_24);
        
        dC_FI_DDFCFT = new JDateChooser();
        dC_FI_DDFCFT.setEnabled(false);
        dC_FI_DDFCFT.setBounds(290, 10, 150, 20);
        pnDiasDesdeFCierreFTurno.add(dC_FI_DDFCFT);
        
        btn_clear_FIDDFCFT = new JButton("");
        btn_clear_FIDDFCFT.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(dC_FI_DDFCFT.getDate()!=null)
        			dC_FI_DDFCFT.setDate(null);
        	}
        });
        btn_clear_FIDDFCFT.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FIDDFCFT.setBounds(450, 10, 25, 20);
        pnDiasDesdeFCierreFTurno.add(btn_clear_FIDDFCFT);
        
        JLabel label_25 = new JLabel("Fecha Fin");
        label_25.setHorizontalAlignment(SwingConstants.CENTER);
        label_25.setEnabled(false);
        label_25.setBounds(486, 11, 120, 20);
        pnDiasDesdeFCierreFTurno.add(label_25);
        
        dC_FF_DDFCFT = new JDateChooser();
        dC_FF_DDFCFT.setEnabled(false);
        dC_FF_DDFCFT.setBounds(615, 10, 150, 20);
        pnDiasDesdeFCierreFTurno.add(dC_FF_DDFCFT);
        
        btn_clear_FFDDFCFT = new JButton("");
        btn_clear_FFDDFCFT.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(dC_FF_DDFCFT.getDate()!=null)
        			dC_FF_DDFCFT.setDate(null);
        	}
        });
        btn_clear_FFDDFCFT.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FFDDFCFT.setBounds(775, 10, 25, 20);
        pnDiasDesdeFCierreFTurno.add(btn_clear_FFDDFCFT);
        
        btn_filtrar_DDFCFT = new JButton("Filtrar");
        btn_filtrar_DDFCFT.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		filtrarIntervaloDDFCFT();
        	}
        });
        btn_filtrar_DDFCFT.setEnabled(false);
        btn_filtrar_DDFCFT.setBounds(810, 10, 110, 20);
        pnDiasDesdeFCierreFTurno.add(btn_filtrar_DDFCFT);
        
        btnExportarTablaDDFCFT = new JButton("");
        btnExportarTablaDDFCFT.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		exportarTablaDDFCFT();
        	}
        });
        btnExportarTablaDDFCFT.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/formulario.png")));
        btnExportarTablaDDFCFT.setBounds(1230, 120, 32, 32);
        pnDiasDesdeFCierreFTurno.add(btnExportarTablaDDFCFT);
        
		//DIAS DESDE FECHA RECURSO A FECHA CIERRE ORDEN//
        pnDiasDesdeFRecursoFCierreOT = new JPanel();
        control.addTab("DIAS DESDE FECHA RECURSO Y FECHA CIERRE ORDEN", null, pnDiasDesdeFRecursoFCierreOT, null);
        pnDiasDesdeFRecursoFCierreOT.setLayout(null);
        modelo_tabla_dias_desde_frecurso_fcierre = new DefaultTableModel(datosTabla_dias_desde_frecurso_fcierre, nombreColumnas_tabla_dias_desde_frecurso_fcierre);
        // Agregamos el ordenador para las tablas de los usuarios
        tabla_DDFRFC = new JTable(modelo_tabla_dias_desde_frecurso_fcierre) {
        	private static final long serialVersionUID = 1L;
        	boolean[] columnEditables = new boolean[] {
        			false, false, false, false, false,
        			false, false, false,false, false,
        			false, false,false,false,false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        };
		TableRowSorter<TableModel> ordenador_tabla_dias_desde_frecurso_fcierre = new TableRowSorter<TableModel>(modelo_tabla_dias_desde_fcierre_fturno);
		for(int i = 0; i < tabla_DDFRFC.getColumnCount(); i++) {
			tabla_DDFRFC.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_dias_desde_frecurso_fcierre.elementAt(i));
			tabla_DDFRFC.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_dias_desde_frecurso_fcierre.elementAt(i));
		}
		//se crea instancia a clase FormatoTable y se indica columna patron
		FormatoTablaDiasDesdeFTrunoFCierre frfc = new FormatoTablaDiasDesdeFTrunoFCierre(10);
        tabla_DDFRFC.setRowSorter(ordenador_tabla_dias_desde_frecurso_fcierre);
        tabla_DDFRFC.getTableHeader().setReorderingAllowed(false);
        tabla_DDFRFC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla_DDFRFC.setDefaultRenderer (Object.class, frfc );
        scrollPane_dias_desde_frecurso_fcierre= new JScrollPane(tabla_DDFRFC);
        scrollPane_dias_desde_frecurso_fcierre.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_dias_desde_frecurso_fcierre.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_dias_desde_frecurso_fcierre.setBounds(5, 160, 1255, 435);
		pnDiasDesdeFRecursoFCierreOT.add(scrollPane_dias_desde_frecurso_fcierre);
        tabla_DDFRFC.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        rB_Intervalo_DDFRFC = new JRadioButton("Intervalo de Fechas");
        rB_Intervalo_DDFRFC.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Intervalo_DDFRFC.isSelected()){
        			dC_FF_DDFRFC.setEnabled(true);
        			dC_FI_DDFRFC.setEnabled(true);
        			btn_filtrar_DDFRFC.setEnabled(true);
        			
        			rB_Hoy_DDFRFC.setSelected(false);
        			rB_USemana_DDFRFC.setSelected(false);
        			rB_Mes_DDFRFC.setSelected(false);
        			rB_MAnterior_DDFRFC.setSelected(false);
        			rB_Anio_DDFRFC.setSelected(false);
        		}else{
        			dC_FF_DDFRFC.setEnabled(false);
        			dC_FI_DDFRFC.setEnabled(false);
        			btn_filtrar_DDFRFC.setEnabled(false);
        		}
        	}
        });
        rB_Intervalo_DDFRFC.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
        rB_Intervalo_DDFRFC.setBounds(5, 10, 150, 20);
        pnDiasDesdeFRecursoFCierreOT.add(rB_Intervalo_DDFRFC);
        
        rB_Hoy_DDFRFC = new JRadioButton("Hoy");
        rB_Hoy_DDFRFC.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Hoy_DDFRFC.isSelected()){
        			rB_Intervalo_DDFRFC.setSelected(false);
        			dC_FF_DDFRFC.setEnabled(false);
        			dC_FI_DDFRFC.setEnabled(false);
        			rB_USemana_DDFRFC.setSelected(false);
        			rB_Mes_DDFRFC.setSelected(false);
        			rB_MAnterior_DDFRFC.setSelected(false);
        			rB_Anio_DDFRFC.setSelected(false);
        			filtrarHoyDDFRFC();
        		}
        	}
        });
        rB_Hoy_DDFRFC.setToolTipText("Piezas llegadas el dia de hoy.");
        rB_Hoy_DDFRFC.setBounds(5, 35, 150, 20);
        pnDiasDesdeFRecursoFCierreOT.add(rB_Hoy_DDFRFC);
        
        rB_USemana_DDFRFC = new JRadioButton("Ultima Semana");
        rB_USemana_DDFRFC.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_USemana_DDFRFC.isSelected()){
        			rB_Intervalo_DDFRFC.setSelected(false);
        			dC_FF_DDFRFC.setEnabled(false);
        			dC_FI_DDFRFC.setEnabled(false);
        			rB_Hoy_DDFRFC.setSelected(false);
        			rB_Mes_DDFRFC.setSelected(false);
        			rB_MAnterior_DDFRFC.setSelected(false);
        			rB_Anio_DDFRFC.setSelected(false);
        			filtrarUSemanaDDFRFC();
        		}
        	}
        });
        rB_USemana_DDFRFC.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
        rB_USemana_DDFRFC.setBounds(5, 60, 150, 20);
        pnDiasDesdeFRecursoFCierreOT.add(rB_USemana_DDFRFC);
        
        rB_Mes_DDFRFC = new JRadioButton("Este Mes");
        rB_Mes_DDFRFC.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Mes_DDFRFC.isSelected()){
        			rB_Intervalo_DDFRFC.setSelected(false);
        			dC_FF_DDFRFC.setEnabled(false);
        			dC_FI_DDFRFC.setEnabled(false);
        			rB_Hoy_DDFRFC.setSelected(false);
        			rB_USemana_DDFRFC.setSelected(false);
        			rB_MAnterior_DDFRFC.setSelected(false);
        			rB_Anio_DDFRFC.setSelected(false);
        			filtrarMesDDFRFC();
        		}
        	}
        });
        rB_Mes_DDFRFC.setToolTipText("Piezas llegadas en el mes corriente.");
        rB_Mes_DDFRFC.setBounds(5, 85, 150, 20);
        pnDiasDesdeFRecursoFCierreOT.add(rB_Mes_DDFRFC);
        
        rB_MAnterior_DDFRFC = new JRadioButton("Mes Anterior");
        rB_MAnterior_DDFRFC.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_MAnterior_DDFRFC.isSelected()){
        			rB_Intervalo_DDFRFC.setSelected(false);
        			dC_FF_DDFRFC.setEnabled(false);
        			dC_FI_DDFRFC.setEnabled(false);
        			rB_Hoy_DDFRFC.setSelected(false);
        			rB_USemana_DDFRFC.setSelected(false);
        			rB_Mes_DDFRFC.setSelected(false);
        			rB_Anio_DDFRFC.setSelected(false);
        			filtrarMAnteriorDDFRFC();
        		}
        	}
        });
        rB_MAnterior_DDFRFC.setBounds(5, 110, 150, 20);
        pnDiasDesdeFRecursoFCierreOT.add(rB_MAnterior_DDFRFC);
        
        rB_Anio_DDFRFC = new JRadioButton("Ultimo A\u00F1o");
        rB_Anio_DDFRFC.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Anio_DDFRFC.isSelected()){
        			rB_Intervalo_DDFRFC.setSelected(false);
        			dC_FF_DDFRFC.setEnabled(false);
        			dC_FI_DDFRFC.setEnabled(false);
        			rB_Hoy_DDFRFC.setSelected(false);
        			rB_USemana_DDFRFC.setSelected(false);
        			rB_Mes_DDFRFC.setSelected(false);
        			rB_MAnterior_DDFRFC.setSelected(false);
        			filtrarUAnioDDFRFC();
        		}
        	}
        });
        rB_Anio_DDFRFC.setBounds(5, 135, 150, 20);
        pnDiasDesdeFRecursoFCierreOT.add(rB_Anio_DDFRFC);
        
        JLabel label_26 = new JLabel("Fecha Inicio");
        label_26.setHorizontalAlignment(SwingConstants.CENTER);
        label_26.setEnabled(false);
        label_26.setBounds(161, 11, 120, 20);
        pnDiasDesdeFRecursoFCierreOT.add(label_26);
        
        dC_FI_DDFRFC = new JDateChooser();
        dC_FI_DDFRFC.setEnabled(false);
        dC_FI_DDFRFC.setBounds(290, 10, 150, 20);
        pnDiasDesdeFRecursoFCierreOT.add(dC_FI_DDFRFC);
        
        btn_clear_FIDDFRFC = new JButton("");
        btn_clear_FIDDFRFC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FI_DDFRFC.getDate()!=null)
					dC_FI_DDFRFC.setDate(null);
			}
		});
        btn_clear_FIDDFRFC.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FIDDFRFC.setBounds(450, 10, 25, 20);
        pnDiasDesdeFRecursoFCierreOT.add(btn_clear_FIDDFRFC);
        
        JLabel label_27 = new JLabel("Fecha Fin");
        label_27.setHorizontalAlignment(SwingConstants.CENTER);
        label_27.setEnabled(false);
        label_27.setBounds(486, 11, 120, 20);
        pnDiasDesdeFRecursoFCierreOT.add(label_27);
        
        dC_FF_DDFRFC = new JDateChooser();
        dC_FF_DDFRFC.setEnabled(false);
        dC_FF_DDFRFC.setBounds(615, 10, 150, 20);
        pnDiasDesdeFRecursoFCierreOT.add(dC_FF_DDFRFC);
        
        btn_clear_FFDDFRFC = new JButton("");
        btn_clear_FFDDFRFC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_DDFRFC.getDate()!=null)
					dC_FF_DDFRFC.setDate(null);
			}
		});
        btn_clear_FFDDFRFC.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FFDDFRFC.setBounds(775, 10, 25, 20);
        pnDiasDesdeFRecursoFCierreOT.add(btn_clear_FFDDFRFC);
        
        btn_filtrar_DDFRFC = new JButton("Filtrar");
        btn_filtrar_DDFRFC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarIntervaloDDFRFC();
			}
		});
        btn_filtrar_DDFRFC.setEnabled(false);
        btn_filtrar_DDFRFC.setBounds(810, 10, 110, 20);
        pnDiasDesdeFRecursoFCierreOT.add(btn_filtrar_DDFRFC);
        
        btnExportarTabla_DDFRFC = new JButton("");
        btnExportarTabla_DDFRFC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaDDFRFC();
			}
		});
        btnExportarTabla_DDFRFC.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/formulario.png")));
        btnExportarTabla_DDFRFC.setBounds(1230, 120, 32, 32);
        pnDiasDesdeFRecursoFCierreOT.add(btnExportarTabla_DDFRFC);
        
        //DIAS DESDE FECHA RECLAMO Y FECHA DEVOLUCION//
        pnDiasDesdeFReclamoFDevolucion = new JPanel();
        pnDiasDesdeFReclamoFDevolucion.setLayout(null);
        control.addTab("DIAS DESDE FECHA RECLAMO Y FECHA DEVOLUCION", null, pnDiasDesdeFReclamoFDevolucion, null);
        modelo_tabla_dias_desde_freclamo_fdevolucion = new DefaultTableModel(datosTabla_dias_desde_freclamo_fdevolucion, nombreColumnas_tabla_dias_desde_freclamo_fdevolucion);
        // Agregamos el ordenador para las tablas de los usuarios
        tabla_DDFRFD = new JTable(modelo_tabla_dias_desde_freclamo_fdevolucion) {
        	private static final long serialVersionUID = 1L;
        	boolean[] columnEditables = new boolean[] {
        			false, false, false, false, false,
        			false, false, false,false, false,
        			false, false,false,false,false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        };
		TableRowSorter<TableModel> ordenador_tabla_dias_desde_freclamo_fdevolucion = new TableRowSorter<TableModel>(modelo_tabla_dias_desde_freclamo_fdevolucion);
		for(int i = 0; i < tabla_DDFRFD.getColumnCount(); i++) {
			tabla_DDFRFD.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_dias_desde_freclamo_fdevolucion.elementAt(i));
			tabla_DDFRFD.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_dias_desde_freclamo_fdevolucion.elementAt(i));
		}
		//se crea instancia a clase FormatoTable y se indica columna patron
		FormatoTablaDiasDesdeFTrunoFCierre frfd = new FormatoTablaDiasDesdeFTrunoFCierre(11);
        tabla_DDFRFD.setRowSorter(ordenador_tabla_dias_desde_freclamo_fdevolucion);
        tabla_DDFRFD.getTableHeader().setReorderingAllowed(false);
        tabla_DDFRFD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla_DDFRFD.setDefaultRenderer (Object.class, frfd );
        scrollPane_dias_desde_freclamo_fdevolucion= new JScrollPane(tabla_DDFRFD);
        scrollPane_dias_desde_freclamo_fdevolucion.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_dias_desde_freclamo_fdevolucion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_dias_desde_freclamo_fdevolucion.setBounds(5, 160, 1255, 435);
        pnDiasDesdeFReclamoFDevolucion.add(scrollPane_dias_desde_freclamo_fdevolucion);
        tabla_DDFRFD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        rB_Intervalo_DDFRFD = new JRadioButton("Intervalo de Fechas");
        rB_Intervalo_DDFRFD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Intervalo_DDFRFD.isSelected()){
        			dC_FF_DDFRFD.setEnabled(true);
        			dC_FI_DDFRFD.setEnabled(true);
        			btn_filtrar_DDFRFD.setEnabled(true);
        			
        			rB_Hoy_DDFRFD.setSelected(false);
        			rB_USemana_DDFRFD.setSelected(false);
        			rB_Mes_DDFRFD.setSelected(false);
        			rB_MAnterior_DDFRFD.setSelected(false);
        			rB_Anio_DDFRFD.setSelected(false);
        		}else{
        			dC_FF_DDFRFD.setEnabled(false);
        			dC_FI_DDFRFD.setEnabled(false);
        			btn_filtrar_DDFRFD.setEnabled(false);
        		}
        	}
        });
        rB_Intervalo_DDFRFD.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
        rB_Intervalo_DDFRFD.setBounds(5, 10, 150, 20);
        pnDiasDesdeFReclamoFDevolucion.add(rB_Intervalo_DDFRFD);
        
        rB_Hoy_DDFRFD = new JRadioButton("Hoy");
        rB_Hoy_DDFRFD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Hoy_DDFRFD.isSelected()){
        			rB_Intervalo_DDFRFD.setSelected(false);
        			dC_FF_DDFRFD.setEnabled(false);
        			dC_FI_DDFRFD.setEnabled(false);
        			rB_USemana_DDFRFD.setSelected(false);
        			rB_Mes_DDFRFD.setSelected(false);
        			rB_MAnterior_DDFRFD.setSelected(false);
        			rB_Anio_DDFRFD.setSelected(false);
        			filtrarHoyDDFRFD();
        		}
        	}
        });
        rB_Hoy_DDFRFD.setToolTipText("Piezas llegadas el dia de hoy.");
        rB_Hoy_DDFRFD.setBounds(5, 35, 150, 20);
        pnDiasDesdeFReclamoFDevolucion.add(rB_Hoy_DDFRFD);
        
        rB_USemana_DDFRFD = new JRadioButton("Ultima Semana");
        rB_USemana_DDFRFD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_USemana_DDFRFD.isSelected()){
        			rB_Intervalo_DDFRFD.setSelected(false);
        			dC_FF_DDFRFD.setEnabled(false);
        			dC_FI_DDFRFD.setEnabled(false);
        			rB_Hoy_DDFRFD.setSelected(false);
        			rB_Mes_DDFRFD.setSelected(false);
        			rB_MAnterior_DDFRFD.setSelected(false);
        			rB_Anio_DDFRFD.setSelected(false);
        			filtrarUSemanaDDFRFD();
        		}
        	}
        });
        rB_USemana_DDFRFD.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
        rB_USemana_DDFRFD.setBounds(5, 60, 150, 20);
        pnDiasDesdeFReclamoFDevolucion.add(rB_USemana_DDFRFD);
        
        rB_Mes_DDFRFD = new JRadioButton("Este Mes");
        rB_Mes_DDFRFD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Mes_DDFRFD.isSelected()){
        			rB_Intervalo_DDFRFD.setSelected(false);
        			dC_FF_DDFRFD.setEnabled(false);
        			dC_FI_DDFRFD.setEnabled(false);
        			rB_Hoy_DDFRFD.setSelected(false);
        			rB_USemana_DDFRFD.setSelected(false);
        			rB_MAnterior_DDFRFD.setSelected(false);
        			rB_Anio_DDFRFD.setSelected(false);
        			filtrarMesDDFRFD();
        		}
        	}
        });
        rB_Mes_DDFRFD.setToolTipText("Piezas llegadas en el mes corriente.");
        rB_Mes_DDFRFD.setBounds(5, 85, 150, 20);
        pnDiasDesdeFReclamoFDevolucion.add(rB_Mes_DDFRFD);
        
        rB_MAnterior_DDFRFD = new JRadioButton("Mes Anterior");
        rB_MAnterior_DDFRFD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_MAnterior_DDFRFD.isSelected()){
        			rB_Intervalo_DDFRFD.setSelected(false);
        			dC_FF_DDFRFD.setEnabled(false);
        			dC_FI_DDFRFD.setEnabled(false);
        			rB_Hoy_DDFRFD.setSelected(false);
        			rB_USemana_DDFRFD.setSelected(false);
        			rB_Mes_DDFRFD.setSelected(false);
        			rB_Anio_DDFRFD.setSelected(false);
        			filtrarMAnteriorDDFRFD();
        		}
        	}
        });
        rB_MAnterior_DDFRFD.setBounds(5, 110, 150, 20);
        pnDiasDesdeFReclamoFDevolucion.add(rB_MAnterior_DDFRFD);
        
        rB_Anio_DDFRFD = new JRadioButton("Ultimo A\u00F1o");
        rB_Anio_DDFRFD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Anio_DDFRFD.isSelected()){
        			rB_Intervalo_DDFRFD.setSelected(false);
        			dC_FF_DDFRFD.setEnabled(false);
        			dC_FI_DDFRFD.setEnabled(false);
        			rB_Hoy_DDFRFD.setSelected(false);
        			rB_USemana_DDFRFD.setSelected(false);
        			rB_Mes_DDFRFD.setSelected(false);
        			rB_MAnterior_DDFRFD.setSelected(false);
        			filtrarUAnioDDFRFD();
        		}
        	}
        });
        rB_Anio_DDFRFD.setBounds(5, 135, 150, 20);
        pnDiasDesdeFReclamoFDevolucion.add(rB_Anio_DDFRFD);
        
        JLabel label_28 = new JLabel("Fecha Inicio");
        label_28.setHorizontalAlignment(SwingConstants.CENTER);
        label_28.setEnabled(false);
        label_28.setBounds(160, 11, 120, 20);
        pnDiasDesdeFReclamoFDevolucion.add(label_28);
        
        dC_FI_DDFRFD = new JDateChooser();
        dC_FI_DDFRFD.setEnabled(false);
        dC_FI_DDFRFD.setBounds(290, 10, 150, 20);
        pnDiasDesdeFReclamoFDevolucion.add(dC_FI_DDFRFD);
        
        btn_clear_FIDDFRFD = new JButton("");
        btn_clear_FIDDFRFD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FI_DDFRFD.getDate()!=null)
					dC_FI_DDFRFD.setDate(null);
			}
		});
        btn_clear_FIDDFRFD.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FIDDFRFD.setBounds(450, 10, 25, 20);
        pnDiasDesdeFReclamoFDevolucion.add(btn_clear_FIDDFRFD);
        
        JLabel label_29 = new JLabel("Fecha Fin");
        label_29.setHorizontalAlignment(SwingConstants.CENTER);
        label_29.setEnabled(false);
        label_29.setBounds(485, 11, 120, 20);
        pnDiasDesdeFReclamoFDevolucion.add(label_29);
        
        dC_FF_DDFRFD = new JDateChooser();
        dC_FF_DDFRFD.setEnabled(false);
        dC_FF_DDFRFD.setBounds(615, 10, 150, 20);
        pnDiasDesdeFReclamoFDevolucion.add(dC_FF_DDFRFD);
        
        btn_clear_FFDDFRFD = new JButton("");
        btn_clear_FFDDFRFD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_DDFRFD.getDate()!=null)
					dC_FF_DDFRFD.setDate(null);
			}
		});
        btn_clear_FFDDFRFD.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FFDDFRFD.setBounds(775, 10, 25, 20);
        pnDiasDesdeFReclamoFDevolucion.add(btn_clear_FFDDFRFD);
        
        btn_filtrar_DDFRFD = new JButton("Filtrar");
        btn_filtrar_DDFRFD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtrarIntervaloDDFRFD();
			}
		});
        btn_filtrar_DDFRFD.setEnabled(false);
        btn_filtrar_DDFRFD.setBounds(810, 10, 110, 20);
        pnDiasDesdeFReclamoFDevolucion.add(btn_filtrar_DDFRFD);
        
        btnExportarTabla_DDFRFD = new JButton("");
        btnExportarTabla_DDFRFD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaDDFRFD();
			}
		});
        btnExportarTabla_DDFRFD.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/formulario.png")));
        btnExportarTabla_DDFRFD.setBounds(1230, 120, 32, 32);
        pnDiasDesdeFReclamoFDevolucion.add(btnExportarTabla_DDFRFD);
        
        //RECLAMOS - TURNOS//
        pnReclamos_Turnos = new JPanel();
        control.addTab("RECLAMOS - TURNOS", null, pnReclamos_Turnos, null);
        pnReclamos_Turnos.setLayout(null);
		//TURNOS - RECLAMOS	(RECLAMOS)//
        modelo_tabla_reclamos_turno_Reclamos = new DefaultTableModel(datos_tabla_reclamos_turno_Reclamos , nombreColumnas_tabla_reclamos_turno_Reclamos );        
        tabla_RTR = new JTable(modelo_tabla_reclamos_turno_Reclamos) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false,false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_tabla_reclamos_turno_Reclamos = new TableRowSorter<TableModel>(modelo_tabla_reclamos_turno_Reclamos);
		for(int i = 0; i < tabla_RTR.getColumnCount(); i++) {
			tabla_RTR.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_reclamos_turno_Reclamos.elementAt(i));
			tabla_RTR.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_reclamos_turno_Reclamos.elementAt(i));
		}
        tabla_RTR.setRowSorter(ordenador_tabla_reclamos_turno_Reclamos);
        tabla_RTR.getTableHeader().setReorderingAllowed(false);
        tabla_RTR.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane_tabla_reclamos_turno_Reclamos= new JScrollPane(tabla_RTR);
        scrollPane_tabla_reclamos_turno_Reclamos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_tabla_reclamos_turno_Reclamos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_tabla_reclamos_turno_Reclamos.setBounds(5, 205, 1255, 170);
        pnReclamos_Turnos.add(scrollPane_tabla_reclamos_turno_Reclamos);	 
        tabla_RTR.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//TURNOS - RECLAMOS	(TURNOS)//
        modelo_tabla_reclamos_turno_Turno = new DefaultTableModel(datos_tabla_reclamos_turno_Turno , nombreColumnas_tabla_reclamos_turno_Turno );
        // Agregamos el ordenador para las tablas de los usuarios 
        tabla_RTT = new JTable(modelo_tabla_reclamos_turno_Turno) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false,false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		TableRowSorter<TableModel> ordenador_tabla_reclamos_turno_Turno = new TableRowSorter<TableModel>(modelo_tabla_reclamos_turno_Turno);
		for(int i = 0; i < tabla_RTT.getColumnCount(); i++) {
			tabla_RTT.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_reclamos_turno_Turno.elementAt(i));
			tabla_RTT.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_reclamos_turno_Turno.elementAt(i));
		}
        tabla_RTT.setRowSorter(ordenador_tabla_reclamos_turno_Turno);
        tabla_RTT.getTableHeader().setReorderingAllowed(false);
        tabla_RTT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane_tabla_reclamos_turnos_Turno= new JScrollPane(tabla_RTT);
        scrollPane_tabla_reclamos_turnos_Turno.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_tabla_reclamos_turnos_Turno.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_tabla_reclamos_turnos_Turno.setBounds(5, 425, 1255, 170);
        pnReclamos_Turnos.add(scrollPane_tabla_reclamos_turnos_Turno);
        tabla_RTT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);      

        JLabel lblReclamos_Turnos = new JLabel("RECLAMOS / TURNOS");
        lblReclamos_Turnos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblReclamos_Turnos.setHorizontalAlignment(SwingConstants.CENTER);
        lblReclamos_Turnos.setBounds(935, 11, 200, 25);
        pnReclamos_Turnos.add(lblReclamos_Turnos);
        
        lblReclamoTurnos = new JLabel("");
        lblReclamoTurnos.setHorizontalAlignment(SwingConstants.CENTER);
        lblReclamoTurnos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblReclamoTurnos.setBounds(1157, 11, 100, 25);
        pnReclamos_Turnos.add(lblReclamoTurnos);
        
        JLabel lblReclamosConTurno = new JLabel("NUMERO DE RECLAMOS CON TURNO");
        lblReclamosConTurno.setHorizontalAlignment(SwingConstants.CENTER);
        lblReclamosConTurno.setBounds(5, 400, 240, 20);
        pnReclamos_Turnos.add(lblReclamosConTurno);
        
        lblNum_ReclmaosConTurno = new JLabel("");
        lblNum_ReclmaosConTurno.setText(String.valueOf(numero_turnos));
        lblNum_ReclmaosConTurno.setHorizontalAlignment(SwingConstants.CENTER);
        lblNum_ReclmaosConTurno.setBounds(250, 400, 46, 20);
        pnReclamos_Turnos.add(lblNum_ReclmaosConTurno);
        
        lblNum_ReclamosSinTurno = new JLabel("");
        lblNum_ReclamosSinTurno.setText(String.valueOf(numero_reclamos));
        lblNum_ReclamosSinTurno.setHorizontalAlignment(SwingConstants.CENTER);
        lblNum_ReclamosSinTurno.setBounds(250, 180, 45, 20);
        pnReclamos_Turnos.add(lblNum_ReclamosSinTurno);
        
        JLabel lblNumeroDeReclamos = new JLabel("NUMERO DE RECLAMOS");
        lblNumeroDeReclamos.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumeroDeReclamos.setBounds(5, 180, 230, 20);
        pnReclamos_Turnos.add(lblNumeroDeReclamos);
        
        rB_Intervalo_RT = new JRadioButton("Intervalo de Fechas");
        rB_Intervalo_RT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Intervalo_RT.isSelected()){
        			dC_FF_RT.setEnabled(true);
        			dC_FI_RT.setEnabled(true);
        			btn_filtrar_RT.setEnabled(true);
        			
        			rB_Hoy_RT.setSelected(false);
        			rB_USemana_RT.setSelected(false);
        			rB_Mes_RT.setSelected(false);
        			rB_MAnterior_RT.setSelected(false);
        			rB_Anio_RT.setSelected(false);
        		}else{
        			dC_FF_RT.setEnabled(false);
        			dC_FI_RT.setEnabled(false);
        			btn_filtrar_RT.setEnabled(false);
        		}
        	}
        });
        rB_Intervalo_RT.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
        rB_Intervalo_RT.setBounds(5, 10, 150, 20);
        pnReclamos_Turnos.add(rB_Intervalo_RT);
        
        rB_Hoy_RT = new JRadioButton("Hoy");
        rB_Hoy_RT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Hoy_RT.isSelected()){
        			rB_Intervalo_RT.setSelected(false);
        			dC_FF_RT.setEnabled(false);
        			dC_FI_RT.setEnabled(false);
        			rB_USemana_RT.setSelected(false);
        			rB_Mes_RT.setSelected(false);
        			rB_MAnterior_RT.setSelected(false);
        			rB_Anio_RT.setSelected(false);
        			filtrarHoyRT();
        		}
        	}
        });
        rB_Hoy_RT.setToolTipText("Piezas llegadas el dia de hoy.");
        rB_Hoy_RT.setBounds(5, 35, 150, 20);
        pnReclamos_Turnos.add(rB_Hoy_RT);
        
        rB_USemana_RT = new JRadioButton("Ultima Semana");
        rB_USemana_RT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_USemana_RT.isSelected()){
        			rB_Intervalo_RT.setSelected(false);
        			dC_FF_RT.setEnabled(false);
        			dC_FI_RT.setEnabled(false);
        			rB_Hoy_RT.setSelected(false);
        			rB_Mes_RT.setSelected(false);
        			rB_MAnterior_RT.setSelected(false);
        			rB_Anio_RT.setSelected(false);
        			filtrarUSemanaRT();
        		}
        	}
        });
        rB_USemana_RT.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
        rB_USemana_RT.setBounds(5, 60, 150, 20);
        pnReclamos_Turnos.add(rB_USemana_RT);
        
        rB_Mes_RT = new JRadioButton("Este Mes");
        rB_Mes_RT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Mes_RT.isSelected()){
        			rB_Intervalo_RT.setSelected(false);
        			dC_FF_RT.setEnabled(false);
        			dC_FI_RT.setEnabled(false);
        			rB_Hoy_RT.setSelected(false);
        			rB_USemana_RT.setSelected(false);
        			rB_MAnterior_RT.setSelected(false);
        			rB_Anio_RT.setSelected(false);
        			filtrarMesRT();
        		}
        	}
        });
        rB_Mes_RT.setToolTipText("Piezas llegadas en el mes corriente.");
        rB_Mes_RT.setBounds(5, 85, 150, 20);
        pnReclamos_Turnos.add(rB_Mes_RT);
        
        rB_MAnterior_RT = new JRadioButton("Mes Anterior");
        rB_MAnterior_RT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_MAnterior_RT.isSelected()){
        			rB_Intervalo_RT.setSelected(false);
        			dC_FF_RT.setEnabled(false);
        			dC_FI_RT.setEnabled(false);
        			rB_Hoy_RT.setSelected(false);
        			rB_USemana_RT.setSelected(false);
        			rB_Mes_RT.setSelected(false);
        			rB_Anio_RT.setSelected(false);
        			filtrarMAnteriorRT();
        		}
        	}
        });
        rB_MAnterior_RT.setBounds(5, 110, 150, 20);
        pnReclamos_Turnos.add(rB_MAnterior_RT);
        
        rB_Anio_RT = new JRadioButton("Ultimo A\u00F1o");
        rB_Anio_RT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Anio_RT.isSelected()){
        			rB_Intervalo_RT.setSelected(false);
        			dC_FF_RT.setEnabled(false);
        			dC_FI_RT.setEnabled(false);
        			rB_Hoy_RT.setSelected(false);
        			rB_USemana_RT.setSelected(false);
        			rB_Mes_RT.setSelected(false);
        			rB_MAnterior_RT.setSelected(false);
        			filtrarUAnioRT();
        		}
        	}
        });
        rB_Anio_RT.setBounds(5, 135, 150, 20);
        pnReclamos_Turnos.add(rB_Anio_RT);
        
        JLabel label = new JLabel("Fecha Inicio");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setEnabled(false);
        label.setBounds(160, 10, 120, 20);
        pnReclamos_Turnos.add(label);
        
        dC_FI_RT = new JDateChooser();
        dC_FI_RT.setEnabled(false);
        dC_FI_RT.setBounds(290, 10, 150, 20);
        pnReclamos_Turnos.add(dC_FI_RT);
        
        btn_clear_FIRT = new JButton("");
        btn_clear_FIRT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FI_RT.getDate()!=null)
					dC_FI_RT.setDate(null);
			}
		});
        btn_clear_FIRT.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FIRT.setBounds(450, 10, 25, 20);
        pnReclamos_Turnos.add(btn_clear_FIRT);
        
        JLabel label_1 = new JLabel("Fecha Fin");
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setEnabled(false);
        label_1.setBounds(485, 10, 120, 20);
        pnReclamos_Turnos.add(label_1);
        
        dC_FF_RT = new JDateChooser();
        dC_FF_RT.setEnabled(false);
        dC_FF_RT.setBounds(615, 10, 150, 20);
        pnReclamos_Turnos.add(dC_FF_RT);
        
        btn_clear_FFRT = new JButton("");
        btn_clear_FFRT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_RT.getDate()!=null)
					dC_FF_RT.setDate(null);
			}
		});
        btn_clear_FFRT.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FFRT.setBounds(775, 10, 25, 20);
        pnReclamos_Turnos.add(btn_clear_FFRT);
        
        btn_filtrar_RT = new JButton("Filtrar");
        btn_filtrar_RT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarIntervaloRT();
			}
		});
        btn_filtrar_RT.setEnabled(false);
        btn_filtrar_RT.setBounds(810, 10, 110, 20);
        pnReclamos_Turnos.add(btn_filtrar_RT);
        
        btnExportarTabla_RT = new JButton("");
        btnExportarTabla_RT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaRT();
			}
		});
        btnExportarTabla_RT.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/formulario.png")));
        btnExportarTabla_RT.setBounds(1230, 165, 32, 32);
        pnReclamos_Turnos.add(btnExportarTabla_RT);
        
		if(numero_reclamos==0 && numero_turnos==0){
			lblReclamoTurnos.setText("N/A");
	        lblReclamoTurnos.setBackground(Color.WHITE);
		}else{
			lblReclamoTurnos.setText(String.valueOf(numero_reclamos/numero_turnos));
			if((numero_reclamos/numero_turnos)<= 1){
				lblReclamoTurnos.setBackground(Color.GREEN);

			}else{
				if((numero_reclamos/numero_turnos)> 2){
					lblReclamoTurnos.setBackground(Color.RED);
				}else{
					lblReclamoTurnos.setBackground(Color.YELLOW);
				}
			}
		}

        //PIEZAS LLEGADAS - PIEZAS DEVUELTAS//        
        pnPiezasLlegadas_PiezasDevueltas = new JPanel();
        pnPiezasLlegadas_PiezasDevueltas.setLayout(null);
        control.addTab("PIEZAS LLEGADAS - PIEZAS DEVUELTAS", null, pnPiezasLlegadas_PiezasDevueltas, null);
		//PIEZAS LLEGADAS PIEZAS DEVUELTAS (LLEGADAS)//
        modelo_tabla_Pllegadas_PDevuletas_LLegadas = new DefaultTableModel(datos_tabla_Pllegadas_PDevuletas_LLegadas , nombreColumnas_tabla_Pllegadas_PDevuletas_LLegadas );
        tabla_PLLPDL = new JTable(modelo_tabla_Pllegadas_PDevuletas_LLegadas) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false,false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_tabla_Pllegadas_PDevuletas_LLegadas = new TableRowSorter<TableModel>(modelo_tabla_Pllegadas_PDevuletas_LLegadas);
		for(int i = 0; i < tabla_PLLPDL.getColumnCount(); i++) {
			tabla_PLLPDL.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_Pllegadas_PDevuletas_LLegadas.elementAt(i));
			tabla_PLLPDL.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_Pllegadas_PDevuletas_LLegadas.elementAt(i));
		}
        tabla_PLLPDL.setRowSorter(ordenador_tabla_Pllegadas_PDevuletas_LLegadas);
        tabla_PLLPDL.getTableHeader().setReorderingAllowed(false);
        tabla_PLLPDL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane_tabla_Pllegadas_PDevuletas_LLegadas= new JScrollPane(tabla_PLLPDL);
        scrollPane_tabla_Pllegadas_PDevuletas_LLegadas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_tabla_Pllegadas_PDevuletas_LLegadas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_tabla_Pllegadas_PDevuletas_LLegadas.setBounds(5, 205, 1255, 170);
        pnPiezasLlegadas_PiezasDevueltas.add(scrollPane_tabla_Pllegadas_PDevuletas_LLegadas);	
        tabla_PLLPDL.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //PIEZAS LLEGADAS PIEZAS DEVUELTAS (DEVUELTAS)//
		modelo_tabla_Pllegadas_PDevuletas_Devueltas = new DefaultTableModel(datos_tabla_Pllegadas_PDevuletas_Devueltas , nombreColumnas_tabla_Pllegadas_PDevuletas_Devueltas );     
        tabla_PLLPDD = new JTable(modelo_tabla_Pllegadas_PDevuletas_Devueltas) {
        	private static final long serialVersionUID = 1L;
        	boolean[] columnEditables = new boolean[] {
        			false, false, false, false, false,
        			false, false, false,false, false,
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        };
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_tabla_Pllegadas_PDevuletas_Devueltas = new TableRowSorter<TableModel>(modelo_tabla_Pllegadas_PDevuletas_Devueltas);
		for(int i = 0; i < tabla_PLLPDD.getColumnCount(); i++) {
			tabla_PLLPDD.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_Pllegadas_PDevuletas_Devueltas.elementAt(i));
			tabla_PLLPDD.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_Pllegadas_PDevuletas_Devueltas.elementAt(i));
		}
        tabla_PLLPDD.setRowSorter(ordenador_tabla_Pllegadas_PDevuletas_Devueltas);
        tabla_PLLPDD.getTableHeader().setReorderingAllowed(false);
        tabla_PLLPDD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane_tabla_Pllegadas_PDevuletas_Devueltas= new JScrollPane(tabla_PLLPDD);
        scrollPane_tabla_Pllegadas_PDevuletas_Devueltas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_tabla_Pllegadas_PDevuletas_Devueltas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_tabla_Pllegadas_PDevuletas_Devueltas.setBounds(5, 425, 1255, 170);
        pnPiezasLlegadas_PiezasDevueltas.add(scrollPane_tabla_Pllegadas_PDevuletas_Devueltas);
        tabla_PLLPDD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        JLabel lblPiezasLlegadas = new JLabel("PIEZAS DEVUELTAS / PIEZAS LLEGADAS");
        lblPiezasLlegadas.setHorizontalAlignment(SwingConstants.CENTER);
        lblPiezasLlegadas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblPiezasLlegadas.setBounds(933, 11, 200, 25);
        pnPiezasLlegadas_PiezasDevueltas.add(lblPiezasLlegadas);
        
        lblLlegadaDevueltas = new JLabel("");
        lblLlegadaDevueltas.setHorizontalAlignment(SwingConstants.CENTER);
        lblLlegadaDevueltas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblLlegadaDevueltas.setBounds(1155, 11, 100, 25);
        pnPiezasLlegadas_PiezasDevueltas.add(lblLlegadaDevueltas);
        
        JLabel lblNumeroDePiezas_1 = new JLabel("NUMERO DE  PIEZAS DEVUELTAS");
        lblNumeroDePiezas_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumeroDePiezas_1.setBounds(5, 400, 230, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(lblNumeroDePiezas_1);
        
        lblNum_PDevueltas = new JLabel("");
        lblNum_PDevueltas.setText(String.valueOf(numero_PiezasDevueltas));
        lblNum_PDevueltas.setHorizontalAlignment(SwingConstants.CENTER);
        lblNum_PDevueltas.setBounds(250, 400, 45, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(lblNum_PDevueltas);
        
        lblNumPLlegadas = new JLabel("");
        lblNumPLlegadas.setText(String.valueOf(numero_PiezasLlegadas));
        lblNumPLlegadas.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumPLlegadas.setBounds(250, 180, 45, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(lblNumPLlegadas);
        
        JLabel lblNumeroDePiezas = new JLabel("NUMERO DE PIEZAS LLEGADAS");
        lblNumeroDePiezas.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumeroDePiezas.setBounds(5, 180, 230, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(lblNumeroDePiezas);
        
        lblPiezasLLegadas_PiezasDevueltas = new JLabel("");
        lblPiezasLLegadas_PiezasDevueltas.setText(String.valueOf(numero_PiezasDevueltas - numero_PiezasLlegadas));
        lblPiezasLLegadas_PiezasDevueltas.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblPiezasLLegadas_PiezasDevueltas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblPiezasLLegadas_PiezasDevueltas.setBounds(1155, 49, 100, 25);
        pnPiezasLlegadas_PiezasDevueltas.add(lblPiezasLLegadas_PiezasDevueltas);
                
        JLabel lblPiezasLlegadas_1 = new JLabel("PIEZAS LLEGADAS - PIEZAS DEVUELTAS");
        lblPiezasLlegadas_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblPiezasLlegadas_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblPiezasLlegadas_1.setBounds(933, 49, 200, 25);
        pnPiezasLlegadas_PiezasDevueltas.add(lblPiezasLlegadas_1);
        
        rB_Anio_PLLPD = new JRadioButton("Ultimo A\u00F1o");
        rB_Anio_PLLPD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Anio_PLLPD.isSelected()){
        			rB_Intervalo_PLLPD.setSelected(false);
        			dC_FF_PLLPD.setEnabled(false);
        			dC_FI_PLLPD.setEnabled(false);
        			rB_Hoy_PLLPD.setSelected(false);
        			rB_USemana_PLLPD.setSelected(false);
        			rB_Mes_PLLPD.setSelected(false);
        			rB_MAnterior_PLLPD.setSelected(false);
        			filtrarUAnioPLLPD();
        		}
        	}
        });
        rB_Anio_PLLPD.setBounds(5, 135, 150, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(rB_Anio_PLLPD);
        
        rB_MAnterior_PLLPD = new JRadioButton("Mes Anterior");
        rB_MAnterior_PLLPD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_MAnterior_PLLPD.isSelected()){
        			rB_Intervalo_PLLPD.setSelected(false);
        			dC_FF_PLLPD.setEnabled(false);
        			dC_FI_PLLPD.setEnabled(false);
        			rB_Hoy_PLLPD.setSelected(false);
        			rB_USemana_PLLPD.setSelected(false);
        			rB_Mes_PLLPD.setSelected(false);
        			rB_Anio_PLLPD.setSelected(false);
        			filtrarMAnteriorPLLPD();
        		}
        	}
        });
        rB_MAnterior_PLLPD.setBounds(5, 110, 150, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(rB_MAnterior_PLLPD);
        
        rB_Mes_PLLPD = new JRadioButton("Este Mes");
        rB_Mes_PLLPD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Mes_PLLPD.isSelected()){
        			rB_Intervalo_PLLPD.setSelected(false);
        			dC_FF_PLLPD.setEnabled(false);
        			dC_FI_PLLPD.setEnabled(false);
        			rB_Hoy_PLLPD.setSelected(false);
        			rB_USemana_PLLPD.setSelected(false);
        			rB_MAnterior_PLLPD.setSelected(false);
        			rB_Anio_PLLPD.setSelected(false);
        			filtrarMesPLLPD();
        		}
        	}
        });
        rB_Mes_PLLPD.setToolTipText("Piezas llegadas en el mes corriente.");
        rB_Mes_PLLPD.setBounds(5, 85, 150, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(rB_Mes_PLLPD);
        
        rB_USemana_PLLPD = new JRadioButton("Ultima Semana");
        rB_USemana_PLLPD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_USemana_PLLPD.isSelected()){
        			rB_Intervalo_PLLPD.setSelected(false);
        			dC_FF_PLLPD.setEnabled(false);
        			dC_FI_PLLPD.setEnabled(false);
        			rB_Hoy_PLLPD.setSelected(false);
        			rB_Mes_PLLPD.setSelected(false);
        			rB_MAnterior_PLLPD.setSelected(false);
        			rB_Anio_PLLPD.setSelected(false);
        			filtrarUSemanaPLLPD();
        		}
        	}
        });
        rB_USemana_PLLPD.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
        rB_USemana_PLLPD.setBounds(5, 60, 150, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(rB_USemana_PLLPD);
        
        rB_Hoy_PLLPD = new JRadioButton("Hoy");
        rB_Hoy_PLLPD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Hoy_PLLPD.isSelected()){
        			rB_Intervalo_PLLPD.setSelected(false);
        			dC_FF_PLLPD.setEnabled(false);
        			dC_FI_PLLPD.setEnabled(false);
        			rB_USemana_PLLPD.setSelected(false);
        			rB_Mes_PLLPD.setSelected(false);
        			rB_MAnterior_PLLPD.setSelected(false);
        			rB_Anio_PLLPD.setSelected(false);
        			filtrarHoyPLLPD();
        		}
        	}
        });
        rB_Hoy_PLLPD.setToolTipText("Piezas llegadas el dia de hoy.");
        rB_Hoy_PLLPD.setBounds(5, 35, 150, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(rB_Hoy_PLLPD);
        
        rB_Intervalo_PLLPD = new JRadioButton("Intervalo de Fechas");
        rB_Intervalo_PLLPD.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Intervalo_PLLPD.isSelected()){
        			dC_FF_PLLPD.setEnabled(true);
        			dC_FI_PLLPD.setEnabled(true);
        			btn_filtrar_PLLPD.setEnabled(true);
        			rB_Hoy_PLLPD.setSelected(false);
        			rB_USemana_PLLPD.setSelected(false);
        			rB_Mes_PLLPD.setSelected(false);
        			rB_MAnterior_PLLPD.setSelected(false);
        			rB_Anio_PLLPD.setSelected(false);
        		}else{
        			dC_FF_PLLPD.setEnabled(false);
        			dC_FI_PLLPD.setEnabled(false);
        			btn_filtrar_PLLPD.setEnabled(false);
        		}
        	}
        });
        rB_Intervalo_PLLPD.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
        rB_Intervalo_PLLPD.setBounds(5, 10, 150, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(rB_Intervalo_PLLPD);
        
        JLabel label_12 = new JLabel("Fecha Inicio");
        label_12.setHorizontalAlignment(SwingConstants.CENTER);
        label_12.setEnabled(false);
        label_12.setBounds(160, 10, 120, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(label_12);
        
        dC_FI_PLLPD = new JDateChooser();
        dC_FI_PLLPD.setEnabled(false);
        dC_FI_PLLPD.setBounds(290, 10, 150, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(dC_FI_PLLPD);
        
        btn_clear_FIPLLPD = new JButton("");
        btn_clear_FIPLLPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FI_PLLPD.getDate()!=null)
					dC_FI_PLLPD.setDate(null);
			}
		});
        btn_clear_FIPLLPD.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FIPLLPD.setBounds(450, 10, 25, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(btn_clear_FIPLLPD);
        
        JLabel label_13 = new JLabel("Fecha Fin");
        label_13.setHorizontalAlignment(SwingConstants.CENTER);
        label_13.setEnabled(false);
        label_13.setBounds(485, 10, 120, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(label_13);
        
        dC_FF_PLLPD = new JDateChooser();
        dC_FF_PLLPD.setEnabled(false);
        dC_FF_PLLPD.setBounds(615, 10, 150, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(dC_FF_PLLPD);
        
        btn_clear_FFPLLPD = new JButton("");
        btn_clear_FFPLLPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_PLLPD.getDate()!=null)
					dC_FF_PLLPD.setDate(null);
			}
		});
        btn_clear_FFPLLPD.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FFPLLPD.setBounds(775, 10, 25, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(btn_clear_FFPLLPD);
        
        btn_filtrar_PLLPD = new JButton("Filtrar");
        btn_filtrar_PLLPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarIntervaloPLLPD();
			}
		});
        btn_filtrar_PLLPD.setEnabled(false);
        btn_filtrar_PLLPD.setBounds(810, 10, 110, 20);
        pnPiezasLlegadas_PiezasDevueltas.add(btn_filtrar_PLLPD);
        
        btnExportarTabla_PLLPD = new JButton("");
        btnExportarTabla_PLLPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaPLLPD();
			}
		});
        btnExportarTabla_PLLPD.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/formulario.png")));
        btnExportarTabla_PLLPD.setBounds(1230, 165, 32, 32);
        pnPiezasLlegadas_PiezasDevueltas.add(btnExportarTabla_PLLPD);

		if(numero_PiezasDevueltas==0 && numero_PiezasLlegadas==0){
			lblLlegadaDevueltas.setText("N/A");
			lblLlegadaDevueltas.setBackground(Color.WHITE);
		}else{
			lblLlegadaDevueltas.setText(String.valueOf(numero_PiezasDevueltas / numero_PiezasLlegadas));
			if((numero_PiezasDevueltas/numero_PiezasLlegadas) <= 0.90){
				lblLlegadaDevueltas.setBackground(Color.RED);

			}else{
				if((numero_PiezasDevueltas/numero_PiezasLlegadas)>0.90 && (numero_PiezasDevueltas/numero_PiezasLlegadas)<1.10){
					lblLlegadaDevueltas.setBackground(Color.GREEN);
				}else{
					lblLlegadaDevueltas.setBackground(Color.YELLOW);
				}
			}
		}
		if((numero_PiezasLlegadas - numero_PiezasDevueltas)> 5){
			lblPiezasLLegadas_PiezasDevueltas.setBackground(Color.RED);
		}else{
			if((numero_PiezasLlegadas - numero_PiezasDevueltas)<= 0){
				lblPiezasLLegadas_PiezasDevueltas.setBackground(Color.GREEN);
			}else{
				lblPiezasLLegadas_PiezasDevueltas.setBackground(Color.YELLOW);
			}
		}
        
		//MANO DE OBRA//    
        pnManoObra = new JPanel();
        control.addTab("MANO DE OBRA", null, pnManoObra, null);
        pnManoObra.setLayout(null);
        modelo_tabla_mano_obra = new DefaultTableModel(datos_tabla_mano_obra , nombreColumnas_tabla_mano_obra );
        // Agregamos el ordenador para las tablas de los usuarios       
        tabla_MO = new JTable(modelo_tabla_mano_obra) {
        	private static final long serialVersionUID = 1L;
        	boolean[] columnEditables = new boolean[] {
        			false, false, false, false, false,
        			false, false, false,false, false,
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        };
		TableRowSorter<TableModel> ordenador_tabla_mano_obra = new TableRowSorter<TableModel>(modelo_tabla_mano_obra);
		for(int i = 0; i < tabla_MO.getColumnCount(); i++) {
			tabla_MO.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_mano_obra.elementAt(i));
			tabla_MO.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_mano_obra.elementAt(i));
		}
        tabla_MO.setRowSorter(ordenador_tabla_mano_obra);
        tabla_MO.getTableHeader().setReorderingAllowed(false);
        tabla_MO.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane_mano_obra= new JScrollPane(tabla_MO);
        scrollPane_mano_obra.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_mano_obra.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_mano_obra.setBounds(5, 160, 1255, 435);
        pnManoObra.add(scrollPane_mano_obra);
        tabla_MO.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        lblValorTotalDe = new JLabel("VALOR TOTAL DE MANO DE OBRA");
        lblValorTotalDe.setHorizontalAlignment(SwingConstants.CENTER);
        lblValorTotalDe.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblValorTotalDe.setBounds(943, 11, 190, 25);
        pnManoObra.add(lblValorTotalDe);
        
        lblHorasTotalesDe = new JLabel("HORAS TOTALES DE MANO DE OBRA");
        lblHorasTotalesDe.setHorizontalAlignment(SwingConstants.CENTER);
        lblHorasTotalesDe.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblHorasTotalesDe.setBounds(943, 49, 190, 25);
        pnManoObra.add(lblHorasTotalesDe);
        
        lbl_HsTotalesMO = new JLabel("");
        lbl_HsTotalesMO.setText(String.valueOf(hs_mano_obra)+"HS");
        lbl_HsTotalesMO.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_HsTotalesMO.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lbl_HsTotalesMO.setBounds(1155, 49, 100, 25);
        pnManoObra.add(lbl_HsTotalesMO);
        
        lbl_ValorTotalMO = new JLabel("");
        lbl_ValorTotalMO.setText("$"+String.valueOf(preico_mano_obra));
        lbl_ValorTotalMO.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_ValorTotalMO.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lbl_ValorTotalMO.setBounds(1155, 11, 100, 25);
        pnManoObra.add(lbl_ValorTotalMO);
        
        rB_Anio_MO = new JRadioButton("Ultimo A\u00F1o");
        rB_Anio_MO.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Anio_MO.isSelected()){
        			rB_Intervalo_MO.setSelected(false);
        			dC_FF_MO.setEnabled(false);
        			dC_FI_MO.setEnabled(false);
        			rB_Hoy_MO.setSelected(false);
        			rB_USemana_MO.setSelected(false);
        			rB_Mes_MO.setSelected(false);
        			rB_MAnterior_MO.setSelected(false);
        			filtrarUAnioMO();
        		}
        	}
        });
        rB_Anio_MO.setBounds(5, 135, 150, 20);
        pnManoObra.add(rB_Anio_MO);
        
        rB_MAnterior_MO = new JRadioButton("Mes Anterior");
        rB_MAnterior_MO.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_MAnterior_MO.isSelected()){
					rB_Intervalo_MO.setSelected(false);
					dC_FF_MO.setEnabled(false);
					dC_FI_MO.setEnabled(false);
					rB_Hoy_MO.setSelected(false);
					rB_USemana_MO.setSelected(false);
					rB_Mes_MO.setSelected(false);
					rB_Anio_MO.setSelected(false);
					filtrarMAnteriorMO();
				}
			}
		});
        rB_MAnterior_MO.setBounds(5, 110, 150, 20);
        pnManoObra.add(rB_MAnterior_MO);
        
        rB_Mes_MO = new JRadioButton("Este Mes");
        rB_Mes_MO.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Mes_MO.isSelected()){
        			rB_Intervalo_MO.setSelected(false);
        			dC_FF_MO.setEnabled(false);
        			dC_FI_MO.setEnabled(false);
        			rB_Hoy_MO.setSelected(false);
        			rB_USemana_MO.setSelected(false);
        			rB_MAnterior_MO.setSelected(false);
        			rB_Anio_MO.setSelected(false);
        			filtrarMesMO();
        		}
        	}
        });
        rB_Mes_MO.setToolTipText("Piezas llegadas en el mes corriente.");
        rB_Mes_MO.setBounds(5, 85, 150, 20);
        pnManoObra.add(rB_Mes_MO);
        
        rB_USemana_MO = new JRadioButton("Ultima Semana");
        rB_USemana_MO.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_USemana_MO.isSelected()){
        			rB_Intervalo_MO.setSelected(false);
        			dC_FF_MO.setEnabled(false);
        			dC_FI_MO.setEnabled(false);
        			rB_Hoy_MO.setSelected(false);
        			rB_Mes_MO.setSelected(false);
        			rB_MAnterior_MO.setSelected(false);
        			rB_Anio_MO.setSelected(false);
        			filtrarUSemanaMO();
        		}
        	}
        });
        rB_USemana_MO.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
        rB_USemana_MO.setBounds(5, 60, 150, 20);
        pnManoObra.add(rB_USemana_MO);
        
        rB_Hoy_MO = new JRadioButton("Hoy");
        rB_Hoy_MO.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		if(rB_Hoy_MO.isSelected()){
        			rB_Intervalo_MO.setSelected(false);
        			dC_FF_MO.setEnabled(false);
        			dC_FI_MO.setEnabled(false);
        			rB_USemana_MO.setSelected(false);
        			rB_Mes_MO.setSelected(false);
        			rB_MAnterior_MO.setSelected(false);
        			rB_Anio_MO.setSelected(false);
        			filtrarHoyMO();
        		}
        	}
        });
        rB_Hoy_MO.setToolTipText("Piezas llegadas el dia de hoy.");
        rB_Hoy_MO.setBounds(5, 35, 150, 20);
        pnManoObra.add(rB_Hoy_MO);
        
        rB_Intervalo_MO = new JRadioButton("Intervalo de Fechas");
        rB_Intervalo_MO.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Intervalo_MO.isSelected()){
					dC_FF_MO.setEnabled(true);
					dC_FI_MO.setEnabled(true);
					btn_filtrar_MO.setEnabled(true);
					rB_Hoy_MO.setSelected(false);
					rB_USemana_MO.setSelected(false);
					rB_Mes_MO.setSelected(false);
					rB_MAnterior_MO.setSelected(false);
					rB_Anio_MO.setSelected(false);
				}else{
					dC_FF_MO.setEnabled(false);
					dC_FI_MO.setEnabled(false);
					btn_filtrar_MO.setEnabled(false);
				}
			}
		});
        rB_Intervalo_MO.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
        rB_Intervalo_MO.setBounds(5, 10, 150, 20);
        pnManoObra.add(rB_Intervalo_MO);
        
        JLabel label_14 = new JLabel("Fecha Inicio");
        label_14.setHorizontalAlignment(SwingConstants.CENTER);
        label_14.setEnabled(false);
        label_14.setBounds(160, 10, 120, 20);
        pnManoObra.add(label_14);
        
        dC_FI_MO = new JDateChooser();
        dC_FI_MO.setEnabled(false);
        dC_FI_MO.setBounds(290, 10, 150, 20);
        pnManoObra.add(dC_FI_MO);
        
        btn_clear_FIMO = new JButton("");
        btn_clear_FIMO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FI_MO.getDate()!=null)
					dC_FI_MO.setDate(null);
			}
		});
        btn_clear_FIMO.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FIMO.setBounds(450, 10, 25, 20);
        pnManoObra.add(btn_clear_FIMO);
        
        JLabel label_15 = new JLabel("Fecha Fin");
        label_15.setHorizontalAlignment(SwingConstants.CENTER);
        label_15.setEnabled(false);
        label_15.setBounds(485, 10, 120, 20);
        pnManoObra.add(label_15);
        
        dC_FF_MO = new JDateChooser();
        dC_FF_MO.setEnabled(false);
        dC_FF_MO.setBounds(615, 10, 150, 20);
        pnManoObra.add(dC_FF_MO);
        
        btn_clear_FFMO = new JButton("");
        btn_clear_FFMO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_MO.getDate()!=null)
					dC_FF_MO.setDate(null);
			}
		});
        btn_clear_FFMO.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
        btn_clear_FFMO.setBounds(775, 10, 25, 20);
        pnManoObra.add(btn_clear_FFMO);
        
        btn_filtrar_MO = new JButton("Filtrar");
        btn_filtrar_MO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarIntervaloMO();
			}
		});
        btn_filtrar_MO.setEnabled(false);
        btn_filtrar_MO.setBounds(810, 10, 110, 20);
        pnManoObra.add(btn_filtrar_MO);
        
        btnExportarTabla_MO = new JButton("");
        btnExportarTabla_MO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaMO();
			}
		});
        btnExportarTabla_MO.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/formulario.png")));
        btnExportarTabla_MO.setBounds(1230, 120, 32, 32);
        pnManoObra.add(btnExportarTabla_MO);
        
        JLabel lbl_PromFRecurso_FCierre = new JLabel("");
        if (sum_frecurso_fcierre!=0 && N_casos!=0){
        	lbl_PromFRecurso_FCierre.setText(String.valueOf(sum_frecurso_fcierre / N_casos));
        }else{
        	lbl_PromFRecurso_FCierre.setText("N/A");
        }

        //RECURSO - CIERRE ORDEN//
        pnRecursoCierre = new JPanel();
        control.addTab("RECURSO - CIERRE ORDEN", null, pnRecursoCierre, null);
        pnRecursoCierre.setLayout(null);
        modelo_tabla_recurso_cierre = new DefaultTableModel(datos_tabla_recurso_cierre , nombreColumnas_tabla_recurso_cierre);
        tabla_RC = new JTable(modelo_tabla_recurso_cierre) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false,false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador_tabla_recurso_cierre = new TableRowSorter<TableModel>(modelo_tabla_recurso_cierre);
		for(int i = 0; i < tabla_RC.getColumnCount(); i++) {
			tabla_RC.getColumnModel().getColumn(i).setPreferredWidth(anchos_tabla_recurso_cierre.elementAt(i));
			tabla_RC.getColumnModel().getColumn(i).setMinWidth(anchos_tabla_recurso_cierre.elementAt(i));
		}
        tabla_RC.setRowSorter(ordenador_tabla_recurso_cierre);
        tabla_RC.getTableHeader().setReorderingAllowed(false);
        tabla_RC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane_recurso_cierre= new JScrollPane(tabla_RC);
        scrollPane_recurso_cierre.setBounds(5, 160, 1255, 435);
        pnRecursoCierre.add(scrollPane_recurso_cierre);
        scrollPane_recurso_cierre.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_recurso_cierre.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        tabla_RC.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        JLabel lblPromediofrecurso = new JLabel("PROMEDIO (F.RECURSO - F.CIERRE ORDEN) / #CASOS");
        lblPromediofrecurso.setFont(new Font("Tahoma", Font.PLAIN, 8));
        lblPromediofrecurso.setBounds(933, 11, 212, 25);
        pnRecursoCierre.add(lblPromediofrecurso);
        lblPromediofrecurso.setHorizontalAlignment(SwingConstants.CENTER);
        lblPromediofrecurso.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
    	lbl_PromFRecurso_FCierre = new JLabel("");
    	lbl_PromFRecurso_FCierre.setBounds(1155, 11, 100, 25);
    	pnRecursoCierre.add(lbl_PromFRecurso_FCierre);
    	lbl_PromFRecurso_FCierre.setHorizontalAlignment(SwingConstants.CENTER);
    	lbl_PromFRecurso_FCierre.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    	
    	JLabel lblSumatoriaFrecurso = new JLabel("SUMATORIA (F.RECURSO - F.CIERRE)");
    	lblSumatoriaFrecurso.setBounds(933, 49, 212, 25);
    	pnRecursoCierre.add(lblSumatoriaFrecurso);
    	lblSumatoriaFrecurso.setHorizontalAlignment(SwingConstants.CENTER);
    	lblSumatoriaFrecurso.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    	
    	JLabel lbl_Sum_FRecurso_FCierre = new JLabel(String.valueOf(sum_frecurso_fcierre));
    	lbl_Sum_FRecurso_FCierre.setBounds(1155, 49, 100, 25);
    	pnRecursoCierre.add(lbl_Sum_FRecurso_FCierre);
    	lbl_Sum_FRecurso_FCierre.setHorizontalAlignment(SwingConstants.CENTER);
    	lbl_Sum_FRecurso_FCierre.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    	
    	JLabel lblcasos = new JLabel("#CASOS");
    	lblcasos.setBounds(933, 90, 212, 25);
    	pnRecursoCierre.add(lblcasos);
    	lblcasos.setHorizontalAlignment(SwingConstants.CENTER);
    	lblcasos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    	
    	JLabel lbl_N_Casos = new JLabel(String.valueOf(N_casos));
    	lbl_N_Casos.setBounds(1155, 90, 100, 25);
    	pnRecursoCierre.add(lbl_N_Casos);
    	lbl_N_Casos.setHorizontalAlignment(SwingConstants.CENTER);
    	lbl_N_Casos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    	
    	rB_Anio_RC = new JRadioButton("Ultimo A\u00F1o");
    	rB_Anio_RC.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Anio_RC.isSelected()){
    				rB_Intervalo_RC.setSelected(false);
    				dC_FF_RC.setEnabled(false);
    				dC_FI_RC.setEnabled(false);
    				rB_Hoy_RC.setSelected(false);
    				rB_USemana_RC.setSelected(false);
    				rB_Mes_RC.setSelected(false);
    				rB_MAnterior_RC.setSelected(false);
    				filtrarUAnioRC();
    			}
    		}
    	});
    	rB_Anio_RC.setBounds(5, 135, 150, 20);
    	pnRecursoCierre.add(rB_Anio_RC);
    	
    	rB_MAnterior_RC = new JRadioButton("Mes Anterior");
    	rB_MAnterior_RC.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_MAnterior_RC.isSelected()){
    				rB_Intervalo_RC.setSelected(false);
    				dC_FF_RC.setEnabled(false);
    				dC_FI_RC.setEnabled(false);
    				rB_Hoy_RC.setSelected(false);
    				rB_USemana_RC.setSelected(false);
    				rB_Mes_RC.setSelected(false);
    				rB_Anio_RC.setSelected(false);
    				filtrarMAnteriorRC();
    			}
    		}
    	});
    	rB_MAnterior_RC.setBounds(5, 110, 150, 20);
    	pnRecursoCierre.add(rB_MAnterior_RC);
    	
    	rB_Mes_RC = new JRadioButton("Este Mes");
    	rB_Mes_RC.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Mes_RC.isSelected()){
    				rB_Intervalo_RC.setSelected(false);
    				dC_FF_RC.setEnabled(false);
    				dC_FI_RC.setEnabled(false);
    				rB_Hoy_RC.setSelected(false);
    				rB_USemana_RC.setSelected(false);
    				rB_MAnterior_RC.setSelected(false);
    				rB_Anio_RC.setSelected(false);
    				filtrarMesRC();
    			}
    		}
    	});
    	rB_Mes_RC.setToolTipText("Piezas llegadas en el mes corriente.");
    	rB_Mes_RC.setBounds(5, 85, 150, 20);
    	pnRecursoCierre.add(rB_Mes_RC);
    	
    	rB_USemana_RC = new JRadioButton("Ultima Semana");
    	rB_USemana_RC.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_USemana_RC.isSelected()){
    				rB_Intervalo_RC.setSelected(false);
    				dC_FF_RC.setEnabled(false);
    				dC_FI_RC.setEnabled(false);
    				rB_Hoy_RC.setSelected(false);
    				rB_Mes_RC.setSelected(false);
    				rB_MAnterior_RC.setSelected(false);
    				rB_Anio_RC.setSelected(false);
    				filtrarUSemanaRC();
    			}
    		}
    	});
    	rB_USemana_RC.setToolTipText("Piezas llegadas desde el dia Lunes a Viernes de la semana anterior.");
    	rB_USemana_RC.setBounds(5, 60, 150, 20);
    	pnRecursoCierre.add(rB_USemana_RC);
    	
    	rB_Hoy_RC = new JRadioButton("Hoy");
    	rB_Hoy_RC.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rB_Hoy_RC.isSelected()){
					rB_Intervalo_RC.setSelected(false);
					dC_FF_RC.setEnabled(false);
					dC_FI_RC.setEnabled(false);
					rB_USemana_RC.setSelected(false);
					rB_Mes_RC.setSelected(false);
					rB_MAnterior_RC.setSelected(false);
					rB_Anio_RC.setSelected(false);
					filtrarHoyRC();
				}
			}
		});
    	rB_Hoy_RC.setToolTipText("Piezas llegadas el dia de hoy.");
    	rB_Hoy_RC.setBounds(5, 35, 150, 20);
    	pnRecursoCierre.add(rB_Hoy_RC);
    	
    	rB_Intervalo_RC = new JRadioButton("Intervalo de Fechas");
    	rB_Intervalo_RC.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent arg0) {
    			if(rB_Intervalo_RC.isSelected()){
    				dC_FF_RC.setEnabled(true);
    				dC_FI_RC.setEnabled(true);
    				btn_filtrar_RC.setEnabled(true);
    				rB_Hoy_RC.setSelected(false);
    				rB_USemana_RC.setSelected(false);
    				rB_Mes_RC.setSelected(false);
    				rB_MAnterior_RC.setSelected(false);
    				rB_Anio_RC.setSelected(false);
    			}else{
    				dC_FF_RC.setEnabled(false);
    				dC_FI_RC.setEnabled(false);
    				btn_filtrar_RC.setEnabled(false);
    			}
    		}
    	});
    	rB_Intervalo_RC.setToolTipText("Habilita la seleccion de piezas llegadas por medio de un intervalo de fechas");
    	rB_Intervalo_RC.setBounds(5, 10, 150, 20);
    	pnRecursoCierre.add(rB_Intervalo_RC);
    	
    	JLabel label_16 = new JLabel("Fecha Inicio");
    	label_16.setHorizontalAlignment(SwingConstants.CENTER);
    	label_16.setEnabled(false);
    	label_16.setBounds(160, 10, 120, 20);
    	pnRecursoCierre.add(label_16);
    	
    	dC_FI_RC = new JDateChooser();
    	dC_FI_RC.setEnabled(false);
    	dC_FI_RC.setBounds(290, 10, 150, 20);
    	pnRecursoCierre.add(dC_FI_RC);
    	
    	btn_clear_FIRC = new JButton("");
    	btn_clear_FIRC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FI_RC.getDate()!=null)
					dC_FI_RC.setDate(null);
			}
		});
    	btn_clear_FIRC.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
    	btn_clear_FIRC.setBounds(450, 10, 25, 20);
    	pnRecursoCierre.add(btn_clear_FIRC);
    	
    	JLabel label_17 = new JLabel("Fecha Fin");
    	label_17.setHorizontalAlignment(SwingConstants.CENTER);
    	label_17.setEnabled(false);
    	label_17.setBounds(485, 10, 120, 20);
    	pnRecursoCierre.add(label_17);
    	
    	dC_FF_RC = new JDateChooser();
    	dC_FF_RC.setEnabled(false);
    	dC_FF_RC.setBounds(615, 10, 150, 20);
    	pnRecursoCierre.add(dC_FF_RC);
    	
    	btn_clear_FFRC = new JButton("");
    	btn_clear_FFRC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dC_FF_RC.getDate()!=null)
					dC_FF_RC.setDate(null);
			}
		});
    	btn_clear_FFRC.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/clear.png")));
    	btn_clear_FFRC.setBounds(775, 10, 25, 20);
    	pnRecursoCierre.add(btn_clear_FFRC);
    	
    	btn_filtrar_RC = new JButton("Filtrar");
    	btn_filtrar_RC.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				filtrarIntervaloRC();
 			}
 		});
    	btn_filtrar_RC.setEnabled(false);
    	btn_filtrar_RC.setBounds(810, 10, 110, 20);
    	pnRecursoCierre.add(btn_filtrar_RC);
    	
    	btnExportarTabla_RC = new JButton("");
    	btnExportarTabla_RC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablaRC();
			}
		});
    	btnExportarTabla_RC.setIcon(new ImageIcon(GUIReportesControl.class.getResource("/cliente/Resources/Icons/formulario.png")));
    	btnExportarTabla_RC.setBounds(1230, 120, 32, 32);
    	pnRecursoCierre.add(btnExportarTabla_RC);
	}

	
	protected void exportarTablaRC() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_RC, "Reclamo - Cierre de Orden");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}	
	}

	protected void filtrarIntervaloRC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyRC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaRC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesRC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorRC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioRC() {
		// TODO Auto-generated method stub
		
	}

	protected void exportarTablaMO() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_MO, "Mano de Obra");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void filtrarIntervaloMO() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyMO() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaMO() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesMO() {

	}

	protected void filtrarMAnteriorMO() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioMO() {
		// TODO Auto-generated method stub
		
	}

	protected void exportarTablaPLLPD() {
		 try {
				List<JTable> t = new ArrayList<JTable>();
				List<String> n = new ArrayList<String>();
				t.add(tabla_PLLPDD);
				t.add(tabla_PLLPDL);
				n.add("Piezas LLegadas");
				n.add("Piezas Devueltas");
				ExportarExcel.exportarTablas(t, n);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
			}
		
	}

	protected void filtrarIntervaloPLLPD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyPLLPD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaPLLPD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesPLLPD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorPLLPD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioPLLPD() {
		// TODO Auto-generated method stub
		
	}

	protected void exportarTablaRT() {
		 try {
				List<JTable> t = new ArrayList<JTable>();
				List<String> n = new ArrayList<String>();
				t.add(tabla_RTR);
				t.add(tabla_RTT);
				n.add("Reclmamos");
				n.add("Turnos");
				ExportarExcel.exportarTablas(t, n);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
			}
	}

	protected void filtrarIntervaloRT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioRT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorRT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesRT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaRT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyRT() {
		// TODO Auto-generated method stub
		
	}

	protected void exportarTablaDDFRFD() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_DDFRFD, "Dias Desde Fecha Reclamo a Fecha Devolucion a Fabrica");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void filtrarIntervaloDDFRFD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioDDFRFD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorDDFRFD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesDDFRFD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaDDFRFD() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyDDFRFD() {
		// TODO Auto-generated method stub
		
	}

	protected void exportarTablaDDFRFC() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_DDFRFC, "Dias Desde Fecha Recurso a Fecha Cierre");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void filtrarIntervaloDDFRFC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioDDFRFC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorDDFRFC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesDDFRFC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaDDFRFC() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyDDFRFC() {
		// TODO Auto-generated method stub
		
	}

	protected void exportarTablaDDFCFT() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_DDFCT, "Dias Desde Fecha Cierre a Fecha Turno");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void filtrarIntervaloDDFCFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioDDFCFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorDDFCFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesDDFCFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaDDFCFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyDDFCFT() {
		// TODO Auto-generated method stub
		
	}

	protected void exportarTablaDDRFT() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_DDRFT, "Dias Desde Reclamo a Fecha Turno");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void filtrarIntervaloDDRFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioDDRFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorDDRFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesDDRFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaDDRFT() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyDDRFT() {
		// TODO Auto-generated method stub
		
	}

	protected void exportarTablaDDRF() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_DDRF, "Dias Desde Recepcion Pedido a Fabrica");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void filtaraPorIntervaloDDRF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioDDRF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorDDRF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesDDRF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaDDRF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyDDRF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarHoyDDPF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUSemanaDDPF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMAnteriorDDPF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarMesDDPF() {
		// TODO Auto-generated method stub
		
	}

	protected void filtrarUAnioDDPF() {
		// TODO Auto-generated method stub
		
	}

	protected void exportarTablaDDPF() {
		try {
			ExportarExcel.exportarUnaTabla(tabla_DDPF, "Dias Desde Pedido a Fabrica");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void filtaraPorIntervaloDDPF() {
		// TODO Auto-generated method stub
		
	}
	//Fin filtros
	
	//gettes and setters
	public MediadorReportes getMediador() {
		return mediador;
	}

	public void setMediador(MediadorReportes mediador) {
		this.mediador = mediador;
	}

	public float getSum_frecurso_fcierre() {
		return sum_frecurso_fcierre;
	}

	public void setSum_frecurso_fcierre(float sum_frecurso_fcierre) {
		this.sum_frecurso_fcierre = sum_frecurso_fcierre;
	}

	public float getN_casos() {
		return N_casos;
	}

	public void setN_casos(float n_casos) {
		N_casos = n_casos;
	}
}
