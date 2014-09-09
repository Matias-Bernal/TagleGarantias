package common.GestionarReclamo;

import java.rmi.Remote;
import java.sql.Date;
import java.util.Vector;

import common.DTOs.ReclamanteDTO;
import common.DTOs.ReclamoDTO;
import common.DTOs.VehiculoDTO;

public interface IControlReclamo extends Remote{
	
	public Long agregarReclamo(ReclamoDTO reclamoDTO)throws Exception;
	public void eliminarReclamo(Long id)throws Exception;
	public void modificarReclamo(Long id,ReclamoDTO modificado)throws Exception;
	
	public Vector<ReclamoDTO> obtenerReclamos()throws Exception;
	public Vector<ReclamoDTO> obtenerReclamosEntidades()throws Exception;
	public Vector<ReclamoDTO> obtenerReclamosEntidadesConPiezas()throws Exception;
	public Vector<ReclamoDTO> obtenerReclamosAgentes()throws Exception;
	public Vector<ReclamoDTO> obtenerReclamosAgentesConPiezas()throws Exception;
	public Vector<ReclamoDTO> obtenerReclamos(Date fecha_reclamo)throws Exception;
	public Vector<ReclamoDTO> obtenerReclamos(String estado_reclamo)throws Exception;
	public Vector<ReclamoDTO> obtenerReclamos(ReclamanteDTO reclamanteDTO)throws Exception;
	public Vector<ReclamoDTO> obtenerReclamos(VehiculoDTO vehiculoDTO)throws Exception;
	
	public boolean existeReclamo(Long id) throws Exception;
	public boolean existeReclamo(Date fecha_reclamo,ReclamanteDTO reclamanteDTO, VehiculoDTO vehiculoDTO) throws Exception;
	
	public ReclamoDTO buscarReclamo(Long id) throws Exception;
	public ReclamoDTO buscarReclamo(Date fecha_reclamo,ReclamanteDTO reclamanteDTO, VehiculoDTO vehiculoDTO) throws Exception;
	public Vector<ReclamoDTO> obtenerOrdenSinSDP() throws Exception;

}
