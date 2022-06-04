package com.example.spacepicture.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.spacepicture.R
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PictureByDate : Fragment() {

    private lateinit var  bottomSheetBehavior : BottomSheetBehavior<ConstraintLayout>
    private lateinit var bottomSheetHeader : TextView

    private lateinit var bottomSheetDescription : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
    }

    private fun setBottomSheetBehavior(bottomSheet : ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetHeader = bottomSheet.findViewById(R.id.bottom_sheet_description_header)
        bottomSheetDescription = bottomSheet.findViewById(R.id.bottom_sheet_description)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = PictureByDate()
    }
}