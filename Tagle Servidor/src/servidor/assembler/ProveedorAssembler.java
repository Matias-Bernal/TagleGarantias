package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Proveedor;

import common.DTOs.ProveedorDTO;

public class ProveedorAssembler {
	
	private AccesoBD accesoBD;

	public ProveedorAssembler (AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public ProveedorDTO getProveedorDTO(Proveedor proveedor) {
		ProveedorDTO proveedorDTO = new ProveedorDTO();
		proveedorDTO.setId(proveedor.getId());
		proveedorDTO.setNombre(proveedor.getNombre());
		return proveedorDTO;
	}

	public Proveedor getProveedor(ProveedorDTO proveedorDTO) {
		Proveedor proveedor = (Proveedor) accesoBD.buscarPorId(Proveedor.class, proveedorDTO.getId());
		proveedor.setId(proveedorDTO.getId());
		proveedor.setNombre(proveedorDTO.getNombre());
		return proveedor;
	}

	public Proveedor getProveedorNuevo(ProveedorDTO proveedorDTO) {
		Proveedor proveedor = new Proveedor();
		proveedor.setId(proveedorDTO.getId());
		proveedor.setNombre(proveedorDTO.getNombre());
		return proveedor;
	}

}
