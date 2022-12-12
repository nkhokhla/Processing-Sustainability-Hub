package org.example

import com.jogamp.nativewindow.WindowClosingProtocol
import com.jogamp.newt.Window
import org.example.Processing.Companion.questions
import processing.awt.PGraphicsJava2D
import processing.awt.PSurfaceAWT.SmoothCanvas
import processing.core.PApplet
import processing.core.PFont
import java.util.*
import javax.swing.JFrame
import javax.swing.WindowConstants


class QuizW : PApplet() {
    //variables
    var app: PApplet? = null

    //This table is the initial imported CSV
    var f: PFont? = null
    var csvFile = dataPath("questions.csv")
    var rowCount = questions!!.rowCount ///How many questions there are in the import
    var pressTime =0
    var id = 0 //Individual question IDs
    var t = ""
    var q=1
    var questionCount = 2 //Maximum size I expect the array of questions could be.

    var question = Array(3) {
        arrayOfNulls<String>(
            9
        )
    } //two dimensional array of questions, their answers, and correct/wrong responses.


    init {
        runSketch(arrayOf(this.javaClass.simpleName), this)
    }

    override fun settings() {
        size(900, 600, RENDERER)
    }

    override fun setup() {
        surface.setTitle("Quiz")
//        try {
//            questionCount = (PApplet().loadTable(csvFile, "header")).getRowCount() //Load up the questions sheet
//        } catch (e: RuntimeException) {
//
//        }
//        try {
//        convertTable() // Convert the questions sheet to a two dimensional array
//        } catch (e: RuntimeException) {
//
//        }
        f = createFont("Arial", 20f, false) // use true/false for smooth/no-smooth

        setDefaultClosePolicy(this, false)
    }

    fun convertTable(n: Int) {
//        app = papp
//        try{
//        val questions = app!!.loadTable(csvFile, "header")
//            println("BRUUUH")
//         } catch (e: RuntimeException) {
//             sketchPath()
//
//    }
//        println("\n" +row.getString("id"))
        if (questions != null) {
            var row = questions!!.getRow(n)
            id = row.getInt("id")
//            println(id)
            question[id][0] = row.getString("id")
            question[id][1] = row.getString("Question")
            t = question[1][1].toString()
            question[id][2] = row.getString("A")
            question[id][3] = row.getString("B")
            question[id][4] = row.getString("C")
            question[id][5] = row.getString("D")
            question[id][6] = row.getString("Correct").toLowerCase()
            question[id][7] = row.getString("Correct Response")
            question[id][8] = row.getString("Wrong Response")
        } else{
            println("AAAHAH")
        }
//        app!!.registerMethod("draw", this);
//        println(question[1][1])
//        return question
    }

    fun displayNewQuestion(que: Array<Array<String?>>, questionID: Int) {
        textFont(f)
        fill(255)
//        println(que[1][1])
        text(que[questionID][1], 20f, 50f)
        fill(220f, 20f, 60f)
        ellipse((width / 16 + 65).toFloat(), (height / 3).toFloat(), 95f, 95f)
        fill(34f, 139f, 34f)
        ellipse((width / 16 + 65).toFloat(), (height / 3 + 100).toFloat(), 95f, 95f)
        fill(70f, 130f, 180f)
        ellipse((width / 16 + 65).toFloat(), (height / 3 + 200).toFloat(), 95f, 95f)
        fill(255f, 215f, 0f)
        ellipse((width / 16 + 65).toFloat(), (height / 3 + 300).toFloat(), 95f, 95f)
        fill(255)
        textFont(f)
        text("A: ", width / 16f + 50, height / 3f + 10)
        text("B: ", width / 16f + 50, height / 3 + 110f)
        text("C: ", width / 16 + 50f, height / 3 + 210f)
        text("D: ", width / 16 + 50f, height / 3 + 310f)
        text(que[questionID][2], width / 16f + 150, height / 3f + 10)
        text(que[questionID][3], width / 16 + 150f, height / 3 + 110f)
        text(que[questionID][4], width / 16f + 150, height / 3 + 210f)
        text(que[questionID][5], width / 16 + 150f, height / 3 + 310f)
        val correctAnswer = question[questionID][6]?.lowercase(Locale.getDefault())?.get(0)
        if (key == correctAnswer) {
            correctAnswerSubmitted(question[questionID][7]);
        } else if (keyPressed == true) {
            wrongAnswerSubmitted(question[questionID][8]);
        }
    }

    //    fun looper(question: Array<Array<String>>) {
//        val questionSelected = random(1f, questionCount.toFloat()).toInt()
////
////        print(questionSelected);
////        print(question[questionSelected][1]);
////        displayNewQuestion(question, questionSelected)
//    }
    override fun draw() {
//        print(rowCount)
        if(q<=rowCount){
            convertTable(q - 1)
            displayNewQuestion(question, q)
        } else{
            text("Quiz finished",50f,20f)
        }

    }
    override fun keyReleased() {
        pressTime = millis()
        println("CKE")
    }
    fun correctAnswerSubmitted(correctResponse: String?) {
//        pressTime=0
        if (millis() < pressTime + 5000) {
            clear()
            background(204f,204f,204f)
            text(correctResponse, 20f, 50f)
//            println(time)
            println("CASE1")
        } else{
//            println("CASE2")
            clear()
            background(204f,204f,204f)
            q++
            println(pressTime)
        }
    }

    fun wrongAnswerSubmitted(wrongResponse: String?) {
        println(pressTime)
        if (millis() < pressTime + 5000) {
            clear()
            background(204f,204f,204f)
            println("CASE3")
            text(wrongResponse, 20f, 50f)
        } else{
            clear()
            background(204f,204f,204f)
            println("CASE4")
            q++
        }
    }

    override fun exit() {
        //made to close only current window
        dispose()
        Processing.winQ = null
    }

    fun setDefaultClosePolicy(pa: PApplet, keepOpen: Boolean) {
        //made to close only current window
        val surf = pa.surface.native
        val canvas = pa.graphics
        if (canvas.isGL) {
            val w = surf as Window
            for (wl in w.windowListeners) if (wl.toString()
                    .startsWith("processing.opengl.PSurfaceJOGL")
            ) w.removeWindowListener(wl)
            w.defaultCloseOperation =
                if (keepOpen) WindowClosingProtocol.WindowClosingMode.DO_NOTHING_ON_CLOSE else WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE
        } else if (canvas is PGraphicsJava2D) {
            val f = (surf as SmoothCanvas).frame as JFrame
            for (wl in f.windowListeners) if (wl.toString()
                    .startsWith("processing.awt.PSurfaceAWT")
            ) f.removeWindowListener(wl)
            f.defaultCloseOperation =
                if (keepOpen) WindowConstants.DO_NOTHING_ON_CLOSE else WindowConstants.DISPOSE_ON_CLOSE
        }
    }

    fun makeVisible() {
        //made to close only current window
        surface.setVisible(true)
    }

    companion object {
        //made to close only current window
        const val RENDERER = P3D
    }
}