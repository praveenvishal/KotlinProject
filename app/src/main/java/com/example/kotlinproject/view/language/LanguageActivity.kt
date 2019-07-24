package com.example.kotlinproject.view.language

import android.content.Intent

import android.os.Handler
import android.util.Log
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityLanguageBinding
import com.example.kotlinproject.databinding.ActivitySplashBinding
import com.example.kotlinproject.global.common.GlobalUtility
import com.example.kotlinproject.global.constant.AppConstant
import com.example.kotlinproject.model.language.LanguageBean
import com.example.kotlinproject.view.adapter.LanguageAdapter
import com.example.kotlinproject.view.adapter.NewsAdapter
import com.example.kotlinproject.view.base.BaseActivity
import com.example.kotlinproject.view.home.HomeActivity
import com.example.kotlinproject.view.login.LoginActivity
import com.example.kotlinproject.view.onBoarding.OnBoardActivity
import com.example.kotlinproject.view.profile.ProfileFragment
import kotlinx.android.synthetic.main.row_list_item.*
import java.text.FieldPosition
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class LanguageActivity : BaseActivity() {
    val TAG: String = LanguageActivity::class.java.simpleName
    private lateinit var mBinding: ActivityLanguageBinding
    private lateinit var mLanguageList: ArrayList<LanguageBean>
    private lateinit var languageAdapter: LanguageAdapter

    override fun getLayout(): Int {
        return R.layout.activity_language
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivityLanguageBinding
        init()
        clickListener();
        languageObserver(0)
        setAdapter();

    }

    private fun init() {
        mBinding?.toolbar?.imgProfile?.visibility = View.GONE
        mBinding?.toolbar?.txtToolbarTitle?.text = resources.getString(R.string.select_language)
        mLanguageList = setLanguageBean();
    }

    private fun clickListener() {
        mBinding.btnNext.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when (v?.id) {
            R.id.btn_next -> {
                navigateToNext()
            }
        }
    }

    private fun setAdapter() {
        languageAdapter = LanguageAdapter(this, mLanguageList)
        mBinding?.rvLanguage.layoutManager = LinearLayoutManager(this)
        mBinding?.rvLanguage.adapter = languageAdapter
    }

    /**
     * navigate to welcome activity after Splash timer Delay
     */
    private fun navigateToNext() {
        var position = languageAdapter.selectedPos
        if (position < 0) {
            GlobalUtility.showToast(resources.getString(R.string.please_select_language))
            return
        } else GlobalUtility.changeLanguage(baseContext, mLanguageList.get(position).languageCode)
        preferenceMgr.setLanguage(mLanguageList.get(position))
        startActivity(Intent(this@LanguageActivity, OnBoardActivity::class.java))
        finish()
    }

    fun languageObserver(position: Int) {
        GlobalUtility.changeLanguage(baseContext, mLanguageList.get(position).languageCode)
        mBinding?.toolbar?.txtToolbarTitle?.text = resources.getString(R.string.select_language)
        mBinding?.btnNext?.text = resources.getString(R.string.submit)
    }

    private fun setLanguageBean(): ArrayList<LanguageBean> {
        var languageBeanList = ArrayList<LanguageBean>()
        languageBeanList.add(LanguageBean().apply {
            id = "0"
            languageCode = Locale.getDefault().language
            languageName = "default (" + Locale.getDefault().displayName.toLowerCase() + ")"
            languageFlag = ""
        })
        languageBeanList.add(LanguageBean().also {
            it.id = "1"
            it.languageCode = "ar"
            it.languageName = "argentina"
            it.languageFlag = "https://mirrorspectator.com/wp-content/uploads/2019/03/31WNPn82f2L._SX425_.jpg"
        })
        languageBeanList.add(LanguageBean().apply {
            id = "2"
            languageCode = "en"
            languageName = "english"
            languageFlag =
                "https://upload.wikimedia.org/wikipedia/en/thumb/a/aa/English_Language_Flag.png/640px-English_Language_Flag.png"
        })
        languageBeanList.add(LanguageBean().apply {
            id = "3"
            languageCode = "hi"
            languageName = "hindi"
            languageFlag = "https://www.imediaethics.org/wp-content/uploads/archive/B_Image_4450.jpg"
        })
        return languageBeanList
    }
}