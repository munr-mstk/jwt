package com.munirmustakoglu.jwt.service;

import com.munirmustakoglu.jwt.dto.DtoUser;
import com.munirmustakoglu.jwt.jwt.AuthRequest;

public interface IAuthService {

    public DtoUser register(AuthRequest request);
}
