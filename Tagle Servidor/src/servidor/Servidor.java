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
package servidor;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;

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
import common.RootAndIp;
import common.Utils;

public class Servidor {

	private ControlAgente controlAgente = null;
	private ControlBdg controlBdg = null;
	private ControlDevolucion_Pieza controlDevolucion_Pieza = null;
	private ControlEntidad controlEntidad = null;
	private ControlMano_Obra controlMano_Obra = null;
	private ControlMarca controlMarca = null;
	private ControlModelo controlModelo = null;
	private ControlMTelefono controlMTelefono = null;
	private ControlMuleto controlMuleto = null;
	private ControlNotificacion controlNotificacion = null;
	private ControlNotificacion_Reclamo controlNotificacion_Reclamo = null;
	private ControlNotificacion_Usuario controlNotificacion_Usuario = null;
	private ControlOrden controlOrden = null;
	private ControlOrden_Reclamo controlOrden_Reclamo = null;
	private ControlPedido controlPedido = null;
	private ControlPedido_Pieza controlPedido_Pieza = null;
	private ControlPedido_Pieza_Reclamo_Agente controlPedido_Pieza_Reclamo_Agente = null;
	private ControlPedido_Pieza_Reclamo_Fabrica controlPedido_Pieza_Reclamo_Fabrica = null;
	private ControlPieza controlPieza = null;
	private ControlProveedor controlProveedor = null;
	private ControlReclamante controlReclamante = null;
	private ControlReclamante_Reclamo controlReclamante_Reclamo = null;
	private ControlReclamo controlReclamo = null;
	private ControlReclamo_Agente controlReclamo_Agente = null;
	private ControlReclamo_Fabrica controlReclamo_Fabrica = null;
	private ControlRecurso controlRecurso = null;
	private ControlRegistrante controlRegistrante = null;
	private ControlUsuario controlUsuario = null;
	private ControlVehiculo controlVehiculo = null;	

	private String name = "";
	private String ip = "";

	public Servidor() {
	}
	
	public void iniciar() throws Exception {
		common.RootAndIp.setConf("");
		int port = RootAndIp.getPort();
		
		Registry Naming = LocateRegistry.createRegistry(port);
		Utils.setCodeBase(Servidor.class);
		
		System.out.println("Iniciando servidor !!!");

		common.RootAndIp.setConf("");
        ip = RootAndIp.getIp_servidor();
        System.out.println("ip localhost"+InetAddress.getLocalHost().toString());
		System.out.println("Ip: " + this.ip);

		this.name = "rmi://" + this.ip + "/IControlAgente";
		Naming.rebind(this.name, this.controlAgente);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlBdg";
		Naming.rebind(this.name, this.controlBdg);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlDevolucion_Pieza";
		Naming.rebind(this.name, this.controlDevolucion_Pieza);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlEntidad";
		Naming.rebind(this.name, this.controlEntidad);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlMano_Obra";
		Naming.rebind(this.name, this.controlMano_Obra);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlMarca";
		Naming.rebind(this.name, this.controlMarca);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlModelo";
		Naming.rebind(this.name, this.controlModelo);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlMTelefono";
		Naming.rebind(this.name, this.controlMTelefono);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlMuleto";
		Naming.rebind(this.name, this.controlMuleto);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlNotificacion";
		Naming.rebind(this.name, this.controlNotificacion);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlNotificacion_Reclamo";
		Naming.rebind(this.name, this.controlNotificacion_Reclamo);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlNotificacion_Usuario";
		Naming.rebind(this.name, this.controlNotificacion_Usuario);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlOrden";
		Naming.rebind(this.name, this.controlOrden);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlOrden_Reclamo";
		Naming.rebind(this.name, this.controlOrden_Reclamo);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlPedido";
		Naming.rebind(this.name, this.controlPedido);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlPedido_Pieza";
		Naming.rebind(this.name, this.controlPedido_Pieza);
		System.out.println("Nombre: " + this.name);	
	
		this.name = "rmi://" + this.ip + "/IControlPedido_Pieza_Reclamo_Agente";
		Naming.rebind(this.name, this.controlPedido_Pieza_Reclamo_Agente);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlPedido_Pieza_Reclamo_Fabrica";
		Naming.rebind(this.name, this.controlPedido_Pieza_Reclamo_Fabrica);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlPieza";
		Naming.rebind(this.name, this.controlPieza);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlProveedor";
		Naming.rebind(this.name, this.controlProveedor);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlReclamante";
		Naming.rebind(this.name, this.controlReclamante);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlReclamante_Reclamo";
		Naming.rebind(this.name, this.controlReclamante_Reclamo);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlReclamo";
		Naming.rebind(this.name, this.controlReclamo);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlReclamo_Agente";
		Naming.rebind(this.name, this.controlReclamo_Agente);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlReclamo_Fabrica";
		Naming.rebind(this.name, this.controlReclamo_Fabrica);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlRecurso";
		Naming.rebind(this.name, this.controlRecurso);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlRegistrante";
		Naming.rebind(this.name, this.controlRegistrante);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlUsuario";
		Naming.rebind(this.name, this.controlUsuario);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlVehiculo";
		Naming.rebind(this.name, this.controlVehiculo);
		System.out.println("Nombre: " + this.name);
		System.out.println("Listo para conexiones");
		int exit = 0;
		synchronized (this) {
			if(exit!=1)
				this.wait();
		}
		//GUIServidor serv = new GUIServidor();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public ControlUsuario getControlUsuario() {
		return controlUsuario;
	}

