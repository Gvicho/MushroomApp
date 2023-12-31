package com.example.recycler3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recycler3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    private lateinit var myAdaper: ListAdapterClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Log.d("adafa111","1")
        setUp()
    }

    private fun setUp(){

        myAdaper = ListAdapterClass()
        bindings()
    }

    private fun bindings(){
        binding.apply {

            addBtn1.setOnClickListener{
                val mushroom = Mushroom("Gapirvelda",IsAdible.ADIBLE,50,1)
                add(mushroom,0)
            }

            recyclerView1.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(this@MainActivity, 1)
                adapter = myAdaper
                updateList()
            }

        }
    }

    fun updateList(){
        val listToCast = mutableListOf<Mushroom>(
            Mushroom("HoneyMushroom",IsAdible.ADIBLE,0,1),
            Mushroom("NikasMushroom",IsAdible.DENGEROUS,1,1),
            Mushroom("Qama",IsAdible.MIGHT_HARM,2,1),
            Mushroom("Qama1",IsAdible.MIGHT_HARM,3,2),
            Mushroom("NikasMushroom",IsAdible.DENGEROUS,4,1),
            Mushroom("NiokasMushroom",IsAdible.DENGEROUS,5,1),
            Mushroom("Ni9kasMushroom",IsAdible.DENGEROUS,6,1),
        )
        myAdaper.submitList(listToCast)
    }

    fun add(mushroom:Mushroom,position: Int){
        myAdaper.add(mushroom,position)
    }
}