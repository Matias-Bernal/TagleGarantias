package common.GestionarRegistrante;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.RegistranteDTO;

public interface IControlRegistrante extends Remote{

	public Long agregarRegistranteDTO(RegistranteDTO regitrante)throws Exception;
	public void eliminarRegistranteDTO(Long id)throws Exception;
	public void modificarRegistrante(Long id,RegistranteDTO modificado)throws Exception;
	
	public Vector<RegistranteDTO> obtenerRegistrantes()throws Exception;
	
	public boolean existeRegistrante(Long id) throws Exception;
	public boolean existeRegistrante(String nombre_registrante) throws Exception;
	
	public RegistranteDTO buscarRegistranteDTO(Long id) throws Exception;
	public RegistranteDTO buscarRegistranteDTO(String nombre_registrante) throws Exception;

}