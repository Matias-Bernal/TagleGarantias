package common.GestionarReclamo_Agente;

import java.rmi.Remote;
import java.sql.Date;
import java.util.Vector;

import common.DTOs.PedidoDTO;
import common.DTOs.PiezaDTO;
import common.DTOs.Reclamo_AgenteDTO;

public interface IControlReclamo_Agente extends Remote{

	public Long agregarReclamo_Agente(Reclamo_AgenteDTO reclamo_agenteDTO)throws Exception;
	public void eliminarReclamo_Agente(Long id)throws Exception;
	public void modificarReclamo_Agente(Long id,Reclamo_AgenteDTO modificado)throws Exception;
	
	public Vector<Reclamo_AgenteDTO> obtenerReclamo_Agente()throws Exception;
	public Vector<Reclamo_AgenteDTO> obtenerReclamo_Agente(PedidoDTO pedidoDTO) throws Exception;
	public Vector<Reclamo_AgenteDTO> obtenerReclamo_Agente(Date fecha_reclamo) throws Exception;
	public Vector<Reclamo_AgenteDTO> obtenerReclamo_Agente(PiezaDTO piezaDTO) throws Exception;
	
	public boolean existeReclamo_Agente(Long id) throws Exception;
	public boolean existeReclamo_Agente(PedidoDTO pedidoDTO, PiezaDTO piezaDTO) throws Exception;
	
	public Reclamo_AgenteDTO buscarReclamo_Agente(Long id) throws Exception;
	public Reclamo_AgenteDTO buscarReclamo_Agente(PedidoDTO pedidoDTO, PiezaDTO pieza) throws Exception;

}
