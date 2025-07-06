package com.igor.msgapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.igor.msgapp.ui.view.ChatScreen
import com.igor.msgapp.ui.view.RoomSelector
import com.igor.msgapp.viewmodel.MsgViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge() // Pode ser desativado para um layout mais simples
        setContent {
            MsgAppTheme {
                MsgAppRoot()
            }
        }
    }
}

@Composable
fun MsgAppTheme(content: @Composable () -> Unit) {
    // A paleta de cores "Crepúsculo Quente" continua a mesma
    val warmTwilightScheme = lightColorScheme(
        primary = Color(0xFFE65100), // Laranja vibrante e forte
        onPrimary = Color(0xFFFFFFFF),
        primaryContainer = Color(0xFFFFCCBC), // Pêssego claro para o container principal
        onPrimaryContainer = Color(0xFF3E1000),

        secondary = Color(0xFF6B5F54), // Cinza quente
        onSecondary = Color(0xFFFFFFFF),
        secondaryContainer = Color(0xFFF4E2D5), // Container secundário com tom de areia
        onSecondaryContainer = Color(0xFF241A13),

        tertiary = Color(0xFF5A537A), // Roxo escuro para contraste
        onTertiary = Color(0xFFFFFFFF),
        tertiaryContainer = Color(0xFFE0D9FF),
        onTertiaryContainer = Color(0xFF170D33),

        error = Color(0xFFBA1A1A),
        errorContainer = Color(0xFFFFDAD6),
        onError = Color(0xFFFFFFFF),
        onErrorContainer = Color(0xFF410002),

        background = Color(0xFFFFF8F6), // Fundo creme muito suave
        onBackground = Color(0xFF1E1B19),
        surface = Color(0xFFFFF8F6),
        onSurface = Color(0xFF1E1B19),
        surfaceVariant = Color(0xFFF4E2D5), // Variante da superfície com tom de areia
        onSurfaceVariant = Color(0xFF4D453E), // Texto sobre a variante
        outline = Color(0xFF80756D),
    )

    MaterialTheme(
        colorScheme = warmTwilightScheme,
        content = content
    )
}

@Composable
fun MsgAppRoot(vm: MsgViewModel = viewModel()) {
    val context = LocalContext.current

    // Login anônimo do Firebase (lógica original)
    val firebaseAuth = remember { FirebaseAuth.getInstance() }
    val user by produceState(initialValue = firebaseAuth.currentUser) {
        if (value == null) {
            firebaseAuth.signInAnonymously()
                .addOnCompleteListener { task -> value = firebaseAuth.currentUser }
        }
    }
    val userId = user?.uid ?: "joao" // Fallback para "joao" como no original
    var userName by remember { mutableStateOf("Usuário-${userId.takeLast(4)}") }
    var currentRoom by remember { mutableStateOf("geral") }
    var lastNotifiedId by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(currentRoom) {
        vm.switchRoom(currentRoom)
    }

    // Estrutura original com Column
    Column(modifier = Modifier.fillMaxSize()) {
        RoomSelector(onRoomSelected = { if (it.isNotBlank()) currentRoom = it })
        ChatScreen(
            username = userName,
            userId = userId,
            messages = vm.messages.collectAsState().value,
            onSend = { text -> vm.sendMessage(userId, userName, text) },
            currentRoom = currentRoom,
            lastNotifiedId = lastNotifiedId,
            onNotify = { msg ->
                // A função de notificação permanece a mesma
                // notifyNewMessage(context, msg)
                lastNotifiedId = msg.id
            }
        )
    }
}