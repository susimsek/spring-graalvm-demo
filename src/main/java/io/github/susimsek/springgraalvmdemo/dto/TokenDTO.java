package io.github.susimsek.springgraalvmdemo.dto;

public record TokenDTO(
    String accessToken,

    String tokenType,

    long accessTokenExpiresIn
) {}
