package quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_lab3quiz.api.RetrofitInstance
import kotlinx.coroutines.launch


class QuizViewModel : ViewModel() {
    var questions: MutableLiveData<MutableList<Question>> = MutableLiveData(quiz.questions)
    var numCorrect: Int = 0
    var numberOfQuestions: Int = quiz.questions.size
    var itQuestion: MutableIterator<Question> = quiz.questions.iterator()
    var currentQuestion: MutableLiveData<Pair<Question?, Boolean>> =
        MutableLiveData<Pair<Question?, Boolean>>()
    var bestScore: Int = 0
    var userName: String = "Please start a game first"
    var selectedQuestion: MutableLiveData<Question> = MutableLiveData<Question>();

    init {
        getQuestionsFromAPI(20)
        numberOfQuestions = questions.value!!.size
        getNextQuestion()
        selectQuestion(0)
    }

    private fun randomizeQuestions() {
        questions.value!!.forEach { q -> q.answers.shuffle() }
        questions.value!!.shuffle()
    }

    fun doQuiz(numOfQuestions: Int) {
        this.randomizeQuestions()
        val questionsToBeShown = questions.value!!.subList(0, numOfQuestions);
        itQuestion = questionsToBeShown.iterator()
    }

    fun getNextQuestion() {
        if (itQuestion.hasNext()) {
            currentQuestion.value = itQuestion.next() to !itQuestion.hasNext()
        }
    }

    fun resetQuiz() {
        numCorrect = 0
        doQuiz(numberOfQuestions)
    }

    fun deleteQuestion(pos: Int) {
        questions.value!!.removeAt(pos)
    }

    fun selectQuestion(pos: Int) {
        selectedQuestion.value = questions.value!![pos]
    }

    fun getQuestionsFromAPI(amount: Int) {
        viewModelScope.launch {
            val questionss = RetrofitInstance.api.getQuestions(amount);
            Log.d("QUESTIONS", questionss.body()?.results.toString())
            val questionsFromApi: MutableList<Question> = mutableListOf()
            questionss.body()?.results?.forEach { it ->
                Log.d("QUESTIONS", it.toString())
                val answers: MutableList<String> = it.incorrectAnswers as MutableList<String>
                answers.add(it.correctAnswer)
                questionsFromApi.add(Question(it.question, answers, it.correctAnswer))
            }
            questions.value = questionsFromApi
            doQuiz(4)
            getNextQuestion()
        }
    }

    fun addQuestion(question: Question) {
        questions.value?.add(0,question)
    }
}

































