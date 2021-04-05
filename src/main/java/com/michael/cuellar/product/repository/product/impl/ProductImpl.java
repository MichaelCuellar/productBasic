package com.michael.cuellar.product.repository.product.impl;

import com.michael.cuellar.product.models.entity.Product;
import com.michael.cuellar.product.repository.product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImpl implements IProductFacade {

    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public List<Product> index() {
        return iProductRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long idProduct) {
        return iProductRepository.findById(idProduct);
    }
}
