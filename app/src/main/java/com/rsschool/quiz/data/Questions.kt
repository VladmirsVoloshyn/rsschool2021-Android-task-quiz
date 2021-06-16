package com.rsschool.quiz.data

object Questions {

    val questions: ArrayList<Question> = ArrayList()

    fun init(
        question: Array<String>,
        arrayOfAnswer: ArrayList<Array<String>>,
        trueAnswers: Array<Int>
    ) {
        questions.add(
            Question(
                question[0],
                arrayOfAnswer[0][0],
                arrayOfAnswer[0][1],
                arrayOfAnswer[0][2],
                arrayOfAnswer[0][3],
                arrayOfAnswer[0][4],
                trueAnswers[0]
            )
        )

        questions.add(
            Question(
                question[1],
                arrayOfAnswer[1][0],
                arrayOfAnswer[1][1],
                arrayOfAnswer[1][2],
                arrayOfAnswer[1][3],
                arrayOfAnswer[1][4],
                trueAnswers[1]
            )
        )

        questions.add(
            Question(
                question[2],
                arrayOfAnswer[2][0],
                arrayOfAnswer[2][1],
                arrayOfAnswer[2][2],
                arrayOfAnswer[2][3],
                arrayOfAnswer[2][4],
                trueAnswers[2]
            )
        )

        questions.add(
            Question(
                question[3],
                arrayOfAnswer[3][0],
                arrayOfAnswer[3][1],
                arrayOfAnswer[3][2],
                arrayOfAnswer[3][3],
                arrayOfAnswer[3][4],
                trueAnswers[3]
            )
        )

        questions.add(
            Question(
                question[4],
                arrayOfAnswer[4][0],
                arrayOfAnswer[4][1],
                arrayOfAnswer[4][2],
                arrayOfAnswer[4][3],
                arrayOfAnswer[4][4],
                trueAnswers[4]
            )
        )
    }


}