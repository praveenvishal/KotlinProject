package com.webaddicted.kotlinproject.view.login

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.webaddicted.androidkeyboardstatechecker.KeyboardEventListener
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmLoginBinding
import com.webaddicted.kotlinproject.databinding.FrmSignupBinding
import com.webaddicted.kotlinproject.global.common.ValidationHelper
import com.webaddicted.kotlinproject.global.db.entity.UserInfoEntity
import com.webaddicted.kotlinproject.view.base.BaseFragment

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
        mBinding.edtFullName.setText("deepak sharma")
        mBinding.edtNickName.setText("namesr")
        mBinding.edtMobileNo.setText("9950607002")
        mBinding.edtEmail.setText("deepak@gmail.com")
        mBinding.edtPassword.setText("Test@12345")

    }

    private fun init() {

    }

    private fun clickListener() {
        mBinding.btnLogin.setOnClickListener(this)
        mBinding.btnSignup.setOnClickListener(this)

//        mBinding.txtForgotPsw.setOnClickListener { this }
    }
    override fun onResume() {
        super.onResume()
        KeyboardEventListener(activity as AppCompatActivity) { isKeyboardOpen: Boolean, softkeybordHeight: Int ->
            if (isKeyboardOpen)
                mBinding.space.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, softkeybordHeight)
            else mBinding.space.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0)
        }
    }
    override fun onClick(v: View) {
        super.onClick(v)
        when (v?.id) {
            R.id.btn_login -> activity?.onBackPressed()
            R.id.btn_signup -> validate()
        }
    }

    private fun validate() {
        if (ValidationHelper.validateBlank(mBinding.edtFullName, mBinding.wrapperFullName, getString(R.string.first_name_can_not_blank)) &&
            ValidationHelper.validateName(mBinding.edtNickName, mBinding.wrapperNickName) &&
            ValidationHelper.validateMobileNo(mBinding.edtMobileNo, mBinding.wrapperMobileNo) &&
            ValidationHelper.validateEmail(mBinding.edtEmail, mBinding.wrapperEmail) &&
            ValidationHelper.validatePwd(mBinding.edtPassword, mBinding.wrapperPassword)) {
            val user = UserInfoEntity()
            user.name = mBinding.edtFullName.text.toString().trim()
            user.nickname = mBinding.edtNickName.text.toString().trim()
            user.mobileno = mBinding.edtMobileNo.text.toString().trim()
            user.email = mBinding.edtEmail.text.toString().trim()
            user.password = mBinding.edtPassword.text.toString().trim()
            getUserDao().insertUser(user)
            activity?.onBackPressed()
        }
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
