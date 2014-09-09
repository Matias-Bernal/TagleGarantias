package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Mano_Obra;

import common.DTOs.Mano_ObraDTO;

public class Mano_ObraAssembler {

	private AccesoBD accesoBD;
	
	public Mano_ObraAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public Mano_ObraDTO getMano_ObraDTO(Mano_Obra mano_obra) {
		Mano_ObraDTO mano_obraDTO = new Mano_ObraDTO();
		mano_obraDTO.setId(mano_obra.getId());
		mano_obraDTO.setCodigo_mano_obra(mano_obra.getCodigo_mano_obra());
		mano_obraDTO.setCantidad_horas(mano_obra.getCantidad_horas());
		mano_obraDTO.setValor_mano_obra(mano_obra.getValor_mano_obra());
		ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
		if (mano_obra.getReclamo()!=null)
			mano_obraDTO.setReclamo(reclamoAssemb.getReclamoDTO(mano_obra.getReclamo()));
		return mano_obraDTO;
	}

	public Mano_Obra getMano_Obra(Mano_ObraDTO mano_obraDTO) {
		Mano_Obra mano_obra = (Mano_Obra) accesoBD.buscarPorId(Mano_Obra.class, mano_obraDTO.getId());
		mano_obra.setId(mano_obraDTO.getId());
		mano_obra.setCodigo_mano_obra(mano_obraDTO.getCodigo_mano_obra());
		mano_obra.setCantidad_horas(mano_obraDTO.getCantidad_horas());
		mano_obra.setValor_mano_obra(mano_obraDTO.getValor_mano_obra());
		ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
		if (mano_obraDTO.getReclamo()!=null)
			mano_obra.setReclamo(reclamoAssemb.getReclamo(mano_obraDTO.getReclamo()));
		return mano_obra;
	}

	public Mano_Obra getMano_ObraNueva(Mano_ObraDTO mano_obraDTO) {
		Mano_Obra mano_obra = new Mano_Obra();
		mano_obra.setId(mano_obraDTO.getId());
		mano_obra.setCodigo_mano_obra(mano_obraDTO.getCodigo_mano_obra());
		mano_obra.setCantidad_horas(mano_obraDTO.getCantidad_horas());
		mano_obra.setValor_mano_obra(mano_obraDTO.getValor_mano_obra());
		ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
		if (mano_obraDTO.getReclamo()!=null)
			mano_obra.setReclamo(reclamoAssemb.getReclamo(mano_obraDTO.getReclamo()));
		return mano_obra;
	}

}