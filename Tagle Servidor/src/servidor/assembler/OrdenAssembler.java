package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Orden;

import common.DTOs.OrdenDTO;

public class OrdenAssembler {
	
	private AccesoBD accesoBD;

	public OrdenAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public OrdenDTO getOrdenDTO(Orden orden) {
		OrdenDTO ordenDTO = new OrdenDTO();
		ordenDTO.setId(orden.getId());
		ordenDTO.setNumero_orden(orden.getNumero_orden());
		ordenDTO.setFecha_cierre(orden.getFecha_cierre());
		ordenDTO.setFecha_apertura(orden.getFecha_apertura());
		ordenDTO.setEstado(orden.getEstado());
		RecursoAssembler recursoAssemb = new RecursoAssembler(accesoBD);
		if (orden.getRecurso()!=null)
			ordenDTO.setRecurso(recursoAssemb.getRecursoDTO(orden.getRecurso()));
		return ordenDTO;
	}

	public Orden getOrden(OrdenDTO ordenDTO) {
		Orden orden = (Orden) accesoBD.buscarPorId(Orden.class, ordenDTO.getId());
		orden.setId(ordenDTO.getId());
		orden.setNumero_orden(ordenDTO.getNumero_orden());
		orden.setFecha_cierre(ordenDTO.getFecha_cierre());
		orden.setFecha_apertura(ordenDTO.getFecha_apertura());
		orden.setEstado(ordenDTO.getEstado());
		RecursoAssembler recursoAssemb = new RecursoAssembler(accesoBD);
		if (ordenDTO.getRecurso()!=null)
			orden.setRecurso(recursoAssemb.getRecurso(ordenDTO.getRecurso()));
		return orden;
	}
	
	public Orden getOrdenNueva(OrdenDTO ordenDTO) {
		Orden orden = new Orden();
		orden.setId(ordenDTO.getId());
		orden.setNumero_orden(ordenDTO.getNumero_orden());
		orden.setFecha_cierre(ordenDTO.getFecha_cierre());
		orden.setFecha_apertura(ordenDTO.getFecha_apertura());
		orden.setEstado(ordenDTO.getEstado());
		RecursoAssembler recursoAssemb = new RecursoAssembler(accesoBD);
		if (ordenDTO.getRecurso()!=null)
			orden.setRecurso(recursoAssemb.getRecurso(ordenDTO.getRecurso()));
		return orden;
	}

}
