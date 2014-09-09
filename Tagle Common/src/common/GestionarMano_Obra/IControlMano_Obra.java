package common.GestionarMano_Obra;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.Mano_ObraDTO;
import common.DTOs.ReclamoDTO;

public interface IControlMano_Obra extends Remote{
	
	public Long agregarMano_Obra(Mano_ObraDTO mano_obraDTO)throws Exception;
	public void eliminarMano_Obra(Long id)throws Exception;
	public void modificarMano_Obra(Long id,Mano_ObraDTO modificado)throws Exception;
	
	public Vector<Mano_ObraDTO> obtenerMano_Obra()throws Exception;
	public Vector<Mano_ObraDTO> obtenerMano_Obra(String codigo_mano_obra)throws Exception;
	public Vector<Mano_ObraDTO> obtenerMano_Obra(ReclamoDTO reclamo)throws Exception;
	
	public boolean existeMano_Obra(Long id) throws Exception;
	public boolean existeMano_Obra(String codigo_mano_obra) throws Exception;
	
	public Mano_ObraDTO buscarMano_Obra(Long id) throws Exception;
	public Mano_ObraDTO buscarMano_Obra(String codigo_mano_obra) throws Exception;
	public Mano_ObraDTO buscarMano_Obra(ReclamoDTO reclamo,String codigo_mano_obra) throws Exception;

}
