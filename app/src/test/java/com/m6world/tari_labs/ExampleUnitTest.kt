package com.m6world.tari_labs

import com.m6world.tari_labs.api.models.RefreshRequest
import com.m6world.tari_labs.commons.JacksonMapper
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
        assertEquals(4, 2 + 2)
    }

    @Test fun jacksonTest(){
        var r = RefreshRequest("access", "refresh");
        var mapper = JacksonMapper()
        var json = mapper.toJson(r)
        println(json)
    }
}
