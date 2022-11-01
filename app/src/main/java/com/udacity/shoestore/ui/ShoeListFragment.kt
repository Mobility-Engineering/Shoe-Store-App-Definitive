package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeListItemBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeStoreViewModel

class ShoeListFragment : Fragment() {
    private lateinit var navController: NavController
    private val desired_width = 256
    private var _binding: FragmentShoeListBinding? = null
    private val binding: FragmentShoeListBinding get() = _binding!!
    private var _shoeListItemBinding: ShoeListItemBinding? = null
    private val shoeListItemBinding: ShoeListItemBinding get() = _shoeListItemBinding!!
    private val shoeStoreViewModel by activityViewModels<ShoeStoreViewModel>()
    private lateinit var shoeView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        shoeStoreViewModel.shoeListFragmentActive = true
        val inflater = LayoutInflater.from(context)
        _shoeListItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.shoe_list_item, null, false)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        navController = container?.findNavController() as NavController
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
            //it.findNavController()
            navController.navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
        val root: View = binding.root
        return root
    }


    fun addView(last: Shoe) {
        val inflater = LayoutInflater.from(context)
        val shoeView = inflater.inflate(R.layout.shoe_list_item, null, false)
        shoeView.findViewById<TextView>(R.id.textViewName).text = last.name
        shoeView.findViewById<TextView>(R.id.textViewCompany).text = last.company
        shoeView.findViewById<TextView>(R.id.textViewSize).text = last.size.toString()
        shoeView.findViewById<TextView>(R.id.textViewDescription).text = last.description
        binding.linearLayoutShoeList.addView(shoeView)
        val separator = View(context)

        separator.setLayoutParams(
            LinearLayout.LayoutParams(convertFromDpToPixels(desired_width), 2)
        )
        separator.setBackgroundResource(R.color.colorPrimary)
        binding.linearLayoutShoeList.addView(separator)
    }

    private fun convertFromDpToPixels(desiredWidth: Int): Int {
        val dm = context?.resources?.displayMetrics
        val displayDensity = dm?.density
        val widthInPixels  = displayDensity?.times(desiredWidth)
        var widthInPixelsInt = 0
        widthInPixels?.let{ widthInPixelsInt = it.toInt()}
        return widthInPixelsInt
    }

    override fun onResume() {
        shoeStoreViewModel.shoeListFragmentActive = true
        super.onResume()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId

        when (id) {
            R.id.action_logout -> {
                shoeStoreViewModel.shoeListFragmentActive = false
                navController.popBackStack()
                navController.popBackStack()
                navController.popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}