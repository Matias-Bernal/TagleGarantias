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
package cliente.GestionarReclamante;

import java.util.Vector;

import cliente.MediadorAccionesIniciarPrograma;
import cliente.MediadorPrincipal;
import common.DTOs.MTelefonoDTO;
import common.DTOs.ReclamanteDTO;
import common.GestionarMTelefono.IControlMTelefono;
import common.GestionarReclamante.IControlReclamante;

public class MediadorReclamante {

	protected GUIAltaReclamante guiAltaReclamante;
	protected GUIGestionReclamante guiGestionReclamante;
	protected MediadorPrincipal mediadorPrincipal;
	protected GUIVerReclamante verReclamante;
	protected GUIModificarReclamante guiModificarRegistrante; 

	public MediadorReclamante(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void altaReclamante() {
		guiAltaReclamante = new GUIAltaReclamante(this);
		guiAltaReclamante.setVisible(true);
	}
	public void altaReclamante(String nombre_apellido, String email, String dni) {
		guiAltaReclamante = new GUIAltaReclamante(this, nombre_apellido, email, dni);
		guiAltaReclamante.setVisible(true);

	}

	public void gestionReclamante() {
		guiGestionReclamante = 	new GUIGestionReclamante(this);
		guiGestionReclamante.setVisible(true);
	}

	public boolean nuevoReclamante(String nombre_apellido, String dni, String email, Vector<String> telefonos) {
		boolean result = false;
		try {
			IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
			if (!iControlReclamante.existeReclamanteDni(dni)){
				
				ReclamanteDTO reclamante = new ReclamanteDTO(nombre_apellido, dni, email);
				reclamante.setId(iControlReclamante.agregarReclamante(reclamante));
				
				IControlMTelefono iControlMTelefono = MediadorAccionesIniciarPrograma.getControlMTelefono();
				for (int i = 0; i<telefonos.size();i++){
					
					if (!iControlMTelefono.existeMTelefono(reclamante, telefonos.elementAt(i))){
						MTelefonoDTO mtelefono = new MTelefonoDTO(reclamante, telefonos.elementAt(i));
						iControlMTelefono.agregarMTelefono(mtelefono);
					}
				}	
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al insertar reclamante en la clase MediadorReclamante");
			e.printStackTrace();
		}
		actualizarDatosGestion();
		return result;
	}

	public Vector<ReclamanteDTO> obtenerReclamantes() {
		IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
		Vector<ReclamanteDTO> reclamantes = new Vector<ReclamanteDTO> ();
		try {
			reclamantes = iControlReclamante.obtenerReclamantes();
		} catch (Exception e) {
			System.out.println("Error al cargar los reclamantes en la clase MediadorReclamantes");
			e.printStackTrace();
		}
		return reclamantes;
	}
	
	public void actualizarDatosGestion(){
		if (guiGestionReclamante != null)
			guiGestionReclamante.actualizarDatos();
	}

	public boolean eliminarReclamante(Long id) {
		IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
		boolean result = false;
		try {
			if (iControlReclamante.existeReclamante(id)){
//				ReclamanteDTO reclamante = iControlReclamante.buscarReclamante(id);
//				IControlMTelefono iControlMTelefono = MediadorAccionesIniciarPrograma.getControlMTelefono();
//				Vector<MTelefonoDTO> telefonosDTO = iControlMTelefono.obtenerMTelefono(reclamante);
//				for (int i = 0; i< telefonosDTO.size(); i++){
//					iControlMTelefono.eliminarMTelefono(telefonosDTO.elementAt(i).getId());
//				}
				iControlReclamante.eliminarReclamante(id);
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al eliminar reclamante en la clase MediadorReclamante");
			e.printStackTrace();
		}
		//actualizarDatosGestion();
		return result;
	}

	public void modificarReclamante(Long id) {
		IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
		try {
			if (iControlReclamante.existeReclamante(id)){
				ReclamanteDTO reclamante = iControlReclamante.buscarReclamante(id);
				
				IControlMTelefono iControlMTelefono = MediadorAccionesIniciarPrograma.getControlMTelefono();

				Vector<MTelefonoDTO> telefonosDTO = iControlMTelefono.obtenerMTelefono(reclamante);
				Vector<String>	telefonos = new Vector<String>();

				for (int i = 0; i< telefonosDTO.size(); i++){
					telefonos.add(telefonosDTO.elementAt(i).getTelefono());
				}
				
				guiModificarRegistrante = new GUIModificarReclamante(this,reclamante,telefonos);
				guiModificarRegistrante.setVisible(true);
			}
		} catch (Exception e) {
			System.out.println("Error al modificar registrante en la clase MediadorUsuario");
			e.printStackTrace();
		}
	}
	
	public boolean modificar(ReclamanteDTO reclamante, Vector<String> telefonos) {
		IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
		boolean result = false;
		try {
			if (iControlReclamante.existeReclamante(reclamante.getId())){
				iControlReclamante.modificarReclamante(reclamante.getId(), reclamante);
				IControlMTelefono iControlMTelefono = MediadorAccionesIniciarPrograma.getControlMTelefono();

				Vector<MTelefonoDTO> telefonosDTO = iControlMTelefono.obtenerMTelefono(reclamante);
				
				for (int i = 0; i< telefonosDTO.size(); i++){
					if (!telefonos.contains(telefonosDTO.elementAt(i).getTelefono())){
						iControlMTelefono.eliminarMTelefono(telefonosDTO.elementAt(i).getId());
					}else{
						telefonos.remove(telefonosDTO.elementAt(i).getTelefono());
					}
				}
				for (int i = 0; i< telefonos.size(); i++){
					MTelefonoDTO mtelefono = new MTelefonoDTO(reclamante, telefonos.elementAt(i));
					iControlMTelefono.agregarMTelefono(mtelefono);
				}
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al modificar reclamante en la clase MediadorReclamante");
			e.printStackTrace();
		}
		//actualizarDatosGestion();
		return result;

	}
	
	public void verRegistrante(Long id) {
		IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
		try {
			if (iControlReclamante.existeReclamante(id)){
				ReclamanteDTO reclamante = iControlReclamante.buscarReclamante(id);
				
				IControlMTelefono iControlMTelefono = MediadorAccionesIniciarPrograma.getControlMTelefono();

				Vector<MTelefonoDTO> telefonosDTO = iControlMTelefono.obtenerMTelefono(reclamante);
				Vector<String>	telefonos = new Vector<String>();

				for (int i = 0; i< telefonosDTO.size(); i++){
					telefonos.add(telefonosDTO.elementAt(i).getTelefono());
				}
				verReclamante = new GUIVerReclamante(this,reclamante.getNombre_apellido(),reclamante.getEmail(),reclamante.getDni(),telefonos);
				verReclamante.SetVisible(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
