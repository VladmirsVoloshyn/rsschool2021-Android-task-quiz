package com.rsschool.quiz

import android.os.Parcel
import android.os.Parcelable
import com.rsschool.quiz.data.QuestionsRepository

class AnswersController() : Parcelable {

    private val trueAnswers: Array<Int> = Array(5) { 0 }

    private val repliesAnswers: Array<Int> = Array(5) { 0 }


    fun getRepliesAnswer(index: Int): Int {
        return repliesAnswers[index - 1]
    }

    private fun getResult(): Int {
        var result = 0
        for (i in this.trueAnswers.indices) {
            if (this.trueAnswers[i] == this.repliesAnswers[i]) {
                result += 20
            }
        }
        return result
    }

    fun registerAnswer(numberOfAnswer: Int, index: Int, trueAnswer: Int) {
        repliesAnswers[index - 1] = numberOfAnswer
        trueAnswers[index - 1] = trueAnswer
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Твой результат : \n ${getResult()}%" + "\n" + when (this.getResult()) {
            0 -> ZERO_PERCENT_COMMENT
            20 -> TWENTY_PERCENT_COMMENT
            40 -> FOURTEEN_PERCENT_COMMENT
            60 -> SIXTEEN_PERCENT_COMMENT
            80 -> EIGHTY_PERCENT_COMMENT
            else -> HUNDRED_PERCENT_COMMENT
        }
    }

    fun generateEmailText(): String {

        var resultEmailMessage = this.toString()

        for (i in 1..5) {
            resultEmailMessage += "\n" +
                    i + ")" + QuestionsRepository.questions[i - 1].question + "\n" +
                    "Твой ответ: " +
                    when (repliesAnswers[i - 1]) {
                        1 -> QuestionsRepository.questions[i - 1].firstAnswer
                        2 -> QuestionsRepository.questions[i - 1].secondAnswer
                        3 -> QuestionsRepository.questions[i - 1].thirdAnswer
                        4 -> QuestionsRepository.questions[i - 1].fourthAnswer
                        else -> QuestionsRepository.questions[i - 1].fifthAnswer
                    } + "\n"
        }
        return resultEmailMessage
    }

    companion object CREATOR : Parcelable.Creator<AnswersController> {
        override fun createFromParcel(parcel: Parcel): AnswersController {
            return AnswersController(parcel)
        }

        override fun newArray(size: Int): Array<AnswersController?> {
            return arrayOfNulls(size)
        }

        const val ZERO_PERCENT_COMMENT = "Очень плохо, старайся лучше!"
        const val TWENTY_PERCENT_COMMENT = "Лучше, чем ничего..."
        const val FOURTEEN_PERCENT_COMMENT =
            "Ты, возможно, что-то да и знаешь о непонятных вопросах."
        const val SIXTEEN_PERCENT_COMMENT = "Больше половины, не дурно!"
        const val EIGHTY_PERCENT_COMMENT = "Да ты мозг, квиз поражен!"
        const val HUNDRED_PERCENT_COMMENT = "exscelsior!!"
    }

    constructor(parcel: Parcel) : this()

}