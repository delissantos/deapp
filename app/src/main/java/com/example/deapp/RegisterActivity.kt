
package com.example.deapp

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RegisterPage()
        }
    }
}

@Composable
fun RegisterPage() {

    var nome by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }
    var repetirSenha by rememberSaveable { mutableStateOf("") }

    val activity = LocalActivity.current as Activity
    val auth = FirebaseAuth.getInstance()

    val campoModifier = Modifier.fillMaxWidth()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Criar conta",
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Digite seu nome") },
            modifier = campoModifier,
            singleLine = true
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Digite seu e-mail") },
            modifier = campoModifier,
            singleLine = true
        )

        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Digite sua senha") },
            modifier = campoModifier,
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        OutlinedTextField(
            value = repetirSenha,
            onValueChange = { repetirSenha = it },
            label = { Text("Repita sua senha") },
            modifier = campoModifier,
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

                if (senha.length < 6) {

                    Toast.makeText(
                        activity,
                        "A senha deve ter pelo menos 6 caracteres!",
                        Toast.LENGTH_LONG
                    ).show()

                } else {

                    auth.createUserWithEmailAndPassword(
                        email.trim(),
                        senha
                    ).addOnCompleteListener(activity) { task ->

                        if (task.isSuccessful) {

                            Toast.makeText(
                                activity,
                                "Registro OK!",
                                Toast.LENGTH_LONG
                            ).show()

                            // O WeatherApp detectará o login
                            // e abrirá a MainActivity.

                        } else {

                            val erro = task.exception

                            Log.e(
                                "FIREBASE_AUTH",
                                "Erro ao cadastrar usuário",
                                erro
                            )

                            Toast.makeText(
                                activity,
                                "Erro: ${erro?.localizedMessage}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            },
            enabled = nome.isNotBlank() &&
                    email.isNotBlank() &&
                    senha.isNotBlank() &&
                    repetirSenha.isNotBlank() &&
                    senha == repetirSenha
        ) {
            Text("Registrar")
        }

        Button(
            onClick = {
                nome = ""
                email = ""
                senha = ""
                repetirSenha = ""
            }
        ) {
            Text("Limpar")
        }
    }
}
