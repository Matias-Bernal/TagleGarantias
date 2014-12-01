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
package cliente.ReclamoRapido;

import java.sql.Date;
import java.util.Vector;

import cliente.MediadorAccionesIniciarPrograma;
import cliente.MediadorPrincipal;
import common.Cuadruple;
import common.RootAndIp;
import common.Tupla;
import common.DTOs.AgenteDTO;
import common.DTOs.EntidadDTO;
import common.DTOs.MTelefonoDTO;
import common.DTOs.Mano_ObraDTO;
import common.DTOs.MarcaDTO;
import common.DTOs.ModeloDTO;
import common.DTOs.MuletoDTO;
import common.DTOs.OrdenDTO;
import common.DTOs.Orden_ReclamoDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.PiezaDTO;
import common.DTOs.ProveedorDTO;
import common.DTOs.ReclamanteDTO;
import common.DTOs.ReclamoDTO;
import common.DTOs.RegistranteDTO;
import common.DTOs.VehiculoDTO;
import common.GestionarAgente.IControlAgente;
import common.GestionarEntidad.IControlEntidad;
import common.GestionarMTelefono.IControlMTelefono;
import common.GestionarMano_Obra.IControlMano_Obra;
import common.GestionarMarca.IControlMarca;
import common.GestionarModelo.IControlModelo;
import common.GestionarMuleto.IControlMuleto;
import common.GestionarOrden.IControlOrden;
import common.GestionarOrden_Reclamo.IControlOrden_Reclamo;
import common.GestionarPedido.IControlPedido;
import common.GestionarPedido_Pieza.IControlPedido_Pieza;
import common.GestionarPieza.IControlPieza;
import common.GestionarProveedor.IControlProveedor;
import common.GestionarReclamante.IControlReclamante;
import common.GestionarReclamo.IControlReclamo;
import common.GestionarRegistrante.IControlRegistrante;
import common.GestionarVehiculo.IControlVehiculo;

public class MediadoReclamoRapido {

	private MediadorPrincipal mediadorPrincipal;
	
	private GUIAltaReclamoRapidoEntidad guiAltaReclamoRapidoEntidad;
	private GUIAltaReclamoRapidoAgente guiAltaReclamoRapidoAgente;
	
	private GUIBuscarReclamante guiBuscarReclamante;
	private GUIBuscarVehiculo guiBuscarVehiculo;
	
	public MediadoReclamoRapido(MediadorPrincipal mediadorPrincipal){
		this.setMediadorPrincipal(mediadorPrincipal);
	}

	public void altaReclamoRapidoEntidad() {
		guiAltaReclamoRapidoEntidad = new GUIAltaReclamoRapidoEntidad(this);
		guiAltaReclamoRapidoEntidad.setVisible(true);
	}
	
	public void altaReclamoRapidoAgente() {
		guiAltaReclamoRapidoAgente = new GUIAltaReclamoRapidoAgente(this);
		guiAltaReclamoRapidoAgente.setVisible(true);
	}

