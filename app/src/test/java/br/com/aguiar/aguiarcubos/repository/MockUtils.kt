@file:Suppress("UNCHECKED_CAST")

package br.com.aguiar.aguiarcubos.repository

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

inline fun <reified T> mock() = Mockito.mock(T::class.java)

inline fun <T> whenever(methodCall: T): OngoingStubbing<T> =
    Mockito.`when`(methodCall)