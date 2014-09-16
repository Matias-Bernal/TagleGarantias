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
package cliente;
public class Main extends Thread { 
	
	private static  MediadorPrincipal mediadorPrinciapal;
  
    public static void main(String[] args) throws Exception {
    	setMediadorPrinciapal(new MediadorPrincipal());
    }
    
	@Override
	public void run (){
		try {
			mediadorPrinciapal = new MediadorPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static MediadorPrincipal getMediadorPrinciapal() {
		return mediadorPrinciapal;
	}

	public static void setMediadorPrinciapal(MediadorPrincipal mediadorPrinciapal) {
		Main.mediadorPrinciapal = mediadorPrinciapal;
	}
}