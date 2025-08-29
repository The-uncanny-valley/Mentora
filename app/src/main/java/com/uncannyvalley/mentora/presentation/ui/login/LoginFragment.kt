package com.uncannyvalley.mentora.presentation.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.uncannyvalley.mentora.R
import androidx.core.content.edit

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: MaterialButton
    private lateinit var btnLoginVk: ImageButton
    private lateinit var btnLoginOk: ImageButton


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate fragment_login.xml layout
        return inflater.inflate(
            R.layout.fragment_login,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        // Observe login form state
        viewModel.loginFormState.observe(viewLifecycleOwner) { isValid ->
            signInButton.isEnabled = isValid
        }

        // Add text change listeners
        emailEditText.addTextChangedListener { text ->
            viewModel.loginDataChanged(
                text.toString(),
                passwordEditText.text.toString()
            )
        }

        passwordEditText.addTextChangedListener { text ->
            viewModel.loginDataChanged(
                emailEditText.text.toString(),
                text.toString()
            )
        }

        btnLoginVk.setOnClickListener {
            val vkUrl = "https://vk.com/"
            val intent = Intent(Intent.ACTION_VIEW, vkUrl.toUri())
            startActivity(intent)
        }

        btnLoginOk.setOnClickListener {
            val okUrl = "https://ok.ru/"
            val intent = Intent(Intent.ACTION_VIEW, okUrl.toUri())
            startActivity(intent)
        }

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (viewModel.isEmailValid(email) && viewModel.isPasswordValid(password)) {
                val prefs = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                prefs.edit { putBoolean("is_logged_in", true) }

                findNavController().navigate(R.id.action_navigation_login_to_navigation_home)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Invalid username or password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun initViews(view: View) {
        emailEditText = view.findViewById(R.id.edit_text_email)
        passwordEditText = view.findViewById(R.id.edit_text_password)

        signInButton = view.findViewById(R.id.btn_login)

        btnLoginVk = view.findViewById(R.id.btn_login_vk)
        btnLoginOk = view.findViewById(R.id.btn_login_ok)
    }
}