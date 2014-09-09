package common.GestionarOrden;

import java.rmi.Remote;
import java.sql.Date;
import java.util.Vector;

import common.DTOs.OrdenDTO;
import common.DTOs.RecursoDTO;

public interface IControlOrden extends Remote{

	public Long agregarOrden(OrdenDTO ordenDTO)throws Exception;
	public void eliminarOrden(Long id)throws Exception;
	public void modificarOrden(Long id,OrdenDTO modificado)throws Exception;
	
	public Vector<OrdenDTO> obtenerOrdenes()throws Exception;
	
	public Vector<OrdenDTO> obtenerOrdenes_estado(String estado)throws Exception;
	public Vector<OrdenDTO> obtenerOrdenes_fecha_apertura(Date fecha_apertura)throws Exception;
	public Vector<OrdenDTO> obtenerOrdenes_fecha_cierre(Date fecha_cierre)throws Exception;

	public boolean existeOrden(Long id) throws Exception;
	public boolean existeOrden(String numero_orden) throws Exception;
	public boolean existeOrden(RecursoDTO recurso) throws Exception;
	
	public OrdenDTO buscarOrden(Long id) throws Exception;
	public OrdenDTO buscarOrden(String numero_orden) throws Exception;
	public OrdenDTO buscarOrden(RecursoDTO recurso) throws Exception;

}
