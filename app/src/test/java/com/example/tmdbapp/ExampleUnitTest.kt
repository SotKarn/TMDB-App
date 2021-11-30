package com.example.tmdbapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        var test: TestObj? = null

        test?.x = 3

        assertEquals(3, test?.x)
    }
}

data class TestObj(var x:Int?, var y:Int?)