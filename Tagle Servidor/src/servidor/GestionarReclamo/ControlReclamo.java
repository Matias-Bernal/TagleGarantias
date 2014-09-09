package servidor.GestionarReclamo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.Collection;
import java.util.Vector;

import javax.jdo.Extent;
import javax.jdo.Query;

import servidor.assembler.OrdenAssembler;
import servidor.assembler.PedidoAssembler;
import servidor.assembler.ReclamanteAssembler;
import servidor.assembler.ReclamoAssembler;
import servidor.assembler.RegistranteAssembler;
import servidor.assembler.UsuarioAssembler;
import servidor.assembler.VehiculoAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Agente;
import servidor.persistencia.dominio.Entidad;
import servidor.persistencia.dominio.Orden;
import servidor.persistencia.dominio.Pedido;
import servidor.persistencia.dominio.Pedido_Pieza;
import servidor.persistencia.dominio.Pedido_Pieza_Reclamo_Fabrica;
import servidor.persistencia.dominio.Reclamo;
import common.DTOs.ReclamanteDTO;
import common.DTOs.ReclamoDTO;
import common.DTOs.VehiculoDTO;
import common.GestionarReclamo.IControlReclamo;

public class ControlReclamo extends UnicastRemoteObject implements
		IControlReclamo {

	private static final long serialVersionUID = 1L;

	public ControlReclamo() throws RemoteException {
		super();
	}

	@Override
	public Long agregarReclamo(ReclamoDTO reclamoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
			Reclamo reclamo = reclamoAssemb.getReclamoNuevo(reclamoDTO);
			accesoBD.hacerPersistente(reclamo);
			id = reclamo.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarReclamo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
			Reclamo reclamo = reclamoAssemb.getReclamo(buscarReclamo(id));
			accesoBD.eliminar(reclamo);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarReclamo(Long id, ReclamoDTO modificado)
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
			Reclamo reclamo = reclamoAssemb.getReclamo(buscarReclamo(id));
			
			reclamo.setDescripcion(modificado.getDescripcion());
			reclamo.setEstado_reclamo(modificado.getEstado_reclamo());
			reclamo.setFecha_reclamo(modificado.getFecha_reclamo());
			reclamo.setFecha_llamado_turno(modificado.getFecha_llamado_turno());
			reclamo.setFecha_turno(modificado.getFecha_turno());
			reclamo.setInmovilizado(modificado.getInmovilizado());
			reclamo.setPeligroso(modificado.getPeligroso());
			ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
			reclamo.setReclamante(reclamanteAssemb.getReclamante(modificado.getReclamante()));
			RegistranteAssembler registranteAssemb = new RegistranteAssembler(accesoBD);
			reclamo.setRegistrante(registranteAssemb.getRegistrante(modificado.getRegistrante()));
			UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
			reclamo.setUsuario(usuarioAssemb.getUsuario(modificado.getUsuario()));
			VehiculoAssembler vehiculoAssemb = new VehiculoAssembler(accesoBD);
			reclamo.setVehiculo(vehiculoAssemb.getVehiculo(modificado.getVehiculo()));
			OrdenAssembler ordenAssemb = new OrdenAssembler(accesoBD);
			reclamo.setOrden(ordenAssemb.getOrden(modificado.getOrden()));

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}

	}

	@Override
	public Vector<ReclamoDTO> obtenerReclamos() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<ReclamoDTO> reclamosDTO = new Vector<ReclamoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
			Vector<Reclamo> reclamos = new Vector<Reclamo> (accesoBD.buscarPorFiltro(Reclamo.class, ""));
			for (int i = 0; i < reclamos.size(); i++) {
				ReclamoDTO reclamoDTO = reclamoAssemb.getReclamoDTO(reclamos.elementAt(i));				
				reclamosDTO.add(reclamoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamosDTO;
	}

	@Override
	public Vector<ReclamoDTO> obtenerReclamos(Date fecha_reclamo)
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<ReclamoDTO> reclamosDTO = new Vector<ReclamoDTO>();
		try {
			accesoBD.iniciarTransaccion();

			Collection movCol =  accesoBD.obtenerObjetosFecha(Reclamo.class, fecha_reclamo.getYear(),fecha_reclamo.getMonth(), fecha_reclamo.getDay());
			ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				reclamosDTO.add(reclamoAssemb.getReclamoDTO((Reclamo)(movCol.toArray())[i]));
			}

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamosDTO;
	}

	@Override
	public Vector<ReclamoDTO> obtenerReclamos(String estado_reclamo) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<ReclamoDTO> reclamosDTO = new Vector<ReclamoDTO>();
		try {
			accesoBD.iniciarTransaccion();

			String filtro = "estado_reclamo.equals(\""+estado_reclamo+"\")";
			Collection ordenes = accesoBD.buscarPorFiltro(Reclamo.class, filtro);
			ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
			for (int i = 0; i < ordenes.size(); i++) {
				reclamosDTO.add(reclamoAssemb.getReclamoDTO((Reclamo)(ordenes.toArray())[i]));
			}

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamosDTO;
	}

	@Override
	public Vector<ReclamoDTO> obtenerReclamos(ReclamanteDTO reclamanteDTO)
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<ReclamoDTO> reclamosDTO = new Vector<ReclamoDTO>();
		try {
			accesoBD.iniciarTransaccion();

			String filtro = "reclamante.id == "+reclamanteDTO.getId();
			Collection ordenes = accesoBD.buscarPorFiltro(Reclamo.class, filtro);
			ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
			for (int i = 0; i < ordenes.size(); i++) {
				reclamosDTO.add(reclamoAssemb.getReclamoDTO((Reclamo)(ordenes.toArray())[i]));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamosDTO;
	}

	@Override
	public Vector<ReclamoDTO> obtenerReclamos(VehiculoDTO vehiculoDTO)
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<ReclamoDTO> reclamosDTO = new Vector<ReclamoDTO>();
		try {
			accesoBD.iniciarTransaccion();

			String filtro = "vehiculo.id == "+vehiculoDTO.getId();
			Collection reclamos = accesoBD.buscarPorFiltro(Reclamo.class, filtro);
			ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
			for (int i = 0; i < reclamos.size(); i++) {
				reclamosDTO.add(reclamoAssemb.getReclamoDTO((Reclamo)(reclamos.toArray())[i]));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamosDTO;
	}

	@Override
	public boolean existeReclamo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Reclamo) accesoBD.buscarPorId(Reclamo.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeReclamo(Date fecha_reclamo, ReclamanteDTO reclamanteDTO, VehiculoDTO vehiculoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "reclamante.id == "+reclamanteDTO.getId()+" && vehiculo.id == "+vehiculoDTO.getId();
			Collection reclamos = accesoBD.buscarPorFiltro(Reclamo.class, filtro);
			if (reclamos != null && reclamos.size()>=1){
				for (int i = 0 ; i < reclamos.size(); i++){
					if (((Reclamo)(reclamos.toArray())[i]).getFecha_reclamo().equals(fecha_reclamo)){
						existe = true;
					}
				}
			}else{
				existe = false;
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public ReclamoDTO buscarReclamo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		ReclamoDTO reclamoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
			reclamoDTO = reclamoAssemb.getReclamoDTO((Reclamo) accesoBD.buscarPorId(Reclamo.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamoDTO;
	}

	@Override
	public ReclamoDTO buscarReclamo(Date fecha_reclamo, ReclamanteDTO reclamanteDTO, VehiculoDTO vehiculoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		ReclamoDTO reclamoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			Vector<ReclamoDTO> reclamosDTO = obtenerReclamos();
			for (int i = 0; i < reclamosDTO.size(); i++) {
				if (reclamosDTO.elementAt(i).getFecha_reclamo().equals(fecha_reclamo) && reclamosDTO.elementAt(i).getReclamante().getId().equals(reclamanteDTO.getId()) && reclamosDTO.elementAt(i).getVehiculo().getId().equals(vehiculoDTO.getId())) {
					reclamoDTO = reclamosDTO.elementAt(i);
					break;
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamoDTO;
	}

	@Override
	public Vector<ReclamoDTO> obtenerReclamosEntidades() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<ReclamoDTO> reclamosDTO = new Vector<ReclamoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Extent e1 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection c1 = (Collection) q1.execute();
			
			Extent e2 = accesoBD.getPersistencia().getExtent(Reclamo.class, true);
			Query q2 = accesoBD.getPersistencia().newQuery(e2, "entidades.contains(this.registrante)");
			q2.declareImports("import java.util.Collection");
			q2.declareParameters("Collection entidades");
			Collection c2 = (Collection) q2.execute(c1);

			Vector<Reclamo> reclamos = new Vector<Reclamo> (c2);
			ReclamoAssembler reclamoAssem = new ReclamoAssembler(accesoBD);
			for (int i = 0; i < reclamos.size(); i++) {
				ReclamoDTO reclamoDTO = reclamoAssem.getReclamoDTO(reclamos.elementAt(i));
				reclamosDTO.add(reclamoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamosDTO;
	}
	
	public Vector<ReclamoDTO> obtenerReclamosEntidadesSinTurno() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<ReclamoDTO> reclamosDTO = new Vector<ReclamoDTO>();
		try {
			accesoBD.iniciarTransaccion();

			Extent e = accesoBD.getPersistencia().getExtent(Pedido.class, true);
			Query q = accesoBD.getPersistencia().newQuery(e, "");
			Collection c = (Collection) q.execute();
			
			Extent e1 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection c1 = (Collection) q1.execute();
			
			Extent e2 = accesoBD.getPersistencia().getExtent(Reclamo.class, true);
			Query q2 = accesoBD.getPersistencia().newQuery(e2, "entidades.contains(this.registrante) && this.fecha_turno == null");
			q2.declareImports("import java.util.Collection");
			q2.declareParameters("Collection entidades");
			Collection c2 = (Collection) q2.execute(c1);

			Vector<Reclamo> reclamos = new Vector<Reclamo> (c2);
			ReclamoAssembler reclamoAssem = new ReclamoAssembler(accesoBD);
			for (int i = 0; i < reclamos.size(); i++) {
				ReclamoDTO reclamoDTO = reclamoAssem.getReclamoDTO(reclamos.elementAt(i));
				reclamosDTO.add(reclamoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamosDTO;
	}

	@Override
	public Vector<ReclamoDTO> obtenerReclamosEntidadesConPiezas() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<ReclamoDTO> reclamosDTO = new Vector<ReclamoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Reclamo> reclamos = new Vector<Reclamo> (accesoBD.buscarPorFiltro(Reclamo.class, ""));
			ReclamoAssembler reclamoAssem = new ReclamoAssembler(accesoBD);
			for (int i = 0; i < reclamos.size(); i++) {
				ReclamoDTO reclamoDTO = reclamoAssem.getReclamoDTO(reclamos.elementAt(i));
				reclamosDTO.add(reclamoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamosDTO;
	}

	@Override
	public Vector<ReclamoDTO> obtenerReclamosAgentes() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<ReclamoDTO> reclamosDTO = new Vector<ReclamoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Extent e1 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection c1 = (Collection) q1.execute();
			
			Extent e2 = accesoBD.getPersistencia().getExtent(Reclamo.class, true);
			Query q2 = accesoBD.getPersistencia().newQuery(e2, "entidades.contains(this.registrante)");
			q2.declareImports("import java.util.Collection");
			q2.declareParameters("Collection entidades");
			Collection c2 = (Collection) q2.execute(c1);

			Vector<Reclamo> reclamos = new Vector<Reclamo> (c2);
			ReclamoAssembler reclamoAssem = new ReclamoAssembler(accesoBD);
			for (int i = 0; i < reclamos.size(); i++) {
				ReclamoDTO reclamoDTO = reclamoAssem.getReclamoDTO(reclamos.elementAt(i));
				reclamosDTO.add(reclamoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamosDTO;
	}

	@Override
	public Vector<ReclamoDTO> obtenerReclamosAgentesConPiezas() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<ReclamoDTO> reclamosDTO = new Vector<ReclamoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Reclamo> reclamos = new Vector<Reclamo> (accesoBD.buscarPorFiltro(Reclamo.class, ""));
			ReclamoAssembler reclamoAssem = new ReclamoAssembler(accesoBD);
			for (int i = 0; i < reclamos.size(); i++) {
				ReclamoDTO reclamoDTO = reclamoAssem.getReclamoDTO(reclamos.elementAt(i));
				reclamosDTO.add(reclamoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamosDTO;
	}
	
	@Override
	public Vector<ReclamoDTO> obtenerOrdenSinSDP() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<ReclamoDTO> reclamosDTO = new Vector<ReclamoDTO>();
		Vector<Reclamo> reclamosDePedidos = new Vector<Reclamo>();
		try {
			accesoBD.iniciarTransaccion();
			Collection reclamos = accesoBD.buscarPorFiltro(Reclamo.class, "orden!=null");
			Collection pedidos = accesoBD.buscarPorFiltro(Pedido.class, "reclamo!=null");
			ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
			for (int i = 0; i < pedidos.size(); i++) {
				if(!reclamosDePedidos.contains((((Pedido)(pedidos.toArray())[i]).getReclamo()))){
					reclamosDePedidos.add(((Pedido)(pedidos.toArray())[i]).getReclamo());
				}
			}
			for (int i = 0; i < reclamos.size(); i++) {
				if(!reclamosDePedidos.contains((Reclamo)(reclamos.toArray())[i])){
					reclamosDTO.add(reclamoAssemb.getReclamoDTO((Reclamo)(reclamos.toArray())[i]));
				}
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return reclamosDTO;
	}

}