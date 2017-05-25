package net.java.cargotracker.interfaces.booking.web;

import org.primefaces.context.RequestContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author davidd
 */
@ManagedBean
@SessionScoped
public class AddFlightDialog implements Serializable {

    public void showDialog() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 640);
        options.put("contentHeight", 260);
        
        Map<String, List<String>> params = new HashMap<>();
        RequestContext.getCurrentInstance().openDialog("/admin/addFlight.xhtml", options, params);
    }

    public void handleReturn() {
        RequestContext.getCurrentInstance().update("mainDash");
    }

    public void close() {
        // just kill the dialog
        RequestContext.getCurrentInstance().closeDialog("");
    }

}

