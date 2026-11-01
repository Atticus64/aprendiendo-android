package com.example.miniproyecto3.data


import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "publicaciones")
data class Publication(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "titulo") val titulo: String,
    @ColumnInfo(name = "descripcion") var descripcion: String,
    @ColumnInfo(name = "fotoUri") var foto: String,
    @ColumnInfo(name = "fecha") var fecha: Date,
)