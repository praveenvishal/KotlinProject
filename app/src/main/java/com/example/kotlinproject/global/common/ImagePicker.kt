package com.example.kotlinproject.global.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import java.io.File
import java.util.ArrayList

class ImagePicker {
    companion object {
        private val TAG = ImagePicker::class.java.simpleName
        val REQUEST_CAMERA = 5000
        val SELECT_FILE = 5001
        val PICK_IMAGE_MULTIPLE = 5002
        val REQUEST_CAPTURE_IMAGE = 1
        private var mImagePickerListener: ImagePickerListener? = null
        private val imageEncoded: String? = null
        private var selectedImage: ArrayList<File>? = null
        var mMimeTypes = arrayOf("image/jpeg", "image/png", "image/jpg", "video/mp4")
        var mMimeTypesSingle = arrayOf("image/jpeg", "image/png", "image/jpg")
        private var mTempPhotoPath: String? = null
        var mActivity: Activity?=null
        private var mIsFromProfile = false
    }

    /**
     * capture image for native camera
     */
    fun captureImage(activity: Activity, imagePickerListener: ImagePickerListener) {
        mActivity = activity
        mImagePickerListener = imagePickerListener
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        activity.startActivityForResult(intent, REQUEST_CAMERA)
    }

    fun onActivityResult(activity: Activity,requestCode: Int, resultCode: Int, data: Intent?) {
        try {
            Log.d("TAG", "Image picker")
            var file: MutableList<File> = ArrayList()
            var compressedFiles: File? = null
            when (requestCode) {
                REQUEST_CAMERA -> {
                    var bitmap: Bitmap? = null
//                    if (mTempPhotoPath != null && (data == null || data.data == null)) {
//                        //                        Bitmap bitmapImg = FileUtils.resamplePic(mActivity, mTempPhotoPath);
////                        if (mIsFromProfile) {
////                            val imageUri = Uri.fromFile(File(mTempPhotoPath))
////                            CropImage.activity(imageUri)
////                                .setActivityTitle(mActivity.getResources().getString(R.string.crop_image))
////                                .start(mActivity)
////                            return
////                        } else {
////                            FileUtils.updateGallery(mActivity, mTempPhotoPath)
////                            compressedFiles = CompressImage.compressImage(mTempPhotoPath.toString())
////                            file.add(compressedFiles)
////
////                        }
//                    } else
                    if (data!!.extras != null && data.extras!!.get("data") != null) {
                        bitmap = data.extras!!.get("data") as Bitmap
                        file.add(FileUtils.saveBitmapImage(bitmap))
                    } else if (data.data != null) {
                        //In case of video
                        file = getData(activity, data)
                    }
                    mImagePickerListener?.imagePath(file)
                    mTempPhotoPath = null
                }
//                SELECT_FILE -> {
//                    val uri = data!!.data
//                    file.add(FileUtils.getPathFromUri(uri))
//                    mImagePickerListener.imagePath(file)
//                }
//                PICK_IMAGE_MULTIPLE -> {
//                    val files = getData(data!!)
//                    if (mIsFromProfile) {
//                        val imageUri = Uri.fromFile(files.get(0))
//                        CropImage.activity(imageUri)
//                            .setActivityTitle(mActivity.getResources().getString(R.string.crop_image))
//                            .start(mActivity)
//                    } else {
//                        for (i in files.indices) {
//                            val mimeType = FileUtils.getMimeType(files.get(i).toString())
//                            if (mimeType == ImagePicker.mMimeTypes[0] ||
//                                mimeType == ImagePicker.mMimeTypes[1] ||
//                                mimeType == ImagePicker.mMimeTypes[2]
//                            ) {
//                                compressedFiles = CompressImage.compressImage(files.get(i).toString())
//                                Lg.d(
//                                    TAG,
//                                    "onActivityResult: old Image - " + FileUtils.getFileSizeInMbTest(files.get(i)) +
//                                            "\n compress image - " + FileUtils.getFileSizeInMbTest(compressedFiles)
//                                )
//                                files.set(i, compressedFiles)
//                            }
//                        }
//                        mImagePickerListener.imagePath(files)
//                    }
//                }
//                REQUEST_CAPTURE_IMAGE -> if (mTempPhotoPath != null) {
//                    if (mIsFromProfile) {
//                        val imageUri = Uri.fromFile(File(mTempPhotoPath))
//                        CropImage.activity(imageUri)
//                            .setActivityTitle(mActivity.getResources().getString(R.string.crop_image))
//                            .start(mActivity)
//                    } else {
//                        FileUtils.updateGallery(mActivity, mTempPhotoPath)
//                        compressedFiles = CompressImage.compressImage(mTempPhotoPath.toString())
//                        file.add(compressedFiles)
//                        mImagePickerListener.imagePath(file)
//                        val imageUri = Uri.fromFile(compressedFiles)
//                        CropImage.activity(imageUri)
//                            .setActivityTitle(mActivity.getResources().getString(R.string.crop_image))
//                            .start(mActivity)
//                    }
//                    mTempPhotoPath = null
//                }
//                CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
//                    val result = CropImage.getActivityResult(data)
//                    val resultUri = result.getUri()
//                    val mFile = getPath(mActivity, resultUri)
//                    compressedFiles = CompressImage.compressImage(mFile)
//                    file.add(compressedFiles)
//                    mImagePickerListener.imagePath(file)
//                    mTempPhotoPath = null
//                }
            }
        } catch (exp: Exception) {
//            Crashlytics.logException(exp)
//            Lg.d(TAG, "onActivityResult: $exp")
        }
    }



    interface ImagePickerListener {
        fun imagePath(filePath: List<File>)
    }


    @SuppressLint("NewApi")
    private fun getData(activity: Activity, data: Intent): ArrayList<File> {
        selectedImage = ArrayList()
        if (data.clipData != null) {
            for (i in 0 until data.clipData!!.itemCount) {
                val uri = data.clipData!!.getItemAt(i).uri
                selectedImage!!.add(File(getPath(activity, uri)))
            }
        } else {
            val pathh = getPath(activity, data.data)
            selectedImage!!.add(File(pathh))
        }
        return selectedImage as ArrayList<File>
    }

    @SuppressLint("NewApi")
    fun getPath(context: Context, uri: Uri?): String? {

        val isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri!!)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type = split[0]

                if ("primary".equals(type, ignoreCase = true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }

                // TODO handle non-primary volumes
            } else if (isDownloadsDocument(uri)) {

                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id)
                )

                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type = split[0]

                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }

                val selection = "_id=?"
                val selectionArgs = arrayOf(split[1])

                return getDataColumn(context, contentUri, selection, selectionArgs)
            }// MediaProvider
            // DownloadsProvider
        } else if ("content".equals(uri!!.scheme!!, ignoreCase = true)) {
            return getDataColumn(context, uri, null, null)
        } else if ("file".equals(uri.scheme!!, ignoreCase = true)) {
            return uri.path
        }// File
        // MediaStore (and general)

        return null
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    fun getDataColumn(
        context: Context, uri: Uri?, selection: String?,
        selectionArgs: Array<String>?
    ): String? {

        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)

        try {
            cursor = context.contentResolver.query(uri!!, projection, selection, selectionArgs, null)
            if (cursor != null && cursor.moveToFirst()) {
                val column_index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(column_index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }


}