package servidor.GestionarReclamo_Agente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.PedidoAssembler;
import servidor.assembler.PiezaAssembler;
import servidor.assembler.Reclamo_AgenteAssembler;
import servidor.assembler.Reclamo_FabricaAssembler;
import servidor.assembler.UsuarioAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Reclamo_Agente;
import servidor.persistencia.dominio.Reclamo_Fabrica;
import common.DTOs.PedidoDTO;
import common.DTOs.PiezaDTO;
import common.DTOs.Reclamo_AgenteDTO;
import common.DTOs.Reclamo_FabricaDTO;
import common.GestionarReclamo_Agente.IControlReclamo_Agente;
import common.GestionarReclamo_Fabrica.IControlReclamo_Fabrica;

public class ControlReclamo_Agente extends UnicastRemoteObject implements IControlReclamo_Agente {

	private static final long serialVersionUID = 1L;

	public ControlReclamo_Agente() throws RemoteException {
		super();
	}

	@Override
	public Long agregarReclamo_Agente(Reclamo_AgenteDTO reclamo_agenteDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Reclamo_AgenteAssembler reclamo_AgenteAssemb = new Reclamo_AgenteAssembler(accesoBD);
			Reclamo_Agente reclamo_Agente = reclamo_AgenteAssemb.getReclamo_AgenteNuevo(reclamo_agenteDTO);
			accesoBD.hacerPersistente(reclamo_Agente);
			id = reclamo_Agente.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarReclamo_Agente(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			Reclamo_AgenteAssembler reclamo_AgenteAssemb = new Reclamo_AgenteAssembler(accesoBD);
			Reclamo_Agente reclamo_Agente = reclamo_AgenteAssemb.getReclamo_Agente(buscarReclamo_Agente(id));
			accesoBD.eliminar(reclamo_Agente);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarReclamo_Agente(Long id, Reclamo_AgenteDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			Reclamo_AgenteAssembler reclamo_AgenteAssemb = new Reclamo_AgenteAssembler(accesoBD);
			Reclamo_Agente reclamo_Agente = reclamo_AgenteAssemb.getReclamo_Agente(buscarReclamo_Agente(id));
			
			reclamo_Agente.setFecha_reclamo_agente(modificado.getFecha_reclamo_agente());
			reclamo_Agente.setDescripcion(modificado.getDescripcion());
			PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
			reclamo_Agente.setPedido(pedidoAssemb.getPedido(modificado.getPedido()));
			PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
			reclamo_Agente.setPieza(piezaAssemb.getPieza(modificado.getPieza()));
			UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
			reclamo_Agente.setUsuario(usuarioAssemb.getUsuario(modificado.getUsuario()));
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<Reclamo_AgenteDTO> obtenerReclamo_Agente() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Reclamo_AgenteDTO> reclamos_agenteDTO = new Vector<Reclamo_AgenteDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Reclamo_Agente> reclamos_agente = new Vector<Reclamo_Agente>( accesoBD.buscarPorFiltro(Reclamo_Agente.class, ""));
			for (int i = 0; i < reclamos_agente.size(); i++) {
				Reclamo_AgenteDTO reclamo_AgenteDTO = new Reclamo_AgenteDTO();

				reclamo_AgenteDTO.setId(reclamos_agente.elementAt(i).getId());
				reclamo_AgenteDTO.setFecha_reclamo_agente(reclamos_agente.elementAt(i).getFecha_reclamo_agente());
				reclamo_AgenteDTO.setDescripcion(reclamos_agente.elementAt(i).getDescripcion());
				PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
				reclamo_AgenteDTO.setPedido(pedidoAssemb.getPedidoDTO(reclamos_agente.elementAt(i).getPedido()));
				PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
				reclamo_AgenteDTO.setPieza(piezaAssemb.getPiezaDTO(reclamos_agente.elementAt(i).getPieza()));
				UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
				reclamo_AgenteDTO.setUsuario(usuarioAssemb.getUsuarioDTO(reclamos_agente.elementAt(i).getUsuario()));

				reclamos_agenteDTO.add(reclamo_AgenteDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamos_agenteDTO;
	}

	@Override
	public Vector<Reclamo_AgenteDTO> obtenerReclamo_Agente(PedidoDTO pedidoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Reclamo_AgenteDTO> reclamos_agenteDTO = new Vector<Reclamo_AgenteDTO>();
		try {
			accesoBD.iniciarTransaccion();

			String filtro = "pedido.id == "+pedidoDTO.getId();
			Collection reclamos_agente = accesoBD.buscarPorFiltro(Reclamo_Agente.class, filtro);
			Reclamo_AgenteAssembler reclamoAgenteAssemb = new Reclamo_AgenteAssembler(accesoBD);
			for (int i = 0; i < reclamos_agente.size(); i++) {
				reclamos_agenteDTO.add(reclamoAgenteAssemb.getReclamo_AgenteDTO((Reclamo_Agente)(reclamos_agente.toArray())[i]));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamos_agenteDTO;
	}

	@Override
	public Vector<Reclamo_AgenteDTO> obtenerReclamo_Agente(Date fecha_reclamo) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Reclamo_AgenteDTO> reclamos_agenteDTO = new Vector<Reclamo_AgenteDTO>();
		try {
			accesoBD.iniciarTransaccion();

			Collection reclamos_agentes =  accesoBD.obtenerObjetosFecha(Reclamo_Agente.class, fecha_reclamo.getYear(),fecha_reclamo.getMonth(), fecha_reclamo.getDay());
			Reclamo_AgenteAssembler reclamoAgenteAssemb = new Reclamo_AgenteAssembler(accesoBD);
			for (int i = 0; i < reclamos_agentes.size(); i++) {
				reclamos_agenteDTO.add(reclamoAgenteAssemb.getReclamo_AgenteDTO((Reclamo_Agente)(reclamos_agentes.toArray())[i]));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamos_agenteDTO;
	}

	@Override
	public Vector<Reclamo_AgenteDTO> obtenerReclamo_Agente(PiezaDTO piezaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Reclamo_AgenteDTO> reclamos_agenteDTO = new Vector<Reclamo_AgenteDTO>();
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "pieza.id == "+piezaDTO.getId();
			Collection reclamos_agente = accesoBD.buscarPorFiltro(Reclamo_Agente.class, filtro);
			Reclamo_AgenteAssembler reclamoAgenteAssemb = new Reclamo_AgenteAssembler(accesoBD);
			for (int i = 0; i < reclamos_agente.size(); i++) {
				reclamos_agenteDTO.add(reclamoAgenteAssemb.getReclamo_AgenteDTO((Reclamo_Agente)(reclamos_agente.toArray())[i]));
			}
				
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamos_agenteDTO;
	}

	@Override
	public boolean existeReclamo_Agente(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Reclamo_Agente) accesoBD.buscarPorId(Reclamo_Agente.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeReclamo_Agente(PedidoDTO pedidoDTO, PiezaDTO piezaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection reclamos_agente = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pieza.id == "+piezaDTO.getId()+" && pedido.id =="+ pedidoDTO.getId();
			reclamos_agente = accesoBD.buscarPorFiltro(Reclamo_Agente.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (reclamos_agente != null && reclamos_agente.size()>=1);
	}

	@Override
	public Reclamo_AgenteDTO buscarReclamo_Agente(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Reclamo_AgenteDTO reclamo_AgenteDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			Reclamo_AgenteAssembler reclamo_AgenteAssemb = new Reclamo_AgenteAssembler(accesoBD);
			reclamo_AgenteDTO = reclamo_AgenteAssemb.getReclamo_AgenteDTO((Reclamo_Agente) accesoBD.buscarPorId(Reclamo_Agente.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamo_AgenteDTO;
	}

	@Override
	public Reclamo_AgenteDTO buscarReclamo_Agente(PedidoDTO pedidoDTO, PiezaDTO piezaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Reclamo_AgenteDTO reclamo_AgenteDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pieza.id == "+piezaDTO.getId()+" && pedido.id =="+ pedidoDTO.getId();
			Collection reclamos_agente = accesoBD.buscarPorFiltro(Reclamo_Agente.class, filtro);
			Reclamo_AgenteAssembler reclamo_agenteAssemb = new Reclamo_AgenteAssembler(accesoBD);
			if (reclamos_agente.size()>=1 ) {
				reclamo_AgenteDTO = reclamo_agenteAssemb.getReclamo_AgenteDTO((Reclamo_Agente)(reclamos_agente.toArray()[0]));
			}		
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamo_AgenteDTO;
	}

	@Override
	public Vector<Reclamo_AgenteDTO> obtenerReclamo_Agente(PedidoDTO pedidoDTO,	PiezaDTO piezaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Reclamo_AgenteDTO> reclamos_AgenteDTO = new Vector<Reclamo_AgenteDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pieza.id == "+piezaDTO.getId()+" && pedido.id =="+ pedidoDTO.getId();
			Vector<Reclamo_Agente> reclamos_agente = new Vector<Reclamo_Agente> (accesoBD.buscarPorFiltro(Reclamo_Agente.class, filtro));
			Reclamo_AgenteAssembler reclamo_agenteAssemb = new Reclamo_AgenteAssembler(accesoBD);
			for (int i = 0; i < reclamos_agente.size(); i++) {
				Reclamo_AgenteDTO reclamo_AgenteDTO = reclamo_agenteAssemb.getReclamo_AgenteDTO(reclamos_agente.elementAt(i));
				reclamos_AgenteDTO.add(reclamo_AgenteDTO);
			}		
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamos_AgenteDTO;
	}

}