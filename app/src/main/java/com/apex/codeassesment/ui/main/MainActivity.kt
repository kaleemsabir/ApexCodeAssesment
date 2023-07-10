package com.apex.codeassesment.ui.main

import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.apex.codeassesment.data.model.User
import com.apex.codeassesment.databinding.ActivityMainBinding
import com.apex.codeassesment.di.MainComponent
import com.apex.codeassesment.ui.main.viewmodel.MainViewModel
import com.apex.codeassesment.utils.BindingUtils
import com.apex.codeassesment.utils.navigateDetails


// TODO (5 points): Use combination of sealed/Dataclasses for exposing the data required by the view from viewModel .
// TODO (3 points): Add tests for viewmodel or presenter.
// TODO (3 points): Add tests
// TODO (Optional Bonus 10 points): Make a copy of this activity with different name and convert the current layout it is using in
//  Jetpack Compose.
class MainActivity : AppCompatActivity() {


    private val mainViewModel: MainViewModel by viewModels()

    lateinit var binding: ActivityMainBinding
    private var randomUser: User = User()
        set(value) {
            BindingUtils.loadImageFromUrl(binding.mainImage, mainViewModel.getUser().picture?.large)

            binding.mainName.text = value.name!!.first
            binding.mainEmail.text = value.email
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        sharedContext = this

        (applicationContext as MainComponent.Injector).mainComponent.inject(this)

        val arrayAdapter = ArrayAdapter<User>(this, android.R.layout.simple_list_item_1)


        binding.mainUserList.adapter = arrayAdapter
        binding.mainUserList.setOnItemClickListener { parent, _, position, _ ->
            navigateDetails(
                parent.getItemAtPosition(position) as User
            )
        }

        randomUser = mainViewModel.getSavedUsers()

        binding.mainSeeDetailsButton.setOnClickListener { navigateDetails(randomUser) }

        binding.mainRefreshButton.setOnClickListener { randomUser = mainViewModel.getUser() }

        binding.mainUserListButton.setOnClickListener {
            val users = mainViewModel.getUsersData()
            arrayAdapter.clear()
            arrayAdapter.addAll(users)
        }
    }


    companion object {
        var sharedContext: Context? = null
    }
}
