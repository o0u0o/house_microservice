package com.o0u0o.house.api.controller;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * <h1>加解密</h1>
 *
 * @author o0u0o
 * @since 2026/8/23 16:41
 */

@Profile({"dev", "qa"}) // ← 生产环境不注册，安全第一
@RestController
@RequestMapping("/dev/tools")
public class EncryptController {

    private final StringEncryptor encryptor;

    public EncryptController(StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    /**
     * 加密接口
     * POST /dev/tools/encrypt
     * body: {"text": "abc123"}
     * resp: {"plain": "abc123", "encrypted": "ENC(xxx==)"}
     */
    @PostMapping("/encrypt")
    public Map<String, String> encrypt(@RequestBody Map<String, String> body) {
        String plain = body.get("text");
        if (plain == null || plain.isEmpty()) {
            return Map.of("error", "text 不能为空");
        }
        String cipher = encryptor.encrypt(plain);
        return Map.of(
                "plain", plain,
                "encrypted", "ENC(" + cipher + ")"
        );
    }

    /**
     * 解密接口
     * POST /dev/tools/decrypt
     * body: {"text": "ENC(xxx==)"}
     * resp: {"cipher": "ENC(xxx==)", "decrypted": "abc123"}
     */
    @PostMapping("/decrypt")
    public Map<String, String> decrypt(@RequestBody Map<String, String> body) {
        String wrapped = body.get("text");
        if (wrapped == null || !wrapped.startsWith("ENC(") || !wrapped.endsWith(")")) {
            return Map.of("error", "请输入 ENC(...) 格式");
        }
        String cipher = wrapped.substring(4, wrapped.length() - 1);
        String plain = encryptor.decrypt(cipher);
        return Map.of(
                "cipher", wrapped,
                "decrypted", plain
        );
    }
}