	public Vector<String> obtenerNombresEntidades() {
		Vector<EntidadDTO> entidadesDTO = new Vector<EntidadDTO>();
		Vector<String> entidades = new Vector<String>();
		try {
			IControlEntidad iControlEntidad = MediadorAccionesIniciarPrograma.getControlEntidad();
			entidadesDTO = iControlEntidad.obtenerEntidad();
			for (int i=0;i<entidadesDTO.size();i++){
				entidades.add(entidadesDTO.elementAt(i).getNombre_registrante());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entidades;
	}
	public Vector<String> obtenerNombresAgentes() {
		Vector<AgenteDTO> agentesDTO = new Vector<AgenteDTO>();
		Vector<String> agentes = new Vector<String>();
		try {
			IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
			agentesDTO = iControlAgente.obtenerAgentes();
			for (int i=0;i<agentesDTO.size();i++){
				agentes.add(agentesDTO.elementAt(i).getNombre_registrante());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agentes;
	}
	public Vector<OrdenDTO> obtenerOrdenes() {
		Vector<OrdenDTO> ordenes = new Vector<OrdenDTO>();
		try {
			IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
			ordenes = iControlOrden.obtenerOrdenes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordenes;
	}
	public Vector<ReclamanteDTO> obtenerReclamantes() {
		Vector<ReclamanteDTO> reclamantes = new Vector<ReclamanteDTO>();
		try {
			IControlReclamante iControlReclamnte = MediadorAccionesIniciarPrograma.getControlReclamante();
			reclamantes = iControlReclamnte.obtenerReclamantes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reclamantes;
	}
	public Vector<VehiculoDTO> obtenerVehiculos() {
		Vector<VehiculoDTO> vehiculos = new Vector<VehiculoDTO>();
		try {
			IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
			vehiculos = iControlVehiculo.obtenerVehiculos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehiculos;
	}
	public Vector<String> obtenerTelefonos(ReclamanteDTO reclamante){
		Vector<String> telefonos = new Vector<String>();
		Vector<MTelefonoDTO> telefonosDTO = new Vector<MTelefonoDTO>();
		try {
			IControlMTelefono iControlMTelefonos = MediadorAccionesIniciarPrograma.getControlMTelefono();
			telefonosDTO = iControlMTelefonos.obtenerMTelefono(reclamante);
			for (int i=0; i< telefonosDTO.size();i++){
				telefonos.add(telefonosDTO.elementAt(i).getTelefono());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return telefonos;
	}
	public Vector<String> obtenerNombresMarcas() {
		Vector<String> marcas = new Vector<String>();
		Vector<MarcaDTO> marcasDTO = new Vector<MarcaDTO>();
		try {
			IControlMarca iControlMarca = MediadorAccionesIniciarPrograma.getControlMarca();
			marcasDTO = iControlMarca.obtenerMarcas();
			for (int i=0; i< marcasDTO.size();i++){
				marcas.add(marcasDTO.elementAt(i).getNombre_marca());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marcas;
	}
	public Vector<String> obtenerNombresModelos() {
		Vector<String> modelos = new Vector<String>();
		Vector<ModeloDTO> modelosDTO = new Vector<ModeloDTO>();
		try {
			IControlModelo iControlModelo = MediadorAccionesIniciarPrograma.getControlModelo();
			modelosDTO = iControlModelo.obtenerModelos();
			for (int i=0; i< modelosDTO.size();i++){
				modelos.add(modelosDTO.elementAt(i).getNombre_modelo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelos;
	}
	public Vector<String> obtenerProveedores() {
		Vector<String> proveedores = new Vector<String>();
		IControlProveedor iControlProveedor = MediadorAccionesIniciarPrograma.getControlProveedor();
		try{
			Vector<ProveedorDTO> proveedoresDTO = iControlProveedor.obtenerProveedores();
			for(int i =0; i< proveedoresDTO.size();i++){
				proveedores.add(proveedoresDTO.elementAt(i).getNombre());
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return proveedores;
	}
	
	public void setReclamante(String id_reclamante) {
		try {
			IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
			ReclamanteDTO reclamante;
			reclamante = iControlReclamante.buscarReclamante(new Long(id_reclamante));
			if(guiAltaReclamoRapidoEntidad!=null && guiAltaReclamoRapidoEntidad.isReclamante_desdeEntidad())
				guiAltaReclamoRapidoEntidad.setReclamanteEntidad(reclamante);
			if(guiAltaReclamoRapidoAgente!=null && guiAltaReclamoRapidoAgente.isReclamante_desdeAgente())
				guiAltaReclamoRapidoAgente.setReclamanteAgente(reclamante);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setVehiculo(String id_vehiculo) {
		try {
			IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
			VehiculoDTO vehiculo;
			vehiculo = iControlVehiculo.buscarVehiculo(new Long(id_vehiculo));
			if(guiAltaReclamoRapidoEntidad!=null && guiAltaReclamoRapidoEntidad.isVehiculo_desdeEntidad())
				guiAltaReclamoRapidoEntidad.setVehiculoEntidad(vehiculo);
			if(guiAltaReclamoRapidoAgente!=null && guiAltaReclamoRapidoAgente.isVehiculo_desdeAgente())
				guiAltaReclamoRapidoAgente.setVehiculoAgente(vehiculo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void buscarReclamante() {
		guiBuscarReclamante = new GUIBuscarReclamante(this);
		guiBuscarReclamante.setVisible(true);
	}
	public void buscarVehiculo() {
		guiBuscarVehiculo = new GUIBuscarVehiculo(this);
		guiBuscarVehiculo.setVisible(true);
	}


	public MediadorPrincipal getMediadorPrincipal() {
		return mediadorPrincipal;
	}

	public void setMediadorPrincipal(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}

	public boolean nuevoReclamo(String nombre_registrante, String nombre_reclamante,
			String dni_reclamante,String email_registrante,  Vector<String> telefonos_reclamante, String nombre_titular,
			String dominio, String vin, String nombre_marca, String nombre_modelo,
			String numero_orden, java.sql.Date fechaApertura,
			java.sql.Date fechaReclamo, String descipcion, Vector<Cuadruple<PiezaDTO, MuletoDTO, String, Tupla<Boolean, Boolean>>> piezas_R, String numero_pedido, Date fecha_solicitud_pedido, Boolean peligroso,
			Boolean inmovilizado) {
		
		boolean res = false;
		RegistranteDTO registrante = new RegistranteDTO();
		ReclamanteDTO reclamante = new ReclamanteDTO();
		VehiculoDTO vehiculo = new VehiculoDTO();
		MarcaDTO marca = new MarcaDTO();
		ModeloDTO modelo = new ModeloDTO();
		OrdenDTO orden = new OrdenDTO();
		ReclamoDTO reclamo = new ReclamoDTO();
		Orden_ReclamoDTO orden_reclamo = new Orden_ReclamoDTO();
		PedidoDTO pedido = new PedidoDTO();
		PiezaDTO pieza = new PiezaDTO();
		Pedido_PiezaDTO pedido_pieza = new Pedido_PiezaDTO();
		Mano_ObraDTO mano_obra = new Mano_ObraDTO();
		MuletoDTO  muleto = new MuletoDTO();

		
		try {
			IControlRegistrante iControlRegistrante = MediadorAccionesIniciarPrograma.getControlRegistrante();
			IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
			IControlMTelefono iControlMTelefono = MediadorAccionesIniciarPrograma.getControlMTelefono();
			IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
			IControlMarca iControlMarca = MediadorAccionesIniciarPrograma.getControlMarca();
			IControlModelo iControlModelo = MediadorAccionesIniciarPrograma.getControlModelo();
			IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
			IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
			IControlOrden_Reclamo iControlOrden_Reclamo = MediadorAccionesIniciarPrograma.getControlOrden_Reclamo();
			IControlPedido iControlPedido = MediadorAccionesIniciarPrograma.getControlPedido();
			IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
			IControlPieza iControl_Pieza = MediadorAccionesIniciarPrograma.getControlPieza();
			IControlMano_Obra iControlMano_Obra = MediadorAccionesIniciarPrograma.getControlMano_Obra();
			IControlMuleto iControlMuleto = MediadorAccionesIniciarPrograma.getControlMuleto();
			
			//REGISTRATE
			if (iControlRegistrante.existeRegistrante(nombre_registrante)){
				registrante = iControlRegistrante.buscarRegistranteDTO(nombre_registrante);
			}else{
				registrante.setNombre_registrante(nombre_registrante);
				registrante.setId(iControlRegistrante.agregarRegistranteDTO(registrante));
			}
			//RECLAMANTE
			if(iControlReclamante.existeReclamanteDni(dni_reclamante)){
				reclamante = iControlReclamante.buscarReclamanteDni(dni_reclamante);
			}else{
				reclamante.setDni(dni_reclamante);
				reclamante.setEmail(email_registrante);
				reclamante.setNombre_apellido(nombre_reclamante);
				reclamante.setId(iControlReclamante.agregarReclamante(reclamante));
			}
			//MTELEFONO
			Vector<MTelefonoDTO> telefonosDTO = new Vector<MTelefonoDTO>();
			telefonosDTO = iControlMTelefono.obtenerMTelefono(reclamante);
			for (int i = 0; i< telefonosDTO.size(); i++){
				if (!telefonos_reclamante.contains(telefonosDTO.elementAt(i).getTelefono())){
					iControlMTelefono.eliminarMTelefono(telefonosDTO.elementAt(i).getId());
				}else{
					telefonos_reclamante.remove(telefonosDTO.elementAt(i).getTelefono());
				}
			}
			for (int i = 0; i< telefonos_reclamante.size(); i++){
				if(!telefonos_reclamante.elementAt(i).equals("")){
					MTelefonoDTO mtelefono = new MTelefonoDTO(reclamante, telefonos_reclamante.elementAt(i));
					iControlMTelefono.agregarMTelefono(mtelefono);
				}
			}
			//VEHICULO
			if(iControlVehiculo.existeVehiculo(dominio)){
				vehiculo = iControlVehiculo.buscarVehiculo(dominio);
			}else{
				vehiculo.setDominio(dominio);
				vehiculo.setNombre_titular(nombre_titular);
				vehiculo.setVin(vin);
				if(iControlMarca.existeMarca(nombre_marca)){
					marca = iControlMarca.buscarMarca(nombre_marca);
				}else{
					marca.setNombre_marca(nombre_marca);
					marca.setId(iControlMarca.agregarMarca(marca));
				}
				vehiculo.setMarca(marca);
				if(iControlModelo.existeModelo(nombre_modelo)){
					modelo = iControlModelo.buscarModelo(nombre_modelo);
				}else{
					modelo.setNombre_modelo(nombre_modelo);
					modelo.setMarca(marca);
					modelo.setId(iControlModelo.agregarModelo(modelo));
				}
				vehiculo.setModelo(modelo);
				vehiculo.setId(iControlVehiculo.agregarVehiculo(vehiculo));
			}
			//ORDEN
			orden.setFecha_apertura(fechaApertura);
			orden.setNumero_orden(numero_orden);
			orden.setEstado("ABIERTA/SIN RECURSO");
			orden.setId(iControlOrden.agregarOrden(orden));

			//RECLAMO

			reclamo.setFecha_reclamo(fechaReclamo);
			if(!piezas_R.isEmpty() && !numero_pedido.isEmpty() && fecha_solicitud_pedido!=null){
				reclamo.setEstado_reclamo("ABIERTO/CON PEDIDO/SIN TURNO");
			}else{
				reclamo.setEstado_reclamo("ABIERTO/SIN PEDIDO/SIN TURNO");
			}
			reclamo.setDescripcion(descipcion);
			reclamo.setInmovilizado(inmovilizado);
			reclamo.setPeligroso(peligroso);
			reclamo.setOrden(orden);
			reclamo.setReclamante(reclamante);
			reclamo.setRegistrante(registrante);
			reclamo.setVehiculo(vehiculo);
			reclamo.setUsuario(mediadorPrincipal.getUsuario());
			reclamo.setId(iControlReclamo.agregarReclamo(reclamo));
			
			mano_obra.setReclamo(reclamo);
			mano_obra.setCantidad_horas(0);
			if(esEntidad(registrante)){
				common.RootAndIp.setConf("");    
				if(registrante.getNombre_registrante().equals(iControlRegistrante.buscarRegistranteDTO("RENAULT").getNombre_registrante())){
					mano_obra.setValor_mano_obra(RootAndIp.getValor_mano_obra_renault());
				}
				if(registrante.getNombre_registrante().equals(iControlRegistrante.buscarRegistranteDTO("NISSAN").getNombre_registrante())){
					mano_obra.setValor_mano_obra(RootAndIp.getValor_mano_obra_nissan());
				}
			}
			mano_obra.setId(iControlMano_Obra.agregarMano_Obra(mano_obra));	
			
			//if(!piezas.isEmpty() && !numero_pedido.isEmpty() && fecha_solicitud_pedido!=null){
			if(!piezas_R.isEmpty()){
				pedido = new PedidoDTO();
				pedido.setReclamo(reclamo);
				pedido.setFecha_solicitud_pedido(fecha_solicitud_pedido);
				pedido.setId(iControlPedido.agregarPedido(pedido));
				
				for (int i = 0; i< piezas_R.size();i++){

					pieza = new PiezaDTO();
					pieza = piezas_R.elementAt(i).first();
					pieza.setId(iControl_Pieza.agregarPieza(pieza));
					
					pedido_pieza = new Pedido_PiezaDTO();
					pedido_pieza.setPedido(pedido);
					pedido_pieza.setPieza(pieza);
					pedido_pieza.setNumero_pedido(numero_pedido);
					pedido_pieza.setEstado_pedido("EN ESPERA DE RECEPCION FABRICA");
					//MULETO
					if(piezas_R.elementAt(i).second()!=null){
						muleto.setDescripcion(piezas_R.elementAt(i).second().getDescripcion());
						muleto.setPedido(pedido);
						muleto.setPieza(pieza);
						muleto.setVin(piezas_R.elementAt(i).second().getVin());
						muleto.setId(iControlMuleto.agregarMuleto(muleto));
						pedido_pieza.setMuleto(muleto);						
					}
					pedido_pieza.setFecha_solicitud_fabrica(fecha_solicitud_pedido);
					pedido_pieza.setPnc(piezas_R.elementAt(i).third());
					pedido_pieza.setStock(piezas_R.elementAt(i).fourth().first());
					pedido_pieza.setPropio(piezas_R.elementAt(i).fourth().second());
					
					mano_obra = new Mano_ObraDTO();
					mano_obra.setReclamo(reclamo);
					mano_obra.setCantidad_horas(0);
					
					if(esEntidad(registrante)){
						common.RootAndIp.setConf("");    
						if(registrante.getNombre_registrante().equals(iControlRegistrante.buscarRegistranteDTO("RENAULT").getNombre_registrante())){
							mano_obra.setValor_mano_obra(RootAndIp.getValor_mano_obra_renault());
						}
						if(registrante.getNombre_registrante().equals(iControlRegistrante.buscarRegistranteDTO("NISSAN").getNombre_registrante())){
							mano_obra.setValor_mano_obra(RootAndIp.getValor_mano_obra_nissan());
						}
					}
					mano_obra.setId(iControlMano_Obra.agregarMano_Obra(mano_obra));	
					pedido_pieza.setMano_obra(mano_obra);
					
					pedido_pieza.setId(iControlPedido_Pieza.agregarPedido_Pieza(pedido_pieza));
				}
			}
			//RECLAMO-ORDEN
			if(!piezas_R.isEmpty()){
				orden.setEstado("ABIERTA/SIN RECURSO");
			}else{
				orden.setEstado("SIN PEDIDO");
			}
			iControlOrden.modificarOrden(orden.getId(), orden);
			orden_reclamo.setOrden(orden);
			orden_reclamo.setReclamo(reclamo);
			orden_reclamo.setId(iControlOrden_Reclamo.agregarOrden_Reclamo(orden_reclamo));

			res= true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private boolean esEntidad(RegistranteDTO registrante) {
		boolean res = false;
		try {
			IControlEntidad iControlEntidad = MediadorAccionesIniciarPrograma.getControlEntidad();
			res = iControlEntidad.existeEntidad(registrante.getNombre_registrante());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public ProveedorDTO obtenerProveedor(String nombre_proveedor) {
		ProveedorDTO proveedor = null;
		try{
			IControlProveedor iControlProveedor = MediadorAccionesIniciarPrograma.getControlProveedor();
			proveedor = iControlProveedor.buscarProveedor(nombre_proveedor);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return proveedor;
	}
}