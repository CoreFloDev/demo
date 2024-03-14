package io.coreflodev.dog

import android.app.Application
import android.content.Context
import io.coreflodev.dog.common.di.AppComponent
import io.coreflodev.dog.common.di.create
import io.coreflodev.dog.common.nav.AndroidNavigation

class DogApp : Application() {

    private val applicationComponent: AppComponent by lazy {
        AppComponent::class.create { AndroidNavigation(baseContext) }
    }

    companion object {

        fun appComponent(context: Context) = (context.applicationContext as DogApp).applicationComponent
    }
}
