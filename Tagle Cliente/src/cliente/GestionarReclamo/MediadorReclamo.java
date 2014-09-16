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
package cliente.GestionarReclamo;

import java.util.Vector;

import cliente.MediadorAccionesIniciarPrograma;
import cliente.MediadorPrincipal;
import common.DTOs.AgenteDTO;
import common.DTOs.EntidadDTO;
import common.DTOs.MTelefonoDTO;
import common.DTOs.OrdenDTO;
import common.DTOs.Orden_ReclamoDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.ReclamanteDTO;
import common.DTOs.ReclamoDTO;
import common.DTOs.RegistranteDTO;
import common.DTOs.VehiculoDTO;
import common.GestionarAgente.IControlAgente;
import common.GestionarEntidad.IControlEntidad;
import common.GestionarMTelefono.IControlMTelefono;
import common.GestionarOrden.IControlOrden;
import common.GestionarOrden_Reclamo.IControlOrden_Reclamo;
import common.GestionarPedido.IControlPedido;
import common.GestionarPedido_Pieza.IControlPedido_Pieza;
import common.GestionarReclamante.IControlReclamante;
import common.GestionarReclamo.IControlReclamo;
import common.GestionarRecurso.IControlRecurso;
import common.GestionarRegistrante.IControlRegistrante;
import common.GestionarVehiculo.IControlVehiculo;

public class MediadorReclamo {
	
	private GUIAltaReclamoEntidad guialtaReclamoEntidad;
	private GUIAltaReclamoAgente guialtaReclamoAgente;
	
	private GUIBuscarVehiculo guiBuscarVehiculo;
	private GUIBuscarReclamante guiBuscarReclamante;
	private GUIBuscarAgente guiBuscarAgente;
	private GUIBuscarEntidad guiBuscarEntidad;
	private GUIBuscarOrden guiBuscarOrden;
	
	private GUIGestionReclamoAgente guiGestionReclamoAgente;
	private GUIGestionReclamoEntidad guiGestionReclamoEntidad;
	
	private MediadorPrincipal mediadorPrincipal;
	private GUIVerReclamante verReclamante;
	private GUIModificarReclamoEntidad guiModificarReclamoEntidad;
	private GUIModificarReclamoAgente guiModificarReclamoAgente;

	public MediadorReclamo(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}

	public void altaReclamoAgente() {
		guialtaReclamoAgente = new GUIAltaReclamoAgente(this);
		guialtaReclamoAgente.setVisible(true);
	}
	
	public void altaReclamoEntidad() {
		guialtaReclamoEntidad = new GUIAltaReclamoEntidad(this);
		guialtaReclamoEntidad.setVisible(true);
	}

	public void gestionReclamoAgente() {
		guiGestionReclamoAgente = new GUIGestionReclamoAgente(this);
		guiGestionReclamoAgente.setVisible(true);
	}
	
	public void gestionReclamoEntidad() {
		guiGestionReclamoEntidad = new GUIGestionReclamoEntidad(this);
		guiGestionReclamoEntidad.setVisible(true);
	}
	