	public void setControlUsuario(ControlUsuario controlUsuario) {
		this.controlUsuario = controlUsuario;
	}

	public ControlRegistrante getControlRegistrante() {
		return controlRegistrante;
	}

	public void setControlRegistrante(ControlRegistrante controlRegistrante) {
		this.controlRegistrante = controlRegistrante;
	}

	public ControlAgente getControlAgente() {
		return controlAgente;
	}

	public void setControlAgente(ControlAgente controlAgente) {
		this.controlAgente = controlAgente;
	}

	public ControlEntidad getContolEntidad() {
		return controlEntidad;
	}

	public void setContolEntidad(ControlEntidad controlEntidad) {
		this.controlEntidad = controlEntidad;
	}

	public ControlBdg getControlBdg() {
		return controlBdg;
	}

	public void setControlBdg(ControlBdg controlBdg) {
		this.controlBdg = controlBdg;
	}

	public ControlDevolucion_Pieza getControlDevolucion_Pieza() {
		return controlDevolucion_Pieza;
	}

	public void setControlDevolucion_Pieza(
			ControlDevolucion_Pieza controlDevolucion_Pieza) {
		this.controlDevolucion_Pieza = controlDevolucion_Pieza;
	}

	public ControlMano_Obra getControlMano_Obra() {
		return controlMano_Obra;
	}

	public void setControlMano_Obra(ControlMano_Obra controlMano_Obra) {
		this.controlMano_Obra = controlMano_Obra;
	}

	public ControlMarca getControlMarca() {
		return controlMarca;
	}

	public void setControlMarca(ControlMarca controlMarca) {
		this.controlMarca = controlMarca;
	}

	public ControlModelo getControlModelo() {
		return controlModelo;
	}

	public void setControlModelo(ControlModelo controlModelo) {
		this.controlModelo = controlModelo;
	}

	public ControlMTelefono getControlMTelefono() {
		return controlMTelefono;
	}

	public void setControlMTelefono(ControlMTelefono controlMTelefono) {
		this.controlMTelefono = controlMTelefono;
	}

	public ControlMuleto getControlMuleto() {
		return controlMuleto;
	}

	public void setControlMuleto(ControlMuleto controlMuleto) {
		this.controlMuleto = controlMuleto;
	}

	public ControlNotificacion getControlNotificacion() {
		return controlNotificacion;
	}

	public void setControlNotificacion(ControlNotificacion controlNotificacion) {
		this.controlNotificacion = controlNotificacion;
	}

	public ControlNotificacion_Usuario getControlNotificacion_Usuario() {
		return controlNotificacion_Usuario;
	}

	public void setControlNotificacion_Usuario(
			ControlNotificacion_Usuario controlNotificacion_Usuario) {
		this.controlNotificacion_Usuario = controlNotificacion_Usuario;
	}

	public ControlOrden getControlOrden() {
		return controlOrden;
	}

	public void setControlOrden(ControlOrden controlOrden) {
		this.controlOrden = controlOrden;
	}

	public ControlPedido getControlPedido() {
		return controlPedido;
	}

	public void setControlPedido(ControlPedido controlPedido) {
		this.controlPedido = controlPedido;
	}

