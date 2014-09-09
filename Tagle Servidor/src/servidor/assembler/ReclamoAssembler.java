package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Reclamo;
import common.DTOs.ReclamanteDTO;
import common.DTOs.ReclamoDTO;

public class ReclamoAssembler {
	
	private AccesoBD accesoBD;

	public ReclamoAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public ReclamoDTO getReclamoDTO(Reclamo reclamo) {
		ReclamoDTO reclamoDTO = new ReclamoDTO();
		reclamoDTO.setId(reclamo.getId());
		reclamoDTO.setEstado_reclamo(reclamo.getEstado_reclamo());
		reclamoDTO.setFecha_reclamo(reclamo.getFecha_reclamo());
		reclamoDTO.setFecha_llamado_turno(reclamo.getFecha_llamado_turno());
		reclamoDTO.setFecha_turno(reclamo.getFecha_turno());
		reclamoDTO.setInmovilizado(reclamo.getInmovilizado());
		reclamoDTO.setPeligroso(reclamo.getPeligroso());
		reclamoDTO.setDescripcion(reclamo.getDescripcion());
		ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
		if(reclamo.getReclamante()!=null)
			reclamoDTO.setReclamante(reclamanteAssemb.getReclamanteDTO(reclamo.getReclamante()));
		UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
		if(reclamo.getUsuario()!=null)
			reclamoDTO.setUsuario(usuarioAssemb.getUsuarioDTO(reclamo.getUsuario()));
		VehiculoAssembler vehiculoAssemb = new VehiculoAssembler(accesoBD);
		if(reclamo.getVehiculo()!=null)
				reclamoDTO.setVehiculo(vehiculoAssemb.getVehiculoDTO(reclamo.getVehiculo()));
		OrdenAssembler ordenAssemb = new OrdenAssembler(accesoBD);
		if(reclamo.getOrden()!=null)
			reclamoDTO.setOrden(ordenAssemb.getOrdenDTO(reclamo.getOrden()));
		RegistranteAssembler registranteAssemb = new RegistranteAssembler(accesoBD);
		if(reclamo.getRegistrante()!=null)
			reclamoDTO.setRegistrante(registranteAssemb.getRegistranteDTO(reclamo.getRegistrante()));
		return reclamoDTO;
	}

	public Reclamo getReclamo(ReclamoDTO reclamoDTO) {
		Reclamo reclamo = (Reclamo) accesoBD.buscarPorId(Reclamo.class, reclamoDTO.getId());
		reclamo.setId(reclamoDTO.getId());
		reclamo.setEstado_reclamo(reclamoDTO.getEstado_reclamo());
		reclamo.setFecha_reclamo(reclamoDTO.getFecha_reclamo());
		reclamo.setFecha_llamado_turno(reclamoDTO.getFecha_llamado_turno());
		reclamo.setFecha_turno(reclamoDTO.getFecha_turno());
		reclamo.setInmovilizado(reclamoDTO.getInmovilizado());
		reclamo.setPeligroso(reclamoDTO.getPeligroso());
		reclamo.setDescripcion(reclamoDTO.getDescripcion());
		ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
		if (reclamoDTO.getReclamante()!=null)
			reclamo.setReclamante(reclamanteAssemb.getReclamante(reclamoDTO.getReclamante()));
		UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
		if (reclamoDTO.getUsuario()!=null)	
			reclamo.setUsuario(usuarioAssemb.getUsuario(reclamoDTO.getUsuario()));
		VehiculoAssembler vehiculoAssemb = new VehiculoAssembler(accesoBD);
		if (reclamoDTO.getVehiculo()!=null)
			reclamo.setVehiculo(vehiculoAssemb.getVehiculo(reclamoDTO.getVehiculo()));
		OrdenAssembler ordenAssemb = new OrdenAssembler(accesoBD);
		if(reclamoDTO.getOrden()!=null)	
			reclamo.setOrden(ordenAssemb.getOrden(reclamoDTO.getOrden()));
		RegistranteAssembler registranteAssemb = new RegistranteAssembler(accesoBD);
		if(reclamoDTO.getRegistrante()!=null)
			reclamo.setRegistrante(registranteAssemb.getRegistrante(reclamoDTO.getRegistrante()));
		return reclamo;
	}
	
	public Reclamo getReclamoNuevo(ReclamoDTO reclamoDTO) {
		Reclamo reclamo = new Reclamo();
		reclamo.setId(reclamoDTO.getId());
		reclamo.setEstado_reclamo(reclamoDTO.getEstado_reclamo());
		reclamo.setFecha_reclamo(reclamoDTO.getFecha_reclamo());
		reclamo.setFecha_llamado_turno(reclamoDTO.getFecha_llamado_turno());
		reclamo.setFecha_turno(reclamoDTO.getFecha_turno());
		reclamo.setInmovilizado(reclamoDTO.getInmovilizado());
		reclamo.setPeligroso(reclamoDTO.getPeligroso());
		reclamo.setDescripcion(reclamoDTO.getDescripcion());
		ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
		if (reclamoDTO.getReclamante()!=null)
			reclamo.setReclamante(reclamanteAssemb.getReclamante(reclamoDTO.getReclamante()));
		UsuarioAssembler usuarioAssemb = new UsuarioAssembler(accesoBD);
		if (reclamoDTO.getUsuario()!=null)
			reclamo.setUsuario(usuarioAssemb.getUsuario(reclamoDTO.getUsuario()));
		VehiculoAssembler vehiculoAssemb = new VehiculoAssembler(accesoBD);
		if (reclamoDTO.getVehiculo()!=null)
			reclamo.setVehiculo(vehiculoAssemb.getVehiculo(reclamoDTO.getVehiculo()));
		OrdenAssembler ordenAssemb = new OrdenAssembler(accesoBD);
		if(reclamoDTO.getOrden()!=null)
			reclamo.setOrden(ordenAssemb.getOrden(reclamoDTO.getOrden()));
		RegistranteAssembler registranteAssemb = new RegistranteAssembler(accesoBD);
		if(reclamoDTO.getRegistrante()!=null)
			reclamo.setRegistrante(registranteAssemb.getRegistrante(reclamoDTO.getRegistrante()));
		return reclamo;
	}

}