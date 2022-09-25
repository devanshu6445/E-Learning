package com.student.learn.internet.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.student.learn.internet.R

class LoginFragment : Fragment() {

    private lateinit var viewModel: AuthViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        super.onViewCreated(view, savedInstanceState)
        val email = view.findViewById<EditText>(R.id.userEmail)
        val password = view.findViewById<EditText>(R.id.userPassword)
        view.findViewById<Button>(R.id.signIn).setOnClickListener {
            viewModel.login(
                email.text.toString(),
                password.text.toString()
            )
        }
        view.findViewById<TextView>(R.id.resetPassword).setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
        viewModel.code.observe(viewLifecycleOwner){
            when(it){
                200 -> {
                    Toast.makeText(this.requireContext(), viewModel.message, Toast.LENGTH_SHORT).show()}
                100 -> {
                    Toast.makeText(this.requireContext(), viewModel.message, Toast.LENGTH_SHORT).show()}
                101 -> {
                    Toast.makeText(this.requireContext(), viewModel.message, Toast.LENGTH_SHORT).show()}
            }
        }
    }
}