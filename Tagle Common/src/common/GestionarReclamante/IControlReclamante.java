package common.GestionarReclamante;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.ReclamanteDTO;

public interface IControlReclamante extends Remote{

	public Long agregarReclamante(ReclamanteDTO reclamanteDTO)throws Exception;
	public void eliminarReclamante(Long id)throws Exception;
	public void modificarReclamante(Long id,ReclamanteDTO modificado)throws Exception;
	
	public Vector<ReclamanteDTO> obtenerReclamantes()throws Exception;
	
	public boolean existeReclamante(Long id) throws Exception;
	public boolean existeReclamante(String nombre_apellido) throws Exception;
	public boolean existeReclamanteDni(String dni) throws Exception;
	
	public ReclamanteDTO buscarReclamante(Long id) throws Exception;
	public ReclamanteDTO buscarReclamante(String nombre_apellido) throws Exception;
	public ReclamanteDTO buscarReclamanteDni(String DNI) throws Exception;
}
