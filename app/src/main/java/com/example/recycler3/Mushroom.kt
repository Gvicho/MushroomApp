package com.example.recycler3

data class Mushroom(val name:String,
                    val isAdible: IsAdible) {

    var listOfAreas = mutableListOf<String>() // where mushroom is common
    var id:Int = -1
    var type = -1

    constructor(name: String,isAdible: IsAdible,listOfAreas: MutableList<String>):this(name,isAdible){
        this.listOfAreas = listOfAreas
    }

    constructor(name: String,isAdible: IsAdible,listOfAreas: MutableList<String>,id:Int):this(name,isAdible){
        this.listOfAreas = listOfAreas
        this.id = id
    }

    constructor(name: String,isAdible: IsAdible,id:Int,type:Int):this(name,isAdible){
        this.id = id
        this.type = type
    }

}

enum class IsAdible{
    ADIBLE,
    MIGHT_HARM,
    DENGEROUS
}