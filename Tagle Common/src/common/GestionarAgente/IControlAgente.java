package common.GestionarAgente;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.AgenteDTO;

public interface IControlAgente extends Remote{

	public Long agregarAgente(AgenteDTO agente)throws Exception;
	public void eliminarAgente(Long id)throws Exception;
	public void modificarAgente(Long id,AgenteDTO modificado)throws Exception;
	
	public Vector<AgenteDTO> obtenerAgentes()throws Exception;
	
	public boolean existeAgente(Long id) throws Exception;
	public boolean existeAgente(String nombre_registrante) throws Exception;
	
	public AgenteDTO buscarAgente(Long id) throws Exception;
	public AgenteDTO buscarAgente(String nombre_registrante) throws Exception;

}