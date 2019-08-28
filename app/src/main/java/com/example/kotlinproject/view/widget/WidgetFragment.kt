package com.example.kotlinproject.view.widget

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.databinding.ViewDataBinding
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityProfileBinding
import com.example.kotlinproject.databinding.FrmWidgetBinding
import com.example.kotlinproject.global.common.GlobalUtility
import com.example.kotlinproject.global.constant.AppConstant
import com.example.kotlinproject.view.base.BaseFragment
import java.io.File

class WidgetFragment : BaseFragment(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private    var isCaptureImg: Boolean=false
    private lateinit var mBinding: FrmWidgetBinding
    companion object {
        val TAG = WidgetFragment::class.java.simpleName
        fun getInstance(bundle: Bundle): WidgetFragment {
            val fragment = WidgetFragment()
            fragment.setArguments(bundle)
            return WidgetFragment()
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
        mBinding?.toolbar?.imgBack?.visibility = View.VISIBLE
        mBinding?.toolbar?.txtToolbarTitle?.text = resources.getString(R.string.my_profile)
    }

    private fun clickListener() {
        mBinding?.btnLogin?.setOnClickListener(this)
        mBinding?.btnDataPicker?.setOnClickListener(this)
        mBinding?.btnTimePicker?.setOnClickListener(this)
        mBinding?.btnStartProgress?.setOnClickListener(this)
        mBinding?.toolbar?.imgBack?.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        super.onClick(v)
        when (v?.id) {
            R.id.btn_login -> {
                GlobalUtility.showToast("login hit")
            }
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
            R.id.btn_time_picker -> GlobalUtility.timePicker(this).show()
            R.id.btn_start_progress -> {
                checkStoragePermission()
            }

            R.id.img_back -> activity?.onBackPressed()
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

