package com.ars.comusenias.domain.library

import android.util.Base64
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object LibraryPassword {

    private const val ITERATION_COUNT = 100000
    private const val KEY_LENGTH = 256
    private const val ALGORITHM = "PBKDF2WithHmacSHA256"
    private const val SALT_LENGTH = 24
    private const val SALT_ARRAY = 16

    fun hashPassword(password: String): String {
        val salt = generateSalt()
        val spec = PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH)
        val factory = SecretKeyFactory.getInstance(ALGORITHM)
        val key = factory.generateSecret(spec)
        val hash = key.encoded
        return Base64.encodeToString(hash, Base64.NO_WRAP)
    }

    fun checkPassword(password: String, hashedPassword: String): Boolean {
        val salt = Base64.decode(hashedPassword.substring(0, SALT_LENGTH), Base64.NO_WRAP)
        val spec = PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH)
        val factory = SecretKeyFactory.getInstance(ALGORITHM)
        val key = factory.generateSecret(spec)
        val hash = key.encoded
        return hash.contentEquals(
            Base64.decode(
                hashedPassword.substring(SALT_LENGTH), Base64.NO_WRAP
            )
        )
    }

    fun encodePassword(password: String): String {
        return Base64.encodeToString(password.toByteArray(), Base64.DEFAULT)
    }

    private fun generateSalt(): ByteArray {
        val bytes = ByteArray(SALT_ARRAY)
        SecureRandom().nextBytes(bytes)
        return bytes
    }
}