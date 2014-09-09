package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Pieza;

import common.DTOs.PiezaDTO;

public class PiezaAssembler {
	
	private AccesoBD accesoBD;

	public PiezaAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public PiezaDTO getPiezaDTO(Pieza pieza) {
		PiezaDTO piezaDTO = new PiezaDTO();
		piezaDTO.setId(pieza.getId());
		piezaDTO.setNumero_pieza(pieza.getNumero_pieza());
		piezaDTO.setDescripcion(pieza.getDescripcion());
		ProveedorAssembler proveedorAssemb = new ProveedorAssembler(accesoBD);
		if(pieza.getProveedor()!=null)
			piezaDTO.setProveedor(proveedorAssemb.getProveedorDTO(pieza.getProveedor()));
		return piezaDTO;
	}

	public Pieza getPieza(PiezaDTO piezaDTO) {
		Pieza pieza = (Pieza) accesoBD.buscarPorId(Pieza.class, piezaDTO.getId());
		pieza.setId(piezaDTO.getId());
		pieza.setNumero_pieza(piezaDTO.getNumero_pieza());
		pieza.setDescripcion(piezaDTO.getDescripcion());
		ProveedorAssembler proveedorAssemb = new ProveedorAssembler(accesoBD);
		if(piezaDTO.getProveedor()!=null)
			pieza.setProveedor(proveedorAssemb.getProveedor(piezaDTO.getProveedor()));
		return pieza;
	}

	public Pieza getPiezaNuevo(PiezaDTO piezaDTO) {
		Pieza pieza = new Pieza();
		pieza.setId(piezaDTO.getId());
		pieza.setNumero_pieza(piezaDTO.getNumero_pieza());
		pieza.setDescripcion(piezaDTO.getDescripcion());
		ProveedorAssembler proveedorAssemb = new ProveedorAssembler(accesoBD);
		if(piezaDTO.getProveedor()!=null)
			pieza.setProveedor(proveedorAssemb.getProveedor(piezaDTO.getProveedor()));
		return pieza;
	}

}