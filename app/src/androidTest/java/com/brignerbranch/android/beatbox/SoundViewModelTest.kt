package com.brignerbranch.android.beatbox

import org.hamcrest.CoreMatchers.`is`
import org.mockito.mock.*

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test


class SoundViewModelTest {

    private lateinit var sound: Sound
    private lateinit var subject: SoundViewModel
    private lateinit var beatBox: BeatBox

    @Before
    fun setUp() {
        sound = Sound("assetPath")
        subject = SoundViewModel()
        subject.sound = sound
    }

    @Test
    fun exposesSoundNameAsTitle(){
        assertThat(subject.title,`is`(sound.name))
    }

    @Test
    fun callsBeatBoxPlayOnButtonClicked(){
        subject.onButtonClicked()
    }
}