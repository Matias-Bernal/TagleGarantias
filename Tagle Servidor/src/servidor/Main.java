package servidor;

import common.DTOs.UsuarioDTO;
import servidor.GestionarAgente.ControlAgente;
import servidor.GestionarBdg.ControlBdg;
import servidor.GestionarDevolucion_Pieza.ControlDevolucion_Pieza;
import servidor.GestionarEntidad.ControlEntidad;
import servidor.GestionarMTelefono.ControlMTelefono;
import servidor.GestionarMano_Obra.ControlMano_Obra;
import servidor.GestionarMarca.ControlMarca;
import servidor.GestionarModelo.ControlModelo;
import servidor.GestionarMuleto.ControlMuleto;
import servidor.GestionarNotificacion.ControlNotificacion;
import servidor.GestionarNotificacion_Reclamo.ControlNotificacion_Reclamo;
import servidor.GestionarNotificacion_Usuario.ControlNotificacion_Usuario;
import servidor.GestionarOrden.ControlOrden;
import servidor.GestionarOrden_Reclamo.ControlOrden_Reclamo;
import servidor.GestionarPedido.ControlPedido;
import servidor.GestionarPedido_Pieza.ControlPedido_Pieza;
import servidor.GestionarPedido_Pieza_Reclamo_Agente.ControlPedido_Pieza_Reclamo_Agente;
import servidor.GestionarPedido_Pieza_Reclamo_Fabrica.ControlPedido_Pieza_Reclamo_Fabrica;
import servidor.GestionarPieza.ControlPieza;
import servidor.GestionarProveedor.ControlProveedor;
import servidor.GestionarReclamante.ControlReclamante;
import servidor.GestionarReclamante_Reclamo.ControlReclamante_Reclamo;
import servidor.GestionarReclamo.ControlReclamo;
import servidor.GestionarReclamo_Agente.ControlReclamo_Agente;
import servidor.GestionarReclamo_Fabrica.ControlReclamo_Fabrica;
import servidor.GestionarRecurso.ControlRecurso;
import servidor.GestionarRegistrante.ControlRegistrante;
import servidor.GestionarUsuario.ControlUsuario;
import servidor.GestionarVehiculo.ControlVehiculo;


public class Main {  
  
	public static void main(String[] args) {
		try {
			ControlAgente controlAgente = new ControlAgente();
			ControlBdg controlBdg = new ControlBdg();
			ControlDevolucion_Pieza controlDevolucion_Pieza = new ControlDevolucion_Pieza();
			ControlEntidad controlEntidad = new ControlEntidad();
			ControlMano_Obra controlMano_Obra = new ControlMano_Obra();
			ControlMarca controlMarca = new ControlMarca();
			ControlModelo controlModelo = new ControlModelo();
			ControlMTelefono controlMTelefono = new ControlMTelefono();
			ControlMuleto controlMuleto = new ControlMuleto();
			ControlNotificacion controlNotificacion = new ControlNotificacion();
			ControlNotificacion_Reclamo controlNotificacion_Reclamo = new ControlNotificacion_Reclamo();
			ControlNotificacion_Usuario controlNotificacion_Usuario = new ControlNotificacion_Usuario();
			ControlOrden controlOrden = new ControlOrden();
			ControlOrden_Reclamo controlOrden_Reclamo = new ControlOrden_Reclamo();
			ControlPedido controlPedido = new ControlPedido();
			ControlPedido_Pieza controlPedido_Pieza = new ControlPedido_Pieza();
			ControlPedido_Pieza_Reclamo_Agente controlPedido_Pieza_Reclamo_Agente = new ControlPedido_Pieza_Reclamo_Agente();
			ControlPedido_Pieza_Reclamo_Fabrica controlPedido_Pieza_Reclamo_Fabrica = new ControlPedido_Pieza_Reclamo_Fabrica();
			ControlPieza controlPieza = new ControlPieza();
			ControlProveedor controlProveedor = new ControlProveedor();
			ControlReclamante controlReclamante = new ControlReclamante();
			ControlReclamante_Reclamo controlReclamante_Reclamo = new ControlReclamante_Reclamo();
			ControlReclamo controlReclamo = new ControlReclamo();
			ControlReclamo_Agente controlReclamo_Agente = new ControlReclamo_Agente();
			ControlReclamo_Fabrica controlReclamo_Fabrica = new ControlReclamo_Fabrica();
			ControlRecurso controlRecurso = new ControlRecurso();
			ControlRegistrante controlRegistrante = new ControlRegistrante();
			ControlUsuario controlUsuario = new ControlUsuario();
			ControlVehiculo controlVehiculo = new ControlVehiculo();

			Servidor servidor = new Servidor();
			SingletonConexion c = new SingletonConexion();
			
			if (!controlUsuario.existeUsuario("Admin")){
				UsuarioDTO admin = new UsuarioDTO("Admin","it10" , "", "Administrativo");
				controlUsuario.agregarUsuario(admin);
				System.out.println("Agregado el usuario Admin");
			}
			
			servidor.setControlAgente(controlAgente);
			servidor.setControlBdg(controlBdg);
			servidor.setControlDevolucion_Pieza(controlDevolucion_Pieza);
			servidor.setContolEntidad(controlEntidad);
			servidor.setControlMano_Obra(controlMano_Obra);
			servidor.setControlMarca(controlMarca);
			servidor.setControlModelo(controlModelo);
			servidor.setControlMTelefono(controlMTelefono);
			servidor.setControlMuleto(controlMuleto);
			servidor.setControlNotificacion(controlNotificacion);
			servidor.setControlNotificacion_Reclamo(controlNotificacion_Reclamo);
			servidor.setControlNotificacion_Usuario(controlNotificacion_Usuario);
			servidor.setControlOrden(controlOrden);
			servidor.setControlOrden_Reclamo(controlOrden_Reclamo);
			servidor.setControlPedido(controlPedido);
			servidor.setControlPedido_Pieza(controlPedido_Pieza);
			servidor.setControlPedido_Pieza_Reclamo_Agente(controlPedido_Pieza_Reclamo_Agente);
			servidor.setControlPedido_Pieza_Reclamo_Fabrica(controlPedido_Pieza_Reclamo_Fabrica);
			servidor.setControlPieza(controlPieza);
			servidor.setControlProveedor(controlProveedor);
			servidor.setControlReclamante(controlReclamante);
			servidor.setControlReclamante_Reclamo(controlReclamante_Reclamo);
			servidor.setControlReclamo(controlReclamo);
			servidor.setControlReclamo_Agente(controlReclamo_Agente);
			servidor.setControlReclamo_Fabrica(controlReclamo_Fabrica);
			servidor.setControlRecurso(controlRecurso);
			servidor.setControlRegistrante(controlRegistrante);
			servidor.setControlUsuario(controlUsuario);
			servidor.setControlVehiculo(controlVehiculo);
			
			servidor.iniciar();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
  
}
