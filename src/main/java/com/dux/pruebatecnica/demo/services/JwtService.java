package com.dux.pruebatecnica.demo.services;

import com.dux.pruebatecnica.demo.utils.AppProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.KeyAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.Password;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    private final AppProperties appProperties;

    public String generarToken(String username) {
        KeyAlgorithm<Password, Password> alg = Jwts.KEY.PBES2_HS512_A256KW;
        AeadAlgorithm enc = Jwts.ENC.A256GCM;

        Password password = Keys.password(appProperties.getSecret().toCharArray());
        return Jwts.builder().issuer(username)
                .encryptWith(password, alg, enc)
                .compact();
    }

    public String obtenerUsuario(String token) {
        Password password = Keys.password(appProperties.getSecret().toCharArray());
        return Jwts.parser().decryptWith(password).build()
                .parseEncryptedClaims(token).getPayload().getIssuer();
    }

}
