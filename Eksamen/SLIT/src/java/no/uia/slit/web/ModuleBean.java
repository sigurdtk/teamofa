package no.uia.slit.web;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import no.uia.slit.ejb.ModulePersistenceService;
import no.uia.slit.entity.Module;


/**
 *
 * @author even
 */
@Named("moduleBean")
@ConversationScoped
public class ModuleBean implements Serializable {
    
    @Inject private Conversation conv;
    @EJB ModulePersistenceService moduleSvc;
    private Module module;
    private boolean updating;
    private boolean notupdating;
    private boolean isDelivered;
    
    private long id;
    private String name;
    private String description;
    
    public ModuleBean() {
    }

    public void setId(long id) {
        if (conv.isTransient()) {
            conv.begin();
        }

        Module mod = moduleSvc.find(id);
        if (null == mod) {
            // we will get here if depNo is not a valid, or if
            // depNo is valid but there is no department with that depno
            updating = false;
            notupdating = true;
            id = 0;
            name = "";
            description = "";
        }
        else {
            updating = true;
            notupdating = false;
            this.id = mod.getId();
            name = mod.getName();
            description = mod.getDescription();
        }
    }

    public long getId(){
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public boolean isUpdating(){
        return updating;
    }

    public boolean isNotUpdating(){
        return notupdating;
    }
    
    private Module createModObject() {
        Module mod = new Module();
        mod.setId(id);
        mod.setName(name);
        mod.setDescription(description);
        return mod;
    }

    public Page saveModule() {
        Module mod = createModObject();
        moduleSvc.insert(mod);
        conv.end();
        return Page.modules;
    }
    
    public Page editModule(){
        Module mod = createModObject();
        moduleSvc.update(mod);
        conv.end();
        return Page.modules;
    }

    public Page deleteModule() {
        Module mod = createModObject();
        moduleSvc.delete(mod);
        conv.end();

        return Page.modules;
    }
    
    public Page cancelModuleCreation(){
        conv.end();
        return Page.modules;
    }
    
    public Page studentCancelModuleView(){
        conv.end();
        return Page.course;
    }
    
    public void endConv(){
        conv.end();
    }
}