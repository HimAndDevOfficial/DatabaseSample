package com.example.mvvmproject.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mvvmproject.R
import com.example.mvvmproject.databinding.FragmentAddBinding
import com.example.mvvmproject.databinding.FragmentShowBinding
import com.example.mvvmproject.model.Employee
import com.example.mvvmproject.viewModel.EmployeeViewModel
import kotlinx.coroutines.launch


class AddFragment : Fragment() {

    private lateinit var binding : FragmentAddBinding

    //private lateinit var employeeViewModel :EmployeeViewModel

    val employeeViewModel: EmployeeViewModel by activityViewModels()  //

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentAddBinding.inflate(inflater,container,false)

        //ViewModelStoreOwner it the scope that own ViewModelStore
        //ViewModeStore it is the class to store ViewModels
     //   employeeViewModel = ViewModelProvider(requireActivity()).get(EmployeeViewModel::class.java)

        binding.saveRecord.setOnClickListener {

             //Event Fired towards viewmodel- means you clicked a button
            val firstName = binding.editTextTextPersonName.text.toString()

           val lastname= binding.editTextTextPersonLastName.text.toString()

            val age = binding.editTextTextPersonAge.text.toString();

            if(inputCheck(firstName,lastname,age))
            {
                val employee = Employee(0,firstName,lastname,Integer.parseInt(age))

                employeeViewModel.addEmployee(employee);

            }
            else
            {
                Toast.makeText(context,"Please enter all Fields",Toast.LENGTH_SHORT).show()
            }

        }

        val listObserver = Observer<Employee>{
            searchEmployee ->
            binding.searchedRecord.text = searchEmployee.firstName +" "+ searchEmployee.lastName+" "+searchEmployee.age
             //   newEmployee -> adapter.setData(newList)  //update ui
        }

        //We will observe the livedata , passing in this activity as lifecycleowner and observer
      binding.apply {
          fetchRecord.setOnClickListener{
              lifecycleScope.launch {
                  employeeViewModel.searchEmployee(binding.enterid.text.toString().toInt()).observe(viewLifecycleOwner,listObserver)
              }

          }
      }
        return binding.root
    }

    private fun inputCheck(first:String,lastName:String,age:String) : Boolean {
        return (first.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty())
    }

    override fun onDestroyView() {
        super.onDestroyView()

        //this you can use to release any resource
    }

}