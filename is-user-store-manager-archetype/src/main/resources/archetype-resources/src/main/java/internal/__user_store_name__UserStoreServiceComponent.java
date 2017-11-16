/**
 * @scr.component name=
 * "${package}.internal.${user_store_name}ConnectorServiceComponent"
 * immediate="true"
 */
public class ${user_store_name}ConnectorServiceComponent {

    private static Log log = LogFactory.getLog(${user_store_name}ConnectorServiceComponent.class);

    protected void activate(ComponentContext context) {
        if (log.isDebugEnabled()) {
            log.debug("Activating ${user_store_name}ConnectorServiceComponent");
        }
        try {
            if (log.isDebugEnabled()) {
                //log.debug("${connector_name} Identity Provisioning Connector bundle is activated");
            }
        } catch (Throwable e) {
            log.fatal(" Error while activating ${user_store_name} User Store Manager ", e);
        }
    }
}
