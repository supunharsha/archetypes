package ${package}.internal;

import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.user.api.UserStoreManager;
import org.wso2.carbon.user.core.service.RealmService;
import ${package}.${user_store_name}AbstractUserStoreManager;

/**
 * @scr.component name="${package}.${user_store_name}.component" immediate="true"
 * @scr.reference name="realm.service"
 * interface="org.wso2.carbon.user.core.service.RealmService"cardinality="1..1"
 * policy="dynamic" bind="setRealmService" unbind="unsetRealmService"
 */
public class ${user_store_name}AbstractUserStoreManagerServiceComponent {

    private static Log log = LogFactory.getLog(${user_store_name}AbstractUserStoreManagerServiceComponent.class);
    private static RealmService realmService;

    protected void activate(ComponentContext context) {
        if (log.isDebugEnabled()) {
            log.debug("Activating ${user_store_name}UserStoreManagerServiceComponent");
        }
        try {
        Hashtable<String, String> props = new Hashtable<String, String>();
        ${user_store_name}AbstractUserStoreManager customUserStoreManager = new ${user_store_name}AbstractUserStoreManager();
        context.getBundleContext().registerService(UserStoreManager.class.getName(), customUserStoreManager, props);
            if (log.isDebugEnabled()) {
                log.debug("${user_store_name} User Store Manager bundle is activated");
            }
        } catch (Throwable e) {
            log.fatal(" Error while activating ${user_store_name} User Store Manager ", e);
        }
    }

    protected void deactivate(ComponentContext ctxt) {
        if (log.isDebugEnabled()) {
            log.info("${user_store_name} User Store Manager bundle is deactivated");
        }
    }

    protected void setRealmService(RealmService realmService) {
        log.debug("Setting the Realm Service");
        ${user_store_name}AbstractUserStoreManagerServiceComponent.realmService = realmService;
    }

    protected void unsetRealmService(RealmService realmService) {
        log.debug("UnSetting the Realm Service");
        ${user_store_name}AbstractUserStoreManagerServiceComponent.realmService = null;
    }

    public static RealmService getRealmService() {
        return realmService;
    }
}
