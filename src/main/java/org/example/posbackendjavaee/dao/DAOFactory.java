package org.example.posbackendjavaee.dao;

import org.example.posbackendjavaee.dao.custom.impl.CustomerDAOImpl;
import org.example.posbackendjavaee.dao.custom.impl.ItemDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, ITEM
    }

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            default:
                return null;
        }
    }
}
