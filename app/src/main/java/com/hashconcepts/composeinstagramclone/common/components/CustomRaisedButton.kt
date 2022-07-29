package com.hashconcepts.composeinstagramclone.common.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hashconcepts.composeinstagramclone.ui.theme.AccentColor

/**
 * @created 29/07/2022 - 11:24 PM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */

@Composable
fun CustomRaisedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {

    Button(
        modifier = modifier.height(44.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = AccentColor),
        shape = RoundedCornerShape(size = 5.dp),
        onClick = onClick
    ) {
        Text(text = text, style = MaterialTheme.typography.button, color = Color.White)
    }
}