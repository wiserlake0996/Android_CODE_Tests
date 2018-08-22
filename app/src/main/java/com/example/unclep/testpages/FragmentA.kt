package com.example.unclep.testpages

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentA.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragmentA.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FragmentA : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Credentials? = null
    private var param2: String? = null
    private var listener: OnFragmentAInteractionListener? = null

    lateinit var email:TextInputEditText

    lateinit var password:TextInputEditText
    lateinit var phoneNumber:TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Credentials?
            param2 = it.getString(ARG_PARAM2)
        }

        println("The Contact Information")
        println(param1)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater!!.inflate(R.layout.fragment_a, container, false)
        val btn: Button = view.findViewById(R.id.next)



        email = view.findViewById(R.id.credentialsEmail)
        password = view.findViewById(R.id.credentialsPassword)
        phoneNumber = view.findViewById(R.id.credentialsPhoneNumber)


        btn.setOnClickListener{

           // if(param1 == null){
                param1 = Credentials("email.text.toString()", password.text.toString(), phoneNumber.text.toString())
          //  }
            param1?.let {
                param1 -> onButtonPressed(1, param1)
            }


        }


        return view

    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Int, credentials: Credentials) {
        listener?.onFragmentAInteraction(1, credentials)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentAInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentAInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentAInteraction(uri: Int, credentials: Credentials)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentA.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Credentials?, param2: String) =
                FragmentA().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
