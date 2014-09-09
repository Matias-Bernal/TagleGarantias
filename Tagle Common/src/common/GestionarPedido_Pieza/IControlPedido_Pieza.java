package common.GestionarPedido_Pieza;

import java.rmi.Remote;
import java.sql.Date;
import java.util.Vector;

import common.Cuadruple;
import common.Triple;
import common.DTOs.AgenteDTO;
import common.DTOs.Devolucion_PiezaDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.PiezaDTO;

public interface IControlPedido_Pieza extends Remote{

	public Long agregarPedido_Pieza(Pedido_PiezaDTO pedido_PiezaDTO)throws Exception;
	public void eliminarPedido_Pieza(Long id)throws Exception;
	public void modificarPedido_Pieza(Long id,Pedido_PiezaDTO modificado)throws Exception;
	
	public Vector<Pedido_PiezaDTO> obtenerPedido_Pieza()throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaAgente()throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaEntidad()throws Exception;
	
	public Vector<Pedido_PiezaDTO> obtenerPedido_Pieza(Date fecha_estado)throws Exception;
	
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaSinTurno() throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaContencion() throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaReclamoAgente() throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaReclamoFabrica() throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaSugerencia() throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPedido_Pieza(PedidoDTO pedidoDTO)throws Exception;

	public boolean existePedido_Pieza(Long id) throws Exception;
	public boolean existePedido_Pieza(Devolucion_PiezaDTO devolucion_piezaDTO) throws Exception;
	public boolean existePedido_Pieza(PedidoDTO pedidoDTO, PiezaDTO piezaDTO) throws Exception;
	public boolean existePedido_Pieza(String numero_pedido)throws Exception;

	public Pedido_PiezaDTO buscarPedido_Pieza(Long id) throws Exception;
	public Pedido_PiezaDTO buscarPedido_Pieza(PedidoDTO pedidoDTO, PiezaDTO piezaDTO) throws Exception;
	public Pedido_PiezaDTO buscarPedido_Pieza(Devolucion_PiezaDTO devolucion_piezaDTO)throws Exception;
	public Pedido_PiezaDTO buscarPedido_Pieza(String numero_pedido)throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPiezasLlegadas() throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPiezasDevueltas() throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPiezasSinLlegar() throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPiezasLlegadasSinTurno() throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerSDPSinNP() throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaEntidad(Long id_pedido)throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaAgente(Long id_pedido)throws Exception;

	public Long piezas_con_ot_entidad(Date desde, Date hasta) throws Exception;
	public Vector<Pedido_PiezaDTO> obtener_piezas_con_ot_entidad(Date desde, Date hasta) throws Exception;
	public Triple<Double,Double,Double> anticuacion_con_ot_entidad(Date desde, Date hasta) throws Exception;
	public Cuadruple<Double,Double,Double,Double> monto_con_ot_entidad(Date desde, Date hasta) throws Exception;
	
	public Long cantidad_piezas_agente(Date desde, Date hasta , AgenteDTO agente, String filtro) throws Exception;
	public Vector<Pedido_PiezaDTO> obtener_piezas_agente(Date desde, Date hasta, AgenteDTO agente, String filtro) throws Exception;
	public Triple<Double, Double, Double> anticuacion_agente(Date desde, Date hasta, AgenteDTO agente, String filtro) throws Exception;
	public Cuadruple<Double, Double, Double, Double> monto_agente(Date desde, Date hasta, AgenteDTO agente, String filtro) throws Exception;
	
	public Long piezas_en_transito(Date desde, Date hasta) throws Exception;
	public Vector<Pedido_PiezaDTO> obtener_piezas_en_transito(Date desde, Date hasta) throws Exception;
	public Triple<Double,Double,Double> anticuacion_en_transito(Date desde, Date hasta) throws Exception;
	public Cuadruple<Double,Double,Double,Double> monto_en_transito(Date desde, Date hasta) throws Exception;
	
	public Long piezas_sin_turno(Date desde, Date hasta) throws Exception;
	public Vector<Pedido_PiezaDTO> obtener_piezas_sin_turno(Date desde, Date hasta) throws Exception;
	public Triple<Double,Double,Double> anticuacion_sin_turno(Date desde, Date hasta) throws Exception;
	public Cuadruple<Double,Double,Double,Double> monto_sin_turno(Date desde, Date hasta) throws Exception;
	
	public Long piezas_con_turno(Date desde, Date hasta) throws Exception;
	public Vector<Pedido_PiezaDTO> obtener_piezas_con_turno(Date desde, Date hasta) throws Exception;
	public Triple<Double,Double,Double> anticuacion_con_turno(Date desde, Date hasta) throws Exception;
	public Cuadruple<Double,Double,Double,Double> monto_con_turno(Date desde, Date hasta) throws Exception;
	
	public Long piezas_cambiadas(Date desde, Date hasta) throws Exception;
	public Vector<Pedido_PiezaDTO> obtener_piezas_cambiadas(Date desde, Date hasta) throws Exception;
	public Triple<Double,Double,Double> anticuacion_cambiadas(Date desde, Date hasta) throws Exception;
	public Cuadruple<Double,Double,Double,Double> monto_cambiadas(Date desde, Date hasta) throws Exception;
	
	public Long piezas_recursadas(Date desde, Date hasta) throws Exception;
	public Vector<Pedido_PiezaDTO> obtener_piezas_recursadas(Date desde, Date hasta) throws Exception;
	public Triple<Double,Double,Double> anticuacion_recursadas(Date desde, Date hasta) throws Exception;
	public Cuadruple<Double,Double,Double,Double> monto_recursadas(Date desde, Date hasta) throws Exception;
	
	public Long piezas_con_sdevolucion(Date desde, Date hasta) throws Exception;
	public Vector<Pedido_PiezaDTO> obtener_piezas_con_sdevolucion(Date desde, Date hasta) throws Exception;
	public Triple<Double,Double,Double> anticuacion_con_sdevolucion(Date desde, Date hasta) throws Exception;
	public Cuadruple<Double,Double,Double,Double> monto_con_sdevolucion(Date desde, Date hasta) throws Exception;
	
	public Long piezas_aprobada_devolucion(Date desde, Date hasta) throws Exception;
	public Vector<Pedido_PiezaDTO> obtener_piezas_aprobada_devolucion(Date desde, Date hasta) throws Exception;
	public Triple<Double,Double,Double> anticuacion_aprobada_devolucion(Date desde, Date hasta) throws Exception;
	public Cuadruple<Double,Double,Double,Double> monto_aprobada_devolucion(Date desde, Date hasta) throws Exception;
	
	public Long piezas_devueltas(Date desde, Date hasta) throws Exception;
	public Vector<Pedido_PiezaDTO> obtener_piezas_devueltas(Date desde, Date hasta) throws Exception;
	public Triple<Double,Double,Double> anticuacion_devueltas(Date desde, Date hasta) throws Exception;
	public Cuadruple<Double,Double,Double,Double> monto_devueltas(Date desde, Date hasta) throws Exception;
	
}
