package de.ait.shop.services.impl;

import de.ait.shop.models.Product;
import de.ait.shop.repositories.ProductsRepository;
import de.ait.shop.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductsServiceImpl implements ProductsService {
  private final ProductsRepository productsRepositoryJdbcTemplate;
  @Override
  public Product getById(Long id) {
    return productsRepositoryJdbcTemplate.findById(id);
  }
}
