package org.example.posbackendjavaee.Controller;

import jakarta.json.JsonException;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.posbackendjavaee.bo.ItemBOImpl;
import org.example.posbackendjavaee.dao.ItemDataDAOImpl;
import org.example.posbackendjavaee.model.ItemDTO;
import org.example.posbackendjavaee.util.UtilProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/Item")
public class ItemController extends HttpServlet {

    static Logger logger = LoggerFactory.getLogger(CustomerController.class);
    Connection connection;

    public void init() {
        logger.info("Initializing ItemController with call init method");
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/POS-BackEnd");
            this.connection = pool.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        logger.info("POST request received");

        try (var writer = resp.getWriter()) {
            Jsonb jObj = JsonbBuilder.create();
            ItemDTO itemDTO = jObj.fromJson(req.getReader(), ItemDTO.class);

            itemDTO.setId(UtilProcess.generateId());

            var saveData = new ItemBOImpl();
            if (saveData.saveItem(itemDTO, connection)) {
                writer.write("Item saved successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("Item save failed");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (JsonbException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            throw new RuntimeException(e);
        }

        logger.info("POST request successfully completed");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var dataProcess = new ItemBOImpl();
        try (var writer = resp.getWriter()) {
            var item = dataProcess.getAllItems(connection);
            System.out.println(item);
            resp.setContentType("application/json");
            Jsonb jsonb = JsonbBuilder.create();
            jsonb.toJson(item, writer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var itemName = req.getParameter("name");
        try (var writer = resp.getWriter()) {
            var itemDataProcess = new ItemDataDAOImpl();
            if (itemDataProcess.deleteItem(itemName, connection)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                writer.write("Delete Failed");
            }
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try (var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            var itemDataProcess = new ItemBOImpl();
            var updatedItem = jsonb.fromJson(req.getReader(), ItemDTO.class);
            if (itemDataProcess.updateItem(updatedItem, connection)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                writer.write("Update Failed");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}
