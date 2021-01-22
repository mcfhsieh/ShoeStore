package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoelistBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var binding:FragmentShoelistBinding
    lateinit var mainLayout:ViewGroup
    //val args by navArgs<ShoeListFragmentArgs>()
    lateinit var shoe: Shoe

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoelist,
            container,
            false
        )
        val viewModel by activityViewModels<ShoeViewModel>()

        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }
        mainLayout = binding.shoeLinearLayout

        viewModel.listOfShoes.observe(viewLifecycleOwner, { it ->
            it.forEach{ shoe ->
                mainLayout.addView(setNewShoeItem(shoe))
            }
            viewModel.shoeCreated.value = false
            binding.executePendingBindings()
        })
        return binding.root
    }


    private fun setNewShoeItem(shoe:Shoe): TextView{
        val tv = TextView(requireContext(),null, 0, R.style.shoe_items)
        tv.layoutParams = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        tv.text = getString(R.string.shoe_details, shoe.name, shoe.company, shoe.size.toString(), shoe.description )
        return tv
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                ||super.onOptionsItemSelected(item)
    }
}