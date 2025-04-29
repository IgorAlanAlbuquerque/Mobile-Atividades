package com.igor.ativ1cadapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.igor.ativ1cadapp.ui.theme.Ativ1CadAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ativ1CadAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CadastroScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CadastroScreen(modifier: Modifier = Modifier){
    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var dataNascimento by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var bairro by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var nacionalidade by remember { mutableStateOf("") }
    var profissao by remember { mutableStateOf("") }

    // Função para limpar todos os campos
    fun limparCampos() {
        nome = ""
        sobrenome = ""
        dataNascimento = ""
        genero = ""
        telefone = ""
        celular = ""
        email = ""
        endereco = ""
        numero = ""
        bairro = ""
        cidade = ""
        estado = ""
        cep = ""
        nacionalidade = ""
        profissao = ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") })
        TextField(value = sobrenome, onValueChange = { sobrenome = it }, label = { Text("Sobrenome") })
        TextField(value = dataNascimento, onValueChange = { dataNascimento = it }, label = { Text("Data de Nascimento") })
        TextField(value = genero, onValueChange = { genero = it }, label = { Text("Gênero") })
        TextField(value = telefone, onValueChange = { telefone = it }, label = { Text("Telefone") })
        TextField(value = celular, onValueChange = { celular = it }, label = { Text("Celular") })
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        TextField(value = endereco, onValueChange = { endereco = it }, label = { Text("Endereço") })
        TextField(value = numero, onValueChange = { numero = it }, label = { Text("Número") })
        TextField(value = bairro, onValueChange = { bairro = it }, label = { Text("Bairro") })
        TextField(value = cidade, onValueChange = { cidade = it }, label = { Text("Cidade") })
        TextField(value = estado, onValueChange = { estado = it }, label = { Text("Estado") })
        TextField(value = cep, onValueChange = { cep = it }, label = { Text("CEP") })
        TextField(value = nacionalidade, onValueChange = { nacionalidade = it }, label = { Text("Nacionalidade") })
        TextField(value = profissao, onValueChange = { profissao = it }, label = { Text("Profissão") })

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { /* Enviar não faz nada neste exercício */ },
                modifier = Modifier.weight(1f)
            ) {
                Text("Enviar")
            }

            Button(
                onClick = { limparCampos() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Limpar")
            }
        }
    }

}