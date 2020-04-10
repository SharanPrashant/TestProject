package com.mvvm.testproject.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mvvm.testproject.data.db.entities.Current_User_Id
import com.mvvm.testproject.data.db.entities.User


@Dao
    interface UserDao{
    @Insert(onConflict= OnConflictStrategy.REPLACE)
      suspend  fun upsert (user : User)  : Long

    @Query("Select * From User where uid = $Current_User_Id")
    fun getUser() : LiveData<User>
    }
