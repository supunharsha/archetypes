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

final IMPORT_FOR_JDBC_USER_STORE_MANAGER = "import org.wso2.carbon.user.core.jdbc.JDBCUserStoreManager;"
final IMPORT_FOR_READ_ONLY_LDAP_USER_STORE_MANAGER = "import org.wso2.carbon.user.core.ldap.ReadOnlyLDAPUserStoreManager;"
final IMPORT_FOR_READ_WRITE_LDAP_USER_STORE_MANAGER = "import org.wso2.carbon.user.core.ldap.ReadWriteLDAPUserStoreManager;"
final IMPORT_FOR_ACTIVE_DIRECTORY_USER_STORE_MANAGER = "import org.wso2.carbon.user.core.ldap.ActiveDirectoryUserStoreManager;"
final IMPORT_FOR_GENERIC_USER_STORE_MANAGER = "import org.wso2.carbon.user.core.common.AbstractUserStoreManager;"

def base, packageFolder, userStoreName, importName;

binding.variables.each{
    if (it.key == "base_user_store_name") {
        base = it.value
    } else if (it.key == "package") {
        packageFolder = it.value
    } else if (it.key == "user_store_name") {
        userStoreName = it.value
    }
}

switch (base) {
    case "JDBCUserStoreManager":
        importName = IMPORT_FOR_JDBC_USER_STORE_MANAGER
        break
    case "ReadOnlyLDAPUserStoreManager":
        importName = IMPORT_FOR_READ_ONLY_LDAP_USER_STORE_MANAGER
        break
    case "ReadWriteLDAPUserStoreManager":
        importName = IMPORT_FOR_READ_WRITE_LDAP_USER_STORE_MANAGER
        break
    case "ActiveDirectoryUserStoreManager":
        importName = IMPORT_FOR_ACTIVE_DIRECTORY_USER_STORE_MANAGER
        break
    case "AbstractUserStoreManager":
        importName = IMPORT_FOR_GENERIC_USER_STORE_MANAGER
        break
    default:
        throw new RuntimeException("Invalid base_user_store_name. Possible values are JDBCUserStoreManager, " +
                "ReadOnlyLDAPUserStoreManager, ReadWriteLDAPUserStoreManager, ActiveDirectoryUserStoreManager or " +
                "AbstractUserStoreManager")
        break
}

def moduleDir = new File(request.getOutputDirectory()+"/"+request.getArtifactId())
def templateFile = new File(moduleDir, "/src/main/java/${packageFolder}/${userStoreName}UserStoreManager.java")

def lines = templateFile.readLines()
lines = lines.plus(11, importName)
templateFile.text = lines.join('\n')
