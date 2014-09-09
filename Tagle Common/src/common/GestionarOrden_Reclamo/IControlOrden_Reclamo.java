package common.GestionarOrden_Reclamo;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.OrdenDTO;
import common.DTOs.Orden_ReclamoDTO;
import common.DTOs.ReclamoDTO;

public interface IControlOrden_Reclamo extends Remote{
	
	public Long agregarOrden_Reclamo(Orden_ReclamoDTO orden_RecursoDTO)throws Exception;
	public void eliminarOrden_Reclamo(Long id)throws Exception;
	public void modificarOrden_Reclamo(Long id,Orden_ReclamoDTO modificado)throws Exception;
	
	public Vector<Orden_ReclamoDTO> obtenerOrdenes_Reclamos()throws Exception;
	public Vector<Orden_ReclamoDTO> obtenerOrdenes_Reclamos(OrdenDTO ordenDTO)throws Exception;
	public Vector<Orden_ReclamoDTO> obtenerOrdenes_Reclamos(ReclamoDTO reclamoDTO)throws Exception;

	public boolean existeOrden_Reclamo(Long id) throws Exception;
	public boolean existeOrden_Reclamo(OrdenDTO ordenDTO,ReclamoDTO reclamoDTO) throws Exception;
	
	public Orden_ReclamoDTO buscarOrden_Reclamo(Long id) throws Exception;
	public Orden_ReclamoDTO buscarOrden_Reclamo(OrdenDTO ordenDTO,ReclamoDTO reclamoDTO) throws Exception;
	public Orden_ReclamoDTO buscarOrden_Reclamo_Por_Orden(Long id_orden)throws Exception;
	public Orden_ReclamoDTO buscarOrden_Reclamo_Por_Reclamo(Long id_reclamo)throws Exception;

}
