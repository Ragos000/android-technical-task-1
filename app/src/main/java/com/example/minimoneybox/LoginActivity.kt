package com.example.minimoneybox

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.example.minimoneybox.api.NetworkLayer
import com.example.minimoneybox.fragments.UserAccountsFragment
import java.util.regex.Pattern

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    lateinit var btn_sign_in : Button
    lateinit var til_email : TextInputLayout
    lateinit var et_email : EditText
    lateinit var til_password : TextInputLayout
    lateinit var et_password : EditText
    lateinit var til_name : TextInputLayout
    lateinit var et_name : EditText
    lateinit var pigAnimation : LottieAnimationView
    lateinit var userPlanFragment: UserAccountsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        NetworkLayer.setUp(applicationContext)
        setupViews()
    }

    override fun onStart() {
        super.onStart()
        setupAnimation()
    }

    private fun setupViews() {
        btn_sign_in = findViewById(R.id.btn_sign_in)
        til_email = findViewById(R.id.til_email)
        et_email = findViewById(R.id.et_email)
        til_password = findViewById(R.id.til_password)
        et_password = findViewById(R.id.et_password)
        til_name = findViewById(R.id.til_name)
        et_name = findViewById(R.id.et_name)
        pigAnimation = findViewById(R.id.animation)

        btn_sign_in.setOnClickListener {
            if (allFieldsValid()) {
                Toast.makeText(this, R.string.input_valid, Toast.LENGTH_LONG).show()
                login(et_email.text.toString(), et_password.text.toString())
            }
        }
    }

    private fun login(email: String, password: String){
        NetworkLayer.loginApiCall(email, password, ::getInvestorProducts)
    }

    private fun getInvestorProducts(){
        NetworkLayer.getInvestorProductsApiCall {
            userPlanFragment = UserAccountsFragment()
            addFragment(userPlanFragment)
        }
    }

    fun addFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
        fragmentTransaction.commit()
    }

    private fun allFieldsValid() : Boolean {
        var allValid = true
        til_email.error = ""
        til_password.error = ""
        til_name.error = ""

        if (!Pattern.matches(EMAIL_REGEX, et_email.text.toString())) {
            til_email.error = getString(R.string.email_address_error)
            allValid = false
        }

        if (!Pattern.matches(PASSWORD_REGEX, et_password.text.toString())) {
            til_password.error = getString(R.string.password_error)
            allValid = false
        }

        if (et_name.text.toString() == "") {
            return allValid
        }

        if (!Pattern.matches(NAME_REGEX, et_name.text.toString())) {
            til_name.error = getString(R.string.full_name_error)
            allValid = false
        }

        return allValid
    }

    private fun setupAnimation() {
        pigAnimation.setMinAndMaxFrame(firstAnim.first, firstAnim.second)
        pigAnimation.playAnimation()
        pigAnimation.addAnimatorUpdateListener { valueAnimator ->

            if (valueAnimator.animatedFraction.toInt() == 1){
                pigAnimation.setMinAndMaxFrame(secondAnim.first, secondAnim.second)
                pigAnimation.repeatCount = LottieDrawable.INFINITE
                pigAnimation.repeatMode = LottieDrawable.RESTART
                pigAnimation.playAnimation()
            }
         }
    }

    override fun onBackPressed() {
        super.onBackPressed()
//        getInvestorProducts()
        if (userPlanFragment != null)
        userPlanFragment.updateAdapter()

    }

    companion object {
        val EMAIL_REGEX = "[^@]+@[^.]+\\..+"
        val NAME_REGEX = "[a-zA-Z]{6,30}"
        val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[A-Z]).{10,50}$"
        val firstAnim = 0 to 109
        val secondAnim = 131 to 158
    }
}
