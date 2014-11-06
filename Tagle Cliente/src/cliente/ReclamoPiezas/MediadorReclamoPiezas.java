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

import java.sql.Date;
import java.util.Vector;

import cliente.MediadorAccionesIniciarPrograma;
import cliente.MediadorPrincipal;
import common.DTOs.MTelefonoDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.Pedido_Pieza_Reclamo_AgenteDTO;
import common.DTOs.Pedido_Pieza_Reclamo_FabricaDTO;
import common.DTOs.PiezaDTO;
import common.DTOs.Reclamo_AgenteDTO;
import common.DTOs.Reclamo_FabricaDTO;
import common.DTOs.RegistranteDTO;
import common.GestionarAgente.IControlAgente;
import common.GestionarEntidad.IControlEntidad;
import common.GestionarMTelefono.IControlMTelefono;
import common.GestionarPedido_Pieza.IControlPedido_Pieza;
import common.GestionarPedido_Pieza_Reclamo_Agente.IControlPedido_Pieza_Reclamo_Agente;
import common.GestionarPedido_Pieza_Reclamo_Fabrica.IControlPedido_Pieza_Reclamo_Fabrica;
import common.GestionarReclamo_Agente.IControlReclamo_Agente;
import common.GestionarReclamo_Fabrica.IControlReclamo_Fabrica;

public class MediadorReclamoPiezas {
	
	private MediadorPrincipal mediadorPrincipal;
	
	private GUIReclamoPiezas guiReclamo;
	

	public MediadorReclamoPiezas(MediadorPrincipal mediadorPrincipal) {
		this.setMediadorPrincipal(mediadorPrincipal);
	}
	
	public void mostrarRepuesto(){
		guiReclamo = new GUIReclamoPiezas(this);
		guiReclamo.setVisible(true);
	}

	public MediadorPrincipal getMediadorPrincipal() {
		return mediadorPrincipal;
	}

	public void setMediadorPrincipal(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}

