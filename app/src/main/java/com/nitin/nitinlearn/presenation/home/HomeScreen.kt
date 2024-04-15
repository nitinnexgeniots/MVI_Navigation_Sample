package com.nitin.nitinlearn.presenation.home

import android.media.MediaPlayer
import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.nitin.nitinlearn.R
import com.nitin.nitinlearn.presenation.navmenu.MainScreen
import com.nitin.nitinlearn.ui.theme.NitinLearnTheme
import kotlinx.coroutines.launch

var isGamefinished=false
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(viewModel: HomeViewModel = hiltViewModel()) {
    val tabBarItems= viewModel.fetchNavigationList().take(4)
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        NitinLearnTheme {
            Modifier.padding(it)
            MainScreen(tabBarItems,viewModel.navigationManager)
        }
    }
}



@Composable
fun PlayRollAndDiceGame(viewModel: HomeViewModel = hiltViewModel()) {
    var diceResult by remember { mutableIntStateOf(0) }
    var playerOneScore by remember { mutableStateOf(0) }
    var playerTwoScore by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    var count by remember { mutableIntStateOf(1) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var isRotating by remember { mutableStateOf(false) }
        DiceWithDots(diceResult,playerOneScore, playerTwoScore,isRotating)
        if(!isGamefinished) {
            Button(
                enabled = !isRotating,
                onClick = {
                    isRotating=true
                    coroutineScope.launch {
                        viewModel.rollDiceAfterDelay { newDiceResult ->
                            diceResult = newDiceResult
                            isRotating=false
                        }
                        if (viewModel.isOdd(count)) {
                            playerOneScore = viewModel.getPlayerScore(diceResult, playerOneScore)
                        } else {
                            playerTwoScore = viewModel.getPlayerScore(diceResult, playerTwoScore)
                        }
                        if(!isGamefinished)
                        {
                            count++
                        }
                    }
                },
            ) {
                val player = if (viewModel.isOdd(count)) "Player 1" else "Player 2"
                Text(text = "$player Roll The Dice")
            }
        }


    }
}

@Composable
fun DisplayScoreCard(playerOneScore: Int, playerTwoScore: Int) {
    Column(modifier = Modifier
        .padding(16.dp)
        .wrapContentHeight()) {
        if (playerOneScore >= 100 || playerTwoScore >= 100) {
            isGamefinished = true
            val winnerPlayerName = if (playerOneScore == 100) "Player 1" else "Player 2"
            Text(text = "Game Over  $winnerPlayerName win the game")
        } else {
            Text(text = "Player one score -> $playerOneScore")

            Text(text = "Player Two score -> $playerTwoScore")
        }
    }
}

@Composable
fun DiceWithDots(number: Int, playerOneScore: Int, playerTwoScore: Int,isRotating:Boolean) {
    DisplayScoreCard(playerOneScore, playerTwoScore)

    // Fetching the local context
    val mContext = LocalContext.current
    // Declaring and Initializing
    // the MediaPlayer to play "audio.mp3"
    val mMediaPlayer = MediaPlayer.create(mContext, com.nitin.common.R.raw.audio)
    if(isRotating) {
        mMediaPlayer.start()
        GifImage()
    }else {
        val instaColors = listOf(Color.Red, Color.Red, Color.Red)
        val dotsColors = listOf(Color.White, Color.White, Color.White)
        val custommodifier: Modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Red)
            .padding(8.dp)
            .size(80.dp)

        val position = remember { Animatable(0f) }
        LaunchedEffect(Unit) {
            position.animateTo(
                targetValue = 550f,
                animationSpec = tween(durationMillis = 3000)
            )
        }
        Canvas(
            modifier = custommodifier
        ) {
            drawRoundRect(
                brush = Brush.linearGradient(colors = instaColors),
                cornerRadius = CornerRadius(60f, 60f),
                style = Stroke(width = 15f, cap = StrokeCap.Round)
            )
            val dotPositions = getDotPositions(number)
            dotPositions.forEach { (x, y) ->
                drawCircle(
                    brush = Brush.linearGradient(colors = dotsColors),
                    radius = 15f,
                    center = Offset(this.size.width * x, this.size.height * y),
                )

            }
        }
        mMediaPlayer.stop()
    }
}

@Composable
fun GifImage(
    modifier: Modifier = Modifier,
) {
    Column(modifier = Modifier.size(200.dp)) {
        val context = LocalContext.current
        val imageLoader = ImageLoader.Builder(context)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(context).data(data = com.nitin.common.R.drawable.animated_dice)
                    .apply(block = {
                        size(250)
                    }).build(), imageLoader = imageLoader
            ),
            contentDescription = null,
            modifier = modifier.fillMaxWidth().padding(16.dp).height(200.dp),
        )
    }
}











