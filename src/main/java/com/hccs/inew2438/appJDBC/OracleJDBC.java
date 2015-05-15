/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hccs.inew2438.appJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import oracle.jdbc.driver.OracleDriver;

/**
 *
 * @author Praveen
 */
public class OracleJDBC {

    public OracleJDBC() {
        super();
    }
    /**
     * This method prepares a database connection/JDBC Connection object.
     *
     */
    public static Connection getConnection() throws Exception {
	//String driver = "oracle.jdbc.driver.OracleDriver";
        //Class.forName(driver);
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "SYSTEM";
        String password = "SYSADMIN";
        DriverManager.registerDriver(new OracleDriver());
        Connection conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);
        return conn;
    }
}


