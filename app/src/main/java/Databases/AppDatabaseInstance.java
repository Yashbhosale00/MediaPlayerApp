package Databases;

import android.content.Context;

import androidx.room.Room;
import Databases.AppDatabase;

public class AppDatabaseInstance {
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .fallbackToDestructiveMigration() // Handle migration
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
