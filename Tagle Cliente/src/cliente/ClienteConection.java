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

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import common.RootAndIp;
import common.GestionarAgente.IControlAgente;
import common.GestionarBdg.IControlBdg;
import common.GestionarDevolucion_Pieza.IControlDevolucion_Pieza;
import common.GestionarEntidad.IControlEntidad;
import common.GestionarMTelefono.IControlMTelefono;
import common.GestionarMano_Obra.IControlMano_Obra;
import common.GestionarMarca.IControlMarca;
import common.GestionarModelo.IControlModelo;
import common.GestionarMuleto.IControlMuleto;
import common.GestionarNotificacion.IControlNotificacion;
import common.GestionarNotificacion_Reclamo.IControlNotificacion_Reclamo;
import common.GestionarNotificacion_Usuario.IControlNotificacion_Usuario;
import common.GestionarOrden.IControlOrden;
import common.GestionarOrden_Reclamo.IControlOrden_Reclamo;
import common.GestionarPedido.IControlPedido;
import common.GestionarPedido_Pieza.IControlPedido_Pieza;
import common.GestionarPedido_Pieza_Reclamo_Agente.IControlPedido_Pieza_Reclamo_Agente;
import common.GestionarPedido_Pieza_Reclamo_Fabrica.IControlPedido_Pieza_Reclamo_Fabrica;
import common.GestionarPieza.IControlPieza;
import common.GestionarProveedor.IControlProveedor;
import common.GestionarReclamante.IControlReclamante;
import common.GestionarReclamante_Reclamo.IControlReclamante_Reclamo;
import common.GestionarReclamo.IControlReclamo;
import common.GestionarReclamo_Agente.IControlReclamo_Agente;
import common.GestionarReclamo_Fabrica.IControlReclamo_Fabrica;
import common.GestionarRecurso.IControlRecurso;
import common.GestionarRegistrante.IControlRegistrante;
import common.GestionarUsuario.IControlUsuario;
import common.GestionarVehiculo.IControlVehiculo;

public class ClienteConection {

//	private String hostServer =RootAndIp.getIp();
	private IControlAgente controlAgente = null;
	private IControlBdg controlBdg = null;
	private IControlDevolucion_Pieza controlDevolucion_Pieza = null;
	private IControlEntidad controlEntidad = null;
	private IControlMano_Obra controlMano_Obra = null;
	private IControlMarca controlMarca = null;
	private IControlModelo controlModelo = null;
	private IControlMTelefono controlMTelefono = null;
	private IControlMuleto controlMuleto = null;
	private IControlNotificacion controlNotificacion = null;
	private IControlNotificacion_Reclamo controlNotificacion_Reclamo = null;
	private IControlNotificacion_Usuario controlNotificacion_Usuario = null;
	private IControlOrden controlOrden = null;
	private IControlOrden_Reclamo controlOrden_Reclamo = null;
	private IControlPedido controlPedido = null;
	private IControlPedido_Pieza controlPedido_Pieza = null;
	private IControlPedido_Pieza_Reclamo_Agente controlPedido_Pieza_Reclamo_Agente = null;
	private IControlPedido_Pieza_Reclamo_Fabrica controlPedido_Pieza_Reclamo_Fabrica = null;
	private IControlPieza controlPieza = null;
	private IControlProveedor controlProveedor = null;
	private IControlReclamante controlReclamante = null;
	private IControlReclamante_Reclamo controlReclamante_Reclamo = null;
	private IControlReclamo controlReclamo = null;
	private IControlReclamo_Agente controlReclamo_Agente = null;
	private IControlReclamo_Fabrica controlReclamo_Fabrica = null;
	private IControlRecurso controlRecurso = null;
	private IControlRegistrante controlRegistrante = null;
	private IControlUsuario controlUsuario = null;
	private IControlVehiculo controlVehiculo = null;	

	public ClienteConection (){
	}

