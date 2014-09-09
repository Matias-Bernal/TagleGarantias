package servidor.GestionarPieza;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.PiezaAssembler;
import servidor.assembler.ProveedorAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Pieza;

import common.DTOs.PiezaDTO;
import common.DTOs.ProveedorDTO;
import common.GestionarPieza.IControlPieza;

public class ControlPieza extends UnicastRemoteObject implements IControlPieza {

	private static final long serialVersionUID = 1L;

	public ControlPieza() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarPieza(PiezaDTO piezaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
			Pieza pieza = piezaAssemb.getPiezaNuevo(piezaDTO);
			accesoBD.hacerPersistente(pieza);
			id = pieza.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarPieza(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
			Pieza pieza = piezaAssemb.getPieza(buscarPieza(id));
			accesoBD.eliminar(pieza);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarPieza(Long id, PiezaDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			
			PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
			Pieza pieza = piezaAssemb.getPieza(buscarPieza(id));
			pieza.setNumero_pieza(modificado.getNumero_pieza());
			pieza.setDescripcion(modificado.getDescripcion());
			ProveedorAssembler proveedorAssemb = new ProveedorAssembler(accesoBD);
			pieza.setProveedor(proveedorAssemb.getProveedor(modificado.getProveedor()));

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<PiezaDTO> obtenerPiezas() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PiezaDTO> piezasDTO = new Vector<PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Pieza> piezas = new Vector<Pieza> (accesoBD.buscarPorFiltro(Pieza.class, ""));
			for (int i = 0; i < piezas.size(); i++) {
				PiezaDTO piezaDTO = new PiezaDTO();
				piezaDTO.setId(piezas.elementAt(i).getId());
				piezaDTO.setNumero_pieza(piezas.elementAt(i).getNumero_pieza());
				piezaDTO.setDescripcion(piezas.elementAt(i).getDescripcion());
				ProveedorAssembler proveedorAssemb = new ProveedorAssembler(accesoBD);
				piezaDTO.setProveedor(proveedorAssemb.getProveedorDTO(piezas.elementAt(i).getProveedor()));
				piezasDTO.add(piezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return piezasDTO;
	}

	@Override
	public Vector<PiezaDTO> obtenerPiezas(String numero_pieza) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PiezaDTO> piezasDTO = new Vector<PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "numero_pieza.equals(\""+numero_pieza+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Pieza.class, filtro);
			PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				piezasDTO.add(piezaAssemb.getPiezaDTO((Pieza)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return piezasDTO;
	}

	@Override
	public boolean existePieza(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Pieza) accesoBD.buscarPorId(Pieza.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existePieza(String numero_pieza) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "numero_pieza.equals(\""+numero_pieza+"\")";
			movCol = accesoBD.buscarPorFiltro(Pieza.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public PiezaDTO buscarPieza(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		PiezaDTO piezaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
			piezaDTO = piezaAssemb.getPiezaDTO((Pieza) accesoBD.buscarPorId(Pieza.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return piezaDTO;
	}

	@Override
	public PiezaDTO buscarPieza(String numero_pieza) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		PiezaDTO piezaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "numero_pieza.equals(\""+numero_pieza+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Pieza.class, filtro);
			PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				piezaDTO = piezaAssemb.getPiezaDTO((Pieza)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return piezaDTO;
	}

	@Override
	public Vector<PiezaDTO> obtenerPiezas(ProveedorDTO proveedorDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PiezaDTO> piezasDTO = new Vector<PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "reclamo.id == "+proveedorDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Pieza.class, filtro);
			PiezaAssembler piezaAssem =new PiezaAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				piezasDTO.add(piezaAssem.getPiezaDTO((Pieza)(movCol.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return piezasDTO;

	}

	@Override
	public boolean existePieza(ProveedorDTO proveedorDTO, String numero_pieza) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PiezaDTO> piezasDTO = new Vector<PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "numero_pieza.equals(\""+numero_pieza+"\") && reclamo.id == "+proveedorDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Pieza.class, filtro);
			PiezaAssembler piezaAssem =new PiezaAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				piezasDTO.add(piezaAssem.getPiezaDTO((Pieza)(movCol.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (piezasDTO!=null && piezasDTO.size()>=1);
	}

	@Override
	public PiezaDTO buscarPieza(ProveedorDTO proveedorDTO, String numero_pieza) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		PiezaDTO piezaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "numero_pieza.equals(\""+numero_pieza+"\") && reclamo.id == "+proveedorDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Pieza.class, filtro);
			PiezaAssembler piezaAssem =new PiezaAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				piezaDTO = piezaAssem.getPiezaDTO((Pieza)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return piezaDTO;
	}

}