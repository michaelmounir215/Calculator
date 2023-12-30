package com.example.calc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import com.example.calc.R
import com.example.calc.databinding.FragmentNameBinding


class NameFragment : Fragment() {

    private lateinit var navControl: NavController
    private lateinit var binding: FragmentNameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNameBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register()

    }


    private fun register() {
        binding.nextBtn.setOnClickListener {
            val name = binding.nameEt.text.toString()
            if (name.isNotEmpty()){
                binding.progressBar.visibility = View.VISIBLE
                Toast.makeText(context, "Hello $name", Toast.LENGTH_SHORT).show()
                navControl.navigate(R.id.action_nameFragment_to_calcFragment)
            }else{
                Toast.makeText(context, "Name Cannot be empty", Toast.LENGTH_SHORT).show()
            }


        }
    }

}