package com.brignerbranch.android.beatbox

private const val WAV = ".wav"

/*
В конструкторе выполняется небольшая подготовительная работа для генерации удобочитаемого имени звука.
 */

class Sound(val assetPath: String) {

    val name = assetPath.split("/").last().removeSuffix(WAV)

}