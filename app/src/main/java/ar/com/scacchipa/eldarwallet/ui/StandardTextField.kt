package ar.com.scacchipa.eldarwallet.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun StandardTextField(
    enabled: Boolean = true,
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    @DrawableRes drawableIconId: Int,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    OutlinedTextField(
        enabled = enabled,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = labelText) },
        leadingIcon = {
            Image(
                modifier = Modifier.size(width = 32.dp, height = 32.dp),
                painter = painterResource(id = drawableIconId),
                contentDescription = "Password"
            )
        },
        modifier = modifier
    )
}