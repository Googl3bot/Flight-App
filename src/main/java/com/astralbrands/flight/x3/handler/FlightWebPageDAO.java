package com.astralbrands.flight.x3.handler;

import com.astralbrands.flight.x3.config.AppConfig;
//import com.astralbrands.flight.x3.controller.FlightPortalController;
import com.astralbrands.flight.x3.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class FlightWebPageDAO {

    Logger log = LoggerFactory.getLogger(FlightWebPageDAO.class);

    @Autowired
    AppConfig appConfig;


    private Connection x3Connection;

    @Autowired
    @Qualifier("x3DataSource")
    private DataSource x3DataSource;

    @Value("${x3.tracking.query}")
    String trackingQuery;

    public ResultSet runQueryFlight(String query) {

        if (x3DataSource != null) {
            if (x3Connection == null) {
                try {
                    x3Connection = x3DataSource.getConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("SQLException is null" + e.getMessage());
                }
            }
            try {
                log.info(":" + query);
                Statement statement = x3Connection.createStatement();
                return statement.executeQuery(query);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(" error while running query");
            }
        }
        throw new RuntimeException("Datasource is null");

    }

    public ResultSet getDateQuery(String date) {
        if (x3DataSource != null) {
            if (x3Connection == null) {
                try {
                    x3Connection = x3DataSource.getConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("SQLException is null" + e.getMessage());
                }
            }
            try {
                Statement statement = x3Connection.createStatement();
                String query = trackingQuery.replace("#currDate", date);
                return statement.executeQuery(query);
            } catch (SQLException sql) {
                System.err.println("--------------------------------------------------------------------------------------------------------------------" + "\r\n");
                System.err.println("----------------------------------**--ERROR-WITH-DATE-QUERY-FUNCTION--**--------------------------------------------" + "\r\n");
                System.err.println("--------------------------------------------------------------------------------------------------------------------" + "\r\n\r\n\"");
                System.err.println("--------------------------------****--GET-MESSAGE-EXCEPTION--****------------------------------------" + "\r\n");
                System.err.println(sql.getMessage() + "\r\n\r\n" + "--------------------------------****--GET-SQL-STATE-EXCEPTION--****------------------------------------" + "\r\n");
                System.err.println(sql.getSQLState() + "\r\n\r\n" + "--------------------------------****--GET-NEXT-EXCEPTION--****------------------------------------" + "\r\n");
                System.err.println(sql.getNextException() + "\r\n\r\n" + "--------------------------------****--GET-ERROR-CODE-EXCEPTION--****------------------------------------" + "\r\n");
                System.err.println(sql.getErrorCode() + "\r\n\r\n" + "--------------------------------****--PRINT-STACK-TRACE--****------------------------------------" + "\r\n");
                sql.printStackTrace();
            }

        }
        throw new RuntimeException("EXCEPTION WHILE REPLACING TRACKING QUERY WITH TODAY'S DATE");
    }

}