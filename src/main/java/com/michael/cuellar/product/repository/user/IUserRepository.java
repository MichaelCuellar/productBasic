package com.michael.cuellar.product.repository.user;

import com.michael.cuellar.product.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
}
