package net.java.cargotracker.interfaces.booking.web;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author davidd
 */
@ManagedBean
@SessionScoped
public class CancelFlightDialog implements Serializable {


    public void showDialog(String flightNo) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 360);
        options.put("contentHeight", 230);
        
        Map<String, List<String>> params = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(flightNo.toString());
        params.put("flightNo", values);
        RequestContext.getCurrentInstance().openDialog("/admin/cancelFlight.xhtml", options, params);
    }

    public void close() {
        // just kill the dialog
        RequestContext.getCurrentInstance().closeDialog("");
    }


}

