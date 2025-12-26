package com.example.wheel.util

import com.example.wheel.model.WheelModel
import kotlin.random.Random


fun calculateWinner(segments: List<WheelModel>): Int {
    val total = segments.sumOf { it.probability }.takeIf { it > 0 } ?: return 0
    val rand = Random.nextInt(total)
    var s = 0
    segments.forEachIndexed { i, seg -> s += seg.probability; if (rand < s) return i }
    return 0
}
