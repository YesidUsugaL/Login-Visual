package com.example.loginandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginandroid.ui.theme.LoginAndroidTheme

// --- Definición del Estado de la Pantalla ---
sealed class AuthScreen {
    object Login : AuthScreen()
    object Register : AuthScreen()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AuthScreenContainer(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AuthScreenContainer(modifier: Modifier = Modifier) {
    var currentScreen by remember { mutableStateOf<AuthScreen>(AuthScreen.Login) }
    val navigateTo: (AuthScreen) -> Unit = { screen -> currentScreen = screen }

    Box(modifier = modifier.fillMaxSize()) {
        when (currentScreen) {
            is AuthScreen.Login -> LoginScreenVisual(navigateTo = navigateTo)
            is AuthScreen.Register -> RegisterScreenVisual(navigateTo = navigateTo)
        }
    }
}

// ---------------------------------------------------------------------
// 1. PANTALLA DE LOGIN (Campo de entrada para el Nombre de Usuario)
// ---------------------------------------------------------------------

@Composable
fun LoginScreenVisual(navigateTo: (AuthScreen) -> Unit) {
    var username by remember { mutableStateOf("") } // Ahora solo espera el Nombre de Usuario
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(32.dp))


        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "Login Icon",
            modifier = Modifier
                .padding(vertical = 16.dp)
                .size(80.dp),
            tint = MaterialTheme.colorScheme.primary)
        Text(
            text = "INICIAR SESIÓN",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 24.dp),
            color = MaterialTheme.colorScheme.onSurface)

        // Campo para Nombre de Usuario
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de Usuario") }, // Etiqueta ajustada
            leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Icono de Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de Contraseña con Botón de Visibilidad
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Icono de Contraseña") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Ocultar Contraseña" else "Mostrar Contraseña"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* Lógica de Login (Visual) */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)) {
            Text("ACCEDER", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "¿No tienes cuenta? Regístrate aquí.",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { navigateTo(AuthScreen.Register) }
        )

        Spacer(modifier = Modifier.height(32.dp))

        LazyAccessRow()
    }
}

// ---------------------------------------------------------------------
// 2. PANTALLA DE REGISTRO (Campo de Correo Electrónico ELIMINADO)
// ---------------------------------------------------------------------

@Composable
fun RegisterScreenVisual(navigateTo: (AuthScreen) -> Unit) {
    var username by remember { mutableStateOf("") } // Único campo de identificación
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(32.dp))


        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "Register Icon",
            modifier = Modifier
                .padding(vertical = 16.dp)
                .size(80.dp),
            tint = MaterialTheme.colorScheme.primary)
        Text(
            text = "REGISTRARSE",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp),
            color = MaterialTheme.colorScheme.onSurface)

        // Campo Nombre de Usuario
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de Usuario") },
            leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Icono de Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de Contraseña (Password) con Botón
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Icono de Contraseña") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = "Mostrar/Ocultar Contraseña")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de Contraseña (Confirmación) con Botón
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar Contraseña") },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Icono de Contraseña") },
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Icon(imageVector = image, contentDescription = "Mostrar/Ocultar Confirmar Contraseña")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* Lógica de Registro (Visual) */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)) {
            Text("CREAR CUENTA", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "¿Ya tienes cuenta? Inicia sesión aquí.",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { navigateTo(AuthScreen.Login) }
        )
    }
}

// ---------------------------------------------------------------------
// 3. LAZYROW
// ---------------------------------------------------------------------

@Composable
fun LazyAccessRow() {
    val items = listOf("Google", "Facebook", "Apple", "GitHub", "Email")

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Text(text = item, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

// ---------------------------------------------------------------------

@Preview(showBackground = true)
@Composable
fun AuthPreview() {
    LoginAndroidTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AuthScreenContainer(modifier = Modifier.padding(innerPadding))
        }
    }
}