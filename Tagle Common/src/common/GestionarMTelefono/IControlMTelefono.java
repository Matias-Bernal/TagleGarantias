package common.GestionarMTelefono;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.MTelefonoDTO;
import common.DTOs.ReclamanteDTO;

public interface IControlMTelefono extends Remote{
	
	public Long agregarMTelefono(MTelefonoDTO mTelefonoDTO)throws Exception;
	public void eliminarMTelefono(Long id)throws Exception;
	public void modificarMTelefono(Long id,MTelefonoDTO modificado)throws Exception;
	
	public Vector<MTelefonoDTO> obtenerMTelefono()throws Exception;
	public Vector<MTelefonoDTO> obtenerMTelefono(ReclamanteDTO reclamanteDTO)throws Exception;
	
	public boolean existeMTelefono(Long id) throws Exception;
	public boolean existeMTelefono(ReclamanteDTO reclamanteDTO, String telefono) throws Exception;
	
	public MTelefonoDTO buscarMTelefono(Long id) throws Exception;
	public MTelefonoDTO buscarMTelefono(ReclamanteDTO reclamanteDTO, String telefono) throws Exception;

}