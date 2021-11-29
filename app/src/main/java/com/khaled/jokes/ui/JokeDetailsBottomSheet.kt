package com.khaled.jokes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.khaled.jokes.R
import com.khaled.jokes.data.model.JokeItem
import com.khaled.jokes.util.Constants.JOKES_DETAILS_KEY
import kotlinx.android.synthetic.main.layout_joke_details_bottom_sheet.*

class JokeDetailsBottomSheet : BottomSheetDialogFragment() {
    lateinit var jokeItem: JokeItem
    fun newInstance(jokeItem: JokeItem): JokeDetailsBottomSheet {
        val args = Bundle()
        args.putSerializable(JOKES_DETAILS_KEY, jokeItem)
        val fragment = JokeDetailsBottomSheet()
        fragment.arguments = args
        return fragment
    }
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jokeItem = arguments?.getSerializable(JOKES_DETAILS_KEY) as JokeItem
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_joke_details_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindArgument(jokeItem)
    }

    private fun bindArgument(jokeItem: JokeItem) {
        populateUI(jokeItem)
    }

    private fun populateUI(jokeItem: JokeItem) {
        idValue.text = jokeItem.id.toString()
        typeValue.text = jokeItem.type
        setupValue.text = jokeItem.setup
        deliveryValue.text = jokeItem.delivery
    }
}
