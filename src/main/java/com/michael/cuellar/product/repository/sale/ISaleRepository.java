package com.michael.cuellar.product.repository.sale;

import com.michael.cuellar.product.models.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleRepository extends JpaRepository<Sale,Long> {

}
