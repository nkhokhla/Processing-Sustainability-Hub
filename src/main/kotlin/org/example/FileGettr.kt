package org.example

import processing.core.PApplet
import processing.data.Table


class FileGettr(var app: PApplet, var csvFile:String) {
    public var questions: Table?= null
    init {
        app.registerMethod("draw", this)
    }

    fun draw() {
        try {
            questions = app!!.loadTable(csvFile, "header")
            println(questions)
        }catch (e:RuntimeException ) {
            print(e.message)
            println("BRUUUH")
        } catch (e:NullPointerException){
            println(e.message)
            println("BRUUUH1")
        }
    }
}