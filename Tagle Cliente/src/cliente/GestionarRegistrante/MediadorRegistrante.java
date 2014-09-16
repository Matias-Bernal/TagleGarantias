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
package cliente.GestionarRegistrante;

import java.util.Vector;

import cliente.MediadorAccionesIniciarPrograma;
import cliente.MediadorPrincipal;

import common.DTOs.AgenteDTO;
import common.DTOs.EntidadDTO;
import common.DTOs.RegistranteDTO;
import common.GestionarAgente.IControlAgente;
import common.GestionarEntidad.IControlEntidad;
import common.GestionarRegistrante.IControlRegistrante;

public class MediadorRegistrante {

	protected GUIAltaRegistrante guiAltaRegistrante;
	protected GUIGestionRegistrante guiGestionRegistrante;
	protected MediadorPrincipal mediadorPrincipal;
	protected GUIModificarRegistrante guimodificarRegistrante; 

	public MediadorRegistrante(MediadorPrincipal mediadorPrincipal){
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void altaRegistrante(){
		guiAltaRegistrante = new GUIAltaRegistrante(this);
		guiAltaRegistrante.setVisible(true);
	}
	public void altaRegistrante(String nombre_usuario, String tipo){
		guiAltaRegistrante = new GUIAltaRegistrante(this,nombre_usuario,tipo);
		guiAltaRegistrante.setVisible(true);
	}
	
	public void gestioRegistrante() {
		guiGestionRegistrante = new GUIGestionRegistrante(this);
		guiGestionRegistrante.setVisible(true);
	}
	
	public boolean nuevoRegistrante(String nombre_registrante,String tipo){
		boolean result = false;
		try {
			IControlRegistrante iControlRegistrante = MediadorAccionesIniciarPrograma.getControlRegistrante();
			if (!iControlRegistrante.existeRegistrante(nombre_registrante)){
				if (tipo.equals("Agente")){
					AgenteDTO registrante = new AgenteDTO(nombre_registrante);
					IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
					if (! iControlAgente.existeAgente(nombre_registrante)){
						iControlAgente.agregarAgente(registrante);
					}
				}else{	
					EntidadDTO registrante = new EntidadDTO(nombre_registrante);
					IControlEntidad iControlEntidad = MediadorAccionesIniciarPrograma.getControlEntidad();
					if (! iControlEntidad.existeEntidad(nombre_registrante)){
						iControlEntidad.agregarEntidad(registrante);
					}
				}
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al insertar registrante en la clase MediadorRegistrante");
			e.printStackTrace();
		}
		actualizarDatosGestion();
		return result;
		
	}

	public Vector<RegistranteDTO> obtenerRegistrantes(){
		IControlRegistrante iControlRegistrante = MediadorAccionesIniciarPrograma.getControlRegistrante();
		Vector<RegistranteDTO> registrantes = new Vector<RegistranteDTO> ();
		try {
			registrantes = iControlRegistrante.obtenerRegistrantes();
		} catch (Exception e) {
			System.out.println("Error al cargar los registrantess en la clase MediadorRegistrantes");
			e.printStackTrace();
		}
		return registrantes;
	}
	
	public void actualizarDatosGestion(){
		if (guiGestionRegistrante != null)
			guiGestionRegistrante.actualizarDatos();
	}

	public boolean eliminarAgente(Long id) {
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
		boolean result = false;
		try {
			if (iControlAgente.existeAgente(id)){
				iControlAgente.eliminarAgente(id);
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al eliminar agente en la clase Mediadorregsitrante");
			e.printStackTrace();
		}
		//actualizarDatosGestion();
		return result;
	}
	
	public void modificarAgente(Long id) {
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
		try {
			if (iControlAgente.existeAgente(id)){
				AgenteDTO agente = iControlAgente.buscarAgente(id);
				guimodificarRegistrante = new GUIModificarRegistrante(this,agente);
				guimodificarRegistrante.setVisible(true);
			}
		} catch (Exception e) {
			System.out.println("Error al modificar agente en la clase MediadorUsuario");
			e.printStackTrace();
		}
	}
	public boolean modificarAgente(AgenteDTO agenteDTO) {
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
		boolean result = false;
		try {
			if (iControlAgente.existeAgente(agenteDTO.getId())){
				iControlAgente.modificarAgente(agenteDTO.getId(), agenteDTO);
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al loguear usuario en la clase MediadorUsuario");
			e.printStackTrace();
		}
		//actualizarDatosGestion();
		return result;
	}
	
	public boolean eliminarEntidad(Long id) {
		IControlEntidad iControlEntidad = MediadorAccionesIniciarPrograma.getControlEntidad();
		boolean result = false;
		try {
			if (iControlEntidad.existeEntidad(id)){
				iControlEntidad.eliminarEntidad(id);
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al eliminar entidad en la clase Mediadorregsitrante");
			e.printStackTrace();
		}
		//actualizarDatosGestion();
		return result;
	}

	public void modificarEntidad(Long id) {
		IControlEntidad iControlEntidad = MediadorAccionesIniciarPrograma.getControlEntidad();
		try {
			if (iControlEntidad.existeEntidad(id)){
				EntidadDTO entidad = iControlEntidad.buscarEntidad(id);
				guimodificarRegistrante = new GUIModificarRegistrante(this,entidad);
				guimodificarRegistrante.setVisible(true);
			}
		} catch (Exception e) {
			System.out.println("Error al modificar registrante en la clase MediadorUsuario");
			e.printStackTrace();
		}
	}
	
	public boolean modificarEntidad(EntidadDTO entidadDTO) {
		IControlEntidad iControlEntidad = MediadorAccionesIniciarPrograma.getControlEntidad();
		boolean result = false;
		try {
			if (iControlEntidad.existeEntidad(entidadDTO.getId())){
				iControlEntidad.modificarEntidad(entidadDTO.getId(), entidadDTO);
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al modificar entidad en la clase MediadorRegistrante");
			e.printStackTrace();
		}
		return result;
	}

	public boolean esAgente(String id) {
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
		boolean res = false;
		try {
			res =  iControlAgente.existeAgente(new Long(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}

