/*
 * Copyright 2020 The Android Open Source Project
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

package androidx.compose.foundation.lazy

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertLeftPositionInRootIsEqualTo
import androidx.compose.ui.test.assertTopPositionInRootIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalFoundationApi::class)
class LazyListHeadersTest {

    private val LazyListTag = "LazyList"

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun lazyColumnShowsHeader() {
        val items = (1..2).map { it.toString() }
        val firstHeaderTag = "firstHeaderTag"
        val secondHeaderTag = "secondHeaderTag"

        rule.setContent {
            LazyColumn(Modifier.preferredHeight(300.dp)) {
                stickyHeader {
                    Spacer(
                        Modifier.preferredHeight(101.dp).fillParentMaxWidth()
                            .testTag(firstHeaderTag)
                    )
                }

                items(items) {
                    Spacer(Modifier.preferredHeight(101.dp).fillParentMaxWidth().testTag(it))
                }

                stickyHeader {
                    Spacer(
                        Modifier.preferredHeight(101.dp).fillParentMaxWidth()
                            .testTag(secondHeaderTag)
                    )
                }
            }
        }

        rule.onNodeWithTag(firstHeaderTag)
            .assertIsDisplayed()

        rule.onNodeWithTag("1")
            .assertIsDisplayed()

        rule.onNodeWithTag("2")
            .assertIsDisplayed()

        rule.onNodeWithTag(secondHeaderTag)
            .assertDoesNotExist()
    }

    @Test
    fun lazyColumnShowsHeadersOnScroll() {
        val items = (1..2).map { it.toString() }
        val firstHeaderTag = "firstHeaderTag"
        val secondHeaderTag = "secondHeaderTag"

        rule.setContentWithTestViewConfiguration {
            LazyColumn(Modifier.preferredHeight(300.dp).testTag(LazyListTag)) {
                stickyHeader {
                    Spacer(
                        Modifier.preferredHeight(101.dp).fillParentMaxWidth()
                            .testTag(firstHeaderTag)
                    )
                }

                items(items) {
                    Spacer(Modifier.preferredHeight(101.dp).fillParentMaxWidth().testTag(it))
                }

                stickyHeader {
                    Spacer(
                        Modifier.preferredHeight(101.dp).fillParentMaxWidth()
                            .testTag(secondHeaderTag)
                    )
                }
            }
        }

        rule.onNodeWithTag(LazyListTag)
            .scrollBy(y = 102.dp, density = rule.density)

        rule.onNodeWithTag(firstHeaderTag)
            .assertIsDisplayed()
            .assertTopPositionInRootIsEqualTo(0.dp)

        rule.onNodeWithTag("2")
            .assertIsDisplayed()

        rule.onNodeWithTag(secondHeaderTag)
            .assertIsDisplayed()
    }

    @Test
    fun lazyColumnHeaderIsReplaced() {
        val items = (1..2).map { it.toString() }
        val firstHeaderTag = "firstHeaderTag"
        val secondHeaderTag = "secondHeaderTag"

        rule.setContentWithTestViewConfiguration {
            LazyColumn(Modifier.preferredHeight(300.dp).testTag(LazyListTag)) {
                stickyHeader {
                    Spacer(
                        Modifier.preferredHeight(101.dp).fillParentMaxWidth()
                            .testTag(firstHeaderTag)
                    )
                }

                stickyHeader {
                    Spacer(
                        Modifier.preferredHeight(101.dp).fillParentMaxWidth()
                            .testTag(secondHeaderTag)
                    )
                }

                items(items) {
                    Spacer(Modifier.preferredHeight(101.dp).fillParentMaxWidth().testTag(it))
                }
            }
        }

        rule.onNodeWithTag(LazyListTag)
            .scrollBy(y = 102.dp, density = rule.density)

        rule.onNodeWithTag(firstHeaderTag)
            .assertDoesNotExist()

        rule.onNodeWithTag(secondHeaderTag)
            .assertIsDisplayed()

        rule.onNodeWithTag("1")
            .assertIsDisplayed()

        rule.onNodeWithTag("2")
            .assertIsDisplayed()
    }

    @Test
    fun lazyRowShowsHeader() {
        val items = (1..2).map { it.toString() }
        val firstHeaderTag = "firstHeaderTag"
        val secondHeaderTag = "secondHeaderTag"

        rule.setContent {
            LazyRow(Modifier.preferredWidth(300.dp)) {
                stickyHeader {
                    Spacer(
                        Modifier.preferredWidth(101.dp).fillParentMaxHeight()
                            .testTag(firstHeaderTag)
                    )
                }

                items(items) {
                    Spacer(Modifier.preferredWidth(101.dp).fillParentMaxHeight().testTag(it))
                }

                stickyHeader {
                    Spacer(
                        Modifier.preferredWidth(101.dp).fillParentMaxHeight()
                            .testTag(secondHeaderTag)
                    )
                }
            }
        }

        rule.onNodeWithTag(firstHeaderTag)
            .assertIsDisplayed()

        rule.onNodeWithTag("1")
            .assertIsDisplayed()

        rule.onNodeWithTag("2")
            .assertIsDisplayed()

        rule.onNodeWithTag(secondHeaderTag)
            .assertDoesNotExist()
    }

    @Test
    fun lazyRowShowsHeadersOnScroll() {
        val items = (1..2).map { it.toString() }
        val firstHeaderTag = "firstHeaderTag"
        val secondHeaderTag = "secondHeaderTag"

        rule.setContentWithTestViewConfiguration {
            LazyRow(Modifier.preferredWidth(300.dp).testTag(LazyListTag)) {
                stickyHeader {
                    Spacer(
                        Modifier.preferredWidth(101.dp).fillParentMaxHeight()
                            .testTag(firstHeaderTag)
                    )
                }

                items(items) {
                    Spacer(Modifier.preferredWidth(101.dp).fillParentMaxHeight().testTag(it))
                }

                stickyHeader {
                    Spacer(
                        Modifier.preferredWidth(101.dp).fillParentMaxHeight()
                            .testTag(secondHeaderTag)
                    )
                }
            }
        }

        rule.onNodeWithTag(LazyListTag)
            .scrollBy(x = 102.dp, density = rule.density)

        rule.onNodeWithTag(firstHeaderTag)
            .assertIsDisplayed()
            .assertLeftPositionInRootIsEqualTo(0.dp)

        rule.onNodeWithTag("2")
            .assertIsDisplayed()

        rule.onNodeWithTag(secondHeaderTag)
            .assertIsDisplayed()
    }

    @Test
    fun lazyRowHeaderIsReplaced() {
        val items = (1..2).map { it.toString() }
        val firstHeaderTag = "firstHeaderTag"
        val secondHeaderTag = "secondHeaderTag"

        rule.setContentWithTestViewConfiguration {
            LazyRow(Modifier.preferredWidth(300.dp).testTag(LazyListTag)) {
                stickyHeader {
                    Spacer(
                        Modifier.preferredWidth(101.dp).fillParentMaxHeight()
                            .testTag(firstHeaderTag)
                    )
                }

                stickyHeader {
                    Spacer(
                        Modifier.preferredWidth(101.dp).fillParentMaxHeight()
                            .testTag(secondHeaderTag)
                    )
                }

                items(items) {
                    Spacer(Modifier.preferredWidth(101.dp).fillParentMaxHeight().testTag(it))
                }
            }
        }

        rule.onNodeWithTag(LazyListTag)
            .scrollBy(x = 102.dp, density = rule.density)

        rule.onNodeWithTag(firstHeaderTag)
            .assertDoesNotExist()

        rule.onNodeWithTag(secondHeaderTag)
            .assertIsDisplayed()

        rule.onNodeWithTag("1")
            .assertIsDisplayed()

        rule.onNodeWithTag("2")
            .assertIsDisplayed()
    }

    @Test
    fun headerIsDisplayedWhenItIsFullyInContentPadding() {
        val headerTag = "header"
        val itemIndexPx = 100
        val itemIndexDp = with(rule.density) { itemIndexPx.toDp() }
        lateinit var state: LazyListState

        rule.setContent {
            LazyColumn(
                Modifier.size(itemIndexDp * 4),
                state = rememberLazyListState().also { state = it },
                contentPadding = PaddingValues(top = itemIndexDp * 2)
            ) {
                stickyHeader {
                    Spacer(Modifier.size(itemIndexDp).testTag(headerTag))
                }

                items((0..4).toList()) {
                    Spacer(Modifier.size(itemIndexDp).testTag("$it"))
                }
            }
        }

        rule.runOnIdle {
            runBlocking { state.snapToItemIndex(1, itemIndexPx / 2) }
        }

        rule.onNodeWithTag(headerTag)
            .assertTopPositionInRootIsEqualTo(itemIndexDp / 2)

        rule.onNodeWithTag("0")
            .assertTopPositionInRootIsEqualTo(itemIndexDp * 3 / 2)
    }
}