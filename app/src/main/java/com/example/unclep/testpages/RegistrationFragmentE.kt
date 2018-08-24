package com.example.unclep.testpages

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RegistrationRegistrationFragmentD.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RegistrationRegistrationFragmentD.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RegistrationFragmentE : Fragment() {
    // TODO: Rename and change types of parameters
    private var ownerProfile: OwnerProfile? = null
    private var listener: OnFragmentInteractionListener? = null


    private var tFirstName:TextInputEditText? = null
    private var tLastName:TextInputEditText? = null
    private var tEmail:TextInputEditText? = null
    private var tPhoneNumber:TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ownerProfile = it.getSerializable(ARG_PARAM1) as OwnerProfile?
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater!!.inflate(R.layout.reg_fragment_e, container, false)
        val btn: Button = view.findViewById(R.id.submitButton)

        tEmail = view.findViewById(R.id.ownerEmail)
        tPhoneNumber = view.findViewById(R.id.ownerPhoneNumber)
        tFirstName = view.findViewById(R.id.ownerFirstName)
        tLastName = view.findViewById(R.id.ownerLastName)


        if(ownerProfile != null){
            tEmail?.setText(ownerProfile?.email)
            tPhoneNumber?.setText(ownerProfile?.phone_number)
            tFirstName?.setText(ownerProfile?.name)
            tLastName?.setText(ownerProfile?.lastName)
        }else{
            ownerProfile = OwnerProfile("","","","")
        }

        btn.setOnClickListener{
            collectDataFromInputs()
            onButtonPressed(ownerProfile)
        }

        return view
    }

    fun collectDataFromInputs(){
        ownerProfile?.let {
            it.lastName = tLastName?.text.toString()
            it.name = tFirstName?.text.toString()
            it.phone_number = tPhoneNumber?.text.toString()
            it.email = tEmail?.text.toString()
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(ownerProfile: OwnerProfile?) {
        listener?.onRegistrationFragmentEInteraction(ownerProfile!!)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
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
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onRegistrationFragmentEInteraction(ownerProfile: OwnerProfile)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegistrationRegistrationFragmentD.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(ownerProfile: OwnerProfile?) =
                RegistrationFragmentE().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PARAM1, ownerProfile)
                    }
                }
    }
}
