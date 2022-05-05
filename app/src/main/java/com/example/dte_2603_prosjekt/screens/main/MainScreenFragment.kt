package com.example.dte_2603_prosjekt.screens.main

import android.os.Bundle
import android.view.*

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.dte_2603_prosjekt.R
import com.example.dte_2603_prosjekt.databinding.FragmentMainScreenBinding
import com.example.dte_2603_prosjekt.screens.auth.MainScreenViewModel


class MainScreenFragment : Fragment() {
    private lateinit var binding: FragmentMainScreenBinding
    private val viewModel: MainScreenViewModel by activityViewModels()


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
        val action = MainScreenFragmentDirections.actionMainScreenFragmentToMapsFragment()
        btnSeLuftkvalitet.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(action)
        }

        viewModel.currentUser.observe(viewLifecycleOwner) {
            if (null == it) {
                findNavController()
                    .navigate(MainScreenFragmentDirections.actionMainScreenFragmentToLoginFragment())
            }
        }

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_screen_toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_about -> {
                this.findNavController().navigate(
                    MainScreenFragmentDirections.actionMainScreenFragmentToAboutFragment()
                )
                return true
            }
            R.id.menu_item_settings -> {
                this.findNavController().navigate(
                    MainScreenFragmentDirections.actionMainScreenFragmentToPreferenceFragment()
                )
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}