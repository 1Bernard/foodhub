package com.example.foodhub.ui.features.auth.signup

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.foodhub.R
import com.example.foodhub.ui.FoodHubTextField
import com.example.foodhub.ui.GroupSocialButtons
import com.example.foodhub.ui.theme.Orange
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignUpScreen(viewModel: SignUpViewModel = hiltViewModel()) {
    val text = stringResource(id = R.string.already_have_account)
    val splitText = text.split("Login")
    Box(modifier = Modifier.fillMaxSize()) {
        val name = viewModel.name.collectAsStateWithLifecycle()
        val email = viewModel.email.collectAsStateWithLifecycle()
        val password = viewModel.password.collectAsStateWithLifecycle()
        val errorMessage = remember { mutableStateOf<String?>(null) }
        val loading = remember { mutableStateOf(false) }

        val uiState = viewModel.uiState.collectAsState()
        when (uiState.value) {
            is SignUpViewModel.SignUpEvent.Loading -> {
                loading.value = true
                errorMessage.value = null
            }

            is SignUpViewModel.SignUpEvent.Error -> {
                loading.value = false
                errorMessage.value = "An error occurred"
            }
            else -> {
                loading.value = false
                errorMessage.value = null
            }
        }

        val context = LocalContext.current
        LaunchedEffect(true) {
            viewModel.navigationEvent.collectLatest { event ->
                when (event) {
                    is SignUpViewModel.SignUpNavigationEvent.NavigateToLogin -> {
                        Toast.makeText(context, "Sign up successful", Toast.LENGTH_SHORT).show()
                    }else -> {

                    }

                }                }
        }

        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.ic_auth_bg),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.sign_up),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(20.dp))
            FoodHubTextField(
                value = name.value,
                onValueChange = {viewModel.onNameChange(it)},
                label = {
                    Text(text = stringResource(id = R.string.full_name), color = Color.Gray)
                },
                modifier = Modifier.fillMaxWidth()
            )
            FoodHubTextField(
                value = email.value,
                onValueChange = {viewModel.onEmailChange(it)},
                label = {
                    Text(text = stringResource(id = R.string.email), color = Color.Gray)
                },
                modifier = Modifier.fillMaxWidth(),
            )
            FoodHubTextField(
                value = password.value,
                onValueChange = {viewModel.onPasswordChange(it)},
                label = {
                    Text(text = stringResource(id = R.string.password), color = Color.Gray)
                },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_eye),
                        contentDescription = null
                    )
                }
            )
            Spacer(modifier = Modifier.height(60.dp))
            Text(text = errorMessage.value ?: "", color = Color.Red)
            Button(
                onClick = viewModel::onSignUpClick,
                modifier = Modifier.height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Orange)
            ) {
                Box() {
                    AnimatedContent(
                        targetState = loading.value,
                        transitionSpec = {
                            fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 0.8f) togetherWith fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = 0.8f)
                        }
                    ) { target ->
                        if (target) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier
                                    .padding(horizontal = 50.dp)
                                    .size(24.dp)
                            )
                        } else {
                            Text(
                                modifier = Modifier.padding(horizontal = 50.dp),
                                text = stringResource(id = R.string.sign_up),
                                color = Color.White
                            )
                        }
                    }

                }

            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = buildAnnotatedString {
                    append(splitText[0]) // "Already have an account? "
                    withStyle(style = SpanStyle(color = Orange)) { // Orange color
                        append("Login")
                    }
                },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .clickable {
                        // Handle click
                    },
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(16.dp))
            GroupSocialButtons(
                color = Color.DarkGray,
                onFacebookClick = {},
                onGoogleClick = {}
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}