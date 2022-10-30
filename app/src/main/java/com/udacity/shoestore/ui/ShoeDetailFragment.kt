package com.udacity.shoestore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeStoreViewModel


class ShoeDetailFragment : Fragment() {
    private var  _binding:FragmentShoeDetailBinding? = null
    private val binding:FragmentShoeDetailBinding get() = _binding!!
    private  val shoeStoreViewModel:ShoeStoreViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.buttonCancel.setOnClickListener{
            it.findNavController().popBackStack()
        }
        binding.buttonSave.setOnClickListener {
            shoeStoreViewModel.addShoe(binding.shoe)
            it.findNavController().popBackStack()
        }

        val root:View = binding.root
        return root
    }
}
