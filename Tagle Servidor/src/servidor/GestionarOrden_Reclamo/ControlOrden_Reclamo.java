package servidor.GestionarOrden_Reclamo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.OrdenAssembler;
import servidor.assembler.Orden_ReclamoAssembler;
import servidor.assembler.ReclamoAssembler;
import servidor.assembler.RecursoAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Orden_Reclamo;
import common.DTOs.OrdenDTO;
import common.DTOs.Orden_ReclamoDTO;
import common.DTOs.ReclamoDTO;
import common.DTOs.RecursoDTO;
import common.GestionarOrden_Reclamo.IControlOrden_Reclamo;

public class ControlOrden_Reclamo extends UnicastRemoteObject implements IControlOrden_Reclamo {

	private static final long serialVersionUID = 1L;

	public ControlOrden_Reclamo() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarOrden_Reclamo(Orden_ReclamoDTO orden_ReclamoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Orden_ReclamoAssembler orden_ReclamoAssem = new Orden_ReclamoAssembler(accesoBD);
			Orden_Reclamo orden_Recurso = orden_ReclamoAssem.getOrden_ReclamoNuevo(orden_ReclamoDTO);
			accesoBD.hacerPersistente(orden_Recurso);
			id = orden_Recurso.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarOrden_Reclamo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			Orden_ReclamoAssembler orden_ReclamoAssem = new Orden_ReclamoAssembler(accesoBD);
			Orden_Reclamo orden_Recurso = orden_ReclamoAssem.getOrden_ReclamoNuevo(buscarOrden_Reclamo(id));			
			accesoBD.eliminar(orden_Recurso);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarOrden_Reclamo(Long id, Orden_ReclamoDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();

			Orden_ReclamoAssembler orden_ReclamoAssem = new Orden_ReclamoAssembler(accesoBD);
			Orden_Reclamo orden_Recurso = orden_ReclamoAssem.getOrden_ReclamoNuevo(buscarOrden_Reclamo(id));
			
			OrdenAssembler ordenAssemb = new OrdenAssembler(accesoBD);
			orden_Recurso.setOrden(ordenAssemb.getOrden(modificado.getOrden()));
			ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
			orden_Recurso.setReclamo(reclamoAssemb.getReclamo(modificado.getReclamo()));
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<Orden_ReclamoDTO> obtenerOrdenes_Reclamos() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Orden_ReclamoDTO> ordenes_ReclamosDTO = new Vector<Orden_ReclamoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Orden_Reclamo> ordenes_Reclamos = new Vector<Orden_Reclamo> (accesoBD.buscarPorFiltro(Orden_Reclamo.class, ""));
			for (int i = 0; i < ordenes_Reclamos.size(); i++) {
				Orden_ReclamoDTO orden_ReclamoDTO = new Orden_ReclamoDTO();
				
				orden_ReclamoDTO.setId(ordenes_Reclamos.elementAt(i).getId());
				OrdenAssembler ordenAssemb = new OrdenAssembler(accesoBD);
				orden_ReclamoDTO.setOrden(ordenAssemb.getOrdenDTO(ordenes_Reclamos.elementAt(i).getOrden()));
				ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
				orden_ReclamoDTO.setReclamo(reclamoAssemb.getReclamoDTO(ordenes_Reclamos.elementAt(i).getReclamo()));
	
				ordenes_ReclamosDTO.add(orden_ReclamoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return ordenes_ReclamosDTO;
	}

	@Override
	public Vector<Orden_ReclamoDTO> obtenerOrdenes_Reclamos(OrdenDTO ordenDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Orden_ReclamoDTO> ordenes_ReclamosDTO = new Vector<Orden_ReclamoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "orden.id == "+ordenDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Orden_Reclamo.class, filtro);
			Orden_ReclamoAssembler orden_ReclamoAssem = new Orden_ReclamoAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				ordenes_ReclamosDTO.add(orden_ReclamoAssem.getOrden_ReclamoDTO((Orden_Reclamo)(movCol.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return ordenes_ReclamosDTO;
	}

	@Override
	public Vector<Orden_ReclamoDTO> obtenerOrdenes_Reclamos(ReclamoDTO reclamoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Orden_ReclamoDTO> ordenes_ReclamosDTO = new Vector<Orden_ReclamoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "reclamo.id == "+reclamoDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Orden_Reclamo.class, filtro);
			Orden_ReclamoAssembler orden_ReclamoAssem = new Orden_ReclamoAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				ordenes_ReclamosDTO.add(orden_ReclamoAssem.getOrden_ReclamoDTO((Orden_Reclamo)(movCol.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return ordenes_ReclamosDTO;
	}

	@Override
	public boolean existeOrden_Reclamo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Orden_Reclamo) accesoBD.buscarPorId(Orden_Reclamo.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeOrden_Reclamo(OrdenDTO ordenDTO, ReclamoDTO reclamoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "orden.id == "+ordenDTO.getId()+ " && reclamo.id == "+reclamoDTO.getId();
			movCol = accesoBD.buscarPorFiltro(Orden_Reclamo.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public Orden_ReclamoDTO buscarOrden_Reclamo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Orden_ReclamoDTO orden_ReclamoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			Orden_ReclamoAssembler orden_ReclamoAssemb = new Orden_ReclamoAssembler(accesoBD);
			orden_ReclamoDTO = orden_ReclamoAssemb.getOrden_ReclamoDTO((Orden_Reclamo) accesoBD.buscarPorId(Orden_Reclamo.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return orden_ReclamoDTO;
	}

	@Override
	public Orden_ReclamoDTO buscarOrden_Reclamo(OrdenDTO ordenDTO, ReclamoDTO reclamoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Orden_ReclamoDTO orden_RecursoDTO = null;
		try {
			accesoBD.iniciarTransaccion();

			String filtro = "orden.id == "+ordenDTO.getId()+ " && reclamo.id == "+reclamoDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Orden_Reclamo.class, filtro);
			Orden_ReclamoAssembler orden_ReclamoAssemb = new Orden_ReclamoAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				orden_RecursoDTO = orden_ReclamoAssemb.getOrden_ReclamoDTO((Orden_Reclamo)(movCol.toArray()[0]));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return orden_RecursoDTO;
	}

	@Override
	public Orden_ReclamoDTO buscarOrden_Reclamo_Por_Orden(Long id_orden) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Orden_ReclamoDTO orden_ReclamoDTO = null;
		try {
			accesoBD.iniciarTransaccion();

			String filtro = "orden.id == "+id_orden.toString();
			Collection movCol = accesoBD.buscarPorFiltro(Orden_Reclamo.class, filtro);
			Orden_ReclamoAssembler orden_ReclamoAssemb = new Orden_ReclamoAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				orden_ReclamoDTO = orden_ReclamoAssemb.getOrden_ReclamoDTO((Orden_Reclamo)(movCol.toArray()[0]));
			}

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return orden_ReclamoDTO;
	}

	@Override
	public Orden_ReclamoDTO buscarOrden_Reclamo_Por_Reclamo(Long id_reclamo) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Orden_ReclamoDTO orden_ReclamoDTO = null;
		try {
			accesoBD.iniciarTransaccion();

			String filtro = "reclamo.id == "+id_reclamo.toString();
			Collection movCol = accesoBD.buscarPorFiltro(Orden_Reclamo.class, filtro);
			Orden_ReclamoAssembler orden_ReclamoAssemb = new Orden_ReclamoAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				orden_ReclamoDTO = orden_ReclamoAssemb.getOrden_ReclamoDTO((Orden_Reclamo)(movCol.toArray()[0]));
			}

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return orden_ReclamoDTO;
	}

}