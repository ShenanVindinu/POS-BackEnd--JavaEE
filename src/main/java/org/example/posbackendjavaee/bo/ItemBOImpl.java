package org.example.posbackendjavaee.bo;

import org.example.posbackendjavaee.dao.ItemDataDAOImpl;
import org.example.posbackendjavaee.model.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemBOImpl {

    ItemDataDAOImpl itemDataProcess = new ItemDataDAOImpl();

    public boolean saveItem(ItemDTO itemDTO, Connection connection) {
        return itemDataProcess.save(itemDTO, connection);
    }

    public List<ItemDTO> getAllItems(Connection connection) throws SQLException {
        return itemDataProcess.getItem(connection);
    }

    public boolean updateItem(ItemDTO updatedItem, Connection connection) {
        return itemDataProcess.update(updatedItem,connection);
    }
}
