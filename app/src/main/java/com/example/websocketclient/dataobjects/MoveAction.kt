package com.example.websocketclient.dataobjects


data class MoveAction(val event: String, val fromPosition: String, val toPosition: String, val timeStamp: Long)
