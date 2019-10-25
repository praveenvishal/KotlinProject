package com.webaddicted.kotlinproject.view.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.FrmShareBinding
import com.webaddicted.kotlinproject.global.annotationDef.MediaPickerType
import com.webaddicted.kotlinproject.global.common.FileUtils
import com.webaddicted.kotlinproject.global.common.MediaPickerUtils
import com.webaddicted.kotlinproject.global.common.visible
import com.webaddicted.kotlinproject.view.base.BaseFragment
import java.io.ByteArrayOutputStream
import java.io.File

class ShareDataFrm : BaseFragment() {
    private lateinit var mBinding: FrmShareBinding

    companion object {
        val TAG = ShareDataFrm::class.java.simpleName
        fun getInstance(bundle: Bundle): ShareDataFrm {
            val fragment = ShareDataFrm()
            fragment.arguments = bundle
            return ShareDataFrm()
        }
    }

    override fun getLayout(): Int {
        return R.layout.frm_share
    }

    override fun onViewsInitialized(binding: ViewDataBinding?, view: View) {
        mBinding = binding as FrmShareBinding
        init()
        clickListener();
    }

    private fun init() {
        mBinding.toolbar.imgBack.visible()
        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.share_title)

    }

    private fun clickListener() {
        mBinding.toolbar.imgBack.setOnClickListener(this)
        mBinding.btnShareText.setOnClickListener(this)
        mBinding.btnShareImage.setOnClickListener(this)
        mBinding.btnShareImgText.setOnClickListener(this)
        mBinding.btnShareLocalImage.setOnClickListener(this)
        mBinding.btnSendEmail.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.img_back -> activity?.onBackPressed()
            R.id.btn_share_text -> shareText()
            R.id.btn_share_image -> checkPermission(false)
            R.id.btn_share_img_text -> checkPermission(true)
            R.id.btn_share_local_image -> shareLocalImage()
            R.id.btn_send_email -> sendEmail()
        }
    }
    private fun shareText() {
        val shareBody = "Here is the share content body"
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(sharingIntent, resources.getString(R.string.share_text)))
    }

    private fun shareImage(file: File) {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/jpeg"
        share.putExtra(Intent.EXTRA_STREAM, Uri.parse(file.toString()))
        startActivity(Intent.createChooser(share, resources.getString(R.string.share_image)))
    }

    private fun checkPermission(isShareImgText: Boolean) {

        mediaPicker.selectMediaOption(activity!!,
            MediaPickerType.SELECT_IMAGE,
            FileUtils.subFolder(),
            object : MediaPickerUtils.ImagePickerListener {
                override fun imagePath(filePath: List<File>) {
                    if (isShareImgText) shareImageText(filePath[0])
                    shareImage(filePath[0])
                }
            })
    }



    private fun shareImageText(file: File) {
        val b = FileUtils.getBitmapFromFile(file)
        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/png"
        val bytes = ByteArrayOutputStream()
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            activity?.getContentResolver(),
            b, "Title", null
        )
        val imageUri = Uri.parse(path)
        share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
        share.putExtra(Intent.EXTRA_STREAM, imageUri)
        share.putExtra(
            Intent.EXTRA_TEXT,
            getString(R.string.dummyText) + "\nhttps://play.google.com/store/apps/details?id=com.solution.mircroprixs.tesseract&referrer=" + "TESSPOWER"
        )
        startActivity(Intent.createChooser(share, "Select"))
    }

    private fun shareLocalImage() {
        val b = BitmapFactory.decodeResource(resources, R.drawable.logo)
        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/png"
        val bytes = ByteArrayOutputStream()
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            activity?.getContentResolver(),
            b, "Title", null
        )
        val imageUri = Uri.parse(path)
        share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
        share.putExtra(Intent.EXTRA_STREAM, imageUri)
        share.putExtra(
            Intent.EXTRA_TEXT,
            getString(R.string.dummyText) + "\nhttps://play.google.com/store/apps/details?id=com.solution.mircroprixs.tesseract&referrer=" + "TESSPOWER"
        )
        startActivity(Intent.createChooser(share, "Select"))
    }

    private fun sendEmail() {
        val emailIntent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "abc@gmail.com", null
            )
        )
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")
        startActivity(Intent.createChooser(emailIntent, "Send email..."))
    }
}

