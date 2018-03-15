package com.kevin.snake.bootlicense.pojo;

import org.springframework.stereotype.Component;

@Component
public class RSAKey {

    private Integer keyId;
    private byte[] privateKey;
    private byte[] publicKey;

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

}
