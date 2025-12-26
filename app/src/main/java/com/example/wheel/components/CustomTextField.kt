package com.example.wheel.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    value: String,
    placeholder: String,
    modifier: Modifier,
    isNum: Boolean = false,
    onValueChange: (String) -> Unit,
) {
    Box(
        modifier = modifier
            .background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
            cursorBrush = SolidColor(Color(0xFF4D7CFF).copy(alpha = 0.5f)),
            keyboardOptions = KeyboardOptions(keyboardType = if (isNum) KeyboardType.Number else KeyboardType.Text),
            decorationBox = { innerTextField -> innerTextField() }
        )
        if (value.isEmpty()) {
            Text(placeholder, color = Color.Gray.copy(alpha = 0.7f), fontSize = 12.sp)
        }
    }
}
