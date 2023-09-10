package com.example.comusenias.domain.library

import android.util.Base64
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object LibraryPassword {

    fun hashPassword(password: String): String {
        val salt = generateSalt()
        val spec = PBEKeySpec(password.toCharArray(), salt, 100000, 256)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val key = factory.generateSecret(spec)
        val hash = key.encoded
        return Base64.encodeToString(hash, Base64.NO_WRAP)
    }

    fun checkPassword(password: String, hashedPassword: String): Boolean {
        val salt = Base64.decode(hashedPassword.substring(0, 24), Base64.NO_WRAP)
        val spec = PBEKeySpec(password.toCharArray(), salt, 100000, 256)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val key = factory.generateSecret(spec)
        val hash = key.encoded
        return hash.contentEquals(Base64.decode(hashedPassword.substring(24), Base64.NO_WRAP))
    }

    private fun generateSalt(): ByteArray {
        val bytes = ByteArray(16)
        SecureRandom().nextBytes(bytes)
        return bytes
    }
}