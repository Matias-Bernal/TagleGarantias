package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.MTelefono;

import common.DTOs.MTelefonoDTO;

public class MTelefonoAssembler {

	private AccesoBD accesoBD;
	
	public MTelefonoAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public MTelefonoDTO getMTelefonoDTO(MTelefono mTelefono) {
		MTelefonoDTO mTelefonoDTO = new MTelefonoDTO();
		mTelefonoDTO.setId(mTelefono.getId());
		mTelefonoDTO.setTelefono(mTelefono.getTelefono());
		ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
		if (mTelefono.getReclamante()!=null)
			mTelefonoDTO.setReclamante(reclamanteAssemb.getReclamanteDTO(mTelefono.getReclamante()));
		return mTelefonoDTO;
	}

	public MTelefono getMTelefono(MTelefonoDTO mTelefonoDTO) {
		MTelefono mTelefono = (MTelefono) accesoBD.buscarPorId(MTelefono.class, mTelefonoDTO.getId());
		mTelefono.setId(mTelefonoDTO.getId());
		mTelefono.setTelefono(mTelefonoDTO.getTelefono());
		ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
		if (mTelefonoDTO.getReclamante()!=null)
			mTelefono.setReclamante(reclamanteAssemb.getReclamante(mTelefonoDTO.getReclamante()));
		return mTelefono;
	}

	public MTelefono getMTelefonoNuevo(MTelefonoDTO mTelefonoDTO) {
		MTelefono mTelefono = new MTelefono();
		mTelefono.setId(mTelefonoDTO.getId());
		mTelefono.setTelefono(mTelefonoDTO.getTelefono());
		ReclamanteAssembler reclamanteAssemb = new ReclamanteAssembler(accesoBD);
		if (mTelefonoDTO.getReclamante()!=null)
			mTelefono.setReclamante(reclamanteAssemb.getReclamante(mTelefonoDTO.getReclamante()));
		return mTelefono;
	}

}