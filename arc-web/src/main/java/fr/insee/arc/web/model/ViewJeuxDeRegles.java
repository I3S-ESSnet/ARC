package fr.insee.arc.web.model;

import java.util.HashMap;

import fr.insee.arc.web.util.ConstantVObject;
import fr.insee.arc.web.util.VObject;
import fr.insee.arc.web.util.ConstantVObject.ColumnRendering;

public class ViewJeuxDeRegles extends VObject {
    public ViewJeuxDeRegles() {
        super();
        
        this.setTitle("view.ruleset");

        this.setPaginationSize(15);

        this.constantVObject = new ConstantVObject(new HashMap<String, ColumnRendering>() {
            /**
			 * 
			 */
            private static final long serialVersionUID = 7742353075723133064L;

			{
                put("id_norme", new ColumnRendering(false, "label.norm", "0", "text", null, true));
                put("periodicite", new ColumnRendering(false, "label.periodicity", "0", "text", null, true));
                put("validite_inf", new ColumnRendering(false, "label.validity.min", "0", "text", null, true));
                put("validite_sup", new ColumnRendering(false, "label.validity.max", "0", "text", null, true));
                put("version", new ColumnRendering(true, "label.version", "60px", "text", null, true));
                put("etat", new ColumnRendering(true, "label.state", "120px", "select", "select id, val from arc.ext_etat_jeuderegle order by id", true));
                put("date_production", new ColumnRendering(false, "label.date.production", "80px", "text", null, true));
                put("date_inactif", new ColumnRendering(false, "label.date.disable", "80px", "text", null, true));

            }

        }

        );
    }
}