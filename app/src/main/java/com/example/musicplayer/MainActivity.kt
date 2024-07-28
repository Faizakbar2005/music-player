package com.example.musicplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playStopButton = findViewById<ImageButton>(R.id.playStopButton)

        // Set the URL of the online audio file
        val audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3" // Replace with your online music URL

        playStopButton.setOnClickListener {
            if (isPlaying) {
                stopMusic()
                playStopButton.setImageResource(R.drawable.play_icon) // Replace with your play icon
            } else {
                playMusic(audioUrl)
                playStopButton.setImageResource(R.drawable.stop_icon) // Replace with your stop icon
            }
            isPlaying = !isPlaying
        }
    }

    private fun playMusic(url: String) {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(url)
            setOnPreparedListener { start() }
            prepareAsync()
        }
    }

    private fun stopMusic() {
        mediaPlayer?.apply {
            stop()
            reset()
            release()
        }
        mediaPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        stopMusic()
    }
}
