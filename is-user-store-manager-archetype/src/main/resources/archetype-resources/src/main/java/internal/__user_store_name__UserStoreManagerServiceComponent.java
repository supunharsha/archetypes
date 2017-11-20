/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package ${package}.internal;

import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.user.api.UserStoreManager;
import org.wso2.carbon.user.core.service.RealmService;
import ${package}.${user_store_name}UserStoreManager;

/**
 * @scr.component name="${package}.${user_store_name}.component" immediate="true"
 * @scr.reference name="realm.service"
 * interface="org.wso2.carbon.user.core.service.RealmService"cardinality="1..1"
 * policy="dynamic" bind="setRealmService" unbind="unsetRealmService"
 */
public class ${user_store_name}UserStoreManagerServiceComponent {

    private static Log log = LogFactory.getLog(${user_store_name}UserStoreManagerServiceComponent.class);
    private static RealmService realmService;

    protected void activate(ComponentContext context) {
        if (log.isDebugEnabled()) {
            log.debug("Activating ${user_store_name}UserStoreManagerServiceComponent");
        }
        try {
        Hashtable<String, String> props = new Hashtable<String, String>();
        ${user_store_name}UserStoreManager customUserStoreManager = new ${user_store_name}UserStoreManager();
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
        ${user_store_name}UserStoreManagerServiceComponent.realmService = realmService;
    }

    protected void unsetRealmService(RealmService realmService) {
        log.debug("UnSetting the Realm Service");
        ${user_store_name}UserStoreManagerServiceComponent.realmService = null;
    }

    public static RealmService getRealmService() {
        return realmService;
    }
}
