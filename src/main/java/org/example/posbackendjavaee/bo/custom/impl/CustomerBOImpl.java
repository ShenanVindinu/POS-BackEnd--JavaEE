package org.example.posbackendjavaee.bo.custom.impl;

import org.example.posbackendjavaee.bo.custom.CustomerBO;
import org.example.posbackendjavaee.dao.DAOFactory;
import org.example.posbackendjavaee.dao.custom.CustomerDAO;
import org.example.posbackendjavaee.dao.custom.impl.CustomerDAOImpl;
import org.example.posbackendjavaee.model.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDataProcess = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO, Connection connection) {
        return customerDataProcess.save(customerDTO, connection);
    }

    @Override
    public boolean deleteCustomer(String cusName, Connection connection) {
        return customerDataProcess.deleteCustomer(cusName, connection);
    }

    @Override
    public List<CustomerDTO> getCustomer(Connection connection) throws SQLException {
        return customerDataProcess.getCustomer(connection);
    }

    @Override
    public boolean updateCustomer(CustomerDTO updatedCustomer, Connection connection) {
        return customerDataProcess.update(updatedCustomer, connection);
    }
}
