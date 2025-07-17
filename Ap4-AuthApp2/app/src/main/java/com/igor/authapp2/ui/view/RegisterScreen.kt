package com.igor.authapp2.ui.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import com.igor.authapp2.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(viewModel: AuthViewModel, navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    // MUDANÇA: Usando um Surface principal para o fundo da tela.
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // MUDANÇA: O Surface que cria o efeito de cartão para o formulário.
            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.9f), // Ocupa 90% da largura
                shape = RoundedCornerShape(16.dp),
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp), // Espaçamento interno mais agradável
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Criar Conta",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary // Cor consistente com a tela de login
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    CustomTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = "Nome",
                        icon = Icons.Filled.Person
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CustomTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = "Email",
                        icon = Icons.Filled.Email
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CustomTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = "Senha",
                        icon = Icons.Filled.Lock,
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            viewModel.register(email, password, name) { success ->
                                if (success) {
                                    // Manda o usuário de volta para o login com uma mensagem
                                    Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                                    navController.navigate("login")
                                } else {
                                    Toast.makeText(context, "Erro no cadastro. Verifique os dados.", Toast.LENGTH_LONG).show()
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp) // Cantos mais arredondados
                    ) {
                        Text("Registrar", fontSize = 18.sp)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    TextButton(onClick = { navController.navigate("login") }) {
                        Text("Já tem uma conta? Faça login")
                    }
                }
            }
        }
    }
}