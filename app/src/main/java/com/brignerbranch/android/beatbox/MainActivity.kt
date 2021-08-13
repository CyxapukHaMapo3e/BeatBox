package com.brignerbranch.android.beatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brignerbranch.android.beatbox.databinding.ActivityMainBinding
import com.brignerbranch.android.beatbox.databinding.ListItemSoundBinding



class MainActivity : AppCompatActivity() {


    private lateinit var beatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beatBox = BeatBox(assets)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.recyclerView.apply{
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(beatBox.sounds)

        }

        val textView: TextView = findViewById(R.id.text_view)

        val seekSpeedControlBar: SeekBar = findViewById(R.id.seek_bar)
        seekSpeedControlBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                RATE_SPEED_SOUND = when(progress){
                    in 0..5 -> 0.1f
                    in 5..10 -> 0.2f
                    in 10..15 -> 0.3f
                    in 15..20 -> 0.4f
                    in 20..25 -> 0.5f
                    in 25..30 -> 0.6f
                    in 30..35 -> 0.7f
                    in 35..40 -> 0.8f
                    in 40..45 -> 0.9f
                    in 45..50 -> 1.0f
                    in 50..55 -> 1.1f
                    in 55..60-> 1.2f
                    in 60..65 -> 1.3f
                    in 65..70 -> 1.4f
                    in 70..75 -> 1.5f
                    in 75..80 -> 1.6f
                    in 80..85 -> 1.7f
                    in 85..90 -> 1.8f
                    in 90..95 -> 1.9f
                    in 95..100 -> 2.0f
                    else -> 1.0f
                }
                textView.setText("SPEED CONTOL: ${(RATE_SPEED_SOUND).toString()}")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

    }
/*
Вызов executePendingBindings() обычно не нужен. Однако в данном случае
данные привязки обновляются в виджете RecyclerView, который обовляет представлеия с
очень высокой скоростью. Вызывая эти функции, мы приказываем
макету обновить себя немедленно, вместо того чтобы ожидать одну-две миллисекунды.
Таким образом обеспечивается быстрота реакции RecyclerView синхронно с его RecyclerView.Adapter.
 */
    private inner class SoundHolder(private val binding: ListItemSoundBinding):
        RecyclerView.ViewHolder(binding.root){
            init {
                binding.viewModel = SoundViewModel(beatBox)
            }
        fun bind (sound: Sound){
            binding.apply{
                viewModel?.sound = sound
                executePendingBindings()
            }
        }
    }

    private inner class SoundAdapter(private val sounds: List<Sound>) : RecyclerView.Adapter<SoundHolder>(){

        override fun getItemCount() = sounds.size

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {

            val sound = sounds[position]
            holder.bind(sound)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                layoutInflater,
                R.layout.list_item_sound,
                parent,
                false
            )
            return SoundHolder(binding)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
    }
}