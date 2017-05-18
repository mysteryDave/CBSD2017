package net.java.cargotracker.interfaces.booking.web;

import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 *
 * @author davidd
 */
@Named
@ViewScoped
public class FlyerView implements Serializable{

    private DashboardModel model;

    private String name = "TestDD";

    public String getName() {
        return name;
    }

    public FlyerView(){
        // Initialize the dashboard model
        this.model = new DefaultDashboardModel();
        DashboardColumn mainColumn = new DefaultDashboardColumn();

        mainColumn.addWidget("Flights");
        mainColumn.addWidget("Routed");
        mainColumn.addWidget("All");
        mainColumn.addWidget("Airports");
 
        this.model.addColumn(mainColumn);
 
    }
 
    public DashboardModel getModel() {
        return model;
    }
 
    public void setModel(DashboardModel model) {
        this.model = model;
    }
    
}
