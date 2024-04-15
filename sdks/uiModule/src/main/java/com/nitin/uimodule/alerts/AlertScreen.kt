package com.nitin.uimodule.alerts

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nitin.core.extensions.collectAsStateWithLifecycle
import com.nitin.uimodule.GlovoItem
import com.nitin.uimodule.GlovoLikeAnimation
import com.nitin.uimodule.R



@Composable
fun AlertScreen(viewModel: AlertViewModel = hiltViewModel())
{
   val ctx= LocalContext.current
    val defaultPath = PathParser().parsePathString(ctx.getString(R.string.default_path))
        .toPath()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        ManageAlertsUistate(viewModel,defaultPath)
        Button(
            onClick = {
                viewModel.acceptIntent(AlertDataIntent.GetAlertsData)
            },
        ) {
            Text(text = "Fetch Retrofit Data")
        }
    }

}

@Composable
fun ManageAlertsUistate(
    viewModel: AlertViewModel,
    defaultPath: Path
) {
    val uiState: AlertUiState by viewModel.uiState.collectAsStateWithLifecycle().apply {
        if (this.value.isLoading) {
            GlovoLikeAnimation(
                onGoalClick = { item ->
                    Log.d("Glovo Item", item.title)
                },
                mainItem = GlovoItem("Main", defaultPath),
                items = listOf(
                    GlovoItem("Secondary 1", defaultPath),
                    GlovoItem("Secondary 2", defaultPath),
                    GlovoItem("Secondary 3", defaultPath),
                    GlovoItem("Secondary 4", defaultPath),
                    GlovoItem("Secondary 5", defaultPath),
                )
            )
            ErrorView("Data is Loading Please Wait!!")
        } else if (this.value.isError) {
            ErrorView("Something Went Wrong!!")
        }else if (this.value.alertsDataResponse!=null)
        {
            ErrorView(this.value.alertsDataResponse.toString())
        }

    }



}
/**
 * Display Error State of Ui
 */
@Composable
fun ErrorView(msg: String) {
    Box(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = msg,
            color = Color.Blue,
            style= TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.sp),
            textAlign = TextAlign.Center
        )
    }
}

