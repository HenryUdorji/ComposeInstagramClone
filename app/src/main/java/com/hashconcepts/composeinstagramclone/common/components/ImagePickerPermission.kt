package com.hashconcepts.composeinstagramclone.common.components

import android.Manifest
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.hashconcepts.composeinstagramclone.common.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @created 01/08/2022 - 2:33 AM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterialApi::class)
@Composable
fun ImagePickerPermission(
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
    onLaunchResult: (Uri?) -> Unit,
) {
    val storagePermission =
        rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
    val cameraPermission = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val showPermissionRationale = remember { mutableStateOf(ShowRationale()) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            run {
                onLaunchResult(uri)
            }
        }
    )

    ImagePickerBottomSheetDialog(bottomSheetState = bottomSheetState) { option ->
        when (option) {
            Constants.GALLERY -> {
                coroutineScope.launch { bottomSheetState.hide() }

                if (storagePermission.status.isGranted) {
                    launcher.launch("image/*")
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
                showPermissionRationale.value =
                    showPermissionRationale.value.copy(showDialog = false)
                when (showPermissionRationale.value.permission) {
                    Constants.GALLERY -> storagePermission.launchPermissionRequest()
                    Constants.CAMERA -> cameraPermission.launchPermissionRequest()
                }
            },
            onDismissRequest = {
                showPermissionRationale.value =
                    showPermissionRationale.value.copy(showDialog = false)
            }
        )
    }
}