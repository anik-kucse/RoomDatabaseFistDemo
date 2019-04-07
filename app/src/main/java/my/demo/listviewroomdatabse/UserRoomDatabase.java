package my.demo.listviewroomdatabse;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {User.class}, version = 1)
public abstract class UserRoomDatabase extends RoomDatabase {
    private static UserRoomDatabase INSTANCE;
    public abstract UserDao userDao();

    public static UserRoomDatabase getUserRoomDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserRoomDatabase.class, "user_table")
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserDao mUserDao;

        PopulateDbAsync(UserRoomDatabase db) {
            mUserDao = db.userDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mUserDao.deleteAll();

            User user = new User("Anik", "24");
            mUserDao.insert(user);
            user = new User("Akib", "16");
            mUserDao.insert(user);
            return null;
        }
    }


    public static void destryInstance(){
        INSTANCE = null;
    }
}
