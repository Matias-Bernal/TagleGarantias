package servidor.GestionarMarca;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.MarcaAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Marca;

import common.DTOs.MarcaDTO;
import common.GestionarMarca.IControlMarca;

public class ControlMarca extends UnicastRemoteObject implements IControlMarca {

	private static final long serialVersionUID = 1L;

	public ControlMarca() throws RemoteException {
		super();
	}

	@Override
	public Long agregarMarca(MarcaDTO marcaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			MarcaAssembler marcaAssemb = new MarcaAssembler(accesoBD); 
			Marca Marca = marcaAssemb.getMarcaNueva(marcaDTO);
			accesoBD.hacerPersistente(Marca);
			id = Marca.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void eliminarMarca(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			MarcaAssembler marcaAssemb = new MarcaAssembler(accesoBD); 
			Marca marca = marcaAssemb.getMarca(buscarMarca(id));
			accesoBD.eliminar(marca);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarMarca(Long id, MarcaDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			MarcaAssembler marcaAssemb = new MarcaAssembler(accesoBD); 
			Marca marca = marcaAssemb.getMarca(buscarMarca(id));
			marca.setNombre_marca(modificado.getNombre_marca());
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<MarcaDTO> obtenerMarcas() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<MarcaDTO> marcasDTO = new Vector<MarcaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Marca> marcas = new Vector<Marca>(accesoBD.buscarPorFiltro(Marca.class, ""));
			for (int i = 0; i < marcas.size(); i++) {
				
				MarcaDTO marcaDTO = new MarcaDTO();
				marcaDTO.setId(marcas.elementAt(i).getId());
				marcaDTO.setNombre_marca(marcas.elementAt(i).getNombre_marca());
				marcasDTO.add(marcaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			System.out.println("Error en obtener todas las marcas desde la BD");
			accesoBD.rollbackTransaccion();
		}
		return marcasDTO;
	}

	@Override
	public boolean existeMarca(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Marca) accesoBD.buscarPorId(Marca.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeMarca(String nombre_marca) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		@SuppressWarnings("rawtypes")
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre_marca.equals(\""+nombre_marca+"\")";
			movCol = accesoBD.buscarPorFiltro(Marca.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public MarcaDTO buscarMarca(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		MarcaDTO marcaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			MarcaAssembler marcaAssemb = new MarcaAssembler(accesoBD); 
			marcaDTO = marcaAssemb.getMarcaDTO((Marca) accesoBD.buscarPorId(Marca.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return marcaDTO;
	}
	
	@Override
	public MarcaDTO buscarMarca(String nombre_marca) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		MarcaDTO marcaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre_marca.equals(\""+nombre_marca+"\")";
			@SuppressWarnings("rawtypes")
			Collection movCol = accesoBD.buscarPorFiltro(Marca.class, filtro);
			MarcaAssembler marcaAssemb = new MarcaAssembler(accesoBD); 
			if (movCol.size()>=1){
				marcaDTO = marcaAssemb.getMarcaDTO((Marca)(movCol.toArray())[0]);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			System.out.println("entre en el roolback");
			accesoBD.rollbackTransaccion();
		}
		return marcaDTO;
	}

}
