package com.example.wikipedia.repositories

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class ArticleDatabaseOpenHelper(context: Context): ManagedSQLiteOpenHelper(context, "ArticlesDatabase.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        //create db table
        db?.createTable("Favourites", true,
            "id" to INTEGER + PRIMARY_KEY,
            "title" to TEXT,
            "url" to TEXT,
            "thumbnailJson" to TEXT)

        //create db table
        db?.createTable("History", true,
            "id" to INTEGER + PRIMARY_KEY,
            "title" to TEXT,
            "url" to TEXT,
            "thumbnailJson" to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //Used to upgrade the schema of the db if needed
    }

}