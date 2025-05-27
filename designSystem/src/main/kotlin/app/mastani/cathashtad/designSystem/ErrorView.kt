package app.mastani.cathashtad.designSystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.mastani.cathashtad.designSystem.theme.Gray4

@Composable
fun ErrorView(
    onTryAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            painter = painterResource(id = R.drawable.img_error_view),
            contentDescription = ""
        )
        Text(
            text = stringResource(id = R.string.error_view_message),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 20.dp)
        )
        CustomizableButton(
            onClick = onTryAgain,
            text = stringResource(id = R.string.try_again),
            type = ButtonType.OUTLINE,
            textColor = Gray4,
            rightIconId = R.drawable.ic_try_again
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorViewPrev() {
    ErrorView(onTryAgain = {})
}