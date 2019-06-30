package com.example.kotlinproject.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinproject.R
import com.example.kotlinproject.base.BaseActivity
import com.example.kotlinproject.databinding.ActivityMainBinding
import com.example.kotlinproject.databinding.ActivitySplashBinding
import com.example.kotlinproject.global.common.GlobalUtility
import com.example.kotlinproject.viewModel.main.MainViewModel

class MainActivity : BaseActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private var mBinding: ActivityMainBinding? = null
    private var mainViewModel: MainViewModel? = null;

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivityMainBinding
        for (i in 1..5) {
            Log.d("TAG", i.toString())
        }
        clickListener();
    }

    private fun clickListener() {
        mBinding?.btnLogin?.setOnClickListener(this)
        mBinding?.btnDataPicker?.setOnClickListener(this)
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
