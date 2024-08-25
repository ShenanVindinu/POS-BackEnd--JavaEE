package org.example.posbackendjavaee.dao;

import org.example.posbackendjavaee.model.CustomerDTO;
import org.example.posbackendjavaee.model.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDataProcessImpl {

    static String SAVE_ITEM = "INSERT INTO Item (id,name,qty,price) VALUES (?,?,?,?)";
    static String GET_ITEM = "SELECT * FROM customer WHERE id=?";
    static String GET_ALL_ITEM = "SELECT * FROM Item";
    static String UPDATE_ITEM = "UPDATE student SET name=?,city=?,email=?,level=? WHERE id=?";
    static String DELETE_ITEM = "DELETE FROM student WHERE id=?";

    public List<ItemDTO> getItem(Connection connection) throws SQLException {
        var items = new ArrayList<ItemDTO>();
        try (var ps = connection.prepareStatement(GET_ALL_ITEM); var resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                var itemDTO = new ItemDTO();
                itemDTO.setId(resultSet.getString("id"));
                itemDTO.setName(resultSet.getString("name"));
                itemDTO.setQty(resultSet.getString("qty"));
                itemDTO.setPrice(resultSet.getString("price"));
                items.add(itemDTO);
            }
        }
        return items;
    }

    public boolean save(ItemDTO itemDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement(SAVE_ITEM);
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
