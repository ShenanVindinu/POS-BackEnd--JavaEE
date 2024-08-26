package org.example.posbackendjavaee.dao;

import org.example.posbackendjavaee.model.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class CustomerDataProcessImpl {

    static String SAVE_CUSTOMER = "INSERT INTO customer (id,name,address,salary) VALUES (?,?,?,?)";
    static String GET_CUSTOMER = "SELECT * FROM customer WHERE id=?";
    static String GET_ALL_CUSTOMER = "SELECT * FROM customer";
    static String UPDATE_CUSTOMER = "UPDATE customer SET name=?,address=?,salary=? WHERE name=?";
    static String DELETE_CUSTOMER = "DELETE FROM customer WHERE name=?";

    public List<CustomerDTO> getCustomer(Connection connection) throws SQLException {
        var customers = new ArrayList<CustomerDTO>();
        try (var ps = connection.prepareStatement(GET_ALL_CUSTOMER); var resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                var customerDTO = new CustomerDTO();
                customerDTO.setId(resultSet.getString("id"));
                customerDTO.setName(resultSet.getString("name"));
                customerDTO.setAddress(resultSet.getString("address"));
                customerDTO.setSalary(resultSet.getString("salary"));
                customers.add(customerDTO);
            }
        }
        return customers;
    }

    public boolean save(CustomerDTO customerDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement(SAVE_CUSTOMER);
            ps.setString(1, customerDTO.getId());
            ps.setString(2, customerDTO.getName());
            ps.setString(3, customerDTO.getAddress());
            ps.setString(4, customerDTO.getSalary());
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    public boolean deleteCustomer(String cusName, Connection connection) {
        try {
            var ps = connection.prepareStatement(DELETE_CUSTOMER);
            ps.setString(1, cusName);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean update(CustomerDTO updatedCustomer, Connection connection) {
        try {
            var ps = connection.prepareStatement(UPDATE_CUSTOMER);
            ps.setString(1, updatedCustomer.getName());
            ps.setString(2, updatedCustomer.getAddress());
            ps.setString(3, updatedCustomer.getSalary());
            ps.setString(4, updatedCustomer.getName());
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

