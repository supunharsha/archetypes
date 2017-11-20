package ${package}.internal;

import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.user.api.UserStoreManager;
import org.wso2.carbon.user.core.service.RealmService;
import ${package}.${user_store_name}ReadWriteLDAPUserStoreManager;

/**
 * @scr.component name="${package}.${user_store_name}ReadWriteLDAPUserStoreManager.component" immediate="true"
 * @scr.reference name="realm.service"
 * interface="org.wso2.carbon.user.core.service.RealmService"cardinality="1..1"
 * policy="dynamic" bind="setRealmService" unbind="unsetRealmService"
 */
public class ${user_store_name}ReadWriteLDAPUserStoreManagerServiceComponent {

    private static Log log = LogFactory.getLog(${user_store_name}ReadWriteLDAPUserStoreManagerServiceComponent.class);
    private static RealmService realmService;

    protected void activate(ComponentContext context) {
        if (log.isDebugEnabled()) {
            log.debug("Activating ${user_store_name}ReadWriteLDAPUserStoreManagerServiceComponent");
        }
        try {
        Hashtable<String, String> props = new Hashtable<String, String>();
        ${user_store_name}ReadWriteLDAPUserStoreManager customUserStoreManager = new ${user_store_name}ReadWriteLDAPUserStoreManager();
        context.getBundleContext().registerService(UserStoreManager.class.getName(), customUserStoreManager, props);
            if (log.isDebugEnabled()) {
                log.debug("${user_store_name} Read Write User Store Manager bundle is activated");
            }
        } catch (Throwable e) {
            log.fatal(" Error while activating ${user_store_name} Read Write User Store Manager ", e);
        }
    }

    protected void deactivate(ComponentContext ctxt) {
        if (log.isDebugEnabled()) {
            log.info("${user_store_name} Read Write User Store Manager bundle is deactivated");
        }
    }

    protected void setRealmService(RealmService realmService) {
        log.debug("Setting the Realm Service");
        ${user_store_name}JDBCUserStoreManagerServiceComponent.realmService = realmService;
    }

    protected void unsetRealmService(RealmService realmService) {
        log.debug("UnSetting the Realm Service");
        ${user_store_name}JDBCUserStoreManagerServiceComponent.realmService = null;
    }

    public static RealmService getRealmService() {
        return realmService;
    }
}
