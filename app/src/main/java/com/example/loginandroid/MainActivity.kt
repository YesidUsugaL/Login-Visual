package com.example.loginandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.* // Importación para Scaffold, MaterialTheme, Icon, Button, etc.
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginandroid.ui.theme.LoginAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginAndroidTheme {
                // Scaffold de Material 3 para la estructura de la aplicación
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Contenido principal de la pantalla de Login
                    LoginScreenVisual(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// ---------------------------------------------------------------------

@Composable
fun LoginScreenVisual(modifier: Modifier = Modifier) {
    // Variables de estado (puramente visuales, sin lógica real)
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        // Logo/Icono (Usando Material 3 y colorScheme)
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "Login Icon",
            modifier = Modifier.padding(vertical = 16.dp).size(80.dp),
            // ✅ CORREGIDO: Usando colorScheme para acceder al color del tema M3
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Acceso de Usuario",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp),
            color = MaterialTheme.colorScheme.onSurface
        )

        // Campo de Texto para Usuario
        OutlinedTextField(
            value = username,
            onValueChange = { username = it }, // Actualiza el estado visual
            label = { Text("Usuario") },
            leadingIcon = {
                Icon(Icons.Filled.Person, contentDescription = "Icono de Usuario")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de Texto para Contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it }, // Actualiza el estado visual
            label = { Text("Contraseña") },
            leadingIcon = {
                Icon(Icons.Filled.Lock, contentDescription = "Icono de Contraseña")
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de Acceso (visual, sin onClick)
        Button(
            onClick = { /* Lógica de acceso (solo visual) */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Text("ENTRAR", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // LazyRow Solicitado
        Text(
            text = "Métodos Alternativos (LazyRow)",
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Start).padding(bottom = 8.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
        LazyAccessRow()
    }
}

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
                elevation = CardDefaults.cardElevation(4.dp), // Uso de CardDefaults de M3
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        // Usamos un color del ColorScheme de M3
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
fun LoginPreview() {
    LoginAndroidTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            LoginScreenVisual(modifier = Modifier.padding(innerPadding))
        }
    }
}