package com.example.wheel.util

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import kotlin.math.abs

class SoundManager(context: Context, private val soundResource: Int) {

    private var tickSoundId: Int = 0
    private var lastSoundAngle: Float = 0f

    private val soundPool: SoundPool = run {
        val attrs = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        SoundPool.Builder()
            .setMaxStreams(5)
            .setAudioAttributes(attrs)
            .build()
    }

    init {
        tickSoundId = soundPool.load(context, soundResource, 1)
    }

    fun playTickIfNeeded(currentRotation: Float, segmentCount: Int) {
        val anglePerSegment = 360f / segmentCount

        if (abs(currentRotation - lastSoundAngle) >= anglePerSegment) {
            soundPool.play(tickSoundId, 1f, 1f, 1, 0, 1f)
            lastSoundAngle = currentRotation
        }
    }


    fun release() {
        soundPool.release()
    }
}