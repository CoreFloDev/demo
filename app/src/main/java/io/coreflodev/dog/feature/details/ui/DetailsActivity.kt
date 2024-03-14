package io.coreflodev.dog.feature.details.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import io.coreflodev.dog.R
import io.coreflodev.dog.feature.details.arch.DetailsInput
import io.coreflodev.dog.feature.details.arch.DetailsOutput
import io.coreflodev.dog.feature.details.arch.UiState
import io.coreflodev.dog.feature.details.di.DetailsStateHolder
import io.coreflodev.dog.common.nav.AndroidNavigation
import io.coreflodev.dog.common.ui.AndroidView
import io.coreflodev.dog.common.ui.BaseUi
import io.coreflodev.dog.common.ui.LoadImage

class DetailsActivity : ComponentActivity() {

    private val stateHolder by lazy {
        ViewModelProvider(
            this,
            DetailsStateHolder.Factory(application, intent.getStringExtra(AndroidNavigation.DetailsActivityNav.ID) ?: "")
        )[DetailsStateHolder::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidView(stateHolder.screen.attach()) { state, input ->
                BaseUi(id = R.string.detail_title) {
                    Content(output = state, input = input, it)
                }
            }
        }
    }

    override fun onDestroy() {
        stateHolder.screen.detach()
        super.onDestroy()
    }
}

@Composable
fun Content(output: DetailsOutput, input: (DetailsInput) -> Unit, paddingValues: PaddingValues) {

    when (output.uiState) {
        is UiState.Display -> {
            Box(modifier = Modifier.padding(paddingValues)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = output.uiState.name)
                    LoadImage(
                        url = output.uiState.image,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(text = output.uiState.origin)
                    Text(text = output.uiState.temperament)
                    Text(text = output.uiState.wikiUrl)
                }
            }
        }

        UiState.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        UiState.Retry -> {
            Button(onClick = {
                input(DetailsInput.RetryClicked)
            }) {
                Text(text = stringResource(id = R.string.retry))
            }
        }
    }
}
