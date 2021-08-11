package com.brignerbranch.android.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

/*
Модель представления. Добавление функции привязки.
 */

class SoundViewModel : BaseObservable(){

    var sound: Sound? = null
    set(sound){
        field = sound
        notifyChange()
    }

    @get:Bindable
    val title: String?
        get() = sound?.name

}