/*
 * Copyright (C) 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.ink.geometry

import androidx.ink.nativeloader.NativeLoader

/** Helper functions for Vec. */
internal object VecNative {

    init {
        NativeLoader.load()
    }

    // TODO: b/355248266 - @Keep must go in Proguard config file instead.
    external fun unitVec(
        vecX: Float,
        vecY: Float,
        immutableVecClass: Class<ImmutableVec>,
    ): ImmutableVec

    external fun populateUnitVec(
        vecX: Float,
        vecY: Float,
        output: MutableVec
    ) // TODO: b/355248266 - @Keep must go in Proguard config file instead.

    // TODO: b/355248266 - @Keep must go in Proguard config file instead.
    external fun absoluteAngleBetween(
        firstVecX: Float,
        firstVecY: Float,
        secondVecX: Float,
        secondVecY: Float,
    ): Float

    // TODO: b/355248266 - @Keep must go in Proguard config file instead.
    external fun signedAngleBetween(
        firstVecX: Float,
        firstVecY: Float,
        secondVecX: Float,
        secondVecY: Float,
    ): Float
}