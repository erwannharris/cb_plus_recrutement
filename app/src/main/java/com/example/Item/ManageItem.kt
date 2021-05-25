package com.example.Item

public class ManageItem {

    val list_of_item : MutableList<Item> = mutableListOf<Item>()

    /*
    * Return the expiry date of the item with the same gtin
    * -1 is consider as an error
    * */

    fun get_expiry_date(gtin : Int): Int {
        for (elm in list_of_item)
        {
            if (elm.gtin_digit_number == gtin)
                return elm.gtin_digit_number
        }
        return -1
    }


    /*
    * return True if the item is already present and the update is
    * successful
    * */
    fun add_item(item: Item) {

        var present = false
        for (elm in list_of_item)
        {
            /*
            * update the expiry date if the item is already in the
            * list (base on the fact that each product possess a unique
            * gtin number
            * */
            if (elm.gtin_digit_number == item.gtin_digit_number)
            {
                present = true
                /*
                * Check if the user is not adding the same item twice
                * */
                if (elm.gtin_digit_number != item.gtin_digit_number)
                {
                    elm.expiry_date = item.expiry_date
                }
            }
        }

        /*
        * Add new element if the item does not match any gtin number
        * in the list of product
        * */
        if (!present)
            list_of_item.add(item)
    }

    fun sort_item(gtin_number : Int): MutableList<Item> {

        /*
        * create a temporary list of Item which match the gtin_number
        * */
        val tmp = mutableListOf<Item>()

        list_of_item.forEach {
            if (it.gtin_digit_number == gtin_number)
                tmp.add(it)
        }

        /*
        * sort the list by expiry date
        * */
        tmp.sortBy { it.expiry_date }

        return tmp
    }
}