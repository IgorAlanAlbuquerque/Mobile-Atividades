package com.igor.msgapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.igor.msgapp.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID


class MsgViewModel(application: Application): AndroidViewModel(application){
    private var currentRoom : String = "geral"
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    private var listner : ValueEventListener? = null

    private fun messagesRef(roomId: String) =
        FirebaseDatabase.getInstance().getReference("rooms").child(roomId)
            .child("messages")

    fun switchRoom(roomId: String){
        listner?.let {
            messagesRef(currentRoom).removeEventListener(it)
        }
        currentRoom = roomId

        listner = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val msgs = mutableListOf<Message>()
                for (child in snapshot.children){
                    val msg = child.getValue(Message::class.java)
                    if(msg != null) msgs.add(msg)
                }

                _messages.value = msgs.sortedBy { it.timestamp }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        messagesRef(currentRoom).orderByChild("timestamp").addValueEventListener(listner!!)
    }

    fun sendMessage(senderId: String, senderName: String, texto: String){
        val ref = messagesRef(currentRoom)

        val key = ref.push().key ?: UUID.randomUUID().toString()

        val msg = Message(
            id = key,
            senderId = senderId,
            senderName = senderName,
            texto = texto,
            timestamp = System.currentTimeMillis()
        )

        ref.child(key).setValue(msg)
    }
}