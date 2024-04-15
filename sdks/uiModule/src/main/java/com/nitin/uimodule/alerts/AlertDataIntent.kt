package com.nitin.uimodule.alerts

import android.content.Context

sealed class AlertDataIntent {

    object  GetAlertsData : AlertDataIntent()

}