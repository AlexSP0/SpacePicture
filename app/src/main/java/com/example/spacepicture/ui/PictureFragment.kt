package com.example.spacepicture.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.spacepicture.R
import com.example.spacepicture.contracts.MainContracts
import com.example.spacepicture.data.NasaImageResponse
import com.example.spacepicture.presenters.PictureFragmentPresenter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class PictureFragment() : Fragment(), MainContracts.MainView {

    private val presenter : MainContracts.MainPresenter = PictureFragmentPresenter()

    private lateinit var image : ImageView

    private lateinit var  bottomSheetBehavior : BottomSheetBehavior<ConstraintLayout>

    private lateinit var bottomSheetHeader : TextView

    private lateinit var bottomSheetDescription : TextView

    private lateinit var input_wiki_text_layout : TextInputLayout

    private lateinit var input_text_search_wiki : TextInputEditText

    private lateinit var chipYesterday : Chip

    private lateinit var chipToday : Chip

    private lateinit var chipTomorrow : Chip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_picture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        image = view.findViewById(R.id.image)
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        initChips(view)
        initSearchWikiIcon(view)
        setHasOptionsMenu(true)
        presenter.getDailyImage()
    }

    private fun initChips(view : View) {
        chipYesterday = view.findViewById<Chip>(R.id.chip_yesterday);
        chipYesterday.setOnClickListener {
            presenter.getDailyImageByDate(takeDate(-1))
        }
        chipToday = view.findViewById<Chip>(R.id.chip_today);
        chipToday.setOnClickListener {
            presenter.getDailyImageByDate(takeDate(0))
        }
        chipTomorrow = view.findViewById<Chip>(R.id.chip_before_yesterday);
        chipTomorrow.setOnClickListener {
            presenter.getDailyImageByDate(takeDate(-2))
        }
    }

    private fun initSearchWikiIcon(view : View) {
        input_wiki_text_layout = view.findViewById<TextInputLayout>(R.id.input_text_search_wiki_layout)
        input_text_search_wiki = view.findViewById<TextInputEditText>(R.id.input_edit_text_search_wiki)
        input_wiki_text_layout.setEndIconOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val url = "https://en.wikipedia.org/wiki/${input_text_search_wiki.text.toString()}"
            val uri = Uri.parse(url)
            intent.data = uri
            startActivity(intent)
        }
    }

    private fun setBottomSheetBehavior(bottomSheet : ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetHeader = bottomSheet.findViewById(R.id.bottom_sheet_description_header)
        bottomSheetDescription = bottomSheet.findViewById(R.id.bottom_sheet_description)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun setImage(imageInfo : NasaImageResponse) {
        Glide.with(this).load(imageInfo.url).placeholder(R.drawable.film).into(image)
        bottomSheetHeader.text = imageInfo.title
        bottomSheetDescription.text = imageInfo.explanation

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    private fun takeDate( count : Int): String {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_MONTH, count)
        val format1 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format1.timeZone = TimeZone.getTimeZone("EST")
        return format1.format(currentDate.time)
    }

}