package com.webaddicted.kotlinproject.view.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.MultiAutoCompleteTextView
import android.widget.TimePicker
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.ViewDataBinding
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmWidgetBinding
import com.webaddicted.kotlinproject.global.common.GlobalUtility
import com.webaddicted.kotlinproject.global.common.visible
import com.webaddicted.kotlinproject.view.base.BaseFragment

class WidgetFrm : BaseFragment(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private lateinit var mBinding: FrmWidgetBinding
    private var autoArray = arrayOf(
        "America",
        "Belgium",
        "Canada",
        "Denmark",
        "England",
        "France",
        "Germany",
        "Holland",
        "India",
        "Indonesia",
        "Italy",
        "Spain"
    )
    private var multiArray = arrayOf(
        "America",
        "Belgium",
        "Canada",
        "Denmark",
        "England",
        "France",
        "Germany",
        "Holland",
        "India",
        "Indonesia",
        "Italy",
        "Spain"
    )

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

    override fun initUI(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmWidgetBinding
        init()
        clickListener()
    }

    private fun init() {
        mBinding.toolbar.imgBack.visible()
        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.widget_title)
        mBinding.txtMarquee.isSelected = true
        setAutoCompleteAdapter()
        startAnimation(1)
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
        mBinding.txtDateValue.text = "$dayOfMonth/$month/$year"
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        mBinding.txtTimeValue.text = "$hourOfDay:$minute"
    }

    private fun showPopupMenu(view: View) { // inflate menu
        val popup = PopupMenu(activity!!, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu_gridview, popup.menu)
        popup.setOnMenuItemClickListener(MyMenuItemClickListen())
        popup.show()
    }

    internal class MyMenuItemClickListen:
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

    private fun setAutoCompleteAdapter() {
        val adapter =
            ArrayAdapter(
                activity!!,
                android.R.layout.simple_list_item_1,
                autoArray
            )

        mBinding.autoCompleteTextVie.setAdapter(adapter)
        mBinding.autoCompleteTextVie.threshold = 1
        mBinding.autoCompleteTextVie.setOnItemClickListener { adapterView, view, position, l ->
            val selection = adapterView.getItemAtPosition(position) as String
            GlobalUtility.showToast(selection)
        }
//        val seledd: String = mBinding.autoCompleteTextVie.getText().toString()
//        GlobalUtility.showToast(seledd)
        mBinding.multiAutoCompleteTextView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

        val adapt =
            ArrayAdapter(
                activity!!,
                android.R.layout.simple_list_item_1,
                multiArray
            )
        mBinding.multiAutoCompleteTextView.setAdapter(adapt)
        mBinding.multiAutoCompleteTextView.threshold = 1
    }

    private fun startAnimation(animatorType: Int) {
        var drawable: AnimatedVectorDrawableCompat? = null
        when (animatorType) {
            1 -> drawable =
                AnimatedVectorDrawableCompat.create(activity!!, R.drawable.animate_wave_1)
            2 -> drawable =
                AnimatedVectorDrawableCompat.create(activity!!, R.drawable.animate_wave_2)
            3 -> drawable =
                AnimatedVectorDrawableCompat.create(activity!!, R.drawable.animate_wave_3)
            4 -> drawable =
                AnimatedVectorDrawableCompat.create(activity!!, R.drawable.animate_wave_4)
            5 ->
                drawable =
                    AnimatedVectorDrawableCompat.create(activity!!, R.drawable.animate_wave_5)
        }
        mBinding.waveForm.background = drawable
        assert(drawable != null)
        drawable?.start()
    }
}

