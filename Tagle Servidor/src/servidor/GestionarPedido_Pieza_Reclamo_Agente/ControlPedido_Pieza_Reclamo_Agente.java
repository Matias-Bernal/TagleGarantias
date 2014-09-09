package servidor.GestionarPedido_Pieza_Reclamo_Agente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.PedidoAssembler;
import servidor.assembler.Pedido_Pieza_Reclamo_AgenteAssembler;
import servidor.assembler.PiezaAssembler;
import servidor.assembler.Reclamo_AgenteAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Pedido_Pieza_Reclamo_Agente;
import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_Pieza_Reclamo_AgenteDTO;
import common.DTOs.Pedido_Pieza_Reclamo_AgenteDTO;
import common.DTOs.PiezaDTO;
import common.DTOs.Reclamo_AgenteDTO;
import common.GestionarPedido_Pieza_Reclamo_Agente.IControlPedido_Pieza_Reclamo_Agente;
import common.GestionarPedido_Pieza_Reclamo_Agente.IControlPedido_Pieza_Reclamo_Agente;

public class ControlPedido_Pieza_Reclamo_Agente extends UnicastRemoteObject implements IControlPedido_Pieza_Reclamo_Agente {

	private static final long serialVersionUID = 1L;

	public ControlPedido_Pieza_Reclamo_Agente() throws RemoteException {
		super();
	}

	@Override
	public Long agregarPedido_Pieza_Reclamo_Agente(Pedido_Pieza_Reclamo_AgenteDTO pedido_Pieza_Reclamo_AgenteDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Pedido_Pieza_Reclamo_AgenteAssembler pedido_Pieza_Reclamo_AgenteAssemb = new Pedido_Pieza_Reclamo_AgenteAssembler(accesoBD);
			Pedido_Pieza_Reclamo_Agente pedido_Pieza_Reclamo_Agente = pedido_Pieza_Reclamo_AgenteAssemb.getPedido_Pieza_Reclamo_AgenteNuevo(pedido_Pieza_Reclamo_AgenteDTO);
			accesoBD.hacerPersistente(pedido_Pieza_Reclamo_Agente);
			id = pedido_Pieza_Reclamo_Agente.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}
	
	@Override
	public void eliminarPedido_Pieza_Reclamo_Agente(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			Pedido_Pieza_Reclamo_AgenteAssembler pedido_Pieza_Reclamo_AgenteAssemb = new Pedido_Pieza_Reclamo_AgenteAssembler(accesoBD);
			Pedido_Pieza_Reclamo_Agente pedido_Pieza_Reclamo_Agente = pedido_Pieza_Reclamo_AgenteAssemb.getPedido_Pieza_Reclamo_Agente(buscarPedido_Pieza_Reclamo_Agente(id));
			accesoBD.eliminar(pedido_Pieza_Reclamo_Agente);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}
	
	@Override
	public void modificarPedido_Pieza_Reclamo_Agente(Long id,Pedido_Pieza_Reclamo_AgenteDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			Pedido_Pieza_Reclamo_AgenteAssembler pedido_Pieza_Reclamo_AgenteAssemb = new Pedido_Pieza_Reclamo_AgenteAssembler(accesoBD);
			Pedido_Pieza_Reclamo_Agente pedido_Pieza_Reclamo_Agente = pedido_Pieza_Reclamo_AgenteAssemb.getPedido_Pieza_Reclamo_Agente(buscarPedido_Pieza_Reclamo_Agente(id));
		
			PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
			pedido_Pieza_Reclamo_Agente.setPedido(pedidoAssemb.getPedido(modificado.getPedido()));
			PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
			pedido_Pieza_Reclamo_Agente.setPieza(piezaAssemb.getPieza(modificado.getPieza()));
			Reclamo_AgenteAssembler reclamo_FrabricaAssemb = new Reclamo_AgenteAssembler(accesoBD);
			pedido_Pieza_Reclamo_Agente.setReclamo_agente(reclamo_FrabricaAssemb.getReclamo_Agente(modificado.getReclamo_agente()));
		
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}
	
	@Override
	public Vector<Pedido_Pieza_Reclamo_AgenteDTO> obtenerPedido_Pieza_Reclamo_Agente() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_Pieza_Reclamo_AgenteDTO> pedidos_Pieza_Reclamo_AgenteDTO = new Vector<Pedido_Pieza_Reclamo_AgenteDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Pedido_Pieza_Reclamo_Agente> pedidos_Pieza_Reclamo_Agente = new Vector<Pedido_Pieza_Reclamo_Agente> (accesoBD.buscarPorFiltro(Pedido_Pieza_Reclamo_Agente.class,""));
			for (int i = 0; i < pedidos_Pieza_Reclamo_Agente.size(); i++) {
		
				Pedido_Pieza_Reclamo_AgenteDTO pedido_Pieza_Reclamo_AgenteDTO = new Pedido_Pieza_Reclamo_AgenteDTO();
		
				pedido_Pieza_Reclamo_AgenteDTO.setId(pedidos_Pieza_Reclamo_Agente.elementAt(i).getId());
				PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
				pedido_Pieza_Reclamo_AgenteDTO.setPedido(pedidoAssemb.getPedidoDTO(pedidos_Pieza_Reclamo_Agente.elementAt(i).getPedido()));
				PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
				pedido_Pieza_Reclamo_AgenteDTO.setPieza(piezaAssemb.getPiezaDTO(pedidos_Pieza_Reclamo_Agente.elementAt(i).getPieza()));
				Reclamo_AgenteAssembler reclamo_FrabricaAssemb = new Reclamo_AgenteAssembler(accesoBD);
				pedido_Pieza_Reclamo_AgenteDTO.setReclamo_agente(reclamo_FrabricaAssemb.getReclamo_AgenteDTO(pedidos_Pieza_Reclamo_Agente.elementAt(i).getReclamo_agente()));
		
				pedidos_Pieza_Reclamo_AgenteDTO.add(pedido_Pieza_Reclamo_AgenteDTO);
		
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_Pieza_Reclamo_AgenteDTO;
	}

