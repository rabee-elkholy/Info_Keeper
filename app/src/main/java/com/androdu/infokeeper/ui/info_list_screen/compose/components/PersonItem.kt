package com.androdu.infokeeper.ui.info_list_screen.compose.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androdu.infokeeper.R
import com.androdu.infokeeper.domain.model.Person
import com.androdu.infokeeper.domain.utils.Gender
import com.androdu.infokeeper.domain.utils.JobTitle
import com.androdu.infokeeper.ui.theme.InfoKeeperTheme

/**
 * Displays a card view for a person's information.
 *
 * This composable function shows a card with a person's avatar, name, job title, age, and gender.
 * It also includes a delete button to remove the person from the list.
 *
 * @param modifier Optional [Modifier] to apply to the card.
 * @param person The [Person] object containing the information to be displayed.
 * @param onDeleteClick A lambda function to handle the delete action, which is called with the person's ID.
 */
@Composable
fun PersonItem(
    modifier: Modifier = Modifier,
    person: Person,
    onDeleteClick: (personId: Int) -> Unit
) {
    Card(
        modifier = modifier
            .height(120.dp)
            .padding(vertical = 4.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(8.dp)),
                painter = painterResource(id = person.avatar),
                contentScale = ContentScale.Inside,
                contentDescription = "${person.name}'s avatar"
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp, end = 4.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = person.name,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = stringResource(id = person.jobTitle.getStringRes()),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                )

                Row {
                    Text(
                        text = stringResource(R.string.age_label, person.age),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = stringResource(R.string.gender_label, stringResource(id = person.gender.getStringRes())),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                    )
                }
            }

            IconButton(
                onClick = { onDeleteClick(person.id) },
                modifier = Modifier.align(alignment = Alignment.Top)
            ) {
                Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Delete")
            }
        }
    }
}

/**
 * Preview of the [PersonItem] composable function.
 *
 * This preview provides a sample view of the [PersonItem] composable with mock data for development purposes.
 */
@Preview
@Composable
private fun PersonItemPreview() {
    InfoKeeperTheme {
        PersonItem(
            person = Person(
                name = "John Doe",
                age = 25,
                jobTitle = JobTitle.ANDROID_DEVELOPER,
                gender = Gender.MALE,
                avatar = R.drawable.ic_man_1
            ),
            onDeleteClick = {}
        )
    }
}
