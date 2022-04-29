package com.prempal.chittoo.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.prempal.chittoo.Activities.LoginActivity;
import com.prempal.chittoo.Activities.WinCertificateActivity;
import com.prempal.chittoo.R;

public class ProfileFragment extends Fragment {

    Button winCertificate, signOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        winCertificate = view.findViewById(R.id.winCertificateButton);
        winCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WinCertificateActivity.class);
                startActivity(intent);
            }
        });

        signOut = view.findViewById(R.id.signOutButton);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }
}