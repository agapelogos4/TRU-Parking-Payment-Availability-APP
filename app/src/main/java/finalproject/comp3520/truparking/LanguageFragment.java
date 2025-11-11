// File: LanguageFragment.java
// Author: Zechariah Montgomery
// Description: Bottom sheet fragment displaying a list of available languages
//              uses a RecyclerView and LanguageAdapter to show selectable language items from the LanguageItem Class
// Date: [08/18/2025]

//If you want to create a new langauge start in this class and add to the items list see below.
//then you want to create a new strings file with the new language for each string, and you want to update the LanguageManager class.

package finalproject.comp3520.truparking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

/*
 * LanguageFragment extends BottomSheetDialogFragment.
 * displays a recyclerview containing available language options.
 * the user can select a language frp, the recyclerview, which updates app preferences and locale via the LanuageManager class see LanguageAdapter class for logic.
 */
public class LanguageFragment extends BottomSheetDialogFragment
{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_language, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.languageRecyclerview);

        List<languageItem> items = new ArrayList<>();
        items.add(new languageItem("English"));
        items.add(new languageItem("French"));
        items.add(new languageItem("Spanish"));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new LanguageAdapter(getContext(), items));//passes the current context and current list of items or langauges to the LanguageAdapter class

        return view;
    }
}