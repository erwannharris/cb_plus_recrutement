package fr.epita.android.cb_plus_test_technique_v3

import com.example.Item.Item
import com.example.Item.ManageItem
import com.example.Item.check_format
import com.example.Item.format_string_if_needed
import org.junit.Assert.assertEquals
import org.junit.Test

class ManageItemTest {

    /*
    * Insert one item
    * */
    @Test
    fun add_item_isCorrect() {
        val a = Item("1", "2")

        val list = ManageItem()

        list.add_item(a)
        assertEquals(list.list_of_item.size, 1)

        assertEquals(list.list_of_item.elementAt(0).expiry_date, "1")
        assertEquals(list.list_of_item.elementAt(0).gtin_digit_number, "2")
    }

    /*
    * Insert several items
    * */
    @Test
    fun add_several_item() {
        val list = ManageItem()
        for (i in 0..10) {
            list.add_item(Item(i.toString(), i.toString()))
        }

        assertEquals(list.list_of_item.size, 11)
    }


    /*
    * Add same element and sort the list
    * */
    @Test
    fun add_same_element_but_with_diff_expiry_date() {
        val list = ManageItem()

        list.add_item(Item("12", "4"))
        list.add_item(Item("13", "5"))

        assertEquals(list.list_of_item.size, 2)

        list.add_item(Item("10", "4"))
        assertEquals(list.list_of_item.size, 2)
    }


    /*
    * Check if an element already in the list has is expiry date
    * has been modified
    * */
    @Test
    fun check_if_expiry_date_is_update() {
        val list = ManageItem()

        list.add_item(Item("12", "4"))
        list.add_item(Item("13", "4"))

        assertEquals(list.list_of_item.size, 1)
        assertEquals(list.list_of_item[0].expiry_date, "13")
        assertEquals(list.list_of_item[0].gtin_digit_number, "4")
    }


    /*
    * Chef if string to list work (not a real test just the output
    * */
    @Test
    fun chef_string_to_list() {
        val list = ManageItem()
        var j = 10

        for (i in 0..10) {
            list.add_item(Item(j.toString(), i.toString()))
            j--
        }

        list.print_list_of_item()

        System.out.println(list.list_to_string())
    }

    /*
    * Test if Date format is working
    * */
    @Test
    fun change_format()
    {
        assertEquals(format_string_if_needed("25112000"), "25/11/2000")
    }

    /*
    * Test if date format is good
    * */
    @Test
    fun test_check_format()
    {
        assertEquals(check_format("25112000"), true)
        assertEquals(check_format("25/11/2000"), true)

        assertEquals(check_format("25112a00"), false)
        assertEquals(check_format(""), false)
        assertEquals(check_format("25112"), false)
        assertEquals(check_format("25/11/2000a"), false)
    }
}
