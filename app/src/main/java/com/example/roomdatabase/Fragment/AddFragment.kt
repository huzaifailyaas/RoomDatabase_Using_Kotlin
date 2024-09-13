package com.example.roomdatabase.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdatabase.R
import com.example.roomdatabase.model.StudentTable
import com.example.roomdatabase.viewmodel.UserViewModel

class AddFragment : Fragment() {

    private lateinit var aUserViewModel: UserViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        aUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        // Find views for the EditTexts and Button
        val firstNameEditText = view.findViewById<EditText>(R.id.updatefirstname)
        val lastNameEditText = view.findViewById<EditText>(R.id.updatelastname)
        val ageEditText = view.findViewById<EditText>(R.id.updateage)
        val addButton = view.findViewById<Button>(R.id.updatebtn)

        // Set click listener for the add button
        addButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val age = ageEditText.text.toString()

            insertDataToDatabase(firstName, lastName, age)
        }

        return view
    }

    private fun insertDataToDatabase(firstName: String, lastName: String, age: String) {
        // Validate the input fields
        if (inputCheck(firstName, lastName, age)) {
            // Create User object
            val user = StudentTable(0, firstName, lastName, Integer.parseInt(age))

            // Add data to the database via ViewModel
            aUserViewModel.addUser(user)

            // Show a success message
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()

            // Navigate back to the previous fragment or list
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            // Show an error message
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
                .show()
        }
    }

    // Function to validate user input
    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return !(firstName.isEmpty() || lastName.isEmpty() || age.isEmpty())
    }
}