package com.example.practica4_controlesavanzados

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practica4_controlesavanzados.components.CustomCheckBox
import com.example.practica4_controlesavanzados.components.CustomDatePicker
import com.example.practica4_controlesavanzados.components.CustomRadioButton
import com.example.practica4_controlesavanzados.components.CustomSpinner
import com.example.practica4_controlesavanzados.components.CustomSwitch

@Composable
fun FormScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Práctica 4: Componentes Avanzados",
            fontSize = 22.sp,
            style = MaterialTheme.typography.headlineMedium
        )

        HorizontalDivider()

        Text("1. Switch", style = MaterialTheme.typography.titleMedium)
        CustomSwitch()

        HorizontalDivider()

        Text("2. RadioButton", style = MaterialTheme.typography.titleMedium)
        CustomRadioButton()

        HorizontalDivider()

        Text("3. Checkbox", style = MaterialTheme.typography.titleMedium)
        CustomCheckBox()

        HorizontalDivider()

        Text("4. Spinner (DropdownMenu)", style = MaterialTheme.typography.titleMedium)
        CustomSpinner()

        HorizontalDivider()

        Text("5. DatePicker", style = MaterialTheme.typography.titleMedium)
        CustomDatePicker()

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                Toast.makeText(context, "Formulario completo", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Probar Formulario")
        }

        Spacer(modifier = Modifier.height(30.dp))


    }

}