package com.github.users.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.designsystem.theme.HintColor
import com.github.designsystem.theme.MyMaterialTheme
import com.github.feature.detail.R

@Composable
fun ErrorState(modifier: Modifier = Modifier, onRetry: () -> Unit = {}) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable { onRetry() }
            .testTag("errorState"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_github),
            contentDescription = "errorStatePlaceHolderImage",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.surface)
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.error),
            fontSize = 12.sp,
            color = Red,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.errorText),
            fontSize = 12.sp,
            color = HintColor
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.tapToRetry),
            fontSize = 12.sp,
            color = HintColor
        )
    }
}

@Preview
@Composable
fun ErrorStatePreview() {
    MyMaterialTheme {
        Surface {
            ErrorState()
        }
    }
}
