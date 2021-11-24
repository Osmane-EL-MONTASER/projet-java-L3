/**
 * 
 */
package project.crew;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author EL MONTASER Osmane
 *
 */
public class Attribution {
	public StringProperty pirateName;
    public StringProperty treasureName;
    
    public Attribution(String pirateName, String treasureName) {
    	this.pirateName = new SimpleStringProperty(pirateName);
    	this.treasureName = new SimpleStringProperty(treasureName);
    }
    
    public String getPirateName() {
        return this.pirateName.get();
    }
 
    public void setPirateName(String pirateName) {
        this.pirateName.set(pirateName);
    }
 
    public String getTreasureName() {
        return this.treasureName.get();
    }
 
    public void setTreasureName(String treasureName) {
        this.treasureName.set(treasureName);
    }
    
    public StringProperty treasureNameProperty() {
        return this.treasureName;
    }
    
    public StringProperty pirateNameProperty() {
        return this.pirateName;
    }
}
