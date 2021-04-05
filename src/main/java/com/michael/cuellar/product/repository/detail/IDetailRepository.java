package com.michael.cuellar.product.repository.detail;

import com.michael.cuellar.product.models.entity.Detail;
import com.michael.cuellar.product.models.entity.Product;
import com.michael.cuellar.product.models.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDetailRepository extends JpaRepository<Detail,Long> {

    Optional<Detail> findDetailByProductAndSale(Product product, Sale sale);
}
