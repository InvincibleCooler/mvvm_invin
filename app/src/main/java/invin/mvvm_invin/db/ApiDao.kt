package invin.mvvm_invin.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * 쿼리 배치
 * select : getXXXX
 * insert : insertXXXX
 * update : updateXXXX
 * delete : deleteXXXX
 */
@Dao
interface ApiDao {
    @Query("select body from tb_api where `key` = :key")
    fun getApi(key: String): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApiEntity(apiEntity: ApiEntity)

    @Query("delete from tb_api where `key` = :key")
    fun deleteApi(key: String): Int

    @Query("delete from tb_api")
    fun deleteAllApi(): Int
}