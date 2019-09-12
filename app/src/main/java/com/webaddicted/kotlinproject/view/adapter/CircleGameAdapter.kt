package com.webaddicted.kotlinproject.view.adapter

import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.RowCircleBinding
import com.webaddicted.kotlinproject.global.common.GlobalUtility.Companion.showImageUsingGLIDE
import com.webaddicted.kotlinproject.model.circle.CircleGameBean
import com.webaddicted.kotlinproject.view.base.BaseAdapter

/**
 * Today's Top game list & animation handle in same adapter
 */
class CircleGameAdapter(internal var mFilterBean: ArrayList<CircleGameBean>?) : BaseAdapter() {

    private val slideAnmimations: Animation
    internal var slideAnmimation: Animation

    init {
        slideAnmimation = AnimationUtils.loadAnimation(mContext, R.anim.game_bounce)
        slideAnmimations = AnimationUtils.loadAnimation(mContext, R.anim.game_bounce)
    }

    override fun getListSize(): Int {
        var size = 0
        if (mFilterBean != null && mFilterBean!!.size > 0) {
            size = mFilterBean!!.size / 4
            if (reminder != 0) {
                size++
            }
        }
        return size
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.row_circle
    }

    override fun onBindTo(rowBinding: ViewDataBinding, position: Int) {
        if (rowBinding is RowCircleBinding) {
            val mBinding = rowBinding as RowCircleBinding
            mBinding.imgFirst.startAnimation(slideAnmimation)
            //            mBinding.imgSecond.startAnimation(slideAnmimation);
            mBinding.imgThird.startAnimation(slideAnmimation)
            //            mBinding.imgFourth.startAnimation(slideAnmimation);
            Handler().postDelayed({
                mBinding.imgSecond.startAnimation(slideAnmimations)
                mBinding.imgFourth.startAnimation(slideAnmimations)
            }, 1000)
            checkView(mBinding, position)
            clickEvent(mBinding, position)
            setImage(mBinding, position)
        }
    }

    /**
     * manage circle image view as per position
     */
    fun checkView(mBinding: RowCircleBinding, position: Int) {
        when (getCurrentReminder(position)) {
            0 -> {
                mBinding.imgFirst.setVisibility(View.VISIBLE)
                mBinding.txtFirst.setVisibility(View.VISIBLE)
                mBinding.imgSecond.setVisibility(View.VISIBLE)
                mBinding.txtSecond.setVisibility(View.VISIBLE)
                mBinding.imgThird.setVisibility(View.VISIBLE)
                mBinding.txtThird.setVisibility(View.VISIBLE)
                mBinding.imgFourth.setVisibility(View.VISIBLE)
                mBinding.txtFourth.setVisibility(View.VISIBLE)
            }
            1 -> {
                mBinding.imgFirst.setVisibility(View.VISIBLE)
                mBinding.txtFirst.setVisibility(View.VISIBLE)
                mBinding.imgSecond.setVisibility(View.GONE)
                mBinding.txtSecond.setVisibility(View.GONE)
                mBinding.imgThird.setVisibility(View.GONE)
                mBinding.txtThird.setVisibility(View.GONE)
                mBinding.imgFourth.setVisibility(View.GONE)
                mBinding.txtFourth.setVisibility(View.GONE)
            }
            2 -> {
                mBinding.imgFirst.setVisibility(View.VISIBLE)
                mBinding.txtFirst.setVisibility(View.VISIBLE)
                mBinding.imgSecond.setVisibility(View.VISIBLE)
                mBinding.txtSecond.setVisibility(View.VISIBLE)
                mBinding.imgThird.setVisibility(View.GONE)
                mBinding.txtThird.setVisibility(View.GONE)
                mBinding.imgFourth.setVisibility(View.GONE)
                mBinding.txtFourth.setVisibility(View.GONE)
            }
            3 -> {
                mBinding.imgFirst.setVisibility(View.VISIBLE)
                mBinding.txtFirst.setVisibility(View.VISIBLE)
                mBinding.imgSecond.setVisibility(View.VISIBLE)
                mBinding.txtSecond.setVisibility(View.VISIBLE)
                mBinding.imgThird.setVisibility(View.VISIBLE)
                mBinding.txtThird.setVisibility(View.VISIBLE)
                mBinding.imgFourth.setVisibility(View.GONE)
                mBinding.txtFourth.setVisibility(View.GONE)
            }
            4 -> {
                mBinding.imgFirst.setVisibility(View.VISIBLE)
                mBinding.txtFirst.setVisibility(View.VISIBLE)
                mBinding.imgSecond.setVisibility(View.VISIBLE)
                mBinding.txtSecond.setVisibility(View.VISIBLE)
                mBinding.imgThird.setVisibility(View.VISIBLE)
                mBinding.txtThird.setVisibility(View.VISIBLE)
                mBinding.imgFourth.setVisibility(View.VISIBLE)
                mBinding.txtFourth.setVisibility(View.VISIBLE)
            }
        }
    }

