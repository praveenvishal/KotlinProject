package com.example.kotlinproject.view.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FrmLoginBinding
import com.example.kotlinproject.databinding.FrmSignupBinding
import com.example.kotlinproject.view.base.BaseFragment
import org.koin.experimental.builder.getArguments
import java.util.*

class SignupFragment : BaseFragment() {

    private lateinit var mBinding: FrmSignupBinding
    companion object {
        val TAG = SignupFragment::class.java.simpleName

        fun getInstance(bundle: Bundle): SignupFragment {
            val fragment = SignupFragment()
            fragment.setArguments(bundle)
            return fragment
        }
    }
    override fun getLayout(): Int {
        return R.layout.frm_signup
    }

    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmSignupBinding
        init()
        clickListener()
    }

    private fun init() {

    }

    private fun clickListener() {
        mBinding.btnLogin.setOnClickListener (this)
        mBinding.btnSignup.setOnClickListener (this)
//        mBinding.txtForgotPsw.setOnClickListener { this }
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v?.id) {
            R.id.btn_login -> activity?.onBackPressed()
            R.id.btn_signup -> validate()
        }
    }

    private fun validate() {
    }

    /**
     * navigate on fragment
     *
     * @param tag represent navigation activity
     */
    private fun navigateScreen(tag: String) {
        var frm: Fragment? = null
        if (tag == LoginFragment.TAG)
            frm = LoginFragment.getInstance(getArguments()!!)
        navigateFragment(R.id.container, frm!!, true)
        //        navigateAddFragment(R.id.container, frm, true);
    }


}
