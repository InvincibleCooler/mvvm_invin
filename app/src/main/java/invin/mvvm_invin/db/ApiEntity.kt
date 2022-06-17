package invin.mvvm_invin.db

import androidx.annotation.Keep
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "tb_api", indices = [(Index(value = ["key"], unique = true))])
data class ApiEntity constructor(
    @ColumnInfo(name = "key")
    @NonNull
    var key: Long,

    @ColumnInfo(name = "body")
    var body: String?,

    @ColumnInfo(name = "timestamp")
    var timestamp: Long = 0
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    @NonNull
    var uid: Long = 0
}