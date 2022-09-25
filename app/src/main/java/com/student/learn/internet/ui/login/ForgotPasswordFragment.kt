package com.student.learn.internet.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.student.learn.internet.R
import kotlin.math.log

class ForgotPasswordFragment : Fragment() {

    private lateinit var viewModel: AuthViewModel

    companion object{
        private const val TAG = "ForgotPasswordFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        viewModel.init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.sendPasswordResetEmail).setOnClickListener {
            view.findViewById<EditText>(R.id.emailForPasswordReset).text.toString().let {
                if(it.trim() != ""){
                    if(viewModel.isEmailValid(it.trim()))
                        viewModel.sendPasswordResetLink(it.trim())
                    else
                        view.findViewById<EditText>(R.id.emailForPasswordReset).error = "Please Enter valid email."
                }
                else
                    view.findViewById<EditText>(R.id.emailForPasswordReset).error = "Please Enter email."
            }
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

    override fun onDetach() {
        super.onDetach()
        Log.i(TAG, "onDetach: ")
    }
}