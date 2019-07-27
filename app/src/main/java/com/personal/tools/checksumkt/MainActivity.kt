package com.personal.tools.checksumkt

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import java.io.File

class MainActivity : AppCompatActivity() {
    var downloadUrl: String = "";
    val downloadedFile: File? = null
    var downloadLinkText: EditText? = null;
    var resultText: EditText? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        downloadLinkText = findViewById(R.id.downloadUrl)
        val button: Button = findViewById(R.id.button)
        resultText = findViewById(R.id.checksumResult)

        downloadLinkText?.setText("1S-z5JJkEs6sJxUgA5rtXsHEnK9h4W4XW")
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

        }

        Task().execute()

    }


    fun downloadApk(url: String) {

    }


}