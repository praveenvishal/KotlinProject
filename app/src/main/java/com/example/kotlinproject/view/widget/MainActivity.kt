package com.example.kotlinproject.view.widget

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.databinding.ViewDataBinding
import com.example.kotlinproject.R
import com.example.kotlinproject.base.BaseActivity
import com.example.kotlinproject.databinding.ActivityMainBinding
import com.example.kotlinproject.global.common.GlobalUtility
import com.example.kotlinproject.global.sharedPref.PreferenceMgr
import com.example.kotlinproject.viewModel.main.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(),View.OnClickListener, DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private var mBinding: ActivityMainBinding? = null
    private var mainViewModel: MainViewModel? = null
    private val preferenceMgr: PreferenceMgr  by inject()

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivityMainBinding
        for (i in 1..5) {
            Log.d("TAG", i.toString())
        }
        init()
        clickListener();
    }

    private fun init() {
//        preferenceMgr?.setUserInfo()
    }

    private fun clickListener() {
        mBinding?.btnLogin?.setOnClickListener(this)
        mBinding?.btnDataPicker?.setOnClickListener(this)
        mBinding?.btnTimePicker?.setOnClickListener(this)
        mBinding?.btnStartProgress?.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                GlobalUtility.showToast("login hit")
            }
            R.id.btn_data_picker -> {
                GlobalUtility.datePicker(this).show()
            }
            R.id.btn_time_picker -> GlobalUtility.timePicker(this).show()
            R.id.btn_start_progress -> {
                checkStoragePermission()
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//        mBinding?.txtDateValue
//        ?.text =dayOfMonth.toString()+":"+month+":"+year
        mBinding?.txtDateValue?.setText(dayOfMonth.toString() + "/" + month + "/" + year)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        mBinding?.txtTimeValue?.setText(hourOfDay.toString() + ":" + minute)
    }
}
