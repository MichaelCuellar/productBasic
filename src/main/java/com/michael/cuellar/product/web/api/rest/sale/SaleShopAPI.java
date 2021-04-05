package com.michael.cuellar.product.web.api.rest.sale;


import com.michael.cuellar.product.commons.request.AddSale;
import com.michael.cuellar.product.commons.request.EditSaleDTO;
import com.michael.cuellar.product.models.entity.Product;
import com.michael.cuellar.product.service.sale.ISaleShopService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class SaleShopAPI {

    @Autowired
    private ISaleShopService iSaleShopService;

    @GetMapping(value = "/product")
    public List<Product> index(){
        return iSaleShopService.index();
    }

    @PostMapping(value = "/sale/{idUser}")
    public ResponseEntity<?> sale(@RequestBody List<AddSale> addSales, @PathVariable Long idUser){
        return iSaleShopService.sale(idUser,addSales);
    }

    @PostMapping(value = "/editSale/{idSale}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idSale" , required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "oldProduct", value = "Campo que tiene el codigo del producto anterior",required = false, dataType = "long", paramType = "body"),
            @ApiImplicitParam(name = "newProduct", value = "Campo que tiene el codigo del nuevo producto a reemplazar o agregar", required = true, dataType = "long", paramType = "body")
    })
    public ResponseEntity<?> editSale(@RequestBody List<EditSaleDTO> editSaleDTOS, @PathVariable Long idSale){
        return iSaleShopService.editSale(idSale,editSaleDTOS);
    }

    @DeleteMapping("/delete/{idSale}")
    public ResponseEntity<?> editSale(@PathVariable Long idSale){
        return iSaleShopService.deleteSale(idSale);
    }

}
