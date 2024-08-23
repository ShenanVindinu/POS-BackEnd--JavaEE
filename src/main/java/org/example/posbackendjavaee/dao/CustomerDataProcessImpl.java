package org.example.posbackendjavaee.dao;

import org.example.posbackendjavaee.model.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;

public final class CustomerDataProcessImpl {

    static String SAVE_STUDENT = "INSERT INTO customer (id,name,address,salary) VALUES (?,?,?,?)";
    static String GET_STUDENT = "SELECT * FROM customer WHERE id=?";
    static String UPDATE_STUDENT = "UPDATE student SET name=?,city=?,email=?,level=? WHERE id=?";
    static String DELETE_STUDENT = "DELETE FROM student WHERE id=?";

    public CustomerDTO getStudentById(String id, Connection connection) throws SQLException {
        var customerDTO = new CustomerDTO();
        try {
            var ps = connection.prepareStatement(GET_STUDENT);
            ps.setString(1, id);
            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                customerDTO.setId(resultSet.getString("id"));
                customerDTO.setName(resultSet.getString("name"));
                customerDTO.setAddress(resultSet.getString("address"));
                customerDTO.setSalary(resultSet.getString("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerDTO;
    }

    public boolean save(CustomerDTO customerDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement(SAVE_STUDENT);
            ps.setString(1, customerDTO.getId());
            ps.setString(2, customerDTO.getName());
            ps.setString(3, customerDTO.getAddress());
            ps.setString(4, customerDTO.getSalary());
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


}