	public void iniciar() throws Exception {

		common.RootAndIp.setConf("");
        String ip = RootAndIp.getIp_cliente();
        int port = RootAndIp.getPort();
		
        Registry Naming = LocateRegistry.getRegistry(ip,port);
		String nombreServer = "";
		
		nombreServer = "rmi://" + ip + "/IControlAgente";
		setControlAgente((IControlAgente)Naming.lookup(nombreServer));

		nombreServer = "rmi://" + ip + "/IControlBdg";
		setControlBdg((IControlBdg)Naming.lookup(nombreServer));

		nombreServer = "rmi://" + ip + "/IControlDevolucion_Pieza";
		setControlDevolucion_Pieza((IControlDevolucion_Pieza)Naming.lookup(nombreServer));

		nombreServer = "rmi://" + ip + "/IControlEntidad";
		setControlEntidad((IControlEntidad)Naming.lookup(nombreServer));

		nombreServer = "rmi://" + ip + "/IControlMano_Obra";
		setControlMano_Obra((IControlMano_Obra)Naming.lookup(nombreServer));

		nombreServer = "rmi://" + ip + "/IControlMarca";
		setControlMarca((IControlMarca)Naming.lookup(nombreServer));

		nombreServer = "rmi://" + ip + "/IControlModelo";
		setControlModelo((IControlModelo)Naming.lookup(nombreServer));

		nombreServer = "rmi://" + ip + "/IControlMTelefono";
		setControlMTelefono((IControlMTelefono)Naming.lookup(nombreServer));

		nombreServer = "rmi://" + ip + "/IControlMuleto";
		setControlMuleto((IControlMuleto)Naming.lookup(nombreServer));

		nombreServer = "rmi://" + ip + "/IControlNotificacion";
		setControlNotificacion((IControlNotificacion)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlNotificacion_Reclamo";
		setControlNotificacion_Reclamo((IControlNotificacion_Reclamo)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlNotificacion_Usuario";
		setControlNotificacion_Usuario((IControlNotificacion_Usuario)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlOrden";
		setControlOrden((IControlOrden)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlOrden_Reclamo";
		setControlOrden_Reclamo((IControlOrden_Reclamo)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlPedido";
		setControlPedido((IControlPedido)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlPedido_Pieza";
		setControlPedido_Pieza((IControlPedido_Pieza)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlPedido_Pieza_Reclamo_Agente";
		setControlPedido_Pieza_Reclamo_Agente((IControlPedido_Pieza_Reclamo_Agente)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlPedido_Pieza_Reclamo_Fabrica";
		setControlPedido_Pieza_Reclamo_Fabrica((IControlPedido_Pieza_Reclamo_Fabrica)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlPieza";
		setControlPieza((IControlPieza)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlProveedor";
		setControlProveedor((IControlProveedor)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlReclamante";
		setControlReclamante((IControlReclamante)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlReclamante_Reclamo";
		setControlReclamante_Reclamo((IControlReclamante_Reclamo)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlReclamo";
		setControlReclamo((IControlReclamo)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlReclamo_Agente";
		setControlReclamo_Agente((IControlReclamo_Agente)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlReclamo_Fabrica";
		setControlReclamo_Fabrica((IControlReclamo_Fabrica)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlRecurso";
		setControlRecurso((IControlRecurso)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlRegistrante";
		setControlRegistrante((IControlRegistrante)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlUsuario";
		setControlUsuario((IControlUsuario)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlVehiculo";
		setControlVehiculo((IControlVehiculo)Naming.lookup(nombreServer));
	}

	public IControlAgente getControlAgente() {
		return controlAgente;
	}

	public void setControlAgente(IControlAgente controlAgente) {
		this.controlAgente = controlAgente;
	}

	public IControlBdg getControlBdg() {
		return controlBdg;
	}

	public void setControlBdg(IControlBdg controlBdg) {
		this.controlBdg = controlBdg;
	}

	public IControlDevolucion_Pieza getControlDevolucion_Pieza() {
		return controlDevolucion_Pieza;
	}

	public void setControlDevolucion_Pieza(
			IControlDevolucion_Pieza controlDevolucion_Pieza) {
		this.controlDevolucion_Pieza = controlDevolucion_Pieza;
	}

	public IControlEntidad getControlEntidad() {
		return controlEntidad;
	}

	public void setControlEntidad(IControlEntidad controlEntidad) {
		this.controlEntidad = controlEntidad;
	}

	public IControlMano_Obra getControlMano_Obra() {
		return controlMano_Obra;
	}

	public void setControlMano_Obra(IControlMano_Obra controlMano_Obra) {
		this.controlMano_Obra = controlMano_Obra;
	}

	public IControlMarca getControlMarca() {
		return controlMarca;
	}

	public void setControlMarca(IControlMarca controlMarca) {
		this.controlMarca = controlMarca;
	}

	public IControlModelo getControlModelo() {
		return controlModelo;
	}

	public void setControlModelo(IControlModelo controlModelo) {
		this.controlModelo = controlModelo;
	}

	public IControlMTelefono getControlMTelefono() {
		return controlMTelefono;
	}

	public void setControlMTelefono(IControlMTelefono controlMTelefono) {
		this.controlMTelefono = controlMTelefono;
	}

	public IControlMuleto getControlMuleto() {
		return controlMuleto;
	}

	public void setControlMuleto(IControlMuleto controlMuleto) {
		this.controlMuleto = controlMuleto;
	}

	public IControlNotificacion getControlNotificacion() {
		return controlNotificacion;
	}

	public void setControlNotificacion(IControlNotificacion controlNotificacion) {
		this.controlNotificacion = controlNotificacion;
	}

	public IControlNotificacion_Usuario getControlNotificacion_Usuario() {
		return controlNotificacion_Usuario;
	}

	public void setControlNotificacion_Usuario(
			IControlNotificacion_Usuario controlNotificacion_Usuario) {
		this.controlNotificacion_Usuario = controlNotificacion_Usuario;
	}

	public IControlOrden getControlOrden() {
		return controlOrden;
	}

	public void setControlOrden(IControlOrden controlOrden) {
		this.controlOrden = controlOrden;
	}

	public IControlOrden_Reclamo getControlOrden_Reclamo() {
		return controlOrden_Reclamo;
	}

	public void setControlOrden_Reclamo(IControlOrden_Reclamo controlOrden_Reclamo) {
		this.controlOrden_Reclamo = controlOrden_Reclamo;
	}

	public IControlPedido getControlPedido() {
		return controlPedido;
	}

	public void setControlPedido(IControlPedido controlPedido) {
		this.controlPedido = controlPedido;
	}

	public IControlPedido_Pieza getControlPedido_Pieza() {
		return controlPedido_Pieza;
	}

	public void setControlPedido_Pieza(IControlPedido_Pieza controlPedido_Pieza) {
		this.controlPedido_Pieza = controlPedido_Pieza;
	}

	public IControlPedido_Pieza_Reclamo_Fabrica getControlPedido_Pieza_Reclamo_Fabrica() {
		return controlPedido_Pieza_Reclamo_Fabrica;
	}

	public void setControlPedido_Pieza_Reclamo_Fabrica(
			IControlPedido_Pieza_Reclamo_Fabrica controlPedido_Pieza_Reclamo_Fabrica) {
		this.controlPedido_Pieza_Reclamo_Fabrica = controlPedido_Pieza_Reclamo_Fabrica;
	}

	public IControlPieza getControlPieza() {
		return controlPieza;
	}

	public void setControlPieza(IControlPieza controlPieza) {
		this.controlPieza = controlPieza;
	}

	public IControlReclamante getControlReclamante() {
		return controlReclamante;
	}

	public void setControlReclamante(IControlReclamante controlReclamante) {
		this.controlReclamante = controlReclamante;
	}

	public IControlReclamante_Reclamo getControlReclamante_Reclamo() {
		return controlReclamante_Reclamo;
	}

	public void setControlReclamante_Reclamo(
			IControlReclamante_Reclamo controlReclamante_Reclamo) {
		this.controlReclamante_Reclamo = controlReclamante_Reclamo;
	}

	public IControlReclamo getControlReclamo() {
		return controlReclamo;
	}

	public void setControlReclamo(IControlReclamo controlReclamo) {
		this.controlReclamo = controlReclamo;
	}

	public IControlReclamo_Fabrica getControlReclamo_Fabrica() {
		return controlReclamo_Fabrica;
	}

	public void setControlReclamo_Fabrica(
			IControlReclamo_Fabrica controlReclamo_Fabrica) {
		this.controlReclamo_Fabrica = controlReclamo_Fabrica;
	}

	public IControlRecurso getControlRecurso() {
		return controlRecurso;
	}

	public void setControlRecurso(IControlRecurso controlRecurso) {
		this.controlRecurso = controlRecurso;
	}

	public IControlRegistrante getControlRegistrante() {
		return controlRegistrante;
	}

	public void setControlRegistrante(IControlRegistrante controlRegistrante) {
		this.controlRegistrante = controlRegistrante;
	}

	public IControlUsuario getControlUsuario() {
		return controlUsuario;
	}

	public void setControlUsuario(IControlUsuario controlUsuario) {
		this.controlUsuario = controlUsuario;
	}

	public IControlVehiculo getControlVehiculo() {
		return controlVehiculo;
	}

	public void setControlVehiculo(IControlVehiculo controlVehiculo) {
		this.controlVehiculo = controlVehiculo;
	}

	public IControlProveedor getControlProveedor() {
		return controlProveedor;
	}

	public void setControlProveedor(IControlProveedor controlProveedor) {
		this.controlProveedor = controlProveedor;
	}

	public IControlReclamo_Agente getControlReclamo_Agente() {
		return controlReclamo_Agente;
	}

	public void setControlReclamo_Agente(IControlReclamo_Agente controlReclamo_Agente) {
		this.controlReclamo_Agente = controlReclamo_Agente;
	}

	public IControlPedido_Pieza_Reclamo_Agente getControlPedido_Pieza_Reclamo_Agente() {
		return controlPedido_Pieza_Reclamo_Agente;
	}

	public void setControlPedido_Pieza_Reclamo_Agente(
			IControlPedido_Pieza_Reclamo_Agente controlPedido_Pieza_Reclamo_Agente) {
		this.controlPedido_Pieza_Reclamo_Agente = controlPedido_Pieza_Reclamo_Agente;
	}

	public IControlNotificacion_Reclamo getControlNotificacion_Reclamo() {
		return controlNotificacion_Reclamo;
	}

	public void setControlNotificacion_Reclamo(
			IControlNotificacion_Reclamo controlNotificacion_Reclamo) {
		this.controlNotificacion_Reclamo = controlNotificacion_Reclamo;
	}

}
