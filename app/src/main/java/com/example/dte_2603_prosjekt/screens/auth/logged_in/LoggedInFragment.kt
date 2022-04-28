package com.example.dte_2603_prosjekt.screens.auth.logged_in

import android.os.Bundle
import android.view.*

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.dte_2603_prosjekt.R
import com.example.dte_2603_prosjekt.databinding.FragmentMainScreenBinding
import com.example.dte_2603_prosjekt.screens.auth.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import java.util.zip.Inflater


class LoggedInFragment : Fragment() {
    private lateinit var binding: FragmentMainScreenBinding
    private val viewModel: AuthViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setHasOptionsMenu(true)

        // Handle buttton for going to MapFragment
        val btnSeLuftkvalitet = binding.btnSeLuftkvalitet
        val action = LoggedInFragmentDirections.actionMainScreenFragmentToMapFragment()
        btnSeLuftkvalitet.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(action)
        }

        viewModel.currentUser.observe(viewLifecycleOwner) {
            if (null == it) {
                findNavController()
                    .navigate(LoggedInFragmentDirections.actionMainScreenFragmentToLoginFragment())
            }
        }

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_about -> {
                this.findNavController().navigate(
                    LoggedInFragmentDirections.actionMainScreenFragmentToAboutFragment()
                )
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}