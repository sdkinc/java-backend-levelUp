package de.ait.shop.services;

import de.ait.shop.models.Product;

public interface ProductsService {
  Product getById(Long id);
}
