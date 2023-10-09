package de.ait.shop.servlets;

import de.ait.shop.config.ApplicationConfig;
import de.ait.shop.models.Product;
import de.ait.shop.services.ProductsService;
import java.io.IOException;
import java.io.Writer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProductsServlet extends HttpServlet {

  private ProductsService productsService;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Long id = Long.parseLong(request.getParameter("id"));
    Product product = productsService.getById(id);
    Writer writer = response.getWriter();

    writer.write("" + product.getId());
    writer.write("<span>" + product.getPackage_unit() + "</span>");
  }

  @Override
  public void init() throws ServletException {
    ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    this.productsService = context.getBean(ProductsService.class);
  }

}