	public Vector<Pedido_PiezaDTO> obtenerPedido_Pieza_Agente() {
		Vector<Pedido_PiezaDTO> pedios_piezas = new Vector<Pedido_PiezaDTO>();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		try {
			pedios_piezas = iControlPedido_Pieza.obtenerPedido_PiezaReclamoAgente();
		} catch (Exception e) {
			System.out.println("Error al cargar los pedidos_piezas de agentes");
			e.printStackTrace();
		}
		return pedios_piezas;
	}

	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaFabrica() {
		Vector<Pedido_PiezaDTO> pedios_piezas = new Vector<Pedido_PiezaDTO>();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		try {
			pedios_piezas = iControlPedido_Pieza.obtenerPedido_PiezaReclamoFabrica();
		} catch (Exception e) {
			System.out.println("Error al cargar los pedidos_piezas");
			e.printStackTrace();
		}
		return pedios_piezas;
	}
	
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaEntidad() {
		Vector<Pedido_PiezaDTO> pedios_piezas = new Vector<Pedido_PiezaDTO>();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		try {
			pedios_piezas = iControlPedido_Pieza.obtenerPedido_PiezaEntidad();
		} catch (Exception e) {
			System.out.println("Error al cargar los pedidos_piezas");
			e.printStackTrace();
		}
		return pedios_piezas;
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

	public Integer cantidadReclamosFabrica(Pedido_PiezaDTO pedido_pieza) {
		Integer res = 0;
		IControlReclamo_Fabrica iControlReclamo_Fabrica = MediadorAccionesIniciarPrograma.getControlReclamo_Fabrica();
		try {
			Vector<Reclamo_FabricaDTO> reclamosDTO = iControlReclamo_Fabrica.obtenerReclamos_Fabrica(pedido_pieza.getPedido(), pedido_pieza.getPieza());
			res = reclamosDTO.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Integer cantidadReclamosAgente(Pedido_PiezaDTO pedido_pieza) {
		Integer res = 0;
		IControlReclamo_Agente iControlReclamo_Agente = MediadorAccionesIniciarPrograma.getControlReclamo_Agente();
		try {
			Vector<Reclamo_AgenteDTO> reclamosDTO = iControlReclamo_Agente.obtenerReclamo_Agente(pedido_pieza.getPedido(), pedido_pieza.getPieza());
			res = reclamosDTO.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Date obtenerUltimoReclamoFabrica(Pedido_PiezaDTO pedido_pieza) {
		Date fecha_ultimo_reclamoFabrica = null;
		IControlReclamo_Fabrica iControlReclamo_Fabrica = MediadorAccionesIniciarPrograma.getControlReclamo_Fabrica();
		try {
			Vector<Reclamo_FabricaDTO> reclamos = iControlReclamo_Fabrica.obtenerReclamos_Fabrica(pedido_pieza.getPedido(), pedido_pieza.getPieza());
			java.util.Date hoy = new java.util.Date();			
			for (int i = 0; i<reclamos.size();i++){
				if (fecha_ultimo_reclamoFabrica==null)
					fecha_ultimo_reclamoFabrica = reclamos.elementAt(i).getFecha_reclamo_fabrica();

				if(reclamos.elementAt(i).getFecha_reclamo_fabrica().compareTo(fecha_ultimo_reclamoFabrica)>=0);
					fecha_ultimo_reclamoFabrica = reclamos.elementAt(i).getFecha_reclamo_fabrica();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fecha_ultimo_reclamoFabrica;
	}
	
	public Date obtenerUltimoReclamoAgente(Pedido_PiezaDTO pedido_pieza) {
		Date fecha_ultimo_reclamoAgente = null;
		IControlReclamo_Agente iControlReclamo_Agente = MediadorAccionesIniciarPrograma.getControlReclamo_Agente();
		try {
			Vector<Reclamo_AgenteDTO> reclamos = iControlReclamo_Agente.obtenerReclamo_Agente(pedido_pieza.getPedido(), pedido_pieza.getPieza());
			java.util.Date hoy = new java.util.Date();
			
			for (int i = 0; i<reclamos.size();i++){
				if (fecha_ultimo_reclamoAgente==null)
					fecha_ultimo_reclamoAgente = reclamos.elementAt(i).getFecha_reclamo_agente();

				if(reclamos.elementAt(i).getFecha_reclamo_agente().compareTo(fecha_ultimo_reclamoAgente)>=0);
					fecha_ultimo_reclamoAgente = reclamos.elementAt(i).getFecha_reclamo_agente();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fecha_ultimo_reclamoAgente;
	}

	public String obtenerTelefono(Pedido_PiezaDTO pedido_pieza) {
		String tel = "";
		IControlMTelefono iControlMTelefono = MediadorAccionesIniciarPrograma.getControlMTelefono();
		Vector<MTelefonoDTO> telefonosDTO;
		try {
			telefonosDTO = iControlMTelefono.obtenerMTelefono(pedido_pieza.getPedido().getReclamo().getReclamante());
			if(telefonosDTO!=null)
				tel = telefonosDTO.elementAt(0).getTelefono();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tel;
	}

	public boolean guardarReclamoFabrica(Pedido_PiezaDTO pedido_pieza, String descripcion) {
		boolean res = false;
		try {
			IControlReclamo_Fabrica iControlReclamo_Fabrica = MediadorAccionesIniciarPrograma.getControlReclamo_Fabrica();
			Reclamo_FabricaDTO reclamo_fabricaDTO = new Reclamo_FabricaDTO();
			IControlPedido_Pieza_Reclamo_Fabrica iControlPedio_Pieza_Reclamo_Fabrica = MediadorAccionesIniciarPrograma.getControlPedido_Pieza_Reclamo_Fabrica();

			
			java.util.Date hoy = new java.util.Date();
		    java.sql.Date freclamo = new java.sql.Date(hoy.getTime());
		    
			reclamo_fabricaDTO.setFecha_reclamo_fabrica(freclamo);
			reclamo_fabricaDTO.setDescripcion(descripcion);
			reclamo_fabricaDTO.setUsuario(mediadorPrincipal.getUsuario());
			reclamo_fabricaDTO.setPedido(pedido_pieza.getPedido());
			reclamo_fabricaDTO.setPieza(pedido_pieza.getPieza());
			reclamo_fabricaDTO.setId(iControlReclamo_Fabrica.agregarReclamo_FabricaDTO(reclamo_fabricaDTO));
			
			Pedido_Pieza_Reclamo_FabricaDTO pedido_pieza_reclamo_fabrica = new Pedido_Pieza_Reclamo_FabricaDTO();
			pedido_pieza_reclamo_fabrica.setPedido(pedido_pieza.getPedido());
			pedido_pieza_reclamo_fabrica.setPieza(pedido_pieza.getPieza());
			pedido_pieza_reclamo_fabrica.setReclamo_fabrica(reclamo_fabricaDTO);
			
			iControlPedio_Pieza_Reclamo_Fabrica.agregarPedido_Pieza_Reclamo_Fabrica(pedido_pieza_reclamo_fabrica);
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		return res;
	}

	public Pedido_PiezaDTO buscarPedido_Pieza(Long id) {
		Pedido_PiezaDTO pedio_pieza = null;
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		try {
			Vector<Pedido_PiezaDTO> pedios_piezasDTO = iControlPedido_Pieza.obtenerPedido_Pieza();
			for (int i = 0; i<pedios_piezasDTO.size();i++){
				if (pedios_piezasDTO.elementAt(i).getId().equals(id)){
					pedio_pieza = pedios_piezasDTO.elementAt(i);
				}
			}
			
		} catch (Exception e) {
			System.out.println("Error al cargar los pedidos_piezas de agentes");
			e.printStackTrace();
		}
		return pedio_pieza;
	}

	public GUIReclamoPiezas getGuiReclamo() {
		return guiReclamo;
	}

	public void setGuiReclamo(GUIReclamoPiezas guiReclamo) {
		this.guiReclamo = guiReclamo;
	}

	public boolean guardarReclamoAgente(Pedido_PiezaDTO pedido_pieza, String descripcion) {
		boolean res = false;
		try {
			IControlReclamo_Agente iControlReclamo_Agente = MediadorAccionesIniciarPrograma.getControlReclamo_Agente();
			Reclamo_AgenteDTO reclamo_agenteDTO = new Reclamo_AgenteDTO();
			IControlPedido_Pieza_Reclamo_Agente iControlPedio_Pieza_Reclamo_Agente = MediadorAccionesIniciarPrograma.getControlPedido_Pieza_Reclamo_Agente();

			
			java.util.Date hoy = new java.util.Date();
		    java.sql.Date freclamo = new java.sql.Date(hoy.getTime());
		    
			reclamo_agenteDTO.setFecha_reclamo_agente(freclamo);
			reclamo_agenteDTO.setDescripcion(descripcion);
			reclamo_agenteDTO.setUsuario(mediadorPrincipal.getUsuario());
			reclamo_agenteDTO.setPedido(pedido_pieza.getPedido());
			reclamo_agenteDTO.setPieza(pedido_pieza.getPieza());
			reclamo_agenteDTO.setId(iControlReclamo_Agente.agregarReclamo_Agente(reclamo_agenteDTO));
			
			Pedido_Pieza_Reclamo_AgenteDTO pedido_pieza_reclamo_agente = new Pedido_Pieza_Reclamo_AgenteDTO();
			pedido_pieza_reclamo_agente.setPedido(pedido_pieza.getPedido());
			pedido_pieza_reclamo_agente.setPieza(pedido_pieza.getPieza());
			pedido_pieza_reclamo_agente.setReclamo_agente(reclamo_agenteDTO);
			
			iControlPedio_Pieza_Reclamo_Agente.agregarPedido_Pieza_Reclamo_Agente(pedido_pieza_reclamo_agente);
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		return res;
	}

	public Reclamo_FabricaDTO buscarReclamoFabrica(Long id) {
		Reclamo_FabricaDTO reclamo = null;
		try {
			IControlPedido_Pieza_Reclamo_Fabrica iControlPedio_Pieza_Reclamo_Fabrica = MediadorAccionesIniciarPrograma.getControlPedido_Pieza_Reclamo_Fabrica();

			reclamo = iControlPedio_Pieza_Reclamo_Fabrica.buscarPedido_Pieza_Reclamo_Fabrica(id).getReclamo_fabrica();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reclamo;
	}
	public Reclamo_AgenteDTO buscarReclamoAgente(Long id) {
		Reclamo_AgenteDTO reclamo = null;
		try {
			IControlPedido_Pieza_Reclamo_Agente iControlPedio_Pieza_Reclamo_Agente = MediadorAccionesIniciarPrograma.getControlPedido_Pieza_Reclamo_Agente();

			reclamo = iControlPedio_Pieza_Reclamo_Agente.buscarPedido_Pieza_Reclamo_Agente(id).getReclamo_agente();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reclamo;
	}
	public Vector<Pedido_Pieza_Reclamo_FabricaDTO> obtenerReclamoFabrica(Pedido_PiezaDTO pedido_pieza) {
		Vector<Pedido_Pieza_Reclamo_FabricaDTO> reclamos = new Vector<Pedido_Pieza_Reclamo_FabricaDTO>();
		try {
			IControlPedido_Pieza_Reclamo_Fabrica iControlPedio_Pieza_Reclamo_Fabrica = MediadorAccionesIniciarPrograma.getControlPedido_Pieza_Reclamo_Fabrica();
			Vector<Pedido_Pieza_Reclamo_FabricaDTO> reclamosDTO = iControlPedio_Pieza_Reclamo_Fabrica.obtenerPedido_Pieza_Reclamo_Fabrica();
			for (int i = 0; i< reclamosDTO.size();i++){
				if(reclamosDTO.elementAt(i).getPedido().getId().equals(pedido_pieza.getPedido().getId()) && reclamosDTO.elementAt(i).getPieza().getId().equals(pedido_pieza.getPieza().getId()))
					reclamos.add(reclamosDTO.elementAt(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reclamos;
	}
	public Vector<Pedido_Pieza_Reclamo_AgenteDTO> obtenerReclamoAgente(Pedido_PiezaDTO pedido_pieza) {
		Vector<Pedido_Pieza_Reclamo_AgenteDTO> reclamos = new Vector<Pedido_Pieza_Reclamo_AgenteDTO>();
		try {
			IControlPedido_Pieza_Reclamo_Agente iControlPedio_Pieza_Reclamo_Agente = MediadorAccionesIniciarPrograma.getControlPedido_Pieza_Reclamo_Agente();
			Vector<Pedido_Pieza_Reclamo_AgenteDTO> reclamosDTO = iControlPedio_Pieza_Reclamo_Agente.obtenerPedido_Pieza_Reclamo_Agente();
			for (int i = 0; i< reclamosDTO.size();i++){
				if(reclamosDTO.elementAt(i).getPedido().getId().equals(pedido_pieza.getPedido().getId()) && reclamosDTO.elementAt(i).getPieza().getId().equals(pedido_pieza.getPieza().getId()))
					reclamos.add(reclamosDTO.elementAt(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reclamos;
	}

	public void verReclamosFabrica(Long id) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		Pedido_PiezaDTO pedido_pieza;
		try {
			pedido_pieza = iControlPedido_Pieza.buscarPedido_Pieza(id);
			GUIVerReclamosFabrica guiVerReclamoFabrica = new GUIVerReclamosFabrica(this, pedido_pieza);
			guiVerReclamoFabrica.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void verReclamosAgente(Long id) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		Pedido_PiezaDTO pedido_pieza;
		try {
			pedido_pieza = iControlPedido_Pieza.buscarPedido_Pieza(id);
			GUIVerReclamosAgente guiVerReclamosAgente = new GUIVerReclamosAgente(this, pedido_pieza);
			guiVerReclamosAgente.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verReclamoFabrica(Reclamo_FabricaDTO reclamo_fabrica) {
			VerReclamoFabrica guiVerReclamoFabrica = new VerReclamoFabrica(this, reclamo_fabrica);
			guiVerReclamoFabrica.setVisible(true);
	}
	public void verReclamoAgente(Reclamo_AgenteDTO reclamo_agente) {
		VerReclamoAgente guiVerReclamoAgente = new VerReclamoAgente(this, reclamo_agente);
		guiVerReclamoAgente.setVisible(true);
	}

	public Pedido_PiezaDTO obtenerPedido_Pieza(PedidoDTO pedido, PiezaDTO pieza) {
		Pedido_PiezaDTO pedido_pieza = null;
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		try {
			pedido_pieza = iControlPedido_Pieza.buscarPedido_Pieza(pedido,pieza);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedido_pieza;
	}
	
	public void actualizarReclamosFabrica(){
		guiReclamo.actualizarReclamosFabrica();
	}
	public void actualizarReclamosAgente(){
		guiReclamo.actualizarReclamosAgente();
	}
}