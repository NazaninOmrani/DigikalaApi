package com.example.digikala.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.digikala.R
import com.example.digikala.data.model.datastore.UserInfo
import com.example.digikala.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login), View.OnClickListener {

    lateinit var navController: NavController
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        navController = Navigation.findNavController(view)
        button.setOnClickListener(this)
        checkBox_save.setOnClickListener(this)
        imageView2.setOnClickListener(this)
        editText_name.setOnClickListener(this)

        editText_name.setText(viewModel.getUserInfo()?.userName)
        editText_pass.setText(viewModel.getUserInfo()?.password)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.button -> navController.navigate(R.id.action_fragmentLogin_to_productFragment)

            R.id.checkBox_save -> {
                if (checkBox_save.isChecked) {
                    viewModel.saveUserInfo(
                        UserInfo(
                            editText_name.text.toString(),
                            editText_pass.text.toString()
                        )
                    )
                }
            }
        }
    }
}