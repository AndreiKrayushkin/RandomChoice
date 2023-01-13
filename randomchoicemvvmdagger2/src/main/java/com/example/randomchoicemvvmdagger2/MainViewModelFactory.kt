package com.example.randomchoicemvvmdagger2

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randomchoicemvvmdagger2.model.GetData

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val usersDataBase by lazy(LazyThreadSafetyMode.NONE) {
        GetData(context = context)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(usersDataBase) as T
    }
}