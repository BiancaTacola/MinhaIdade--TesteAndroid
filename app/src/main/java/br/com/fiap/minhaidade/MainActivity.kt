package br.com.fiap.minhaidade


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.minhaidade.ui.theme.MinhaIdadeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinhaIdadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterScreen()
                }
            }
        }
    }
}

@Composable
fun CounterScreen() {

    //Aqui começa , como a função vai funcionar. Aqui criamos a variavel
    var idade = remember {
        mutableStateOf(0)
    }

    // Função para verificar se a idade está no intervalo desejado (0 a 130)
    fun isIdadeValida(novaIdade: Int): Boolean {
        return novaIdade in 0..130
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Qual a sua idade?",
            fontSize = 24.sp,
            color = Color(0xFFAD1F4E),
            fontWeight = FontWeight.Bold
        )

        Text(text = "Aperte os botões para informar a sua idade.",
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "${idade.value}",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row {
            Button(
                onClick = {
                    if (isIdadeValida(idade.value - 1)) {
                        idade.value--
                    }
                          },
                modifier = Modifier.size(84.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFAD1F4E))
            ) {
                Text(text = "-", fontSize = 40.sp)
            }
            Spacer(modifier = Modifier.width(32.dp))
            Button(
                onClick = {
                          if (isIdadeValida(idade.value + 1)) {
                              idade.value++
                          }
                },
                modifier = Modifier.size(84.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFAD1F4E))
                ) {
                Text(text = "+", fontSize = 40.sp)
            }
        }
        // Adiciona o texto informando se é maior ou menor de idade
        val mensagem = if (idade.value >= 18) {
            "Você é MAIOR de idade!"
        } else {
            "Você é MENOR de Idade!"
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = mensagem,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = if (idade.value >= 18) Color.Green else Color.Red)

    }
}

@Preview(showBackground = true)
@Composable
fun CounterScreenPreview() {
    MinhaIdadeTheme {
        CounterScreen()
    }
}
