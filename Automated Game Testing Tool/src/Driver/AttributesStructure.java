/*This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package Driver;


/**
 * Structure to  Variables 
 * 
 * @author
 *
 */
public class AttributesStructure{
    private String attributeName;
    private String attributeType;
    private String attributeVisibility;
    private String direction;
    private Object value;
	
    
	public AttributesStructure(String name, String type, String visibility,
			Object value) {
		super();
		attributeName = name;
		attributeType = type;
		attributeVisibility = visibility;
		this.value = value;
	}
   
	public AttributesStructure() {
		attributeName = "";
		attributeType = "";
		attributeVisibility = "";
	}
	public AttributesStructure(String variableName, String variableType, String variableVisibility) {
		this.attributeName = variableName;
		this.attributeType = variableType;
		this.attributeVisibility = variableVisibility;
	}
	
	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	public String getAttributeVisibility() {
		return attributeVisibility;
	}

	public void setAttributeVisibility(String attributeVisibility) {
		this.attributeVisibility = attributeVisibility;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		value = value;
	}

	public String printAttribute(){
		String variable = "";	
		variable+=this.attributeType+" "+this.attributeName;
		return variable;
		
	}
	public String printCompleteVariable(){
		String variable = "";
		variable+=this.attributeVisibility+" "+this.attributeType+" "+this.attributeName;
		return variable;
	}

}

