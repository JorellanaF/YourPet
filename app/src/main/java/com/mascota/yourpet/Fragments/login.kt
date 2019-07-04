package com.mascota.yourpet.Fragments


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mascota.yourpet.MainActivity
import com.mascota.yourpet.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class login : Fragment() {

    lateinit var btnL: Button
    lateinit var btnS: Button
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login, container, false)

        etEmail = view.findViewById(R.id.et_email)
        etPassword = view.findViewById(R.id.et_password)

        btnL = view.findViewById(R.id.btn_login)
        btnS = view.findViewById(R.id.btn_signup)

        mAuth = FirebaseAuth.getInstance()

        btnL.setOnClickListener { item ->
            if (TextUtils.isEmpty(etEmail.text.toString().trim())) {
                Toast.makeText(context, "Ingrese un email", Toast.LENGTH_LONG).show()
            }

            if (TextUtils.isEmpty(etPassword.text.toString().trim())) {
                Toast.makeText(context, "Ingrese una contraseña", Toast.LENGTH_LONG).show()
            } else {
                mAuth.signInWithEmailAndPassword(etEmail.text.toString().trim(), etPassword.text.toString().trim())
                    .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                        override fun onComplete(p0: Task<AuthResult>) {

                            if (p0.isSuccessful) {
                                Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(context, MainActivity::class.java))
                            } else {
                                Toast.makeText(context, "El email o la contrasela son incorrectas", Toast.LENGTH_SHORT).show()
                            }

                        }

                    })
            }

        }

        btnS.setOnClickListener { item ->
            if (TextUtils.isEmpty(etEmail.text.toString().trim())) {
                Toast.makeText(context, "Ingrese un email", Toast.LENGTH_LONG).show()
            }

            if (TextUtils.isEmpty(etPassword.text.toString().trim())) {
                Toast.makeText(context, "Ingrese una contraseña", Toast.LENGTH_LONG).show()
            } else {
                mAuth.createUserWithEmailAndPassword(etEmail.text.toString().trim(), etPassword.text.toString().trim())
                    .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                        override fun onComplete(p0: Task<AuthResult>) {
                            if (p0.isSuccessful) {
                                Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, p0.exception?.message, Toast.LENGTH_SHORT).show()
                            }
                        }

                    })
            }

        }

        return view
    }


}
