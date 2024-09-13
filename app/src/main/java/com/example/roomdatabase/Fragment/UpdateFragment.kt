package com.example.roomdatabase.Fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdatabase.R
import com.example.roomdatabase.model.StudentTable
import com.example.roomdatabase.viewmodel.UserViewModel
import java.nio.file.attribute.AclEntry.Builder


@Suppress("DEPRECATION")
class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Initialize the TextViews and Button
        val update_firstname: TextView = view.findViewById(R.id.updatefirstname)
        val update_lastname: TextView = view.findViewById(R.id.updatelastname)
        val update_age: TextView = view.findViewById(R.id.updateage)
        val update_btn: Button = view.findViewById(R.id.updatebtn)

        // Set the text from arguments
        update_firstname.text = args.currentuser.firstname
        update_lastname.text = args.currentuser.lastname
        update_age.text = args.currentuser.age.toString()

        update_btn.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        // Get updated values from TextViews
        val FirstName = view?.findViewById<TextView>(R.id.updatefirstname)?.text.toString()
        val LastName = view?.findViewById<TextView>(R.id.updatelastname)?.text.toString()
        val Age = view?.findViewById<TextView>(R.id.updateage)?.text.toString().toInt()

        if (inputCheck(FirstName, LastName, Age.toString())) {
            //Create User Object
            val UpadteUser = StudentTable(args.currentuser.id, FirstName, LastName, Age)
            //Update Current User
            mUserViewModel.updateUser(UpadteUser)

            Toast.makeText(requireContext(), "Successfully Updated data", Toast.LENGTH_SHORT).show()

            //Navigate back to list fragment After updating
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Failed to Update data", Toast.LENGTH_SHORT).show()

        }
    }

    // Function to validate user input
    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return !(firstName.isEmpty() || lastName.isEmpty() || age.isEmpty())
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentuser)
            Toast.makeText(
                requireContext(),
                "Successfully removed ${args.currentuser}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setMessage("Are you sure you wanna delete ${args.currentuser.firstname}")
        builder.setTitle("Delete ${args.currentuser.firstname}")
        builder.show()
    }
}
