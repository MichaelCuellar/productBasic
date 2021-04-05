package com.michael.cuellar.product.service.sale;

import com.michael.cuellar.product.commons.request.AddSale;
import com.michael.cuellar.product.commons.request.EditSaleDTO;
import com.michael.cuellar.product.commons.response.ResponseSale;
import com.michael.cuellar.product.models.entity.*;
import com.michael.cuellar.product.repository.detail.impl.IDetailFacade;
import com.michael.cuellar.product.repository.product.impl.IProductFacade;
import com.michael.cuellar.product.repository.sale.impl.ISaleFacade;
import com.michael.cuellar.product.repository.user.impl.IUserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements ISaleShopService {

    static final int COSTDELIVERY = 15000;
    @Autowired
    private IProductFacade iProductFacade;

    @Autowired
    private ISaleFacade iSaleFacade;

    @Autowired
    private IUserFacade iUserFacade;

    @Autowired
    private IDetailFacade iDetailFacade;


    @Override
    public List<Product> index() {
        return iProductFacade.index();
    }

    @Override
    public ResponseEntity<?> sale(Long idUser, List<AddSale> addSales) {

        Optional<User> user = iUserFacade.findUserById(idUser);
        if (!user.isPresent())
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Sale sale = new Sale();
        List<Detail> detailList = new ArrayList<>();
        long total = 0L;

        for (AddSale saleProduct : addSales) {
            Optional<Product> product = iProductFacade.findById(saleProduct.getIdProduct());
            if (!product.isPresent())
                return new ResponseEntity<>(HttpStatus.CONFLICT);

            Detail detail = new Detail();
            detail.setProduct(product.get());
            detail.setSale(sale);
            total += product.get().getPrice();
            detailList.add(detail);
        }

        sale.setDate(Calendar.getInstance());
        sale.setUser(user.get());
        sale.setDetailList(detailList);
        sale.setTotalCost(total);

        iSaleFacade.addSale(sale);

        ResponseSale responseSale = getResponseSale(sale, total);
        return new ResponseEntity<>(responseSale, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> editSale(Long idSale, List<EditSaleDTO> editSaleDTO) {

        Optional<Sale> sale = iSaleFacade.findById(idSale);
        if (!sale.isPresent())
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Calendar now = Calendar.getInstance();
        double time = (now.getTimeInMillis() - sale.get().getDate().getTimeInMillis()) / 3600000.00;

        if (time <= 5.0) {
            long total = (long) sale.get().getTotalCost();
            for (EditSaleDTO editDto : editSaleDTO) {
                Optional<Product> newProduct = iProductFacade.findById(editDto.getNewProduct());
                if (!newProduct.isPresent())
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                if (editDto.getOldProduct() != null) {
                    Optional<Product> oldProduct = iProductFacade.findById(editDto.getOldProduct());
                    if (!oldProduct.isPresent())
                        return new ResponseEntity<>(HttpStatus.CONFLICT);

                    if (oldProduct.get().getPrice() > newProduct.get().getPrice())
                        return new ResponseEntity<>(HttpStatus.CONFLICT);

                    Optional<Detail> detail = iDetailFacade.findByProduct(oldProduct.get(), sale.get());
                    detail.ifPresent(value -> value.setProduct(newProduct.get()));
                    total += (newProduct.get().getPrice() - oldProduct.get().getPrice());
                } else {
                    Detail detail = new Detail();
                    detail.setSale(sale.get());
                    detail.setProduct(newProduct.get());
                    sale.get().getDetailList().add(detail);
                    total += newProduct.get().getPrice();
                }
                sale.get().setTotalCost(total);
            }
            iSaleFacade.addSale(sale.get());
            ResponseSale responseSale = getResponseSale(sale.get(), total);
            return new ResponseEntity<>(responseSale, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @Override
    public ResponseEntity<?> deleteSale(Long idSale) {

        Optional<Sale> sale = iSaleFacade.findById(idSale);
        if (!sale.isPresent())
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Calendar now = Calendar.getInstance();
        double time = (now.getTimeInMillis() - sale.get().getDate().getTimeInMillis()) / 3600000.00;
        iSaleFacade.delete(sale.get());
        if (time <= 12)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(sale.get().getTotalCost() * 0.1, HttpStatus.OK);

    }

    private ResponseSale getResponseSale(Sale sale, long total) {
        ResponseSale responseSale = new ResponseSale();
        responseSale.setCostDelivery(COSTDELIVERY);
        if (total > 70000)
            responseSale.setSubTotal(total * 1.19);
        else
            responseSale.setSubTotal(total);

        if (total > 100000)
            responseSale.setCostDelivery(0);

        responseSale.setTotal(responseSale.getSubTotal() + responseSale.getCostDelivery());
        responseSale.setIdSale(sale.getIdSale());
        return responseSale;
    }

}
