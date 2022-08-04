package com.hashconcepts.composeinstagramclone.auth.presentation.forgotpassword

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hashconcepts.composeinstagramclone.R
import com.hashconcepts.composeinstagramclone.auth.presentation.viewmodel.AuthScreenEvents
import com.hashconcepts.composeinstagramclone.auth.presentation.viewmodel.AuthViewModel
import com.hashconcepts.composeinstagramclone.auth.presentation.viewmodel.ResultEvents
import com.hashconcepts.composeinstagramclone.auth.presentation.destinations.RegisterScreenDestination
import com.hashconcepts.composeinstagramclone.common.components.CustomFormTextField
import com.hashconcepts.composeinstagramclone.common.components.CustomRaisedButton
import com.hashconcepts.composeinstagramclone.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

/**
 * @created 03/08/2022 - 6:01 AM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun ForgotPasswordScreen(
    navigator: DestinationsNavigator,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val darkTheme: Boolean = isSystemInDarkTheme()
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventChannelFlow.collectLatest { events ->
            when (events) {
                is ResultEvents.OnError -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = events.message,
                        duration = SnackbarDuration.Short
                    )
                }
                is ResultEvents.OnSuccess -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = events.message!!,
                        duration = SnackbarDuration.Short
                    )
                    navigator.navigateUp()
                }
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp)
                    .clickable { navigator.navigateUp() }
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                IconSection(darkTheme)

                Spacer(modifier = Modifier.height(20.dp))

                FormSection(viewModel)

                Spacer(modifier = Modifier.height(40.dp))

                SignUpSection {
                    navigator.popBackStack()
                    navigator.navigate(RegisterScreenDestination)
                }
            }
        }
    }
}

@Composable
fun SignUpSection(onSignUpClicked: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .height(1.dp)
                .weight(1f)
                .background(LineColor)
        )

        Text(
            text = "OR",
            style = MaterialTheme.typography.body1,
            color = LightGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .height(1.dp)
                .weight(1f)
                .background(LineColor)
        )
    }

    Spacer(modifier = Modifier.height(40.dp))

    Text(
        text = "Create New Account",
        style = MaterialTheme.typography.button,
        textAlign = TextAlign.Center,
        modifier = Modifier.clickable { onSignUpClicked() }
    )
}

@Composable
fun FormSection(viewModel: AuthViewModel) {
    Text(
        text = "Trouble Logging In?",
        style = MaterialTheme.typography.h2,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(10.dp))

    Text(
        text = "Enter your email and we'll send you a link to get back into your account.",
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(20.dp))

    var email by remember { mutableStateOf("") }

    CustomFormTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        hint = "Email",
        keyboardType = KeyboardType.Email,
        value = email,
        onValueChange = { email = it }
    )

    Spacer(modifier = Modifier.height(20.dp))

    CustomRaisedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        text = "Send Reset Email",
        isLoading = viewModel.isLoading
    ) {
        viewModel.onUserEvents(AuthScreenEvents.OnForgotPassword(email))
    }
}

@Composable
fun IconSection(darkTheme: Boolean) {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .border(
                width = 2.dp,
                color = if (darkTheme) IconDark else IconLight,
                shape = CircleShape
            )
            .padding(10.dp)
            .size(90.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.Lock,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = if (darkTheme) IconDark else IconLight,
        )
    }
}
