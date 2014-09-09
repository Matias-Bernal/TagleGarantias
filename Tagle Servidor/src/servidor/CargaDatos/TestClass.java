package servidor.CargaDatos;

import java.rmi.RemoteException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import common.Triple;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.ReclamoDTO;
import common.GestionarPedido_Pieza.IControlPedido_Pieza;
import servidor.SingletonConexion;
import servidor.GestionarPedido_Pieza.ControlPedido_Pieza;
import servidor.GestionarReclamo.ControlReclamo;

public class TestClass {

	public static void main(String[] args) {
		try {
			SingletonConexion control = new SingletonConexion();
			ControlPedido_Pieza controlPedidoPieza = new ControlPedido_Pieza();
			
			Calendar desdeCalendar = Calendar.getInstance();
			desdeCalendar.setFirstDayOfWeek(desdeCalendar.SUNDAY);
			desdeCalendar.add(desdeCalendar.MONTH, -2);
			desdeCalendar.set(desdeCalendar.DAY_OF_MONTH,1);
			java.sql.Date desde = new java.sql.Date(desdeCalendar.getTime().getTime());
			
			Calendar hastaCalendar = Calendar.getInstance();
			hastaCalendar.setFirstDayOfWeek(hastaCalendar.SUNDAY);
			hastaCalendar.set(hastaCalendar.DAY_OF_MONTH,hastaCalendar.getMaximum(hastaCalendar.DAY_OF_MONTH));		
			java.sql.Date hasta = new java.sql.Date(hastaCalendar.getTime().getTime());
			
//			Vector<Pedido_PiezaDTO> pp = controlPedidoPieza.obtener_piezas_con_turno(null, null);
//			Triple<Double, Double, Double> anticuacion = controlPedidoPieza.anticuacion_recursadas(desde, hasta);
//			Triple<Double, Double, Double> monto = controlPedidoPieza.monto_recursadas(desde, hasta);
//			System.out.println("cantidad de pedidos: ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
