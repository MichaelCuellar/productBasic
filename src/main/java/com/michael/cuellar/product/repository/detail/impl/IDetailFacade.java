package com.michael.cuellar.product.repository.detail.impl;

import com.michael.cuellar.product.models.entity.Detail;
import com.michael.cuellar.product.models.entity.Product;
import com.michael.cuellar.product.models.entity.Sale;

import java.util.Optional;

public interface IDetailFacade {

    Optional<Detail> findByProduct(Product product, Sale sale);
}
