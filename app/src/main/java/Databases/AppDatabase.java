package Databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mediaplayerapp.Download.DownloadedFile;
import com.example.mediaplayerapp.Interface.FileDao;

@Database(entities = {DownloadedFile.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FileDao fileDao();
}
