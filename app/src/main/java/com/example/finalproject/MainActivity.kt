package com.example.finalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.finalproject.Stocks.SymbolsRepository
import com.example.finalproject.databinding.ActivityMainBinding
import retrofit.SymbolRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        SymbolRepository()
        SymbolsRepository // Inicializa o repositório de símbolos
        replaceFragment(Ativos_filter())
        SymbolsRepository.getRepository().symbolList.clear()
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.ativos -> replaceFragment(Ativos_filter())
                R.id.noticias -> replaceFragment(Noticias())
            }
            true
        }
        SymbolsRepository.getRepository().symbolList.clear()
    }

    private fun replaceFragment(fragment: Fragment)
    {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}