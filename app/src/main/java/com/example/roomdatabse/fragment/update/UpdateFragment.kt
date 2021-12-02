package com.example.roomdatabse.fragment.update

import android.icu.number.IntegerWidth
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdatabse.R
import com.example.roomdatabse.model.User
import com.example.roomdatabse.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import org.w3c.dom.Text

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel  = ViewModelProvider(this).get(UserViewModel::class.java)

        view.etUpdFirstName.setText(args.currentUser.firstName)
        view.etUpdLastName.setText(args.currentUser.lastName)
        view.etUpdAge.setText(args.currentUser.age.toString())

        view.btnUpdAdd.setOnClickListener{
            UpdateItem()
        }

        return view
    }

    private fun UpdateItem(){
        val firstName = etUpdFirstName.text.toString()
        val lastName = etUpdLastName.text.toString()
        val age = Integer.parseInt(etUpdAge.text.toString())

        if(inputCheck(firstName, lastName, etUpdAge.text)){
            val updatedUser = User(args.currentUser.id, firstName, lastName, age)
            mUserViewModel.updateUser(updatedUser)

            Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(), "Please Fill all the fields", Toast.LENGTH_SHORT).show()

        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}