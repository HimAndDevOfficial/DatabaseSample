package com.example.mvvmproject.fragments.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mvvmproject.R
import com.example.mvvmproject.databinding.FragmentUpdateBinding
import com.example.mvvmproject.model.Employee
import com.example.mvvmproject.viewModel.EmployeeViewModel
import kotlinx.coroutines.launch

class UpdateFragment : Fragment() {

    private lateinit var binding :FragmentUpdateBinding
    val employeeViewModel:EmployeeViewModel by activityViewModels()
    private val args by navArgs<UpdateFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater,container,false)

        binding.updateEditTextTextPersonName.setText(args.currentEmployee.firstName)
        binding.updateEditTextTextPersonLastName.setText(args.currentEmployee.lastName)
        binding.updateEditTextTextPersonAge.setText(args.currentEmployee.age.toString())

        binding.updateRecord.setOnClickListener {
            updateItem()
        }

        return binding.root
    }

    private fun updateItem() {

        val firstName= binding.updateEditTextTextPersonName.text.toString()
        val lastName = binding.updateEditTextTextPersonLastName.text.toString()
        val age = binding.updateEditTextTextPersonAge.text.toString()

        if(inputCheck(firstName,lastName,age))
        {
            val employee = Employee(args.currentEmployee.id,firstName,lastName,Integer.parseInt(age))

            lifecycleScope.launch {
                employeeViewModel.updateEmployee(employee)
            }

            Toast.makeText(requireContext(),"Updated Successfully",Toast.LENGTH_SHORT).show()

            findNavController().popBackStack()
        }
        else
        {

            Toast.makeText(requireContext(),"Please enter all fields ",Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(first:String,lastName:String,age:String) : Boolean {
        return (first.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty())
    }

}