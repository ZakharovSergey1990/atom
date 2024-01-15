package ru.atom.ui.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.atom.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    navigationIcon: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = navigationIcon,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}