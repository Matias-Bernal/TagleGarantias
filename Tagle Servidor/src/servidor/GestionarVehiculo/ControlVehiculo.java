package servidor.GestionarVehiculo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.MarcaAssembler;
import servidor.assembler.ModeloAssembler;
import servidor.assembler.VehiculoAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Vehiculo;

import common.DTOs.MarcaDTO;
import common.DTOs.ModeloDTO;
import common.DTOs.VehiculoDTO;
import common.GestionarVehiculo.IControlVehiculo;

public class ControlVehiculo extends UnicastRemoteObject implements IControlVehiculo {

	private static final long serialVersionUID = 1L;

	public ControlVehiculo() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarVehiculo(VehiculoDTO vehiculoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			VehiculoAssembler vehiculoAssemb = new VehiculoAssembler(accesoBD);
			Vehiculo nuevovehiculo = vehiculoAssemb.getVehiculoNuevo(vehiculoDTO);
			accesoBD.hacerPersistente(nuevovehiculo);
			id = nuevovehiculo.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarVehiculo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			VehiculoAssembler vehiculoAssemb = new VehiculoAssembler(accesoBD);
			Vehiculo vehiculo = vehiculoAssemb.getVehiculo(buscarVehiculo(id));
			accesoBD.eliminar(vehiculo);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}

	}

	@Override
	public void modificarVehiculo(Long id, VehiculoDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			VehiculoAssembler vehiculoAssemb = new VehiculoAssembler(accesoBD);
			Vehiculo vehiculo = vehiculoAssemb.getVehiculo(buscarVehiculo(id));

			vehiculo.setDominio(modificado.getDominio());
			vehiculo.setNombre_titular(modificado.getNombre_titular());
			vehiculo.setVin(modificado.getVin());
			MarcaAssembler marcaAssemb = new MarcaAssembler(accesoBD);
			vehiculo.setMarca(marcaAssemb.getMarca(modificado.getMarca()));
			ModeloAssembler modeloAssemb = new ModeloAssembler(accesoBD);
			vehiculo.setModelo(modeloAssemb.getModelo(modificado.getModelo()));

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<VehiculoDTO> obtenerVehiculos() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<VehiculoDTO> vehiculosDTO = new Vector<VehiculoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Vehiculo> vehiculos = new Vector<Vehiculo> (accesoBD.buscarPorFiltro(Vehiculo.class, ""));
			for (int i = 0; i < vehiculos.size(); i++) {
				VehiculoDTO vehiculoDTO = new VehiculoDTO();
				
				vehiculoDTO.setId(vehiculos.elementAt(i).getId());
				vehiculoDTO.setDominio(vehiculos.elementAt(i).getDominio());
				vehiculoDTO.setNombre_titular(vehiculos.elementAt(i).getNombre_titular());
				vehiculoDTO.setVin(vehiculos.elementAt(i).getVin());
				MarcaAssembler marcaAssemb = new MarcaAssembler(accesoBD);
				vehiculoDTO.setMarca(marcaAssemb.getMarcaDTO(vehiculos.elementAt(i).getMarca()));
				ModeloAssembler modeloAssemb = new ModeloAssembler(accesoBD);
				vehiculoDTO.setModelo(modeloAssemb.getModeloDTO(vehiculos.elementAt(i).getModelo()));
				
				vehiculosDTO.add(vehiculoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return vehiculosDTO;
	}

	@Override
	public Vector<VehiculoDTO> obtenerVehiculos(MarcaDTO marcaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<VehiculoDTO> vehiculosDTO = new Vector<VehiculoDTO>();
		try {
			accesoBD.iniciarTransaccion();

			String filtro = "marca.id == "+marcaDTO.getId();
			Collection vehiculos = accesoBD.buscarPorFiltro(Vehiculo.class, filtro);
			VehiculoAssembler vehiculoAssemb = new VehiculoAssembler(accesoBD);
			for (int i = 0; i < vehiculos.size(); i++) {
				vehiculosDTO.add(vehiculoAssemb.getVehiculoDTO((Vehiculo)(vehiculos.toArray())[i]));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return vehiculosDTO;
	}

	@Override
	public Vector<VehiculoDTO> obtenerVehiculos(ModeloDTO modeloDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<VehiculoDTO> vehiculosDTO = new Vector<VehiculoDTO>();
		try {
			accesoBD.iniciarTransaccion();

			String filtro = "modelo.id == "+modeloDTO.getId();
			Collection vehiculos = accesoBD.buscarPorFiltro(Vehiculo.class, filtro);
			VehiculoAssembler vehiculoAssemb = new VehiculoAssembler(accesoBD);
			for (int i = 0; i < vehiculos.size(); i++) {
				vehiculosDTO.add(vehiculoAssemb.getVehiculoDTO((Vehiculo)(vehiculos.toArray())[i]));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return vehiculosDTO;
	}

	@Override
	public boolean existeVehiculo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Vehiculo) accesoBD.buscarPorId(Vehiculo.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeVehiculo(String dominio) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection vehiculos = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "dominio.equals(\""+dominio+"\")";
			vehiculos = accesoBD.buscarPorFiltro(Vehiculo.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (vehiculos!=null && vehiculos.size()>=1);
	}

	@Override
	public VehiculoDTO buscarVehiculo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		VehiculoDTO vehiculoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			VehiculoAssembler vehiculoAssemb = new VehiculoAssembler(accesoBD);
			vehiculoDTO = vehiculoAssemb.getVehiculoDTO((Vehiculo) accesoBD.buscarPorId(Vehiculo.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return vehiculoDTO;
	}

	@Override
	public VehiculoDTO buscarVehiculo(String dominio) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		VehiculoDTO vehiculoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "dominio.equals(\""+dominio+"\")";
			Collection usuarios = accesoBD.buscarPorFiltro(Vehiculo.class, filtro);
			VehiculoAssembler vehiculoAssemb = new VehiculoAssembler(accesoBD);
			if (usuarios.size()>=1 ) {
				vehiculoDTO = vehiculoAssemb.getVehiculoDTO((Vehiculo)(usuarios.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return vehiculoDTO;
	}

}