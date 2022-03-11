package com.example.digikala.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.digikala.R
import com.example.digikala.data.database.datastore.model.UserInfo
import com.example.digikala.util.DataStoreManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login), View.OnClickListener {

    lateinit var navController: NavController

    @Inject
    lateinit var dataStoreManager: DataStoreManager

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
        lifecycleScope.launch {
            editText_name.setText(dataStoreManager.getString()?.userName)
            editText_pass.setText(dataStoreManager.getString()?.password)
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.button -> navController.navigate(R.id.action_fragmentLogin_to_productFragment)

            R.id.checkBox_save -> {
                if (checkBox_save.isChecked) {
                    val name = editText_name.text.toString()
                    val pass = editText_pass.text.toString()
                    lifecycleScope.launch {
                        dataStoreManager.putString(UserInfo(name, pass))
                    }
                }
            }

        }
    }
}