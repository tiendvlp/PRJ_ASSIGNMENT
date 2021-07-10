package servlet.shopping;

import data.dao.ProductDao;
import data.dto.ProductDto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static common.Config.*;
import common.ResouceDynamicMapping;
import data.dto.CategoryDto;
import java.util.HashMap;

public class LoadShoppingProductServlet extends HttpServlet {

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
        ResouceDynamicMapping resourceMapping = (ResouceDynamicMapping) req.getServletContext().getAttribute(ResouceDynamicMapping.KEY);
        HashMap<String, String> roadMap = resourceMapping.getRoadMap();
        String url = roadMap.get(SHOPPING_JSP);
        try {
            List<ProductDto> products = productDao.getAll();
            List<CategoryDto> categories = productDao.getAllCategories();
            req.setAttribute("SHOPPING_PRODUCT", products);
            req.setAttribute("SHOPPING_CATEGORY", categories);

        } catch (SQLException ex) {
            log("LoadShoppingProductServlet error due to: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("LoadShoppingProductServlet error due to: " + ex.getMessage());
        } finally {
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

}
