package common.GestionarPedido_Pieza_Reclamo_Agente;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_Pieza_Reclamo_AgenteDTO;
import common.DTOs.PiezaDTO;
import common.DTOs.Reclamo_AgenteDTO;

public interface IControlPedido_Pieza_Reclamo_Agente extends Remote{
	
	public Long agregarPedido_Pieza_Reclamo_Agente(Pedido_Pieza_Reclamo_AgenteDTO pedido_Pieza_Reclamo_AgenteDTO)throws Exception;
	public void eliminarPedido_Pieza_Reclamo_Agente(Long id)throws Exception;
	public void modificarPedido_Pieza_Reclamo_Agente(Long id,Pedido_Pieza_Reclamo_AgenteDTO modificado)throws Exception;
	
	public Vector<Pedido_Pieza_Reclamo_AgenteDTO> obtenerPedido_Pieza_Reclamo_Agente()throws Exception;
	public Vector<Pedido_Pieza_Reclamo_AgenteDTO> obtenerPedido_Pieza_Reclamo_Agente(PiezaDTO piezaDTO)throws Exception;
	public Vector<Pedido_Pieza_Reclamo_AgenteDTO> obtenerPedido_Pieza_Reclamo_Agente(PedidoDTO pedidoDTO)throws Exception;
	
	public boolean existePedido_Pieza_Reclamo_Agente(Long id) throws Exception;
	public boolean existePedido_Pieza_Reclamo_Agente(PedidoDTO pedidoDTO, PiezaDTO piezaDTO, Reclamo_AgenteDTO reclamo_agenteDTO) throws Exception;
	
	public Pedido_Pieza_Reclamo_AgenteDTO buscarPedido_Pieza_Reclamo_Agente(Long id) throws Exception;
	public Pedido_Pieza_Reclamo_AgenteDTO buscarPedido_Pieza_Reclamo_Agente(PedidoDTO pedidoDTO, PiezaDTO piezaDTO, Reclamo_AgenteDTO reclamo_agenteDTO) throws Exception;

}
