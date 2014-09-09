package common.GestionarReclamo_Fabrica;

import java.rmi.Remote;
import java.sql.Date;
import java.util.Vector;

import common.DTOs.PedidoDTO;
import common.DTOs.PiezaDTO;
import common.DTOs.Reclamo_FabricaDTO;

public interface IControlReclamo_Fabrica extends Remote{

	public Long agregarReclamo_FabricaDTO(Reclamo_FabricaDTO reclamo_fabricaDTO)throws Exception;
	public void eliminarReclamo_FabricaDTO(Long id)throws Exception;
	public void modificarReclamo_Fabrica(Long id,Reclamo_FabricaDTO modificado)throws Exception;
	
	public Vector<Reclamo_FabricaDTO> obtenerReclamo_Fabrica()throws Exception;
	public Vector<Reclamo_FabricaDTO> obtenerReclamo_Fabrica(PedidoDTO pedidoDTO) throws Exception;
	public Vector<Reclamo_FabricaDTO> obtenerReclamo_Fabrica(Date fecha_reclamo) throws Exception;
	public Vector<Reclamo_FabricaDTO> obtenerReclamo_Fabrica(PiezaDTO piezaDTO) throws Exception;
	
	public boolean existeReclamo_Fabrica(Long id) throws Exception;
	public boolean existeReclamo_Fabrica(PedidoDTO pedidoDTO, PiezaDTO piezaDTO) throws Exception;
	
	public Reclamo_FabricaDTO buscarReclamo_FabricaDTO(Long id) throws Exception;
	public Reclamo_FabricaDTO buscarReclamo_FabricaDTO(PedidoDTO pedidoDTO, PiezaDTO pieza) throws Exception;

}