    /**
     * circle image row item click
     */
    private fun clickEvent(mBinding: RowCircleBinding, position: Int) {
        mBinding.imgFirst.setOnClickListener(clickImg(mBinding.imgFirst, position))
        mBinding.txtFirst.setOnClickListener(clickImg(mBinding.txtFirst, position))
        mBinding.imgSecond.setOnClickListener(clickImg(mBinding.imgSecond, position))
        mBinding.txtSecond.setOnClickListener(clickImg(mBinding.txtSecond, position))
        mBinding.imgThird.setOnClickListener(clickImg(mBinding.imgThird, position))
        mBinding.txtThird.setOnClickListener(clickImg(mBinding.txtThird, position))
        mBinding.imgFourth.setOnClickListener(clickImg(mBinding.imgFourth, position))
        mBinding.txtFourth.setOnClickListener(clickImg(mBinding.txtFourth, position))
    }

    private fun clickImg(imgFirst: View?, position: Int): View.OnClickListener? {
        val currentPosi = position + 1
        var categoriesBean: CircleGameBean
        return View.OnClickListener {
            when (imgFirst?.id) {
                R.id.img_first -> categoriesBean = getGameInfo(currentPosi, 0)
                R.id.img_second -> categoriesBean = getGameInfo(currentPosi, 1)
                R.id.img_third -> categoriesBean = getGameInfo(currentPosi, 2)
                R.id.img_fourth -> categoriesBean = getGameInfo(currentPosi, 3)

            }
        }
    }

    /**
     * get url & name of game from bean list
     *
     * @param currentPos of row position
     * @param remPos     is circle image of one row  image
     * @return current position game bean
     */
    fun getGameInfo(currentPos: Int, remPos: Int): CircleGameBean {
        val staggeredModel = CircleGameBean()
        staggeredModel.gameName = (mFilterBean!![currentPos * 4 - 4 + remPos].gameName)
        staggeredModel.gameImg = (mFilterBean!![currentPos * 4 - 4 + remPos].gameImg)
        //  staggeredModel.setCategoryImage(mStorageBaseUrl + mCategoriesDir + mFilterBean.get(((currentPos * 4) - 4) + remPos).getCategoryImage());
        staggeredModel.id = (mFilterBean!![currentPos * 4 - 4 + remPos].id)
        return staggeredModel
    }

