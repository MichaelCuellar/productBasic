package com.michael.cuellar.product.repository.sale.impl;

import com.michael.cuellar.product.models.entity.Sale;
import com.michael.cuellar.product.repository.sale.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class SaleImpl implements ISaleFacade{

    @Autowired
    private ISaleRepository iSaleRepository;

    @Override
    public Boolean addSale(Sale sale) {
        return !ObjectUtils.isEmpty(iSaleRepository.save(sale));
    }

    @Override
    public Optional<Sale> findById(Long idSale) {
        return iSaleRepository.findById(idSale);
    }

    @Override
    public void delete(Sale sale) {
        iSaleRepository.delete(sale);
    }
}
