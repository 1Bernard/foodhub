package com.example.foodhub.ui.features.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodhub.R
import com.example.foodhub.ui.theme.LinearDark
import com.example.foodhub.ui.theme.Orange

@Composable
fun AuthScreen() {
    val imageSize = remember {
        mutableStateOf(IntSize.Zero)
    }
    val brush = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            LinearDark,
        ),
        startY = imageSize.value.height.toFloat() / 2,
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.signin_bg),
            contentDescription = null,
            modifier = Modifier
                .onGloballyPositioned {
                    imageSize.value = it.size
                }
                .alpha(0.7f),
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(brush = brush)
        )

        Button(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            onClick = { /*TODO*/ },
        ) {
            Text(text = stringResource(id = R.string.skip), color = Orange)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 110.dp)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.welcome),
                color = Color.Black,
                modifier = Modifier,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold

            )

            Text(
                text = stringResource(id = R.string.app_name),
                color = Orange,
                modifier = Modifier,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(id = R.string.welcome_desc),
                color = Color.DarkGray,
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.sign_in_title),
                color = Color.White
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
               Button(
                   onClick = { /*TODO*/ },
                   colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                   shape = RoundedCornerShape(32.dp)
               ) {
                   Row(
                       modifier = Modifier.height(40.dp),
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       Image(
                           painter = painterResource(id = R.drawable.ic_google),
                           contentDescription = null,
                           modifier = Modifier.padding(end = 8.dp)
                       )
                       Text(text = stringResource(id = R.string.sign_with_google), color = Color.Black)
                   }

               }

               Button(
                   onClick = { /*TODO*/ },
                   colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                   shape = RoundedCornerShape(32.dp)
               ) {
                   Row(
                       modifier = Modifier.height(40.dp),
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       Image(
                           painter = painterResource(id = R.drawable.ic_facebook),
                           contentDescription = null,
                           modifier = Modifier.padding(end = 8.dp)
                       )
                       Text(text = stringResource(id = R.string.sign_with_facebook), color = Color.Black)
                   }

               }
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.2f)),
                shape = RoundedCornerShape(32.dp),
                border = BorderStroke(1.dp, Color.White),
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(vertical = 20.dp)
            ) {
                Text(text = stringResource(id = R.string.sign_with_email), color = Color.White)
            }
            TextButton(
                onClick = { /*TODO*/ },
            ) {
                Text(text = stringResource(id = R.string.already_have_account), color = Color.White)
            }
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen()
}