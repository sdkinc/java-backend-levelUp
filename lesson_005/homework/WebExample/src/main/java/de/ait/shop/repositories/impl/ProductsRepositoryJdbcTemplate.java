package de.ait.shop.repositories.impl;

import de.ait.shop.models.Product;
import de.ait.shop.repositories.ProductsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository

public class ProductsRepositoryJdbcTemplate implements ProductsRepository {
  private final JdbcTemplate jdbcTemplate;
  private static final RowMapper<Product> PRODUCT_ROW_MAPPER = (row, rowNum) -> Product.builder()
      .id(row.getLong("id"))
      .price(row.getDouble("price"))
      .package_unit(row.getString("package_unit"))
      .build();

  private static final String SQL_SELECT_ONE_BY_ID = "select * from product where id = ? limit 1";
  @Override
  public Product findById(Long id) {
    try {
      return jdbcTemplate.queryForObject(SQL_SELECT_ONE_BY_ID, PRODUCT_ROW_MAPPER, id);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override
  public List<Product> findAll() {
    return null;
  }

  @Override
  public void save(Product model) {

  }

  @Override
  public void deleteById(Long id) {

  }

  @Override
  public void update(Product model) {

  }
}
