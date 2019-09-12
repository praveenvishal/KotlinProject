package com.webaddicted.kotlinproject.view.CalendarActivity

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.ViewDataBinding
import com.applandeo.materialcalendarview.utils.DateUtils
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.ActivityCalendarBinding
import com.webaddicted.kotlinproject.databinding.ActivityCommonBinding
import com.webaddicted.kotlinproject.global.common.GlobalUtility
import com.webaddicted.kotlinproject.view.base.BaseActivity
import com.webaddicted.kotlinproject.view.login.LoginActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Deepak Sharma on 01/07/19.
 */
class CalendarActivity : BaseActivity() {

    private lateinit var mBinding: ActivityCalendarBinding

    companion object {
        val TAG: String = LoginActivity::class.java.simpleName
        fun newIntent(activity: Activity) {
            activity.startActivity(Intent(activity, CalendarActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_calendar
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivityCalendarBinding
        init()
        clickListener()
//        navigateScreen(TaskFrm.TAG)
    }

    private fun clickListener() {
        mBinding.toolbar.imgBack.setOnClickListener(this)
    }

    private fun init() {
        mBinding.toolbar.imgBack.visibility = View.VISIBLE
        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.calendar_title)
        mBinding.calendarView.setDisabledDays(getDisabledDays())
        mBinding.calendarView.setFilledDays(getFilledDays())
        mBinding.calendarView.setOnDayClickListener({ eventDay ->
            mBinding.txtClickData.setText(
                "\n selected date -> " + eventDay.getCalendar().getTime().toString()
                        + "\nis days Enable-> " + eventDay.isEnabled() +
                        "\nis days Filled  -> " + eventDay.isFilled()
            )
        })
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when (v?.id) {
            R.id.img_back -> finish()
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private fun getDisabledDays(): List<Calendar> {
        val firstDisabled = DateUtils.getCalendar()
        firstDisabled.add(Calendar.DAY_OF_MONTH, printDifference("12/09/2019"))

        val secondDisabled = DateUtils.getCalendar()
        secondDisabled.add(Calendar.DAY_OF_MONTH, printDifference("09/09/2019"))

        val thirdDisabled = DateUtils.getCalendar()
        thirdDisabled.add(Calendar.DAY_OF_MONTH, printDifference("14/09/2019"))

        val calendars = ArrayList<Calendar>()
        calendars.add(firstDisabled)
        calendars.add(secondDisabled)
        calendars.add(thirdDisabled)
        return calendars
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private fun getFilledDays(): List<Calendar> {
        val firstDisabled = DateUtils.getCalendar()
        firstDisabled.add(Calendar.DAY_OF_MONTH, printDifference("24/09/2019"))

        val secondDisabled = DateUtils.getCalendar()
        secondDisabled.add(Calendar.DAY_OF_MONTH, printDifference("25/09/2019"))

        val thirdDisabled = DateUtils.getCalendar()
        thirdDisabled.add(Calendar.DAY_OF_MONTH, printDifference("26/09/2019"))

        val calendars = ArrayList<Calendar>()
        calendars.add(firstDisabled)
        calendars.add(secondDisabled)
        calendars.add(thirdDisabled)
        return calendars
    }


    //    public void get
    //1 minute = 60 seconds
    //1 hour = 60 x 60 = 3600
    //1 day = 3600 x 24 = 86400
    fun printDifference(endDateStr: String): Int {
        //        DateTimeUtils obj = new DateTimeUtils();
        val simpleDateFormat = SimpleDateFormat("dd/M/yyyy")
        val currentDate = simpleDateFormat.format(Calendar.getInstance().time)
        var startDate: Date? = null
        var endDate: Date? = null
        try {
            startDate = simpleDateFormat.parse(currentDate)
            endDate = simpleDateFormat.parse(endDateStr)
            //            obj.printDifference(date1, date2);
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        //milliseconds
        var different = endDate!!.time - startDate!!.time

        println("startDate : $startDate")
        println("endDate : $endDate")
        println("different : $different")

        val secondsInMilli: Long = 1000
        val minutesInMilli = secondsInMilli * 60
        val hoursInMilli = minutesInMilli * 60
        val daysInMilli = hoursInMilli * 24

        val elapsedDays = different / daysInMilli
        different = different % daysInMilli

        val elapsedHours = different / hoursInMilli
        different = different % hoursInMilli

        val elapsedMinutes = different / minutesInMilli
        different = different % minutesInMilli

        val elapsedSeconds = different / secondsInMilli

        System.out.printf(
            "%d days, %d hours, %d minutes, %d seconds%n",
            elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds
        )
        return Integer.parseInt(elapsedDays.toString() + "")
    }
//    /**
//     * navigate on fragment
//     *
//     *
//     * @param tag represent navigation activity
//     */
//    private fun navigateScreen(tag: String) {
//        val frm: Fragment
//        if (tag == TaskFrm.TAG) {
//            frm = TaskFrm.getInstance(Bundle())
//            navigateFragment(R.id.container, frm, false)
//        }
//    }
}