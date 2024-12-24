package com.munirmustakoglu.jwt.controller;

import com.munirmustakoglu.jwt.dto.DtoUser;
import com.munirmustakoglu.jwt.jwt.AuthRequest;

public interface IRestAuthController {


    public DtoUser register(AuthRequest request);
}
