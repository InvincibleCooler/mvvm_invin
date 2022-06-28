package invin.mvvm_invin.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * 쿼리 배치
 * select : getXXXX
 * insert : insertXXXX
 * update : updateXXXX
 * delete : deleteXXXX
 */
@Dao
interface ApiDao {
    @Query("select * from tb_api")
    fun getAll(): Flow<List<ApiEntity>>

    @Query("select count(*) from tb_api")
    fun getTotalCount(): Int

    @Query("select body from tb_api where `key` = :key")
    fun getApi(key: String): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApiEntity(apiEntity: ApiEntity)

    @Query("delete from tb_api where `key` = :key")
    suspend fun deleteApi(key: String): Int

    @Query("delete from tb_api")
    suspend fun deleteAll(): Int
}