package com.example.mediaplayerapp.Download;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.mediaplayerapp.Download.DownloadedFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import Databases.AppDatabase;
import Databases.AppDatabaseInstance;

public class DownloadWorker extends Worker {

    public DownloadWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public Result doWork() {
        String fileUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";
        String fileName = "sample.mp4";
        File downloadDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(downloadDirectory, fileName);

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(fileUrl).openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            if (file.exists()) {
                Log.d("DownloadWorker", "File download completed successfully!");

                AppDatabase database = AppDatabaseInstance.getInstance(getApplicationContext());
                DownloadedFile downloadedFile = new DownloadedFile();
                downloadedFile.fileName = fileName;
                downloadedFile.filePath = file.getAbsolutePath();
                downloadedFile.playbackProgress = 0;
                downloadedFile.isSynced = false;
                database.fileDao().insertFile(downloadedFile);

                return Result.success();
            } else {
                Log.d("DownloadWorker", "File download failed.");
                return Result.failure();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return Result.failure();
        }
    }
}
