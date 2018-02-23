package io.github.muninn.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by viacheslavokolitiy on 16.02.2018.
 */
@Entity(tableName = "todo")
data class Todo(@PrimaryKey var id: Long,
                @ColumnInfo(name = "todo_title") var title: String,
                @ColumnInfo(name = "todo_description") var description: String,
                @ColumnInfo(name = "created_at") var createdAt: Long,
                @ColumnInfo(name = "updated_at") var updatedAt: Long,
                @ColumnInfo(name = "expires_at") var expiresAt: Long,
                @ColumnInfo(name = "expired") var expired: Boolean) {
}