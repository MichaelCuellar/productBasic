package com.michael.cuellar.product.repository.user.impl;

import com.michael.cuellar.product.models.entity.User;
import com.michael.cuellar.product.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserImpl implements IUserFacade{

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public Optional<User> findUserById(Long idUser) {
        return iUserRepository.findById(idUser);
    }
}
