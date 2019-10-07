package com.webaddicted.kotlinproject.view.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.databinding.ViewDataBinding
import com.android.boxlty.global.common.visible
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmWidgetBinding
import com.webaddicted.kotlinproject.global.common.GlobalUtility
import com.webaddicted.kotlinproject.global.constant.AppConstant
import com.webaddicted.kotlinproject.view.base.BaseFragment

class WidgetFrm : BaseFragment(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private lateinit var mBinding: FrmWidgetBinding
    companion object {
        val TAG = WidgetFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): WidgetFrm {
            val fragment = WidgetFrm()
            fragment.arguments = bundle
            return WidgetFrm()
        }
    }
    override fun getLayout(): Int {
        return R.layout.frm_widget
    }
    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmWidgetBinding
        init()
        clickListener();
    }
    private fun init() {
        mBinding.toolbar.imgBack.visible()
        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.widget_title)
    }

    private fun clickListener() {
        mBinding.btnLogin.setOnClickListener(this)
        mBinding.btnDataPicker.setOnClickListener(this)
        mBinding.btnTimePicker.setOnClickListener(this)
        mBinding.btnStartProgress.setOnClickListener(this)
        mBinding.toolbar.imgBack.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.btn_login -> GlobalUtility.showToast("login hit")
            R.id.btn_data_picker -> {
                val stringBuilder = StringBuilder()
                activity?.let { GlobalUtility.getDate(it, mBinding.txtDateValue) }
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
            R.id.btn_time_picker -> activity?.let { GlobalUtility.timePicker(it,this).show() }
            R.id.btn_start_progress -> checkStoragePermission()
            R.id.img_back -> activity?.onBackPressed()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        mBinding.txtDateValue.text = dayOfMonth.toString() + "/" + month + "/" + year
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        mBinding.txtTimeValue.text = hourOfDay.toString() + ":" + minute
    }
}

