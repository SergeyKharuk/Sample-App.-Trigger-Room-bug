package com.example.triggerroombug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = createDb()

        putInitialDataToDb()

        btn_insert.setOnClickListener {
            //Oleg had a birthday and need to update row using @Insert annotation
            val olegUser = User(2, "Oleg", 13)

            val carsBefore = database.dao().getCarsByUserId(olegUser.id)
            tv_cars_before.text = "Oleg has ${carsBefore.size} cars"

            database.dao().insertUser(olegUser)

            val carsAfter = database.dao().getCarsByUserId(olegUser.id)
            tv_cars_after.text = "Oleg has ${carsAfter.size} cars"
        }
    }

    private fun putInitialDataToDb() {
        database.dao().insertUser(User(1, "Alex", 43))
        database.dao().insertUser(User(2, "Oleg", 12))

        database.dao().insertCar(Car(1, "Toyota", "red", 1))
        database.dao().insertCar(Car(2, "Ford", "black", 1))
        database.dao().insertCar(Car(3, "Opel", "white", 2))
        database.dao().insertCar(Car(4, "Audi", "brown", 2))
    }

    private fun createDb(): AppDatabase {
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, "best_ever_db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}