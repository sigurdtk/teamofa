/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.web;

import no.uia.slit.ejb.ModulePersistenceService;
import no.uia.slit.entity.Module;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author Even (UiA)
 */
@Named("modconverter") @RequestScoped
@FacesConverter(forClass=Module.class)
public class ModuleConverter implements Converter {

    @EJB
    ModulePersistenceService moduleSvc;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        long id = Long.parseLong(value);
        if (null == moduleSvc) {
            System.out.println("Ingen EJB!");
            throw new ConverterException("Ingen EJB!");
        }
        Module mod = moduleSvc.find(id);
        if (null == mod) {
            System.out.println("Ingen verdi!");
            throw new ConverterException("Kan ikke konvertere \""+value+"\" til Moduler");
        }
        return mod;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (null != value && value instanceof Module) {
            Module mod = (Module)value;
            return ""+mod.getId();
        }
        throw new ConverterException("Ulovlig verdi - Må være et modul objekt");
    }
}
