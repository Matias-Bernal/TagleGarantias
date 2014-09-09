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
package cliente.GestionarPedido;

import java.sql.Date;
import java.util.Vector;

import cliente.MediadorAccionesIniciarPrograma;
import cliente.MediadorPrincipal;
import common.RootAndIp;
import common.DTOs.AgenteDTO;
import common.DTOs.BdgDTO;
import common.DTOs.Devolucion_PiezaDTO;
import common.DTOs.EntidadDTO;
import common.DTOs.MTelefonoDTO;
import common.DTOs.Mano_ObraDTO;
import common.DTOs.MuletoDTO;
import common.DTOs.OrdenDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.PiezaDTO;
import common.DTOs.ProveedorDTO;
import common.DTOs.ReclamanteDTO;
import common.DTOs.ReclamoDTO;
import common.DTOs.RegistranteDTO;
import common.GestionarAgente.IControlAgente;
import common.GestionarBdg.IControlBdg;
import common.GestionarDevolucion_Pieza.IControlDevolucion_Pieza;
import common.GestionarEntidad.IControlEntidad;
import common.GestionarMTelefono.IControlMTelefono;
import common.GestionarMano_Obra.IControlMano_Obra;
import common.GestionarMuleto.IControlMuleto;
import common.GestionarOrden.IControlOrden;
import common.GestionarPedido.IControlPedido;
import common.GestionarPedido_Pieza.IControlPedido_Pieza;
import common.GestionarPieza.IControlPieza;
import common.GestionarProveedor.IControlProveedor;
import common.GestionarReclamante.IControlReclamante;
import common.GestionarReclamo.IControlReclamo;
import common.GestionarRegistrante.IControlRegistrante;

public class MediadorPedido {

	private MediadorPrincipal mediadorPrincipal;
	
	private GUIAltaPedidoAgente guiAltaPedidoAgente;
	private GUIAltaPedidoEntidad guiAltaPedidoEntidad;
	
	private GUIGestionPedidoAgente guiGestionPedidoAgente;
	private GUIGestionPedidoEntidad guiGestionPedidoEntidad;
	
	private GUIModificarPedidoAgente guiModificarPedidoAgente;
	private GUIModificarPedidoEntidad guiModificarPedidoEntidad;
	
	private GUIBuscarReclamoEntidad guiBuscarReclamoEntidad;
	private GUIBuscarReclamoAgente guiBuscarReclamoAgente;
	
	private GUIVerReclamante verReclamante;
		
	public MediadorPedido(MediadorPrincipal mediadorPrincipal) {
		this.setMediadorPrincipal(mediadorPrincipal);
	}

	public void altaPedidoEntidad(String nombre_registrante, String tipo) {
		guiAltaPedidoEntidad = new GUIAltaPedidoEntidad(this); // faltan datos
		guiAltaPedidoEntidad.setVisible(true);		
	}
	public void altaPedidoAgente(String nombre_registrante, String tipo) {
		guiAltaPedidoAgente = new GUIAltaPedidoAgente(this);
		guiAltaPedidoAgente.setVisible(true);	
	}

	public void altaPedidoEntidad() {
		guiAltaPedidoEntidad = new GUIAltaPedidoEntidad(this);
		guiAltaPedidoEntidad.setVisible(true);		
	}
	public void altaPedidoAgente() {
		guiAltaPedidoAgente = new GUIAltaPedidoAgente(this);
		guiAltaPedidoAgente.setVisible(true);		
	}

	public void gestionarPedidoEntidad() {
		guiGestionPedidoEntidad = new GUIGestionPedidoEntidad(this);
		guiGestionPedidoEntidad.setVisible(true);	
	}
	public void gestionarPedidoAgente() {
		guiGestionPedidoAgente = new GUIGestionPedidoAgente(this);
		guiGestionPedidoAgente.setVisible(true);
	}

