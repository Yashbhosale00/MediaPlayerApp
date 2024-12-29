package com.example.mediaplayerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.mediaplayerapp.Download.DownloadWorker


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mediaPlayerHelper = MediaPlayerHelper(this)

        setContent {
            var isPlaying by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Text widget to display the title
                Text("Audio/Video Player")

                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = {
                    if (isPlaying) {
                        mediaPlayerHelper.pause()
                    } else {
                        mediaPlayerHelper.setMediaSource("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4\n") // Use a valid URL
                        mediaPlayerHelper.play()
                    }
                    isPlaying = !isPlaying
                }) {
                    Text(if (isPlaying) "Pause" else "Play")
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = {
                    val workRequest = OneTimeWorkRequest.Builder(DownloadWorker::class.java).build()
                    val workRequestList: List<WorkRequest> = listOf(workRequest)
                    WorkManager.getInstance(applicationContext).enqueue(workRequestList)
                }) {
                    Text("Download")
                }
            }
        }
    }
}
