package com.ihebchiha.hiltapp.di.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.Assisted
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ihebchiha.hiltapp.R
import com.ihebchiha.hiltapp.base.BaseActivity
import com.ihebchiha.hiltapp.data.database.local.QuotesDao
import com.ihebchiha.hiltapp.data.database.local.QuotesDatabase
import com.ihebchiha.hiltapp.data.database.remote.QuotesRemoteDataSource
import com.ihebchiha.hiltapp.networking.service.QuotesApiService
import com.ihebchiha.hiltapp.repository.QuotesRepositoryImpl
import com.ihebchiha.hiltapp.ui.view.adapters.QuotesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideQuotesRemoteDataSource(quotesApiService: QuotesApiService): QuotesRemoteDataSource = QuotesRemoteDataSource(quotesApiService)


    //injecting or providing the room instance to the app
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): QuotesDatabase {
        return Room.databaseBuilder(context, QuotesDatabase::class.java, "Quotes").allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO


    @Singleton
    @Provides
    fun provideCharacterDao(db: QuotesDatabase) = db.quoteDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: QuotesRemoteDataSource, localDataSource: QuotesDao) = QuotesRepositoryImpl(remoteDataSource, localDataSource)

//    @Singleton
//    @Provides
//    fun provideActivity(): AppCompatActivity = return AppCompatActivity()

    @Provides
    fun provideBaseActivity(activity: AppCompatActivity): BaseActivity {
        check(activity is BaseActivity) { "Every Activity is expected to extend BaseActivity" }
        return activity
    }

}

@Module
@InstallIn(ActivityComponent::class)
object MainActivityModule {
    @Provides
    fun provideNavController(activity: AppCompatActivity): NavController {
        return activity.findNavController(R.id.nav_host_fragment)
    }
}
