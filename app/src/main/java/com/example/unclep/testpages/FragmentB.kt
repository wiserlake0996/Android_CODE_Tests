package com.example.unclep.testpages

import android.content.Context
import android.net.Uri
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

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentB.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragmentB.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FragmentB : Fragment() {
    // TODO: Rename and change types of parameters
    private var businessProfile: BusinessProfile? = null
    private var listener: OnFragmentInteractionListener? = null

    private var tName:TextInputEditText? = null
    private var tCategory:TextInputEditText? = null
    private var tDescription:TextInputEditText? = null
    private var tTags:TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            businessProfile = it.getSerializable(ARG_PARAM1) as BusinessProfile?
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_b, container, false)
        val btn: Button = view.findViewById(R.id.next)

        tName = view.findViewById(R.id.businessName)
        tCategory = view.findViewById(R.id.businessCategory)
        tDescription = view.findViewById(R.id.businessDescription)
        tTags = view.findViewById(R.id.businessTags)
        /**
         * Check if credential value is null, if so initialize else set the components with the credentials value
         */
        if(businessProfile != null){
            tName?.setText(businessProfile?.name)
            tCategory?.setText(businessProfile?.category)
            tDescription?.setText(businessProfile?.description)
            val toSt = businessProfile?.tags?.joinToString(",")
            tTags?.setText(toSt)
        }else{
            businessProfile = BusinessProfile("","","",null,null,null)
        }
        /**
         * When the next button is clicked, send data back to the activity and open the next fragment
         */
        btn.setOnClickListener{
            collectDataFromInputs()
            onButtonPressed(2, businessProfile!!)
        }

        return view
    }

    fun collectDataFromInputs(){
        businessProfile?.let {
            it.name = tName?.text.toString()
            it.category = tCategory?.text.toString()
            it.tags = null// tTags?.text.toString()
            it.description = tDescription?.text.toString()

            println("Param Object updated")
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Int, businessProfile: BusinessProfile) {
        listener?.onFragmentBInteraction(2, businessProfile)
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
        fun onFragmentBInteraction(uri: Int, businessProfile: BusinessProfile)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentB.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: BusinessProfile?) =
                FragmentB().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PARAM1, param1)
                    }
                }
    }
}
