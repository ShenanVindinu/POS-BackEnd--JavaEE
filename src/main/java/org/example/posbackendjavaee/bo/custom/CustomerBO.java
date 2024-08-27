package org.example.posbackendjavaee.bo.custom;

import org.example.posbackendjavaee.bo.SuperBO;
import org.example.posbackendjavaee.model.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {

    boolean saveCustomer(CustomerDTO customerDTO, Connection connection);
    boolean deleteCustomer(String cusName, Connection connection);
    List<CustomerDTO> getCustomer(Connection connection) throws SQLException;
    boolean updateCustomer(CustomerDTO updatedCustomer, Connection connection);

}
