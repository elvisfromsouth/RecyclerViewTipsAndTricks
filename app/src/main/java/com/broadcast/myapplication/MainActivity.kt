package com.broadcast.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.broadcast.myapplication.adapter.FingerprintAdapter
import com.broadcast.myapplication.adapter.fingerprints.PostFingerprint
import com.broadcast.myapplication.adapter.fingerprints.TitleFingerprint
import com.broadcast.myapplication.databinding.ActivityMainBinding
import com.broadcast.myapplication.utils.getRandomFeed

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FingerprintAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        adapter = FingerprintAdapter(getFingerprints())

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        adapter.setItems(getRandomFeed())
    }

    private fun getFingerprints() = listOf(
        TitleFingerprint(),
        PostFingerprint()
    )
}