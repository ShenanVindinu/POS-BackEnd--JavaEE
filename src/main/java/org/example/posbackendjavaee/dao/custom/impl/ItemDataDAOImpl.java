package org.example.posbackendjavaee.dao.custom.impl;

import org.example.posbackendjavaee.dao.custom.ItemDAO;
import org.example.posbackendjavaee.model.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDataDAOImpl implements ItemDAO {

    static String SAVE_ITEM = "INSERT INTO Item (id,name,qty,price) VALUES (?,?,?,?)";
    static String GET_ITEM = "SELECT * FROM customer WHERE id=?";
    static String GET_ALL_ITEM = "SELECT * FROM Item";
    static String UPDATE_ITEM = "UPDATE Item SET name=?,qty=?,price=? WHERE name=?";
    static String DELETE_ITEM = "DELETE FROM Item WHERE name=?";

    @Override
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

    @Override
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

    @Override
    public boolean deleteItem(String itemName, Connection connection) {
        try {
            var ps = connection.prepareStatement(DELETE_ITEM);
            ps.setString(1, itemName);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean update(ItemDTO updatedItem, Connection connection) {
        try {
            var ps = connection.prepareStatement(UPDATE_ITEM);
            ps.setString(1, updatedItem.getName());
            ps.setString(2, updatedItem.getQty());
            ps.setString(3, updatedItem.getPrice());
            ps.setString(4, updatedItem.getName());
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
