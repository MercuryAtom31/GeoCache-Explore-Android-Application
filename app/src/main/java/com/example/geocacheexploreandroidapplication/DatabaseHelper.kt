package com.example.geocacheexploreandroidapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "geocacheDatabase"
        private const val DATABASE_VERSION = 1

        private const val TABLE_GEOCACHE = "geocaches"
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_ADDRESS = "address"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createGeocacheTable = """
            CREATE TABLE $TABLE_GEOCACHE (
                $KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $KEY_NAME TEXT,
                $KEY_DESCRIPTION TEXT,
                $KEY_ADDRESS TEXT
            )
        """.trimIndent()
        db.execSQL(createGeocacheTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_GEOCACHE")
        onCreate(db)
    }

    fun addGeocache(geocache: Geocache) {
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(KEY_NAME, geocache.name)
            put(KEY_DESCRIPTION, geocache.description)
            put(KEY_ADDRESS, geocache.address)
        }

        db.insert(TABLE_GEOCACHE, null, values)
        db.close()
    }

    fun getAllGeocaches(): List<Geocache> {
        val geocaches = mutableListOf<Geocache>()
        val selectQuery = "SELECT * FROM $TABLE_GEOCACHE"

        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(KEY_ID)
            val nameIndex = cursor.getColumnIndex(KEY_NAME)
            val descriptionIndex = cursor.getColumnIndex(KEY_DESCRIPTION)
            val addressIndex = cursor.getColumnIndex(KEY_ADDRESS)

            do {
                val geocache = Geocache(
                    id = if (idIndex != -1) cursor.getInt(idIndex) else null,
                    name = if (nameIndex != -1) cursor.getString(nameIndex) else "",
                    description = if (descriptionIndex != -1) cursor.getString(descriptionIndex) else "",
                    address = if (addressIndex != -1) cursor.getString(addressIndex) else ""
                )
                geocaches.add(geocache)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return geocaches
    }

}
