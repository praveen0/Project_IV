/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hccs.inew2438.appManager;

import com.hccs.inew2438.appDAO.CustomerDAO;
import com.hccs.inew2438.appPOJO.Customer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Praveen
 */
@Path("/customer")
public class CustomerManager {

    private CustomerDAO custDAO = new CustomerDAO();

    /**
     * The form comes from users
     *
     * @param inputEmail
     * @param inputPassword
     */
    @Path("/addCustomer")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addCustomer(@FormParam("inputEmail") String inputEmail,
            @FormParam("inputPassword") String inputPassword) {

        Customer customer = new Customer(inputPassword, inputEmail);

        int count = custDAO.insertCustomerRecord(customer);

        if (count > 0) {
            return Response.status(200).entity("New customer information was inserted in to database successfully.").build();
        } else {
            return Response.status(200).entity("New customer information was not recorded.").build();
        }
    }

    /**
     * For multiple input.
     *
     * @POST
     * @Path("/customers")
     * @Consumes(MediaType.APPLICATION_FORM_URLENCODED) public Response
     * createCustomers(MultivaluedMap<String, String> parameter1) { String
     * output = " Form parameters :\n"; for (String key : parameter1.keySet()) {
     * output += key + " : " + parameter1.getFirst(key) +"\n"; } return
     * Response.status(200).entity(output).build(); }
     */
    @GET
    // http://localhost:8081/Project_III/webresources/customer/getCustomers
    @Path("getCustomers")
    @Produces({"application/json", "application/xml"})
    public List<Customer> getAllCustomers() {

        StringBuffer sBuffer = new StringBuffer();
        List<Customer> custList = custDAO.readAllCustomerRecords();

        for (Customer readList : custList) {

            sBuffer.append(readList.toString() + "\n");
        }

        System.out.println("Customer information: \n" + sBuffer);
//        return Response.status(200).entity(sBuffer).build();
        return custList;
    }

    @GET
    // 
    
    @Path("getCustomersAsString")
    @Produces("text/html")
    public String getCustomersAsString() {
        String details;
        StringBuffer sBuffer = new StringBuffer();
        List<Customer> custList = custDAO.readAllCustomerRecords();

        details = "<html><body>";
        details = details + "<table border=1>";
        details = details + "<tr><td><Strong> Id </Strong></td>" + "<td><Strong> Email </Strong></td>"
                + "<td><Strong> Password </Strong></td>" + "</tr>";
        int i = 0;
        while (i < custList.size()) {
            details = details + "<tr><td>" + (custList.get(i)).getId() + "</td>" + "<td>" + (custList.get(i)).getEmail() + "</td>"
                    + "<td>" + (custList.get(i)).getPassword() + "</td></tr>";
            i++;
        }
        details += "</table></body></html>";
        return details;
    }

    @GET
    // http://localhost:8081/Project_III/webresources/customer/getCustomersAsStringBuffer
    @Path("getCustomersAsStringBuffer")
    @Produces("text/html")
    public String getCustomersAsStringBuffer() {
        String details;
        StringBuffer sBuffer = new StringBuffer();
        List<Customer> custList = custDAO.readAllCustomerRecords();

        sBuffer.append("<html><body>");
        sBuffer.append("<table border=1>");
        sBuffer.append("<tr><td><Strong> Id </Strong></td>" + "<td><Strong> Email </Strong></td>"
                + "<td><Strong> Password </Strong></td>" + "</tr>");
        int i = 0;
        while (i < custList.size()) {
            sBuffer.append("<tr><td>" + (custList.get(i)).getId() + "</td>" + "<td>" + (custList.get(i)).getEmail() + "</td>"
                    + "<td>" + (custList.get(i)).getPassword() + "</td></tr>");
            i++;
        }
        sBuffer.append("</table></body></html>");

        System.out.println(sBuffer.toString());
        return sBuffer.toString();
    }

    @GET
    @Path("addUser")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    // http://localhost:8081/Project_III/webresources/customer/addUser
    public Response addUser(@Context UriInfo uriInfo, @Context Response response) throws URISyntaxException {
        //Something will be done here
//URI uri = uriInfo.getBaseUriBuilder().path("../index.html").build();
//return Response.seeOther(uri).build();
        
        String shared = "shared";
//request.setAttribute("sharedId", shared); // add to request

        java.net.URI location = new java.net.URI("../index.html?msg=A_User_Added");
        Response.ok(shared);
        return Response.temporaryRedirect(location).build();

    }
    
    @DELETE
    @Path("/deleteUser")
    public Response removeCustomer(@FormParam("inputEmail") String inputEmail) {
        
        Customer cust = new Customer();
        cust.setEmail(inputEmail);
        
        int count = custDAO.deleteCustomerRecord(cust);

        if (count > 0) {
            return Response.status(200).entity("New customer information was inserted in to database successfully.").build();
        } else {
            return Response.status(200).entity("New customer information was not recorded.").build();
        }
    }

    @PUT
    @Path("/putUser")
    public Response updateCustomer(@FormParam("newEmail") String newEmail,
            @FormParam("newPassword") String newPassword, @FormParam("oldEmail") String oldEmail) {
        
        Customer cust = new Customer();
        cust.setEmail(newEmail);
        cust.setPassword(newPassword);
        
        int count = custDAO.updateCustomerRecord(cust, oldEmail);

        if (count > 0) {
            return Response.status(200).entity("New customer information was inserted in to database successfully.").build();
        } else {
            return Response.status(200).entity("New customer information was not recorded.").build();
        }
    }

}
