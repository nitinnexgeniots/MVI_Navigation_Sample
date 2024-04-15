package com.nitin.uimodule.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nitin.core.extensions.collectAsStateWithLifecycle
import com.nitin.uimodule.alerts.AlertDataIntent


@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel())
{
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        ManageAlertsUistate(viewModel)
        Button(
            onClick = {
                viewModel.acceptIntent(SettingsDataIntent.GetAlertsData)
            },
        ) {
            Text(text = "Fetch GraphQl Data")
        }
    }

}

@Composable
fun ManageAlertsUistate(
    viewModel: SettingsViewModel
) {
    val uiState: SettingsUiState by viewModel.uiState.collectAsStateWithLifecycle().apply {
        if (this.value.isLoading) {
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

