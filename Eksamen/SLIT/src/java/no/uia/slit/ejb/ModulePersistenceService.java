package no.uia.slit.ejb;

import no.uia.slit.entity.Module;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import no.uia.slit.entity.Module;


/**
 *
 * @author even
 */
@Stateless
public class ModulePersistenceService extends AbstractPersister<Module>{

   @PersistenceContext
   private EntityManager em;

   @Override
    protected EntityManager getEntityManager() {
        return em;
    }
   
    public ModulePersistenceService() {
        super(Module.class);
    }
    
    public Module getDefaultModule() {
        List<Module> modList = findAll();
        if (modList != null && modList.size() > 0) {
            return modList.get(0);
        } else {
            throw new IllegalStateException("Ingen moduler tilgjengelig.");
        }
    }
    
   /** Retrieve the module with the specified id from the database */
    public Module find(long id) {
        Module mod = super.find(id);
        if (null != mod) mod.getDescription();
        return mod;
    }
    
    @Override
    public void delete(Module mod) {
        mod = em.merge(mod);
        super.delete(mod);
    }
}
