package ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_table")
data class CardEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ownerName: String,
    val cardNumber: String,
    val cvv: String,
    val dueDate: String
)
