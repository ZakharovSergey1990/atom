package ru.atom.ui.charger

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.atom.R
import ru.atom.ui.common.TopBar
import ru.atom.ui.common.ViewStateContent
import ru.atom.ui.theme.paddings

@Composable
fun ChargersPage(vm: ChargersViewModel) {
    val state by vm.state.collectAsState()
    Scaffold(
        topBar = {
            TopBar(title = stringResource(id = R.string.chargers),
                navigationIcon = {
                    IconButton(onClick = {
                        vm.onBackButtonClicked()
                    },
                        colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
                        ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {
        ViewStateContent(state = state) { chargers ->
            LazyColumn(modifier = Modifier.padding(it)) {
                items(chargers) { charger ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = MaterialTheme.paddings.medium,
                                vertical = MaterialTheme.paddings.small
                            ),
                        border = BorderStroke(width = 1.dp, color = if (charger.busy) Color.Red else Color.Green)
                    ) {
                        Column(modifier = Modifier.padding(MaterialTheme.paddings.default)) {
                            Text(
                                text = charger.name,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = MaterialTheme.paddings.medium)
                            )
                            Text(
                                text = charger.address,
                                modifier = Modifier.padding(horizontal = MaterialTheme.paddings.medium)
                            )
                        }
                    }
                }
            }
        }
    }
}