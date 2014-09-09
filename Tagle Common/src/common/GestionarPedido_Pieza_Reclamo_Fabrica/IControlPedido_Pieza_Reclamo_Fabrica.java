package common.GestionarPedido_Pieza_Reclamo_Fabrica;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.Pedido_Pieza_Reclamo_FabricaDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.PiezaDTO;
import common.DTOs.Reclamo_FabricaDTO;

public interface IControlPedido_Pieza_Reclamo_Fabrica extends Remote{
	
	public Long agregarPedido_Pieza_Reclamo_Fabrica(Pedido_Pieza_Reclamo_FabricaDTO pedido_Pieza_Reclamo_FabricaDTO)throws Exception;
	public void eliminarPedido_Pieza_Reclamo_Fabrica(Long id)throws Exception;
	public void modificarPedido_Pieza_Reclamo_Fabrica(Long id,Pedido_Pieza_Reclamo_FabricaDTO modificado)throws Exception;
	
	public Vector<Pedido_Pieza_Reclamo_FabricaDTO> obtenerPedido_Pieza_Reclamo_Fabrica()throws Exception;
	public Vector<Pedido_Pieza_Reclamo_FabricaDTO> obtenerPedido_Pieza_Reclamo_Fabrica(PiezaDTO piezaDTO)throws Exception;
	public Vector<Pedido_Pieza_Reclamo_FabricaDTO> obtenerPedido_Pieza_Reclamo_Fabrica(PedidoDTO pedidoDTO)throws Exception;
	
	public boolean existePedido_Pieza_Reclamo_Fabrica(Long id) throws Exception;
	public boolean existePedido_Pieza_Reclamo_Fabrica(PedidoDTO pedidoDTO, PiezaDTO piezaDTO, Reclamo_FabricaDTO reclamo_fabricaDTO) throws Exception;
	
	public Pedido_Pieza_Reclamo_FabricaDTO buscarPedido_Pieza_Reclamo_Fabrica(Long id) throws Exception;
	public Pedido_Pieza_Reclamo_FabricaDTO buscarPedido_Pieza_Reclamo_Fabrica(PedidoDTO pedidoDTO, PiezaDTO piezaDTO, Reclamo_FabricaDTO reclamo_fabricaDTO) throws Exception;

}
