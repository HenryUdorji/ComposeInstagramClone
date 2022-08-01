package com.hashconcepts.composeinstagramclone.auth.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrowseGallery
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.*
import com.hashconcepts.composeinstagramclone.R
import com.hashconcepts.composeinstagramclone.auth.data.dto.CreateUserDto
import com.hashconcepts.composeinstagramclone.auth.presentation.destinations.LoginScreenDestination
import com.hashconcepts.composeinstagramclone.common.components.*
import com.hashconcepts.composeinstagramclone.common.utils.Constants
import com.hashconcepts.composeinstagramclone.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @created 29/07/2022 - 11:04 PM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun RegisterScreen(
    navigator: DestinationsNavigator,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val darkTheme: Boolean = isSystemInDarkTheme()
    val scaffoldState = rememberScaffoldState()
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

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

                    //navigator.navigate()
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
                Image(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = null,
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = if (darkTheme) IconDark else IconLight,
                            shape = CircleShape
                        )
                        .padding(20.dp)
                        .size(90.dp)
                        .clickable {
                            coroutineScope.launch {
                                bottomSheetState.show()
                            }
                        }
                )

                Spacer(modifier = Modifier.height(40.dp))

                var email by remember { mutableStateOf("") }
                var fullName by remember { mutableStateOf("") }
                var username by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                CustomFormTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    hint = "Email",
                    keyboardType = KeyboardType.Email,
                    value = email,
                    onValueChange = { email = it }
                )

                Spacer(modifier = Modifier.height(12.dp))

                CustomFormTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    hint = "Full Name",
                    value = fullName,
                    onValueChange = { fullName = it }
                )

                Spacer(modifier = Modifier.height(12.dp))

                CustomFormTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    hint = "Username",
                    value = username,
                    onValueChange = { username = it }
                )

                Spacer(modifier = Modifier.height(12.dp))

                CustomFormTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    hint = "Password",
                    value = password,
                    onValueChange = { password = it }
                )

                Spacer(modifier = Modifier.height(30.dp))

                CustomRaisedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = "Sign up",
                    isLoading = viewModel.isLoading
                ) {
                    val createUserDto = CreateUserDto(
                        email = email,
                        fullName = fullName,
                        username = username,
                        password = password,
                    )
                    viewModel.onUserEvents(AuthScreenEvents.OnRegister(null, createUserDto))
                }

                Spacer(modifier = Modifier.height(38.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp)
                ) {
                    Text(
                        text = "Have an account?",
                        style = MaterialTheme.typography.button,
                        color = LightGray,
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Log in",
                        style = MaterialTheme.typography.button,
                        modifier = Modifier.clickable {
                            navigator.popBackStack()
                            navigator.navigate(LoginScreenDestination)
                        }
                    )
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {

                Box(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(LineColor)
                )

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp)
                ) {
                    Text(
                        text = "Instagram from Meta",
                        style = MaterialTheme.typography.button,
                        color = LightGray,
                    )
                }
            }
        }


        val storagePermission = rememberPermissionState(permission = android.Manifest.permission.READ_EXTERNAL_STORAGE)
        val cameraPermission = rememberPermissionState(permission = android.Manifest.permission.CAMERA)
        val showPermissionRationale = remember { mutableStateOf(ShowRationale()) }

        ImagePickerBottomSheetDialog(bottomSheetState = bottomSheetState) { option ->
            when (option) {
                Constants.GALLERY -> {
                    coroutineScope.launch { bottomSheetState.hide() }

                    if (storagePermission.status.isGranted) {
                        //Open Gallery
                    } else if (storagePermission.status.shouldShowRationale) {
                        showPermissionRationale.value = showPermissionRationale.value.copy(
                            showDialog = true,
                            message = "InstagramClone Requires this Storage permission to access images in your phones Gallery.",
                            imageVector = Icons.Filled.Image,
                            permission = Constants.GALLERY
                        )
                    } else {
                        storagePermission.launchPermissionRequest()
                    }
                }
                Constants.CAMERA -> {
                    coroutineScope.launch { bottomSheetState.hide() }

                    if (cameraPermission.status.isGranted) {
                        //Open Camera
                    } else if (cameraPermission.status.shouldShowRationale) {
                        showPermissionRationale.value = showPermissionRationale.value.copy(
                            showDialog = true,
                            message = "InstagramClone Requires this Camera permission to access your phones Camera.",
                            imageVector = Icons.Filled.Camera,
                            permission = Constants.CAMERA
                        )
                    } else {
                        cameraPermission.launchPermissionRequest()
                    }
                }
            }
        }

        if (showPermissionRationale.value.showDialog) {
            PermissionRationaleDialog(
                message = showPermissionRationale.value.message,
                imageVector = showPermissionRationale.value.imageVector!!,
                onRequestPermission = {
                    showPermissionRationale.value = showPermissionRationale.value.copy(showDialog = false)
                    when(showPermissionRationale.value.permission) {
                        Constants.GALLERY -> storagePermission.launchPermissionRequest()
                        Constants.CAMERA -> cameraPermission.launchPermissionRequest()
                    }
                },
                onDismissRequest = { showPermissionRationale.value = showPermissionRationale.value.copy(showDialog = false) }
            )
        }
    }
}