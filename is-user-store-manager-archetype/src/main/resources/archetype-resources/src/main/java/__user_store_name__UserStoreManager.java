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

package ${package};

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.CarbonConstants;
import org.wso2.carbon.user.api.Properties;
import org.wso2.carbon.user.api.RealmConfiguration;
import org.wso2.carbon.user.core.UserRealm;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.claim.ClaimManager;
import org.wso2.carbon.user.core.profile.ProfileConfigurationManager;

import java.util.Map;

public class ${user_store_name}UserStoreManager extends ${base_user_store_name} {

    private static final Log log = LogFactory.getLog(${user_store_name}UserStoreManager.class);

    public ${user_store_name}UserStoreManager() {
    }

    public ${user_store_name}UserStoreManager(RealmConfiguration realmConfig, Map<String, Object> properties,
            ClaimManager claimManager, ProfileConfigurationManager profileManager, UserRealm realm, Integer tenantId)
            throws UserStoreException {
        super(realmConfig, properties, claimManager, profileManager, realm, tenantId);
        log.info("${user_store_name}UserStoreManager initialized...");
    }

    @Override
    public boolean doAuthenticate(String userName, Object credential) throws UserStoreException {

        boolean isAuthed = super.doAuthenticate(userName, credential);
        if (CarbonConstants.REGISTRY_ANONNYMOUS_USERNAME.equals(userName)) {
            log.error("Anonymous user trying to login");
            return false;
        }

        //Add user authentication logic in here
        //Or leave this as it is to use default behavior
        return isAuthed;
    }

    @Override
    public Properties getDefaultUserStoreProperties() {

        Properties properties = super.getDefaultUserStoreProperties();

        //Add properties realated to user store in here
        //Or leave it as it is to use default behavior
        return properties;
    }

    @Override
    protected boolean checkUserNameValid(String userName) throws UserStoreException {

        boolean isValidUserName = super.checkUserNameValid(userName);

        //Add user name validation logic in here.
        //Or leave it as it is to use default behavior
        return isValidUserName;
    }

    @Override
    protected boolean checkUserPasswordValid(Object credential) throws UserStoreException {

        boolean isValidUserPassword  = super.checkUserPasswordValid(credential);

        //Add user password validation logic in here.
        //Or leave it as it is to use default behavior
        return isValidUserPassword;
    }
}
