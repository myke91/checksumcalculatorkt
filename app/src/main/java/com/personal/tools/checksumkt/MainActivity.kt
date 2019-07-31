package com.personal.tools.checksumkt

import android.content.Context
import android.content.ContextWrapper
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.EditText
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    var downloadUrl: String = "";
    var downloadedFile: File? = null
    var downloadLinkText: EditText? = null;
    var resultText: EditText? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        downloadLinkText = findViewById(R.id.downloadUrl)
        val button: Button = findViewById(R.id.button)
        resultText = findViewById(R.id.checksumResult)

        downloadLinkText?.setText("1FUmsSGn_KjeQD14_ajctKFGzJef4nLBl")
        downloadUrl =
            "https://drive.google.com/uc?export=download&confirm=no_antivirus&id=" + downloadLinkText?.getText().toString()

        button.setOnClickListener {
            process();
        }
    }

    fun process() {
        class Task : AsyncTask<Void, Void, String>() {

            override fun onPreExecute() {
                resultText?.setText("processing...")
            }

            override fun doInBackground(vararg params: Void?): String? {
                Log.d("CHECKSUM_KT", "started...")
                val md5Checker = MD5Utils()
                downloadApk(downloadUrl)
                return md5Checker.getMD5(downloadedFile)
            }

            override fun onPostExecute(result: String?) {
                resultText?.setText(result)
            }

        }

        Task().execute()

    }


    fun downloadApk(urlString: String) {
        val url = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        try {
            connection.connect()

            val cw = ContextWrapper(this)
            val directory = cw.getDir("media", Context.MODE_PRIVATE)
            val pathToFile = String.format("%s/%s.apk", directory, "checksumfile")
            downloadedFile = File(pathToFile)
            val fos = FileOutputStream(downloadedFile)
            copyStream(connection.inputStream, fos)
        } finally {
            connection.disconnect()
        }
    }

    fun copyStream(inputStream: InputStream, outputStream: OutputStream){

    }

}