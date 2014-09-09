package common.GestionarEntidad;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.EntidadDTO;

public interface IControlEntidad extends Remote{

	public Long agregarEntidad(EntidadDTO entidad)throws Exception;
	public void eliminarEntidad(Long id)throws Exception;
	public void modificarEntidad(Long id,EntidadDTO modificado)throws Exception;
	
	public Vector<EntidadDTO> obtenerEntidad()throws Exception;
	
	public boolean existeEntidad(Long id) throws Exception;
	public boolean existeEntidad(String nombre_registrante) throws Exception;
	
	public EntidadDTO buscarEntidad(Long id) throws Exception;
	public EntidadDTO buscarEntidad(String nombre_registrante) throws Exception;

}
