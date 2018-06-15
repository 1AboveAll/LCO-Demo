package com.himanshurawat.lcodemo.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.himanshurawat.lcodemo.R
import kotlinx.android.synthetic.main.activity_splash.*

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var progressStatus = 0;
        val handler = Handler()


        Thread(Runnable {
            while (progressStatus < 100) {
                progressStatus += 1
                // Update the progress bar and display the
                //current value in the text view
                handler.post(Runnable { progress_bar.setProgress(progressStatus) })
                try {
                    // Sleep for 50 milliseconds.
                    Thread.sleep(50)
                    if (progressStatus == 100) {
                        startActivity(Intent(this@Splash, Question::class.java))
                        finish()
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                }

            }
        }).start()


    }
}
