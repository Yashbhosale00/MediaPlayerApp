package com.example.mediaplayerapp.Interface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mediaplayerapp.Download.DownloadedFile;

import java.util.List;

@Dao
public interface FileDao {
    @Insert
    void insertFile(DownloadedFile file);

    @Query("SELECT * FROM downloaded_files")
    List<DownloadedFile> getAllFiles();

    @Query("UPDATE downloaded_files SET playbackProgress = :progress WHERE id = :fileId")
    void updateProgress(int fileId, long progress);
}
