package com.APIBackend.demo.Utils

import java.nio.charset.StandardCharsets.UTF_8
import java.security.MessageDigest

fun md5(s: String):ByteArray = MessageDigest.getInstance("MD5").digest(s.toByteArray(UTF_8))

fun ByteArray.toHex() = joinToString(""){byte: Byte -> "%02x".format(byte) }