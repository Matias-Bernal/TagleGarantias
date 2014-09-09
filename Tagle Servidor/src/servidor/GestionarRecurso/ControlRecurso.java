package servidor.GestionarRecurso;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.Pedido_PiezaAssembler;
import servidor.assembler.RecursoAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Recurso;

import common.DTOs.RecursoDTO;
import common.GestionarRecurso.IControlRecurso;

public class ControlRecurso extends UnicastRemoteObject implements IControlRecurso {

	private static final long serialVersionUID = 1L;

	public ControlRecurso() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarRecurso(RecursoDTO recursoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			RecursoAssembler recursoAssemb = new RecursoAssembler(accesoBD);
			Recurso recurso = recursoAssemb.getRecursoNuevo(recursoDTO);
			accesoBD.hacerPersistente(recurso);
			id = recurso.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarRecurso(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			RecursoAssembler recursoAssemb = new RecursoAssembler(accesoBD);
			Recurso recurso = recursoAssemb.getRecurso(buscarRecurso(id));
			accesoBD.eliminar(recurso);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}	
	}

	@Override
	public void modificarRecurso(Long id, RecursoDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			RecursoAssembler recursoAssemb = new RecursoAssembler(accesoBD);
			Recurso recurso = recursoAssemb.getRecurso(buscarRecurso(id));
			
			recurso.setFecha_recurso(modificado.getFecha_recurso());
			recurso.setNumero_recurso(modificado.getNumero_recurso());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}	

	}

	@Override
	public Vector<RecursoDTO> obtenerRecursos() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<RecursoDTO> recursosDTO = new Vector<RecursoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Recurso> recursos = new Vector<Recurso> (accesoBD.buscarPorFiltro(Recurso.class, ""));
			for (int i = 0; i < recursos.size(); i++) {
				RecursoDTO recursoDTO = new RecursoDTO();

				recursoDTO.setId(recursos.elementAt(i).getId());
				recursoDTO.setFecha_recurso(recursos.elementAt(i).getFecha_recurso());
				recursoDTO.setNumero_recurso(recursos.elementAt(i).getNumero_recurso());

				recursosDTO.add(recursoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return recursosDTO;
	}

	@Override
	public Vector<RecursoDTO> obtenerRecursos(Date fecha_recurso) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<RecursoDTO> recursosDTO = new Vector<RecursoDTO>();
		try {
			accesoBD.iniciarTransaccion();

			Collection recursos =  accesoBD.obtenerObjetosFecha(Recurso.class, fecha_recurso.getYear(),fecha_recurso.getMonth(), fecha_recurso.getDay());
			RecursoAssembler recursoAssemb = new RecursoAssembler(accesoBD);
			for (int i = 0; i < recursos.size(); i++) {
				recursosDTO.add(recursoAssemb.getRecursoDTO((Recurso)(recursos.toArray())[i]));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return recursosDTO;
	}

	@Override
	public boolean existeRecurso(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Recurso) accesoBD.buscarPorId(Recurso.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeRecurso(String numero_recurso) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection recursos = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "numero_recurso.equals(\""+numero_recurso+"\")";
			recursos = accesoBD.buscarPorFiltro(Recurso.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (recursos!=null && recursos.size()>=1);
	}

	@Override
	public RecursoDTO buscarRecurso(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		RecursoDTO recursoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			RecursoAssembler recursoAssemb = new RecursoAssembler(accesoBD);
			recursoDTO = recursoAssemb.getRecursoDTO((Recurso) accesoBD.buscarPorId(Recurso.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return recursoDTO;
	}

	@Override
	public RecursoDTO buscarRecurso(String numero_recurso) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		RecursoDTO recursoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "numero_recurso.equals(\""+numero_recurso+"\")";
			Collection recursos = accesoBD.buscarPorFiltro(Recurso.class, filtro);
			RecursoAssembler recursoAssemb = new RecursoAssembler(accesoBD);
			if (recursos.size()>=1 ) {
				recursoDTO = recursoAssemb.getRecursoDTO((Recurso)(recursos.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return recursoDTO;
	}

}