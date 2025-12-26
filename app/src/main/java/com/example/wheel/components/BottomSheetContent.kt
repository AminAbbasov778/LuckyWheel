package com.example.wheel.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wheel.model.WheelModel

@Composable
fun BottomSheetContent(
    segments: List<WheelModel>,
    onAdd: (WheelModel) -> Unit,
    onEdit: (List<WheelModel>) -> Unit,
    onRemove: (List<WheelModel>) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.9f)
            .background(Color(0xFF030714))
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF4D7CFF).copy(alpha = 0.05f), RoundedCornerShape(16.dp))
                .padding(12.dp)
        ) {
            WheelItemEditor(
                isNew = true,
                onAction = { n, p, u ->
                    onAdd(WheelModel(u, n, p, (segments.maxOfOrNull { it.id } ?: 0) + 1))
                },
                onEdit = { _, _, _, _ -> },
                onRemove = {}
            )
        }



        Spacer(modifier = Modifier.height(24.dp))
        Divider(color = Color(0xFF6EE7FF).copy(alpha = 0.2f), thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(segments.reversed(), key = { it.id }) { model ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1A1B23).copy(alpha = 0.6f), RoundedCornerShape(12.dp))
                ) {
                    WheelItemEditor(
                        model = model,
                        onEdit = { i, n, p, u ->
                            onEdit(segments.map {
                                if (it.id == model.id) WheelModel(
                                    u,
                                    n,
                                    p,
                                    i
                                ) else it
                            })
                        },
                        onRemove = { id -> onRemove(segments.filter { it.id != id }) },
                        onAction = { _, _, _ -> }
                    )
                }
            }
        }
    }
}
