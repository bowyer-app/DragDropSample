package com.bowyer.app.dragdropsample

enum class ScoreValueType(val type: String) {
    DEFO("-"),
    MARU("○"),
    BATSU("×");

    companion object {
        fun of(type: String): ScoreValueType {
            when (type) {
                MARU.type -> return MARU
                BATSU.type -> return BATSU
                DEFO.type -> return DEFO
                else -> return DEFO
            }
        }
    }
}
