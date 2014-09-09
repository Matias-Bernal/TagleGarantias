package servidor.GestionarNotificacion_Reclamo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.NotificacionAssembler;
import servidor.assembler.Notificacion_ReclamoAssembler;
import servidor.assembler.Notificacion_UsuarioAssembler;
import servidor.assembler.ReclamoAssembler;
import servidor.assembler.UsuarioAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Notificacion_Reclamo;
import servidor.persistencia.dominio.Notificacion_Usuario;
import common.DTOs.NotificacionDTO;
import common.DTOs.Notificacion_ReclamoDTO;
import common.DTOs.Notificacion_UsuarioDTO;
import common.DTOs.ReclamoDTO;
import common.GestionarNotificacion_Reclamo.IControlNotificacion_Reclamo;
import common.GestionarNotificacion_Usuario.IControlNotificacion_Usuario;

public class ControlNotificacion_Reclamo extends UnicastRemoteObject implements IControlNotificacion_Reclamo{

	public ControlNotificacion_Reclamo() throws RemoteException {
		super();
	}

	@Override
	public Long agregarNotificacionReclamo(Notificacion_ReclamoDTO notificacion_reclamoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Notificacion_ReclamoAssembler notificacion_reclamoAssem = new Notificacion_ReclamoAssembler(accesoBD);
			Notificacion_Reclamo notificacion_Reclamo = notificacion_reclamoAssem.getNotificacion_Reclamo(notificacion_reclamoDTO);
			accesoBD.hacerPersistente(notificacion_Reclamo);
			id = notificacion_Reclamo.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarNotificacionReclamo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			Notificacion_ReclamoAssembler notificacion_reclamoAssem = new Notificacion_ReclamoAssembler(accesoBD);
			Notificacion_Reclamo notificacion_Reclamo = notificacion_reclamoAssem.getNotificacion_Reclamo(buscarNotificacionReclamo(id));
			accesoBD.eliminar(notificacion_Reclamo);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarNotificacionReclamo(Long id,Notificacion_ReclamoDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			Notificacion_ReclamoAssembler notificacion_reclamoAssem = new Notificacion_ReclamoAssembler(accesoBD);
			
			Notificacion_Reclamo notificacion_Reclamo = notificacion_reclamoAssem.getNotificacion_Reclamo(buscarNotificacionReclamo(id));
			
			NotificacionAssembler notificacionAssemb = new NotificacionAssembler(accesoBD);
			notificacion_Reclamo.setNotificacion(notificacionAssemb.getNotificacion(modificado.getNotificacion()));
			ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
			notificacion_Reclamo.setReclamo(reclamoAssemb.getReclamo(modificado.getReclamo()));

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<Notificacion_ReclamoDTO> obtenerNotificaciones_Reclamos()	throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Notificacion_ReclamoDTO> notificaciones_ReclamosDTO = new Vector<Notificacion_ReclamoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Notificacion_Reclamo> notificaciones_reclamos = new Vector<Notificacion_Reclamo> (accesoBD.buscarPorFiltro(Notificacion_Reclamo.class, ""));
			for (int i = 0; i < notificaciones_reclamos.size(); i++) {
				Notificacion_ReclamoDTO notificacion_ReclamoDTO = new Notificacion_ReclamoDTO();
				
				notificacion_ReclamoDTO.setId(notificaciones_reclamos.elementAt(i).getId());
			
				NotificacionAssembler notificacionAssemb = new NotificacionAssembler(accesoBD);
				notificacion_ReclamoDTO.setNotificacion(notificacionAssemb.getNotificacionDTO(notificaciones_reclamos.elementAt(i).getNotificacion()));
				ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
				notificacion_ReclamoDTO.setReclamo(reclamoAssemb.getReclamoDTO(notificaciones_reclamos.elementAt(i).getReclamo()));				
				
				notificaciones_ReclamosDTO.add(notificacion_ReclamoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return notificaciones_ReclamosDTO;
	}

	@Override
	public Vector<Notificacion_ReclamoDTO> obtenerNotificaciones_Reclamo(ReclamoDTO reclamo) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Notificacion_ReclamoDTO> notificacion_ReclamoDTO = new Vector<Notificacion_ReclamoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "reclamo.id == "+reclamo.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Notificacion_Reclamo.class, filtro);
			Notificacion_ReclamoAssembler notificacion_ReclamoAssemb = new Notificacion_ReclamoAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				notificacion_ReclamoDTO.add(notificacion_ReclamoAssemb.getNotificacion_ReclamoDTO((Notificacion_Reclamo)(movCol.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return notificacion_ReclamoDTO;
	}

	@Override
	public Vector<Notificacion_ReclamoDTO> obtenerNotificaciones_Reclamo(NotificacionDTO notificacion) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Notificacion_ReclamoDTO> notificacion_ReclamoDTO = new Vector<Notificacion_ReclamoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "notificacion.id == "+notificacion.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Notificacion_Reclamo.class, filtro);
			Notificacion_ReclamoAssembler notificacion_ReclamoAssemb = new Notificacion_ReclamoAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				notificacion_ReclamoDTO.add(notificacion_ReclamoAssemb.getNotificacion_ReclamoDTO((Notificacion_Reclamo)(movCol.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return notificacion_ReclamoDTO;
	}

	@Override
	public boolean existeNotificacionReclamo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Notificacion_Reclamo) accesoBD.buscarPorId(Notificacion_Reclamo.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeNotificacionReclamo(ReclamoDTO reclamo, NotificacionDTO notificacion) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "reclamo.id == "+reclamo.getId()+ " && notificacion.id == "+notificacion.getId();
			movCol = accesoBD.buscarPorFiltro(Notificacion_Reclamo.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public Notificacion_ReclamoDTO buscarNotificacionReclamo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Notificacion_ReclamoDTO notificacion_ReclamoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			Notificacion_ReclamoAssembler notificacion_ReclamoAssemb = new Notificacion_ReclamoAssembler(accesoBD);
			notificacion_ReclamoDTO = notificacion_ReclamoAssemb.getNotificacion_ReclamoDTO((Notificacion_Reclamo) accesoBD.buscarPorId(Notificacion_Reclamo.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return notificacion_ReclamoDTO;
	}

	@Override
	public Notificacion_ReclamoDTO buscarNotificacionReclamo(ReclamoDTO reclamo, NotificacionDTO notificacion) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Notificacion_ReclamoDTO notificacion_ReclamoDTO = null;
		try {
			accesoBD.iniciarTransaccion();

			String filtro = "reclamo.id == "+reclamo.getId()+ " && notificacion.id == "+notificacion.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Notificacion_Reclamo.class, filtro);
			Notificacion_ReclamoAssembler notificacion_ReclamoAssemb = new Notificacion_ReclamoAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				notificacion_ReclamoDTO = notificacion_ReclamoAssemb.getNotificacion_ReclamoDTO((Notificacion_Reclamo)(movCol.toArray()[0]));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return notificacion_ReclamoDTO;
	}

}
