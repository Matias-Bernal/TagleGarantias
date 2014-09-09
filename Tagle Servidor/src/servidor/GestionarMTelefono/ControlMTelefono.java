package servidor.GestionarMTelefono;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.MTelefonoAssembler;
import servidor.assembler.ReclamanteAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.MTelefono;

import common.DTOs.MTelefonoDTO;
import common.DTOs.ReclamanteDTO;
import common.GestionarMTelefono.IControlMTelefono;

public class ControlMTelefono extends UnicastRemoteObject implements IControlMTelefono {

	private static final long serialVersionUID = 1L;

	public ControlMTelefono() throws RemoteException {
		super();
	}

	@Override
	public Long agregarMTelefono(MTelefonoDTO mTelefonoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			MTelefonoAssembler mTelefonoAssemb = new MTelefonoAssembler(accesoBD);
			MTelefono mTelefono = mTelefonoAssemb.getMTelefonoNuevo(mTelefonoDTO);
			accesoBD.hacerPersistente(mTelefono);
			id = mTelefono.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarMTelefono(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			MTelefonoAssembler mTelefonoAssemb = new MTelefonoAssembler(accesoBD);
			MTelefono mTelefono = mTelefonoAssemb.getMTelefono(buscarMTelefono(id));
			accesoBD.eliminar(mTelefono);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarMTelefono(Long id, MTelefonoDTO modificado)
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			MTelefonoAssembler mTelefonoAssemb = new MTelefonoAssembler(accesoBD);
			MTelefono mTelefono = mTelefonoAssemb.getMTelefono(buscarMTelefono(id));

			mTelefono.setTelefono(modificado.getTelefono());
			ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
			mTelefono.setReclamante(reclamanteAssemb.getReclamante(modificado.getReclamante()));

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<MTelefonoDTO> obtenerMTelefono() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<MTelefonoDTO> mTelefonosDTO = new Vector<MTelefonoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<MTelefono> mTelefonos = new Vector<MTelefono> (accesoBD.buscarPorFiltro(MTelefono.class, ""));
			for (int i = 0; i < mTelefonos.size(); i++) {
				MTelefonoDTO mTelefonoDTO = new MTelefonoDTO();
				
				mTelefonoDTO.setId(mTelefonos.elementAt(i).getId());
				mTelefonoDTO.setTelefono(mTelefonos.elementAt(i).getTelefono());
				ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
				mTelefonoDTO.setReclamante(reclamanteAssemb.getReclamanteDTO(mTelefonos.elementAt(i).getReclamante()));
				
				mTelefonosDTO.add(mTelefonoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return mTelefonosDTO;
	}

	@Override
	public Vector<MTelefonoDTO> obtenerMTelefono(ReclamanteDTO reclamanteDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<MTelefonoDTO> mTelefonosDTO = new Vector<MTelefonoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "reclamante.id == "+reclamanteDTO.getId();
			Collection mTelefonos = accesoBD.buscarPorFiltro(MTelefono.class, filtro);
			MTelefonoAssembler mTelefonoAssemb = new MTelefonoAssembler(accesoBD);
			for (int i = 0; i < mTelefonos.size(); i++) {
				mTelefonosDTO.add(mTelefonoAssemb.getMTelefonoDTO((MTelefono)(mTelefonos.toArray()[i])));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return mTelefonosDTO;
	}

	@Override
	public boolean existeMTelefono(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((MTelefono) accesoBD.buscarPorId(MTelefono.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeMTelefono(ReclamanteDTO reclamanteDTO, String telefono) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "reclamante.id == "+reclamanteDTO.getId()+" && telefono.equals(\""+telefono+"\")";
			movCol = accesoBD.buscarPorFiltro(MTelefono.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public MTelefonoDTO buscarMTelefono(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		MTelefonoDTO mTelefonoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			
			MTelefonoAssembler mTelefonoAssemb = new MTelefonoAssembler(accesoBD);
			mTelefonoDTO = mTelefonoAssemb.getMTelefonoDTO((MTelefono) accesoBD.buscarPorId(MTelefono.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return mTelefonoDTO;
	}

	@Override
	public MTelefonoDTO buscarMTelefono(ReclamanteDTO reclamanteDTO, String telefono) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		MTelefonoDTO mTelefonoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "reclamante.id == "+reclamanteDTO.getId()+" && telefono.equals(\""+telefono+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(MTelefono.class, filtro);
			MTelefonoAssembler mTelefonoAssemb = new MTelefonoAssembler(accesoBD);
			if (movCol.size()>=1){
				mTelefonoDTO = mTelefonoAssemb.getMTelefonoDTO((MTelefono)(movCol.toArray())[0]);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return mTelefonoDTO;
	}

}