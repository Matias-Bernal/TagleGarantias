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

import cliente.MediadorPrincipal;

import common.DTOs.UsuarioDTO;

public class MediadorNotificacion {

	private MediadorPrincipal mediador;
	private GUIGestionNotificacion guiGestionNotificacion;
	
	public MediadorNotificacion(MediadorPrincipal mediadorPrincipal) {
		this.mediador = mediadorPrincipal;
	}

	public void gestionNotificacion() {
		guiGestionNotificacion = new GUIGestionNotificacion(this, mediador.getUsuario());
		guiGestionNotificacion.setVisible(true);	
	}

	public void actualizarNotificaciones() {
		mediador.actualizarTablaNotificaciones();
	}
	
	public boolean esAdministrativo (UsuarioDTO usuario){
		return usuario.getTipo().equals("Administrativo");
	}

	public boolean setTiposNotificaciones(boolean turnos, boolean contencion, boolean agentes, boolean reclamos, boolean sugerencias) {
		mediador.setTiposNotificaciones(turnos,contencion,agentes, reclamos,sugerencias);
		return true;
	}

}
