package servidor.GestionarMuleto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.MuletoAssembler;
import servidor.assembler.PedidoAssembler;
import servidor.assembler.PiezaAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Muleto;

import common.DTOs.MuletoDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.PiezaDTO;
import common.GestionarMuleto.IControlMuleto;

public class ControlMuleto extends UnicastRemoteObject implements
		IControlMuleto {

	private static final long serialVersionUID = 1L;

	public ControlMuleto() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarMuleto(MuletoDTO muletoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			MuletoAssembler muletoAssemb = new MuletoAssembler(accesoBD);
			Muleto muleto = muletoAssemb.getMuletoNuevo(muletoDTO);
			accesoBD.hacerPersistente(muleto);
			id = muleto.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarMuleto(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			MuletoAssembler muletoAssemb = new MuletoAssembler(accesoBD);
			Muleto muleto = muletoAssemb.getMuleto(buscarMuleto(id));
			accesoBD.eliminar(muleto);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarMuleto(Long id, MuletoDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			MuletoAssembler muletoAssemb = new MuletoAssembler(accesoBD);
			Muleto muleto = muletoAssemb.getMuleto(buscarMuleto(id));

			muleto.setVin(modificado.getVin());
			muleto.setDescripcion(modificado.getDescripcion());
			PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
			muleto.setPedido(pedidoAssemb.getPedido(modificado.getPedido()));
			PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
			muleto.setPieza(piezaAssemb.getPieza(modificado.getPieza()));

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<MuletoDTO> obtenerMuleto() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<MuletoDTO> muletosDTO = new Vector<MuletoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Muleto> muletos = new Vector<Muleto> (accesoBD.buscarPorFiltro(Muleto.class, ""));
			for (int i = 0; i < muletos.size(); i++) {
				MuletoDTO muletoDTO = new MuletoDTO();
				muletoDTO.setId(muletos.elementAt(i).getId());
				muletoDTO.setVin(muletos.elementAt(i).getVin());
				muletoDTO.setDescripcion(muletos.elementAt(i).getDescripcion());
				PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
				muletoDTO.setPedido(pedidoAssemb.getPedidoDTO(muletos.elementAt(i).getPedido()));
				PiezaAssembler piezaAssembler = new PiezaAssembler(accesoBD);
				muletoDTO.setPieza(piezaAssembler.getPiezaDTO(muletos.elementAt(i).getPieza()));
				muletosDTO.add(muletoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return muletosDTO;
	}

	@Override
	public Vector<MuletoDTO> obtenerMuleto(PedidoDTO pedidoDTO)
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<MuletoDTO> muletosDTO = new Vector<MuletoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pedido.id =="+pedidoDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Muleto.class, filtro);
			MuletoAssembler muletoAssemb = new MuletoAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				muletosDTO.add(muletoAssemb.getMuletoDTO((Muleto)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return muletosDTO;
	}

	@Override
	public boolean existeMuleto(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Muleto) accesoBD.buscarPorId(Muleto.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeMuleto(PedidoDTO pedidoDTO, PiezaDTO piezaDTO)	throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pedido.id =="+pedidoDTO.getId()+" && pieza.id == "+piezaDTO.getId();
			movCol = accesoBD.buscarPorFiltro(Muleto.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public MuletoDTO buscarMuleto(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		MuletoDTO muletoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			MuletoAssembler muletoAssemb = new MuletoAssembler(accesoBD);
			muletoDTO = muletoAssemb.getMuletoDTO((Muleto) accesoBD.buscarPorId(Muleto.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return muletoDTO;
	}

	@Override
	public MuletoDTO buscarMuleto(PedidoDTO pedidoDTO, PiezaDTO piezaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		MuletoDTO muletoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pieza.id == "+piezaDTO.getId()+" && pedido.id =="+ pedidoDTO.getId();
			Collection muletos = accesoBD.buscarPorFiltro(Muleto.class, filtro);
			MuletoAssembler muletoAssemb = new MuletoAssembler(accesoBD);
			if (muletos.size()>=1 ) {
				muletoDTO = muletoAssemb.getMuletoDTO((Muleto)(muletos.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return muletoDTO;
	}
}