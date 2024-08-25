package org.example.posbackendjavaee.bo;

import org.example.posbackendjavaee.dao.ItemDataProcessImpl;
import org.example.posbackendjavaee.model.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemBOImpl {

    ItemDataProcessImpl itemDataProcess = new ItemDataProcessImpl();

    public boolean saveItem(ItemDTO itemDTO, Connection connection) {
        return itemDataProcess.save(itemDTO, connection);
    }

    public List<ItemDTO> getAllItems(Connection connection) throws SQLException {
        return itemDataProcess.getItem(connection);
    }

}
