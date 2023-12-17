package br.com.socialfolio.socialfolioapi.firebase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DefaultFolder {
    COVER("cover-usuarios/"),
    AVATAR("avatar-usuarios/");

    private final String path;

    public String getPath() {
        return path;
    }
}