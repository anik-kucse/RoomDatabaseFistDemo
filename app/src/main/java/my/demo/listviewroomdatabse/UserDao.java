package my.demo.listviewroomdatabse;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user_table")
    List<User> getAllUser();

    @Insert
    void insert(User user);

    @Query("DELETE FROM user_table")
    void deleteAll();
}
