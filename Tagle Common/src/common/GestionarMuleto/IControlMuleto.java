package common.GestionarMuleto;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.MuletoDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.PiezaDTO;

public interface IControlMuleto extends Remote{

	public Long agregarMuleto(MuletoDTO muletoDTO)throws Exception;
	public void eliminarMuleto(Long id)throws Exception;
	public void modificarMuleto(Long id,MuletoDTO modificado)throws Exception;
	
	public Vector<MuletoDTO> obtenerMuleto()throws Exception;
	public Vector<MuletoDTO> obtenerMuleto(PedidoDTO pedidoDTO)throws Exception;
	
	public boolean existeMuleto(Long id) throws Exception;
	public boolean existeMuleto(PedidoDTO pedidoDTO, PiezaDTO piezaDTO) throws Exception;
	
	public MuletoDTO buscarMuleto(Long id) throws Exception;
	public MuletoDTO buscarMuleto(PedidoDTO pedidoDTO, PiezaDTO piezaDTO) throws Exception;

}
