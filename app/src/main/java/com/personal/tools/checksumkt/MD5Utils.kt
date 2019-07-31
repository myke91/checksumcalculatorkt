package com.personal.tools.checksumkt

import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.math.BigInteger
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

        var buffer = ByteArray(8192)
        var read: Int
        try {
            while (inputStream.read() > 0) {
                read = inputStream.read(buffer)
                digest.update(buffer, 0, read);
            }
        }finally {
            inputStream.close()
        }

        val md5sum: ByteArray = digest.digest();
        val bigInt: BigInteger = BigInteger(1, md5sum)
        var output: String = bigInt.toString(16)

        output = String.format("%32", output).replace(' ', '0')
        return output
    }
}