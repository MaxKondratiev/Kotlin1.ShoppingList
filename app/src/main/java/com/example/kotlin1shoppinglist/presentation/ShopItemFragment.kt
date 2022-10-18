package com.example.kotlin1shoppinglist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin1shoppinglist.R
import com.example.kotlin1shoppinglist.domain.ShopingItem
import com.google.android.material.textfield.TextInputLayout

class ShopItemFragment(
    private val screenMode: String = UNKNOWN_MODE ,
            private val shopItemId : Int = ShopingItem.UNDEFINED_ID
): Fragment() {

    private lateinit var viewModel: ShopItemViewModel
    private lateinit var tVName: TextInputLayout
    private lateinit var tVCount: TextInputLayout
    private lateinit var editTextName: EditText
    private lateinit var editTextCount: EditText
    private lateinit var buttonSave: Button



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shop_item, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseParams()
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        initViews(view)
        addChangeTextListeners()
        when (screenMode) {
            EDIT_MODE -> launchEditMode()
            ADD_MODE -> launchAddMode()
        }

        observersViewModel()
//

    }

        private fun observersViewModel() {
            viewModel.errorInputCount.observe(viewLifecycleOwner) {

                val message = if (it) {
                    getString(R.string.error_input)
                } else {
                    null
                }
                tVCount.error = message.toString()
            }
            viewModel.errorInputName.observe(viewLifecycleOwner) {

                val message = if (it) {
                    getString(R.string.error_input)
                } else {
                    null
                }
                tVName.error = message.toString()
            }
            viewModel.isTimeToFinishScreen.observe(viewLifecycleOwner) {
                activity?.onBackPressed()
            }
        }

        private fun launchEditMode() {
            viewModel.getShopItem(shopItemId)
            viewModel.shopItem.observe(viewLifecycleOwner) {
                editTextName.setText(it.name)
                editTextCount.setText(it.count.toString())
            }
            buttonSave.setOnClickListener {
                viewModel.editShopItem(
                    editTextName.text?.toString(),
                    editTextCount.text?.toString()
                )
            }
        }

        private fun launchAddMode() {

            buttonSave.setOnClickListener {
                viewModel.addShopItem(editTextName.text?.toString(), editTextCount.text?.toString())
            }
        }


        private fun parseParams() {
            if (screenMode != EDIT_MODE && screenMode != ADD_MODE) {
                throw RuntimeException("Unknown mode")
            }
            if (screenMode == EDIT_MODE && shopItemId == ShopingItem.UNDEFINED_ID) {
                throw RuntimeException("Unknown ItemID")
            }

            }



        private fun initViews(view: View) {
            tVName = view.findViewById(R.id.til_name)
            tVCount = view.findViewById(R.id.til_count)
            editTextName = view.findViewById(R.id.et_name)
            editTextCount = view.findViewById(R.id.et_count)
            buttonSave = view.findViewById(R.id.save_button)
        }

        private fun addChangeTextListeners() {
            editTextName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    viewModel.resetErrorInputName()
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })
            editTextCount.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    viewModel.resetErrorInputCount()
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })
        }


        companion object {
            private const val EXTRA_SCREEN_MODE = "extra_mode"
            private const val EXTRA_ITEM_ID = "Item_id"

            private const val EDIT_MODE = "mode_edit"
            private const val ADD_MODE = "mode_add"
            private const val UNKNOWN_MODE = ""


            fun newIntentAddItem(context: Context): Intent {
                val intent = Intent(context, ShopItemActivity::class.java)
                intent.putExtra(EXTRA_SCREEN_MODE, ADD_MODE)
                return intent
            }

            fun newIntentEditItem(context: Context, itemId: Int): Intent {
                val intent = Intent(context, ShopItemActivity::class.java)
                intent.putExtra(EXTRA_SCREEN_MODE, EDIT_MODE)
                intent.putExtra(EXTRA_ITEM_ID, itemId)
                return intent
            }

            fun newInstanceAddItem() : ShopItemFragment{
                return ShopItemFragment(ADD_MODE)
            }
            fun newInstanceEditItem(itemId: Int) : ShopItemFragment{
                return ShopItemFragment(EDIT_MODE, itemId)
            }

        }
    }
