package com.apex.codeassesment.ui.main

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.apex.codeassesment.data.model.User
import com.apex.codeassesment.databinding.ActivityMainBinding
import com.apex.codeassesment.di.MainComponent
import com.apex.codeassesment.ui.main.adapter.SimpleItemListAdapter
import com.apex.codeassesment.ui.main.viewmodel.MainViewModel
import com.apex.codeassesment.utils.BindingUtils
import com.apex.codeassesment.utils.navigateDetails
import javax.inject.Inject


// TODO (5 points): Use combination of sealed/Dataclasses for exposing the data required by the view from viewModel .
// TODO (3 points): Add tests for viewmodel or presenter.
// TODO (3 points): Add tests
// TODO (Optional Bonus 10 points): Make a copy of this activity with different name and convert the current layout it is using in
//  Jetpack Compose.
class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var  mainViewModel: MainViewModel

    lateinit var binding: ActivityMainBinding
    private var randomUser: User = User()
        set(value) {
            BindingUtils.loadImageFromUrl(binding.mainImage, randomUser.picture?.large)

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

       // randomUser = mainViewModel.getSavedUsers()

        binding.mainSeeDetailsButton.setOnClickListener { navigateDetails(randomUser) }

        binding.mainRefreshButton.setOnClickListener { mainViewModel.getUserData() }

        binding.mainUserListButton.setOnClickListener {
             mainViewModel.getUsersData()
        }

        mainViewModel.users.observe(this) {
            binding.mainUserList.adapter = it?.let { it1 ->
                SimpleItemListAdapter(it1) {
                    navigateDetails(it)
                }
            }
        }

        mainViewModel.user.observe(this) {
            if (it != null) {
                randomUser = it
            }
        }
    }


    companion object {
        var sharedContext: Context? = null
    }
}