    /**
     * set image & text parameter on current position view
     */
    fun setImage(mBinding: RowCircleBinding, position: Int) {
        val currentPos = position + 1
        when (getCurrentReminder(position)) {
            0 -> {
                mBinding.txtFirst.setText(getGameInfo(currentPos, 0).gameName)
                mBinding.txtSecond.setText(getGameInfo(currentPos, 1).gameName)
                mBinding.txtThird.setText(getGameInfo(currentPos, 2).gameName)
                mBinding.txtFourth.setText(getGameInfo(currentPos, 3).gameName)
                showImageUsingGLIDE(
                    mBinding.imgFirst,
                    getGameInfo(currentPos, 0).gameImg,
                    getPlaceHolder(0)
                );
                showImageUsingGLIDE(
                    mBinding.imgSecond,
                    getGameInfo(currentPos, 1).gameImg,
                    getPlaceHolder(0)
                );
                showImageUsingGLIDE(
                    mBinding.imgThird,
                    getGameInfo(currentPos, 2).gameImg,
                    getPlaceHolder(0)
                );
                showImageUsingGLIDE(
                    mBinding.imgFourth,
                    getGameInfo(currentPos, 3).gameImg,
                    getPlaceHolder(0)
                );
            }
            1 -> {
                mBinding.txtFirst.setText(getGameInfo(currentPos, 0).gameName)
                showImageUsingGLIDE(
                    mBinding.imgFirst,
                    getGameInfo(currentPos, 0).gameImg,
                    getPlaceHolder(0)
                );
            }
            2 -> {
                mBinding.txtFirst.setText(getGameInfo(currentPos, 0).gameName)
                mBinding.txtSecond.setText(getGameInfo(currentPos, 1).gameName)
                showImageUsingGLIDE(
                    mBinding.imgFirst,
                    getGameInfo(currentPos, 0).gameImg,
                    getPlaceHolder(0)
                );
                showImageUsingGLIDE(
                    mBinding.imgSecond,
                    getGameInfo(currentPos, 1).gameImg,
                    getPlaceHolder(0)
                );
            }
            3 -> {
                mBinding.txtFirst.setText(getGameInfo(currentPos, 0).gameName)
                mBinding.txtSecond.setText(getGameInfo(currentPos, 1).gameName)
                mBinding.txtThird.setText(getGameInfo(currentPos, 2).gameName)
                showImageUsingGLIDE(
                    mBinding.imgFirst,
                    getGameInfo(currentPos, 0).gameImg,
                    getPlaceHolder(0)
                );
                showImageUsingGLIDE(
                    mBinding.imgSecond,
                    getGameInfo(currentPos, 1).gameImg,
                    getPlaceHolder(0)
                );
                showImageUsingGLIDE(
                    mBinding.imgThird,
                    getGameInfo(currentPos, 2).gameImg,
                    getPlaceHolder(0)
                );
            }
        }
    }

    /**
     * get image item position
     *
     * @param position        row position
     * @param adapterPosition item position on one row
     * @return
     */
    fun getClickPosition(position: Int, adapterPosition: Int): Int {
        return position * 4 + adapterPosition
    }

    val reminder: Int
        get() = mFilterBean!!.size % 4

