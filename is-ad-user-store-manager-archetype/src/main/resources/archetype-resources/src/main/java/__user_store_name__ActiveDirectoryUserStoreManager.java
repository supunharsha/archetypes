package ${package};

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.CarbonConstants;
import org.wso2.carbon.user.api.Properties;
import org.wso2.carbon.user.api.RealmConfiguration;
import org.wso2.carbon.user.core.UserRealm;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.claim.ClaimManager;
import org.wso2.carbon.user.core.ldap.ActiveDirectoryUserStoreManager;
import org.wso2.carbon.user.core.profile.ProfileConfigurationManager;

import java.util.Map;

public class ${user_store_name}ActiveDirectoryUserStoreManager extends ActiveDirectoryUserStoreManager {

    private static final Log log = LogFactory.getLog(${user_store_name}ActiveDirectoryUserStoreManager.class);

    public ${user_store_name}ActiveDirectoryUserStoreManager() {
    }

    public ${user_store_name}ActiveDirectoryUserStoreManager(RealmConfiguration realmConfig, Map<String, Object> properties,
            ClaimManager claimManager, ProfileConfigurationManager profileManager, UserRealm realm, Integer tenantId)
            throws UserStoreException {
        super(realmConfig, properties, claimManager, profileManager, realm, tenantId);
        log.info("${user_store_name}ActiveDirectoryUserStoreManager initialized...");
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
    protected String preparePassword(Object password, String saltValue) throws UserStoreException {

        String passwordString = super.preparePassword(password, saltValue);

        //Encrypt password encryption logic or save it as plain text in here
        //Or leave this as it is to use default behavior
        return passwordString;
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
