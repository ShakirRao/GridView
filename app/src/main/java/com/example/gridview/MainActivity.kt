package com.example.gridview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gridview.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var adapter:Adapter? = null
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.pointer.value?.let { setAdapter(it) }
        setListener()
        setObserver()
    }

    private fun setAdapter(i: Int) {
        adapter = Adapter(createlist(),this,i)
        binding.gridview.adapter = adapter
        binding.gridview.setSelection(i-1)
    }

    private fun setListener(){
        binding.etRow.setOnEditorActionListener { v, actionId, event ->
            val input = binding.etRow.text.toString()
            if(!input.isBlank()){
                if(binding.etCol.text.isNotBlank()) {
                    viewModel.pointer.value = input.toInt() + binding.etCol.text.toString().toInt()
                    binding.etRow.text.clear()
                    binding.etCol.text.clear()
                    binding.etRow.requestFocus()
                } else{
                    binding.etCol.requestFocus()
                }
                true
            }
            false
        }
        binding.etCol.setOnEditorActionListener { v, actionId, event ->
            val input = binding.etCol.text.toString()
            if(!input.isBlank()){
                if(binding.etRow.text.isNotBlank()) {
                    viewModel.pointer.value = input.toInt() + binding.etRow.text.toString().toInt()
                    binding.etRow.text.clear()
                    binding.etCol.text.clear()
                    binding.etRow.requestFocus()
                } else{
                    binding.etRow.requestFocus()
                }
                true
            }
            false
        }
    }

    private fun setObserver(){
        viewModel.pointer.observe(this, Observer {
            setAdapter(it)
        })
    }

    private fun createlist():ArrayList<Int>{
        var arr = ArrayList<Int>()
        for(i in 1..1000){
            arr.add(i)
        }
        return arr
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putInt("index",index)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        index = savedInstanceState.getInt("index")
//        adapter = Adapter(createlist(), this, index)
//        binding.gridview.adapter = adapter
//        binding.gridview.setSelection(index-1)
//    }

}