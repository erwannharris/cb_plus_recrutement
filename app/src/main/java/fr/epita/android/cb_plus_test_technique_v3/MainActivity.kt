package fr.epita.android.cb_plus_test_technique_v3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.Item.Item
import com.example.Item.ManageItem
import com.example.Item.check_format

class MainActivity : AppCompatActivity() {

    private lateinit var print_list: Button
    private lateinit var new_gtin: Button
    private lateinit var edit_text_gtin: TextView
    private lateinit var edit_text_date: TextView
    private lateinit var textViewMain: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val List_of_Item = ManageItem()

        textViewMain = findViewById(R.id.TextViewMain)
        edit_text_gtin = findViewById(R.id.editTextNumber)
        edit_text_date = findViewById(R.id.editTextDate)


        print_list = findViewById(R.id.expiry_date_show)

        print_list.setOnClickListener {

            if (List_of_Item.list_of_item.size == 0)
                textViewMain.text = getString(R.string.list_empty)
            else
                textViewMain.text = List_of_Item.list_to_string()

        }

        new_gtin = findViewById(R.id.add_new_gtin)

        new_gtin.setOnClickListener {

            when {
                edit_text_gtin.text.isEmpty() -> {
                    textViewMain.text = getString(R.string.gtin_wrong)
                }
                edit_text_date.text.isEmpty() -> {
                    textViewMain.text = getString(R.string.expiry_wrong)
                }
                else -> {

                    if (!check_format(edit_text_date.text.toString())) {
                        textViewMain.text = getString(R.string.date_wrong)
                    } else {
                        List_of_Item.add_item(
                            Item(
                                (edit_text_date.text.toString()),
                                (edit_text_gtin.text.toString())
                            )
                        )
                        textViewMain.text = ""
                        edit_text_gtin.text = ""
                        edit_text_date.text = ""
                    }

                }
            }

        }

    }
}