	public void setVehiculo(String id_vehiculo){
		if (guialtaReclamoAgente!=null){
			try {
				IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
				guialtaReclamoAgente.setVehiculo(iControlVehiculo.buscarVehiculo(new Long(id_vehiculo)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (guialtaReclamoEntidad!=null){
			try {
				IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
				guialtaReclamoEntidad.setVehiculo(iControlVehiculo.buscarVehiculo(new Long(id_vehiculo)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (guiModificarReclamoEntidad!=null){
			try {
				IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
				guiModificarReclamoEntidad.setVehiculo(iControlVehiculo.buscarVehiculo(new Long(id_vehiculo)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (guiModificarReclamoAgente!=null){
			try {
				IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
				guiModificarReclamoAgente.setVehiculo(iControlVehiculo.buscarVehiculo(new Long(id_vehiculo)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void setReclamante(String id_reclamante) {
		if (guialtaReclamoAgente!=null){
			try {
				IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
				guialtaReclamoAgente.setReclamante(iControlReclamante.buscarReclamante(new Long(id_reclamante)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (guialtaReclamoEntidad!=null){
			try {
				IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
				guialtaReclamoEntidad.setReclamante(iControlReclamante.buscarReclamante(new Long(id_reclamante)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (guiModificarReclamoEntidad!=null){
			try {
				IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
				guiModificarReclamoEntidad.setReclamante(iControlReclamante.buscarReclamante(new Long(id_reclamante)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (guiModificarReclamoAgente!=null){
			try {
				IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
				guiModificarReclamoAgente.setReclamante(iControlReclamante.buscarReclamante(new Long(id_reclamante)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void setAgente(String id_agente) {
		if (guialtaReclamoAgente!=null){
			try {
				IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
				guialtaReclamoAgente.setAgente(iControlAgente.buscarAgente(new Long(id_agente)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (guiModificarReclamoAgente!=null){
			try {
				IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
				guiModificarReclamoAgente.setAgente(iControlAgente.buscarAgente(new Long(id_agente)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void setEntidad(String id_entidad) {
		if (guialtaReclamoEntidad!=null){
			try {
				IControlEntidad iControlEntidad = MediadorAccionesIniciarPrograma.getControlEntidad();
				guialtaReclamoEntidad.setEntidad(iControlEntidad.buscarEntidad(new Long(id_entidad)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (guiModificarReclamoEntidad!=null){
			try {
				IControlEntidad iControlEntidad = MediadorAccionesIniciarPrograma.getControlEntidad();
				guiModificarReclamoEntidad.setEntidad(iControlEntidad.buscarEntidad(new Long(id_entidad)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}	
	public void setOrden(String id_orden){
		if (guialtaReclamoAgente!=null){
			try {
				IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
				guialtaReclamoAgente.setOrden(iControlOrden.buscarOrden(new Long(id_orden)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (guialtaReclamoEntidad!=null){
			try {
				IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
				guialtaReclamoEntidad.setOrden(iControlOrden.buscarOrden(new Long(id_orden)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (guiModificarReclamoEntidad!=null){
			try {
				IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
				guiModificarReclamoEntidad.setOrden(iControlOrden.buscarOrden(new Long(id_orden)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (guiModificarReclamoAgente!=null){
			try {
				IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
				guiModificarReclamoAgente.setOrden(iControlOrden.buscarOrden(new Long(id_orden)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void buscarVehiculo() {
		guiBuscarVehiculo = new GUIBuscarVehiculo(this);
		guiBuscarVehiculo.setVisible(true);
	}
	public void buscarReclamante() {
		guiBuscarReclamante = new GUIBuscarReclamante(this);
		guiBuscarReclamante.setVisible(true);
	}
	public void buscarAgente() {
		guiBuscarAgente = new GUIBuscarAgente(this);
		guiBuscarAgente.setVisible(true);
	}
	public void buscarEntidad() {
		guiBuscarEntidad = new GUIBuscarEntidad(this);
		guiBuscarEntidad.setVisible(true);
		
	}
	public void buscarOrden() {
		guiBuscarOrden = new GUIBuscarOrden(this);
		guiBuscarOrden.setVisible(true);	
	}
	
	public Vector<VehiculoDTO> obtenerVehiculos() {
		IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
		Vector<VehiculoDTO> vehiculos = new Vector<VehiculoDTO> ();
		try {
			vehiculos = iControlVehiculo.obtenerVehiculos();
		} catch (Exception e) {
			System.out.println("Error al cargar los registrantess en la clase MediadorRegistrantes");
			e.printStackTrace();
		}
		return vehiculos;
	}
	public Vector<ReclamanteDTO> obtenerReclamantes() {
		IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
		Vector<ReclamanteDTO> reclamantes = new Vector<ReclamanteDTO> ();
		try {
			reclamantes = iControlReclamante.obtenerReclamantes();
		} catch (Exception e) {
			System.out.println("Error al cargar los reclamantes en la clase MediadorReclamantes");
			e.printStackTrace();
		}
		return reclamantes;
	}
	public Vector<AgenteDTO> obtenerAgentes() {
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
		Vector<AgenteDTO> agentes = new Vector<AgenteDTO> ();
		try {
			agentes = iControlAgente.obtenerAgentes();
		} catch (Exception e) {
			System.out.println("Error al cargar los registrantess en la clase MediadorRegistrantes");
			e.printStackTrace();
		}
		return agentes;
	}
	public Vector<EntidadDTO> obtenerEntidades() {
		IControlEntidad iControlEntidad = MediadorAccionesIniciarPrograma.getControlEntidad();
		Vector<EntidadDTO> entidades = new Vector<EntidadDTO> ();
		try {
			entidades = iControlEntidad.obtenerEntidad();
		} catch (Exception e) {
			System.out.println("Error al cargar los registrantess en la clase MediadorRegistrantes");
			e.printStackTrace();
		}
		return entidades;
	}
	public Vector<OrdenDTO> obtenerOrdenes(){
		IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
		Vector<OrdenDTO> ordenes = new Vector<OrdenDTO> ();
		try {
			ordenes = iControlOrden.obtenerOrdenes();
		} catch (Exception e) {
			System.out.println("Error al cargar los registrantess en la clase MediadorRegistrantes");
			e.printStackTrace();
		}
		return ordenes;
	}
	
	public boolean nuevoReclamoAgente(java.sql.Date fecha_reclamo, Long id_agente, Long id_reclamante,	Long id_vehiculo, Long id_orden, String descripcion) {
		boolean res = false;
		try{
			IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
			IControlRegistrante iControlRegistrante = MediadorAccionesIniciarPrograma.getControlRegistrante();
			IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
			IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
			IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();

			ReclamoDTO reclamo = new ReclamoDTO();
			reclamo.setFecha_reclamo(fecha_reclamo);
			reclamo.setEstado_reclamo("ABIERTO/SIN PEDIDO/SIN TURNO");
			
			RegistranteDTO registrante = iControlRegistrante.buscarRegistranteDTO(id_agente);
			reclamo.setRegistrante(registrante);
			
			ReclamanteDTO reclamante = iControlReclamante.buscarReclamante(id_reclamante);
			reclamo.setReclamante(reclamante);
			
			VehiculoDTO vehiculo = iControlVehiculo.buscarVehiculo(id_vehiculo);
			reclamo.setVehiculo(vehiculo);
			
			OrdenDTO orden = iControlOrden.buscarOrden(id_orden);
			orden.setEstado("ABIERTA/SIN RECURSO");
			reclamo.setOrden(orden);
			iControlOrden.modificarOrden(orden.getId(), orden);
			
			reclamo.setDescripcion(descripcion);
			
			reclamo.setUsuario(mediadorPrincipal.getUsuario());
			
			reclamo.setId(iControlReclamo.agregarReclamo(reclamo));
			
			IControlOrden_Reclamo iControlOrden_Reclamo = MediadorAccionesIniciarPrograma.getControlOrden_Reclamo();
			Orden_ReclamoDTO orden_reclamo = new Orden_ReclamoDTO();
			
			orden_reclamo.setOrden(orden);
			orden_reclamo.setReclamo(reclamo);
			orden_reclamo.setId(iControlOrden_Reclamo.agregarOrden_Reclamo(orden_reclamo));
			res = true;

		} catch (Exception e) {
			System.out.println("Error al cargar los registrantess en la clase MediadorRegistrantes");
			e.printStackTrace();
		}
		return res;
	}
	public boolean nuevoReclamoEntidad(java.sql.Date fecha_reclamo, Long id_entidad, Long id_reclamante, Long id_vehiculo, Long id_orden, boolean peligroso, boolean inmovilizado, String descripcion) {
		boolean res = false;
			try{
				IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
				IControlRegistrante iControlRegistrante = MediadorAccionesIniciarPrograma.getControlRegistrante();
				IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
				IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
				IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();

				ReclamoDTO reclamo = new ReclamoDTO();
				reclamo.setFecha_reclamo(fecha_reclamo);
				reclamo.setEstado_reclamo("ABIERTO/SIN PEDIDO/SIN TURNO");
				
				RegistranteDTO registrante = iControlRegistrante.buscarRegistranteDTO(id_entidad);
				reclamo.setRegistrante(registrante);
				
				ReclamanteDTO reclamante = iControlReclamante.buscarReclamante(id_reclamante);
				reclamo.setReclamante(reclamante);
				
				VehiculoDTO vehiculo = iControlVehiculo.buscarVehiculo(id_vehiculo);
				reclamo.setVehiculo(vehiculo);
				
				reclamo.setInmovilizado(inmovilizado);
				reclamo.setPeligroso(peligroso);
						
				OrdenDTO orden = iControlOrden.buscarOrden(id_orden);
				orden.setEstado("ABIERTA/SIN RECURSO");
				reclamo.setOrden(orden);
				iControlOrden.modificarOrden(orden.getId(), orden);

				reclamo.setDescripcion(descripcion);
				
				reclamo.setUsuario(mediadorPrincipal.getUsuario());
				
				iControlReclamo.agregarReclamo(reclamo);
				
				IControlOrden_Reclamo iControlOrden_Reclamo = MediadorAccionesIniciarPrograma.getControlOrden_Reclamo();
				Orden_ReclamoDTO orden_reclamo = new Orden_ReclamoDTO();
				
				orden_reclamo.setOrden(orden);
				orden_reclamo.setReclamo(reclamo);
				orden_reclamo.setId(iControlOrden_Reclamo.agregarOrden_Reclamo(orden_reclamo));
	
				res = true;

		} catch (Exception e) {
			System.out.println("Error al cargar los registrantess en la clase MediadorRegistrantes");
			e.printStackTrace();
		}
		return res;
	}

	public void actualizarDatosGestion() {
		if (guiGestionReclamoEntidad!=null)
			guiGestionReclamoEntidad.actualizarDatos();
		if (guiGestionReclamoAgente!=null)
			guiGestionReclamoAgente.actualizarDatos();
	}

	public Vector<PedidoDTO> obtenerPedidos() {
		Vector<PedidoDTO> pedidos = new Vector<PedidoDTO>();
		try {
			IControlPedido iControlPedido = MediadorAccionesIniciarPrograma.getControlPedido();
			pedidos = iControlPedido.obtenerPedidos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedidos;
	}

	public Vector<ReclamoDTO> obtenerReclamos() {
		Vector<ReclamoDTO> reclamos = new Vector<ReclamoDTO>();
		try {
			IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
			reclamos = iControlReclamo.obtenerReclamos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reclamos;
	}
	
	public Vector<ReclamoDTO> obtenerReclamosAgente() {
		Vector<ReclamoDTO> reclamos = new Vector<ReclamoDTO>();
		try {
			IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
			reclamos = iControlReclamo.obtenerReclamosAgentes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reclamos;
	}
	public Vector<ReclamoDTO> obtenerReclamosEntidad() {
		Vector<ReclamoDTO> reclamos = new Vector<ReclamoDTO>();
		try {
			IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
			reclamos = iControlReclamo.obtenerReclamosEntidades();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reclamos;
	}

	public boolean esEntidad(RegistranteDTO registrante) {
		boolean res = false;
		try {
			IControlEntidad iControlEntidad = MediadorAccionesIniciarPrograma.getControlEntidad();
			res = iControlEntidad.existeEntidad(registrante.getNombre_registrante());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean esAgente(RegistranteDTO registrante) {
		boolean res = false;
		try {
			IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
			res = iControlAgente.existeAgente(registrante.getNombre_registrante());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public void verRegistrante(String id_reclamo) {
		IControlReclamo iControlReclamo= MediadorAccionesIniciarPrograma.getControlReclamo();
		try {
			if (iControlReclamo.existeReclamo(new Long(id_reclamo))){
				ReclamoDTO reclamo =iControlReclamo.buscarReclamo(new Long(id_reclamo));
	
				ReclamanteDTO reclamante = reclamo.getReclamante();
				
				IControlMTelefono iControlMTelefono = MediadorAccionesIniciarPrograma.getControlMTelefono();

				Vector<MTelefonoDTO> telefonosDTO = iControlMTelefono.obtenerMTelefono(reclamante);
				Vector<String>	telefonos = new Vector<String>();

				for (int i = 0; i< telefonosDTO.size(); i++){
					telefonos.add(telefonosDTO.elementAt(i).getTelefono());
				}
				verReclamante = new GUIVerReclamante(this,reclamante.getNombre_apellido(),reclamante.getEmail(),reclamante.getDni(),telefonos);
				verReclamante.setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void eliminarReclamoEntidad(Long id) {
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		try {
			OrdenDTO orden = new OrdenDTO();
			if (iControlReclamo.existeReclamo(id)){
				if (iControlReclamo.buscarReclamo(id).getOrden()!=null){
					IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
					orden = iControlReclamo.buscarReclamo(id).getOrden(); 
					iControlReclamo.eliminarReclamo(id);
					if (orden.getRecurso()!=null){
						IControlRecurso iControlRecurso = MediadorAccionesIniciarPrograma.getControlRecurso();
						iControlOrden.eliminarOrden(orden.getId());
						iControlRecurso.eliminarRecurso(orden.getRecurso().getId());
					}else{
						iControlOrden.eliminarOrden(orden.getId());
					}
				}else{
					iControlReclamo.eliminarReclamo(id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void eliminarReclamoAgente(Long id) {
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		try {
			OrdenDTO orden = new OrdenDTO();
			if (iControlReclamo.existeReclamo(id)){
				if (iControlReclamo.buscarReclamo(id).getOrden()!=null){
					IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
					orden = iControlReclamo.buscarReclamo(id).getOrden(); 
					iControlReclamo.eliminarReclamo(id);
					if (orden.getRecurso()!=null){
						IControlRecurso iControlRecurso = MediadorAccionesIniciarPrograma.getControlRecurso();
						iControlOrden.eliminarOrden(orden.getId());
						iControlRecurso.eliminarRecurso(orden.getRecurso().getId());
					}else{
						iControlOrden.eliminarOrden(orden.getId());
					}
				}else{
					iControlReclamo.eliminarReclamo(id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void modificarReclamoEntidad(Long id) {
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		try {
			if (iControlReclamo.existeReclamo(id)){
				ReclamoDTO reclamo = iControlReclamo.buscarReclamo(id);
				guiModificarReclamoEntidad = new GUIModificarReclamoEntidad(this,reclamo);
				guiModificarReclamoEntidad.setVisible(true);
			}
		} catch (Exception e) {
			System.out.println("Error al modificar registrante en la clase MediadorUsuario");
			e.printStackTrace();
		}
	}
	
	public void modificarReclamoAgente(Long id) {
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		try {
			if (iControlReclamo.existeReclamo(id)){
				ReclamoDTO reclamo = iControlReclamo.buscarReclamo(id);
				guiModificarReclamoAgente = new GUIModificarReclamoAgente(this,reclamo);
				guiModificarReclamoAgente.setVisible(true);
			}
		} catch (Exception e) {
			System.out.println("Error al modificar agente en la clase MediadorUsuario");
			e.printStackTrace();
		}
	}

	public boolean modificarReclamoEntidad(ReclamoDTO reclamo) {
		boolean res = false;
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		try {
			ReclamoDTO original = iControlReclamo.buscarReclamo(reclamo.getId());
			if(! original.getOrden().getId().equals(reclamo.getOrden().getId())){
				IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
				
				OrdenDTO orden = iControlOrden.buscarOrden(reclamo.getOrden().getId());
				orden.setEstado("ABIERTA/SIN RECURSO");
				iControlOrden.modificarOrden(orden.getId(), orden);

				reclamo.setOrden(orden);
				iControlReclamo.modificarReclamo(reclamo.getId(), reclamo);

				IControlOrden_Reclamo iControlOrden_Reclamo = MediadorAccionesIniciarPrograma.getControlOrden_Reclamo();
				Orden_ReclamoDTO orden_reclamo = new Orden_ReclamoDTO();
				if (iControlOrden_Reclamo.existeOrden_Reclamo(original.getOrden(), reclamo)){
					orden_reclamo = iControlOrden_Reclamo.buscarOrden_Reclamo(original.getOrden(), reclamo);
					orden_reclamo.setOrden(orden);
					iControlOrden_Reclamo.modificarOrden_Reclamo(orden_reclamo.getId(), orden_reclamo);
				}else{
					orden_reclamo.setOrden(orden);
					orden_reclamo.setReclamo(reclamo);
					orden_reclamo.setId(iControlOrden_Reclamo.agregarOrden_Reclamo(orden_reclamo));
				}
				iControlOrden.eliminarOrden(original.getOrden().getId());
			}else{
				iControlReclamo.modificarReclamo(reclamo.getId(), reclamo);
			}
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public boolean modificarReclamoAgente(ReclamoDTO reclamo) {
		boolean res = false;
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		try {
			ReclamoDTO original = iControlReclamo.buscarReclamo(reclamo.getId());
			if(! original.getOrden().getId().equals(reclamo.getOrden().getId())){
				IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
				
				OrdenDTO orden = iControlOrden.buscarOrden(reclamo.getOrden().getId());
				orden.setEstado("ABIERTA/SIN RECURSO");
				iControlOrden.modificarOrden(orden.getId(), orden);

				reclamo.setOrden(orden);
				iControlReclamo.modificarReclamo(reclamo.getId(), reclamo);

				IControlOrden_Reclamo iControlOrden_Reclamo = MediadorAccionesIniciarPrograma.getControlOrden_Reclamo();
				Orden_ReclamoDTO orden_reclamo = new Orden_ReclamoDTO();
				if (iControlOrden_Reclamo.existeOrden_Reclamo(original.getOrden(), reclamo)){
					orden_reclamo = iControlOrden_Reclamo.buscarOrden_Reclamo(original.getOrden(), reclamo);
					orden_reclamo.setOrden(orden);
					iControlOrden_Reclamo.modificarOrden_Reclamo(orden_reclamo.getId(), orden_reclamo);
				}else{
					orden_reclamo.setOrden(orden);
					orden_reclamo.setReclamo(reclamo);
					orden_reclamo.setId(iControlOrden_Reclamo.agregarOrden_Reclamo(orden_reclamo));
				}
				iControlOrden.eliminarOrden(original.getOrden().getId());
			}else{
				iControlReclamo.modificarReclamo(reclamo.getId(), reclamo);
			}
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public EntidadDTO obtenerEntidad(Long id) {
		EntidadDTO entidad = new EntidadDTO();
		IControlEntidad iControlEntidad = MediadorAccionesIniciarPrograma.getControlEntidad();
		try {
			entidad = iControlEntidad.buscarEntidad(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entidad;
	}

	public AgenteDTO obtenerAgente(Long id) {
		AgenteDTO agente = new AgenteDTO();
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
		try {
			agente = iControlAgente.buscarAgente(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agente;
	}

	public boolean tienePedido(ReclamoDTO reclamo) {
		boolean res = true;
		IControlPedido iControlPedido = MediadorAccionesIniciarPrograma.getControlPedido();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();

		try {
			Vector<PedidoDTO> pedidos = iControlPedido.obtenerPedidos(reclamo);
			
			for (int i = 0; i<pedidos.size();i++){
				Vector<Pedido_PiezaDTO> pedidos_piezas = iControlPedido_Pieza.obtenerPedido_Pieza(pedidos.elementAt(i));
				for (int j= 0; j<pedidos_piezas.size();j++){
					res &= pedidos_piezas.elementAt(j).getEstado_pedido().equals("SIN SOLICITUD A FABRICA");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return !res;
	}



}
