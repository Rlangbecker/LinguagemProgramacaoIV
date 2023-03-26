package br.com.fundatec

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    private val name = MutableLiveData<String>()
    val publicName : LiveData<String> = name

    private val helloVisibility = MutableLiveData<Int>()
    val visibility : LiveData<Int> = helloVisibility

    private val toast = MutableLiveData<Unit>()
    val showToast : LiveData<Unit> = toast

    val startnavigate : Boolean = false

    fun validateName(text: Editable? , text2: Editable?): Boolean {
        val newText = text.toString()
        val newText2 = text2.toString()
        if(!newText.isNullOrBlank() && !newText2.isNullOrBlank()){
            return true
        } else {
            toast.value = Unit
            return false
        }
    }

    fun clear(){
        name.value = ""
        helloVisibility.value=View.GONE
    }


}