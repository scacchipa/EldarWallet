package ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CardDao {
    @Insert
    suspend fun insert(cardEntity: CardEntity)

    @Query("SELECT * FROM my_table")
    suspend fun getAll(): List<CardEntity>
}
