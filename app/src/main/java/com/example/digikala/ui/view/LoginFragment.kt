package com.example.digikala.ui.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.digikala.R
import com.example.digikala.data.model.datastore.UserInfo
import com.example.digikala.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * This class is fragment login for user login
 */
@AndroidEntryPoint
class LoginFragment
    : Fragment(R.layout.fragment_login), View.OnClickListener {

    lateinit var navController: NavController
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initViews()
    }

    private fun saveUserInfo() {
        viewModel.saveUserInfo(
            UserInfo(
                editTextName.text.toString(),
                editTextPass.text.toString()
            )
        )
    }

    private fun getUserInfo() {
        if (viewModel.getUserInfo() != null) {
            editTextName.setText(viewModel.getUserInfo()?.userName)
            editTextPass.setText(viewModel.getUserInfo()?.password)
        }
    }

    private fun initViews() {
        setOnClickViews()
        getUserInfo()
        saveUserInfo()
        checkBoxClick()
        editTextChangeListener()
        showPassVisibility()
    }

    private fun setOnClickViews() {
        buttonLogin.setOnClickListener(this)
        frameShowPass.setOnClickListener(this)
        editTextName.setOnClickListener(this)
    }

    private fun showPassVisibility() {
        if (editTextPass.text.toString() != "") {
            frameShowPass.visibility = View.VISIBLE
        } else {
            frameShowPass.visibility = View.GONE
        }
    }

    private fun editTextChangeListener() {
        editTextName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (viewModel.getUserInfo()?.userName != null &&
                    viewModel.getUserInfo()?.userName != s.toString()
                ) {
                    checkBoxSaveInfo.visibility = View.VISIBLE
                    checkBoxSaveInfo.text = getString(R.string.change_info)
                } else if (viewModel.getUserInfo()?.userName == null && s.isNotEmpty()) {
                    checkBoxSaveInfo.visibility = View.VISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })
        editTextPass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty())
                    frameShowPass.visibility = View.VISIBLE
                else
                    frameShowPass.visibility = View.GONE

                if (viewModel.getUserInfo()?.password != null &&
                    viewModel.getUserInfo()?.password != s.toString()
                ) {
                    checkBoxSaveInfo.visibility = View.VISIBLE
                    checkBoxSaveInfo.text = getString(R.string.change_info)
                } else if (viewModel.getUserInfo()?.password == null && s.isNotEmpty()) {
                    checkBoxSaveInfo.visibility = View.VISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.buttonLogin -> buttonClick()
            R.id.frameShowPass -> showPassIconClick()
        }
    }

    private fun buttonClick() {
        if (editTextName.text.toString().isEmpty() || editTextPass.text.toString().isEmpty()) {
            Toast.makeText(context, R.string.please_inter_name, Toast.LENGTH_SHORT).show()
        } else {
            if (editTextName.text.toString() != viewModel.getUserInfo()?.userName || editTextPass.text.toString() != viewModel.getUserInfo()?.password) {
                checkBoxSaveInfo.visibility = View.VISIBLE
                if (checkBoxSaveInfo.isChecked)
                    navController.navigate(R.id.action_fragmentLogin_to_productFragment)
                else
                    Toast.makeText(context, R.string.please_save_info, Toast.LENGTH_SHORT).show()
            } else {
                navController.navigate(R.id.action_fragmentLogin_to_productFragment)
            }
        }
    }

    private fun checkBoxClick() {
        checkBoxSaveInfo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                saveUserInfo()
            } else {
                Toast.makeText(context, R.string.please_save_info, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showPassIconClick() {
        if (editTextPass.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
            editTextPass.transformationMethod = PasswordTransformationMethod.getInstance()
            iconShowPass.setImageDrawable(context?.let {
                AppCompatResources.getDrawable(
                    it,
                    R.drawable.ic_show_pass
                )
            })
        } else {
            editTextPass.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
            iconShowPass.setImageDrawable(context?.let {
                AppCompatResources.getDrawable(
                    it,
                    R.drawable.ic_hide_pass
                )
            })
        }
    }
}
