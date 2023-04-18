package dev.dgomes.businesscard

import android.app.Application
import dev.dgomes.businesscard.data.BusinessCardDatabase
import dev.dgomes.businesscard.data.BusinessCardRepository

class App : Application() {

    private val database by lazy { BusinessCardDatabase.getDatabase(this)}
    val repository by lazy { BusinessCardRepository(database.businessCardDao()) }

}