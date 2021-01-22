package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe


class ShoeDetailFragment : Fragment() {

    private lateinit var binding:FragmentShoeDetailBinding
    private var shoe:Shoe = Shoe("", 0.toDouble(), "", "")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.setLifecycleOwner(this)
        val viewModel by activityViewModels<ShoeViewModel>()
        binding.shoe = shoe
        binding.viewModel = viewModel
        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }
        binding.saveButton.setOnClickListener {
        val action = ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment()
            findNavController().navigate(action)
        }

        viewModel.shoeCreated.observe(viewLifecycleOwner, {
            if(it){
                findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
            }
        })

        return binding.root
    }

}