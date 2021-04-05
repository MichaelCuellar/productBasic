package com.michael.cuellar.product.repository.user.impl;

import com.michael.cuellar.product.models.entity.User;

import java.util.Optional;

public interface IUserFacade {


    Optional<User> findUserById(Long idUser);
}
