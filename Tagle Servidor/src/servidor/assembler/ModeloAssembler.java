package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Modelo;

import common.DTOs.ModeloDTO;

public class ModeloAssembler {
	
	private AccesoBD accesoBD;

	public ModeloAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public ModeloDTO getModeloDTO(Modelo modelo) {
		ModeloDTO modeloDTO = new ModeloDTO();
		modeloDTO.setId(modelo.getId());
		modeloDTO.setNombre_modelo(modelo.getNombre_modelo());
		MarcaAssembler marcaAssemb = new MarcaAssembler(accesoBD);
		if(modelo.getMarca()!=null)
			modeloDTO.setMarca(marcaAssemb.getMarcaDTO(modelo.getMarca()));
		return modeloDTO;
	}

	public Modelo getModelo(ModeloDTO modeloDTO) {
		Modelo modelo = (Modelo) accesoBD.buscarPorId(Modelo.class, modeloDTO.getId());
		modelo.setId(modeloDTO.getId());
		modelo.setNombre_modelo(modeloDTO.getNombre_modelo());
		MarcaAssembler marcaAssemb = new MarcaAssembler(accesoBD);
		if(modeloDTO.getMarca()!=null)
			modelo.setMarca(marcaAssemb.getMarca(modeloDTO.getMarca()));
		return modelo;
	}
	
	public Modelo getModeloNuevo(ModeloDTO modeloDTO) {
		Modelo modelo = new Modelo();
		modelo.setId(modeloDTO.getId());
		modelo.setNombre_modelo(modeloDTO.getNombre_modelo());
		MarcaAssembler marcaAssemb = new MarcaAssembler(accesoBD);
		if(modeloDTO.getMarca()!=null)
			modelo.setMarca(marcaAssemb.getMarca(modeloDTO.getMarca()));
		return modelo;
	}

}