package com.example.wheel.components

import android.R
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.wheel.model.WheelModel

@Composable
fun WheelItemEditor(
    model: WheelModel? = null,
    isNew: Boolean = false,
    onAction: (String, Int, Uri?) -> Unit,
    onEdit: (Int, String, Int, Uri?) -> Unit,
    onRemove: (Int) -> Unit,
) {
    var name by remember(model) { mutableStateOf(model?.name ?: "") }
    var prob by remember(model) { mutableStateOf(model?.probability?.toString() ?: "") }
    var uri by remember(model) { mutableStateOf(model?.img) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        uri = it
        if (!isNew) onEdit(model?.id ?: 0, name, prob.toIntOrNull() ?: 0, uri)
    }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(Color(0xFF030714), RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .clickable { launcher.launch("image/*") }
                .then(if (isNew) Modifier.background(Color(0xFF4D7CFF).copy(alpha = 0.1f)) else Modifier),
            contentAlignment = Alignment.Center
        ) {
            if (uri != null) {
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Icon(
                    painterResource(R.drawable.ic_menu_gallery),
                    contentDescription = null,
                    tint = Color(0xFF4D7CFF).copy(alpha = 0.5f),
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Column(modifier = Modifier.weight(1f)) {
            CustomTextField(
                name, "Item Name", Modifier
                    .fillMaxWidth()
                    .height(26.dp)
            ) {
                name = it
                if (!isNew) onEdit(model?.id ?: 0, it, prob.toIntOrNull() ?: 0, uri)
            }
            Spacer(modifier = Modifier.height(4.dp))
            CustomTextField(
                prob,
                "Weight %",
                Modifier
                    .padding(end = 50.dp)
                    .fillMaxWidth()
                    .height(26.dp),
                true
            ) {
                prob = it
                if (!isNew) onEdit(model?.id ?: 0, name, it.toIntOrNull() ?: 0, uri)
            }
        }

        IconButton(
            onClick = {
                if (isNew) {
                    if ((name.isNotEmpty() || uri != null) && prob.isNotEmpty()) {
                        onAction(name, prob.toIntOrNull() ?: 0, uri)
                        name = ""; prob = ""; uri = null
                    }
                } else {
                    onRemove(model?.id ?: 0)
                }
            },
            modifier = Modifier
                .size(40.dp)
                .background(
                    if (isNew) Color(0xFF4D7CFF).copy(alpha = 0.2f) else Color(0xFFFF4D4D).copy(
                        alpha = 0.1f
                    ),
                    RoundedCornerShape(12.dp)
                )
        ) {
            Icon(
                imageVector = if (isNew) Icons.Default.Check else Icons.Default.Delete,
                contentDescription = null,
                tint = if (isNew) Color(0xFF4D7CFF) else Color(0xFFFF4D4D),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
