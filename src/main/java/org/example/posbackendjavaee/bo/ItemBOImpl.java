package org.example.posbackendjavaee.bo;

import org.example.posbackendjavaee.dao.ItemDataProcessImpl;
import org.example.posbackendjavaee.model.ItemDTO;

import java.sql.Connection;

public class ItemBOImpl {

    ItemDataProcessImpl itemDataProcess = new ItemDataProcessImpl();

    public boolean saveItem(ItemDTO itemDTO, Connection connection) {
        return itemDataProcess.save(itemDTO, connection);
    }

}
