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
package cliente.GestionarVehiculo;

import java.util.Vector;

import cliente.MediadorAccionesIniciarPrograma;
import cliente.MediadorPrincipal;

import common.DTOs.MarcaDTO;
import common.DTOs.ModeloDTO;
import common.DTOs.VehiculoDTO;
import common.GestionarMarca.IControlMarca;
import common.GestionarModelo.IControlModelo;
import common.GestionarVehiculo.IControlVehiculo;

public class MediadorVehiculo {
	
	protected GUIAltaVehiculo guiAltaVehiculo;
	protected GUIGestionVehiculo guiGestionVehiculo;
	protected MediadorPrincipal mediadorPrincipal;
	protected GUIModificarVehiculo  guiModificarVehiculo;

	public MediadorVehiculo(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}

	public void altaVehiculo() {
		guiAltaVehiculo = new GUIAltaVehiculo(this);
		guiAltaVehiculo.setVisible(true);
	}
	public void altaVehiculo(String nombre_titular, String dominio, String vin, String marca, String modelo) {
		guiAltaVehiculo = new GUIAltaVehiculo(this, nombre_titular, dominio, vin, marca, modelo);
		guiAltaVehiculo.setVisible(true);
	}
	
	public void gestionVehiculo() {
		guiGestionVehiculo = new GUIGestionVehiculo(this);
		guiGestionVehiculo.setVisible(true);
	}

	public boolean nuevoVehiculo(String nombre_titular, String dominio, String vin, String marca, String modelo){
		boolean result = false;
		try {
			IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
			if (!iControlVehiculo.existeVehiculo(dominio)){
				IControlMarca iControlMarca = MediadorAccionesIniciarPrograma.getControlMarca();
				if (iControlMarca.existeMarca(marca)){
					IControlModelo iControlModelo = MediadorAccionesIniciarPrograma.getControlModelo();
					
					ModeloDTO modeloDTO = iControlModelo.buscarModelo(modelo);
					VehiculoDTO vehiculo = new VehiculoDTO(dominio, vin, nombre_titular, modeloDTO.getMarca(), modeloDTO);
					iControlVehiculo.agregarVehiculo(vehiculo);
					
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

	public Vector<VehiculoDTO> obtenerVehiculos(){
		IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
		Vector<VehiculoDTO> vehiculos = new Vector<VehiculoDTO> ();
		try {
			vehiculos = iControlVehiculo.obtenerVehiculos();
		} catch (Exception e) {
			System.out.println("Error al cargar los registrantess en la clase MediadorRegistrantes");
			e.printStackTrace();
		}
		return vehiculos;
	}
	
	public void actualizarDatosGestion() {
		if (guiGestionVehiculo != null)
			guiGestionVehiculo.actualizarDatos();
	}

	public boolean eliminarVehiculo(Long id) {
		IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
		boolean result = false;
		try {
			if (iControlVehiculo.existeVehiculo(id)){
				iControlVehiculo.eliminarVehiculo(id);
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al eliminar reclamante en la clase MediadorReclamante");
			e.printStackTrace();
		}
		//actualizarDatosGestion();
		return result;
	}

	public void modificarVehiculo(Long id) {
		IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
		try {
			if (iControlVehiculo.existeVehiculo(id)){
				VehiculoDTO vehiculo = iControlVehiculo.buscarVehiculo(id);
				guiModificarVehiculo = new GUIModificarVehiculo(this,vehiculo);
				guiModificarVehiculo.setVisible(true);
			}
		} catch (Exception e) {
			System.out.println("Error al modificar registrante en la clase MediadorUsuario");
			e.printStackTrace();
		}
	}
	
	public boolean modificar(VehiculoDTO vehiculo) {
		IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
		boolean result = false;
		try {
			if (iControlVehiculo.existeVehiculo(vehiculo.getId())){ // controlar dominio repetido
				IControlMarca iControlMarca = MediadorAccionesIniciarPrograma.getControlMarca();
				if (iControlMarca.existeMarca(vehiculo.getMarca().getNombre_marca())){
					IControlModelo iControlModelo = MediadorAccionesIniciarPrograma.getControlModelo();
					
					ModeloDTO modeloDTO = iControlModelo.buscarModelo(vehiculo.getModelo().getNombre_modelo());
					vehiculo.setModelo(modeloDTO);
					vehiculo.setMarca(modeloDTO.getMarca());
					
					iControlVehiculo.modificarVehiculo(vehiculo.getId(), vehiculo);
					result = true;
				}
			}
		} catch (Exception e) {
			System.out.println("Error al modificar reclamante en la clase MediadorReclamante");
			e.printStackTrace();
		}
		//actualizarDatosGestion();
		return result;

	}

	public Vector<String> obtenerMarcas() {
		Vector<String> nombre_marcas = new Vector<String>();
		try {
			IControlMarca iControlMarca = MediadorAccionesIniciarPrograma.getControlMarca();
			Vector<MarcaDTO> marcasDTO = iControlMarca.obtenerMarcas();
			for (int i=0; i< marcasDTO.size();i++){
				nombre_marcas.add(marcasDTO.elementAt(i).getNombre_marca());
			}
		} catch (Exception e) {
			System.out.println("Error al obtener marcas en vehiculo en la clase MediadorVehiculo");
			e.printStackTrace();
		}
		return nombre_marcas;
	}

	public Vector<String> obtenerModelos() {
		Vector<String> nombre_modelos = new Vector<String>();
		try {
			IControlModelo iControlModelo = MediadorAccionesIniciarPrograma.getControlModelo();
			Vector<ModeloDTO> modelosDTO = iControlModelo.obtenerModelos();
			for (int i=0; i< modelosDTO.size();i++){
				nombre_modelos.add(modelosDTO.elementAt(i).getNombre_modelo());
			}
		} catch (Exception e) {
			System.out.println("Error al obtener modelos en vehiculo en la clase MediadorVehiculo");
			e.printStackTrace();
		}
		return nombre_modelos;
	}


}
