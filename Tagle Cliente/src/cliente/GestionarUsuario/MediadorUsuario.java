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
package cliente.GestionarUsuario;

import java.util.Vector;

import common.DTOs.UsuarioDTO;
import common.GestionarUsuario.IControlUsuario;
import cliente.MediadorAccionesIniciarPrograma;
import cliente.MediadorPrincipal;

public class MediadorUsuario {
	
	protected GUIAltaUsuario guiAltaUsuario;
	protected GUIModificarUsuario guiModificarUsuario;
	protected GUIGestionUsuario guiGestionUsuario;
	protected MediadorPrincipal mediadorPrincipal;
	

	public MediadorUsuario(MediadorPrincipal mediadorPrincipal){
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void altaUsuario(){
		guiAltaUsuario = new GUIAltaUsuario(this);
		guiAltaUsuario.setVisible(true);
	}
	public void altaUsuario(String nombre_usuario, String email, String Tipo){
		guiAltaUsuario = new GUIAltaUsuario(this,nombre_usuario,email, Tipo);
		guiAltaUsuario.setVisible(true);
	}
	
	public void gestioUsuario(){
		guiGestionUsuario = new GUIGestionUsuario(this);
		guiGestionUsuario.setVisible(true);
	}

	public boolean nuevoUsuario(String nombre_usuario, String clave , String email ,String tipo){
		IControlUsuario iControlUsuario = MediadorAccionesIniciarPrograma.getControlUsuarios();
		boolean result = false;
		try {
			if (! iControlUsuario.existeUsuario(nombre_usuario)){
				UsuarioDTO usuario = new UsuarioDTO(nombre_usuario, clave, email, tipo);
				iControlUsuario.agregarUsuario(usuario);
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al insertar usuario en la clase MediadorUsuario");
			e.printStackTrace();
		}
		actualizarDatosGestion();
		return result;
		
	}
	
	public Vector<UsuarioDTO> obtenerUsuarios(){
		IControlUsuario iControlUsuario = MediadorAccionesIniciarPrograma.getControlUsuarios();
		Vector<UsuarioDTO> usuarios = new Vector<UsuarioDTO> ();
		try {
			usuarios = iControlUsuario.obtenerUsuarios();
		} catch (Exception e) {
			System.out.println("Error al cargar los usuarios en la clase MediadorUsuario");
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public void actualizarDatosGestion(){
		if (guiGestionUsuario != null)
			guiGestionUsuario.actualizarDatos();
	}

	public boolean eliminarUsuario(Long id) {
		IControlUsuario iControlUsuario = MediadorAccionesIniciarPrograma.getControlUsuarios();
		boolean result = false;
		try {
			if (iControlUsuario.existeUsuario(id)){
				iControlUsuario.eliminarUsuario(id);
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al eliminar usuario en la clase MediadorUsuario");
			e.printStackTrace();
		}
		//actualizarDatosGestion();
		return result;
	}

	public void modificarUsuario(Long id) {
		IControlUsuario iControlUsuario = MediadorAccionesIniciarPrograma.getControlUsuarios();
		try {
			if (iControlUsuario.existeUsuario(id)){
				UsuarioDTO usuario = iControlUsuario.buscarUsuario(id);
				guiModificarUsuario = new GUIModificarUsuario(this,usuario);
				guiModificarUsuario.setVisible(true);
			}
		} catch (Exception e) {
			System.out.println("Error al modificar usuario en la clase MediadorUsuario");
			e.printStackTrace();
		}
	}

	public boolean login(String nombre_usuario, String clave) {
		IControlUsuario iControlUsuario = MediadorAccionesIniciarPrograma.getControlUsuarios();
		boolean result = false;
		try {
			if (iControlUsuario.existeUsuario(nombre_usuario)){
				result = iControlUsuario.login(nombre_usuario, clave);
			}
		} catch (Exception e) {
			System.out.println("Error al loguear usuario en la clase MediadorUsuario");
			e.printStackTrace();
		}
		//actualizarDatosGestion();
		return result;
	}

	public boolean modificar(UsuarioDTO usuariomodif) {
		IControlUsuario iControlUsuario = MediadorAccionesIniciarPrograma.getControlUsuarios();
		boolean result = false;
		try {
			if (iControlUsuario.existeUsuario(usuariomodif.getNombre_usuario())){
				iControlUsuario.modificarUsuario(iControlUsuario.buscarUsuario(usuariomodif.getNombre_usuario()).getId(), usuariomodif);;
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al loguear usuario en la clase MediadorUsuario");
			e.printStackTrace();
		}
		//actualizarDatosGestion();
		return result;

	}

}
