package org.example.posbackendjavaee.dao;

import org.example.posbackendjavaee.model.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {

    List<CustomerDTO> getCustomer(Connection connection) throws SQLException;
    boolean save(CustomerDTO customerDTO, Connection connection);
    boolean deleteCustomer(String cusName, Connection connection);
    boolean update(CustomerDTO updatedCustomer, Connection connection);

}
