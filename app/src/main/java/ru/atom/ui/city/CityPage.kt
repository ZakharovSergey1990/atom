package ru.atom.ui.city

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.serialization.Serializable
import ru.atom.R
import ru.atom.ui.common.TopBar
import ru.atom.ui.common.ViewStateContent
import ru.atom.ui.theme.paddings


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityPage(vm: CityPageViewModel = hiltViewModel()) {
    val state by vm.state.collectAsState()

    Scaffold(
        topBar = {
     TopBar(title = stringResource(R.string.choose_city))
        }
    ) {
        ViewStateContent(state = state) { cities ->
            LazyColumn(modifier = Modifier.padding(it)){
                items(cities){ city ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = MaterialTheme.paddings.medium,
                            vertical = MaterialTheme.paddings.small
                        )
                        .clickable {
                            vm.onCityClicked(city)
                        }
                    ) {
                        Text(text = city, modifier = Modifier.padding(MaterialTheme.paddings.default))
                    }
                }
            }
        }
    }
}

