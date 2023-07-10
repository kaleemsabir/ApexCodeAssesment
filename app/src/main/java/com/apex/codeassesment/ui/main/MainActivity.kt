package com.apex.codeassesment.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.apex.codeassesment.R
import com.apex.codeassesment.data.UserRepository
import com.apex.codeassesment.data.model.User
import com.apex.codeassesment.di.MainComponent
import com.apex.codeassesment.ui.details.DetailsActivity
import com.apex.codeassesment.ui.main.viewmodel.MainViewModel
import com.apex.codeassesment.utils.navigateDetails
import javax.inject.Inject

// TODO (5 points): Use combination of sealed/Dataclasses for exposing the data required by the view from viewModel .
// TODO (3 points): Add tests for viewmodel or presenter.
// TODO (1 point): Add content description to images
// TODO (3 points): Add tests
// TODO (Optional Bonus 10 points): Make a copy of this activity with different name and convert the current layout it is using in
//  Jetpack Compose.
class MainActivity : AppCompatActivity() {

  // TODO (2 points): Convert to view binding
  private var userImageView: ImageView? = null
  private var userNameTextView: TextView? = null
  private var userEmailTextView: TextView? = null
  private var seeDetailsButton: Button? = null
  private var refreshUserButton: Button? = null
  private var showUserListButton: Button? = null
  private var userListView: ListView? = null

  private val mainViewModel: MainViewModel by viewModels()

  private var randomUser: User = User()
    set(value) {
      // TODO (1 point): Use Glide to load images after getting the data from endpoints mentioned in RemoteDataSource
      // userImageView.setImageBitmap(refreshedUser.image)
      userNameTextView!!.text = value.name!!.first
      userEmailTextView!!.text = value.email
      field = value
    }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    sharedContext = this

    (applicationContext as MainComponent.Injector).mainComponent.inject(this)

    val arrayAdapter = ArrayAdapter<User>(this, android.R.layout.simple_list_item_1)

    userImageView = findViewById(R.id.main_image)
    userNameTextView = findViewById(R.id.main_name)
    userEmailTextView = findViewById(R.id.main_email)
    seeDetailsButton = findViewById(R.id.main_see_details_button)
    refreshUserButton = findViewById(R.id.main_refresh_button)
    showUserListButton = findViewById(R.id.main_user_list_button)
    userListView = findViewById(R.id.main_user_list)
    userListView!!.adapter = arrayAdapter
    userListView?.setOnItemClickListener { parent, _, position, _ -> navigateDetails(parent.getItemAtPosition(position) as User) }

    randomUser = mainViewModel.getSavedUsers()

    seeDetailsButton!!.setOnClickListener { navigateDetails(randomUser) }

    refreshUserButton!!.setOnClickListener { randomUser = mainViewModel.getUser() }

    showUserListButton!!.setOnClickListener {
      val users = mainViewModel.getUsersData()
      arrayAdapter.clear()
      arrayAdapter.addAll(users)
    }
  }




  companion object {
    var sharedContext: Context? = null
  }
}
