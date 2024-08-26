package org.example.posbackendjavaee.bo;

import org.example.posbackendjavaee.dao.CustomerDataProcessImpl;
import org.example.posbackendjavaee.model.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class CustomerBOImpl {

    CustomerDataProcessImpl customerDataProcess = new CustomerDataProcessImpl();

    public boolean saveCustomer(CustomerDTO customerDTO, Connection connection) {
        return customerDataProcess.save(customerDTO, connection);
    }

    public boolean deleteCustomer(String cusName, Connection connection) {
        return customerDataProcess.deleteCustomer(cusName, connection);
    }

    public List<CustomerDTO> getCustomer(Connection connection) throws SQLException {
        return customerDataProcess.getCustomer(connection);
    }

    public boolean updateStudent(CustomerDTO updatedCustomer, Connection connection) {
        return customerDataProcess.update(updatedCustomer, connection);
    }
}
