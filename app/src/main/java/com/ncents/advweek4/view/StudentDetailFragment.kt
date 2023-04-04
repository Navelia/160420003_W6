package com.ncents.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.ncents.advweek4.R
import com.ncents.advweek4.util.loadImage
import com.ncents.advweek4.viewmodel.DetailViewModel

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        if(arguments != null){
            val studentID = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId

            viewModel.fetch(studentID)
            observeViewModel(view)
        }
    }

    fun observeViewModel(view:View){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer{
            view.findViewById<ImageView>(R.id.imageView2).loadImage(it.photoUrl, view.findViewById<ProgressBar>(R.id.progressBar2))
            view.findViewById<TextInputEditText>(R.id.txtID).setText(it.id)
            view.findViewById<TextInputEditText>(R.id.txtName).setText(it.name)
            view.findViewById<TextInputEditText>(R.id.txtBod).setText(it.bod)
            view.findViewById<TextInputEditText>(R.id.txtPhone).setText(it.phone)
        })
    }
}