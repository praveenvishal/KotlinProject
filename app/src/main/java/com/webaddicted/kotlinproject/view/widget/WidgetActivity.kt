package com.webaddicted.kotlinproject.view.widget

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.view.base.BaseActivity
import com.webaddicted.kotlinproject.databinding.FrmWidgetBinding
import com.webaddicted.kotlinproject.global.common.GlobalUtility
import com.webaddicted.kotlinproject.global.constant.AppConstant
import com.webaddicted.kotlinproject.viewModel.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class WidgetActivity : BaseActivity(),View.OnClickListener, DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private lateinit var mBinding: FrmWidgetBinding
    private val mainViewModel: MainViewModel by viewModel()
//    private val preferenceMgr: PreferenceMgr  by inject()

    override fun getLayout(): Int {
        return R.layout.frm_widget
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as FrmWidgetBinding
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
                val stringBuilder = StringBuilder()
                GlobalUtility.getDate(this, mBinding.txtDateValue)
                val dateformate = mBinding.txtDateValue.text.toString()
                stringBuilder.append(dateformate + "\n")
                stringBuilder.append(
                    GlobalUtility.dateFormate(
                        dateformate,
                        AppConstant.DATE_FORMAT_D_M_Y,
                        AppConstant.DATE_FORMAT_Y_M_D
                    ) + "\n"
                )
                stringBuilder.append(
                    GlobalUtility.dateFormate(
                        dateformate,
                        AppConstant.DATE_FORMAT_D_M_Y,
                        AppConstant.DATE_FORMAT_D_M_Y_H
                    ) + "\n"
                )
                stringBuilder.append(
                    GlobalUtility.dateFormate(
                        dateformate,
                        AppConstant.DATE_FORMAT_D_M_Y,
                        AppConstant.DATE_FORMAT_SRC
                    ) + "\n"
                )
                mBinding.txtDateValue.text = stringBuilder.toString()

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
