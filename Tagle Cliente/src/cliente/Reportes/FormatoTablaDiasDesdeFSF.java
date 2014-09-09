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
package cliente.Reportes;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class FormatoTablaDiasDesdeFSF extends DefaultTableCellRenderer{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int columna_patron ;

    public FormatoTablaDiasDesdeFSF(int Colpatron)
    {
        this.columna_patron = Colpatron;
    }

    @Override
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column )
    {        
        setBackground(Color.white);//color de fondo
        table.setForeground(Color.black);//color de texto
        //Si la celda corresponde a los dias desde fsf esta entre [7;15), se cambia el color de fondo a amarillo
        if(Float.parseFloat(table.getValueAt(row,columna_patron).toString())>=7 && Float.parseFloat(table.getValueAt(row,columna_patron).toString())<15)
        {
            setBackground(Color.yellow);
        }else{
            //Si la celda corresponde a los dias desde fsf es mayor igual a 15), se cambia el color de fondo a rojo
        	if(Float.parseFloat(table.getValueAt(row,columna_patron).toString())>=15)
        		setBackground(Color.red);
        }
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
 }

}