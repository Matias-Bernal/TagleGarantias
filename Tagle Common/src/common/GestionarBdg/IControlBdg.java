package common.GestionarBdg;

import java.rmi.Remote;
import java.sql.Date;
import java.util.Vector;

import common.DTOs.BdgDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.PiezaDTO;

public interface IControlBdg extends Remote{
	
	public Long agregarBdg(BdgDTO bdg)throws Exception;
	public void eliminarBdg(Long id)throws Exception;
	public void modificarBdg(Long id,BdgDTO modificado)throws Exception;
	
	public Vector<BdgDTO> obtenerBdgs()throws Exception;
	public Vector<BdgDTO> obtenerBdgs(Date fecha_bdg)throws Exception;
	public Vector<BdgDTO> obtenerBdgs(PiezaDTO pieza)throws Exception;
	public Vector<BdgDTO> obtenerBdgs(PedidoDTO pedido)throws Exception;
	
	public boolean existeBdg(Long id) throws Exception;
	public boolean existeBdg(PiezaDTO pieza, PedidoDTO pedido) throws Exception;
	public boolean existeBdg(String numero_bdg) throws Exception;
	public boolean existeBdg(Date fecha_bdg, PiezaDTO pieza, PedidoDTO pedido) throws Exception;

	public BdgDTO buscarBdg(Long id) throws Exception;
	public BdgDTO buscarBdg(PiezaDTO pieza, PedidoDTO pedido) throws Exception;
	public BdgDTO buscarBdg(String numero_bdg) throws Exception;
	public BdgDTO buscarBdg(Date fecha_bdg, PiezaDTO pieza, PedidoDTO pedido) throws Exception;

}
