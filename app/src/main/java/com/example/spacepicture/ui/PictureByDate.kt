package com.example.spacepicture.ui

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.spacepicture.R
import com.example.spacepicture.contracts.MainContracts
import com.example.spacepicture.data.NasaImageResponse
import com.example.spacepicture.presenters.ByDatePresenterImpl
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.text.SimpleDateFormat
import java.util.*

class PictureByDate : Fragment(), MainContracts.MainView {

    private lateinit var  bottomSheetBehavior : BottomSheetBehavior<ConstraintLayout>
    private lateinit var bottomSheetHeader : TextView
    private lateinit var bottomSheetDescription : TextView

    private val presenter : MainContracts.ByDatePresenter = ByDatePresenterImpl()

    private lateinit var image : ImageView

    private var date : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture_by_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        image = view.findViewById(R.id.image)
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        checkDate()
    }

    private fun setBottomSheetBehavior(bottomSheet : ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetHeader = bottomSheet.findViewById(R.id.bottom_sheet_description_header)
        bottomSheetDescription = bottomSheet.findViewById(R.id.bottom_sheet_description)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    companion object {
        @JvmStatic
        fun newInstance() = PictureByDate()
    }

    override fun setImage(response: NasaImageResponse) {
        Glide.with(this).load(response.url).placeholder(R.drawable.film).into(image)
        bottomSheetHeader.text = response.title
        bottomSheetDescription.text = response.explanation
    }

    fun checkDate() {
        if(date == null) {
            showDateDialog()
        } else {
            presenter.getDailyImageByDate(date!!)
        }
    }

    private fun showDateDialog() {
        val calendar = Calendar.getInstance(Locale.getDefault())
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day  = calendar.get(Calendar.DAY_OF_MONTH)
        val dlg = DatePickerDialog(requireActivity(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val simpleDateFormat = SimpleDateFormat("YYYY-MM-dd", Locale.getDefault())
            calendar.set(year, month, dayOfMonth)
            val chosenTime = simpleDateFormat.format(calendar.time)
            this.date = chosenTime
            presenter.getDailyImageByDate(chosenTime)
        }, year, month, day).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}