	public ControlPedido_Pieza getControlPedido_Pieza() {
		return controlPedido_Pieza;
	}

	public void setControlPedido_Pieza(ControlPedido_Pieza controlPedido_Pieza) {
		this.controlPedido_Pieza = controlPedido_Pieza;
	}

	public ControlPedido_Pieza_Reclamo_Fabrica getControlPedido_Pieza_Reclamo_Fabrica() {
		return controlPedido_Pieza_Reclamo_Fabrica;
	}

	public void setControlPedido_Pieza_Reclamo_Fabrica(
			ControlPedido_Pieza_Reclamo_Fabrica controlPedido_Pieza_Reclamo_Fabrica) {
		this.controlPedido_Pieza_Reclamo_Fabrica = controlPedido_Pieza_Reclamo_Fabrica;
	}

	public ControlPieza getControlPieza() {
		return controlPieza;
	}

	public void setControlPieza(ControlPieza controlPieza) {
		this.controlPieza = controlPieza;
	}

	public ControlReclamante getControlReclamante() {
		return controlReclamante;
	}

	public void setControlReclamante(ControlReclamante controlReclamante) {
		this.controlReclamante = controlReclamante;
	}

	public ControlReclamante_Reclamo getControlReclamante_Reclamo() {
		return controlReclamante_Reclamo;
	}

	public void setControlReclamante_Reclamo(
			ControlReclamante_Reclamo controlReclamante_Reclamo) {
		this.controlReclamante_Reclamo = controlReclamante_Reclamo;
	}

	public ControlReclamo getControlReclamo() {
		return controlReclamo;
	}

	public void setControlReclamo(ControlReclamo controlReclamo) {
		this.controlReclamo = controlReclamo;
	}

	public ControlReclamo_Fabrica getControlReclamo_Fabrica() {
		return controlReclamo_Fabrica;
	}

	public void setControlReclamo_Fabrica(
			ControlReclamo_Fabrica controlReclamo_Fabrica) {
		this.controlReclamo_Fabrica = controlReclamo_Fabrica;
	}

	public ControlRecurso getControlRecurso() {
		return controlRecurso;
	}

	public void setControlRecurso(ControlRecurso controlRecurso) {
		this.controlRecurso = controlRecurso;
	}

	public ControlVehiculo getControlVehiculo() {
		return controlVehiculo;
	}

	public void setControlVehiculo(ControlVehiculo controlVehiculo) {
		this.controlVehiculo = controlVehiculo;
	}

	public ControlProveedor getControlProveedor() {
		return controlProveedor;
	}

	public void setControlProveedor(ControlProveedor controlProveedor) {
		this.controlProveedor = controlProveedor;
	}

	public ControlEntidad getControlEntidad() {
		return controlEntidad;
	}

	public void setControlEntidad(ControlEntidad controlEntidad) {
		this.controlEntidad = controlEntidad;
	}

	public ControlOrden_Reclamo getControlOrden_Reclamo() {
		return controlOrden_Reclamo;
	}

	public void setControlOrden_Reclamo(ControlOrden_Reclamo controlOrden_Reclamo) {
		this.controlOrden_Reclamo = controlOrden_Reclamo;
	}

	public ControlReclamo_Agente getControlReclamo_Agente() {
		return controlReclamo_Agente;
	}

	public void setControlReclamo_Agente(ControlReclamo_Agente controlReclamo_Agente) {
		this.controlReclamo_Agente = controlReclamo_Agente;
	}

	public ControlPedido_Pieza_Reclamo_Agente getControlPedido_Pieza_Reclamo_Agente() {
		return controlPedido_Pieza_Reclamo_Agente;
	}

	public void setControlPedido_Pieza_Reclamo_Agente(ControlPedido_Pieza_Reclamo_Agente controlPedido_Pieza_Reclamo_Agente) {
		this.controlPedido_Pieza_Reclamo_Agente = controlPedido_Pieza_Reclamo_Agente;
	}

	public ControlNotificacion_Reclamo getControlNotificacion_Reclamo() {
		return controlNotificacion_Reclamo;
	}

	public void setControlNotificacion_Reclamo(
			ControlNotificacion_Reclamo controlNotificacion_Reclamo) {
		this.controlNotificacion_Reclamo = controlNotificacion_Reclamo;
	}

}
