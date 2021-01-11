package com.udacity.shoestore

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoelistBinding

class ShoeListFragment : Fragment() {

    private lateinit var binding:FragmentShoelistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoelist, container, false)
        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }
        return binding.root
    }
}