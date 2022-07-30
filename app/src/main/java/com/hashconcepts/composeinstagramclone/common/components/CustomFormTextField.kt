package com.hashconcepts.composeinstagramclone.common.components

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.hashconcepts.composeinstagramclone.ui.theme.*

/**
 * @created 30/07/2022 - 1:32 AM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomFormTextField(
    modifier: Modifier = Modifier,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    val darkTheme: Boolean = isSystemInDarkTheme()
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = modifier.border(
            width = 1.dp,
            color = if (darkTheme) LineColorDark else LineColorLight,
            shape = RoundedCornerShape(10)
        ),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = hint)
        },
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = if (darkTheme) FormFieldBgDark else FormFieldBgLight
        )
    )
}