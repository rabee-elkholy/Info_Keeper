package com.androdu.infokeeper.ui.info_list_screen.compose

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androdu.infokeeper.R
import com.androdu.infokeeper.ui.info_list_screen.InfoListScreenAction
import com.androdu.infokeeper.ui.info_list_screen.InfoListScreenSideEffect
import com.androdu.infokeeper.ui.info_list_screen.InfoListScreenState
import com.androdu.infokeeper.ui.info_list_screen.InfoListViewModel
import com.androdu.infokeeper.ui.info_list_screen.compose.components.PersonItem
import com.androdu.infokeeper.ui.theme.InfoKeeperTheme
import org.koin.androidx.compose.koinViewModel

/**
 * Root composable for the Info List Screen.
 *
 * This composable sets up the Info List Screen by collecting the state from the view model,
 * handling side effects like showing a toast message for successful person deletion,
 * and composing the content of the screen.
 *
 * @param onBackClick Lambda function to handle back button clicks.
 * @param viewModel The [InfoListViewModel] instance provided by Koin.
 */
@Composable
fun InfoListScreenRoot(
    onBackClick: () -> Unit,
    viewModel: InfoListViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(true) {
        viewModel.sideEffect.collect {
            when (it) {
                is InfoListScreenSideEffect.PersonDeleteSuccess -> {
                    Toast.makeText(context, context.getString(R.string.person_deleted), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    InfoListScreenContent(
        state = state,
        onAction = viewModel::onAction,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoListScreenContent(
    state: InfoListScreenState,
    onAction: (InfoListScreenAction) -> Unit,
    onBackClick: () -> Unit,
) {
    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(horizontal = 12.dp)
                    .clip(RoundedCornerShape(8.dp)),
                title = {
                    Text(
                        text = stringResource(R.string.info_list),
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = "Back",
                        )
                    }
                }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding() + 8.dp)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val personsList = state.infoList
            if (personsList.isEmpty())
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(R.string.empty_list))
                }
            else
                LazyColumn(
                    state = scrollState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 8.dp)
                ) {
                    items(
                        count = personsList.size,
                        key = { personsList[it].id }
                    ) { index ->
                        val person = personsList[index]
                        PersonItem(
                            person = person,
                            onDeleteClick = {
                                onAction(InfoListScreenAction.OnDeleteClick(it))
                            }
                        )
                    }
                }
        }
    }
}

/**
 * Preview of the Info List Screen content.
 *
 * This preview provides a sample view of the Info List Screen content with mock data.
 */
@Preview
@Composable
private fun InfoListScreenContentPreview() {
    InfoKeeperTheme {
        InfoListScreenContent(
            state = InfoListScreenState(),
            onAction = {},
            onBackClick = {}
        )
    }
}
