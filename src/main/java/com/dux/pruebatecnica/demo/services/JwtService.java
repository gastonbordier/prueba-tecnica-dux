package com.dux.pruebatecnica.demo.services;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public void generarToken(){
//        AeadAlgorithm enc = Jwts.ENC.A256GCM; //or A128GCM, A192GCM, A256CBC-HS512, etc...
//        SecretKey key = enc.key().build();
//
//        String message = "Live long and prosper.";
//        byte[] content = message.getBytes(StandardCharsets.UTF_8);
//
//// Create the compact JWE:
//        String jwe = Jwts.builder().content(content, "text/plain").encryptWith(key, enc).compact();
    }

}
