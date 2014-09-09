package common.GestionarProveedor;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.ProveedorDTO;

public interface IControlProveedor extends Remote{
	
	public Long agregarProveedor(ProveedorDTO proveedorDTO)throws Exception;
	public void eliminarProveedor(Long id)throws Exception;
	public void modificarProveedor(Long id,ProveedorDTO modificado)throws Exception;
	
	public Vector<ProveedorDTO> obtenerProveedores()throws Exception;
	
	public boolean existeProveedor(Long id) throws Exception;
	public boolean existeProveedor(String nombre) throws Exception;
	
	public ProveedorDTO buscarProveedor(Long id) throws Exception;
	public ProveedorDTO buscarProveedor(String nombre) throws Exception;

}