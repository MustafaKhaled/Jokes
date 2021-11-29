package com.khaled.jokes.ui

import androidx.recyclerview.widget.RecyclerView
import com.khaled.jokes.data.model.Flags
import com.khaled.jokes.data.model.JokeItem
import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Test

class JokesAdapterTest {
    @Test
    fun verify_adapterSize(){
        val list = ArrayList<JokeItem>()
        mockk<RecyclerView>(relaxed = true)
        list.add(JokeItem("cat","type","setup","delivery",
            Flags(true,true, true, true, true, true),1, true,"lang"))
        val adapter = mockk<JokesAdapter>()
        every { adapter.notifyDataSetChanged() } just Runs
        every { adapter.addAll(list) } just Runs
        every { adapter.itemCount } returns list.size
        adapter.addAll(list)
        assertEquals(1, adapter.itemCount)
    }
}