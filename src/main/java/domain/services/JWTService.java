package domain.services;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.*;
import com.auth0.jwt.interfaces.JWTVerifier;
import domain.models.*;

import javax.ejb.*;
import java.io.*;
import java.util.*;

@Local
@Stateless
public class JWTService {
    private static final String issuer = "gamestore";
    private static final int MINUTES = 20;
    private JWTVerifier verifier;

    public JWTService() {
        verifier = JWT.require(createAlgoritm())
                .withIssuer(issuer)
                .build();
    }

    public String createJWT(User u) throws JWTCreationException {
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("id", u.getId())
                .withClaim("email", u.getEmail())
                .withExpiresAt(getExpireDate(MINUTES))
                .sign(createAlgoritm());
    }

    public void verifyJWT(String token) throws JWTVerificationException {
        DecodedJWT decoded = verifier.verify(token);

        if (decoded.getClaim("email").isNull())
            throw new JWTVerificationException("Invalid claim data");

        // expired
        if (decoded.getExpiresAt().compareTo(new Date()) < 0)
            throw new JWTVerificationException("Token expired");
    }

    private Date getExpireDate(int minutesToAdd) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MINUTE, minutesToAdd);
        return c.getTime();
    }

    private Algorithm createAlgoritm(){

        byte[] key = new byte[0];
        try {
            key = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=".getBytes("UTF-8");
            Algorithm algorithm = Algorithm.HMAC256(key);
            return algorithm;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
