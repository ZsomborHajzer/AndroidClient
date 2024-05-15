package com.example.websocketclient

import android.os.Handler
import android.os.Looper
import okhttp3.*
import okio.ByteString

class WebSocketHandler(
    private val receivedMessages: (String) -> Unit
) {

    private var webSocket: WebSocket? = null

    fun startWebSocket(url: String, onOpen: () -> Unit, onClose: (String) -> Unit, onError: (String) -> Unit) {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                Handler(Looper.getMainLooper()).post {
                    onOpen()
                }
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Handler(Looper.getMainLooper()).post {
                    receivedMessages(text)
                }
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                webSocket.close(1000, null)
                Handler(Looper.getMainLooper()).post {
                    onClose(reason)
                }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Handler(Looper.getMainLooper()).post {
                    onError(t.message ?: "Unknown error")
                }
            }
        })
        client.dispatcher.executorService.shutdown()
    }

    fun sendMessage(message: String) {
            webSocket?.send(message)
    }

    fun isInitialized(): Boolean {
        return webSocket != null
    }
}