package servidor.GestionarNotificacion;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.NotificacionAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Notificacion;

import common.DTOs.NotificacionDTO;
import common.GestionarNotificacion.IControlNotificacion;

public class ControlNotificacion extends UnicastRemoteObject implements
		IControlNotificacion {

	private static final long serialVersionUID = 1L;

	public ControlNotificacion() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarNotificacion(NotificacionDTO notificacionDTO)
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			NotificacionAssembler notificacionAssemb = new NotificacionAssembler(accesoBD);
			Notificacion notificacion = notificacionAssemb.getNotificacionNueva(notificacionDTO);
			accesoBD.hacerPersistente(notificacion);
			id = notificacion.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarNotificacion(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			NotificacionAssembler notificacionAssemb = new NotificacionAssembler(accesoBD);
			Notificacion notificacion = notificacionAssemb.getNotificacion(buscarNotificacion(id));
			accesoBD.eliminar(notificacion);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarNotificacion(Long id, NotificacionDTO modificado)
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();

			NotificacionAssembler notificacionAssemb = new NotificacionAssembler(accesoBD);
			Notificacion notificacion = notificacionAssemb.getNotificacion(buscarNotificacion(id));
			notificacion.setConcretada_notificacion(modificado.getConcretada_notificacion());
			notificacion.setTexto_notificacion(modificado.getTexto_notificacion());
			notificacion.setFecha_notificacion(modificado.getFecha_notificacion());
			notificacion.setTipo_notificacion(modificado.getTipo_notificacion());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<NotificacionDTO> obtenerNotificacion() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<NotificacionDTO> notificacionesDTO = new Vector<NotificacionDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Notificacion> notificaciones = new Vector<Notificacion> (accesoBD.buscarPorFiltro(Notificacion.class, ""));
			for (int i = 0; i < notificaciones.size(); i++) {
				NotificacionDTO notificacionDTO = new NotificacionDTO();
				
				notificacionDTO.setId(notificaciones.elementAt(i).getId());
				notificacionDTO.setFecha_notificacion(notificaciones.elementAt(i).getFecha_notificacion());
				notificacionDTO.setConcretada_notificacion(notificaciones.elementAt(i).getConcretada_notificacion());
				notificacionDTO.setTexto_notificacion(notificaciones.elementAt(i).getTexto_notificacion());
				notificacionDTO.setTipo_notificacion(notificaciones.elementAt(i).getTipo_notificacion());
			
				notificacionesDTO.add(notificacionDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return notificacionesDTO;
	}

	@Override
	public Vector<NotificacionDTO> obtenerNotificacion(Boolean concretada_notificacion) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<NotificacionDTO> notificacionesDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "concretada_notificacion.equals(\""+concretada_notificacion+"\")";
			Collection notificaciones = accesoBD.buscarPorFiltro(Notificacion.class, filtro);
			NotificacionAssembler notificacionAssemb = new NotificacionAssembler(accesoBD);
			for (int i = 0; i < notificaciones.size(); i++) {
				notificacionesDTO.add(notificacionAssemb.getNotificacionDTO((Notificacion)(notificaciones.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return notificacionesDTO;
	}

	@Override
	public Vector<NotificacionDTO> obtenerNotificacion(String tipo_notificacion)throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<NotificacionDTO> notificacionesDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "concretada_notificacion.equals(\""+tipo_notificacion+"\")";
			Collection notificaciones = accesoBD.buscarPorFiltro(Notificacion.class, filtro);
			NotificacionAssembler notificacionAssemb = new NotificacionAssembler(accesoBD);
			for (int i = 0; i < notificaciones.size(); i++) {
				notificacionesDTO.add(notificacionAssemb.getNotificacionDTO((Notificacion)(notificaciones.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return notificacionesDTO;
	}

	@Override
	public boolean existeNotificacion(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Notificacion) accesoBD.buscarPorId(Notificacion.class,id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeNotificacion(NotificacionDTO notificacionDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "notificacion.id == "+notificacionDTO.getId();
			movCol = accesoBD.buscarPorFiltro(Notificacion.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public NotificacionDTO buscarNotificacion(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		NotificacionDTO notificacionDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			NotificacionAssembler notificacionAssemb = new NotificacionAssembler(accesoBD);
			notificacionDTO = notificacionAssemb.getNotificacionDTO((Notificacion) accesoBD.buscarPorId(Notificacion.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return notificacionDTO;
	}

	@Override
	public NotificacionDTO buscarNotificacion(NotificacionDTO notificacionDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		NotificacionDTO notifDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "notificacion.id == "+notificacionDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Notificacion.class, filtro);
			NotificacionAssembler notificacionAssemb = new NotificacionAssembler(accesoBD);
			if (movCol.size()>=1){
				notifDTO = notificacionAssemb.getNotificacionDTO((Notificacion)(movCol.toArray())[0]);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return notifDTO;
	}


}