package org.example.posbackendjavaee.bo;

import org.example.posbackendjavaee.dao.CustomerDataProcessImpl;
import org.example.posbackendjavaee.model.CustomerDTO;

import java.sql.Connection;


public class CustomerBOImpl {

    CustomerDataProcessImpl customerDataProcess = new CustomerDataProcessImpl();

    public boolean saveCustomer(CustomerDTO customerDTO, Connection connection) {
        return customerDataProcess.save(customerDTO, connection);
    }

}
