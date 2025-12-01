package org.example.app

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

/**
 * PUBLIC_INTERFACE
 * Ensures the JUnit Platform is active during Gradle test execution.
 */
class DiscoveryTest {
    @Test
    fun addition() {
        assertEquals(2, 1 + 1)
    }
}
