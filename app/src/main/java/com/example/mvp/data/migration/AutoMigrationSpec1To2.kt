package com.example.mvp.data.migration

import androidx.room.DeleteTable
import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec

@DeleteTable(tableName = "user_note")
@RenameColumn(tableName = "notes", fromColumnName = "date_note", toColumnName = "note_date")
class AutoMigrationSpec1To2: AutoMigrationSpec