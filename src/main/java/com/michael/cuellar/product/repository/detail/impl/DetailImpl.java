package com.michael.cuellar.product.repository.detail.impl;

import com.michael.cuellar.product.models.entity.Detail;
import com.michael.cuellar.product.models.entity.Product;
import com.michael.cuellar.product.models.entity.Sale;
import com.michael.cuellar.product.repository.detail.IDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class DetailImpl implements IDetailFacade{

    @Autowired
    private IDetailRepository iDetailRepository;

    @Override
    public Optional<Detail> findByProduct(Product product, Sale sale) {
        return iDetailRepository.findDetailByProductAndSale(product,sale);
    }
}
