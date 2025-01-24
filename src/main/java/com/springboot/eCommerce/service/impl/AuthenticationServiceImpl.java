package com.springboot.eCommerce.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.springboot.eCommerce.dto.request.AuthenticationRequest;
import com.springboot.eCommerce.dto.request.IntrospectRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.AuthenticationResponse;
import com.springboot.eCommerce.dto.response.IntrospectResponse;
import com.springboot.eCommerce.entity.User;
import com.springboot.eCommerce.exception.AppException;
import com.springboot.eCommerce.exception.ErrorCode;
import com.springboot.eCommerce.repository.UserRepository;
import com.springboot.eCommerce.service.AuthenticationServiceInterface;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationServiceInterface {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;
    @Override
    public ApiResponse<AuthenticationResponse> login(AuthenticationRequest request) {
        User user = userRepository.findUserByUsername(request.getUsername()).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND)
        );

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        String token = generateToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(true, token);
        return new ApiResponse<>(authenticationResponse);
    }

    @Override
    public ApiResponse<IntrospectResponse> introspect(IntrospectRequest request) throws JOSEException, ParseException {

        String token = request.getToken();

        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean valid = signedJWT.verify(jwsVerifier);
        IntrospectResponse introspectResponse = new IntrospectResponse(valid && expiryTime.after(new Date()));

        return new ApiResponse<>(introspectResponse);
    }

    private String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("eCommerce.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(2, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", buildScope(user))

        .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token: ", e);
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(
                    role -> {
                        stringJoiner.add("ROLE_" + role.getName());

                        role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
                    }
            );

        return stringJoiner.toString();
    }
}