	@Override
	public Vector<Pedido_Pieza_Reclamo_AgenteDTO> obtenerPedido_Pieza_Reclamo_Agente(PiezaDTO piezaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_Pieza_Reclamo_AgenteDTO> pedidos_Pieza_Reclamo_AgenteDTO = new Vector<Pedido_Pieza_Reclamo_AgenteDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pieza.id == "+piezaDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Pedido_Pieza_Reclamo_Agente.class, filtro);
			Pedido_Pieza_Reclamo_AgenteAssembler pedido_Pieza_Reclamo_AgenteAssemb = new Pedido_Pieza_Reclamo_AgenteAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				pedidos_Pieza_Reclamo_AgenteDTO.add(pedido_Pieza_Reclamo_AgenteAssemb.getPedido_Pieza_Reclamo_AgenteDTO((Pedido_Pieza_Reclamo_Agente)(movCol.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_Pieza_Reclamo_AgenteDTO;
	}
	
	@Override
	public Vector<Pedido_Pieza_Reclamo_AgenteDTO> obtenerPedido_Pieza_Reclamo_Agente(PedidoDTO pedidoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_Pieza_Reclamo_AgenteDTO> pedidos_Pieza_Reclamo_AgenteDTO = new Vector<Pedido_Pieza_Reclamo_AgenteDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pedido.id == "+pedidoDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Pedido_Pieza_Reclamo_Agente.class, filtro);
			Pedido_Pieza_Reclamo_AgenteAssembler pedido_Pieza_Reclamo_AgenteAssemb = new Pedido_Pieza_Reclamo_AgenteAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				pedidos_Pieza_Reclamo_AgenteDTO.add(pedido_Pieza_Reclamo_AgenteAssemb.getPedido_Pieza_Reclamo_AgenteDTO((Pedido_Pieza_Reclamo_Agente)(movCol.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_Pieza_Reclamo_AgenteDTO;
	}
	
	@Override
	public boolean existePedido_Pieza_Reclamo_Agente(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Pedido_Pieza_Reclamo_Agente) accesoBD.buscarPorId(Pedido_Pieza_Reclamo_Agente.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}
	
	@Override
	public boolean existePedido_Pieza_Reclamo_Agente(PedidoDTO pedidoDTO, PiezaDTO piezaDTO, Reclamo_AgenteDTO reclamo_AgenteDTO)throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "pieza.id == "+piezaDTO.getId()+" && pedido.id == "+pedidoDTO.getId()+ " && reclamo_Agente.id == "+reclamo_AgenteDTO.getId();
			movCol = accesoBD.buscarPorFiltro(Pedido_Pieza_Reclamo_Agente.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}
	
	@Override
	public Pedido_Pieza_Reclamo_AgenteDTO buscarPedido_Pieza_Reclamo_Agente(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Pedido_Pieza_Reclamo_AgenteDTO pedido_Pieza_Reclamo_AgenteDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			Pedido_Pieza_Reclamo_AgenteAssembler pedido_Pieza_Reclamo_AgenteAssemb = new Pedido_Pieza_Reclamo_AgenteAssembler(accesoBD);
			pedido_Pieza_Reclamo_AgenteDTO = pedido_Pieza_Reclamo_AgenteAssemb.getPedido_Pieza_Reclamo_AgenteDTO((Pedido_Pieza_Reclamo_Agente) accesoBD.buscarPorId(Pedido_Pieza_Reclamo_Agente.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedido_Pieza_Reclamo_AgenteDTO;
	}
	
	@Override
	public Pedido_Pieza_Reclamo_AgenteDTO buscarPedido_Pieza_Reclamo_Agente(PedidoDTO pedidoDTO, PiezaDTO piezaDTO, Reclamo_AgenteDTO reclamo_AgenteDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Pedido_Pieza_Reclamo_AgenteDTO pedido_Pieza_Reclamo_AgenteDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pieza.id == "+piezaDTO.getId()+" && pedido.id == "+pedidoDTO.getId()+ " && reclamo_Agente.id == "+reclamo_AgenteDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Pedido_Pieza_Reclamo_Agente.class, filtro);
			Pedido_Pieza_Reclamo_AgenteAssembler pedido_Pieza_Reclamo_AgenteAssemb = new Pedido_Pieza_Reclamo_AgenteAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				pedido_Pieza_Reclamo_AgenteDTO = pedido_Pieza_Reclamo_AgenteAssemb.getPedido_Pieza_Reclamo_AgenteDTO((Pedido_Pieza_Reclamo_Agente)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedido_Pieza_Reclamo_AgenteDTO;
	}

}