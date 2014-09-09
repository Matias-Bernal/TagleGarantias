package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Marca;

import common.DTOs.MarcaDTO;

public class MarcaAssembler {
	
	private AccesoBD accesoBD;

	public MarcaAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public MarcaDTO getMarcaDTO(Marca marca) {
		MarcaDTO marcaDTO = new MarcaDTO();
		marcaDTO.setId(marca.getId());
		marcaDTO.setNombre_marca(marca.getNombre_marca());
		return marcaDTO;
	}

	public Marca getMarca(MarcaDTO marcaDTO) {
		Marca marca = (Marca) accesoBD.buscarPorId(Marca.class, marcaDTO.getId());
		marca.setId(marcaDTO.getId());
		marca.setNombre_marca(marcaDTO.getNombre_marca());
		return marca;
	}

	public Marca getMarcaNueva(MarcaDTO marcaDTO) {
		Marca marca = new Marca();
		marca.setId(marcaDTO.getId());
		marca.setNombre_marca(marcaDTO.getNombre_marca());
		return marca;
	}
}