package com.example.kotlinproject.view.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FrmLoginBinding
import com.example.kotlinproject.view.base.BaseFragment
import com.example.kotlinproject.view.home.HomeActivity

class LoginFragment : BaseFragment() {
    private lateinit var mBinding: FrmLoginBinding

    companion object {
        val TAG = LoginFragment::class.java.simpleName
        fun getInstance(bundle: Bundle): LoginFragment {
            val fragment = LoginFragment()
            fragment.setArguments(bundle)
            return LoginFragment()
        }
    }

    override fun getLayout(): Int {
        return R.layout.frm_login
    }

    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmLoginBinding
        init()
        clickListener()
    }

    private fun init() {
    }

    private fun clickListener() {
        mBinding.btnLogin.setOnClickListener (this)
        mBinding.btnSignup.setOnClickListener (this)
        mBinding.txtForgotPsw.setOnClickListener (this)
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v?.id) {
            R.id.btn_login -> validate()
            R.id.btn_signup -> navigateScreen(SignupFragment.TAG)
            R.id.txt_forgot_psw -> {
            }
        }
    }

    private fun validate() {
        startActivity(Intent(activity, HomeActivity::class.java))
    }

    /**
     * navigate on fragment
     *
     * @param tag represent navigation activity
     */
    private fun navigateScreen(tag: String) {
        var frm: Fragment? = null
        if (tag == SignupFragment.TAG)
            frm = SignupFragment.getInstance(Bundle())
//        navigateFragment(R.id.container, frm!!, true)
        navigateAddFragment(R.id.container, frm!!, true);
    }

}