    /**
     * get current position of game
     *
     * @param position one row position
     * @return current position of game list
     */
    fun getCurrentReminder(position: Int): Int {
        val row = mFilterBean!!.size / 4
        return if (position == row || row == 0) {
            reminder
        } else
            0
    }

//
//    override fun getItemCount(): Int {
//        var size = 0
//            if (mFilterBean != null && mFilterBean!!.size > 0) {
//                size = mFilterBean!!.size / 4
//                if (reminder != 0) {
//                    size++
//                }
//            }
//            return size
//
//    }


//    val itemCount: Int
//        get() {
//            var size = 0
//            if (mFilterBean != null && mFilterBean!!.size > 0) {
//                size = mFilterBean!!.size / 4
//                if (reminder != 0) {
//                    size++
//                }
//            }
//            return size
//        }


//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): CircleGameAdapter.FilterViewHolder {
//        val frm = DataBindingUtil.inflate(
//            LayoutInflater.from(parent.context),
//            R.layout.row_circle,
//            parent, false
//        )
//        return CircleGameAdapter.FilterViewHolder(frm)
//    }

//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//    override fun onBindViewHolder(holder: CircleGameAdapter.FilterViewHolder, position: Int) {
//        holder.binding()
//    }


//    inner class FilterViewHolder(private val mBinding: RowCircleBinding) :
//        RecyclerView.ViewHolder(mBinding.getRoot()), View.OnClickListener {
//
//        fun binding() {
//
//        }
//
//
////        /**
////         * get url & name of game from bean list
////         *
////         * @param currentPos of row position
////         * @param remPos     is circle image of one row  image
////         * @return current position game bean
////         */
////        private fun getGameInfo(currentPos: Int, remPos: Int): CircleGameBean {
////            val staggeredModel = CircleGameBean()
////            staggeredModel.gameName = (mFilterBean!![currentPos * 4 - 4 + remPos].gameName)
////            staggeredModel.gameImg = (mFilterBean!![currentPos * 4 - 4 + remPos].gameImg)
////            //  staggeredModel.setCategoryImage(mStorageBaseUrl + mCategoriesDir + mFilterBean.get(((currentPos * 4) - 4) + remPos).getCategoryImage());
////            staggeredModel.id = (mFilterBean!![currentPos * 4 - 4 + remPos].id)
////            return staggeredModel
////        }
//
//        /**
//         * set image & text parameter on current position view
//         */
////        fun setImage() {
////            val loaderImage = mContext?.getResources()?.getStringArray(R.array.image_loader)
////            val currentPos = getAdapterPosition() + 1
////            when (getCurrentReminder(getAdapterPosition())) {
////                0 -> {
////                    mBinding.txtFirst.setText(getGameInfo(currentPos, 0).gameName)
////                    mBinding.txtSecond.setText(getGameInfo(currentPos, 1).gameName)
////                    mBinding.txtThird.setText(getGameInfo(currentPos, 2).gameName)
////                    mBinding.txtFourth.setText(getGameInfo(currentPos, 3).gameName)
////                }
////                1 -> mBinding.txtFirst.setText(getGameInfo(currentPos, 0).gameName)
////
////                2 -> {
////                    mBinding.txtFirst.setText(getGameInfo(currentPos, 0).gameName)
////                    mBinding.txtSecond.setText(getGameInfo(currentPos, 1).gameName)
////                }
////                3 -> {
////                    mBinding.txtFirst.setText(getGameInfo(currentPos, 0).gameName)
////                    mBinding.txtSecond.setText(getGameInfo(currentPos, 1).gameName)
////                    mBinding.txtThird.setText(getGameInfo(currentPos, 2).gameName)
////                }
////            }
////        }
//
//
////        override fun onClick(view: View) {
////            val currentPosi = getAdapterPosition() + 1
////            val categoriesBean: CircleGameBean
////            when (view.id) {
////                R.id.img_first -> categoriesBean = getGameInfo(currentPosi, 0)
////                R.id.img_second -> categoriesBean = getGameInfo(currentPosi, 1)
////                R.id.img_third -> categoriesBean = getGameInfo(currentPosi, 2)
////                R.id.img_fourth -> categoriesBean = getGameInfo(currentPosi, 3)
////            }
//            /* goToSubGame(categoriesBean.getCategoryId(),categoriesBean.getCategoryName(),
//                            categoriesBean.getCategoryImage(), categoriesBean.isUserCategoryGame());*//*goToSubGame(categoriesBean.getCategoryId(),categoriesBean.getCategoryName(),
//                            categoriesBean.getCategoryImage(), categoriesBean.isUserCategoryGame());*//* goToSubGame(categoriesBean.getCategoryId(),categoriesBean.getCategoryName(),
//                            categoriesBean.getCategoryImage(), categoriesBean.isUserCategoryGame());*//*  goToSubGame(categoriesBean.getCategoryId(),categoriesBean.getCategoryName(),
//                            categoriesBean.getCategoryImage(), categoriesBean.isUserCategoryGame());*/
//        }
//    }


}