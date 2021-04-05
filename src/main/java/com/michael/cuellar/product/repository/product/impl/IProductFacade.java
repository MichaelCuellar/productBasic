package com.michael.cuellar.product.repository.product.impl;

import com.michael.cuellar.product.models.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductFacade {


    List<Product> index();

    Optional<Product> findById(Long idProduct);
}
