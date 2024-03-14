package io.coreflodev.dog.feature.details.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.coreflodev.dog.common.arch.Screen
import io.coreflodev.dog.DogApp
import io.coreflodev.dog.feature.details.arch.DetailsInput
import io.coreflodev.dog.feature.details.arch.DetailsOutput

class DetailsStateHolder(app: Application, imageId: String) : AndroidViewModel(app) {

    val screen: Screen<DetailsInput, DetailsOutput> = DetailsComponent::class.create(DogApp.appComponent(app), imageId).screen

    override fun onCleared() {
        super.onCleared()
        screen.terminate()
    }

    class Factory(private val app: Application, private val imageId: String) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = DetailsStateHolder(app, imageId) as T
    }
}
