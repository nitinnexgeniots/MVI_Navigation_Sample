package com.nitin.network.webSockets



import androidx.annotation.NonNull
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import timber.log.Timber

const val CLOSE_CODE = 1000

class WebSocketListenerImpl constructor(private val socketListener: SocketListener?) : WebSocketListener() {

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        Timber.i("onClosed :: " + code.toString().plus(" ").plus(reason))
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        Timber.i("onClosing :: " +  code.toString().plus(" ").plus(reason))
        GlobalScope.launch {
            socketListener?.onComplete()
        }
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        if (response != null) {
            Timber.e("onFailure :: " +  response.message)
            if (response.networkResponse != null)
                Timber.e("onFailure :: " + response.networkResponse.toString())
            if (response.body != null)
                Timber.e("onFailure :: " + response.body.toString())
        }
        t.message?.let { Timber.e("onFailure :: $it") }
        t.message?.let {
            GlobalScope.launch {
                socketListener?.onError(it)
            }
        }

    }

    override fun onMessage(webSocket: WebSocket, message: String) {
        Timber.i("onMessage :: $message")
        GlobalScope.launch {
            socketListener?.onMessageReceived(webSocket, message)
        }

    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        Timber.i("onOpen :: " +  response.message)
        socketListener?.onOpen(webSocket)
    }


    interface SocketListener {
        fun onOpen(@NonNull webSocket: WebSocket)
        suspend fun onComplete()
        suspend fun onError(@NonNull error: String)
        suspend fun onMessageReceived(@NonNull webSocket: WebSocket, message: String)
    }
}