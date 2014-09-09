package common.GestionarVehiculo;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.MarcaDTO;
import common.DTOs.ModeloDTO;
import common.DTOs.VehiculoDTO;

public interface IControlVehiculo extends Remote{

	public Long agregarVehiculo(VehiculoDTO vehiculoDTO)throws Exception;
	public void eliminarVehiculo(Long id)throws Exception;
	public void modificarVehiculo(Long id,VehiculoDTO modificado)throws Exception;
	
	public Vector<VehiculoDTO> obtenerVehiculos()throws Exception;
	public Vector<VehiculoDTO> obtenerVehiculos(MarcaDTO marca)throws Exception;
	public Vector<VehiculoDTO> obtenerVehiculos(ModeloDTO modelo)throws Exception;
	
	public boolean existeVehiculo(Long id) throws Exception;
	public boolean existeVehiculo(String dominio) throws Exception;
	
	public VehiculoDTO buscarVehiculo(Long id) throws Exception;
	public VehiculoDTO buscarVehiculo(String dominio) throws Exception;

}