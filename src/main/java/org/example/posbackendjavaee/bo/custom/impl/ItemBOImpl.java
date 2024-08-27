package org.example.posbackendjavaee.bo.custom.impl;

import org.example.posbackendjavaee.bo.custom.ItemBO;
import org.example.posbackendjavaee.dao.DAOFactory;
import org.example.posbackendjavaee.dao.custom.ItemDAO;
import org.example.posbackendjavaee.dao.custom.impl.ItemDAOImpl;
import org.example.posbackendjavaee.model.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDataProcess = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean saveItem(ItemDTO itemDTO, Connection connection) {
        return itemDataProcess.save(itemDTO, connection);
    }

    @Override
    public List<ItemDTO> getAllItems(Connection connection) throws SQLException {
        return itemDataProcess.getItem(connection);
    }

    @Override
    public boolean updateItem(ItemDTO updatedItem, Connection connection) {
        return itemDataProcess.update(updatedItem,connection);
    }

    @Override
    public boolean deleteItem(String itemName, Connection connection) {
        return itemDataProcess.deleteItem(itemName,connection);
    }
}
