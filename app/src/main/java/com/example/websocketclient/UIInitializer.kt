package com.example.websocketclient

import android.app.Activity
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

class UIInitializer(private val activity: Activity) {

    lateinit var connectButton: Button
    lateinit var sendButton: Button
    lateinit var messageInput: EditText
    lateinit var receivedMessages: EditText
    lateinit var username: EditText
    lateinit var usernameLayout: TextInputLayout

    fun initialize() {
        connectButton = activity.findViewById(R.id.connectButton)
        sendButton = activity.findViewById(R.id.sendButton)
        username = activity.findViewById(R.id.username)
        messageInput = activity.findViewById(R.id.messageInput)
        receivedMessages = activity.findViewById(R.id.receivedMessages)
        usernameLayout = activity.findViewById(R.id.usernameLayout)
    }
}
