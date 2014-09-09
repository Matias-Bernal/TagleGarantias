package common.GestionarRecurso;

import java.rmi.Remote;
import java.sql.Date;
import java.util.Vector;

import common.DTOs.RecursoDTO;

public interface IControlRecurso extends Remote{

	public Long agregarRecurso(RecursoDTO Recurso)throws Exception;
	public void eliminarRecurso(Long id)throws Exception;
	public void modificarRecurso(Long id,RecursoDTO modificado)throws Exception;
	
	public Vector<RecursoDTO> obtenerRecursos()throws Exception;
	public Vector<RecursoDTO> obtenerRecursos(Date fecha_recurso)throws Exception;
	
	public boolean existeRecurso(Long id) throws Exception;
	public boolean existeRecurso(String numero_recurso) throws Exception;
	
	public RecursoDTO buscarRecurso(Long id) throws Exception;
	public RecursoDTO buscarRecurso(String numero_recurso) throws Exception;

}