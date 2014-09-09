package servidor.GestionarPedido_Pieza_Reclamo_Fabrica;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.PedidoAssembler;
import servidor.assembler.Pedido_Pieza_Reclamo_FabricaAssembler;
import servidor.assembler.PiezaAssembler;
import servidor.assembler.Reclamo_FabricaAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Pedido_Pieza_Reclamo_Fabrica;

import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_Pieza_Reclamo_FabricaDTO;
import common.DTOs.PiezaDTO;
import common.DTOs.Reclamo_FabricaDTO;
import common.GestionarPedido_Pieza_Reclamo_Fabrica.IControlPedido_Pieza_Reclamo_Fabrica;

public class ControlPedido_Pieza_Reclamo_Fabrica extends UnicastRemoteObject
		implements IControlPedido_Pieza_Reclamo_Fabrica {

	private static final long serialVersionUID = 1L;

	public ControlPedido_Pieza_Reclamo_Fabrica() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarPedido_Pieza_Reclamo_Fabrica(Pedido_Pieza_Reclamo_FabricaDTO pedido_Pieza_Reclamo_FabricaDTO)
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Pedido_Pieza_Reclamo_FabricaAssembler pedido_Pieza_Reclamo_FabricaAssemb = new Pedido_Pieza_Reclamo_FabricaAssembler(accesoBD);
			Pedido_Pieza_Reclamo_Fabrica pedido_Pieza_Reclamo_Fabrica = pedido_Pieza_Reclamo_FabricaAssemb.getPedido_Pieza_Reclamo_FabricaNuevo(pedido_Pieza_Reclamo_FabricaDTO);
			accesoBD.hacerPersistente(pedido_Pieza_Reclamo_Fabrica);
			id = pedido_Pieza_Reclamo_Fabrica.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarPedido_Pieza_Reclamo_Fabrica(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			Pedido_Pieza_Reclamo_FabricaAssembler pedido_Pieza_Reclamo_FabricaAssemb = new Pedido_Pieza_Reclamo_FabricaAssembler(accesoBD);
			Pedido_Pieza_Reclamo_Fabrica pedido_Pieza_Reclamo_Fabrica = pedido_Pieza_Reclamo_FabricaAssemb.getPedido_Pieza_Reclamo_Fabrica(buscarPedido_Pieza_Reclamo_Fabrica(id));
			accesoBD.eliminar(pedido_Pieza_Reclamo_Fabrica);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarPedido_Pieza_Reclamo_Fabrica(Long id,Pedido_Pieza_Reclamo_FabricaDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			Pedido_Pieza_Reclamo_FabricaAssembler pedido_Pieza_Reclamo_FabricaAssemb = new Pedido_Pieza_Reclamo_FabricaAssembler(accesoBD);
			Pedido_Pieza_Reclamo_Fabrica pedido_Pieza_Reclamo_Fabrica = pedido_Pieza_Reclamo_FabricaAssemb.getPedido_Pieza_Reclamo_Fabrica(buscarPedido_Pieza_Reclamo_Fabrica(id));

			PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
			pedido_Pieza_Reclamo_Fabrica.setPedido(pedidoAssemb.getPedido(modificado.getPedido()));
			PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
			pedido_Pieza_Reclamo_Fabrica.setPieza(piezaAssemb.getPieza(modificado.getPieza()));
			Reclamo_FabricaAssembler reclamo_FrabricaAssemb = new Reclamo_FabricaAssembler(accesoBD);
			pedido_Pieza_Reclamo_Fabrica.setReclamo_fabrica(reclamo_FrabricaAssemb.getReclamo_Fabrica(modificado.getReclamo_fabrica()));

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<Pedido_Pieza_Reclamo_FabricaDTO> obtenerPedido_Pieza_Reclamo_Fabrica()
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_Pieza_Reclamo_FabricaDTO> pedidos_Pieza_Reclamo_FabricaDTO = new Vector<Pedido_Pieza_Reclamo_FabricaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Pedido_Pieza_Reclamo_Fabrica> pedidos_Pieza_Reclamo_Fabrica = new Vector<Pedido_Pieza_Reclamo_Fabrica> (accesoBD.buscarPorFiltro(Pedido_Pieza_Reclamo_Fabrica.class,""));
			for (int i = 0; i < pedidos_Pieza_Reclamo_Fabrica.size(); i++) {

				Pedido_Pieza_Reclamo_FabricaDTO pedido_Pieza_Reclamo_FabricaDTO = new Pedido_Pieza_Reclamo_FabricaDTO();

				pedido_Pieza_Reclamo_FabricaDTO.setId(pedidos_Pieza_Reclamo_Fabrica.elementAt(i).getId());
				PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
				pedido_Pieza_Reclamo_FabricaDTO.setPedido(pedidoAssemb.getPedidoDTO(pedidos_Pieza_Reclamo_Fabrica.elementAt(i).getPedido()));
				PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
				pedido_Pieza_Reclamo_FabricaDTO.setPieza(piezaAssemb.getPiezaDTO(pedidos_Pieza_Reclamo_Fabrica.elementAt(i).getPieza()));
				Reclamo_FabricaAssembler reclamo_FrabricaAssemb = new Reclamo_FabricaAssembler(accesoBD);
				pedido_Pieza_Reclamo_FabricaDTO.setReclamo_fabrica(reclamo_FrabricaAssemb.getReclamo_FabricaDTO(pedidos_Pieza_Reclamo_Fabrica.elementAt(i).getReclamo_fabrica()));

				pedidos_Pieza_Reclamo_FabricaDTO.add(pedido_Pieza_Reclamo_FabricaDTO);

			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_Pieza_Reclamo_FabricaDTO;
	}

	@Override
	public Vector<Pedido_Pieza_Reclamo_FabricaDTO> obtenerPedido_Pieza_Reclamo_Fabrica(PiezaDTO piezaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_Pieza_Reclamo_FabricaDTO> pedidos_Pieza_Reclamo_FabricaDTO = new Vector<Pedido_Pieza_Reclamo_FabricaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pieza.id == "+piezaDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Pedido_Pieza_Reclamo_Fabrica.class, filtro);
			Pedido_Pieza_Reclamo_FabricaAssembler pedido_Pieza_Reclamo_FabricaAssemb = new Pedido_Pieza_Reclamo_FabricaAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				pedidos_Pieza_Reclamo_FabricaDTO.add(pedido_Pieza_Reclamo_FabricaAssemb.getPedido_Pieza_Reclamo_FabricaDTO((Pedido_Pieza_Reclamo_Fabrica)(movCol.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_Pieza_Reclamo_FabricaDTO;
	}

	@Override
	public Vector<Pedido_Pieza_Reclamo_FabricaDTO> obtenerPedido_Pieza_Reclamo_Fabrica(PedidoDTO pedidoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_Pieza_Reclamo_FabricaDTO> pedidos_Pieza_Reclamo_FabricaDTO = new Vector<Pedido_Pieza_Reclamo_FabricaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pedido.id == "+pedidoDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Pedido_Pieza_Reclamo_Fabrica.class, filtro);
			Pedido_Pieza_Reclamo_FabricaAssembler pedido_Pieza_Reclamo_FabricaAssemb = new Pedido_Pieza_Reclamo_FabricaAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				pedidos_Pieza_Reclamo_FabricaDTO.add(pedido_Pieza_Reclamo_FabricaAssemb.getPedido_Pieza_Reclamo_FabricaDTO((Pedido_Pieza_Reclamo_Fabrica)(movCol.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_Pieza_Reclamo_FabricaDTO;
	}

	@Override
	public boolean existePedido_Pieza_Reclamo_Fabrica(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Pedido_Pieza_Reclamo_Fabrica) accesoBD.buscarPorId(Pedido_Pieza_Reclamo_Fabrica.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existePedido_Pieza_Reclamo_Fabrica(PedidoDTO pedidoDTO, PiezaDTO piezaDTO, Reclamo_FabricaDTO reclamo_fabricaDTO)throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "pieza.id == "+piezaDTO.getId()+" && pedido.id == "+pedidoDTO.getId()+ " && reclamo_fabrica.id == "+reclamo_fabricaDTO.getId();
			movCol = accesoBD.buscarPorFiltro(Pedido_Pieza_Reclamo_Fabrica.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public Pedido_Pieza_Reclamo_FabricaDTO buscarPedido_Pieza_Reclamo_Fabrica(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Pedido_Pieza_Reclamo_FabricaDTO pedido_Pieza_Reclamo_FabricaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			Pedido_Pieza_Reclamo_FabricaAssembler pedido_Pieza_Reclamo_FabricaAssemb = new Pedido_Pieza_Reclamo_FabricaAssembler(accesoBD);
			pedido_Pieza_Reclamo_FabricaDTO = pedido_Pieza_Reclamo_FabricaAssemb.getPedido_Pieza_Reclamo_FabricaDTO((Pedido_Pieza_Reclamo_Fabrica) accesoBD.buscarPorId(Pedido_Pieza_Reclamo_Fabrica.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedido_Pieza_Reclamo_FabricaDTO;
	}

	@Override
	public Pedido_Pieza_Reclamo_FabricaDTO buscarPedido_Pieza_Reclamo_Fabrica(PedidoDTO pedidoDTO, PiezaDTO piezaDTO, Reclamo_FabricaDTO reclamo_fabricaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Pedido_Pieza_Reclamo_FabricaDTO pedido_Pieza_Reclamo_FabricaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pieza.id == "+piezaDTO.getId()+" && pedido.id == "+pedidoDTO.getId()+ " && reclamo_fabrica.id == "+reclamo_fabricaDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Pedido_Pieza_Reclamo_Fabrica.class, filtro);
			Pedido_Pieza_Reclamo_FabricaAssembler pedido_Pieza_Reclamo_FabricaAssemb = new Pedido_Pieza_Reclamo_FabricaAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				pedido_Pieza_Reclamo_FabricaDTO = pedido_Pieza_Reclamo_FabricaAssemb.getPedido_Pieza_Reclamo_FabricaDTO((Pedido_Pieza_Reclamo_Fabrica)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedido_Pieza_Reclamo_FabricaDTO;
	}

}