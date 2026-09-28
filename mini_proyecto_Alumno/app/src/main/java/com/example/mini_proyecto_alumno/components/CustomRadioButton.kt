package com.example.practica4_controlesavanzados.components
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp


@Composable
fun CustomRadioButton(opcion1: String, opcion2: String, selectedOption: String, onClick1: (() -> Unit), onClick2: () -> Unit) {

    Row (verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            (selectedOption == opcion1),
            onClick = onClick1
        )
        Text(
            text = opcion1,
            modifier = Modifier.clickable { onClick1() }
        )

        Spacer(modifier = Modifier.width(16.dp))

        RadioButton(
            selected = (selectedOption == opcion2 ),
            onClick = onClick2
        )

        Text(opcion2, modifier = Modifier.clickable { onClick2() })
    }
}
