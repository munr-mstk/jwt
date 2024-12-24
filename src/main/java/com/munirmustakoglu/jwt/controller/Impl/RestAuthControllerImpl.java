package com.munirmustakoglu.jwt.controller.Impl;

import com.munirmustakoglu.jwt.controller.IRestAuthController;
import com.munirmustakoglu.jwt.dto.DtoUser;
import com.munirmustakoglu.jwt.jwt.AuthRequest;
import com.munirmustakoglu.jwt.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

    @Autowired
    private IAuthService authService;


    @PostMapping("/register")
    @Override
    public DtoUser register(@Valid @RequestBody AuthRequest request) {
        return authService.register(request);
    }
}

