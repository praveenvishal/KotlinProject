package com.webaddicted.kotlinproject.view.fragment

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmSharedPrefBinding
import com.webaddicted.kotlinproject.global.common.visible
import com.webaddicted.kotlinproject.global.constant.PreferenceConstant.Companion.PREF_USER_AGE
import com.webaddicted.kotlinproject.global.constant.PreferenceConstant.Companion.PREF_USER_IS_MARRIED
import com.webaddicted.kotlinproject.global.constant.PreferenceConstant.Companion.PREF_USER_NAME
import com.webaddicted.kotlinproject.model.bean.preference.PreferenceBean
import com.webaddicted.kotlinproject.view.base.BaseFragment

class SharedPrefFrm : BaseFragment() {
    private lateinit var mBinding: FrmSharedPrefBinding

    companion object {
        val TAG = SharedPrefFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): SharedPrefFrm {
            val fragment = SharedPrefFrm()
            fragment.arguments = bundle
            return SharedPrefFrm()
        }
    }

    override fun getLayout(): Int {
        return R.layout.frm_shared_pref
    }

    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmSharedPrefBinding
        init()
        clickListener();
    }

    private fun init() {
        mBinding.toolbar.imgBack.visible()
        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.shared_pref_title)
    }


    private fun clickListener() {
        mBinding.toolbar.imgBack.setOnClickListener(this)
        mBinding.btnSetValue.setOnClickListener(this)
        mBinding.btnGetValueFromPreference.setOnClickListener(this)
        mBinding.btnRemoveKey.setOnClickListener(this)
        mBinding.btnClearPreference.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.img_back -> activity?.onBackPressed()
            R.id.btn_set_value -> setValuePref()
            R.id.btn_get_value_from_preference -> getValuePref()
            R.id.btn_remove_key -> removeKeyPref()
            R.id.btn_clear_preference -> clearPref()
        }
    }

    private fun setValuePref() {
        var prefUer = PreferenceBean().apply {
            name = "Deepak Sharma"
            gender = "M"
            age = 24
            weight = 75
            isMarried = false
        }
        preferenceMgr.setUserInfo(prefUer)
        mBinding.txtSavePreference.setText(getString(R.string.user_info_saved))
    }

    private fun getValuePref() {
        var userInfo = preferenceMgr.getUserInfo()
        var userInfoString = "<br><font color='#000000'>Name : </font>" + userInfo.name+"<br>" +
                "<font color='"+resources.getColor(R.color.black)+"'>Gender : </font>" + userInfo.gender + "<br>" +
                "<font color='"+resources.getColor(R.color.black)+"'>Age : </font>" + userInfo.age + "<br>" +
                "<font color='"+resources.getColor(R.color.black)+"'>Weight : </font>" + userInfo.weight + "<br>" +
                "<font color='"+resources.getColor(R.color.black)+"'>Married : </font>" + userInfo.isMarried + "<br>"
        mBinding.txtGetPreference.setText(Html.fromHtml(userInfoString))
    }

    private fun removeKeyPref() {
        preferenceMgr.removeKey(PREF_USER_NAME)
        preferenceMgr.removeKey(PREF_USER_AGE)
        preferenceMgr.removeKey(PREF_USER_IS_MARRIED)
        var userInfo = preferenceMgr.getUserInfo()
        var userInfoString = "<br><font color='#000000'>Name : </font>" + userInfo.name + "<br>" +
                "<font color='"+resources.getColor(R.color.black)+"'>Gender : </font>" + userInfo.gender + "<br>" +
                "<font color='"+resources.getColor(R.color.black)+"'>Age : </font>" + userInfo.age + "<br>" +
                "<font color='"+resources.getColor(R.color.black)+"'>Weight : </font>" + userInfo.weight + "<br>" +
                "<font color='"+resources.getColor(R.color.black)+"'>Married : </font>" + userInfo.isMarried + "<br>"
        mBinding.txtRemoveKey.setText(Html.fromHtml(userInfoString))
    }

    private fun clearPref() {
        preferenceMgr.clearPref()
        mBinding.txtClearPreference.setText(getString(R.string.pref_cleared_successfully))
    }

}

