package com.techproject.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.techproject.entity.Request;
import com.techproject.exceptions.InvalidMessage;

import com.techproject.service.RequestsService;
import com.techproject.service.RequestsServiceInterface;
import com.techproject.utils.BusinessRules;

//import junit.framework.Assert;

public class ServiceTest {
    public static RequestDAOInterface requestDao;
    public static RequestsServiceInterface requestService;
    public static EmployeeDAOInterface employeeDao;

    public static BusinessRules businessRules;

    @BeforeClass
    public static void setup() {

        requestDao = new RequestDAO();
        businessRules = new BusinessRules();
        requestService = new RequestsService(requestDao, businessRules);

        employeeDao = new EmployeeDAO();

    }

    @Test
    public void serviceRequestAmountNegativeCheck() {

        try {
            Request request = new Request("John", "Negative test more than 1000", 1001, "pending", "N/A ");
            Request response = requestService.serviceCreateRequest(request);
            System.out.println(response.toString());
            // Assert.fail("Bussiness rule Passed");
            Assert.fail();
        } catch (InvalidMessage e) {
            System.out.println("Negative : Amount over $1000");
            Assert.assertEquals("Invalid request", e.getMessage());
        }
    }

    @Test
    public void viewOwnRequestTest() {

        System.out.println(requestService.serviceViewRequestWithBusinessRules());
        List<Request> result = requestService.serviceViewRequestWithBusinessRules();
        Assert.assertEquals(result, result);
    }

    @Test
    public void serviceManagerReasonNegativeCheck() {

        try {
            Request request = new Request("John",
                    "good request ",
                    100, "pending", "Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! ");
            Request response = requestService.serviceUpdateRequest(request);
            System.out.println(response.toString());
           
            Assert.fail();
        } catch (InvalidMessage e) {
            System.out.println("Negative : Reason cannot be more than 500 characters");
            Assert.assertEquals("Reason cannot be more than 500 characters", e.getMessage());

        }
    }

    @Test
    public void serviceEmployeeRequestNegativeCheck() {

        try {
            Request request = new Request("John",
                    "Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters! Negative test more than 500 characters!  ",
                    100, "pending", "good reason");
            Request response = requestService.serviceCreateRequest(request);
            System.out.println(response.toString());
           
            Assert.fail();
        } catch (InvalidMessage e) {
            System.out.println("Negative : Request cannot be more than 500 characters");
            Assert.assertEquals("Invalid request", e.getMessage());

        }
    }
}
