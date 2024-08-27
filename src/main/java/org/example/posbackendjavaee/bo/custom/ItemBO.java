package org.example.posbackendjavaee.bo.custom;

import org.example.posbackendjavaee.bo.SuperBO;
import org.example.posbackendjavaee.model.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {

    boolean saveItem(ItemDTO itemDTO, Connection connection);

    List<ItemDTO> getAllItems(Connection connection) throws SQLException;

    boolean updateItem(ItemDTO updatedItem, Connection connection);

    boolean deleteItem(String itemName, Connection connection);
}
