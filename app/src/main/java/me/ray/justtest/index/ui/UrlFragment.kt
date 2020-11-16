package me.ray.justtest.index.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import me.ray.justtest.R
import me.ray.justtest.WebActivity
import me.ray.utils.extensions.isRightUrl

/**
 * Description: 社区-关注
 * Author : ray
 */
class UrlFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.frag_url_input, null)
        val btnSearch = view.findViewById<Button>(R.id.btnSearchUrl)
        val etInput = view.findViewById<EditText>(R.id.etInput)
        btnSearch.setOnClickListener {

            if(etInput.text.isEmpty()||!etInput.text.toString().isRightUrl()){
                Toast.makeText(requireContext(),"请输入正确URL",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            WebActivity.start(requireContext(),etInput.text.toString())
        }
        return view;
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            UrlFragment()
    }

}