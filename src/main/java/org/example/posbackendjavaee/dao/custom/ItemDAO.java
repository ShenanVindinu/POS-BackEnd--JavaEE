package org.example.posbackendjavaee.dao.custom;

import org.example.posbackendjavaee.model.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {

    List<ItemDTO> getItem(Connection connection) throws SQLException;
    boolean save(ItemDTO itemDTO, Connection connection);
    boolean deleteItem(String itemName, Connection connection);
    boolean update(ItemDTO updatedItem, Connection connection);

}
