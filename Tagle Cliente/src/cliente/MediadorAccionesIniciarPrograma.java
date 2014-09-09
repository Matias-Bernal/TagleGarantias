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


public final class MediadorAccionesIniciarPrograma{

	public static IControlAgente getControlAgente() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error en getControlAgente. Constructor");
		}
		return clienteConexion.getControlAgente();
	}
	
	public static IControlBdg getControlBdg() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlBdg. Constructor");
		}
		return clienteConexion.getControlBdg();
	}

	public static IControlDevolucion_Pieza getControlDevolucion_Pieza() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlDevolucion_Pieza. Constructor");
		}
		return clienteConexion.getControlDevolucion_Pieza();
	}

	public static IControlEntidad getControlEntidad() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlEntidad. Constructor");
		}
		return clienteConexion.getControlEntidad();
	}

	public static IControlMano_Obra getControlMano_Obra() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlMano_Obra. Constructor");
		}
		return clienteConexion.getControlMano_Obra();
	}

	public static IControlMarca getControlMarca() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlMarca. Constructor");
		}
		return clienteConexion.getControlMarca();
	}
	
	public static IControlModelo getControlModelo() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlModelo. Constructor");
		}
		return clienteConexion.getControlModelo();
	}
	
	public static IControlMTelefono getControlMTelefono() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlMTelefono. Constructor");
		}
		return clienteConexion.getControlMTelefono();
	}
	
	public static IControlMuleto getControlMuleto() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlMuleto. Constructor");
		}
		return clienteConexion.getControlMuleto();
	}
	
	public static IControlNotificacion getControlNotificacion() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlNotificacion. Constructor");
		}
		return clienteConexion.getControlNotificacion();
	}
	
	public static IControlNotificacion_Reclamo getControlNotificacion_Reclamo() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlNotificacion_Reclamo. Constructor");
		}
		return clienteConexion.getControlNotificacion_Reclamo();
	}
	
	public static IControlNotificacion_Usuario getControlNotificacion_Usuario() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlNotificacion_Usuario. Constructor");
		}
		return clienteConexion.getControlNotificacion_Usuario();
	}
	
	public static IControlOrden getControlOrden() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlOrden. Constructor");
		}
		return clienteConexion.getControlOrden();
	}
	
	public static IControlOrden_Reclamo getControlOrden_Reclamo() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlOrden_Reclamo. Constructor");
		}
		return clienteConexion.getControlOrden_Reclamo();
	}
	
	public static IControlPedido getControlPedido() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlMTelefono. Constructor");
		}
		return clienteConexion.getControlPedido();
	}
	
	public static IControlPedido_Pieza getControlPedido_Pieza() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlPedido_Pieza. Constructor");
		}
		return clienteConexion.getControlPedido_Pieza();
	}

	public static IControlPedido_Pieza_Reclamo_Agente getControlPedido_Pieza_Reclamo_Agente() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlPedido_Pieza_Reclamo_Agente. Constructor");
		}
		return clienteConexion.getControlPedido_Pieza_Reclamo_Agente();
	}
	
	public static IControlPedido_Pieza_Reclamo_Fabrica getControlPedido_Pieza_Reclamo_Fabrica() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlPedido_Pieza_Reclamo_Fabrica. Constructor");
		}
		return clienteConexion.getControlPedido_Pieza_Reclamo_Fabrica();
	}
	
	public static IControlPieza getControlPieza() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlPieza. Constructor");
		}
		return clienteConexion.getControlPieza();
	}
	
	public static IControlProveedor getControlProveedor(){
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlProveedor. Constructor");
		}
		return clienteConexion.getControlProveedor();
	}
 	public static IControlReclamante getControlReclamante() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlReclamante. Constructor");
		}
		return clienteConexion.getControlReclamante();
	}

	public static IControlReclamante_Reclamo getControlReclamante_Reclamo() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlReclamante_Reclamo. Constructor");
		}
		return clienteConexion.getControlReclamante_Reclamo();
	}
	
	public static IControlReclamo getControlReclamo() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlReclamo. Constructor");
		}
		return clienteConexion.getControlReclamo();
	}
	
	public static IControlReclamo_Agente getControlReclamo_Agente() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlReclamo_Agente. Constructor");
		}
		return clienteConexion.getControlReclamo_Agente();
	}
	
	public static IControlReclamo_Fabrica getControlReclamo_Fabrica() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlReclamo_Fabrica. Constructor");
		}
		return clienteConexion.getControlReclamo_Fabrica();
	}
	
	public static IControlRecurso getControlRecurso() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlRecurso. Constructor");
		}
		return clienteConexion.getControlRecurso();
	}
	
	public static IControlRegistrante getControlRegistrante() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlRegistrante. Constructor");
		}
		return clienteConexion.getControlRegistrante();
	}
	
	public static IControlUsuario getControlUsuarios() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error en getControlUsuarios. Constructor");
		}
		return clienteConexion.getControlUsuario();
	}

	public static IControlVehiculo getControlVehiculo() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlVehiculo. Constructor");
		}
		return clienteConexion.getControlVehiculo();
	}
	
}