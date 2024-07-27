package ar.com.scacchipa.eldarwallet.data.repository.cardrepository

import net.sqlcipher.database.SQLiteDatabase

class CardRepository {


    val passphrase: ByteArray = SQLiteDatabase.getBytes("your_secure_passphrase".toCharArray())
    return CardDataBase.getDatabase(context, username, passphrase)

}