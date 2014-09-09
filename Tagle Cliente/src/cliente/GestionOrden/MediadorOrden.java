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
package cliente.GestionOrden;

import java.sql.Date;
import java.util.Vector;

import cliente.MediadorAccionesIniciarPrograma;
import cliente.MediadorPrincipal;

import common.DTOs.OrdenDTO;
import common.DTOs.Orden_ReclamoDTO;
import common.DTOs.ReclamoDTO;
import common.DTOs.RecursoDTO;
import common.GestionarOrden.IControlOrden;
import common.GestionarOrden_Reclamo.IControlOrden_Reclamo;
import common.GestionarReclamo.IControlReclamo;
import common.GestionarRecurso.IControlRecurso;

public class MediadorOrden {
	
	private MediadorPrincipal mediadorPrincipal;
	
	private GUIAltaOrden guiAltaOrden;
	private GUIGestionarOrden guigestionOrden;
	private GUIModificarOrden guiModificarOrden;


	public MediadorOrden(MediadorPrincipal mediadorPrincipal){
		this.setMediadorPrincipal(mediadorPrincipal);
	}
	
	public void altaOrden(){
		guiAltaOrden = new GUIAltaOrden(this);
		guiAltaOrden.setVisible(true);
	}
	public boolean nuevaOrden(Date fecha_apertura, String numero_orden) {
		boolean res= false;
		try {
			IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
			OrdenDTO orden = new OrdenDTO();
			orden.setNumero_orden(numero_orden);
			orden.setEstado("SIN RECLAMO");
			orden.setFecha_apertura(fecha_apertura);
			iControlOrden.agregarOrden(orden);
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public void gestionarOrden() {
		guigestionOrden = new GUIGestionarOrden(this);
		guigestionOrden.setVisible(true);
	}
	public void modificarOrden(Long id) {
		IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
		try {
			OrdenDTO orden = iControlOrden.buscarOrden(id);
			guiModificarOrden = new GUIModificarOrden(this, orden);
			guiModificarOrden.setVisible(true);
			
		} catch (Exception e) {
			System.out.println("Error al modifcar orden");
			e.printStackTrace();
		}
		
	}
	public boolean eliminarOrden(Long id) {
		boolean res = false;
		IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
		try {
			iControlOrden.eliminarOrden(id);
			res = true;
		} catch (Exception e) {
			System.out.println("Error al eliminar orden");
			e.printStackTrace();
		}
		return res;
	}

	public Vector<OrdenDTO> obtenerOrdenes() {
		Vector<OrdenDTO> ordenes = new Vector<OrdenDTO>();
		IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
		try {
			ordenes = iControlOrden.obtenerOrdenes();
		} catch (Exception e) {
			System.out.println("Error al obtener ordenes");
			e.printStackTrace();
		}
		return ordenes;
	}
	public Vector<RecursoDTO> obtenerRecursos() {
		Vector<RecursoDTO> recursos = new Vector<RecursoDTO>();
		IControlRecurso iControlRecurso = MediadorAccionesIniciarPrograma.getControlRecurso();
		try {
			recursos = iControlRecurso.obtenerRecursos();
		} catch (Exception e) {
			System.out.println("Error al obtener recursos");
			e.printStackTrace();
		}
		return recursos;
	}
	public Vector<ReclamoDTO> obtenerReclamos() {
		Vector<ReclamoDTO> reclamos = new Vector<ReclamoDTO>();
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		try {
			reclamos = iControlReclamo.obtenerReclamos();
		} catch (Exception e) {
			System.out.println("Error al obtener recursos");
			e.printStackTrace();
		}
		return reclamos;
	}

	public ReclamoDTO obtenerReclamo(Long id_orden) {
		ReclamoDTO reclamoDTO = null;
		IControlOrden_Reclamo iControlOrden_Reclamo = MediadorAccionesIniciarPrograma.getControlOrden_Reclamo(); 
		try {
			Orden_ReclamoDTO orden_reclamo = iControlOrden_Reclamo.buscarOrden_Reclamo_Por_Orden(id_orden);
			if(orden_reclamo!=null){
				reclamoDTO = orden_reclamo.getReclamo();
			}
		} catch (Exception e) {
			System.out.println("Error al buscar reclamo");
			e.printStackTrace();
		}	
		return reclamoDTO;
	}

	public RecursoDTO obtenerRecurso(String num_recurso) {
		RecursoDTO recursoDTO = null;
		IControlRecurso iControlRecurso = MediadorAccionesIniciarPrograma.getControlRecurso();
		try {
			recursoDTO = iControlRecurso.buscarRecurso(num_recurso);
		} catch (Exception e) {
			System.out.println("Error al buscar recurso");
			e.printStackTrace();
		}	
		return recursoDTO;
	}
	
	public void actualizarDatosGestion() {
		if (guigestionOrden!=null)
			guigestionOrden.actualizarDatos();
	}

	public boolean modificarOrden(OrdenDTO orden) {
		IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		boolean res = false;
		try {
//			OrdenDTO ordenDTO = iControlOrden.buscarOrden(orden.getId());
//			
//			ordenDTO.setFecha_apertura(orden.getFecha_apertura());
//			if (orden.getFecha_cierre()!=null)
//				ordenDTO.setFecha_cierre(orden.getFecha_cierre());
//			if (orden.getRecurso()!=null)
//				ordenDTO.setRecurso(orden.getRecurso());
//			ordenDTO.setEstado(orden.getEstado());
//			ordenDTO.setNumero_orden(orden.getNumero_orden());
			if (orden.getEstado()=="CERRADA"){
				Vector <ReclamoDTO> reclamos = iControlReclamo.obtenerReclamos();
				for (int i =0; i<reclamos.size();i++){
					if (reclamos.elementAt(i).getOrden().getId().equals(orden.getId())){
						reclamos.elementAt(i).setEstado_reclamo("CERRADO");
						iControlReclamo.modificarReclamo(reclamos.elementAt(i).getId(),reclamos.elementAt(i));
						break;
					}
				}
			}
			iControlOrden.modificarOrden(orden.getId(), orden);
			res = true;
			
		} catch (Exception e) {
			System.out.println("Error al modifcar orden");
			e.printStackTrace();
		}
		return res;
	}

//	public void setRecurso(String id_recurso) {
//		IControlRecurso iControlRecurso = MediadorAccionesIniciarPrograma.getControlRecurso();
//		try {
//			RecursoDTO recurso = iControlRecurso.buscarRecurso(new Long(id_recurso));
//			if (guigestionOrden!=null){
//				guigestionOrden.setRecurso(recurso);
//			}
//		} catch (Exception e) {
//			System.out.println("Error al buscar recurso");
//			e.printStackTrace();
//		}
//	}

	public MediadorPrincipal getMediadorPrincipal() {
		return mediadorPrincipal;
	}

	public void setMediadorPrincipal(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}

	public RecursoDTO nuevoRecurso(String numero_recurso, java.sql.Date fechaRecurso) {
		RecursoDTO recurso = null;
		try {
			IControlRecurso iControlRecurso = MediadorAccionesIniciarPrograma.getControlRecurso();
			
			recurso = new RecursoDTO();
			recurso.setFecha_recurso(fechaRecurso);
			recurso.setNumero_recurso(numero_recurso);
			
			recurso.setId(iControlRecurso.agregarRecurso(recurso));
		} catch (Exception e) {
			System.out.println("Error al agregar recurso");
			e.printStackTrace();
		}
		return recurso;
	}
	
	public void eliminarRecurso(Long id_recurso) {
		try {
			IControlRecurso iControlRecurso = MediadorAccionesIniciarPrograma.getControlRecurso();
			iControlRecurso.eliminarRecurso(id_recurso);
		} catch (Exception e) {
			System.out.println("Error al eliminar recurso");
			e.printStackTrace();
		}
	}

	public Vector<Orden_ReclamoDTO> obtenerOrdenes_Reclamos() {
		Vector<Orden_ReclamoDTO> ordenes_reclamos = new Vector<Orden_ReclamoDTO>();
		IControlOrden_Reclamo iControlOrdenReclamo = MediadorAccionesIniciarPrograma.getControlOrden_Reclamo();
		try {
			ordenes_reclamos = iControlOrdenReclamo.obtenerOrdenes_Reclamos();
		} catch (Exception e) {
			System.out.println("Error al obtener ordenes reclamos");
			e.printStackTrace();
		}
		return ordenes_reclamos;
	}


}