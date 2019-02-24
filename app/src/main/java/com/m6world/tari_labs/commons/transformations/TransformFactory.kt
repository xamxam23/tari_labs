package com.m6world.tari_labs.commons.transformations

import android.support.v4.view.ViewPager

object TransformFactory {
    val transformers = arrayOf(CubeOutTransformation(), RotationTransformation())

    operator fun get(x: Int): ViewPager.PageTransformer? {
        return if (x < 0 || x >= size()) null else transformers[x]
    }

    fun size(): Int {
        return transformers.size
    }
}