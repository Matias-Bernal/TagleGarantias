
/********************************************************
  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *********************************************************/
package cliente.GestionarNotificacion;

import javax.swing.JOptionPane;

import cliente.MediadorAccionesIniciarPrograma;
import cliente.MediadorPrincipal;
import common.DTOs.Notificacion_ReclamoDTO;
import common.DTOs.Notificacion_UsuarioDTO;
import common.DTOs.UsuarioDTO;
import common.GestionarNotificacion.IControlNotificacion;
import common.GestionarNotificacion_Reclamo.IControlNotificacion_Reclamo;
import common.GestionarNotificacion_Usuario.IControlNotificacion_Usuario;
import common.GestionarReclamo.IControlReclamo;

public class MediadorEjecutarNotificacion extends Thread {

	protected MediadorPrincipal mediador_principal;
	private GUINotificacion guiNotificacion;
	private Notificacion_ReclamoDTO notificacion_reclamo;
	private long tiempoInicial;
	private UsuarioDTO usuario;
	
	private boolean posponer;
	private boolean completada;
	private boolean guardada;
	
	public MediadorEjecutarNotificacion(MediadorPrincipal mediador_principal, Notificacion_ReclamoDTO notificacion_reclamo,long tiempoInicial,UsuarioDTO usuario){
		this.setMediador_principal(mediador_principal);
		this.tiempoInicial= tiempoInicial;
		this.notificacion_reclamo = notificacion_reclamo;
		this.usuario = usuario;
		setPosponer(false);
		setCompletada(false);
		setGuardada(false);
		guiNotificacion = new GUINotificacion(this,notificacion_reclamo.getNotificacion().getTipo_notificacion(),notificacion_reclamo.getNotificacion().getTexto_notificacion());
	}
	
	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	public void run (){
		try {
			synchronized (this){
				this.sleep(tiempoInicial);
				if(!posponer && !completada && !guardada)
					guiNotificacion.setVisible(true);
				while (!completada){ // no completada la notificacion
					if(posponer){ // si se preciono posponer
						guiNotificacion.setVisible(false);
						this.sleep(900000);
						guiNotificacion.setVisible(true);
						guiNotificacion.toFront();
						setPosponer(false);
					}else{	// no se presiono posponer
						if(!completada || !posponer)
							this.wait();
					}
				}
				if(!guardada){
					IControlNotificacion iControlNotificacion = MediadorAccionesIniciarPrograma.getControlNotificacion();
					IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
					IControlNotificacion_Reclamo iControlNotificacion_Reclamo = MediadorAccionesIniciarPrograma.getControlNotificacion_Reclamo();
					IControlNotificacion_Usuario iControlNotificacion_Usuario = MediadorAccionesIniciarPrograma.getControlNotificacion_Usuario();
					notificacion_reclamo.getNotificacion().setId(iControlNotificacion.agregarNotificacion(notificacion_reclamo.getNotificacion()));
					notificacion_reclamo.setReclamo(iControlReclamo.buscarReclamo(notificacion_reclamo.getReclamo().getId()));
					notificacion_reclamo.setId(iControlNotificacion_Reclamo.agregarNotificacionReclamo(notificacion_reclamo));
					Notificacion_UsuarioDTO notificacion_usuarioDTO = new Notificacion_UsuarioDTO();
					notificacion_usuarioDTO.setUsuario(usuario);
					notificacion_usuarioDTO.setNotificacion(notificacion_reclamo.getNotificacion());
					iControlNotificacion_Usuario.agregarNotificacion_Usuario(notificacion_usuarioDTO);
					JOptionPane.showMessageDialog(null,"Notificacion Guardada","Advertencia",JOptionPane.INFORMATION_MESSAGE);
					guiNotificacion.dispose();
					mediador_principal.borrarNotificacion(notificacion_reclamo);
				}
				this.stop();

			}
		} catch (InterruptedException ie) {
			
		} catch (Exception e) {
			   // log the exception or process it.
			   // re-throw the original exception without the compiler complaining.
			   Thread.currentThread().stop(e);
		}
	}

	public boolean isCompletada() {
		return completada;
	}

	public void setCompletada(boolean completada) {
		this.completada = completada;
	}

	public boolean isPosponer() {
		return posponer;
	}

	public void setPosponer(boolean posponer) {
		this.posponer = posponer;
	}

	public MediadorPrincipal getMediador_principal() {
		return mediador_principal;
	}

	public void setMediador_principal(MediadorPrincipal mediador_principal) {
		this.mediador_principal = mediador_principal;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
	@SuppressWarnings("deprecation")
	public void destruir(){
		if (this.getState().equals(Thread.State.TIMED_WAITING)){
			this.interrupt();
		}else{
			stop();
		}
		guiNotificacion.dispose();
	}

	public Notificacion_ReclamoDTO getNotificacion_reclamo() {
		return notificacion_reclamo;
	}

	public void setNotificacion_reclamo(Notificacion_ReclamoDTO notificacion_reclamo) {
		this.notificacion_reclamo = notificacion_reclamo;
	}

	public void verGuiNotificacion() {
		guiNotificacion.setVisible(true);
		guiNotificacion.toFront();	
	}
	
	public void completada(){
		if (this.getState().equals(Thread.State.TIMED_WAITING)){
			try {
				setCompletada(true);
				IControlNotificacion iControlNotificacion = MediadorAccionesIniciarPrograma.getControlNotificacion();
				IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
				IControlNotificacion_Reclamo iControlNotificacion_Reclamo = MediadorAccionesIniciarPrograma.getControlNotificacion_Reclamo();
				IControlNotificacion_Usuario iControlNotificacion_Usuario = MediadorAccionesIniciarPrograma.getControlNotificacion_Usuario();
				notificacion_reclamo.getNotificacion().setId(iControlNotificacion.agregarNotificacion(notificacion_reclamo.getNotificacion()));
				notificacion_reclamo.setReclamo(iControlReclamo.buscarReclamo(notificacion_reclamo.getReclamo().getId()));
				notificacion_reclamo.setId(iControlNotificacion_Reclamo.agregarNotificacionReclamo(notificacion_reclamo));
				Notificacion_UsuarioDTO notificacion_usuarioDTO = new Notificacion_UsuarioDTO();
				notificacion_usuarioDTO.setUsuario(usuario);
				notificacion_usuarioDTO.setNotificacion(notificacion_reclamo.getNotificacion());
				iControlNotificacion_Usuario.agregarNotificacion_Usuario(notificacion_usuarioDTO);
				JOptionPane.showMessageDialog(null,"Notificacion Guardada","Advertencia",JOptionPane.INFORMATION_MESSAGE);
				guiNotificacion.dispose();
				mediador_principal.borrarNotificacion(notificacion_reclamo);
				setGuardada(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			synchronized (this){
				setCompletada(true);
				this.notify();
			}
		}
	}

	public void posponer() {
		if (this.getState().equals(Thread.State.TIMED_WAITING)){
			setPosponer(true);
		}else{
			synchronized (this){
				setPosponer(true);
				this.notify();
			}
		}
	}

	public boolean isGuardada() {
		return guardada;
	}

	public void setGuardada(boolean guardada) {
		this.guardada = guardada;
	}
}
