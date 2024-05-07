package com.example.lemonadecompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadecompose.ui.theme.LemonadeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeCompose()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun LemonadeCompose() {
    // Definir el número total de estados de imágenes
    var state by remember{ mutableStateOf(1) }
    var clickCount by remember { mutableStateOf(2) }

    // Función para obtener el recurso de imagen según el estado actual
    fun getImageResource(state: Int): Int {
        return when (state) {
            1 -> R.drawable.lemon_tree
            2 -> R.drawable.lemon_squeeze
            3 -> R.drawable.lemon_drink
            4 -> R.drawable.lemon_restart
            else -> R.drawable.lemon_tree
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Lemonade")},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Yellow
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Image(
                painter = painterResource(getImageResource(state)),
                contentDescription = stringResource(R.string.lemon_tree_description),
                modifier = Modifier
                    .clip(RoundedCornerShape(25.dp))
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        when (state) {
                            1 -> {
                                clickCount = (2..4).random()
                                state++
                            }

                            2 -> {
                                if (clickCount == 0) {
                                    state++
                                } else {
                                    clickCount--
                                }
                            }

                            3 -> {
                                state++
                            }

                            4 -> {
                                state = 1
                            }
                        }
                        //state = (state % totalStates) + 1
                    }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Texto que describe la imagen actual
            Text(
                text = getImageDescription(state),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 18.sp

            )
        }
    }
}

@Composable
// Función para obtener la descripción de la imagen según el estado
fun getImageDescription(state: Int): String {
    return when (state) {
        1 -> stringResource(R.string.lemon_tree_description)
        2 -> stringResource(R.string.lemon_description)
        3 -> stringResource(R.string.glass_of_lemon_description)
        4 -> stringResource(R.string.empty_glass_description)
        else -> stringResource(R.string.lemon_tree_description)
    }
}