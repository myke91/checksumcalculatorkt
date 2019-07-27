package com.personal.tools.checksumkt

import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.security.MessageDigest

class MD5Utils {

    fun getMD5(file: File?): String {
        val calculatedDigest = calculateDigest(file)
        Log.w("CHECKSUM_CALCULATOR", calculatedDigest)
        return calculatedDigest
    }

    fun calculateDigest(file: File?): String {
        val digest: MessageDigest = MessageDigest.getInstance("MD5")
        val inputStream: InputStream = FileInputStream(file)
    }
}