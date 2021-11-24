package project.crew;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author EL MONTASER Osmane
 *
 */
public class Preference {
	public StringProperty treasureName;
    public StringProperty order;
    
    public Preference(String treasureName, String order) {
    	this.treasureName = new SimpleStringProperty(treasureName);
    	this.order = new SimpleStringProperty(order);
    }
    
    public String getTreasureName() {
        return this.treasureName.get();
    }
 
    public void setTreasureName(String treasureName) {
        this.treasureName.set(treasureName);
    }
 
    public String getOrder() {
        return this.order.get();
    }
 
    public void setOrder(String order) {
        this.order.set(order);
    }
    
    public StringProperty orderProperty() {
        return this.order;
    }
    
    public StringProperty treasureNameProperty() {
        return this.treasureName;
    }
}
