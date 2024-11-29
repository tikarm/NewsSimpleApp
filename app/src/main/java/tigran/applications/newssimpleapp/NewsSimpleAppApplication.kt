package tigran.applications.newssimpleapp

import android.app.Application
import tigran.applications.newssimpleapp.data.local.db.AppDatabase

class NewsSimpleAppApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
}