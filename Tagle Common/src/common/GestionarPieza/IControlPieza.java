package common.GestionarPieza;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.PiezaDTO;
import common.DTOs.ProveedorDTO;

public interface IControlPieza extends Remote{
	
	public Long agregarPieza(PiezaDTO piezaDTO)throws Exception;
	public void eliminarPieza(Long id)throws Exception;
	public void modificarPieza(Long id,PiezaDTO modificado)throws Exception;
	
	public Vector<PiezaDTO> obtenerPiezas()throws Exception;
	public Vector<PiezaDTO> obtenerPiezas(String numero_pieza)throws Exception;
	public Vector<PiezaDTO> obtenerPiezas(ProveedorDTO proveedorDTO)throws Exception;
	
	public boolean existePieza(Long id) throws Exception;
	public boolean existePieza(String numero_pieza) throws Exception;
	public boolean existePieza(ProveedorDTO proveedorDTO, String numero_pieza) throws Exception;

	public PiezaDTO buscarPieza(Long id) throws Exception;
	public PiezaDTO buscarPieza(String numero_pieza) throws Exception;
	public PiezaDTO buscarPieza(ProveedorDTO proveedorDTO, String numero_pieza) throws Exception;
	
}