	public Vector<PedidoDTO> obtenerPedidos() {
		Vector<PedidoDTO> pedido = new Vector<PedidoDTO>();
		IControlPedido iControlPedido = MediadorAccionesIniciarPrograma.getControlPedido();
		try {
			pedido = iControlPedido.obtenerPedidos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedido;
	}
	
	public Vector<PedidoDTO> obtenerPedidosAgentes() {
		Vector<PedidoDTO> pedido = new Vector<PedidoDTO>();
		IControlPedido iControlPedido = MediadorAccionesIniciarPrograma.getControlPedido();
		try {
			pedido = iControlPedido.obtenerPedidosAgentes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedido;
	}
	
	public Vector<PedidoDTO> obtenerPedidosEntidades() {
		Vector<PedidoDTO> pedido = new Vector<PedidoDTO>();
		IControlPedido iControlPedido = MediadorAccionesIniciarPrograma.getControlPedido();
		try {
			pedido = iControlPedido.obtenerPedidosEntidades();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedido;
	}
	
	
	public Vector<Pedido_PiezaDTO> buscarPedidoPieza(Long id_pedido) {
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
			IControlPedido iControlPedido = MediadorAccionesIniciarPrograma.getControlPedido();
			PedidoDTO pedido;
			pedido = iControlPedido.buscarPedido(id_pedido);
			pedidos_piezas = iControlPedido_Pieza.obtenerPedido_Pieza(pedido);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedidos_piezas;
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
	
	public PiezaDTO obtenerPieza(String num_pieza) {
		PiezaDTO pieza = null;
		try{
			IControlPieza iContolPieza = MediadorAccionesIniciarPrograma.getControlPieza();
			pieza = iContolPieza.buscarPieza(num_pieza);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return pieza;
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

	public void buscarReclamoEntidad() {
		guiBuscarReclamoEntidad = new GUIBuscarReclamoEntidad(this);
		guiBuscarReclamoEntidad.setVisible(true);
	}
	public void buscarReclamoAgente() {
		guiBuscarReclamoAgente = new GUIBuscarReclamoAgente(this);
		guiBuscarReclamoAgente.setVisible(true);
	}

	public boolean nuevoPedido(String numero_pedido, Date fsp, ReclamoDTO reclamo, Vector<PiezaDTO> piezas) {
		boolean res = false;
		IControlPedido iControlPedido = MediadorAccionesIniciarPrograma.getControlPedido();
		try {
				PedidoDTO pedidoDTO = new PedidoDTO(fsp,reclamo);
				pedidoDTO.setId(iControlPedido.agregarPedido(pedidoDTO));
								
				IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
				IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
				
				if (reclamo.getFecha_turno()==null){
					reclamo.setEstado_reclamo("ABIERTO/CON PEDIDO/SIN TURNO");
				}else{
					reclamo.setEstado_reclamo("ABIERTO/CON PEDIDO/CON TURNO");
				}
				iControlReclamo.modificarReclamo(reclamo.getId(), reclamo);
				
				if(reclamo.getOrden()!=null){
					OrdenDTO orden = reclamo.getOrden();
					if(orden.getRecurso()==null){
						orden.setEstado("ABIERTA/SIN RECURSO");
					}else{
						orden.setEstado("ABIERTA/CON RECURSO");
					}
					iControlOrden.modificarOrden(orden.getId(), orden);
				}
				
				IControlPieza iControlPieza = MediadorAccionesIniciarPrograma.getControlPieza();
				IControlProveedor iControlProveedor = MediadorAccionesIniciarPrograma.getControlProveedor();
				IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
				for(int i=0; i<piezas.size();i++){
					if (piezas.elementAt(i).getProveedor()!=null){
						ProveedorDTO proveedor = iControlProveedor.buscarProveedor(piezas.elementAt(i).getProveedor().getNombre());
						PiezaDTO piezaDTO = new PiezaDTO(piezas.elementAt(i).getNumero_pieza(), piezas.elementAt(i).getDescripcion(),proveedor);
						piezaDTO.setId(iControlPieza.agregarPieza(piezaDTO));
						if(!iControlPedido_Pieza.existePedido_Pieza(pedidoDTO, piezaDTO)){
							Pedido_PiezaDTO pedido_piezaDTO = new Pedido_PiezaDTO(pedidoDTO, piezaDTO);
							pedido_piezaDTO.setNumero_pedido(numero_pedido);
							
							Mano_ObraDTO mano_obra = new Mano_ObraDTO();
							mano_obra.setReclamo(reclamo);
							mano_obra.setCantidad_horas(0);
							
							if(esEntidad(reclamo.getRegistrante())){
								common.RootAndIp.setConf("");    
								IControlRegistrante iControlRegistrante = MediadorAccionesIniciarPrograma.getControlRegistrante();
								if(reclamo.getRegistrante().getNombre_registrante().equals(iControlRegistrante .buscarRegistranteDTO("RENAULT").getNombre_registrante())){
									mano_obra.setValor_mano_obra(RootAndIp.getValor_mano_obra_renault());
								}
								if(reclamo.getRegistrante().getNombre_registrante().equals(iControlRegistrante.buscarRegistranteDTO("NISSAN").getNombre_registrante())){
									mano_obra.setValor_mano_obra(RootAndIp.getValor_mano_obra_nissan());
								}
							}
							IControlMano_Obra iControlMano_Obra = MediadorAccionesIniciarPrograma.getControlMano_Obra();
							mano_obra.setId(iControlMano_Obra.agregarMano_Obra(mano_obra));	
							pedido_piezaDTO.setMano_obra(mano_obra);
							
							pedido_piezaDTO.setEstado_pedido("SIN SOLICITUD A FABRICA");
							pedido_piezaDTO.setId(iControlPedido_Pieza.agregarPedido_Pieza(pedido_piezaDTO));
						}
					}
				}
			res = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public void setReclamoEntidad(String id) {
		if(guiAltaPedidoEntidad != null){
			IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
			try {
				guiAltaPedidoEntidad.setReclamo(iControlReclamo.buscarReclamo(new Long(id)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void setReclamoAgente(String id) {
		if(guiAltaPedidoAgente != null){
			IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
			try {
				guiAltaPedidoAgente.setReclamo(iControlReclamo.buscarReclamo(new Long(id)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(guiModificarPedidoAgente !=null){
			IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
			try {
				guiModificarPedidoAgente.setReclamo(iControlReclamo.buscarReclamo(new Long(id)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
			res = iControlAgente.existeAgente(registrante.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public MediadorPrincipal getMediadorPrincipal() {
		return mediadorPrincipal;
	}
	public void setMediadorPrincipal(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void actualizarDatosGestion() {
		if(guiGestionPedidoAgente!=null)
			guiGestionPedidoAgente.actualizarDatos();
		if(guiGestionPedidoEntidad!=null)
			guiGestionPedidoEntidad.actualizarDatos();
	}

	public void eliminarPedido(Long id_pedido) {
		IControlPedido iControlPedido =MediadorAccionesIniciarPrograma.getControlPedido();
		IControlPedido_Pieza iControlPedido_Pieza =MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
		IControlPieza iControlPieza = MediadorAccionesIniciarPrograma.getControlPieza();
		IControlBdg iControlBdg = MediadorAccionesIniciarPrograma.getControlBdg();
		IControlDevolucion_Pieza iControlDevolucion = MediadorAccionesIniciarPrograma.getControlDevolucion_Pieza();
		IControlMuleto iControlMuleto = MediadorAccionesIniciarPrograma.getControlMuleto();
		IControlMano_Obra iControlMano_Obra = MediadorAccionesIniciarPrograma.getControlMano_Obra();
		try {
			PedidoDTO pedido = iControlPedido.buscarPedido(id_pedido);
			Vector<Pedido_PiezaDTO> pedidos_piezas = iControlPedido_Pieza.obtenerPedido_Pieza(pedido);
			for(int i = 0 ; i<pedidos_piezas.size();i++){
				if(pedidos_piezas.elementAt(i).getBdg()!=null)
					iControlBdg.eliminarBdg(pedidos_piezas.elementAt(i).getBdg().getId());
				if(pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null)
					iControlDevolucion.eliminarDevolucion_Pieza(pedidos_piezas.elementAt(i).getDevolucion_pieza().getId());
				if(pedidos_piezas.elementAt(i).getMano_obra()!=null)	
					iControlMano_Obra.eliminarMano_Obra(pedidos_piezas.elementAt(i).getMano_obra().getId());
				if(pedidos_piezas.elementAt(i).getMuleto()!=null)
					iControlMuleto.eliminarMuleto(pedidos_piezas.elementAt(i).getMuleto().getId());
				
				iControlPedido_Pieza.eliminarPedido_Pieza(pedidos_piezas.elementAt(i).getId());
				
				if(pedido.getReclamo()!=null){
					if(pedido.getReclamo().getOrden()!=null){
						OrdenDTO orden = pedido.getReclamo().getOrden();
						orden.setEstado("SIN PEDIDO");
						iControlOrden.modificarOrden(orden.getId(), orden);
						//TODO para eliminar orden hacerlo aca
					}
					ReclamoDTO reclamoDTO = pedido.getReclamo();
					if(reclamoDTO.getFecha_turno()!=null){
						reclamoDTO.setEstado_reclamo("ABIERTO/SIN PEDIDO/CON TURNO");
					}else{
						reclamoDTO.setEstado_reclamo("ABIERTO/SIN PEDIDO/SIN TURNO");
					}
					iControlReclamo.modificarReclamo(reclamoDTO.getId(), reclamoDTO);
				}
				iControlPedido.eliminarPedido(id_pedido);
				iControlPieza.eliminarPieza(pedidos_piezas.elementAt(i).getPieza().getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void modificarPedidoAgente(Long id) {
		IControlPedido iControlPedido = MediadorAccionesIniciarPrograma.getControlPedido();
		try {
			PedidoDTO pedido = iControlPedido.buscarPedidoAgente(id);
			if(pedido!=null){
				guiModificarPedidoAgente = new GUIModificarPedidoAgente(this,pedido);
				guiModificarPedidoAgente.setVisible(true);
			}
			else
				System.out.println("Error al modificar agente en la clase MediadorPedido");
		} catch (Exception e) {
			System.out.println("Error al modificar agente en la clase MediadorPedido");
			e.printStackTrace();
		}
	}
	public void modificarPedidoEntidad(Long id) {
		IControlPedido iControlPedido = MediadorAccionesIniciarPrograma.getControlPedido();
		try {
			PedidoDTO pedido = iControlPedido.buscarPedidoEntidad(id);
			if(pedido!=null){
				guiModificarPedidoEntidad = new GUIModificarPedidoEntidad(this,pedido);
				guiModificarPedidoEntidad.setVisible(true);
			}
			else
				System.out.println("Error al modificar agente en la clase MediadorPedido");
		} catch (Exception e) {
			System.out.println("Error al modificar agente en la clase MediadorPedido");
			e.printStackTrace();
		}
	}

	public void verRegistrante(String id_pedido) {
		IControlPedido iControlPedido = MediadorAccionesIniciarPrograma.getControlPedido();
		IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
		try {
			PedidoDTO pedido = iControlPedido.buscarPedido(new Long(id_pedido));
			if (pedido!=null && pedido.getReclamo()!=null && pedido.getReclamo().getReclamante()!=null){
				ReclamanteDTO reclamante = pedido.getReclamo().getReclamante();
				
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

	public Vector<AgenteDTO> obtenerAgentes() {
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
		Vector<AgenteDTO> agentes = new Vector<AgenteDTO> ();
		try {
			agentes = iControlAgente.obtenerAgentes();
		} catch (Exception e) {
			System.out.println("Error al cargar los registrantess en la clase MediadorPedido");
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
			System.out.println("Error al cargar los registrantess en la clase MediadorPedido");
			e.printStackTrace();
		}
		return entidades;
	}

	public Vector<Pedido_PiezaDTO> obtenerPedidos_Piezas(Long id_pedido) {
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlPedido iControlPedido = MediadorAccionesIniciarPrograma.getControlPedido();
		try {
			PedidoDTO pedido = iControlPedido.buscarPedido(id_pedido);
			pedidos_piezas = iControlPedido_Pieza.obtenerPedido_Pieza(pedido);	
		} catch (Exception e) {
			System.out.println("Error al cargar los PEDIDOS_PIEZAS en la clase MediadorPedido");
			e.printStackTrace();
		}
		return pedidos_piezas;
	}
	
	public Vector<Pedido_PiezaDTO> obtenerPedidos_PiezasEntidad(Long id_pedido) {
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		try {
			pedidos_piezas = iControlPedido_Pieza.obtenerPedido_PiezaEntidad(id_pedido);	
		} catch (Exception e) {
			System.out.println("Error al cargar los PEDIDOS_PIEZAS en la clase MediadorPedido");
			e.printStackTrace();
		}
		return pedidos_piezas;
	}
	
	public Vector<Pedido_PiezaDTO> obtenerPedidos_PiezasAgente(Long id_pedido) {
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		try {
			pedidos_piezas = iControlPedido_Pieza.obtenerPedido_PiezaAgente(id_pedido);	
		} catch (Exception e) {
			System.out.println("Error al cargar los PEDIDOS_PIEZAS en la clase MediadorPedido");
			e.printStackTrace();
		}
		return pedidos_piezas;
	}

	public boolean modificarPedidoAgente(PedidoDTO pedido, Vector<Pedido_PiezaDTO> pedidos_piezas) {
		boolean res = false;
		IControlPedido iControlPedido = MediadorAccionesIniciarPrograma.getControlPedido();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlBdg iControlBdg = MediadorAccionesIniciarPrograma.getControlBdg();
		IControlMuleto iControlMuelto = MediadorAccionesIniciarPrograma.getControlMuleto();
		IControlPieza iControlPieza = MediadorAccionesIniciarPrograma.getControlPieza();
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		try {
			IControlDevolucion_Pieza iControlDevolucion_Pieza = MediadorAccionesIniciarPrograma.getControlDevolucion_Pieza();

			if (iControlPedido.existePedido(pedido.getId())){
				Vector<Pedido_PiezaDTO> pedidos_piezasDTO = iControlPedido_Pieza.obtenerPedido_Pieza(pedido);
				for(int i=0; i<pedidos_piezasDTO.size();i++){
					for(int j=0; j<pedidos_piezas.size();j++){
						if(pedidos_piezasDTO.elementAt(i).getId().equals(pedidos_piezas.elementAt(j).getId())){
							
							Pedido_PiezaDTO original = pedidos_piezasDTO.elementAt(i);
							Pedido_PiezaDTO modificado = pedidos_piezas.elementAt(j);

							//BDG
							if(modificado.getBdg()!=null){
								if (original.getBdg()!=null){
									//EXISTE BDG
									BdgDTO bdg = original.getBdg();
									bdg.setNumero_bdg(modificado.getBdg().getNumero_bdg());
									if(modificado.getBdg().getFecha_bdg()!=null){
										bdg.setFecha_bdg(modificado.getBdg().getFecha_bdg());
									}else{
										bdg.setFecha_bdg(null);
									}
									iControlBdg.modificarBdg(bdg.getId(), bdg);
									//NO SE DEBERIAN CAMBIAR LA PIEZA Y EL PEDIDO
								}else{
									//NO EXISTE BDG
									BdgDTO bdg = new BdgDTO();
									bdg.setNumero_bdg(modificado.getBdg().getNumero_bdg());
									if(modificado.getBdg().getFecha_bdg()!=null){
										bdg.setFecha_bdg(modificado.getBdg().getFecha_bdg());
									}else{
										bdg.setFecha_bdg(null);
									}
									bdg.setPedido(original.getPedido());
									bdg.setPieza(original.getPieza());
									bdg.setId(iControlBdg.agregarBdg(bdg));

									original.setBdg(bdg);
								}
							}else{
								original.setBdg(null);
							}
							//DEVOLUCION PIEZA
							//FECHA SOLICITUD DEVOLUCION
							if(modificado.getFecha_solicitud_devolucion()!=null){
								original.setFecha_solicitud_devolucion(modificado.getFecha_solicitud_devolucion());
							}else{
								original.setFecha_solicitud_devolucion(null);
							}
							//FECHA APROBACION SOLICITUD DEVOLUCION
							if(modificado.getFecha_aprobacion_solicitud_devolucion()!=null){
								original.setFecha_aprobacion_solicitud_devolucion(modificado.getFecha_aprobacion_solicitud_devolucion());
							}else{
								original.setFecha_aprobacion_solicitud_devolucion(null);
							}
							if(modificado.getDevolucion_pieza()!=null){
								if (original.getDevolucion_pieza()!=null){
									//EXISTE DEVOLUCION
									Devolucion_PiezaDTO devolucion = original.getDevolucion_pieza();
									devolucion.setNumero_remito(modificado.getDevolucion_pieza().getNumero_remito());
									devolucion.setNumero_retiro(modificado.getDevolucion_pieza().getNumero_retiro());
									devolucion.setTransporte(modificado.getDevolucion_pieza().getTransporte());
									if(modificado.getDevolucion_pieza().getFecha_devolucion()!=null){
										devolucion.setFecha_devolucion(modificado.getDevolucion_pieza().getFecha_devolucion());
									}else{
										devolucion.setFecha_devolucion(null);
									}
									iControlDevolucion_Pieza.modificarDevolucion_Pieza(devolucion.getId(), devolucion);
								}else{
									//NO EXISTE DEVOLUCION
									Devolucion_PiezaDTO devolucion = new Devolucion_PiezaDTO();
									devolucion.setNumero_remito(modificado.getDevolucion_pieza().getNumero_remito());
									devolucion.setNumero_retiro(modificado.getDevolucion_pieza().getNumero_retiro());
									devolucion.setTransporte(modificado.getDevolucion_pieza().getTransporte());
									if(modificado.getDevolucion_pieza().getFecha_devolucion()!=null){
										devolucion.setFecha_devolucion(modificado.getDevolucion_pieza().getFecha_devolucion());
									}else{
										devolucion.setFecha_devolucion(null);
									}
									devolucion.setId(iControlDevolucion_Pieza.agregarDevolucion_Pieza(devolucion));

									original.setDevolucion_pieza(devolucion);
								}
							}else{
								original.setDevolucion_pieza(null);
							}
							//FECHA ENVIO AGENTE
							if(modificado.getFecha_envio_agente()!=null){
								original.setFecha_envio_agente(modificado.getFecha_envio_agente());
							}else{
								original.setFecha_envio_agente(null);
							}
							//FECHA RECEPCION AGENTE
							if(modificado.getFecha_recepcion_agente()!=null){
								original.setFecha_recepcion_agente(modificado.getFecha_recepcion_agente());
							}else{
								original.setFecha_recepcion_agente(null);
							}
							//FECHA RECEPCION FABRICA
							if(modificado.getFecha_recepcion_fabrica()!=null){
								original.setFecha_recepcion_fabrica(modificado.getFecha_recepcion_fabrica());
							}else{
								original.setFecha_recepcion_fabrica(null);
							}
							//FECHA SOLICITUD FABRICA
							if(modificado.getFecha_solicitud_fabrica()!=null){
								original.setFecha_solicitud_fabrica(modificado.getFecha_solicitud_fabrica());
							}else{
								original.setFecha_solicitud_fabrica(null);
							}
							//MULETO
							if(modificado.getMuleto()!=null){
								if (original.getMuleto()!=null){
									//EXISTE MULETO
									MuletoDTO muleto = original.getMuleto();
									
									muleto.setDescripcion(modificado.getMuleto().getDescripcion());
									muleto.setVin(modificado.getMuleto().getVin());
		
									iControlMuelto.modificarMuleto(muleto.getId(), muleto);
								}else{
									//NO EXISTE MULETO
									MuletoDTO muleto = new MuletoDTO();
									muleto.setDescripcion(modificado.getMuleto().getDescripcion());
									muleto.setVin(modificado.getMuleto().getVin());
									muleto.setPedido(original.getPedido());
									muleto.setPieza(original.getPieza());
									muleto.setId(iControlMuelto.agregarMuleto(muleto));

									original.setMuleto(muleto);
								}
							}else{
								original.setMuleto(null);
							}
							//NUMERO PEDIDOO
							original.setNumero_pedido(modificado.getNumero_pedido());
							//PIEZA USADA
							if(modificado.getPieza_usada()!=null)
								original.setPieza_usada(modificado.getPieza_usada());
							//PNC
							original.setPnc(modificado.getPnc());
							//ESTADO PEDIDO
							if(original.getDevolucion_pieza()!=null){
								original.setEstado_pedido("ENVIADO A FABRICA");
								ReclamoDTO reclamo = pedido.getReclamo();
								reclamo.setEstado_reclamo("CERRADO");
								iControlReclamo.modificarReclamo(reclamo.getId(), reclamo);
							}else{
								if(original.getFecha_recepcion_agente()!=null){
									original.setEstado_pedido("SIN ENVIAR A FABRICA");
								}else{
									if(original.getFecha_envio_agente()!=null){
										original.setEstado_pedido("EN ESPERA DE RECEPCION AGENTE");
									}else{
										if(original.getFecha_recepcion_fabrica()!=null){
											original.setEstado_pedido("SIN ENVIAR A AGENTE");
										}else{
											if(original.getFecha_solicitud_fabrica()!=null){
												original.setEstado_pedido("EN ESPERA DE RECEPCION FABRICA");
											}else{
												original.setEstado_pedido("SIN SOLICITUD A FABRICA");
											}
										}
									}
								}
							}
							//PIEZA
							PiezaDTO pieza = original.getPieza();
							pieza.setDescripcion(modificado.getPieza().getDescripcion());
							pieza.setNumero_pieza(modificado.getPieza().getNumero_pieza());
							pieza.setProveedor(modificado.getPieza().getProveedor());
							iControlPieza.modificarPieza(pieza.getId(), pieza);
							original.setPieza(pieza);
							iControlPedido_Pieza.modificarPedido_Pieza(original.getId(), original);
							break;
						}
					}
				}
				pedidos_piezasDTO = iControlPedido_Pieza.obtenerPedido_Pieza(pedido);
				boolean cerrado = true;
				boolean sinsolicitud = false;
				for(int i=0;i<pedidos_piezasDTO.size();i++){
					cerrado &= pedidos_piezasDTO.elementAt(i).getEstado_pedido().equals("CERRADO");
					sinsolicitud |= pedidos_piezasDTO.elementAt(i).getEstado_pedido().equals("SIN SOLICITUD A FABRICA");
				}
				
				ReclamoDTO reclamo = pedido.getReclamo();
				if(sinsolicitud){
					if (reclamo.getFecha_turno()!=null){
						reclamo.setEstado_reclamo("ABIERTO/SIN PEDIDO/CON TURNO");
					}else{
						reclamo.setEstado_reclamo("ABIERTO/SIN PEDIDO/SIN TURNO");
					}
				}else{
					if (reclamo.getFecha_turno()!=null){
						reclamo.setEstado_reclamo("ABIERTO/CON PEDIDO/CON TURNO");
					}else{
						reclamo.setEstado_reclamo("ABIERTO/CON PEDIDO/SIN TURNO");
					}
				}
				iControlReclamo.modificarReclamo(reclamo.getId(), reclamo);
				if(cerrado){
					reclamo.setEstado_reclamo("CERRADO");
					iControlReclamo.modificarReclamo(reclamo.getId(), reclamo);
				}
				res = true;
			}
		} catch (Exception e) {
			System.out.println("Error al modificar pedido en la clase MediadorPedido");
			e.printStackTrace();
		}
		return res;
	}

	public boolean modificarPedidoEntidad(PedidoDTO pedido,	Vector<Pedido_PiezaDTO> pedidos_piezas) {
		boolean res = false;
		IControlPedido iControlPedido = MediadorAccionesIniciarPrograma.getControlPedido();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlBdg iControlBdg = MediadorAccionesIniciarPrograma.getControlBdg();
		IControlMuleto iControlMuelto = MediadorAccionesIniciarPrograma.getControlMuleto();
		IControlPieza iControlPieza = MediadorAccionesIniciarPrograma.getControlPieza();
		IControlMano_Obra iControlMano_Obra = MediadorAccionesIniciarPrograma.getControlMano_Obra();
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		try {
			IControlDevolucion_Pieza iControlDevolucion_Pieza = MediadorAccionesIniciarPrograma.getControlDevolucion_Pieza();

			if (iControlPedido.existePedido(pedido.getId())){
				Vector<Pedido_PiezaDTO> pedidos_piezasDTO = iControlPedido_Pieza.obtenerPedido_Pieza(pedido);
				for(int i=0; i<pedidos_piezasDTO.size();i++){
					for(int j=0; j<pedidos_piezas.size();j++){
						if(pedidos_piezasDTO.elementAt(i).getId().equals(pedidos_piezas.elementAt(j).getId())){
							
							Pedido_PiezaDTO original = pedidos_piezasDTO.elementAt(i);
							Pedido_PiezaDTO modificado = pedidos_piezas.elementAt(j);

							//BDG
							if(modificado.getBdg()!=null){
								if (original.getBdg()!=null){
									//EXISTE BDG
									BdgDTO bdg = original.getBdg();
									bdg.setNumero_bdg(modificado.getBdg().getNumero_bdg());
									if(modificado.getBdg().getFecha_bdg()!=null){
										bdg.setFecha_bdg(modificado.getBdg().getFecha_bdg());
									}else{
										bdg.setFecha_bdg(null);
									}
									iControlBdg.modificarBdg(bdg.getId(), bdg);
									//NO SE DEBERIAN CAMBIAR LA PIEZA Y EL PEDIDO
								}else{
									//NO EXISTE BDG
									BdgDTO bdg = new BdgDTO();
									bdg.setNumero_bdg(modificado.getBdg().getNumero_bdg());
									if(modificado.getBdg().getFecha_bdg()!=null){
										bdg.setFecha_bdg(modificado.getBdg().getFecha_bdg());
									}else{
										bdg.setFecha_bdg(null);
									}
									bdg.setPedido(original.getPedido());
									bdg.setPieza(original.getPieza());
									bdg.setId(iControlBdg.agregarBdg(bdg));

									original.setBdg(bdg);
								}
							}else{
								original.setBdg(null);
							}
							//FECHA CAMBIO
							if(modificado.getFecha_cambio()!=null){
								original.setFecha_cambio(modificado.getFecha_cambio());
							}else{
								original.setFecha_cambio(null);
							}
							//DEVOLUCION PIEZA
							//FECHA SOLICITUD DEVOLUCION
							if(modificado.getFecha_solicitud_devolucion()!=null){
								original.setFecha_solicitud_devolucion(modificado.getFecha_solicitud_devolucion());
							}else{
								original.setFecha_solicitud_devolucion(null);
							}
							//FECHA APROBACION SOLICITUD DEVOLUCION
							if(modificado.getFecha_aprobacion_solicitud_devolucion()!=null){
								original.setFecha_aprobacion_solicitud_devolucion(modificado.getFecha_aprobacion_solicitud_devolucion());
							}else{
								original.setFecha_aprobacion_solicitud_devolucion(null);
							}
							if(modificado.getDevolucion_pieza()!=null){
								if (original.getDevolucion_pieza()!=null){
									//EXISTE DEVOLUCION
									Devolucion_PiezaDTO devolucion = original.getDevolucion_pieza();
									devolucion.setNumero_remito(modificado.getDevolucion_pieza().getNumero_remito());
									devolucion.setNumero_retiro(modificado.getDevolucion_pieza().getNumero_retiro());
									devolucion.setTransporte(modificado.getDevolucion_pieza().getTransporte());
									if(modificado.getDevolucion_pieza().getFecha_devolucion()!=null){
										devolucion.setFecha_devolucion(modificado.getDevolucion_pieza().getFecha_devolucion());
									}else{
										devolucion.setFecha_devolucion(null);
									}
									iControlDevolucion_Pieza.modificarDevolucion_Pieza(devolucion.getId(), devolucion);
								}else{
									//NO EXISTE DEVOLUCION
									Devolucion_PiezaDTO devolucion = new Devolucion_PiezaDTO();
									devolucion.setNumero_remito(modificado.getDevolucion_pieza().getNumero_remito());
									devolucion.setNumero_retiro(modificado.getDevolucion_pieza().getNumero_retiro());
									devolucion.setTransporte(modificado.getDevolucion_pieza().getTransporte());
									if(modificado.getDevolucion_pieza().getFecha_devolucion()!=null){
										devolucion.setFecha_devolucion(modificado.getDevolucion_pieza().getFecha_devolucion());
									}else{
										devolucion.setFecha_devolucion(null);
									}
									devolucion.setId(iControlDevolucion_Pieza.agregarDevolucion_Pieza(devolucion));

									original.setDevolucion_pieza(devolucion);
								}
							}else{
								original.setDevolucion_pieza(null);
							}

							//FECHA RECEPCION FABRICA
							if(modificado.getFecha_recepcion_fabrica()!=null){
								original.setFecha_recepcion_fabrica(modificado.getFecha_recepcion_fabrica());
							}else{
								original.setFecha_recepcion_fabrica(null);
							}
							//FECHA SOLICITUD FABRICA
							if(modificado.getFecha_solicitud_fabrica()!=null){
								original.setFecha_solicitud_fabrica(modificado.getFecha_solicitud_fabrica());
							}else{
								original.setFecha_solicitud_fabrica(null);
							}
							//MULETO
							if(modificado.getMuleto()!=null){
								if (original.getMuleto()!=null){
									//EXISTE MULETO
									MuletoDTO muleto = original.getMuleto();
									
									muleto.setDescripcion(modificado.getMuleto().getDescripcion());
									muleto.setVin(modificado.getMuleto().getVin());
		
									iControlMuelto.modificarMuleto(muleto.getId(), muleto);
								}else{
									//NO EXISTE MULETO
									MuletoDTO muleto = new MuletoDTO();
									muleto.setDescripcion(modificado.getMuleto().getDescripcion());
									muleto.setVin(modificado.getMuleto().getVin());
									muleto.setPedido(original.getPedido());
									muleto.setPieza(original.getPieza());
									muleto.setId(iControlMuelto.agregarMuleto(muleto));

									original.setMuleto(muleto);
								}
							}else{
								original.setMuleto(null);
							}
							//NUMERO PEDIDOO
							original.setNumero_pedido(modificado.getNumero_pedido());
							//PROPIO
							if(modificado.getPropio()!=null)
								original.setPropio(modificado.getPropio());
							//STOCK
							if(modificado.getStock()!=null)
								original.setStock(modificado.getStock());
							//PNC
							original.setPnc(modificado.getPnc());
							
							//MANO OBRA
							if(modificado.getMano_obra()!=null){
								if (original.getMano_obra()!=null){
									//EXISTE MANO OBRA
									Mano_ObraDTO mano_obra = original.getMano_obra();
									mano_obra.setCantidad_horas(modificado.getMano_obra().getCantidad_horas());
									mano_obra.setValor_mano_obra(modificado.getMano_obra().getValor_mano_obra());
									mano_obra.setCodigo_mano_obra(modificado.getMano_obra().getCodigo_mano_obra());
									iControlMano_Obra.modificarMano_Obra(mano_obra.getId(), mano_obra);
								}else{
									//NO EXISTE MANO OBRA
									Mano_ObraDTO mano_obra = new Mano_ObraDTO();
									mano_obra.setCantidad_horas(modificado.getMano_obra().getCantidad_horas());
									mano_obra.setValor_mano_obra(modificado.getMano_obra().getValor_mano_obra());
									mano_obra.setCodigo_mano_obra(modificado.getMano_obra().getCodigo_mano_obra());
									mano_obra.setId(iControlMano_Obra.agregarMano_Obra(mano_obra));

									original.setMano_obra(mano_obra);
								}
							}else{
								original.setMano_obra(null);
							}
							
							//ESTADO PEDIDO
							if(original.getDevolucion_pieza()!=null){
								original.setEstado_pedido("ENVIADO A FABRICA");
							}else{
								if(original.getFecha_recepcion_fabrica()!=null){
									original.setEstado_pedido("SIN ENVIAR A FABRICA");
								}else{
									if(original.getFecha_solicitud_fabrica()!=null){
										original.setEstado_pedido("EN ESPERA DE RECEPCION FABRICA");
									}else{
										original.setEstado_pedido("SIN SOLICITUD A FABRICA");										
									}
								}
							}
							//PIEZA
							PiezaDTO pieza = original.getPieza();
							pieza.setDescripcion(modificado.getPieza().getDescripcion());
							pieza.setNumero_pieza(modificado.getPieza().getNumero_pieza());
							pieza.setProveedor(modificado.getPieza().getProveedor());
							iControlPieza.modificarPieza(pieza.getId(), pieza);
							original.setPieza(pieza);
							iControlPedido_Pieza.modificarPedido_Pieza(original.getId(), original);
							break;
						}
					}
				}
				pedidos_piezasDTO = iControlPedido_Pieza.obtenerPedido_Pieza(pedido);
				boolean cerrado = true;
				boolean sinsolicitud = false;
				for(int i=0;i<pedidos_piezasDTO.size();i++){
					cerrado &= pedidos_piezasDTO.elementAt(i).getEstado_pedido().equals("CERRADO");
					sinsolicitud |= pedidos_piezasDTO.elementAt(i).getEstado_pedido().equals("SIN SOLICITUD A FABRICA");
				}
				
				ReclamoDTO reclamo = pedido.getReclamo();
				if(sinsolicitud){
					if (reclamo.getFecha_turno()!=null){
						reclamo.setEstado_reclamo("ABIERTO/SIN PEDIDO/CON TURNO");
					}else{
						reclamo.setEstado_reclamo("ABIERTO/SIN PEDIDO/SIN TURNO");
					}
				}else{
					if (reclamo.getFecha_turno()!=null){
						reclamo.setEstado_reclamo("ABIERTO/CON PEDIDO/CON TURNO");
					}else{
						reclamo.setEstado_reclamo("ABIERTO/CON PEDIDO/SIN TURNO");
					}
				}
				iControlReclamo.modificarReclamo(reclamo.getId(), reclamo);
				if(cerrado){
					reclamo.setEstado_reclamo("CERRADO");
					iControlReclamo.modificarReclamo(reclamo.getId(), reclamo);
				}
				res = true;
			}
		} catch (Exception e) {
			System.out.println("Error al modificar pedido en la clase MediadorPedido");
			e.printStackTrace();
		}
		return res;
	}

	public Vector<Pedido_PiezaDTO> obtenerPedidosPiezasEntidades() {
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		try {
			pedidos_piezas = iControlPedido_Pieza.obtenerPedido_PiezaEntidad();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedidos_piezas;
	}

	public Vector<Pedido_PiezaDTO> obtenerPedidos_PiezasAgente() {
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		try {
			pedidos_piezas = iControlPedido_Pieza.obtenerPedido_PiezaAgente();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedidos_piezas;
	}

}
