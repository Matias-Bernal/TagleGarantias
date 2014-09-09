package common.GestionarMarca;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.MarcaDTO;

public interface IControlMarca extends Remote{
	
	public Long agregarMarca(MarcaDTO marcaDTO)throws Exception;
	public void eliminarMarca(Long id)throws Exception;
	public void modificarMarca(Long id,MarcaDTO modificado)throws Exception;
	
	public Vector<MarcaDTO> obtenerMarcas()throws Exception;
	
	public boolean existeMarca(Long id) throws Exception;
	public boolean existeMarca(String nombre_marca) throws Exception;
	
	public MarcaDTO buscarMarca(Long id) throws Exception;
	public MarcaDTO buscarMarca(String nombre_marca) throws Exception;

}
