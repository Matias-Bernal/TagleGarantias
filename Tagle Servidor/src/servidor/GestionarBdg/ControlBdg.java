package servidor.GestionarBdg;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.BdgAssembler;
import servidor.assembler.PedidoAssembler;
import servidor.assembler.PiezaAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Bdg;
import common.DTOs.BdgDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.PiezaDTO;
import common.GestionarBdg.IControlBdg;

public class ControlBdg extends UnicastRemoteObject implements IControlBdg {

	private static final long serialVersionUID = 1L;

	public ControlBdg() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarBdg(BdgDTO bdgDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
			Bdg bdg = bdgAssemb.getBdgNuevo(bdgDTO);
			accesoBD.hacerPersistente(bdg);
			id = bdg.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarBdg(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
			Bdg bdg = bdgAssemb.getBdg(buscarBdg(id));
			accesoBD.eliminar(bdg);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarBdg(Long id, BdgDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
			Bdg bdg = bdgAssemb.getBdg(buscarBdg(id));

			bdg.setFecha_bdg(modificado.getFecha_bdg());
			PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
			bdg.setPedido(pedidoAssemb.getPedido(modificado.getPedido()));
			PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
			bdg.setPieza(piezaAssemb.getPieza(modificado.getPieza()));
			bdg.setNumero_bdg(modificado.getNumero_bdg());
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<BdgDTO> obtenerBdgs() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<BdgDTO> bdgsDTO = new Vector<BdgDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Bdg> bdgs = new Vector<Bdg> (accesoBD.buscarPorFiltro(Bdg.class, ""));
			PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
			PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
			for (int i = 0; i < bdgs.size(); i++) {
				BdgDTO bdgDTO = new BdgDTO();
				
				bdgDTO.setId(bdgs.elementAt(i).getId());
				bdgDTO.setFecha_bdg(bdgs.elementAt(i).getFecha_bdg());
				bdgDTO.setPedido(pedidoAssemb.getPedidoDTO(bdgs.elementAt(i).getPedido()));
				bdgDTO.setPieza(piezaAssemb.getPiezaDTO(bdgs.elementAt(i).getPieza()));
				bdgDTO.setNumero_bdg(bdgs.elementAt(i).getNumero_bdg());

				bdgsDTO.add(bdgDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return bdgsDTO;
	}

	@Override
	public Vector<BdgDTO> obtenerBdgs(Date fecha_bdg) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<BdgDTO> bdgsDTO = new Vector<BdgDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings({ "rawtypes", "deprecation" })
			Collection bdgs =  accesoBD.obtenerObjetosFecha(Bdg.class, fecha_bdg.getYear(),fecha_bdg.getMonth(), fecha_bdg.getDay());
			BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
			for (int i = 0; i < bdgs.size(); i++) {
				bdgsDTO.add(bdgAssemb.getBdgDTO((Bdg)(bdgs.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return bdgsDTO;
	}

	@Override
	public Vector<BdgDTO> obtenerBdgs(PiezaDTO pieza) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<BdgDTO> bdgsDTO = new Vector<BdgDTO>();
		try {
			accesoBD.iniciarTransaccion();

			String filtro = "pieza.id == "+pieza.getId();
			@SuppressWarnings("rawtypes")
			Collection bdgs = accesoBD.buscarPorFiltro(Bdg.class, filtro);
			BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
			for (int i = 0; i < bdgs.size(); i++) {
				bdgsDTO.add(bdgAssemb.getBdgDTO((Bdg)(bdgs.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return bdgsDTO;
	}

	@Override
	public Vector<BdgDTO> obtenerBdgs(PedidoDTO pedido) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<BdgDTO> bdgsDTO = new Vector<BdgDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pedido.id == "+pedido.getId();
			@SuppressWarnings("rawtypes")
			Collection bdgs = accesoBD.buscarPorFiltro(Bdg.class, filtro);
			BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
			for (int i = 0; i < bdgs.size(); i++) {
				bdgsDTO.add(bdgAssemb.getBdgDTO((Bdg)(bdgs.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return bdgsDTO;
	}

	@Override
	public boolean existeBdg(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Bdg) accesoBD.buscarPorId(Bdg.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeBdg(PiezaDTO pieza, PedidoDTO pedido) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		@SuppressWarnings("rawtypes")
		Collection bdgs = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pieza.id == "+pieza.getId()+" && pedido.id =="+ pedido.getId();
			bdgs = accesoBD.buscarPorFiltro(Bdg.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (bdgs != null && bdgs.size()>=1);
	}

	@Override
	public boolean existeBdg(Date fecha_bdg, PiezaDTO pieza, PedidoDTO pedido) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		@SuppressWarnings("rawtypes")
		Collection bdgs = null;
		try {
			accesoBD.iniciarTransaccion();	
			@SuppressWarnings("deprecation")
			String filtro = "pieza.id == "+pieza.getId()+" && pedido.id == "+pedido.getId()+" && fecha_bdg.getYear() == "+fecha_bdg.getYear()+" && fecha_bdg.getMonth() == "+fecha_bdg.getMonth()+" && fecha_bdg.getDay() == "+fecha_bdg.getDay();
			bdgs = accesoBD.buscarPorFiltro(Bdg.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (bdgs != null && bdgs.size()>=1);
	}

	@Override
	public BdgDTO buscarBdg(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		BdgDTO bdgDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
			bdgDTO = bdgAssemb.getBdgDTO((Bdg) accesoBD.buscarPorId(Bdg.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return bdgDTO;
	}

	@Override
	public BdgDTO buscarBdg(PiezaDTO pieza, PedidoDTO pedido) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		BdgDTO bdgDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pieza.id == "+pieza.getId()+" && pedido.id =="+ pedido.getId();
			@SuppressWarnings("rawtypes")
			Collection bdgs = accesoBD.buscarPorFiltro(Bdg.class, filtro);
			BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
			if (bdgs.size()>=1 ) {
				bdgDTO = bdgAssemb.getBdgDTO((Bdg)(bdgs.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return bdgDTO;
	}

	@Override
	public BdgDTO buscarBdg(Date fecha_bdg, PiezaDTO pieza, PedidoDTO pedido) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		BdgDTO bdgDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("deprecation")
			String filtro = "pieza.id == "+pieza.getId()+" && pedido.id == "+pedido.getId()+" && fecha_bdg.getYear() == "+fecha_bdg.getYear()+" && fecha_bdg.getMonth() == "+fecha_bdg.getMonth()+" && fecha_bdg.getDay() == "+fecha_bdg.getDay();
			@SuppressWarnings("rawtypes")
			Collection bdgs = accesoBD.buscarPorFiltro(Bdg.class, filtro);
			BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
			if (bdgs.size()>=1 ) {
				bdgDTO = bdgAssemb.getBdgDTO((Bdg)(bdgs.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return bdgDTO;
	}

	@Override
	public boolean existeBdg(String numero_bdg) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		@SuppressWarnings("rawtypes")
		Collection bdgs = null;
		try {
			accesoBD.iniciarTransaccion();	

			String filtro = "numero_bdg.equals(\""+numero_bdg+"\")";
			bdgs = accesoBD.buscarPorFiltro(Bdg.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (bdgs != null && bdgs.size()>=1);
	}

	@Override
	public BdgDTO buscarBdg(String numero_bdg) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		BdgDTO bdgDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "numero_bdg.equals(\""+numero_bdg+"\")";
			Collection bdgs = accesoBD.buscarPorFiltro(Bdg.class, filtro);
			BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
			if (bdgs.size()>=1 ) {
				bdgDTO = bdgAssemb.getBdgDTO((Bdg)(bdgs.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return bdgDTO;
	}

}
