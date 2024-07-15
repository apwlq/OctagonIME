package io.github.apwlq.octagon

import io.github.apwlq.octagon.hangul.MoeumGestureProcessor
import org.junit.Assert
import org.junit.Test

// FIXME: Add more tests
class MoeumGestureProcessorTest {

    @Test
    fun testYaGesture() {
        val processor = MoeumGestureProcessor()
        processor.appendMoeum("ㅏ")
        processor.appendMoeum("ㅓ")
        processor.appendMoeum("ㅏ")
        Assert.assertEquals("ㅑ", processor.resolveMoeumList())
    }

}