package com.example.calculadora

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(
                        name = "Calculadora",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}



@Composable
fun Btn(value: String, operation: () -> Unit) {
    Button(content = { Text(value) } , onClick = { operation() })
}

@Composable
fun Menu(modifier: Modifier = Modifier) {

    Column  (modifier = Modifier.padding(14.dp,14.dp)) {
        var operationText by remember { mutableStateOf("") }
        var operation by remember { mutableStateOf("") }
        var acc by remember { mutableStateOf(0) }
        Text(
            text = "Mi calculadora",
            modifier = modifier
        )
        Text(
            "Operacion => $operationText",
            modifier = modifier
        )
        Column() {
                Row() {
                    Btn("*",  {
                        if (acc == 0) {
                            acc = operationText.toInt()
                            operationText = ""
                            operation = "mul"
                        } else {
                            acc *= operationText.toInt()
                            operationText = acc.toString()
                            acc = 0
                        }
                    })
                    Btn("/",  {
                        if (acc == 0) {
                            acc = operationText.toInt()
                            operationText = ""
                            operation = "div"
                        } else {
                            acc /= operationText.toInt()
                            operationText = acc.toString()
                            acc = 0
                        }
                    })
                    Btn("CE",  {
                        operationText = ""
                        acc = 0
                    })
                    Btn("<=", {
                        operationText = operationText.dropLast(1)
                    })
                }
                Row() {
                    Btn("1",  {
                        operationText += "1"
                    })
                    Btn("2",  {
                        operationText += "2"
                    })
                    Btn("3",  {
                        operationText += "3"
                    })
                    Btn("+",  {
                        if (acc == 0) {
                            acc = operationText.toInt()
                            operationText = ""
                            operation = "add"
                        } else {
                            acc += operationText.toInt()
                            operationText = acc.toString()
                            acc = 0
                        }
                    })


                }
                Row() {
                    Btn("4",  {
                        operationText += "4"
                    })
                    Btn("5",  {
                        operationText += "5"
                    })
                    Btn("6",  {
                        operationText += "7"
                    })
                    Btn("-",  {
                        if (acc == 0) {
                            acc = operationText.toInt()
                            operationText = ""
                            operation = "sub"
                        } else {
                            acc -= operationText.toInt()
                            operationText = acc.toString()
                            acc = 0
                        }
                    })

                }
                Row() {
                    Btn("7",  {
                        operationText += "7"
                    })
                    Btn("8",  {
                        operationText += "8"
                    })
                    Btn("9",  {
                        operationText += "9"
                    })
                    Btn("=",  {

                        if (operation == "") {}

                        if (operation == "add") {
                            operationText = (acc + operationText.toInt()).toString()
                        }

                        if (operation == "sub") {
                            operationText = (acc - operationText.toInt()).toString()
                        }

                        if (operation == "mul") {
                            operationText = (acc * operationText.toInt()).toString()
                        }

                        if (operation == "div") {
                            operationText = (acc / operationText.toInt()).toString()
                        }

                    })
                }
                Btn("0",  {
                    operationText += "0"
                })
        }
    }
}

@Composable
fun App(name: String, modifier: Modifier = Modifier) {

    Menu(modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculadoraTheme {
        App("Android")
    }
}