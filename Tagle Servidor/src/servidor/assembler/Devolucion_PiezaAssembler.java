package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Devolucion_Pieza;

import common.DTOs.Devolucion_PiezaDTO;

public class Devolucion_PiezaAssembler {
	
	private AccesoBD accesoBD;

	public Devolucion_PiezaAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public Devolucion_PiezaDTO getDevolucion_PiezaDTO(Devolucion_Pieza devolucion_pieza) {
		Devolucion_PiezaDTO devolucion_piezaDTO = new Devolucion_PiezaDTO();
		devolucion_piezaDTO.setId(devolucion_pieza.getId());
		devolucion_piezaDTO.setFecha_devolucion(devolucion_pieza.getFecha_devolucion());
		devolucion_piezaDTO.setNumero_remito(devolucion_pieza.getNumero_remito());
		devolucion_piezaDTO.setTransporte(devolucion_pieza.getTransporte());
		devolucion_piezaDTO.setNumero_retiro(devolucion_pieza.getNumero_remito());
		return devolucion_piezaDTO;
	}

	public Devolucion_Pieza getDevolucion_Pieza(Devolucion_PiezaDTO devolucion_piezaDTO) {
		Devolucion_Pieza devolucion_pieza = (Devolucion_Pieza) accesoBD.buscarPorId(Devolucion_Pieza.class, devolucion_piezaDTO.getId());
		devolucion_pieza.setId(devolucion_piezaDTO.getId());
		devolucion_pieza.setFecha_devolucion(devolucion_piezaDTO.getFecha_devolucion());
		devolucion_pieza.setNumero_remito(devolucion_piezaDTO.getNumero_remito());
		devolucion_pieza.setTransporte(devolucion_piezaDTO.getTransporte());
		devolucion_pieza.setNumero_retiro(devolucion_piezaDTO.getNumero_remito());
		return devolucion_pieza;
	}

	public Devolucion_Pieza getDevolucion_PiezaNueva(Devolucion_PiezaDTO devolucion_piezaDTO) {
		Devolucion_Pieza devolucion_pieza = new Devolucion_Pieza();
		devolucion_pieza.setId(devolucion_piezaDTO.getId());
		devolucion_pieza.setFecha_devolucion(devolucion_piezaDTO.getFecha_devolucion());
		devolucion_pieza.setNumero_remito(devolucion_piezaDTO.getNumero_remito());
		devolucion_pieza.setTransporte(devolucion_piezaDTO.getTransporte());
		devolucion_pieza.setNumero_retiro(devolucion_piezaDTO.getNumero_remito());
		return devolucion_pieza;
	}

}