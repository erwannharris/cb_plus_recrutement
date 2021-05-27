package com.example.Item

import fr.epita.android.cb_plus_test_technique_v3.BuildConfig
import java.io.File

class ManageItem {

    val list_of_item : MutableList<Item> = mutableListOf<Item>()
    val filename : String = "tmp.txt"

    /*
    * Load the list from a file where each line contain the gtin and the expiry date
    * following this format <gtin,date>
    * */
    fun load_list_from_a_file()
    {
        File(filename).forEachLine {

            val list : List<String> = it.split(",")
            val gtin : String =  list[0]
            val date : String = list[1]
            list_of_item.add(Item(gtin, date))
        }
    }


    /*
    * Create a file which contains all the item in the list
    * */
    fun save_list_to_a_file() {
        var myfile = File(filename)
        myfile.delete()
        if (BuildConfig.DEBUG && myfile.exists()) {
            error("Assertion failed: file was not deleted")
        }
        myfile = File(filename)

        for (elm in list_of_item) {
            myfile.printWriter().use { out ->
                out.println(elm.gtin_digit_number + "," + elm.expiry_date)
            }
        }
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
                if (elm.expiry_date != item.expiry_date)
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

    /*
    * Print list for debug
    * */
    fun print_list_of_item()
    {
        list_of_item.forEach { println("expiry_date : " + it.expiry_date +"  gtin_number  " + it.gtin_digit_number  ) }
    }

    /*
    * Convert list to String for a better output
    * */
    fun list_to_string() : String
    {

        var result = ""

        list_of_item.forEach {
            result += "Expiry Date : " + it.expiry_date + "   Gtin Number : " + it.gtin_digit_number + "\n"
        }

        return result
    }



}
/*
* Format Date if needed : 25112000  => 25/11/2000
* */
fun format_string_if_needed(string: String) : String =
    (string[0] + string[1].toString() + '/' + string[2] + string[3] + '/' + string[4] + string[5] + string[6] + string[7])

fun isNUmber(string: String): Boolean {
    return string.filter { it in '0'..'9' }.length == string.length
}

/*
* Check if format is good
* */
fun check_format(string: String) : Boolean
{
    var new_string = string

    new_string = if (string.contains("/"))
        string.replace("/", "")
    else
        string

    if (new_string.length != 8)
        return false

    return isNUmber(new_string)
}