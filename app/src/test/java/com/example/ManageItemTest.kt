package com.example

import com.example.Item.Item
import com.example.Item.ManageItem
import org.junit.Test

import org.junit.Assert.*
import kotlin.time.TimeSource

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ManageItemTest {

    /*
    * Insert one item
    * */
    @Test
    fun add_item_isCorrect() {
        val a = Item(1, 2)

        val list = ManageItem()

        list.add_item(a)
        assertEquals(list.list_of_item.size, 1)

        assertEquals(list.list_of_item.elementAt(0).expiry_date, 1)
        assertEquals(list.list_of_item.elementAt(0).gtin_digit_number, 2)
    }

    /*
    * Insert several items
    * */
    @Test
    fun add_several_item() {
        val list = ManageItem()
        for (i in 0..10) {
            list.add_item(Item(i, i))
        }

       assertEquals(list.list_of_item.size, 11)
    }


    /*
    * Insert several item in an unsorted place
    * */
    @Test
    fun add_element_in_unsorted_rank()
    {
        val list = ManageItem()

        for (i in 10 downTo 1)
        {
            list.add_item(Item(i, i))
        }

        var tmp =  list.sort_item(0)
        assertEquals(tmp.size, 0)

        for (i in 1..10)
        {
            tmp =  list.sort_item(i)
            assertEquals(tmp.size, 1)
        }
    }

    /*
    * Add same element and sort the list
    * */
    @Test
    fun add_same_element_but_with_diff_expiry_date()
    {
        val list = ManageItem()

        list.add_item(Item(12, 4))
        list.add_item(Item(13, 5))

        assertEquals(list.list_of_item.size, 2)

        list.add_item(Item(10, 4))
        assertEquals(list.list_of_item.size, 2)
    }
}
