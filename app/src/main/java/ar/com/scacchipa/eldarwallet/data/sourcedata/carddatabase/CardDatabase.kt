package ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.sqlcipher.database.SupportFactory

@Database(entities = [CardEntity::class], version = 1)
abstract class CardDataBase : RoomDatabase() {
    abstract fun cardDao(): CardDao

    companion object {
        @Volatile
        private var INSTANCE: CardDataBase? = null

        fun getDatabase(context: Context, userName: String,passphrase: ByteArray): CardDataBase {
            return INSTANCE ?: synchronized(this) {
                val factory = SupportFactory(passphrase)
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardDataBase::class.java,
                    "encrypted_database_$userName"
                ).openHelperFactory(factory)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

