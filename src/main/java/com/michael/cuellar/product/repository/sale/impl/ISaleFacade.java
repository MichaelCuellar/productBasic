package com.michael.cuellar.product.repository.sale.impl;

import com.michael.cuellar.product.models.entity.Sale;

import java.util.Optional;

public interface ISaleFacade {

    Boolean addSale(Sale sale);

    Optional<Sale> findById(Long idSale);

    void delete(Sale sale);
}
