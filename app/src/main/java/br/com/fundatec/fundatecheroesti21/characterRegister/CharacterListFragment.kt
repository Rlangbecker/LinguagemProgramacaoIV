package br.com.fundatec.fundatecheroesti21.characterRegister


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.fundatec.fundatecheroesti21.characterRegister.domain.CharacterRegisterModel
import br.com.fundatec.fundatecheroesti21.characterRegister.view.CharacterListAdapter
import br.com.fundatec.fundatecheroesti21.databinding.FragmentCharacterBinding

private const val ARG_PARAM1 = "param1"


class CharacterListFragment : Fragment() {


    private lateinit var binding: FragmentCharacterBinding

    private val adapter by lazy { CharacterListAdapter() }
    private val list = listOf(
        CharacterRegisterModel("Mestre Kame")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvList.adapter = adapter
        adapter.add(list)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            CharacterListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}