package com.udacity.shoestore.ui

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeListItemBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeStoreViewModel

class ShoeListFragment : Fragment() {

    private var _binding: FragmentShoeListBinding? = null
    private val binding: FragmentShoeListBinding get() = _binding!!
    private var _shoeListItemBinding: ShoeListItemBinding? = null
    private val shoeListItemBinding: ShoeListItemBinding get() = _shoeListItemBinding!!
    private val shoeStoreViewModel by activityViewModels<ShoeStoreViewModel>()
    private lateinit var shoeView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //

        val inflater = LayoutInflater.from(context)
        _shoeListItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.shoe_list_item, null, false)
    }

    fun addView(last: Shoe) {
        val inflater = LayoutInflater.from(context)
        val shoeView = inflater.inflate(R.layout.shoe_list_item, null, false)
        shoeView.findViewById<TextView>(R.id.textViewName).text = last.name
        shoeView.findViewById<TextView>(R.id.textViewCompany).text = last.company
        shoeView.findViewById<TextView>(R.id.textViewSize).text = last.size.toString()
        shoeView.findViewById<TextView>(R.id.textViewDescription).text = last.description
        binding.linearLayoutShoeList.addView(shoeView)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        shoeStoreViewModel.shoeList.observe(viewLifecycleOwner) {
            if (!it.isEmpty()) {
                it.forEach { shoeItem -> addView(shoeItem) }
            }
        }
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )
        binding.addFab.setOnClickListener {
            it.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        val root: View = binding.root
        return root
    }
}