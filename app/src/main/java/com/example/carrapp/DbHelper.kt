package com.example.carrapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, "app", factory, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE users (id INT PRIMARY KEY, email TEXT, pass TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun addUser(user: User) {
        val values = ContentValues()
        values.put("email", user.email)
        values.put("pass", user.pass)

        val db = this.writableDatabase
        db.insert("users", null, values)

        db.close()
    }

    fun getUser(email: String, pass: String): Boolean {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM users WHERE email = '$email' AND pass = '$pass'", null)
        return result.moveToFirst()
    }

    fun getLastId(): Int {
        val db = this.readableDatabase

        var lastId = -1 // Значение по умолчанию, если таблица пуста или произошла ошибка

        try {
            val result: Cursor = db.rawQuery("SELECT id FROM users ORDER BY id DESC LIMIT 1", null)

            if (result.moveToFirst()) { // Проверяем, есть ли хоть одна запись
                lastId = result.getInt(0) // Получаем значение id из первого столбца (индекс 0)
            }

            result.close() // Закрываем курсор, чтобы освободить ресурсы

        } catch (e: Exception) {
            // Обработка ошибок, например, логирование
            e.printStackTrace()
        } finally {
            db.close() // Закрываем базу данных в любом случае
        }

        return lastId

    }

}