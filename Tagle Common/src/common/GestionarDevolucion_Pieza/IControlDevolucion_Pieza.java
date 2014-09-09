package common.GestionarDevolucion_Pieza;

import java.rmi.Remote;
import java.sql.Date;
import java.util.Vector;

import common.DTOs.Devolucion_PiezaDTO;

public interface IControlDevolucion_Pieza extends Remote{

	public Long agregarDevolucion_Pieza(Devolucion_PiezaDTO devolucion_piezaDTO)throws Exception;
	public void eliminarDevolucion_Pieza(Long id)throws Exception;
	public void modificarDevolucion_Pieza(Long id,Devolucion_PiezaDTO modificado)throws Exception;
	
	public Vector<Devolucion_PiezaDTO> obtenerDevolucion_Pieza()throws Exception;
	public Vector<Devolucion_PiezaDTO> obtenerDevolucion_Pieza(Date fecha_devolucion)throws Exception;
	public Vector<Devolucion_PiezaDTO> obtenerDevolucion_Pieza_Trasnporte(String transporte)throws Exception;
	
	public boolean existeDevolucion_Pieza(Long id) throws Exception;
	public boolean existeDevolucion_Pieza(String numero_remito) throws Exception;
	public boolean existeDevolucion_Pieza_Numero_Retiro(String numero_retiro) throws Exception;
	
	public Devolucion_PiezaDTO buscarDevolucion_Pieza(Long id) throws Exception;
	public Devolucion_PiezaDTO buscarDevolucion_Pieza(String numero_remito) throws Exception;
	public Devolucion_PiezaDTO buscarDevolucion_Pieza_Numero_Retiro(String numero_retiro) throws Exception;


}