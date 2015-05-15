/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hccs.inew2438.appDAO;

import com.hccs.inew2438.appJDBC.OracleJDBC;
import com.hccs.inew2438.appPOJO.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Praveen
 */
public class CustomerDAO {

    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private Statement st = null;

    public int insertCustomerRecord(Customer newCustomerRecord) {
        int insertedRow = 0;
        try {
            conn = OracleJDBC.getConnection();
            pst = conn.prepareStatement("INSERT INTO CUSTOMER(CUSTOMER_ID,CUSTOMER_PASSWORD,CUSTOMER_EMAIL) VALUES(?,?,?)");

            pst.setInt(1, readMaxCustomerRecord() + 1);
            pst.setString(2, newCustomerRecord.getPassword());
            pst.setString(3, newCustomerRecord.getEmail());

            insertedRow = pst.executeUpdate();
            conn.commit();
        } catch (SQLException sqlEx) {
            System.out.println("Problem while executing sql");
        } catch (Exception ex) {
            System.out.println("Unknown exception.");

        } finally {
            try {
                pst.close();
                conn.close();

            } catch (SQLException sqlExce) {
                System.out.println("Problem while closing the connection.");
            }
        }
        return insertedRow;
    }

    public List<Customer> readAllCustomerRecords() {
        try {
            conn = OracleJDBC.getConnection();
            pst = conn.prepareStatement("SELECT CUSTOMER_ID,CUSTOMER_PASSWORD,CUSTOMER_EMAIL FROM CUSTOMER");
            rs = pst.executeQuery();
            List<Customer> allCustomers = new ArrayList<>();

            while (rs.next()) {
                // As this is only one record, returning book to caller.
                allCustomers.add(new Customer(rs.getString("CUSTOMER_PASSWORD"), rs.getString("CUSTOMER_EMAIL"), rs.getInt("CUSTOMER_ID")));
            }
            conn.commit();
            return allCustomers;
        } catch (SQLException sqlEx) {
            System.out.println("Problem while executing sql");

        } catch (Exception ex) {
            System.out.println("Unknown exception.");

        } finally {
            try {
                rs.close();
                pst.close();
                conn.close();

            } catch (SQLException sqlExce) {
                System.out.println("Problem while closing the connection.");
            }
        }
        return null;
    }

    public int readMaxCustomerRecord() {
    Connection conn = null;
    
        try {
            conn = OracleJDBC.getConnection();
            st = conn.createStatement();

            rs = st.executeQuery("SELECT MAX(CUSTOMER_ID) MAX_ID FROM CUSTOMER");
       
            if (rs.next()) {
                System.out.println("The max customer_id = " + rs.getInt("MAX_ID"));
                return rs.getInt("MAX_ID");
            }

        } catch (SQLException sqlEx) {
            System.out.println("Problem while executing sql");

        } catch (Exception ex) {
            System.out.println("Unknown exception.");

        } finally {
            try {
                rs.close();
                st.close();
                conn.close();

            } catch (SQLException sqlExce) {
                System.out.println("Problem while closing the connection.");
            }
        }
        return 0;
    }
    
    public int deleteCustomerRecord(Customer existingCustomerRecord) {	
	    int deletedRow = 0;
		try
		{
			conn = OracleJDBC.getConnection();
			pst = conn.prepareStatement("DELETE CUSTOMER WHERE CUSTOMER_EMAIL=?");
		
			pst.setString(1, existingCustomerRecord.getEmail());
			deletedRow = pst.executeUpdate();
		    
		    conn.commit();
		    return deletedRow;
		}catch(SQLException sqlEx)
		{
			System.out.println("Problem while executing sql");
			sqlEx.printStackTrace();
		}catch(Exception ex)
		{
			System.out.println("Unknown exception.");
			
		}finally
		{
			try
			{
				rs.close();
				pst.close();
				conn.close();
				
			}catch(SQLException sqlExce)
			{
				System.out.println("Problem while closing the connection.");
			}
		}
		return deletedRow;
	}
    
    public int updateCustomerRecord(Customer updatedCustomerObject, String customerOldEmail) {
		int updatedRow = 0;
		try {
			conn = OracleJDBC.getConnection();
			pst = conn.prepareStatement("UPDATE CUSTOMER SET CUSTOMER_EMAIL=?,CUSTOMER_PASSWORD=? WHERE CUSTOMER_EMAIL=?");
			pst.setString(1, updatedCustomerObject.getEmail());
			pst.setString(2, updatedCustomerObject.getPassword());
			pst.setString(3, customerOldEmail);
			
			updatedRow = pst.executeUpdate();
			System.out.println("Customer record updated successfully.");
			System.out.println("Customer records updated: " + updatedRow);
		    conn.commit();
		    return updatedRow;
		}catch(SQLException sqlEx)
		{
			System.out.println("Problem while executing sql");
			sqlEx.printStackTrace();
		}catch(Exception ex)
		{
			System.out.println("Unknown exception.");
			
		}finally
		{
			try
			{
				rs.close();
				pst.close();
				conn.close();
				
			}catch(SQLException sqlExce)
			{
				System.out.println("Problem while closing the connection.");
			}
		}
		return updatedRow;
	}

}
