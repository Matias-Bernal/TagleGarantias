package servidor.GestionarOrden;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.OrdenAssembler;
import servidor.assembler.RecursoAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Orden;
import common.DTOs.OrdenDTO;
import common.DTOs.RecursoDTO;
import common.GestionarOrden.IControlOrden;

public class ControlOrden extends UnicastRemoteObject implements IControlOrden {

	private static final long serialVersionUID = 1L;

	public ControlOrden() throws RemoteException {
		super();
	}

	@Override
	public Long agregarOrden(OrdenDTO ordenDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			OrdenAssembler ordenAssem = new OrdenAssembler(accesoBD);
			Orden orden = ordenAssem.getOrdenNueva(ordenDTO);
			accesoBD.hacerPersistente(orden);
			id = orden.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarOrden(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			OrdenAssembler ordenAssem = new OrdenAssembler(accesoBD);
			Orden orden = ordenAssem.getOrden(buscarOrden(id));
			accesoBD.eliminar(orden);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarOrden(Long id, OrdenDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			OrdenAssembler ordenAssem = new OrdenAssembler(accesoBD);
			Orden orden = ordenAssem.getOrden(buscarOrden(id));

			orden.setEstado(modificado.getEstado());
			orden.setFecha_apertura(modificado.getFecha_apertura());
			orden.setFecha_cierre(modificado.getFecha_cierre());
			orden.setNumero_orden(modificado.getNumero_orden());
			
			RecursoAssembler recursoAssem = new RecursoAssembler(accesoBD);
			if (modificado.getRecurso()!=null){
				orden.setRecurso(recursoAssem.getRecurso(modificado.getRecurso()));
			}
		
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<OrdenDTO> obtenerOrdenes() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<OrdenDTO> ordenesDTO = new Vector<OrdenDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			OrdenAssembler ordenAssemb = new OrdenAssembler(accesoBD);
			Vector<Orden> ordenes = new Vector<Orden> (accesoBD.buscarPorFiltro(Orden.class, ""));
			for (int i = 0; i < ordenes.size(); i++) {
				OrdenDTO ordenDTO = ordenAssemb.getOrdenDTO(ordenes.elementAt(i));				
				ordenesDTO.add(ordenDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return ordenesDTO;
	}

	@Override
	public Vector<OrdenDTO> obtenerOrdenes_estado(String estado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<OrdenDTO> ordenesDTO = new Vector<OrdenDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "estado.equals(\""+estado+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Orden.class, filtro);
			OrdenAssembler ordenAssem = new OrdenAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				ordenesDTO.add(ordenAssem.getOrdenDTO((Orden)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return ordenesDTO;
	}


	@Override
	public Vector<OrdenDTO> obtenerOrdenes_fecha_apertura(Date fecha_apertura) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<OrdenDTO> ordenesDTO = new Vector<OrdenDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol =  accesoBD.obtenerObjetosFecha(Orden.class, fecha_apertura.getYear(),fecha_apertura.getMonth(), fecha_apertura.getDay());
			OrdenAssembler ordenAssem = new OrdenAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				ordenesDTO.add(ordenAssem.getOrdenDTO((Orden)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return ordenesDTO;
	}

	@Override
	public Vector<OrdenDTO> obtenerOrdenes_fecha_cierre(Date fecha_cierre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<OrdenDTO> ordenesDTO = new Vector<OrdenDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol =  accesoBD.obtenerObjetosFecha(Orden.class, fecha_cierre.getYear(),fecha_cierre.getMonth(), fecha_cierre.getDay());
			OrdenAssembler ordenAssem = new OrdenAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				ordenesDTO.add(ordenAssem.getOrdenDTO((Orden)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return ordenesDTO;
	}

	@Override
	public boolean existeOrden(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Orden) accesoBD.buscarPorId(Orden.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeOrden(String numero_orden) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection ordenes = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "numero_orden.equals(\""+numero_orden+"\")";
			ordenes = accesoBD.buscarPorFiltro(Orden.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (ordenes!=null && ordenes.size()>=1);
	}

	public boolean existeOrden(RecursoDTO recurso) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection ordenes = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "recurso.id == "+recurso.getId();
			ordenes = accesoBD.buscarPorFiltro(Orden.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (ordenes!=null && ordenes.size()>=1);
	}
	
	@Override
	public OrdenDTO buscarOrden(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		OrdenDTO ordenDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			OrdenAssembler ordenAssem = new OrdenAssembler(accesoBD);
			ordenDTO = ordenAssem.getOrdenDTO((Orden) accesoBD.buscarPorId(Orden.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return ordenDTO;
	}

	@Override
	public OrdenDTO buscarOrden(String numero_orden) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		OrdenDTO ordenDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "numero_orden.equals(\""+numero_orden+"\")";
			Collection ordenes = accesoBD.buscarPorFiltro(Orden.class, filtro);
			OrdenAssembler ordenAssem = new OrdenAssembler(accesoBD);
			if (ordenes.size()>=1 ) {
				ordenDTO = ordenAssem.getOrdenDTO((Orden)(ordenes.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return ordenDTO;
	} 

	public OrdenDTO buscarOrden(RecursoDTO recurso) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		OrdenDTO ordenDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "recurso.id == "+recurso.getId();
			Collection ordenes = accesoBD.buscarPorFiltro(Orden.class, filtro);
			OrdenAssembler ordenAssem = new OrdenAssembler(accesoBD);
			if (ordenes.size()>=1 ) {
				ordenDTO = ordenAssem.getOrdenDTO((Orden)(ordenes.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return ordenDTO;
	}
}
