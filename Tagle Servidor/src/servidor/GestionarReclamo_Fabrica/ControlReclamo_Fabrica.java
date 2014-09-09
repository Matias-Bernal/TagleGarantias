package servidor.GestionarReclamo_Fabrica;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.PedidoAssembler;
import servidor.assembler.PiezaAssembler;
import servidor.assembler.Reclamo_FabricaAssembler;
import servidor.assembler.UsuarioAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Reclamo_Fabrica;

import common.DTOs.PedidoDTO;
import common.DTOs.PiezaDTO;
import common.DTOs.Reclamo_FabricaDTO;
import common.GestionarReclamo_Fabrica.IControlReclamo_Fabrica;

public class ControlReclamo_Fabrica extends UnicastRemoteObject implements IControlReclamo_Fabrica {

	private static final long serialVersionUID = 1L;

	public ControlReclamo_Fabrica() throws RemoteException {
		super();
	}

	@Override
	public Long agregarReclamo_FabricaDTO(Reclamo_FabricaDTO reclamo_fabricaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Reclamo_FabricaAssembler reclamo_FabricaAssemb = new Reclamo_FabricaAssembler(accesoBD);
			Reclamo_Fabrica reclamo_Fabrica = reclamo_FabricaAssemb.getReclamo_FabricaNuevo(reclamo_fabricaDTO);
			accesoBD.hacerPersistente(reclamo_Fabrica);
			id = reclamo_Fabrica.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarReclamo_FabricaDTO(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			Reclamo_FabricaAssembler reclamo_FabricaAssemb = new Reclamo_FabricaAssembler(accesoBD);
			Reclamo_Fabrica reclamo_Fabrica = reclamo_FabricaAssemb.getReclamo_Fabrica(buscarReclamo_FabricaDTO(id));
			accesoBD.eliminar(reclamo_Fabrica);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarReclamo_Fabrica(Long id, Reclamo_FabricaDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			Reclamo_FabricaAssembler reclamo_FabricaAssemb = new Reclamo_FabricaAssembler(accesoBD);
			Reclamo_Fabrica reclamo_Fabrica = reclamo_FabricaAssemb.getReclamo_Fabrica(buscarReclamo_FabricaDTO(id));
			
			reclamo_Fabrica.setFecha_reclamo_fabrica(modificado.getFecha_reclamo_fabrica());
			reclamo_Fabrica.setDescripcion(modificado.getDescripcion());
			PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
			reclamo_Fabrica.setPedido(pedidoAssemb.getPedido(modificado.getPedido()));
			PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
			reclamo_Fabrica.setPieza(piezaAssemb.getPieza(modificado.getPieza()));
			UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
			reclamo_Fabrica.setUsuario(usuarioAssemb.getUsuario(modificado.getUsuario()));
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<Reclamo_FabricaDTO> obtenerReclamo_Fabrica() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Reclamo_FabricaDTO> reclamos_fabricaDTO = new Vector<Reclamo_FabricaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Reclamo_Fabrica> reclamos_fabrica = new Vector<Reclamo_Fabrica>( accesoBD.buscarPorFiltro(Reclamo_Fabrica.class, ""));
			for (int i = 0; i < reclamos_fabrica.size(); i++) {
				Reclamo_FabricaDTO reclamo_FabricaDTO = new Reclamo_FabricaDTO();

				reclamo_FabricaDTO.setId(reclamos_fabrica.elementAt(i).getId());
				reclamo_FabricaDTO.setFecha_reclamo_fabrica(reclamos_fabrica.elementAt(i).getFecha_reclamo_fabrica());
				reclamo_FabricaDTO.setDescripcion(reclamos_fabrica.elementAt(i).getDescripcion());
				PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
				reclamo_FabricaDTO.setPedido(pedidoAssemb.getPedidoDTO(reclamos_fabrica.elementAt(i).getPedido()));
				PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
				reclamo_FabricaDTO.setPieza(piezaAssemb.getPiezaDTO(reclamos_fabrica.elementAt(i).getPieza()));
				UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
				reclamo_FabricaDTO.setUsuario(usuarioAssemb.getUsuarioDTO(reclamos_fabrica.elementAt(i).getUsuario()));

				reclamos_fabricaDTO.add(reclamo_FabricaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamos_fabricaDTO;
	}

	@Override
	public Vector<Reclamo_FabricaDTO> obtenerReclamo_Fabrica(PedidoDTO pedidoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Reclamo_FabricaDTO> reclamos_fabricaDTO = new Vector<Reclamo_FabricaDTO>();
		try {
			accesoBD.iniciarTransaccion();

			String filtro = "pedido.id == "+pedidoDTO.getId();
			Collection reclamos_fabrica = accesoBD.buscarPorFiltro(Reclamo_Fabrica.class, filtro);
			Reclamo_FabricaAssembler reclamofabricaAssemb = new Reclamo_FabricaAssembler(accesoBD);
			for (int i = 0; i < reclamos_fabrica.size(); i++) {
				reclamos_fabricaDTO.add(reclamofabricaAssemb.getReclamo_FabricaDTO((Reclamo_Fabrica)(reclamos_fabrica.toArray())[i]));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamos_fabricaDTO;
	}

	@Override
	public Vector<Reclamo_FabricaDTO> obtenerReclamo_Fabrica(Date fecha_reclamo) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Reclamo_FabricaDTO> reclamos_fabricaDTO = new Vector<Reclamo_FabricaDTO>();
		try {
			accesoBD.iniciarTransaccion();

			Collection reclamos_fabrica =  accesoBD.obtenerObjetosFecha(Reclamo_Fabrica.class, fecha_reclamo.getYear(),fecha_reclamo.getMonth(), fecha_reclamo.getDay());
			Reclamo_FabricaAssembler reclamofabricaAssemb = new Reclamo_FabricaAssembler(accesoBD);
			for (int i = 0; i < reclamos_fabrica.size(); i++) {
				reclamos_fabricaDTO.add(reclamofabricaAssemb.getReclamo_FabricaDTO((Reclamo_Fabrica)(reclamos_fabrica.toArray())[i]));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamos_fabricaDTO;
	}

	@Override
	public Vector<Reclamo_FabricaDTO> obtenerReclamo_Fabrica(PiezaDTO piezaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Reclamo_FabricaDTO> reclamos_fabricaDTO = new Vector<Reclamo_FabricaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "pieza.id == "+piezaDTO.getId();
			Collection reclamos_fabrica = accesoBD.buscarPorFiltro(Reclamo_Fabrica.class, filtro);
			Reclamo_FabricaAssembler reclamofabricaAssemb = new Reclamo_FabricaAssembler(accesoBD);
			for (int i = 0; i < reclamos_fabrica.size(); i++) {
				reclamos_fabricaDTO.add(reclamofabricaAssemb.getReclamo_FabricaDTO((Reclamo_Fabrica)(reclamos_fabrica.toArray())[i]));
			}
				
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamos_fabricaDTO;
	}

	@Override
	public boolean existeReclamo_Fabrica(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Reclamo_Fabrica) accesoBD.buscarPorId(Reclamo_Fabrica.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeReclamo_Fabrica(PedidoDTO pedidoDTO, PiezaDTO piezaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection reclamos_fabrica = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pieza.id == "+piezaDTO.getId()+" && pedido.id =="+ pedidoDTO.getId();
			reclamos_fabrica = accesoBD.buscarPorFiltro(Reclamo_Fabrica.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (reclamos_fabrica != null && reclamos_fabrica.size()>=1);
	}

	@Override
	public Reclamo_FabricaDTO buscarReclamo_FabricaDTO(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Reclamo_FabricaDTO reclamo_FabricaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			Reclamo_FabricaAssembler reclamo_fabricaAssemb = new Reclamo_FabricaAssembler(accesoBD);
			reclamo_FabricaDTO = reclamo_fabricaAssemb.getReclamo_FabricaDTO((Reclamo_Fabrica) accesoBD.buscarPorId(Reclamo_Fabrica.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamo_FabricaDTO;
	}

	@Override
	public Reclamo_FabricaDTO buscarReclamo_FabricaDTO(PedidoDTO pedidoDTO, PiezaDTO piezaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Reclamo_FabricaDTO reclamo_FabricaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pieza.id == "+piezaDTO.getId()+" && pedido.id =="+ pedidoDTO.getId();
			Collection reclamos_fabrica = accesoBD.buscarPorFiltro(Reclamo_Fabrica.class, filtro);
			Reclamo_FabricaAssembler reclamo_fabricaAssemb = new Reclamo_FabricaAssembler(accesoBD);
			if (reclamos_fabrica.size()>=1 ) {
				reclamo_FabricaDTO = reclamo_fabricaAssemb.getReclamo_FabricaDTO((Reclamo_Fabrica)(reclamos_fabrica.toArray()[0]));
			}		
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamo_FabricaDTO;
	}

}