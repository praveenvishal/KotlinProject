package com.example.kotlinproject.global.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.security.Provider


class AppViewModelFactory(val context: Context) : ViewModelProvider.NewInstanceFactory() {

//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//
//        return modelClass(context) as T
//    }
}
//@Singleton
//class AppViewModelFactory  constructor(private val viewModels: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
//    ViewModelProvider.Factory {
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        var creator: Provider<out ViewModel>? = viewModels[modelClass]
//        if (creator == null) {
//            for ((key, value) in viewModels) {
//                if (modelClass.isAssignableFrom(key)) {
//                    creator = value; break
//                }
//            }
//        }
//        if (creator == null) {
//            throw IllegalArgumentException("unknown model class $modelClass")
//        }
//        try {
//            /* NOTE: Get HomeOfferViewModel from static Scope throughout the app
//             *
//             * */
////            if (creator.get() is HomeOffersViewModel) {
////                var strViewModelName = creator.get()::class.java
////                if (!viewModelsCommon.containsKey(strViewModelName))
////                    viewModelsCommon[strViewModelName] = creator.get()
////                return viewModelsCommon[strViewModelName] as T
////            } else {
//                /*  Returning ViewModel from Provider */
//                return creator.get() as T
////            }
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }
//    }
//    /*  override fun <T : ViewModel> create(modelClass: Class<T>): T {
//           return viewModels[modelClass]?.get() as T
//      }
//  */
//    companion object {
//        /* ViewModel Map used for static Scope  throughout the app  */
//        val viewModelsCommon = HashMap<Class<out ViewModel>, ViewModel>()
//    }
//    fun nukeVm()
//    {
//     viewModelsCommon.clear()
//    }
