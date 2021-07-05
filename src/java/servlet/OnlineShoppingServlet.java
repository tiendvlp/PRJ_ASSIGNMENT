package servlet;

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
import servlet.bean.ProductBean;
import static common.Config.*;

public class OnlineShoppingServlet extends HttpServlet {
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
        String url = SHOPPING_PAGE; 
        try {
            List<ProductBean> productBeans = new ArrayList();
            List<ProductDto> result = productDao.getAll();
            if (!result.isEmpty()) {
                for (ProductDto product : result) {
                    productBeans.add(new ProductBean(product.getId(), product.getName(), product.getDescription(), product.getPrice()));
                }
            }
            req.setAttribute("SHOPPING_PRODUCT", productBeans);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
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
