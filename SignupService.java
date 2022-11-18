package com.trainingsite.core.services.impl;

import com.day.commons.datasource.poolservice.DataSourceNotFoundException;
import com.day.commons.datasource.poolservice.DataSourcePool;
import com.trainingsite.core.services.SignupServiceInterface;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.trainingsite.core.services.SignupServiceInterface;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;



public class SignupService implements SignupServiceInterface {
    private static final Logger log = LoggerFactory.getLogger(SignupService.class);

    @Reference
    private DataSourcePool dataSourcePool;


    private static final String DATA_SOURCE_NAME = "formds";

    @Override
    public boolean isAccessible() {

        try {
            // Get the JDBC data source based on the named OSGi configuration
            DataSource dataSource = (DataSource) dataSourcePool.getDataSource(DATA_SOURCE_NAME);


            try (Connection connection = dataSource.getConnection()) {

                // Validate the connection
                connection.isValid(1000);

                connection.close();

                // Return true if AEM could reach the external JDBC service
                return true;
            } catch (SQLException e) {
                log.error("Unable to validate SQL connection for [ {} ]", DATA_SOURCE_NAME, e);
            }
        } catch (DataSourceNotFoundException e) {
            log.error("Unable to establish an connection with the JDBC data source [ {} ]", DATA_SOURCE_NAME, e);
        }
        return false;
    }

}