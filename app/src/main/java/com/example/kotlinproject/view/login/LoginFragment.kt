package com.example.kotlinproject.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.androidkeyboardstatechecker.KeyboardEventListener
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FrmLoginBinding
import com.example.kotlinproject.global.common.GlobalUtility
import com.example.kotlinproject.global.common.ValidationHelper
import com.example.kotlinproject.global.db.entity.UserInfoEntity
import com.example.kotlinproject.view.base.BaseFragment
import com.example.kotlinproject.view.home.HomeActivity
import javax.xml.validation.ValidatorHandler

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
        mBinding.edtEmail.setText("deepak@gmail.com")
        mBinding.edtPassword.setText("Test@12345")

        1
    }

    private fun init() {
//        KeyboardEventListener(this) { isKeyboardOpen: Boolean, softkeybordHeight: Int ->
//            Log.v("Keyboard checker", "Keyboard is open = softkeybordHeight")
//            if (isKeyboardOpen) {
//
//                makeToast("Keyboard Open"+softkeybordHeight)
//            } else {
//                makeToast("Keyboard closed")
//            }
//        }
    }
    override fun onResume() {
        super.onResume()
        KeyboardEventListener(activity as AppCompatActivity) { isKeyboardOpen: Boolean, softkeybordHeight: Int ->
            if (isKeyboardOpen)
                mBinding.space.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, softkeybordHeight)
             else mBinding.space.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0)
        }
    }
    private fun clickListener() {
        mBinding.btnLogin.setOnClickListener(this)
        mBinding.btnSignup.setOnClickListener(this)
        mBinding.txtForgotPsw.setOnClickListener(this)
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

        if (ValidationHelper.validateEmail(mBinding.edtEmail, mBinding.wrapperEmail) &&
            ValidationHelper.validatePwd(mBinding.edtPassword, mBinding.wrapperPassword)) {
            var userInfo = getUserDao().getCouponsBySize(mBinding.edtEmail.text.toString())
            if (userInfo==null)GlobalUtility.showToast(resources.getString(R.string.create_an_account))
            else if (userInfo.password.equals(mBinding.edtPassword.text.toString()))
                activity?.let { HomeActivity.newIntent(it) }
            else GlobalUtility.showToast(resources.getString(R.string.please_enter_correct_password))
        }

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
