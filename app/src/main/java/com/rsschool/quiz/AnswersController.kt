package com.rsschool.quiz

import android.os.Parcel
import android.os.Parcelable

class AnswersController() : Parcelable{

    private val truthAnswers : Array<Int> = arrayOf(3,4,1,5,3)

    private val repliesAnswers : Array<Int> = Array(5){0}

    constructor(parcel: Parcel) : this() {
    }

    private fun getResult() : Int{
        var result = 0
        for (i in this.truthAnswers.indices){
            if (this.truthAnswers[i] == this.repliesAnswers[i]){
                result += 20
            }
        }
        return result
    }

    fun registerAnswer(numberOfAnswer : Int, index : Int){
        repliesAnswers[index -1] = numberOfAnswer
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "AnswersController(repliesAnswers=${getResult()})"
    }

    companion object CREATOR : Parcelable.Creator<AnswersController> {
        override fun createFromParcel(parcel: Parcel): AnswersController {
            return AnswersController(parcel)
        }

        override fun newArray(size: Int): Array<AnswersController?> {
            return arrayOfNulls(size)
        }
    }
}