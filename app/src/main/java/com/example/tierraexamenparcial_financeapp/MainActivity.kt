package com.example.tierraexamenparcial_financeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tierraexamenparcial_financeapp.ui.theme.TierraExamenParcialFinanceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TierraExamenParcialFinanceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF5F5F5)
                ) {
                    PantallaInicio()
                }
            }
        }
    }
}


// CLASES DE DATOS

// usuario
data class Usuario(
    val nombre: String,
    val saldo: Double
)

//  tarjeta de resumen
data class TarjetaResumen(
    val titulo: String,
    val monto: Double,
    val color: Color
)

// Clase que representa una transacción
data class Transaccion(
    val nombreLugar: String,
    val categoria: String,
    val monto: Double,
    val hora: String,
    val icono: ImageVector
)
// DATOS DE PRUEBA

val miUsuario = Usuario(nombre = "Diego", saldo = 3200.00)

val misTarjetas = listOf(
    TarjetaResumen("Presupuesto\ndel Mes",  0.0,    Color(0xFFD6EAF8)),  // Azul claro
    TarjetaResumen("Egresos",              1573.79, Color(0xFFFDE8E8)),  // Rosa claro
    TarjetaResumen("Ahorros",              826.21,  Color(0xFFE8F8E8))   // Verde claro
)

val misTransacciones = listOf(
    Transaccion("Universidad",   "Colegiatura",      -220.00, "09:00 AM", Icons.Default.School),
    Transaccion("Farmacia",      "Medicamentos",      -48.50, "11:30 AM", Icons.Default.LocalHospital),
    Transaccion("Metro",         "Transporte",        -15.00, "08:15 AM", Icons.Default.Train),
    Transaccion("Nómina",        "Ingreso",          1600.00, "10:00 AM", Icons.Default.AccountBalance),
    Transaccion("Streaming",     "Entretenimiento",   -13.99, "06:00 PM", Icons.Default.PlayCircle),
    Transaccion("Mercado",       "Despensa",          -95.30, "02:00 PM", Icons.Default.ShoppingBag)
)

// PANTALLA PRINCIPAL
@Composable
fun PantallaInicio() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Header
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Encabezado(usuario = miUsuario)
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Tarjetas
        item {
            SeccionTarjetas(tarjetas = misTarjetas)
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Título de la lista
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Transacciones",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Ver todo",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Lista de transacciones
        items(misTransacciones) { transaccion ->
            FilaTransaccion(transaccion = transaccion)
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}


// ENCABEZADO
@Composable
fun Encabezado(usuario: Usuario) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Foto de perfil + saludo
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Círculo gris con ícono de persona
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0E0E0)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Foto",
                    tint = Color.Gray,
                    modifier = Modifier.size(30.dp)
                )
            }
            Column {
                Text(
                    text = "Hola ${usuario.nombre}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Bienvenida",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
        // Ícono de menú hamburguesa
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menú",
            modifier = Modifier.size(28.dp)
        )
    }
}

// TARJETAS RESUMEN
@Composable
fun SeccionTarjetas(tarjetas: List<TarjetaResumen>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TarjetaGrande(tarjeta = tarjetas[0])
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TarjetaPequena(tarjeta = tarjetas[1], modifier = Modifier.weight(1f))
            TarjetaPequena(tarjeta = tarjetas[2], modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun TarjetaGrande(tarjeta: TarjetaResumen) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .fillMaxHeight(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = tarjeta.color)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.BarChart,
                contentDescription = "Presupuesto",
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = tarjeta.titulo,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun TarjetaPequena(tarjeta: TarjetaResumen, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = tarjeta.color)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = tarjeta.titulo, fontSize = 13.sp, color = Color.Gray)
            Text(
                text = "$${tarjeta.monto}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// FILA DE TRANSACCIÓN

@Composable
fun FilaTransaccion(transaccion: Transaccion) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        // Row principal: izquierda = ícono+info, derecha = monto+hora
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Lado izquierdo: ícono de categoría + nombre + categoría
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Círculo negro con el ícono de la categoría
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = transaccion.icono,
                        contentDescription = transaccion.categoria,
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }
                // Nombre del lugar y categoría
                Column {
                    Text(
                        text = transaccion.nombreLugar,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = transaccion.categoria,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            // Lado derecho: monto y hora
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "$${transaccion.monto}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    // Rojo si negativo, verde si positivo
                    color = if (transaccion.monto < 0) Color.Red else Color(0xFF2E7D32)
                )
                Text(
                    text = transaccion.hora,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}


// PREVIEW
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VistaPrevia() {
    TierraExamenParcialFinanceAppTheme {
        PantallaInicio()
    }
}