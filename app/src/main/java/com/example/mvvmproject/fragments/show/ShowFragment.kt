package com.example.mvvmproject.fragments.show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmproject.R
import com.example.mvvmproject.databinding.FragmentShowBinding
import com.example.mvvmproject.model.Employee
import com.example.mvvmproject.viewModel.EmployeeViewModel
import kotlinx.coroutines.launch


class ShowFragment : Fragment() ,ShowListAdapter.OnDeleteClickListener  {

    private lateinit var binding : FragmentShowBinding

    val employeeViewModel: EmployeeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentShowBinding.inflate(inflater,container,false)

        val adapter = ShowListAdapter()
        adapter.onDeleteClickListener= this

        val recyclerView = binding.recyclerView

        recyclerView.adapter= adapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Creating the observer which will update the UI
        val listObserver = Observer<List<Employee>> {
            newList -> adapter.setData(newList)  //update ui
        }

        //We will observe the livedata , passing in this activity as lifecycleowner and observer
        employeeViewModel.readAllData.observe(viewLifecycleOwner,listObserver)

        return binding.root
    }

    override fun onEmployeeDeleteClick(employee: Employee) {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.setTitle("Deleting the employee")

        alertDialog.setMessage("Are you sure you want to delete the employee?")

        alertDialog.setPositiveButton("Yes") {dialog, hello ->
            lifecycleScope.launch {
            employeeViewModel.deleteUser(employee)
            dialog.dismiss()
        }

        }
        alertDialog.setNegativeButton("No") {dialog, hello ->
            dialog.dismiss()
        }

        val alertDialogShow = alertDialog.create()

        alertDialogShow.show()

//        lifecycleScope.launch {
//            employeeViewModel.deleteUser(employee)
//        }

    }

}