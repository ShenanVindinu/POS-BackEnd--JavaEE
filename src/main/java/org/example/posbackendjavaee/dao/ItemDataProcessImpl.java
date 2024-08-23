package org.example.posbackendjavaee.dao;

import org.example.posbackendjavaee.model.CustomerDTO;
import org.example.posbackendjavaee.model.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class ItemDataProcessImpl {

    static String SAVE_STUDENT = "INSERT INTO Item (id,name,qty,price) VALUES (?,?,?,?)";
    static String GET_STUDENT = "SELECT * FROM customer WHERE id=?";
    static String UPDATE_STUDENT = "UPDATE student SET name=?,city=?,email=?,level=? WHERE id=?";
    static String DELETE_STUDENT = "DELETE FROM student WHERE id=?";

    public boolean save(ItemDTO itemDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement(SAVE_STUDENT);
            ps.setString(1, itemDTO.getId());
            ps.setString(2, itemDTO.getName());
            ps.setString(3, itemDTO.getQty());
            ps.setString(4, itemDTO.getPrice());
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}
