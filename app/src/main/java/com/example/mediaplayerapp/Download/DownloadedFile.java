package com.example.mediaplayerapp.Download;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "downloaded_files")
public class DownloadedFile {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String fileName;
    public String filePath;
    public long playbackProgress;

    public boolean isSynced;
}
