package com.vedangj044.paradiigm_client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vedangj044.paradiigm_client.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var basicInfoViewModel: BasicInfoViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var scoreAdapter: ScoreClassAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        title = "Paradigm"
        setupViewModel()
        setupView()
    }

    private fun setupView() {

        basicInfoViewModel.sendObj.observe(this, Observer {
            value ->
            run {
                binding.basic = value
                binding.activeRecycler.adapter = ActiveClassAdapter(value.active)
                scoreAdapter = ScoreClassAdapter(value.history){
                    val hf = TestReviewFragment(it.classID)
                    supportFragmentManager.beginTransaction().replace(android.R.id.content, hf, "Test Review").addToBackStack("review").commit()
                }
                binding.scoreboardRecycler.adapter = scoreAdapter

            }
        })

        binding.profilePic.setOnClickListener {

            val pf = ProfileFragment()
            supportFragmentManager.beginTransaction().replace(android.R.id.content, pf, "PROFILE").addToBackStack("profile").commit()

        }


    }

    private fun setupViewModel() {

        basicInfoViewModel = ViewModelProvider(
            this,
            BasicInfoViewModelFactory(BasicInfoApiService.getApiService())
        )[BasicInfoViewModel::class.java]
    }
}