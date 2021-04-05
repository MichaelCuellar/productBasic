package com.michael.cuellar.product.service.sale;

import com.michael.cuellar.product.commons.request.AddSale;
import com.michael.cuellar.product.commons.request.EditSaleDTO;
import com.michael.cuellar.product.models.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ISaleShopService {

    List<Product> index();

    ResponseEntity<?> sale(Long idUser, List<AddSale> addSales);

    ResponseEntity<?> editSale(Long idSale, List<EditSaleDTO> editSaleDTO);

    ResponseEntity<?> deleteSale(Long idSale);
}
