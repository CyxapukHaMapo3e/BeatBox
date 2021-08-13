package com.brignerbranch.android.beatbox

private const val WAV = ".wav"


/*
В конструкторе выполняется небольшая подготовительная работа для генерации удобочитаемого имени звука.
 */

class Sound(val assetPath: String, var soundId: Int? = null) {

    val name = assetPath.split("/").last().removeSuffix(WAV).substringAfter("_")

}