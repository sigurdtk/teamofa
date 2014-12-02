/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.web;

import no.uia.slit.ejb.ModulePersistenceService;
import no.uia.slit.entity.Module;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author evenal
 */
@Named("modlistbean")
@RequestScoped
public class ModuleListBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB ModulePersistenceService moduleSvc;

    public ModuleListBean() {
    }

    public List<Module> getModules() {
        List<Module> l = moduleSvc.findAll();
        return l;
    }
}
