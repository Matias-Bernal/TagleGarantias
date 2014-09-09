package common.GestionarReclamante_Reclamo;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.ReclamanteDTO;
import common.DTOs.Reclamante_ReclamoDTO;
import common.DTOs.ReclamoDTO;

public interface IControlReclamante_Reclamo extends Remote{
	
	public Long agregarReclamante_Reclamo(Reclamante_ReclamoDTO reclamante_ReclamoDTO)throws Exception;
	public void eliminarReclamante_Reclamo(Long id)throws Exception;
	public void modificarReclamante_Reclamo(Long id,Reclamante_ReclamoDTO modificado)throws Exception;
	
	public Vector<Reclamante_ReclamoDTO> obtenerReclamante_Reclamos()throws Exception;
	public Vector<Reclamante_ReclamoDTO> obtenerReclamante_Reclamos(ReclamoDTO reclamoDTO)throws Exception;
	
	public boolean existeReclamante_Reclamo(Long id) throws Exception;
	public boolean existeReclamante_Reclamo(ReclamanteDTO reclamanteDTO, ReclamoDTO reclamoDTO) throws Exception;
	
	public Reclamante_ReclamoDTO buscarReclamante_Reclamo(Long id) throws Exception;
	public Reclamante_ReclamoDTO buscarReclamante_Reclamo(ReclamanteDTO reclamanteDTO, ReclamoDTO reclamoDTO) throws Exception;

}