package com.rsschool.quiz

import android.os.Parcel
import android.os.Parcelable
import com.rsschool.quiz.data.Questions

class AnswersController() : Parcelable {

    private val truthAnswers: Array<Int> = arrayOf(3, 4, 1, 5, 3)

    private val repliesAnswers: Array<Int> = Array(5) { 0 }

    constructor(parcel: Parcel) : this() {
    }

    private fun getResult(): Int {
        var result = 0
        for (i in this.truthAnswers.indices) {
            if (this.truthAnswers[i] == this.repliesAnswers[i]) {
                result += 20
            }
        }
        return result
    }

    fun registerAnswer(numberOfAnswer: Int, index: Int) {
        repliesAnswers[index - 1] = numberOfAnswer
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Твой результат результат : \n ${getResult()}%"
    }

    fun generateEmailText(): String {
        return this.toString() + "\n\n" +
                "1)" + Questions.questions[0].question + "\n" +
                "Твой ответ: " +
                when (repliesAnswers[0]) {
                    1 -> Questions.questions[0].firstAnswer
                    2 -> Questions.questions[0].secondAnswer
                    3 -> Questions.questions[0].thirdAnswer
                    4 -> Questions.questions[0].fourthAnswer
                    else -> Questions.questions[0].fifthAnswer
                } + "\n\n" +
                "2)" + Questions.questions[1].question + "\n" +
                "Твой ответ: " +
                when (repliesAnswers[1]) {
                    1 -> Questions.questions[1].firstAnswer
                    2 -> Questions.questions[1].secondAnswer
                    3 -> Questions.questions[1].thirdAnswer
                    4 -> Questions.questions[1].fourthAnswer
                    else -> Questions.questions[1].fifthAnswer
                } + "\n\n" +
                "3)" + Questions.questions[2].question + "\n" +
                "Твой ответ: " +
                when (repliesAnswers[2]) {
                    1 -> Questions.questions[2].firstAnswer
                    2 -> Questions.questions[2].secondAnswer
                    3 -> Questions.questions[2].thirdAnswer
                    4 -> Questions.questions[2].fourthAnswer
                    else -> Questions.questions[2].fifthAnswer
                } + "\n\n" +
                "4)" + Questions.questions[3].question + "\n" +
                "Твой ответ: " +
                when (repliesAnswers[3]) {
                    1 -> Questions.questions[3].firstAnswer
                    2 -> Questions.questions[3].secondAnswer
                    3 -> Questions.questions[3].thirdAnswer
                    4 -> Questions.questions[3].fourthAnswer
                    else -> Questions.questions[3].fifthAnswer
                } + "\n\n" +
                "5)" + Questions.questions[4].question + "\n" +
                "Твой ответ: " +
                when (repliesAnswers[4]) {
                    1 -> Questions.questions[4].firstAnswer
                    2 -> Questions.questions[4].secondAnswer
                    3 -> Questions.questions[4].thirdAnswer
                    4 -> Questions.questions[4].fourthAnswer
                    else -> Questions.questions[4].fifthAnswer
                }
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