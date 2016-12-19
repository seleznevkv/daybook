package com.elysey.daybook.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.elysey.daybook.MainActivity;
import com.elysey.daybook.R;
import com.elysey.daybook.base.BaseUtil;
import com.elysey.daybook.util.DateUtil;

import java.util.Date;

public class AddNoteFragment extends Fragment {

    EditText etTitleNote, etTextNote;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_note, container, false);

        etTitleNote = (EditText) v.findViewById(R.id.etTitleNote);
        etTextNote = (EditText) v.findViewById(R.id.etTextNote);

        initToolbar();

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_add, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addNote) {
            if (!etTitleNote.getText().toString().equals("")) {

                ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                ((MainActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getResources().getString(R.string.app_name));
                ((MainActivity) getActivity()).setVisibleFab(true);
                ((MainActivity) getActivity()).showSnackbar("Заметка добавлена");

                Date date = new Date(System.currentTimeMillis());

                ContentValues cv = new ContentValues();
                cv.put(BaseUtil.NOTE_TITLE, etTitleNote.getText().toString());
                cv.put(BaseUtil.NOTE_TEXT, etTextNote.getText().toString());
                cv.put(BaseUtil.NOTE_TIME, DateUtil.getCurrentTime(System.currentTimeMillis()));
                cv.put(BaseUtil.NOTE_DATE, DateUtil.getCurrentDate(System.currentTimeMillis()));

                BaseUtil.addBase(getActivity(), BaseUtil.NAME_BASE, cv);

                ListFragment listFragment = (ListFragment) ((MainActivity) getActivity()).getSupportFragmentManager().findFragmentByTag("listNote");
                listFragment.reloadList();

                View view = getActivity().getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                getActivity().onBackPressed();
            } else {
                Toast.makeText(getActivity(), "Введите название заметки", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void initToolbar() {
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Новая запись");
        ((MainActivity) getActivity()).setVisibleFab(false);

        setHasOptionsMenu(true);

    }

    @Override
    public void onDestroy() {
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getResources().getString(R.string.app_name));
        ((MainActivity) getActivity()).setVisibleFab(true);

        super.onDestroy();
    }


}
