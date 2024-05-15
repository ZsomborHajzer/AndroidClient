package com.example.websocketclient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var uiInitializer: UIInitializer
    private lateinit var webSocketHandler: WebSocketHandler
    private lateinit var eventHandlers: EventHandlers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        uiInitializer = UIInitializer(this)
        uiInitializer.initialize()

        webSocketHandler = WebSocketHandler {
            uiInitializer.receivedMessages.append("\n$it")
        }

        eventHandlers = EventHandlers(uiInitializer, webSocketHandler)
        eventHandlers.setupEventHandlers()
    }
}



//package com.example.websocketclient
//
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import androidx.appcompat.app.AppCompatActivity
//import com.google.android.material.textfield.TextInputLayout
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import okhttp3.WebSocket
//import okhttp3.WebSocketListener
//import okhttp3.Response
//import okio.ByteString
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var webSocket: WebSocket
//    private lateinit var connectButton: Button
//    private lateinit var sendButton: Button
//    private lateinit var messageInput: EditText
//    private lateinit var receivedMessages: EditText
//    private lateinit var username: EditText
//    private lateinit var usernameLayout: TextInputLayout
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        connectButton = findViewById(R.id.connectButton)
//        sendButton = findViewById(R.id.sendButton)
//        username = findViewById(R.id.username)
//        messageInput = findViewById(R.id.messageInput)
//        receivedMessages = findViewById(R.id.receivedMessages)
//        usernameLayout = findViewById(R.id.usernameLayout)
//
//        connectButton.setOnClickListener {
//
//            if(username.text.isBlank()) {
//                usernameLayout.error = "This field is required!"
//            }
//
//            if(username.text.isNotBlank() && !::webSocket.isInitialized) {
//                startWebSocket()
//            }
//
//            if(::webSocket.isInitialized) {
//                webSocket.send( "{\"username\": \"${username.text}\"}" )
//            }
//        }
//
//        sendButton.setOnClickListener {
//            webSocket.send(messageInput.text.toString())
//        }
//    }
//
//    private fun startWebSocket() {
//        val client = OkHttpClient()
//        // Replace "your_local_ip" with your actual local IP, if running server on the same machine use 10.0.2.2 for emulator to access localhost
//        val request = Request.Builder().url("ws://10.0.2.2:8081").build()
//        webSocket = client.newWebSocket(request, object : WebSocketListener() {
//            override fun onOpen(webSocket: WebSocket, response: Response) {
//                runOnUiThread {
//                    receivedMessages.setText("Connected to the server")
//                }
//            }
//
//            override fun onMessage(webSocket: WebSocket, text: String) {
//                runOnUiThread {
//                    receivedMessages.append("\n$text")
//                }
//            }
//
//            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
//                webSocket.close(1000, null)
//                runOnUiThread {
//                    receivedMessages.append("\nConnection is closing")
//                }
//            }
//
//            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
//                runOnUiThread {
//                    receivedMessages.setText("Error : " + t.message)
//                }
//            }
//        })
//
//        client.dispatcher.executorService.shutdown()
//    }
//}