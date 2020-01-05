package com.webaddicted.kotlinproject.view.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmWidgetBinding
import com.webaddicted.kotlinproject.global.common.GlobalUtility
import com.webaddicted.kotlinproject.global.common.visible
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
        mBinding.btnDataPicker.setOnClickListener(this)
        mBinding.btnTimePicker.setOnClickListener(this)
        mBinding.imgOptionMenu.setOnClickListener(this)
        mBinding.btnStartProgress.setOnClickListener(this)
        mBinding.toolbar.imgBack.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.img_option_menu -> showPopupMenu(mBinding.imgOptionMenu)
            R.id.btn_data_picker ->
                activity?.let { GlobalUtility.getDate(it, mBinding.txtDateValue) }
            R.id.btn_time_picker -> activity?.let { GlobalUtility.timePicker(it, this).show() }
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

    private fun showPopupMenu(view: View) { // inflate menu
        val popup = PopupMenu(activity!!, view)
        val inflater: MenuInflater = popup.getMenuInflater()
        inflater.inflate(R.menu.menu_gridview, popup.getMenu())
        popup.setOnMenuItemClickListener(MyMenuItemClickListen())
        popup.show()
    }

    internal class MyMenuItemClickListen() :
        PopupMenu.OnMenuItemClickListener {
        override fun onMenuItemClick(menuItem: MenuItem): Boolean {
            when (menuItem.itemId) {
                R.id.viewImage -> GlobalUtility.showToast("View Image")
                R.id.delete -> GlobalUtility.showToast("Delete item")
                else -> GlobalUtility.showToast("Other menu click")
            }
            return false
        }

    }

}

