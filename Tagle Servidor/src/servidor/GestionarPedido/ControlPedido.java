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
package servidor.GestionarPedido;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.Collection;
import java.util.Vector;

import javax.jdo.Extent;
import javax.jdo.Query;

import servidor.assembler.PedidoAssembler;
import servidor.assembler.ReclamoAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Agente;
import servidor.persistencia.dominio.Entidad;
import servidor.persistencia.dominio.Pedido;
import servidor.persistencia.dominio.Pedido_Pieza;
import common.DTOs.PedidoDTO;
import common.DTOs.ReclamoDTO;
import common.GestionarPedido.IControlPedido;

public class ControlPedido extends UnicastRemoteObject implements IControlPedido {

	private static final long serialVersionUID = 1L;

	public ControlPedido() throws RemoteException {
		super();
	}

	@Override
	public Long agregarPedido(PedidoDTO pedidoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			PedidoAssembler pedidoAssem = new PedidoAssembler(accesoBD);
			Pedido pedido = pedidoAssem.getPedidoNuevo(pedidoDTO);
			accesoBD.hacerPersistente(pedido);
			id = pedido.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarPedido(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			PedidoAssembler pedidoAssem = new PedidoAssembler(accesoBD);
			Pedido pedido = pedidoAssem.getPedidoNuevo(buscarPedido(id));
			accesoBD.eliminar(pedido);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarPedido(Long id, PedidoDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			PedidoAssembler pedidoAssem = new PedidoAssembler(accesoBD);
			Pedido pedido = pedidoAssem.getPedidoNuevo(buscarPedido(id));
			
			pedido.setFecha_solicitud_pedido(modificado.getFecha_solicitud_pedido());
			ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
			pedido.setReclamo(reclamoAssemb.getReclamo(modificado.getReclamo()));

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<PedidoDTO> obtenerPedidos() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PedidoDTO> pedidosDTO = new Vector<PedidoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Pedido> pedidos = new Vector<Pedido> (accesoBD.buscarPorFiltro(Pedido.class, ""));
			for (int i = 0; i < pedidos.size(); i++) {
				PedidoDTO pedidoDTO = new PedidoDTO();
				
				pedidoDTO.setId(pedidos.elementAt(i).getId());
				pedidoDTO.setFecha_solicitud_pedido(pedidos.elementAt(i).getFecha_solicitud_pedido());
				ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
				pedidoDTO.setReclamo(reclamoAssemb.getReclamoDTO(pedidos.elementAt(i).getReclamo()));
				
				pedidosDTO.add(pedidoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidosDTO;
	}

	@Override
	public Vector<PedidoDTO> obtenerPedidos_Fecha_Solicitud_Pedido(Date fecha_solicitud_pedido) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PedidoDTO> pedidosDTO = new Vector<PedidoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol =  accesoBD.obtenerObjetosFecha(Pedido.class, fecha_solicitud_pedido.getYear(),fecha_solicitud_pedido.getMonth(), fecha_solicitud_pedido.getDay());
			PedidoAssembler pedidoAssem = new PedidoAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				pedidosDTO.add(pedidoAssem.getPedidoDTO((Pedido)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidosDTO;
	}

	@Override
	public Vector<PedidoDTO> obtenerPedidos(ReclamoDTO reclamoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PedidoDTO> pedidosDTO = new Vector<PedidoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "reclamo.id == "+reclamoDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Pedido.class, filtro);
			PedidoAssembler pedidoAssem = new PedidoAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				pedidosDTO.add(pedidoAssem.getPedidoDTO((Pedido)(movCol.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidosDTO;
	}

	@Override
	public boolean existePedido(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Pedido) accesoBD.buscarPorId(Pedido.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public PedidoDTO buscarPedido(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		PedidoDTO pedidoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			PedidoAssembler pedidoAssem = new PedidoAssembler(accesoBD);
			pedidoDTO = pedidoAssem.getPedidoDTO((Pedido) accesoBD.buscarPorId(Pedido.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidoDTO;
	}

	@Override
	public Vector<PedidoDTO> obtenerPedidosAgentes() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PedidoDTO> pedidosDTO = new Vector<PedidoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Extent e1 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection agentes = (Collection) q1.execute();	
			
			String filtro = "agentes.contains(reclamo.registrante)";       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido.class, false);
	        Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
	        
	        query.declareImports("import java.util.Collection");
	        query.declareParameters("Collection agentes");
			Collection c2 = (Collection) query.execute(agentes);
			
			Vector<Pedido> pedidos = new Vector<Pedido> (c2);
			PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
			for (int i = 0; i < pedidos.size(); i++) {
				PedidoDTO pedidoDTO = pedidoAssemb.getPedidoDTO(pedidos.elementAt(i));
				pedidosDTO.add(pedidoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidosDTO;
	}

	@Override
	public Vector<PedidoDTO> obtenerPedidosEntidades() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PedidoDTO> pedidosDTO = new Vector<PedidoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Extent e1 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection entidades = (Collection) q1.execute();	
			
			String filtro = "entidades.contains(reclamo.registrante)";       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido.class, false);
	        Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
	        
	        query.declareImports("import java.util.Collection");
	        query.declareParameters("Collection entidades");
			Collection c2 = (Collection) query.execute(entidades);
			
			Vector<Pedido> pedidos = new Vector<Pedido> (c2);
			PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
			for (int i = 0; i < pedidos.size(); i++) {
				PedidoDTO pedidoDTO = pedidoAssemb.getPedidoDTO(pedidos.elementAt(i));
				pedidosDTO.add(pedidoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidosDTO;
	}

	@Override
	public PedidoDTO buscarPedidoEntidad(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		PedidoDTO pedidoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Extent e1 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection entidades = (Collection) q1.execute();	
			
			String filtro = "entidades.contains(reclamo.registrante) && id=="+id.toString();       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido.class, false);
	        Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
	        
	        query.declareImports("import java.util.Collection");
	        query.declareParameters("Collection entidades");
			Collection c2 = (Collection) query.execute(entidades);
			
			PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
			
			pedidoDTO = pedidoAssemb.getPedidoDTO((Pedido) c2.toArray()[0]);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidoDTO;
	}

	@Override
	public PedidoDTO buscarPedidoAgente(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		PedidoDTO pedidoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Extent e1 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection agentes = (Collection) q1.execute();	
			
			String filtro = "agentes.contains(reclamo.registrante) && id=="+id.toString();       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido.class, false);
	        Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
	        
	        query.declareImports("import java.util.Collection");
	        query.declareParameters("Collection agentes");
			Collection c2 = (Collection) query.execute(agentes);
			
			PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
			
			pedidoDTO = pedidoAssemb.getPedidoDTO((Pedido) c2.toArray()[0]);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidoDTO;
	}

}