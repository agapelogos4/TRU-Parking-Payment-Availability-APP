// File: fragment_account_information.java
// Author: Zechariah Montgomery
// Description: Bottom sheet fragment displays account information in a bottom sheet dialog fragment.
// Date: [08/18/2025]

package com.tru.truparkingpaymentavailabilityapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/*
 * fragment_account_information extends BottomSheetDialogFragment.
 * Inflates the layout 'fragment_account_information' when created.
 *
 *
 * ---> Currently, this fragment does not handle any user interactions. <---
 *
 *
 */

public class fragment_account_information extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_account_information, container, false);

        return view;
    }

}